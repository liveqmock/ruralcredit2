package com.creditease.rc.app.sia.service.impl;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.creditease.rc.app.pdf.CeBorrowingProductsWS;
import com.creditease.rc.app.pdf.ProInfo;
import com.creditease.rc.app.pdf.ProListParam;
import com.creditease.rc.app.pdf.ProductInst;
import com.creditease.rc.app.pdf.QueryProByDepartResult;
import com.creditease.rc.app.pdf.QueryProByDepartResult.ProInfoList;
import com.creditease.rc.app.pdf.QueryProByDepartResult.ProInstList;
import com.creditease.rc.app.sia.service.IcomprehensiveServe;
import com.creditease.rc.app.sia.vo.BorrowerApplyFormInfoReq;
import com.creditease.rc.app.sia.vo.BorrowerBasicInfoVo;
import com.creditease.rc.app.sia.vo.BorrowerContactVo;
import com.creditease.rc.app.sia.vo.BorrowerContactWayVo;
import com.creditease.rc.app.sia.vo.BorrowerMarketVo;
import com.creditease.rc.app.sia.vo.BorrowerTransactionVo;
import com.creditease.rc.app.smp.EmployeeDTO;
import com.creditease.rc.dao.IApplicationDao;
import com.creditease.rc.dao.ICreditApplicationDAO;
import com.creditease.rc.dao.ICreditCoBorrowerDao;
import com.creditease.rc.dao.ICustomerConsultDAO;
import com.creditease.rc.dao.IFamilymemberDao;
import com.creditease.rc.dao.INationalStandardCodeDAO;
import com.creditease.rc.dao.IProtocolMappingDAO;
import com.creditease.rc.dao.ISpecialApplyDao;
import com.creditease.rc.dao.IWebsiteDao;
import com.creditease.rc.domain.Application;
import com.creditease.rc.domain.CreditCoBorrower;
import com.creditease.rc.domain.Familymember;
import com.creditease.rc.domain.Message;
import com.creditease.rc.domain.NationalStandardCode;
import com.creditease.rc.domain.SpecialApply;
import com.creditease.rc.domain.Website;
import com.creditease.rc.service.IBorrowerServiceAppService;
import com.creditease.rc.util.CommonUtil;
import com.creditease.rc.util.DicUtil;
import com.creditease.rc.util.JsonUtil;
import com.creditease.rc.util.SmpWSUtil;
import com.creditease.rc.vo.BorrowerServiceAppVo2;
import com.creditease.sia.api.SIA;
import com.creditease.sia.pojo.SIAMessage;

@Service
public class ComprehensiveServe implements IcomprehensiveServe{

	
	@Resource
	private IBorrowerServiceAppService iBorrowerServiceAppService;//借款人信息
	@Resource
	private IFamilymemberDao iFamilymemberDao;//家庭成员信息
	@Resource
	private ICreditCoBorrowerDao iCreditCoBorrowerDao;//共借人信息
	@Resource
	private ICreditApplicationDAO applicationDAO;
	@Resource
	private IProtocolMappingDAO iProtocolMappingDAO;
	@Resource
	private IWebsiteDao iWebsiteDao;
	@Resource
	private CeBorrowingProductsWS ceBorrowingProductsWS;
	@Resource
	private SmpWSUtil smpWSUtil;
	@Resource
	private ICustomerConsultDAO customerConsultDAO;
	@Resource
	private INationalStandardCodeDAO nationalStandardCodeDAO;
	@Resource
	private ISpecialApplyDao specialApplyDao;
	@Resource
	private IApplicationDao applicationDao;
	private List<BorrowerServiceAppVo2> queryBorrowerInfoList = new ArrayList<BorrowerServiceAppVo2>();
	private static List<NationalStandardCode> selectNSCByName = new ArrayList<NationalStandardCode>();
	Logger log = Logger.getLogger(ComprehensiveServe.class);
	public ComprehensiveServe(){
	}
	
	
	@PostConstruct
	private void getNationalStandardCode(){
		if(CommonUtil.isEmpty(selectNSCByName)){
			if(CommonUtil.isEmpty(selectNSCByName)){
				NationalStandardCode nationalStandardCode = new NationalStandardCode();
				selectNSCByName = nationalStandardCodeDAO.selectNSCByName(nationalStandardCode);
			}
		}
		
	}
	
