package com.creditease.rc.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.creditease.rc.app.pdf.ActualChargeInfo;
import com.creditease.rc.app.pdf.ActualPeriodSchedule;
import com.creditease.rc.app.pdf.ActualPeriodSchedule.OverdueChargeList;
import com.creditease.rc.app.pdf.AppointChargeInfo;
import com.creditease.rc.app.pdf.AppointPeriodSchedule;
import com.creditease.rc.app.pdf.AppointSchedule.TotalOverdueChargeList;
import com.creditease.rc.app.pdf.AppointSchedule.TotalPeriodChargeList;
import com.creditease.rc.app.pdf.CeBorrowingProductsWS;
import com.creditease.rc.app.pdf.ChargeInfo;
import com.creditease.rc.app.pdf.PaymentAmountReq;
import com.creditease.rc.app.pdf.PaymentAmountReq.ApsList;
import com.creditease.rc.app.pdf.PaymentAmountReqResult;
import com.creditease.rc.app.pdf.PaymentTypeConfig;
import com.creditease.rc.app.pdf.PaymentTypeParam;
import com.creditease.rc.app.pdf.PeriodSchedule;
import com.creditease.rc.app.pdf.PeriodSchedule.PeriodChargeList;
import com.creditease.rc.app.pdf.ProInfo;
import com.creditease.rc.app.pdf.ProListParam;
import com.creditease.rc.app.pdf.ProPayTypeParam;
import com.creditease.rc.app.pdf.ProductInst;
import com.creditease.rc.app.pdf.QueryProByDepartResult;
import com.creditease.rc.app.pdf.QueryProByDepartResult.ProInfoList;
import com.creditease.rc.app.pdf.QueryProByDepartResult.ProInstList;
import com.creditease.rc.app.pdf.QueryProByDepartResult.ProPayTypeParamList;
import com.creditease.rc.app.pdf.QueryProInfoResult;
import com.creditease.rc.app.pdf.RateReqParam;
import com.creditease.rc.app.pdf.RateReqParam.PaymentTypeList;
import com.creditease.rc.app.pdf.RateReqResult;
import com.creditease.rc.app.pdf.RateReqResult.FrontChargeList;
import com.creditease.rc.app.pdf.RateReqResult.PeriodScheduleList;
import com.creditease.rc.app.pdf.RepaymentPlanReq;
import com.creditease.rc.app.pdf.RepaymentPlanReqResult;
import com.creditease.rc.app.pdf.RepaymentPlanReqResult.FrontChangeList;
import com.creditease.rc.dao.IReturnPlanDao;
import com.creditease.rc.domain.ActualPeriodScheduleDTO;
import com.creditease.rc.domain.ApsListInfo;
import com.creditease.rc.domain.CalculatedBorrowingRateInfo;
import com.creditease.rc.domain.ChargePeriodScheduleInfo;
import com.creditease.rc.domain.ChargePeriodScheduleInfo2;
import com.creditease.rc.domain.PaymentAmountReqCommon;
import com.creditease.rc.domain.PaymentAmountResultInfo;
import com.creditease.rc.domain.ProductInfo;
import com.creditease.rc.domain.RepaymentPlanInfo;
import com.creditease.rc.domain.ReturnPlan;
import com.creditease.rc.framework.exception.AppBusinessException;
import com.creditease.rc.service.IBorrowingProductsService;
import com.creditease.rc.util.CommonUtil;
import com.creditease.rc.util.CurrencyUtil;
import com.creditease.rc.util.DateUtil;
import com.creditease.rc.util.JsonUtil;
import com.creditease.rc.vo.AdvancePlanVo;
import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;

@Service
public class BorrowingProductsService implements IBorrowingProductsService {

	@Resource
	public CeBorrowingProductsWS ceBorrowingProductsWS;
	@Resource
	private IReturnPlanDao returnPlanDao;
	private Logger log = Logger.getLogger(BorrowingProductsService.class);

	@Override
	public List<ChargePeriodScheduleInfo> rateCalculate(CalculatedBorrowingRateInfo rateInfo) {

		RateReqParam rr = new RateReqParam();
		rr = converterRateInfo(rateInfo);
		log.info("ceBorrowingProductsWS.ceCalculatedBorrowingRateReq(RateReqParam) request_params:******");
		log.info(JsonUtil.getJackson(rr));
		RateReqResult rateReqResult = ceBorrowingProductsWS.ceCalculatedBorrowingRateReq(rr);
		log.info("ceBorrowingProductsWS.ceCalculatedBorrowingRateReq(RateReqParam) response_params:******");
		log.info(JsonUtil.getJackson(rateReqResult));
		return converterRateReturnInfo(rateReqResult);
	}

	@Override
	public ChargePeriodScheduleInfo2 rateCalculate2(CalculatedBorrowingRateInfo rateInfo) {
		ChargePeriodScheduleInfo2 chargePeriodScheduleInfo = new ChargePeriodScheduleInfo2();
		RateReqParam rr = new RateReqParam();
		rr = converterRateInfo(rateInfo);
		try {
			log.info("ceBorrowingProductsWS.ceCalculatedBorrowingRateReq(RateReqParam) request_params:******");
			log.info(JsonUtil.getJackson(rr));
			RateReqResult rateReqResult = ceBorrowingProductsWS.ceCalculatedBorrowingRateReq(rr);
			log.info("ceBorrowingProductsWS.ceCalculatedBorrowingRateReq(RateReqParam) response_params:******");
			log.info(JsonUtil.getJackson(rateReqResult));
			if ("0".equals(rateReqResult.getResultCode())) {
				chargePeriodScheduleInfo = converterRateReturnInfo2(rateReqResult);
			} else {
				chargePeriodScheduleInfo.setResultCode(rateReqResult.getResultCode());
				chargePeriodScheduleInfo.setResultMsg(rateReqResult.getResultMessage());
			}
			return chargePeriodScheduleInfo;
		} catch (Exception e) {
			e.printStackTrace();
			return chargePeriodScheduleInfo;
		}
	}

