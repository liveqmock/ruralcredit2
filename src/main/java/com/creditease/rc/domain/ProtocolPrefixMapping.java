package com.creditease.rc.domain;

import java.util.Date;

public class ProtocolPrefixMapping {
    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column RL_PROTOCOL_PREFIX_MAPPING.PREFIX_ID
     *
     * @abatorgenerated
     */
    private Long prefixId;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column RL_PROTOCOL_PREFIX_MAPPING.BRANCHOFFICE_NAME
     *
     * @abatorgenerated
     */
    private String branchofficeName;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column RL_PROTOCOL_PREFIX_MAPPING.PREFIX_NUMBER
     *
     * @abatorgenerated
     */
    private String prefixNumber;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column RL_PROTOCOL_PREFIX_MAPPING.BRANCHOFFICE_ID
     *
     * @abatorgenerated
     */
    private String branchofficeId;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column RL_PROTOCOL_PREFIX_MAPPING.OPERATOR
     *
     * @abatorgenerated
     */
    private String operator;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column RL_PROTOCOL_PREFIX_MAPPING.OPERATE_DATE
     *
     * @abatorgenerated
     */
    private Date operateDate;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column RL_PROTOCOL_PREFIX_MAPPING.ON_OFF
     *
     * @abatorgenerated
     */
    private String onOff;

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column RL_PROTOCOL_PREFIX_MAPPING.PREFIX_ID
     *
     * @return the value of RL_PROTOCOL_PREFIX_MAPPING.PREFIX_ID
     *
     * @abatorgenerated
     */
    
    private String provinceHome;		//省
    private String cityHome;			//市
    private String countyHome;			//区
    
    private String addRess;				//户籍地址
    
    
    
    
    
    
    public String getAddRess() {
		return addRess;
	}

	public void setAddRess(String addRess) {
		this.addRess = addRess;
	}

	public String getProvinceHome() {
		return provinceHome;
	}

	public void setProvinceHome(String provinceHome) {
		this.provinceHome = provinceHome;
	}

	public String getCityHome() {
		return cityHome;
	}

	public void setCityHome(String cityHome) {
		this.cityHome = cityHome;
	}

	public String getCountyHome() {
		return countyHome;
	}

	public void setCountyHome(String countyHome) {
		this.countyHome = countyHome;
	}

	public Long getPrefixId() {
        return prefixId;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column RL_PROTOCOL_PREFIX_MAPPING.PREFIX_ID
     *
     * @param prefixId the value for RL_PROTOCOL_PREFIX_MAPPING.PREFIX_ID
     *
     * @abatorgenerated
     */
    public void setPrefixId(Long prefixId) {
        this.prefixId = prefixId;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column RL_PROTOCOL_PREFIX_MAPPING.BRANCHOFFICE_NAME
     *
     * @return the value of RL_PROTOCOL_PREFIX_MAPPING.BRANCHOFFICE_NAME
     *
     * @abatorgenerated
     */
    public String getBranchofficeName() {
        return branchofficeName;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column RL_PROTOCOL_PREFIX_MAPPING.BRANCHOFFICE_NAME
     *
     * @param branchofficeName the value for RL_PROTOCOL_PREFIX_MAPPING.BRANCHOFFICE_NAME
     *
     * @abatorgenerated
     */
    public void setBranchofficeName(String branchofficeName) {
        this.branchofficeName = branchofficeName == null ? null : branchofficeName.trim();
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column RL_PROTOCOL_PREFIX_MAPPING.PREFIX_NUMBER
     *
     * @return the value of RL_PROTOCOL_PREFIX_MAPPING.PREFIX_NUMBER
     *
     * @abatorgenerated
     */
    public String getPrefixNumber() {
        return prefixNumber;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column RL_PROTOCOL_PREFIX_MAPPING.PREFIX_NUMBER
     *
     * @param prefixNumber the value for RL_PROTOCOL_PREFIX_MAPPING.PREFIX_NUMBER
     *
     * @abatorgenerated
     */
    public void setPrefixNumber(String prefixNumber) {
        this.prefixNumber = prefixNumber == null ? null : prefixNumber.trim();
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column RL_PROTOCOL_PREFIX_MAPPING.BRANCHOFFICE_ID
     *
     * @return the value of RL_PROTOCOL_PREFIX_MAPPING.BRANCHOFFICE_ID
     *
     * @abatorgenerated
     */
    public String getBranchofficeId() {
        return branchofficeId;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column RL_PROTOCOL_PREFIX_MAPPING.BRANCHOFFICE_ID
     *
     * @param branchofficeId the value for RL_PROTOCOL_PREFIX_MAPPING.BRANCHOFFICE_ID
     *
     * @abatorgenerated
     */
    public void setBranchofficeId(String branchofficeId) {
        this.branchofficeId = branchofficeId == null ? null : branchofficeId.trim();
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column RL_PROTOCOL_PREFIX_MAPPING.OPERATOR
     *
     * @return the value of RL_PROTOCOL_PREFIX_MAPPING.OPERATOR
     *
     * @abatorgenerated
     */
    public String getOperator() {
        return operator;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column RL_PROTOCOL_PREFIX_MAPPING.OPERATOR
     *
     * @param operator the value for RL_PROTOCOL_PREFIX_MAPPING.OPERATOR
     *
     * @abatorgenerated
     */
    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column RL_PROTOCOL_PREFIX_MAPPING.OPERATE_DATE
     *
     * @return the value of RL_PROTOCOL_PREFIX_MAPPING.OPERATE_DATE
     *
     * @abatorgenerated
     */
    public Date getOperateDate() {
        return operateDate;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column RL_PROTOCOL_PREFIX_MAPPING.OPERATE_DATE
     *
     * @param operateDate the value for RL_PROTOCOL_PREFIX_MAPPING.OPERATE_DATE
     *
     * @abatorgenerated
     */
    public void setOperateDate(Date operateDate) {
        this.operateDate = operateDate;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column RL_PROTOCOL_PREFIX_MAPPING.ON_OFF
     *
     * @return the value of RL_PROTOCOL_PREFIX_MAPPING.ON_OFF
     *
     * @abatorgenerated
     */
    public String getOnOff() {
        return onOff;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column RL_PROTOCOL_PREFIX_MAPPING.ON_OFF
     *
     * @param onOff the value for RL_PROTOCOL_PREFIX_MAPPING.ON_OFF
     *
     * @abatorgenerated
     */
    public void setOnOff(String onOff) {
        this.onOff = onOff == null ? null : onOff.trim();
    }
}