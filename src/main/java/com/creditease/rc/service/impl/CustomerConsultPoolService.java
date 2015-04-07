package com.creditease.rc.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.annotation.Resource;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;

import com.creditease.core.security.SpringSecurityUtils;
import com.creditease.core.security.User;
import com.creditease.rc.dao.ICustomerConsultPoolDao;
import com.creditease.rc.dao.INationalStandardCodeDAO;
import com.creditease.rc.domain.CodeTable;
import com.creditease.rc.domain.ConsultPoolLog;
import com.creditease.rc.domain.ConsultPoolLogDetail;
import com.creditease.rc.domain.CustomerConsultPool;
import com.creditease.rc.domain.CustomerConsultPoolLog;
import com.creditease.rc.domain.Message;
import com.creditease.rc.domain.NationalStandardCode;
import com.creditease.rc.framework.exception.AppBusinessException;
import com.creditease.rc.service.ICustomerConsultPoolLogService;
import com.creditease.rc.service.ICustomerConsultPoolService;
import com.creditease.rc.service.IDataDictionaryService;
import com.creditease.rc.service.INationalStandardCodeService;
import com.creditease.rc.service.IOperateLogService;
import com.creditease.rc.util.CommonUtil;
import com.creditease.rc.vo.DataDictionaryVo;

@Service
public class CustomerConsultPoolService implements ICustomerConsultPoolService {

	public static Map<Integer, NationalStandardCode> cityCodeNationStandardMap = new HashMap<Integer, NationalStandardCode>();

	public static Map<String, List<CodeTable>> codeMap = new HashMap<String, List<CodeTable>>();
	@Resource
	private ICustomerConsultPoolDao customerConsultPoolDao;

	@Resource
	private INationalStandardCodeDAO iNationalStandardCodeDAO;

	@Resource
	private ICustomerConsultPoolLogService customerConsultPoolLogService;

	@Resource
	private INationalStandardCodeService NationalStandardCodeService;

	@Resource
	private IDataDictionaryService dataDictionaryService;

	public CustomerConsultPool queryCustomerConsultPool(Long consultPoolId) {
		return customerConsultPoolDao.selectByPrimaryKey(consultPoolId);
	}

	/**
	 * 根据咨询池id查询受理咨询信息
	 */
	public CustomerConsultPool getCustomerConsultPoolByPrimaryKey(Long consultPoolId) {
		return customerConsultPoolDao.showAcceptAdvice(consultPoolId);
	}

	/**
	 * 更新咨询池信息
	 */
	public boolean updateCustomerConsultPool(CustomerConsultPool customerConsultPool, String invokeSource) {
		// 查询咨询池信息for日志
		CustomerConsultPool customerConsultPoolDB = customerConsultPoolDao.selectByPrimaryKey(customerConsultPool.getConsultPoolId());
		String trackStatus = getDictionaryValue(customerConsultPool.getTraceStatus(), "traceStatus");
		
		customerConsultPool.setReceiveDate(new Date());

		int row = customerConsultPoolDao.updateByPrimaryKey_(customerConsultPool);
		int rows = 0;
		if (row > 0) {
			customerConsultPool.setLoanOfficerId(Long.parseLong(SpringSecurityUtils.getCurrentUser().getUserId()));
			customerConsultPool.setLoanOfficerName(SpringSecurityUtils.getCurrentUser().getName_zh());
			if (customerConsultPool.getTraceStatus().equals("0")) {
				customerConsultPool.setAcceptConsultState("4");

			} else if (customerConsultPool.getTraceStatus().equals("1")) {
				customerConsultPool.setAcceptConsultState("5");

			} else if (customerConsultPool.getTraceStatus().equals("2")) {
				customerConsultPool.setAcceptConsultState("8");

			} else if (customerConsultPool.getTraceStatus().equals("3")) {
				customerConsultPool.setAcceptConsultState("9");

			} else if (customerConsultPool.getTraceStatus().equals("4")) {
				customerConsultPool.setAcceptConsultState("10");
			}
			
			if(!(trackStatus.equals("跟进中")) && !(trackStatus .equals("申请进件"))){
				customerConsultPool.setBorrowUse("");
			}

			rows = customerConsultPoolDao.updateByPrimaryKey_(customerConsultPool);
		}
		// 加日志
			if(CommonUtil.isNotEmpty(invokeSource) && invokeSource.equals("customer")){
				CustomerConsultPoolLog consultPoolLog = new CustomerConsultPoolLog();
				consultPoolLog.setOptModule("b00050");
				consultPoolLog.setCurrStatus(customerConsultPoolDB.getAcceptConsultState());
				consultPoolLog.setNextStatus(customerConsultPool.getAcceptConsultState());
				consultPoolLog.setConnectionId(customerConsultPool.getConsultPoolId());
				consultPoolLog.setConnectionCetegory("0");	
				consultPoolLog.setCurrConnectionDicSection("acceptConsultState");
				consultPoolLog.setNextConnectionDicSection("acceptConsultState");
				consultPoolLog.setOptDate(new Date());
				
				Map<String, String> contentMap = new HashMap<String, String>();
				contentMap.put("跟踪状态", trackStatus);
				contentMap.put("沟通记录", customerConsultPool.getCommunicationRecord() == null ? "" : customerConsultPool.getCommunicationRecord());
				customerConsultPoolLogService.insertOpt(consultPoolLog, contentMap);
			}
		
		return (rows > 0) ? true : false;
	}

