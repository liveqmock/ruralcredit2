package com.creditease.rc.vo;

import java.util.List;

import com.creditease.rc.domain.AppelleeRecord;
import com.creditease.rc.domain.CreditHistory;
import com.creditease.rc.domain.CreditInvestigation;
import com.creditease.rc.domain.LinkmanInvestigation;

/**
 * Title:
 * Description: 农村商贷系统研发
 * Copyright: Copyright (c) 2012
 * Company: 普信恒业科技发展（北京）有限公司
 * Date: 2013-3-14
 * 
 * @author: 解兵丰
 * @version: v1.0
 */
public class CreditInvestigationVo extends CreditInvestigation {

	private String groupNumber; // 小组编号


	public String getGroupNumber() {
		return groupNumber;
	}

	public void setGroupNumber(String groupNumber) {
		this.groupNumber = groupNumber;
	}

}
