
package hu.iboard.coandco.datamagic.webservice.managementreport;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="dbServer" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dbPort" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dbUser" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dbPass" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dataBase" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cegUnikazon" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dolgozoKod" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="dolgozoPass" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "dbServer",
    "dbPort",
    "dbUser",
    "dbPass",
    "dataBase",
    "cegUnikazon",
    "dolgozoKod",
    "dolgozoPass"
})
@XmlRootElement(name = "GetSessionId")
public class GetSessionId {

    @XmlElementRef(name = "dbServer", namespace = "http://tempuri.org/", type = JAXBElement.class)
    protected JAXBElement<String> dbServer;
    @XmlElementRef(name = "dbPort", namespace = "http://tempuri.org/", type = JAXBElement.class)
    protected JAXBElement<String> dbPort;
    @XmlElementRef(name = "dbUser", namespace = "http://tempuri.org/", type = JAXBElement.class)
    protected JAXBElement<String> dbUser;
    @XmlElementRef(name = "dbPass", namespace = "http://tempuri.org/", type = JAXBElement.class)
    protected JAXBElement<String> dbPass;
    @XmlElementRef(name = "dataBase", namespace = "http://tempuri.org/", type = JAXBElement.class)
    protected JAXBElement<String> dataBase;
    @XmlElementRef(name = "cegUnikazon", namespace = "http://tempuri.org/", type = JAXBElement.class)
    protected JAXBElement<String> cegUnikazon;
    protected Integer dolgozoKod;
    @XmlElementRef(name = "dolgozoPass", namespace = "http://tempuri.org/", type = JAXBElement.class)
    protected JAXBElement<String> dolgozoPass;

    /**
     * Gets the value of the dbServer property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDbServer() {
        return dbServer;
    }

    /**
     * Sets the value of the dbServer property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDbServer(JAXBElement<String> value) {
        this.dbServer = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the dbPort property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDbPort() {
        return dbPort;
    }

    /**
     * Sets the value of the dbPort property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDbPort(JAXBElement<String> value) {
        this.dbPort = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the dbUser property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDbUser() {
        return dbUser;
    }

    /**
     * Sets the value of the dbUser property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDbUser(JAXBElement<String> value) {
        this.dbUser = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the dbPass property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDbPass() {
        return dbPass;
    }

    /**
     * Sets the value of the dbPass property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDbPass(JAXBElement<String> value) {
        this.dbPass = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the dataBase property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDataBase() {
        return dataBase;
    }

    /**
     * Sets the value of the dataBase property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDataBase(JAXBElement<String> value) {
        this.dataBase = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the cegUnikazon property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCegUnikazon() {
        return cegUnikazon;
    }

    /**
     * Sets the value of the cegUnikazon property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCegUnikazon(JAXBElement<String> value) {
        this.cegUnikazon = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the dolgozoKod property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getDolgozoKod() {
        return dolgozoKod;
    }

    /**
     * Sets the value of the dolgozoKod property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setDolgozoKod(Integer value) {
        this.dolgozoKod = value;
    }

    /**
     * Gets the value of the dolgozoPass property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDolgozoPass() {
        return dolgozoPass;
    }

    /**
     * Sets the value of the dolgozoPass property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDolgozoPass(JAXBElement<String> value) {
        this.dolgozoPass = ((JAXBElement<String> ) value);
    }

}
