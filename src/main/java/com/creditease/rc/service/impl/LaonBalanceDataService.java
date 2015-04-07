package com.creditease.rc.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.creditease.rc.app.credit.ContractObj;
import com.creditease.rc.app.credit.ContractObj.QyOverdueList;
import com.creditease.rc.app.credit.LoanBalanceDataResponse;
import com.creditease.rc.app.credit.LoanBalanceDataResult;
import com.creditease.rc.app.credit.LoanBalanceDataResult.ContractList;
import com.creditease.rc.app.credit.QyOverdueListObj;
import com.creditease.rc.dao.ILaonBalanceDataDao;
import com.creditease.rc.domain.LaonBalanceData;
import com.creditease.rc.domain.Message;
import com.creditease.rc.domain.OverDueData;
import com.creditease.rc.service.ILaonBalanceDataService;
import com.creditease.rc.service.IOverDueDataService;
import com.creditease.rc.service.IRural2CreditService;
import com.creditease.rc.util.CommonUtil;
import com.creditease.rc.util.DateUtil;
import com.creditease.rc.vo.LoanBalanceQuertData;
import com.creditease.rc.vo.LoanTableData;

@Service
public class LaonBalanceDataService implements ILaonBalanceDataService {

	@Resource
	private ILaonBalanceDataDao balanceDataDao;

	@Resource
	private IRural2CreditService rural2CreditService;
	@Resource
	private IOverDueDataService overDueDataService;

	@Override
	public boolean dynamicInsert(LaonBalanceData balanceData) {
		// TODO Auto-generated method stub
		return balanceDataDao.dynamicInsert(balanceData);
	}

	@Override
	public boolean dynamicUpdate(LaonBalanceData balanceData) {
		// TODO Auto-generated method stub
		return balanceDataDao.dynamicUpdate(balanceData);
	}

