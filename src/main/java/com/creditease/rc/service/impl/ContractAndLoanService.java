package com.creditease.rc.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.creditease.rc.app.orgams.BaseResponse;
import com.creditease.rc.app.orgams.MatchBorrowResVo;
import com.creditease.rc.app.pdf.RepaymentPlanReq;
import com.creditease.rc.app.pdf.RepaymentPlanReqResult;
import com.creditease.rc.common.Constants;
import com.creditease.rc.dao.BorrowMatchingDAO;
import com.creditease.rc.dao.IApplicationDao;
import com.creditease.rc.dao.IContractAndLoanDao;
import com.creditease.rc.dao.TradeDealFormDAO;
import com.creditease.rc.domain.AccountInfo;
import com.creditease.rc.domain.AmountConfirm;
import com.creditease.rc.domain.Application;
import com.creditease.rc.domain.BorrowMatching;
import com.creditease.rc.domain.CreditApplication;
import com.creditease.rc.domain.GroupLoanRegist;
import com.creditease.rc.domain.LendingConfiguration;
import com.creditease.rc.domain.Message;
import com.creditease.rc.domain.TradeDealForm;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.service.IAmountConfirmService;
import com.creditease.rc.service.IBorrowingProductsService;
import com.creditease.rc.service.IContractAndLoanService;
import com.creditease.rc.service.ICreditApplicationService;
import com.creditease.rc.service.IOperateLogService;
import com.creditease.rc.service.IRefuseReasonService;
import com.creditease.rc.service.IorgamsService;
import com.creditease.rc.util.CommonUtil;
import com.creditease.rc.util.DESPlus;
import com.creditease.rc.util.DateUtil;
import com.creditease.rc.util.DicUtil;
import com.creditease.rc.vo.ContractAndLoan;
import com.creditease.rc.vo.ContractRate;
import com.creditease.rc.vo.ContractRateForQYResult;
import com.creditease.rc.vo.LoanConfirmMessageVo;
import com.creditease.rc.vo.OperateLogBusinessStruct;
import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;

@Service
public class ContractAndLoanService implements IContractAndLoanService {

	@Resource
	private IContractAndLoanDao contractAndLoanDao;

	@Resource
	private IBorrowingProductsService borrowingProductsService;
	
	@Resource
	private ICreditApplicationService creditApplicationService;
	
	@Resource
	private IOperateLogService operateLogService;
	
	@Resource
	private IorgamsService orgamsService;
	
	@Resource IAmountConfirmService amountConfirmService;
	
	@Resource
	private BorrowMatchingDAO borrowMatchingDAO;
	@Resource
	private TradeDealFormDAO dealFormDAO;

	@Resource
	private IRefuseReasonService refuseReasonService;
	
	// 借款申请
	@Resource
	private IApplicationDao applicationDao;
	
