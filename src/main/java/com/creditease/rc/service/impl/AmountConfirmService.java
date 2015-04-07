package com.creditease.rc.service.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.creditease.core.exception.BusinessException;
import com.creditease.core.security.SpringSecurityUtils;
import com.creditease.rc.app.pdf.CeBorrowingProductsWS;
import com.creditease.rc.app.pdf.ChargeInfo;
import com.creditease.rc.app.pdf.PaymentTypeConfig;
import com.creditease.rc.app.pdf.RateReqParam;
import com.creditease.rc.app.pdf.RateReqParam.PaymentTypeList;
import com.creditease.rc.app.pdf.RateReqResult;
import com.creditease.rc.app.pdf.RateReqResult.FrontChargeList;
import com.creditease.rc.common.Constants;
import com.creditease.rc.dao.IAmountConfirmDAO;
import com.creditease.rc.dao.IBorrowerServiceAppDAO;
import com.creditease.rc.dao.IContractAndLoanDao;
import com.creditease.rc.dao.IGroupLoanRegistDAO;
import com.creditease.rc.domain.AccountInfo;
import com.creditease.rc.domain.AmountConfirm;
import com.creditease.rc.domain.BorrowerServiceApp;
import com.creditease.rc.domain.CreditApplication;
import com.creditease.rc.domain.GroupLoanRegist;
import com.creditease.rc.domain.Message;
import com.creditease.rc.service.IAccountInfoService;
import com.creditease.rc.service.IAmountConfirmService;
import com.creditease.rc.service.IContractAndLoanService;
import com.creditease.rc.service.ICreditApplicationService;
import com.creditease.rc.service.IFinancePaymentService;
import com.creditease.rc.service.IOperateLogService;
import com.creditease.rc.service.IProtocolManagementService;
import com.creditease.rc.util.BankCardNOLock;
import com.creditease.rc.util.CommonUtil;
import com.creditease.rc.util.CurrencyUtil;
import com.creditease.rc.util.DateUtil;
import com.creditease.rc.util.JsonUtil;
import com.creditease.rc.vo.AmountConfirmVo;
import com.creditease.rc.vo.OperateLogBusinessStruct;
import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;

/**
 * 放款金额确认服务类
 * 
 * @author zhangman
 * 
 */
@Service
public class AmountConfirmService implements IAmountConfirmService {

	@Resource
	private IAmountConfirmDAO amountConfirmDAO;
	@Resource
	private ICreditApplicationService creditApplicationService;
	@Resource
	private CeBorrowingProductsWS ceBorrowingProductsWS;
	@Resource
	private IOperateLogService operateLogService;
	@Resource
	private IFinancePaymentService financePaymentService;
	@Resource
	private IAccountInfoService accountInfoService;
	@Resource
	private IBorrowerServiceAppDAO borrowerServiceAppDAO;
	@Resource
	private IGroupLoanRegistDAO groupLoanRegistDao;
	@Resource
	private IContractAndLoanDao contractAndLoanDao;
	@Resource
	private IProtocolManagementService protocolManagementService;
	@Resource
	private IContractAndLoanService contractAndLoanService;

	private Logger log = Logger.getLogger(AmountConfirmService.class);

