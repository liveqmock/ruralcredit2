package com.creditease.rc.dao.impl;

import com.creditease.rc.dao.IOverDueSummaryDao;
import com.creditease.rc.domain.OverDueSummary;
import com.creditease.rc.framework.dao.IBaseDao;
import com.creditease.rc.framework.dao.impl.BaseDao;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
@Repository
public class OverDueSummaryDaoImpl  extends BaseDao implements IOverDueSummaryDao {
    @Resource
    private IBaseDao baseDao;
    /**
     *   插入逾期统计信息到数据表
     * @param overDueSummaryList
     * @throws Exception
     */
    @Override
    public void mergeOverDueSummaryInfoToLocalTable(List<OverDueSummary> overDueSummaryList) throws Exception {
        //To change body of implemented methods use File | Settings | File Templates.
        OverDueSummary overDueSummary = new OverDueSummary();
        baseDao.delete("overDueSummary.overDueSummary_truncate",overDueSummary);
        baseDao.batchInsert("overDueSummary.overDueSummary_insert", overDueSummaryList);
    }
    /**
     *   通过信贷申请id查询逾期统计信息
     * @param overDueSummary
     * @return
     * @throws Exception
     */
    @Override
    public OverDueSummary getOverDueInfoByCreidtApplicationId(OverDueSummary overDueSummary) throws Exception {
        return (OverDueSummary)baseDao.queryList("overDueSummary.getOverDueInfoByCreidtApplicationId",overDueSummary);  //To change body of implemented methods use File | Settings | File Templates.
    }
    /**
     * 通过UUID查询逾期统计信息
     * @param sysUUID
     * @return
     * @throws Exception
     */
    @Override
    public OverDueSummary getOverDueInfoBySysUUID(String sysUUID) throws Exception {
        return (OverDueSummary)baseDao.queryUnique("overDueSummary.getOverDueInfoBySysUUID",sysUUID);
    }

    @Override
    public Integer checkOverDueById(Long creditApplicationId) {
        return (Integer) baseDao.queryUnique("overDueSummary.checkOverDueById", creditApplicationId);
    }
}
