
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
 *         &lt;element name="GetMachineNameResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "getMachineNameResult"
})
@XmlRootElement(name = "GetMachineNameResponse")
public class GetMachineNameResponse {

    @XmlElementRef(name = "GetMachineNameResult", namespace = "http://tempuri.org/", type = JAXBElement.class)
    protected JAXBElement<String> getMachineNameResult;

    /**
     * Gets the value of the getMachineNameResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getGetMachineNameResult() {
        return getMachineNameResult;
    }

    /**
     * Sets the value of the getMachineNameResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setGetMachineNameResult(JAXBElement<String> value) {
        this.getMachineNameResult = ((JAXBElement<String> ) value);
    }

}
