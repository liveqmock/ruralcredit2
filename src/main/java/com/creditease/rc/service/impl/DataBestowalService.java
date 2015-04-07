/**
 * Title:DataBestowalService.java
 * Description:
 */
package com.creditease.rc.service.impl;

import com.creditease.core.security.Authorization;
import com.creditease.core.security.SpringSecurityUtils;
import com.creditease.rc.app.smp.EmployeeDTO;
import com.creditease.rc.common.Constants;
import com.creditease.rc.dao.ICreditApplicationDAO;
import com.creditease.rc.dao.ICustomerConsultDAO;
import com.creditease.rc.dao.IDataBestowalDAO;
import com.creditease.rc.dao.IDataBestowalDetailDAO;
import com.creditease.rc.domain.*;
import com.creditease.rc.framework.exception.AppBusinessException;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.service.*;
import com.creditease.rc.util.CommonUtil;
import com.creditease.rc.util.Environment;
import com.creditease.rc.util.SmpWSUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * Title:DataBestowalService.java
 * Description:
 * Copyright:Copyright (c) 2011
 * Company:普信恒业科技发展（北京）有限公司
 * 
 * @author 韩大年
 * @version v1.0
 *          2013-5-31
 */
@Service
public class DataBestowalService implements IDataBestowalService {

    private static Logger logger = Logger.getLogger(DataBestowalService.class);
	/**
	 * @Description 默认构造器
	 */
	public DataBestowalService() {
		// TODO Auto-generated constructor stub
	}

	@Resource
	private SmpWSUtil smpWSUtil;
	@Resource
	private IDataBestowalDAO dataBestowalDAO;
	@Resource
	private IDataBestowalDetailDAO dataBestowalDetailDAO;
	@Resource
	private ICreditApplicationDAO creditApplicationDAO;
	@Resource
	private IOperateLogService operateLogService;
	@Resource
	private ICreditApplicationService creditApplicationService;
	@Resource
	private IRural2CreditService rural2CreditService;
	@Resource
	private ICustomerConsultDAO customerConsultDAO;

	@Resource
	private IOverDueDataService overDueDataService ;

