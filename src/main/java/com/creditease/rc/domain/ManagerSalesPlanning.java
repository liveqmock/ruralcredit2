package com.creditease.rc.domain;

import java.math.BigDecimal;
import java.util.Date;

public class ManagerSalesPlanning {
    private Long managerSalesPlanningId;    //营业部经理销售计划主键
    private Long year;					    //年份
    private String type;					//数据类型：0、放款量，1、合同金额
    private Long comEmpId;					//信贷员ID
    private String Name;					//信贷员姓名
    private Long operatorId;				//操作人ID
    private String operatorName;			//操作人姓名
    private Date creditDate;				//首次创建日期
    private Long areaDepartmentId;			//营业部ID
    private String areaDepartmentName;		//营业部名称
    private BigDecimal jan;					//一月
    private BigDecimal feb;					//二月
    private BigDecimal mar;					//三月
    private BigDecimal apr;					//四月
    private BigDecimal may;					//五月
    private BigDecimal jun;					//六月
    private BigDecimal jul;					//七月
    private BigDecimal aug;					//八月
    private BigDecimal sep;					//九月
    private BigDecimal oct;					//十月
    private BigDecimal nov;					//十一月
    private BigDecimal dec;					//十二月
    
    
	public Long getManagerSalesPlanningId() {
        return managerSalesPlanningId;
    }
    public void setManagerSalesPlanningId(Long managerSalesPlanningId) {
        this.managerSalesPlanningId = managerSalesPlanningId;
    }
    public Long getYear() {
        return year;
    }
    public void setYear(Long year) {
        this.year = year;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Long getComEmpId() {
		return comEmpId;
	}
	public void setComEmpId(Long comEmpId) {
		this.comEmpId = comEmpId;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public Long getOperatorId() {
        return operatorId;
    }
    public void setOperatorId(Long operatorId) {
        this.operatorId = operatorId;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName == null ? null : operatorName.trim();
    }

    public Date getCreditDate() {
        return creditDate;
    }

    public void setCreditDate(Date creditDate) {
        this.creditDate = creditDate;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column RL_MANAGER_SALES_PLANNING.AREA_DEPARTMENT_ID
     *
     * @return the value of RL_MANAGER_SALES_PLANNING.AREA_DEPARTMENT_ID
     *
     * @abatorgenerated
     */
    public Long getAreaDepartmentId() {
        return areaDepartmentId;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column RL_MANAGER_SALES_PLANNING.AREA_DEPARTMENT_ID
     *
     * @param areaDepartmentId the value for RL_MANAGER_SALES_PLANNING.AREA_DEPARTMENT_ID
     *
     * @abatorgenerated
     */
    public void setAreaDepartmentId(Long areaDepartmentId) {
        this.areaDepartmentId = areaDepartmentId;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column RL_MANAGER_SALES_PLANNING.AREA_DEPARTMENT_NAME
     *
     * @return the value of RL_MANAGER_SALES_PLANNING.AREA_DEPARTMENT_NAME
     *
     * @abatorgenerated
     */
    public String getAreaDepartmentName() {
        return areaDepartmentName;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column RL_MANAGER_SALES_PLANNING.AREA_DEPARTMENT_NAME
     *
     * @param areaDepartmentName the value for RL_MANAGER_SALES_PLANNING.AREA_DEPARTMENT_NAME
     *
     * @abatorgenerated
     */
    public void setAreaDepartmentName(String areaDepartmentName) {
        this.areaDepartmentName = areaDepartmentName == null ? null : areaDepartmentName.trim();
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column RL_MANAGER_SALES_PLANNING.JAN
     *
     * @return the value of RL_MANAGER_SALES_PLANNING.JAN
     *
     * @abatorgenerated
     */
    public BigDecimal getJan() {
        return jan;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column RL_MANAGER_SALES_PLANNING.JAN
     *
     * @param jan the value for RL_MANAGER_SALES_PLANNING.JAN
     *
     * @abatorgenerated
     */
    public void setJan(BigDecimal jan) {
        this.jan = jan;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column RL_MANAGER_SALES_PLANNING.FEB
     *
     * @return the value of RL_MANAGER_SALES_PLANNING.FEB
     *
     * @abatorgenerated
     */
    public BigDecimal getFeb() {
        return feb;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column RL_MANAGER_SALES_PLANNING.FEB
     *
     * @param feb the value for RL_MANAGER_SALES_PLANNING.FEB
     *
     * @abatorgenerated
     */
    public void setFeb(BigDecimal feb) {
        this.feb = feb;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column RL_MANAGER_SALES_PLANNING.MAR
     *
     * @return the value of RL_MANAGER_SALES_PLANNING.MAR
     *
     * @abatorgenerated
     */
    public BigDecimal getMar() {
        return mar;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column RL_MANAGER_SALES_PLANNING.MAR
     *
     * @param mar the value for RL_MANAGER_SALES_PLANNING.MAR
     *
     * @abatorgenerated
     */
    public void setMar(BigDecimal mar) {
        this.mar = mar;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column RL_MANAGER_SALES_PLANNING.APR
     *
     * @return the value of RL_MANAGER_SALES_PLANNING.APR
     *
     * @abatorgenerated
     */
    public BigDecimal getApr() {
        return apr;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column RL_MANAGER_SALES_PLANNING.APR
     *
     * @param apr the value for RL_MANAGER_SALES_PLANNING.APR
     *
     * @abatorgenerated
     */
    public void setApr(BigDecimal apr) {
        this.apr = apr;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column RL_MANAGER_SALES_PLANNING.MAY
     *
     * @return the value of RL_MANAGER_SALES_PLANNING.MAY
     *
     * @abatorgenerated
     */
    public BigDecimal getMay() {
        return may;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column RL_MANAGER_SALES_PLANNING.MAY
     *
     * @param may the value for RL_MANAGER_SALES_PLANNING.MAY
     *
     * @abatorgenerated
     */
    public void setMay(BigDecimal may) {
        this.may = may;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column RL_MANAGER_SALES_PLANNING.JUN
     *
     * @return the value of RL_MANAGER_SALES_PLANNING.JUN
     *
     * @abatorgenerated
     */
    public BigDecimal getJun() {
        return jun;
    }

    public void setJun(BigDecimal jun) {
        this.jun = jun;
    }

    public BigDecimal getJul() {
        return jul;
    }

    public void setJul(BigDecimal jul) {
        this.jul = jul;
    }

    public BigDecimal getAug() {
        return aug;
    }

    public void setAug(BigDecimal aug) {
        this.aug = aug;
    }

    public BigDecimal getSep() {
        return sep;
    }

    public void setSep(BigDecimal sep) {
        this.sep = sep;
    }

    public BigDecimal getOct() {
        return oct;
    }

    public void setOct(BigDecimal oct) {
        this.oct = oct;
    }

    public BigDecimal getNov() {
        return nov;
    }

    public void setNov(BigDecimal nov) {
        this.nov = nov;
    }

    public BigDecimal getDec() {
        return dec;
    }

    public void setDec(BigDecimal dec) {
        this.dec = dec;
    }
}