	/**
	 * @return codeValue
	 */
	private String getDictionaryValue(String codeKey, String section) {
		// 跟踪状态codeValue
		String codeValue = "";
		DataDictionaryVo dictionaryVo = new DataDictionaryVo();
		dictionaryVo.setSection(section);
		dictionaryVo.setSelectType("E");// 查询启用的
		List<CodeTable> codeTables = dataDictionaryService.getCodeTableBySection(dictionaryVo);
		if (CommonUtil.isNotEmpty(codeKey)) {
			for (CodeTable codeTable : codeTables) {
				if (codeTable.getCodeKey().equals(codeKey)) {
					codeValue = codeTable.getCodeVlue();
					break;
				}
			}
		}
		return codeValue;
	}

	/**
	 * @author luohongjie
	 *         申请退回 将受理咨询中的状态改为 待退回确认
	 */
	@Override
	public boolean updateMarkingConsultStatus(Long consultPoolId) {
		int rows = customerConsultPoolDao.updateMarkingConsultStatus(consultPoolId);
		return (rows > 0) ? true : false;
	}

	/**
	 * @author luohongjie
	 *         退回确认 将营销咨询中的状态改为 已退回
	 */
	@Override
	public boolean updateMarkingConsultStatusConfirm(Long consultPoolId) {
		int rows = customerConsultPoolDao.updateMarkingConsultStatusConfirm(consultPoolId);
		return (rows > 0) ? true : false;
	}

	@Override
	public void uploadExcel(List<CustomerConsultPool> entityList) {
		customerConsultPoolDao.batchInsert(entityList);
	}

