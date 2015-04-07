package com.creditease.rc.vo;

/**
 * Created by v-guoxingye on 2015/3/17.
 */
public class CustomerInfoVO {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private String phone;
    private String type;
}