	/**
	 * 费率计算结果转换
	 * 
	 * @author wyf
	 * @Description:
	 * @param: rateReqResult
	 * @return: List<ChargePeriodScheduleInfo>
	 * @throws:
	 */
	private ChargePeriodScheduleInfo2 converterRateReturnInfo2(RateReqResult rateReqResult) {

		ChargePeriodScheduleInfo2 chargePeriodScheduleInfo = new ChargePeriodScheduleInfo2();
		List<PeriodSchedule> periodSchedules = new ArrayList<PeriodSchedule>();
		PeriodScheduleList psList = rateReqResult.getPeriodScheduleList();
		if (psList != null) {
			periodSchedules = psList.getPeriodSchedule();
		}
		chargePeriodScheduleInfo.setRecvMoney(rateReqResult.getRecvMoney());
		chargePeriodScheduleInfo.setPeriodSchedules(periodSchedules);
		chargePeriodScheduleInfo.seteChargeInfos(rateReqResult.getFrontChargeList());
		chargePeriodScheduleInfo.setResultCode(rateReqResult.getResultCode());
		chargePeriodScheduleInfo.setResultMsg(rateReqResult.getResultMessage());
		return chargePeriodScheduleInfo;

	}

	/**
	 * 费率计算结果转换
	 * 
	 * @author wyf
	 * @Description:
	 * @param: rateReqResult
	 * @return: List<ChargePeriodScheduleInfo>
	 * @throws:
	 */
	private List<ChargePeriodScheduleInfo> converterRateReturnInfo(RateReqResult rateReqResult) {
		List<ChargePeriodScheduleInfo> chargePeriodScheduleInfos = new ArrayList<ChargePeriodScheduleInfo>();
		List<PeriodSchedule> periodSchedules = new ArrayList<PeriodSchedule>();
		PeriodScheduleList psList = rateReqResult.getPeriodScheduleList();
		if (psList != null) {
			periodSchedules = psList.getPeriodSchedule();
		}
		for (PeriodSchedule p : periodSchedules) {
			ChargePeriodScheduleInfo info = new ChargePeriodScheduleInfo();
			info.setPhase(p.getPhase());
			info.setPeriod(p.getPeriod());
			info.setRepayDate(p.getRepayDate().toGregorianCalendar().getTime());
			info.setIrr(p.getIrr());
			info.setAmortizedPrincipal(p.getAmortizedPrincipal());
			info.setAmortizedInterest(p.getAmortizedInterest());
			info.setPeriodCharge(p.getPeriodCharge());
			info.setPeriodMoney(p.getPeriodMoney());

			info.setRecvMoney(rateReqResult.getRecvMoney());
			/*
			 * FrontChargeList frontChargeList = rateReqResult.getFrontChargeList() ;
			 * if(frontChargeList!=null){
			 * List<ChargeInfo> chargeInfos = frontChargeList.getChargeInfo();
			 * if(CommonUtil.isNotEmpty(chargeInfos)){
			 * ChargeInfo ci = chargeInfos.get(0);
			 * info.seteChargeType(ci.getChargeType());
			 * info.seteChargeName(ci.getChargeName());
			 * info.seteCharge(ci.getCharge());
			 * info.seteChargeFormula(ci.getChargeFormula());
			 * }
			 * }
			 */
			PeriodChargeList periodChargeList = p.getPeriodChargeList();
			if (periodChargeList != null) {
				List<ChargeInfo> chargeInfos = periodChargeList.getChargeInfo();
				if (CommonUtil.isNotEmpty(chargeInfos)) {
					ChargeInfo ci = chargeInfos.get(0);
					info.setsChargeType(ci.getChargeType());
					info.setsChargeName(ci.getChargeName());
					info.setsCharge(ci.getCharge());
					info.setsChargeFormula(ci.getChargeFormula());
				}
			}
			chargePeriodScheduleInfos.add(info);
		}
		return chargePeriodScheduleInfos;
	}

	/**
	 * 费率计算参数转换
	 * 
	 * @author wyf
	 * @Description:
	 * @param: rateInfo
	 * @return: RateReqParam
	 * @throws:
	 */
	private RateReqParam converterRateInfo(CalculatedBorrowingRateInfo rateInfo) {
		RateReqParam rr = new RateReqParam();
		GregorianCalendar auditDate = new GregorianCalendar();
		auditDate.setTime(rateInfo.getAuditDate());
		rr.setAuditDate(new XMLGregorianCalendarImpl(auditDate));
		rr.setCatagoryId(rateInfo.getCatagoryId());
		rr.setContractMoney(rateInfo.getContractMoney());
		rr.setDepartmentId(rateInfo.getDepartmentId());

		PaymentTypeConfig p = new PaymentTypeConfig();
		p.setPaymentTypeCode(rateInfo.getPaymentTypeCode());

// PaymentTypeParam ptp = new PaymentTypeParam();
// ptp.setParamName(rateInfo.getParamName());
// ptp.setParamValue(rateInfo.getParamValue());

// com.creditease.rc.app.pdf.PaymentTypeConfig.PaymentTypeParam conf = new com.creditease.rc.app.pdf.PaymentTypeConfig.PaymentTypeParam();
// conf.getPaymentTypeParams().add(ptp);
// p.setPaymentTypeParam(conf);
		PaymentTypeList ptlist = new PaymentTypeList();
		ptlist.getPaymentTypeConfig().add(p);
		rr.setPaymentTypeList(ptlist);
		GregorianCalendar lenderDate = new GregorianCalendar();
		lenderDate.setTime(rateInfo.getAuditDate());
		rr.setLenderDate(new XMLGregorianCalendarImpl(lenderDate));
		rr.setPeriodCount(rateInfo.getPeriodCount());
// rr.setProductInfoId(rateInfo.getProductInfoId());
		GregorianCalendar reqDate = new GregorianCalendar();
		reqDate.setTime(rateInfo.getReqDate());
		rr.setReqDate(new XMLGregorianCalendarImpl(reqDate));
		return rr;
	}

