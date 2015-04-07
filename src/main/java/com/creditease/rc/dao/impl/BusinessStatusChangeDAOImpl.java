package com.creditease.rc.dao.impl;

import com.creditease.rc.dao.IBusinessStatusChangeDAO;
import com.creditease.rc.domain.BusinessStatusChange;
import com.creditease.rc.domain.BusinessStatusChangeVo;
import com.creditease.rc.domain.LegalProceedings;
import com.creditease.rc.framework.dao.IBaseDao;
import com.creditease.rc.framework.dao.impl.BaseDao;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.vo.LegalProceedingsVo;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: zhangwei
 * Date: 2014-6-13
 * Time: 18:35
 */
@Repository
public class BusinessStatusChangeDAOImpl extends BaseDao implements IBusinessStatusChangeDAO {

    @Resource
    private IBaseDao baseDao;

    @Override
    public Long insert(BusinessStatusChange record) {
        Integer key = updateCreditApplicationStatus(record);
        Object newKey = baseDao.insertObject("BUSINESSSTATUSCHANGE.insertChange", record);
        if (key.intValue() > 0 && ((Long) newKey).longValue() > 0) {
            return 1l;
        }
        return 0l;
    }

    @Override
    public Pagination selectByPagination(Pagination pagination, Map searchValue) {
        return baseDao.queryForPaginatedList("BUSINESSSTATUSCHANGE.selectChangeLog", "BUSINESSSTATUSCHANGE.countBySelectChangeLog",
                searchValue, pagination.getStartResult(), pagination.getPageSize());
    }

    @Override
    public Pagination selectPrepareCreditApplication(Pagination pagination, Map searchValue) {
        return baseDao.queryForPaginatedList("BUSINESSSTATUSCHANGE.selectPrepareCreditApplication", "BUSINESSSTATUSCHANGE.countByselectPrepareCreditApplication",
                searchValue, pagination.getStartResult(), pagination.getPageSize());
    }

    @Override
    public BusinessStatusChange selectChangeById(Long changeId) {
        return (BusinessStatusChange) baseDao.queryUnique("BUSINESSSTATUSCHANGE.selectChangeLogById", changeId);
    }

    @Override
    public Integer updateChangeHistoryFlag(BusinessStatusChange change) {
        return baseDao.update("BUSINESSSTATUSCHANGE.updateChangeHistoryFlag", change);
    }

    @Override
    public BusinessStatusChange selectLatestStatusByCreditApplicationId(Long creditApplicationId) {
        return (BusinessStatusChange)baseDao.queryUnique("BUSINESSSTATUSCHANGE.seelctLatestStatusByCreditApplicationId",creditApplicationId);
    }

    @Override
    public List<BusinessStatusChange> viewChangeLogByTimer(Long creditApplicationId) {
        return (List<BusinessStatusChange>)baseDao.queryList("BUSINESSSTATUSCHANGE.viewChangeLogByTimer",creditApplicationId);
    }

    @Override
    public Pagination selectByPaginationByNumber(Pagination pagination, Map searchValue) {
        return baseDao.queryForPaginatedList("BUSINESSSTATUSCHANGE.selectChangeLogByNumber", "BUSINESSSTATUSCHANGE.countBySelectChangeLogByNumber",
                searchValue, pagination.getStartResult(), pagination.getPageSize());
    }

    @Override
    public Pagination viewLegalProceedings(Pagination pagination, Map searchValue) {
        return baseDao.queryForPaginatedList("BUSINESSSTATUSCHANGE.viewLegalProceedings", "BUSINESSSTATUSCHANGE.countByViewLegalProceedings",
                searchValue, pagination.getStartResult(), pagination.getPageSize());
    }

    @Override
    public Pagination viewLegalProceedingsDetail(Pagination pagination, LegalProceedings legalProceedings) {
        return baseDao.queryForPaginatedList("BUSINESSSTATUSCHANGE.viewLegalProceedingsDetail", "BUSINESSSTATUSCHANGE.countByViewLegalProceedingsDetail",
                legalProceedings, pagination.getStartResult(), pagination.getPageSize());
    }

    @Override
    public Long saveLegalProceedings(LegalProceedings legalProceedings) {
        Object newKey = baseDao.insertObject("BUSINESSSTATUSCHANGE.insertLegalProceedings", legalProceedings);
        return (Long) newKey;
    }

    private Integer updateCreditApplicationStatus(BusinessStatusChange record) {
        //更新信贷申请状态
        Map<String, String> map = new HashMap<String, String>();
        map.put("auditStatus", record.getAfterStatus());
        map.put("creditApplicationId", record.getCreditapplicationId().toString());
        return baseDao.update("BUSINESSSTATUSCHANGE.updateCreditApplicationStatus", map);
    }
}
