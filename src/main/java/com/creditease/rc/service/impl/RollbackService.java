/**
 * Title:RollbackService.java  
 * Description:  
 */
package com.creditease.rc.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.creditease.core.security.SpringSecurityUtils;
import com.creditease.rc.app.icp.rollback.ContractReturnReq;
import com.creditease.rc.app.icp.rollback.ContractReturnRes;
import com.creditease.rc.common.Constants;
import com.creditease.rc.dao.IAmountConfirmDAO;
import com.creditease.rc.dao.ICreditApplicationDAO;
import com.creditease.rc.dao.IGroupLoanRegistDAO;
import com.creditease.rc.dao.IRlApplyAuditTableDao;
import com.creditease.rc.domain.CreditApplication;
import com.creditease.rc.domain.GroupLoanRegist;
import com.creditease.rc.domain.Message;
import com.creditease.rc.service.IDiscountConfigurationService;
import com.creditease.rc.service.IFinanceMoneyService;
import com.creditease.rc.service.IOperateLogService;
import com.creditease.rc.service.IRollbackService;
import com.creditease.rc.service.IcpServiceInterface;
import com.creditease.rc.util.CommonUtil;
import com.creditease.rc.util.DicUtil;
import com.creditease.rc.vo.OperateLogBusinessStruct;

/**
 * Title:RollbackService.java  
 * Description:  
 * Copyright:Copyright (c) 2011 
 * Company:普信恒业科技发展（北京）有限公司 
 * @author 韩大年  
 * @version v1.0 
 * 2013-5-15 
 */
@Service
public class RollbackService implements IRollbackService {

