package com.creditease.rc.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.creditease.rc.dao.IGroupLoanRegistDetailDAO;
import com.creditease.rc.domain.GroupLoanRegistDetail;
import com.creditease.rc.framework.dao.impl.BaseDao;
/**
 * 放款登记详细
 * @author zhangman
 *
 */
@Repository
public class GroupLoanRegistDetailDAO extends BaseDao implements IGroupLoanRegistDetailDAO {

	@Override
	public void addBatch(List<GroupLoanRegistDetail> groupLoanRegistDetailList) {
		// TODO Auto-generated method stub
		batchInsert("GROUPLOANREGISTDETAIL.insert", groupLoanRegistDetailList);
	}

}
