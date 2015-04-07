package com.creditease.rc.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.creditease.rc.dao.IRlAuditDetailDao;
import com.creditease.rc.domain.RlAuditDetail;
import com.creditease.rc.framework.dao.impl.BaseDao;
/**
 * 组员类Dao层
 * @author xubingzhao
 *
 */
@Repository
public class RlAuditDetailDao extends BaseDao implements IRlAuditDetailDao {

	@Override
	public void insert(List<RlAuditDetail>list) {
		batchInsert("RLAUDITDETAIL.insertRlAuditDetail", list);
	}

	@Override
	public List<RlAuditDetail> selectChgMount(Integer id) {
		List<RlAuditDetail> list=(List<RlAuditDetail>) queryList("RLAUDITDETAIL.selectChgMount", id);
		return list;
	}

}
