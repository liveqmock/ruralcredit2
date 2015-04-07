package com.creditease.rc.vo;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.creditease.rc.common.JsonYMDHMDDateSerializer;
import com.creditease.rc.domain.CustomerConsult;

public class CustomerConsultVo extends CustomerConsult {

	private Date consultDateBegin;
	private Date consultDateEnd;
    private Date operateDateBegin;
    private Date operateDateEnd;

	@JsonSerialize(using = JsonYMDHMDDateSerializer.class)
	public Date getConsultDateBegin() {
		return consultDateBegin;
	}
	public void setConsultDateBegin(Date consultDateBegin) {
		this.consultDateBegin = consultDateBegin;
	}
	@JsonSerialize(using = JsonYMDHMDDateSerializer.class)
	public Date getConsultDateEnd() {
		return consultDateEnd;
	}
	public void setConsultDateEnd(Date consultDateEnd) {
		this.consultDateEnd = consultDateEnd;
	}
    @JsonSerialize(using = JsonYMDHMDDateSerializer.class)
    public Date getOperateDateBegin() {
        return operateDateBegin;
    }
    public void setOperateDateBegin(Date operateDateBegin) {
        this.operateDateBegin = operateDateBegin;
    }
    @JsonSerialize(using = JsonYMDHMDDateSerializer.class)
    public Date getOperateDateEnd() {
        return operateDateEnd;
    }
    public void setOperateDateEnd(Date operateDateEnd) {
        this.operateDateEnd = operateDateEnd;
    }
}
