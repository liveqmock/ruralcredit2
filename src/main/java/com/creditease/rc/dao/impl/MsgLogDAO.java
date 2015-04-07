package com.creditease.rc.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.creditease.rc.dao.IMsgLogDAO;
import com.creditease.rc.domain.MsgLog;
import com.creditease.rc.framework.dao.IBaseDao;
import com.creditease.rc.framework.dao.impl.BaseDao;
@Repository
public class MsgLogDAO extends BaseDao implements IMsgLogDAO {

	@Resource
	private IBaseDao baseDao;
	
    public MsgLogDAO() {
        super();
    }

    /**
	 * 保存现金流入流出错误的最大借款额度的相关信息
	 * CreditApplicationDAO 实现方法：
	 * @param cashIOErrLog
	 */
    public void insert(MsgLog msgLog) {
    	baseDao.insert("RLMSGLOG.saveCashIOErrLog",msgLog);
    }
    /**
     * 根据creditapplicationId查询数据
     */
    public List<MsgLog> selectMsgLogList(int creditapplicationId) {
		return (List<MsgLog>) baseDao.queryList("RLMSGLOG.selectBycreditapplicationId",creditapplicationId);
	}

    public int updateByPrimaryKeySelective(MsgLog record) {
        int rows = getSqlMapClientTemplate().update("RLMSGLOG.abatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

	@Override
	public MsgLog selectByPrimaryKey(Short msgLogId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteBycreditapllicationId(Long creditapplicationId) {
		int rows=baseDao.delete("RLMSGLOG.deleteBycreditapllicationId",creditapplicationId);
		return rows;
	}

	@Override
	public int updateMsgLogDataByCreditapplicationId(MsgLog msgLog) {
		int rows=baseDao.update("RLMSGLOG.updateMsgLogDataByCreditapplicationId",msgLog);
		return rows;
	}

	
}