	@Override
	public RepaymentPlanInfo repaymentCalculate(CalculatedBorrowingRateInfo rateInfo) {
		RepaymentPlanInfo repaymentPlanInfo = new RepaymentPlanInfo();
		RepaymentPlanReq repaymentPlanReq = converterRepaymentPlanInfo(rateInfo);
		log.info("ceBorrowingProductsWS.ceRepaymentPlanReq(repaymentPlanReq) request_params:******");
		log.info(JsonUtil.getJackson(repaymentPlanReq));
		RepaymentPlanReqResult repaymentPlanReqResult = ceBorrowingProductsWS.ceRepaymentPlanReq(repaymentPlanReq);
		log.info("ceBorrowingProductsWS.ceRepaymentPlanReq(repaymentPlanReq) response_params:******");
		log.info(JsonUtil.getJackson(repaymentPlanReqResult));
		if ("0".equals(repaymentPlanReqResult.getResultCode())) {
			repaymentPlanInfo = converterRepaymentReturnInfo(repaymentPlanReqResult);
		} else {
			repaymentPlanInfo.setResultCode(repaymentPlanReqResult.getResultCode());
			repaymentPlanInfo.setResultMsg(repaymentPlanReqResult.getResultMessage());
		}
		return repaymentPlanInfo;
	}

	private RepaymentPlanInfo converterRepaymentReturnInfo(RepaymentPlanReqResult repaymentPlanReqResult) {
		RepaymentPlanInfo repaymentPlanInfo = new RepaymentPlanInfo();
		List<PeriodSchedule> periodSchedules = new ArrayList<PeriodSchedule>();
		com.creditease.rc.app.pdf.RepaymentPlanReqResult.PeriodScheduleList psList = repaymentPlanReqResult
				.getPeriodScheduleList();
		if (psList != null) {
			periodSchedules = psList.getPeriodSchedule();
		}
		repaymentPlanInfo.setPeriodSchedules(periodSchedules);
		repaymentPlanInfo.setResultCode(repaymentPlanReqResult.getResultCode());
		repaymentPlanInfo.setResultMsg(repaymentPlanReqResult.getResultMessage());
		return repaymentPlanInfo;
	}

	/**
	 * 产品计算（提前还款）参数转换
	 * 
	 * @author wyf
	 * @Description:
	 * @param: rateInfo
	 * @return: RepaymentPlanReq
	 * @throws:
	 */
	private RepaymentPlanReq converterRepaymentPlanInfo(CalculatedBorrowingRateInfo rateInfo) {
		RepaymentPlanReq rr = new RepaymentPlanReq();
		GregorianCalendar auditDate = new GregorianCalendar();
		auditDate.setTime(rateInfo.getAuditDate());
		rr.setAuditDate(new XMLGregorianCalendarImpl(auditDate));
		rr.setCatagoryId(rateInfo.getCatagoryId());
		rr.setContractMoney(rateInfo.getContractMoney());
		rr.setDepartmentId(rateInfo.getDepartmentId());

		PaymentTypeConfig p = new PaymentTypeConfig();
		p.setPaymentTypeCode(rateInfo.getPaymentTypeCode());

		PaymentTypeParam ptp = new PaymentTypeParam();
		ptp.setParamName(rateInfo.getParamName());
		ptp.setParamValue(rateInfo.getParamValue());

		com.creditease.rc.app.pdf.PaymentTypeConfig.PaymentTypeParam conf = new com.creditease.rc.app.pdf.PaymentTypeConfig.PaymentTypeParam();
		conf.getPaymentTypeParams().add(ptp);
		p.setPaymentTypeParam(conf);
		com.creditease.rc.app.pdf.RepaymentPlanReq.PaymentTypeList ptlist = new com.creditease.rc.app.pdf.RepaymentPlanReq.PaymentTypeList();
		ptlist.getPaymentTypeConfig().add(p);
		rr.setPaymentTypeList(ptlist);
		GregorianCalendar lenderDate = new GregorianCalendar();
		lenderDate.setTime(rateInfo.getAuditDate());
		rr.setLenderDate(new XMLGregorianCalendarImpl(lenderDate));
		rr.setPeriodCount(rateInfo.getPeriodCount());
		rr.setProductInfoId(rateInfo.getProductInfoId());
		return rr;
	}

