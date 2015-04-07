package com.creditease.rc.service.impl;

import com.creditease.rc.common.Constants;
import com.creditease.rc.dao.ICreditApplicationDAO;
import com.creditease.rc.dao.IRlApplyAuditTableDao;
import com.creditease.rc.dao.IRlAuditDetailDao;
import com.creditease.rc.dao.IRlCreditApplicationDao;
import com.creditease.rc.domain.CreditApplication;
import com.creditease.rc.domain.RlApplyAuditTable;
import com.creditease.rc.domain.RlAuditDetail;
import com.creditease.rc.service.IOperateLogService;
import com.creditease.rc.service.IRlCreditApplicationService;
import com.creditease.rc.vo.OperateLogBusinessStruct;
import com.creditease.rc.vo.TBorrowerServiceAppVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * IApprovalService接口的实现类
 * @author xubingzhao
 *
 */
@Service
public class RlCreditApplicationService implements IRlCreditApplicationService{
	@Resource
	private IRlCreditApplicationDao iRlCreditApplicationDao;
	@Resource
	private IRlApplyAuditTableDao rlApplyAuditTableDao;
	@Resource
	private IRlAuditDetailDao iRlAuditDetailDao;
	@Resource
	private IOperateLogService operateLogService;
    @Resource
    private ICreditApplicationDAO creditApplicationDAO;

	@Override
	public List<TBorrowerServiceAppVo> selectRlBorrowerServiceApp(CreditApplication rlCreditApplication) {
		List<TBorrowerServiceAppVo> list=iRlCreditApplicationDao.selectRlBorrowerServiceApp(rlCreditApplication);
		return list;
		
	}

	@Override
	public CreditApplication selectRlCreditApplication(
			CreditApplication rlCreditApplication) {
		CreditApplication result=iRlCreditApplicationDao.selectRlCreditApplication(rlCreditApplication);
		return result;
	}
	
