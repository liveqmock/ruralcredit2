package com.creditease.rc.dao;

import java.util.List;
import java.util.Map;

import org.omg.PortableServer.LIFESPAN_POLICY_ID;

import com.creditease.rc.domain.RefuseReason;
import com.creditease.rc.framework.pager.Pagination;

public interface IRefuseReasonDAO {
	public long insert(RefuseReason  refuseReason);
	
	public void batchInsert(List<RefuseReason> refuseReasonList);
	
	Pagination customerGiveUpList(Pagination pagination, Map<String, String> pramMap); 	//查询客户放弃列表分页方法

	Pagination deniedLoansList(Pagination pagination,
			Map<String, String> pramMap);	//查询拒贷列表分页

    List<RefuseReason> selectRefuseReasonById(Long creditApplicationId);
}
