package com.creditease.rc.dao;

import java.util.List;

import com.creditease.rc.domain.GroupLoanRegist;
import com.creditease.rc.framework.dao.IBaseDao;
import com.creditease.rc.vo.GroupLoanRegistVo;

/**
 * 放款登记dao
 * @author zhangman
 *
 */
public interface IGroupLoanRegistDAO extends IBaseDao{

	/**
	 * 新增放款登记
	 * @param groupLoanRegist 对象
	 * @return 主键id
	 */
	public int addGroupLoanRegist(GroupLoanRegist groupLoanRegist );
	/**
	 * 查询（如果有条件按条件查询，没有条件查询全部）
	 * @param  groupLoanRegist 查询条件
	 * @return 登记列表
	 */
	public List<GroupLoanRegist> searchHistory(GroupLoanRegist groupLoanRegist);
	/**
	 * 修改放款登记
	 * @param groupLoanRegist 放款登记对象
	 * @return 修改的行数
	 */
	public int updateGroupLoanRegist(GroupLoanRegist groupLoanRegist);
	/**
	 * 除放款回退意外的放款登记
	 * @param groupLoanRegist 查询条件
	 * @return 方款登记（排除放款回退）
	 */
	public GroupLoanRegist selectDengji(GroupLoanRegist groupLoanRegist);
	
	/**
	 * 
	 * @author 韩大年  
	 * @Description:  放款确认回退
	 * @param groupLoanRegist 
	 * @return  int
	 * @version v1.0 
	 * 2013-5-17
	 */
	public int updateGroupLoanAffirmRollback(GroupLoanRegist groupLoanRegist);
	
	/**
	 * 根据业务单号查询放款登记数据
	 * liuli 2013-05-20
	 * @param businessNum 
	 * @return List<GroupLoanRegist>
	 */
	public List<GroupLoanRegist> selectByBusinessNum(String businessNum);
	
	/**
	 * 查询放款登记 用于放款确认的查看
	 * zhangman 
	 * @param groupLoanRegist  
	 * @return List<GroupLoanRegistVo>
	 */
	public List<GroupLoanRegistVo> searchConfirm(GroupLoanRegist groupLoanRegist);
	/**
	 * 按照 主键查询
	 * @param groupLoanRegistId
	 * @return
	 */
	public GroupLoanRegist selectById(Integer groupLoanRegistId);
	
	/**
	 *上传合同资料的时候更新 放款信息确认
	 * @param creditapplicationId
	 * @return
	 */
	int updateGroupLoanRegistConfirm(GroupLoanRegist groupLoanRegist);
	/**
	 * 打印合同的时候保存放款信息确认
	 * @param groupLoanRegist
	 * @return
	 */
	int addGroupLoanRegist2(GroupLoanRegist groupLoanRegist);
	/**
	 * 查询是否做过上传合同资料
	 * @param groupLoanRegist
	 * @return
	 */
	GroupLoanRegist searchByCreditApplicationId(GroupLoanRegist groupLoanRegist);
	/**
	 * 放款列表的查看
	 * @param creditapplicationId
	 * @return
	 */
	public GroupLoanRegist searchForGroupLoanRegist(Long creditapplicationId);
	
	/** add by ygx 2014-09-09
	 * 放款列表的查看
	 * @param creditapplicationId
	 * @return
	 */
	public GroupLoanRegist searchForGroupLoanRegistForGroupLoanRegist(Long creditapplicationId);
	
	/**
	 * 根据uuid 查询主键id为财务预约记录表提供数据 
	 * @param creditapplicationId
	 * @return
	 */
	public String selectGroupLoanRegistId(String uuid);
	/**
	 * 综合信贷回退更新本地为历史
	 * @param creditapplicationId
	 * @return
	 */
	int updateIcp(String creditapplicationId);
	
}
