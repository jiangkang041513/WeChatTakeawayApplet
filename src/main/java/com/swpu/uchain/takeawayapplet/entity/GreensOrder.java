package com.swpu.uchain.takeawayapplet.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class GreensOrder implements Serializable {
    private String preorderId;

    private Integer greenProductId;

    private Integer greensNum;

    private BigDecimal unitPrice;

    private BigDecimal totalPrice;

    private String address;

    private String phoneNum;

    private String contact;

    private String creatTime;

    private static final long serialVersionUID = 1L;

    public String getPreorderId() {
        return preorderId;
    }

    public void setPreorderId(String preorderId) {
        this.preorderId = preorderId == null ? null : preorderId.trim();
    }

    public Integer getGreenProductId() {
        return greenProductId;
    }

    public void setGreenProductId(Integer greenProductId) {
        this.greenProductId = greenProductId;
    }

    public Integer getGreensNum() {
        return greensNum;
    }

    public void setGreensNum(Integer greensNum) {
        this.greensNum = greensNum;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum == null ? null : phoneNum.trim();
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact == null ? null : contact.trim();
    }

    public String getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(String creatTime) {
        this.creatTime = creatTime == null ? null : creatTime.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", preorderId=").append(preorderId);
        sb.append(", greenProductId=").append(greenProductId);
        sb.append(", greensNum=").append(greensNum);
        sb.append(", unitPrice=").append(unitPrice);
        sb.append(", totalPrice=").append(totalPrice);
        sb.append(", address=").append(address);
        sb.append(", phoneNum=").append(phoneNum);
        sb.append(", contact=").append(contact);
        sb.append(", creatTime=").append(creatTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}