	/**
	 * 
	 * @author 韩大年
	 * @Description: 插入数据赠与主表与子表
	 * @param
	 * @version v1.0
	 *          2013-5-31
	 */
	public Message insertDataBestowalAndDataBestowalDetail(DataBestowal dataBestowal, Long[] creditApplicationIds) {
		Message message = new Message();
		message.setSuccess(true);
		if (dataBestowal == null) {
			message.setSuccess(false);
			message.setMsg("dataBestowal对象为空");
		}
		if (CommonUtil.isEmpty(dataBestowal.getOldLoanOfficerId())
				|| CommonUtil.isEmpty(dataBestowal.getOldLoanOfficerName())) {
			message.setSuccess(false);
			message.setMsg("赠与的客户经理姓名或id为空");
		}
		if (CommonUtil.isEmpty(dataBestowal.getNewLoanOfficerId())
				|| CommonUtil.isEmpty(dataBestowal.getNewLoanOfficerName())) {
			message.setSuccess(false);
			message.setMsg("接者的客户经理姓名或id为空");
		}
		if (creditApplicationIds == null || creditApplicationIds.length < 1) {
			message.setSuccess(false);
			message.setMsg("赠与数据笔数为空");
		}
		// 验证
		if (!message.isSuccess()) {
			return message;
		}
		/*之前：加入调用贷后查询逾期数据
		  现在（2014-12-22）：修改为调用综合信贷查询逾期数据（代码见下）
		Map<Long, OverDueObj> overDueObjMap = getOverDueObjMapLocal(dataBestowal.getOldLoanOfficerId());*/
		String userId = SpringSecurityUtils.getCurrentUser().getUserId();
		if (CommonUtil.isEmpty(userId)) {
			userId = "-1";
		}
		String name_zh = SpringSecurityUtils.getCurrentUser().getName_zh();
		if (CommonUtil.isEmpty(name_zh)) {
			name_zh = "";
		}

		dataBestowal.setOperatingId(userId);
		dataBestowal.setOperatingName(name_zh);
		this.dataBestowalDAO.insertByDataBestowal(dataBestowal);
		// 获取新插入的id
		Long dataBestowalId = dataBestowal.getDataBestowalId();
		if (dataBestowalId == null) {
			throw new AppBusinessException("未取到新插入的赠与数据id");
		}
		List<Long> creditapplicationIdList = new ArrayList<Long>();
		Environment env = Environment.getInstance();

// 获取新员工的 departmentid
		EmployeeDTO employeeDTO = smpWSUtil.getEmployeeDTO(dataBestowal.getNewLoanOfficerId());
		String newDepartmentId = employeeDTO.getDepartmentId().toString();
// 调贷后需要的id
		List<Long> daihuoIds = new ArrayList<Long>();
		for (int i = 0; i < creditApplicationIds.length; i++) {
			DataBestowalDetail detail = new DataBestowalDetail();
			detail.setDataBestowalId(dataBestowalId);
			Long creditAppLicationId = creditApplicationIds[i];
//			OverDueObj dueObj = overDueObjMap.get(creditAppLicationId);

			CreditApplication creditApplicationEntity = this.creditApplicationDAO.selectById(creditAppLicationId);
			if (null == creditAppLicationId) {
				throw new AppBusinessException("第" + (i + 1) + "条数据的申请id为空");
			}
			creditapplicationIdList.add(creditAppLicationId);

			/*调用综合信贷查询逾期数据*/
			Integer over = overDueDataService.checkOverDueById(creditAppLicationId);
			if (over.intValue() == 0) {
				detail.setIsOverdue("0");
			} else {
				detail.setIsOverdue("1");
			}
			detail.setCreditApplicationId(creditAppLicationId);
			detail.setDataBestowalId(dataBestowalId);
			detail.setSignagreementDate(creditApplicationEntity.getSignagreementDate());
			if (null != creditApplicationEntity) {
				detail.setAuditStatus(creditApplicationEntity.getAuditStatus());
				String autus = creditApplicationEntity.getAuditStatus();
				if (autus != null
						&& (Constants.STATE_16.equals(autus) || Constants.STATE_15.equals(autus) || Constants.STATE_20
								.equals(autus))) {
					daihuoIds.add(creditApplicationEntity.getCreditapplicationId().longValue());
				}
			}
			// 1 插入赠与明细
			this.dataBestowalDetailDAO.insertByDataBestowalDetail(detail);
			// 2 更新信贷申请中客户经理
			CreditApplication creditApplicationTemp = new CreditApplication();
			creditApplicationTemp.setCreditapplicationId(Integer.parseInt(creditAppLicationId.toString()));
			creditApplicationTemp.setLoanOfficerId(dataBestowal.getNewLoanOfficerId());
			creditApplicationTemp.setLoanOfficerName(dataBestowal.getNewLoanOfficerName());
// 3 更新团队id
			creditApplicationTemp.setDepartmentId(newDepartmentId);
			// 用旧的客户经理id占用创建客户经理id位置
			creditApplicationTemp.setCreateLoanOfficerId(dataBestowal.getOldLoanOfficerId());
			Integer count = this.creditApplicationDAO.updateLoanOfficerByCreditApplication(creditApplicationTemp);
			if (count == 0) {
				throw new AppBusinessException("第" + (i + 1) + "条数据的更新0条");
			}

			// 3 删除旧的权限。插入新的权限
			try {
				Authorization authorization = env.getAuthorizationService();
				authorization.deleteAuth(CreditApplication.class, creditApplicationIds[i].intValue());
				authorization.createAuth(CreditApplication.class, creditApplicationIds[i].intValue(),
						Integer.parseInt(dataBestowal.getNewLoanOfficerId()), Integer.parseInt(Constants.SYSTEM_SIGN));
			} catch (Exception e) {
				e.printStackTrace();
			}

			// 2 更新信贷申请中客户经理
			CustomerConsult consult = new CustomerConsult();
			consult.setCustomerConsultId(creditApplicationEntity.getCustomerConsultId());
			consult.setReceptionistId(dataBestowal.getNewLoanOfficerId());
			consult.setReceptionist(dataBestowal.getNewLoanOfficerName());
			consult.setDepartmentId(employeeDTO.getAreaDepartmentId().toString());
			consult.setDepartmentName(employeeDTO.getAreaDepartmentName());
			Integer countConsult = customerConsultDAO.updateCustomerConsultAll(consult);
			// 3 删除旧的权限。插入新的权限 (客户咨询)
			try {
				Authorization authorization = env.getAuthorizationService();
				authorization.deleteAuth(CustomerConsult.class, creditApplicationEntity.getCustomerConsultId());
				authorization.createAuth(CustomerConsult.class, creditApplicationEntity.getCustomerConsultId(),
						Integer.parseInt(dataBestowal.getNewLoanOfficerId()), Integer.parseInt(Constants.SYSTEM_SIGN));
			} catch (Exception e) {
				e.printStackTrace();
			}
			OperateLog operateLog = new OperateLog();
			operateLog.setFunctionBussiness("数据赠与,赠与前客户经理姓名：" + dataBestowal.getOldLoanOfficerName() + ";赠与后客户经理姓名："
					+ dataBestowal.getNewLoanOfficerName());
			operateLog.setFunctionCode("700010");
			operateLog.setCreditapplicationId(creditAppLicationId);
			operateLog.setOperateLogId(Long.valueOf(userId));
			operateLog.setOperatorName(name_zh);
			operateLog.setOperateDate(new Date());
			operateLogService.insert(operateLog);

		}

		// 4更新贷后中还款计划
		Boolean flage = false;
		if (CommonUtil.isNotEmpty(daihuoIds)) {
			flage = rural2CreditService.modifySeller(daihuoIds, dataBestowal.getNewLoanOfficerId(),
					dataBestowal.getNewLoanOfficerName());
			if (!flage) {
				throw new AppBusinessException("信贷员ID变更失败,请联系贷后人员");
			}
		}
        /*调用综合信贷系统人员变更接口*/
        boolean flag_comprehensive = rural2CreditService.updateStaffComprehensive(dataBestowal.getOldLoanOfficerId(), dataBestowal.getNewLoanOfficerId());
        if (flag_comprehensive == false) {
            logger.warn("------->>信贷员ID变更失败,请联系综合信贷系统相关人员！");
        }
		message.setMsg("");
		return message;

	}

