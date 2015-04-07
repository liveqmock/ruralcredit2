package com.creditease.rc.dao;

import com.creditease.rc.domain.OverDueSummary;
import com.creditease.rc.framework.dao.IBaseDao;

import java.util.List;

public interface IOverDueSummaryDao extends IBaseDao {
    /**
     *   插入逾期统计信息到数据表
     * @param overDueSummaryList
     * @throws Exception
     */
    public  void mergeOverDueSummaryInfoToLocalTable(List<OverDueSummary> overDueSummaryList) throws Exception;

    /**
     *   通过信贷申请id查询逾期统计信息
     * @param overDueSummary
     * @return
     * @throws Exception
     */
    public  OverDueSummary getOverDueInfoByCreidtApplicationId(OverDueSummary overDueSummary) throws Exception;

    /**
     * 通过UUID查询逾期统计信息
     * @param sysUUID
     * @return
     * @throws Exception
     */
    public  OverDueSummary getOverDueInfoBySysUUID(String sysUUID) throws Exception;

    /*判断业务单是否逾期*/
    public Integer checkOverDueById(Long creditApplicationId);
}