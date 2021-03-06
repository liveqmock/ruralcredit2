package com.creditease.rc.dao;

import java.util.List;

import com.creditease.rc.domain.EmployeeChange;

public interface EmployeeChangeDAO {
    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table RL_EMPLOYEE_CHANGE
     *
     * @abatorgenerated
     */
    Long insert(EmployeeChange record);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table RL_EMPLOYEE_CHANGE
     *
     * @abatorgenerated
     */
    int updateByPrimaryKey(EmployeeChange record);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table RL_EMPLOYEE_CHANGE
     *
     * @abatorgenerated
     */
    int updateByPrimaryKeySelective(EmployeeChange record);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table RL_EMPLOYEE_CHANGE
     *
     * @abatorgenerated
     */
    EmployeeChange selectByPrimaryKey(Long employeeChangeId);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table RL_EMPLOYEE_CHANGE
     *
     * @abatorgenerated
     */
    int deleteByPrimaryKey(Long employeeChangeId);
    
    /**
     * 根据员工id号，查询是否有历史变更是否存在
     * @param comEmpId
     * @return
     */
    public int selectHistoryCount(Integer comEmpId);
    
    /**
     * 根据员工id号查询历史变更数据
     * @param comEmpId
     * @return
     */
    public List<EmployeeChange> queryHistoryChange(Integer comEmpId);
}