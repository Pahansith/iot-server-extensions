/*
 *  Copyright (c) 2015, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *  WSO2 Inc. licenses this file to you under the Apache License,
 *  Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 *
 */

package org.wso2.carbon.device.mgt.iot.common.api;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.analytics.datasource.commons.Record;
import org.wso2.carbon.analytics.datasource.commons.exception.AnalyticsException;
import org.wso2.carbon.context.PrivilegedCarbonContext;
import org.wso2.carbon.device.mgt.analytics.service.DeviceAnalyticsService;
import org.wso2.carbon.device.mgt.iot.common.analytics.statistics.dto.DeviceUsageDTO;

import javax.jws.WebService;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebService public class StatsManagerService {

    private static Log log = LogFactory.getLog(StatsManagerService.class);

	@Context  //injected response proxy supporting multiple thread
	private HttpServletResponse response;

    @Path("/stats/device/type/{type}/identifier/{identifier}")
	@GET
	@Consumes("application/json")
	@Produces("application/json")
	public DeviceUsageDTO[] getDeviceStats(@PathParam("type") String type, @PathParam("identifier") String identifier,
            @FormParam("table") String table, @FormParam("column") String column, @FormParam("username")  String user,
            @FormParam("from") long from, @FormParam("to") long to) {

        String fromDate = String.valueOf(from);
        String toDate = String.valueOf(to);

        List<DeviceUsageDTO> deviceUsageDTOs = new ArrayList<DeviceUsageDTO>();
        PrivilegedCarbonContext.startTenantFlow();
        PrivilegedCarbonContext ctx = PrivilegedCarbonContext.getThreadLocalCarbonContext();
        ctx.setTenantDomain("carbon.super", true);
        DeviceAnalyticsService deviceAnalyticsService = (DeviceAnalyticsService) ctx
                .getOSGiService(DeviceAnalyticsService.class, null);
        String query;
        if (fromDate != null && toDate != null) {
            query = "owner:" + user + " AND deviceId:" + identifier + " AND deviceType:" + type +
                    " AND time : [" + fromDate + " TO " + toDate + "]";
        }else{
            return deviceUsageDTOs.toArray(new DeviceUsageDTO[deviceUsageDTOs.size()]);
        }
        try {
            List<Record> records = deviceAnalyticsService.getAllSensorEventsForDevice(table, query);


            for (Record record : records) {
                DeviceUsageDTO deviceUsageDTO = new DeviceUsageDTO();
                deviceUsageDTO.setTime("" + (long)record.getValue("time"));
                deviceUsageDTO.setValue("" + (float) record.getValue(column.toLowerCase()));
                deviceUsageDTOs.add(deviceUsageDTO);
            }
            return deviceUsageDTOs.toArray(new DeviceUsageDTO[deviceUsageDTOs.size()]);
        } catch (AnalyticsException e) {
            String errorMsg= "Error on retrieving stats on table " + table + " with query " + query;
            log.error(errorMsg);
            response.setStatus(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
            return deviceUsageDTOs.toArray(new DeviceUsageDTO[deviceUsageDTOs.size()]);
        } finally {
            PrivilegedCarbonContext.endTenantFlow();
        }
    }

}
