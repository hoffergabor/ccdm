
package hu.iboard.coandco.datamagic.webservice.managementreport;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ManagementReport complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ManagementReport">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Adott_havi_atlagos_arres" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="Bank_egyenleg" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="Havi_arbevetel" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="Keszletertek" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="Leszamlazatlan_szallitolevelek" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="Penztar_egyenleg" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="Szallitoi_tartozasok" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="Szallitoi_tartozasok_lejart" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="Vevo_tartozasok" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="Vevo_tartozasok_lejart" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="Visszaigazolatlan_szallitolevelek" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="Visszaigazolt_rendelesek_erteke" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ManagementReport", namespace = "http://schemas.datacontract.org/2004/07/SZLA", propOrder = {
    "adottHaviAtlagosArres",
    "bankEgyenleg",
    "haviArbevetel",
    "keszletertek",
    "leszamlazatlanSzallitolevelek",
    "penztarEgyenleg",
    "szallitoiTartozasok",
    "szallitoiTartozasokLejart",
    "vevoTartozasok",
    "vevoTartozasokLejart",
    "visszaigazolatlanSzallitolevelek",
    "visszaigazoltRendelesekErteke"
})
public class ManagementReport {

    @XmlElement(name = "Adott_havi_atlagos_arres")
    protected BigDecimal adottHaviAtlagosArres;
    @XmlElement(name = "Bank_egyenleg")
    protected BigDecimal bankEgyenleg;
    @XmlElement(name = "Havi_arbevetel")
    protected BigDecimal haviArbevetel;
    @XmlElement(name = "Keszletertek")
    protected BigDecimal keszletertek;
    @XmlElement(name = "Leszamlazatlan_szallitolevelek")
    protected BigDecimal leszamlazatlanSzallitolevelek;
    @XmlElement(name = "Penztar_egyenleg")
    protected BigDecimal penztarEgyenleg;
    @XmlElement(name = "Szallitoi_tartozasok")
    protected BigDecimal szallitoiTartozasok;
    @XmlElement(name = "Szallitoi_tartozasok_lejart")
    protected BigDecimal szallitoiTartozasokLejart;
    @XmlElement(name = "Vevo_tartozasok")
    protected BigDecimal vevoTartozasok;
    @XmlElement(name = "Vevo_tartozasok_lejart")
    protected BigDecimal vevoTartozasokLejart;
    @XmlElement(name = "Visszaigazolatlan_szallitolevelek")
    protected BigDecimal visszaigazolatlanSzallitolevelek;
    @XmlElement(name = "Visszaigazolt_rendelesek_erteke")
    protected BigDecimal visszaigazoltRendelesekErteke;

    /**
     * Gets the value of the adottHaviAtlagosArres property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getAdottHaviAtlagosArres() {
        return adottHaviAtlagosArres;
    }

    /**
     * Sets the value of the adottHaviAtlagosArres property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setAdottHaviAtlagosArres(BigDecimal value) {
        this.adottHaviAtlagosArres = value;
    }

    /**
     * Gets the value of the bankEgyenleg property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getBankEgyenleg() {
        return bankEgyenleg;
    }

    /**
     * Sets the value of the bankEgyenleg property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setBankEgyenleg(BigDecimal value) {
        this.bankEgyenleg = value;
    }

    /**
     * Gets the value of the haviArbevetel property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getHaviArbevetel() {
        return haviArbevetel;
    }

    /**
     * Sets the value of the haviArbevetel property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setHaviArbevetel(BigDecimal value) {
        this.haviArbevetel = value;
    }

    /**
     * Gets the value of the keszletertek property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getKeszletertek() {
        return keszletertek;
    }

    /**
     * Sets the value of the keszletertek property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setKeszletertek(BigDecimal value) {
        this.keszletertek = value;
    }

    /**
     * Gets the value of the leszamlazatlanSzallitolevelek property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getLeszamlazatlanSzallitolevelek() {
        return leszamlazatlanSzallitolevelek;
    }

    /**
     * Sets the value of the leszamlazatlanSzallitolevelek property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setLeszamlazatlanSzallitolevelek(BigDecimal value) {
        this.leszamlazatlanSzallitolevelek = value;
    }

    /**
     * Gets the value of the penztarEgyenleg property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPenztarEgyenleg() {
        return penztarEgyenleg;
    }

    /**
     * Sets the value of the penztarEgyenleg property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPenztarEgyenleg(BigDecimal value) {
        this.penztarEgyenleg = value;
    }

    /**
     * Gets the value of the szallitoiTartozasok property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getSzallitoiTartozasok() {
        return szallitoiTartozasok;
    }

    /**
     * Sets the value of the szallitoiTartozasok property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setSzallitoiTartozasok(BigDecimal value) {
        this.szallitoiTartozasok = value;
    }

    /**
     * Gets the value of the szallitoiTartozasokLejart property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getSzallitoiTartozasokLejart() {
        return szallitoiTartozasokLejart;
    }

    /**
     * Sets the value of the szallitoiTartozasokLejart property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setSzallitoiTartozasokLejart(BigDecimal value) {
        this.szallitoiTartozasokLejart = value;
    }

    /**
     * Gets the value of the vevoTartozasok property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getVevoTartozasok() {
        return vevoTartozasok;
    }

    /**
     * Sets the value of the vevoTartozasok property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setVevoTartozasok(BigDecimal value) {
        this.vevoTartozasok = value;
    }

    /**
     * Gets the value of the vevoTartozasokLejart property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getVevoTartozasokLejart() {
        return vevoTartozasokLejart;
    }

    /**
     * Sets the value of the vevoTartozasokLejart property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setVevoTartozasokLejart(BigDecimal value) {
        this.vevoTartozasokLejart = value;
    }

    /**
     * Gets the value of the visszaigazolatlanSzallitolevelek property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getVisszaigazolatlanSzallitolevelek() {
        return visszaigazolatlanSzallitolevelek;
    }

    /**
     * Sets the value of the visszaigazolatlanSzallitolevelek property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setVisszaigazolatlanSzallitolevelek(BigDecimal value) {
        this.visszaigazolatlanSzallitolevelek = value;
    }

    /**
     * Gets the value of the visszaigazoltRendelesekErteke property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getVisszaigazoltRendelesekErteke() {
        return visszaigazoltRendelesekErteke;
    }

    /**
     * Sets the value of the visszaigazoltRendelesekErteke property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setVisszaigazoltRendelesekErteke(BigDecimal value) {
        this.visszaigazoltRendelesekErteke = value;
    }

}
