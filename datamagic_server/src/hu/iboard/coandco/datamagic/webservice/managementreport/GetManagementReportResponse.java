
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
 *         &lt;element name="GetManagementReportResult" type="{http://schemas.datacontract.org/2004/07/SZLA}ManagementReport" minOccurs="0"/>
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
    "getManagementReportResult"
})
@XmlRootElement(name = "GetManagementReportResponse")
public class GetManagementReportResponse {

    @XmlElementRef(name = "GetManagementReportResult", namespace = "http://tempuri.org/", type = JAXBElement.class)
    protected JAXBElement<ManagementReport> getManagementReportResult;

    /**
     * Gets the value of the getManagementReportResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ManagementReport }{@code >}
     *     
     */
    public JAXBElement<ManagementReport> getGetManagementReportResult() {
        return getManagementReportResult;
    }

    /**
     * Sets the value of the getManagementReportResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ManagementReport }{@code >}
     *     
     */
    public void setGetManagementReportResult(JAXBElement<ManagementReport> value) {
        this.getManagementReportResult = ((JAXBElement<ManagementReport> ) value);
    }

}