	@Override
	public Message send2Comprehensive(Long creditApplicationId){
		Message message = new Message();
		BorrowerApplyFormInfoReq borrowerApplyFormInfoReq = new BorrowerApplyFormInfoReq();
		borrowerApplyFormInfoReq.setSystemCode("44");
		borrowerApplyFormInfoReq.setInterfaceCode("101");//申请表接口：101
		//根据bean对象生成xml,并推送到综合信贷
		try {
			BorrowerTransactionVo borrowerTransactionVo = getBorrowerTransactionVo(creditApplicationId);//进件信息
			List<BorrowerBasicInfoVo> borrowerBasicInfoVo = getBorrowerBasicInfoVo(creditApplicationId);//借款人信息/共借人信息
			BorrowerMarketVo borrowerMarketVo = getBorrowerMarketVo(creditApplicationId);//借款人营销信息
			borrowerApplyFormInfoReq.setTransactionVo(borrowerTransactionVo);
			borrowerApplyFormInfoReq.setBorrowerBasicInfoVo(borrowerBasicInfoVo);
			borrowerTransactionVo.setMarket(borrowerMarketVo);
			String applyXML = borrowerApplyFormInfoReq.toXML();
			SIAMessage siaMessage = new SIAMessage();
			siaMessage.setBusinessCode("zhxdxt_asyn_applyDataSave");//推送到综合信贷的对应code
			siaMessage.setMessageInfoClob(applyXML);
			log.info("推送综合信贷数据入参："+JsonUtil.getJackson(borrowerApplyFormInfoReq));
			SIA.send(siaMessage);
			message.setSuccess(true);
		} catch (Exception e) {
			message.setCode("88888");
			message.setMsg("发送申请表信息失败！");
			message.setSuccess(false);
			log.info("发送申请表信息失败！");
			throw new RuntimeException("发送申请表信息失败！");
		}
		queryBorrowerInfoList.clear();
		queryBorrowerInfoList = null;
		return message;
	}
	//借款人进件信息
	private BorrowerTransactionVo getBorrowerTransactionVo(Long creditApplicationId) throws ParseException{
		BorrowerTransactionVo borrowerTransactionVo = new BorrowerTransactionVo();
		Map<String, Object> queryForZh = applicationDAO.queryForZh(creditApplicationId);
		//查询借款用途
//		Map<String, Object> queryBorrowUseBycreditapplicationId = customerConsultDAO.queryBorrowUseBycreditapplicationId(creditApplicationId.toString());
		
		if(CommonUtil.isEmpty(queryBorrowerInfoList)){
			
			queryBorrowerInfoList = iBorrowerServiceAppService.queryBorrowerInfo(creditApplicationId);
		}
		for (BorrowerServiceAppVo2 queryBorrowerInfo : queryBorrowerInfoList) {
			if("1".equals(queryBorrowerInfo.getLeader())){
				
				borrowerTransactionVo.setName(queryBorrowerInfo.getName());//姓名
				borrowerTransactionVo.setCardNo(queryBorrowerInfo.getCredentialsNumber());//证件号码
				
				List<Application> borrowUse = getBorrowUse(Long.parseLong(queryBorrowerInfo.getBorrowerServiceAppId().toString()));//查询借款用途
				if(CommonUtil.isNotEmpty(borrowUse)){
					String loanPurpose = "";
					String getBorrowUse = "";
					String borrowUseStr="";
					for (Application application : borrowUse) {
						String use = application.getBorrowUse();
						if(CommonUtil.isNotEmpty(use)){
							
							getBorrowUse = application.getBorrowUse();
							borrowUseStr +=getBorrowUse+",";
						}
						if (DicUtil.sectionMap.containsKey("borroerUserConsult")) {
							Map<String, String> detailsLoansuseTypeMap = DicUtil.sectionMap.get("borroerUserConsult");
							String detailsLoansuseType = getBorrowUse;
							if (detailsLoansuseTypeMap.containsKey(detailsLoansuseType)) {
								//追加借款用途
								loanPurpose += detailsLoansuseTypeMap.get(detailsLoansuseType)+",";
							}
						}
					}
					StringBuffer str = new StringBuffer(loanPurpose); 
					StringBuffer str2= new StringBuffer(borrowUseStr);
					//截取
					StringBuffer loanPurpose2=str.delete(loanPurpose.lastIndexOf(","),loanPurpose.length());
					loanPurpose=loanPurpose2.toString();
					StringBuffer borrowUseStr2 = str2.delete(borrowUseStr.lastIndexOf(","), borrowUseStr.length());
					borrowUseStr = borrowUseStr2.toString();
					borrowerTransactionVo.setLoanPurposeDesc(loanPurpose);//借款用途详细
					borrowerTransactionVo.setLoanPurposeOne(borrowUseStr);//借款用途
				}
				
			}
		}
		borrowerTransactionVo.setUuid(queryForZh.get("SYS_GUID").toString());//uuid
		borrowerTransactionVo.setBusinessCode(queryForZh.get("BUSINESS_NUMBER").toString());//业务单号
		borrowerTransactionVo.setBsApplyId(creditApplicationId);//进件id
		borrowerTransactionVo.setRemark(queryForZh.get("REPAYMENT_PLAN_NAME").toString());//贷款产品 REPAYMENT_PLAN_NAME
		//borrowerTransactionVo.setRepaymentMode(queryForZh.get("REPAYMENT_TYPE").toString());//还款方式
		borrowerTransactionVo.setIsCompliance("1");//是否合规。0[否],1[是] 默认是
		if("1".equals(queryForZh.get("REVOLVING_CREDIT"))){
			borrowerTransactionVo.setLoanCategory(Long.parseLong("3"));//1.新增客户、2.结清再贷、3.借新还旧
		}else if("0".equals(queryForZh.get("REVOLVING_CREDIT"))){
			borrowerTransactionVo.setLoanCategory(Long.parseLong("1"));
		}
		borrowerTransactionVo.setApplyInstalments(Short.valueOf(queryForZh.get("INSTALMENTS").toString()));//申请期限 
		borrowerTransactionVo.setApplyPeriods(Short.valueOf(queryForZh.get("INSTALMENTS").toString()));//申请期数 
		borrowerTransactionVo.setOverAmount(new BigDecimal(queryForZh.get("AMOUNT").toString()));//申请金额 
		borrowerTransactionVo.setLoanAmount(new BigDecimal(queryForZh.get("AMOUNT").toString()));//借款总额
		//得到年利率
		Double annualizedRateById = applicationDAO.getAnnualizedRateById(Integer.parseInt(creditApplicationId.toString()));
		borrowerTransactionVo.setYearTariffing(new BigDecimal(annualizedRateById.toString()));//服务费率
		
		borrowerTransactionVo.setCardType("1");//证件类型，默认是身份证
		borrowerTransactionVo.setIsReported("1");//默认1查过
		borrowerTransactionVo.setLoanType1(queryForZh.get("REVOLVING_CREDIT").toString());//是否循环贷
		borrowerTransactionVo.setState("0");//默认等待打印合同状态
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date parse = formatter.parse(queryForZh.get("CREATE_DATE").toString());
		borrowerTransactionVo.setFillFormDate(parse);//申请人填表日期
		borrowerTransactionVo.setTransportDate(parse);//申请时间 
		borrowerTransactionVo.setUuid(queryForZh.get("SYS_GUID").toString());//uuid
		String quPrefixNumber = iProtocolMappingDAO.quPrefixNumber(queryForZh.get("COMPANY_ID").toString());
		borrowerTransactionVo.setContractPrefix(quPrefixNumber);//合同前缀
		List<Map> productList = selectProInfo(queryForZh.get("COMPANY_ID").toString());
		for (Map map : productList) {
			if(queryForZh.get("PRODUCTTYPEID").toString().equals(map.get("producttypeid").toString())){
				
				borrowerTransactionVo.setProductType(map.get("producttypeid").toString());//产品大类
				borrowerTransactionVo.setProductVersionCode(map.get("productInfoId").toString());//产品版本编号
				borrowerTransactionVo.setProductVersion(map.get("version").toString());//产品版本号
				break;
			}
		}
		//判断是否是打折
		if("1".equals(queryForZh.get("DISCOUNT_FLAG").toString()) && "1".equals(queryForZh.get("REVOLVING_CREDIT").toString())){//标识打折
			borrowerTransactionVo.setDiscountWay("循环贷打折");//打折方式
			borrowerTransactionVo.setDiscountRate(new BigDecimal(queryForZh.get("DISCOUNT_FLAG").toString()));//打折率
		}
		//特殊申请信息
		List<SpecialApply> querySpecial = specialApplyDao.querySpecial(creditApplicationId);
		if(CommonUtil.isNotEmpty(querySpecial)){
			borrowerTransactionVo.setSpecialApplyList(querySpecial);
		}
		return borrowerTransactionVo;
	}
	
