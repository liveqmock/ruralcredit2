package com.creditease.rc.vo;

public class WebsiteInfoRequestParam { 
	private String provincehome;     //省的code  
	private String cityhome;         //市的code  
	private String countyhome;       //区的code  
	private String branchofficeId;	 //营业网点Id  
	
	public String getProvincehome() {
		return provincehome;
	}
	public void setProvincehome(String provincehome) {
		this.provincehome = provincehome;
	}
	public String getCityhome() {
		return cityhome;
	}
	public void setCityhome(String cityhome) {
		this.cityhome = cityhome;
	}
	public String getCountyhome() {
		return countyhome;
	}
	public void setCountyhome(String countyhome) {
		this.countyhome = countyhome;
	}
	public String getBranchofficeId() {
		return branchofficeId;
	}
	public void setBranchofficeId(String branchofficeId) {
		this.branchofficeId = branchofficeId;
	}
	
	
	
}
