package com.creditease.rc.vo;

import com.creditease.rc.domain.BorrowerService;
/**
 * 
 * @author zhangman
 * Copyright: Copyright (c) 2013
 * Company: 普信恒业科技发展（北京）有限公司
 */
public class BorrowerServiceVo extends BorrowerService{
	private String businessNumber;

	public String getBusinessNumber() {
		return businessNumber;
	}

	public void setBusinessNumber(String businessNumber) {
		this.businessNumber = businessNumber;
	}
}