	@Override
	public int updateLoanDate(CreditApplication rlCreditApplication){
		return iRlCreditApplicationDao.updateLoanDate(rlCreditApplication);
	}
	/**
	 * 审批通过
	 * @param list  
	 * @param rlApplyAuditTable  
	 * @param rlCreditApplication  
	 * @return result
	 */
	@Override
	public boolean updateAppraval(List<RlAuditDetail>list,
			RlApplyAuditTable rlApplyAuditTable,
			CreditApplication rlCreditApplication){
		//业务逻辑  先查当前登录人是否做过审批，如果做过，则不能再做审批。没做就做审批，插入一条记录，再查一次，看已经有几条审批记录，有2条则通过审批。
		boolean result=false;
		List<RlApplyAuditTable> rlList=rlApplyAuditTableDao.selectAuditRecord(rlApplyAuditTable);
		if(1>rlList.size()){
			rlApplyAuditTableDao.insert(rlApplyAuditTable);
			if(list!=null){
				for(RlAuditDetail r:list){
					r.setApplyaudittableId(rlApplyAuditTable.getApplyaudittableId());
				}
				iRlAuditDetailDao.insert(list);
			}else{
				List<RlAuditDetail> rlAuditDetailList=rlApplyAuditTableDao.selectById(rlApplyAuditTable);
				for(RlAuditDetail r:rlAuditDetailList){
					r.setApplyaudittableId(rlApplyAuditTable.getApplyaudittableId());
				}
				iRlAuditDetailDao.insert(rlAuditDetailList);
			}
			//判断是否有已经审批的两条数据
			RlApplyAuditTable r=new RlApplyAuditTable();
			r.setCreditapplicationId(rlApplyAuditTable.getCreditapplicationId());
			r.setExamResult(rlApplyAuditTable.getExamResult());
			List<RlApplyAuditTable> rlList2=rlApplyAuditTableDao.selectAuditRecord(r);
			OperateLogBusinessStruct examOperateLogBusinessStruct=new OperateLogBusinessStruct();
			examOperateLogBusinessStruct.setAuditConfirmAmount(rlApplyAuditTable.getExamAmount().toString());
			examOperateLogBusinessStruct.setCreditapplicationId(rlApplyAuditTable.getCreditapplicationId().longValue());
			StringBuffer sbf=new StringBuffer("信贷申请单号=").append(examOperateLogBusinessStruct.getCreditapplicationId());
			examOperateLogBusinessStruct.setFunctionCode("200010");//由于权限没加，先加上这段代码以防正常测试报错
			CreditApplication creditapplication =new CreditApplication();
			creditapplication.setCreditapplicationId(rlApplyAuditTable.getCreditapplicationId());
			if(Constants.ROLE_RISK_MGR.equals(rlCreditApplication.getRole())){
				examOperateLogBusinessStruct.setFunctionCode("200010");
				sbf.append(";风险经理同意");
			}else if(Constants.ROLE_LOAN_MGR.equals(rlCreditApplication.getRole())){
				examOperateLogBusinessStruct.setFunctionCode("200020");
				sbf.append(";业务经理同意");
			}else if(Constants.ROLE_REGION_RISK_MGR.equals(rlCreditApplication.getRole())){
                /* 日志模块代码：2000101 */
				examOperateLogBusinessStruct.setFunctionCode("2000101");
				sbf.append(";区域风险经理同意");
			}else if(Constants.ROLE_REGION_MGR.equals(rlCreditApplication.getRole())){
                /* 日志模块代码：2000202 */
				examOperateLogBusinessStruct.setFunctionCode("2000201");
				sbf.append(";区域经理同意");
			}else if(Constants.ROLE_PARTICIPATE.equals(rlCreditApplication.getRole())){
                /* 日志模块代码：2000301 */
				examOperateLogBusinessStruct.setFunctionCode("2000301");
				sbf.append(";城市经理同意");
			}
			examOperateLogBusinessStruct.setFunctionBussiness(sbf.toString());
			examOperateLogBusinessStruct.setResult("审批通过");
			operateLogService.insert(examOperateLogBusinessStruct);

			/*变更（2014-12-08）状态变化：待审批-审批中-待城市审批-城市审批中-审批通过*/
			CreditApplication application = new CreditApplication();
			application.setCreditapplicationId(rlCreditApplication.getCreditapplicationId());
			application = creditApplicationDAO.selectCreditApplicationById(application);
			if (!application.getAuditStatus().equals(Constants.STATE_32)) {
				/*
				变更（2014-12-08）根据审批结果改变信贷申请单状态
				1  <=>  则更新为审批中
				2  <=>  如果状态为审批中，则更新为待城市审批，如果状态为待城市审批，则更新为城市审批中，
				*/
				List<Integer> retList = creditApplicationDAO.getTop2ApproveInfo(rlApplyAuditTable.getCreditapplicationId());
				if (retList.get(0).intValue() == 1) {
					creditapplication.setAuditStatus(Constants.STATE_24);
				} else if (retList.get(0).intValue() == 2) {
					if (application.getAuditStatus().equals(Constants.STATE_31)) {
						creditapplication.setAuditStatus(Constants.STATE_32);
					} else {
						creditapplication.setAuditStatus(Constants.STATE_31);
					}
				}
				result = iRlCreditApplicationDao.updateiRlCreditApplication(creditapplication);
			} else {
				result = true;
			}

            //有的话就修改记录变成审批通过
            /*变更（2014-07-23）：动态判定参审人数 */
            Integer pCount = creditApplicationDAO.getParticateApproveCount(rlApplyAuditTable.getCreditapplicationId());
            if(pCount + 2 == rlList2.size()){
				//审批通过金额是两个审批记录中金额的最小值
				/*Double amount1=rlList2.get(0).getExamAmount();
				Double amount2=rlList2.get(1).getExamAmount();
				if(amount1>=amount2){
					rlCreditApplication.setAmount(amount2);
				}else{
					rlCreditApplication.setAmount(amount1);
				}*/
                Integer finalAmount = rlApplyAuditTableDao.getMinExamineAmount(rlApplyAuditTable.getCreditapplicationId());
                if (finalAmount != null) {
                    rlCreditApplication.setAmount(finalAmount.doubleValue());
                }
                OperateLogBusinessStruct aduitOperateLogBusinessStruct=new OperateLogBusinessStruct();
				aduitOperateLogBusinessStruct.setCreditapplicationId(rlApplyAuditTable.getApplyaudittableId().longValue());
				aduitOperateLogBusinessStruct.setAuditConfirmAmount(rlCreditApplication.getAmount().toString());
				aduitOperateLogBusinessStruct.setFunctionCode("200030");
				StringBuffer business=new StringBuffer();
				business.append("信贷申请单号=").append(aduitOperateLogBusinessStruct.getCreditapplicationId()).append(" 审批通过");
				aduitOperateLogBusinessStruct.setFunctionBussiness(business.toString());
				operateLogService.insert(aduitOperateLogBusinessStruct);
				result=iRlCreditApplicationDao.updateiRlCreditApplication(rlCreditApplication);
			}
//			修改日期
//			if("1".equals(rlApplyAuditTable.getAuditType())){
//				iRlCreditApplicationDao.updateLoanDate(rlCreditApplication);
//			}
		}
		return result;
	}
	/**
	 * 审批拒绝
	 * @param list  
	 * @param rlApplyAuditTable  
	 * @param rlCreditApplication  
	 * @return result
	 */
	@Override
	public boolean updateAppraval2Refuse(List<RlAuditDetail>list,
			RlApplyAuditTable rlApplyAuditTable,
			CreditApplication rlCreditApplication){
		//先查当前登录人有没有做过审批，有的话则不能做审批拒绝，没有则做审批拒绝。审批拒绝业务逻辑是一个人拒绝，则单子就置为审批拒绝状态，可以再做提交申请
		boolean result=false;
		List<RlApplyAuditTable> rlList=rlApplyAuditTableDao.selectAuditRecord(rlApplyAuditTable);
		if(1>rlList.size()){
			rlApplyAuditTableDao.insert(rlApplyAuditTable);
			if(list!=null){
				for(RlAuditDetail r1:list){
					r1.setApplyaudittableId(rlApplyAuditTable.getApplyaudittableId());
				}
				iRlAuditDetailDao.insert(list);
			}else{
				List<RlAuditDetail> rlAuditDetailList=rlApplyAuditTableDao.selectById(rlApplyAuditTable);
				for(RlAuditDetail r1:rlAuditDetailList){
					r1.setApplyaudittableId(rlApplyAuditTable.getApplyaudittableId());
				}
				iRlAuditDetailDao.insert(rlAuditDetailList);
			}
			RlApplyAuditTable r=new RlApplyAuditTable();
			r.setCreditapplicationId(r.getCreditapplicationId());
			r.setHistoryFlag("T");
			rlApplyAuditTableDao.updateRlApplyAuditTable(r);
			//有一个人拒绝就拒绝
			result=iRlCreditApplicationDao.updateiRlCreditApplication(rlCreditApplication);
			OperateLogBusinessStruct operateLogBusinessStruct=new OperateLogBusinessStruct();
			operateLogBusinessStruct.setCreditapplicationId(rlApplyAuditTable.getCreditapplicationId().longValue());
			StringBuffer sbf=new StringBuffer("信贷申请单号=").append(operateLogBusinessStruct.getCreditapplicationId());
			operateLogBusinessStruct.setFunctionCode("200011");//由于权限没加，先加上这段代码以防正常测试报错
			if(Constants.ROLE_RISK_MGR.equals(rlCreditApplication.getRole())){
				operateLogBusinessStruct.setFunctionCode("200011");
				sbf.append(";风险经理审批拒绝");
			}else if(Constants.ROLE_LOAN_MGR.equals(rlCreditApplication.getRole())){
				operateLogBusinessStruct.setFunctionCode("200021");
				sbf.append(";业务经理审批拒绝");
			}else if(Constants.ROLE_LOAN_MGR.equals(rlCreditApplication.getRole())){
				operateLogBusinessStruct.setFunctionCode("2000111");
				sbf.append(";区域风险经理审批拒绝");
			}else if(Constants.ROLE_LOAN_MGR.equals(rlCreditApplication.getRole())){
				operateLogBusinessStruct.setFunctionCode("2000211");
				sbf.append(";区域经理审批拒绝");
			}else if(Constants.ROLE_PARTICIPATE.equals(rlCreditApplication.getRole())){
				operateLogBusinessStruct.setFunctionCode("2000311");
				sbf.append(";城市经理拒绝");
			}
			operateLogBusinessStruct.setFunctionBussiness(sbf.toString());
			operateLogBusinessStruct.setResult("审批拒绝");
			operateLogBusinessStruct.setAuditConfirmAmount(rlApplyAuditTable.getExamAmount().toString());
			operateLogService.insert(operateLogBusinessStruct);
//			修改日期
//			if("1".equals(rlApplyAuditTable.getAuditType())){
//				iRlCreditApplicationDao.updateLoanDate(rlCreditApplication);
//			}
            /*更新区域经理/区域风险经理参审状态为历史数据，即再次提交申请时，需要重新参审。*/
//            creditApplicationDAO.updateCityParticipate(rlApplyAuditTable.getCreditapplicationId(),"0");
//            creditApplicationDAO.updateCityParticipatePerman(rlApplyAuditTable.getCreditapplicationId(),"T");
		}
		return result;
	}

	@Override
	public boolean updateiRlCreditApplicationByList(
			List<CreditApplication> creditApplicationList) {
		boolean result=iRlCreditApplicationDao.updateiRlCreditApplicationByList(creditApplicationList);
		return result;
	}
}
