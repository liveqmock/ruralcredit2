/**
 * Title:OperateLogService.java  
 * Description:  
 */
package com.creditease.rc.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.creditease.core.security.SpringSecurityUtils;
import com.creditease.rc.dao.IOperateLogDAO;
import com.creditease.rc.domain.OperateLog;
import com.creditease.rc.framework.exception.AppBusinessException;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.service.IOperateLogService;
import com.creditease.rc.util.CommonUtil;
import com.creditease.rc.util.DicUtil;
import com.creditease.rc.vo.OperateLogBusinessStruct;
import com.creditease.rc.vo.OperateLogVO;

/**
 * Title:OperateLogService.java  
 * Description:  
 * Copyright:Copyright (c) 2011 
 * Company:普信恒业科技发展（北京）有限公司 
 * @author 韩大年  
 * @version v1.0 
 * 2013-3-11 
 */
@Service
public class OperateLogService implements IOperateLogService {

	/**
	 * @Description 默认构造器 
	 */
	public OperateLogService() {
		// TODO Auto-generated constructor stub
	}
	@Resource
	private IOperateLogDAO operateLogDAO;
	/**
	 * 
	 * @author 韩大年  
	 * @Description:  日志插入
	 * @param operateLog
	 * @return 
	 * @version v1.0 
	 * 2013-3-11
	 */
	public Long insert(OperateLog operateLog) {
		if(null==operateLog){
			operateLog= new OperateLog();
		}
		if(null ==operateLog.getCreditapplicationId()){
			throw new AppBusinessException("申请单主键为空");
		}
		if(CommonUtil.isEmpty(operateLog.getFunctionCode())){
			throw new AppBusinessException("日志模块代码为空");
		}
		operateLog.setOperateDate(new Date());
		if(null!=SpringSecurityUtils.getCurrentUser()){
			operateLog.setOperatorId(SpringSecurityUtils.getCurrentUser().getUserId());
			operateLog.setOperatorName(SpringSecurityUtils.getCurrentUser().getName_zh());
		}
		return operateLogDAO.insert(operateLog);
    }
	
