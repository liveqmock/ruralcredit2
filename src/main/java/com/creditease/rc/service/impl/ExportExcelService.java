package com.creditease.rc.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.creditease.rc.dao.IExportExcelDao;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.service.IExportExcelService;

/**
 * 
 * cs
 * Title:ExportExcelService.java
 * Description: 宜农贷2.0系统开发
 * Copyright:Copyright (c) 2013
 * Company:普信恒业科技发展（北京）有限公司
 * 2013-6-5下午03:45:46
 * @author wyf
 * @version v2.0
 */
@Service
public class ExportExcelService implements IExportExcelService {

	@Resource
	private IExportExcelDao excelDao;
	
	@Override
	public Pagination getQMData(Map map,Pagination pagination) throws Exception {
		return excelDao.getQMData(map,pagination);
	}

	@Override
	public Pagination getCMPData(Map map, Pagination pagination)
			throws Exception {
		return excelDao.getCMPData(map,pagination);
	}

	@Override
	public Pagination getBDData(Map map, Pagination pagination)
			throws Exception {
		return excelDao.getBDData(map, pagination);
	}

}
