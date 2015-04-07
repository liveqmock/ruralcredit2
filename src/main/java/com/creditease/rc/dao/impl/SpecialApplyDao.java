package com.creditease.rc.dao.impl;

import com.creditease.rc.dao.ISpecialApplyDao;
import com.creditease.rc.domain.SpecialApply;
import com.creditease.rc.framework.dao.IBaseDao;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.vo.SpecialApplyVo;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Repository
public class SpecialApplyDao implements ISpecialApplyDao {

	@Resource
	private IBaseDao baseDao;

	@Override
	public boolean insert(SpecialApply specialapply) {
		Long rows=(Long) baseDao.insertObject("SPECIALAPPLY.insert", specialapply);
		return rows>0 ? true:false;
	}

	@Override
	public int dynamicUpdate(SpecialApplyVo specialapplyVo) {
		System.out.println(specialapplyVo.getSpecialApplyId());
		if (specialapplyVo.getSpecialApplyId() != null) {
			return baseDao.update("SPECIALAPPLY.dynamicUpdate", specialapplyVo);
		}
		return 0;
	}

	@Override
	public Pagination queryUrgeList(Pagination pagination, Map<String, String> pramMap) {
		return baseDao.queryForPaginatedList("SPECIALAPPLY.queryApplyListEntity", "SPECIALAPPLY.queryApplyListCount",
				pramMap, pagination.getStartResult(), pagination.getPageSize());
	}

	@Override
	public List<SpecialApplyVo> queryApplyById(Long specialApplyId) {
		return (List<SpecialApplyVo>) baseDao.queryList("SPECIALAPPLY.queryApplyById", specialApplyId);
	}

	@Override
	public List<SpecialApplyVo> selectSpecialState(Long creditapplicationId) {
		return (List<SpecialApplyVo>) baseDao.queryList("SPECIALAPPLY.selectSpecialState", creditapplicationId);
	}

	@Override
	public SpecialApplyVo queryApplyVoByPrimaryKey(Long specialApplyId) {
		// TODO Auto-generated method stub
		return (SpecialApplyVo) baseDao.queryUnique("SPECIALAPPLY.queryApplyById", specialApplyId);
	}
@Override
	public Pagination searchApplyByCreditId(Pagination pagination, Long id) {
		return baseDao.queryForPaginatedList("SPECIALAPPLY.searchApplyByCreditId", "SPECIALAPPLY.selectSearchApplyByCreditIdCount", id, pagination.getStartResult(), pagination.getPageSize());
	}
@Override
	public List<SpecialApply> querySpecial(Long specialApplyId) {
		return (List<SpecialApply>) baseDao.queryList("SPECIALAPPLY.querySpecial", specialApplyId);
	}

}