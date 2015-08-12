
package org.wso2.carbon.device.mgt.iot.common.config.server.datasource;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DataStoresConfig complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DataStoresConfig">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DataStore" type="{}DataStore" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DataStoresConfig", propOrder = {
    "dataStore"
})
public class DataStoresConfig {

    @XmlElement(name = "DataStore")
    protected List<DataStore> dataStore;

    /**
     * Gets the value of the dataStore property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dataStore property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDataStore().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DataStore }
     * 
     * 
     */
    public List<DataStore> getDataStore() {
        if (dataStore == null) {
            dataStore = new ArrayList<DataStore>();
        }
        return this.dataStore;
    }

}
