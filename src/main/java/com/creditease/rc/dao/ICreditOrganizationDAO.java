package com.creditease.rc.dao;

import java.text.ParseException;
import java.util.List;

import com.creditease.rc.domain.CreditOrganization;
/**
 * 信用卡发卡机构
 * @author zhangman
 *
 */
public interface ICreditOrganizationDAO {

	/**
	 * 批量添加信用卡发卡机构
	 * @param creditOrganization 发卡机构列表
	 */
	public void addCreditOrganization(List<CreditOrganization> creditOrganization);
	/**
	 * 单个添加信用卡发卡机构
	 * @param creditOrganization 发卡机构对象
	 * @return 发卡机构id
	 */
	public int addCreditOrganization(CreditOrganization creditOrganization);
	/**
	 * 修改信用卡发卡机构
	 * @param creditOrganization 发卡机构对象
	 */
	public void updateCreditOrganization(CreditOrganization creditOrganization);
	/**
	 * 批量修改信用卡发卡机构
	 * @param creditOrganizations  发卡机构列表
	 */
	public void batchUpdateCreditOrganization(List<CreditOrganization> creditOrganizations);
	
	/**
	 * 查询信用卡发卡机构
	 * @param creditCardInfoId 信用卡id
	 * @return 发卡机构列表
	 * @throws ParseException 抛出异常
	 */
	public List<CreditOrganization> selectCreditOrganization(int creditCardInfoId) throws ParseException ;
	/**
	 * 按 信用卡信息 id 删除 
	 * @param creditCardInfoId 信用卡id
	 * @return 删除的行数
	 */
	public int deleteCreditOrganization(int creditCardInfoId);
	/**
	 * 按 发卡机构信息 id 删除 
	 * @param creditOrgId 信用卡发卡机构 id
	 * @return 删除的行数
	 */
	public int deleteById(int creditOrgId);
}
