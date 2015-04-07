package com.creditease.rc.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.creditease.core.security.SpringSecurityUtils;
import com.creditease.rc.dao.IIndexRemindDao;
import com.creditease.rc.domain.CreditApplication;
import com.creditease.rc.service.IIndexRemindService;
import com.creditease.rc.util.CommonUtil;
import com.creditease.rc.util.CurrencyUtil;
import com.creditease.rc.util.DateUtil;
import com.creditease.rc.vo.AuditRemindVo;
import com.creditease.rc.vo.CountInfo;
import com.creditease.rc.vo.CreditapplicationView;
import com.creditease.rc.vo.DepartmentCountInfo;
import com.creditease.rc.vo.IndexRemindVo;
import com.creditease.rc.vo.InvestigationVo;

/**
 * 
 * @author Administrator
 * 
 */
@Service
public class IndexRemindService implements IIndexRemindService {
	@Resource
	private IIndexRemindDao indexRemindDao;

	@Override
	public IndexRemindVo selectRemind(CreditApplication creditApplication) {
		IndexRemindVo indexRemind = new IndexRemindVo();
		List<CreditapplicationView> list = this.selectRemindFromView(creditApplication);
// for(CreditapplicationView c : list){
// System.out.println(c.getAudit_status());
// }
// String loanOfficerId=SpringSecurityUtils.getCurrentUser().getUserId();
// creditApplication.setLoanOfficerId(loanOfficerId);
		this.selectAuditRemind(indexRemind, list);
// this.selectInvestigatioRemind(indexRemind, creditApplication);
// this.selectLoanRegister(indexRemind, creditApplication);
// this.selectFinancePaymentRemind(indexRemind, creditApplication);
// this.selectFinanceReceiveRemind(indexRemind, creditApplication);
// this.selectReceiptRegisterRemind(indexRemind, creditApplication);
// this.selectCountInfo(indexRemind, creditApplication);
        /*等待上传合同提醒*/
        Integer count = indexRemindDao.selectUploadingContractRemind(creditApplication);
        AuditRemindVo vo = new AuditRemindVo();
        vo.setAuditCount(count);
        indexRemind.setLoanRegister(vo);
        return indexRemind;
    }