	@Override
	public Message addAmountConfirm(AmountConfirm amountConfirm,
			AccountInfo accountInfo) {
		Message message = new Message();
		// 计算放款确认金额的服务费，实际放款 也要通过他 判断状态
		CreditApplication creditApplication = creditApplicationService
				.selectById(amountConfirm.getCreditapplicationId());
		if (!Constants.STATE_4.equals(creditApplication.getAuditStatus())) {
			message.setMsg("已经不是‘审批通过’的状态,该状态下不能做放款额度确认，请您刷新页面");
			message.setSuccess(false);
			return message;
		} else {
			// List<AmountConfirm> amountConfirm2 =
			// amountConfirmDAO.searchAmountConfirm(amountConfirm);
			// 修改之前的确认金额为历史
			this.updateAmountConfirm(amountConfirm.getCreditapplicationId());

			System.out.println(amountConfirm.getLoanTime());
			GregorianCalendar gregorianCalendar = DateUtil
					.getGregorianCalendar(amountConfirm.getLoanTime());

			if (accountInfo != null
					&& (accountInfo.getAccountInfoId() == null || ""
							.equals(accountInfo.getAccountInfoId()))) {
				// 对私账户
				BorrowerServiceApp borrowerServiceApp = borrowerServiceAppDAO
						.selectBorrowerLeaderByCreditApplicationId(amountConfirm
								.getCreditapplicationId());
				accountInfo.setBorrowerName(borrowerServiceApp.getName());
				accountInfo.setBorrowerCredentialsNumber(borrowerServiceApp
						.getCredentialsNumber());
				AccountInfo accountInfoParam = new AccountInfo();
				accountInfoParam.setBorrowerName(borrowerServiceApp.getName());
				accountInfoParam
						.setBorrowerCredentialsNumber(borrowerServiceApp
								.getCredentialsNumber());

				AccountInfo accountInfoExist = accountInfoService
						.selctAccountInfo(accountInfoParam);
				int accountInfoId = 0;
				// 如果该借款人 有付款账户 就用付款账户并且更新信息
				if (accountInfoExist != null) {
					accountInfoId = accountInfoExist.getAccountInfoId();
					accountInfo.setAccountInfoId(accountInfoId);
					accountInfoService.updateAccountInfo(accountInfo);
				} else {
					accountInfoId = accountInfoService.addAccount(accountInfo);
				}
				creditApplication.setAccountInfoId(accountInfoId);
				creditApplication.setReturnAccountInfoId(accountInfoId);
				creditApplication.setBusinessType("1");
				creditApplication.setDefaultReturnmentWay("0");
				creditApplicationService
						.updateCreditApplicationForAccount(creditApplication);
			} else {
				// 对公账户
				creditApplication.setAccountInfoId(accountInfo
						.getAccountInfoId());
				// creditApplication.setReturnAccountInfoId(accountInfo.getAccountInfoId());
				creditApplication.setBusinessType("0");
				creditApplication.setDefaultReturnmentWay("1");
				creditApplicationService
						.updateCreditApplicationForAccount(creditApplication);
			}

			RateReqParam rr = new RateReqParam();
			// 部门id
			if (creditApplication.getDepartmentId() != null) {
				rr.setDepartmentId(Long.valueOf(creditApplication
						.getDepartmentId()));
			} else {
				if (SpringSecurityUtils.getCurrentUser().getDepartmentId() != null) {
					rr.setDepartmentId(SpringSecurityUtils.getCurrentUser()
							.getDepartmentId());
				}
			}
			// 合同金额
			rr.setContractMoney(new BigDecimal(amountConfirm.getAmount()));
			// 请求放款时间
			rr.setLenderDate(new XMLGregorianCalendarImpl(gregorianCalendar));
			// 产品id
			rr.setProductInfoId(Long.valueOf(creditApplication
					.getRepaymentPlanId()));
			// 分期数
			rr.setPeriodCount(Integer.valueOf(creditApplication
					.getInstalments()));
			PaymentTypeList ptlist = new PaymentTypeList();
			PaymentTypeConfig p = new PaymentTypeConfig();
			p.setPaymentTypeCode(creditApplication.getRepaymentType());
			ptlist.getPaymentTypeConfig().add(p);
			rr.setPaymentTypeList(ptlist);
			log.info("ceBorrowingProductsWS.ceCalculatedBorrowingRateReq(RateReqParam) request_params:******");
			log.info(JsonUtil.getJackson(rr));
			RateReqResult result = ceBorrowingProductsWS
					.ceCalculatedBorrowingRateReq(rr);// 费率计算请求-查询借款产品费率，并计算利息和服务费等
			if (result != null) {
				log.info("ceBorrowingProductsWS.ceCalculatedBorrowingRateReq(RateReqParam) response_params:******");
				log.info(JsonUtil.getJackson(result));
				if ("0".equals(result.getResultCode())) {
					FrontChargeList frontChargeList = result
							.getFrontChargeList();
					if (frontChargeList != null) {
						List<ChargeInfo> chargeInfos = frontChargeList
								.getChargeInfo();
						if (chargeInfos.size() > 0) {
							ChargeInfo chargeInfo = chargeInfos.get(0);
							if (chargeInfo != null) {
								BigDecimal charge = chargeInfo.getCharge();
								// 服务费
								amountConfirm.setServiceCharge(charge
										.doubleValue());
								// 实际放款
								amountConfirm.setRealAmount(CurrencyUtil.sub(
										amountConfirm.getAmount(),
										charge.doubleValue()));
								amountConfirm.setCreateTime(new Date());
								amountConfirm.setOperator(SpringSecurityUtils
										.getCurrentUser().getName_zh());
								amountConfirm.setOperatorId(Integer
										.valueOf(SpringSecurityUtils
												.getCurrentUser().getUserId()));
								amountConfirm.setHistoryFlag("0");
								amountConfirmDAO
										.addAmountConfirm(amountConfirm);
								// 信贷申请状态改为“放款额度确认”
								creditApplicationService.changeAuditing(
										creditApplication,
										amountConfirm.getCreditapplicationId(),
										Constants.STATE_21);
								// 如果对分公司账户 向财务预约
								if ("0".equals(creditApplication
										.getBusinessType())) {
									financePaymentService.financialpayment(
											creditApplication
													.getCreditapplicationId(),
											null);
								}
								/******** 日志 ********/
								OperateLogBusinessStruct operateLog = new OperateLogBusinessStruct();
								operateLog.setCreditapplicationId(Long
										.valueOf(amountConfirm
												.getCreditapplicationId()));
								operateLog.setFunctionCode("300010");
								operateLog.setResult("借款金额确认");
								operateLog
										.setExpectLoanDate(new SimpleDateFormat(
												"yyyy-MM-dd")
												.format(amountConfirm
														.getLoanTime()));
								operateLog.setConfirmAmount(String
										.valueOf(amountConfirm.getAmount()));
								operateLogService.insert(operateLog);
								message.setSuccess(true);
								message.setMsg("操作成功！");
							} else {
								message.setSuccess(false);
								message.setMsg("产品前期服务费为空");
							}
						} else {
							message.setSuccess(false);
							message.setMsg("产品前期服务费为空");
						}
					} else {
						message.setSuccess(false);
						message.setMsg("产品前期服务费为空");
					}
				} else {
					message.setSuccess(false);
					message.setMsg(result.getResultMessage());
				}
			} else {
				message.setSuccess(false);
				message.setMsg("产品中心返回结果是空");
			}
		}
		return message;
	}

