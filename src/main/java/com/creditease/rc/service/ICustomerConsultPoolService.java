package com.creditease.rc.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.creditease.rc.domain.CodeTable;
import com.creditease.rc.domain.Message;
import com.creditease.rc.vo.DataDictionaryVo;
import org.apache.poi.ss.usermodel.Workbook;

import com.creditease.rc.domain.CustomerConsultPool;

public interface ICustomerConsultPoolService {

	CustomerConsultPool queryCustomerConsultPool(Long consultPoolId);

	CustomerConsultPool getCustomerConsultPoolByPrimaryKey(Long consultPoolId);

	CustomerConsultPool getConsultPoolByForeignKey(Long customerConsultId);

	boolean updateCustomerConsultPool(CustomerConsultPool customerConsultPool, String invokeSource);

	/**
	 * 更改受理咨询状态为 "待退回确认" hongjieluo
	 * 
	 * @param consultPoolId
	 * @return
	 */
	boolean updateMarkingConsultStatus(Long consultPoolId);

	/**
	 * 上传excel文件
	 * 
	 * @param entityList
	 */
	void uploadExcel(List<CustomerConsultPool> entityList);

	/**
	 * 获得所有上传文件的内容，不包括头信息
	 * 
	 * @param workbook
	 * @param sheetIndex
	 * @return
	 */
	List<String[]> getAllData(Workbook workbook, int sheetIndex);

	/**
	 * 查询一个月之前的所有手机号码
	 * 
	 * @return
	 */
	public List<String> selectMoilePhonebyDate();

	public Map<String, String> isRepeat(List<String[]> list);

	/**
	 * @author hongjieluo
	 *         退回确认 把营销咨询状态变为 待分配状态
	 * @param consultPoolId
	 * @return
	 */
	boolean updateMarkingConsultStatusConfirm(Long consultPoolId);

	// 营销咨询-获取特定section数据字典
	public Map<String, List<CodeTable>> getCodeTableMapBySection(String[] sectionArr);

	/**
	 * 查询所有待分配和无营业网点的数据
	 * 
	 * @author 郝强
	 * @return
	 */
	List<CustomerConsultPool> queryCustomerConsultPoolNeedDistribute();

	/**
	 * 查询每个营业部现有咨询数量
	 * 
	 * @author 郝强
	 * @param queryDepartmentIdsString
	 * @return
	 */
	Map<String, Integer> queryDepartmentIdandCountMap(String queryDepartmentIdsString);

	/**
	 * 将这些电话号码涉及到的咨询全部置为不可操作状态
	 * 
	 * @param phoneSet
	 * @return
	 */
	Message updateOperationFlag(Set<String> phoneSet);

	/**
	 * @author hongjieluo
	 *         取消退回操作的时候 更新受理咨询状态为：待分件
	 * @param consultPoolId
	 * @return
	 */
	boolean updateAcceptConsultStatusCancel(Long consultPoolId);

	Message updateCustomerConsultPoolForAutoDistribute(List<CustomerConsultPool> customerConsultPoolsForUpdate);

	/**
	 * 查询受理咨询条件为 主键List
	 * 
	 * @author 郝强
	 * @param consultPoolIds
	 * @return
	 */
	List<CustomerConsultPool> queryCustomerConsultPoolListByConsultPoolIds(Long[] consultPoolIds);

	/**
	 * 批量修改CustomerConsultPool表信息Dynamic（动态）
	 * 
	 * @author 郝强
	 * @param customerConsultPool
	 * @return
	 */
	Message updateCustomerConsultPoolByListForDistributeOrders(List<CustomerConsultPool> customerConsultPoolsForUpdate);

	/**
	 * 修改分件操作权限
	 * 
	 * @author 郝强
	 * @param phoneSet
	 * @return
	 */
	Message updateComponentOperationFlagByPhone(Set<String> phoneSet);

	/**
	 * 分配与分件操作接口
	 * 
	 * @author 郝强
	 * @param customerConsultPoolsForUpdate 分配与分件数据
	 * @param operationType 操作类型0：分配 1：分件
	 * @param distributeType 分配分件类型0：手动 1：自动
	 * @return
	 */
	Message insertDistributLog(List<CustomerConsultPool> customerConsultPoolsForDistribut, String operationType,
			String distributeType);

	/**
	 * 通过borrowerServiceAppId查询咨询池信息
	 * 
	 * @author 郝强
	 * @param borrowerServiceAppId
	 * @return
	 */
	List<CustomerConsultPool> queryCustomerConsultPoolByBorrowerServiceAppId(Integer borrowerServiceAppId);

	/**
	 * 通过creditapplicationId查询咨询池信息
	 * 
	 * @author 郝强
	 * @param creditapplicationId
	 * @return
	 */
	List<CustomerConsultPool> queryCustomerConsultPoolByCreditapplicationId(Long creditapplicationId);

	/**
	 * 通过creditApplicationId修改咨询池表中的acceptConsultState字段
	 * 
	 * @author 郝强
	 * @param creditApplicationId
	 * @return
	 */
	Message updateAcceptConsultStateByCreditApplicationId(Integer creditApplicationId);

	/**
	 * @author hongjieluo
	 * 
	 */

}
