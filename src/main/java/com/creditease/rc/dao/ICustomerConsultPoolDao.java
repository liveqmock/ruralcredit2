package com.creditease.rc.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.creditease.rc.domain.CodeTable;
import com.creditease.rc.domain.ConsultPoolLog;
import com.creditease.rc.domain.ConsultPoolLogDetail;
import com.creditease.rc.domain.CustomerConsultPool;
import com.creditease.rc.framework.dao.IBaseDao;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.vo.CustomerConsultPoolVo;
import com.creditease.rc.vo.DataDictionaryVo;

public interface ICustomerConsultPoolDao extends IBaseDao {
	Long insert(CustomerConsultPool record);

	int updateByPrimaryKey(CustomerConsultPool record);

	int updateByPrimaryKeySelective(CustomerConsultPool record);

	int updateOperationFlag(CustomerConsultPool record);

	CustomerConsultPool selectByPrimaryKey(Long consultPoolId);

	CustomerConsultPool selectByForeignKey(Long customerConsultId);

	int deleteByPrimaryKey(Long consultPoolId);

	// 查看咨询池
	CustomerConsultPool selectCustomerConsultPool(CustomerConsultPool customerConsultPool);

	/**
	 * 查询受理咨询列表
	 * 
	 * @author luohongjie
	 * @param pagination
	 * @param map
	 * @param request
	 * @return
	 */
	Pagination selectInquirePoolOfficeList(Pagination pagination, Map<String, String> map);

	/**
	 * 用于营销咨询列表 - 查看
	 * 
	 * @author manzhang
	 * @param customerConsultPoolVo
	 * @return
	 */
	CustomerConsultPoolVo registerInquireView(CustomerConsultPoolVo customerConsultPoolVo);

	int updateMarkingConsultStatus(Long consultPoolId);

	CustomerConsultPool showAcceptAdvice(Long consultPoolId);

	// 批量插入

	void batchInsert(List entityList);

	/**
	 * 
	 * @author 韩大年
	 * @Description: 批量更新
	 * @param customerConsultPoolList
	 * @return
	 * @version v1.0 2014-2-20
	 */
	public Integer batchUpdate(List<CustomerConsultPool> customerConsultPoolList);

	/**
	 * 
	 * @author 韩大年
	 * @Description: 查询无城市网点的所有咨询池数据
	 * @return
	 * @version v1.0 2014-2-20
	 */
	public List<CustomerConsultPool> selectConsultPoolByNoTeamDepartmentId();

	/**
	 * 
	 * @author 韩大年
	 * @Description: 咨询池中 选择了“区/县”
	 * @return
	 * @version v1.0 2014-2-20
	 */
	public List<CustomerConsultPool> selectByAreaIsNotNull(String area);

	/**
	 * 
	 * @author 韩大年
	 * @Description: 没有选择“区/县”，选择城市
	 * @return
	 * @version v1.0 2014-2-20
	 */
	public List<CustomerConsultPool> selectByCityIsNotNull(String city);

	/**
	 * 
	 * @author 韩大年
	 * @Description: 根据区县进行分组
	 * @return
	 * @version v1.0 2014-2-25
	 */
	public List<String> selectGroupByArea();

	/**
	 * 
	 * @author 韩大年
	 * @Description: 根据城市进行分组
	 * @return
	 * @version v1.0 2014-2-25
	 */
	public List<String> selectGroupByCity();

	public String selectValueByStatus(Map recond);

	public int updateByPrimaryKeyEdit(CustomerConsultPool record);

	/**
	 * 查询一个月之前的所有手机号
	 * 
	 * @return
	 */
	public List<String> selectMoilePhonebyDate();

	public int updateScrapFlagByPrimarykey(Long consultPoolId);

	/**
	 * 导出数据
	 * 
	 * @param pagination
	 * @param map
	 * @return
	 */
	public Pagination downloadExcel(Pagination pagination, Map<String, Object> map);

	Pagination searchSaleInquireInfo(Map searchValue, Pagination pagination);

	List selectTeamDepartment(Long long1);

	List<String> selectDistributeCity(String distributeDepartmentId);

	List<String> selectTeamDepartmentId(String distributeDepartmentId);

	CustomerConsultPool selectByPrimaryKey(CustomerConsultPool ccp);

	Pagination selectDetailByPhoneOrId(Map searchValue, Pagination pagination);

	List<CustomerConsultPoolVo> selectHistory(String telphone);

	public List<CodeTable> getCodeTableBySection(DataDictionaryVo dictionaryVo);

	List<CustomerConsultPool> queryCustomerConsultPoolNeedDistribute();

	List<Map<String, String>> queryDepartmentIdandCountMap(String queryDepartmentIdsString);

	boolean updateOperationFlag(Set<String> phoneSet);

	int updateMarkingConsultStatusConfirm(Long consultPoolId);

	int updateAcceptConsultStatusCancel(Long consultPoolId);

	// 受理咨询提交 GYH
	int updateByPrimaryKey_(CustomerConsultPool customerConsultPool);

	boolean updateCustomerConsultPoolForAutoDistribute(List<CustomerConsultPool> customerConsultPoolsForUpdate);

	// 营销管理-保存业务操作日志-分配/分件
	public void saveConsultPoolLog(List<? extends Object> list);

	/**
	 * 查询受理咨询条件为 主键List
	 * 
	 * @author 郝强
	 * @param consultPoolIdString
	 * @return
	 */
	List<CustomerConsultPool> queryCustomerConsultPoolListByConsultPoolIds(String consultPoolIdString);

	/**
	 * 批量修改CustomerConsultPool表信息Dynamic（动态）
	 * 
	 * @author 郝强
	 * @param customerConsultPool
	 * @return
	 */
	boolean updateCustomerConsultPoolByListForDistributeOrders(List<CustomerConsultPool> customerConsultPoolsForUpdate);

	/**
	 * 修改分件操作权限
	 * 
	 * @author 郝强
	 * @param phoneSet
	 * @return
	 */
	boolean updateComponentOperationFlagByPhone(Set<String> phoneSet);

	/**
	 * 插入分配分件日志表
	 * 
	 * @author 郝强
	 * @param consultPoolLog
	 */
	boolean insertConsultPoolLog(ConsultPoolLog consultPoolLog);

	/**
	 * 插入分配分件详情日志表
	 * 
	 * @author 郝强
	 * @param consultPoolLogDetails
	 * @return
	 */
	boolean insertConsultPoolLogDetail(List<ConsultPoolLogDetail> consultPoolLogDetails);

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
	boolean updateAcceptConsultStateByCreditApplicationId(Integer creditApplicationId);

}