	@Override
	public int updateAmountConfirm(AmountConfirm amountConfirm) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void updateAmountConfirm(int creditapplicationId) {
		// TODO Auto-generated method stub
		amountConfirmDAO.updateAmountConfirm(creditapplicationId);
	}

	@Override
	public void updateAmountConfirmHistory(int creditapplicationId) {

		int result = amountConfirmDAO.updateAmountConfirm(creditapplicationId);
		if (result > 0) {
			OperateLogBusinessStruct operateLog = new OperateLogBusinessStruct();
			operateLog
					.setCreditapplicationId(Long.valueOf(creditapplicationId));
			operateLog.setFunctionCode("300050");
			operateLog.setResult("额度确认置为历史");
			operateLog.setRemark("历史标记是“1”");
			operateLogService.insert(operateLog);
		}
	}

	@Override
	public List<AmountConfirm> searchAmountConfirm(AmountConfirm amountConfirm) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AmountConfirm selectNew(Integer creditapplicationId) {
		// TODO Auto-generated method stub
		return amountConfirmDAO.selectNew(creditapplicationId);
	}

	@Override
	public AmountConfirmVo selectAmount(Integer creditapplicationId) {
		// TODO Auto-generated method stub
		return amountConfirmDAO.selectAmount(creditapplicationId);
	}

