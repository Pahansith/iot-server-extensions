/*
 * Copyright (c) 2015, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * you may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.wso2.carbon.device.mgt.iot.arduino.firealarm.impl;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.device.mgt.common.*;
import org.wso2.carbon.device.mgt.iot.arduino.firealarm.constants.FireAlarmConstants;
import org.wso2.carbon.device.mgt.iot.arduino.firealarm.impl.dao.FireAlarmDAO;
import org.wso2.carbon.device.mgt.iot.arduino.firealarm.impl.util.FireAlarmUtils;
import org.wso2.carbon.device.mgt.iot.dao.IotDeviceManagementDAOException;
import org.wso2.carbon.device.mgt.iot.dao.IotDeviceManagementDAOFactory;
import org.wso2.carbon.device.mgt.iot.dto.IotDevice;
import org.wso2.carbon.device.mgt.iot.util.IotDeviceManagementUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.wso2.carbon.device.mgt.common.app.mgt.AppManagerConnectorException;
import org.wso2.carbon.device.mgt.common.operation.mgt.Operation;
import org.wso2.carbon.device.mgt.common.spi.DeviceMgtService;


/**
 * This represents the Arduino implementation of DeviceManagerService.
 */
public class FireAlarmManager implements DeviceMgtService {

    private static final IotDeviceManagementDAOFactory iotDeviceManagementDAOFactory = new FireAlarmDAO();
    private static final Log log = LogFactory.getLog(FireAlarmManager.class);

    @Override
    public String getProviderType() {
        return FireAlarmConstants.DEVICE_TYPE;
    }

    @Override
    public FeatureManager getFeatureManager() {
        return null;
    }

    @Override
    public boolean enrollDevice(Device device) throws DeviceManagementException {
        boolean status;
        IotDevice iotDevice = IotDeviceManagementUtil.convertToIotDevice(device);
        try {
            if (log.isDebugEnabled()) {
                log.debug("Enrolling a new Arduino device : " + device.getDeviceIdentifier());
            }
            FireAlarmDAO.beginTransaction();
            status = iotDeviceManagementDAOFactory.getIotDeviceDAO().addIotDevice(
                    iotDevice);
            FireAlarmDAO.commitTransaction();
        } catch (IotDeviceManagementDAOException e) {
            try {
                FireAlarmDAO.rollbackTransaction();
            } catch (IotDeviceManagementDAOException iotDAOEx) {
                String msg = "Error occurred while roll back the device enrol transaction :" + device.toString();
                log.warn(msg, iotDAOEx);
            }
            String msg = "Error while enrolling the Arduino device : " + device.getDeviceIdentifier();
            log.error(msg, e);
            throw new DeviceManagementException(msg, e);
        }
        return status;
    }

    @Override
    public boolean modifyEnrollment(Device device) throws DeviceManagementException {
        boolean status;
        IotDevice iotDevice = IotDeviceManagementUtil.convertToIotDevice(device);
        try {
            if (log.isDebugEnabled()) {
                log.debug("Modifying the Arduino device enrollment data");
            }
            FireAlarmDAO.beginTransaction();
            status = iotDeviceManagementDAOFactory.getIotDeviceDAO()
                    .updateIotDevice(iotDevice);
            FireAlarmDAO.commitTransaction();
        } catch (IotDeviceManagementDAOException e) {
            try {
                FireAlarmDAO.rollbackTransaction();
            } catch (IotDeviceManagementDAOException iotDAOEx) {
                String msg = "Error occurred while roll back the update device transaction :" + device.toString();
                log.warn(msg, iotDAOEx);
            }
            String msg = "Error while updating the enrollment of the Arduino device : " +
                    device.getDeviceIdentifier();
            log.error(msg, e);
            throw new DeviceManagementException(msg, e);
        }
        return status;
    }

    @Override
    public boolean disenrollDevice(DeviceIdentifier deviceId) throws DeviceManagementException {
        boolean status;
        try {
            if (log.isDebugEnabled()) {
                log.debug("Dis-enrolling Arduino device : " + deviceId);
            }
            FireAlarmDAO.beginTransaction();
            status = iotDeviceManagementDAOFactory.getIotDeviceDAO()
                    .deleteIotDevice(deviceId.getId());
            FireAlarmDAO.commitTransaction();
        } catch (IotDeviceManagementDAOException e) {
            try {
                FireAlarmDAO.rollbackTransaction();
            } catch (IotDeviceManagementDAOException iotDAOEx) {
                String msg = "Error occurred while roll back the device dis enrol transaction :" + deviceId.toString();
                log.warn(msg, iotDAOEx);
            }
            String msg = "Error while removing the Arduino device : " + deviceId.getId();
            log.error(msg, e);
            throw new DeviceManagementException(msg, e);
        }
        return status;
    }

