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

package org.wso2.carbon.device.mgt.iot.arduino.firealarm.impl.dao.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.device.mgt.iot.arduino.firealarm.constants.FireAlarmConstants;
import org.wso2.carbon.device.mgt.iot.arduino.firealarm.impl.dao.FireAlarmDAO;
import org.wso2.carbon.device.mgt.iot.arduino.firealarm.impl.util.FireAlarmUtils;
import org.wso2.carbon.device.mgt.iot.devicecloud.dao.IotDeviceDAO;
import org.wso2.carbon.device.mgt.iot.devicecloud.dao.IotDeviceManagementDAOException;
import org.wso2.carbon.device.mgt.iot.devicecloud.dao.util.IotDeviceManagementDAOUtil;
import org.wso2.carbon.device.mgt.iot.devicecloud.dto.IotDevice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implements IotDeviceDAO for firealarm Devices.
 */
public class FireAlarmDeviceDAOImpl implements IotDeviceDAO{
	

	    private static final Log log = LogFactory.getLog(FireAlarmDeviceDAOImpl.class);

	    @Override
	    public IotDevice getIotDevice(String iotDeviceId)
	            throws IotDeviceManagementDAOException {
	        Connection conn = null;
	        PreparedStatement stmt = null;
	        IotDevice iotDevice = null;
	        ResultSet resultSet = null;
	        try {
	            conn = FireAlarmDAO.getConnection();
	            String selectDBQuery =
						"SELECT FIREALARM_DEVICE_ID, DEVICE_NAME" +
						" FROM FIREALARM_DEVICE WHERE FIREALARM_DEVICE_ID = ?";
	            stmt = conn.prepareStatement(selectDBQuery);
	            stmt.setString(1, iotDeviceId);
	            resultSet = stmt.executeQuery();

	            if (resultSet.next()) {
					iotDevice = new IotDevice();
					
					Map<String, String> propertyMap = new HashMap<String, String>();
					
					propertyMap.put(FireAlarmConstants.DEVICE_PLUGIN_DEVICE_NAME,
					             resultSet.getString(FireAlarmConstants.DEVICE_PLUGIN_DEVICE_NAME));

					iotDevice.setDeviceProperties(propertyMap);

					if (log.isDebugEnabled()) {
						log.debug("Arduino device " + iotDeviceId + " data has been fetched from " +
						          "Arduino database.");
					}
				}
	        } catch (SQLException e) {
	            String msg = "Error occurred while fetching Arduino device : '" + iotDeviceId + "'";
	            log.error(msg, e);
	            throw new IotDeviceManagementDAOException(msg, e);
	        } finally {
	            IotDeviceManagementDAOUtil.cleanupResources(stmt, resultSet);
	            FireAlarmDAO.closeConnection();
	        }

	        return iotDevice;
	    }

	    @Override
	    public boolean addIotDevice(IotDevice iotDevice)
	            throws IotDeviceManagementDAOException {
	        boolean status = false;
	        Connection conn = null;
	        PreparedStatement stmt = null;
	        try {
	            conn = FireAlarmDAO.getConnection();
	            String createDBQuery =
						"INSERT INTO FIREALARM_DEVICE(FIREALARM_DEVICE_ID, DEVICE_NAME) VALUES (?, ?)";

	            stmt = conn.prepareStatement(createDBQuery);
				stmt.setString(1, iotDevice.getIotDeviceId());

				if (iotDevice.getDeviceProperties() == null) {
					iotDevice.setDeviceProperties(new HashMap<String, String>());
				}

				
				
				
				stmt.setString(2, FireAlarmUtils.getDeviceProperty(iotDevice.getDeviceProperties(),
				                                             FireAlarmConstants.DEVICE_PLUGIN_DEVICE_NAME));
			
				
				int rows = stmt.executeUpdate();
				if (rows > 0) {
					status = true;
					if (log.isDebugEnabled()) {
						log.debug("Arduino device " + iotDevice.getIotDeviceId() + " data has been" +
						          " added to the Arduino database.");
					}
				}
	        } catch (SQLException e) {
	            String msg = "Error occurred while adding the Arduino device '" +
	                         iotDevice.getIotDeviceId() + "' to the Arduino db.";
	            log.error(msg, e);
	            throw new IotDeviceManagementDAOException(msg, e);
	        } finally {
	            IotDeviceManagementDAOUtil.cleanupResources(stmt, null);
	        }
	        return status;
	    }

