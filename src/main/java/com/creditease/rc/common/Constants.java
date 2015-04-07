package com.creditease.rc.common;
/**
 * Title:常量类
 * Description: 宜农贷2.0系统开发
 * Copyright:Copyright (c) 2011
 * Company:普信恒业科技发展（北京）有限公司
 * 2011-6-30
 * @author wangxinqiang
 * @version v1.0
 */
public class Constants {
	
	/**
	 * 农商贷二期系统标识号
	 */
	public static final String SYSTEM_SIGN = "44";
	
	/**
	 * 综合信贷系统标识号
	 */
	public static final String SYSTEM_SIGN_ICP = "97";
	
	/**
	 * 图片配置文件名
	 */
	public static final String PICTURECONFIG = "picture.properties";
	
	/**
	 * 上传图片存储路径在配置文件中的key，即通过该key可以在图片配置文件中提取对应的vlue
	 */
	public static final String PICTURESTORAGEDIRECTORY = "picStorageDir";
	
	/**
	 * 上传图片存储路径在配置文件中的key，即通过该key可以在图片配置文件中提取对应的vlue
	 */
	public static final String ZIPPICTURESTORAGEDIRECTORY = "zipPicStorageDir";
	
	/**
	 * 小组公共资料
	 */
	public static final String FILE_TYPE_GROUP_FILES = "2";
	/**
	 * 个人其它资料
	 */
	public static final String FILE_TYPE_BORROWER_OTHERS = "1";
	
	/**
	 * 放款合同
	 */
	public static final String FILE_TYPE_PROTOCOL_FILE = "3";
	
	/**
	 * 媒体文件
	 */
	public static final String FILE_TYPE_MEDIA = "1";
	
	/**
	 * 征信委托书
	 */
	public static final String FILE_TYPE_CREDIT = "2";
	
	/**
	 * 表格
	 */
	public static final String FILE_TYPE_TABLE = "3";
	
	/**
	 * 证件
	 */
	public static final String FILE_TYPE_CERTIFICATE = "4";
	
	/**
	 * 贷后文件
	 */
	public static final String FILE_TYPE_LOAN_AFTER = "5";
	
	/**
	 * 免罚申请单
	 */
	public static final String FILE_TYPE_BREACH_ABSOLUTION = "6";
	/**
	 * 分析报告
	 */
	public static final String FILE_TYPE_ANALYSIS_REPORT = "7";
	
	/**
	 *其他资料上传类名称
	 */
	public static final String BORROWER_SERVICE_APP_CLASS="com.creditease.rc.domain.BorrowerServiceApp";
	
	/**
	 * 小组共同资料上传类名称
	 */
	public static final String CREDIT_APPLICATION_CLASS =  "com.creditease.rc.domain.CreditApplication";
	
	/**
	 * 放款登记上传类名称
	 */
	public static final String GROUP_LOAN_REGIST_CLASS =  "com.creditease.rc.domain.GroupLoanRegist";
	/**
	 * 违约金免罚上传类名称
	 */
	public static final String BREACH_PENALTY_CLASS =  "com.creditease.rc.domain.BreachRegist";
	
	/**申请状态表
	 *  00-未提交       
		01-待审查     
		04-审批通过 
		18-审批拒绝
		06-放款失败登记   
		07-撤回         
		10-款项到位     
		//11-已放款登记   
		//13-放款确认     
		15-还款中       
		16-还款完成  
		20-提前还款完成
		//21-放款额度确认
		24-审批中
		26-注销
		27- 据贷
		28-客户放弃
		29-不良贷款
		30-法律诉讼
        31-待城市审批
        32-城市审批中
        33_等待上传合同
        34_等待重新打印合同
        35_等待合同复核
        36_等待信托复核
        37_等待重新合同复核
        38_等待放款
	 */
	public static final String STATE_0="00";
	public static final String STATE_1="01";
	public static final String STATE_4="04";
	public static final String STATE_6="06";
	public static final String STATE_7="07";
	public static final String STATE_10="10";
	public static final String STATE_11="11";
	public static final String STATE_13="13";
	public static final String STATE_15="15";
	public static final String STATE_16="16";
	public static final String STATE_18="18";
	public static final String STATE_19="19";
	public static final String STATE_20="20";
	public static final String STATE_21="21";
	public static final String STATE_22="22";
	public static final String STATE_23="23";
	public static final String STATE_24="24";
	public static final String STATE_25="25";
	public static final String STATE_26="26";
	public static final String STATE_27="27";
	public static final String STATE_28="28";
	