    @Override
    public boolean isEnrolled(DeviceIdentifier deviceId) throws DeviceManagementException {
        boolean isEnrolled = false;
        try {
            if (log.isDebugEnabled()) {
                log.debug("Checking the enrollment of Arduino device : " + deviceId.getId());
            }
            IotDevice iotDevice =
                    iotDeviceManagementDAOFactory.getIotDeviceDAO().getIotDevice(
                            deviceId.getId());
            if (iotDevice != null) {
                isEnrolled = true;
            }
        } catch (IotDeviceManagementDAOException e) {
            String msg = "Error while checking the enrollment status of Arduino device : " +
                    deviceId.getId();
            log.error(msg, e);
            throw new DeviceManagementException(msg, e);
        }
        return isEnrolled;
    }

    @Override
    public boolean isActive(DeviceIdentifier deviceId) throws DeviceManagementException {
        return true;
    }

    @Override
    public boolean setActive(DeviceIdentifier deviceId, boolean status)
            throws DeviceManagementException {
        return true;
    }

    @Override
    public Device getDevice(DeviceIdentifier deviceId) throws DeviceManagementException {
        Device device;
        try {
            if (log.isDebugEnabled()) {
                log.debug("Getting the details of Arduino device : " + deviceId.getId());
            }
            IotDevice iotDevice = iotDeviceManagementDAOFactory.getIotDeviceDAO().
                    getIotDevice(deviceId.getId());
            device = IotDeviceManagementUtil.convertToDevice(iotDevice);
        } catch (IotDeviceManagementDAOException e) {
            String msg = "Error while fetching the Arduino device : " + deviceId.getId();
            log.error(msg, e);
            throw new DeviceManagementException(msg, e);
        }
        return device;
    }

    @Override
    public boolean setOwnership(DeviceIdentifier deviceId, String ownershipType)
            throws DeviceManagementException {
        return true;
    }

    @Override public boolean isClaimable(DeviceIdentifier deviceIdentifier) throws DeviceManagementException {
        return false;
    }

    @Override
    public boolean updateDeviceInfo(DeviceIdentifier deviceIdentifier, Device device) throws DeviceManagementException {
        boolean status;
        IotDevice iotDevice = IotDeviceManagementUtil.convertToIotDevice(device);
        try {
            if (log.isDebugEnabled()) {
                log.debug(
                        "updating the details of Arduino device : " + deviceIdentifier);
            }
            FireAlarmDAO.beginTransaction();
            status = iotDeviceManagementDAOFactory.getIotDeviceDAO()
                    .updateIotDevice(iotDevice);
            FireAlarmDAO.commitTransaction();
        } catch (IotDeviceManagementDAOException e) {
            try {
                FireAlarmDAO.rollbackTransaction();
            } catch (IotDeviceManagementDAOException iotDAOEx) {
                String msg = "Error occurred while roll back the update device info transaction :" + device.toString();
                log.warn(msg, iotDAOEx);
            }
            String msg =
                    "Error while updating the Arduino device : " + deviceIdentifier;
            log.error(msg, e);
            throw new DeviceManagementException(msg, e);
        }
        return status;
    }

    @Override
    public List<Device> getAllDevices() throws DeviceManagementException {
        List<Device> devices = null;
        try {
            if (log.isDebugEnabled()) {
                log.debug("Fetching the details of all Arduino devices");
            }
            List<IotDevice> iotDevices =
                    iotDeviceManagementDAOFactory.getIotDeviceDAO().getAllIotDevices();
            if (iotDevices != null) {
                devices = new ArrayList<Device>();
                for (IotDevice iotDevice : iotDevices) {
                    devices.add(IotDeviceManagementUtil.convertToDevice(iotDevice));
                }
            }
        } catch (IotDeviceManagementDAOException e) {
            String msg = "Error while fetching all Arduino devices.";
            log.error(msg, e);
            throw new DeviceManagementException(msg, e);
        }
        return devices;
    }

    /**
     * Create and returns a zip archive given a directory/file.
     * @param filePath directory/file to zip
     * @return Zip archive file
     * @throws DeviceManagementException
     */
    public File downloadSketch(String filePath) throws DeviceManagementException{
        return FireAlarmUtils.createZipArchive(filePath);
    }
    
    @Override
    public void installApplication(Operation operation, List<DeviceIdentifier> deviceIdentifiers) throws AppManagerConnectorException {
	    // TODO Auto-generated method stub
	    
    }

    
}