	@Override
	public Message insertSynchronizationLoan(Long creditapplicationId, String officeId, String sellerId) {
		// TODO Auto-generated method stub
		Message message = new Message();
		LoanBalanceDataResponse loanBalanceDataResponse = rural2CreditService.loanBalanceData(null, null, null);
		LoanBalanceDataResult loanBalanceDataResult = loanBalanceDataResponse.getLoanBalanceDataResult();
		String getCode = loanBalanceDataResult.getCode();
		String getMessage = loanBalanceDataResult.getMessage();
		System.out.println(loanBalanceDataResult.getCode());
		System.out.println(loanBalanceDataResult.getMessage());
		message.setCode(getCode);
		message.setMsg(getMessage);
		if ("0".equals(getCode)) {
			message.setSuccess(true);
			List<LaonBalanceData> laonBalanceDatas = new ArrayList<LaonBalanceData>();
			Map<String, LaonBalanceData> mapLaonBalanceData = new HashMap<String, LaonBalanceData>();
			List<OverDueData> overDueDatas = new ArrayList<OverDueData>();
			Map<String, OverDueData> mapOverDueData = new HashMap<String, OverDueData>();
			ContractList contractList = loanBalanceDataResult.getContractList();
			if (contractList != null) {
				List<ContractObj> contractObjs = contractList.getContractObj();
				if (CommonUtil.isNotEmpty(contractObjs)) {
					int i = 0;
					for (ContractObj contractObj : contractObjs) {
						String applyId = contractObj.getApplyId();
						String dCorpus = contractObj.getDCorpus();
						String dInterest = contractObj.getDInterest();
						String dOverhead = contractObj.getDOverhead();
						String dCharges = contractObj.getDCharges();
						String fCorpus = contractObj.getFCorpus();
						String fInterest = contractObj.getFInterest();
						String fOverhead = contractObj.getFOverhead();
						String fCharges = contractObj.getFCharges();
						String dfCorpus = contractObj.getDfCorpus();
						String dfInterest = contractObj.getDfInterest();
						String dfOverhead = contractObj.getDfOverhead();
						String dfCharges = contractObj.getDfCharges();

						LaonBalanceData balanceData = new LaonBalanceData();
						balanceData.setSysGuid(applyId);
						balanceData.setdCorpus(trancBigDecimal(dCorpus));
						balanceData.setdInterest(trancBigDecimal(dInterest));
						balanceData.setdOverhead(trancBigDecimal(dOverhead));
						balanceData.setdCharges(trancBigDecimal(dCharges));
						balanceData.setfCorpus(trancBigDecimal(fCorpus));
						balanceData.setfInterest(trancBigDecimal(fInterest));
						balanceData.setfOverhead(trancBigDecimal(fOverhead));
						balanceData.setfCharges(trancBigDecimal(fCharges));
						balanceData.setDfCorpus(trancBigDecimal(dfCorpus));
						balanceData.setDfInterest(trancBigDecimal(dfInterest));
						balanceData.setDfOverhead(trancBigDecimal(dfOverhead));
						balanceData.setDfCharges(trancBigDecimal(dfCharges));

						laonBalanceDatas.add(balanceData);
						mapLaonBalanceData.put(String.valueOf(i), balanceData);
						QyOverdueList overdueList = contractObj.getQyOverdueList();
						if (overdueList != null) {
							List<QyOverdueListObj> qyOverdueListObjs = overdueList.getQyOverdueListObj();
							if (CommonUtil.isNotEmpty(qyOverdueListObjs)) {
								int j = 0;
								for (QyOverdueListObj qyOverdueListObj : qyOverdueListObjs) {

									OverDueData overDueData = new OverDueData();
									String overdueDays = qyOverdueListObj.getOverdueDays();
									String overdueStart = qyOverdueListObj.getOverdueStart();
									String odCorpus = qyOverdueListObj.getOdCorpus();
									String odInterest = qyOverdueListObj.getOdInterest();
									String odOverhead = qyOverdueListObj.getOdOverhead();
									String odCharges = qyOverdueListObj.getOdCharges();
									String odForfeit = qyOverdueListObj.getOdForfeit();
									String odLatefee = qyOverdueListObj.getOdLatefee();
									String odDamages = qyOverdueListObj.getOdDamages();
									String ofCorpus = qyOverdueListObj.getOfCorpus();
									String ofInterest = qyOverdueListObj.getOfInterest();
									String ofOverhead = qyOverdueListObj.getOfOverhead();
									String ofCharges = qyOverdueListObj.getOfCharges();
									String ofForfeit = qyOverdueListObj.getOfForfeit();
									String ofLatefee = qyOverdueListObj.getOfLatefee();
									String ofDamages = qyOverdueListObj.getOfDamages();
									String odfCorpus = qyOverdueListObj.getOdfCorpus();
									String odfInterest = qyOverdueListObj.getOdfInterest();
									String odfOverhead = qyOverdueListObj.getOdfOverhead();
									String odfCharges = qyOverdueListObj.getOdfCharges();
									String odfForfeit = qyOverdueListObj.getOdfForfeit();
									String odfLatefee = qyOverdueListObj.getOdfLatefee();
									String odfDamages = qyOverdueListObj.getOdfDamages();

									overDueData.setLaonBalanceDataId(null);
									overDueData.setOverdueDays(Integer.parseInt(CommonUtil.isEmpty(overdueDays) ? "0"
											: overdueDays));
									overDueData.setOverduestart(CommonUtil.isEmpty(overdueStart) ? null : DateUtil
											.stringConvertDate(overdueStart, "yyyy-MM-dd"));
									overDueData.setOdCorpus(trancBigDecimal(odCorpus));
									overDueData.setOdInterest(trancBigDecimal(odInterest));
									overDueData.setOdOverhead(trancBigDecimal(odOverhead));
									overDueData.setOdCharges(trancBigDecimal(odCharges));
									overDueData.setOdForfeit(trancBigDecimal(odForfeit));
									overDueData.setOdLatefee(trancBigDecimal(odLatefee));
									overDueData.setOdDamages(trancBigDecimal(odDamages));
									overDueData.setOfCorpus(trancBigDecimal(ofCorpus));
									overDueData.setOfInterest(trancBigDecimal(ofInterest));
									overDueData.setOfOverhead(trancBigDecimal(ofOverhead));
									overDueData.setOfCharges(trancBigDecimal(ofCharges));
									overDueData.setOfForfeit(trancBigDecimal(ofForfeit));
									overDueData.setOfLatefee(trancBigDecimal(ofLatefee));
									overDueData.setOfDamages(trancBigDecimal(ofDamages));
									overDueData.setOdfCorpus(trancBigDecimal(odfCorpus));
									overDueData.setOdfInterest(trancBigDecimal(odfInterest));
									overDueData.setOdfOverhead(trancBigDecimal(odfOverhead));
									overDueData.setOdfCharges(trancBigDecimal(odfCharges));
									overDueData.setOdfForfeit(trancBigDecimal(odfForfeit));
									overDueData.setOdfLatefee(trancBigDecimal(odfLatefee));
									overDueData.setOdfDamages(trancBigDecimal(odfDamages));

									mapOverDueData.put(i + "-%" + j, overDueData);
									j++;
								}
							}
						}
						i++;
					}
				}
			}
			deleteLaonBalanceData();
			overDueDataService.deleteOverDueData();
			saveLaonBalanceDatas(laonBalanceDatas);
			Set<Entry<String, OverDueData>> entries = mapOverDueData.entrySet();
			Iterator<Entry<String, OverDueData>> iterator = entries.iterator();
			do {
				Entry<String, OverDueData> entry = iterator.next();
				String eKey = entry.getKey();
				OverDueData overDueData = entry.getValue();
				String[] keyArray = eKey.split("-%");
				String key = keyArray[0];
				System.out.println(key);
				LaonBalanceData laonBalanceData = mapLaonBalanceData.get(key);
				String getSysGuid = laonBalanceData.getSysGuid();
				System.out.println(getSysGuid);
				Long getLaonBalanceDataId = laonBalanceData.getLaonBalanceDataId();
				overDueData.setLaonBalanceDataId(getLaonBalanceDataId);
				overDueDatas.add(overDueData);
			} while (iterator.hasNext());
			overDueDataService.saveOverDueDatas(overDueDatas);
			message.setMsg("同步成功！");
		} else {
			message.setSuccess(false);
		}
		return message;
	}

