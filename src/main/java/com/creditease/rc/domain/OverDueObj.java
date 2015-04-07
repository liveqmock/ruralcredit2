package com.creditease.rc.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class OverDueObj implements Serializable {

	private Long creditApplicationId;
	private String sysId;
	private Date aOverdueStart;
	private BigDecimal aOverdueMoney;
	private Integer aOverdueCount;
	private Integer overdueDayCount;

	public Date getaOverdueStart() {
		return aOverdueStart;
	}

	public void setaOverdueStart(Date aOverdueStart) {
		this.aOverdueStart = aOverdueStart;
	}

	public BigDecimal getaOverdueMoney() {
		return aOverdueMoney;
	}

	public void setaOverdueMoney(BigDecimal aOverdueMoney) {
		this.aOverdueMoney = aOverdueMoney;
	}

	public Integer getaOverdueCount() {
		return aOverdueCount;
	}

	public void setaOverdueCount(Integer aOverdueCount) {
		this.aOverdueCount = aOverdueCount;
	}

	public Long getCreditApplicationId() {
		return creditApplicationId;
	}

	public void setCreditApplicationId(Long creditApplicationId) {
		this.creditApplicationId = creditApplicationId;
	}

	public String getSysId() {
		return sysId;
	}

	public void setSysId(String sysId) {
		this.sysId = sysId;
	}

	public Integer getOverdueDayCount() {
		return overdueDayCount;
	}

	public void setOverdueDayCount(Integer overdueDayCount) {
		this.overdueDayCount = overdueDayCount;
	}

}
