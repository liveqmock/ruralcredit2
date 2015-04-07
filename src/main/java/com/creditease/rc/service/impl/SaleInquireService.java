package com.creditease.rc.service.impl;

import java.sql.Timestamp;
import java.util.*;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import com.creditease.rc.util.*;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.creditease.core.exception.BusinessException;
import com.creditease.core.security.Authorization;
import com.creditease.core.security.SpringSecurityUtils;
import com.creditease.core.security.User;
import com.creditease.rc.app.smp.EmployeeDTO;
import com.creditease.rc.common.Constants;
import com.creditease.rc.dao.ICompactRulePrefixDao;
import com.creditease.rc.dao.ICustomerConsultPoolDao;
import com.creditease.rc.domain.CompactRulePrefix;
import com.creditease.rc.domain.CustomerConsultPool;
import com.creditease.rc.domain.CustomerConsultPoolLog;
import com.creditease.rc.domain.Message;
import com.creditease.rc.domain.ProtocolPrefixMapping;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.service.ICustomerConsultPoolLogService;
import com.creditease.rc.service.ICustomerConsultPoolService;
import com.creditease.rc.service.IEasyUIService;
import com.creditease.rc.service.IOperateLogService;
import com.creditease.rc.service.IProtocolPrefixMappingService;
import com.creditease.rc.service.ISaleInquireService;
import com.creditease.rc.vo.CustomerConsultPoolVo;
import com.creditease.rc.vo.DepartmentEntityVo;
import com.creditease.rc.vo.DistributeDepartmentVO;
import com.creditease.rc.vo.SaleInquireSearchInfoVO;

@Service
public class SaleInquireService implements ISaleInquireService {

	@Resource
	private ICustomerConsultPoolService customerConsultPoolService;

	@Resource
	private SmpWSUtil smpWSUtil;

	@Resource
	private IOperateLogService operateLogService;

	@Resource
	private ICustomerConsultPoolDao customerConsultPoolDao;

	@Resource
	private ICompactRulePrefixDao compactRulePrefixDao;

	@Resource
	private IProtocolPrefixMappingService prefixMappingService;

	@Resource
	private Authorization authorization;
	@Resource
	private IEasyUIService easyUIService;
	@Resource
	private ICustomerConsultPoolLogService customerConsultPoolLogService;

	@Override
	public long saveInquire(CustomerConsultPool customerConsultPool) {
		long i = 0;
        String name_zh = "";
        try{
            name_zh = SpringSecurityUtils.getCurrentUser().getName_zh();
        }catch (Exception e){
            e.printStackTrace();
        }
        if (CommonUtil.isEmpty(name_zh)) {
            name_zh = null;
        }

		// 创建人
		customerConsultPool.setCreater(name_zh);
		// 登记方式 0-手工登记、1-Excel导入
		customerConsultPool.setMarketConsultRegistType("0");
		// 待分配 CODE_KEY-CODE_VALUE 0 待分配 1 已退回, 2 无营业网点, 3 已分配, 4 拒绝, 5 无效名单, 6 未联系上客户
		customerConsultPool.setMarketConsultState("0");
		if (CommonUtil.isEmpty(customerConsultPool.getArea())) {
			customerConsultPool.setArea(null);
		}
		if (CommonUtil.isEmpty(customerConsultPool.getMarketConsultRemark())) {
			customerConsultPool.setMarketConsultRemark(null);
		}
		i = (Long) customerConsultPoolDao.insert(customerConsultPool);
        if(i > 0){
            //记录操作日志：登记日期、客户姓名、备注
            CustomerConsultPoolLog log = new CustomerConsultPoolLog();
            log.setOptModule("a00010");//操作模块名
            log.setCurrStatus("0");//操作前状态
            log.setNextStatus("0");//操作后状态
            log.setConnectionId(i);//咨询记录主键ID
            log.setConnectionCetegory("0");//关联ID分类
            log.setCurrConnectionDicSection("marketConsultStatus");//操作前状态section
            log.setNextConnectionDicSection("marketConsultStatus");//操作后状态section
            HashMap<String,String> cm = new LinkedHashMap<String, String>();//记录内容
            cm.put("登记日期",DateUtil.dateConvertString(customerConsultPool.getRegistDate()));
            cm.put("客户姓名",customerConsultPool.getCustomerName());
            cm.put("备注", customerConsultPool.getMarketConsultRemark() == null ? "" : customerConsultPool.getMarketConsultRemark());
            customerConsultPoolLogService.insertOpt(log,cm);
        }
		return i;
	}

	@Override
	public Pagination searchSaleInquireInfo(SaleInquireSearchInfoVO saleInquireSearchInfoVO, Pagination pagination,
			String fuzzyValue, String sort, String order, HttpSession session) {

		Map searchValue = new HashMap();
		searchValue.put("SORT", sort);
		searchValue.put("ORDER", order);
		// 搜索查询时，查询所有状态咨询记录
		if (CommonUtil.isNotEmpty(saleInquireSearchInfoVO.getViewType())) {
			searchValue.put("viewType", "any");
		}
		// 给模糊查询条件赋值
		searchValue.put("mobilePhone", saleInquireSearchInfoVO.getMobilePhone() == null ? "" : saleInquireSearchInfoVO
				.getMobilePhone().trim());
		// 给具体查询条件赋值
		// 客户姓名
		searchValue.put("customerName", saleInquireSearchInfoVO.getCustomerName() == null ? ""
				: saleInquireSearchInfoVO.getCustomerName().trim());
		// 咨询方式
		searchValue.put("channel", saleInquireSearchInfoVO.getChannel());
		// 咨询日期
		searchValue.put("registDate1", saleInquireSearchInfoVO.getRegistDate1());
		searchValue.put("registDate2", saleInquireSearchInfoVO.getRegistDate2());
		// 状态
		searchValue.put("marketConsultState", saleInquireSearchInfoVO.getMarketConsultState());
		// 分配日期
		searchValue.put("distributionDate1", saleInquireSearchInfoVO.getDistributionDate1());
		searchValue.put("distributionDate2", saleInquireSearchInfoVO.getDistributionDate2());
		// 户籍地址
		if (CommonUtil.isNotEmpty(saleInquireSearchInfoVO.getProvince())
				&& (saleInquireSearchInfoVO.getProvince().equals("1") || saleInquireSearchInfoVO.getProvince().equals(
						""))) {
			saleInquireSearchInfoVO.setProvince(null);
		}
		if (CommonUtil.isNotEmpty(saleInquireSearchInfoVO.getCity())
				&& (saleInquireSearchInfoVO.getCity().equals("1") || saleInquireSearchInfoVO.getProvince().equals(""))) {
			saleInquireSearchInfoVO.setCity(null);
		}
		if (CommonUtil.isNotEmpty(saleInquireSearchInfoVO.getArea())
				&& (saleInquireSearchInfoVO.getArea().equals("1") || saleInquireSearchInfoVO.getArea().equals(""))) {
			saleInquireSearchInfoVO.setCity(null);
		}
		searchValue.put("province", saleInquireSearchInfoVO.getProvince());
		searchValue.put("city", saleInquireSearchInfoVO.getCity());
		searchValue.put("area", saleInquireSearchInfoVO.getArea());
		// 历史
		if (CommonUtil.isNotEmpty(saleInquireSearchInfoVO.getHistory())) {
			searchValue.put("history", saleInquireSearchInfoVO.getHistory());
		} else {
			searchValue.put("history", "0");
		}
		return customerConsultPoolDao.searchSaleInquireInfo(searchValue, pagination);
	}

