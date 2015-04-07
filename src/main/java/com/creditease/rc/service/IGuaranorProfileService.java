package com.creditease.rc.service;

import java.util.List;

import com.creditease.rc.vo.BorrowerInfoVo;
import com.creditease.rc.vo.CustomerBasicInfoVo;
import com.creditease.rc.vo.GuaranorProfile;

/**
 * 
 * @author haoqiang
 * 
 */
public interface IGuaranorProfileService {
	/**
	 * 保存担保人信息
	 * 
	 * @param guaranorProfile 担保人信息
	 * @param firstFlag 保存标识
	 * @return 是否成功
	 */
	boolean saveGuaranorProfile(GuaranorProfile guaranorProfile,String firstFlag);

	/**
	 * 更新担保人信息
	 * 
	 * @param guaranorProfile 更新担保人信息
	 * @return 是否更新成功
	 */
	boolean updateGuaranorProfile(GuaranorProfile guaranorProfile,String firstFlag);

	/**
	 * 查询担保人信息
	 * 
	 * @param borrowerServiceAppId 信息主键
	 * @return 担保人信息对象
	 */
	GuaranorProfile selectBorrowerService(Long borrowerServiceAppId);

	/**
	 * 查询担保人信息
	 * 
	 * @param borrowerServiceAppId 信息主键
	 * @return 担保人信息对象
	 */
	GuaranorProfile selectGuaranorProfile(Long borrowerServiceAppId);

	List<CustomerBasicInfoVo> selectCustomerBasicInfoForGuaranorProfile(BorrowerInfoVo borrowerService);
	
	/**
	 * 将编码对应到汉字
	 * liuli 2013-05-07
	 * @param guaranorProfile
	 */
	public void displayNameByCode(GuaranorProfile guaranorProfile);
	/**
	 * 保存担保人对象
	 * zhangman
	 * @param guaranorProfile 担保人对象
	 * @return 是否保存成功
	 */
	boolean saveUpdateGuaranorProfile(GuaranorProfile guaranorProfile, String doSaveStatus);
	
}
