package com.creditease.rc.vo;

import com.creditease.rc.domain.RlAuditDetail;
/**
 * 
 * @author xubingzhao
 *
 */
public class RlAuditDetailVO extends RlAuditDetail{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5514828121812348045L;
	private Double chgMount;

	public Double getChgMount() {
		return chgMount;
	}

	public void setChgMount(Double chgMount) {
		this.chgMount = chgMount;
	}

}
