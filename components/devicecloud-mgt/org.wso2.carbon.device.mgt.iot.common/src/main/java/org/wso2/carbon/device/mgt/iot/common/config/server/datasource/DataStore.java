
package org.wso2.carbon.device.mgt.iot.common.config.server.datasource;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DataStore complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DataStore">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Enabled" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="PublisherClass" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ServerURL" type="{}http-url"/>
 *         &lt;element name="Port" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *         &lt;element name="Username" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Password" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DataStore", propOrder = {
    "name",
    "enabled",
    "publisherClass",
    "serverURL",
    "port",
    "username",
    "password"
})
public class DataStore {

    @XmlElement(name = "Name", required = true)
    protected String name;
    @XmlElement(name = "Enabled")
    protected boolean enabled;
    @XmlElement(name = "PublisherClass", required = true)
    protected String publisherClass;
    @XmlElement(name = "ServerURL", required = true)
    protected String serverURL;
    @XmlElement(name = "Port")
    protected short port;
    @XmlElement(name = "Username", required = true)
    protected String username;
    @XmlElement(name = "Password", required = true)
    protected String password;

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the enabled property.
     * 
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * Sets the value of the enabled property.
     * 
     */
    public void setEnabled(boolean value) {
        this.enabled = value;
    }

    /**
     * Gets the value of the publisherClass property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPublisherClass() {
        return publisherClass;
    }

    /**
     * Sets the value of the publisherClass property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPublisherClass(String value) {
        this.publisherClass = value;
    }

    /**
     * Gets the value of the serverURL property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServerURL() {
        return serverURL;
    }

    /**
     * Sets the value of the serverURL property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServerURL(String value) {
        this.serverURL = value;
    }

    /**
     * Gets the value of the port property.
     * 
     */
    public short getPort() {
        return port;
    }

    /**
     * Sets the value of the port property.
     * 
     */
    public void setPort(short value) {
        this.port = value;
    }

    /**
     * Gets the value of the username property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the value of the username property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsername(String value) {
        this.username = value;
    }

    /**
     * Gets the value of the password property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the value of the password property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPassword(String value) {
        this.password = value;
    }

}
