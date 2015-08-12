
package org.wso2.carbon.device.mgt.iot.common.config.devicetype.datasource;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for IoTDeviceTypeConfigManager complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="IoTDeviceTypeConfigManager">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="IotDeviceTypeConfig" type="{}IotDeviceTypeConfig" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IoTDeviceTypeConfigManager", propOrder = {
    "iotDeviceTypeConfig"
})

@XmlRootElement(name = "IoTDeviceTypeConfigManager")
public class IoTDeviceTypeConfigManager {

    @XmlElement(name = "IotDeviceTypeConfig")
    protected List<IotDeviceTypeConfig> iotDeviceTypeConfig;

    /**
     * Gets the value of the iotDeviceTypeConfig property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the iotDeviceTypeConfig property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIotDeviceTypeConfig().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link IotDeviceTypeConfig }
     * 
     * 
     */
    public List<IotDeviceTypeConfig> getIotDeviceTypeConfig() {
        if (iotDeviceTypeConfig == null) {
            iotDeviceTypeConfig = new ArrayList<IotDeviceTypeConfig>();
        }
        return this.iotDeviceTypeConfig;
    }

}
