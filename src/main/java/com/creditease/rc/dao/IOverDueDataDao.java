package com.creditease.rc.dao;

import java.util.List;

import com.creditease.rc.domain.OverDueData;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.vo.OverDueListVo;

public interface IOverDueDataDao {

	boolean dynamicInsert(OverDueData overDueData);

	boolean dynamicUpdate(OverDueData overDueData);

	boolean saveOverDueDatas(List<OverDueData> overDueDatas);

	boolean deleteOverDueData();
    //查询逾期信息

    /**
     *
     查询逾期信息
     * @param queryObj
     * @param pagination
     * @return
     */
    Pagination queryOverdueGrid(Object queryObj, Pagination pagination);
    //查询逾期信息

    /**
     *   查询逾期信息 供导出使用
     * @param queryObj
     * @return
     */
    List<OverDueListVo> queryOverdueListForExport(Object queryObj);
}