	@Override
	public Pagination contractDateGrid(Map<String, String> queryMap, Pagination pagination) {
		// TODO Auto-generated method stub
		Pagination paginationResult = new Pagination();
		paginationResult =contractAndLoanDao.contractDateGrid(queryMap, pagination);
		List<ContractAndLoan> items = paginationResult.getRows();
		for (ContractAndLoan c : items) {
			if (c.getCreditapplicationId() != null) {
				try {
					DESPlus des = new DESPlus();
					StringBuffer sb = new StringBuffer(String.valueOf(c.getCreditapplicationId()));
					sb.append(Constants.CM_LOAN);
					String creditapplicationDESId = des.encrypt(sb.toString());
					// 查看申请单附件的 id （与定义的属性 意义不同）
					c.setLaonDESId((des.encrypt(String.valueOf(c.getCreditapplicationId()))));
					// 查看代后资料的 id （与定义的属性 意义不同）（LaonDESId 与 CreditapplicationDESId 的意义对调了 ）
					c.setCreditapplicationDESId(creditapplicationDESId);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return paginationResult;
	}
	
	public ContractRate CalculateByCreditapplicationId(Long creditapplicationId,BigDecimal contractMoney){
		System.out.println("888888888888888888888888888");
		ContractRate contractRate = new ContractRate();

		RepaymentPlanReq rateInfo = new RepaymentPlanReq();

		ContractRateForQYResult contractRateForQYResult = contractAndLoanDao.qyContractRateForQYResult(creditapplicationId);

		String getClientName = contractRateForQYResult.getClientName();
		String getBorrowUse = contractRateForQYResult.getBorrowUse();
		String getBorrowUseText = contractRateForQYResult.getBorrowUseText();
		String getProductName = contractRateForQYResult.getProductName();
		Long getDepartmentId = contractRateForQYResult.getDepartmentId();
		Long getCatagoryId = contractRateForQYResult.getCatagoryId();
		Date getAuditDate = contractRateForQYResult.getAuditDate();
		Date getReqDate = contractRateForQYResult.getReqDate();
		Long getProductInfoId = contractRateForQYResult.getProductInfoId();
		BigDecimal getContractMoney = contractMoney;
		int getPeriodCount = contractRateForQYResult.getPeriodCount();
		Date getLenderDate = contractRateForQYResult.getLenderDate();
		String getPaymentTypeCode = contractRateForQYResult.getPaymentTypeCode();
		String getParamName = contractRateForQYResult.getParamName();
		BigDecimal getParamValue = contractRateForQYResult.getParamValue();

		rateInfo.setDepartmentId(11011196l);
		rateInfo.setCatagoryId(getCatagoryId);
		rateInfo.setAuditDate(new XMLGregorianCalendarImpl(DateUtil.getGregorianCalendar(getAuditDate)));
		// rateInfo.setReqDate(getReqDate);
		rateInfo.setProductInfoId(getProductInfoId);
		rateInfo.setContractMoney(getContractMoney);
		rateInfo.setPeriodCount(getPeriodCount);
		rateInfo.setLenderDate(new XMLGregorianCalendarImpl(DateUtil.getGregorianCalendar(getLenderDate)));

		/*
		 * System.out.println("产品类型Id"+rateInfo.getCatagoryId());
		 * System.out.println("合同金额"+rateInfo.getContractMoney());
		 */
		RepaymentPlanReqResult repaymentPlanReqResult = borrowingProductsService.advanceReturnPlan(rateInfo);
		//取申请单的借款用途   --2015-01-08 begin
		//现在的取法的取法 现在修改为： 借款详细用途 2014-12-25修改
		String loanPurpose = "";
		String getBorrowUseNew = "";
        //1.从申请单中获取
		List<Application> applicationsList = (List<Application>) applicationDao.queryList(
				"RL_APPLICATION.selectBorrowerServiceApp",contractRateForQYResult.getBorrowerServiceAppId());
		if (CommonUtil.isNotEmpty(applicationsList)) {
			for(int i=0;i<applicationsList.size();i++){
				Application application = applicationsList.get(i);
				if (application != null) {
					getBorrowUseNew = application.getBorrowUse();
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
		}
		//end
		// *****给出参赋值****//
		// 1.合同编号系统自动生成 contractNo
		// 2.客户姓名 clientName
		contractRate.setClientName(getClientName);
		// 3.借款总额 contractMoney
		contractRate.setContractMoney(getContractMoney);
		// 4.借款用途 borrowUse 借款用途改为在申请单中取 2015-01-08
		//contractRate.setBorrowUse(getBorrowUseText);
		contractRate.setBorrowUse(loanPurpose);
		// 5.产品名称 productName
		contractRate.setProductName(getProductName);
		// 6.分期数 periodCount
		contractRate.setPeriodCount((int) repaymentPlanReqResult.getPeriodScheduleList().getPeriodSchedule().get(repaymentPlanReqResult.getPeriodScheduleList().getPeriodSchedule().size()-1).getPeriod());
		// 7.到手金额 toAmount
		contractRate.setToAmount(repaymentPlanReqResult.getRecvMoney());
		// 8.服务费 serviceCharge
		contractRate.setServiceCharge(repaymentPlanReqResult.getPeriodScheduleList().getPeriodSchedule().get(repaymentPlanReqResult.getPeriodScheduleList().getPeriodSchedule().size()-1).getPeriodCharge());
		// 9.管理费 managementFee
		//如果没有前期服务费就是新产品
		BigDecimal ChargeInfo=new BigDecimal(0);
		if(repaymentPlanReqResult.getFrontChangeList().getChargeInfo().size()==0){
			contractRate.setManagementFee(ChargeInfo);
		}else{
			contractRate.setManagementFee(repaymentPlanReqResult.getFrontChangeList().getChargeInfo().get(repaymentPlanReqResult.getFrontChangeList().getChargeInfo().size()-1).getCharge());
		}
		// 10.年利率 iRR   （取系统配置的数据字典）
		//contractRate.setiRR(repaymentPlanReqResult.getPeriodScheduleList().getPeriodSchedule().get(repaymentPlanReqResult.getPeriodScheduleList().getPeriodSchedule().size()-1).getIrr());
		Double  yearInterestRate= creditApplicationService.getAnnualizedRateById(Integer.parseInt(String.valueOf(creditapplicationId)));
		BigDecimal iRR1=new BigDecimal(yearInterestRate);
		BigDecimal iRR2=iRR1.setScale(4, BigDecimal.ROUND_HALF_UP); 
		BigDecimal num100=new BigDecimal(100);
		BigDecimal iRR=iRR2.multiply(num100);
		BigDecimal iRR3=iRR.setScale(2,BigDecimal.ROUND_HALF_UP);
		contractRate.setiRR(iRR3);
		// 11.每月还款额 monthlyPayments 取第三期的
		contractRate.setMonthlyPayments(repaymentPlanReqResult.getPeriodScheduleList().getPeriodSchedule().get(2).getPeriodMoney());
		// 12.提前一次性还款金额 prepayments  取第一期的
		contractRate.setPrepayments(repaymentPlanReqResult.getPeriodScheduleList().getPeriodSchedule().get(0).getAllheadMoney());
		// 13.借款用途Value  借款用途改为在申请单中取 2015-01-08
		//contractRate.setBorrowUseText(getBorrowUseText);
		contractRate.setBorrowUseText(loanPurpose);
		return contractRate;
	}
	@Override
	public ContractRate CalculateByCreditapplicationId(Long creditapplicationId) {
		System.out.println("999999999999999999");
		ContractRate contractRate = new ContractRate();

		RepaymentPlanReq rateInfo = new RepaymentPlanReq();

		ContractRateForQYResult contractRateForQYResult = contractAndLoanDao.qyContractRateForQYResult(creditapplicationId);

		AmountConfirm amountConfirm=contractAndLoanDao.searchForConfirmAmount(creditapplicationId);
		
		String getClientName = contractRateForQYResult.getClientName();
		String getBorrowUse = contractRateForQYResult.getBorrowUse();
		String getBorrowUseText = contractRateForQYResult.getBorrowUseText();
		String getProductName = contractRateForQYResult.getProductName();
		Long getDepartmentId = contractRateForQYResult.getDepartmentId();
		Long getCatagoryId = contractRateForQYResult.getCatagoryId();
		Date getAuditDate = contractRateForQYResult.getAuditDate();
		Date getReqDate = contractRateForQYResult.getReqDate();
		Long getProductInfoId = contractRateForQYResult.getProductInfoId();
		BigDecimal getContractMoney=new BigDecimal(0);
		//判断借款金额是否存在（修改过借款金额后，再重新打印，签订合同时显示的放款金额,判断业务状态：33是否是等待上传合同，34等待重新打印合同）
		if(amountConfirm!=null){
			if(amountConfirm.getAmount()!=null&&(contractRateForQYResult.getAuditStatus().equals("33")||contractRateForQYResult.getAuditStatus().equals("34"))){
			getContractMoney=new BigDecimal(amountConfirm.getAmount());
			//System.out.println(getContractMoney);
			}else{
				// 合同金额
				 getContractMoney = contractRateForQYResult.getContractMoney();
			}
		}else{
		// 合同金额
		 getContractMoney = contractRateForQYResult.getContractMoney();
		}
		int getPeriodCount = contractRateForQYResult.getPeriodCount();
		Date getLenderDate = contractRateForQYResult.getLenderDate();
		String getPaymentTypeCode = contractRateForQYResult.getPaymentTypeCode();
		String getParamName = contractRateForQYResult.getParamName();
		BigDecimal getParamValue = contractRateForQYResult.getParamValue();

		rateInfo.setDepartmentId(11011196l);
		rateInfo.setCatagoryId(getCatagoryId);
		rateInfo.setAuditDate(new XMLGregorianCalendarImpl(DateUtil.getGregorianCalendar(getAuditDate)));
		// rateInfo.setReqDate(getReqDate);
		rateInfo.setProductInfoId(getProductInfoId);
		rateInfo.setContractMoney(getContractMoney);
		rateInfo.setPeriodCount(getPeriodCount);
		rateInfo.setLenderDate(new XMLGregorianCalendarImpl(DateUtil.getGregorianCalendar(getLenderDate)));
		// rateInfo.setPaymentTypeCode(getPaymentTypeCode);
		// rateInfo.setParamName(getParamName);
		// rateInfo.setParamValue(getParamValue);

		/*
		 * System.out.println("产品类型Id"+rateInfo.getCatagoryId());
		 * System.out.println("合同金额"+rateInfo.getContractMoney());
		 */
		RepaymentPlanReqResult repaymentPlanReqResult = borrowingProductsService.advanceReturnPlan(rateInfo);

		//取申请单的借款用途   --2015-01-08 begin
		//现在的取法的取法 现在修改为： 借款详细用途 2014-12-25修改
		String loanPurpose = "";
		String getBorrowUseNew = "";
        //1.从申请单中获取
		List<Application> applicationsList = (List<Application>) applicationDao.queryList(
				"RL_APPLICATION.selectBorrowerServiceApp",contractRateForQYResult.getBorrowerServiceAppId());
		if (CommonUtil.isNotEmpty(applicationsList)) {
			for(int i=0;i<applicationsList.size();i++){
				Application application = applicationsList.get(i);
				if (application != null) {
					getBorrowUseNew = application.getBorrowUse();
				}
				if (DicUtil.sectionMap.containsKey("borroerUserConsult")) {
					Map<String, String> detailsLoansuseTypeMap = DicUtil.sectionMap.get("borroerUserConsult");
					String detailsLoansuseType = getBorrowUseNew;
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
		}
		//end
		// *****给出参赋值****//
		// 1.合同编号系统自动生成 contractNo
		// 2.客户姓名 clientName
		contractRate.setClientName(getClientName);
		// 3.借款总额 contractMoney
		contractRate.setContractMoney(getContractMoney);
		// 4.借款用途 borrowUse  借款用途改为在申请单中取 2015-01-08
		//contractRate.setBorrowUse(getBorrowUseText);
		contractRate.setBorrowUse(loanPurpose);
		// 5.产品名称 productName
		contractRate.setProductName(getProductName);
		// 6.分期数 periodCount
		contractRate.setPeriodCount((int) repaymentPlanReqResult.getPeriodScheduleList().getPeriodSchedule().get(repaymentPlanReqResult.getPeriodScheduleList().getPeriodSchedule().size()-1).getPeriod());
		// 7.到手金额 toAmount
		contractRate.setToAmount(repaymentPlanReqResult.getRecvMoney());
		// 8.服务费 serviceCharge
		contractRate.setServiceCharge(repaymentPlanReqResult.getPeriodScheduleList().getPeriodSchedule().get(repaymentPlanReqResult.getPeriodScheduleList().getPeriodSchedule().size()-1).getPeriodCharge());
		// 9.管理费 managementFee
		//如果没有前期服务费就是新产品
		BigDecimal ChargeInfo=new BigDecimal(0);
		if(repaymentPlanReqResult.getFrontChangeList().getChargeInfo().size()==0){
			contractRate.setManagementFee(ChargeInfo);
		}else{
			contractRate.setManagementFee(repaymentPlanReqResult.getFrontChangeList().getChargeInfo().get(repaymentPlanReqResult.getFrontChangeList().getChargeInfo().size()-1).getCharge());
		}
		// 10.年利率 iRR   （取系统配置的数据字典）
		//contractRate.setiRR(repaymentPlanReqResult.getPeriodScheduleList().getPeriodSchedule().get(repaymentPlanReqResult.getPeriodScheduleList().getPeriodSchedule().size()-1).getIrr());
		Double  yearInterestRate= creditApplicationService.getAnnualizedRateById(Integer.parseInt(String.valueOf(creditapplicationId)));
		BigDecimal iRR1=new BigDecimal(yearInterestRate);
		BigDecimal iRR2=iRR1.setScale(4, BigDecimal.ROUND_HALF_UP); 
		BigDecimal num100=new BigDecimal(100);
		BigDecimal iRR=iRR2.multiply(num100);
		BigDecimal iRR3=iRR.setScale(2,BigDecimal.ROUND_HALF_UP);
		contractRate.setiRR(iRR3);

		// 11.每月还款额 monthlyPayments 取第三期的
		contractRate.setMonthlyPayments(repaymentPlanReqResult.getPeriodScheduleList().getPeriodSchedule().get(2).getPeriodMoney());
		// 12.提前一次性还款金额 prepayments  取第一期的
		contractRate.setPrepayments(repaymentPlanReqResult.getPeriodScheduleList().getPeriodSchedule().get(0).getAllheadMoney());
		// 13.借款用途Value 借款用途改为在申请单中取 2015-01-08
		//contractRate.setBorrowUseText(getBorrowUseText);
		contractRate.setBorrowUseText(loanPurpose);
		// String getResultCode = chargePeriodScheduleInfo2.getResultCode();
		// String getResultMsg = chargePeriodScheduleInfo2.getResultMsg();
		//
		// System.out.println("-------------------" + getResultCode +
		// "----------------------");
		// System.out.println("-------------------" + getResultMsg +
		// "----------------------");
		// BigDecimal getRecvMoney = chargePeriodScheduleInfo2.getRecvMoney();
		// List<PeriodSchedule> periodSchedules =
		// chargePeriodScheduleInfo2.getPeriodSchedules();
		// FrontChargeList frontChargeList =
		// chargePeriodScheduleInfo2.geteChargeInfos();
		// //List<ChargeInfo> chargeInfos = frontChargeList.getChargeInfo();
		System.out.println("----------------------------------------------------------------");
		System.out.println("----------------------------------------------------------------");
		System.out.println("----------------------------------------------------------------");

		return contractRate;
	}

	@Override
	public boolean updatePrintContractState(Long creditapplicationId) {
		
		//查询当前业务状态
		CreditApplication creditApplication=new CreditApplication();
		creditApplication=creditApplicationService.selectById(Integer.parseInt(creditapplicationId.toString()));
		//打印合同记录操作日志
		// 加日志 begin
		OperateLogBusinessStruct operateLog = new OperateLogBusinessStruct();
	      operateLog.setCreditapplicationId(Long.valueOf(creditapplicationId));
	      operateLog.setFunctionCode("100041");
	      operateLog.setResult("打印合同时把业务状态 改为等待上传合同");
	      String aa="";
	      if(creditApplication.getAuditStatus().equals("04")){
	    	  aa="操作前状态="+"04"+"审批通过;";
	      }else if(creditApplication.getAuditStatus().equals("34")){
	    	  aa="操作前状态="+"34"+"等待重新打印合同;";
	      }
	      int rows=contractAndLoanDao.updatePrintContractState(creditapplicationId);
	      creditApplication=creditApplicationService.selectById(Integer.parseInt(creditapplicationId.toString()));
	      String bb="操作后状态："+creditApplication.getAuditStatus()+"=等待上传合同"; //操作后状态
	      operateLog.setRemark(aa+bb);
				operateLogService.insert(operateLog);
				//end
		return rows>0 ? true:false;
	}

	@Override
	public boolean updateFailContractState(Long creditapplicationId) {
		//查询当前业务状态
		CreditApplication creditApplication=new CreditApplication();
		creditApplication=creditApplicationService.selectById(Integer.parseInt(creditapplicationId.toString()));
		//打印合同记录操作日志
		// 加日志 begin
		OperateLogBusinessStruct operateLog = new OperateLogBusinessStruct();
	      operateLog.setCreditapplicationId(Long.valueOf(creditapplicationId));
	      operateLog.setFunctionCode("100041");
	      operateLog.setResult("作废合同时把业务状态 改为等待重新打印合同");
	      String aa="操作前状态=" + creditApplication.getAuditStatus()+"等待上传合同;";
	      int rows=contractAndLoanDao.updateFailContractState(creditapplicationId);
	      creditApplication=creditApplicationService.selectById(Integer.parseInt(creditapplicationId.toString()));
	      String bb="操作后状态="+creditApplication.getAuditStatus()+"等待重新打印合同"; //操作后状态
	      operateLog.setRemark(aa+bb);
				operateLogService.insert(operateLog);
				//end
		return rows>0 ? true:false;
	}

	@Override
	public boolean updateUploadingContractState(Long creditapplicationId) {
		//查询当前业务状态
		CreditApplication creditApplication=new CreditApplication();
		creditApplication=creditApplicationService.selectById(Integer.parseInt(creditapplicationId.toString()));
		//上传合同资料记录操作日志
		// 加日志 begin
		OperateLogBusinessStruct operateLog = new OperateLogBusinessStruct();
	      operateLog.setCreditapplicationId(Long.valueOf(creditapplicationId));
	      operateLog.setFunctionCode("100041");
	      operateLog.setResult("上传合同资料时把业务状态 改为等待合同复核");
	      String aa="操作前状态=" + creditApplication.getAuditStatus()+"等待上传合同;";
	      int rows=contractAndLoanDao.updateUploadingContractState(creditapplicationId);
	      creditApplication=creditApplicationService.selectById(Integer.parseInt(creditapplicationId.toString()));
	      String bb="操作后状态="+creditApplication.getAuditStatus()+"等待合同复核"; //操作后状态
	      operateLog.setRemark(aa+bb);
				operateLogService.insert(operateLog);
				//end
		return rows>0 ? true:false;
	}

	@Override
	public boolean updateWaitLoanContractState(Long creditapplicationId) {
		//查询当前业务状态
		CreditApplication creditApplication=new CreditApplication();
		creditApplication=creditApplicationService.selectById(Integer.parseInt(creditapplicationId.toString()));
		//通知贷后放款前记录操作日志
		// 加日志 begin
		OperateLogBusinessStruct operateLog = new OperateLogBusinessStruct();
	      operateLog.setCreditapplicationId(Long.valueOf(creditapplicationId));
	      operateLog.setFunctionCode("100041");
	      operateLog.setResult("通知贷后放款前把业务状态 改为等待放款");
	      String aa="操作前状态=" + creditApplication.getAuditStatus()+"等待信托复核;";
	      int rows=contractAndLoanDao.updateWaitLoanContractState(creditapplicationId);
	      creditApplication=creditApplicationService.selectById(Integer.parseInt(creditapplicationId.toString()));
	      String bb="操作后状态="+creditApplication.getAuditStatus()+"等待放款"; //操作后状态
	      operateLog.setRemark(aa+bb);
				operateLogService.insert(operateLog);
				//end
		return rows>0 ? true:false;
	}
	
	/*@Override
	public boolean updateReturnToSignContractState(Long creditapplicationId) {
		//查询当前业务状态
		CreditApplication creditApplication=new CreditApplication();
		creditApplication=creditApplicationService.selectById(Integer.parseInt(creditapplicationId.toString()));
		//退回重签记录操作日志
		// 加日志 begin
		OperateLogBusinessStruct operateLog = new OperateLogBusinessStruct();
	      operateLog.setCreditapplicationId(Long.valueOf(creditapplicationId));
	      operateLog.setFunctionCode("100041");
	      operateLog.setResult("放款确认时退回重签时把业务状态 改为等待重新打印合同");
	      String aa="操作前状态=" + creditApplication.getAuditStatus()+"等待合同复核;";
	      int rows=contractAndLoanDao.updateFailContractState(creditapplicationId);
	      creditApplication=creditApplicationService.selectById(Integer.parseInt(creditapplicationId.toString()));
	      String bb="操作后状态="+creditApplication.getAuditStatus()+"等待重新打印合同"; //操作后状态
	      operateLog.setRemark(aa+bb);
				operateLogService.insert(operateLog);
				//end
		  //更改为历史数据
		   contractAndLoanDao.updateSupplementaryDataContractStateHistryFlag(creditapplicationId);
		return rows>0 ? true:false;
	}*/
	@Override
	public LoanConfirmMessageVo searchForConfirm(Long creditapplicationId) {
		return contractAndLoanDao.searchForConfirm(creditapplicationId);
	}

	@Override
	public AmountConfirm searchForConfirmAmount(Long creditapplicationId) {
		// TODO Auto-generated method stub
		return contractAndLoanDao.searchForConfirmAmount(creditapplicationId);
	}

	@Override
	public ContractRateForQYResult qyContractRateForQYResult(Long creditapplicationId) {
		// TODO Auto-generated method stub
		ContractRateForQYResult contractRateForQYResult=contractAndLoanDao.qyContractRateForQYResult(creditapplicationId);
		//取申请单的借款用途   --2015-01-08 begin
		//现在的取法的取法 现在修改为： 借款详细用途 2014-12-25修改
		String loanPurpose = "";
		String getBorrowUseNew = "";
        //1.从申请单中获取
		List<Application> applicationsList = (List<Application>) applicationDao.queryList(
				"RL_APPLICATION.selectBorrowerServiceApp",contractRateForQYResult.getBorrowerServiceAppId());
		if (CommonUtil.isNotEmpty(applicationsList)) {
			for(int i=0;i<applicationsList.size();i++){
				Application application = applicationsList.get(i);
				if (application != null) {
					getBorrowUseNew = application.getBorrowUse();
				}
				if (DicUtil.sectionMap.containsKey("borroerUserConsult")) {
					Map<String, String> detailsLoansuseTypeMap = DicUtil.sectionMap.get("borroerUserConsult");
					String detailsLoansuseType = getBorrowUseNew;
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
		}
		//end
		contractRateForQYResult.setBorrowUse(loanPurpose);
		contractRateForQYResult.setBorrowUseText(loanPurpose);
		return contractRateForQYResult;
	}

	@Override
	public CreditApplication searchForCreditApplicationStates(Long creditapplicationId) {
		
		return contractAndLoanDao.searchForCreditApplicationStates(creditapplicationId);
	}

	@Override
	public List<Long> queryCreditIdListLoan() {
		// TODO Auto-generated method stub
		return contractAndLoanDao.queryCreditIdListLoan();
	}

	@Override
	public LendingConfiguration searchConfigureChannel(Long creditapplicationId) {
		return contractAndLoanDao.searchConfigureChannel(creditapplicationId);
	}
	
	@Override
	public AccountInfo searchForCompanyNameAndMobilePhone(
			Long creditapplicationId) {
		return contractAndLoanDao.searchForCompanyNameAndMobilePhone(creditapplicationId);
	}
	@Override
	public Message addTtrustprintContract(AmountConfirm amountConfirm, AccountInfo accountInfo, GroupLoanRegist groupLoadRegist,CreditApplication creditApplication ,String type){
		Message message=new Message();
		MatchBorrowResVo borrowResVo = new MatchBorrowResVo();
		//保存卡信息的方法
		amountConfirmService.addAmountConfirm(amountConfirm,  accountInfo, groupLoadRegist,creditApplication,type);
		//得出对应creditapplicationId的年化利率 luohongjie
		Double  yearInterestRateXin= creditApplicationService.getAnnualizedRateById(amountConfirm.getCreditapplicationId());
		/*System.out.println("222222");
		Long.valueOf(groupLoadRegist.getCreditapplicationId());
		amountConfirm.getAmount();
		System.out.println(Long.valueOf(groupLoadRegist.getCreditapplicationId()));
		amountConfirm.setAmount(3500.00);
		System.out.println(amountConfirm.getAmount());
		System.out.println("222222");*/
		//调用撮合接口(加入撮合成功判断)
		String str = "";
		str = borrowMatchingDAO.selectStatusbyCreditapplicationId(Long.parseLong(amountConfirm.getCreditapplicationId().toString()));//Long.parseLong(amountConfirm.getCreditapplicationId().toString())
		if("0".equals(str) || "".equals(str) || null == str){
			borrowResVo=orgamsService.borrowMatchingReq(Long.valueOf(groupLoadRegist.getCreditapplicationId()),yearInterestRateXin,amountConfirm.getAmount());
			Long status=borrowResVo.getStatus();
			if(status==1){
				//获取  “信托计划名称”   记得要存到数据库中(问周磊   这个字段保存在哪张表里了)
			String planName=borrowResVo.getTradeDealList().get(0).getPlanName();
			message.setMsg("撮合成功:"+planName);
			message.setSuccess(true);
			}else{
				//失败原因  需要存到数据库里吗？周磊  存了没有？
				String errorMsg=borrowResVo.getErrorMsg();
				message.setMsg("撮合失败！");
				message.setSuccess(false);
			}
		}else if("1".equals(str)){
			
			List<TradeDealForm> tradeDealFormResult = dealFormDAO.selectTradeDealFormResultByCaId(Long.valueOf(groupLoadRegist.getCreditapplicationId()));
			String planName ="";
			if(CommonUtil.isNotEmpty(tradeDealFormResult)){
				
				planName= tradeDealFormResult.get(0).getPlanname();
			}
				//tradeDealForm.getPlanname();
			message.setMsg("撮合成功:"+planName);
			message.setSuccess(true);
		}
		/*//获取撮合结果
		Long status=borrowResVo.getStatus();
		System.out.println(status);
		if(status==1){
			//获取  “信托计划名称”   记得要存到数据库中(问周磊   这个字段保存在哪张表里了)
		String planName=borrowResVo.getTradeDealList().get(0).getPlanName();
		System.out.println(planName);
		message.setMsg("撮合成功:"+planName);
		message.setSuccess(true);
		}else{
			//失败原因  需要存到数据库里吗？周磊  存了没有？
			String errorMsg=borrowResVo.getErrorMsg();
			message.setMsg("撮合失败！");
			message.setSuccess(false);
		}*/
		return message;
	}

	@Override
	public String selectByCaIdLf(Long creditapplicationId) {
		return contractAndLoanDao.selectByCaIdLf(creditapplicationId);
	}

	/**
	 * 退回重签时 撤销撮合
	 */
	@Override
	public Message revokeMatchmaking(CreditApplication creditApplication) {
		Message message=new Message();
		BaseResponse baseResponse=orgamsService.upMatching(Long.valueOf(creditApplication.getCreditapplicationId()));
		Long status=baseResponse.getStatus();
		if(status==1){
			//更新撮合结果
			BorrowMatching borrowMatching = new BorrowMatching();
			borrowMatching.setStatus("0");//失败
			borrowMatching.setCreditapplicationId(Long.valueOf(creditApplication.getCreditapplicationId()));
			borrowMatchingDAO.updateByCaId(borrowMatching);
			//判断当前的业务状态  33等待上传合同，是作废合同的时候调的撤销撮合
			/*if(creditApplication.getAuditStatus().equals(33)){
				this.updateFailContractState(Long.valueOf(creditApplication.getCreditapplicationId()));
			}else if(creditApplication.getAuditStatus().equals(35)){ 
				//判断当前的业务状态  35等待合同复核，是退回重签的时候调的撤销撮合
				//this.updateReturnToSignContractState(Long.valueOf(creditApplication.getCreditapplicationId()));
			}*//*else if(creditApplication.getAuditStatus().equals(36)){
				//判断当前的业务状态  36等待信托复核，是信托复核未通过的时候调的撤销撮合
				this.updateWaitContractAgainCheck(Long.valueOf(creditApplication.getCreditapplicationId()));
			}*/
			System.out.println("撤销成功！");
			message.setMsg("撤销成功！");
			message.setSuccess(true);
		}else{
			System.out.println("撤销失败！");
			message.setMsg("撤销失败！");
			message.setSuccess(false);
		}
		return message;
	}

	//拒贷，客户放弃的时候撤销撮合
	@Override
	public Message revokeMatchmaking(Long creditapplicationId,String section,String refuseReasons,String auditStatus){
		Message message=new Message();
		BaseResponse baseResponse=orgamsService.upMatching(creditapplicationId);
		Long status=baseResponse.getStatus();
		if(status==1){
			//更新撮合结果
			BorrowMatching borrowMatching = new BorrowMatching();
			borrowMatching.setStatus("0");//失败
			borrowMatching.setCreditapplicationId(creditapplicationId);
			borrowMatchingDAO.updateByCaId(borrowMatching);
			//调用拒贷客户放弃保存原因方法
			refuseReasonService.updateRefuse(creditapplicationId,section,refuseReasons,auditStatus);
			System.out.println("撤销成功！");
			message.setMsg("撤销成功！");
			message.setSuccess(true);
		}else{
			System.out.println("撤销失败！");
			message.setMsg("撤销失败！");
			message.setSuccess(false);
		}
		return message;
	}
	@Override
	public boolean updateWaitContractAgainCheck(Long creditapplicationId) {
		//查询当前业务状态
		CreditApplication creditApplication=new CreditApplication();
		creditApplication=creditApplicationService.selectById(Integer.parseInt(creditapplicationId.toString()));
		//退回重签记录操作日志
		// 加日志 begin
		OperateLogBusinessStruct operateLog = new OperateLogBusinessStruct();
	      operateLog.setCreditapplicationId(Long.valueOf(creditapplicationId));
	      operateLog.setFunctionCode("100041");
	      operateLog.setResult("信托合同复核时把业务状态 改为等待合同重新复核");
	      String aa="操作前状态=" + creditApplication.getAuditStatus()+"等待信托复核;";
	      int rows=contractAndLoanDao.updateWaitContractAgainCheckState(creditapplicationId);
	      creditApplication=creditApplicationService.selectById(Integer.parseInt(creditapplicationId.toString()));
	      String bb="操作后状态="+creditApplication.getAuditStatus()+"等待合同重新复核"; //操作后状态
	      operateLog.setRemark(aa+bb);
				operateLogService.insert(operateLog);
				//end
		  //更改为历史数据
		   contractAndLoanDao.updateSupplementaryDataContractStateHistryFlag(creditapplicationId);
		return rows>0 ? true:false;
	}

	@Override
	public boolean updateChangeLoanChannelXinTuo(Long creditapplicationId) {
		//变更放款渠道
		int rows=contractAndLoanDao.updateChangeLoanChannelXinTuo(creditapplicationId);
		// 加日志 begin
		OperateLogBusinessStruct operateLog = new OperateLogBusinessStruct();
	      operateLog.setCreditapplicationId(Long.valueOf(creditapplicationId));
	      operateLog.setFunctionCode("100041");
	      operateLog.setResult("变更放款渠道操作：把放款渠道改为“债权转让”");
	      operateLog.setRemark("放款渠道从“信托”变更为“债权转让”");
				operateLogService.insert(operateLog);
				//end
		return rows>0?true:false;
	}

	@Override
	public List<TradeDealForm> selectTradeDealFormResultByCaId(
			Long creditapplicationId) {
		List<TradeDealForm> TradeDealForm=dealFormDAO.selectTradeDealFormResultByCaId(creditapplicationId);
		return TradeDealForm;
	}

	@Override
	public boolean updateWaitContractAgainCheckOther(Long creditapplicationId) {
		//查询当前业务状态
		CreditApplication creditApplication=new CreditApplication();
		creditApplication=creditApplicationService.selectById(Integer.parseInt(creditapplicationId.toString()));
		//退回重签记录操作日志
		// 加日志 begin
		OperateLogBusinessStruct operateLog = new OperateLogBusinessStruct();
	      operateLog.setCreditapplicationId(Long.valueOf(creditapplicationId));
	      operateLog.setFunctionCode("100041");
	      operateLog.setResult("信托合同复核时把业务状态 改为等待合同重新复核");
	      String aa="操作前状态=" + creditApplication.getAuditStatus()+"等待信托复核;";
	      int rows=contractAndLoanDao.updateWaitContractAgainCheckState(creditapplicationId);
	      creditApplication=creditApplicationService.selectById(Integer.parseInt(creditapplicationId.toString()));
	      String bb="操作后状态="+creditApplication.getAuditStatus()+"等待合同重新复核"; //操作后状态
	      operateLog.setRemark(aa+bb);
				operateLogService.insert(operateLog);
				//end
		return rows>0 ? true:false;
	}
}
