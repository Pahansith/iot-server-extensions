
package org.wso2.carbon.device.mgt.iot.common.config.server.datasource;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ApiManagerConfig complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ApiManagerConfig">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Enabled" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="AccessTokenURL" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ServerURL" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ServerPort" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="GatewayPort" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="LoginURL" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SubscriptionListURL" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Username" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Password" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DeviceGrantType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DeviceScopes" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ApiManagerConfig", propOrder = {
    "enabled",
    "accessTokenURL",
    "serverURL",
    "serverPort",
    "gatewayPort",
    "loginURL",
    "subscriptionListURL",
    "username",
    "password",
    "deviceGrantType",
    "deviceScopes"
})
public class ApiManagerConfig {

    @XmlElement(name = "Enabled")
    protected boolean enabled;
    @XmlElement(name = "AccessTokenURL", required = true)
    protected String accessTokenURL;
    @XmlElement(name = "ServerURL", required = true)
    protected String serverURL;
    @XmlElement(name = "ServerPort", required = true)
    protected String serverPort;
    @XmlElement(name = "GatewayPort", required = true)
    protected String gatewayPort;
    @XmlElement(name = "LoginURL", required = true)
    protected String loginURL;
    @XmlElement(name = "SubscriptionListURL", required = true)
    protected String subscriptionListURL;
    @XmlElement(name = "Username", required = true)
    protected String username;
    @XmlElement(name = "Password", required = true)
    protected String password;
    @XmlElement(name = "DeviceGrantType", required = true)
    protected String deviceGrantType;
    @XmlElement(name = "DeviceScopes", required = true)
    protected String deviceScopes;

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
     * Gets the value of the accessTokenURL property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccessTokenURL() {
        return accessTokenURL;
    }

    /**
     * Sets the value of the accessTokenURL property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccessTokenURL(String value) {
        this.accessTokenURL = value;
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
     * Gets the value of the serverPort property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServerPort() {
        return serverPort;
    }

    /**
     * Sets the value of the serverPort property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServerPort(String value) {
        this.serverPort = value;
    }

    /**
     * Gets the value of the gatewayPort property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGatewayPort() {
        return gatewayPort;
    }

    /**
     * Sets the value of the gatewayPort property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGatewayPort(String value) {
        this.gatewayPort = value;
    }

    /**
     * Gets the value of the loginURL property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLoginURL() {
        return loginURL;
    }

    /**
     * Sets the value of the loginURL property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLoginURL(String value) {
        this.loginURL = value;
    }

    /**
     * Gets the value of the subscriptionListURL property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubscriptionListURL() {
        return subscriptionListURL;
    }

    /**
     * Sets the value of the subscriptionListURL property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubscriptionListURL(String value) {
        this.subscriptionListURL = value;
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

    /**
     * Gets the value of the deviceGrantType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeviceGrantType() {
        return deviceGrantType;
    }

    /**
     * Sets the value of the deviceGrantType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeviceGrantType(String value) {
        this.deviceGrantType = value;
    }

    /**
     * Gets the value of the deviceScopes property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeviceScopes() {
        return deviceScopes;
    }

    /**
     * Sets the value of the deviceScopes property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeviceScopes(String value) {
        this.deviceScopes = value;
    }

}