	@Override
	public Map<String, List<ProductInfo>> queryProByDepart(Long departmentId) {
		log.info("ceBorrowingProductsWS.queryProByDepart(departmentId) request_params:******");
		log.info(JsonUtil.getJackson(departmentId));
		ProListParam proListParam = new ProListParam();
		proListParam.setDepartmentId(departmentId);
		QueryProByDepartResult byDepartResult = ceBorrowingProductsWS.queryProByDepart(proListParam);
		log.info("ceBorrowingProductsWS.queryProByDepart(departmentId) response_params:******");
		log.info(JsonUtil.getJackson(byDepartResult));
		ProInfoList infoList = byDepartResult.getProInfoList();
		List<ProInfo> proInfos = infoList.getProInfo();
		ProInstList instList = byDepartResult.getProInstList();
		List<ProductInst> productInsts = instList.getProductInst();
		ProPayTypeParamList payTypeParamList = byDepartResult.getProPayTypeParamList();
		List<ProPayTypeParam> payTypeParams = payTypeParamList.getProPayTypeParam();
		List<ProductInfo> productInfos = new ArrayList<ProductInfo>();
		Map<String, List<ProductInfo>> map = new HashMap<String, List<ProductInfo>>();
		ProductInfo product = new ProductInfo();
		product.setProductName("请选择");
		product.setProductCategoryId("");
		productInfos.add(product);
		for (ProInfo p : proInfos) {
			Long productId = p.getProductInfoId();
			ProductInfo productInfo = new ProductInfo();
			productInfo.setProductName(p.getProductName());
			productInfo.setProductCategoryId(p.getProductCategoryId());
			productInfo.setInstalments(p.getInstalments());
			for (ProductInst pi : productInsts) {
				if (pi.getProductInfoId().intValue() == productId.intValue()) {
					productInfo.setPaymentTypeCode(pi.getRepaymentType());
					productInfos.add(productInfo);
				}
			}
		}
		map.put("paymentType", productInfos);
		return map;
	}

// @Override
// public PaymentAmountReqResult paymentAmountCalculate(PaymentAmountReqCommon paymentAmountReq, List<ActualPeriodSchedule> apsDtos) {
// PaymentAmountReq req = converterPaymentAmountReq(paymentAmountReq, apsDtos);
// PaymentAmountReqResult result = ceBorrowingProductsWS.cePaymentAmountReq(req);
// return result;
// }

	@Override
	public PaymentAmountResultInfo paymentAmountCalculate(PaymentAmountReqCommon paymentAmountReq,
			List<ActualPeriodScheduleDTO> apsDtos) {
		List<ActualPeriodSchedule> actualPeriodSchedules = converterActualPeriodSchedule(apsDtos);
		PaymentAmountReq req = converterPaymentAmountReq(paymentAmountReq, actualPeriodSchedules);
		PaymentAmountReqResult result = ceBorrowingProductsWS.cePaymentAmountReq(req);
		PaymentAmountResultInfo resultInfo = converterResult(result);
		return resultInfo;
	}

	private PaymentAmountResultInfo converterResult(PaymentAmountReqResult result) {
		PaymentAmountResultInfo amountResultInfo = new PaymentAmountResultInfo();
		if (result != null) {
			amountResultInfo.setResultCode(result.getResultCode());
			amountResultInfo.setResultMsg(result.getResultMessage());
			amountResultInfo.setAheadSchedule(result.getAllAheadSchedule());

			List<ApsListInfo> apsListInfos = new ArrayList<ApsListInfo>();
			com.creditease.rc.app.pdf.AppointSchedule.ApsList apsList = new com.creditease.rc.app.pdf.AppointSchedule.ApsList();
			if (result.getAppointSchedule() != null) {
				apsList = result.getAppointSchedule().getApsList();
			}
			List<AppointPeriodSchedule> periodSchedules = new ArrayList<AppointPeriodSchedule>();
			if (apsList != null) {
				periodSchedules = apsList.getAppointPeriodSchedule();
			}
			for (AppointPeriodSchedule periodSchedule : periodSchedules) {
				ApsListInfo apsListInfo = new ApsListInfo();
				apsListInfo.setPeriod(periodSchedule.getPeriod());
				apsListInfo.setReceivableInterest(periodSchedule.getReceivableInterest());
				apsListInfo.setReceivableMoney(periodSchedule.getReceivableMoney());
				apsListInfo.setReceivablePrincipal(periodSchedule.getReceivablePrincipal());

				com.creditease.rc.app.pdf.AppointPeriodSchedule.OverdueChargeList overdueChargeList = periodSchedule
						.getOverdueChargeList();
				List<AppointChargeInfo> appointChargeInfos = overdueChargeList.getAppointChargeInfo();
				if (CommonUtil.isNotEmpty(appointChargeInfos)) {
					for (AppointChargeInfo appointChargeInfo : appointChargeInfos) {
						if ("FX".equals(appointChargeInfo.getChargeTypeCode())) {
							apsListInfo.setOverdueReceivableChargeFX(appointChargeInfo.getReceivableCharge());
						} else if ("ZNJ".equals(appointChargeInfo.getChargeTypeCode())) {
							apsListInfo.setOverdueReceivableChargeZNJ(appointChargeInfo.getReceivableCharge());
						}
					}
				}
				com.creditease.rc.app.pdf.AppointPeriodSchedule.PeriodChargeList periodChargeList = periodSchedule
						.getPeriodChargeList();
				List<AppointChargeInfo> appointChargeInfos2 = periodChargeList.getAppointChargeInfo();
				if (CommonUtil.isNotEmpty(appointChargeInfos2)) {
					for (AppointChargeInfo appointChargeInfo : appointChargeInfos2) {
						if ("FQFWF".equals(appointChargeInfo.getChargeTypeCode())) {
							apsListInfo.setPeriodReceivableChargeFQFWF(appointChargeInfo.getReceivableCharge());
						}
					}
				}
				apsListInfos.add(apsListInfo);
			}
			amountResultInfo.setApsListInfoList(apsListInfos);
			if (result.getAppointSchedule() != null) {
				amountResultInfo.setTotalReceivableInterest(result.getAppointSchedule().getTotalReceivableInterest());
				amountResultInfo.setTotalReceivableMoney(result.getAppointSchedule().getTotalReceivableMoney());
				amountResultInfo.setTotalReceivablePrincipal(result.getAppointSchedule().getTotalReceivablePrincipal());
				// 提取违约和分期服务费
				TotalOverdueChargeList totalOverdueChargeList = result.getAppointSchedule().getTotalOverdueChargeList();
				if (totalOverdueChargeList != null) {
					List<AppointChargeInfo> appointChargeInfos = totalOverdueChargeList.getAppointChargeInfo();
					if (CommonUtil.isNotEmpty(appointChargeInfos)) {
						for (AppointChargeInfo appointChargeInfo : appointChargeInfos) {
							if ("FX".equals(appointChargeInfo.getChargeTypeCode())) {
								amountResultInfo.setTotalOverdueReceivableChargeFX(appointChargeInfo
										.getReceivableCharge());
							} else if ("ZNJ".equals(appointChargeInfo.getChargeTypeCode())) {
								amountResultInfo.setTotalOverdueReceivableChargeZNJ(appointChargeInfo
										.getReceivableCharge());
							}
						}
					}
				}
				TotalPeriodChargeList periodChargeList = result.getAppointSchedule().getTotalPeriodChargeList();
				if (periodChargeList != null) {
					List<AppointChargeInfo> appointChargeInfos = periodChargeList.getAppointChargeInfo();
					if (CommonUtil.isNotEmpty(appointChargeInfos)) {
						for (AppointChargeInfo appointChargeInfo : appointChargeInfos) {
							if ("FQFWF".equals(appointChargeInfo.getChargeTypeCode())) {
								amountResultInfo.setTotalPeriodReceivableChargeFQFWF(appointChargeInfo
										.getReceivableCharge());
							}
						}
					}
				}
			}
		}
		return amountResultInfo;
	}

