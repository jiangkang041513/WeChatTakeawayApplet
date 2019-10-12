package com.swpu.uchain.takeawayapplet.entity;

import java.io.Serializable;

public class MenuBaseType implements Serializable {
    private Integer id;

    private String menuBaseType;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMenuBaseType() {
        return menuBaseType;
    }

    public void setMenuBaseType(String menuBaseType) {
        this.menuBaseType = menuBaseType == null ? null : menuBaseType.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", menuBaseType=").append(menuBaseType);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}