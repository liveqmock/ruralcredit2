package com.creditease.rc.domain;

import java.math.BigDecimal;

public class LaonBalanceData {

        private  Long        laonBalanceDataId       ; //贷款余额数据主键
        private  String      sysGuid                 ; //销售系统申请编号
        private  BigDecimal  dCorpus                 ; //应还本金
        private  BigDecimal  dInterest               ; //应还利息
        private  BigDecimal  dOverhead               ; //应还服务费
        private  BigDecimal  dCharges                ; //应还本息费
        private  BigDecimal  fCorpus                 ; //实收本金
        private  BigDecimal  fInterest               ; //实收利息
        private  BigDecimal  fOverhead               ; //实收服务费
        private  BigDecimal  fCharges                ; //实收本息费
        private  BigDecimal  dfCorpus                ; //未还本金
        private  BigDecimal  dfInterest              ; //未还利息
        private  BigDecimal  dfOverhead              ; //未还服务费
        private  BigDecimal  dfCharges               ; //未还本息费
        
        
        
        
        
        
        
		public Long getLaonBalanceDataId() {
			return laonBalanceDataId;
		}
		public void setLaonBalanceDataId(Long laonBalanceDataId) {
			this.laonBalanceDataId = laonBalanceDataId;
		}
		public String getSysGuid() {
			return sysGuid;
		}
		public void setSysGuid(String sysGuid) {
			this.sysGuid = sysGuid;
		}
		public BigDecimal getdCorpus() {
			return dCorpus;
		}
		public void setdCorpus(BigDecimal dCorpus) {
			this.dCorpus = dCorpus;
		}
		public BigDecimal getdInterest() {
			return dInterest;
		}
		public void setdInterest(BigDecimal dInterest) {
			this.dInterest = dInterest;
		}
		public BigDecimal getdOverhead() {
			return dOverhead;
		}
		public void setdOverhead(BigDecimal dOverhead) {
			this.dOverhead = dOverhead;
		}
		public BigDecimal getdCharges() {
			return dCharges;
		}
		public void setdCharges(BigDecimal dCharges) {
			this.dCharges = dCharges;
		}
		public BigDecimal getfCorpus() {
			return fCorpus;
		}
		public void setfCorpus(BigDecimal fCorpus) {
			this.fCorpus = fCorpus;
		}
		public BigDecimal getfInterest() {
			return fInterest;
		}
		public void setfInterest(BigDecimal fInterest) {
			this.fInterest = fInterest;
		}
		public BigDecimal getfOverhead() {
			return fOverhead;
		}
		public void setfOverhead(BigDecimal fOverhead) {
			this.fOverhead = fOverhead;
		}
		public BigDecimal getfCharges() {
			return fCharges;
		}
		public void setfCharges(BigDecimal fCharges) {
			this.fCharges = fCharges;
		}
		public BigDecimal getDfCorpus() {
			return dfCorpus;
		}
		public void setDfCorpus(BigDecimal dfCorpus) {
			this.dfCorpus = dfCorpus;
		}
		public BigDecimal getDfInterest() {
			return dfInterest;
		}
		public void setDfInterest(BigDecimal dfInterest) {
			this.dfInterest = dfInterest;
		}
		public BigDecimal getDfOverhead() {
			return dfOverhead;
		}
		public void setDfOverhead(BigDecimal dfOverhead) {
			this.dfOverhead = dfOverhead;
		}
		public BigDecimal getDfCharges() {
			return dfCharges;
		}
		public void setDfCharges(BigDecimal dfCharges) {
			this.dfCharges = dfCharges;
		}

	
	
	
	
}