	/**
	 * 每期收款记录转换
	 * 
	 * @author wyf
	 * @Description:
	 * @param: apsDtos
	 * @return: List<ActualPeriodSchedule>
	 */
	private List<ActualPeriodSchedule> converterActualPeriodSchedule(List<ActualPeriodScheduleDTO> apsDtos) {
		List<ActualPeriodSchedule> actualPeriodSchedules = new ArrayList<ActualPeriodSchedule>();
		for (ActualPeriodScheduleDTO apsDto : apsDtos) {
			ActualPeriodSchedule aps = new ActualPeriodSchedule();
			aps.setIsOverdue(apsDto.isOverdue());
			aps.setPeriod(apsDto.getPeriod());
			aps.setReceivableInterest(apsDto.getReceivableInterest());
			aps.setReceivableMoney(apsDto.getReceivableMoney());
			aps.setReceivablePrincipal(apsDto.getReceivablePrincipal());
			aps.setReceivedInterest(apsDto.getReceivedInterest());
			aps.setReceivedMoney(apsDto.getReceivedMoney());
			aps.setReceivedPrincipal(apsDto.getReceivedPrincipal());
			GregorianCalendar repayDate = new GregorianCalendar();
			repayDate.setTime(DateUtil.stringConvertDate(apsDto.getRepayDate()));
			aps.setRepayDate(new XMLGregorianCalendarImpl(repayDate));

			OverdueChargeList oChargeList = new OverdueChargeList();
			if (apsDto.getOverdueFxReceivableCharge() != null && apsDto.getOverdueFxReceivedCharge() != null) {
				ActualChargeInfo actualChargeInfoFx = new ActualChargeInfo();
				actualChargeInfoFx.setChargeTypeCode("FX");
				actualChargeInfoFx.setReceivableCharge(apsDto.getOverdueFxReceivableCharge());
				actualChargeInfoFx.setReceivedCharge(apsDto.getOverdueFxReceivedCharge());
				oChargeList.getActualChargeInfo().add(actualChargeInfoFx);
			}
			if (apsDto.getOverdueZnjReceivableCharge() != null && apsDto.getOverdueZnjReceivedCharge() != null) {
				ActualChargeInfo actualChargeInfoZnj = new ActualChargeInfo();
				actualChargeInfoZnj.setChargeTypeCode("ZNJ");
				actualChargeInfoZnj.setReceivableCharge(apsDto.getOverdueZnjReceivableCharge());
				actualChargeInfoZnj.setReceivedCharge(apsDto.getOverdueZnjReceivedCharge());
				oChargeList.getActualChargeInfo().add(actualChargeInfoZnj);
			}
// if(apsDto.getOverdueFQFWFReceivableCharge()!=null && apsDto.getOverdueFQFWFReceivedCharge()!=null){
// ActualChargeInfo actualChargeInfoFQFWF = new ActualChargeInfo();
// actualChargeInfoFQFWF.setChargeTypeCode("FQFWF");
// actualChargeInfoFQFWF.setReceivableCharge(apsDto.getOverdueFQFWFReceivableCharge());
// actualChargeInfoFQFWF.setReceivedCharge(apsDto.getOverdueFQFWFReceivedCharge());
// oChargeList.getActualChargeInfo().add(actualChargeInfoFQFWF);
// }
			if (CommonUtil.isNotEmpty(oChargeList.getActualChargeInfo())) {
				aps.setOverdueChargeList(oChargeList);
			}

			com.creditease.rc.app.pdf.ActualPeriodSchedule.PeriodChargeList pChargeList = new com.creditease.rc.app.pdf.ActualPeriodSchedule.PeriodChargeList();
			if (apsDto.getPeriodFQFWFReceivableCharge() != null && apsDto.getPeriodFQFWFReceivedCharge() != null) {
				ActualChargeInfo actualChargeInfoFQFWF = new ActualChargeInfo();
				actualChargeInfoFQFWF.setChargeTypeCode("FQFWF");
				actualChargeInfoFQFWF.setReceivableCharge(apsDto.getPeriodFQFWFReceivableCharge());
				actualChargeInfoFQFWF.setReceivedCharge(apsDto.getPeriodFQFWFReceivedCharge());
				pChargeList.getActualChargeInfo().add(actualChargeInfoFQFWF);
			}
			if (CommonUtil.isNotEmpty(pChargeList.getActualChargeInfo())) {
				aps.setPeriodChargeList(pChargeList);
			}
			actualPeriodSchedules.add(aps);
		}
		return actualPeriodSchedules;
	}