	/**
	 * 根据申请单号查询该单号的额度确认数据 liuli 2013-05-20
	 * 
	 * @param creditApplicationId
	 * @return List<AmountConfirm>
	 */
	@Override
	public List<AmountConfirm> selectAmountsByCreditAppId(
			Integer creditApplicationId) {
		List l = amountConfirmDAO
				.selectAmountsByCreditAppId(creditApplicationId);
		return l;
	}

	/**
	 * 根据额度确认id将状态置为1 历史状态 liuli 2013-05-20
	 * 
	 * @param amountConfirmId
	 * @return int
	 */
	@Override
	public int updateByAmountConfirmId(String amountConfirmId) {
		int i = amountConfirmDAO.updateByAmountConfirmId(amountConfirmId);
		return i;
	}

	@Override
	public AmountConfirm queryAmountConfirmByPrimaryKey(long longValue) {
		// TODO Auto-generated method stub
		return (AmountConfirm) amountConfirmDAO.queryUnique(
				"AmountConfirm.queryAmountConfirmByPrimaryKey", longValue);
	}

	@Override
	public Message addAmountConfirm(AmountConfirm amountConfirm,
			AccountInfo accountInfo, GroupLoanRegist groupLoadRegist,
			CreditApplication creditApplication, String type) {
		Message message = new Message();
		// 添加最后一次下载合同时间
		amountConfirm.setLastDownloadContractTime(new Timestamp(new Date()
				.getTime()));
		// 同步申请列表的放款时间（signagreementDate）起息日期
		creditApplication.setSignagreementDate(amountConfirm
				.getBeginInterestTime());
		System.out.println(creditApplication.getSignagreementDate());
		int rows1 = creditApplicationService
				.updateSignagreementDate(creditApplication);
		System.out.println(rows1);
		// 计算放款确认金额的服务费，实际放款 也要通过他 判断状态
		creditApplication = creditApplicationService.selectById(amountConfirm
				.getCreditapplicationId());
		if (!Constants.STATE_4.equals(creditApplication.getAuditStatus())
				&& !Constants.STATE_34.equals(creditApplication
						.getAuditStatus())) {
			message.setMsg("已经不是‘审批通过’的状态,也去不是'等待重新打印合同'状态该状态下不能做放款额度确认，请您刷新页面");
			message.setSuccess(false);
			return message;
		} else {
			// List<AmountConfirm> amountConfirm2 =
			// amountConfirmDAO.searchAmountConfirm(amountConfirm);
			//查询是否配置放款渠道(为保存提供数据)
			String lendingChannelInfo = amountConfirmDAO.selectLendingChannel(Long.parseLong(amountConfirm.getCreditapplicationId().toString()));
			
			if(CommonUtil.isEmpty(lendingChannelInfo)){
				lendingChannelInfo = amountConfirm.getLendingChannel();
			}
			
			// 修改之前的确认金额为历史
			this.updateAmountConfirm(amountConfirm.getCreditapplicationId());

			amountConfirm.setLoanTime(new Date());
			System.out.println(amountConfirm.getLoanTime());
			GregorianCalendar gregorianCalendar = DateUtil
					.getGregorianCalendar(amountConfirm.getLoanTime());

			if (accountInfo != null
					&& (accountInfo.getAccountInfoId() == null || ""
							.equals(accountInfo.getAccountInfoId()))) {
				// 对私账户
				BorrowerServiceApp borrowerServiceApp = borrowerServiceAppDAO
						.selectBorrowerLeaderByCreditApplicationId(amountConfirm
								.getCreditapplicationId());
				accountInfo.setBorrowerName(borrowerServiceApp.getName());
				accountInfo.setBorrowerCredentialsNumber(borrowerServiceApp
						.getCredentialsNumber());
				AccountInfo accountInfoParam = new AccountInfo();
				accountInfoParam.setBorrowerName(borrowerServiceApp.getName());
				accountInfoParam
						.setBorrowerCredentialsNumber(borrowerServiceApp
								.getCredentialsNumber());

				AccountInfo accountInfoExist = accountInfoService
						.selctAccountInfo(accountInfoParam);
				int accountInfoId = 0;
				// 如果该借款人 有付款账户 就用付款账户并且更新信息
				if (accountInfoExist != null) {
					accountInfoId = accountInfoExist.getAccountInfoId();
					accountInfo.setAccountInfoId(accountInfoId);
					accountInfoService.updateAccountInfo(accountInfo, type);
				} else {
					accountInfoId = accountInfoService.addAccount(accountInfo);
				}
				creditApplication.setAccountInfoId(accountInfoId);
				creditApplication.setReturnAccountInfoId(accountInfoId);
				creditApplication.setBusinessType("1");
				creditApplication.setDefaultReturnmentWay("0");
				creditApplicationService
						.updateCreditApplicationForAccount(creditApplication);
			} else {
				// 对公账户
				creditApplication.setAccountInfoId(accountInfo
						.getAccountInfoId());
				// creditApplication.setReturnAccountInfoId(accountInfo.getAccountInfoId());
				creditApplication.setBusinessType("0");
				creditApplication.setDefaultReturnmentWay("1");
				creditApplicationService
						.updateCreditApplicationForAccount(creditApplication);
			}

			RateReqParam rr = new RateReqParam();
			// 部门id
			if (creditApplication.getDepartmentId() != null) {
				rr.setDepartmentId(Long.valueOf(creditApplication
						.getDepartmentId()));
			} else {
				if (SpringSecurityUtils.getCurrentUser().getDepartmentId() != null) {
					rr.setDepartmentId(SpringSecurityUtils.getCurrentUser()
							.getDepartmentId());
				}
			}
			// 合同金额
			rr.setContractMoney(new BigDecimal(amountConfirm.getAmount()));
			// 请求放款时间
			rr.setLenderDate(new XMLGregorianCalendarImpl(gregorianCalendar));
			// 产品id
			rr.setProductInfoId(Long.valueOf(creditApplication
					.getRepaymentPlanId()));
			// 分期数
			rr.setPeriodCount(Integer.valueOf(creditApplication
					.getInstalments()));
			PaymentTypeList ptlist = new PaymentTypeList();
			PaymentTypeConfig p = new PaymentTypeConfig();
			p.setPaymentTypeCode(creditApplication.getRepaymentType());
			ptlist.getPaymentTypeConfig().add(p);
			rr.setPaymentTypeList(ptlist);
			log.info("ceBorrowingProductsWS.ceCalculatedBorrowingRateReq(RateReqParam) request_params:******");
			log.info(JsonUtil.getJackson(rr));
			RateReqResult result = ceBorrowingProductsWS
					.ceCalculatedBorrowingRateReq(rr);// 费率计算请求-查询借款产品费率，并计算利息和服务费等
			if (result != null) {
				log.info("ceBorrowingProductsWS.ceCalculatedBorrowingRateReq(RateReqParam) response_params:******");
				log.info(JsonUtil.getJackson(result));
				if ("0".equals(result.getResultCode())) {
					FrontChargeList frontChargeList = result
							.getFrontChargeList();
					if (frontChargeList != null) {
						List<ChargeInfo> chargeInfos = frontChargeList
								.getChargeInfo();
						if (chargeInfos.size() > 0) {
							ChargeInfo chargeInfo = chargeInfos.get(0);
							if (chargeInfo != null) {
								BigDecimal charge = chargeInfo.getCharge();
								// 服务费
								amountConfirm.setServiceCharge(charge
										.doubleValue());
								// 实际放款
								amountConfirm.setRealAmount(CurrencyUtil.sub(
										amountConfirm.getAmount(),
										charge.doubleValue()));
								amountConfirm.setCreateTime(new Date());
								amountConfirm.setOperator(SpringSecurityUtils
										.getCurrentUser().getName_zh());
								amountConfirm.setOperatorId(Integer
										.valueOf(SpringSecurityUtils
												.getCurrentUser().getUserId()));
								amountConfirm.setHistoryFlag("0");
								amountConfirm.setLendingChannel(lendingChannelInfo);
								//amountConfirmDAO
								//.saveOrUpdate(amountConfirm);
										//.addAmountConfirm(amountConfirm);
								selectIsExist(amountConfirm);
								// 信贷申请状态改为“放款额度确认”
								/*
								 * creditApplicationService.changeAuditing(
								 * creditApplication,
								 * amountConfirm.getCreditapplicationId(),
								 * Constants.STATE_21);
								 */
								// 如果对分公司账户 向财务预约
								if ("0".equals(creditApplication
										.getBusinessType())) {
									financePaymentService.financialpayment(
											creditApplication
													.getCreditapplicationId(),
											null);
								}
								/******** 日志 ********/
								OperateLogBusinessStruct operateLog = new OperateLogBusinessStruct();
								operateLog.setCreditapplicationId(Long
										.valueOf(amountConfirm
												.getCreditapplicationId()));
								operateLog.setFunctionCode("300010");
								operateLog.setResult("借款金额确认");
								operateLog
										.setExpectLoanDate(new SimpleDateFormat(
												"yyyy-MM-dd")
												.format(amountConfirm
														.getLoanTime()));
								operateLog.setConfirmAmount(String
										.valueOf(amountConfirm.getAmount()));
								operateLogService.insert(operateLog);
								message.setSuccess(true);
								message.setMsg("操作成功！");
							} else {
								BigDecimal charge =new BigDecimal(0);
								// 服务费
								amountConfirm.setServiceCharge(charge
										.doubleValue());
								// 实际放款
								amountConfirm.setRealAmount(CurrencyUtil.sub(
										amountConfirm.getAmount(),
										charge.doubleValue()));
								amountConfirm.setCreateTime(new Date());
								amountConfirm.setOperator(SpringSecurityUtils
										.getCurrentUser().getName_zh());
								amountConfirm.setOperatorId(Integer
										.valueOf(SpringSecurityUtils
												.getCurrentUser().getUserId()));
								amountConfirm.setHistoryFlag("0");
								amountConfirm.setLendingChannel(lendingChannelInfo);
								//amountConfirmDAO
								//.saveOrUpdate(amountConfirm);
										//.addAmountConfirm(amountConfirm);
								selectIsExist(amountConfirm);
								// 信贷申请状态改为“放款额度确认”
								/*
								 * creditApplicationService.changeAuditing(
								 * creditApplication,
								 * amountConfirm.getCreditapplicationId(),
								 * Constants.STATE_21);
								 */
								// 如果对分公司账户 向财务预约
								if ("0".equals(creditApplication
										.getBusinessType())) {
									financePaymentService.financialpayment(
											creditApplication
													.getCreditapplicationId(),
											null);
								}
								/******** 日志 ********/
								OperateLogBusinessStruct operateLog = new OperateLogBusinessStruct();
								operateLog.setCreditapplicationId(Long
										.valueOf(amountConfirm
												.getCreditapplicationId()));
								operateLog.setFunctionCode("300010");
								operateLog.setResult("借款金额确认");
								operateLog
										.setExpectLoanDate(new SimpleDateFormat(
												"yyyy-MM-dd")
												.format(amountConfirm
														.getLoanTime()));
								operateLog.setConfirmAmount(String
										.valueOf(amountConfirm.getAmount()));
								operateLogService.insert(operateLog);
								message.setSuccess(true);
								message.setMsg("操作成功！");
								//message.setSuccess(false);
								//message.setMsg("产品前期服务费为空");
							}
						} else {
							BigDecimal charge = new BigDecimal(0);
							// 服务费
							amountConfirm.setServiceCharge(charge
									.doubleValue());
							// 实际放款
							amountConfirm.setRealAmount(CurrencyUtil.sub(
									amountConfirm.getAmount(),
									charge.doubleValue()));
							amountConfirm.setCreateTime(new Date());
							amountConfirm.setOperator(SpringSecurityUtils
									.getCurrentUser().getName_zh());
							amountConfirm.setOperatorId(Integer
									.valueOf(SpringSecurityUtils
											.getCurrentUser().getUserId()));
							amountConfirm.setHistoryFlag("0");
							amountConfirm.setLendingChannel(lendingChannelInfo);
							//amountConfirmDAO
							//.saveOrUpdate(amountConfirm);
									//.addAmountConfirm(amountConfirm);
							selectIsExist(amountConfirm);
							// 信贷申请状态改为“放款额度确认”
							/*
							 * creditApplicationService.changeAuditing(
							 * creditApplication,
							 * amountConfirm.getCreditapplicationId(),
							 * Constants.STATE_21);
							 */
							// 如果对分公司账户 向财务预约
							if ("0".equals(creditApplication
									.getBusinessType())) {
								financePaymentService.financialpayment(
										creditApplication
												.getCreditapplicationId(),
										null);
							}
							/******** 日志 ********/
							OperateLogBusinessStruct operateLog = new OperateLogBusinessStruct();
							operateLog.setCreditapplicationId(Long
									.valueOf(amountConfirm
											.getCreditapplicationId()));
							operateLog.setFunctionCode("300010");
							operateLog.setResult("借款金额确认");
							operateLog
									.setExpectLoanDate(new SimpleDateFormat(
											"yyyy-MM-dd")
											.format(amountConfirm
													.getLoanTime()));
							operateLog.setConfirmAmount(String
									.valueOf(amountConfirm.getAmount()));
							operateLogService.insert(operateLog);
							message.setSuccess(true);
							message.setMsg("操作成功！");
							//message.setSuccess(false);
							//message.setMsg("产品前期服务费为空");
						}
					} else {
						BigDecimal charge = new BigDecimal(0);
						// 服务费
						amountConfirm.setServiceCharge(charge
								.doubleValue());
						// 实际放款
						amountConfirm.setRealAmount(CurrencyUtil.sub(
								amountConfirm.getAmount(),
								charge.doubleValue()));
						amountConfirm.setCreateTime(new Date());
						amountConfirm.setOperator(SpringSecurityUtils
								.getCurrentUser().getName_zh());
						amountConfirm.setOperatorId(Integer
								.valueOf(SpringSecurityUtils
										.getCurrentUser().getUserId()));
						amountConfirm.setHistoryFlag("0");
						amountConfirm.setLendingChannel(lendingChannelInfo);
						//amountConfirmDAO
						//.saveOrUpdate(amountConfirm);
								//.addAmountConfirm(amountConfirm);
						selectIsExist(amountConfirm);
						// 信贷申请状态改为“放款额度确认”
						/*
						 * creditApplicationService.changeAuditing(
						 * creditApplication,
						 * amountConfirm.getCreditapplicationId(),
						 * Constants.STATE_21);
						 */
						// 如果对分公司账户 向财务预约
						if ("0".equals(creditApplication
								.getBusinessType())) {
							financePaymentService.financialpayment(
									creditApplication
											.getCreditapplicationId(),
									null);
						}
						/******** 日志 ********/
						OperateLogBusinessStruct operateLog = new OperateLogBusinessStruct();
						operateLog.setCreditapplicationId(Long
								.valueOf(amountConfirm
										.getCreditapplicationId()));
						operateLog.setFunctionCode("300010");
						operateLog.setResult("借款金额确认");
						operateLog
								.setExpectLoanDate(new SimpleDateFormat(
										"yyyy-MM-dd")
										.format(amountConfirm
												.getLoanTime()));
						operateLog.setConfirmAmount(String
								.valueOf(amountConfirm.getAmount()));
						operateLogService.insert(operateLog);
						message.setSuccess(true);
						message.setMsg("操作成功！");
						//message.setSuccess(false);
						//message.setMsg("产品前期服务费为空");
					}
				} else {
					message.setSuccess(false);
					message.setMsg(result.getResultMessage());
				}
			} else {
				message.setSuccess(false);
				message.setMsg("产品中心返回结果是空");
			}
		}
		return message;
	}

