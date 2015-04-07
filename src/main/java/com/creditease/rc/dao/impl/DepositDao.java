package com.creditease.rc.dao.impl;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.creditease.rc.dao.IDepositDao;
import com.creditease.rc.domain.Deposit;
import com.creditease.rc.framework.dao.impl.BaseDao;

/**
 *  存款 和应收现金 
 * @author zhangman
 *
 */
@Repository
public class DepositDao extends BaseDao implements IDepositDao {
	@Override
	public List<Deposit> selectByBorrowerServiceAppId(Long borrowerServiceAppId) {
		// TODO Auto-generated method stub
		return (List<Deposit>) queryList("RL_DEPOSIT.selectByBorrowerServiceAppId", borrowerServiceAppId);
	}
}
