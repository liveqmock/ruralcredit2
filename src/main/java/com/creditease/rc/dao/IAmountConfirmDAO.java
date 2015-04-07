package com.creditease.rc.dao;

import java.util.List;

import com.creditease.rc.domain.AmountConfirm;
import com.creditease.rc.framework.dao.IBaseDao;
import com.creditease.rc.vo.AmountConfirmVo;

/**
 * 放款金额确认
 * @author zhangman
 *
 */
public interface IAmountConfirmDAO extends IBaseDao{
	/**
	 * 新镇放款金额
	 * @param amountConfirm 放款金额对象
	 * @return 放款金额对象
	 */
	public long addAmountConfirm(AmountConfirm amountConfirm);
	/**
	 * 修改
	 * @param amountConfirm 放款金额对象
	 * @return 影响的行数
	 */
	public int updateAmountConfirm(AmountConfirm amountConfirm);
	/**
	 * 按借款申请id修改
	 * @param creditapplicationId 放款金额对象id
	 * @return 影响的行数
	 */
	public int updateAmountConfirm(int  creditapplicationId);
	/**
	 * 查询
	 * @param amountConfirm 放款金额对象
	 * @return 放款金额列表
	 */
	public List<AmountConfirm> searchAmountConfirm(AmountConfirm amountConfirm);
	/**
	 * 查询最新的放款
	 * @param creditapplicationId 信贷申请对象
	 * @return 放款金额对象
	 */
	public AmountConfirm selectNew(Integer creditapplicationId);
	/**
	 * 查询最新的放款
	 * @param creditapplicationId 信贷申请对象
	 * @return 放款金额对象
	 */
	public AmountConfirmVo selectAmount(Integer creditapplicationId);
	
	/**
	 * 根据申请单号查询该单号的额度确认数据
	 * liuli 2013-05-20
	 * @param creditApplicationId 申请id
	 * @return 列表
	 */
	public List<AmountConfirm> selectAmountsByCreditAppId(Integer creditApplicationId);
	
	/**
	 * 根据额度确认id将状态置为1 历史状态
	 * liuli 2013-05-20
	 * @param amountConfirmId 额度确认id
	 * @return int
	 */
	public int updateByAmountConfirmId(String amountConfirmId);
	/**
	 * 更新起息日期
	 * @param amountConfirm
	 * @return
	 */
	int updateBeginInterestTime(AmountConfirm amountConfirm);
	
	/**
	 * 根据申请id查询放款配置
	 * @param creditApplicationId
	 * @return
	 */
	String selectLendingChannel(Long creditApplicationId);
	
	
	/**
	 * 根据uuid查询放款渠道
	 * @param applyId
	 * @return
	 */
	String selectLendingChannelByApplyId(String applyId);
	
	/**
	 * 打印合同的时候更新金额确认表，如果存在数据更新，否则插入
	 * @param amountConfirm
	 * @return
	 *//*
	int saveOrUpdate(AmountConfirm amountConfirm);*/
	
	/**
	 * 查询是否有该笔数据
	 * @param creditApplicationId
	 * @return
	 */
	int selectIsExist(AmountConfirm amountConfirm);
	
	/**
	 * 根据信贷申请id跟新该条数据
	 * @param amountConfirm
	 */
	 int updateAmountConfirmClass(AmountConfirm amountConfirm);
	
}