	/**
	 * 封装打印合同的方法
	 */
	@Override
	public Message addPrintContract(AmountConfirm amountConfirm,
			AccountInfo accountInfo, GroupLoanRegist groupLoadRegist,
			String loanRegistList, String groupLoanRegist,
			String[] uploadFileList, String[] uploadFileTypeList,
			String[] originalFileList, Date time,
			CreditApplication creditApplication, String loanDate,
			HttpServletRequest request) {
		Message message = new Message();
		String type = "1";
		Integer accountInfoId = accountInfo.getAccountInfoId();
		// 判断对公对私
		if (accountInfoId == null) {// 对私
			message = this.addAmountConfirm(amountConfirm, accountInfo,
					groupLoadRegist, creditApplication, type);
			if (!message.isSuccess()) {
				return message;
			}
		} else {// 对公
			// 后台通过主键查账号
			AccountInfo accountInfoSelect = accountInfoService
					.selectByAccountID(accountInfoId);
			String account = accountInfoSelect.getAccount();
			if (CommonUtil.isNotEmpty(account)) {
				// 判断是否存在锁
				Boolean b = BankCardNOLock.lockBankCardNO(account);
				if (b) {
					// 获取银行卡号。加锁
					message = this.addAmountConfirm(amountConfirm, accountInfo,
							groupLoadRegist, creditApplication, type);
					// 解锁
					BankCardNOLock.unLockBankCardNO(account);
					if (!message.isSuccess()) {
						return message;
					}
				} else {
					throw new BusinessException("卡号：" + account + "已锁住，请等待或解锁");
				}

			}
		}
		// 判断保存数据是否成功
		if (message.isSuccess()) {
			Date loan_Date = null;
			if (CommonUtil.isNotEmpty(loanDate)) {
				loan_Date = DateUtil.stringConvertDate(loanDate);
			} else {
				loan_Date = new Date();
			}
			String url = protocolManagementService.saveAndDownProtocol(
					creditApplication.getCreditapplicationId(), loan_Date);
			String isURL = request.getRequestURL().toString();
			if (isURL.indexOf(".cn") > 0) {
				if (url.indexOf(".corp") > 0) {
					url = url.replaceAll(".corp", ".cn");
				}
			} else if (isURL.indexOf(".corp") > 0) {
				if (url.indexOf(".cn") > 0) {
					url = url.replaceAll(".cn", ".corp");
				}
			}
			message.setMsg(url);
		}
		// 判断打印合同是否成功
		if (message.isSuccess()) {
			// 调用修改状态的放款
			boolean isSuccess = contractAndLoanService.updatePrintContractState(Long.valueOf(creditApplication.getCreditapplicationId()));
			if (isSuccess) {
				message.setSuccess(true);
			}
		}
		return message;
	}