	/**
	 * PaymentAmountReq参数转换
	 * 
	 * @author wyf
	 * @Description:
	 * @param: paymentAmountReq
	 * @param: apsDto
	 * @return: PaymentAmountReq
	 */
	private PaymentAmountReq converterPaymentAmountReq(PaymentAmountReqCommon paymentAmountReq,
			List<ActualPeriodSchedule> apsDtos) {
		PaymentAmountReq req = new PaymentAmountReq();

		req.setIsALLAhead(paymentAmountReq.isaLLAhead());
		GregorianCalendar appointmentDate = new GregorianCalendar();
		appointmentDate.setTime(DateUtil.stringConvertDate(paymentAmountReq.getAppointmentDate()));
		req.setAppointmentDate(new XMLGregorianCalendarImpl(appointmentDate));
// GregorianCalendar auditDate = new GregorianCalendar();
// auditDate.setTime(paymentAmountReq.getAuditDate());
// req.setAuditDate(new XMLGregorianCalendarImpl(auditDate));
		req.setCatagoryId(paymentAmountReq.getCatagoryId());
		req.setContractMoney(paymentAmountReq.getContractMoney());
		req.setDepartmentId(paymentAmountReq.getDepartmentId());
		GregorianCalendar lenderDate = new GregorianCalendar();
		lenderDate.setTime(DateUtil.stringConvertDate(paymentAmountReq.getLenderDate()));
		req.setLenderDate(new XMLGregorianCalendarImpl(lenderDate));
		req.setPeriodCount(paymentAmountReq.getPeriodCount());
// GregorianCalendar prevCalcDate = new GregorianCalendar();
// prevCalcDate.setTime(paymentAmountReq.getPrevCalcDate());
// req.setPrevCalcDate(new XMLGregorianCalendarImpl(prevCalcDate));
		req.setProductInfoId(paymentAmountReq.getProductInfoId());
		GregorianCalendar reqSysDate = new GregorianCalendar();
		reqSysDate.setTime(DateUtil.stringConvertDate(paymentAmountReq.getReqSysDate()));
		req.setReqSysDate(new XMLGregorianCalendarImpl(reqSysDate));

		ApsList apsList = new ApsList();
		for (ActualPeriodSchedule dto : apsDtos) {
			apsList.getActualPeriodSchedule().add(dto);
		}
		req.setApsList(apsList);
		return req;
	}

	@Override
	public List<ReturnPlan> queryReturnPlanByGroupNum(String groupNum) {
		return (List<ReturnPlan>) returnPlanDao.queryList("RETURNPLAN.queryForGroupNum", groupNum);
	}

