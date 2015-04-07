package com.creditease.rc.dao;

import java.util.List;
import com.creditease.rc.domain.Deposit;
import com.creditease.rc.framework.dao.IBaseDao;

/**
 * 存款 和应收现金
 * @author zhangman
 *
 */
public interface IDepositDao extends IBaseDao {
	/**
	 * 
	 * @param borrowerServiceAppId
	 * @return
	 */
	List<Deposit> selectByBorrowerServiceAppId(Long borrowerServiceAppId);

}
