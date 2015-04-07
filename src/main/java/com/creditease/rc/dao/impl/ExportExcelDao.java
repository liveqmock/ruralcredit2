package com.creditease.rc.dao.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.creditease.rc.dao.IExportExcelDao;
import com.creditease.rc.framework.dao.IBaseDao;
import com.creditease.rc.framework.pager.Pagination;

@Repository
public class ExportExcelDao implements IExportExcelDao {
	@Resource
	private IBaseDao baseDao;
	@Override
	public Pagination getQMData(Map map,Pagination pagination) throws Exception {
		return baseDao.queryForPaginatedList("qmexcel.selectByPagination", "qmexcel.countByPage", map, pagination.getStartResult(), pagination.getPageSize());
	}
	@Override
	public Pagination getCMPData(Map map, Pagination pagination)
			throws Exception {
		return baseDao.queryForPaginatedList("qmexcel.selectCMPByPagination", "qmexcel.countCMPByPage", map, pagination.getStartResult(), pagination.getPageSize());
	}
	@Override
	public Pagination getBDData(Map map, Pagination pagination)
			throws Exception {
		return baseDao.queryForPaginatedList("qmexcel.selectBDByPagination", "qmexcel.countBDByPage", map, pagination.getStartResult(), pagination.getPageSize());
	}

}
