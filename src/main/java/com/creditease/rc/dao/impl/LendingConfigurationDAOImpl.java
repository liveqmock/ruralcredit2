package com.creditease.rc.dao.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.creditease.rc.dao.LendingConfigurationDAO;
import com.creditease.rc.domain.LendingConfiguration;
import com.creditease.rc.framework.dao.impl.BaseDao;
import com.creditease.rc.framework.pager.Pagination;
@Repository
public class LendingConfigurationDAOImpl extends BaseDao implements LendingConfigurationDAO {

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table RL_LENDING_CONFIGURATION
     *
     * @abatorgenerated
     */
    public LendingConfigurationDAOImpl() {
        super();
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table RL_LENDING_CONFIGURATION
     *
     * @abatorgenerated
     */
    public Long insert(LendingConfiguration record) {
        Object newKey = getSqlMapClientTemplate().insert("RL_LENDING_CONFIGURATION.abatorgenerated_insert", record);
        return (Long) newKey;
    }


	@Override
	public Pagination selectAll(
			Pagination pagination, Map<String, Object> map) {
		return queryForPaginatedList("RL_LENDING_CONFIGURATION.selectAll", "RL_LENDING_CONFIGURATION.selectCount", map, pagination.getStartResult(), pagination.getPageSize());
	}

	@Override
	public int removeLendingConfiguration(Long lendingConfigurationId) {
		return delete("RL_LENDING_CONFIGURATION.removeLendingConfiguration", lendingConfigurationId);
	}

	@Override
	public int updateLendingConfiguration(LendingConfiguration record) {
		return update("RL_LENDING_CONFIGURATION.updateLendingConfiguration", record);
	}

	@Override
	public int updateLendingConfigurationByareaDepartmentId(
			LendingConfiguration record) {
		return update("RL_LENDING_CONFIGURATION.updateLendingConfigurationByareaDepartmentId", record);
	}

	@Override
	public int selectIsExist(LendingConfiguration record) {
		return (Integer) queryUnique("RL_LENDING_CONFIGURATION.selectIsExist", record);
	}

}