	public Map<String, String> isRepeat(List<String[]> list) {

		Map<String, String> map = new LinkedHashMap<String, String>();
		Map<String, String> tMap = new LinkedHashMap<String, String>();
		Integer city_code = null;
		Integer parent_id = null;
		if (!list.isEmpty()) {
			int counter = 0;
			boolean onceFlag = true;
			for (int i = 0; i < list.size(); i++) {
				String[] value = list.get(i);
				int cValue = value.length - 4;
				// 验证日期
				if (cValue == 0) {
					map.put("aa缺失[咨询日期]的行有", map.containsKey("aa缺失[咨询日期]的行有") ? map.get("aa缺失[咨询日期]的行有") + ","
							+ (i + 2) : (i + 2) + "");
				} else if (value[0] == null || "".equals(value[0].trim())) {

					map.put("aa缺失[咨询日期]的行有", map.containsKey("aa缺失[咨询日期]的行有") ? map.get("aa缺失[咨询日期]的行有") + ","
							+ (i + 2) : (i + 2) + "");

				} else if (!"".equals(value[0].trim()) && value[0] != null) {
					try {

						Date date = new Date(value[0]);
						date.setHours(0);
						Date nowDate = new Date();
						if (date.getTime() > nowDate.getTime()) {
							map.put("aa[咨询日期]大于当前日期的行有",
									map.containsKey("aa[咨询日期]大于当前日期的行有") ? map.get("aa[咨询日期]大于当前日期的行有") + "," + (i + 2)
											: (i + 2) + "");
						}

					} catch (Exception e) {
						// e.printStackTrace();
						map.put("aa[咨询日期]格式有误的行有", map.containsKey("aa[咨询日期]格式有误的行有") ? map.get("aa[咨询日期]格式有误的行有")
								+ "," + (i + 2) : (i + 2) + "");
					}

				}

				// 验证姓名
				if (cValue <= 0) {
					map.put("aa缺失[姓名]行有", map.containsKey("aa缺失[姓名]行有") ? map.get("aa缺失[姓名]行有") + "," + (i + 2)
							: (i + 2) + "");
				} else if (value[1] == null || "".equals(value[1].trim())) {
					map.put("aa缺失[客户姓名]的行有", map.containsKey("aa缺失[客户姓名]的行有") ? map.get("aa缺失[客户姓名]的行有") + ","
							+ (i + 2) : (i + 2) + "");
				} else if (!"".equals(value[1].trim()) && value[1] != null) {

					if (!value[1].trim().matches("[\u4e00-\u9fa5_a-zA-Z]{1,10}")) {
						map.put("aa[客户姓名]格式有误的行有", map.containsKey("aa[客户姓名]格式有误的行有") ? map.get("aa[客户姓名]格式有误的行有")
								+ "," + (i + 2) : (i + 2) + "");
					}
				}
				// 验证性别
				if (cValue <= 1) {
					map.put("aa缺失[性别]的行有", map.containsKey("aa缺失[性别]的行有") ? map.get("aa缺失[性别]的行有") + "," + (i + 2)
							: (i + 2) + "");
				} else if (!"".equals(value[2].trim()) && value[2] != null) {
					if (!value[2].equals("男") && !value[2].equals("女")) {
						map.put("aa[性别]有误的行有", map.containsKey("aa[性别]有误的行有") ? map.get("aa[性别]有误的行有") + "," + (i + 2)
								: (i + 2) + "");
					}
				} else if (value[2] == null || "".equals(value[2].trim())) {
					map.put("aa[性别]有误的行有", map.containsKey("aa[性别]有误的行有") ? map.get("aa[性别]有误的行有") + "," + (i + 2)
							: (i + 2) + "");
				}

				// 验证省市区
				if (cValue <= 2) {
					map.put("aa缺失[省]的行有", map.containsKey("aa缺失[省]的行有") ? map.get("aa缺失[省]的行有") + "," + (i + 2)
							: (i + 2) + "");
				} else if (cValue <= 3) {
					map.put("aa缺失[市]的行有", map.containsKey("aa缺失[市]的行有") ? map.get("aa缺失[市]的行有") + "," + (i + 2)
							: (i + 2) + "");
				} else if (cValue <= 4) {
					map.put("aa缺失[区]的行有", map.containsKey("aa缺失[区]的行有") ? map.get("aa缺失[区]的行有") + "," + (i + 2)
							: (i + 2) + "");
				} else if (value[3] == null || "".equals(value[3].trim())) {
					map.put("aa缺失[省]的行有", map.containsKey("aa缺失[省]的行有") ? map.get("aa缺失[省]的行有") + "," + (i + 2)
							: (i + 2) + "");
				} else if (value[4] == null || "".equals(value[4].trim())) {
					map.put("aa缺失[城市]的行有", map.containsKey("aa缺失[城市]的行有") ? map.get("aa缺失[城市]的行有") + "," + (i + 2)
							: (i + 2) + "");
				} else if (value[5] == null || "".equals(value[5].trim())) {
					map.put("aa缺失[区/县]的行有", map.containsKey("aa缺失[区/县]的行有") ? map.get("aa缺失[区/县]的行有") + "," + (i + 2)
							: (i + 2) + "");
				} else {
					// 判断省市区名称是否存在，存在则保存省市区国标码
					NationalStandardCode standardCode;
					boolean nextFlag = true;
					// 省存在性
					standardCode = NationalStandardCodeService.selectByCityName(value[3]);
					if (standardCode == null) {
						map.put("aa[省]名称不存在的行有", map.containsKey("aa[省]名称不存在的行有") ? map.get("aa[省]名称不存在的行有") + ","
								+ (i + 2) : (i + 2) + "");
						nextFlag = false;
					}
					// 市存在性
					standardCode = NationalStandardCodeService.selectByCityName(value[4]);
					if (standardCode == null) {
						map.put("aa[城市]名称不存在的行有", map.containsKey("aa[城市]名称不存在的行有") ? map.get("aa[城市]名称不存在的行有") + ","
								+ (i + 2) : (i + 2) + "");
						nextFlag = false;
					}
					// 区/县存在性
					standardCode = NationalStandardCodeService.selectByCityName(value[4]);
					if (standardCode == null) {
						map.put("aa[区/县]名称不存在的行有", map.containsKey("aa[区/县]名称不存在的行有") ? map.get("aa[区/县]名称不存在的行有")
								+ "," + (i + 2) : (i + 2) + "");
						nextFlag = false;
					}

					if (nextFlag && !"".equals(value[4].trim()) && value[4] != null) {
						boolean b = true;
						if (cityCodeNationStandardMap.size() == 0) {
							cityCodeNationStandardMap = NationalStandardCodeService.cityCodeNationStandardMap();
						}

						for (Map.Entry<Integer, NationalStandardCode> cityEntry : cityCodeNationStandardMap.entrySet()) {
							NationalStandardCode NationalStandardCodeCity = cityEntry.getValue();
							if (NationalStandardCodeCity.getCityName().equals(value[4])) {

								if (NationalStandardCodeCity.getParentId() != null) {

									value[value.length - 3] = NationalStandardCodeCity.getParentId().toString();
								}

								value[value.length - 2] = NationalStandardCodeCity.getCityCode().toString();
								// 区
								if (!"".equals(value[5].trim()) && value[5] != null) {
									for (Map.Entry<Integer, NationalStandardCode> areaEntry : cityCodeNationStandardMap
											.entrySet()) {
										NationalStandardCode NationalStandardCode = areaEntry.getValue();
										if (NationalStandardCode.getCityName().equals(value[5].trim())) {
											city_code = NationalStandardCode.getCityCode();
											parent_id = NationalStandardCode.getParentId();
											NationalStandardCode = cityCodeNationStandardMap.get(parent_id);
											if (NationalStandardCode.getCityName().equals(value[4].trim())) {

												value[value.length - 1] = city_code.toString();
												b = false;
												break;
											} else {
												parent_id = NationalStandardCode.getParentId();
												NationalStandardCode = cityCodeNationStandardMap.get(parent_id);
												if (NationalStandardCode.getCityName().equals(value[4].trim())) {

													value[value.length - 1] = city_code.toString();
													b = false;
													break;
												}
											}
										}
									}

									if (b) {
										map.put("aa[区/县]名称不存在的行有",
												map.containsKey("aa[区/县]名称不存在的行有") ? map.get("aa[区/县]名称不存在的行有") + ","
														+ (i + 2) : (i + 2) + "");
									}
								}
								b = false;
								break;
							}
						}
						if (b) {
							map.put("aa[城市]名称不存在的行有", map.containsKey("aa[城市]名称不存在的行有") ? map.get("aa[城市]名称不存在的行有")
									+ "," + (i + 2) : (i + 2) + "");
						}

					}
				}
				// 验证手机号
				if (cValue <= 5) {
					map.put("aa缺失[手机号]的行有", map.containsKey("aa缺失[手机号]的行有") ? map.get("aa缺失[手机号]的行有") + "," + (i + 2)
							: (i + 2) + "");
				} else if (value[6] == null || "".equals(value[6].trim())) {
					map.put("aa缺失[手机号]的行有", map.containsKey("aa缺失[手机号]的行有") ? map.get("aa缺失[手机号]的行有") + "," + (i + 2)
							: (i + 2) + "");
				} else if (value[6] != null && !"".equals(value[6].trim())) {
					if (onceFlag) {
						// 对于手机号重复
						for (int j = 1; j < list.size(); j++) {
							if (list.get(0)[6].trim().equals(list.get(j)[6].trim())) {
								counter++;
							}
						}
						onceFlag = false;
						tMap.put("手机号" + list.get(0)[6] + "重复咨询 ", counter + " 次");
					}
					if (!value[6].trim().matches("^(13|15|18|14|17)\\d{9}$")) {
						map.put("aa[手机号]格式有误的行有", map.containsKey("aa[手机号]格式有误的行有") ? map.get("aa[手机号]格式有误的行有") + ","
								+ (i + 2) : (i + 2) + "");
					}
					/*
					 * List<String> mobilePhonelist = selectMoilePhonebyDate();
					 * 
					 * Set<String> set = new HashSet<String>(mobilePhonelist);
					 * 
					 * if (set.contains(value[6])) {
					 * map.put("aa[手机号" + value[6] + "]30天内重复的上传的行有",
					 * map.containsKey("aa[手机号" + value[6] + "]30天内重复的上传的行有") ? map.get("aa[手机号" + value[6]
					 * + "]30天内重复的上传的行有")
					 * + "," + (i + 2) : (i + 2) + "");
					 * }
					 */
				}

				/*
				 * // 验证固定电话-取消验证
				 * if (value[6] != null && !"".equals(value[6].trim())) {
				 * 
				 * if (!value[6].trim().matches("((\\d{3,4})-(\\d{7,8}))$")) {
				 * map.put("aa[固定电话]格式有误的行有", map.containsKey("aa[固定电话]格式有误的行有") ? map.get("aa[固定电话]格式有误的行有")
				 * + "," + (i + 2) : (i + 2) + "");
				 * }
				 * }
				 */
				// 验证年龄
				if (cValue <= 6) {
					map.put("aa缺失[年龄]的行有", map.containsKey("aa缺失[年龄]的行有") ? map.get("aa缺失[年龄]的行有") + "," + (i + 2)
							: (i + 2) + "");
				} else if (value[7] != null && !"".equals(value[7].trim())) {
					if (!value[7].trim().matches("^[2-9][0-9]|100")) {
						map.put("aa[年龄]不匹配的行有", map.containsKey("aa[年龄]不匹配的行有") ? map.get("aa[年龄]不匹配的行有") + ","
								+ (i + 2) : (i + 2) + "");
					}
				}
				// 验证借款用途
				if (cValue <= 7) {
					map.put("aa缺失[借款用途]的行有", map.containsKey("aa缺失[借款用途]的行有") ? map.get("aa缺失[借款用途]的行有") + ","
							+ (i + 2) : (i + 2) + "");
				} else if (value[8] == null || "".equals(value[8].trim())) {
					map.put("aa缺失[借款用途]的行有", map.containsKey("aa缺失[借款用途]的行有") ? map.get("aa缺失[借款用途]的行有") + ","
							+ (i + 2) : (i + 2) + "");
				} else if (value[8] != null && !"".equals(value[8].trim())) {
					if (!("零售/批发".equals(value[8])) && !("加工/制造".equals(value[8])) && !("运输".equals(value[8]))
							&& !("工程".equals(value[8])) && !("农业经营".equals(value[8])) && !("家庭消费".equals(value[8]))
							&& !("服务业".equals(value[8]))) {
						map.put("aa[借款用途]不匹配的行有", map.containsKey("aa[借款用途]不匹配的行有") ? map.get("aa[借款用途]不匹配的行有") + ","
								+ (i + 2) : (i + 2) + "");
					}
				}
				// 验证咨询金额
				if (cValue <= 8) {
					map.put("aa缺失[咨询金额]的行有", map.containsKey("aa缺失[咨询金额]的行有") ? map.get("aa缺失[咨询金额]的行有") + ","
							+ (i + 2) : (i + 2) + "");
				} else if (value[9] == null || "".equals(value[9].trim())) {
					map.put("aa缺失[咨询金额]的行有", map.containsKey("aa缺失[咨询金额]的行有") ? map.get("aa缺失[咨询金额]的行有") + ","
							+ (i + 2) : (i + 2) + "");
				} else if (value[9] != null && !"".equals(value[9].trim())) {
					try {
						if (Double.parseDouble(value[9].trim()) > 99 || Double.parseDouble(value[9]) < 0) {

							map.put("aa[咨询金额]不匹配的行有", map.containsKey("aa[咨询金额]不匹配的行有") ? map.get("aa[咨询金额]不匹配的行有")
									+ "," + (i + 2) : (i + 2) + "");
						}
					} catch (Exception e) {
						map.put("aa[咨询金额]不匹配的行有", map.containsKey("aa[咨询金额]不匹配的行有") ? map.get("aa[咨询金额]不匹配的行有") + ","
								+ (i + 2) : (i + 2) + "");
					}
				}

				// 验证信息来源
				if (cValue <= 9) {
					map.put("aa缺失[信息来源]的行有", map.containsKey("aa缺失[信息来源]的行有") ? map.get("aa缺失[信息来源]的行有") + ","
							+ (i + 2) : (i + 2) + "");
				} else if (value[10] == null || "".equals(value[10].trim())) {
					map.put("aa缺失[信息来源]的行有", map.containsKey("aa缺失[信息来源]的行有") ? map.get("aa缺失[信息来源]的行有") + ","
							+ (i + 2) : (i + 2) + "");
				} else if (value[10] != null && !"".equals(value[10].trim())) {
					if (!("广告".equals(value[10])) && !("市场活动".equals(value[10])) && !("客户经理介绍".equals(value[10]))
							&& !("朋友介绍".equals(value[10]))) {
						map.put("aa[信息来源]不匹配的行有", map.containsKey("aa[信息来源]不匹配的行有") ? map.get("aa[信息来源]不匹配的行有") + ","
								+ (i + 2) : (i + 2) + "");
					}
				}
				// 验证咨询方式
				if (cValue <= 10) {
					map.put("aa缺失[咨询方式]的行有", map.containsKey("aa缺失[咨询方式]的行有") ? map.get("aa缺失[咨询方式]的行有") + ","
							+ (i + 2) : (i + 2) + "");
				} else if (value[11] == null || "".equals(value[11].trim())) {
					map.put("aa缺失[咨询方式]的行有", map.containsKey("aa缺失[咨询方式]的行有") ? map.get("aa缺失[咨询方式]的行有") + ","
							+ (i + 2) : (i + 2) + "");
				} else if (value[11] != null && !"".equals(value[11].trim())) {
					if (!("农贷官网在线咨询".equals(value[11])) && !("农贷微信咨询".equals(value[11]))
							&& !("400电话".equals(value[11]))) {
						map.put("aa[咨询方式]不匹配的行有", map.containsKey("aa[咨询方式]不匹配的行有") ? map.get("aa[咨询方式]不匹配的行有") + ","
								+ (i + 2) : (i + 2) + "");
					}
				}
				/*
				 * // 验证经营年限-取消
				 * if (value[8] != null && !"".equals(value[8].trim())) {
				 * if (!"不满半年".equals(value[8]) && !"半年到一年（不含）".equals(value[8]) && !"一年到两年（不含）".equals(value[8])
				 * && !"两年到三年（不含）".equals(value[8]) && !"三年以上".equals(value[8])) {
				 * map.put("aa[经营年限]不匹配的行有", map.containsKey("aa[经营年限]不匹配的行有") ? map.get("aa[经营年限]不匹配的行有") + ","
				 * + (i + 2) : (i + 2) + "");
				 * }
				 * }
				 */

				/*
				 * // 验证是否有营养执照-取消
				 * if (value[9] != null && !"".equals(value[9].trim())) {
				 * if (!value[9].equals("是") && !"否".equals(value[9])) {
				 * map.put("aa[是否有营业执照]有误的行有", map.containsKey("aa[是否有营业执照]有误的行有") ? map.get("aa[是否有营业执照]有误的行有")
				 * + "," + (i + 2) : (i + 2) + "");
				 * }
				 * }
				 */

				/*
				 * // 验证借款额度-取消
				 * if (value[10] != null && !"".equals(value[10].trim())) {
				 * if (Double.parseDouble(value[10]) > 50 || Double.parseDouble(value[10]) < 0) {
				 * 
				 * map.put("aa[借款额度]不匹配的行有", map.containsKey("aa[借款额度]不匹配的行有") ? map.get("aa[借款额度]不匹配的行有") + ","
				 * + (i + 2) : (i + 2) + "");
				 * }
				 * }
				 */

				/*
				 * // 验证推荐渠道
				 * if (value[11] == null || "".equals(value[11].trim())) {
				 * map.put("aa缺失[推荐渠道]的行有", map.containsKey("aa缺失[推荐渠道]的行有") ? map.get("aa缺失[推荐渠道]的行有") + ","
				 * + (i + 2) : (i + 2) + "");
				 * }
				 * if (value[11] != null && !"".equals(value[11].trim())) {
				 * if (!("400客服".equals(value[11])) && !("宜人贷客服".equals(value[11])) && !("小微官网表单申请".equals(value[11]))
				 * && !("小微官网在线咨询".equals(value[11])) && !("小微微信咨询".equals(value[11]))
				 * && !("批量渠道".equals(value[11]))) {
				 * map.put("aa[推荐渠道]不匹配的行有", map.containsKey("aa[推荐渠道]不匹配的行有") ? map.get("aa[推荐渠道]不匹配的行有") + ","
				 * + (i + 2) : (i + 2) + "");
				 * }
				 * }
				 */
				// 验证备注
				if (cValue <= 11) {
					map.put("aa缺失[备注列]的行有", map.containsKey("aa缺失[备注列]行有") ? map.get("aa缺失[备注列]行有") + "," + (i + 2)
							: (i + 2) + "");
				} else if (value[12] != null && !"".equals(value[12].trim())) {
					if (value[12].length() > 200) {
						map.put("aa[备注]超长的行有", map.containsKey("aa[备注]超长的行有") ? map.get("aa[备注]超长的行有") + "," + (i + 2)
								: (i + 2) + "");
					}
				}

			}

			if (map.size() == 0 || map == null) {
				map.put("成功上传", list.size() + "条");
				if (counter != 0) {
					map.putAll(tMap);
				}
			}

		}

		return map;
	}