	//借款人信息/共借人信息
	private List<BorrowerBasicInfoVo> getBorrowerBasicInfoVo(Long creditApplicationId) throws ParseException{
		List<BorrowerBasicInfoVo> borrowerBasicInfoVoList = new ArrayList<BorrowerBasicInfoVo>();
		if(CommonUtil.isEmpty(queryBorrowerInfoList)){
			
			queryBorrowerInfoList = iBorrowerServiceAppService.queryBorrowerInfo(creditApplicationId);
		}
		for (BorrowerServiceAppVo2 queryBorrowerInfo : queryBorrowerInfoList) {
			if("1".equals(queryBorrowerInfo.getLeader())){//主借人信息
				BorrowerBasicInfoVo borrowerBasicInfoVo  = new BorrowerBasicInfoVo();
				borrowerBasicInfoVo.setMortgagorName(queryBorrowerInfo.getName());//客户姓名
				borrowerBasicInfoVo.setIsTogetherBorrower("1");//主借人
				//判断名族
				if("1".equals(queryBorrowerInfo.getNational())){
					
					borrowerBasicInfoVo.setNation("1");//  民族
				}else if("11".equals(queryBorrowerInfo.getNational())){
					borrowerBasicInfoVo.setNation("2");//  民族
				}else if("16".equals(queryBorrowerInfo.getNational())){
					borrowerBasicInfoVo.setNation("3");//  民族
				}else if("7".equals(queryBorrowerInfo.getNational())){
					borrowerBasicInfoVo.setNation("4");//  民族
				}else if("14".equals(queryBorrowerInfo.getNational())){
					borrowerBasicInfoVo.setNation("5");//  民族
				}else if("5".equals(queryBorrowerInfo.getNational())){
					borrowerBasicInfoVo.setNation("6");//  民族
				}else if("18".equals(queryBorrowerInfo.getNational())){
					borrowerBasicInfoVo.setNation("7");//  民族
				}else if("33".equals(queryBorrowerInfo.getNational())){
					borrowerBasicInfoVo.setNation("8");//  民族
				}else{
					borrowerBasicInfoVo.setNation("9");//  民族
				}
				
				borrowerBasicInfoVo.setFormerName(queryBorrowerInfo.getFormer());//曾用名
				DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				Date parse = formatter.parse(queryBorrowerInfo.getBirthday());
				borrowerBasicInfoVo.setBirthday(parse);//出生日期
				if("0".equals(queryBorrowerInfo.getGender())){
					borrowerBasicInfoVo.setGender("1");//性别
				}else if("1".equals(queryBorrowerInfo.getGender())){
					
					borrowerBasicInfoVo.setGender("2");//性别
				}
				borrowerBasicInfoVo.setIdNumber(queryBorrowerInfo.getCredentialsNumber());//证件号码
				if("0".equals(queryBorrowerInfo.getMaritalStatus())){
					borrowerBasicInfoVo.setMarriage("2");//婚姻状况
					
				}else if("1".equals(queryBorrowerInfo.getMaritalStatus())){
					borrowerBasicInfoVo.setMarriage("1");//婚姻状况
				}else if("2".equals(queryBorrowerInfo.getMaritalStatus())){
					borrowerBasicInfoVo.setMarriage("4");//婚姻状况
				}else if("3".equals(queryBorrowerInfo.getMaritalStatus())){
					borrowerBasicInfoVo.setMarriage("7");//婚姻状况
				}
				if(queryBorrowerInfo.getAge()!=null){
					
					borrowerBasicInfoVo.setAge(Short.valueOf(queryBorrowerInfo.getAge().toString()));//年龄
				}
				borrowerBasicInfoVo.setDomicileAddr(queryBorrowerInfo.getHourseholdAddress());//户籍地址
				//匹配户籍省市区*************start
				String hourseholdAddress = queryBorrowerInfo.getHourseholdAddress();
				String[] split = hourseholdAddress.split("-");
				String sheng="";
				String shi="";
				String qu="";
				if(split!=null && split.length>0){
					sheng=split[0];
					shi=split[1];
					qu=split[2];
				}
				String shengCode="";
				String shiCode="";
				if(selectNSCByName!=null){
					for (NationalStandardCode nationalStandardCode2 : selectNSCByName) {
						if(sheng.equals(nationalStandardCode2.getCityName())){
							shengCode = nationalStandardCode2.getCityCode().toString();
							borrowerBasicInfoVo.setDomicileProvince(shengCode);
						}
						if(shi.equals(nationalStandardCode2.getCityName())){
							String shiParentCode = nationalStandardCode2.getParentId().toString();
							
							if(shiParentCode.equals(shengCode)){
								shiCode=nationalStandardCode2.getCityCode().toString();
								borrowerBasicInfoVo.setDomicileCity(shiCode);
							}
						}
						if(qu.equals(nationalStandardCode2.getCityName())){
							String quParentCode = nationalStandardCode2.getParentId().toString();
							if(quParentCode.equals(shiCode)){
								
								borrowerBasicInfoVo.setDomicileDistrict(nationalStandardCode2.getCityCode().toString());
							}
						}
					}
				}
				//匹配户籍省市区****************end
				if((null !=queryBorrowerInfo.getLivingSelf() && ""!=queryBorrowerInfo.getLivingSelf())){
					
					borrowerBasicInfoVo.setHasHouse("1");//有无房产1 有  2 无
				}
				borrowerBasicInfoVo.setCreateTime(queryBorrowerInfo.getApplyDate());//创建时间
				borrowerBasicInfoVo.setRemark(queryBorrowerInfo.getBorrRemark());//备注
				List<Familymember> queryFamilyMemberInfo = iFamilymemberDao.queryFamilyMemberInfo(queryBorrowerInfo.getBorrowerServiceAppId());
				for (Familymember familymember : queryFamilyMemberInfo) {
					if("3".equals(familymember.getBorrowerreRationship()) || "4".equals(familymember.getBorrowerreRationship())){
						
						borrowerBasicInfoVo.setHasChildren("1");//有无子女1 有  2 无
					}else{
						borrowerBasicInfoVo.setHasChildren("2");//有无子女1 有  2 无
					}
				}
				borrowerBasicInfoVo.setIsCreditReport("1");//是否代办信用报告
				borrowerBasicInfoVo.setIdType("1");//证件类型，默认是身份证
				borrowerBasicInfoVo.setTransactionId(creditApplicationId);//进件ID
				borrowerBasicInfoVo.setBusinessBorrowerId(Long.parseLong(queryBorrowerInfo.getBorrowerServiceAppId().toString()));//业务借款人ID
				List<BorrowerContactVo> borrowerContactVo = getBorrowerContactVo(creditApplicationId);
				borrowerBasicInfoVo.setContact(borrowerContactVo);//主借人里面的担保人，家庭成员信息等
				//主借人联系方式对象
				List<BorrowerContactWayVo> borrowerContactWayVoList = new ArrayList<BorrowerContactWayVo>();
				BorrowerContactWayVo borrowerContactWayVo = new BorrowerContactWayVo();
				borrowerContactWayVo.setBorrowerInfoId(Long.parseLong(queryBorrowerInfo.getBorrowerServiceAppId().toString()));
				borrowerContactWayVo.setContactWay("1"); 
				borrowerContactWayVo.setContactValue(queryBorrowerInfo.getMobilephone());
				borrowerContactWayVoList.add(borrowerContactWayVo);
				borrowerBasicInfoVo.setContactWay(borrowerContactWayVoList);
				borrowerBasicInfoVoList.add(borrowerBasicInfoVo);
				
				//共借人信息
				CreditCoBorrower queryCoBOrrowerInfo = iCreditCoBorrowerDao.queryCoBOrrowerInfo(Integer.parseInt(queryBorrowerInfo.getBorrowerServiceAppId().toString()));
				BorrowerBasicInfoVo borrowerBasicInfoVo1  = new BorrowerBasicInfoVo();	
				borrowerBasicInfoVo1.setIsTogetherBorrower("0");
				borrowerBasicInfoVo1.setMortgagorName(queryCoBOrrowerInfo.getName());
				borrowerBasicInfoVo1.setAge(Short.valueOf(queryCoBOrrowerInfo.getAge().toString()));
				borrowerBasicInfoVo1.setIdType("1");
				borrowerBasicInfoVo1.setIdNumber(queryCoBOrrowerInfo.getIdNumber());
				borrowerBasicInfoVo1.setMaxDiploma(queryCoBOrrowerInfo.getEducation());
				borrowerBasicInfoVo1.setBusinessBorrowerId(queryCoBOrrowerInfo.getCoBorrowerId());
				borrowerBasicInfoVo1.setTransactionId(creditApplicationId);
				borrowerBasicInfoVo1.setIsCreditReport("1");//是否代办信用报告
				//共借人联系方式对象
				List<BorrowerContactWayVo> borrowerContactWayVoList1 = new ArrayList<BorrowerContactWayVo>();
				BorrowerContactWayVo borrowerContactWayVo1 = new BorrowerContactWayVo();
				borrowerContactWayVo1.setBorrowerInfoId(queryCoBOrrowerInfo.getCoBorrowerId());
				borrowerContactWayVo1.setContactWay("1"); 
				borrowerContactWayVo1.setContactValue(queryCoBOrrowerInfo.getTelphone());
				borrowerContactWayVoList1.add(borrowerContactWayVo1);
				borrowerBasicInfoVo1.setContactWay(borrowerContactWayVoList1);
				borrowerBasicInfoVoList.add(borrowerBasicInfoVo1);
				break;
			}
		}
		return borrowerBasicInfoVoList;
	}
	//借款人营销信息
	private BorrowerMarketVo getBorrowerMarketVo(Long creditApplicationId){
		Map<String, Object> queryForZh = applicationDAO.queryForZh(creditApplicationId);
		Website selectByCompanyId = iWebsiteDao.selectByCompanyId(queryForZh.get("COMPANY_ID").toString());
		BorrowerMarketVo borrowerMarketVo = new BorrowerMarketVo();
		borrowerMarketVo.setSaleCityId(Long.parseLong(selectByCompanyId.getCityhome()));
		borrowerMarketVo.setSaleAreaCountryId(Long.parseLong(selectByCompanyId.getCountyhome()));
		borrowerMarketVo.setSaleDepartmentId(Long.parseLong(queryForZh.get("COMPANY_ID").toString()));
		borrowerMarketVo.setSubmitDeptNo(queryForZh.get("COMPANY_ID").toString());
	/*	User currentUser = SpringSecurityUtils.getCurrentUser();
		currentUser.getName_zh();
		List<EmployeeDTO> iterUsersByDepId = smpWSUtil.getIterUsersByDepId(currentUser.getAreaDepartmentId().toString());
		borrowerMarketVo.setTeamManagerId(teamManagerId);//团队经理id
		borrowerMarketVo.setTeamManagerName(teamManagerName);//团队经理名字
*/		borrowerMarketVo.setSellUserId(Long.parseLong(queryForZh.get("LOAN_OFFICER_ID").toString()));
		borrowerMarketVo.setSellUserName(queryForZh.get("LOAN_OFFICER_NAME").toString());
		borrowerMarketVo.setServiceUserId(Long.parseLong(queryForZh.get("LOAN_OFFICER_ID").toString()));
		borrowerMarketVo.setServiceUserName(queryForZh.get("LOAN_OFFICER_NAME").toString());
		Integer deptId = getDeptId(queryForZh.get("COMPANY_ID").toString());
		borrowerMarketVo.setDirectlyDepartmentId(Long.parseLong(deptId.toString()));
		return borrowerMarketVo;
	}
	
