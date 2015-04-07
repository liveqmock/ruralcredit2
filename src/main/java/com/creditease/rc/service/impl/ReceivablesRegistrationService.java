package com.creditease.rc.service.impl;

import java.math.BigDecimal;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Service;

import com.creditease.core.security.SpringSecurityUtils;
import com.creditease.core.security.User;
import com.creditease.rc.app.pdf.AllAheadSchedule;
import com.creditease.rc.dao.ICreditApplicationDAO;
import com.creditease.rc.dao.IReceivedRecordDao;
import com.creditease.rc.dao.IReturnPlanDao;
import com.creditease.rc.dao.IRuralReturnDisDao;
import com.creditease.rc.domain.AccountInfo;
import com.creditease.rc.domain.ActualPeriodScheduleDTO;
import com.creditease.rc.domain.BorrowerServiceApp;
import com.creditease.rc.domain.CreditApplication;
import com.creditease.rc.domain.FinanceMoney;
import com.creditease.rc.domain.PaymentAmountReqCommon;
import com.creditease.rc.domain.PaymentAmountResultInfo;
import com.creditease.rc.domain.ReceivedRecord;
import com.creditease.rc.domain.ReturnPlan;
import com.creditease.rc.domain.RuralReturnDis;
import com.creditease.rc.framework.exception.AppBusinessException;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.service.IAmountConfirmService;
import com.creditease.rc.service.IBorrowingProductsService;
import com.creditease.rc.service.ICreditApplicationService;
import com.creditease.rc.service.IFinanceMoneyService;
import com.creditease.rc.service.IFinanceReceiveService;
import com.creditease.rc.service.IOperateLogService;
import com.creditease.rc.service.IReceivablesRegistrationService;
import com.creditease.rc.service.IRepaymentPlanService;
import com.creditease.rc.service.IReturnPlanService;
import com.creditease.rc.util.CommonUtil;
import com.creditease.rc.util.CurrencyUtil;
import com.creditease.rc.util.DateUtil;
import com.creditease.rc.vo.OperateLogBusinessStruct;

/**
 * 
 * @author haoqiang
 * 
 */
@Service
public class ReceivablesRegistrationService extends QuartzJobBean implements IReceivablesRegistrationService {

	@Resource
	private IReturnPlanDao returnPlanDao;

	@Resource
	private IRuralReturnDisDao ruralReturnDisDao;

	@Resource
	private IReceivedRecordDao receivedRecordDao;

	@Resource
	private IRepaymentPlanService repaymentPlanService;

	@Resource
	private IFinanceReceiveService financeReceiveService;
	@Resource
	private IReturnPlanService returnPlanService;
	@Resource
	private IFinanceMoneyService financeMoneyService;
	@Resource
	private ICreditApplicationService creditApplicationService;
	@Resource
	private IOperateLogService operateLogService;
	@Resource
	private ICreditApplicationDAO creditApplicationDAO;
	@Resource
	private IBorrowingProductsService borrowingProductsService;
	@Resource
	private IAmountConfirmService amountConfirmService;

	// ★━━━━━━━━━━━━━━━━━━━收款记录配相关━━━━━━━━━━━━━━━━━━━━━━━━★
	@Override
	public boolean saveReceivedRecord(ReceivedRecord passReceivedRecord) {
		// passReceivedRecord
		OperateLogBusinessStruct operateLogBusinessStruct = new OperateLogBusinessStruct();
		User user = SpringSecurityUtils.getCurrentUser();
		passReceivedRecord.setOperatorId(Integer.parseInt(user.getUserId()));
		passReceivedRecord.setOperatorName(user.getName_zh());
		operateLogBusinessStruct.setCreditapplicationId((passReceivedRecord.getCreditapplicationId()).longValue());
		operateLogBusinessStruct.setFunctionCode("400010");
		operateLogBusinessStruct.setReceivedAmount(passReceivedRecord.getReceivedAmount().toString());
		operateLogBusinessStruct.setReceivedTime(dateToString(passReceivedRecord.getReceivedTime(), "MEDIUM"));
		// TODO Auto-generated method stub
		receivedRecordDao.insert("RECEIVEDRECORD.dynamicInsert", passReceivedRecord);
		operateLogService.insert(operateLogBusinessStruct);
		return true;
	}

	@Override
	public int updateReceivedStatus(Integer receivedRecordId) {
		// TODO Auto-generated method stub
		ReceivedRecord tempReceivedRecord = new ReceivedRecord();
		tempReceivedRecord.setReceivedRecordId(receivedRecordId);
		tempReceivedRecord.setReceivedStatus("1");
		return receivedRecordDao.update("RECEIVEDRECORD.dynamicUpdate", tempReceivedRecord);
	}

