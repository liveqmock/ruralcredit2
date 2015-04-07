package com.creditease.rc.vo;
/**
 * 
 * cs
 * Title:CreditapplicationView.java
 * Description: 宜农贷2.0系统开发
 * Copyright:Copyright (c) 2013
 * Company:普信恒业科技发展（北京）有限公司
 * 2013-6-5下午03:25:41
 * @author wyf
 * @version v2.0
 */
public class CreditapplicationView {
//	private String --公共字段
	private String customer_consult_id;
	private String creditapplication_id;
	private String sys_guid;
	private String company_id;
	private String company_name;
	private String loan_officer_id;
	private String loan_officer_name;
//	private String --产品中心数据
	private String productInfoId;
	private String productName;
	private String department_id;
	private String instalments;
	private String repayment_type;
	private String capital_upper_limit;
	private String capital_lower_limit;
//	private String --业务数据
	private String business_number;
	private String business_type;
	private String audit_status;
	private String create_date;
	private String first_submit_date;
	private String last_submit_date;
//	private String --个人信息
	private String borrower_service_app_id;
	private String borrower_name;
	private String credentials_number;
//	private String --业务经理审批记录
	private String business_applyaudittable_id;
	private String business_exam_result;
	private String business_exam_amount;
	private String business_exam_date;
	private String business_borr_remark;
//	private String --风险经理审批记录
	private String rist_applyaudittable_id;
	private String rist_exam_result;
	private String rist_exam_amount;
	private String rist_exam_date;
	private String rist_borr_remark;
//	private String --额度确认
	private String amount_confirm_id;
	private String amount_confirm_operator_id;
	private String amount_confirm_operator;
	private String amount_confirm_amount;
	private String service_charge;
	private String real_amount;
//	private String --放款登记
	private String group_loan_regist_id;
	private String protocol_no;
	private String SIGNAGREEMENT_DATE;
	private String loan_regist_person_id;
	private String loan_regist_person_name;
	private String loan_regist_date;
	private String loan_confirm_person_name;
	private String loan_confirm_time;
	private String loan_confirm_remark;
	private String account_info_id;
	private String return_account_info_id;
//	private String --财务付款
	private String pay_finance_finance_money_id;
	private String pay_finance_operator_id;
	private String pay_finance_operator_name;
	private String pay_finance_operate_date;
	private String pay_finance_biz_id;
	private String pay_finance_amount;
	private String pay_finance_remark;
	private String pay_finance_reserve_time;
	private String pay_finance_trade_time;
	private String pay_finance_return_msg;
	private String pay_finance_finance_status;
	private String credit_investigatio_id;
	private String credit_investigatio_is_submit;
	public String getCustomer_consult_id() {
		return customer_consult_id;
	}
	public void setCustomer_consult_id(String customer_consult_id) {
		this.customer_consult_id = customer_consult_id;
	}
	public String getCreditapplication_id() {
		return creditapplication_id;
	}
	public void setCreditapplication_id(String creditapplication_id) {
		this.creditapplication_id = creditapplication_id;
	}
	public String getSys_guid() {
		return sys_guid;
	}
	public void setSys_guid(String sys_guid) {
		this.sys_guid = sys_guid;
	}
	public String getCompany_id() {
		return company_id;
	}
	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	public String getLoan_officer_id() {
		return loan_officer_id;
	}
	public void setLoan_officer_id(String loan_officer_id) {
		this.loan_officer_id = loan_officer_id;
	}
	public String getLoan_officer_name() {
		return loan_officer_name;
	}
	public void setLoan_officer_name(String loan_officer_name) {
		this.loan_officer_name = loan_officer_name;
	}
	public String getProductInfoId() {
		return productInfoId;
	}
	public void setProductInfoId(String productInfoId) {
		this.productInfoId = productInfoId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getDepartment_id() {
		return department_id;
	}
	public void setDepartment_id(String department_id) {
		this.department_id = department_id;
	}
	public String getInstalments() {
		return instalments;
	}
	public void setInstalments(String instalments) {
		this.instalments = instalments;
	}
	public String getRepayment_type() {
		return repayment_type;
	}
	public void setRepayment_type(String repayment_type) {
		this.repayment_type = repayment_type;
	}
	public String getCapital_upper_limit() {
		return capital_upper_limit;
	}
	public void setCapital_upper_limit(String capital_upper_limit) {
		this.capital_upper_limit = capital_upper_limit;
	}
	public String getCapital_lower_limit() {
		return capital_lower_limit;
	}
	public void setCapital_lower_limit(String capital_lower_limit) {
		this.capital_lower_limit = capital_lower_limit;
	}
	public String getBusiness_number() {
		return business_number;
	}
	public void setBusiness_number(String business_number) {
		this.business_number = business_number;
	}
	public String getBusiness_type() {
		return business_type;
	}
	public void setBusiness_type(String business_type) {
		this.business_type = business_type;
	}
	public String getAudit_status() {
		return audit_status;
	}
	public void setAudit_status(String audit_status) {
		this.audit_status = audit_status;
	}
	public String getCreate_date() {
		return create_date;
	}
	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}
	public String getFirst_submit_date() {
		return first_submit_date;
	}
	public void setFirst_submit_date(String first_submit_date) {
		this.first_submit_date = first_submit_date;
	}
	public String getLast_submit_date() {
		return last_submit_date;
	}
	public void setLast_submit_date(String last_submit_date) {
		this.last_submit_date = last_submit_date;
	}
	public String getBorrower_service_app_id() {
		return borrower_service_app_id;
	}
	public void setBorrower_service_app_id(String borrower_service_app_id) {
		this.borrower_service_app_id = borrower_service_app_id;
	}
	public String getBorrower_name() {
		return borrower_name;
	}
	public void setBorrower_name(String borrower_name) {
		this.borrower_name = borrower_name;
	}
	public String getCredentials_number() {
		return credentials_number;
	}
	public void setCredentials_number(String credentials_number) {
		this.credentials_number = credentials_number;
	}
	public String getBusiness_applyaudittable_id() {
		return business_applyaudittable_id;
	}
	public void setBusiness_applyaudittable_id(String business_applyaudittable_id) {
		this.business_applyaudittable_id = business_applyaudittable_id;
	}
	public String getBusiness_exam_result() {
		return business_exam_result;
	}
	public void setBusiness_exam_result(String business_exam_result) {
		this.business_exam_result = business_exam_result;
	}
	public String getBusiness_exam_amount() {
		return business_exam_amount;
	}
	public void setBusiness_exam_amount(String business_exam_amount) {
		this.business_exam_amount = business_exam_amount;
	}
	public String getBusiness_exam_date() {
		return business_exam_date;
	}
	public void setBusiness_exam_date(String business_exam_date) {
		this.business_exam_date = business_exam_date;
	}
	public String getBusiness_borr_remark() {
		return business_borr_remark;
	}
	public void setBusiness_borr_remark(String business_borr_remark) {
		this.business_borr_remark = business_borr_remark;
	}
	public String getRist_applyaudittable_id() {
		return rist_applyaudittable_id;
	}
	public void setRist_applyaudittable_id(String rist_applyaudittable_id) {
		this.rist_applyaudittable_id = rist_applyaudittable_id;
	}
	public String getRist_exam_result() {
		return rist_exam_result;
	}
	public void setRist_exam_result(String rist_exam_result) {
		this.rist_exam_result = rist_exam_result;
	}
	public String getRist_exam_amount() {
		return rist_exam_amount;
	}
	public void setRist_exam_amount(String rist_exam_amount) {
		this.rist_exam_amount = rist_exam_amount;
	}
	public String getRist_exam_date() {
		return rist_exam_date;
	}
	public void setRist_exam_date(String rist_exam_date) {
		this.rist_exam_date = rist_exam_date;
	}
	public String getRist_borr_remark() {
		return rist_borr_remark;
	}
	public void setRist_borr_remark(String rist_borr_remark) {
		this.rist_borr_remark = rist_borr_remark;
	}
	public String getAmount_confirm_id() {
		return amount_confirm_id;
	}
	public void setAmount_confirm_id(String amount_confirm_id) {
		this.amount_confirm_id = amount_confirm_id;
	}
	public String getAmount_confirm_operator_id() {
		return amount_confirm_operator_id;
	}
	public void setAmount_confirm_operator_id(String amount_confirm_operator_id) {
		this.amount_confirm_operator_id = amount_confirm_operator_id;
	}
	public String getAmount_confirm_operator() {
		return amount_confirm_operator;
	}
	public void setAmount_confirm_operator(String amount_confirm_operator) {
		this.amount_confirm_operator = amount_confirm_operator;
	}
	public String getAmount_confirm_amount() {
		return amount_confirm_amount;
	}
	public void setAmount_confirm_amount(String amount_confirm_amount) {
		this.amount_confirm_amount = amount_confirm_amount;
	}
	public String getService_charge() {
		return service_charge;
	}
	public void setService_charge(String service_charge) {
		this.service_charge = service_charge;
	}
	public String getReal_amount() {
		return real_amount;
	}
	public void setReal_amount(String real_amount) {
		this.real_amount = real_amount;
	}
	public String getGroup_loan_regist_id() {
		return group_loan_regist_id;
	}
	public void setGroup_loan_regist_id(String group_loan_regist_id) {
		this.group_loan_regist_id = group_loan_regist_id;
	}
	public String getProtocol_no() {
		return protocol_no;
	}
	public void setProtocol_no(String protocol_no) {
		this.protocol_no = protocol_no;
	}
	public String getSIGNAGREEMENT_DATE() {
		return SIGNAGREEMENT_DATE;
	}
	public void setSIGNAGREEMENT_DATE(String sIGNAGREEMENT_DATE) {
		SIGNAGREEMENT_DATE = sIGNAGREEMENT_DATE;
	}
	public String getLoan_regist_person_id() {
		return loan_regist_person_id;
	}
	public void setLoan_regist_person_id(String loan_regist_person_id) {
		this.loan_regist_person_id = loan_regist_person_id;
	}
	public String getLoan_regist_person_name() {
		return loan_regist_person_name;
	}
	public void setLoan_regist_person_name(String loan_regist_person_name) {
		this.loan_regist_person_name = loan_regist_person_name;
	}
	public String getLoan_regist_date() {
		return loan_regist_date;
	}
	public void setLoan_regist_date(String loan_regist_date) {
		this.loan_regist_date = loan_regist_date;
	}
	public String getLoan_confirm_person_name() {
		return loan_confirm_person_name;
	}
	public void setLoan_confirm_person_name(String loan_confirm_person_name) {
		this.loan_confirm_person_name = loan_confirm_person_name;
	}
	public String getLoan_confirm_time() {
		return loan_confirm_time;
	}
	public void setLoan_confirm_time(String loan_confirm_time) {
		this.loan_confirm_time = loan_confirm_time;
	}
	public String getLoan_confirm_remark() {
		return loan_confirm_remark;
	}
	public void setLoan_confirm_remark(String loan_confirm_remark) {
		this.loan_confirm_remark = loan_confirm_remark;
	}
	public String getAccount_info_id() {
		return account_info_id;
	}
	public void setAccount_info_id(String account_info_id) {
		this.account_info_id = account_info_id;
	}
	public String getReturn_account_info_id() {
		return return_account_info_id;
	}
	public void setReturn_account_info_id(String return_account_info_id) {
		this.return_account_info_id = return_account_info_id;
	}
	public String getPay_finance_finance_money_id() {
		return pay_finance_finance_money_id;
	}
	public void setPay_finance_finance_money_id(String pay_finance_finance_money_id) {
		this.pay_finance_finance_money_id = pay_finance_finance_money_id;
	}
	public String getPay_finance_operator_id() {
		return pay_finance_operator_id;
	}
	public void setPay_finance_operator_id(String pay_finance_operator_id) {
		this.pay_finance_operator_id = pay_finance_operator_id;
	}
	public String getPay_finance_operator_name() {
		return pay_finance_operator_name;
	}
	public void setPay_finance_operator_name(String pay_finance_operator_name) {
		this.pay_finance_operator_name = pay_finance_operator_name;
	}
	public String getPay_finance_operate_date() {
		return pay_finance_operate_date;
	}
	public void setPay_finance_operate_date(String pay_finance_operate_date) {
		this.pay_finance_operate_date = pay_finance_operate_date;
	}
	public String getPay_finance_biz_id() {
		return pay_finance_biz_id;
	}
	public void setPay_finance_biz_id(String pay_finance_biz_id) {
		this.pay_finance_biz_id = pay_finance_biz_id;
	}
	public String getPay_finance_amount() {
		return pay_finance_amount;
	}
	public void setPay_finance_amount(String pay_finance_amount) {
		this.pay_finance_amount = pay_finance_amount;
	}
	public String getPay_finance_remark() {
		return pay_finance_remark;
	}
	public void setPay_finance_remark(String pay_finance_remark) {
		this.pay_finance_remark = pay_finance_remark;
	}
	public String getPay_finance_reserve_time() {
		return pay_finance_reserve_time;
	}
	public void setPay_finance_reserve_time(String pay_finance_reserve_time) {
		this.pay_finance_reserve_time = pay_finance_reserve_time;
	}
	public String getPay_finance_trade_time() {
		return pay_finance_trade_time;
	}
	public void setPay_finance_trade_time(String pay_finance_trade_time) {
		this.pay_finance_trade_time = pay_finance_trade_time;
	}
	public String getPay_finance_return_msg() {
		return pay_finance_return_msg;
	}
	public void setPay_finance_return_msg(String pay_finance_return_msg) {
		this.pay_finance_return_msg = pay_finance_return_msg;
	}
	public String getPay_finance_finance_status() {
		return pay_finance_finance_status;
	}
	public void setPay_finance_finance_status(String pay_finance_finance_status) {
		this.pay_finance_finance_status = pay_finance_finance_status;
	}
	public String getCredit_investigatio_id() {
		return credit_investigatio_id;
	}
	public void setCredit_investigatio_id(String credit_investigatio_id) {
		this.credit_investigatio_id = credit_investigatio_id;
	}
	public String getCredit_investigatio_is_submit() {
		return credit_investigatio_is_submit;
	}
	public void setCredit_investigatio_is_submit(
			String credit_investigatio_is_submit) {
		this.credit_investigatio_is_submit = credit_investigatio_is_submit;
	}
	
	
}