	private Map<Long, OverDueObj> getOverDueObjMapLocal(String oldLoanOfficerId) {
		// TODO Auto-generated method stub
		List<LocalOfficeIdDTO> localOfficeIdDTOList = new ArrayList<LocalOfficeIdDTO>();
		System.out.println(oldLoanOfficerId);

		LocalOfficeIdDTO localOfficeIdDTO = new LocalOfficeIdDTO();
		// 员工编号列表
		List<LocalSellIdDTO> localSellIdDTOsList = new ArrayList<LocalSellIdDTO>();
		// 员工编号
		LocalSellIdDTO localSellIdDTO = new LocalSellIdDTO();
		localSellIdDTO.setSellId(oldLoanOfficerId);

		// 放入列表中
		localSellIdDTOsList.add(localSellIdDTO);
		// 员工放入2
		localOfficeIdDTO.setLocalSellIdDTOs(localSellIdDTOsList);
		localOfficeIdDTOList.add(localOfficeIdDTO);
		LocalOverdueInfoResponse localOverdueInfoResponse = rural2CreditService.overdueInfo(localOfficeIdDTOList);
		return localOverdueInfoResponse.getOverDueObjMap();
	}

	/**
	 * 
	 * @author 韩大年
	 * @Description: 查询数据赠与主表
	 * @param record
	 * @return
	 * @version v1.0
	 *          2013-5-31
	 */
	public List<DataBestowal> selectByDataBestowalSelective(DataBestowal record) {
		return this.dataBestowalDAO.selectByDataBestowalSelective(record);
	}

	/**
	 * 
	 * @author 韩大年
	 * @Description: 根据赠与主表id查询赠与明细
	 * @param DataBestowalId
	 * @return
	 * @version v1.0
	 *          2013-5-31
	 */
	public List<DataBestowalDetail> selectDataBestowalDetailByDataBestowalId(Long DataBestowalId) {
		return this.dataBestowalDetailDAO.selectDataBestowalDetailByDataBestowalId(DataBestowalId);
	}

