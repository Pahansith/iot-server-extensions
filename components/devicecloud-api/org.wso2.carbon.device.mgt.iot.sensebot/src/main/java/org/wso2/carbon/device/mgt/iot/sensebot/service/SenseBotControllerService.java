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

package org.wso2.carbon.device.mgt.iot.sensebot.service;

//@Path(value = "/FireAlarmController")
public class SenseBotControllerService {

//    private static Logger log = Logger.getLogger(SenseBotControllerService.class);
//    private static final Map<String, String> deviceIPList = new HashMap<String, String>();
//
//    private static HttpURLConnection httpConn;
//    private static final String URL_PREFIX = "http://";
//    private static final String FORWARD_URL = "/move/F";
//    private static final String BACKWARD_URL = "/move/B";
//    private static final String LEFT_URL = "/move/L";
//    private static final String RIGHT_URL = "/move/R";
//    private static final String STOP_URL = "/move/S";
//
//    /*    Service to switch "ON" and "OFF" the FireAlarm bulb
//               Called by an external client intended to control the FireAlarm bulb */
//    @Path("/forward") @POST public String moveForward(@HeaderParam("owner") String owner,
//            @HeaderParam("deviceId") String deviceId, @FormParam("ip") String deviceIp,
//            @FormParam("port") int deviceServerPort) {
//
//        String result = null;
//        result = sendCommand(deviceIp, deviceServerPort, FORWARD_URL);
//        return result;
//    }
//
//    /*    Service to read the temperature from the FireAlarm temperature sensor
//                   Called by an external client intended to get the current temperature */
//    @Path("/backward") @POST public String moveBackward(@HeaderParam("owner") String owner,
//            @HeaderParam("deviceId") String deviceId, @FormParam("ip") String deviceIp,
//            @FormParam("port") int deviceServerPort) {
//        String result = null;
//        result = sendCommand(deviceIp, deviceServerPort, BACKWARD_URL);
//        return result;
//    }
//
//    /*    Service to toggle the FireAlarm fan between "ON" and "OFF"
//               Called by an external client intended to control the FireAlarm fan */
//    @Path("/left") @POST public String turnLeft(@HeaderParam("owner") String owner,
//            @HeaderParam("deviceId") String deviceId, @FormParam("ip") String deviceIp,
//            @FormParam("port") int deviceServerPort) {
//        String result = null;
//        result = sendCommand(deviceIp, deviceServerPort, LEFT_URL);
//        return result;
//    }
//
//    @Path("/right") @POST public String turnRight(@HeaderParam("owner") String owner,
//            @HeaderParam("deviceId") String deviceId, @FormParam("ip") String deviceIp,
//            @FormParam("port") int deviceServerPort) {
//        String result = null;
//        result = sendCommand(deviceIp, deviceServerPort, RIGHT_URL);
//        return result;
//    }
//
//    @Path("/stop") @POST public String stop(@HeaderParam("owner") String owner,
//            @HeaderParam("deviceId") String deviceId, @FormParam("ip") String deviceIp,
//            @FormParam("port") int deviceServerPort) {
//        String result = null;
//        result = sendCommand(deviceIp, deviceServerPort, STOP_URL);
//        return result;
//    }
//
//    /*    Service to push all the sensor data collected by the FireAlarm
//       Called by the FireAlarm device  */
//    @Path("/pushsensordata") @POST @Consumes(MediaType.APPLICATION_JSON) public String pushAlarmData(
//            final DeviceJSON dataMsg, @Context HttpServletResponse response) {
//        String result = null;
//
//        String sensorValues = dataMsg.value;                            //TEMP-PIR-SONAR-LDR
//        log.info("Recieved Sensor Data Values: " + sensorValues);
//
//        String sensors[] = sensorValues.split(":");
//
//        if (sensors.length == 4) {
//            String temperature = sensors[0];
//            String motion = sensors[1];
//            String sonar = sensors[2];
//            String light = sensors[3];
//
//            if (sonar.equals("-1")) {
//                sonar = "No Object";
//            }
//
//            sensorValues =
//                    "Temperature:" + temperature + "C\t\tMotion:" + motion + "\tSonar:" + sonar + "\tLight:" + light;
//            log.info(sensorValues);
//
//            result = DeviceController
//                    .pushData(dataMsg.owner, "SenseBot", dataMsg.deviceId, System.currentTimeMillis(), "DeviceData",
//                            temperature, "TEMP", response);
//
//            if (response.getStatus() != HttpStatus.SC_ACCEPTED) {
//                return result;
//            }
//
//            result = DeviceController
//                    .pushData(dataMsg.owner, "SenseBot", dataMsg.deviceId, System.currentTimeMillis(), "DeviceData",
//                            motion, "MOTION", response);
//
//            if (response.getStatus() != HttpStatus.SC_ACCEPTED) {
//                return result;
//            }
//
//            if (!sonar.equals("No Object")) {
//                result = DeviceController
//                        .pushData(dataMsg.owner, "SenseBot", dataMsg.deviceId, System.currentTimeMillis(), "DeviceData",
//                                sonar, "SONAR", response);
//
//                if (response.getStatus() != HttpStatus.SC_ACCEPTED) {
//                    return result;
//                }
//            }
//
//            result = DeviceController
//                    .pushData(dataMsg.owner, "SenseBot", dataMsg.deviceId, System.currentTimeMillis(), "DeviceData",
//                            light, "LIGHT", response);
//
//        } else {
//            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
//            return "Invalid data stream format. Needs to be \"TEMP:PIR:SONAR:LDR\"";
//        }
//
//        return result;
//    }
//
//    @Path("/pushtempdata") @POST @Consumes(MediaType.APPLICATION_JSON) public String pushTempData(
//            final DeviceJSON dataMsg, @Context HttpServletResponse response) {
//        String result = null;
//
//        String temperature = dataMsg.value;                            //TEMP-PIR-SONAR-LDR
//        log.info("Recieved Tenperature Data Value: " + temperature + " degrees C");
//
//        result = DeviceController
//                .pushData(dataMsg.owner, "SenseBot", dataMsg.deviceId, System.currentTimeMillis(), "DeviceData",
//                        temperature, "TEMP", response);
//
//        return result;
//    }
//
//    @Path("/pushpirdata") @POST @Consumes(MediaType.APPLICATION_JSON) public String pushPIRData(
//            final DeviceJSON dataMsg, @Context HttpServletResponse response) {
//        String result = null;
//
//        String motion = dataMsg.value;                            //TEMP-PIR-SONAR-LDR
//        log.info("Recieved PIR (Motion) Sensor Data Value: " + motion);
//
//        result = DeviceController
//                .pushData(dataMsg.owner, "SenseBot", dataMsg.deviceId, System.currentTimeMillis(), "DeviceData", motion,
//                        "MOTION", response);
//
//        return result;
//    }
//
//    @Path("/pushsonardata") @POST @Consumes(MediaType.APPLICATION_JSON) public String pushSonarData(
//            final DeviceJSON dataMsg, @Context HttpServletResponse response) {
//        String result = null;
//
//        String sonar = dataMsg.value;                            //TEMP-PIR-SONAR-LDR
//
//        if (sonar.equals("-1")) {
//            log.info("Recieved a 'No Obstacle' Sonar value. (Means there are no abstacles within 30cm)");
//        } else {
//            log.info("Recieved Sonar Sensor Data Value: " + sonar + " cm");
//
//            result = DeviceController
//                    .pushData(dataMsg.owner, "SenseBot", dataMsg.deviceId, System.currentTimeMillis(), "DeviceData",
//                            sonar, "SONAR", response);
//
//            if (response.getStatus() != HttpStatus.SC_ACCEPTED) {
//                return result;
//            }
//        }
//
//        return result;
//    }
//
//    @Path("/pushlightdata") @POST @Consumes(MediaType.APPLICATION_JSON) public String pushlightData(
//            final DeviceJSON dataMsg, @Context HttpServletResponse response) {
//        String result = null;
//
//        String light = dataMsg.value;                            //TEMP-PIR-SONAR-LDR
//        log.info("Recieved LDR (Light) Sensor Data Value: " + light);
//
//        result = DeviceController
//                .pushData(dataMsg.owner, "SenseBot", dataMsg.deviceId, System.currentTimeMillis(), "DeviceData", light,
//                        "LIGHT", response);
//
//        return result;
//    }
//
//    private String sendCommand(String deviceIp, int deviceServerPort, String motionType) {
//
//        if (deviceServerPort == 0) {
//            deviceServerPort = 80;
//        }
//
//        String urlString = URL_PREFIX + deviceIp + ":" + deviceServerPort + motionType;
//        log.info(urlString);
//
//        String result = null;
//        URL url = null;
//        int responseCode = 200;
//
//        try {
//            url = new URL(urlString);
//        } catch (MalformedURLException e) {
//            log.error("Invalid URL: " + urlString);
//        }
//        try {
//            httpConn = (HttpURLConnection) url.openConnection();
//        } catch (IOException e) {
//            log.error("Error Connecting to HTTP Endpoint at: " + urlString);
//        }
//
//        try {
//            httpConn.setRequestMethod(HttpMethod.GET);
//            httpConn.setRequestProperty("User-Agent", "WSO2 Carbon Server");
//            responseCode = httpConn.getResponseCode();
//            result = "" + responseCode + HttpStatus.getStatusText(responseCode) + "(No reply from Robot)";
//
//            log.info("\nSending 'GET' request to URL : " + urlString);
//            log.info("Response Code : " + responseCode);
//        } catch (ProtocolException e) {
//            log.error("Protocol mismatch exception occured whilst trying to 'GET' resource");
//        } catch (IOException e) {
//            log.error(
//                    "Error occured whilst reading return code from server. This could be because the server did not return anything");
//            result = "" + responseCode + " " + HttpStatus.getStatusText(responseCode) + "(No reply from Robot)";
//            return result;
//        }
//
//        return result;
//    }

}
