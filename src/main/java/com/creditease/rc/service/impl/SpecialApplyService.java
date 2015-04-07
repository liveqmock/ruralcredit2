package com.creditease.rc.service.impl;

import com.creditease.core.security.SpringSecurityUtils;
import com.creditease.core.security.User;
import com.creditease.rc.dao.ISpecialApplyDao;
import com.creditease.rc.domain.CreditApplication;
import com.creditease.rc.domain.SpecialApply;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.service.ICreditApplicationService;
import com.creditease.rc.service.ISpecialApplyService;
import com.creditease.rc.vo.SpecialApplyVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Service
public class SpecialApplyService implements ISpecialApplyService {

	@Resource
	private ISpecialApplyDao specialApplyDao;  
	@Resource
	private ICreditApplicationService  creditApplicationService;
	
	/**
	 * 添加特殊申请信息
	 * 
	 * @author luohongjie
	 * 
	 */
	@Override
	public boolean insert(SpecialApply specialapply) {
		
		CreditApplication application = creditApplicationService.selectById(specialapply.getCreditapplicationId().intValue());
		//String num  = application.getGroupNumber();//编号
		String auditState = application.getAuditStatus();//业务状态
		
		// TODO Auto-generated method stub
		User user = SpringSecurityUtils.getCurrentUser();
		//申请人ID
		specialapply.setProposerId(Long.parseLong(user.getUserId()));
		System.out.println(user.getUserId());
		//申请人姓名
		specialapply.setProposerName(user.getName_zh());
		//申请时间
		specialapply.setApplyTime(new Date());
		//业务状态
		//specialapply.setBusinessState(auditState);
		//申请时记录业务状态
		specialapply.setSpecialApplyState(auditState);
		
		return specialApplyDao.insert(specialapply);
	}

	/**
	 * 特殊申请审批信息
	 * 
	 * @author luohongjie
	 * 
	 */
	@Override
	public boolean dynamicUpdate(SpecialApplyVo specialapplyVo) {
		// TODO Auto-generated method stub
		User user = SpringSecurityUtils.getCurrentUser();
		//审批人ID
		specialapplyVo.setAuditorId(Long.parseLong(user.getUserId()));
		//审批人姓名
		specialapplyVo.setAuditorName(user.getName_zh());
		//审批人时间
		specialapplyVo.setAuditTime(new Date());
		int rows=specialApplyDao.dynamicUpdate(specialapplyVo);
		if (rows > 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 特殊申请信息列表查询
	 * 
	 * @author luohongjie
	 * 
	 */
	@Override
	public Pagination querySpecialApplyList(Pagination pagination,
			Map<String, String> pramMap) {
		pagination = specialApplyDao.queryUrgeList(pagination, pramMap);
		return pagination;
	}
	
	/**
	 * 根据ID查询特殊申请信息
	 * 
	 * @author luohongjie
	 * 
	 */
	@Override
	public List<SpecialApplyVo> queryApplyById(Long specialApplyId){
		return specialApplyDao.queryApplyById(specialApplyId);
	}

	/**
	 * 根据业务单号查询   该笔单子的 特殊申请状态
	 */
	@Override
	public List<SpecialApplyVo> selectSpecialState(Long creditapplicationId){
		return (List<SpecialApplyVo>) specialApplyDao.selectSpecialState(creditapplicationId);
	}

	@Override
	public SpecialApplyVo queryApplyVoByPrimaryKey(Long specialApplyId) {
		// TODO Auto-generated method stub
		return specialApplyDao.queryApplyVoByPrimaryKey(specialApplyId);
	}

	@Override
	public Pagination searchApplyByCreditId(Pagination pagination, Long id){
		return specialApplyDao.searchApplyByCreditId(pagination, id);
	}
}