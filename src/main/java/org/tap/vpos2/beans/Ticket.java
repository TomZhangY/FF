//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.11 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2018.03.23 时间 04:55:15 PM CST 
//


package org.tap.vpos2.beans;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>anonymous complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{}Items"/&gt;
 *         &lt;element ref="{}LoyaltyInfo"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="DiscountAmount" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="MobileNo" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="NetTotalAmount" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="NonLoyaltySavingAmt" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="LoyaltySavingAmt" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="O2oTxnNo" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="PosID" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="StoreID" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="TenderCode" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="TicketID" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="TotalAmount" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="TxnNo" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="TxnTime" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="URL" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="ActivationCode" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "items",
    "loyaltyInfo"
})
@XmlRootElement(name = "Ticket")
public class Ticket {

    @XmlElement(name = "Items", required = true)
    protected Items items;
    @XmlElement(name = "LoyaltyInfo", required = true)
    protected LoyaltyInfo loyaltyInfo;
    @XmlAttribute(name = "DiscountAmount", required = true)
    protected String discountAmount;
    @XmlAttribute(name = "MobileNo", required = true)
    protected String mobileNo;
    @XmlAttribute(name = "NetTotalAmount", required = true)
    protected String netTotalAmount;
    @XmlAttribute(name = "NonLoyaltySavingAmt", required = true)
    protected String nonLoyaltySavingAmt;
    @XmlAttribute(name = "LoyaltySavingAmt", required = true)
    protected String loyaltySavingAmt;
    @XmlAttribute(name = "O2oTxnNo", required = true)
    protected String o2OTxnNo;
    @XmlAttribute(name = "PosID", required = true)
    protected String posID;
    @XmlAttribute(name = "StoreID", required = true)
    protected String storeID;
    @XmlAttribute(name = "TenderCode", required = true)
    protected String tenderCode;
    @XmlAttribute(name = "TicketID", required = true)
    protected String ticketID;
    @XmlAttribute(name = "TotalAmount", required = true)
    protected String totalAmount;
    @XmlAttribute(name = "TxnNo", required = true)
    protected String txnNo;
    @XmlAttribute(name = "TxnTime", required = true)
    protected String txnTime;
    @XmlAttribute(name = "URL", required = true)
    protected String url;
    @XmlAttribute(name = "ActivationCode", required = true)
    protected String activationCode;

    /**
     * 获取items属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Items }
     *     
     */
    public Items getItems() {
        return items;
    }

    /**
     * 设置items属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Items }
     *     
     */
    public void setItems(Items value) {
        this.items = value;
    }

    /**
     * 获取loyaltyInfo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link LoyaltyInfo }
     *     
     */
    public LoyaltyInfo getLoyaltyInfo() {
        return loyaltyInfo;
    }

    /**
     * 设置loyaltyInfo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link LoyaltyInfo }
     *     
     */
    public void setLoyaltyInfo(LoyaltyInfo value) {
        this.loyaltyInfo = value;
    }

    /**
     * 获取discountAmount属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDiscountAmount() {
        return discountAmount;
    }

    /**
     * 设置discountAmount属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDiscountAmount(String value) {
        this.discountAmount = value;
    }

    /**
     * 获取mobileNo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMobileNo() {
        return mobileNo;
    }

    /**
     * 设置mobileNo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMobileNo(String value) {
        this.mobileNo = value;
    }

    /**
     * 获取netTotalAmount属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNetTotalAmount() {
        return netTotalAmount;
    }

    /**
     * 设置netTotalAmount属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNetTotalAmount(String value) {
        this.netTotalAmount = value;
    }

    /**
     * 获取nonLoyaltySavingAmt属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNonLoyaltySavingAmt() {
        return nonLoyaltySavingAmt;
    }

    /**
     * 设置nonLoyaltySavingAmt属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNonLoyaltySavingAmt(String value) {
        this.nonLoyaltySavingAmt = value;
    }

    /**
     * 获取loyaltySavingAmt属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLoyaltySavingAmt() {
        return loyaltySavingAmt;
    }

    /**
     * 设置loyaltySavingAmt属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLoyaltySavingAmt(String value) {
        this.loyaltySavingAmt = value;
    }

    /**
     * 获取o2OTxnNo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getO2OTxnNo() {
        return o2OTxnNo;
    }

    /**
     * 设置o2OTxnNo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setO2OTxnNo(String value) {
        this.o2OTxnNo = value;
    }

    /**
     * 获取posID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPosID() {
        return posID;
    }

    /**
     * 设置posID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPosID(String value) {
        this.posID = value;
    }

    /**
     * 获取storeID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStoreID() {
        return storeID;
    }

    /**
     * 设置storeID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStoreID(String value) {
        this.storeID = value;
    }

    /**
     * 获取tenderCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTenderCode() {
        return tenderCode;
    }

    /**
     * 设置tenderCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTenderCode(String value) {
        this.tenderCode = value;
    }

    /**
     * 获取ticketID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTicketID() {
        return ticketID;
    }

    /**
     * 设置ticketID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTicketID(String value) {
        this.ticketID = value;
    }

    /**
     * 获取totalAmount属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotalAmount() {
        return totalAmount;
    }

    /**
     * 设置totalAmount属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotalAmount(String value) {
        this.totalAmount = value;
    }

    /**
     * 获取txnNo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTxnNo() {
        return txnNo;
    }

    /**
     * 设置txnNo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTxnNo(String value) {
        this.txnNo = value;
    }

    /**
     * 获取txnTime属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTxnTime() {
        return txnTime;
    }

    /**
     * 设置txnTime属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTxnTime(String value) {
        this.txnTime = value;
    }

    /**
     * 获取url属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getURL() {
        return url;
    }

    /**
     * 设置url属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setURL(String value) {
        this.url = value;
    }

    /**
     * 获取activationCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActivationCode() {
        return activationCode;
    }

    /**
     * 设置activationCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActivationCode(String value) {
        this.activationCode = value;
    }

}
