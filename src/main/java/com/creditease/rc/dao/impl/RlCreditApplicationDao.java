package com.creditease.rc.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.creditease.rc.dao.IRlCreditApplicationDao;
import com.creditease.rc.domain.CreditApplication;
import com.creditease.rc.framework.dao.impl.BaseDao;
import com.creditease.rc.vo.TBorrowerServiceAppVo;
/**
 * IRlCreditApplicationDao接口实现类
 * @author xubingzhao
 *
 */
@Repository
public class RlCreditApplicationDao extends BaseDao implements IRlCreditApplicationDao{

	@Override
	public boolean updateiRlCreditApplication(CreditApplication rlCreditApplication) {
		boolean result=false;
		int i=update("RLCREDITAPPLICATION.update",rlCreditApplication);
		if(i>0){
			result=true;
		}
		return result;
	}

	@Override
	public List<TBorrowerServiceAppVo> selectRlBorrowerServiceApp(CreditApplication rlCreditApplication) {
		List<TBorrowerServiceAppVo>list=(List<TBorrowerServiceAppVo>)queryList("RLCREDITAPPLICATION.selectRlBorrowerServiceApp",
				rlCreditApplication);
		return list;
	}

	@Override
	public CreditApplication selectRlCreditApplication(
			CreditApplication rlCreditApplication) {
		CreditApplication result=(CreditApplication) queryUnique("RLCREDITAPPLICATION.selectRlCreditApplication", rlCreditApplication);
		return result;
	}

	@Override
	public int updateLoanDate(CreditApplication rlCreditApplication) {
		return this.update("RLCREDITAPPLICATION.updateLoanDate", rlCreditApplication);
	}

	@Override
	public boolean updateiRlCreditApplicationByList(
			List<CreditApplication> creditApplicationList) {
			int count=this.batchUpdate("RLCREDITAPPLICATION.updateiRlCreditApplicationByList", creditApplicationList);
			if(0<count){
				return true;
			}
			return false;
	}

}