	@Override
	/**
	 * 还款方案 参数封装 
	 * @param departmentId
	 * @param ContractMoney
	 * @param lenderDate
	 * @param productInfoId
	 * @param periodCount
	 * @return
	 */
	public RateReqResult advanceCaculateResult(Long departmentId, BigDecimal ContractMoney,
			XMLGregorianCalendarImpl lenderDate, Long productInfoId, Integer periodCount) {
		RateReqParam rr = new RateReqParam();
		// 部门id
		rr.setDepartmentId(departmentId);
		// 合同金额
		rr.setContractMoney(ContractMoney);
		// 请求放款时间
		rr.setLenderDate(lenderDate);
		// 产品id
		rr.setProductInfoId(productInfoId);
		// 分期数
		rr.setPeriodCount(periodCount);
		PaymentTypeList ptlist = new PaymentTypeList();
		PaymentTypeConfig p = new PaymentTypeConfig();
		p.setPaymentTypeCode("");
		ptlist.getPaymentTypeConfig().add(p);
		rr.setPaymentTypeList(ptlist);

		RateReqResult result = this.advanceCaculate(rr);// 费率计算请求-查询借款产品费率，并计算利息和服务费等
		ChargeInfo chargeInfo = null;
		if (result != null && "0".equals(result.getResultCode())) {
			FrontChargeList frontChargeList = result.getFrontChargeList();
			if (frontChargeList != null) {
				List<ChargeInfo> chargeInfos = frontChargeList.getChargeInfo();
				if (chargeInfos.size() > 0) {
					chargeInfo = chargeInfos.get(0);
					if (chargeInfo != null) {
// BigDecimal charge = chargeInfo.getCharge();
// // 服务费
// charge.doubleValue();
// // 实际放款
// CurrencyUtil.sub(amountConfirm.getAmount(),
// charge.doubleValue());
					} else {
						throw new AppBusinessException("产品前期服务费为空");
					}
				} else {
					throw new AppBusinessException("产品前期服务费为空");
				}
			} else {
				throw new AppBusinessException("产品前期服务费为空");
			}
		}

		if (result.getPeriodScheduleList() != null) {
			PeriodScheduleList periodScheduleList = result.getPeriodScheduleList();
			List<PeriodSchedule> periodSchedules = periodScheduleList.getPeriodSchedule();
			List<ReturnPlan> returnPlanList = new ArrayList<ReturnPlan>();
			for (PeriodSchedule periodSchedule : periodSchedules) {
				ReturnPlan returnPlan = new ReturnPlan();
				// 本期还款应还款时间
				returnPlan.setRepaymentDate(periodSchedule.getRepayDate().toGregorianCalendar().getTime());
				// 当前应还本金
				// new BigDecimal(periodSchedule.getAmortizedPrincipal());
				returnPlan.setCurrMonPrincipal(periodSchedule.getAmortizedPrincipal());
				// 当月应还利息
				returnPlan.setCurrMonInterest(periodSchedule.getAmortizedInterest());
				// 当月应还管理费
				returnPlan.setCurrMonManageFree(periodSchedule.getPeriodCharge());
				// 当月应还合计
				returnPlan.setCurrAccountTotal(periodSchedule.getPeriodMoney());
				// 提前还款总额 allHeadMoney
				returnPlan.setAllHeadMoney(periodSchedule.getAllheadMoney());
				// 分期数
				returnPlan.setPeriod(String.valueOf(periodSchedule.getPeriod()));
				if (returnPlan.getCurrAccountTotal().compareTo(new BigDecimal(0)) == 0) {
					returnPlan.setCollectionStatus("3");
				}
				returnPlanList.add(returnPlan);
			}

		} else {
			throw new AppBusinessException("产品前期服务费为空");
		}
		return result;
	}

	/**
	 * 前期服务费率
	 * 
	 * @return
	 */
	public RateReqResult advanceCaculate(RateReqParam rr) {
		log.info("ceBorrowingProductsWS.ceCalculatedBorrowingRateReq(RateReqParam) request_params:******");
		log.info(JsonUtil.getJackson(rr));
		RateReqResult result = ceBorrowingProductsWS.ceCalculatedBorrowingRateReq(rr);// 费率计算请求-查询借款产品费率，并计算利息和服务费等
		if (result != null) {
			log.info("ceBorrowingProductsWS.ceCalculatedBorrowingRateReq(RateReqParam) response_params:******");
			log.info(JsonUtil.getJackson(result));
		}
		return result;
	}

	/**
	 * 前期服务费率 参数封装
	 * 
	 * @param departmentId
	 * @param ContractMoney
	 * @param lenderDate
	 * @param productInfoId
	 * @param periodCount
	 * @return
	 */
	public AdvancePlanVo advanceReturnPlanResult(Long departmentId, BigDecimal ContractMoney,
			XMLGregorianCalendarImpl lenderDate, Long productInfoId, Integer periodCount, String repaymentType) {

		RepaymentPlanReq repaymentPlanReq = new RepaymentPlanReq();
		repaymentPlanReq.setDepartmentId(departmentId);
		repaymentPlanReq.setContractMoney(ContractMoney);
		repaymentPlanReq.setProductInfoId(productInfoId);
		repaymentPlanReq.setPeriodCount(periodCount);
		repaymentPlanReq.setLenderDate(lenderDate);

		PaymentTypeConfig p = new PaymentTypeConfig();
		p.setPaymentTypeCode(repaymentType);
		com.creditease.rc.app.pdf.RepaymentPlanReq.PaymentTypeList ptlist = new com.creditease.rc.app.pdf.RepaymentPlanReq.PaymentTypeList();
		ptlist.getPaymentTypeConfig().add(p);
		repaymentPlanReq.setPaymentTypeList(ptlist);

		AdvancePlanVo advancePlanVo = new AdvancePlanVo();
		RepaymentPlanReqResult result = this.advanceReturnPlan(repaymentPlanReq);// 费率计算请求-查询借款产品费率，并计算利息和服务费等
		ChargeInfo chargeInfo = null;
		if (result != null && "0".equals(result.getResultCode())) {
			FrontChangeList frontChargeList = result.getFrontChangeList();
			if (frontChargeList != null) {
				List<ChargeInfo> chargeInfos = frontChargeList.getChargeInfo();
				if (chargeInfos.size() > 0) {
					chargeInfo = chargeInfos.get(0);
					if (chargeInfo != null) {
						BigDecimal charge = chargeInfo.getCharge();
// // 服务费
						advancePlanVo.setServiceCharge(charge.doubleValue());
// charge.doubleValue();
// // 实际放款
// CurrencyUtil.sub(amountConfirm.getAmount(),
// charge.doubleValue());
						advancePlanVo.setActualAmount(CurrencyUtil.sub(ContractMoney.doubleValue(),
								charge.doubleValue()));
					} else {
						throw new AppBusinessException("产品前期服务费为空");
					}
				} else {
					throw new AppBusinessException("产品前期服务费为空");
				}
			} else {
				throw new AppBusinessException("产品前期服务费为空");
			}
		}

		if (result.getPeriodScheduleList() != null) {
			RepaymentPlanReqResult.PeriodScheduleList periodScheduleList = result.getPeriodScheduleList();
			List<PeriodSchedule> periodSchedules = periodScheduleList.getPeriodSchedule();
			List<ReturnPlan> returnPlanList = new ArrayList<ReturnPlan>();
			for (PeriodSchedule periodSchedule : periodSchedules) {
				ReturnPlan returnPlan = new ReturnPlan();
				// 本期还款应还款时间
				returnPlan.setRepaymentDate(periodSchedule.getRepayDate().toGregorianCalendar().getTime());
				// 当前应还本金
				returnPlan.setCurrMonPrincipal(periodSchedule.getAmortizedPrincipal());
				// 当月应还利息
				returnPlan.setCurrMonInterest(periodSchedule.getAmortizedInterest());
				// 当月应还管理费
				returnPlan.setCurrMonManageFree(periodSchedule.getPeriodCharge());
				// 当月应还合计
				returnPlan.setCurrAccountTotal(periodSchedule.getPeriodMoney());
				// 提前还款总额 allHeadMoney
				returnPlan.setAllHeadMoney(periodSchedule.getAllheadMoney());
				// 分期数
				returnPlan.setPeriod(String.valueOf(periodSchedule.getPeriod()));
				if (returnPlan.getCurrAccountTotal().compareTo(new BigDecimal(0)) == 0) {
					returnPlan.setCollectionStatus("3");
				}
				returnPlanList.add(returnPlan);
			}
			advancePlanVo.setReturnPlanList(returnPlanList);

		} else {
			throw new AppBusinessException("产品前期服务费为空");
		}
		return advancePlanVo;
	}

