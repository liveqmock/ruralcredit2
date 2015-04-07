package com.creditease.rc.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.creditease.rc.dao.INationalStandardCodeDAO;
import com.creditease.rc.domain.NationalStandardCode;
import com.creditease.rc.framework.dao.impl.BaseDao;

/**
 * 
 * NationalStandardCodeDAO.java
 * @author yifengwang
 * 2012-12-24 下午03:30:09
 * @version V2.0
 */
@Repository
public class NationalStandardCodeDAO implements INationalStandardCodeDAO {

	@Resource
	private BaseDao baseDao;


	public BaseDao getBaseDao() {
		return baseDao;
	}

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}
	
	@Override
	public List<NationalStandardCode> selectNSC(String cityName) {
		NationalStandardCode nation = new NationalStandardCode();
		nation.setCityName(cityName);
		List<NationalStandardCode> list = new ArrayList<NationalStandardCode>();
		list = (List<NationalStandardCode>) baseDao.queryList("NSC.selectProvince",nation);
		return list;
	}
	/**
	 * 
	 * @param nationalStandardCode 国标码
	 * @return 列表
	 */
	public List<NationalStandardCode> selectNSCByName(NationalStandardCode nationalStandardCode) {
		return (List<NationalStandardCode>) baseDao.queryList("NSC.selectCity", nationalStandardCode);
	}
	@Override
	public List<NationalStandardCode> selectCity(String cityName,
			Integer parentId) {
		NationalStandardCode nation = new NationalStandardCode();
		nation.setCityName(cityName);
		if(parentId != null && !"".equals(parentId)){
			nation.setParentId(Integer.valueOf(parentId));
		}
		List<NationalStandardCode> list = new ArrayList<NationalStandardCode>();
		list = (List<NationalStandardCode>) baseDao.queryList("NSC.selectCity",nation);
		return list;
	}

	@Override
	public List<NationalStandardCode> selectPrefecture(String cityName) {
		NationalStandardCode nation = new NationalStandardCode();
		nation.setCityName(cityName);
		List<NationalStandardCode> list = new ArrayList<NationalStandardCode>();
		list = (List<NationalStandardCode>) baseDao.queryList("NSC.selectPrefecture",nation);
		return list;
	}
	
	@Override
	public NationalStandardCode selectByCode(Integer cityCode) {
		// TODO Auto-generated method stub
		return (NationalStandardCode) baseDao.queryUnique("NSC.selectByCode", cityCode);
	}

    @Override
	public List<NationalStandardCode> selectByCityName(String cityName) {

		return (List<NationalStandardCode>) baseDao.queryList("NSC.selectByCityName", cityName);
	}
}