	/**
	 * 获得审批提醒，额度确认提醒
	 * 
	 * @param indexRemind IndexRemindVo
	 * @param list List<CreditapplicationView>
	 */
	private void selectAuditRemind(IndexRemindVo indexRemind, List<CreditapplicationView> list) {
		int countAudit = 0;
		int countAuditRemindFX = 0;
		int countAuditRemindBS = 0;
		int countAmountConfirm = 0;
		int countInvestigatio = 0;
		int countPaymentFail = 0;
		int countLoanRegister = 0;
		AuditRemindVo auditAmountConfirm = new AuditRemindVo();
		AuditRemindVo auditRemindFX = new AuditRemindVo();
		AuditRemindVo auditRemindBS = new AuditRemindVo();
		AuditRemindVo financePaymentRemind = new AuditRemindVo();
		InvestigationVo investigatio = new InvestigationVo();
		AuditRemindVo loanRegisterRemind = new AuditRemindVo();
		// 统计咨询咨询转了申请的总数！也即是List<CreditapplicationView> 的长度！
		CountInfo consultToApply = new CountInfo();
		consultToApply.setRemindCount(list.size());

		Double sum = 0d;// 放款合同总金额
		int count = 0; // 放款笔数
		Double currentsum = 0d;// 当月放款合同金额总计
		int currentcount = 0;// 当月放款笔数
		CountInfo loanSumAmount = new CountInfo();// 放款对象
		CountInfo currentLoanSumAmount = new CountInfo();
		String current = DateUtil.dateConvertString(new Date(), "yyyy-MM");//
		List<DepartmentCountInfo> departmentCountInfoList = getMonthList();
		Collections.reverse(departmentCountInfoList);
		if (CommonUtil.isNotEmpty(list)) {
			for (CreditapplicationView cv : list) {
				if (CommonUtil.isEmpty(cv.getCredit_investigatio_is_submit())
						|| "0".equals(cv.getCredit_investigatio_is_submit())) {// 新增风险单提醒
					countInvestigatio++;
				}
				if ("01".equals(cv.getAudit_status())) {// 获得已提交未审批的对象
					countAudit++;
				} else if ("04".equals(cv.getAudit_status())) {// 审批通过的条数，即额度确认提醒
					countAmountConfirm++;
				} else if ("24".equals(cv.getAudit_status())
						&& CommonUtil.isNotEmpty(cv.getBusiness_applyaudittable_id())) {// 提醒条数为提交后未审批的+对方已审批自己没审批的和
					countAuditRemindBS++;
				} else if ("24".equals(cv.getAudit_status()) && CommonUtil.isNotEmpty(cv.getRist_applyaudittable_id())) {// 提醒条数为提交后未审批的+对方已审批自己没审批的和
					countAuditRemindFX++;
				} else if (("10".equals(cv.getAudit_status()) && "0".equals(cv.getBusiness_type()))
						|| ("21".equals(cv.getAudit_status()) && "1".equals(cv.getBusiness_type()))) {
					countLoanRegister++;
				} else if (("11".equals(cv.getAudit_status()) || "15".equals(cv.getAudit_status()))
						&& "0".equals(cv.getBusiness_type())) {// 这是对公的做了放宽登记的状态 和 在还款中的状态
					sum = CurrencyUtil.add(sum, Double.valueOf(cv.getAmount_confirm_amount()));
					count++;
					if (CommonUtil.isNotEmpty(cv.getSIGNAGREEMENT_DATE())
							&& cv.getSIGNAGREEMENT_DATE().substring(0, 7).equals(current)) {
						currentsum = CurrencyUtil.add(currentsum, Double.valueOf(cv.getAmount_confirm_amount()));
						currentcount++;
					}
					for (DepartmentCountInfo d : departmentCountInfoList) {
						if (CommonUtil.isNotEmpty(cv.getSIGNAGREEMENT_DATE())
								&& d.getMonthName().equals(cv.getSIGNAGREEMENT_DATE().substring(0, 7))) {
							d.setPaymentBusinessCount(d.getPaymentBusinessCount() + 1);
							d.setPaymentBusinessSum(CurrencyUtil.add(d.getPaymentBusinessSum(),
									Double.valueOf(cv.getAmount_confirm_amount())));
							break;
						}
					}
				} else if ("15".equals(cv.getAudit_status()) && "1".equals(cv.getBusiness_type())) {// 这是对私的做了放款确认的状态
					sum = CurrencyUtil
							.add(sum, Double.valueOf(cv.getAmount_confirm_amount() == null ? "0" : cv
									.getAmount_confirm_amount()));
					count++;
					if (CommonUtil.isNotEmpty(cv.getSIGNAGREEMENT_DATE())
							&& cv.getSIGNAGREEMENT_DATE().substring(0, 7).equals(current)) {
						currentsum = CurrencyUtil.add(currentsum, Double.valueOf(cv.getAmount_confirm_amount()));
						currentcount++;
					}
					for (DepartmentCountInfo d : departmentCountInfoList) {
						if (CommonUtil.isNotEmpty(cv.getSIGNAGREEMENT_DATE())
								&& d.getMonthName().equals(cv.getSIGNAGREEMENT_DATE().substring(0, 7))) {
							d.setPaymentBusinessCount(d.getPaymentBusinessCount() + 1);
							d.setPaymentBusinessSum(CurrencyUtil.add(d.getPaymentBusinessSum(),
									Double.valueOf(cv.getAmount_confirm_amount())));
							break;
						}
					}
				} else if ("16".equals(cv.getAudit_status()) || "20".equals(cv.getAudit_status())) {// 这是还款完成和提前还款完成的状态
					sum = CurrencyUtil.add(sum, Double.valueOf(CommonUtil.isEmpty(cv.getAmount_confirm_amount()) ? "0"
							: cv.getAmount_confirm_amount()));
					count++;
					if (CommonUtil.isNotEmpty(cv.getSIGNAGREEMENT_DATE())
							&& cv.getSIGNAGREEMENT_DATE().substring(0, 7).equals(current)) {
						currentsum = CurrencyUtil.add(currentsum, Double.valueOf(CommonUtil.isEmpty(cv
								.getAmount_confirm_amount()) ? "0" : cv.getAmount_confirm_amount()));
						currentcount++;
					}
					for (DepartmentCountInfo d : departmentCountInfoList) {
						if (CommonUtil.isNotEmpty(cv.getSIGNAGREEMENT_DATE())
								&& d.getMonthName().equals(cv.getSIGNAGREEMENT_DATE().substring(0, 7))) {
							d.setPaymentBusinessCount(d.getPaymentBusinessCount() + 1);
							d.setPaymentBusinessSum(CurrencyUtil.add(d.getPaymentBusinessSum(), Double
									.valueOf(CommonUtil.isEmpty(cv.getAmount_confirm_amount()) ? "0" : cv
											.getAmount_confirm_amount())));
							break;
						}
					}
				}
				if ("3".equals(cv.getPay_finance_finance_status())) {
					countPaymentFail++;
				}

			}
		}
		auditRemindFX.setAuditCount(countAudit + countAuditRemindBS);
		auditRemindBS.setAuditCount(countAudit + countAuditRemindFX);
		auditAmountConfirm.setAuditCount(countAmountConfirm);
		financePaymentRemind.setAuditCount(countPaymentFail);
		investigatio.setAddFxCount(countInvestigatio);
		loanRegisterRemind.setAuditCount(countLoanRegister);
		indexRemind.setAuditRemindFX(auditRemindFX);
		indexRemind.setAuditRemindBS(auditRemindBS);
		indexRemind.setAmountConfirm(auditAmountConfirm);
		indexRemind.setInvestigation(investigatio);
		indexRemind.setFinancePaymentRemind(financePaymentRemind);
		indexRemind.setLoanRegister(loanRegisterRemind);
		loanSumAmount.setRemindCount(count);
		loanSumAmount.setRemindSum(sum);
		indexRemind.setLoanSumAmount(loanSumAmount);
		currentLoanSumAmount.setRemindCount(currentcount);
		currentLoanSumAmount.setRemindSum(currentsum);
		indexRemind.setCurrentLoanSumAmount(currentLoanSumAmount);
		indexRemind.setDepartmentCountInfoList(departmentCountInfoList);

		// 将咨询转了申请的统计信息set近返回的对象中
		indexRemind.setConsultToApply(consultToApply);
	}

