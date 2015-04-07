package com.creditease.rc.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.creditease.core.security.SpringSecurityUtils;
import com.creditease.core.security.User;
import com.creditease.rc.domain.AcceptConsultRollback;
import com.creditease.rc.domain.CustomerConsultPool;
import com.creditease.rc.domain.CustomerConsultPoolLog;
import com.creditease.rc.service.IAcceptConsultRollbackService;
import com.creditease.rc.service.IConsultPoolAndRollbackService;
import com.creditease.rc.service.ICustomerConsultPoolLogService;
import com.creditease.rc.service.ICustomerConsultPoolService;
import com.creditease.rc.service.IOperateLogService;
import com.creditease.rc.vo.CustomerConsultPoolVo;

@Service
public class ConsultPoolAndRollbackService implements IConsultPoolAndRollbackService {

	@Resource
	private ICustomerConsultPoolService customerConsultPoolService;

	@Resource
	private IAcceptConsultRollbackService acceptConsultRollbackService;

	@Resource
	private IOperateLogService operateLogService;
	
	@Resource
	private ICustomerConsultPoolLogService customerConsultPoolLogService;

	//退回到待退回确认
	//更新受理咨询状态为："待退回确认"
	@Override
	public boolean updateRollBackAndConsultStatus(CustomerConsultPoolVo customerConsultPoolVo,AcceptConsultRollback acceptConsultRollback) {

		User user = SpringSecurityUtils.getCurrentUser();
		// 组织数据
		acceptConsultRollback.setOperator(user.getName_zh());	//操作人姓名
		Date operateTimeDate = new Date();
		acceptConsultRollback.setOperateDate(operateTimeDate);  //操作时间
		acceptConsultRollback.setOperatorId(user.getUserId());	//操作人Id
		CustomerConsultPool customerconsultPool=new CustomerConsultPool();
		System.out.println(customerConsultPoolVo.getConsultPoolId());
		//调用查询受理咨询方法
		customerconsultPool=customerConsultPoolService.queryCustomerConsultPool(customerConsultPoolVo
				.getConsultPoolId());
		
		acceptConsultRollback.setOperatorBeforeState(customerconsultPool.getAcceptConsultState());  //操作前状态
		acceptConsultRollback.setOperatorBeforeSection("acceptConsultState");     							//操作前状态的section
		
		// 加日志 begin
		CustomerConsultPoolLog customerConsultPoolLog=new CustomerConsultPoolLog();
		customerConsultPoolLog.setConnectionId(acceptConsultRollback.getConsultPoolId());  //咨询池Id
		customerConsultPoolLog.setConnectionCetegory("0");			//关联id分类 0-营销咨询log
		customerConsultPoolLog.setOptModule("b00011");				//操作模块，即保存日志所涉及模块，如批量导入，
		customerConsultPoolLog.setCurrStatus(customerconsultPool.getAcceptConsultState()); //操作前状态
		customerConsultPoolLog.setCurrConnectionDicSection("acceptConsultState");			//操作前状态的section
		customerConsultPoolLog.setOptDate(new Date());	//操作时间
		
		Map<String, String> contentMap=new HashMap<String, String>();
		String rollbackReason=acceptConsultRollback.getRollbackReason();
		contentMap.put("退回原因",rollbackReason);    //退回原因
		//end
		
		//添加退回原因
		acceptConsultRollback.setRollbackReason(acceptConsultRollback.getRollbackReason());
		// 更新受理咨询状态为："待退回确认"
		boolean isSuccessStatus = false;
		isSuccessStatus = customerConsultPoolService.updateMarkingConsultStatus(customerconsultPool
				.getConsultPoolId());
		
		//调用查询受理咨询列表方法
		customerconsultPool=customerConsultPoolService.queryCustomerConsultPool(customerConsultPoolVo
				.getConsultPoolId());
		
		//加日志begin
		customerConsultPoolLog.setNextStatus(customerconsultPool.getAcceptConsultState());   //操作后状态
		customerConsultPoolLog.setNextConnectionDicSection("acceptConsultState");		//操作后状态的section
		//调用添加日志方法
		customerConsultPoolLogService.insert(customerConsultPoolLog, contentMap);
		//加日志 **end
		
		
		acceptConsultRollback.setOperatorAfterState(customerconsultPool.getAcceptConsultState()); //操作后状态
		acceptConsultRollback.setOperatorAfterSection("acceptConsultState");							//操作后状态的section
		
		acceptConsultRollback.setHistoryFlag("0");											//为当前申请退回的最新操作  "0"
		
		acceptConsultRollback.setApplyOperator("0");										//记录申请操作为0代表是"申请退回时操作"
		
		// 更新退回原因对象信息为历史
		boolean isSuccessForUpdate = false;
		isSuccessForUpdate = acceptConsultRollbackService.updateAcceptConsultRollbackForHis(acceptConsultRollback
				.getConsultPoolId());
		
		//调用添加申请退回申请是记录的方法
		boolean isSuccessForInsert = false;
		isSuccessForInsert = acceptConsultRollbackService.addAcceptConsultRollback(acceptConsultRollback);

		
		// 插入一条退回圆心对象信息
		//boolean isSuccessForInsert = false;
		//isSuccessForInsert = acceptConsultRollbackService.addAcceptConsultRollback(acceptConsultRollback);

		
		
// CunsultLog cunsultLog = new CunsultLog();
// cunsultLog.setConnectionId(acceptConsultRollback.getConsultPoolId());
// cunsultLog.setOptModule("b00011");
// cunsultLog.setCurrApplicationStatus("3");
// cunsultLog.setNextApplicationStatus("1");
// cunsultLog.setConnectionCetegory("1");
// cunsultLog.setConnectionDictionarySection("acceptConsultStatus");
// Map contentMap = new HashMap();
// contentMap.put("退回原因", acceptConsultRollback.getRollbackReason());
// operateLogService.insertOpt(cunsultLog, contentMap);

		return (isSuccessStatus && isSuccessForUpdate && isSuccessForInsert);
	}

	
	/**
	 * 更新退回原因和营销咨询状态     为已退回
	 */
	@Override
	public boolean updateMarkingConsultStatusConfirm(CustomerConsultPoolVo customerconsultPoolVo,AcceptConsultRollback acceptConsultRollback){
		User user = SpringSecurityUtils.getCurrentUser();
		// 组织数据
		acceptConsultRollback.setOperator(user.getName_zh());	//操作人姓名
		Date operateTimeDate = new Date();
		acceptConsultRollback.setOperateDate(operateTimeDate);  //操作时间
		acceptConsultRollback.setOperatorId(user.getUserId());	//操作人Id
		CustomerConsultPool customerconsultPool=new CustomerConsultPool();
		//调用查询受理咨询方法
		customerconsultPool=customerConsultPoolService.queryCustomerConsultPool(customerconsultPoolVo
				.getConsultPoolId());
		acceptConsultRollback.setOperatorBeforeState(customerconsultPool.getAcceptConsultState());  //操作前状态
		acceptConsultRollback.setOperatorBeforeSection("acceptConsultState");	//操作前的section
		acceptConsultRollback.setApplyOperator("1");//记录申请操作为1代表是"申请退回时操作"
		acceptConsultRollback.setHistoryFlag("1");	//为当前申请退回的最新操作  "1"
		
		
		// 加日志** begin
		CustomerConsultPoolLog customerConsultPoolLog=new CustomerConsultPoolLog();
		customerConsultPoolLog.setConnectionId(acceptConsultRollback.getConsultPoolId());  //咨询池Id
		customerConsultPoolLog.setConnectionCetegory("0");			//关联id分类 0-营销咨询log
		customerConsultPoolLog.setOptModule("b00011");				//操作模块，即保存日志所涉及模块，如批量导入，
		customerConsultPoolLog.setCurrStatus(customerconsultPool.getAcceptConsultState()); //操作前状态
		customerConsultPoolLog.setCurrConnectionDicSection("acceptConsultState");			//操作前状态的section
		customerConsultPoolLog.setOptDate(new Date());	//操作时间
		
		Map<String, String> contentMap=new HashMap<String, String>();
		//String rollbackReason=acceptConsultRollback.getRollbackReason();
		//contentMap.put("rollbackReason",rollbackReason);    //退回原因
		//end
		
		
			boolean isSuccessStatusConfirm = false;
			 isSuccessStatusConfirm = customerConsultPoolService.updateMarkingConsultStatusConfirm(
					acceptConsultRollback.getConsultPoolId());
			 
			//加日志begin
				customerConsultPoolLog.setNextStatus(customerconsultPool.getMarketConsultState());   //操作后状态
				customerConsultPoolLog.setNextConnectionDicSection("marketConsultStatus");		//操作后状态的section
				//调用添加日志方法
				customerConsultPoolLogService.insert(customerConsultPoolLog, contentMap);
				//加日志 **end
			 
			 
			 acceptConsultRollback.setOperatorAfterState(customerconsultPool.getMarketConsultState());   //操作后状态
			 acceptConsultRollback.setOperatorAfterSection("marketConsultStatus");//操作后的section
			//添加退回原因
				//acceptConsultRollback.setRollbackReason(acceptConsultRollback.getRollbackReason());
			//调用添加申请退回申请是记录的方法
				boolean isSuccessForInsert = false;
				isSuccessForInsert = acceptConsultRollbackService.addAcceptConsultRollback(acceptConsultRollback);
			return (isSuccessStatusConfirm&&isSuccessForInsert);
	}

