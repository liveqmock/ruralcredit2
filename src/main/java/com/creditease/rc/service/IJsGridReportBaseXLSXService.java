package com.creditease.rc.service;

import com.creditease.rc.report.excel.TableData;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: admin
 * Date: 14-11-27
 * Time: 下午2:07
 * To change this template use File | Settings | File Templates.
 */
public interface IJsGridReportBaseXLSXService {
    /**
     *  导出Excel
     * @param title    标题
     * @param creator   创建者
     * @param tableDataLst 需导出数据
     * @throws Exception
     */
    public void exportToExcel(String title, String creator, List<TableData> tableDataLst) throws Exception ;

    /**
     *
     * @param title
     * @param creator
     * @param tableData
     * @throws Exception
     */
    public void exportToExcel(String title, String creator, TableData tableData) throws Exception;

    /**
     *
     * @param request
     * @param response
     * @throws Exception
     */
    public void jsGridReportBaseXLSXService(HttpServletRequest request, HttpServletResponse response) throws Exception ;
    }
