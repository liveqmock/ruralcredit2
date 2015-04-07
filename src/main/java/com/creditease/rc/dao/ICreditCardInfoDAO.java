package com.creditease.rc.dao;

import java.util.List;

import com.creditease.rc.domain.CreditCardInfo;
/**
 * 信用卡信息
 * @author zhangman
 *
 */
public interface ICreditCardInfoDAO {

	/**
	 * 添加信用卡信息
	 * @param creditCardInfo 信用卡对象
	 * @return 信用卡id
	 */
	public int addCreditCardInfo(CreditCardInfo creditCardInfo);
	/**
	 * 修改信用卡信息
	 * @param creditCardInfo 信用卡信息对象
	 */
	public void updateCardInfo(CreditCardInfo creditCardInfo);
	/**
	 * 查询信用卡信息
	 * @param borrowerServiceAppId 借款服务申请id
	 * @return 信用卡信息列表
	 */
	public List<CreditCardInfo> selectCreditCardInfo(int borrowerServiceAppId);
	/**
	 * 按id 删除
	 * @param borrowerServiceAppId 借款服务申请
	 * @return 删除了多少条数据
	 */
	public int deleteCreditCradInfo(int borrowerServiceAppId);
	/**
	 * 按照 借款服务申请id 查询
	 * @param borrowerServiceAppId 借款服务申请id
	 * @return 信用卡信息对象
	 */
	public CreditCardInfo selectByBorrowerServiceAppId(int borrowerServiceAppId);
}
