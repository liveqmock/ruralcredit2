package com.creditease.rc.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.creditease.rc.dao.ILoanRegistDAO;
import com.creditease.rc.domain.LoanRegist;
import com.creditease.rc.framework.dao.impl.BaseDao;
/**
 * 放款登记详细
 * @author zhangman
 *
 */
@Repository
public class LoanRegistDAO extends BaseDao implements  ILoanRegistDAO {

	@Override
	public void addBatch(List<LoanRegist> loanRegistDetail) {
		// TODO Auto-generated method stub
		batchInsert("LOANREGIST.insert", loanRegistDetail);
	}

	@Override
	public List<LoanRegist> selectLoanRegistList(int groupLoanRegistId) {
		// TODO Auto-generated method stub
		return (List<LoanRegist>) queryList("LOANREGIST.listByPID", groupLoanRegistId);
	}
	@Override
	public List<LoanRegist> selectLoanRegists(int creditApplicationId) {
		return (List<LoanRegist>) queryList("LOANREGIST.selectLoanRegists", creditApplicationId);
	}
}