	/**
	 * @Description 默认构造器 
	 */
	public RollbackService() {
		// TODO Auto-generated constructor stub
	}
	@Resource
	private ICreditApplicationDAO creditApplicationDAO;
	@Resource
	private IRlApplyAuditTableDao iRlApplyAduitTableDao;
	@Resource
	private IOperateLogService operateLogService;
	@Resource
	private IFinanceMoneyService financeMoneyService; 
	// 放款登记服务
	@Resource
	private IGroupLoanRegistDAO groupLoanRegistDAO;
	@Resource
	private IAmountConfirmDAO amountConfirmDAO;
	@Resource
	private IDiscountConfigurationService discountConfigurationService;
	@Resource
	private IcpServiceInterface icpServiceInterface;
	
	
	/**
	 * 
	 * @author 韩大年  
	 * @Description:  所有回退调用的方法
	 * @param creditapplicationId
	 * @return 
	 * @version v1.0 
	 * 2013-5-15
	 */
	public Message rollback(Long creditapplicationId){
		Message message= new Message();
		if(null!=creditapplicationId){
			CreditApplication creditApplicationEntity=creditApplicationDAO.selectById(creditapplicationId);
			if(null!=creditApplicationEntity){
				String auditState=creditApplicationEntity.getAuditStatus();
				
				if(Constants.STATE_1.equals(auditState)){         //01待审批-回退
					message=creditApplicatoinSumbit_Rollback(creditapplicationId);
				}else if(Constants.STATE_24.equals(auditState)){  //24审批中-回退
					message=applyAuditTable_Rollback(creditapplicationId);
				}else if(Constants.STATE_4.equals(auditState)){   //04审批通过-回退
					message=applyAuditTable_Rollback(creditapplicationId);
                    if(message.isSuccess()){
                        creditApplicationDAO.updateAmountByCreditApplicationId(creditapplicationId);
                    }
				}else if("40".equals(auditState)){
					// 添加推送综合信贷回退   判断是否是推送到综合信贷
					String queryIsZhxindai = discountConfigurationService.queryIsZhxindai(creditapplicationId);
					if(CommonUtil.isNotEmpty(queryIsZhxindai)){
						if("1".equals(queryIsZhxindai)){
							//调用综合信贷回退接口
							ContractReturnReq contractReturnReq = icpServiceInterface.getContractReturnReq(creditapplicationId);
							contractReturnReq.setOperator(creditApplicationEntity.getLoanOfficerName());
							ContractReturnRes clicContractReturnRuralReq = icpServiceInterface.clicContractReturnRuralReq(contractReturnReq);
							//  综合信贷回退返回值
							if("0".equals(clicContractReturnRuralReq.getResultFlg())){//证明可以回退
								message=applyAuditTable_Rollback(creditapplicationId);
								if(message.isSuccess()){
									creditApplicationDAO.updateAmountByCreditApplicationId(creditapplicationId);
								}
							}else{
								message.setMsg("该笔数据已经在ICP不能回退，请联系ICP人员");
								message.setSuccess(false);
								
							}
						}
					}else{
						//原来流程不变
						message=applyAuditTable_Rollback(creditapplicationId);
						if(message.isSuccess()){
							creditApplicationDAO.updateAmountByCreditApplicationId(creditapplicationId);
						}
					}
				} else if(Constants.STATE_31.equals(auditState)){  //31待城市审批-回退
					message=applyAuditTable_Rollback(creditapplicationId);
				}else if(Constants.STATE_32.equals(auditState)){   //32城市审批中-回退
					message=applyAuditTable_Rollback(creditapplicationId);
				}else if(Constants.STATE_34.equals(auditState)){   //32等待重新打印合同-回退
					message=applyAuditTable_Rollback(creditapplicationId);
				}else if(Constants.STATE_21.equals(auditState)){  //额度确认回退 对私与对公：21-额度确认
					if("1".equals(creditApplicationEntity.getBusinessType())){
						//对私
						message=amountConfirm_Rollback(creditapplicationId);
					}else {
						//对公
						message=financeMoneyService.paymentUndo(Integer.valueOf(creditapplicationId.toString()));
					}
				}else if(Constants.STATE_11.equals(auditState)){  //放款登记回退
					message=groupLoanRegist_Rollback(creditapplicationId);
				}else if(Constants.STATE_13.equals(auditState)){  // 放款确认回退，只有对私存在-13放款确认 
					message= financeMoneyService.paymentUndo(Integer.valueOf(creditapplicationId.toString()));
				}else{
					message.setSuccess(false);
					message.setMsg("该笔业务的状态不允许回退，当前状态为："+DicUtil.convertCodeKeyToCodeValue("auditStatus",auditState));
				}
                /*2014-11-04回退操作后无需再次点击参审*/
                creditApplicationEntity = creditApplicationDAO.selectById(creditapplicationId);
                if(!auditState.equals(creditApplicationEntity.getAuditStatus())){
                    creditApplicationDAO.updateCityParticipatePerman(creditapplicationId.intValue(),"F");
                }
			}else{
				message.setSuccess(false);
				message.setMsg("查询无该笔申请单");
			}
		}else{
			message.setSuccess(false);
			message.setMsg("申请单id为空");
		}

			return message;
	}
	/**
	 * 
	 * @author 韩大年  
	 * @Description:  已提交的回退
	 * @param creditapplicationId
	 * @return 
	 * @version v1.0 
	 * 2013-5-16
	 */
	public Message creditApplicatoinSumbit_Rollback(Long creditapplicationId){
		Message message= new Message();
		message.setSuccess(true);
		OperateLogBusinessStruct operateLogBusinessStruct = new OperateLogBusinessStruct();
		operateLogBusinessStruct.setCreditapplicationId( creditapplicationId);
		operateLogBusinessStruct.setResult("未提交");
		operateLogBusinessStruct.setRemark("待审批回退成功,回退后状态：未提交");
		operateLogBusinessStruct.setFunctionCode("10002*");
		operateLogService.insert(operateLogBusinessStruct);
		
		CreditApplication creditApplicationVo = new CreditApplication();
		creditApplicationVo.setCreditapplicationId(Integer.valueOf(creditapplicationId.toString()));
		//未提交
		creditApplicationVo.setAuditStatus(Constants.STATE_0);
		creditApplicationDAO.submitAuditing(creditApplicationVo);

        /*2014-11-04移动到外层主方法(this.rollback(args))内，适用于所有状态下回退操作数据更新，
        更新区域经理/区域风险经理参审状态为历史数据，即再次提交申请时，需要重新参审。
        creditApplicationDAO.updateCityParticipate(creditapplicationId.intValue(),"0");
        creditApplicationDAO.updateCityParticipatePerman(creditapplicationId.intValue(),"T");*/
		return message;
	}
	