	//担保人信息
	private List<BorrowerContactVo> getBorrowerContactVo(Long creditApplicationId){
		List<BorrowerContactVo> list = new ArrayList<BorrowerContactVo>();
		if(CommonUtil.isEmpty(queryBorrowerInfoList)){
			
			queryBorrowerInfoList = iBorrowerServiceAppService.queryBorrowerInfo(creditApplicationId);
		}
		for (BorrowerServiceAppVo2 queryBorrowerInfo : queryBorrowerInfoList) {
			if("0".equals(queryBorrowerInfo.getLeader())){//说明是担保人
				BorrowerContactVo borrowerContactVo = new BorrowerContactVo();
				borrowerContactVo.setContactId(Long.parseLong(queryBorrowerInfo.getBorrowerServiceAppId().toString()));
				borrowerContactVo.setIdentityNo(queryBorrowerInfo.getCredentialsNumber());
				borrowerContactVo.setName(queryBorrowerInfo.getName());
				borrowerContactVo.setContactType("9");//其他联系人
				borrowerContactVo.setUsedName(queryBorrowerInfo.getFormer());
				borrowerContactVo.setGender(queryBorrowerInfo.getGender());
				borrowerContactVo.setRelation("15");
				if(null !=queryBorrowerInfo.getAge()){
					
					borrowerContactVo.setAge(Short.valueOf(queryBorrowerInfo.getAge().toString()));
				}
				borrowerContactVo.setLiveAddress(queryBorrowerInfo.getHomeAddress());
				borrowerContactVo.setSource("3");
				borrowerContactVo.setMobile1(queryBorrowerInfo.getMobilephone());//
				//匹配省市区start**************
				String hourseholdAddress = queryBorrowerInfo.getHomeAddress();
				String[] split = hourseholdAddress.split("-");
				String sheng="";
				String shi="";
				String qu="";
				if(split!=null && split.length>0){
					sheng=split[0];
					shi=split[1];
					qu=split[2];
				}
				String shengCode="";
				String shiCode="";
				if(selectNSCByName!=null){
					for (NationalStandardCode nationalStandardCode2 : selectNSCByName) {
						if(sheng.equals(nationalStandardCode2.getCityName())){
							shengCode = nationalStandardCode2.getCityCode().toString();
							borrowerContactVo.setProvince(shengCode);
						}
						if(shi.equals(nationalStandardCode2.getCityName())){
							String shiParentCode = nationalStandardCode2.getParentId().toString();
							
							if(shiParentCode.equals(shengCode)){
								shiCode=nationalStandardCode2.getCityCode().toString();
								borrowerContactVo.setCity(shiCode);
							}
						}
						if(qu.equals(nationalStandardCode2.getCityName())){
							String quParentCode = nationalStandardCode2.getParentId().toString();
							if(quParentCode.equals(shiCode)){
								
								borrowerContactVo.setArea(nationalStandardCode2.getCityCode().toString());
							}
						}
					}
				}
				//匹配省市区start**************
				list.add(borrowerContactVo);
			}
			if("1".equals(queryBorrowerInfo.getLeader())){
				//家庭成员
				List<Familymember> queryFamilyMemberInfo = iFamilymemberDao.queryFamilyMemberInfo(Integer.parseInt(queryBorrowerInfo.getBorrowerServiceAppId().toString()));
				for (Familymember familymember : queryFamilyMemberInfo) {
						BorrowerContactVo borrowerContactVo = new BorrowerContactVo();
						borrowerContactVo.setBorrowerInfoId(creditApplicationId);
						borrowerContactVo.setContactId(familymember.getFamilyMemberId());
						borrowerContactVo.setIdentityNo(familymember.getIdNumber());
						borrowerContactVo.setName(familymember.getName());
						if("0".equals(familymember.getGender())){//男
							
							borrowerContactVo.setGender("1");
						}else if("1".equals(familymember.getGender())){
							borrowerContactVo.setGender("0");
						}
						borrowerContactVo.setContactType("9");
						if("0".equals(familymember.getBorrowerreRationship())){
							borrowerContactVo.setRelation("2");
						}else if("1".equals(familymember.getBorrowerreRationship())){
							borrowerContactVo.setRelation("3");
						}else if("2".equals(familymember.getBorrowerreRationship())){
							borrowerContactVo.setRelation("4");
						}else if("3".equals(familymember.getBorrowerreRationship())){
							borrowerContactVo.setRelation("11");
						}else if("4".equals(familymember.getBorrowerreRationship())){
							borrowerContactVo.setRelation("11");
						}else if("5".equals(familymember.getBorrowerreRationship())){
							borrowerContactVo.setRelation("7");
						}else if("6".equals(familymember.getBorrowerreRationship())){
							borrowerContactVo.setRelation("1");
						}
						borrowerContactVo.setAge(Short.valueOf(familymember.getAge().toString()));
						//职业：0-务农；1-待业；2-学生；3-打工；4-政府机构；5经营
						borrowerContactVo.setJob(familymember.getProfessionView());
						borrowerContactVo.setMobile1(familymember.getTelphone());
						if(familymember.getYearIncome()!=null){
							
							borrowerContactVo.setYearIncome(new BigDecimal(familymember.getYearIncome()/12));
						}
						list.add(borrowerContactVo);
				}
			}
		}
		return list;
	}
	