	/**
	 * 获得信用及背景调查新增提醒
	 * 
	 * @param indexRemind
	 * @param creditApplication
	 */
	private void selectInvestigatioRemind(IndexRemindVo indexRemind, CreditApplication creditApplication) {
		InvestigationVo investigatio = indexRemindDao.selectInvestigatioRemind(creditApplication);
		investigatio = investigatio == null ? new InvestigationVo() : investigatio;
		indexRemind.setInvestigation(investigatio);
	}

	/**
	 * 获得放款登记提醒
	 * 
	 * @param indexRemind
	 * @param creditApplication
	 */
	private void selectLoanRegister(IndexRemindVo indexRemind, CreditApplication creditApplication) {
		List<AuditRemindVo> loanRegister = indexRemindDao.selectLoanRegister(creditApplication);
		AuditRemindVo loanRegisterRemind = new AuditRemindVo();
		int count = 0;
		for (AuditRemindVo a : loanRegister) {
			count += a.getAuditCount();
		}
		loanRegisterRemind.setAuditCount(count);
		indexRemind.setLoanRegister(loanRegisterRemind);
	}

	/**
	 * 获得财务付款提醒
	 * 
	 * @param indexRemind
	 * @param creditApplication
	 */
	private void selectFinancePaymentRemind(IndexRemindVo indexRemind, CreditApplication creditApplication) {
		AuditRemindVo financePaymentRemind = indexRemindDao.selectFinancePaymentRemind(creditApplication);
		financePaymentRemind = financePaymentRemind == null ? new AuditRemindVo() : financePaymentRemind;
		indexRemind.setFinancePaymentRemind(financePaymentRemind);
	}