	/**
	 * 
	 * @author 韩大年  
	 * @Description:  审查中、等待重新打印合同、审批通过回退
	 * @param creditapplicationId
	 * @return 
	 * @version v1.0 
	 * 2013-5-15
	 */
	public Message applyAuditTable_Rollback(Long creditapplicationId){
		Message message= new Message();
		message.setSuccess(true);
		//审批记录变更成历史数据
		int i = iRlApplyAduitTableDao.updateApplyAuditByCreditapplicationId(Integer.valueOf(creditapplicationId.toString()));
		if(0<i){
			OperateLogBusinessStruct operateLogBusinessStruct = new OperateLogBusinessStruct();
			operateLogBusinessStruct.setCreditapplicationId(creditapplicationId);
			operateLogBusinessStruct.setFunctionCode("20003**");
			operateLogBusinessStruct.setResult("未提交");
			operateLogBusinessStruct.setRemark("审查中/审批通过/等待重新打印合同的回退,回退后状态:未提交");
			operateLogService.insert(operateLogBusinessStruct);
			
			CreditApplication creditApplicationVo = new CreditApplication();
			creditApplicationVo.setCreditapplicationId(Integer.valueOf(creditapplicationId.toString()));
			//未提交
			creditApplicationVo.setAuditStatus(Constants.STATE_0);
			creditApplicationDAO.submitAuditing(creditApplicationVo);

            /*更新区域经理/区域风险经理参审状态为历史数据，即再次提交申请时，需要重新参审。
              2014-11-04需求变更--->不需要再次点击重新参审即可参与审批。*/
//            creditApplicationDAO.updateCityParticipate(creditapplicationId.intValue(),"0");
//            creditApplicationDAO.updateCityParticipatePerman(creditapplicationId.intValue(),"T");
		}else{
			message.setSuccess(false);
			message.setMsg("根据申请查询无审批记录");
		}

		return message;
	}
	
	/**
	 * 
	 * @author 韩大年  
	 * @Description:  额度确认回退:个人卡放款
	 * @param creditapplicationId
	 * @return 
	 * @version v1.0 
	 * 2013-5-17
	 */
	public Message amountConfirm_Rollback(Long creditapplicationId){
		Message message= new Message();
		message.setSuccess(true);
		CreditApplication entityApplication=creditApplicationDAO.selectById(creditapplicationId);
		if(null!=entityApplication){
			String businessType=entityApplication.getBusinessType();
			if(CommonUtil.isNotEmpty(businessType) && !"null".equals(businessType)){
				//个人卡放款-修改业务状态和额度确认表历史标志
				OperateLogBusinessStruct operateLogBusinessStruct = new OperateLogBusinessStruct();
				operateLogBusinessStruct.setCreditapplicationId( creditapplicationId);
				operateLogBusinessStruct.setResult("审批通过");
				operateLogBusinessStruct.setRemark("额度确认回退成功,个人卡放款回退后状态：审批通过");
				operateLogBusinessStruct.setFunctionCode("30001*");
				operateLogService.insert(operateLogBusinessStruct);
				
				CreditApplication creditApplicationVo = new CreditApplication();
				creditApplicationVo.setCreditapplicationId(Integer.valueOf(creditapplicationId.toString()));
				//审批通过
				creditApplicationVo.setAuditStatus(Constants.STATE_4);
				creditApplicationDAO.submitAuditing(creditApplicationVo);
				amountConfirmDAO.updateAmountConfirm(Integer.valueOf(creditapplicationId.toString()));
				
			}else{
				message.setSuccess(false);
				message.setMsg("申请单的业务类型为空");
				return message;
			}
		}else{
			message.setSuccess(false);
			message.setMsg("查询无申请单信息");
			return message;
		}
		//个人卡放款 的返回
		return message;
	}
	
