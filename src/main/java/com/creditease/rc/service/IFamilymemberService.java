package com.creditease.rc.service;

import java.util.List;

import com.creditease.rc.domain.Familymember;

/**
 * 
 * @author haoqiang
 * 
 */
public interface IFamilymemberService {
	/**
	 * 
	 * @param familymember 家庭成员List集合
	 * @return 布尔类型
	 */
	boolean insertIntofamilymember(List<Familymember> familymember);

	/**
	 * 
	 * @param familymember 家庭成员List集合
	 * @return 布尔类型
	 */
	boolean batchInsert(List<Familymember> familymember);

	/**
	 * 
	 * @param borrowerSurveyId 上述合计主键
	 * @return 家庭成员List集合
	 */
	List<Familymember> searchfamilymemberList(Long borrowerSurveyId);

	/**
	 * 
	 * @param familymembers 家庭成员List集合
	 * @return 布尔类型
	 */
	boolean updateFamilyMemberList(List<Familymember> familymembers);

	/**
	 * 
	 * @param familymembers 家庭成员List集合
	 * @return 布尔类型
	 */
	boolean addFamilyMemberList(List<Familymember> familymembers);

	/**
	 * 
	 * @param familymembers 家庭成员List集合
	 * @return 布尔类型
	 */
	boolean insertFamilyMemberList(List<Familymember> familymembers);

	/**
	 * 回显Familymember数据
	 * 
	 * @param borrowerServiceAppId 信贷申请主键
	 * @return 家庭成员List集合
	 */
	List<Familymember> searchFamilymembers(Long borrowerServiceAppId);

	/**
	 * 保存Familymember家庭成员的操作
	 * 
	 * @param familymemberList 家庭成员List集合
	 * @return 家庭成员List集合
	 */
	List<Familymember> saveFamilymembers(List<Familymember> familymemberList);

    /**
     *
     根据申请id 查询其他家庭成员信息 不包括配偶部分
     * @param borrowerServiceAppId
     * @return
     */
    List<Familymember> queryFamilymembers(Long borrowerServiceAppId);
}