	/**
	 * 读取excel文件数据
	 */
	@Override
	public List<String[]> getAllData(Workbook workbook, int sheetIndex) {
		List<String[]> dataList = new ArrayList<String[]>(100);
		int columnNum = 0;
		Sheet sheet = workbook.getSheetAt(sheetIndex);
		if (sheet.getRow(0) != null) {
			columnNum = sheet.getRow(0).getLastCellNum() - sheet.getRow(0).getFirstCellNum();
		}
		if (columnNum > 0) {
			for (Row row : sheet) {
				// 跳过第一行
				if (row.getRowNum() == 0) {
					continue;
				}
				String[] singleRow = new String[columnNum + 3];
				int n = 0;
				for (int i = 0; i < columnNum; i++) {
					Cell cell = row.getCell(i, Row.CREATE_NULL_AS_BLANK);
					switch (cell.getCellType()) {
					case Cell.CELL_TYPE_BLANK:
						singleRow[n] = "";
						break;
					case Cell.CELL_TYPE_BOOLEAN:
						singleRow[n] = Boolean.toString(cell.getBooleanCellValue());
						break;
					// 数值
					case Cell.CELL_TYPE_NUMERIC:
						if (DateUtil.isCellDateFormatted(cell)) {
							singleRow[n] = String.valueOf(cell.getDateCellValue());
						} else {
							cell.setCellType(Cell.CELL_TYPE_STRING);
							String temp = cell.getStringCellValue();
							// 判断是否包含小数点，如果不含小数点，则以字符串读取，如果含小数点，则转换为Double类型的字符串
							if (temp.indexOf(".") > -1) {
								singleRow[n] = String.valueOf(new Double(temp)).trim();
							} else {
								singleRow[n] = temp.trim();
							}
						}
						break;
					case Cell.CELL_TYPE_STRING:
						singleRow[n] = cell.getStringCellValue().trim();
						break;
					case Cell.CELL_TYPE_ERROR:
						singleRow[n] = "";
						break;
					case Cell.CELL_TYPE_FORMULA:
						cell.setCellType(Cell.CELL_TYPE_STRING);
						singleRow[n] = cell.getStringCellValue();
						if (singleRow[n] != null) {
							singleRow[n] = singleRow[n].replaceAll("#N/A", "").trim();
						}
						break;

					default:
						singleRow[n] = "";
						break;
					}
					n++;
				}
				// if ("".equals(singleRow[0])) {
				// continue;
				// }// 如果第一行为空，跳过
				dataList.add(singleRow);
			}
		}
		return dataList;
	}