	public static final String STATE_29="29";
	public static final String STATE_30="30";
	
	public static final String STATE_31="31";
	public static final String STATE_32="32";
	public static final String STATE_33="33";
	public static final String STATE_34="34";
	public static final String STATE_35="35";
	public static final String STATE_36="36";
	public static final String STATE_37="37";
	public static final String STATE_38="38";

	/**
	 * 银行字典类型
	 */
	public static final String CODETABLE_SECTION_BANK="bank";
	
	//payplatformParams.properties中参数名称常量 begin
	/**
	 * 产品代码ID,值=系统来源ID+"_"+下标
	 */
	public static final String PRODUCT_ID="productId";
	/**
	 * 系统来源ID,与结算平台中业务系统授权一致
	 */
	public static final String SYSTEM_SOURCE_ID="systemSourceId";
	/**
	 * 加密标识
	 */
	public static final String ENCRYPTION_KEY="encryptionKey";
	/**
	 * 财务状态  
	 * 0-未预约
	 * 1-已预约
	 * 2-成功
	 * 3-失败
	 * 6-违约;
	 * 4:撤销
	 * 9:线下收款
	 * 5:作废
	 */
	 
	public static final String FINANCE_STATE_NOBESPEAK="0";
	public static final String FINANCE_STATE_BESPEAK="1";
	public static final String FINANCE_STATE_SUCESS="2";
	public static final String FINANCE_STATE_FAIL="3";
	public static final String FINANCE_STATE_BREACH="6";
	public static final String FINANCE_STATE_UNDO="4";
	public static final String FINANCE_STATE_Receive_OFFLINE="9";
	public static final String FINANCE_STATE_OUTLINE="9";
	public static final String FINANCE_STATE_CANCEL="5";
	
	/**
	 * 财务状态类型
	 *  类型Type(1-财务付款;2-撤销;3-正常收款,4-违约收款,5-财务付款撤销 6-提前还款)
	 */
	public static final String FINANCE_TYPE_PAYING="1";
	public static final String FINANCE_TYPE_REPEAL="2";
	public static final String FINANCE_TYPE_RECEIVE="3";
	public static final String FINANCE_TYPE_BREACH="4";
	public static final String FINANCE_TYPE_UNDO="5";
	public static final String FINANCE_TYPE_PREREPAYMENT="6";
	
	/** -1:收/放款失败 ;0:收/放款成功;1:等待收/放款 8:等待撤销;9:撤销 **/
	public static final String PAYPLATFORM_STATE_SUCCESS="0";
	/** -1:收/放款失败 ;0:收/放款成功;1:等待收/放款 8:等待撤销;9:撤销 **/
	public static final String PAYPLATFORM_STATE_FAIL="-1";
	/** -1:收/放款失败 ;0:收/放款成功;1:等待收/放款 8:等待撤销;9:撤销 **/
	public static final String PAYPLATFORM_STATE_WAIT="1";
	/** -1:收/放款失败 ;0:收/放款成功;1:等待收/放款 8:等待撤销;9:撤销 **/
	public static final String PAYPLATFORM_STATE_WAITDO="8";
	/** -1:收/放款失败 ;0:收/放款成功;1:等待收/放款 8:等待撤销;9:撤销 **/
	public static final String PAYPLATFORM_STATE_UNDO="9";
	