	    @Override
	    public boolean updateIotDevice(IotDevice iotDevice)
	            throws IotDeviceManagementDAOException {
	        boolean status = false;
	        Connection conn = null;
	        PreparedStatement stmt = null;
	        try {
	            conn = FireAlarmDAO.getConnection();
	            String updateDBQuery =
						"UPDATE FIREALARM_DEVICE SET  DEVICE_NAME = ? WHERE FIREALARM_DEVICE_ID = ?";

				stmt = conn.prepareStatement(updateDBQuery);

				if (iotDevice.getDeviceProperties() == null) {
					iotDevice.setDeviceProperties(new HashMap<String, String>());
				}
				stmt.setString(1, FireAlarmUtils.getDeviceProperty(iotDevice.getDeviceProperties(),
				                                               FireAlarmConstants.DEVICE_PLUGIN_DEVICE_NAME));
	
				stmt.setString(2, iotDevice.getIotDeviceId());
				int rows = stmt.executeUpdate();
				if (rows > 0) {
					status = true;
					if (log.isDebugEnabled()) {
						log.debug("Arduino device " + iotDevice.getIotDeviceId() + " data has been" +
						          " modified.");
					}
				}
	        } catch (SQLException e) {
	            String msg = "Error occurred while modifying the Arduino device '" +
	                         iotDevice.getIotDeviceId() + "' data.";
	            log.error(msg, e);
	            throw new IotDeviceManagementDAOException(msg, e);
	        } finally {
	            IotDeviceManagementDAOUtil.cleanupResources(stmt, null);
	        }
	        return status;
	    }

	    @Override
	    public boolean deleteIotDevice(String iotDeviceId)
	            throws IotDeviceManagementDAOException {
	        boolean status = false;
	        Connection conn = null;
	        PreparedStatement stmt = null;
	        try {
	            conn = FireAlarmDAO.getConnection();
	            String deleteDBQuery =
						"DELETE FROM FIREALARM_DEVICE WHERE FIREALARM_DEVICE_ID = ?";
				stmt = conn.prepareStatement(deleteDBQuery);
				stmt.setString(1, iotDeviceId);
				int rows = stmt.executeUpdate();
				if (rows > 0) {
					status = true;
					if (log.isDebugEnabled()) {
						log.debug("Arduino device " + iotDeviceId + " data has deleted" +
						          " from the Arduino database.");
					}
				}
	        } catch (SQLException e) {
	            String msg = "Error occurred while deleting arduino device " + iotDeviceId;
	            log.error(msg, e);
	            throw new IotDeviceManagementDAOException(msg, e);
	        } finally {
	            IotDeviceManagementDAOUtil.cleanupResources(stmt, null);
	        }
	        return status;
	    }

	    @Override
	    public List<IotDevice> getAllIotDevices()
	            throws IotDeviceManagementDAOException {

	        Connection conn = null;
	        PreparedStatement stmt = null;
	        ResultSet resultSet = null;
	        IotDevice iotDevice;
	        List<IotDevice> iotDevices = new ArrayList<IotDevice>();

	        try {
	            conn = FireAlarmDAO.getConnection();
	            String selectDBQuery =
						"SELECT FIREALARM_DEVICE_ID, DEVICE_NAME " +
						"FROM FIREALARM_DEVICE";
				stmt = conn.prepareStatement(selectDBQuery);
				resultSet = stmt.executeQuery();
				while (resultSet.next()) {
					iotDevice = new IotDevice();
					iotDevice.setIotDeviceId(resultSet.getString(FireAlarmConstants.DEVICE_PLUGIN_DEVICE_ID));
					

					Map<String, String> propertyMap = new HashMap<String, String>();
				
					propertyMap.put(FireAlarmConstants.DEVICE_PLUGIN_DEVICE_NAME,
					                resultSet.getString(FireAlarmConstants.DEVICE_PLUGIN_DEVICE_NAME));

					iotDevice.setDeviceProperties(propertyMap);
					iotDevices.add(iotDevice);
				}
	            if (log.isDebugEnabled()) {
	                log.debug("All Arduino device details have fetched from Arduino database.");
	            }
	            return iotDevices;
	        } catch (SQLException e) {
	            String msg = "Error occurred while fetching all Arduino device data'";
	            log.error(msg, e);
	            throw new IotDeviceManagementDAOException(msg, e);
	        } finally {
	            IotDeviceManagementDAOUtil.cleanupResources(stmt, resultSet);
	            FireAlarmDAO.closeConnection();
	        }
	        
	    }

	}