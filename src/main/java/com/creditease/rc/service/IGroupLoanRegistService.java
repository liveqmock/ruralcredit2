package com.creditease.rc.service;

import java.util.List;
import java.util.Map;

import com.creditease.rc.domain.CreditApplication;
import com.creditease.rc.domain.GroupLoanRegist;
import com.creditease.rc.domain.LoanRegist;
import com.creditease.rc.domain.Message;
import com.creditease.rc.vo.GroupLoanRegistVo;

/**
 * 放款登记service
 * @author zhangman
 *
 */
public interface IGroupLoanRegistService {
	/**
	 *新增  放款登记 ，以及附属操作
	 * @param groupLoanRegist 对象
	 * @param loanRegistList 放款明细
	 * @param creditApplication 借款申请
	 * @param uploadFileList 
	 * @param uploadFileTypeList 
	 * @param originalFileList 
	 * @return 主键id
	 */
	public boolean addGroupLoanRegist(GroupLoanRegist groupLoanRegist,CreditApplication creditApplication,
			List<LoanRegist> loanRegistList,String[] uploadFileList, String[] uploadFileTypeList, String[] originalFileList);
	/**
	 *新增 或者修改放款登记 ，以及附属操作
	 * @param groupLoanRegist 对象
	 * @param registStatus 状态（0--登记，1--确认，2--回退 ）
	 * @param loanRegistList 放款明细
	 * @param creditApplication 借款申请
	 * @return 主键id
	 */
	public Message addOrUpdateGroupLoanRegist(GroupLoanRegist groupLoanRegist,CreditApplication creditApplication, String registStatus,
			List<LoanRegist> loanRegistList);
	/**
	 * 新增放款登记 
	 * @param groupLoanRegist 放款登记对象
	 * @return 放款登记id
	 */
	public int addGroupLoanRegist(GroupLoanRegist groupLoanRegist);
	
	/**
	 * 按条件查询（如果有条件按条件查询，没有条件查询全部）
	 * @param  groupLoanRegist 条件
	 * @return 登记列表
	 */
	public List<GroupLoanRegist> searchHistory(GroupLoanRegist groupLoanRegist);
	
	/**
	 * 按条件查询（如果有条件按条件查询，没有条件查询全部）
	 * @param  groupLoanRegist 条件
	 * @return 登记列表
	 */
	public List<GroupLoanRegist> searchConfirm(GroupLoanRegist groupLoanRegist);
	/**
	 * 从审批结果中查询放款登记详细
	 * @param creditApplicationId 信贷申请单id
	 * @return 放款登记详细list
	 */
	public List<LoanRegist> selectLoanDetail(Integer creditApplicationId);
	/**
	 * 从放款登记查询放款登记详细
	 * @param creditApplicationId 信贷申请单id
	 * @return 放款登记详细list
	 */
	public List<LoanRegist> selectLoanRegist(Integer creditApplicationId);
	
	/**
	 * 查询最新的放款登记记录
	 * @param groupLoanRegist 查询条件
	 * @return 方款登记（排除放款回退）
	 */
	public GroupLoanRegist selectDengji(GroupLoanRegist groupLoanRegist);
	
	/**
	 * @author zhangman
	 * @param creditapplicationId id
	 */
	public void changeToRegist(Integer creditapplicationId);
	
	/**
	 * 对应任何一笔单子的放款登记 
	 * @author zhangman
	 * @param groupLoanRegist 放款登记
	 * @return 放款登记
	 */
	public GroupLoanRegist selectRegist(GroupLoanRegist groupLoanRegist);
	
	/**
	 * 根据业务单号查询放款登记数据
	 * liuli 2013-05-20
	 * @param businessNum 
	 * @return List<GroupLoanRegist>
	 */
	public List<GroupLoanRegist> selectByBusinessNum(String businessNum);
	
	/**
	 * 修改放款登记指定条目的状态
	 * liuli 2013-05-20
	 * @param groupLoanRegist 
	 * @return int 
	 */
	public int changeRegistStatus(GroupLoanRegist groupLoanRegist);
	/**
	 * 放款登记查看
	 * @param creditApplicationId 
	 * @return Map
	 */
	public Map selectForLook(Integer creditApplicationId);
	/**
	 * 查询放款登记 用于放款确认的查看
	 * zhangman 
	 * @param groupLoanRegist  
	 * @return List<GroupLoanRegistVo>
	 */
	public GroupLoanRegistVo searchForConfirm(GroupLoanRegist groupLoanRegist);
	/**
	 * 上传合同资料的时候更改放款信息确认
	 * @param groupLoadRegistTemp
	 * @return
	 */
	//方法重载
	boolean updateGroupLoanRegistConfirm(GroupLoanRegist groupLoadRegist);
	Message addOrUpdateGroupLoanRegist(GroupLoanRegist groupLoanRegist, CreditApplication creditApplication, String registStatus, List<LoanRegist> loanRegistList, String type);
	public GroupLoanRegist searchByCreditApplicationId(GroupLoanRegist groupLoadRegist);
	//查看放款列表
	public GroupLoanRegist searchForGroupLoanRegist(Long creditapplicationId);
	//查看放款列表new add By ygx 2014-09-09
	public GroupLoanRegist searchForGroupLoanRegistForGroupLoanRegist(Long creditapplicationId);


}