	/** 上传文件返回状态-成功**/
	public static final String UPLOAD_STATE_SUCCESS = "0";
	/** 上传文件返回状态-没有申请单ID**/
	public static final String UPLOAD_STATE_FAIL_NOID = "1";
	/** 上传文件返回状态-失败**/
	public static final String UPLOAD_STATE_FAIL_ERROR = "2";
	/** 风险经理 
	 * 
	 */
	public static final String ROLE_RISK_MGR="fxapprove";
	/** 业务经理 
	 * 
	 */
	public static final String ROLE_LOAN_MGR="approve";
	/** 内容管理平台表名 **/
	public static final String CM_U_YINONGDAI2_YW = "CM_U_YINONGDAI2_YW";
	/** CM放款登记确认标记字段 **/
	public static final String CM_LOAN = "CM_LOAN";
	/** 审批确认标记字段 **/
	public static final String CM_EXAM = "CM_EXAM";
	/** 生成业务编码规则文件名 **/
	public static final String SERIAL_NUMBER = "BusinessSerialNumber.dat";
	/** 营业部编码section **/
	public static final String DEPARTMENTSN = "departmentSN";
	/** 营业部编码不存在标识 **/
	public static final String DEPARTMENTSN_NO = "departmentsn_no";
	/** 产品编码section **/
	public static final String PRODUCTSN = "productSN";
	/** 产品编码不存在标识 **/
	public static final String PRODUCTSN_NO = "productsn_no";
	/**
	 * 0 - 已放款登记
	 * 1 - 放款确认
	 * 2 - 放款确认回退
	 */
	public static final String LOAN_STATE_0="0";
	public static final String LOAN_STATE_1="1";
	public static final String LOAN_STATE_2="2";
	
	/**
	 * 0 - 借款人
	 * 1 - 担保人
	 */
	public static final String CUSTOMER_TYPE1="0";
	public static final String CUSTOMER_TYPE2="1";
	
	/**
	 * 审批结果
	 * 00 - 审批通过
	 * 11 - 审批拒绝
	 */
	public static final String AUDIT_STATE_PASS="00";
	public static final String AUDIT_STATE_REFUSE="11";
	
	/**
	 * 0-业务经理
	 * 1-风险经理
	 */
	public static final String AUDIT_TYPE_BS="0";
	public static final String AUDIT_TYPE_FX="1";
	
	/** 卡信息中的状态 **/
	/**
	 * 0-公司账户
	 * 1-个人账户
	 */
	public static final String ACCOUNT_TYPE_0 = "0";
	public static final String ACCOUNT_TYPE_1 = "1";
	
	/**一期中正在还款5**/
	public static final String CUSTOME_RTYPE_FIRSTDO = "5";
	
	/** 咨询中的状态
	 * 0-没有借款
	 * 1-借款
	 */
	public static final String CONSULT_STATUS_0 = "0";
	public static final String CONSULT_STATUS_1 = "1";
	/** 保存状态 1-全部保存 0-暂存 **/
	public static final String DO_SAVE_STATUS = "1";
	/** 保存状态 1-全部保存 0-暂存 **/
	public static final String TEMP_SAVE_STATUS = "0";

    /*
    区域风险经理（废弃：均采用统一参审入口）
     */
    public static final String ROLE_REGION_RISK_MGR="qyfxapprove";

    /*
    区域经理（废弃：均采用统一参审入口）
     */
    public static final String ROLE_REGION_MGR="qyapprove";

	/*
	参审
	 */
	public static final String ROLE_PARTICIPATE = "participateApprove";

    /**
     * 2-区域经理
     * 3-区域风险经理
     */
    public static final String AUDIT_TYPE_QY="2";
    public static final String AUDIT_TYPE_QYFX="3";

	/*
	4 - 参审
	 */
    public static final String AUDIT_TYPE_PARTICIPATE = "4";
}

