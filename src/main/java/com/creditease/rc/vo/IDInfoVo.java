package com.creditease.rc.vo;

import com.creditease.rc.domain.*;

import java.util.List;

/**
 * 申请单 vo
 * @author zhangman
 *
 */
public class IDInfoVo {
    // 共借人信息
    private CreditCoBorrower cb;
    // 担保人信息
    private List<BorrowerService>  guarantListInfo;

    public CreditCoBorrower getCb() {
        return cb;
    }

    public List<BorrowerService> getGuarantListInfo() {
        return guarantListInfo;
    }

    public void setGuarantListInfo(List<BorrowerService> guarantListInfo) {
        this.guarantListInfo = guarantListInfo;
    }

    public BorrowerService getBorrowerInfo() {
        return borrowerInfo;
    }

    public void setBorrowerInfo(BorrowerService borrowerInfo) {
        this.borrowerInfo = borrowerInfo;
    }

    public void setCb(CreditCoBorrower cb) {
        this.cb = cb;
    }
    // 借款人信息
    private BorrowerService  borrowerInfo ;
}