	@Override
	public boolean deleteLaonBalanceData() {
		// TODO Auto-generated method stub
		return balanceDataDao.deleteLaonBalanceData();
	}

	@Override
	public boolean saveLaonBalanceDatas(List<LaonBalanceData> laonBalanceDatas) {
		// TODO Auto-generated method stub
		return balanceDataDao.saveLaonBalanceDatas(laonBalanceDatas);
	}

	private BigDecimal trancBigDecimal(String str) {
		BigDecimal bigDecimal = new BigDecimal(CommonUtil.isEmpty(str) ? "0" : str);
		return bigDecimal;
	}

	@Override
	public List<LoanTableData> queryLoanTableDatas(Map<String, String> queryMap) {
		// TODO Auto-generated method stub
		List<LoanTableData> loanTableDatas = new ArrayList<LoanTableData>();
		for (int i = 0; i < 9; i++) {
			LoanTableData loanTableData = new LoanTableData();
			loanTableData.setIndex(i);
			loanTableDatas.add(loanTableData);
		}
		LoanTableData loanTableData0 = loanTableDatas.get(0);
		LoanTableData loanTableData1 = loanTableDatas.get(1);
		LoanTableData loanTableData2 = loanTableDatas.get(2);
		LoanTableData loanTableData3 = loanTableDatas.get(3);
		LoanTableData loanTableData4 = loanTableDatas.get(4);
		LoanTableData loanTableData5 = loanTableDatas.get(5);
		LoanTableData loanTableData6 = loanTableDatas.get(6);
		LoanTableData loanTableData7 = loanTableDatas.get(7);
		LoanTableData loanTableData8 = loanTableDatas.get(8);
		LoanTableData loanTableDataLast = new LoanTableData();

		int cont0 = 0;
		int cont1 = 0;
		int cont2 = 0;
		int cont3 = 0;
		int cont4 = 0;
		int cont5 = 0;
		int cont6 = 0;
		int cont7 = 0;
		int cont8 = 0;
		int contLast = 0;

		BigDecimal defaultValue0 = new BigDecimal(0);
		BigDecimal defaultValue1 = new BigDecimal(0);
		BigDecimal defaultValue2 = new BigDecimal(0);
		BigDecimal defaultValue3 = new BigDecimal(0);
		BigDecimal defaultValue4 = new BigDecimal(0);
		BigDecimal defaultValue5 = new BigDecimal(0);
		BigDecimal defaultValue6 = new BigDecimal(0);
		BigDecimal defaultValue7 = new BigDecimal(0);
		BigDecimal defaultValue8 = new BigDecimal(0);
		BigDecimal defaultValueLast = new BigDecimal(0);

		BigDecimal loanBalance0 = new BigDecimal(0);
		BigDecimal loanBalance1 = new BigDecimal(0);
		BigDecimal loanBalance2 = new BigDecimal(0);
		BigDecimal loanBalance3 = new BigDecimal(0);
		BigDecimal loanBalance4 = new BigDecimal(0);
		BigDecimal loanBalance5 = new BigDecimal(0);
		BigDecimal loanBalance6 = new BigDecimal(0);
		BigDecimal loanBalance7 = new BigDecimal(0);
		BigDecimal loanBalance8 = new BigDecimal(0);
		BigDecimal loanBalanceLast = new BigDecimal(0);

		List<LoanBalanceQuertData> balanceQuertDatas = balanceDataDao.queryLoanTableDatas(queryMap);
		if (CommonUtil.isNotEmpty(balanceQuertDatas)) {
			for (LoanBalanceQuertData loanBalanceQuertData : balanceQuertDatas) {
				int getOverdueDays = loanBalanceQuertData.getOverdueDays();
				if (getOverdueDays == 0) {
					cont0 = cont0 + loanBalanceQuertData.getCustomerCount();
					defaultValue0 = defaultValue0.add(loanBalanceQuertData.getDefaultValue());
					loanBalance0 = loanBalance0.add(loanBalanceQuertData.getBalance());
					loanTableData0.setCustomerCount(cont0);
					loanTableData0.setDefaultValue(defaultValue0);
					loanTableData0.setLoanBalance(loanBalance0);
				} else if (getOverdueDays > 0 && getOverdueDays <= 7) {
					cont1 = cont1 + loanBalanceQuertData.getCustomerCount();
					defaultValue1 = defaultValue1.add(loanBalanceQuertData.getDefaultValue());
					loanBalance1 = loanBalance1.add(loanBalanceQuertData.getBalance());
					loanTableData1.setCustomerCount(cont1);
					loanTableData1.setDefaultValue(defaultValue1);
					loanTableData1.setLoanBalance(loanBalance1);

				} else if (getOverdueDays > 7 && getOverdueDays <= 15) {
					cont2 = cont2 + loanBalanceQuertData.getCustomerCount();
					defaultValue2 = defaultValue2.add(loanBalanceQuertData.getDefaultValue());
					loanBalance2 = loanBalance2.add(loanBalanceQuertData.getBalance());
					loanTableData2.setCustomerCount(cont2);
					loanTableData2.setDefaultValue(defaultValue2);
					loanTableData2.setLoanBalance(loanBalance2);
				} else if (getOverdueDays > 15 && getOverdueDays <= 30) {
					cont3 = cont3 + loanBalanceQuertData.getCustomerCount();
					defaultValue3 = defaultValue3.add(loanBalanceQuertData.getDefaultValue());
					loanBalance3 = loanBalance3.add(loanBalanceQuertData.getBalance());
					loanTableData3.setCustomerCount(cont3);
					loanTableData3.setDefaultValue(defaultValue3);
					loanTableData3.setLoanBalance(loanBalance3);
				} else if (getOverdueDays > 30 && getOverdueDays <= 60) {
					cont4 = cont4 + loanBalanceQuertData.getCustomerCount();
					defaultValue4 = defaultValue4.add(loanBalanceQuertData.getDefaultValue());
					loanBalance4 = loanBalance4.add(loanBalanceQuertData.getBalance());
					loanTableData4.setCustomerCount(cont4);
					loanTableData4.setDefaultValue(defaultValue4);
					loanTableData4.setLoanBalance(loanBalance4);
				} else if (getOverdueDays > 60 && getOverdueDays <= 90) {
					cont5 = cont5 + loanBalanceQuertData.getCustomerCount();
					defaultValue5 = defaultValue5.add(loanBalanceQuertData.getDefaultValue());
					loanBalance5 = loanBalance5.add(loanBalanceQuertData.getBalance());
					loanTableData5.setCustomerCount(cont5);
					loanTableData5.setDefaultValue(defaultValue5);
					loanTableData5.setLoanBalance(loanBalance5);
				} else if (getOverdueDays > 90 && getOverdueDays <= 180) {
					cont6 = cont6 + loanBalanceQuertData.getCustomerCount();
					defaultValue6 = defaultValue6.add(loanBalanceQuertData.getDefaultValue());
					loanBalance6 = loanBalance6.add(loanBalanceQuertData.getBalance());
					loanTableData6.setCustomerCount(cont6);
					loanTableData6.setDefaultValue(defaultValue6);
					loanTableData6.setLoanBalance(loanBalance6);
				} else if (getOverdueDays > 180 && getOverdueDays <= 365) {
					cont7 = cont7 + loanBalanceQuertData.getCustomerCount();
					defaultValue7 = defaultValue7.add(loanBalanceQuertData.getDefaultValue());
					loanBalance7 = loanBalance7.add(loanBalanceQuertData.getBalance());
					loanTableData7.setCustomerCount(cont7);
					loanTableData7.setDefaultValue(defaultValue7);
					loanTableData7.setLoanBalance(loanBalance7);
				} else if (getOverdueDays > 365) {
					cont8 = cont8 + loanBalanceQuertData.getCustomerCount();
					defaultValue8 = defaultValue8.add(loanBalanceQuertData.getDefaultValue());
					loanBalance8 = loanBalance8.add(loanBalanceQuertData.getBalance());
					loanTableData8.setCustomerCount(cont8);
					loanTableData8.setDefaultValue(defaultValue8);
					loanTableData8.setLoanBalance(loanBalance8);
				}
				contLast = contLast + loanBalanceQuertData.getCustomerCount();
				defaultValueLast = defaultValueLast.add(loanBalanceQuertData.getDefaultValue());
				loanBalanceLast = loanBalanceLast.add(loanBalanceQuertData.getBalance());
				loanTableDataLast.setCustomerCount(contLast);
				loanTableDataLast.setDefaultValue(defaultValueLast);
				loanTableDataLast.setLoanBalance(loanBalanceLast);
			}
			loanTableDatas.add(loanTableDataLast);
			for (LoanTableData loanTableData : loanTableDatas) {
				int getCustomerCount = loanTableData.getCustomerCount();
				BigDecimal getLoanBalance = loanTableData.getLoanBalance();
				BigDecimal getDefaultValue = loanTableData.getDefaultValue();

				if (getLoanBalance == null) {
					getLoanBalance = new BigDecimal(0);
				}

				if (getDefaultValue == null) {
					getDefaultValue = new BigDecimal(0);
				}

				BigDecimal customerCountRate = new BigDecimal(0);
				BigDecimal loanBalanceRate = new BigDecimal(0);
				BigDecimal defaultValueRate = new BigDecimal(0);

				customerCountRate = new BigDecimal(getCustomerCount).divide(new BigDecimal(contLast), 4,
						BigDecimal.ROUND_HALF_EVEN);
				if (loanBalanceLast == null) {
					loanBalanceLast = new BigDecimal(0);
				}
				if (loanBalanceLast.compareTo(new BigDecimal(0)) == 0) {
					loanBalanceRate = new BigDecimal(0);
				} else {
					loanBalanceRate = getLoanBalance.divide(loanBalanceLast, 4, BigDecimal.ROUND_HALF_EVEN);
				}
				if (loanBalanceLast.compareTo(new BigDecimal(0)) == 0) {
					defaultValueRate = new BigDecimal(0);
				} else {
					defaultValueRate = getDefaultValue.divide(loanBalanceLast, 4, BigDecimal.ROUND_HALF_EVEN);
				}

				loanTableData.setCustomerCountRate(customerCountRate);
				loanTableData.setDefaultValueRate(defaultValueRate);
				loanTableData.setLoanBalanceRate(loanBalanceRate);
			}
		} else {
			loanTableDatas.add(loanTableDataLast);
		}
		return loanTableDatas;
	}

	@Override
	public void insertSynchronization() {
		// TODO Auto-generated method stub
		System.out.println("----------------------------------------");
		System.out.println("----------------------------------------");
		System.out.println("----------------------------------------");
		System.out.println("insertSynchronization定时任务启动！！！！！！！！");
		System.out.println("----------------------------------------");
		System.out.println("----------------------------------------");
		System.out.println("----------------------------------------");
		insertSynchronizationLoan(null, null, null);
	}
}