	/**
	 * 获得财务收款提醒
	 * 
	 * @param indexRemind
	 * @param creditApplication
	 */
	private void selectFinanceReceiveRemind(IndexRemindVo indexRemind, CreditApplication creditApplication) {
		List<AuditRemindVo> financeReceiveRemind = indexRemindDao.selectFinanceReceiveRemind(creditApplication);
		if (CommonUtil.isNotEmpty(financeReceiveRemind)) {
			for (AuditRemindVo a : financeReceiveRemind) {
				if ("0".equals(a.getAuditStatus())) {
					indexRemind.setFinanceReceiveRemindWill(a);
				} else {
					indexRemind.setFinanceReceiveRemindFail(a);
				}
			}
		}
	}

	/**
	 * 获得收款登记提醒
	 * 
	 * @param indexRemind
	 * @param creditApplication
	 */
	private void selectReceiptRegisterRemind(IndexRemindVo indexRemind, CreditApplication creditApplication) {
		List<AuditRemindVo> receiptRegisterRemind = indexRemindDao.selectReceiptRegisterRemind(creditApplication);
		if (CommonUtil.isNotEmpty(receiptRegisterRemind)) {
			for (AuditRemindVo a : receiptRegisterRemind) {
				if ("1".equals(a.getAuditStatus())) {
					indexRemind.setReceiptRegister1(a);
				} else if ("3".equals(a.getAuditStatus())) {
					indexRemind.setReceiptRegister3(a);
				} else {
					indexRemind.setReceiptRegister7(a);
				}
			}
		}
	}

	/**
	 * 统计信息
	 * 
	 * @param indexRemind
	 * @param creditApplication
	 */
	private void selectCountInfo(IndexRemindVo indexRemind, CreditApplication creditApplication) {
		Map<String, CountInfo> map = indexRemindDao.selectCountInfo(creditApplication);
		CountInfo receiptCountInfo = map.get("receiptCountInfo");
		CountInfo toPublic = map.get("toPublic");
		CountInfo toPrivate = map.get("toPrivate");
		CountInfo allCountInfo = map.get("allCountInfo");
		indexRemind.setReceiptCountInfo(receiptCountInfo);
// indexRemind.setToPublic(toPublic);
// indexRemind.setToPrivate(toPrivate);
		indexRemind.setAllCountInfo(allCountInfo);

	}

	@Override
	public List<DepartmentCountInfo> selectDepartmentCountInfo() {
		Integer departmentId = SpringSecurityUtils.getCurrentUser().getAreaDepartmentId();
		return indexRemindDao.selectDepartmentCountInfo(departmentId);
	}

	@Override
	public List<CreditapplicationView> selectRemindFromView(CreditApplication creditApplication) {
		return indexRemindDao.selectRemindFromView(creditApplication);
	}

	/**
	 * 获得年月集合
	 * 
	 * @return List<DepartmentCountInfo>
	 */
	private List<DepartmentCountInfo> getMonthList() {
		Calendar calendarOnline = Calendar.getInstance();
		calendarOnline.set(2013, 03, 01);
		int onlineYear = calendarOnline.get(Calendar.YEAR);
		int onlineMonth = calendarOnline.get(Calendar.MONTH);
		Calendar calendar = Calendar.getInstance();
		int nowMonth = calendar.get(Calendar.MONTH);
		int nowYear = calendar.get(Calendar.YEAR);
		int amount = (nowYear - onlineYear) * 12 + (nowMonth - onlineMonth) + 1;
		List<DepartmentCountInfo> departmentCountInfoList = new ArrayList<DepartmentCountInfo>();
		for (int i = 0; i < amount; i++) {
			DepartmentCountInfo departmentCountInfo = new DepartmentCountInfo();
			// String t = i < 10 ? "0" + i : i + "";
			Calendar calendarf = Calendar.getInstance();
			calendarf.set(2013, 03, 01);
			calendarf.set(calendarf.get(Calendar.YEAR), calendarf.get(Calendar.MONTH) + i, 01);
			int temp = calendarf.get(Calendar.MONTH) + 1;
			String tmonth = temp < 10 ? "0" + temp : temp + "";
			String monthName = calendarf.get(Calendar.YEAR) + "-" + tmonth;
			departmentCountInfo.setMonthName(monthName);
			departmentCountInfoList.add(departmentCountInfo);
		}
		return departmentCountInfoList;
	}

	@Override
	public int conSultCount(Map<String, String> querySultMap) {
		// TODO Auto-generated method stub
		return indexRemindDao.conSultCount(querySultMap);
	}
}
