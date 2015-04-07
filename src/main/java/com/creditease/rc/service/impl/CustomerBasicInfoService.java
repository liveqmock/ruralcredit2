package com.creditease.rc.service.impl;

import java.text.ParseException;
import java.util.*;

import javax.annotation.Resource;

import com.creditease.rc.domain.*;
import com.creditease.rc.service.*;
import com.creditease.rc.util.CommonUtil;
import com.creditease.rc.vo.*;
import org.springframework.stereotype.Service;

import com.creditease.core.security.SpringSecurityUtils;
import com.creditease.rc.dao.ICustomerBasicInfoDao;
import com.creditease.rc.dao.ICustomerHistoryInfoDao;
import com.creditease.rc.dao.IRural2CreditDao;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.common.Constants;
import com.creditease.rc.service.IFirstSystemService;
/**
 * 客户基本信息服务类
 * 
 * @author zhangman
 * 
 */

@Service
public class CustomerBasicInfoService implements ICustomerBasicInfoService {
    @Resource
	private ICustomerBasicInfoDao customerBasicInfoDao;
	@Resource
	private ICustomerHistoryInfoDao customerHistoryInfoDao;
	@Resource
	private IBorrowerServiceAppService borrowerServiceAppService;
	@Resource
	private ICustomerConsultService customerConsultService;
	@Resource
	private ICreditApplicationService creditApplicationService;

