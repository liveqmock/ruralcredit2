package com.creditease.rc.dao;

import com.creditease.rc.domain.SpecialApply;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.vo.SpecialApplyVo;

import java.util.List;
import java.util.Map;

public interface ISpecialApplyDao {
	boolean insert(SpecialApply specialapply);

	int dynamicUpdate(SpecialApplyVo specialapplyVo);

	Pagination queryUrgeList(Pagination pagination, Map<String, String> pramMap);

	List<SpecialApplyVo> queryApplyById(Long specialApplyId);

	List<SpecialApplyVo> selectSpecialState(Long creditapplicationId);

	SpecialApplyVo queryApplyVoByPrimaryKey(Long specialApplyId);
public Pagination searchApplyByCreditId(Pagination pagination, Long id);
	List<SpecialApply> querySpecial(Long specialApplyId);
}