	/**
	 * 根据咨询id，查询咨询池信息
	 */
	public CustomerConsultPool getConsultPoolByForeignKey(Long customerConsultId) {
		return customerConsultPoolDao.selectByForeignKey(customerConsultId);
	}

	@Override
	public List<String> selectMoilePhonebyDate() {
		return customerConsultPoolDao.selectMoilePhonebyDate();
	}

	@Override
	public Map<String, List<CodeTable>> getCodeTableMapBySection(String[] sectionArr) {
		if (codeMap.isEmpty()) {
			DataDictionaryVo dictionaryVo;
			for (String sec : sectionArr) {
				dictionaryVo = new DataDictionaryVo();
				dictionaryVo.setSection(sec.trim());
				codeMap.put(sec, customerConsultPoolDao.getCodeTableBySection(dictionaryVo));
			}
		}
		return codeMap;
	}

	@Override
	public List<CustomerConsultPool> queryCustomerConsultPoolNeedDistribute() {
		// TODO Auto-generated method stub
		return customerConsultPoolDao.queryCustomerConsultPoolNeedDistribute();
	}

	@Override
	public Map<String, Integer> queryDepartmentIdandCountMap(String queryDepartmentIdsString) {
		// TODO Auto-generated method stub
		Map<String, Integer> departmentIdandCountMap = new HashMap<String, Integer>();
		List<Map<String, String>> queryResulMapList = customerConsultPoolDao
				.queryDepartmentIdandCountMap(queryDepartmentIdsString);

		for (Map<String, String> map : queryResulMapList) {
			String departmentId = map.get("ID");
			String count = map.get("COUNT");
			int c = 0;
			if (CommonUtil.isNotEmpty(count)) {
				c = Integer.parseInt(count);
			}
			departmentIdandCountMap.put(departmentId, c);
		}
		return departmentIdandCountMap;
	}

