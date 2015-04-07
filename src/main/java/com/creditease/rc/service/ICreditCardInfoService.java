package com.creditease.rc.service;

import java.util.List;

import com.creditease.rc.domain.CreditCardInfo;
import com.creditease.rc.vo.CreditCardInfoVo;
/**
 * 
 * @author zhangman
 *
 */
public interface ICreditCardInfoService{
	/**
	 * 添加或是修改信用卡信息
	 * @param creditCardInfoVo 信用卡vo对象
	 * @return 信用卡vo对象
	 */
	public CreditCardInfoVo addOrUpdateCreditCardInfo(CreditCardInfoVo creditCardInfoVo);
	/**
	 * 查询信用卡信息
	 * @param borrowerServiceAppId 借款服务申请id
	 * @return  信用卡列表
	 */
	public List<CreditCardInfo> selectCreditCardInfo(int borrowerServiceAppId);
	/**
	 * 按id 删除
	 * @param borrowerServiceAppId 借款服务申请id
	 * @return 删除的行数
	 */
	public int deleteCreditCradInfo(int borrowerServiceAppId);
	/**
	 * 按借款服务申请 id 查询
	 * @param borrowerServiceAppId 借款服务申请id
	 * @return 信用卡vo对象
	 */
	public CreditCardInfoVo selectByBorrowerServiceAppId(int borrowerServiceAppId);
}