	 /**
	  * 查询产品信息
	  * @return
	  */
	private List<Map> selectProInfo(String areaDepartmentId) {

			QueryProByDepartResult queryProByDepartResult = null;
			List<Map> list = new ArrayList<Map>();
			List<ProInfo> proInfos = null;
			List<ProductInst> productInsts = null;
			// 登录人的部门id

			if (CommonUtil.isEmpty(areaDepartmentId)) {
				System.out.println("areaDepartmentId是Null！");
				return list;
			}
			List<EmployeeDTO> employeeDTOs = smpWSUtil.getEmployeesListByRole_DepartmentId(areaDepartmentId,
					"ROLE_LOAN_OFFICER");

			if (CommonUtil.isEmpty(employeeDTOs)) {
				System.out.println("该营业部下没有信贷员！");
				return list;
			}
			int departMentId = 0;
			for (EmployeeDTO employeeDTO : employeeDTOs) {
				Integer getComEmpId = employeeDTO.getComEmpId();
				EmployeeDTO temp = smpWSUtil.getEmployee(getComEmpId.toString());
				Integer getDepartmentId = temp.getDepartmentId();
				System.out.println(getDepartmentId);
				if (getDepartmentId != null) {
					departMentId = getDepartmentId;
					break;
				}
			}
			log.info("ceBorrowingProductsWS.queryProByDepart(departMentId) request_params:******");
			log.info(JsonUtil.getJackson(departMentId));
			ProListParam proListParam = new ProListParam();
			proListParam.setDepartmentId(departMentId);
			queryProByDepartResult = ceBorrowingProductsWS.queryProByDepart(proListParam);
			if (null != queryProByDepartResult) {
				log.info("ceBorrowingProductsWS.queryProByDepart(departMentId) response_params:******");
				log.info(JsonUtil.getJackson(queryProByDepartResult));
				if ("0".equals(queryProByDepartResult.getResultCode())) {
					ProInfoList proInfoList = queryProByDepartResult.getProInfoList();
					ProInstList proInstList = queryProByDepartResult.getProInstList();

					if (null != proInfoList) {
						proInfos = proInfoList.getProInfo();
						if (proInfos.size() == 0) {
							System.out.println("proInfoList中的List<ProInfo> 长度是 0：proInfoList.getProInfoList():"
									+ proInfoList.getProInfo());
						}
						productInsts = proInstList.getProductInst();
						if (productInsts.size() == 0) {
							System.out.println("proInfoList中的List<ProductInst> 长度是 0：proInstList.getProductInst():"
									+ proInstList.getProductInst());
						}
	                    //存储产品类型大类
	                    Map<String,String> cateGoryIds = new HashMap<String,String>();
	                    Map<Long,Map> catas = new HashMap<Long, Map>();
	                    //循环产品类型大类
	                    for (int i = 0; i < proInfos.size(); i++) {
	                        ProInfo proInfo =  proInfos.get(i);
	                        //循环产品
	                        for (int j = 0; j < productInsts.size(); j++) {
	                            if (proInfos.get(i).getProductInfoId().equals(productInsts.get(j).getProductInfoId())) {
	                                //循环产品 把产品的还款方式添加到对应的产品大类上
	                                if(catas.containsKey(proInfo.getProductInfoId())){
	                                    //   大类已经包含 循环拼接出期数   还款方式
	                                    Map map = catas.get(proInfo.getProductInfoId());
	                                    String   repaymentTypeStr = (String)map.get("repaymentType");
	                                    repaymentTypeStr += "," +  productInsts.get(j).getRepaymentType();
	                                    map.remove("repaymentType");
	                                    map.put("repaymentType",repaymentTypeStr);

	                                    catas.remove(proInfo.getProductInfoId())  ;
	                                    catas.put(proInfo.getProductInfoId(),map);
	                                }else{
	                                    //大类未包含 存入数据里面
	                                    Map product = new HashMap();
	                                    product.put("productInfoId", proInfo.getProductInfoId());
	                                    product.put("productName", proInfo.getProductName());
	                                    product.put("instalments", proInfo.getInstalments());
	                                    product.put("version", proInfos.get(i).getVersion());
	                                    product.put("producttypeid", proInfo.getProductCategoryId()); // 产品分类编号
	                                    product.put("repaymentType", productInsts.get(j).getDefaultRepaymentType());
	                                    product.put("capitalUpperLimit", convertBigDecimal(proInfo
	                                            .getCapitalUpperLimit()));
	                                    product.put("capitalLowerLimit", convertBigDecimal(proInfo
	                                            .getCapitalLowerLimit()));
	                                    catas.put(proInfo.getProductInfoId(),product);
	                                    //list.add(product);
	                                }
	                            }
	                        }
	                    }
	                    //循环产品大类 放入返回列表
	                    for(Map.Entry<Long,Map> entry:catas.entrySet()) {
	                        list.add(entry.getValue());
	                    }
	                   // System.out.println(list.toString());
					} else {
						System.out.println("proInfoList 是 null：proInfoList:" + proInfoList);
					}
				} else {
					System.out.println("产品返回接口返回结果失败：queryProByDepartResult.getResultCode():"
							+ queryProByDepartResult.getResultCode());
				}
			} else {
				System.out.println("产品返回接口返回数据是null：ceBorrowingProductsWS.queryProByDepart:"
						+ ceBorrowingProductsWS.queryProByDepart(proListParam));
			}
			return list;
		}
		
	
	
	
	/**
	 * 
	 * 
	 * @Description: BigDecimal转换字符串
	 * @param decimal
	 * @return String
	 * @version v1.0
	 *          2013-3-22
	 */
	private String convertBigDecimal(BigDecimal decimal) {
		StringBuffer value = new StringBuffer();
		if (null == decimal) {
			value.append("0");
		} else {
			value.append(decimal.toString());
		}
		return value.toString();
	}
	
	
	private Integer getDeptId(String DepartmentId){
		int departMentId = 0;
		// 登录人的部门id
		if (CommonUtil.isEmpty(DepartmentId)) {
			System.out.println("areaDepartmentId是Null！");
			return null;
		}
		List<EmployeeDTO> employeeDTOs = smpWSUtil.getEmployeesListByRole_DepartmentId(DepartmentId,
				"ROLE_LOAN_OFFICER");

		if (CommonUtil.isEmpty(employeeDTOs)) {
			System.out.println("该营业部下没有信贷员！");
			return null;
		}
		for (EmployeeDTO employeeDTO : employeeDTOs) {
			Integer getComEmpId = employeeDTO.getComEmpId();
			EmployeeDTO temp = smpWSUtil.getEmployee(getComEmpId.toString());
			Integer getDepartmentId = temp.getDepartmentId();
			System.out.println(getDepartmentId);
			if (getDepartmentId != null) {
				departMentId = getDepartmentId;
				break;
			}
		}
		return departMentId;
	}
	
	
	private List<Application> getBorrowUse(Long borrowerServiceAppId){
		/*//现在的取法的取法 现在修改为： 借款详细用途 2014-12-25修改
		String loanPurpose = "";
		String getBorrowUse = "";*/
        //1.从申请单中获取
		return (List<Application>) applicationDao.queryList(
				"RL_APPLICATION.selectBorrowerServiceApp", borrowerServiceAppId);
		/*if (CommonUtil.isNotEmpty(applicationsList)) {
			for(int i=0;i<applicationsList.size();i++){
				Application application = applicationsList.get(i);
				if (application != null) {
					getBorrowUse = application.getBorrowUse();
				}
				if (DicUtil.sectionMap.containsKey("borroerUserConsult")) {
					Map<String, String> detailsLoansuseTypeMap = DicUtil.sectionMap.get("borroerUserConsult");
					String detailsLoansuseType = getBorrowUse;
					if (detailsLoansuseTypeMap.containsKey(detailsLoansuseType)) {
						//追加借款用途
						loanPurpose += detailsLoansuseTypeMap.get(detailsLoansuseType)+",";
					}
				}
			}
			StringBuffer str = new StringBuffer(loanPurpose); 
			//截取
			StringBuffer loanPurpose2=str.delete(loanPurpose.lastIndexOf(","),loanPurpose.length());
			loanPurpose=loanPurpose2.toString();
		}*/
	}
}
