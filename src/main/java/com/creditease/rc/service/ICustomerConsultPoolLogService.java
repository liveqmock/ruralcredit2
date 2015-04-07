package com.creditease.rc.service;

import com.creditease.rc.domain.CustomerConsultPoolLog;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.vo.CustomerConsultPoolLogVo;
import com.creditease.rc.vo.OperateLogVO;

import java.util.List;
import java.util.Map;

public interface ICustomerConsultPoolLogService {

    /*
    保存日志
     */
    public Long insert(CustomerConsultPoolLog log, Map<String, String> contentMap);


    /**
     * 新增日志
     *
     */
    // ----- 下面方法  用于咨询的日志
    public CustomerConsultPoolLog insertOpt(CustomerConsultPoolLog record, Map<String, String> contentMap);

    /**
     * 修改
     *
     */
    public int updateOpt(CustomerConsultPoolLog record);

    /**
     * 查询
     *
     */
    public List<CustomerConsultPoolLogVo> selectOpt(CustomerConsultPoolLogVo operateLogVO);

    /**
     * 查询 分页
     *
     */
    public Pagination selectPagination(CustomerConsultPoolLogVo operateLogVO, Pagination pagination);

    /**
     * 按照关联id查询
     *
     */
    public List<CustomerConsultPoolLogVo> selectById(Long connectionId);

    /**
     * 修改申请id
     *
     * @param connectionId  关联 id
     * @param applicationId 申请id
     */
    public int updateApplicationId(Long connectionId, Long applicationId);

    /**
     * 查询咨询日志
     *
     * @param CustomerConsultPoolLogVo
     * @param pagination
     */

    public Pagination queryCunsult(CustomerConsultPoolLogVo CustomerConsultPoolLogVo, Pagination pagination);
}
