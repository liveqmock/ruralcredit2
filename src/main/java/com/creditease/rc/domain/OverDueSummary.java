package com.creditease.rc.domain;

import com.creditease.rc.common.JsonYMDDateSerializer;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.math.BigDecimal;
import java.util.Date;
/**
 *  @author  ygx
 *  *逾期汇总Domain
 *供逾期列表展示使用
 *逾期列表的数据每隔两个小时会跑批生成
 **
*/
public class OverDueSummary {
	private Long overDueSummaryId;	//逾期汇总主键
    private Long creditApplicationId;   //信贷申请id
    private Date aOverdueStart;          //逾期开始时间
    private BigDecimal aOverdueMoney;    //逾期金额
    private Integer aOverdueCount;        //逾期期数
    private Integer historyMaxOverDays;   //历史逾期最大天数

    public Integer getHistoryMaxOverDays() {
        return historyMaxOverDays;
    }

    public void setHistoryMaxOverDays(Integer historyMaxOverDays) {
        this.historyMaxOverDays = historyMaxOverDays;
    }

    public Long getOverDueSummaryId() {
        return overDueSummaryId;
    }

    public void setOverDueSummaryId(Long overDueSummaryId) {
        this.overDueSummaryId = overDueSummaryId;
    }

    public Long getCreditApplicationId() {
        return creditApplicationId;
    }

    public void setCreditApplicationId(Long creditApplicationId) {
        this.creditApplicationId = creditApplicationId;
    }

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

    public Integer getOverdueDayCount() {
        return overdueDayCount;
    }

    public void setOverdueDayCount(Integer overdueDayCount) {
        this.overdueDayCount = overdueDayCount;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    private Integer overdueDayCount;      //逾期天数
    private Date createDate;              //创建时间
    private String createUser;            //创建人


}