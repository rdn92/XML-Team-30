//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.08.31 at 03:50:04 PM CEST 
//


package entities.act;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
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
 *         &lt;element name="Preambula" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Naslov" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;choice>
 *           &lt;element name="Tacke">
 *             &lt;complexType>
 *               &lt;complexContent>
 *                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                   &lt;sequence>
 *                     &lt;element ref="{http://www.skupstinans.rs/akti}Tacka" maxOccurs="unbounded" minOccurs="2"/>
 *                   &lt;/sequence>
 *                 &lt;/restriction>
 *               &lt;/complexContent>
 *             &lt;/complexType>
 *           &lt;/element>
 *           &lt;element name="NormativniDelovi">
 *             &lt;complexType>
 *               &lt;complexContent>
 *                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                   &lt;sequence>
 *                     &lt;element ref="{http://www.skupstinans.rs/akti}NormativniDeo" maxOccurs="unbounded"/>
 *                   &lt;/sequence>
 *                 &lt;/restriction>
 *               &lt;/complexContent>
 *             &lt;/complexType>
 *           &lt;/element>
 *         &lt;/choice>
 *       &lt;/sequence>
 *       &lt;attribute name="korisnik" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="filename" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "preambula",
    "naslov",
    "tacke",
    "normativniDelovi"
})
@XmlRootElement(name = "Akt")
public class Akt {

    @XmlElement(name = "Preambula", required = true)
    protected String preambula;
    @XmlElement(name = "Naslov", required = true)
    protected String naslov;
    @XmlElement(name = "Tacke")
    protected Akt.Tacke tacke;
    @XmlElement(name = "NormativniDelovi")
    protected Akt.NormativniDelovi normativniDelovi;
    @XmlAttribute(name = "korisnik")
    protected String korisnik;
    @XmlAttribute(name = "filename")
    protected String filename;

    /**
     * Gets the value of the preambula property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPreambula() {
        return preambula;
    }

    /**
     * Sets the value of the preambula property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPreambula(String value) {
        this.preambula = value;
    }

    /**
     * Gets the value of the naslov property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNaslov() {
        return naslov;
    }

    /**
     * Sets the value of the naslov property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNaslov(String value) {
        this.naslov = value;
    }

    /**
     * Gets the value of the tacke property.
     * 
     * @return
     *     possible object is
     *     {@link Akt.Tacke }
     *     
     */
    public Akt.Tacke getTacke() {
        return tacke;
    }

    /**
     * Sets the value of the tacke property.
     * 
     * @param value
     *     allowed object is
     *     {@link Akt.Tacke }
     *     
     */
    public void setTacke(Akt.Tacke value) {
        this.tacke = value;
    }

    /**
     * Gets the value of the normativniDelovi property.
     * 
     * @return
     *     possible object is
     *     {@link Akt.NormativniDelovi }
     *     
     */
    public Akt.NormativniDelovi getNormativniDelovi() {
        return normativniDelovi;
    }

    /**
     * Sets the value of the normativniDelovi property.
     * 
     * @param value
     *     allowed object is
     *     {@link Akt.NormativniDelovi }
     *     
     */
    public void setNormativniDelovi(Akt.NormativniDelovi value) {
        this.normativniDelovi = value;
    }

    /**
     * Gets the value of the korisnik property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKorisnik() {
        return korisnik;
    }

    /**
     * Sets the value of the korisnik property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKorisnik(String value) {
        this.korisnik = value;
    }

    /**
     * Gets the value of the filename property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFilename() {
        return filename;
    }

    /**
     * Sets the value of the filename property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFilename(String value) {
        this.filename = value;
    }


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
     *         &lt;element ref="{http://www.skupstinans.rs/akti}NormativniDeo" maxOccurs="unbounded"/>
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
        "normativniDeo"
    })
    public static class NormativniDelovi {

        @XmlElement(name = "NormativniDeo", required = true)
        protected List<NormativniDeo> normativniDeo;

        /**
         * Gets the value of the normativniDeo property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the normativniDeo property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getNormativniDeo().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link NormativniDeo }
         * 
         * 
         */
        public List<NormativniDeo> getNormativniDeo() {
            if (normativniDeo == null) {
                normativniDeo = new ArrayList<NormativniDeo>();
            }
            return this.normativniDeo;
        }

    }


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
     *         &lt;element ref="{http://www.skupstinans.rs/akti}Tacka" maxOccurs="unbounded" minOccurs="2"/>
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
        "tacka"
    })
    public static class Tacke {

        @XmlElement(name = "Tacka", required = true)
        protected List<Tacka> tacka;

        /**
         * Gets the value of the tacka property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the tacka property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getTacka().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Tacka }
         * 
         * 
         */
        public List<Tacka> getTacka() {
            if (tacka == null) {
                tacka = new ArrayList<Tacka>();
            }
            return this.tacka;
        }

    }

}
