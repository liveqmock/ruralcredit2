package com.creditease.rc.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.creditease.rc.dao.IFirstSystemDao;
import com.creditease.rc.domain.Message;
import com.creditease.rc.service.IFirstSystemService;
import com.creditease.rc.util.CommonUtil;

@Service
public class FirstSystemService implements IFirstSystemService {

	@Resource
	private IFirstSystemDao firstSystemDao;
	
	@Override
	public Message queryByUndone(String credentialsNumber) throws Exception {
		Message msg = new Message();
		//1返回Message 根据状态进行判断提示信息
		String auditStatus = firstSystemDao.queryAuditStatus(credentialsNumber);
		int count = firstSystemDao.queryCountByUndone(credentialsNumber);
		//撤销的可以借
		if(CommonUtil.isNotEmpty(auditStatus)){
			if(!"6".equals(auditStatus) && !"16".equals(auditStatus) && !"20".equals(auditStatus) && !"0".equals(auditStatus)){
				msg.setMsg(convertAuditStatus(auditStatus));
				msg.setSuccess(false);
				return msg;
			}
		}else{
			msg.setSuccess(true);
			return msg;
		}
		if(count==0){
			msg.setSuccess(true);
			return msg;
		}else{//小组还款完成但个人没完成--一期中错误数据
			msg.setMsg("还款中");
			msg.setSuccess(false);
			return msg;
		}
	}

	public String convertAuditStatus(String auditStatus){
		String status = null;
		if(CommonUtil.isNotEmpty(auditStatus)){
			if("0".equals(auditStatus)){
				status = "未提交 ";
			}else if("1".equals(auditStatus)){
				status = "审查中";
			}else if("2".equals(auditStatus)){
				status = "审查通过";
			}else if("3".equals(auditStatus)){
				status = "审查拒绝";
			}else if("4".equals(auditStatus)){
				status = "审批通过";
			}else if("5".equals(auditStatus)){
				status = "审批补充资料";
			}else if("6".equals(auditStatus)){
				status = "撤销";
			}else if("7".equals(auditStatus)){
				status = "撤回";
			}else if("8".equals(auditStatus)){
				status = "推迟放款";
			}else if("9".equals(auditStatus)){
				status = "已付款";
			}else if("10".equals(auditStatus)){
				status = "款项到位";
			}else if("11".equals(auditStatus)){
				status = "已放款登记";
			}else if("12".equals(auditStatus)){
				status = "放款失败-推迟";
			}else if("13".equals(auditStatus)){
				status = "放款确认";
			}else if("14".equals(auditStatus)){
				status = "放款确认退回";
			}else if("15".equals(auditStatus)){
				status = "还款中";
			}else if("16".equals(auditStatus)){
				status = "还款完成";
			}else if("17".equals(auditStatus)){
				status = "审批变更额度";
			}else if("18".equals(auditStatus)){
				status = "审批拒绝";
			}else if("19".equals(auditStatus)){
				status = "提前还款登记";
			}else if("20".equals(auditStatus)){
				status = "提前还款完成";
			}
		}
		return "一期"+status;
	}
}
