package com.creditease.rc.dao;

import java.util.List;

import com.creditease.rc.domain.Familymember;
import com.creditease.rc.framework.dao.IBaseDao;

/**
 * 
 * @author haoqiang
 * 
 */
public interface IFamilymemberDao extends IBaseDao {
	/**
	 * 
	 * @param familymember 家庭成员List集合
	 */
	public void insertIntofamilymember(List<Familymember> familymember);

	/**
	 * 
	 * @param familymember 家庭成员List集合
	 */
	public void batchInsertfamilymemberList(List<Familymember> familymember);

	/**
	 * 
	 * @param borrowerSurveyId 上述合计主键
	 * @return 家庭成员List集合
	 */
	public List<Familymember> querySelectFamilymember(Long borrowerSurveyId);

	/**
	 * 
	 * @param updateFamilymembers 家庭成员List集合
	 */
	public void batchUpdateFamilyMemberList(List<Familymember> updateFamilymembers);

    /**
     *
     根据申请id 查询其他家庭成员信息 不包括配偶部分
     * @param borrowerServiceAppId
     * @return
     */
    public List<Familymember> queryFamilymembers(Long borrowerServiceAppId)  ;
    
    /**
     * 根据借款服务申请ID查询家庭成员信息
     * @param borrowerServiceAppId
     * @return
     */
    public List<Familymember> queryFamilyMemberInfo(Integer borrowerServiceAppId);
}
