package com.creditease.rc.vo;

import com.creditease.rc.common.JsonYMDDateSerializer;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.Date;

public class SaleInquireSearchInfoVO {

    private String customerName;
    private String mobilePhone;
    private String channel;
    private String marketConsultState;
    private String province;
    private String city;
    private String area;
    private Date registDate1;
    private Date registDate2;
    private Date distributionDate1;
    private Date distributionDate2;
    private String searchButton;
    private String history;
    private String viewType;

    public String getSearchButton() {
        return searchButton;
    }

    public void setSearchButton(String searchButton) {
        this.searchButton = searchButton;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getMarketConsultState() {
        return marketConsultState;
    }

    public void setMarketConsultState(String marketConsultState) {
        this.marketConsultState = marketConsultState;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @JsonSerialize(using = JsonYMDDateSerializer.class)
    public Date getRegistDate1() {
        return registDate1;
    }

    public void setRegistDate1(Date registDate1) {
        this.registDate1 = registDate1;
    }

    @JsonSerialize(using = JsonYMDDateSerializer.class)
    public Date getRegistDate2() {
        return registDate2;
    }

    public void setRegistDate2(Date registDate2) {
        this.registDate2 = registDate2;
    }

    @JsonSerialize(using = JsonYMDDateSerializer.class)
    public Date getDistributionDate1() {
        return distributionDate1;
    }

    public void setDistributionDate1(Date distributionDate1) {
        this.distributionDate1 = distributionDate1;
    }

    public Date getDistributionDate2() {
        return distributionDate2;
    }

    public void setDistributionDate2(Date distributionDate2) {
        this.distributionDate2 = distributionDate2;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public String getViewType() {
        return viewType;
    }

    public void setViewType(String viewType) {
        this.viewType = viewType;
    }
}