	/**
	 * 取消退回操作
	 */
	@Override
	public boolean updateRollbackReasonCancel(
			CustomerConsultPoolVo customerConsultPoolVo,
			AcceptConsultRollback acceptConsultRollback) {
		User user = SpringSecurityUtils.getCurrentUser();
		// 组织数据
		acceptConsultRollback.setOperator(user.getName_zh());	//操作人姓名
		Date operateTimeDate = new Date();
		acceptConsultRollback.setOperateDate(operateTimeDate);  //操作时间
		acceptConsultRollback.setOperatorId(user.getUserId());	//操作人Id
		CustomerConsultPool customerconsultPool=new CustomerConsultPool();
		//调用查询受理咨询方法
		customerconsultPool=customerConsultPoolService.queryCustomerConsultPool(customerConsultPoolVo
				.getConsultPoolId());
		acceptConsultRollback.setOperatorBeforeState(customerconsultPool.getAcceptConsultState());  //操作前状态
		acceptConsultRollback.setOperatorBeforeSection("acceptConsultState");						//操作前的section
		acceptConsultRollback.setApplyOperator("1");//记录申请操作为1代表是"申请退回时操作"
		acceptConsultRollback.setHistoryFlag("1");	//为当前申请退回的最新操作  "1"
		// 查询退回原因对象信息（当前标识为0）
		//acceptConsultRollback = acceptConsultRollbackService
				//.getAcceptConsultRollbackByForeignKey(customerConsultPoolVo.getConsultPoolId());
		//System.out.println(acceptConsultRollback.getRollbackReason());
		//添加退回原因
		//acceptConsultRollback.setRollbackReason(acceptConsultRollback.getRollbackReason());
		
		// 加日志** begin
		CustomerConsultPoolLog customerConsultPoolLog=new CustomerConsultPoolLog();
		customerConsultPoolLog.setConnectionId(acceptConsultRollback.getConsultPoolId());  //咨询池Id
		customerConsultPoolLog.setConnectionCetegory("0");			//关联id分类 0-营销咨询log
		customerConsultPoolLog.setOptModule("b00011");				//操作模块，即保存日志所涉及模块，如批量导入，
		customerConsultPoolLog.setCurrStatus(customerconsultPool.getAcceptConsultState()); //操作前状态
		customerConsultPoolLog.setCurrConnectionDicSection("acceptConsultState");			//操作前状态的section
		customerConsultPoolLog.setOptDate(new Date());	//操作时间
		
		Map<String, String> contentMap=new HashMap<String, String>();
		//String rollbackReason=acceptConsultRollback.getRollbackReason();
		//contentMap.put("rollbackReason",rollbackReason);    //退回原因
		//end
		
		
		// 更新受理咨询状态为："待分件"
		boolean isSuccessStatus = false;
		isSuccessStatus = customerConsultPoolService.updateAcceptConsultStatusCancel(customerconsultPool
				.getConsultPoolId());
		//调用查询受理咨询方法
		customerconsultPool=customerConsultPoolService.queryCustomerConsultPool(customerConsultPoolVo
				.getConsultPoolId());
		
		//加日志begin
		customerConsultPoolLog.setNextStatus(customerconsultPool.getAcceptConsultState());   //操作后状态
		customerConsultPoolLog.setNextConnectionDicSection("acceptConsultState");		//操作后状态的section
		//调用添加日志方法
		customerConsultPoolLogService.insert(customerConsultPoolLog, contentMap);
		//加日志 **end
		
		
		
		acceptConsultRollback.setOperatorAfterState(customerconsultPool.getAcceptConsultState()); //操作后状态
		acceptConsultRollback.setOperatorAfterSection("acceptConsultState");							//操作后状态的section
		//调用添加申请退回申请是记录的方法
		boolean isSuccessForInsert = false;
		isSuccessForInsert = acceptConsultRollbackService.addAcceptConsultRollback(acceptConsultRollback);

		return (isSuccessStatus&& isSuccessForInsert);
	}
}
