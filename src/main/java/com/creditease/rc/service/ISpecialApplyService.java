package com.creditease.rc.service;

import com.creditease.rc.domain.SpecialApply;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.vo.SpecialApplyVo;

import java.util.List;
import java.util.Map;

public interface ISpecialApplyService {
	boolean insert(SpecialApply specialapply);

	boolean dynamicUpdate(SpecialApplyVo specialapplyVo);

	Pagination querySpecialApplyList(Pagination pagination, Map<String, String> pramMap);

	List<SpecialApplyVo> queryApplyById(Long specialApplyId);

	List<SpecialApplyVo> selectSpecialState(Long creditapplicationId);

	SpecialApplyVo queryApplyVoByPrimaryKey(Long specialApplyId);

	public Pagination searchApplyByCreditId(Pagination pagination, Long id);

}
