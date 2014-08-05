//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.08.04 at 04:25:49 PM PDT 
//


package net.authorize.api.contract.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for paymentScheduleType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="paymentScheduleType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="interval" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="length">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}short">
 *                         &lt;minInclusive value="1"/>
 *                         &lt;maxInclusive value="32000"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="unit" type="{AnetApi/xml/v1/schema/AnetApiSchema.xsd}ARBSubscriptionUnitEnum"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="startDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="totalOccurrences" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}short">
 *               &lt;minInclusive value="1"/>
 *               &lt;maxInclusive value="32000"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="trialOccurrences" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}short">
 *               &lt;minInclusive value="0"/>
 *               &lt;maxInclusive value="32000"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "paymentScheduleType", propOrder = {
    "interval",
    "startDate",
    "totalOccurrences",
    "trialOccurrences"
})
public class PaymentScheduleType {

    protected PaymentScheduleType.Interval interval;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar startDate;
    protected Short totalOccurrences;
    protected Short trialOccurrences;

    /**
     * Gets the value of the interval property.
     * 
     * @return
     *     possible object is
     *     {@link PaymentScheduleType.Interval }
     *     
     */
    public PaymentScheduleType.Interval getInterval() {
        return interval;
    }

    /**
     * Sets the value of the interval property.
     * 
     * @param value
     *     allowed object is
     *     {@link PaymentScheduleType.Interval }
     *     
     */
    public void setInterval(PaymentScheduleType.Interval value) {
        this.interval = value;
    }

    /**
     * Gets the value of the startDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getStartDate() {
        return startDate;
    }

    /**
     * Sets the value of the startDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setStartDate(XMLGregorianCalendar value) {
        this.startDate = value;
    }

    /**
     * Gets the value of the totalOccurrences property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getTotalOccurrences() {
        return totalOccurrences;
    }

    /**
     * Sets the value of the totalOccurrences property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setTotalOccurrences(Short value) {
        this.totalOccurrences = value;
    }

    /**
     * Gets the value of the trialOccurrences property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getTrialOccurrences() {
        return trialOccurrences;
    }

    /**
     * Sets the value of the trialOccurrences property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setTrialOccurrences(Short value) {
        this.trialOccurrences = value;
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
     *         &lt;element name="length">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}short">
     *               &lt;minInclusive value="1"/>
     *               &lt;maxInclusive value="32000"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="unit" type="{AnetApi/xml/v1/schema/AnetApiSchema.xsd}ARBSubscriptionUnitEnum"/>
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
        "length",
        "unit"
    })
    public static class Interval {

        protected short length;
        @XmlElement(required = true)
        @XmlSchemaType(name = "string")
        protected ARBSubscriptionUnitEnum unit;

        /**
         * Gets the value of the length property.
         * 
         */
        public short getLength() {
            return length;
        }

        /**
         * Sets the value of the length property.
         * 
         */
        public void setLength(short value) {
            this.length = value;
        }

        /**
         * Gets the value of the unit property.
         * 
         * @return
         *     possible object is
         *     {@link ARBSubscriptionUnitEnum }
         *     
         */
        public ARBSubscriptionUnitEnum getUnit() {
            return unit;
        }

        /**
         * Sets the value of the unit property.
         * 
         * @param value
         *     allowed object is
         *     {@link ARBSubscriptionUnitEnum }
         *     
         */
        public void setUnit(ARBSubscriptionUnitEnum value) {
            this.unit = value;
        }

    }

}
