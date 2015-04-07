package com.creditease.rc.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.creditease.rc.common.Constants;
import com.creditease.rc.dao.IRefuseReasonDAO;
import com.creditease.rc.domain.CreditApplication;
import com.creditease.rc.domain.Message;
import com.creditease.rc.domain.RefuseReason;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.service.ICreditApplicationService;
import com.creditease.rc.service.IFinanceMoneyService;
import com.creditease.rc.service.IOperateLogService;
import com.creditease.rc.service.IRefuseReasonService;
import com.creditease.rc.util.DicUtil;
import com.creditease.rc.vo.OperateLogBusinessStruct;

@Service
public class RefuseReasonService implements IRefuseReasonService {

	@Resource
	private IRefuseReasonDAO refuseReasonDAO;
	@Resource
	private ICreditApplicationService creditApplicationService;
	@Resource
	private IFinanceMoneyService financeMoneyService;
	@Resource
	private IOperateLogService operateLogService;
	
	@Override
	public Message insert(RefuseReason refuseReason) {
		Message message = new Message();
		Long id = refuseReasonDAO.insert(refuseReason);
		if(id != null){
			message.setSuccess(true);
		}else{
			message.setMsg("添加操作失败");
		}
		return message;
	}

	@Override
	public Message updateRefuse(Long creditapplicationId,String section,String refuseReasons,String auditStatus) {
		Message message	 = new Message(); 
		String[] strings = refuseReasons.split(",");
		boolean flag = true;
		CreditApplication creditApplication = creditApplicationService.selectById(creditapplicationId.intValue());
//		可以据贷的状态
        /*2014-07-24 新增：可以进行拒贷操作的状态：待城市审批、城市审批中。*/
		 /*2014-08-19 新增：可以进行拒贷操作的状态：等待上传合同。*/
		if((Constants.STATE_0.equals(creditApplication.getAuditStatus())
			||Constants.STATE_1.equals(creditApplication.getAuditStatus())
			||Constants.STATE_24.equals(creditApplication.getAuditStatus())
			||Constants.STATE_4.equals(creditApplication.getAuditStatus())
			||Constants.STATE_21.equals(creditApplication.getAuditStatus())
            ||Constants.STATE_31.equals(creditApplication.getAuditStatus())
            ||Constants.STATE_32.equals(creditApplication.getAuditStatus())
            ||Constants.STATE_33.equals(creditApplication.getAuditStatus())
			)
			||((Constants.STATE_11.equals(creditApplication.getAuditStatus()))&&
				("1".equals(creditApplication.getBusinessType()))
			  )
			){
//			额度确认的状态 做撤销预约操作 (对公的)
				if(Constants.STATE_21.equals(creditApplication.getAuditStatus()) &&
					("0".equals(creditApplication.getBusinessType()))){
					message=financeMoneyService.paymentUndo(Integer.valueOf(creditapplicationId.toString()));
					flag = message.isSuccess();
				}
				if(flag){

					List<RefuseReason> refuseReasonList = new ArrayList<RefuseReason>();
					for (String string : strings) {
						RefuseReason refuseReason = new RefuseReason();
						refuseReason.setCodeKey(string);
						refuseReason.setCodeSection(section);
						refuseReason.setCreditapplicationId(creditapplicationId);
						refuseReasonList.add(refuseReason);
					}

					boolean b = false;
					try {
                        /*2014-07-25 在审批拒绝-新增拒贷原因时，此时拒贷操作不改变信贷申请状态，不保存操作日志，AR即表示这一特殊情况.*/
                        if ("AR".equals(auditStatus)) {
                            message.setSuccess(true);
                            return message;
                        } else {
                            b = creditApplicationService.updateRefuse(creditapplicationId.intValue(), auditStatus);
                        }
                    } catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(b){
						refuseReasonDAO.batchInsert(refuseReasonList);
						message.setSuccess(true);
					}

					OperateLogBusinessStruct operateLogBusinessStruct = new OperateLogBusinessStruct();
					operateLogBusinessStruct.setCreditapplicationId( creditapplicationId);
					operateLogBusinessStruct.setResult(DicUtil.convertCodeKeyToCodeValue("auditStatus",auditStatus));
					operateLogBusinessStruct.setFunctionCode("100060");
					operateLogService.insert(operateLogBusinessStruct);
				}
		}else{
			message.setMsg("该笔业务的状态不允许操作，当前状态为："+DicUtil.convertCodeKeyToCodeValue("auditStatus",creditApplication.getAuditStatus()));
		}
		return message;
	}

	/**
	 * @author hongjieluo
	 * 客户放弃列表查询
	 * @param customerGiveUpList
	 * 
	 */
	@Override
	public Pagination customerGiveUpList(Pagination pagination,
			Map<String, String> pramMap) {
		pagination = refuseReasonDAO.customerGiveUpList(pagination, pramMap);
		return pagination;
	}
	/**
	 * @author hongjieluo
	 * 拒贷列表查询
	 * @param 
	 */
	@Override
	public Pagination deniedLoansList(Pagination pagination,
			Map<String, String> pramMap) {
		pagination = refuseReasonDAO.deniedLoansList(pagination, pramMap);
		return pagination;
	}

    @Override
    public List<RefuseReason> selectRefuseReasonById(Long creditApplicationId) {
        return refuseReasonDAO.selectRefuseReasonById(creditApplicationId);
    }

}