	/**
	 * 
	 * @author 韩大年
	 * @Description: 分页查询可赠与的申请单
	 * @param
	 * @return
	 * @version v1.0
	 *          2013-6-3
	 */
	public Pagination selectBestowalCreditApplicationByPagination(CreditApplication creditApplication,
			Pagination pagination) {
		return this.creditApplicationDAO.selectBestowalCreditApplicationByPagination(creditApplication, pagination);
	}

	/**
	 * 
	 * @author 韩大年
	 * @Description: 查询系统中所有的客户经理
	 * @param creditApplication
	 * @return
	 * @version v1.0
	 *          2013-6-5
	 */
	public List querySysloanOfficerList(CreditApplication creditApplication) {

		return this.creditApplicationDAO.querySysloanOfficerList(creditApplication);
	}

	/**
	 * 分页查询赠与列表
	 * 
	 * @param record 赠与对象
	 * @param pagination 分页条件
	 * @return 分页列表
	 */
	@Override
	public Pagination selectBestowalPagination(DataBestowal record, Pagination pagination, String businessNumber) {
		// TODO Auto-generated method stub
		if (businessNumber != null) {
			CreditApplication credit = creditApplicationService.selectByGroupNumber(businessNumber);
			if (credit != null) {
				pagination = dataBestowalDAO.searchbusinessPagination(credit.getCreditapplicationId().longValue(),
						pagination);
			}
		} else {
			pagination = dataBestowalDAO.selectBestowalPagination(record, pagination);
		}
		return pagination;
	}

	/**
	 * zhangman
	 * 
	 * @param DataBestowalId 主表id
	 * @return bestowalDetailsMap
	 */
	@Override
	public List<HashMap> selectCredit(Long DataBestowalId) {
		// TODO Auto-generated method stub
		return dataBestowalDAO.selectCredit(DataBestowalId);
	}

	/**
	 * zhangman
	 * 
	 * @param DataBestowalId 主表id
	 * @return bestowalDetailsMap
	 */
	@Override
	public List<HashMap> selectCustomerConsult(Long DataBestowalId) {
		// TODO Auto-generated method stub
		return dataBestowalDAO.selectCustomerConsult(DataBestowalId);
	}

	@Override
	public Message updateCreditApplicationAuthorization(List<Integer> objectIds, int loanOfficerId) {
		Message message = new Message();
		message.setSuccess(true);
		Environment env = Environment.getInstance();
		List<Integer> contomerConsultIds = new ArrayList<Integer>();
		int sum = 0;
		if (objectIds != null && objectIds.size() > 0) {
			for (Integer objectId : objectIds) {
				if (objectId != null) {
					CreditApplication application = creditApplicationDAO.selectById(objectId.longValue());
					try {
						Authorization authorization = env.getAuthorizationService();
						authorization.deleteAuth(CreditApplication.class, objectId);
						authorization.createAuth(CreditApplication.class, objectId,
								Integer.valueOf(application.getLoanOfficerId()),
								Integer.parseInt(Constants.SYSTEM_SIGN));
						sum = sum + 1;
					} catch (Exception e) {
						message.setSuccess(false);
						e.printStackTrace();
					}
				}
				CreditApplication application = creditApplicationDAO.selectById(objectId.longValue());
				contomerConsultIds.add(application.getCustomerConsultId().intValue());
			}
		} else {
			List<CreditApplication> applications = creditApplicationDAO.selectByLoanOfficer(loanOfficerId);
			if (applications != null && applications.size() > 0) {
				for (CreditApplication creditApplication : applications) {
					if (creditApplication != null && creditApplication.getCreditapplicationId() != null) {
						try {
							Authorization authorization = env.getAuthorizationService();
							authorization.deleteAuth(CreditApplication.class,
									creditApplication.getCreditapplicationId());
							authorization.createAuth(CreditApplication.class,
									creditApplication.getCreditapplicationId(), loanOfficerId,
									Integer.parseInt(Constants.SYSTEM_SIGN));
							sum = sum + 1;
						} catch (Exception e) {
							message.setSuccess(false);
							e.printStackTrace();
						}
					}

					contomerConsultIds.add(creditApplication.getCustomerConsultId().intValue());
				}

			}
		}

// 更新咨询权限
		updateCustomerConsultAuthorization(contomerConsultIds, loanOfficerId);
		message.setMsg("一共更新了" + sum + "条申请数据");
		return message;
	}

