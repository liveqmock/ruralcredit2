package com.creditease.rc.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.creditease.rc.dao.IReturnPlanDao;
import com.creditease.rc.framework.dao.IBaseDao;
import com.creditease.rc.framework.dao.impl.BaseDao;
import com.creditease.rc.util.CommonUtil;

/**
 * 
 * @author haoqiang
 * 
 */
@Repository
public class ReturnPlanDao extends BaseDao implements IReturnPlanDao {

	@Resource
	private IBaseDao baseDao;

	@Override
	public Map<String, String> queryProductMap() {
		// TODO Auto-generated method stub
		Map<String, String> map = new HashMap<String, String>();
		List<Map<String, String>> list = (List<Map<String, String>>) baseDao.queryList("RETURNPLAN.queryProductMap");
		if (CommonUtil.isNotEmpty(list)) {
			for (Map<String, String> m : list) {
				String PRODUCTID = "";
				String PRODUCTNAME = "";
				if (null != m.get("PRODUCTID")) {
					PRODUCTID = m.get("PRODUCTID");
				}
				if (null != m.get("PRODUCTNAME")) {
					PRODUCTNAME = m.get("PRODUCTNAME");
				}
				map.put(PRODUCTID, PRODUCTNAME);
			}
		}

		return map;
	}

	@Override
	public List<Long> queryHasNoReturnPlan() {
		// TODO Auto-generated method stub
		return (List<Long>) queryList("RETURNPLAN.queryHasNoReturnPlan");
	}

}
