package com.creditease.rc.dao.impl;

import com.creditease.rc.dao.ICustomerConsultPoolLogDao;
import com.creditease.rc.domain.CustomerConsultPoolLog;
import com.creditease.rc.framework.dao.IBaseDao;
import com.creditease.rc.framework.dao.impl.BaseDao;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.vo.CustomerConsultPoolLogVo;
import com.creditease.rc.vo.OperateLogVO;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

@Repository
public class CustomerConsultPoolLogDAO extends BaseDao implements ICustomerConsultPoolLogDao {

    @Resource
    private IBaseDao baseDao;

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table CUSTOMERCONSULTPOOLLOG
     *
     * @abatorgenerated
     */
    public CustomerConsultPoolLogDAO() {
        super();
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table CUSTOMERCONSULTPOOLLOG
     *
     * @abatorgenerated
     */
    @Override
    public void insert(CustomerConsultPoolLog record) {
        baseDao.insert("CUSTOMERCONSULTPOOLLOG.abatorgenerated_insert", record);
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table CUSTOMERCONSULTPOOLLOG
     *
     * @abatorgenerated
     */
    @Override
    public int updateByPrimaryKey(CustomerConsultPoolLog record) {
        int rows = this.baseDao.update("CUSTOMERCONSULTPOOLLOG.abatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table CUSTOMERCONSULTPOOLLOG
     *
     * @abatorgenerated
     */
    @Override
    public int updateByPrimaryKeySelective(CustomerConsultPoolLog record) {
        int rows = this.baseDao.update("CUSTOMERCONSULTPOOLLOG.abatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table CUSTOMERCONSULTPOOLLOG
     *
     * @abatorgenerated
     */
    @Override
    public CustomerConsultPoolLog selectByPrimaryKey(Long optLogId) {
        CustomerConsultPoolLog key = new CustomerConsultPoolLog();
        key.setOptLogId(optLogId);
        CustomerConsultPoolLog record = (CustomerConsultPoolLog) this.baseDao.queryUnique("CUSTOMERCONSULTPOOLLOG.abatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table CUSTOMERCONSULTPOOLLOG
     *
     * @abatorgenerated
     */
    @Override
    public int deleteByPrimaryKey(Long optLogId) {
        CustomerConsultPoolLog key = new CustomerConsultPoolLog();
        key.setOptLogId(optLogId);
        int rows = this.baseDao.delete("CUSTOMERCONSULTPOOLLOG.abatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    @Override
    public Pagination selectOperateLogByPagination(OperateLogVO operateLogVO, Pagination pagination) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Long insertOpt(CustomerConsultPoolLog record) {
        return (Long) baseDao.insertObject("CUSTOMERCONSULTPOOLLOG.abatorgenerated_insert", record);
    }

    @Override
    public List<CustomerConsultPoolLogVo> selectOpt(CustomerConsultPoolLogVo operateLogVO) {
        return (List<CustomerConsultPoolLogVo>) baseDao.queryList("CUSTOMERCONSULTPOOLLOG.select_opt", operateLogVO);
    }

    @Override
    public int updateOpt(CustomerConsultPoolLog record) {
        return baseDao.update("CUSTOMERCONSULTPOOLLOG.update_cunsult_dymic", record);
    }

    @Override
    public Pagination selectPagination(Map<String, Object> map, Pagination pagination) {
        return baseDao.queryForPaginatedList("CUSTOMERCONSULTPOOLLOG.select_map", "CUSTOMERCONSULTPOOLLOG.count_map", map, pagination.getStartResult(), pagination.getPageSize());
    }

    @Override
    public Pagination queryCunsult(CustomerConsultPoolLogVo cunsultLogVo, Pagination pagination) {
        return baseDao.queryForPaginatedList("CUSTOMERCONSULTPOOLLOG.select_opt", "CUSTOMERCONSULTPOOLLOG.count_opt", cunsultLogVo, pagination.getStartResult(), pagination.getPageSize());
    }
}
