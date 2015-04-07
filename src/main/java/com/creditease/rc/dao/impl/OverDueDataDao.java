package com.creditease.rc.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.vo.OverDueListVo;
import org.springframework.stereotype.Repository;

import com.creditease.rc.dao.IOverDueDataDao;
import com.creditease.rc.domain.OverDueData;
import com.creditease.rc.framework.dao.IBaseDao;

@Repository
public class OverDueDataDao implements IOverDueDataDao {

	@Resource
	private IBaseDao baseDao;

	@Override
	public boolean dynamicInsert(OverDueData overDueData) {
		// TODO Auto-generated method stub
		baseDao.insert("OVERDUEDATA.dynamicInsert", overDueData);
		return true;
	}

	@Override
	public boolean dynamicUpdate(OverDueData overDueData) {
		// TODO Auto-generated method stub
		baseDao.update("OVERDUEDATA.dynamicUpdate", overDueData);
		return true;
	}

	@Override
	public boolean saveOverDueDatas(List<OverDueData> overDueDatas) {
		// TODO Auto-generated method stub
		baseDao.batchInsert("OVERDUEDATA.dynamicInsert", overDueDatas);
		return true;
	}

	@Override
	public boolean deleteOverDueData() {
		// TODO Auto-generated method stub
		baseDao.delete("OVERDUEDATA.deleteAll", 0l);
		return true;
	}

    /**
     *   查询逾期信息
     * @param queryObj
     * @param pagination
     * @return
     */
    @Override
    public Pagination queryOverdueGrid(Object queryObj, Pagination pagination) {
        return baseDao.queryForPaginatedList("OVERDUEDATA.queryOverdueGrid",
                "OVERDUEDATA.countQueryOverdueGrid", queryObj, pagination.getStartResult(),
                pagination.getPageSize());
    }

    /**
     *    查询逾期信息 供导出使用
     * @param queryObj
     * @return
     */
    @Override
    public List<OverDueListVo> queryOverdueListForExport(Object queryObj) {
        return (List<OverDueListVo>)baseDao.queryList("OVERDUEDATA.queryOverdueListForExport",queryObj);  //To change body of implemented methods use File | Settings | File Templates.
    }
}