	@Override
	public boolean batchUpdateReceivedStatus(List<Integer> receivedRecordIdList, String status) {
		// TODO Auto-generated method stub
		List<ReceivedRecord> receivedRecordList = new ArrayList<ReceivedRecord>();
		for (Integer i : receivedRecordIdList) {
			ReceivedRecord receivedRecord = new ReceivedRecord();
			receivedRecord.setReceivedRecordId(i);
			receivedRecord.setReceivedStatus(status);
			receivedRecordList.add(receivedRecord);
		}
		try {
			receivedRecordDao.batchUpdate("RECEIVEDRECORD.dynamicUpdate", receivedRecordList);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public List<ReceivedRecord> searchReceivedRecord(ReceivedRecord passReceivedRecord) {
		// TODO Auto-generated method stub
		return (List<ReceivedRecord>) receivedRecordDao.queryList("RECEIVEDRECORD.dynamicSelect", passReceivedRecord);
	}

	@Override
	public boolean updateAutomaticallyDeducted(ReturnPlan passReturnPlan) {
		// TODO Auto-generated method stub
		// System.out.println("查询在当前时间可以进行预约登记的还款计划");
		List<ReturnPlan> list = (List<ReturnPlan>) returnPlanDao.queryList("RETURNPLAN.dynamicSelect", passReturnPlan);
		// System.out.println("这样的还款计划共有=======" + list.size());
		// System.out.println("即将进入returnAutoList方法来筛选出符合预约条件的还款计划List");
		List<ReturnPlan> resultReturnPlanList = returnAutoList(list);
		List<ReceivedRecord> insertList = new ArrayList<ReceivedRecord>();
		List<Integer> iDList = new ArrayList<Integer>();
		for (ReturnPlan r : resultReturnPlanList) {
			ReceivedRecord tempReceivedRecord = new ReceivedRecord();
			Date d = new Date();
			Timestamp da = new Timestamp(d.getTime());
			// da = d.getTime()
			// d.getTime() = getTimestamp(d);

			// 用来查询收款成功但还没有分配的收款登记
			ReceivedRecord linshiReceivedRecord = new ReceivedRecord();
			// linshiReceivedRecord.setCreditapplicationId(r.getCreditapplicationId());
			linshiReceivedRecord.setReceivedStatus("1");
			linshiReceivedRecord.setHistoryFlag("F");
			List<ReceivedRecord> linshiReceivedRecordList = (List<ReceivedRecord>) receivedRecordDao.queryList(
					"RECEIVEDRECORD.dynamicSelect", linshiReceivedRecord);
			// 如果他不是空就去取登记的金额
			Double setReceivedAmount = 0.00;
			if (CommonUtil.isNotEmpty(linshiReceivedRecordList)) {
				for (ReceivedRecord h : linshiReceivedRecordList) {
					setReceivedAmount = CurrencyUtil.add(setReceivedAmount, h.getReceivedAmount());
				}
			}

// Double getCurrAccountTotal = r.getCurrAccountTotal();// 当月应还款总额
// Double getCurrMonPayTotal = r.getCurrMonPayTotal();// 当月实收还款总额
// Double getCurrReduceTotal = r.getCurrReduceTotal();// 当月减免总额
// Double getReceEarlyDamages = r.getReceEarlyDamages();// 当月应收一次性提前还款违约金
// Double getPaidEarlyDamages = r.getPaidEarlyDamages();// 当月实收一次性提前还款违约金

// Double actualReceivableTotal = CurrencyUtil.add(CurrencyUtil.add(getCurrAccountTotal, getCurrReduceTotal),
// getReceEarlyDamages);// 当月实际应还款总额
// Double currNotTotal = CurrencyUtil.sub(
// CurrencyUtil.sub(CurrencyUtil.sub(actualReceivableTotal, getCurrMonPayTotal), getPaidEarlyDamages),
// setReceivedAmount);// 当月未还款差值

			// tempReceivedRecord.setBorrowerServiceAppId(null);
			tempReceivedRecord.setCapitalSource("A");
			// tempReceivedRecord.setCreditapplicationId(r.getCreditapplicationId());
			// tempReceivedRecord.setHistoryFlag(null);
			tempReceivedRecord.setOperateDate(da);
			// tempReceivedRecord.setOperatorId(null);
			// tempReceivedRecord.setOperatorName(null);
			// tempReceivedRecord.setReceivedAmount(currNotTotal);
			// tempReceivedRecord.setReceivedRecordId(null);
			// tempReceivedRecord.setReceivedStatus(null);
			tempReceivedRecord.setReceivedTime(d);
			// tempReceivedRecord.setReceivedType(null);
			// 重要包括卡自动预约和set操作人姓名和ID
			Integer aId = (Integer) creditApplicationDAO.queryUnique("CREDITAPPLICATION.selectCard",
					r.getCreditapplicationId());
			tempReceivedRecord.setAccountInfoId(aId);
			insertList.add(tempReceivedRecord);
			// iDList.add(r.getCreditapplicationId());
		}
		if (CommonUtil.isEmpty(iDList)) {
			return true;
		} else {
			List<ReceivedRecord> resultList = (List<ReceivedRecord>) receivedRecordDao.queryList(
					"CREDITAPPLICATION.selectByIntegerList", iDList);
			// System.out.println("mmmmmmmmmmmiDList.size============" + iDList.size());
			for (Integer i : iDList) {
				// System.out.println("主键============" + i);
			}
			// System.out.println("dddddddddddresultList.size============" + resultList.size());
			for (ReceivedRecord r : resultList) {
				// System.out.println("艾迪为=========" + r.getOperatorId());
			}
			for (ReceivedRecord k : insertList) {
				Integer kGetCreditapplicationId = k.getCreditapplicationId();
				// System.out.println("kGetCreditapplicationId==========" + kGetCreditapplicationId);
				for (ReceivedRecord j : resultList) {
					Integer jGetCreditapplicationId = j.getCreditapplicationId();
					// System.out.println("jGetCreditapplicationId==========" + jGetCreditapplicationId);
					if (kGetCreditapplicationId.equals(jGetCreditapplicationId)) {
						k.setOperatorId(j.getOperatorId());
						k.setOperatorName(j.getOperatorName());
					}
				}
			}
			receivedRecordDao.batchInsert("RECEIVEDRECORD.dynamicInsert", insertList);
			List<Integer> rIdList = new ArrayList<Integer>();
			for (ReceivedRecord r : insertList) {
				rIdList.add(r.getReceivedRecordId());
			}
			financeReceiveService.financialReceive(rIdList, null);
			return true;
		}
	}

	/**
	 * 得到能进行自动预约的还款计划List
	 * 
	 * @param resultReturnPlanList 还款计划List
	 * @return 能进行自动预约的还款计划List
	 */
	private List<ReturnPlan> returnAutoList(List<ReturnPlan> resultReturnPlanList) {
		List<ReturnPlan> returnAutoList = new ArrayList<ReturnPlan>();
		// System.out.println("循环来取出每一个还款计划去判断符不符合预约标准");
		for (ReturnPlan r : resultReturnPlanList) {
			boolean s = checkAuto(r);
			if (s == true) {
				returnAutoList.add(r);
			}
		}

		return returnAutoList;
	}

	/**
	 * 检测这笔还款计划能否进行自动预约
	 * 
	 * @param pReturnPlan 传入的还款计划
	 * @return 是否能进行自动预约
	 */
	private boolean checkAuto(ReturnPlan pReturnPlan) {
		// System.out.println("进入checkAuto的还款计划主键为=====" + pReturnPlan.getReturnPlanId());
		// Integer getCreditapplicationId = pReturnPlan.getCreditapplicationId();
		// System.out.println("得到CreditapplicationId为=========" + getCreditapplicationId);
		// System.out.println("===用他去查这笔业务有没有在途的收款登记===");
// List<ReceivedRecord> receivedRecordList = (List<ReceivedRecord>) receivedRecordDao.queryList(
// "RECEIVEDRECORD.selectForCancel", getCreditapplicationId);
		// System.out.println("这里可能有问题！在途的收款条数为：==============" + receivedRecordList.size());
// if (CommonUtil.isEmpty(receivedRecordList)) {
// // 如果List为空则证明没有在途的收款登记
// // System.out.println("如果List为空则证明没有在途的收款登记");
// Date thisD = pReturnPlan.getRepaymentDate();
// // System.out.println("得到当前传入的还款计划的RepaymentDate=======" + thisD);
// // 创建用来查询的还款计划
// ReturnPlan sReturnPlan = new ReturnPlan();
// //sReturnPlan.setCreditapplicationId(getCreditapplicationId);
// List<ReturnPlan> resultReturnPlanList = (List<ReturnPlan>) returnPlanDao.queryList(
// "RETURNPLAN.dynamicSelect", sReturnPlan);
// // System.out.println("resultReturnPlanList为这个整个的还款计划");
// int index = 0;
// for (int i = 0; i < resultReturnPlanList.size(); i++) {
// ReturnPlan tReturnPlan = resultReturnPlanList.get(i);
// Date tD = tReturnPlan.getRepaymentDate();
// if (thisD.compareTo(tD) <= 0) {
// // 属于这个还款月
// index = i;
// break;
// }
// }
// // System.out.println("找到这个还款月的index==========" + index);
// if ((index - 1) < 0) {
// // 证明没有上个月判断这个月
// // System.out.println("证明没有上个月判断这个月");
// String nCollectionStatus = resultReturnPlanList.get(index).getCollectionStatus();
// if ("0".equals(nCollectionStatus) || "2".equals(nCollectionStatus)) {
// // 这个月没还清
// // System.out.println("这个月没还清");
// return true;
// } else {
// // 这个月已还清
// // System.out.println("这个月已还清");
// return false;
// }
// } else {
// // 有上个月
// // System.out.println("有上个月");
// String bCollectionStatus = resultReturnPlanList.get(index - 1).getCollectionStatus();
// if ("0".equals(bCollectionStatus) || "2".equals(bCollectionStatus)) {
// // 上个月没还清
// // System.out.println("上个月没还清");
// return false;
// } else {
// // 判断这个月
// // System.out.println("判断这个月");
// String nCollectionStatus = resultReturnPlanList.get(index).getCollectionStatus();
// if ("0".equals(nCollectionStatus) || "2".equals(nCollectionStatus)) {
// // 这个月没还清
// // System.out.println("这个月没还清");
// return true;
// } else {
// // 这个月已还清
// // System.out.println("这个月已还清");
// return false;
// }
// }
//
// }
// } else {
// // 有在途的收款登记
// // System.out.println("有在途的收款登记");
// return false;
// }
		return false;
	}

	/**
	 * 日期加时分秒
	 * 
	 * @param date Date类型的数据
	 * @return 加时分秒后的数据
	 */
	public java.sql.Timestamp getTimestamp(Date date) {
		return new java.sql.Timestamp(date.getTime());
	}

	// ★━━━━━━━━━━━━━━━━━━━收款记录配相关END━━━━━━━━━━━━━━━━━━━━━★

	// ★━━━━━━━━━━━━━━━━━━━━还款计划相关━━━━━━━━━━━━━━━━━━━━━━━━★

	@Override
	public boolean batchInsertReceivablesHistory(List<ReturnPlan> passReturnPlanList) {
		// TODO Auto-generated method stub
		try {
			returnPlanDao.batchInsert("RETURNPLAN.dynamicInsert", passReturnPlanList);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public int batchUpdateReceivablesHistory(List<ReturnPlan> passReturnPlanList) {
		// TODO Auto-generated method stub
		try {
			return returnPlanDao.batchUpdate("RETURNPLAN.dynamicUpdate", passReturnPlanList);
		} catch (Exception e) {
			return 0;
		}
	}

	@Override
	public Pagination queryReceivablesHistoryDataGrid(Pagination pagination, Integer creditapplicationId) {
		// TODO Auto-generated method stub
		ReturnPlan reutrnPlan = new ReturnPlan();
		// reutrnPlan.setCreditapplicationId(creditapplicationId);
		return returnPlanDao.queryForPaginatedList("RETURNPLAN.queryForShow", "RETURNPLAN.countForShow", reutrnPlan,
				pagination.getStartResult(), pagination.getPageSize());
	}

	@Override
	public int deleteReceivablesHistory(Integer creditapplicationId) {
		// TODO Auto-generated method stub
		try {
			return returnPlanDao.delete("RETURNPLAN.deleteReturnPlanByCreditapplicationId", creditapplicationId);
		} catch (Exception e) {
			return 0;
		}
	}

	@Override
	public boolean autoSwitch(Integer creditapplicationId, String param) {
		// TODO Auto-generated method stub
		ReturnPlan returnPlan = new ReturnPlan();
		// returnPlan.setCreditapplicationId(creditapplicationId);
		List<ReturnPlan> returnPlanList = returnPlanService.selectList(returnPlan);
		if (param == "0") {
			for (ReturnPlan r : returnPlanList) {
				r.setAutoSwitch(param);
			}
		} else if (param == "1") {
			for (ReturnPlan r : returnPlanList) {
				r.setAutoSwitch(param);
			}
		}
		try {
			this.batchUpdateReceivablesHistory(returnPlanList);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// ★━━━━━━━━━━━━━━━━━━还款计划相关END━━━━━━━━━━━━━━━━━━━━━━★

	// ★━━━━━━━━━━━━━━━━━━━实收分配相关━━━━━━━━━━━━━━━━━━━━━━━━★
	@Override
	public boolean batchInsertDistribution(List<RuralReturnDis> passRuralReturnDisList) {
		// TODO Auto-generated method stub
		try {
			ruralReturnDisDao.batchInsert("RURALRETURNDIS.insert", passRuralReturnDisList);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public int batchUpdateDistribution(List<RuralReturnDis> passRuralReturnDisList) {
		// TODO Auto-generated method stub
		try {
			return ruralReturnDisDao.batchUpdate("RURALRETURNDIS.update", passRuralReturnDisList);
		} catch (Exception e) {
			return 0;
		}
	}

	@Override
	public Pagination queryDistributionDataGrid(Pagination pagination, Integer returnPlanId) {
		// TODO Auto-generated method stub
		Pagination resultPagination = new Pagination();
		resultPagination = ruralReturnDisDao.queryForPaginatedList(
				"RURALRETURNDIS.selectRuralReturnDisByDistributionId",
				"RURALRETURNDIS.countRuralReturnDisByDistributionId", returnPlanId, pagination.getStartResult(),
				pagination.getPageSize());
		return resultPagination;
	}

	@Override
	public int deleteDistribution(Integer returnPlanId) {
		// TODO Auto-generated method stub
		try {
			return ruralReturnDisDao.delete("RURALRETURNDIS.deleteRuralReturnDisByReturnPlanId", returnPlanId);
		} catch (Exception e) {
			return 0;
		}
	}

	// ★━━━━━━━━━━━━━━━━━━━实收分配相关END━━━━━━━━━━━━━━━━━━━━━━━━★
	// ★━━━━━━━━━━━━━━━━━━━━分配算法相关━━━━━━━━━━━━━━━━━━━━━━━━━━★
	/**
	 * 总体思路：先把所有类型为收款成功的登记都放到一个List中
	 * 接下来再对List中的每一笔登记进行操作
	 * 
	 * @return 通过返回不同的数字 来代表不同的结果
	 *         0-代表没有收款成功的登记。-1代表分配失败了。>0代表收款成功了并返回成功的条数。
	 */
	@Override
	public Integer saveDistribution() {
		// TODO Auto-generated method stub
		// 新建一个resultReceivedRecordList用来盛放需要分配的所有登记
		List<ReceivedRecord> resultReceivedRecordList = new ArrayList<ReceivedRecord>();
		// 进入queryByReceivedStatus()方法
		resultReceivedRecordList = queryByReceivedStatus();
		// System.out.println("resultReceivedRecordList的长度为：" + resultReceivedRecordList.size());
		// System.out.println("也就是说收款登记记录的条数为：" + resultReceivedRecordList.size());
		// 如果resultReceivedRecordList为空那么说明没有需要分配的登记
		if (CommonUtil.isNotEmpty(resultReceivedRecordList)) {
			// System.out.println("接下来就要进入到judgment算法了");
			// 如果不为空则进入 judgment(resultReceivedRecordList)方法来进行分配
			// 返回为true则所有的分配成功
			boolean isSuccess = judgment(resultReceivedRecordList);
			if (isSuccess == true) {
				return resultReceivedRecordList.size();
			} else {
				return -1;
			}
		} else {
			return 0;
		}
	}

	/**
	 * 查询状态为收款成功的方法
	 * 
	 * @return 收款记录列表
	 */
	private List<ReceivedRecord> queryByReceivedStatus() {
		// TODO Auto-generated method stub
		ReceivedRecord tempReceivedRecord = new ReceivedRecord();
		// 1代表收款状态为 成功
		tempReceivedRecord.setReceivedStatus("1");
		tempReceivedRecord.setHistoryFlag("F");
		List<ReceivedRecord> resultReceivedRecordList = (List<ReceivedRecord>) receivedRecordDao.queryList(
				"RECEIVEDRECORD.dynamicSelect", tempReceivedRecord);
		return resultReceivedRecordList;
	}

	/**
	 * 传入paramReceivedRecordList 将这个List中的每笔收款登记拆出来进行分配
	 * 
	 * @param paramReceivedRecordList 传入的ReceivedRecordList
	 * @return 是否全部分配成功
	 */
	private boolean judgment(List<ReceivedRecord> paramReceivedRecordList) {
		boolean isSuccess = false;
		for (int i = 0; i < paramReceivedRecordList.size(); i++) {
			ReceivedRecord tempReceivedRecord = new ReceivedRecord();
			// 对每一笔收款登记进行分配
			tempReceivedRecord = paramReceivedRecordList.get(i);
			// System.out.println("在judgment中从所有的还款记录中对每一个还款记录进行实收分配其中ID为：" + tempReceivedRecord.getReceivedRecordId()
			// + "的记录传入到allotReceivedRecord方法中");
			boolean isAllotSuccess = false;
			// 将一笔收款登记传入到allotReceivedRecord中进行分配
			isAllotSuccess = allotReceivedRecord(tempReceivedRecord);
			// 如果分配成功isAllotSuccess置成ture失败的话则置成false并break
			if (isAllotSuccess == true) {
				isSuccess = true;
			} else {
				isSuccess = false;
				break;
			}
		}
		return isSuccess;
	}

	/**
	 * 对一笔收款登记进行分配 返回布尔类型
	 * 
	 * @param paramReceivedRecord 传入的收款登记对象
	 * @return 是否分配成功
	 */
	private boolean allotReceivedRecord(ReceivedRecord paramReceivedRecord) {
		// System.out.println("进入到allotReceivedRecord方法中了将要对这笔记录进行分配");
		// 通过传入的paramReceivedRecord收款登记得到getCreditapplicationId外键
		Integer getCreditapplicationId = paramReceivedRecord.getCreditapplicationId();
		// 收款登记类型 0正常登记 1一次性还款登记
		String getReceivedType = paramReceivedRecord.getReceivedType();
		// System.out.println("getCreditapplicationId为：" + getCreditapplicationId);
		// 创建一个用于查询还款登记所对应的还款计划的returnplan对象
		ReturnPlan forSelectReturnPlan = new ReturnPlan();
		// 将外键set近new的ReturnPlan中
		// forSelectReturnPlan.setCreditapplicationId(getCreditapplicationId);
		// 创建一个临时的还款计划List
		List<ReturnPlan> tempReturnPlanList = new ArrayList<ReturnPlan>();
		// 创建一个用于分配的划款计划List
		List<ReturnPlan> allotReturnPlanList = new ArrayList<ReturnPlan>();
		// System.out.println("将对getCreditapplicationId为：" + getCreditapplicationId + "的还款计划进行查询");
		// 将还款计划作为查询对象进行查询
		// 将查询结果放到tempReturnPlanList中
		tempReturnPlanList = (List<ReturnPlan>) returnPlanDao
				.queryList("RETURNPLAN.dynamicSelect", forSelectReturnPlan);
		// System.out.println("tempReturnPlanList.size=" + tempReturnPlanList.size());
		// System.out.println("也就是说还款计划的条数为" + tempReturnPlanList.size());
		// 将tempReturnPlanList还款计划中每一期拆出来进行过滤
		for (ReturnPlan r : tempReturnPlanList) {
			// 得到当期期的还款状态
			String getCollectionStatus = r.getCollectionStatus();
			// 将还款状态为未还款或者部分还款的挑出来放到allotReturnPlanList中
			if ("0".equals(getCollectionStatus.trim()) || "2".equals(getCollectionStatus.trim())) {
				// System.out.println("主键为：" + r.getReturnPlanId() + "的还款计划放入到未还款或者部分还款的还款计划列表中");
				allotReturnPlanList.add(r);
			}
		}

		// 如果收款登记是“1”即提前收款登记那么将对allotReceivedAmount进行提前收款登记的减免
		if ("1".equals(getReceivedType.trim())) {
			// earlyReceviedList方法用来区分不同的提前还款类型需要进行不同的减免
			Date getReceivedTime = paramReceivedRecord.getReceivedTime();
			earlyReceviedList(allotReturnPlanList, tempReturnPlanList, paramReceivedRecord);
		}
		// System.out.println("即将进入allotReceivedAmount方法");
		// 将整个paramReceivedRecord还款计划和allotReturnPlanList需要还的期数传入到allotReceivedAmount(paramReceivedRecord, allotReturnPlanList)方法中
		allotReceivedAmount(paramReceivedRecord, allotReturnPlanList);
		paramReceivedRecord.setReceivedStatus("3");
		receivedRecordDao.update("RECEIVEDRECORD.dynamicUpdate", paramReceivedRecord);
		// 收款完成receivablesEnd
		receivablesEnd(paramReceivedRecord);
		return true;
	}

	/**
	 * 将需要分配的登记和需要进行分配的还款计划进行匹配和分配
	 * 
	 * @param paramReceivedRecord 需要分配的登记
	 * @param allotReturnPlanList 相对应的还款计划
	 * @return 是否成功
	 */
	@Override
	public boolean allotReceivedAmount(ReceivedRecord paramReceivedRecord, List<ReturnPlan> allotReturnPlanList) {
		// 得到还款登记的主键ID
		Integer getReceivedRecordId = paramReceivedRecord.getReceivedRecordId();

		// 得到还款登记的登记类型
		// String getReceivedType = paramReceivedRecord.getReceivedType();
		// 得到还款登记中登记的还款金额
		Double getReceivedAmount = paramReceivedRecord.getReceivedAmount();
		// 创建一个需要更新的还款计划List
		List<ReturnPlan> updateReturnPlanList = new ArrayList<ReturnPlan>();
		// 创建一个正常的还款计划List
		// List<ReturnPlan> normalReturnPlanList = new ArrayList<ReturnPlan>();
		// System.out.println("将对ID为" + getReceivedRecordId + "的收款登记记录进行分配");
		// System.out.println("他的收款金额为" + getReceivedAmount);
		// 创建一个实收分配List用于批量插入
		List<RuralReturnDis> insertRuralReturnDis = new ArrayList<RuralReturnDis>();
		for (ReturnPlan r : allotReturnPlanList) {
// Double getCurrAccountTotal = r.getCurrAccountTotal();// 当月应还款总额
// Double getCurrMonPayTotal = r.getCurrMonPayTotal();// 当月实收还款总额
// Double getCurrReduceTotal = r.getCurrReduceTotal();// 当月减免总额
// Double getReceEarlyDamages = r.getReceEarlyDamages();// 当月应收一次性提前还款违约金
// Double getPaidEarlyDamages = r.getPaidEarlyDamages();// 当月实收一次性提前还款违约金

// Double actualReceivableTotal = CurrencyUtil.add(CurrencyUtil.add(getCurrAccountTotal, getCurrReduceTotal),
// getReceEarlyDamages);// 当月实际应还款总额
// Double currNotTotal = CurrencyUtil.sub(CurrencyUtil.sub(actualReceivableTotal, getCurrMonPayTotal),
// getPaidEarlyDamages);// 当月未还款差值
			// System.out.println("当月应还款总额:" + getCurrAccountTotal);
			// System.out.println("当月实收还款总额:" + getCurrMonPayTotal);
			// System.out.println("当月实际应还款总额:" + actualReceivableTotal);
			// System.out.println("当月未还款差值:" + currNotTotal);
			// 新建一个变量 toBeDistributed = null 用来当作分配金额
			Double toBeDistributed = null;

// if (currNotTotal > 0.00) {
// // 如果登记金额大于当月实际应还差值
// if (getReceivedAmount.doubleValue() > currNotTotal.doubleValue()) {
// // 那么被分配金额就等未还款差值金额
// toBeDistributed = currNotTotal;
// // System.out.println("如果待分配的金额大于当期未还款差值那么分配到这一期的toBeDistributed为：" + toBeDistributed);
// // 之后收款金额就等于减去被分配金额后的金额
// getReceivedAmount = CurrencyUtil.sub(getReceivedAmount, toBeDistributed);
// // System.out.println("即将进入returnRuralReturnDis方法");
// // 将被分配金额和当期的还款计划传入到returnRuralReturnDis(toBeDistributed, r)方法中
// // RuralReturnDis ruralReturnDis = new RuralReturnDis();
// r.setCollectionStatus("1");
// RuralReturnDis resultRuralReturnDis = returnRuralReturnDis(toBeDistributed, paramReceivedRecord, r);
// insertRuralReturnDis.add(resultRuralReturnDis);
// updateReturnPlanList.add(r);
// Integer getReturnPlanId = r.getReturnPlanId();
// Double getCurrMonPrincipal = r.getCurrMonPrincipal();// 应收本金
// Double getCurrMonInterest = r.getCurrMonInterest();// 应收利息
// Double getCurrMonManageFree = r.getCurrMonManageFree();// 应收服务费
// Double getCurrMonPenalty = r.getCurrMonPenalty();// 应收罚息
// Double getCurrMonLaterFree = r.getCurrMonLaterFree();// 应收滞纳金
// Double getCurrReducePrincipal = r.getCurrReducePrincipal();// 减免本金
// Double getCurrReduceInterest = r.getCurrReduceInterest();// 减免利息
// Double getCurrReduceManageFree = r.getCurrReduceManageFree();// 减免服务费
// Double getCurrReducePenalty = r.getCurrReducePenalty();// 减免罚息
// Double getCurrReduceLaterFree = r.getCurrReduceLaterFree();// 减免滞纳金
// Double getCurrPaindinCapital = r.getCurrPaindinCapital();// 实收本金
// Double getCurrPaindinInterest = r.getCurrPaindinInterest();// 实收利息
// Double getCurrPaindinManageFree = r.getCurrPaindinManageFree();// 实收服务费
// Double getCurrMonPaidPenalty = r.getCurrMonPaidPenalty();// 实收罚息
// Double getCurrLateFreePaid = r.getCurrLateFreePaid();// 实收滞纳金
// // System.out.println("分配完金额后的结果");
// // System.out.println("还款计划ID为：" + getReturnPlanId);
// // System.out.println("应收本金为：" + getCurrMonPrincipal);
// // System.out.println("应收利息为：" + getCurrMonInterest);
// // System.out.println("应收服务费为：" + getCurrMonManageFree);
// // System.out.println("应收罚息为：" + getCurrMonPenalty);
// // System.out.println("应收滞纳金为：" + getCurrMonLaterFree);
// // System.out.println("减免本金为：" + getCurrReducePrincipal);
// // System.out.println("减免利息为：" + getCurrReduceInterest);
// // System.out.println("减免服务费为：" + getCurrReduceManageFree);
// // System.out.println("减免罚息为：" + getCurrReducePenalty);
// // System.out.println("减免滞纳金为：" + getCurrReduceLaterFree);
// // System.out.println("实收本金为：" + getCurrPaindinCapital);
// // System.out.println("实收利息为：" + getCurrPaindinInterest);
// // System.out.println("实收服务费为：" + getCurrPaindinManageFree);
// // System.out.println("实收罚息为：" + getCurrMonPaidPenalty);
// // System.out.println("实收滞纳金为：" + getCurrLateFreePaid);
// // System.out.println("======================================");
// } else if (getReceivedAmount.doubleValue() == currNotTotal.doubleValue()) {
// // 如果登记的金额等于当月未还款差值金额
// // 那么待分配金额就等于收款金额
// toBeDistributed = getReceivedAmount;
// // System.out.println("如果待分配的金额小于于当期未还款差值那么分配到这一期的toBeDistributed为：" + toBeDistributed);
// // 收款金额置0
// getReceivedAmount = 0.00;
// // System.out.println("即将进入returnRuralReturnDis方法");
// // 将被分配金额和当期的还款计划传入到returnRuralReturnDis(toBeDistributed, r)方法中
// RuralReturnDis resultRuralReturnDis = returnRuralReturnDis(toBeDistributed, paramReceivedRecord, r);
// r.setCollectionStatus("1");
// insertRuralReturnDis.add(resultRuralReturnDis);
// updateReturnPlanList.add(r);
// Integer getReturnPlanId = r.getReturnPlanId();
// Double getCurrMonPrincipal = r.getCurrMonPrincipal();// 应收本金
// Double getCurrMonInterest = r.getCurrMonInterest();// 应收利息
// Double getCurrMonManageFree = r.getCurrMonManageFree();// 应收服务费
// Double getCurrMonPenalty = r.getCurrMonPenalty();// 应收罚息
// Double getCurrMonLaterFree = r.getCurrMonLaterFree();// 应收滞纳金
// Double getCurrReducePrincipal = r.getCurrReducePrincipal();// 减免本金
// Double getCurrReduceInterest = r.getCurrReduceInterest();// 减免利息
// Double getCurrReduceManageFree = r.getCurrReduceManageFree();// 减免服务费
// Double getCurrReducePenalty = r.getCurrReducePenalty();// 减免罚息
// Double getCurrReduceLaterFree = r.getCurrReduceLaterFree();// 减免滞纳金
// Double getCurrPaindinCapital = r.getCurrPaindinCapital();// 实收本金
// Double getCurrPaindinInterest = r.getCurrPaindinInterest();// 实收利息
// Double getCurrPaindinManageFree = r.getCurrPaindinManageFree();// 实收服务费
// Double getCurrMonPaidPenalty = r.getCurrMonPaidPenalty();// 实收罚息
// Double getCurrLateFreePaid = r.getCurrLateFreePaid();// 实收滞纳金
// // System.out.println("分配完金额后的结果");
// // System.out.println("还款计划ID为：" + getReturnPlanId);
// // System.out.println("应收本金为：" + getCurrMonPrincipal);
// // System.out.println("应收利息为：" + getCurrMonInterest);
// // System.out.println("应收服务费为：" + getCurrMonManageFree);
// // System.out.println("应收罚息为：" + getCurrMonPenalty);
// // System.out.println("应收滞纳金为：" + getCurrMonLaterFree);
// // System.out.println("减免本金为：" + getCurrReducePrincipal);
// // System.out.println("减免利息为：" + getCurrReduceInterest);
// // System.out.println("减免服务费为：" + getCurrReduceManageFree);
// // System.out.println("减免罚息为：" + getCurrReducePenalty);
// // System.out.println("减免滞纳金为：" + getCurrReduceLaterFree);
// // System.out.println("实收本金为：" + getCurrPaindinCapital);
// // System.out.println("实收利息为：" + getCurrPaindinInterest);
// // System.out.println("实收服务费为：" + getCurrPaindinManageFree);
// // System.out.println("实收罚息为：" + getCurrMonPaidPenalty);
// // System.out.println("实收滞纳金为：" + getCurrLateFreePaid);
// // System.out.println("======================================");
// break;
// } else if (getReceivedAmount.doubleValue() < currNotTotal.doubleValue()) {
// // 如果登记的金额小于当月未还款差值金额
// // 那么待分配金额就等于收款金额
// toBeDistributed = getReceivedAmount;
// // System.out.println("如果待分配的金额小于于当期未还款差值那么分配到这一期的toBeDistributed为：" + toBeDistributed);
// // 收款金额置0
// getReceivedAmount = 0.00;
// // System.out.println("即将进入returnRuralReturnDis方法");
// // 将被分配金额和当期的还款计划传入到returnRuralReturnDis(toBeDistributed, r)方法中
// RuralReturnDis resultRuralReturnDis = returnRuralReturnDis(toBeDistributed, paramReceivedRecord, r);
// r.setCollectionStatus("2");
// insertRuralReturnDis.add(resultRuralReturnDis);
// updateReturnPlanList.add(r);
// Integer getReturnPlanId = r.getReturnPlanId();
// Double getCurrMonPrincipal = r.getCurrMonPrincipal();// 应收本金
// Double getCurrMonInterest = r.getCurrMonInterest();// 应收利息
// Double getCurrMonManageFree = r.getCurrMonManageFree();// 应收服务费
// Double getCurrMonPenalty = r.getCurrMonPenalty();// 应收罚息
// Double getCurrMonLaterFree = r.getCurrMonLaterFree();// 应收滞纳金
// Double getCurrReducePrincipal = r.getCurrReducePrincipal();// 减免本金
// Double getCurrReduceInterest = r.getCurrReduceInterest();// 减免利息
// Double getCurrReduceManageFree = r.getCurrReduceManageFree();// 减免服务费
// Double getCurrReducePenalty = r.getCurrReducePenalty();// 减免罚息
// Double getCurrReduceLaterFree = r.getCurrReduceLaterFree();// 减免滞纳金
// Double getCurrPaindinCapital = r.getCurrPaindinCapital();// 实收本金
// Double getCurrPaindinInterest = r.getCurrPaindinInterest();// 实收利息
// Double getCurrPaindinManageFree = r.getCurrPaindinManageFree();// 实收服务费
// Double getCurrMonPaidPenalty = r.getCurrMonPaidPenalty();// 实收罚息
// Double getCurrLateFreePaid = r.getCurrLateFreePaid();// 实收滞纳金
// // System.out.println("分配完金额后的结果");
// // System.out.println("还款计划ID为：" + getReturnPlanId);
// // System.out.println("应收本金为：" + getCurrMonPrincipal);
// // System.out.println("应收利息为：" + getCurrMonInterest);
// // System.out.println("应收服务费为：" + getCurrMonManageFree);
// // System.out.println("应收罚息为：" + getCurrMonPenalty);
// // System.out.println("应收滞纳金为：" + getCurrMonLaterFree);
// // System.out.println("减免本金为：" + getCurrReducePrincipal);
// // System.out.println("减免利息为：" + getCurrReduceInterest);
// // System.out.println("减免服务费为：" + getCurrReduceManageFree);
// // System.out.println("减免罚息为：" + getCurrReducePenalty);
// // System.out.println("减免滞纳金为：" + getCurrReduceLaterFree);
// // System.out.println("实收本金为：" + getCurrPaindinCapital);
// // System.out.println("实收利息为：" + getCurrPaindinInterest);
// // System.out.println("实收服务费为：" + getCurrPaindinManageFree);
// // System.out.println("实收罚息为：" + getCurrMonPaidPenalty);
// // System.out.println("实收滞纳金为：" + getCurrLateFreePaid);
// // System.out.println("======================================");
// break;
// }
//
// } else if (currNotTotal < 0.00) {
// // 如果调整后似的当期的差值小于0
// toBeDistributed = getReceivedAmount;
// RuralReturnDis resultRuralReturnDis = returnRuralReturnDis(toBeDistributed, paramReceivedRecord, r);
// insertRuralReturnDis.add(resultRuralReturnDis);
// updateReturnPlanList.add(r);
// getReceivedAmount = getReceivedAmount - currNotTotal;
// Integer getReturnPlanId = r.getReturnPlanId();
// Double getCurrMonPrincipal = r.getCurrMonPrincipal();// 应收本金
// Double getCurrMonInterest = r.getCurrMonInterest();// 应收利息
// Double getCurrMonManageFree = r.getCurrMonManageFree();// 应收服务费
// Double getCurrMonPenalty = r.getCurrMonPenalty();// 应收罚息
// Double getCurrMonLaterFree = r.getCurrMonLaterFree();// 应收滞纳金
// Double getCurrReducePrincipal = r.getCurrReducePrincipal();// 减免本金
// Double getCurrReduceInterest = r.getCurrReduceInterest();// 减免利息
// Double getCurrReduceManageFree = r.getCurrReduceManageFree();// 减免服务费
// Double getCurrReducePenalty = r.getCurrReducePenalty();// 减免罚息
// Double getCurrReduceLaterFree = r.getCurrReduceLaterFree();// 减免滞纳金
// Double getCurrPaindinCapital = r.getCurrPaindinCapital();// 实收本金
// Double getCurrPaindinInterest = r.getCurrPaindinInterest();// 实收利息
// Double getCurrPaindinManageFree = r.getCurrPaindinManageFree();// 实收服务费
// Double getCurrMonPaidPenalty = r.getCurrMonPaidPenalty();// 实收罚息
// Double getCurrLateFreePaid = r.getCurrLateFreePaid();// 实收滞纳金
// // System.out.println("分配完金额后的结果");
// // System.out.println("还款计划ID为：" + getReturnPlanId);
// // System.out.println("应收本金为：" + getCurrMonPrincipal);
// // System.out.println("应收利息为：" + getCurrMonInterest);
// // System.out.println("应收服务费为：" + getCurrMonManageFree);
// // System.out.println("应收罚息为：" + getCurrMonPenalty);
// // System.out.println("应收滞纳金为：" + getCurrMonLaterFree);
// // System.out.println("减免本金为：" + getCurrReducePrincipal);
// // System.out.println("减免利息为：" + getCurrReduceInterest);
// // System.out.println("减免服务费为：" + getCurrReduceManageFree);
// // System.out.println("减免罚息为：" + getCurrReducePenalty);
// // System.out.println("减免滞纳金为：" + getCurrReduceLaterFree);
// // System.out.println("实收本金为：" + getCurrPaindinCapital);
// // System.out.println("实收利息为：" + getCurrPaindinInterest);
// // System.out.println("实收服务费为：" + getCurrPaindinManageFree);
// // System.out.println("实收罚息为：" + getCurrMonPaidPenalty);
// // System.out.println("实收滞纳金为：" + getCurrLateFreePaid);
// // System.out.println("======================================");
// }
		}
		// 更新还款计划
		returnPlanDao.batchUpdate("RETURNPLAN.dynamicUpdate", updateReturnPlanList);
		// 插入实收分配
		ruralReturnDisDao.batchInsert("RURALRETURNDIS.dynamicInsert", insertRuralReturnDis);
		// 去检测是否全部收款完成！

		return true;

	}

	/**
	 * 将传入的金额和当期的还款计划按照服务费、罚息、滞纳金、利息和本金的顺序依次收齐
	 * 
	 * @param toBeDistributed 当期被分配的金额
	 * @param paramReceivedRecord 当期分配的登记中取出金额
	 * @param r 还款计划
	 * @return 更新后的还款计划
	 */
	@Override
	public RuralReturnDis returnRuralReturnDis(Double toBeDistributed, ReceivedRecord paramReceivedRecord, ReturnPlan r) {
// RuralReturnDis ruralReturnDis = new RuralReturnDis();
// ruralReturnDis.setReturnPlanId(r.getReturnPlanId());
// ruralReturnDis.setBorrowerServiceAppId(paramReceivedRecord.getBorrowerServiceAppId());
// ruralReturnDis.setCurrRecievePrincipal(r.getCurrMonPrincipal());
// ruralReturnDis.setCurrRecieveInterest(r.getCurrMonInterest());
// ruralReturnDis.setCurrRecieveManage(r.getCurrMonManageFree());
// ruralReturnDis.setCurrRecieveLatefree(r.getCurrMonLaterFree());
// ruralReturnDis.setCurrRecievePenalty(r.getCurrMonPenalty());
// ruralReturnDis.setCurrRecieveTotal(r.getCurrAccountTotal());
//
// ruralReturnDis.setOneTimeRepayment(paramReceivedRecord.getReceivedType());
// ruralReturnDis.setDateCharge(paramReceivedRecord.getReceivedTime());
// // 所属还款月份是啥？RESPECTIVE_MON_REPAY
// // 确认还款时间是啥？CONFIM_REPAY_TIME
// // 借款人姓名还要记么？BORROWER_NAME
// // 回款打到分公司标记RETURN_COMPANY_FLAG
// // 免罚标识CLEAR_BREACH_FLAG
// ruralReturnDis.setBreachFlag(r.getOverdueFlag());
// ruralReturnDis.setCurrReducePrincipal(r.getCurrReducePrincipal());
// ruralReturnDis.setCurrReduceInterest(r.getCurrReduceInterest());
// ruralReturnDis.setCurrReduceManageFree(r.getCurrReduceManageFree());
// ruralReturnDis.setCurrReducePenalty(r.getCurrReducePenalty());
// ruralReturnDis.setCurrReduceLaterFree(r.getCurrReduceLaterFree());
// ruralReturnDis.setReceivedRecordId(paramReceivedRecord.getReceivedRecordId());
// ruralReturnDis.setReceEarlyDamages(r.getReceEarlyDamages());
//
// Double getCurrMonPrincipal = r.getCurrMonPrincipal();// 应收本金
// Double getCurrMonInterest = r.getCurrMonInterest();// 应收利息
// Double getCurrMonManageFree = r.getCurrMonManageFree();// 应收服务费
// Double getCurrMonPenalty = r.getCurrMonPenalty();// 应收罚息
// Double getCurrMonLaterFree = r.getCurrMonLaterFree();// 应收滞纳金
//
// Double getReceEarlyDamages = r.getReceEarlyDamages();// 应收提前还款违约金
//
// Double getCurrReducePrincipal = r.getCurrReducePrincipal();// 减免本金
// Double getCurrReduceInterest = r.getCurrReduceInterest();// 减免利息
// Double getCurrReduceManageFree = r.getCurrReduceManageFree();// 减免服务费
// Double getCurrReducePenalty = r.getCurrReducePenalty();// 减免罚息
// Double getCurrReduceLaterFree = r.getCurrReduceLaterFree();// 减免滞纳金
//
// Double actualReceivablePrincipal = CurrencyUtil.add(getCurrMonPrincipal, getCurrReducePrincipal);// 实际应收本金
// Double actualReceivableInterest = CurrencyUtil.add(getCurrMonInterest, getCurrReduceInterest);// 实际应收利息
// Double actualReceivableManageFree = CurrencyUtil.add(getCurrMonManageFree, getCurrReduceManageFree);// 实际应收服务费
// Double actualReceivablePenalty = CurrencyUtil.add(getCurrMonPenalty, getCurrReducePenalty);// 实际应收罚息
// Double actualReceivableLaterFree = CurrencyUtil.add(getCurrMonLaterFree, getCurrReduceLaterFree);// 实际应收滞纳金
//
// Double getCurrPaindinCapital = r.getCurrPaindinCapital();// 实收本金
// Double getCurrPaindinInterest = r.getCurrPaindinInterest();// 实收利息
// Double getCurrPaindinManageFree = r.getCurrPaindinManageFree();// 实收服务费
// Double getCurrMonPaidPenalty = r.getCurrMonPaidPenalty();// 实收罚息
// Double getCurrLateFreePaid = r.getCurrLateFreePaid();// 实收滞纳金
//
// Double getPaidEarlyDamages = r.getPaidEarlyDamages();// 实收提前还款违约金
//
// Double freeDifference = CurrencyUtil.sub(actualReceivableLaterFree, getCurrLateFreePaid);// 滞纳金差
// Double penaltyDifference = CurrencyUtil.sub(actualReceivablePenalty, getCurrMonPaidPenalty);// 罚息差
// Double manageDifference = CurrencyUtil.sub(actualReceivableManageFree, getCurrPaindinManageFree);// 服务费差
// Double interestDifference = CurrencyUtil.sub(actualReceivableInterest, getCurrPaindinInterest);// 利息差
// Double principalDifference = CurrencyUtil.sub(actualReceivablePrincipal, getCurrPaindinCapital);// 本金差
// Double earlyDamagesDifference = CurrencyUtil.sub(getReceEarlyDamages, getPaidEarlyDamages);// 违约金差
// // // System.out.println("应收本金为：" + getCurrMonPrincipal);
// // // System.out.println("应收利息为：" + getCurrMonInterest);
// // // System.out.println("应收服务费为：" + getCurrMonManageFree);
// // // System.out.println("应收罚息为：" + getCurrMonPenalty);
// // // System.out.println("应收滞纳金为：" + getCurrMonLaterFree);
// // // System.out.println("实收本金为：" + getCurrPaindinCapital);
// // // System.out.println("实收利息为：" + getCurrPaindinInterest);
// // // System.out.println("实收服务费为：" + getCurrPaindinManageFree);
// // // System.out.println("实收罚息为：" + getCurrMonPaidPenalty);
// // // System.out.println("实收滞纳金为：" + getCurrLateFreePaid);
// // // System.out.println("滞纳金差：" + freeDifference);
// // // System.out.println("罚息差：" + penaltyDifference);
// // // System.out.println("服务费差：" + manageDifference);
// // // System.out.println("利息差：" + interestDifference);
// // // System.out.println("本金差：" + principalDifference);
//
// // 收服务费
// if (manageDifference > 0.00 && toBeDistributed > 0.00) {
// // System.out.println("收服务费");
// Double temp = toBeDistributed;
// toBeDistributed = CurrencyUtil.sub(toBeDistributed, manageDifference);
// if (toBeDistributed > 0.00) {
// r.setCurrPaindinManageFree(CurrencyUtil.add(getCurrPaindinManageFree, manageDifference));
// ruralReturnDis.setActPaidManageFree(manageDifference);
// } else {
// r.setCurrPaindinManageFree(CurrencyUtil.add(getCurrPaindinManageFree, temp));
// ruralReturnDis.setActPaidManageFree(temp);
// }
// }
// if (manageDifference < 0.00 && toBeDistributed >= 0.00) {
// // System.out.println("免服务费");
// toBeDistributed = CurrencyUtil.sub(toBeDistributed, manageDifference);
// r.setCurrPaindinManageFree(CurrencyUtil.add(getCurrPaindinManageFree, manageDifference));
// ruralReturnDis.setActPaidManageFree(manageDifference);
// }
//
// // 收罚息
// if (penaltyDifference > 0.00 && toBeDistributed > 0.00) {
// // System.out.println("收罚息");
// Double temp = toBeDistributed;
// toBeDistributed = CurrencyUtil.sub(toBeDistributed, penaltyDifference);
// if (toBeDistributed > 0.00) {
// r.setCurrMonPaidPenalty(CurrencyUtil.add(getCurrMonPaidPenalty, penaltyDifference));
// ruralReturnDis.setActPaidPanalty(penaltyDifference);
// } else {
// r.setCurrMonPaidPenalty(CurrencyUtil.add(getCurrMonPaidPenalty, temp));
// ruralReturnDis.setActPaidPanalty(temp);
// }
// }
// if (penaltyDifference < 0.00 && toBeDistributed >= 0.00) {
// // System.out.println("免罚息");
// toBeDistributed = CurrencyUtil.sub(toBeDistributed, penaltyDifference);
// r.setCurrMonPaidPenalty(CurrencyUtil.add(getCurrMonPaidPenalty, penaltyDifference));
// ruralReturnDis.setActPaidPanalty(penaltyDifference);
// }
//
// // 收滞纳金
// if (freeDifference > 0.00 && toBeDistributed > 0.00) {
// // System.out.println("收滞纳金");
// Double temp = toBeDistributed;
// toBeDistributed = CurrencyUtil.sub(toBeDistributed, freeDifference);
// if (toBeDistributed > 0.00) {
// r.setCurrLateFreePaid(CurrencyUtil.add(getCurrLateFreePaid, freeDifference));
// ruralReturnDis.setActPaidFine(freeDifference);
// } else {
// r.setCurrLateFreePaid(CurrencyUtil.add(getCurrLateFreePaid, temp));
// ruralReturnDis.setActPaidFine(temp);
// }
// }
// if (freeDifference < 0.00 && toBeDistributed >= 0.00) {
// // System.out.println("免滞纳金");
// toBeDistributed = CurrencyUtil.sub(toBeDistributed, freeDifference);
// r.setCurrLateFreePaid(CurrencyUtil.add(getCurrLateFreePaid, freeDifference));
// ruralReturnDis.setActPaidFine(freeDifference);
// }
//
// // 收利息
// if (interestDifference > 0.00 && toBeDistributed > 0.00) {
// // System.out.println("收利息");
// Double temp = toBeDistributed;
// toBeDistributed = CurrencyUtil.sub(toBeDistributed, interestDifference);
// if (toBeDistributed > 0.00) {
// r.setCurrPaindinInterest(CurrencyUtil.add(getCurrPaindinInterest, interestDifference));
// ruralReturnDis.setActPaidInterest(interestDifference);
// } else {
// r.setCurrPaindinInterest(CurrencyUtil.add(getCurrPaindinInterest, temp));
// ruralReturnDis.setActPaidInterest(temp);
// }
// }
// if (interestDifference < 0.00 && toBeDistributed >= 0.00) {
// // System.out.println("免利息");
// toBeDistributed = CurrencyUtil.sub(toBeDistributed, interestDifference);
// r.setCurrPaindinInterest(CurrencyUtil.add(getCurrPaindinInterest, interestDifference));
// ruralReturnDis.setActPaidInterest(interestDifference);
// }
// // 收本金
// if (principalDifference > 0.00 && toBeDistributed > 0.00) {
// // System.out.println("收本金");
// Double temp = toBeDistributed;
// toBeDistributed = CurrencyUtil.sub(toBeDistributed, principalDifference);
// if (toBeDistributed > 0.00) {
// r.setCurrPaindinCapital(CurrencyUtil.add(getCurrPaindinCapital, principalDifference));
// ruralReturnDis.setActPaidinCapital(principalDifference);
// } else {
// r.setCurrPaindinCapital(CurrencyUtil.add(getCurrPaindinCapital, temp));
// ruralReturnDis.setActPaidinCapital(temp);
// }
// }
// if (principalDifference < 0.00 && toBeDistributed >= 0.00) {
// // System.out.println("免本金");
// toBeDistributed = CurrencyUtil.sub(toBeDistributed, principalDifference);
// r.setCurrPaindinCapital(CurrencyUtil.add(getCurrPaindinCapital, principalDifference));
// ruralReturnDis.setActPaidinCapital(principalDifference);
// }
// // 收一次性还款违约金
// if (earlyDamagesDifference > 0.00 && toBeDistributed > 0.00) {
// // System.out.println("收提前一次性还款违约金");
// Double temp = toBeDistributed;
// toBeDistributed = CurrencyUtil.sub(toBeDistributed, earlyDamagesDifference);
// if (toBeDistributed > 0.00) {
// r.setPaidEarlyDamages(CurrencyUtil.add(getPaidEarlyDamages, earlyDamagesDifference));
// ruralReturnDis.setPaidEarlyDamages(earlyDamagesDifference);
// } else {
// r.setPaidEarlyDamages(CurrencyUtil.add(getPaidEarlyDamages, temp));
// ruralReturnDis.setPaidEarlyDamages(temp);
// }
// }
//
// // returnPlanDao.update("RETURNPLAN.dynamicUpdate", r);
// // 如果被分配的省下了肿么办
// if (toBeDistributed > 0) {
// /*
// * Integer getCreditapplicationId = r.getCreditapplicationId();
// * ReceivedRecord tempReceivedRecord = (ReceivedRecord) receivedRecordDao.queryUnique(
// * "RECEIVEDRECORD.dynamicSelect", getCreditapplicationId);
// * allotReceivedRecord(tempReceivedRecord);
// */
// }
// // 实收总计
// Double total = CurrencyUtil.add(
// r.getCurrPaindinCapital(),
// CurrencyUtil.add(
// r.getCurrPaindinInterest(),
// CurrencyUtil.add(r.getCurrPaindinManageFree(),
// CurrencyUtil.add(r.getCurrMonPaidPenalty(), r.getCurrLateFreePaid()))));
// r.setCurrMonPayTotal(total);
		// ruralReturnDis.setCurrPaidTotal(total);
		return null;
	};

	/**
	 * 这个是已经完成分配后调整减免的接口
	 * 
	 * @param paramReceivedRecord 收款登记
	 * @param adjustReturnPlanList 调整后的
	 * @return 布尔型
	 */
	@Override
	public boolean adjustment(ReceivedRecord paramReceivedRecord, List<ReturnPlan> adjustReturnPlanList) {
		// TODO Auto-generated method stub
		int count = returnPlanDao.batchUpdate("RETURNPLAN.dynamicUpdate", adjustReturnPlanList);
		boolean success = false;
		if (count > 0) {
			success = allotReceivedRecord(paramReceivedRecord);
		}
		return success;
	}

	/**
	 * 一次性提前还款（可能需要调整！！！！[判断当期的可能有问题]）
	 * 
	 * @param allotReturnPlanList 需要分配的List
	 * @param earlyType 提前还款类型
	 * @param getReceivedTime 收款时间
	 * @param tempReturnPlanList 还款计划List
	 * @return 重新set的List
	 */
	private List<ReturnPlan> earlyReceviedList(List<ReturnPlan> allotReturnPlanList, List<ReturnPlan> returnPlanList,
			ReceivedRecord receivedRecord) {
// CreditApplication application = creditApplicationService.selectById(returnPlanList.get(0)
// .getCreditapplicationId());
// AmountConfirm amountConfirm = amountConfirmService.selectNew(returnPlanList.get(0).getCreditapplicationId());

		Integer creditapplicationId = receivedRecord.getCreditapplicationId();

		Date receivedDate = receivedRecord.getReceivedTime();

		// 通过creditapplicationId去查对应的还款计划全部的哦要！
		ReturnPlan passReturnPlan = new ReturnPlan();
		// passReturnPlan.setCreditapplicationId(creditapplicationId);
		// 得到了全部的还款计划
		// 当期
		ReturnPlan thisRP = null;
		// 找当期
		for (ReturnPlan r : returnPlanList) {
			if (receivedDate.compareTo(r.getRepaymentDate()) <= 0) {
				// 找到这一期
				thisRP = r;
				break;
			} else {
				// 这是错的不可能大于零
				// throw new AppBusinessException("后台日期判断出错！");
			}
		}

		String getPeriod = thisRP.getPeriod();
		int tPeriod = Integer.parseInt(getPeriod);
		int fPeriod = Integer.parseInt(allotReturnPlanList.get(0).getPeriod());
		Date submitDate;
		if (tPeriod < fPeriod) {
			// 当期已经还完了约的日期应该是未还款的第一期的上一期
			int i = 0;
			for (i = 0; i < returnPlanList.size(); i++) {
				ReturnPlan r = returnPlanList.get(i);
				int tempP = Integer.parseInt(r.getPeriod());
				if (tempP == fPeriod) {
					break;
				}

			}
			submitDate = returnPlanList.get(i - 1).getRepaymentDate();
		} else if (tPeriod == fPeriod) {
			// 当期还没有还完及当期就是未还款的当期
			submitDate = allotReturnPlanList.get(0).getRepaymentDate();
		} else {
			// 这是错的不可能大于零
			throw new AppBusinessException("后台日期判断属于哪一期出错！");
		}

		/** 第一个参数对象 **/
		PaymentAmountReqCommon paymentAmountReqCommon = new PaymentAmountReqCommon();
		// 是否一次性还清
		paymentAmountReqCommon.setaLLAhead(true);
		// 预约还款时间
		paymentAmountReqCommon.setAppointmentDate(DateUtil.dateConvertString(submitDate));
		// 审核日期（系统审核通过的日期，本地表应该有）
		// paymentAmountReqCommon.setAuditDate(new Date());
		// 产品分类编号CP
		// paymentAmountReqCommon.setCatagoryId(Long.getLong("1111111"));
		// 合同金额
// paymentAmountReqCommon.setContractMoney(BigDecimal.valueOf(amountConfirm.getAmount()));
// // 营业部编号
// paymentAmountReqCommon.setDepartmentId(Long.valueOf(application.getDepartmentId()));
// // 放款日期
// paymentAmountReqCommon.setLenderDate(DateUtil.dateConvertString(application.getSignagreementDate()));
// // 分期数
// paymentAmountReqCommon.setPeriodCount(Integer.parseInt(application.getInstalments()));
// // 上一次计算逾期费用时间
// // paymentAmountReqCommon.setPrevCalcDate(new Date());
// // 产品版本编号--REPAYMENT_PLAN_ID
// paymentAmountReqCommon.setProductInfoId(application.getRepaymentPlanId().longValue());
// // 请求系统日期
// paymentAmountReqCommon.setReqSysDate(DateUtil.dateConvertString(new Date()));
		/** 第二个参数对象 **/

		List<ActualPeriodScheduleDTO> apsDtos = new ArrayList<ActualPeriodScheduleDTO>();

		if (CommonUtil.isNotEmpty(returnPlanList)) {
			for (ReturnPlan r : returnPlanList) {

				ActualPeriodScheduleDTO actualPeriodScheduleDTO = new ActualPeriodScheduleDTO();
				boolean s = false;
				if ("1".equals(r.getOverdueFlag())) {
					s = !s;
				}
				// 逾期分期服务费相关
				actualPeriodScheduleDTO.setOverdueFQFWFReceivableCharge(BigDecimal.valueOf(0));
				actualPeriodScheduleDTO.setOverdueFQFWFReceivedCharge(BigDecimal.valueOf(0));
				// 罚息相关
// actualPeriodScheduleDTO.setOverdueFxReceivableCharge(BigDecimal.valueOf(r.getCurrMonPenalty()));
// actualPeriodScheduleDTO.setOverdueFxReceivedCharge(BigDecimal.valueOf(CurrencyUtil.sub(
// r.getCurrMonPaidPenalty(), r.getCurrReducePenalty())));
// // 滞纳金相关
// actualPeriodScheduleDTO.setOverdueZnjReceivableCharge(BigDecimal.valueOf(r.getCurrMonLaterFree()));
// actualPeriodScheduleDTO.setOverdueZnjReceivedCharge(BigDecimal.valueOf(CurrencyUtil.sub(
// r.getCurrLateFreePaid(), r.getCurrReduceLaterFree())));
// // 分期服务费相关
// actualPeriodScheduleDTO.setPeriodFQFWFReceivableCharge(BigDecimal.valueOf(r.getCurrMonManageFree()));
// actualPeriodScheduleDTO.setPeriodFQFWFReceivedCharge(BigDecimal.valueOf(CurrencyUtil.sub(
// r.getCurrPaindinManageFree(), r.getCurrReduceManageFree())));
// // 利息相关
// actualPeriodScheduleDTO.setReceivableInterest(BigDecimal.valueOf(r.getCurrMonInterest()));
// actualPeriodScheduleDTO.setReceivedInterest(BigDecimal.valueOf(CurrencyUtil.sub(
// r.getCurrPaindinInterest(), r.getCurrReduceInterest())));
// // 本金相关
// actualPeriodScheduleDTO.setReceivablePrincipal(BigDecimal.valueOf(r.getCurrMonPrincipal()));
// actualPeriodScheduleDTO.setReceivedPrincipal(BigDecimal.valueOf(CurrencyUtil.sub(
// r.getCurrPaindinCapital(), r.getCurrReducePrincipal())));
// // 总金额相关
// actualPeriodScheduleDTO.setReceivableMoney(BigDecimal.valueOf(r.getCurrAccountTotal()));
// actualPeriodScheduleDTO.setReceivedMoney(BigDecimal.valueOf(CurrencyUtil.sub(r.getCurrMonPayTotal(),
// r.getCurrReduceTotal())));
				// 其他相关
				actualPeriodScheduleDTO.setOverdue(s);
				actualPeriodScheduleDTO.setPeriod(Integer.parseInt(r.getPeriod()));
				actualPeriodScheduleDTO.setRepayDate(DateUtil.dateConvertString(r.getRepaymentDate()));
				apsDtos.add(actualPeriodScheduleDTO);
			}
		}

		// 转化成webservice
		PaymentAmountResultInfo paymentAmountResultInfo = borrowingProductsService.paymentAmountCalculate(
				paymentAmountReqCommon, apsDtos);
		// 概况
		paymentAmountResultInfo.getResultCode();
		// System.out.println("返回接口操作代码，0表示成功，非0表示错误代码。===========" + paymentAmountResultInfo.getResultCode());
		paymentAmountResultInfo.getResultMsg();
		// System.out.println("返回接口操作信息，调用成功返回“success”，失败返回错误代码对应的错误信息。==========="
		// + paymentAmountResultInfo.getResultMsg());
		// 得到提前一次性还款对象
		AllAheadSchedule aheadSchedule = paymentAmountResultInfo.getAheadSchedule();

		BigDecimal getCurrentPrincipal = aheadSchedule.getCurrentPrincipal();
		BigDecimal getCurrentInterest = aheadSchedule.getCurrentInterest();
		BigDecimal getCurrentPeriodCharge = aheadSchedule.getCurrentPeriodCharge();

		// 目前还没有考虑违约情况
		if (getCurrentPrincipal.doubleValue() == 0.00 && getCurrentInterest.doubleValue() == 0.00
				&& getCurrentPeriodCharge.doubleValue() == 0.00) {
			// 当期要么不用还；要么已经还清，从未还清的第一期开始做减免
			for (ReturnPlan r : allotReturnPlanList) {

// r.setCurrReduceInterest(CurrencyUtil.sub(r.getCurrReduceInterest(), r.getCurrMonInterest()));
// r.setCurrReduceManageFree(CurrencyUtil.sub(r.getCurrReduceManageFree(), r.getCurrMonManageFree()));
// r.setCurrReduceTotal(CurrencyUtil.add(
// r.getCurrReduceLaterFree(),
// CurrencyUtil.add(
// r.getCurrReducePenalty(),
// CurrencyUtil.add(r.getCurrReducePrincipal(),
// CurrencyUtil.add(r.getCurrReduceInterest(), r.getCurrReduceManageFree())))));
			}
// allotReturnPlanList.get(allotReturnPlanList.size() - 1).setReceEarlyDamages(
// aheadSchedule.getPenalbond().doubleValue());
		} else {
			// 当期没有还清，当期不用做减免，从下一期开始做减免
// for (int i = 1; i < allotReturnPlanList.size(); i++) {
// allotReturnPlanList.get(i).setCurrReduceInterest(
// CurrencyUtil.sub(allotReturnPlanList.get(i).getCurrReduceInterest(), allotReturnPlanList.get(i)
// .getCurrMonInterest()));
// allotReturnPlanList.get(i).setCurrReduceManageFree(
// CurrencyUtil.sub(allotReturnPlanList.get(i).getCurrReduceManageFree(),
// allotReturnPlanList.get(i).getCurrMonManageFree()));
// allotReturnPlanList.get(i).setCurrReduceTotal(
// CurrencyUtil.add(allotReturnPlanList.get(i).getCurrReduceLaterFree(), CurrencyUtil.add(
// allotReturnPlanList.get(i).getCurrReducePenalty(),
// CurrencyUtil.add(allotReturnPlanList.get(i).getCurrReducePrincipal(), CurrencyUtil.add(
// allotReturnPlanList.get(i).getCurrReduceInterest(), allotReturnPlanList.get(i)
// .getCurrReduceManageFree())))));
// }
// allotReturnPlanList.get(allotReturnPlanList.size() - 1).setReceEarlyDamages(
// aheadSchedule.getPenalbond().doubleValue());
		}

		return allotReturnPlanList;
	}

	/**
	 * 收款完成检测器
	 * 
	 * @param paramReceivedRecord 传入的收款登记对象
	 * @return 业务单是否完成收款
	 */
	private boolean receivablesEnd(ReceivedRecord paramReceivedRecord) {
		// 收款完成标志
		boolean completFlag = true;
		Integer getCreditapplicationId = paramReceivedRecord.getCreditapplicationId();
		ReturnPlan returnPlan = new ReturnPlan();
		// returnPlan.setCreditapplicationId(getCreditapplicationId);
		List<ReturnPlan> returnPlanList = (List<ReturnPlan>) returnPlanDao.queryList("RETURNPLAN.dynamicSelect",
				returnPlan);
		for (ReturnPlan r : returnPlanList) {
			String getCollectionStatus = r.getCollectionStatus();
			if ("0".equals(getCollectionStatus) || "2".equals(getCollectionStatus)) {
				completFlag = false;
				break;
			}
		}
		if (completFlag == true) {
			// 更改一个状态changeAuditing
			int size = returnPlanList.size();
			int last = size - 1;
			String getPreRegistFlag = returnPlanList.get(last).getPreRegistFlag();
			if ("1".equals(getPreRegistFlag)) {
				CreditApplication creditApplication = new CreditApplication();
				creditApplication.setAuditStatus("20");
				creditApplication.setCreditapplicationId(getCreditapplicationId);
				creditApplicationService.changeAuditing(creditApplication, getCreditapplicationId, "20");
			} else {
				CreditApplication creditApplication = new CreditApplication();
				creditApplication.setAuditStatus("16");
				creditApplication.setCreditapplicationId(getCreditapplicationId);
				creditApplicationService.changeAuditing(creditApplication, getCreditapplicationId, "16");
			}

		}
		return true;
	}

	/**
	 * 测试定时的任务
	 */
	public void test() {
		// TODO Auto-generated method stub
// // System.out.println("==============================================================================");
// // System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
// // System.out.println("******************************************************************************");
// // System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
// // System.out.println("##############################################################################");
// List<ReceivedRecord> resultReceivedRecordList = new ArrayList<ReceivedRecord>();
// // 进入queryByReceivedStatus()方法
// resultReceivedRecordList = queryByReceivedStatus();
// // System.out.println("resultReceivedRecordList的长度为：" + resultReceivedRecordList.size());
// // System.out.println("也就是说收款登记记录的条数为：" + resultReceivedRecordList.size());
// // System.out.println("---------------------------测试定时任务成功------------------------------------");
		String a = new String("a");
		String b = new String("a");
		// System.out.println(a == b);
	}

	@Override
	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
		// TODO Auto-generated method stub

	}

	// ★━━━━━━━━━━━━━━━━━━━分配算法相关END━━━━━━━━━━━━━━━━━━━━━━━━★
	@Override
	public Map<String, List<ReceivedRecord>> checkOnWay(Integer creditapplicationId) {
		// TODO Auto-generated method stub
		// 0 -代表没有收款登记。 1-代表有收款登记但未进行预约。 2-代表有一条收款登记已经进行预约 但可以撤回。 3-代表有多条收款预约或有一条收款登记但有多条收款预约。
		Map<String, List<ReceivedRecord>> returnMap = new HashMap<String, List<ReceivedRecord>>();
		// this.checkOnWay(creditapplicationId);
		ReceivedRecord receivedRecord = new ReceivedRecord();
		receivedRecord.setCreditapplicationId(creditapplicationId);
		List<ReceivedRecord> receivedRecordList = (List<ReceivedRecord>) receivedRecordDao.queryList(
				"RECEIVEDRECORD.selectForCancel", creditapplicationId);
		if (CommonUtil.isEmpty(receivedRecordList)) {
			// 证明没有在途的登记
			returnMap.put("0", null);
		} else {
			// 有在途的登记
			List<Integer> receivedRecordIdList = new ArrayList<Integer>();
			for (ReceivedRecord r : receivedRecordList) {
				receivedRecordIdList.add(r.getReceivedRecordId());
			}
			// 传入收款登记List对应的的IDList能返回这个List对应的收款预约记录
			List<FinanceMoney> financeMoneyList = (List<FinanceMoney>) financeMoneyService
					.selectOnline(receivedRecordIdList);
			if (CommonUtil.isEmpty(financeMoneyList)) {
				// 证明所有收款登记均未预约
				// returnMap.put("0", value)
				returnMap.put("1", receivedRecordList);
			} else {
				// 有收款登记已经预约
				// 比较收款登记与收款预约记录是否相等
				if (receivedRecordList.size() == financeMoneyList.size()) {
					// 收款登记已经全部预约过去了
					// 那么就比较是否是有一笔收款登记
					if (1 == receivedRecordList.size()) {
						// 查询这一笔收款登记对应的预约下有没有其他的登记
						FinanceMoney financeMoney = new FinanceMoney();
						financeMoney.setBizId(financeMoneyList.get(0).getBizId());
						int size = financeMoneyService.selectCountReceiveFinanceMoneyList(financeMoney);
						if (1 == size) {
							// 只有这一笔登记的预约
							returnMap.put("2", receivedRecordList);
						} else if (1 < size) {
							// 还有其他登记的预约
							returnMap.put("3", receivedRecordList);
						}
					} else {
						// 有多笔收款登记预约过去了
						// 这种状态下不能撤销
						returnMap.put("3", receivedRecordList);
					}
				} else {
					// 收款登记与预约记录不相等
					// 不能撤销
					returnMap.put("3", receivedRecordList);
				}
			}
		}
		return returnMap;
	}

	@Override
	public boolean updateAppointmentRevoked(String param, List<Integer> receivedRecordIdList) {
		// TODO Auto-generated method stub

		User user = SpringSecurityUtils.getCurrentUser();
		List<OperateLogBusinessStruct> operateLogBusinessStructList = new ArrayList<OperateLogBusinessStruct>();
		if ("1".endsWith(param)) {
			List<CreditApplication> creditapplicationList = (List<CreditApplication>) receivedRecordDao.queryList(
					"RECEIVEDRECORD.selectReceivedRecordByList", receivedRecordIdList);
			List<ReceivedRecord> receivedRecordList = new ArrayList<ReceivedRecord>();
			for (Integer r : receivedRecordIdList) {
				ReceivedRecord receivedRecord = new ReceivedRecord();
				receivedRecord.setReceivedRecordId(r);
				receivedRecord.setRevocationOperatorId(Integer.parseInt(user.getUserId()));
				receivedRecord.setHistoryFlag("T");
				receivedRecord.setRevocationTime(new Timestamp(new Date().getTime()));
				receivedRecordList.add(receivedRecord);
			}
			receivedRecordDao.batchUpdate("RECEIVEDRECORD.dynamicUpdate", receivedRecordList);

			for (CreditApplication c : creditapplicationList) {
				OperateLogBusinessStruct operateLogBusinessStruct = new OperateLogBusinessStruct();
				operateLogBusinessStruct.setCreditapplicationId(c.getCreditapplicationId().longValue());
				operateLogBusinessStruct.setFunctionCode("40001*");
				operateLogBusinessStructList.add(operateLogBusinessStruct);
			}
			for (OperateLogBusinessStruct o : operateLogBusinessStructList) {
				operateLogService.insert(o);
			}
		} else if ("2".endsWith(param)) {
		} else {
		}
		return true;
	}

	@Override
	public BorrowerServiceApp selectBycId(Integer creditapplicationId) {
		// TODO Auto-generated method stub
		return (BorrowerServiceApp) receivedRecordDao.queryUnique(
				"BORROWERSERVICE.selectByCreditapplicationIdForPerson", creditapplicationId);
	}

	@Override
	public AccountInfo addPersonalAccInfo(AccountInfo accountInfo) {
		// TODO Auto-generated method stub
		accountInfo.setOperaterId(SpringSecurityUtils.getCurrentUser().getUserId());
		accountInfo.setOperaterName(SpringSecurityUtils.getCurrentUser().getName_zh());
		accountInfo.setAccountType("1");
		receivedRecordDao.insertObject("ACCOUNTINFO.insert", accountInfo);
		if (accountInfo.getAccountInfoId() == null || "".equals(accountInfo.getAccountInfoId())) {
			return null;
		} else {
			return accountInfo;
		}
	}

	@Override
	public List<CreditApplication> selectCreditapplicationId(List<Integer> rrList) {
		List<CreditApplication> list = (List<CreditApplication>) receivedRecordDao.queryList(
				"RECEIVEDRECORD.selectCreditapplicationId", rrList);
		return list;
	}

	/**
	 * 
	 * @param date 日期
	 * @param type 形式
	 * @return String型
	 */
	public static String dateToString(Date date, String type) {
		String str = null;
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		if (type.equals("SHORT")) {
			// 07-1-18
			format = DateFormat.getDateInstance(DateFormat.SHORT);
			str = format.format(date);
		} else if (type.equals("MEDIUM")) {
			// 2007-1-18
			format = DateFormat.getDateInstance(DateFormat.MEDIUM);
			str = format.format(date);
		} else if (type.equals("FULL")) {
			// 2007年1月18日 星期四
			format = DateFormat.getDateInstance(DateFormat.FULL);
			str = format.format(date);
		}
		return str;
	}
}
