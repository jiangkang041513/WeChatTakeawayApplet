package com.swpu.uchain.takeawayapplet.entity;

import java.io.Serializable;

public class GreenBaseExpand implements Serializable {
    private Integer id;

    private Integer greensBaseId;

    private String address;

    private String phoneNum;

    private String contact;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGreensBaseId() {
        return greensBaseId;
    }

    public void setGreensBaseId(Integer greensBaseId) {
        this.greensBaseId = greensBaseId;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", greensBaseId=").append(greensBaseId);
        sb.append(", address=").append(address);
        sb.append(", phoneNum=").append(phoneNum);
        sb.append(", contact=").append(contact);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}