	@Override
	public Message updateOperationFlag(Set<String> phoneSet) {
		// TODO Auto-generated method stub
		Message message = new Message();
		boolean success = customerConsultPoolDao.updateOperationFlag(phoneSet);
		message.setSuccess(success);
		return message;
	}

	@Override
	public boolean updateAcceptConsultStatusCancel(Long consultPoolId) {
		int rows = customerConsultPoolDao.updateAcceptConsultStatusCancel(consultPoolId);
		return (rows > 0) ? true : false;
	}

	@Override
	public Message updateCustomerConsultPoolForAutoDistribute(List<CustomerConsultPool> customerConsultPoolsForUpdate) {
		// TODO Auto-generated method stub
		Message message = new Message();
		boolean success = customerConsultPoolDao
				.updateCustomerConsultPoolForAutoDistribute(customerConsultPoolsForUpdate);
		message.setSuccess(success);
		return message;
	}

	@Override
	public List<CustomerConsultPool> queryCustomerConsultPoolListByConsultPoolIds(Long[] consultPoolIds) {
		// TODO Auto-generated method stub
		List<CustomerConsultPool> customerConsultPools = new ArrayList<CustomerConsultPool>();
		if (consultPoolIds.length != 0) {
			String consultPoolIdString = "";
			for (Long consultPoolId : consultPoolIds) {
				consultPoolIdString = consultPoolIdString + "," + consultPoolId;
			}
			consultPoolIdString = consultPoolIdString.substring(1);
			customerConsultPools = customerConsultPoolDao
					.queryCustomerConsultPoolListByConsultPoolIds(consultPoolIdString);
		}
		return customerConsultPools;
	}