	// 营销咨询手工分配
	@Override
	public Map<String, Object> updateDistributeDepartment(DistributeDepartmentVO distributeDepartmentVO,
			Long[] consultPoolIds, String[] statusArr,String[] dateArr) {
		Map<String, Object> retMap = new LinkedHashMap<String, Object>();
		retMap.put("flag", "success");
		if (distributeDepartmentVO == null) {
			retMap.put("flag", "error");
			retMap.put("error", "distributeDepartmentVO对象为空");
		}
		if (CommonUtil.isEmpty(distributeDepartmentVO.getDistributeDepartmentId())
				|| CommonUtil.isEmpty(distributeDepartmentVO.getDistributeDepartmentName())) {
			retMap.put("flag", "error");
			retMap.put("error", "营业部的id或名称为空");
		}
		if (consultPoolIds == null || consultPoolIds.length < 1) {
			retMap.put("flag", "error");
			retMap.put("error", "分配的咨询记录数据笔数为空");
		}
		// 验证
		if (retMap.get("flag").equals("error")) {
			return retMap;
		}

        Long[] temConsultPoolIds = consultPoolIds;
        Long[] finalConsultPoolIds = consultPoolIds;

		// 先判断
		StringBuilder ownSale = new StringBuilder();
		Map<String, Object> tempMap = new LinkedHashMap<String, Object>();
		for (int i = 0; i < consultPoolIds.length; i++) {
			/*
			 * 前台已限制复选框
			 * // 判断当前状态是否为“已分配”、“客户放弃”、“拒绝”、“无效名单”或“未联系上客户”，则不允许选中
			 * CustomerConsultPool ccPool = new CustomerConsultPool();
			 * ccPool = customerConsultPoolService.queryCustomerConsultPool(consultPoolIds[i]);
			 * if (ccPool != null && CommonUtil.isNotEmpty(ccPool.getMarketConsultState())) {
			 * if (ccPool.getMarketConsultState().equals("3") || ccPool.getMarketConsultState().equals("4")
			 * || ccPool.getMarketConsultState().equals("5") || ccPool.getMarketConsultState().equals("6")
			 * || ccPool.getMarketConsultState().equals("7")) {
			 * message.setSuccess(false);
			 * message.setMsg("所要分配的咨询客户已经被处理");
			 * return message;
			 * }
			 * }
			 */

			// 手工分配规则2：如果咨询客户所在区县有营业部，则不允许手工分配给其它区县的营业部；
			List<HashMap> departmentInfos = customerConsultPoolDao.selectTeamDepartment(consultPoolIds[i]);
            List<String> departmentNames = new ArrayList<String>();
			for (Map map : departmentInfos) {
				Set<Map.Entry<String, String>> entrySet = map.entrySet();
				for (Map.Entry<String, String> entry : entrySet) {
					/*if (entry.getKey().equals("BRANCHOFFICE_NAME")
							&& !entry.getValue().equals(distributeDepartmentVO.getDistributeDepartmentName())) {
						ownSale.append(customerConsultPoolDao.selectByPrimaryKey(consultPoolIds[i]).getCustomerName()
								+ "、");
						tempMap.put("ownSale", "客户姓名为： " + ownSale.toString().substring(0, ownSale.length() - 1) + " "
								+ "户籍地址有营业网点未分配成功");
					}*/
                    //针对一个区有多个营业部的情况
                    if (entry.getKey().equals("BRANCHOFFICE_NAME")){
                        departmentNames.add(entry.getValue());
                    }
				}
			}
            if(!departmentNames.isEmpty()){
               if(!departmentNames.contains(distributeDepartmentVO.getDistributeDepartmentName())){
                   ownSale.append(customerConsultPoolDao.selectByPrimaryKey(consultPoolIds[i]).getCustomerName()
                           + "、");
                   tempMap.put("ownSale", "客户姓名为： " + ownSale.toString().substring(0, ownSale.length() - 1) + " "
                           + "户籍地址有营业网点未分配成功");
                   finalConsultPoolIds = (Long[]) ArrayUtils.removeElement(finalConsultPoolIds, consultPoolIds[i]);
               }
            }
			/*
			 * List<CustomerConsultPool> customerConsultPoolList = new ArrayList<CustomerConsultPool>();
			 * customerConsultPoolList = customerConsultPoolDao.selectTeamDepartment(consultPoolIds[i]);
			 * if (CommonUtil.isNotEmpty(customerConsultPoolList)) {
			 * List<String> distributeCity = customerConsultPoolDao.selectDistributeCity(distributeDepartmentVO
			 * .getDistributeDepartmentId());
			 * // 要分配的营业部所在的城市和该人的实际城市不同，并且该人实际城市下有营业部，则不能分配
			 * if (CommonUtil.isNotEmpty(distributeCity)
			 * && CommonUtil.isNotEmpty(customerConsultPoolList.get(0).getCity())
			 * && !(distributeCity.get(0).equals(customerConsultPoolList.get(0).getCity()))) {
			 * message.setSuccess(false);
			 * message.setMsg("所要分配的咨询客户所在城市下有营业部，不允许手工分配给其它城市的营业部");
			 * return message;
			 * }
			 * }
			 */

			/*
			 * 前台已做限制
			 * // 手工分配规则3：一次只能分配给某一个营业部，不能分配给分中心或者城市。
			 * List<String> teamDepartmentIdList = customerConsultPoolDao.selectTeamDepartmentId(distributeDepartmentVO
			 * .getDistributeDepartmentId());
			 * if (CommonUtil.isEmpty(teamDepartmentIdList)) {
			 * message.setSuccess(false);
			 * message.setMsg("没有配置要分配的营业部,或者选择的是分中心或城市");
			 * return message;
			 * }
			 */
		}

        // 插入权限，有可能是总部人分配所以不用SpringSecurityUtils.getCurrentUser().getUserId()
        Environment env = Environment.getInstance();
        List<EmployeeDTO> employeeDTOs = null;
        try {
            // 区域area、城市city、部门team、直接departmentId
            employeeDTOs = smpWSUtil.getEmployeesListByRole_DepartmentId(
                    distributeDepartmentVO.getDistributeDepartmentId(), "ROLE_LocalFin");
            if (CommonUtil.isEmpty(employeeDTOs) || employeeDTOs.get(0) == null) {
                StringBuilder noAssistant = new StringBuilder();
                for (Long pId : temConsultPoolIds) {
                    noAssistant.append(customerConsultPoolDao.selectByPrimaryKey(pId).getCustomerName() + "、");
                }

                retMap.put("noAssistant", "客户姓名为： " + noAssistant.toString().substring(0, noAssistant.length() - 1)
                        + " " + "所分配营业部下无行政助理未分配成功");
                if(tempMap.isEmpty()){
                    return retMap;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(e.getMessage());
        }

		// 再开始手工分配
		// 分配完成后插入日志
		int counter = 0;
        CustomerConsultPoolLog log;
        CustomerConsultPool poolBase = new CustomerConsultPool();
        List<CustomerConsultPool> pools = new ArrayList<CustomerConsultPool>();
		for (int i = 0; i < finalConsultPoolIds.length; i++) {

			/*
			 * CustomerConsultPool customerConsultPool = new CustomerConsultPool();
			 * customerConsultPool.setConsultPoolId(consultPoolIds[i]);
			 * customerConsultPool.setDistributionDate(new Date());
			 * customerConsultPool.setReceiveDate(new Date());
			 * // 已分配
			 * customerConsultPool.setMarketConsultState("3");
			 * // 待分件
			 * customerConsultPool.setAcceptConsultState("3");
			 * customerConsultPool.setTeamdepartmentId(distributeDepartmentVO.getDistributeDepartmentId());
			 * customerConsultPool.setTeamdepartmentName(distributeDepartmentVO.getDistributeDepartmentName());
			 * customerConsultPool.setAreaDepartmentId(employeeDTOs.get(0).getAreaDepartmentId().toString());
			 * customerConsultPool.setAreaDepartmentName(employeeDTOs.get(0).getAreaDepartmentName());
			 * customerConsultPool.setCityDepartmentId(employeeDTOs.get(0).getCityDepartmentId().toString());
			 * customerConsultPool.setCityDepartmentName(employeeDTOs.get(0).getCityDepartmentName());
			 * customerConsultPool.setDepartmentId(employeeDTOs.get(0).getDepartmentId().toString());
			 * customerConsultPool.setDepartmentName(employeeDTOs.get(0).getDepartmentName());
			 * customerConsultPool.setSalesDepartmentManagerId(employeeDTOs.get(0).getComEmpId() == null ? ""
			 * : employeeDTOs.get(0).getComEmpId().toString());
			 * customerConsultPool.setSalesDepartmentManagerName(employeeDTOs.get(0).getName());
			 * 
			 * String name_zh = SpringSecurityUtils.getCurrentUser().getName_zh();
			 * if (CommonUtil.isEmpty(name_zh)) {
			 * name_zh = "";
			 * }
			 * customerConsultPool.setOperator(name_zh);
			 * customerConsultPool.setOperateDate(new Date());
			 */

			// 加日志 先去掉了
// CunsultLog cunsultLog = new CunsultLog();
// cunsultLog.setConnectionId(consultPoolIds[i]);
// cunsultLog.setOptModule("a00040");
// // 营销咨询状态
// CustomerConsultPool ccp = new CustomerConsultPool();
// ccp.setConsultPoolId(consultPoolIds[i]);
// ccp = (CustomerConsultPool) customerConsultPoolDao.queryUnique(
// "MFC_CUSTOMER_CONSULT_POOL.abatorgenerated_selectByPrimaryKey", ccp);
// cunsultLog.setCurrApplicationStatus(ccp.getMarketConsultState());
// cunsultLog.setNextApplicationStatus("3");
// // 对应咨询部分
// cunsultLog.setConnectionCetegory("1");
// cunsultLog.setConnectionDictionarySection("saleConsultStatus");
// Map contentMap = new HashMap();
// contentMap.put("分配营业部", distributeDepartmentVO.getDistributeDepartmentName());
// operateLogService.insertOpt(cunsultLog, contentMap);
			// 更新：状态(3-已分配)、分配日期、分配营业部
			CustomerConsultPool pool = new CustomerConsultPool();
			pool.setConsultPoolId(consultPoolIds[i]);
            poolBase = customerConsultPoolDao.selectCustomerConsultPool(pool);
            /*针对微信渠道咨询记录，分配时增加操作人*/
            if (StringUtils.isEmpty(poolBase.getCreater())) {
                String name_zh = "";
                try {
                    name_zh = SpringSecurityUtils.getCurrentUser().getName_zh();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (CommonUtil.isEmpty(name_zh)) {
                    name_zh = null;
                }
                pool.setCreater(name_zh);
            }
			pool.setMarketConsultState("3");
			pool.setDistributionDate(new Date());
			pool.setDistributeDepartmentId(distributeDepartmentVO.getDistributeDepartmentId());
			pool.setDistributeDepartmentName(distributeDepartmentVO.getDistributeDepartmentName());
			pool.setDistributeOperationFlag("0");
			// 将此联系方式所关联咨询记录更新为不可操作
			customerConsultPoolDao.updateOperationFlag(pool);
			customerConsultPoolDao.updateByPrimaryKeySelective(pool);

            pools.add(poolBase);

            log = new CustomerConsultPoolLog();
            log.setOptModule("a00040");//操作模块名
            log.setCurrStatus(statusArr[i]);//操作前状态
            log.setNextStatus("3");//操作后状态
            log.setConnectionId(pool.getConsultPoolId());//咨询记录主键ID
            log.setConnectionCetegory("0");//关联ID分类
            log.setCurrConnectionDicSection("marketConsultStatus");//操作前状态section
            log.setNextConnectionDicSection("marketConsultStatus");//操作后状态section
            HashMap<String, String> cm = new LinkedHashMap<String, String>();//记录内容
            cm.put("分配营业部", pool.getDistributeDepartmentName());
            customerConsultPoolLogService.insertOpt(log, cm);
			counter++;
			retMap.put("assigned", "成功分配 " + counter + " 条");

			try {
				Authorization authorization = env.getAuthorizationService();
				authorization.deleteAuth(CustomerConsultPool.class, consultPoolIds[i].intValue());
				authorization.createAuth(CustomerConsultPool.class, consultPoolIds[i].intValue(), employeeDTOs.get(0)
						.getComEmpId().intValue(), Integer.parseInt(Constants.SYSTEM_SIGN));

			} catch (Exception e) {
				e.printStackTrace();
				throw new BusinessException("更新权限异常:" + e.getMessage());
			}

		}

        if(!pools.isEmpty()){
            customerConsultPoolService.insertDistributLog(pools, "0", "0");
        }

		if (tempMap.containsKey("ownSale")) {
            if(retMap.containsKey("assigned")){
                retMap.put("assigned", (String) retMap.get("assigned") + "；" + tempMap.get("ownSale"));
            }else{
                retMap.putAll(tempMap);
            }
		}
		return retMap;

	}

	// 根据咨询池ID查询记录
	public CustomerConsultPool selectCustomerPoolById(long consultPoolId) {
		CustomerConsultPool consultPool = customerConsultPoolDao.selectByPrimaryKey(consultPoolId);
		return consultPool;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public boolean updateConsulePool(CustomerConsultPool customerConsultPool, String oldStatus, String newStatus) {
        /*针对微信渠道咨询记录，编辑时增加操作人*/
        if(StringUtils.isEmpty(customerConsultPool.getCreater())){
            String name_zh = "";
            try{
                name_zh = SpringSecurityUtils.getCurrentUser().getName_zh();
            }catch (Exception e){
                e.printStackTrace();
            }
            if (CommonUtil.isEmpty(name_zh)) {
                name_zh = null;
            }
            customerConsultPool.setCreater(name_zh);
        }
		int num = customerConsultPoolDao.updateByPrimaryKeyEdit(customerConsultPool);
		if (num > 0) {
            //记录操作日志：备注、无效登记状态、具体反馈结果（无）
            CustomerConsultPoolLog log = new CustomerConsultPoolLog();
            log.setOptModule("a00023");//操作模块名
            log.setCurrStatus(oldStatus);//操作前状态
            log.setNextStatus(customerConsultPool.getMarketConsultState());//操作后状态
            log.setConnectionId(customerConsultPool.getConsultPoolId());//咨询记录主键ID
            log.setConnectionCetegory("0");//关联ID分类
            log.setCurrConnectionDicSection("marketConsultStatus");//操作前状态section
            log.setNextConnectionDicSection("marketConsultStatus");//操作后状态section
            HashMap<String,String> cm = new LinkedHashMap<String, String>();//记录内容
            cm.put("备注",customerConsultPool.getMarketConsultRemark() == null ? "" : customerConsultPool.getMarketConsultRemark());
            String inValidStatus = "";
            if (customerConsultPool.getMarketConsultState().equals("4") ||
                    customerConsultPool.getMarketConsultState().equals("5") ||
                    customerConsultPool.getMarketConsultState().equals("6")){
                inValidStatus = DicUtil.convertCodeKeyToCodeValue("marketConsultStatus", customerConsultPool.getMarketConsultState());
            }
            cm.put("无效登记状态", inValidStatus);
            customerConsultPoolLogService.insertOpt(log,cm);
			return true;
		}

		else {
			return false;
		}
	}

	@Override
	public CustomerConsultPoolVo registerInquireView(Long consultPoolId) {
		// TODO Auto-generated method stub
		CustomerConsultPoolVo customerConsultPoolVo = new CustomerConsultPoolVo();
		customerConsultPoolVo.setConsultPoolId(consultPoolId);
		return customerConsultPoolDao.registerInquireView(customerConsultPoolVo);
	}

	/**
	 * 
	 * @author 韩大年
	 * @Description: 插入营业部经理的数据权限
	 * @param managerMap
	 *            :营业部经理信息
	 * @version v1.0 2014-2-25
	 */
	private void createCustomerConsultPoolDataAccess(CustomerConsultPool customerConsultPool,
			Map<String, EmployeeDTO> managerMap) {
		String teamDepartmentId = customerConsultPool.getDistributeDepartmentId();
		Long consultPoolId = customerConsultPool.getConsultPoolId();
		EmployeeDTO employeseDTO = managerMap.get(teamDepartmentId);
		if (null == employeseDTO) {
			queryManagerInfo(customerConsultPool, managerMap);
			employeseDTO = managerMap.get(teamDepartmentId);
		}

		Integer userId = 0;
		// 再次判断是否存在营业部经理
		if (null != employeseDTO) {
			userId = employeseDTO.getComEmpId();
		}
		if (null != userId && userId > 0) {
			try {
				// 接入uc
				Environment env = Environment.getInstance();
				Authorization authorization = env.getAuthorizationService();
				authorization.createAuth(CustomerConsultPool.class, consultPoolId.intValue(), userId,
						Integer.parseInt(Constants.SYSTEM_SIGN));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new BusinessException("更新权限异常");
			}
		} else {
			throw new BusinessException(customerConsultPool.getDistributeDepartmentName() + "下营业部经理:"
					+ employeseDTO.getName() + ",员工id(comEmpId)为空");
		}

	}

	/**
	 * 
	 * @author 韩大年
	 * @Description: 插入日志
	 * @param newCustomerConsultPool
	 * @version v1.0 2014-2-25
	 */
	private void insertConsultPoolLog(String currentState, CustomerConsultPool newCustomerConsultPool) {
		// 加日志
// CunsultLog cunsultLog = new CunsultLog();
// cunsultLog.setConnectionId(newCustomerConsultPool.getConsultPoolId());
// cunsultLog.setOptModule("a00050");
// // 营销咨询状态
// cunsultLog.setCurrApplicationStatus(currentState);
// cunsultLog.setNextApplicationStatus(newCustomerConsultPool.getMarketConsultState());
// // 对应咨询部分
// cunsultLog.setConnectionCetegory("1");
// cunsultLog.setConnectionDictionarySection("saleConsultStatus");
// Map contentMap = new HashMap();
// String nameString = "";
// if (CommonUtil.isNotEmpty(newCustomerConsultPool.getTeamdepartmentName())) {
// nameString = newCustomerConsultPool.getTeamdepartmentName();
// }
// contentMap.put("分配营业部", nameString);
// operateLogService.insertOpt(cunsultLog, contentMap);
	}

	public String selectValueByStatus(Map recond) {
		return customerConsultPoolDao.selectValueByStatus(recond);
	}

	/**
	 * 
	 * @author 韩大年
	 * @Description: 根据部门id获取营业部经理信息（包括部门信息）
	 * @param customerConsultPool
	 * @return
	 * @version v1.0 2014-2-27
	 */
	private Map<String, EmployeeDTO> queryManagerInfo(CustomerConsultPool customerConsultPool,
			Map<String, EmployeeDTO> managerMap) {
		// teamDepartmentId 和 员工信息
		List<EmployeeDTO> list = null;

		list = smpWSUtil.getEmployeesListByRole_DepartmentId(customerConsultPool.getDistributeDepartmentId(),
				"ROLE_LocalFin");

// if (CommonUtil.isEmpty(list)) {
// // 没有营业部经理，则查询风险经理 或者 行政前台 ，因为他们都在同级的营业部下，并且数据权限是共享的
// list = smpWSUtil.getEmployeesListByRole_DepartmentId(customerConsultPool.getDistributeDepartmentId(),
// "ROLE_RISK_MANAGER", "ROLE_RECEPTIONIST");
// }
		if (CommonUtil.isNotEmpty(list)) {
			EmployeeDTO employeseDTO = list.get(0);
			managerMap.put(customerConsultPool.getDistributeDepartmentId(), employeseDTO);
		}
		return managerMap;
	}

	/**
	 * 将该营销咨询信息置为已废弃（废弃标识(0:未废弃,1:已废弃)）
	 */
	public boolean updateScrapConsult(Long consultPoolId) {
		int rows = customerConsultPoolDao.updateScrapFlagByPrimarykey(consultPoolId);
		return (rows > 0) ? true : false;
	}

	@Override
	public Pagination searchSaleInquireInfoMenu(Pagination pagination, String sort, String order) {
		Map searchValue = new HashMap();
		searchValue.put("SORT", sort);
		searchValue.put("ORDER", order);
		searchValue.put("history", "0");
		return customerConsultPoolDao.searchSaleInquireInfo(searchValue, pagination);
	}

	@Override
	public Pagination selectDetailByPhoneOrId(Pagination pagination, String sort, String order, String objId,
			String type) {
		Map searchValue = new HashMap();
		searchValue.put("sort", sort);
		searchValue.put("order", order);
		searchValue.put("type", type);
		searchValue.put("objId", objId);

		return customerConsultPoolDao.selectDetailByPhoneOrId(searchValue, pagination);
	}

	/**
	 * 
	 * @author 韩大年
	 * @Description: 批量自动分配
	 * @version v1.0 2014-2-25
	 */
	public Message updateAutoDistributeDepartment() {
		Date startDate = new Date();
		Map<String, EmployeeDTO> managerMap = new HashMap<String, EmployeeDTO>(); // 营业部经理map
		String endTime = "";
		Message message = new Message();
		message.setSuccess(true);
		long successNum = 0;
		long cityNotTeamDepartmentNum = 0;
		// 可分配的所有咨询池信息
		List<CustomerConsultPool> normalDistributionList = new ArrayList<CustomerConsultPool>();
		String name_zh = SpringSecurityUtils.getCurrentUser().getName_zh();
		// 规则一 查询无城市网点的所有咨询池数据
		// 待分配 无网点 （合同编码规则没有对应的城市营业部）
		List<CustomerConsultPool> consultPoolByNoTeamDepartmentIdList = customerConsultPoolDao
				.selectConsultPoolByNoTeamDepartmentId();
		// 循环这个List 目的是把那些未分配和无网点的且和城市匹配不上的 设置成 无网点的
		if (CommonUtil.isNotEmpty(consultPoolByNoTeamDepartmentIdList)) {
			cityNotTeamDepartmentNum = consultPoolByNoTeamDepartmentIdList.size();
			for (CustomerConsultPool CustomerConsultPool : consultPoolByNoTeamDepartmentIdList) {
				String currentState = CustomerConsultPool.getMarketConsultState();
				CustomerConsultPool.setMarketConsultState("2");// 设置成无营业网点
				CustomerConsultPool.setOperator(name_zh);
				CustomerConsultPool.setOperateDate(new Date());
				// 插入日志
				insertConsultPoolLog(currentState, CustomerConsultPool);
			}

		}

		// 规则2 如果选择了“区/县”，并且该“区/县”下只有一个营业部，则只能分配给该营业部 --area 和 city不为null
		// 规则3 如果选择了“区/县”，并且“区/县”下有多个营业部，则以轮询方式平均分配给所选“区/县”下的营业部 --area 和
		// city不为null
		// 两个规则合并成一个方法：查询“区/县”的所有咨询池数据分配到营业部（一个或多个）
		// 3.1 查询区县下所有的area
		List<String> areaList = this.customerConsultPoolDao.selectGroupByArea();
		// 存放选择区县，区县下无营业部的咨询池数据
		Map<String, List<CustomerConsultPool>> city_map = new HashMap<String, List<CustomerConsultPool>>();

		if (CommonUtil.isNotEmpty(areaList)) {
			for (String area : areaList) {
				// 3.2 查询每一个区县下所有营业部信息
				List<CompactRulePrefix> compactRulePrefixList = this.compactRulePrefixDao
						.selectCompactRulePrefixByArea(area);
				// 3.3 查询每一个区县下所有 咨询池信息
				List<CustomerConsultPool> customerConsultPoolList = this.customerConsultPoolDao
						.selectByAreaIsNotNull(area);
				// 3.4 轮询 营业部,分配咨询池信息
				if (CommonUtil.isEmpty(customerConsultPoolList)) {
					continue;
				}
				// 3.4.1判断区下面是否有营业部，如果没有。则分配到城市下营业部
				if (CommonUtil.isEmpty(compactRulePrefixList)) {
					// 新规则4 如果选择了“区/县”，但是“区/县”下无营业部，并且城市只有一个营业部, 则直接分配到对应的营业部；
					// 新规则 5 如果选择了“区/县”，但是“区/县”下无营业部，并且城市有多个营业部,
					// 则以轮循的方式平均分配给该城市下的营业部（所选城市下的营业部的排列顺序为：按照受理咨询列表中营业部的收件条数从小到大排列）；
					// 区编码不重复。唯一，所以取出一个即可
					String city = customerConsultPoolList.get(0).getCity();
					if (city_map.containsKey(city)) {
						List<CustomerConsultPool> listTemp = city_map.get(city);
						listTemp.addAll(customerConsultPoolList);
						city_map.put(city, listTemp);
					} else {
						city_map.put(city, customerConsultPoolList);
					}
				}
				// 再次判断，城市下是否存在营业部
				if (CommonUtil.isEmpty(compactRulePrefixList)) {
					continue;
				}

				int teamLen = compactRulePrefixList.size();
				int index = 0;
				// 批量分配时，如果所在城市下的营业部没有营业部经理，则不分配，但是可以成功分配其他的城市的咨询客户；
				List<CustomerConsultPool> tempList = new ArrayList<CustomerConsultPool>();
				for (CustomerConsultPool CustomerConsultPool : customerConsultPoolList) {
					if (index >= teamLen) {
						index = 0;
					}
					String currentState = CustomerConsultPool.getMarketConsultState();

					CompactRulePrefix compactRulePrefix = compactRulePrefixList.get(index);
					CustomerConsultPool.setMarketConsultState("3");
					CustomerConsultPool.setDistributeDepartmentId(compactRulePrefix.getTeamDepartmentId());
					CustomerConsultPool.setDistributeDepartmentName(compactRulePrefix.getTeamDepartmentName());
					CustomerConsultPool.setOperator(name_zh);
					CustomerConsultPool.setOperateDate(new Date());
					CustomerConsultPool.setDistributionDate(new Date());
					CustomerConsultPool.setReceiveDate(new Date());
					// 待分件
					CustomerConsultPool.setAcceptConsultState("3");
					// 获取营业部经理信息
					if (!managerMap.containsKey(CustomerConsultPool.getDistributeDepartmentId())) {
						queryManagerInfo(CustomerConsultPool, managerMap);
					}
					EmployeeDTO EmployeeDTO = managerMap.get(CustomerConsultPool.getDistributeDepartmentId());
					// 没有营业部经理。则不分配，跳出来,营业部还是取值不变，index 不用加1
					if (null == EmployeeDTO) {
						continue;
					}
					CustomerConsultPool.setDepartmentId(EmployeeDTO.getDepartmentId() == null ? "" : EmployeeDTO
							.getDepartmentId().toString());
					CustomerConsultPool.setDepartmentName(EmployeeDTO.getDepartmentName() == null ? "" : EmployeeDTO
							.getDepartmentName().toString());
					CustomerConsultPool.setAreaDepartmentId(EmployeeDTO.getAreaDepartmentId() == null ? ""
							: EmployeeDTO.getAreaDepartmentId().toString());
					CustomerConsultPool.setAreaDepartmentName(EmployeeDTO.getAreaDepartmentName() == null ? ""
							: EmployeeDTO.getAreaDepartmentName().toString());
					CustomerConsultPool.setCityDepartmentId(EmployeeDTO.getCityDepartmentId() == null ? ""
							: EmployeeDTO.getCityDepartmentId().toString());
					CustomerConsultPool.setCityDepartmentName(EmployeeDTO.getCityDepartmentName() == null ? ""
							: EmployeeDTO.getCityDepartmentName().toString());
					CustomerConsultPool.setSalesDepartmentManagerId(EmployeeDTO.getComEmpId() == null ? ""
							: EmployeeDTO.getComEmpId().toString());
					CustomerConsultPool.setSalesDepartmentManagerName(EmployeeDTO.getName());

					tempList.add(CustomerConsultPool);

					index++;
					// 插入日志
					insertConsultPoolLog(currentState, CustomerConsultPool);
				}
				normalDistributionList.addAll(tempList);

			}

		}

		// 新规则4 如果选择了“区/县”，但是“区/县”下无营业部，并且城市只有一个营业部, 则直接分配到对应的营业部；
		// 新规则 5 如果选择了“区/县”，但是“区/县”下无营业部，并且城市有多个营业部,
		// 则以轮循的方式平均分配给该城市下的营业部（所选城市下的营业部的排列顺序为：按照受理咨询列表中营业部的收件条数从小到大排列）；
		if (city_map.size() > 0) {
			Set<String> citySet = city_map.keySet();
			for (String city : citySet) {
				// 5.2 查询每一个城市下所有营业部信息
				List<CompactRulePrefix> compactRulePrefixList = this.compactRulePrefixDao
						.selectCompactRulePrefixByCity(city);
				// 5.3 查询每一个城市下所有 咨询池信息
				List<CustomerConsultPool> customerConsultPoolList = city_map.get(city);
				// 5.4 轮询 营业部,分配咨询池信息
				if (CommonUtil.isNotEmpty(customerConsultPoolList) && CommonUtil.isNotEmpty(compactRulePrefixList)) {
					int teamLen = compactRulePrefixList.size();
					int index = 0;

					// 批量分配时，如果所在城市下的营业部没有营业部经理，则不分配，但是可以成功分配其他的城市的咨询客户；
					List<CustomerConsultPool> tempList = new ArrayList<CustomerConsultPool>();
					for (CustomerConsultPool CustomerConsultPool : customerConsultPoolList) {
						if (index >= teamLen) {
							index = 0;
						}
						String currentState = CustomerConsultPool.getMarketConsultState();
						CompactRulePrefix compactRulePrefix = compactRulePrefixList.get(index);
						CustomerConsultPool.setMarketConsultState("3");
						CustomerConsultPool.setDistributeDepartmentId(compactRulePrefix.getTeamDepartmentId());
						CustomerConsultPool.setDistributeDepartmentName(compactRulePrefix.getTeamDepartmentName());
						CustomerConsultPool.setOperator(name_zh);
						CustomerConsultPool.setOperateDate(new Date());
						CustomerConsultPool.setDistributionDate(new Date());
						CustomerConsultPool.setReceiveDate(new Date());
						// 待分件
						CustomerConsultPool.setAcceptConsultState("3");
						// 获取营业部经理信息
						if (!managerMap.containsKey(CustomerConsultPool.getDistributeDepartmentId())) {
							queryManagerInfo(CustomerConsultPool, managerMap);
						}
						EmployeeDTO EmployeeDTO = managerMap.get(CustomerConsultPool.getDistributeDepartmentId());
						// 没有营业部经理。则不分配，跳出来,营业部还是取值不变，index 不用加1
						if (null == EmployeeDTO) {
							continue;
						}
						CustomerConsultPool.setDepartmentId(EmployeeDTO.getDepartmentId() == null ? "" : EmployeeDTO
								.getDepartmentId().toString());
						CustomerConsultPool.setDepartmentName(EmployeeDTO.getDepartmentName() == null ? ""
								: EmployeeDTO.getDepartmentName().toString());
						CustomerConsultPool.setAreaDepartmentId(EmployeeDTO.getAreaDepartmentId() == null ? ""
								: EmployeeDTO.getAreaDepartmentId().toString());
						CustomerConsultPool.setAreaDepartmentName(EmployeeDTO.getAreaDepartmentName() == null ? ""
								: EmployeeDTO.getAreaDepartmentName().toString());
						CustomerConsultPool.setCityDepartmentId(EmployeeDTO.getCityDepartmentId() == null ? ""
								: EmployeeDTO.getCityDepartmentId().toString());
						CustomerConsultPool.setCityDepartmentName(EmployeeDTO.getCityDepartmentName() == null ? ""
								: EmployeeDTO.getCityDepartmentName().toString());
						CustomerConsultPool.setSalesDepartmentManagerId(EmployeeDTO.getComEmpId() == null ? ""
								: EmployeeDTO.getComEmpId().toString());
						CustomerConsultPool.setSalesDepartmentManagerName(EmployeeDTO.getName());

						tempList.add(CustomerConsultPool);
						index++;
						// 插入日志
						insertConsultPoolLog(currentState, CustomerConsultPool);
					}

					normalDistributionList.addAll(tempList);

				}
			}

		}

		// 规则6 如果没有选择“区/县”，并且该城市下只有一个营业部，则直接分配到对应的营业部；
		// 规则7
		// 如果没有选择“区/县”，并且该城市下有多个营业部，则以轮循的方式平均分配给该城市下的营业部（所选城市下的营业部的排列顺序为：按照收件条数从小到大排列）。
		// 两个规则合并成一个方法：查询“城市”的所有咨询池数据分配到营业部（一个或多个）
		// 7.1 查询城市下所有的city
		List<String> cityList = this.customerConsultPoolDao.selectGroupByCity();
		if (CommonUtil.isNotEmpty(cityList)) {
			for (String city : cityList) {
				// 7.2 查询每一个城市下所有营业部信息
				List<CompactRulePrefix> compactRulePrefixList = this.compactRulePrefixDao
						.selectCompactRulePrefixByCity(city);
				// 7.3 查询每一个城市下所有 咨询池信息
				List<CustomerConsultPool> customerConsultPoolList = this.customerConsultPoolDao
						.selectByCityIsNotNull(city);
				// 7.4 轮询 营业部,分配咨询池信息
				if (CommonUtil.isNotEmpty(customerConsultPoolList) && CommonUtil.isNotEmpty(compactRulePrefixList)) {
					int teamLen = compactRulePrefixList.size();
					int index = 0;
					// 批量分配时，如果所在城市下的营业部没有营业部经理，则不分配，但是可以成功分配其他的城市的咨询客户；
					List<CustomerConsultPool> tempList = new ArrayList<CustomerConsultPool>();
					for (CustomerConsultPool CustomerConsultPool : customerConsultPoolList) {
						if (index >= teamLen) {
							index = 0;
						}
						String currentState = CustomerConsultPool.getMarketConsultState();
						CompactRulePrefix compactRulePrefix = compactRulePrefixList.get(index);
						CustomerConsultPool.setMarketConsultState("3");
						CustomerConsultPool.setDistributeDepartmentId(compactRulePrefix.getTeamDepartmentId());
						CustomerConsultPool.setDistributeDepartmentName(compactRulePrefix.getTeamDepartmentName());
						CustomerConsultPool.setOperator(name_zh);
						CustomerConsultPool.setOperateDate(new Date());
						CustomerConsultPool.setDistributionDate(new Date());
						CustomerConsultPool.setReceiveDate(new Date());
						// 待分件
						CustomerConsultPool.setAcceptConsultState("3");
						// 获取营业部经理信息
						if (!managerMap.containsKey(CustomerConsultPool.getDistributeDepartmentId())) {
							queryManagerInfo(CustomerConsultPool, managerMap);
						}
						EmployeeDTO EmployeeDTO = managerMap.get(CustomerConsultPool.getDistributeDepartmentId());
						// 没有营业部经理。则不分配，跳出来,营业部还是取值不变，index 不用加1
						if (null == EmployeeDTO) {
							continue;
						}
						CustomerConsultPool.setDepartmentId(EmployeeDTO.getDepartmentId() == null ? "" : EmployeeDTO
								.getDepartmentId().toString());
						CustomerConsultPool.setDepartmentName(EmployeeDTO.getDepartmentName() == null ? ""
								: EmployeeDTO.getDepartmentName().toString());
						CustomerConsultPool.setAreaDepartmentId(EmployeeDTO.getAreaDepartmentId() == null ? ""
								: EmployeeDTO.getAreaDepartmentId().toString());
						CustomerConsultPool.setAreaDepartmentName(EmployeeDTO.getAreaDepartmentName() == null ? ""
								: EmployeeDTO.getAreaDepartmentName().toString());
						CustomerConsultPool.setCityDepartmentId(EmployeeDTO.getCityDepartmentId() == null ? ""
								: EmployeeDTO.getCityDepartmentId().toString());
						CustomerConsultPool.setCityDepartmentName(EmployeeDTO.getCityDepartmentName() == null ? ""
								: EmployeeDTO.getCityDepartmentName().toString());
						CustomerConsultPool.setSalesDepartmentManagerId(EmployeeDTO.getComEmpId() == null ? ""
								: EmployeeDTO.getComEmpId().toString());
						CustomerConsultPool.setSalesDepartmentManagerName(EmployeeDTO.getName());

						tempList.add(CustomerConsultPool);
						index++;
						// 插入日志
						insertConsultPoolLog(currentState, CustomerConsultPool);
					}

					normalDistributionList.addAll(tempList);

				}
			}
		}
		// 规则1 批量更新
		if (CommonUtil.isNotEmpty(consultPoolByNoTeamDepartmentIdList)) {

			cityNotTeamDepartmentNum = this.customerConsultPoolDao.batchUpdate(consultPoolByNoTeamDepartmentIdList);
		}
		// 规则2-5 统一批量更新
		if (CommonUtil.isNotEmpty(normalDistributionList)) {

			successNum = this.customerConsultPoolDao.batchUpdate(normalDistributionList);
			// 插入数据权限
			for (CustomerConsultPool customerConsultPool : normalDistributionList) {
				createCustomerConsultPoolDataAccess(customerConsultPool, managerMap);
			}
		}
		String startTime = DateUtil.dateConvertStringTime(startDate);
		Date endDate = new Date();
		endTime = DateUtil.dateConvertStringTime(endDate);
		message.setMsg("成功分配" + successNum + "条，无营业网点" + cityNotTeamDepartmentNum + "条！执行开始时间：" + startTime + ",结束时间："
				+ endTime + ",执行时间：" + String.valueOf(endDate.getTime() - startDate.getTime()) + "毫秒");
		return message;
	}

	/**
	 * 以下为郝强添加的自动分配方法
	 */
	@Override
	public Message updateAutoDistributeSaleInquire() {
		// TODO Auto-generated method stub
		Message message = new Message();
		Timestamp distributeDate = new Timestamp(new Date().getTime());
		Map<String, EmployeeDTO> managerMap = new HashMap<String, EmployeeDTO>(); // 营业部经理map
		DepartmentUtil.departmentMap.clear();
		easyUIService.constructDepartmentMap();// 重新加载新的营业部Map
		List<CustomerConsultPoolLog> customerConsultPoolLogs = new ArrayList<CustomerConsultPoolLog>();
		int successNum = 0;
		int noPointNum = 0;
		int noManagerNum = 0;
		// Ⅰ 查询所有待分配和无营业网点的数据
		List<CustomerConsultPool> customerConsultPools = customerConsultPoolService
				.queryCustomerConsultPoolNeedDistribute();
		// 声明电话号码SET
		Set<String> phoneSet = new HashSet<String>();
		// 声明一个用来update的CustomerConsultPool List用来将分配好的数据进行更新
		List<CustomerConsultPool> customerConsultPoolsForUpdate = new ArrayList<CustomerConsultPool>();
		List<CustomerConsultPool> customerConsultPoolsForUpdateNoAuthorization = new ArrayList<CustomerConsultPool>();

		// 得到区和待分配的咨询map
		Map<String, List<CustomerConsultPool>> districtAndCustomerConsultPoolMap = getDistrictDepartmentAndCustomerConsultPoolMap(customerConsultPools);

		// Ⅱ 查询营业部和区/县对应关系 -- 以及根据营业部的受理咨询数据量对其排序
		Map<String, List<String>> districtAndDepartmentIdListMap = getDistrictAndDepartmentMap();

		Set<Entry<String, List<CustomerConsultPool>>> districtAndCustomerConsultPoolSet = districtAndCustomerConsultPoolMap
				.entrySet();
		for (Entry<String, List<CustomerConsultPool>> entry : districtAndCustomerConsultPoolSet) {
			String district = entry.getKey();
			List<CustomerConsultPool> consultPools = entry.getValue();
			List<String> departmentIdList = districtAndDepartmentIdListMap.get(district);// 取到对应的区下的营业部
			if (CommonUtil.isEmpty(departmentIdList)) {
				// 如果对应区下没有营业部
				// 该数据则为无营业网点
				for (CustomerConsultPool consultPool : consultPools) {
					String currStatus = consultPool.getMarketConsultState();
					Long getConsultPoolId = consultPool.getConsultPoolId();
					consultPool.setMarketConsultState("2");
					CustomerConsultPoolLog log = new CustomerConsultPoolLog();
					log.setOptModule("a00050");
					log.setCurrStatus(currStatus);
					log.setNextStatus("2");
					log.setConnectionId(getConsultPoolId);
					log.setConnectionCetegory("0");// marketConsultStatus acceptConsultState
					log.setCurrConnectionDicSection("marketConsultStatus");
					log.setNextConnectionDicSection("marketConsultStatus");
					log.setOptBussinessContent("自动分配营业部：无营业网点");
					customerConsultPoolLogs.add(log);
				}
				customerConsultPoolsForUpdateNoAuthorization.addAll(consultPools);
				noPointNum++;
			} else {
				int max = departmentIdList.size();
				int index = 0;
				for (CustomerConsultPool customerConsultPool : consultPools) {
					String departmentId = departmentIdList.get(index);
					customerConsultPool.setDistributeDepartmentId(departmentId);
					DepartmentEntityVo departmentEntityVo = DepartmentUtil.departmentMap.get(departmentId);// 更新下 没有营业部经理怎么办
					if (departmentEntityVo == null) {
						String currStatus = customerConsultPool.getMarketConsultState();
						Long getConsultPoolId = customerConsultPool.getConsultPoolId();
						customerConsultPool.setMarketConsultState("2");
						CustomerConsultPoolLog log = new CustomerConsultPoolLog();
						log.setOptModule("a00050");
						log.setCurrStatus(currStatus);
						log.setNextStatus("2");
						log.setConnectionId(getConsultPoolId);
						log.setConnectionCetegory("0");// marketConsultStatus acceptConsultState
						log.setCurrConnectionDicSection("marketConsultStatus");
						log.setNextConnectionDicSection("marketConsultStatus");
						log.setOptBussinessContent("自动分配营业部：无营业网点");
						customerConsultPoolLogs.add(log);
						customerConsultPoolsForUpdateNoAuthorization.addAll(consultPools);
						noPointNum++;
						continue;
					}
					String departmentName = departmentEntityVo.getDepartmentName();
					System.out.println(departmentName);

					customerConsultPool.setDistributeDepartmentName(departmentName);

					String currStatus = customerConsultPool.getMarketConsultState();
					System.out.println(currStatus);
					customerConsultPool.setMarketConsultState("3");
					customerConsultPool.setAcceptConsultState("0");
					customerConsultPool.setDistributionDate(distributeDate);
					customerConsultPool.setDistributeOperationFlag("0");
					customerConsultPool.setComponentOperationFlag("0");
					System.out.println(customerConsultPool.getDistributeDepartmentId());
					System.out.println(customerConsultPool.getDistributeDepartmentName());
					// 获取行政助理信息
					if (!managerMap.containsKey(customerConsultPool.getDistributeDepartmentId())) {
						queryManagerInfo(customerConsultPool, managerMap);
					}
					EmployeeDTO EmployeeDTO = managerMap.get(customerConsultPool.getDistributeDepartmentId());
					// 没有行政助理。则不分配，跳出来,营业部还是取值不变，index 不用加1
					if (EmployeeDTO == null) {
						currStatus = customerConsultPool.getMarketConsultState();
						Long getConsultPoolId = customerConsultPool.getConsultPoolId();
						CustomerConsultPoolLog log = new CustomerConsultPoolLog();
						log.setOptModule("a00050");
						log.setCurrStatus(currStatus);
						log.setNextStatus("0");
						log.setConnectionId(getConsultPoolId);
						log.setConnectionCetegory("0");// marketConsultStatus acceptConsultState
						log.setCurrConnectionDicSection("marketConsultStatus");
						log.setNextConnectionDicSection("marketConsultStatus");
						log.setOptBussinessContent("自动分配营业部：该营业部下无行政助理");
						customerConsultPoolLogs.add(log);
						noManagerNum++;
						message.setSuccess(false);
						message.setMsg("自动分分配涉及到的"+departmentName+"下没有行政助理，请先添加行政助理后再分配！");
						return message;
					}
					String getMobilePhone = customerConsultPool.getMobilePhone();
					phoneSet.add(getMobilePhone);
					customerConsultPoolsForUpdate.add(customerConsultPool);
					Long getConsultPoolId = customerConsultPool.getConsultPoolId();
					CustomerConsultPoolLog log = new CustomerConsultPoolLog();
					log.setOptModule("a00050");
					log.setCurrStatus(currStatus);
					log.setNextStatus("3");
					log.setConnectionId(getConsultPoolId);
					log.setConnectionCetegory("0");// marketConsultStatus acceptConsultState
					log.setCurrConnectionDicSection("marketConsultStatus");
					log.setNextConnectionDicSection("marketConsultStatus");
					log.setOptBussinessContent("自动分配营业部：" + departmentName);
					customerConsultPoolLogs.add(log);

					successNum++;
					// 还差营业部名称
					// 设置为可操作状态
					index++;
					if (!(index < max)) {
						index = 0;
					}
				}
			}
		}
		// 将这些电话号码涉及到的咨询全部置为不可操作状态
		customerConsultPoolService.updateOperationFlag(phoneSet);
		customerConsultPoolService.updateComponentOperationFlagByPhone(phoneSet);
		// 将这分配好的数据更新掉
		message = customerConsultPoolService.updateCustomerConsultPoolForAutoDistribute(customerConsultPoolsForUpdate);
		message = customerConsultPoolService
				.updateCustomerConsultPoolForAutoDistribute(customerConsultPoolsForUpdateNoAuthorization);
		if (CommonUtil.isNotEmpty(customerConsultPoolsForUpdate)) {

			customerConsultPoolService.insertDistributLog(customerConsultPoolsForUpdate, "0", "1");
		}

		for (CustomerConsultPoolLog customerConsultPoolLog : customerConsultPoolLogs) {
			customerConsultPoolLogService.insertOpt(customerConsultPoolLog, null);
		}

		if (message.isSuccess()) {
			// 插入数据权限
			for (CustomerConsultPool customerConsultPool : customerConsultPoolsForUpdate) {
				createCustomerConsultPoolDataAccess(customerConsultPool, managerMap);
			}
		}
		message.setMsg("成功分配 " + successNum + " 条！，无营业网点 " + noPointNum + " 条！，营业部下无行政助理 " + noManagerNum + " 条！");
		return message;
	}

	private Map<String, List<CustomerConsultPool>> getDistrictDepartmentAndCustomerConsultPoolMap(
			List<CustomerConsultPool> customerConsultPools) {
		// TODO Auto-generated method stub
		Map<String, List<CustomerConsultPool>> districtAndCustomerConsultPoolMap = new HashMap<String, List<CustomerConsultPool>>();

		for (CustomerConsultPool customerConsultPool : customerConsultPools) {
			String district = customerConsultPool.getArea();
			List<CustomerConsultPool> consultPools = districtAndCustomerConsultPoolMap.get(district);
			if (CommonUtil.isEmpty(consultPools)) {
				consultPools = new ArrayList<CustomerConsultPool>();
			}
			consultPools.add(customerConsultPool);
			districtAndCustomerConsultPoolMap.put(district, consultPools);
		}
		return districtAndCustomerConsultPoolMap;
	}

	// 得到营业部和区/县对应关系 -- 以及根据营业部的受理咨询数据量对其排序
	private Map<String, List<String>> getDistrictAndDepartmentMap() {
		// TODO Auto-generated method stub
		Map<String, List<String>> districtAndDepartmentIdListMap = new HashMap<String, List<String>>();
		List<ProtocolPrefixMapping> prefixMappings = prefixMappingService.queryDistrictAndDepartment();
		String queryDepartmentIdsString = "";
		for (ProtocolPrefixMapping protocolPrefixMapping : prefixMappings) {
			String getCountyHome = protocolPrefixMapping.getCountyHome();
			String getBranchofficeId = protocolPrefixMapping.getBranchofficeId();
			List<String> departmentIdList = districtAndDepartmentIdListMap.get(getCountyHome);
			if (CommonUtil.isEmpty(departmentIdList)) {
				departmentIdList = new ArrayList<String>();
			}
			departmentIdList.add(getBranchofficeId);
			queryDepartmentIdsString = queryDepartmentIdsString + getBranchofficeId + ",";
			districtAndDepartmentIdListMap.put(getCountyHome, departmentIdList);
		}
		// Map<String, Integer> departmentIdandCountMap = new HashMap<String, Integer>();
		if (CommonUtil.isNotEmpty(queryDepartmentIdsString)) {
			queryDepartmentIdsString = queryDepartmentIdsString.substring(0, queryDepartmentIdsString.length() - 1);
			// 查询每个营业部现有咨询数量
			final Map<String, Integer> departmentIdandCountMap = customerConsultPoolService
					.queryDepartmentIdandCountMap(queryDepartmentIdsString);
			// 每个区域下的营业部对受理咨询量从小到大排序
			Set<Entry<String, List<String>>> entries = districtAndDepartmentIdListMap.entrySet();

			for (Entry<String, List<String>> entry : entries) {
				List<String> departmentIds = entry.getValue();
				Collections.sort(departmentIds, new Comparator<String>() {
					@Override
					public int compare(String o1, String o2) {
						// TODO Auto-generated method stub
						Integer o1l = departmentIdandCountMap.get(o1);
						Integer o2l = departmentIdandCountMap.get(o2);
						int o1Count = o1l == null ? 0 : o1l;
						int o2Count = o2l == null ? 0 : o2l;
						return o1Count - o2Count;
					}
				});
			}
		}

		return districtAndDepartmentIdListMap;
	}

	// 受理咨询分件
	@Override
	public Message updateDistributeOrders(CustomerConsultPool customerConsultPool, Long[] consultPoolIds) {
		Message message = new Message();
		message.setSuccess(true);
		List<CustomerConsultPoolLog> customerConsultPoolLogs = new ArrayList<CustomerConsultPoolLog>();
		if (customerConsultPool == null) {
			message.setSuccess(false);
			message.setMsg("customerConsultPool对象为空！");
		}

// if (CommonUtil.isEmpty(customerConsultPool.getDistributeDepartmentId())
// || CommonUtil.isEmpty(customerConsultPool.getDistributeDepartmentName())) {
// message.setSuccess(false);
// message.setMsg("受理营业部名称或id为空");
// }
		if (customerConsultPool.getLoanOfficerId() == null
				|| CommonUtil.isEmpty(customerConsultPool.getLoanOfficerName())) {
			message.setSuccess(false);
			message.setMsg("受理客户经理姓名或id为空！");
		}
		if (consultPoolIds == null || consultPoolIds.length < 1) {
			message.setSuccess(false);
			message.setMsg("分件数据笔数为空！");
		}
		List<CustomerConsultPool> customerConsultPools = customerConsultPoolService
				.queryCustomerConsultPoolListByConsultPoolIds(consultPoolIds);
		if (CommonUtil.isEmpty(customerConsultPools)) {
			message.setSuccess(false);
			message.setMsg("分件数据数据库查询为空！");
		}
		// 验证
		if (!message.isSuccess()) {
			return message;
		}

		// 先验证
		for (CustomerConsultPool consultPool : customerConsultPools) {
			//  只有“待分件”、 “待分件确认”状态的咨询客户才可以选择，其他状态均不允许被选中；
			if (consultPool != null && CommonUtil.isNotEmpty(consultPool.getAcceptConsultState())) {
				if (!consultPool.getAcceptConsultState().equals("0")
						&& !consultPool.getAcceptConsultState().equals("1")) {
					message.setSuccess(false);
					message.setMsg("所要分件的咨询客户状态已经变更！");
					return message;
				}

				if (consultPool != null && CommonUtil.isNotEmpty(consultPool.getDistributeOperationFlag())) {
					if (consultPool.getDistributeOperationFlag().equals("1")) {
						message.setSuccess(false);
						message.setMsg("所要分件的数据中有无分件权限的数据！");
						return message;
					}
				} else {
					message.setSuccess(false);
					message.setMsg("所要分件的咨询客操作权限没有取到！");
					return message;
				}
// if ((consultPool.getAcceptConsultState().equals("0") || consultPool.getAcceptConsultState().equals("1"))
// && CommonUtil.isNotEmpty(consultPool.getLoanOfficerName())) {
// message.setSuccess(false);
// message.setMsg("所要分件的咨询客户状态已经变更！");
// return message;
// }
			}
		}

		List<CustomerConsultPool> customerConsultPoolsForUpdate = new ArrayList<CustomerConsultPool>();
		Long getLoanOfficerId = customerConsultPool.getLoanOfficerId();
		String getLoanOfficerName = customerConsultPool.getLoanOfficerName();
		for (CustomerConsultPool consultPool : customerConsultPools) {
			consultPool.setSentDate(new Date());
			String name_zh = SpringSecurityUtils.getCurrentUser().getName_zh();
			if (CommonUtil.isEmpty(name_zh)) {
				name_zh = "";
			}
			Long getConsultPoolId = consultPool.getConsultPoolId();
			String getAcceptConsultState = consultPool.getAcceptConsultState();
			consultPool.setOperator(name_zh);
			consultPool.setOperateDate(new Date());
			consultPool.setAcceptConsultState("1");
			consultPool.setLoanOfficerId(getLoanOfficerId);
			consultPool.setLoanOfficerName(getLoanOfficerName);
			CustomerConsultPoolLog log = new CustomerConsultPoolLog();
			log.setOptModule("b00030");
			log.setCurrStatus(getAcceptConsultState);
			log.setNextStatus("1");
			log.setConnectionId(getConsultPoolId);
			log.setConnectionCetegory("0");// marketConsultStatus acceptConsultState
			log.setCurrConnectionDicSection("acceptConsultState");
			log.setNextConnectionDicSection("acceptConsultState");
			log.setOptBussinessContent("分件确认给的信贷员：" + getLoanOfficerName);
			customerConsultPoolLogs.add(log);
			// 加日志
// CunsultLog cunsultLog = new CunsultLog();
// cunsultLog.setLoanOfficerName(consultPool.getLoanOfficerName());
// cunsultLog.setConnectionId(consultPoolIds[i]);
// cunsultLog.setOptModule("b00020");

// // 受理咨询状态
// consultPool ccp = new consultPool();
// ccp.setConsultPoolId(consultPoolIds[i]);
// ccp = (consultPool) consultPoolDao.selectByPrimaryKey(ccp);
// // cunsultLog.setCurrApplicationStatus(ccp.getAcceptConsultState());
//
// if (ccp != null & CommonUtil.isNotEmpty(ccp.getAcceptConsultState())) {
// // “跟进中”、“申请进件”状态的咨询客户被分件给信贷员后维持原来的状态
// if (ccp.getAcceptConsultState().equals("0") || ccp.getAcceptConsultState().equals("1")) {
// consultPool.setAcceptConsultState(ccp.getAcceptConsultState());
// // cunsultLog.setNextApplicationStatus(ccp.getAcceptConsultState());
// } else {
// // 待处理
// consultPool.setAcceptConsultState("8");
// // cunsultLog.setNextApplicationStatus("8");
// }
// }
			// 对应咨询部分
			// cunsultLog.setConnectionCetegory("1");
			// cunsultLog.setConnectionDictionarySection("acceptConsultStatus");
// Map contentMap = new HashMap();
// contentMap.put("分配信贷员", consultPool.getLoanOfficerName());
			// operateLogService.insertOpt(cunsultLog, contentMap);
			customerConsultPoolsForUpdate.add(consultPool);

			// 插入权限，有可能是总部人分配所以不用SpringSecurityUtils.getCurrentUser().getUserId()
// Environment env = Environment.getInstance();
// try {
// Authorization authorization = env.getAuthorizationService();
// authorization.deleteAuth(CustomerConsultPool.class, consultPoolIds[i].intValue());
// authorization.createAuth(CustomerConsultPool.class, consultPoolIds[i].intValue(), customerConsultPool
// .getLoanOfficerId().intValue(), Integer.parseInt(Constants.SYSTEM_SIGN));
//
// } catch (Exception e) {
// e.printStackTrace();
// throw new BusinessException("更新权限异常");
// }
		}

		for (CustomerConsultPoolLog customerConsultPoolLog : customerConsultPoolLogs) {
			customerConsultPoolLogService.insertOpt(customerConsultPoolLog, null);
		}
		message = customerConsultPoolService
				.updateCustomerConsultPoolByListForDistributeOrders(customerConsultPoolsForUpdate);
// if (message.isSuccess()) {
// for (CustomerConsultPool consultPool : customerConsultPoolsForUpdate) {
// Environment env = Environment.getInstance();
// try {
// Authorization authorization = env.getAuthorizationService();
// authorization.deleteAuth(CustomerConsultPool.class, consultPool.getConsultPoolId().intValue());
// authorization.createAuth(CustomerConsultPool.class, consultPool.getConsultPoolId().intValue(),
// customerConsultPool.getLoanOfficerId().intValue(), Integer.parseInt(Constants.SYSTEM_SIGN));
//
// } catch (Exception e) {
// e.printStackTrace();
// throw new BusinessException("更新权限异常");
// }
// }
// }

		return message;
	}

	@Override
	public Message updateDistributeConfirm(CustomerConsultPool customerConsultPool, Long[] consultPoolIds) {
		Message message = new Message();
		message.setSuccess(true);
		List<CustomerConsultPoolLog> customerConsultPoolLogs = new ArrayList<CustomerConsultPoolLog>();
		Timestamp sentdate = new Timestamp(new Date().getTime());

		if (consultPoolIds == null || consultPoolIds.length < 1) {
			message.setSuccess(false);
			message.setMsg("分件数据笔数为空！");
		}
		List<CustomerConsultPool> customerConsultPools = customerConsultPoolService
				.queryCustomerConsultPoolListByConsultPoolIds(consultPoolIds);
		if (CommonUtil.isEmpty(customerConsultPools)) {
			message.setSuccess(false);
			message.setMsg("分件数据数据库查询为空！");
		}
		// 验证
		if (!message.isSuccess()) {
			return message;
		}

		// 先验证
		for (CustomerConsultPool consultPool : customerConsultPools) {
			//  只有 “待分件确认”状态的咨询客户才可以选择，其他状态均不允许被选中；
			if (consultPool != null && CommonUtil.isNotEmpty(consultPool.getAcceptConsultState())) {
				if (!consultPool.getAcceptConsultState().equals("1")) {
					message.setSuccess(false);
					message.setMsg("所要分件的咨询客户状态已经变更！");
					return message;
				}
				if (consultPool != null && CommonUtil.isNotEmpty(consultPool.getDistributeOperationFlag())) {
					if (consultPool.getDistributeOperationFlag().equals("1")) {
						message.setSuccess(false);
						message.setMsg("所要分件确认的数据中有无分件权限的数据！");
						return message;
					}
				} else {
					message.setSuccess(false);
					message.setMsg("所要分件的咨询客操作权限没有取到！");
					return message;
				}
// if ((consultPool.getAcceptConsultState().equals("0") || consultPool.getAcceptConsultState().equals("1"))
// && CommonUtil.isNotEmpty(consultPool.getLoanOfficerName())) {
// message.setSuccess(false);
// message.setMsg("所要分件的咨询客户状态已经变更！");
// return message;
// }
			}
		}

		List<CustomerConsultPool> customerConsultPoolsForUpdate = new ArrayList<CustomerConsultPool>();

		// 声明电话号码SET
		Set<String> phoneSet = new HashSet<String>();

		for (CustomerConsultPool consultPool : customerConsultPools) {
			consultPool.setSentDate(new Date());
			String name_zh = SpringSecurityUtils.getCurrentUser().getName_zh();
			if (CommonUtil.isEmpty(name_zh)) {
				name_zh = "";
			}
			Long getConsultPoolId = consultPool.getConsultPoolId();
			String getAcceptConsultState = consultPool.getAcceptConsultState();
			String getLoanOfficerName = consultPool.getLoanOfficerName();
			consultPool.setOperator(name_zh);
			consultPool.setOperateDate(new Date());
			consultPool.setAcceptConsultState("2");
			consultPool.setComponentOperationFlag("0");
			consultPool.setSentDate(sentdate);

			CustomerConsultPoolLog log = new CustomerConsultPoolLog();
			log.setOptModule("b00040");
			log.setCurrStatus(getAcceptConsultState);
			log.setNextStatus("2");
			log.setConnectionId(getConsultPoolId);
			log.setConnectionCetegory("0");// marketConsultStatus acceptConsultState
			log.setCurrConnectionDicSection("acceptConsultState");
			log.setNextConnectionDicSection("acceptConsultState");
			log.setOptBussinessContent("分件确认给的信贷员：" + getLoanOfficerName);
			customerConsultPoolLogs.add(log);

			// 加日志
// CunsultLog cunsultLog = new CunsultLog();
// cunsultLog.setLoanOfficerName(consultPool.getLoanOfficerName());
// cunsultLog.setConnectionId(consultPoolIds[i]);
// cunsultLog.setOptModule("b00020");

// // 受理咨询状态
// consultPool ccp = new consultPool();
// ccp.setConsultPoolId(consultPoolIds[i]);
// ccp = (consultPool) consultPoolDao.selectByPrimaryKey(ccp);
// // cunsultLog.setCurrApplicationStatus(ccp.getAcceptConsultState());
//
// if (ccp != null & CommonUtil.isNotEmpty(ccp.getAcceptConsultState())) {
// // “跟进中”、“申请进件”状态的咨询客户被分件给信贷员后维持原来的状态
// if (ccp.getAcceptConsultState().equals("0") || ccp.getAcceptConsultState().equals("1")) {
// consultPool.setAcceptConsultState(ccp.getAcceptConsultState());
// // cunsultLog.setNextApplicationStatus(ccp.getAcceptConsultState());
// } else {
// // 待处理
// consultPool.setAcceptConsultState("8");
// // cunsultLog.setNextApplicationStatus("8");
// }
// }
			// 对应咨询部分
			// cunsultLog.setConnectionCetegory("1");
			// cunsultLog.setConnectionDictionarySection("acceptConsultStatus");
// Map contentMap = new HashMap();
// contentMap.put("分配信贷员", consultPool.getLoanOfficerName());
			// operateLogService.insertOpt(cunsultLog, contentMap);
			customerConsultPoolsForUpdate.add(consultPool);

			phoneSet.add(consultPool.getMobilePhone());

			// 插入权限，有可能是总部人分配所以不用SpringSecurityUtils.getCurrentUser().getUserId()

		}
		message = customerConsultPoolService.updateComponentOperationFlagByPhone(phoneSet);
		message = customerConsultPoolService
				.updateCustomerConsultPoolByListForDistributeOrders(customerConsultPoolsForUpdate);

		if (CommonUtil.isNotEmpty(customerConsultPoolsForUpdate)) {

			customerConsultPoolService.insertDistributLog(customerConsultPoolsForUpdate, "1", "0");
		}
		for (CustomerConsultPoolLog customerConsultPoolLog : customerConsultPoolLogs) {
			customerConsultPoolLogService.insertOpt(customerConsultPoolLog, null);
		}
		if (message.isSuccess()) {
			for (CustomerConsultPool consultPool : customerConsultPoolsForUpdate) {
				Environment env = Environment.getInstance();
				try {
					Authorization authorization = env.getAuthorizationService();
					authorization.deleteAuth(CustomerConsultPool.class, consultPool.getConsultPoolId().intValue());
					authorization.createAuth(CustomerConsultPool.class, consultPool.getConsultPoolId().intValue(),
							consultPool.getLoanOfficerId().intValue(), Integer.parseInt(Constants.SYSTEM_SIGN));

				} catch (Exception e) {
					e.printStackTrace();
					throw new BusinessException("更新权限异常");
				}
			}
		}

		return message;
	}

	@Override
	public Message checkROLELocalFin() {
		// TODO Auto-generated method stub
		return null;
	}
}
