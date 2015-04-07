package com.creditease.rc.dao;

import com.creditease.rc.domain.BusinessStatusChange;
import com.creditease.rc.domain.BusinessStatusChangeVo;
import com.creditease.rc.domain.LegalProceedings;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.vo.LegalProceedingsVo;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: zhangwei
 * Date: 2014-6-13
 * Time: 18:31
 */
public interface IBusinessStatusChangeDAO {
    Long insert(BusinessStatusChange record);

    Pagination selectByPagination(Pagination pagination, Map searchValue);

    Pagination selectPrepareCreditApplication(Pagination pagination, Map searchValue);

    BusinessStatusChange selectChangeById(Long changeId);

    Integer updateChangeHistoryFlag(BusinessStatusChange change);

    BusinessStatusChange selectLatestStatusByCreditApplicationId(Long creditApplicationId);

    List<BusinessStatusChange> viewChangeLogByTimer(Long creditApplicationId);

    Pagination selectByPaginationByNumber(Pagination pagination, Map searchValue);

    Pagination viewLegalProceedings(Pagination pagination, Map searchValue);

    Pagination viewLegalProceedingsDetail(Pagination pagination, LegalProceedings legalProceedings);

    Long saveLegalProceedings(LegalProceedings legalProceedings);
}
