package com.creditease.rc.vo;

import java.util.Date;

public class UserLoginRequestParam{

	
	private String 	userCode	       ; //用户编码
	private String 	name		       ; //姓名  
	private String 	phone		       ; //电话号码
    private String  identityCardSub	   ; //身份证后四位
    private String  loginType          ; //登陆类型	0-vip；1-伪球迷；2-资深球迷
    
    
	public String getLoginType() {
		return loginType;
	}
	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
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
	public String getIdentityCardSub() {
		return identityCardSub;
	}
	public void setIdentityCardSub(String identityCardSub) {
		this.identityCardSub = identityCardSub;
	}

    
    
}