	@Override
	public Message updateCustomerConsultAuthorization(List<Integer> objectIds, int loanOfficerId) {
		Message message = new Message();
		message.setSuccess(true);
		Environment env = Environment.getInstance();
		int sum = 0;
		if (objectIds != null && objectIds.size() > 0) {
			for (Integer objectId : objectIds) {
				if (objectId != null) {
					CustomerConsult customerConsult = customerConsultDAO
							.selectCustomerConsultById(objectId.longValue());
					try {
						Authorization authorization = env.getAuthorizationService();
						authorization.deleteAuth(CustomerConsult.class, objectId);
						authorization.createAuth(CustomerConsult.class, objectId,
								Integer.valueOf(customerConsult.getReceptionistId()),
								Integer.parseInt(Constants.SYSTEM_SIGN));
						sum = sum + 1;
					} catch (Exception e) {
						message.setSuccess(false);
						e.printStackTrace();
					}
				}
			}
		} else {
			List<CustomerConsult> consults = customerConsultDAO.selectByReceptionist(String.valueOf(loanOfficerId));
			if (consults != null && consults.size() > 0) {
				for (CustomerConsult customerConsult : consults) {
					if (customerConsult != null && customerConsult.getCustomerConsultId() != null) {
						try {
							Authorization authorization = env.getAuthorizationService();
							authorization.deleteAuth(CustomerConsult.class, customerConsult.getCustomerConsultId());
							authorization.createAuth(CustomerConsult.class, customerConsult.getCustomerConsultId(),
									loanOfficerId, Integer.parseInt(Constants.SYSTEM_SIGN));
							sum = sum + 1;
						} catch (Exception e) {
							message.setSuccess(false);
							e.printStackTrace();
						}
					}
				}
			}
		}
		message.setMsg("一共更新了" + sum + "条咨询数据");
		return message;
	}