	@Override
	public boolean updateBeginInterestTime(AmountConfirm amountConfirm) {
		int rows = amountConfirmDAO.updateBeginInterestTime(amountConfirm);
		return rows > 0 ? true : false;
	}
	

	@Override
	public String updatePrintContract(Long creditapplicationId,HttpServletRequest request) {
		String url = protocolManagementService.saveAndDownProtocolXinTuo(Integer.parseInt(creditapplicationId.toString()), new Date());
		String isURL = request.getRequestURL().toString();
		if (isURL.indexOf(".cn") > 0) {
			if (url.indexOf(".corp") > 0) {
				url = url.replaceAll(".corp", ".cn");
			}
		} else if (isURL.indexOf(".corp") > 0) {
			if (url.indexOf(".cn") > 0) {
				url = url.replaceAll(".cn", ".corp");
			}
		}
		contractAndLoanService.updatePrintContractState(creditapplicationId);
		return url;
	}

	@Override
	public String selectLendingChannel(Long creditApplicationId) {
		return amountConfirmDAO.selectLendingChannel(creditApplicationId);
	}

	/*@Override
	public int saveOrUpdate(AmountConfirm amountConfirm) {
		return amountConfirmDAO.saveOrUpdate(amountConfirm);
	}*/

	@Override
	public boolean selectIsExist(AmountConfirm amountConfirm) {
		boolean flag=false;
		int selectIsExist = amountConfirmDAO.selectIsExist(amountConfirm);
		if(selectIsExist == 0){
			amountConfirmDAO.addAmountConfirm(amountConfirm);
			flag=true;
		}else{
			amountConfirmDAO.updateAmountConfirmClass(amountConfirm);
			flag=true;
		}
		return flag;
	}
}
