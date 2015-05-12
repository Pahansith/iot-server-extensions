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

package org.wso2.carbon.device.mgt.iot.enroll;

import org.apache.commons.collections.map.LRUMap;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.device.mgt.common.DeviceIdentifier;
import org.wso2.carbon.device.mgt.iot.common.IOTAPIException;
import org.wso2.carbon.device.mgt.iot.utils.IoTConfiguration;

/**
 * @author ayyoobhamza
 *
 */
public class DeviceValidator {
	private static LRUMap cache;
	private static Log log = LogFactory.getLog(DeviceValidator.class);
	static{
		
		
		try {
	        cache=new LRUMap(IoTConfiguration.getInstance().getDeviceCheckerCacheSize());
        } catch (ConfigurationException e) {
	        log.error("Configuration Failed"+e);
        }
				
				
	}
	
	
	public boolean isExist(String owner, DeviceIdentifier deviceId) throws InstantiationException, IllegalAccessException, ConfigurationException, IOTAPIException{
		boolean status=false;
		status=cacheCheck(owner, deviceId.getId());
		if(!status){
    		DeviceManagement deviceManagement= IoTConfiguration.getInstance().getDeviceManagementImpl();
    		status =deviceManagement.isExist(owner, deviceId);
    		if(status){
    			addToCache(owner, deviceId.getId());
    			
    		}
		}
		
		return status;
		
	}
	
	private boolean cacheCheck(String owner,String deviceId){
		
		String value= (String) cache.get(deviceId);
		
		if(value!=null && value.equals(owner)){
			return true;
			
		}
		return false;
		
	}
	
	private void addToCache(String owner,String deviceId){
		
		cache.put(deviceId, owner);
	}
}