	/**
	 * 
	 * @author 韩大年  
	 * @Description:  创建并组装日志对象方法
	 * @param operateLogBusinessStruct
	 * @return 
	 * @version v1.0 
	 * 2013-3-13
	 */
	private OperateLog createOperateLog(OperateLogBusinessStruct operateLogBusinessStruct){
		if(operateLogBusinessStruct==null){
			operateLogBusinessStruct = new OperateLogBusinessStruct();
		}
		if(null ==operateLogBusinessStruct.getCreditapplicationId()){
			throw new AppBusinessException("申请单主键为空");
		}
		if(CommonUtil.isEmpty(operateLogBusinessStruct.getFunctionCode())){
			throw new AppBusinessException("日志模块代码为空");
		}
		OperateLog operateLog= new OperateLog();
		operateLog.setCreditapplicationId(operateLogBusinessStruct.getCreditapplicationId());
		operateLog.setFunctionCode(operateLogBusinessStruct.getFunctionCode());
		operateLog.setOperateDate(new Date());
		try{
			if(null!=SpringSecurityUtils.getCurrentUser()){
				operateLog.setOperatorId(SpringSecurityUtils.getCurrentUser().getUserId());
				operateLog.setOperatorName(SpringSecurityUtils.getCurrentUser().getName_zh());
			}
		}catch(Exception e){
			operateLog.setOperatorId("");
			operateLog.setOperatorName("");
		}
			
		StringBuffer stringBuffer = new StringBuffer();
		/*****************************申请模块字段******************************/
		if(CommonUtil.isNotEmpty(operateLogBusinessStruct.getBorrowerName())){
			stringBuffer.append("借款人姓名="+operateLogBusinessStruct.getBorrowerName()+";");
		}
		if(CommonUtil.isNotEmpty(operateLogBusinessStruct.getAuditAmount())){
			stringBuffer.append("申请金额="+operateLogBusinessStruct.getAuditAmount()+";");
		}
		if(CommonUtil.isNotEmpty(operateLogBusinessStruct.getApplyDate())){
			stringBuffer.append("申请日期="+operateLogBusinessStruct.getApplyDate()+";");
		}
		if(CommonUtil.isNotEmpty(operateLogBusinessStruct.getBusinessNumber())){
			stringBuffer.append("业务单号="+operateLogBusinessStruct.getBusinessNumber()+";");
		}
		if(CommonUtil.isNotEmpty(operateLogBusinessStruct.getRepaymentPlanName())){
			stringBuffer.append("还款方案="+operateLogBusinessStruct.getRepaymentPlanName()+";");
		}
		/*****************************审批模块*********************************/
		if(CommonUtil.isNotEmpty(operateLogBusinessStruct.getAuditAmount())){
			stringBuffer.append("审查金额="+operateLogBusinessStruct.getAuditAmount()+";");
		}
		if(CommonUtil.isNotEmpty(operateLogBusinessStruct.getAuditConfirmAmount())){
			stringBuffer.append("审批金额="+operateLogBusinessStruct.getAuditConfirmAmount()+";");
		}
		/*****************************放款登记模块******************************/
		if(CommonUtil.isNotEmpty(operateLogBusinessStruct.getConfirmAmount())){
			stringBuffer.append("额度确认金额="+operateLogBusinessStruct.getConfirmAmount()+";");
		}
		if(CommonUtil.isNotEmpty(operateLogBusinessStruct.getExpectLoanDate())){
			stringBuffer.append("期望放款日期="+operateLogBusinessStruct.getExpectLoanDate()+";");
		}
		if(CommonUtil.isNotEmpty(operateLogBusinessStruct.getLoanAmount())){
			stringBuffer.append("放款登记金额="+operateLogBusinessStruct.getLoanAmount()+";");
		}
		if(CommonUtil.isNotEmpty(operateLogBusinessStruct.getRealAmount())){
			stringBuffer.append("实发金额="+operateLogBusinessStruct.getRealAmount()+";");
		}
		if(CommonUtil.isNotEmpty(operateLogBusinessStruct.getLoanTime())){
			stringBuffer.append("放款日期="+operateLogBusinessStruct.getLoanTime()+";");
		}
		if(CommonUtil.isNotEmpty(operateLogBusinessStruct.getProtocolNO())){
			stringBuffer.append("合同编号="+operateLogBusinessStruct.getProtocolNO()+";");
		}
		/*****************************收款模块*********************************/
		if(CommonUtil.isNotEmpty(operateLogBusinessStruct.getReceivedAmount())){
			stringBuffer.append("收款登记金额="+operateLogBusinessStruct.getReceivedAmount()+";");
		}
		if(CommonUtil.isNotEmpty(operateLogBusinessStruct.getReceivedTime())){
			stringBuffer.append("实还日期="+operateLogBusinessStruct.getReceivedTime()+";");
		}
		/*****************************财务模块*********************************/
		if(CommonUtil.isNotEmpty(operateLogBusinessStruct.getAccountNo())){
			stringBuffer.append("账号="+operateLogBusinessStruct.getAccountNo()+";");
		}
		if(CommonUtil.isNotEmpty(operateLogBusinessStruct.getAccountName())){
			stringBuffer.append("账户名称="+operateLogBusinessStruct.getAccountName()+";");
		}
		if(CommonUtil.isNotEmpty(operateLogBusinessStruct.getIdCard())){
			stringBuffer.append("证件号码="+operateLogBusinessStruct.getIdCard()+";");
		}
		if(CommonUtil.isNotEmpty(operateLogBusinessStruct.getSubscribeTime())){
			stringBuffer.append("预约时间="+operateLogBusinessStruct.getSubscribeTime()+";");
		}
		if(CommonUtil.isNotEmpty(operateLogBusinessStruct.getTradeTime())){
			stringBuffer.append("结算平台执行时间="+operateLogBusinessStruct.getTradeTime()+";");
		}
		if(CommonUtil.isNotEmpty(operateLogBusinessStruct.getSubscribeAmount())){
			stringBuffer.append("预约金额="+operateLogBusinessStruct.getSubscribeAmount()+";");
		}
		if(CommonUtil.isNotEmpty(operateLogBusinessStruct.getBizid())){
			stringBuffer.append("订单号="+operateLogBusinessStruct.getBizid()+";");
		}
		/*****************************免罚模块*********************************/
		if(CommonUtil.isNotEmpty(operateLogBusinessStruct.getImpunityAmount())){
			stringBuffer.append("免罚金额="+operateLogBusinessStruct.getImpunityAmount()+";");
		}
		/******************************通用字段**********************************/
		if(CommonUtil.isNotEmpty(operateLogBusinessStruct.getResult())){
			stringBuffer.append("处理结果="+operateLogBusinessStruct.getResult()+";");
		}
		if(CommonUtil.isNotEmpty(operateLogBusinessStruct.getRemark())){
			stringBuffer.append("备注="+operateLogBusinessStruct.getRemark()+";");
		}
		
		if(CommonUtil.isEmpty(stringBuffer.toString())){
			String msg=DicUtil.convertCodeKeyToCodeValue("operateLogType",operateLogBusinessStruct.getFunctionCode());
			operateLog.setFunctionBussiness(msg);
		}else{
			operateLog.setFunctionBussiness(stringBuffer.toString());
		}
		
		return operateLog;
	}
	
	/**
	 * 
	 * @author 韩大年  
	 * @Description:  日志插入
	 * @param operateLogBusinessStruct
	 * @return 
	 * @version v1.0 
	 * 2013-3-11
	 */
	public Long insert(OperateLogBusinessStruct operateLogBusinessStruct){
		OperateLog operateLog=createOperateLog(operateLogBusinessStruct);
		return operateLogDAO.insert(operateLog);
	}
	
	 /**
     * 
     * @author 韩大年  
     * @Description:  批量插入日志方法
     * @param oList 
     * @version v1.0 
     * 2013-3-13
     */
	public void batchInsert(List<OperateLogBusinessStruct> oList) {
		List<OperateLog> operateLogList = null;
		if(CommonUtil.isNotEmpty(oList)){
			operateLogList= new ArrayList<OperateLog>();
			for(OperateLogBusinessStruct operateLogBusinessStruct:oList){
				OperateLog operateLog=createOperateLog(operateLogBusinessStruct);
				operateLogList.add(operateLog);
			}
			if(CommonUtil.isNotEmpty(operateLogList)){
				operateLogDAO.batchInsert(operateLogList);
			}
		}
	}
	 /**
     * 
     * @author 韩大年  
     * @Description:  日志查询
     * @param operateLogVO
     * @param pagination
     * @return 
     * @version v1.0 
     * 2013-3-12
     */
    public Pagination selectOperateLogByPagination(OperateLogVO operateLogVO,Pagination pagination){
    	return this.operateLogDAO.selectOperateLogByPagination(operateLogVO, pagination);
    }

    public Pagination queryOperateLogByCreditId(String creditAppId,Pagination pagination){
    	return this.operateLogDAO.queryOperateLogByCreditId(creditAppId, pagination);
    }
}
