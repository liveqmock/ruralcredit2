package com.creditease.rc.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.creditease.rc.app.orgams.BaseResponse;
import com.creditease.rc.common.Constants;
import com.creditease.rc.dao.BorrowMatchingDAO;
import com.creditease.rc.dao.ICreditApplicationDAO;
import com.creditease.rc.domain.BorrowMatching;
import com.creditease.rc.domain.CreditApplication;
import com.creditease.rc.domain.Message;
import com.creditease.rc.service.IOperateLogService;
import com.creditease.rc.service.IorgamsService;
import com.creditease.rc.service.RevokeMatchTask;
import com.creditease.rc.util.CommonUtil;
import com.creditease.rc.vo.OperateLogBusinessStruct;

public class RevokeMatchTaskImpl implements RevokeMatchTask {

	@Resource
	private BorrowMatchingDAO borrowMatchingDAO;
	@Resource
	private IorgamsService iorgamsService;
	@Resource
	private IOperateLogService operateLogService;
	@Resource
	private ICreditApplicationDAO creditApplicationDAO;
	private Logger logger = Logger.getLogger(RevokeMatchTaskImpl.class);
	@Override
	public Message revokeMatch() {
		CreditApplication creditApplication = new CreditApplication();
		Message message = new Message();
		//得到需要撤销撮合的creditapplicationId
		List<Long> creditApplicationIds = borrowMatchingDAO.selectNotUpload(new Date());
		if(CommonUtil.isNotEmpty(creditApplicationIds)){
			for (Long creditApplicationId : creditApplicationIds) {
				BaseResponse upMatching = iorgamsService.upMatching(creditApplicationId);
				if("1".equals(upMatching.getStatus().toString())){
					//更新撮合结果
					BorrowMatching borrowMatching = new BorrowMatching();
					borrowMatching.setStatus("0");//失败
					borrowMatching.setCreditapplicationId(Long.parseLong(creditApplicationId.toString()));
					borrowMatchingDAO.updateByCaId(borrowMatching);
					OperateLogBusinessStruct operateLog = new OperateLogBusinessStruct();
					operateLog.setCreditapplicationId(creditApplicationId);
					operateLog.setFunctionCode("1000**");//修改
					operateLog.setResult("撤销撮合");
					operateLogService.insert(operateLog);
					creditApplication.setAuditStatus(Constants.STATE_34);
					creditApplication.setCreditapplicationId(Integer.parseInt(creditApplicationId.toString()));
					creditApplicationDAO.submitAuditing(creditApplication);
					logger.debug("撤销撤销成功:"+"融资保单号为："+creditApplicationId);
					message.setMsg("撤销成功");
					message.setSuccess(true);
				}else{
					logger.debug("撤销撤销失败:"+"融资保单号为："+creditApplicationId);
					message.setMsg("撤销失败");
					message.setSuccess(false);
				}
			}
		}else{
			logger.debug("没有可撤销的数据:"+"融资保单号为："+"null");
			message.setMsg("没有可撤销的数据");
			message.setSuccess(true);
		}
		return message;
	}
	

}