	@Override
	public Message updateCustomerConsultAurhority(DataBestowal dataBestowal, Long[] idsLong) {
		Message message = new Message();
		message.setSuccess(true);
		if (dataBestowal == null) {
			message.setSuccess(false);
			message.setMsg("dataBestowal对象为空");
		}
		if (CommonUtil.isEmpty(dataBestowal.getOldLoanOfficerId())
				|| CommonUtil.isEmpty(dataBestowal.getOldLoanOfficerName())) {
			message.setSuccess(false);
			message.setMsg("赠与的客户经理姓名或id为空");
		}
		if (CommonUtil.isEmpty(dataBestowal.getNewLoanOfficerId())
				|| CommonUtil.isEmpty(dataBestowal.getNewLoanOfficerName())) {
			message.setSuccess(false);
			message.setMsg("接者的客户经理姓名或id为空");
		}
		if (idsLong == null || idsLong.length < 1) {
			message.setSuccess(false);
			message.setMsg("赠与数据笔数为空");
		}
		// 验证
		if (!message.isSuccess()) {
			return message;
		}
		String userId = SpringSecurityUtils.getCurrentUser().getUserId();
		if (CommonUtil.isEmpty(userId)) {
			userId = "-1";
		}
		String name_zh = SpringSecurityUtils.getCurrentUser().getName_zh();
		if (CommonUtil.isEmpty(name_zh)) {
			name_zh = "";
		}

		dataBestowal.setOperatingId(userId);
		dataBestowal.setOperatingName(name_zh);
		this.dataBestowalDAO.insertByDataBestowal(dataBestowal);
		// 获取新插入的id
		Long dataBestowalId = dataBestowal.getDataBestowalId();
		if (dataBestowalId == null) {
			throw new AppBusinessException("未取到新插入的赠与数据id");
		}
// 获取新员工的 departmentid
		EmployeeDTO employeeDTO = smpWSUtil.getEmployeeDTO(dataBestowal.getNewLoanOfficerId());
		String newDepartmentId = employeeDTO.getDepartmentId().toString();
		List<Long> customerConsultIdList = new ArrayList<Long>();
		Environment env = Environment.getInstance();
		for (int i = 0; i < idsLong.length; i++) {
			DataBestowalDetail detail = new DataBestowalDetail();
			detail.setDataBestowalId(dataBestowalId);
			Long customerConsultId = idsLong[i];
			CustomerConsult customerConsult = customerConsultDAO.selectCustomerConsultById(customerConsultId);
			if (null == customerConsultId) {
				throw new AppBusinessException("第" + (i + 1) + "条数据的申请id为空");
			}
			customerConsultIdList.add(customerConsultId);
			detail.setCreditApplicationId(customerConsultId);
			detail.setDataBestowalId(dataBestowalId);
			if (null != customerConsult) {
				detail.setAuditStatus(customerConsult.getStatus());
			}
			// 1 插入赠与明细
			this.dataBestowalDetailDAO.insertByDataBestowalDetail(detail);
			// 2 更新信贷申请中客户经理
			CustomerConsult consult = new CustomerConsult();
			consult.setCustomerConsultId(customerConsultId);
			consult.setReceptionistId(dataBestowal.getNewLoanOfficerId());
			consult.setReceptionist(dataBestowal.getNewLoanOfficerName());
			consult.setDepartmentId(employeeDTO.getAreaDepartmentId().toString());
			consult.setDepartmentName(employeeDTO.getAreaDepartmentName());
			Integer count = customerConsultDAO.updateCustomerConsultAll(consult);
			// 3 删除旧的权限。插入新的权限
			try {
				Authorization authorization = env.getAuthorizationService();
				authorization.deleteAuth(CustomerConsult.class, idsLong[i].intValue());
				authorization.createAuth(CustomerConsult.class, idsLong[i].intValue(),
						Integer.parseInt(dataBestowal.getNewLoanOfficerId()), Integer.parseInt(Constants.SYSTEM_SIGN));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return message;
	}

	@Override
	public Message updatesynchroAurhorityByDepartment(List<Integer> customerIdsList,List<Integer> creditApplicationIdsList,
			String departmentId) {
		Message message = new Message();
		message.setSuccess(true);
		Environment env = Environment.getInstance();
		int creditApplicationSum = 0;
		int customerConsultSum = 0;
		if(CommonUtil.isNotEmpty(departmentId)){
			CreditApplication creditApplication = new CreditApplication();
			creditApplication.setCompanyId(departmentId);
			//根据分公司id查询系统中所有的客户经理
			List loanOfficerList = querySysloanOfficerList(creditApplication);
			if(CommonUtil.isNotEmpty(loanOfficerList)){
				//操作时没有选择任何数据，表示更新所有数据的权限
					//得到每个客户经理的id
					for (Object object : loanOfficerList) {
						Map<String, Object> map = (Map<String, Object>) object;
						if(CommonUtil.isEmpty(customerIdsList) && CommonUtil.isEmpty(creditApplicationIdsList)){
							//查询该客户经理对应的申请数据权限
							List<CreditApplication> applications = creditApplicationDAO.selectByLoanOfficer(Integer.parseInt(map.get("LOAN_OFFICER_ID").toString()));
							if(CommonUtil.isNotEmpty(applications)){
								if (CommonUtil.isNotEmpty(applications)) {
									for (CreditApplication creditApplication1 : applications) {
										if (creditApplication1 != null && creditApplication1.getCreditapplicationId() != null) {
											try {
												Authorization authorization = env.getAuthorizationService();
												authorization.deleteAuth(CreditApplication.class,
														creditApplication1.getCreditapplicationId());
												authorization.createAuth(CreditApplication.class,
														creditApplication1.getCreditapplicationId(), Integer.parseInt(map.get("LOAN_OFFICER_ID").toString()),
														Integer.parseInt(Constants.SYSTEM_SIGN));
												creditApplicationSum += 1;
											} catch (Exception e) {
												message.setSuccess(false);
												e.printStackTrace();
											}
										}

										//contomerConsultIds.add(creditApplication1.getCustomerConsultId().intValue());
									}

								}
							}
							//查询客户经理对应的咨询数据权限
							List<CustomerConsult> consults = customerConsultDAO.selectByReceptionist(map.get("LOAN_OFFICER_ID").toString());
							if(CommonUtil.isNotEmpty(consults)){
								for (CustomerConsult customerConsult : consults) {
									if(customerConsult != null && customerConsult.getCustomerConsultId()!=null){
										try {
											Authorization authorization = env.getAuthorizationService();
											authorization.deleteAuth(CustomerConsult.class, customerConsult.getCustomerConsultId());
											authorization.createAuth(CustomerConsult.class, customerConsult.getCustomerConsultId(),
													Integer.parseInt(map.get("LOAN_OFFICER_ID").toString()), Integer.parseInt(Constants.SYSTEM_SIGN));
											customerConsultSum += 1;
										} catch (Exception e) {
											message.setSuccess(false);
											e.printStackTrace();
										}
									}
								}
							}
						}else if(CommonUtil.isEmpty(customerIdsList) && CommonUtil.isNotEmpty(creditApplicationIdsList)){//咨询数据时空，申请数据有
							for (Integer creditApplicationId : creditApplicationIdsList) {
								if(creditApplicationId!=null){
									CreditApplication application = creditApplicationDAO.selectById(creditApplicationId.longValue());
									try {
										Authorization authorization = env.getAuthorizationService();
										authorization.deleteAuth(CreditApplication.class, creditApplicationId);
										authorization.createAuth(CreditApplication.class, creditApplicationId,
												Integer.valueOf(application.getLoanOfficerId()),
												Integer.parseInt(Constants.SYSTEM_SIGN));
										creditApplicationSum = creditApplicationIdsList.size();
									} catch (Exception e) {
										message.setSuccess(false);
										e.printStackTrace();
									}
								}
							}
						}else if(CommonUtil.isNotEmpty(customerIdsList) && CommonUtil.isEmpty(creditApplicationIdsList)){//咨询有数据，申请数据是空
							for (Integer customerId : customerIdsList) {
								if (customerId != null) {
									CustomerConsult customerConsult = customerConsultDAO
											.selectCustomerConsultById(customerId.longValue());
									try {
										Authorization authorization = env.getAuthorizationService();
										authorization.deleteAuth(CustomerConsult.class, customerId);
										authorization.createAuth(CustomerConsult.class, customerId,
												Integer.valueOf(customerConsult.getReceptionistId()),
												Integer.parseInt(Constants.SYSTEM_SIGN));
										customerConsultSum = customerIdsList.size();
									} catch (Exception e) {
										message.setSuccess(false);
										e.printStackTrace();
									}
								}
							}
							
						}else if(CommonUtil.isNotEmpty(customerIdsList) && CommonUtil.isNotEmpty(creditApplicationIdsList)){//咨询申请都选了数据
							
							//申请数据
							for (Integer creditApplicationId : creditApplicationIdsList) {
								if(creditApplicationId!=null){
									CreditApplication application = creditApplicationDAO.selectById(creditApplicationId.longValue());
									try {
										Authorization authorization = env.getAuthorizationService();
										authorization.deleteAuth(CreditApplication.class, creditApplicationId);
										authorization.createAuth(CreditApplication.class, creditApplicationId,
												Integer.valueOf(application.getLoanOfficerId()),
												Integer.parseInt(Constants.SYSTEM_SIGN));
										creditApplicationSum = creditApplicationIdsList.size();
									} catch (Exception e) {
										message.setSuccess(false);
										e.printStackTrace();
									}
								}
							}
							//咨询数据
							for (Integer customerId : customerIdsList) {
								if (customerId != null) {
									CustomerConsult customerConsult = customerConsultDAO
											.selectCustomerConsultById(customerId.longValue());
									try {
										Authorization authorization = env.getAuthorizationService();
										authorization.deleteAuth(CustomerConsult.class, customerId);
										authorization.createAuth(CustomerConsult.class, customerId,
												Integer.valueOf(customerConsult.getReceptionistId()),
												Integer.parseInt(Constants.SYSTEM_SIGN));
										customerConsultSum = customerIdsList.size();
									} catch (Exception e) {
										message.setSuccess(false);
										e.printStackTrace();
									}
								}
							}
							
						}
						
						
						
					}
			}
		}
		message.setMsg("一共更新了" + creditApplicationSum + "条申请数据,"+customerConsultSum+"条咨询数据");
		return message;
	}
}