	/**
	 * 
	 * @author 韩大年  
	 * @Description:  放款登记回退
	 * @param creditapplicationId
	 * @return 
	 * @version v1.0 
	 * 2013-5-17
	 */
	public Message groupLoanRegist_Rollback(Long creditapplicationId){
		Message message= new Message();
		message.setSuccess(true);
		CreditApplication entityApplication=creditApplicationDAO.selectById(creditapplicationId);
		
		CreditApplication creditApplicationVo = new CreditApplication();
		creditApplicationVo.setCreditapplicationId(Integer.valueOf(creditapplicationId.toString()));
		
		OperateLogBusinessStruct operateLogBusinessStruct = new OperateLogBusinessStruct();
		operateLogBusinessStruct.setCreditapplicationId(creditapplicationId);
		operateLogBusinessStruct.setFunctionCode("30003*");
		if("1".equals(entityApplication.getBusinessType())){
			
			operateLogBusinessStruct.setResult("额度确认");
			operateLogBusinessStruct.setRemark("客户经理操作放款登记回退，放款类型是个人的时候 状态改为 “额度确认”");
			//21放款额度确认
			creditApplicationVo.setAuditStatus(Constants.STATE_21);
			
		}else{
			operateLogBusinessStruct.setResult("款项到位");
			operateLogBusinessStruct.setRemark("客户经理操作放款登记回退，放款类型是分公司的时候 状态改为 “款项到位”");
			//10款项到位  
			creditApplicationVo.setAuditStatus(Constants.STATE_10);
		}
		
		GroupLoanRegist groupLoanRegistVO = new GroupLoanRegist();
		groupLoanRegistVO.setCreditapplicationId(entityApplication.getCreditapplicationId());
		groupLoanRegistVO.setRegistStatus("0");
		GroupLoanRegist groupLoanRegistEntity =groupLoanRegistDAO.selectDengji(groupLoanRegistVO);
		//更新放款登记记录
		if(null!=groupLoanRegistEntity){
			groupLoanRegistVO.setGroupLoanRegistId(groupLoanRegistEntity.getGroupLoanRegistId());
			//放款登记记录变更为历史标志
			groupLoanRegistVO.setRegistStatus("2");
			groupLoanRegistVO.setLoanPerson(SpringSecurityUtils.getCurrentUserName());
			groupLoanRegistVO.setRemarkComment("客户经理操作放款登记回退");
			groupLoanRegistVO.setLoanConfirmTime(new Date());
			groupLoanRegistDAO.updateGroupLoanRegist(groupLoanRegistVO);
		}else{
			message.setSuccess(false);
			message.setMsg("查询无放款登记记录");
			return message;
		}
		//更新业务状态
		creditApplicationDAO.submitAuditing(creditApplicationVo);
		//插入日志
		operateLogService.insert(operateLogBusinessStruct);
		
		return message;
	}
	
	/**
	 * 
	 * @author 韩大年  
	 * @Description:  放款确认回退:对公的回退
	 * @param creditapplicationId
	 * @return 
	 * @version v1.0 
	 * 2013-5-17
	 */
	public Message groupLoanAffirm_Rollback(Long creditapplicationId){
		Message message= new Message();
		message.setSuccess(true);
		CreditApplication entityApplication=creditApplicationDAO.selectById(creditapplicationId);
		
		CreditApplication creditApplicationVo = new CreditApplication();
		creditApplicationVo.setCreditapplicationId(Integer.valueOf(creditapplicationId.toString()));
		
		OperateLogBusinessStruct operateLogBusinessStruct = new OperateLogBusinessStruct();
		operateLogBusinessStruct.setCreditapplicationId(creditapplicationId);
		operateLogBusinessStruct.setFunctionCode("30004*");
		GroupLoanRegist groupLoanRegistVO = new GroupLoanRegist();
		groupLoanRegistVO.setCreditapplicationId(entityApplication.getCreditapplicationId());
		//查询放款确认状态 的数据
		groupLoanRegistVO.setRegistStatus("1");
		GroupLoanRegist groupLoanRegistEntity =groupLoanRegistDAO.selectDengji(groupLoanRegistVO);
		//更新放款登记记录
		if(null!=groupLoanRegistEntity){
			groupLoanRegistVO.setGroupLoanRegistId(groupLoanRegistEntity.getGroupLoanRegistId());
			groupLoanRegistVO.setLoanConfirmTime(new Date());
			//更新登记记录状态为已放款登记。同时清除确认人和确认日期
			groupLoanRegistDAO.updateGroupLoanAffirmRollback(groupLoanRegistVO);
		}else{
			message.setSuccess(false);
			message.setMsg("查询无放款确认记录");
			return message;
		}	
		creditApplicationVo.setAuditStatus(Constants.STATE_11);
		//更新业务状态
		creditApplicationDAO.submitAuditing(creditApplicationVo);
		//插入日志
		operateLogBusinessStruct.setResult("已放款登记");
		operateLogBusinessStruct.setRemark("客户经理操作放款确认回退，回退到已放款登记状态");
		operateLogService.insert(operateLogBusinessStruct);
		return message;
			
		
	}
	
	
}
