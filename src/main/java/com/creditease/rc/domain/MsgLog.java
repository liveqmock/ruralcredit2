package com.creditease.rc.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: zhangwei
 * Date: 14-4-28
 * Time: 下午3:13
 */
public class MsgLog implements Serializable{

    private Long msgLogId;//记录ID
    private Long creditapllicationId;//信贷申请ID
    private Date operateDate = new Date();//操作时间
    private Long operatorId;//操作人ID
    private String operatorName;//操作人姓名
    private Double maxloanlimitError;//错误最大借款额度
    private Double maxloanlimitRight;//正确最大借款额度
    private Double deviationRate;//误差率

   

    public Long getMsgLogId() {
		return msgLogId;
	}

	public void setMsgLogId(Long msgLogId) {
		this.msgLogId = msgLogId;
	}

	

    public Long getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Long operatorId) {
        this.operatorId = operatorId;
    }

    public Date getOperateDate() {
		return operateDate;
	}

	public void setOperateDate(Date operateDate) {
		this.operateDate = operateDate;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

    public Long getCreditapllicationId() {
		return creditapllicationId;
	}

	public void setCreditapllicationId(Long creditapllicationId) {
		this.creditapllicationId = creditapllicationId;
	}

	public Double getMaxloanlimitError() {
		return maxloanlimitError;
	}

	public void setMaxloanlimitError(Double maxloanlimitError) {
		this.maxloanlimitError = maxloanlimitError;
	}

	public Double getMaxloanlimitRight() {
		return maxloanlimitRight;
	}

	public void setMaxloanlimitRight(Double maxloanlimitRight) {
		this.maxloanlimitRight = maxloanlimitRight;
	}

	public Double getDeviationRate() {
        return deviationRate;
    }

    public void setDeviationRate(Double deviationRate) {
        this.deviationRate = deviationRate;
    }
}