	@Override
	public Message updateCustomerConsultPoolByListForDistributeOrders(
			List<CustomerConsultPool> customerConsultPoolsForUpdate) {
		// TODO Auto-generated method stub
		Message message = new Message();
		boolean success = customerConsultPoolDao
				.updateCustomerConsultPoolByListForDistributeOrders(customerConsultPoolsForUpdate);
		message.setSuccess(success);
		return message;
	}

	@Override
	public Message updateComponentOperationFlagByPhone(Set<String> phoneSet) {
		// TODO Auto-generated method stub
		Message message = new Message();
		boolean success = customerConsultPoolDao.updateComponentOperationFlagByPhone(phoneSet);
		message.setSuccess(success);
		return message;
	}

	@Override
	public Message insertDistributLog(List<CustomerConsultPool> customerConsultPoolsForDistribut, String operationType,
			String distributeType) {
		Message message = new Message();
		if (CommonUtil.isEmpty(operationType)) {
			System.out.println("operationType is null");
			throw new AppBusinessException("operationType is null");
		}
		if (CommonUtil.isEmpty(distributeType)) {
			System.out.println("distributeType is null");
			throw new AppBusinessException("distributeType is null");
		}
		if (CommonUtil.isEmpty(customerConsultPoolsForDistribut)) {
			System.out.println("分配或分件List is null");
			throw new AppBusinessException("分配或分件List is null");
		}
		User user = SpringSecurityUtils.getCurrentUser();
		if (user == null) {
			System.out.println("没有取到当前登陆人");
			throw new AppBusinessException("没有取到当前登陆人");
		}
		String getUserId = user.getUserId();
		String getName_zh = user.getName_zh();
		if (CommonUtil.isEmpty(getUserId) || CommonUtil.isEmpty(getName_zh)) {
			System.out.println("没有取到当前登陆人ID或姓名");
			throw new AppBusinessException("没有取到当前登陆人ID或姓名");
		}
		ConsultPoolLog consultPoolLog = new ConsultPoolLog();

        if (operationType.equals("0")) {
            if (distributeType.equals("1")) {
                consultPoolLog.setRemark("系统自动分配");
            } else if (distributeType.equals("0")) {
                consultPoolLog.setRemark("手动分配");
            }
        }
        consultPoolLog.setBusinessType(operationType);
		consultPoolLog.setDistributeType(distributeType);
		consultPoolLog.setOperateDate(new Timestamp(new Date().getTime()));
		consultPoolLog.setOperatorId(getUserId);
		consultPoolLog.setOperatorName(getName_zh);

		customerConsultPoolDao.insertConsultPoolLog(consultPoolLog);

		Long getConsultPoolDistributeId = consultPoolLog.getConsultPoolDistributeId();
		if (getConsultPoolDistributeId == null) {
			System.out.println("getConsultPoolDistributeId is null");
			throw new AppBusinessException("getConsultPoolDistributeId is null");
		}
		List<ConsultPoolLogDetail> consultPoolLogDetails = new ArrayList<ConsultPoolLogDetail>();

		for (CustomerConsultPool customerConsultPool : customerConsultPoolsForDistribut) {
			Long getConsultPoolId = customerConsultPool.getConsultPoolId();
			String getDistributeDepartmentId = customerConsultPool.getDistributeDepartmentId();
			Long distributeDepartmentId = CommonUtil.isEmpty(getDistributeDepartmentId) ? null : Long
					.parseLong(getDistributeDepartmentId);
			String getDistributeDepartmentName = customerConsultPool.getDistributeDepartmentName();
			Long getLoanOfficerId = customerConsultPool.getLoanOfficerId();
			String getLoanOfficerName = customerConsultPool.getLoanOfficerName();
			ConsultPoolLogDetail consultPoolLogDetail = new ConsultPoolLogDetail();

			consultPoolLogDetail.setConsultPoolDistributeId(getConsultPoolDistributeId);
			consultPoolLogDetail.setConsultPoolId(getConsultPoolId);
			consultPoolLogDetail.setDistributeDepartmentId(distributeDepartmentId);
			consultPoolLogDetail.setDistributeDepartmentName(getDistributeDepartmentName);
			consultPoolLogDetail.setDistributeLoanOfficerId(getLoanOfficerId);
			consultPoolLogDetail.setDistributeLoanOfficerName(getLoanOfficerName);
			consultPoolLogDetails.add(consultPoolLogDetail);
		}

		boolean success = customerConsultPoolDao.insertConsultPoolLogDetail(consultPoolLogDetails);
		message.setSuccess(success);
		return message;
	}

	@Override
	public List<CustomerConsultPool> queryCustomerConsultPoolByBorrowerServiceAppId(Integer borrowerServiceAppId) {
		// TODO Auto-generated method stub
		return customerConsultPoolDao.queryCustomerConsultPoolByBorrowerServiceAppId(borrowerServiceAppId);
	}

	@Override
	public List<CustomerConsultPool> queryCustomerConsultPoolByCreditapplicationId(Long creditapplicationId) {
		// TODO Auto-generated method stub
		return customerConsultPoolDao.queryCustomerConsultPoolByCreditapplicationId(creditapplicationId);
	}

	@Override
	public Message updateAcceptConsultStateByCreditApplicationId(Integer creditApplicationId) {
		Message message = new Message();
		// TODO Auto-generated method stub
		boolean s = customerConsultPoolDao.updateAcceptConsultStateByCreditApplicationId(creditApplicationId);
		message.setSuccess(s);
		return message;
	}
}
