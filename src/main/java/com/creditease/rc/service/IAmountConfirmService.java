package com.creditease.rc.service;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.creditease.rc.domain.AccountInfo;
import com.creditease.rc.domain.AmountConfirm;
import com.creditease.rc.domain.CreditApplication;
import com.creditease.rc.domain.GroupLoanRegist;
import com.creditease.rc.domain.Message;
import com.creditease.rc.vo.AmountConfirmVo;

/**
 * 
 * @author zhangman
 *
 */
public interface IAmountConfirmService {
	
	/**
	 * 信託打印合同并更新狀態
	 * @param creditapplicationId
	 * @return
	 */
	public String updatePrintContract(Long creditapplicationId,HttpServletRequest request);
	/**
	 * 新镇放款金额
	 * @param amountConfirm 放款金额对象
	 * @param accountInfo 
	 * @return 放款金额对象
	 */
	public Message addAmountConfirm(AmountConfirm amountConfirm,AccountInfo accountInfo);
	/**
	 * 修改
	 * @param amountConfirm 放款金额对象
	 * @return 影响的行数
	 */
	public int updateAmountConfirm(AmountConfirm amountConfirm);
	/**
	 * 撤回
	 * @param creditapplicationId id
	 */
	public void updateAmountConfirmHistory(int  creditapplicationId);
	/**
	 * 按借款申请id修改
	 * @param creditapplicationId 放款金额对象id
	 */
	public void updateAmountConfirm(int  creditapplicationId);
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
	 * @param creditApplicationId 
	 * @return 列表
	 */
	public List<AmountConfirm> selectAmountsByCreditAppId(Integer creditApplicationId);
	
	/**
	 * 根据额度确认id将状态置为1 历史状态
	 * liuli 2013-05-20
	 * @param amountConfirmId 
	 * @return int
	 */
	public int updateByAmountConfirmId(String amountConfirmId);
	
	public AmountConfirm queryAmountConfirmByPrimaryKey(long longValue);
	Message addAmountConfirm(AmountConfirm amountConfirm, AccountInfo accountInfo,GroupLoanRegist groupLoadRegist,CreditApplication creditApplication, String type);
	/**
	 * 更新起息日期
	 * @param amountConfirm
	 * @return
	 */
	boolean updateBeginInterestTime(AmountConfirm amountConfirm);
	/**
	 * 封装打印合同的方法
	 * @param amountConfirm
	 * @param accountInfo
	 * @param groupLoadRegist
	 * @param loanRegistList
	 * @param groupLoanRegist
	 * @param uploadFileList
	 * @param uploadFileTypeList
	 * @param originalFileList
	 * @param time
	 * @param creditApplication
	 * @param loanDate
	 * @param request
	 * @param type2 
	 * @param type 
	 * @return
	 */
	Message addPrintContract(AmountConfirm amountConfirm,
			AccountInfo accountInfo, GroupLoanRegist groupLoadRegist,
			String loanRegistList, String groupLoanRegist,
			String[] uploadFileList, String[] uploadFileTypeList,
			String[] originalFileList, Date time,
			CreditApplication creditApplication, String loanDate,
			HttpServletRequest request);
	
	
	/**
	 * 根据申请id查询放款配置
	 * @param creditApplicationId
	 * @return
	 */
	String selectLendingChannel(Long creditApplicationId);
	
	
	/**
	 * 打印合同的时候更新金额确认表，如果存在数据更新，否则插入
	 * @param amountConfirm
	 * @return
	 */
	/*int saveOrUpdate(AmountConfirm amountConfirm);*/
	
	/**
	 * 打印合同的时候更新金额确认表，如果存在数据更新，否则插入
	 */
	boolean selectIsExist(AmountConfirm amountConfirm);
	
}