	@Resource
	private ICustomerConsultPoolService customerConsultPoolService;
    @Resource
    private IRefuseReasonService refuseReasonService;
    @Resource
    private IFirstSystemService firstSystemService;
	@Resource
	private IRural2CreditDao rural2CreditDao;
	@Override
	public Pagination listCustomerBasicInfos(
			CustomerBasicInfo customerBasicInfo, String fuzzyValue,
			Pagination pagination) {
		Pagination paginationReturn = new Pagination();
		if (null != fuzzyValue && !("".equals(fuzzyValue))) {
			paginationReturn = customerBasicInfoDao.selectFuzzy(fuzzyValue,
					pagination);
		} else {
			paginationReturn = customerBasicInfoDao.selectCustomerBasic(
					customerBasicInfo, pagination);
		}
		List<CustomerBasicInfoVo> customerBasicInfoVos = paginationReturn
				.getRows();
		for (CustomerBasicInfoVo customerBasicInfoVo : customerBasicInfoVos) {
			Map customerBasicInfoVoMap = customerBasicInfoDao
					.selectAutus(customerBasicInfoVo.getCustomerBasicId());
			if (customerBasicInfoVoMap != null) {
				if (customerBasicInfoVoMap.get("AUDITSTATUS") != null) {
					customerBasicInfoVo.setAuditStatus(customerBasicInfoVoMap
							.get("AUDITSTATUS").toString());
				}
				if (customerBasicInfoVoMap.get("AUDITSTATUSSHOW") != null) {
					customerBasicInfoVo
							.setAuditStatusShow(customerBasicInfoVoMap.get(
									"AUDITSTATUSSHOW").toString());
				}
			} else {
				customerBasicInfoVo.setAuditStatusShow("未申请");
			}
		}
		return paginationReturn;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.creditease.rc.service.ICustomerBasicInfoService#addCustomerBasicInfo
	 * (com.creditease.rc.domain.CustomerBasicInfo)
	 */
	@Override
	public boolean addCustomerBasicInfo(CustomerBasicInfo customerBasicInfo) {
		customerBasicInfo.setOperateId(Integer.valueOf(SpringSecurityUtils
				.getCurrentUser().getUserId()));
		customerBasicInfo.setOperateName(SpringSecurityUtils.getCurrentUser()
				.getName_zh());
		customerBasicInfoDao.insert(customerBasicInfo);
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.creditease.rc.service.ICustomerBasicInfoService#updateCustomerBasicInfo
	 * (com.creditease.rc.domain.CustomerBasicInfo)
	 */
	@Override
	public boolean updateCustomerBasicInfo(CustomerBasicInfo customerBasicInfo) {
		// 按主键查询
		CustomerBasicInfo tempcustomerBasic = customerBasicInfoDao
				.selectByPrimaryKey(customerBasicInfo.getCustomerBasicId());
		CustomerHistoryInfo tempcustomerHistory = new CustomerHistoryInfo();
		tempcustomerHistory.setChangeTime(new Date());
		tempcustomerHistory.setCredentialsNumber(tempcustomerBasic
				.getCredentialsNumber());
		tempcustomerHistory.setCredentialsType(tempcustomerBasic
				.getCredentialsType());
		tempcustomerHistory.setCustomerBasicId(tempcustomerBasic
				.getCustomerBasicId());
		tempcustomerHistory.setFormer(tempcustomerBasic.getFormer());
		tempcustomerHistory.setGender(tempcustomerBasic.getGender());
		tempcustomerHistory.setHighestEducation(tempcustomerBasic
				.getHighestEducation());
		tempcustomerHistory.setMaritalStatus(tempcustomerBasic
				.getMaritalStatus());
		tempcustomerHistory.setMobilephone(tempcustomerBasic.getMobilephone());
		tempcustomerHistory.setName(tempcustomerBasic.getName());
		tempcustomerHistory.setNational(tempcustomerBasic.getNational());
		tempcustomerHistory.setOperateId(tempcustomerBasic.getOperateId());
		tempcustomerHistory.setOperateName(tempcustomerBasic.getOperateName());
		tempcustomerHistory
				.setPersonIncome(tempcustomerBasic.getPersonIncome());
		tempcustomerHistory.setPresentAddress(tempcustomerBasic
				.getPresentAddress());
		tempcustomerHistory.setRemark(tempcustomerBasic.getRemark());
		tempcustomerHistory.setResidenceAddress(tempcustomerBasic
				.getPresentAddress());
		tempcustomerHistory.setTelephone(tempcustomerBasic.getTelephone());
		customerHistoryInfoDao.insert(tempcustomerHistory);

		customerBasicInfo.setOperateId(Integer.valueOf(SpringSecurityUtils
				.getCurrentUser().getUserId()));
		customerBasicInfo.setOperateName(SpringSecurityUtils.getCurrentUser()
				.getName_zh());
		int count = customerBasicInfoDao
				.updateByPrimaryKeySelective(customerBasicInfo);
		if (count > 0) {
			return true;
		} else {
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.creditease.rc.service.ICustomerBasicInfoService#
	 * deleteCustomerBasicInfoById(int)
	 */
	@Override
	public boolean deleteCustomerBasicInfoById(int customerBasicInfoId) {
		CustomerBasicInfo tempcustomerBasic = customerBasicInfoDao
				.selectByPrimaryKey(customerBasicInfoId);
		CustomerHistoryInfo tempcustomerHistory = new CustomerHistoryInfo();
		int count = 0;
		try {
			tempcustomerHistory.setChangeTime(new Date());
			tempcustomerHistory.setCredentialsNumber(tempcustomerBasic
					.getCredentialsNumber());
			tempcustomerHistory.setCredentialsType(tempcustomerBasic
					.getCredentialsType());
			tempcustomerHistory.setFormer(tempcustomerBasic.getFormer());
			tempcustomerHistory.setGender(tempcustomerBasic.getGender());
			tempcustomerHistory.setHighestEducation(tempcustomerBasic
					.getHighestEducation());
			tempcustomerHistory.setMaritalStatus(tempcustomerBasic
					.getMaritalStatus());
			tempcustomerHistory.setMobilephone(tempcustomerBasic
					.getMobilephone());
			tempcustomerHistory.setName(tempcustomerBasic.getName());
			tempcustomerHistory.setNational(tempcustomerBasic.getNational());
			tempcustomerHistory.setOperateId(tempcustomerBasic.getOperateId());
			tempcustomerHistory.setOperateName(tempcustomerBasic
					.getOperateName());
			tempcustomerHistory.setPersonIncome(tempcustomerBasic
					.getPersonIncome());
			tempcustomerHistory.setPresentAddress(tempcustomerBasic
					.getPresentAddress());
			tempcustomerHistory.setRemark(tempcustomerBasic.getRemark());
			tempcustomerHistory.setResidenceAddress(tempcustomerBasic
					.getPresentAddress());
			tempcustomerHistory.setTelephone(tempcustomerBasic.getTelephone());
			count = customerBasicInfoDao
					.deleteByPrimaryKey(customerBasicInfoId);
			customerHistoryInfoDao.insert(tempcustomerHistory);
			if (count > 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public CustomerBasicInfo selectCustomerBasicInfo(
			CustomerBasicInfo cunstomerBasicInfo) {
		return customerBasicInfoDao.selectCustomerBasicInfo(cunstomerBasicInfo);
	}

	@Override
	public List<CustomerBasicInfoVo> selectCustomerBasicInfo(
			String credentialsNumber, Long creditapplicationId) {
		List<CustomerBasicInfoVo> customerBasicInfoVos = new ArrayList<CustomerBasicInfoVo>();
		List<CustomerBasicInfoVo> customerBasicInfoFamilys = new ArrayList<CustomerBasicInfoVo>();
		BorrowerServiceApp borrowerServiceApp = new BorrowerServiceApp();
		if (creditapplicationId != null) {
			borrowerServiceApp.setCreditapplicationId(creditapplicationId
					.intValue());
			// 身份证号 在set方法中转为大写
			borrowerServiceApp.setCredentialsNumber(credentialsNumber);
			// 查询本组担保人 (查询语句 已经忽略大小写)
			customerBasicInfoVos = customerBasicInfoDao
					.selectGuanor(borrowerServiceApp);
		}
		if (customerBasicInfoVos.size() > 0) {
			return customerBasicInfoVos;
		} else {
			// 不是担保人 查询客户基本信息 关联借款人 （查询语句 身份证号 已经忽略大小写)
			customerBasicInfoVos = customerBasicInfoDao
					.selectByCredentialsNumber(credentialsNumber.toUpperCase());
			// 如果不存在借款人管理中
			if (customerBasicInfoVos.size() == 0) {
				// 查询 借款人配偶 (查询语句 身份证号 已经忽略大小写)
				customerBasicInfoVos = customerBasicInfoDao
						.selectFamilyByCredentialsNumber(credentialsNumber);
			} else {
				for (CustomerBasicInfoVo customerBasicInfoVo : customerBasicInfoVos) {
					if ((customerBasicInfoVo.getBlackFlag() != null)
							&& !"".equals(customerBasicInfoVo.getBlackFlag())) {
						return customerBasicInfoVos;
					}
					// 不是借款人
					if (!"0".equals(customerBasicInfoVo.getCustomerType())) {
						// 就查借款人配偶信息
						customerBasicInfoFamilys = customerBasicInfoDao
								.selectFamilyByCredentialsNumber(credentialsNumber);
						if (customerBasicInfoFamilys.size() != 0) {// 信息表里没数据
																	// 就返回借款人配偶信息
							customerBasicInfoVos = customerBasicInfoFamilys;
							break;
						}
					}
					if (customerBasicInfoVo.getFlag() == null
							|| "".equals(customerBasicInfoVo.getFlag())
							|| "16".equals(customerBasicInfoVo.getFlag())
							|| "27".equals(customerBasicInfoVo.getFlag())
							|| "28".equals(customerBasicInfoVo.getFlag())
							|| "20".equals(customerBasicInfoVo.getFlag())) {
						// 就查借款人配偶信息
						customerBasicInfoFamilys = customerBasicInfoDao
								.selectFamilyByCredentialsNumber(credentialsNumber);
						if (customerBasicInfoFamilys.size() != 0) {// 信息表里没数据
																	// 就返回借款人配偶信息
							customerBasicInfoVos = customerBasicInfoFamilys;
							break;
						}
					} else {
						if (customerBasicInfoVos.size() == 0) {
							// 查询担保人
							customerBasicInfoVos = customerBasicInfoDao
									.selectGuaranorByCredentialsNumber(credentialsNumber
											.toUpperCase());
						}
						return customerBasicInfoVos;
					}
                    //拒贷状态时，特定拒贷原因（特定限制人群（同行从业者[207]；涉黄、涉毒、涉赌、涉黑人员[209]）以及虚假&欺诈[212,213,214,215,216,217]）不允许进件，
                    if (customerBasicInfoVo.getAuditStatusShow().equals("拒贷")) {
                        List<RefuseReason> reasons = refuseReasonService.selectRefuseReasonById(Long.valueOf(customerBasicInfoVo.getCreditApplicationId()));
                        if (!reasons.isEmpty()) {
                            List<String> specials = new ArrayList<String>(Arrays.asList("207", "209", "212", "213", "214", "215", "216", "217"));
                            for (Iterator<RefuseReason> iterator = reasons.iterator(); iterator.hasNext(); ) {
                                if (specials.contains(iterator.next().getCodeKey())) {
                                    customerBasicInfoVo.setSpecialRefuseFlag("sayYes");
                                    break;
                                }
                            }
                        }
                    }
                }
			}
		}

		if (customerBasicInfoVos.size() == 0) {
			// 查询担保人
			customerBasicInfoVos = customerBasicInfoDao
					.selectGuaranorByCredentialsNumber(credentialsNumber
							.toUpperCase());
		}
		return customerBasicInfoVos;
	}

    /**
     * 按身份证     查询借款人基本信息在信贷申请前进行验证使用
     * add by ygx 2014-09-17
     * * @param  identityNumber 身份证号码
     * @return 客户基本信息对象列表
     */
    public List<CustomerBasicInfoVo> getIdentityListInfo(String identityNumber) throws Exception {
        //step0检查借款人是否为一期未完成
        //step1检查借款人黑名单信息
        //step2检查借款人信息
        //step3检查借款人配偶信息  change 共借人信息
        //step4检查借款人担保信息
        //step5 检查本人直系亲属在借款期或担保期内不能再借款或担保
        //借款人信息临时变量
        String canReturn = "false";
        List<CustomerBasicInfoVo> customerBasicInfoVos = new ArrayList<CustomerBasicInfoVo>();
        //返回正确的借款人信息
        List<CustomerBasicInfoVo>   returnRightCustomerBasicInfoVos = new ArrayList<CustomerBasicInfoVo>();
        //借款人不符合条件时使用
        List<CustomerBasicInfoVo> returnCustomerBasicInfoList = new ArrayList<CustomerBasicInfoVo>();
        //借款人配偶不符合条件时使用 共借人
        List<CustomerBasicInfoVo> returnCustomerBasicInfoList1 = new ArrayList<CustomerBasicInfoVo>();
        //借款人担保不符合条件时使用
        List<CustomerBasicInfoVo> returnCustomerBasicInfoList2 = new ArrayList<CustomerBasicInfoVo>();
        //借款人直系亲属在借款期或担保期内不符合条件时使用
        List<CustomerBasicInfoVo> returnCustomerBasicInfoListConsanguinity  = new ArrayList<CustomerBasicInfoVo>();

        BorrowerServiceApp borrowerServiceApp = new BorrowerServiceApp();

        //step0检查借款人是否为一期未完成
        identityNumber = identityNumber.trim();
        // wyf检查一期是否you借款
         Message undone = firstSystemService.queryByUndone(identityNumber.trim());
         if (!undone.isSuccess()) {
                CustomerBasicInfoVo firstBasicInfoVo = new CustomerBasicInfoVo();
                firstBasicInfoVo.setCustomerType(Constants.CUSTOME_RTYPE_FIRSTDO);
                firstBasicInfoVo.setAuditStatusShow(undone.getMsg());
                firstBasicInfoVo.setCredentialsNumber(identityNumber);
                firstBasicInfoVo.setCanBorrow("false");
                customerBasicInfoVos.add(firstBasicInfoVo);
                return customerBasicInfoVos;
         }

        if (identityNumber.trim().length() != 18) {
               throw new Exception("身份证号长度不够18位，不合格....");
        } else{
            identityNumber = identityNumber.toUpperCase();
        }
        //step1检查借款人黑名单信息
        customerBasicInfoVos = customerBasicInfoDao.getCustomerBlackInfo(identityNumber.toUpperCase());
        if (customerBasicInfoVos.size() > 0) {
            return customerBasicInfoVos;
        }
        //step2检查借款人信息
        customerBasicInfoVos = customerBasicInfoDao.getCustomerInfo(identityNumber.toUpperCase());
        if (customerBasicInfoVos.size() > 0) {
            for (CustomerBasicInfoVo customerBasicInfoVo : customerBasicInfoVos) {
                if ("26".equals(customerBasicInfoVo.getFlag())
                        || "18".equals(customerBasicInfoVo.getFlag())
                        || "16".equals(customerBasicInfoVo.getFlag())
                        || "27".equals(customerBasicInfoVo.getFlag())
                        || "28".equals(customerBasicInfoVo.getFlag())
                        || "20".equals(customerBasicInfoVo.getFlag())) {

                    if (customerBasicInfoVo.getAuditStatusShow().equals("拒贷")) {
                        List<RefuseReason> reasons = refuseReasonService.selectRefuseReasonById(Long.valueOf(customerBasicInfoVo.getCreditApplicationId()));
                        if (!reasons.isEmpty()) {
                            List<String> specials = new ArrayList<String>(Arrays.asList("207", "209", "212", "213", "214", "215", "216", "217"));
                            for (Iterator<RefuseReason> iterator = reasons.iterator(); iterator.hasNext(); ) {
                                if (specials.contains(iterator.next().getCodeKey())) {
                                    customerBasicInfoVo.setSpecialRefuseFlag("sayYes");
                                    customerBasicInfoVo.setCanBorrow("false");
                                    returnCustomerBasicInfoList.add(customerBasicInfoVo);
                                    canReturn = "true";
                                    break;
                                }
                            }
                            if(canReturn.equals("true")){
                                break;
                            }
                        }
                    }
                    returnRightCustomerBasicInfoVos.add(customerBasicInfoVo);
                    //可以正常借款的保证人后续验证保证人配偶信息
                } else {
                    customerBasicInfoVo.setCanBorrow("false");
                    returnCustomerBasicInfoList.add(customerBasicInfoVo);
                    canReturn = "true";
                   break;
                }
            }
        }
        if(returnCustomerBasicInfoList.size()>0 && canReturn.equals("true") )  {
            return returnCustomerBasicInfoList;
        }
        //step3检查借款人配偶信息           共借人
        //  customerBasicInfoVos = customerBasicInfoDao.getMateInfo(identityNumber.toUpperCase());
        //step3检查借款人配偶信息  change 共借人信息
        customerBasicInfoVos  =  customerBasicInfoDao.getCoBorrowerInfo(identityNumber.toUpperCase());
        if (customerBasicInfoVos.size() > 0) {
            for (CustomerBasicInfoVo customerBasicInfoVo : customerBasicInfoVos) {
                if ("26".equals(customerBasicInfoVo.getFlag())
                        || "18".equals(customerBasicInfoVo.getFlag())
                        || "16".equals(customerBasicInfoVo.getFlag())
                        || "27".equals(customerBasicInfoVo.getFlag())
                        || "28".equals(customerBasicInfoVo.getFlag())
                        || "20".equals(customerBasicInfoVo.getFlag())) {

                    if (customerBasicInfoVo.getAuditStatusShow().equals("拒贷")) {
                        List<RefuseReason> reasons = refuseReasonService.selectRefuseReasonById(Long.valueOf(customerBasicInfoVo.getCreditApplicationId()));
                        if (!reasons.isEmpty()) {
                            List<String> specials = new ArrayList<String>(Arrays.asList("207", "209", "212", "213", "214", "215", "216", "217"));
                            for (Iterator<RefuseReason> iterator = reasons.iterator(); iterator.hasNext(); ) {
                                if (specials.contains(iterator.next().getCodeKey())) {
                                    customerBasicInfoVo.setSpecialRefuseFlag("sayYes");
                                    customerBasicInfoVo.setCanBorrow("false");
                                    returnCustomerBasicInfoList1.add(customerBasicInfoVo);
                                    canReturn = "true";
                                    break;
                                }
                            }
                        }
                    }
                    returnRightCustomerBasicInfoVos.add(customerBasicInfoVo);
                    //可以正常借款的保证人后续验证保证人担保信息
                } else {
                    customerBasicInfoVo.setCanBorrow("false");
                    returnCustomerBasicInfoList1.add(customerBasicInfoVo);
                    canReturn = "true";
                    break;
                }
            }
        }
        if(returnCustomerBasicInfoList1.size()>0 && canReturn.equals("true") )  {
            return returnCustomerBasicInfoList1;
        }
        //step4检查借款人担保信息
        customerBasicInfoVos = customerBasicInfoDao.getGuaranorInfo(identityNumber.toUpperCase());
        if (customerBasicInfoVos.size() > 0) {
            for (CustomerBasicInfoVo customerBasicInfoVo : customerBasicInfoVos) {
                if ("26".equals(customerBasicInfoVo.getFlag())
                        || "18".equals(customerBasicInfoVo.getFlag())
                        || "16".equals(customerBasicInfoVo.getFlag())
                        || "27".equals(customerBasicInfoVo.getFlag())
                        || "28".equals(customerBasicInfoVo.getFlag())
                        || "20".equals(customerBasicInfoVo.getFlag())) {
                    returnRightCustomerBasicInfoVos.add(customerBasicInfoVo);
                    //可以正常借款的保证人后续验证保证人担保信息
                } else {
                    customerBasicInfoVo.setCanBorrow("false");
                    returnCustomerBasicInfoList2.add(customerBasicInfoVo);
                    canReturn="true";
                    break;
                }
            }
        }
        if(returnCustomerBasicInfoList2.size()>0 && canReturn.equals("true") )  {
            return returnCustomerBasicInfoList2;
        }


        //step5检查借款人直系亲属在借款期或担保期内不符合条件时使用
        customerBasicInfoVos = customerBasicInfoDao.getConsanguinityInfo(identityNumber.toUpperCase());
        if (customerBasicInfoVos.size() > 0) {
            CustomerBasicInfoVo     customerBasicInfoVo = new CustomerBasicInfoVo();
            customerBasicInfoVo.setCanBorrow("false");
            returnCustomerBasicInfoListConsanguinity.add(customerBasicInfoVos.get(0));
            canReturn="true";
        }
        if(returnCustomerBasicInfoListConsanguinity.size()>0 && canReturn.equals("true") )  {
            return returnCustomerBasicInfoListConsanguinity;
        }
        return returnRightCustomerBasicInfoVos;
    }

    @Override
	public CustomerBasicInfo selectByNumber(String credentialsNumber) {
		return customerBasicInfoDao.selectByNumber(credentialsNumber);
	}

	@Override
	public Message deleteCustomerBasicInfo(int customerBasicInfoId) {
		Message message = new Message();
		BorrowerInfoVo borrowerService = new BorrowerInfoVo();
		borrowerService.setCustomerBasicId(customerBasicInfoId);
		borrowerService.setLeader("");
		List<BorrowerServiceApp> apps = borrowerServiceAppService
				.selectByBorrowerInfo(borrowerService);
		if (apps.size() > 0) {
			message.setSuccess(false);
			message.setMsg("借款申请表中存在数据");
		} else {
			int rowHistory = customerHistoryInfoDao.delete(customerBasicInfoId);
			int row = customerBasicInfoDao
					.deleteByPrimaryKey(customerBasicInfoId);
			if (row > 0) {
				message.setSuccess(true);
				message.setMsg("操作成功");
			} else {
				message.setSuccess(false);
				message.setMsg("操作失败");
			}
		}
		return message;
	}

	@Override
	public Message updateCustomerBasicInfoForSystem(
			CustomerBasicInfo customerBasicInfo) {
		// TODO Auto-generated method stub
		Message message = new Message();
		int result = customerBasicInfoDao.updateDynamic(customerBasicInfo);
		if (result > 0) {
			message.setMsg("更新了" + result + "条记录");
			message.setSuccess(true);
		} else {
			message.setMsg("更新了" + result + "条记录");
			message.setSuccess(false);
		}
		return message;
	}

	@Override
	public Message addNewBorrower(String credentialsNumber,
			String creditapplicationId, Long consultPoolId,
			Long customerConsultId, Long borrowerServiceAppId) throws Exception{
		credentialsNumber = credentialsNumber.toUpperCase();
		Message message = new Message();
		// 组织 borroer_service_app 对象
		BorrowerServiceApp borrowerServiceApp = new BorrowerServiceApp();
		// 重新设置 借款人信息的值
		borrowerServiceApp.setCredentialsNumber(credentialsNumber);// 身份证号
		String birthday = credentialsNumber.substring(6, 10) + "-"
				+ credentialsNumber.substring(10, 12) + "-"
				+ credentialsNumber.substring(12, 14);
		borrowerServiceApp.setBirthday(birthday); // 设置生日
		borrowerServiceApp.setCreditapplicationId(Integer
				.valueOf(creditapplicationId)); // 设置 关联的申请编号

		// 判断customerConsultId，consultPoolId是否为空
		if (customerConsultId != null) {
			// 查询客户咨询
			CustomerConsult customerConsult = customerConsultService
					.selectCustomerConsultById(customerConsultId);
			if (customerConsult != null) {
				borrowerServiceApp.setName(customerConsult.getCustomerName());
				borrowerServiceApp
						.setMobilephone(customerConsult.getTelphone());
			}
		} else if (consultPoolId != null) {
			// 查询营销咨询
			CustomerConsultPool customerConsultPool = customerConsultPoolService
					.getCustomerConsultPoolByPrimaryKey(consultPoolId);
			if (customerConsultPool != null) {
				borrowerServiceApp.setName(customerConsultPool
						.getCustomerName());
				borrowerServiceApp.setMobilephone(customerConsultPool
						.getMobilePhone());
			}
		}

		// 验证 该身份证能否借款
		List<CustomerBasicInfoVo> customers = this.selectCustomerBasicInfo(
				credentialsNumber, Long.valueOf(creditapplicationId));
		if (customers.size() == 0) {
			// 没有 可以添加
			message = borrowerServiceAppService.addNewBorrower(
					credentialsNumber, creditapplicationId, borrowerServiceApp,
					borrowerServiceAppId);

		} else if (customers.get(0).getCustomerType() == "4") {
			message.setMsg("该人是本组的担保人，保存失败");
			message.setSuccess(false);
		} else if ((customers.get(0).getCustomerType() == null || // 没有角色
				"3".equals(customers.get(0).getCustomerType()) || // 借款人
				"0".equals(customers.get(0).getCustomerType()) // 借款人配偶
				)
				&& (customers.get(0).getFlag() != null // 没有状态
						&& !"".equals(customers.get(0).getFlag()) // 没有状态
						&& !"16".equals(customers.get(0).getFlag()) // 还款完成
						&& !"20".equals(customers.get(0).getFlag()) // 提前还款完成
						&& !"06".equals(customers.get(0).getFlag()) // 放款失败
						&& !"26".equals(customers.get(0).getFlag()) // 注销
						&& !"27".equals(customers.get(0).getFlag()) // 据贷
				&& !"28".equals(customers.get(0).getFlag()) // 客户放弃
				)) {
			message.setMsg("该人正在借款中，保存失败");
			message.setSuccess(false);
		} else {
			// 其他情况
			message = borrowerServiceAppService.addNewBorrower(
					credentialsNumber, creditapplicationId, borrowerServiceApp,
					borrowerServiceAppId);
		}
		return message;
	}

	/**
	 * 验证担保人配偶的身份证号码
	 * 
	 * @param credentialsNumber
	 *            身份证号
	 * @param creditapplicationId
	 *            小组申请id
	 */
	@Override
	public Message checkForCredentialsNumber(String credentialsNumber,
			Long creditapplicationId) {
		// TODO Auto-generated method stub
		Message message = new Message();
		credentialsNumber = credentialsNumber.toUpperCase();
		Map<String, String> mapTemp = new HashMap<String, String>();
		List<Map<String, String>> resultMapList = customerBasicInfoDao
				.checkForCredentialsNumber(creditapplicationId);
		for (Map<String, String> map : resultMapList) {
			String type = map.get("TYPE");
			String credentialsNumberTemp = map.get("CREDENTIALSNUMBER");
			String reason = map.get("REASON");
			mapTemp.put(credentialsNumberTemp, type + "," + reason);
		}
		if (!mapTemp.containsKey(credentialsNumber)) {
			message.setSuccess(true);
		} else {
			message.setSuccess(false);
			String tandr = mapTemp.get(credentialsNumber);
			String[] array = tandr.split(",");
			message.setCode(array[0]);
			message.setMsg(array[1]);
		}
		return message;
	}

	@Override
	public Message addNewBorrowerIncludeRevolvingAndDiscount(
			String credentialsNumber, String creditapplicationId,
			Long consultPoolId, Long customerConsultId,
			Long borrowerServiceAppId) throws Exception{
		// TODO Auto-generated method stub
		Message message = new Message();
		message = this.addNewBorrower(credentialsNumber, creditapplicationId,
				consultPoolId, customerConsultId, borrowerServiceAppId);
		creditApplicationService.updateRevolving(Integer
				.parseInt(creditapplicationId));
		return message;
	}
    @Override

    public Message addNewBorrowerToCreditAppplication(BorrowerInfo borrowerInfo) throws Exception{
        Message msg = new Message();
        msg = this.getUserValidInfoById(borrowerInfo.getCredentialsNumber());

        //再次验证身份证号是否可以使用
        if(msg.isSuccess()){//身份证号可以使用
            //初始化借款人信贷申请
            BorrowerServiceApp borrowerServiceApp = this.initBorrowerServiceApp(borrowerInfo);
            if("OldUser".equals(msg.getCode())){
               // borrowerInfo.setBorrowerServiceAppId(msg.getMsg());
                borrowerInfo.setOldBorrowerServiceAppId(msg.getMsg());
                borrowerInfo.setOldUser("true");
            }else{
                borrowerInfo.setOldUser("false");
                borrowerInfo.setBorrowerServiceAppId("");
            }
            msg =   borrowerServiceAppService.addNewBorrower(borrowerServiceApp,borrowerInfo) ;
            return msg;
        }else {
            return msg;
        }
    }

    @Override
    public List<CustomerBasicInfo> getBlackListCustomerInfoList() {
         return customerBasicInfoDao.getBlackListCustomerInfoList();
    }

    //初始化借款人信贷申请
    private  BorrowerServiceApp initBorrowerServiceApp(BorrowerInfo borrowerInfo){
        BorrowerServiceApp borrowerServiceApp = new BorrowerServiceApp();
        //       重新设置借款人申请信息
        //设置借款人申请单身份证号
        String credentialsNumber  = borrowerInfo.getCredentialsNumber().toUpperCase();
        borrowerServiceApp.setCredentialsNumber(credentialsNumber);
        String birthday = credentialsNumber.substring(6, 10) + "-"
                + credentialsNumber.substring(10, 12) + "-"
                + credentialsNumber.substring(12, 14);
        //设置借款人申请单借款人生日
        borrowerServiceApp.setBirthday(birthday); // 设置生日
        //设置借款人申请单与贷款申请单的关联主键关联
        borrowerServiceApp.setCreditapplicationId(Integer.valueOf(borrowerInfo.getCreditApplicationId())); // 设置 关联的申请编号
        //如果借款人为客户咨询借款
        if(null != borrowerInfo.getCustomerConsultId() && !"".equals(borrowerInfo.getCustomerConsultId()) &&
                !"null".equals(borrowerInfo.getCustomerConsultId()) ){

            CustomerConsult customerConsult = customerConsultService
                    .selectCustomerConsultById(Long.valueOf(borrowerInfo.getCustomerConsultId()));
            if (customerConsult != null) {
                borrowerServiceApp.setName(customerConsult.getCustomerName());
                borrowerServiceApp.setMobilephone(customerConsult.getTelphone());
            }
        }
        //如果是营销咨询
        if (borrowerInfo.getConsultPoolId() != null && !"".equals(borrowerInfo.getConsultPoolId()) && !"null".equals(borrowerInfo.getConsultPoolId()) ){
            CustomerConsultPool customerConsultPool = customerConsultPoolService
                    .getCustomerConsultPoolByPrimaryKey(Long.valueOf(borrowerInfo.getConsultPoolId()));
            if (customerConsultPool != null) {
                borrowerServiceApp.setName(customerConsultPool.getCustomerName());
                borrowerServiceApp.setMobilephone(customerConsultPool.getMobilePhone());
            }
        }
        return    borrowerServiceApp;
    }
    private  Message   getUserValidInfoById(String credentialsNumber) throws Exception {
       Message msg = new Message();
       List<CustomerBasicInfoVo>  customerBasicInfoVos =  this.getIdentityListInfo(credentialsNumber) ;
       if(customerBasicInfoVos.size() == 0 ){
           //此身份证号在系统中未做过信贷
           msg.setSuccess(true);
           msg.setCode("NewUser");
           return msg;
       }
       //验证身份证号是否可用
       String canBorrow = "true";
       for (CustomerBasicInfoVo customerBasicInfoVo: customerBasicInfoVos ){
           if(customerBasicInfoVo.getCustomerType() !=null && !"".equals(customerBasicInfoVo.getCustomerType())){
               String customerType =  customerBasicInfoVo.getCustomerType().trim();
               String  canBorrowed =   customerBasicInfoVo.getCanBorrow();
               if(CommonUtil.isNotEmpty(canBorrowed)  && canBorrowed.equals("false")){
                   //一期未完成
                   if("5".equals(customerType)){
                       msg.setSuccess(false);
                       msg.setMsg("借款人一期未完成，不能借款.....");
                       canBorrow = "false";
                       break;
                   }
                   //step1检查借款人黑名单信息
                   if("9".equals(customerType) ){
                       msg.setSuccess(false);
                       msg.setMsg("借款人在黑名单中，不能借款.....");
                       canBorrow = "false";
                       break;
                   }
                   //step2检查同组担保人信息
                   if("4".equals(customerType)){
                       msg.setSuccess(false);
                       msg.setMsg("借款人为同组担保人，不能借款.....");
                       canBorrow = "false";
                       break;
                   }
                   //step3检查借款人信息
                   if("1".equals(customerType)){
                       msg.setSuccess(false);
                       msg.setMsg("借款人已借款，不能借款.....");
                       canBorrow = "false";
                       break;
                   }
                   //step4检查人共借人信息
                   if("3".equals(customerType)){
                       msg.setSuccess(false);
                       msg.setMsg("借款人为其他借款人共借人，不能借款.....");
                       canBorrow = "false";
                       break;
                   }
                   //step5检查担保人信息
                   if("0".equals(customerType)){
                       msg.setSuccess(false);
                       msg.setMsg("借款人已为其他借款人做担保，不能借款.....");
                       canBorrow = "false";
                       break;
                   }
                   //step5检查担保人信息
                   if("6".equals(customerType)){
                       msg.setSuccess(false);
                       msg.setMsg("此人直系亲属在借款期或者担保期，不能借款.....");
                       canBorrow = "false";
                       break;
                   }
                   //step5检查担保人信息
                   if("6".equals(customerType)){
                       msg.setSuccess(false);
                       msg.setMsg("此人直系亲属在借款期或者担保期，不能借款.....");
                       canBorrow = "false";
                       break;
                   }
               }
           }
       }
       if(canBorrow.equals("false")) {
           return msg;
       }else{
           //  去掉借款人中共借人这部分信息
           List<CustomerBasicInfoVo>       customerBasicInfoVoList = new ArrayList<CustomerBasicInfoVo>();
           for(CustomerBasicInfoVo customerBasicInfoTmp: customerBasicInfoVos){
               if(CommonUtil.isNotEmpty(customerBasicInfoTmp.getCustomerType()) && "3".equals(customerBasicInfoTmp.getCustomerType())){

               } else{
                   customerBasicInfoTmp.setCanBorrow("true");
                   customerBasicInfoVoList.add(customerBasicInfoTmp);
               }
           }
           if(customerBasicInfoVoList.size()>0){
               msg.setSuccess(true);
               msg.setCode("OldUser");
               msg.setMsg(customerBasicInfoVoList.get(0).getBorrowerServiceAppId());
           }else{
               msg.setSuccess(true);
               msg.setCode("NewUser");
               return msg;
           }
           return msg;
       }
    }
    @Override
    public Message updateStatusByReceiveCreditMsg(List<ReceiveCreditObj> receiveCreditObjs) throws ParseException{
    	Message msg=new Message();
    	msg=rural2CreditDao.updateStatusByReceiveCreditMsg(receiveCreditObjs);
    	return msg;
    }
    @Override
    public String selBusinessNumberByUUID(String businessNumber){
    	return customerBasicInfoDao.selBusinessNumberByUUID(businessNumber);
    }
	@Override
	public List<Employee> getEmployeeListByDeparmentId(String departmentId) {
		return customerBasicInfoDao.getEmployeeListByDeparmentId(departmentId);
	}

	@Override
	public List<CustomerInfoVO> getCustomerInfoListByCreditApplicationID(long creditapplicationId) {
		return customerBasicInfoDao.getCustomerInfoListByCreditApplicationID(creditapplicationId);
	}

}