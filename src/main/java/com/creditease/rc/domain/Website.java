package com.creditease.rc.domain;

/**
 * 
 * @author hongjieluo
 *
 */
public class Website {
    
    private Long websiteId;      		//营业网点Id
    private String branchofficeId;	  	//营业部ID
    private String branchofficeName;	//营业部名称
    private String workAddress;			//详细办公地址
    private String businessTime;        //营业时间时间
    private String consultingTelephone; //咨询电话
    private String provincehome;        //省的国标码
    private String cityhome;            //市的国标码
    private String countyhome;          //区的国标码
    private String searchAddress;		//搜索地图用的地址
    
    
    public String getSearchAddress() {
		return searchAddress;
	}

	public void setSearchAddress(String searchAddress) {
		this.searchAddress = searchAddress;
	}

	public Long getWebsiteId() {
        return websiteId;
    }
    
    public void setWebsiteId(Long websiteId) {
        this.websiteId = websiteId;
    }
    
    public String getBranchofficeId() {
        return branchofficeId;
    }

    public void setBranchofficeId(String branchofficeId) {
        this.branchofficeId = branchofficeId == null ? null : branchofficeId.trim();
    }

    public String getBranchofficeName() {
        return branchofficeName;
    }

    public void setBranchofficeName(String branchofficeName) {
        this.branchofficeName = branchofficeName == null ? null : branchofficeName.trim();
    }

    public String getWorkAddress() {
        return workAddress;
    }

    public void setWorkAddress(String workAddress) {
        this.workAddress = workAddress == null ? null : workAddress.trim();
    }

    public String getBusinessTime() {
        return businessTime;
    }

    public void setBusinessTime(String businessTime) {
        this.businessTime = businessTime == null ? null : businessTime.trim();
    }

    public String getConsultingTelephone() {
        return consultingTelephone;
    }

    public void setConsultingTelephone(String consultingTelephone) {
        this.consultingTelephone = consultingTelephone == null ? null : consultingTelephone.trim();
    }

    public String getProvincehome() {
        return provincehome;
    }

    public void setProvincehome(String provincehome) {
        this.provincehome = provincehome == null ? null : provincehome.trim();
    }

    public String getCityhome() {
        return cityhome;
    }

    public void setCityhome(String cityhome) {
        this.cityhome = cityhome == null ? null : cityhome.trim();
    }

    public String getCountyhome() {
        return countyhome;
    }

    public void setCountyhome(String countyhome) {
        this.countyhome = countyhome == null ? null : countyhome.trim();
    }
}