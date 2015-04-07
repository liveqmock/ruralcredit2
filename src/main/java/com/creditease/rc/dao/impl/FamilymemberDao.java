package com.creditease.rc.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.creditease.rc.dao.IFamilymemberDao;
import com.creditease.rc.domain.Familymember;
import com.creditease.rc.framework.dao.IBaseDao;
import com.creditease.rc.framework.dao.impl.BaseDao;

/**
 * 
 * @author haoqiang
 * 
 */
@Repository
public class FamilymemberDao extends BaseDao implements IFamilymemberDao {

	@Resource
	private IBaseDao baseDao;

	/**
	 * @author haoqiang
	 */
	public FamilymemberDao() {
		super();
	}

	public IBaseDao getBaseDao() {
		return baseDao;
	}

	public void setBaseDao(IBaseDao baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public void insertIntofamilymember(List<Familymember> familymember) {
		// TODO Auto-generated method stub
		baseDao.batchInsert("familymember.insertfamilymember", familymember, 200);

	}

	@Override
	public void batchInsertfamilymemberList(List<Familymember> familymember) {
		baseDao.batchInsert("familymember.insertfamilymember", familymember);
	}

	@Override
	public List<Familymember> querySelectFamilymember(Long borrowerSurveyId) {
		// TODO Auto-generated method stub
		List<Familymember> familymembers = (List<Familymember>) baseDao.queryList(
				"familymember.selectfamilymemberByBorrowerServiceAppId", borrowerSurveyId);
		return familymembers;
	}

	@Override
	public void batchUpdateFamilyMemberList(List<Familymember> updateFamilymembers) {
		// TODO Auto-generated method stub
		baseDao.batchUpdate("familymember.updatefamilymemberByPrimaryKey", updateFamilymembers);
	}

    /**
     * 根据申请id 查询其他家庭成员信息 不包括配偶部分
     * @param borrowerServiceAppId
     * @return
     */
    @Override
    public List<Familymember> queryFamilymembers(Long borrowerServiceAppId) {
        return (List<Familymember>) baseDao.queryList(
                "familymember.queryFamilymembers", borrowerServiceAppId);
    }

	@Override
	public List<Familymember> queryFamilyMemberInfo(Integer borrowerServiceAppId) {
		return (List<Familymember>) queryList("familymember.queryFamilyMemberInfo", borrowerServiceAppId);
	}
}
