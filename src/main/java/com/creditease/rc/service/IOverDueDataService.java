package com.creditease.rc.service;

import com.creditease.rc.domain.Message;
import com.creditease.rc.domain.OverDueData;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.vo.OverDueListVo;

import java.util.List;

public interface IOverDueDataService {

	boolean dynamicInsert(OverDueData overDueData);

	boolean dynamicUpdate(OverDueData overDueData);

	boolean saveOverDueDatas(List<OverDueData> overDueDatas);

	boolean deleteOverDueData();

    /**
     * 同步逾期及逾期跑批时使用
     * create by ygx
     * @return
     * @throws Exception
     */
    Message insertOverDueData() throws Exception;

    /**
     *create by ygx
     逾期列表查询
     根据用户角色查询逾期信息列表
     * @param queryObj
     * @param pagination
     * @return
     */
    //查询逾期信息
    Pagination queryOverdueGrid(Object queryObj, Pagination pagination);

    /**
     * 根据用户角色查询逾期信息列表供Excel导出使用
     * @param queryObj
     * @return
     */
    //查询逾期信息
    List<OverDueListVo> queryOverdueListForExport(Object queryObj);

    /*判断业务单是否逾期*/
    public Integer checkOverDueById(Long creditApplicationId);
}