	/**
	 * 还款计划
	 * 
	 * @return
	 */
	@Override
	public RepaymentPlanReqResult advanceReturnPlan(RepaymentPlanReq repaymentPlanReq) {
		log.info("ceBorrowingProductsWS.ceCalculatedBorrowingRateReq(RateReqParam) request_params:******");
		log.info(JsonUtil.getJackson(repaymentPlanReq));
		RepaymentPlanReqResult result = ceBorrowingProductsWS.ceRepaymentPlanReq(repaymentPlanReq);// 费率计算请求-查询借款产品费率，并计算利息和服务费等
		if (result != null) {
			log.info("ceBorrowingProductsWS.ceCalculatedBorrowingRateReq(RateReqParam) response_params:******");
			log.info(JsonUtil.getJackson(result));
		}
		return result;
	}
	
	@Override
	public List<Map> getProductList(Integer departMentId) {
		List<Map> list = new ArrayList<Map>();
		List<ProInfo> proInfos = null;
		List<ProductInst> productInsts = null;
		ProListParam proListParam = new ProListParam();
		proListParam.setDepartmentId(departMentId);
		QueryProByDepartResult queryProByDepartResult = this.queryProByDepart(proListParam);
		if (null != queryProByDepartResult) {
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
					for (int i = 0; i < proInfos.size(); i++) {
						for (int j = 0; j < productInsts.size(); j++) {
							if (proInfos.get(i).getProductInfoId().equals(productInsts.get(j).getProductInfoId())) {
								Map product = new HashMap();
								product.put("productInfoId", proInfos.get(i).getProductInfoId());
								product.put("productName", proInfos.get(i).getProductName());
								product.put("version", proInfos.get(i).getVersion());
								product.put("instalments", proInfos.get(i).getInstalments());
								product.put("producttypeid", proInfos.get(j).getProductCategoryId()); // 产品分类编号
								product.put("repaymentType", productInsts.get(j).getDefaultRepaymentType());
								product.put("capitalUpperLimit", convertBigDecimal(proInfos.get(i)
										.getCapitalUpperLimit()));
								product.put("capitalLowerLimit", convertBigDecimal(proInfos.get(i)
										.getCapitalLowerLimit()));
								list.add(product);
							}
						}
					}

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
	
	public QueryProByDepartResult queryProByDepart(ProListParam proListParam) {
		log.info("ceBorrowingProductsWS.queryProByDepart(departMentId) request_params:******");
		log.info(JsonUtil.getJackson(proListParam));
		QueryProByDepartResult queryProByDepartResult = ceBorrowingProductsWS.queryProByDepart(proListParam);
		if (null != queryProByDepartResult) {
			log.info("ceBorrowingProductsWS.queryProByDepart(departMentId) response_params:******");
			log.info(JsonUtil.getJackson(queryProByDepartResult));
		}
		return queryProByDepartResult;
	}
	
	
	/**
	 * 
	 * @author 韩大年
	 * @Description: BigDecimal转换字符串
	 * @param decimal
	 * @return String
	 * @version v1.0
	 *          2013-3-22
	 */
	public String convertBigDecimal(BigDecimal decimal) {
		StringBuffer value = new StringBuffer();
		if (null == decimal) {
			value.append("0");
		} else {
			value.append(decimal.toString());
		}
		return value.toString();
	}

	@Override
	public QueryProInfoResult queryProducts() {
		log.debug("ceBorrowingProductsWS.queryProducts() request_params:******");
		log.debug(JsonUtil.getJackson(null));
		QueryProInfoResult queryProducts = ceBorrowingProductsWS.queryProducts();
		log.debug("ceBorrowingProductsWS.queryProducts() response_params:******");
		log.debug(JsonUtil.getJackson(queryProducts));
		return queryProducts;
	}
}
