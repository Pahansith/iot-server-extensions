
package org.wso2.carbon.device.mgt.iot.common.config.server.datasource;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ControlQueue complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ControlQueue">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Enabled" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="ControlClass" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Protocol" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ServerURL" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
@XmlType(name = "ControlQueue", propOrder = {
    "name",
    "enabled",
    "controlClass",
    "protocol",
    "serverURL",
    "port",
    "username",
    "password"
})
public class ControlQueue {

    @XmlElement(name = "Name", required = true)
    protected String name;
    @XmlElement(name = "Enabled")
    protected boolean enabled;
    @XmlElement(name = "ControlClass", required = true)
    protected String controlClass;
    @XmlElement(name = "Protocol", required = true)
    protected String protocol;
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
     * Gets the value of the controlClass property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getControlClass() {
        return controlClass;
    }

    /**
     * Sets the value of the controlClass property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setControlClass(String value) {
        this.controlClass = value;
    }

    /**
     * Gets the value of the protocol property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProtocol() {
        return protocol;
    }

    /**
     * Sets the value of the protocol property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProtocol(String value) {
        this.protocol = value;
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
