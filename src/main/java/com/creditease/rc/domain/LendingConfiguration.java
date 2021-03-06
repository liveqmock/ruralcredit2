package com.creditease.rc.domain;

public class LendingConfiguration {
    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column RL_LENDING_CONFIGURATION.LENDING_CONFIGURATION_ID
     *
     * @abatorgenerated
     */
    private Long lendingConfigurationId;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column RL_LENDING_CONFIGURATION.AREA_DEPARTMENT_ID
     *
     * @abatorgenerated
     */
    private Long areaDepartmentId;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column RL_LENDING_CONFIGURATION.AREA_DEPARTMENT_NAME
     *
     * @abatorgenerated
     */
    private String areaDepartmentName;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column RL_LENDING_CONFIGURATION.TRUST_LENDING
     *
     * @abatorgenerated
     */
    private String trustLending;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column RL_LENDING_CONFIGURATION.DEBENTURE_TRANSFER
     *
     * @abatorgenerated
     */
    private String debentureTransfer;

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column RL_LENDING_CONFIGURATION.LENDING_CONFIGURATION_ID
     *
     * @return the value of RL_LENDING_CONFIGURATION.LENDING_CONFIGURATION_ID
     *
     * @abatorgenerated
     */
    public Long getLendingConfigurationId() {
        return lendingConfigurationId;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column RL_LENDING_CONFIGURATION.LENDING_CONFIGURATION_ID
     *
     * @param lendingConfigurationId the value for RL_LENDING_CONFIGURATION.LENDING_CONFIGURATION_ID
     *
     * @abatorgenerated
     */
    public void setLendingConfigurationId(Long lendingConfigurationId) {
        this.lendingConfigurationId = lendingConfigurationId;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column RL_LENDING_CONFIGURATION.AREA_DEPARTMENT_ID
     *
     * @return the value of RL_LENDING_CONFIGURATION.AREA_DEPARTMENT_ID
     *
     * @abatorgenerated
     */
    public Long getAreaDepartmentId() {
        return areaDepartmentId;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column RL_LENDING_CONFIGURATION.AREA_DEPARTMENT_ID
     *
     * @param areaDepartmentId the value for RL_LENDING_CONFIGURATION.AREA_DEPARTMENT_ID
     *
     * @abatorgenerated
     */
    public void setAreaDepartmentId(Long areaDepartmentId) {
        this.areaDepartmentId = areaDepartmentId;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column RL_LENDING_CONFIGURATION.AREA_DEPARTMENT_NAME
     *
     * @return the value of RL_LENDING_CONFIGURATION.AREA_DEPARTMENT_NAME
     *
     * @abatorgenerated
     */
    public String getAreaDepartmentName() {
        return areaDepartmentName;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column RL_LENDING_CONFIGURATION.AREA_DEPARTMENT_NAME
     *
     * @param areaDepartmentName the value for RL_LENDING_CONFIGURATION.AREA_DEPARTMENT_NAME
     *
     * @abatorgenerated
     */
    public void setAreaDepartmentName(String areaDepartmentName) {
        this.areaDepartmentName = areaDepartmentName == null ? null : areaDepartmentName.trim();
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column RL_LENDING_CONFIGURATION.TRUST_LENDING
     *
     * @return the value of RL_LENDING_CONFIGURATION.TRUST_LENDING
     *
     * @abatorgenerated
     */
    public String getTrustLending() {
        return trustLending;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column RL_LENDING_CONFIGURATION.TRUST_LENDING
     *
     * @param trustLending the value for RL_LENDING_CONFIGURATION.TRUST_LENDING
     *
     * @abatorgenerated
     */
    public void setTrustLending(String trustLending) {
        this.trustLending = trustLending == null ? null : trustLending.trim();
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column RL_LENDING_CONFIGURATION.DEBENTURE_TRANSFER
     *
     * @return the value of RL_LENDING_CONFIGURATION.DEBENTURE_TRANSFER
     *
     * @abatorgenerated
     */
    public String getDebentureTransfer() {
        return debentureTransfer;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column RL_LENDING_CONFIGURATION.DEBENTURE_TRANSFER
     *
     * @param debentureTransfer the value for RL_LENDING_CONFIGURATION.DEBENTURE_TRANSFER
     *
     * @abatorgenerated
     */
    public void setDebentureTransfer(String debentureTransfer) {
        this.debentureTransfer = debentureTransfer == null ? null : debentureTransfer.trim();
    }
}