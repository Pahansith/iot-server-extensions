/*
 * Copyright (c) 2014, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.wso2.carbon.device.mgt.iot.arduino.api;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.device.mgt.common.DeviceManagementException;
import org.wso2.carbon.device.mgt.iot.arduino.api.util.DeviceJSON;
import org.wso2.carbon.device.mgt.iot.arduino.api.util.MqttArduinoSubscriber;
import org.wso2.carbon.device.mgt.iot.arduino.constants.ArduinoConstants;
import org.wso2.carbon.device.mgt.iot.common.devicecloud.DeviceController;
import org.wso2.carbon.device.mgt.iot.common.devicecloud.config.DeviceCloudConfigManager;
import org.wso2.carbon.device.mgt.iot.common.devicecloud.config.DeviceCloudManagementConfig;
import org.wso2.carbon.device.mgt.iot.common.devicecloud.config
        .DeviceCloudManagementControllerConfig;
import org.wso2.carbon.device.mgt.iot.common.devicecloud.config.controlqueue
        .DeviceControlQueueConfig;
import org.wso2.carbon.device.mgt.iot.common.devicecloud.datastore.bam.BAMStreamDefinitions;
import org.wso2.carbon.device.mgt.iot.common.devicecloud.exception.DeviceControllerException;
import org.wso2.carbon.device.mgt.iot.common.devicecloud.exception.UnauthorizedException;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.*;

public class ArduinoControllerService {

	private static Log log = LogFactory.getLog(ArduinoControllerService.class);

	public static final String CONTROL_QUEUE_ENDPOINT;
	private static Map<String, LinkedList<String>> replyMsgQueue = new HashMap<>();
	private static Map<String, LinkedList<String>> internalControlsQueue = new HashMap<>();
	private static MqttArduinoSubscriber mqttArduinoSubscriber;
//Todo first call create a instance;

	static {

		DeviceCloudManagementConfig config = null;
		try {
			config = DeviceCloudConfigManager.getInstance().getDeviceCloudMgtConfig();
		} catch (DeviceControllerException ex) {
			log.error(ex.getMessage());
		}

		if (config != null) {
			// controller configurations
			DeviceCloudManagementControllerConfig controllerConfig =
					config.getDeviceCloudManagementControllerConfig();

			// reading control queue configurations
			String controlQueueKey = controllerConfig.getDeviceControlQueue();
			DeviceControlQueueConfig controlQueueConfig = config.getControlQueuesMap().get(
					controlQueueKey);
			if (controlQueueConfig == null) {
				log.error("Error occurred when trying to read control queue configurations");
			}

			String mqttUrl = "";
			String mqttPort = "";
			if (controlQueueConfig != null) {
				mqttUrl = controlQueueConfig.getEndPoint();
				mqttPort = controlQueueConfig.getPort();
			}

			CONTROL_QUEUE_ENDPOINT = mqttUrl + ":" + mqttPort;
			log.info("CONTROL_QUEUE_ENDPOINT Successfully initialized.");
		} else {
			CONTROL_QUEUE_ENDPOINT = null;
			log.error("CONTROL_QUEUE_ENDPOINT initialization failed.");
		}

	}

	public void setMqttArduinoSubscriber(MqttArduinoSubscriber mqttArduinoSubscriber) {
		ArduinoControllerService.mqttArduinoSubscriber = mqttArduinoSubscriber;
		try {
			mqttArduinoSubscriber.subscribe();
		} catch (DeviceManagementException e) {
			log.error(e.getErrorMessage());
		}
	}

	public MqttArduinoSubscriber getMqttArduinoSubscriber() {
		return mqttArduinoSubscriber;
	}

	public static Map<String, LinkedList<String>> getReplyMsgQueue() {
		return replyMsgQueue;
	}

	public static Map<String, LinkedList<String>> getInternalControlsQueue() {
		return internalControlsQueue;
	}

	/*    Service to switch arduino bulb (pin 13) between "ON" and "OFF"
			   Called by an external client intended to control the Arduino */
	@Path("/bulb/{deviceId}/{state}")
	@POST
	public void switchBulb(@QueryParam("owner") String owner,
						   @PathParam("deviceId") String deviceId,
						   @PathParam("state") String state,
						   @Context HttpServletResponse response) {

		String switchToState = state.toUpperCase();

		if (!switchToState.equals(ArduinoConstants.STATE_ON) && !switchToState.equals(
				ArduinoConstants.STATE_OFF)) {
			log.error("The requested state change shoud be either - 'ON' or 'OFF'");
			response.setStatus(HttpStatus.SC_BAD_REQUEST);
			return;
		}

		try {
			boolean result = DeviceController.setControl(owner, ArduinoConstants.DEVICE_TYPE,
														 deviceId, "BULB", switchToState);
			if (result) {
				response.setStatus(HttpStatus.SC_ACCEPTED);

			} else {
				response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);

			}

		} catch (UnauthorizedException e) {
			response.setStatus(HttpStatus.SC_UNAUTHORIZED);

		}

	}

	/*    Service to poll the control-queue for the controls sent to the Arduino
			   Called by the Arduino device  */
	@Path("/readcontrols/{deviceId}")
	@GET
	public String readControls(@QueryParam("owner") String owner,
							   @PathParam("deviceId") String deviceId,
							   @Context HttpServletResponse response) {
		String result;
		LinkedList<String> deviceControlList = internalControlsQueue.get(deviceId);

		if (deviceControlList == null) {
			result = "No controls have been set for device " + deviceId + " of owner " + owner;
			response.setStatus(HttpStatus.SC_NO_CONTENT);
		} else {
			try {
				result = deviceControlList.remove(); //returns the  head value
				response.setStatus(HttpStatus.SC_ACCEPTED);

			} catch (NoSuchElementException ex) {
				result = "There are no more controls for device " + deviceId + " of owner " +
						owner;
				response.setStatus(HttpStatus.SC_NO_CONTENT);
			}
		}
		if (log.isDebugEnabled()) {
			log.debug(result);
		}

		return result;
	}


	/*    Service to push all the sensor data collected by the Arduino
		   Called by the Arduino device  */
	@Path("/pushdata")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void pushData(final DeviceJSON dataMsg, @Context HttpServletResponse response) {

		String temperature = dataMsg.value;                            //TEMP
		log.info("Recieved Sensor Data Values: " + temperature);

		if (log.isDebugEnabled()) {
			log.debug("Recieved Temperature Data Value: " + temperature + " degrees C");
		}
		try {
			boolean result = DeviceController.pushData(dataMsg.owner, ArduinoConstants.DEVICE_TYPE,
													   dataMsg.deviceId,
													   System.currentTimeMillis(), "DeviceData",
													   temperature, BAMStreamDefinitions.StreamTypeLabel.TEMPERATURE);

			if (!result) {
				response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
			}

		} catch (UnauthorizedException e) {
			response.setStatus(HttpStatus.SC_UNAUTHORIZED);

		}
	}
}
