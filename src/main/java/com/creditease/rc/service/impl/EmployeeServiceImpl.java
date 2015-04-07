package com.creditease.rc.service.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import com.creditease.core.security.Authorization;
import com.creditease.core.security.SpringSecurityUtils;
import com.creditease.core.security.User;
import com.creditease.rc.app.smp.EmployeeDTO;
import com.creditease.rc.app.smp.EmployeeResult;
import com.creditease.rc.common.Constants;
import com.creditease.rc.dao.EmployeeChangeDAO;
import com.creditease.rc.dao.EmployeeDAO;
import com.creditease.rc.domain.Employee;
import com.creditease.rc.domain.EmployeeChange;
import com.creditease.rc.domain.Message;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.service.EmployeeService;
import com.creditease.rc.util.CommonUtil;
import com.creditease.rc.util.DepartmentUtil;
import com.creditease.rc.util.Environment;
import com.creditease.rc.util.JsonUtil;
import com.creditease.rc.util.SmpWSUtil;
import com.creditease.rc.vo.DepartmentEntityVo;
import com.creditease.rc.vo.EasyUITree;
@Service
public class EmployeeServiceImpl implements EmployeeService{

	Logger log = Logger.getLogger(EmployeeServiceImpl.class);
	@Autowired
	private EmployeeDAO employeeDAO;
	
	@Resource
	private SmpWSUtil smpWSUtil;
	@Autowired
	private EmployeeChangeDAO employeeChangeDAO;
	//*************************
	public List<EasyUITree> getDepartmentComboboxTree(HttpSession session) {
		Integer getAreaDepartmentId = SpringSecurityUtils.getCurrentUser().getAreaDepartmentId();
		List<EasyUITree> easyUITrees = new ArrayList<EasyUITree>();
		if (getAreaDepartmentId == null) {
			// 如果没有getAreaDepartmentId说明是总部人员，或者大区经理，或者风险经理
			Integer getCityDepartmentId = SpringSecurityUtils.getCurrentUser().getCityDepartmentId();
			if (getCityDepartmentId == null) {
				// 如果没有getCityDepartmentId说明是总部人员
				easyUITrees = constructEasyUITreeList("0");
			} else {
				// 如果有getCityDepartmentId说明是区域级别的经理或者风险经理
				easyUITrees = constructEasyUITreeList(getCityDepartmentId.toString());
			}
		} else {
			// 如果有getAreaDepartmentId说明是 营业部经理或者信贷员
			easyUITrees = constructEasyUITreeList(getAreaDepartmentId.toString());
		}
		return easyUITrees;
	}
	
	
	
	private List<EasyUITree> constructEasyUITreeList(String departmentId) {

		List<EasyUITree> easyUITrees = new ArrayList<EasyUITree>();
		EasyUITree easyUITree = new EasyUITree();
		departmentId = departmentId == null ? "0" : departmentId;
		DepartmentEntityVo departmentEntityVo = DepartmentUtil.departmentMap.get(departmentId);

		String getDepartmentId = departmentEntityVo.getDepartmentId();
		String getDepartmentName = departmentEntityVo.getDepartmentName();

		easyUITree.setId(getDepartmentId);
		easyUITree.setText(getDepartmentName);
		List<EasyUITree> childrenTrees = new ArrayList<EasyUITree>();
		easyUITree.setChildren(childrenTrees);

		List<String> list = departmentEntityVo.getDepartmentIds();

		if (CommonUtil.isNotEmpty(list)) {
			// 递归constructEasyUITreeList
			for (String str : list) {
				childrenTrees.addAll(constructEasyUITreeList(str));
			}
		}
		easyUITrees.add(easyUITree);
		return easyUITrees;

	}
	
	//************************
	
	/**
	 * 员工列表查询
	 */
	@Override
	public Pagination selectAllCollection(Pagination pagination,
			Employee employee, String page, String rows,
			HttpServletRequest request,HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
//		List<String> authList = SpringSecurityUtils.getAuthList(session);
//		String sqlsid = "";
		StringBuffer stringBuffer = new StringBuffer();
//		if (authList.size() > 0) {
//			for (String e : authList) {
//				if (sqlsid != null) {
//					sqlsid = sqlsid + "'" + e + "'" + ",";
//				} else {
//					sqlsid = "'" + e + "'" + ",";
//				}
//			}
//			sqlsid = sqlsid.substring(0, sqlsid.length() - 1);
//		}
		List<EasyUITree> departmentComboboxTree = getDepartmentComboboxTree(session);
		String forList = this.forList(departmentComboboxTree,stringBuffer);
		forList.lastIndexOf(",");
		String substring = forList.substring(0, forList.lastIndexOf(","));
		map.put("authority", substring);
		map.put("employee", employee);
		return employeeDAO.selectAllCollection(pagination, map);
	}
	
	private String forList(List<EasyUITree> list,StringBuffer stringBuffer){
		for (EasyUITree easyUITree : list) {
			String id = easyUITree.getId();
			stringBuffer.append(id+",");
			if(easyUITree.getChildren().size()>0){
				
				forList(easyUITree.getChildren(),stringBuffer);
			}
				
		}
		return stringBuffer.toString();
	}
	
	@Override
	public Message synchronizationData(String flag) throws Exception {
		Message message = new Message();
//		//增加权限
//		Environment env = Environment.getInstance();
//		Authorization authorization = null;
//		authorization = env.getAuthorizationService();
		List<Employee> employeeList = employeeDAO.selectEmployeeList();
		log.debug("************同步smp员工开始");
		EmployeeResult employeeBySystemSign = smpWSUtil.getEmployeeBySystemSign();
		log.debug(JsonUtil.getJackson(employeeBySystemSign));
		log.debug("************同步smp员工结束");
		List<EmployeeDTO> employeeDTOList = employeeBySystemSign.getEmployeeList();
		if("1".equals(flag)){
			
			if(CommonUtil.isEmpty(employeeList)){//第一次同步数据（本地还没有数据）
				List<Employee> list = new ArrayList<Employee>();
				//将数据加入到本地
				if(CommonUtil.isNotEmpty(employeeDTOList)){
					for (EmployeeDTO employeeDTO : employeeDTOList) {
						Employee employee = new Employee();
						employee.setComEmpId(employeeDTO.getComEmpId());
						employee.setComempno(employeeDTO.getComEmpNo().toString());
						employee.setAccount(employeeDTO.getAccount());
						employee.setName(employeeDTO.getName());
						employee.setActivestatus(employeeDTO.getActiveStatus());
						employee.setIsactive(employeeDTO.getIsActive());
						employee.setRolename(employeeDTO.getRoleName());
						employee.setRoledesc(employeeDTO.getRoleDesc());
						if(employeeDTO.getAreaDepartmentId()==null){
							employee.setAreadepartmentid("");
							employee.setAreadepartmentname("");
						}else{
							employee.setAreadepartmentid(employeeDTO.getAreaDepartmentId().toString());
							employee.setAreadepartmentname(employeeDTO.getAreaDepartmentName());
						}
						if(employeeDTO.getDepartmentId()==null){
							employee.setDepartmentname("");
							employee.setDepartmentid("");
							
						}else{
							
							employee.setDepartmentname(employeeDTO.getDepartmentName());
							employee.setDepartmentid(employeeDTO.getDepartmentId().toString());
						}
						if(employeeDTO.getCityDepartmentId()==null){
							employee.setCitydepartmentname("");
							employee.setCitydepartmentid("");
							
						}else{
							
							employee.setCitydepartmentname(employeeDTO.getCityDepartmentName());
							employee.setCitydepartmentid(employeeDTO.getCityDepartmentId().toString());
						}
						if(CommonUtil.isNotEmpty(employeeDTO.getEntryTime())){
							
							DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
							Date parse = formatter.parse(employeeDTO.getEntryTime());
							
							employee.setOpenauthoritydate(parse);
							employee.setEntrytime(parse);
						}
						employee.setEmailaddr(employeeDTO.getEmailAddr());
						list.add(employee);
					}
					//批量插入
					employeeDAO.bachInsert(list);
//				if(SpringSecurityUtils.getCurrentUser().getUserId()!=null){
//					//查询插入的主键id
//					List<Integer> employeeIds = employeeDAO.selectEmployeeId();
//					Long[] idLongs=null;
//					if(CommonUtil.isNotEmpty(employeeIds)){
//						idLongs = new Long[employeeIds.size()];
//					}
//					for (int j = 0; j < idLongs.length; j++) {
//						idLongs[j]=employeeIds.get(j).longValue();
//							//增加权限
//							authorization.createAuth(Employee.class, idLongs[j].intValue(),Integer.parseInt(SpringSecurityUtils.getCurrentUser().getUserId()), Integer.parseInt(Constants.SYSTEM_SIGN));
//					}
//				}
					message.setSuccess(true);
					message.setMsg("同步成功");
				}else{
					message.setSuccess(false);
					message.setMsg("同步失败");
				}
			}else{
				List<String> emplocal = new ArrayList<String>();//用于存储本地不同的数据
				List<String> empSmp = new ArrayList<String>();//用于存储smp不同的数据
				List<String> employeeSmp = new ArrayList<String>();//用于存储smp中的员工编号
				List<String> listlocal = new ArrayList<String>();//用于存储本地的员工编号
				Set<String> set = new HashSet<String>();
				if(CommonUtil.isNotEmpty(employeeDTOList)){
					//将同步过来的数据放入到list中
					for (EmployeeDTO employeeDTO : employeeDTOList) {
						String comempnoStr = employeeDTO.getComEmpId().toString();
						employeeSmp.add(comempnoStr);
					}
				}
				//将本地员工id号放入到list中
				for (Employee employee : employeeList) {
					String comempnoStr = employee.getComEmpId().toString();
					listlocal.add(comempnoStr);
				}
				//判断两个list是否有相同的和不同的(首先以本地为基础)说明本地有smp中没有，有离职的员工
				for (String string : listlocal) {
					if(!employeeSmp.contains(string)){//不同的
						emplocal.add(string);
					}else{
						set.add(string);
					}
				}
				//判断两个list是否有相同的和不同的(以smp基础)说明smp中有，本地没有，那就是有新加入的员工
				for (String string : employeeSmp) {
					if(!listlocal.contains(string)){
						empSmp.add(string);
					}else{
						set.add(string);
					}
				}
				//说明本地有smp中没有那么直接更新将本地更新成离职并停用
				if(CommonUtil.isNotEmpty(emplocal)){
					List<Employee> empUpdateHistory = new ArrayList<Employee>();
					
					for (String str : emplocal) {
						if(employeeChangeDAO.selectHistoryCount(Integer.parseInt(str))<=0){//如果已经有历史变更那么就不在变更
							Employee employee = new Employee();
							DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
							Date parse =  formatter.parse(formatter.format(new Date()));
							employee.setCloseauthoritydate(parse);
							employee.setComEmpId(Integer.parseInt(str));
							//将数据封装到list中用于批量更新
							empUpdateHistory.add(employee);
						}
					}
					//批量更新
					employeeDAO.updatebyComempno(empUpdateHistory);
				}
				//说明smp中有，本地没有，那就是有新加入的员工
				if(CommonUtil.isNotEmpty(empSmp)){
					List<Employee> smpMoreList = new ArrayList<Employee>();
					for (int i=0 ; i<employeeDTOList.size();i++) {
						for (int j = 0; j < empSmp.size(); j++) {
							if(empSmp.get(j).equals(employeeDTOList.get(i).getComEmpId().toString())){
								Employee employee = new Employee();
								employee.setComEmpId(employeeDTOList.get(i).getComEmpId());
								employee.setComempno(employeeDTOList.get(i).getComEmpNo().toString());
								employee.setAccount(employeeDTOList.get(i).getAccount());
								employee.setName(employeeDTOList.get(i).getName());
								employee.setActivestatus(employeeDTOList.get(i).getActiveStatus());
								employee.setIsactive(employeeDTOList.get(i).getIsActive());
								employee.setRolename(employeeDTOList.get(i).getRoleName());
								employee.setRoledesc(employeeDTOList.get(i).getRoleDesc());
								if(employeeDTOList.get(i).getDepartmentId()==null){
									employee.setDepartmentname("");
									employee.setDepartmentid("");
								}else{
									employee.setDepartmentname(employeeDTOList.get(i).getDepartmentName());
									employee.setDepartmentid(employeeDTOList.get(i).getDepartmentId().toString());
								}
								if(employeeDTOList.get(i).getAreaDepartmentId()==null){
									employee.setAreadepartmentname("");
									employee.setAreadepartmentid("");
								}else{
									employee.setAreadepartmentname(employeeDTOList.get(i).getAreaDepartmentName());
									employee.setAreadepartmentid(employeeDTOList.get(i).getAreaDepartmentId().toString());
								}
								if(employeeDTOList.get(i).getCityDepartmentId()==null){
									employee.setCitydepartmentname("");
									employee.setCitydepartmentid("");
								}else{
									employee.setCitydepartmentname(employeeDTOList.get(i).getCityDepartmentName());
									employee.setCitydepartmentid(employeeDTOList.get(i).getCityDepartmentId().toString());
								}
								if(CommonUtil.isNotEmpty(employeeDTOList.get(i).getEntryTime())){
									DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
									Date parse=formatter.parse(employeeDTOList.get(i).getEntryTime());
									employee.setEntrytime(parse);
									employee.setOpenauthoritydate(parse);
								}
								employee.setEmailaddr(employeeDTOList.get(i).getEmailAddr());
								
								smpMoreList.add(employee);
								
							}
						}
					}
					//批量插入
					employeeDAO.bachInsert(smpMoreList);
					/*//插入权限
				//查询插入的主键id
				List<Integer> employeeIds = employeeDAO.selectEmployeeId();
				Long[] idLongs=null;
				if(CommonUtil.isNotEmpty(employeeIds)){
					idLongs = new Long[employeeIds.size()];
					
				}
				for (int j = 0; j < idLongs.length; j++) {
					idLongs[j]=employeeIds.get(j).longValue();
					//增加权限
					authorization.createAuth(Employee.class, idLongs[j].intValue(),Integer.parseInt(SpringSecurityUtils.getCurrentUser().getUserId()), Integer.parseInt(Constants.SYSTEM_SIGN));
				}*/
				}
				//相同的更新本地
				//首先去本地相同的放入到list中
				if(CommonUtil.isNotEmpty(set)){
					List<Employee> sameEmployeeLocal = new ArrayList<Employee>();
					List<EmployeeDTO> sameEmployeeSmp = new ArrayList<EmployeeDTO>();
					for(String str : set){
						for(int i=0;i<employeeList.size();i++){
							if(str.equals(employeeList.get(i).getComEmpId().toString())){
								sameEmployeeLocal.add(employeeList.get(i));
							}
						}
						for (int i=0 ; i<employeeDTOList.size();i++) {
							if(str.equals(employeeDTOList.get(i).getComEmpId().toString())){
								sameEmployeeSmp.add(employeeDTOList.get(i));
							}
						}
					}
					
					//将本地的数据更新成smp中的数据
					for (int i = 0; i < sameEmployeeLocal.size(); i++) {
						for (int j = 0; j < sameEmployeeSmp.size(); j++) {
							if(sameEmployeeLocal.get(i).getComEmpId().toString().equals(sameEmployeeSmp.get(j).getComEmpId().toString())){
								if(!sameEmployeeSmp.get(j).getIsActive().equals("1")){
									
									sameEmployeeLocal.get(i).setComEmpId(sameEmployeeSmp.get(j).getComEmpId());
									sameEmployeeLocal.get(i).setComempno(sameEmployeeSmp.get(j).getComEmpNo().toString());
									sameEmployeeLocal.get(i).setAccount(sameEmployeeSmp.get(j).getAccount());
									sameEmployeeLocal.get(i).setName(sameEmployeeSmp.get(j).getName());
									sameEmployeeLocal.get(i).setActivestatus(sameEmployeeSmp.get(j).getActiveStatus());
									sameEmployeeLocal.get(i).setRolename(sameEmployeeSmp.get(j).getRoleName());
									sameEmployeeLocal.get(i).setRoledesc(sameEmployeeSmp.get(j).getRoleDesc());
									if(sameEmployeeSmp.get(j).getDepartmentId()==null){
										
										sameEmployeeLocal.get(i).setDepartmentname("");
										sameEmployeeLocal.get(i).setDepartmentid("");
									}else{
										sameEmployeeLocal.get(i).setDepartmentname(sameEmployeeSmp.get(j).getDepartmentName());
										sameEmployeeLocal.get(i).setDepartmentid(sameEmployeeSmp.get(j).getDepartmentId().toString());
									}
									if(sameEmployeeSmp.get(j).getAreaDepartmentId()==null){
										
										sameEmployeeLocal.get(i).setAreadepartmentname("");
										sameEmployeeLocal.get(i).setAreadepartmentid("");
									}else{
										sameEmployeeLocal.get(i).setAreadepartmentname(sameEmployeeSmp.get(j).getAreaDepartmentName());
										sameEmployeeLocal.get(i).setAreadepartmentid(sameEmployeeSmp.get(j).getAreaDepartmentId().toString());
									}
									if(sameEmployeeSmp.get(j).getCityDepartmentId()==null){
										
										sameEmployeeLocal.get(i).setCitydepartmentname("");
										sameEmployeeLocal.get(i).setCitydepartmentid("");
									}else{
										sameEmployeeLocal.get(i).setCitydepartmentname(sameEmployeeSmp.get(j).getCityDepartmentName());
										sameEmployeeLocal.get(i).setCitydepartmentid(sameEmployeeSmp.get(j).getCityDepartmentId().toString());
									}
									sameEmployeeLocal.get(i).setIsactive(sameEmployeeSmp.get(j).getIsActive());
									sameEmployeeLocal.get(i).setEmailaddr(sameEmployeeSmp.get(j).getEmailAddr());
									DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
									Date parse =  formatter.parse(formatter.format(new Date()));
									sameEmployeeLocal.get(i).setCloseauthoritydate(parse);
									employeeDAO.updateEmployee(sameEmployeeLocal.get(i));
									break;
								}else{
									sameEmployeeLocal.get(i).setComEmpId(sameEmployeeSmp.get(j).getComEmpId());
									sameEmployeeLocal.get(i).setComempno(sameEmployeeSmp.get(j).getComEmpNo().toString());
									sameEmployeeLocal.get(i).setAccount(sameEmployeeSmp.get(j).getAccount());
									sameEmployeeLocal.get(i).setName(sameEmployeeSmp.get(j).getName());
									sameEmployeeLocal.get(i).setActivestatus(sameEmployeeSmp.get(j).getActiveStatus());
									sameEmployeeLocal.get(i).setRolename(sameEmployeeSmp.get(j).getRoleName());
									sameEmployeeLocal.get(i).setRoledesc(sameEmployeeSmp.get(j).getRoleDesc());
									if(sameEmployeeSmp.get(j).getDepartmentId()==null){
										
										sameEmployeeLocal.get(i).setDepartmentname("");
										sameEmployeeLocal.get(i).setDepartmentid("");
									}else{
										sameEmployeeLocal.get(i).setDepartmentname(sameEmployeeSmp.get(j).getDepartmentName());
										sameEmployeeLocal.get(i).setDepartmentid(sameEmployeeSmp.get(j).getDepartmentId().toString());
									}
									if(sameEmployeeSmp.get(j).getAreaDepartmentId()==null){
										
										sameEmployeeLocal.get(i).setAreadepartmentname("");
										sameEmployeeLocal.get(i).setAreadepartmentid("");
									}else{
										sameEmployeeLocal.get(i).setAreadepartmentname(sameEmployeeSmp.get(j).getAreaDepartmentName());
										sameEmployeeLocal.get(i).setAreadepartmentid(sameEmployeeSmp.get(j).getAreaDepartmentId().toString());
									}
									if(sameEmployeeSmp.get(j).getCityDepartmentId()==null){
										
										sameEmployeeLocal.get(i).setCitydepartmentname("");
										sameEmployeeLocal.get(i).setCitydepartmentid("");
									}else{
										sameEmployeeLocal.get(i).setCitydepartmentname(sameEmployeeSmp.get(j).getCityDepartmentName());
										sameEmployeeLocal.get(i).setCitydepartmentid(sameEmployeeSmp.get(j).getCityDepartmentId().toString());
									}
									sameEmployeeLocal.get(i).setIsactive(sameEmployeeSmp.get(j).getIsActive());
									sameEmployeeLocal.get(i).setEmailaddr(sameEmployeeSmp.get(j).getEmailAddr());
									employeeDAO.updateEmployee(sameEmployeeLocal.get(i));
									break;
								}
							}
							
						}
					}
				}
			}
			message.setSuccess(true);
			message.setMsg("同步成功");
		}else{
			if(CommonUtil.isEmpty(employeeList)){//第一次同步数据（本地还没有数据）
				List<Employee> list = new ArrayList<Employee>();
				//将数据加入到本地
				if(CommonUtil.isNotEmpty(employeeDTOList)){
					for (EmployeeDTO employeeDTO : employeeDTOList) {
						Employee employee = new Employee();
						employee.setComEmpId(employeeDTO.getComEmpId());
						employee.setComempno(employeeDTO.getComEmpNo().toString());
						employee.setAccount(employeeDTO.getAccount());
						employee.setName(employeeDTO.getName());
						employee.setActivestatus(employeeDTO.getActiveStatus());
						employee.setIsactive(employeeDTO.getIsActive());
						employee.setRolename(employeeDTO.getRoleName());
						employee.setRoledesc(employeeDTO.getRoleDesc());
						if(employeeDTO.getAreaDepartmentId()==null){
							employee.setAreadepartmentid("");
							employee.setAreadepartmentname("");
						}else{
							employee.setAreadepartmentid(employeeDTO.getAreaDepartmentId().toString());
							employee.setAreadepartmentname(employeeDTO.getAreaDepartmentName());
						}
						if(employeeDTO.getDepartmentId()==null){
							employee.setDepartmentname("");
							employee.setDepartmentid("");
							
						}else{
							
							employee.setDepartmentname(employeeDTO.getDepartmentName());
							employee.setDepartmentid(employeeDTO.getDepartmentId().toString());
						}
						if(employeeDTO.getCityDepartmentId()==null){
							employee.setCitydepartmentname("");
							employee.setCitydepartmentid("");
							
						}else{
							
							employee.setCitydepartmentname(employeeDTO.getCityDepartmentName());
							employee.setCitydepartmentid(employeeDTO.getCityDepartmentId().toString());
						}
						if(CommonUtil.isNotEmpty(employeeDTO.getEntryTime())){
							
							DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
							Date parse = formatter.parse(employeeDTO.getEntryTime());
							
							employee.setOpenauthoritydate(parse);
							employee.setEntrytime(parse);
						}
						employee.setEmailaddr(employeeDTO.getEmailAddr());
						list.add(employee);
					}
					//批量插入
					employeeDAO.bachInsert(list);
					message.setSuccess(true);
					message.setMsg("同步成功");
				}else{
					message.setSuccess(false);
					message.setMsg("同步失败");
				}
			}else{//*******************用于跑批
				List<String> emplocal = new ArrayList<String>();//用于存储本地不同的数据
				List<String> empSmp = new ArrayList<String>();//用于存储smp不同的数据
				List<String> employeeSmp = new ArrayList<String>();//用于存储smp中的员工编号
				List<String> listlocal = new ArrayList<String>();//用于存储本地的员工编号
				Set<String> set = new HashSet<String>();
				if(CommonUtil.isNotEmpty(employeeDTOList)){
					//将同步过来的数据放入到list中
					for (EmployeeDTO employeeDTO : employeeDTOList) {
						String comempnoStr = employeeDTO.getComEmpId().toString();
						employeeSmp.add(comempnoStr);
					}
				}
				//将本地员工编号放入到list中
				for (Employee employee : employeeList) {
					String comempnoStr = employee.getComEmpId().toString();
					listlocal.add(comempnoStr);
				}
				//判断两个list是否有相同的和不同的(首先以本地为基础)说明本地有smp中没有，有离职的员工
				for (String string : listlocal) {
					if(!employeeSmp.contains(string)){//不同的
						emplocal.add(string);
					}else{
						set.add(string);
					}
				}
				//判断两个list是否有相同的和不同的(以smp基础)说明smp中有，本地没有，那就是有新加入的员工
				for (String string : employeeSmp) {
					if(!listlocal.contains(string)){
						empSmp.add(string);
					}else{
						set.add(string);
					}
				}
				//说明本地有smp中没有那么直接更新将本地更新成离职并停用
				if(CommonUtil.isNotEmpty(emplocal)){
					List<Employee> empUpdateHistory = new ArrayList<Employee>();
					
					for (String str : emplocal) {
						if(employeeChangeDAO.selectHistoryCount(Integer.parseInt(str))<=0){//如果已经有历史变更那么就不在变更，没有记录变更
							Employee employee = new Employee();
							Calendar calendar = Calendar.getInstance();
							calendar.setTime(new Date());
							calendar.add(Calendar.DAY_OF_MONTH, -1);
							Date beforeTime = calendar.getTime();
							DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
							Date parse =  formatter.parse(formatter.format(beforeTime));
							employee.setCloseauthoritydate(parse);
							employee.setComEmpId(Integer.parseInt(str));
							//将数据封装到list中用于批量更新
							empUpdateHistory.add(employee);
						}
					}
					//批量更新
					employeeDAO.updatebyComempno(empUpdateHistory);
				}
				//说明smp中有，本地没有，那就是有新加入的员工
				if(CommonUtil.isNotEmpty(empSmp)){
					List<Employee> smpMoreList = new ArrayList<Employee>();
					for (int i=0 ; i<employeeDTOList.size();i++) {
						for (int j = 0; j < empSmp.size(); j++) {
							if(empSmp.get(j).equals(employeeDTOList.get(i).getComEmpId().toString())){
								Employee employee = new Employee();
								employee.setComEmpId(employeeDTOList.get(i).getComEmpId());
								employee.setComempno(employeeDTOList.get(i).getComEmpNo().toString());
								employee.setAccount(employeeDTOList.get(i).getAccount());
								employee.setName(employeeDTOList.get(i).getName());
								employee.setActivestatus(employeeDTOList.get(i).getActiveStatus());
								employee.setIsactive(employeeDTOList.get(i).getIsActive());
								employee.setRolename(employeeDTOList.get(i).getRoleName());
								employee.setRoledesc(employeeDTOList.get(i).getRoleDesc());
								if(employeeDTOList.get(i).getDepartmentId()==null){
									employee.setDepartmentname("");
									employee.setDepartmentid("");
								}else{
									employee.setDepartmentname(employeeDTOList.get(i).getDepartmentName());
									employee.setDepartmentid(employeeDTOList.get(i).getDepartmentId().toString());
								}
								if(employeeDTOList.get(i).getAreaDepartmentId()==null){
									employee.setAreadepartmentname("");
									employee.setAreadepartmentid("");
								}else{
									employee.setAreadepartmentname(employeeDTOList.get(i).getAreaDepartmentName());
									employee.setAreadepartmentid(employeeDTOList.get(i).getAreaDepartmentId().toString());
								}
								if(employeeDTOList.get(i).getCityDepartmentId()==null){
									employee.setCitydepartmentname("");
									employee.setCitydepartmentid("");
								}else{
									employee.setCitydepartmentname(employeeDTOList.get(i).getCityDepartmentName());
									employee.setCitydepartmentid(employeeDTOList.get(i).getCityDepartmentId().toString());
								}
								if(CommonUtil.isNotEmpty(employeeDTOList.get(i).getEntryTime())){
									DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
									Date parse=formatter.parse(employeeDTOList.get(i).getEntryTime());
									employee.setEntrytime(parse);
									employee.setOpenauthoritydate(parse);
								}
								employee.setEmailaddr(employeeDTOList.get(i).getEmailAddr());
								
								smpMoreList.add(employee);
								
							}
						}
					}
					//批量插入
					employeeDAO.bachInsert(smpMoreList);
				}
				//相同的更新本地
				//首先去本地相同的放入到list中
				if(CommonUtil.isNotEmpty(set)){
					List<Employee> sameEmployeeLocal = new ArrayList<Employee>();
					List<EmployeeDTO> sameEmployeeSmp = new ArrayList<EmployeeDTO>();
					for(String str : set){
						for(int i=0;i<employeeList.size();i++){
							if(str.equals(employeeList.get(i).getComEmpId().toString())){
								sameEmployeeLocal.add(employeeList.get(i));
							}
						}
						for (int i=0 ; i<employeeDTOList.size();i++) {
							if(str.equals(employeeDTOList.get(i).getComEmpId().toString())){
								sameEmployeeSmp.add(employeeDTOList.get(i));
							}
						}
					}
					
					//将本地的数据更新成smp中的数据
					for (int i = 0; i < sameEmployeeLocal.size(); i++) {
						for (int j = 0; j < sameEmployeeSmp.size(); j++) {
							if(sameEmployeeLocal.get(i).getComEmpId().toString().equals(sameEmployeeSmp.get(j).getComEmpId().toString())){
								if(!"1".equals(sameEmployeeSmp.get(j).getIsActive())){
									
									sameEmployeeLocal.get(i).setComEmpId(sameEmployeeSmp.get(j).getComEmpId());
									sameEmployeeLocal.get(i).setComempno(sameEmployeeSmp.get(j).getComEmpNo().toString());
									sameEmployeeLocal.get(i).setAccount(sameEmployeeSmp.get(j).getAccount());
									sameEmployeeLocal.get(i).setName(sameEmployeeSmp.get(j).getName());
									sameEmployeeLocal.get(i).setActivestatus(sameEmployeeSmp.get(j).getActiveStatus());
									sameEmployeeLocal.get(i).setRolename(sameEmployeeSmp.get(j).getRoleName());
									sameEmployeeLocal.get(i).setRoledesc(sameEmployeeSmp.get(j).getRoleDesc());
									if(sameEmployeeSmp.get(j).getDepartmentId()==null){
										
										sameEmployeeLocal.get(i).setDepartmentname("");
										sameEmployeeLocal.get(i).setDepartmentid("");
									}else{
										sameEmployeeLocal.get(i).setDepartmentname(sameEmployeeSmp.get(j).getDepartmentName());
										sameEmployeeLocal.get(i).setDepartmentid(sameEmployeeSmp.get(j).getDepartmentId().toString());
									}
									if(sameEmployeeSmp.get(j).getAreaDepartmentId()==null){
										
										sameEmployeeLocal.get(i).setAreadepartmentname("");
										sameEmployeeLocal.get(i).setAreadepartmentid("");
									}else{
										sameEmployeeLocal.get(i).setAreadepartmentname(sameEmployeeSmp.get(j).getAreaDepartmentName());
										sameEmployeeLocal.get(i).setAreadepartmentid(sameEmployeeSmp.get(j).getAreaDepartmentId().toString());
									}
									if(sameEmployeeSmp.get(j).getCityDepartmentId()==null){
										
										sameEmployeeLocal.get(i).setCitydepartmentname("");
										sameEmployeeLocal.get(i).setCitydepartmentid("");
									}else{
										sameEmployeeLocal.get(i).setCitydepartmentname(sameEmployeeSmp.get(j).getCityDepartmentName());
										sameEmployeeLocal.get(i).setCitydepartmentid(sameEmployeeSmp.get(j).getCityDepartmentId().toString());
									}
									sameEmployeeLocal.get(i).setIsactive(sameEmployeeSmp.get(j).getIsActive());
									sameEmployeeLocal.get(i).setEmailaddr(sameEmployeeSmp.get(j).getEmailAddr());
									DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
									Date parse =  formatter.parse(formatter.format(new Date()));
									sameEmployeeLocal.get(i).setCloseauthoritydate(parse);
									employeeDAO.updateEmployee(sameEmployeeLocal.get(i));
									break;
								}else{
									sameEmployeeLocal.get(i).setComEmpId(sameEmployeeSmp.get(j).getComEmpId());
									sameEmployeeLocal.get(i).setComempno(sameEmployeeSmp.get(j).getComEmpNo().toString());
									sameEmployeeLocal.get(i).setAccount(sameEmployeeSmp.get(j).getAccount());
									sameEmployeeLocal.get(i).setName(sameEmployeeSmp.get(j).getName());
									sameEmployeeLocal.get(i).setActivestatus(sameEmployeeSmp.get(j).getActiveStatus());
									sameEmployeeLocal.get(i).setRolename(sameEmployeeSmp.get(j).getRoleName());
									sameEmployeeLocal.get(i).setRoledesc(sameEmployeeSmp.get(j).getRoleDesc());
									if(sameEmployeeSmp.get(j).getDepartmentId()==null){
										
										sameEmployeeLocal.get(i).setDepartmentname("");
										sameEmployeeLocal.get(i).setDepartmentid("");
									}else{
										sameEmployeeLocal.get(i).setDepartmentname(sameEmployeeSmp.get(j).getDepartmentName());
										sameEmployeeLocal.get(i).setDepartmentid(sameEmployeeSmp.get(j).getDepartmentId().toString());
									}
									if(sameEmployeeSmp.get(j).getAreaDepartmentId()==null){
										
										sameEmployeeLocal.get(i).setAreadepartmentname("");
										sameEmployeeLocal.get(i).setAreadepartmentid("");
									}else{
										sameEmployeeLocal.get(i).setAreadepartmentname(sameEmployeeSmp.get(j).getAreaDepartmentName());
										sameEmployeeLocal.get(i).setAreadepartmentid(sameEmployeeSmp.get(j).getAreaDepartmentId().toString());
									}
									if(sameEmployeeSmp.get(j).getCityDepartmentId()==null){
										
										sameEmployeeLocal.get(i).setCitydepartmentname("");
										sameEmployeeLocal.get(i).setCitydepartmentid("");
									}else{
										sameEmployeeLocal.get(i).setCitydepartmentname(sameEmployeeSmp.get(j).getCityDepartmentName());
										sameEmployeeLocal.get(i).setCitydepartmentid(sameEmployeeSmp.get(j).getCityDepartmentId().toString());
									}
									sameEmployeeLocal.get(i).setIsactive(sameEmployeeSmp.get(j).getIsActive());
									sameEmployeeLocal.get(i).setEmailaddr(sameEmployeeSmp.get(j).getEmailAddr());
									employeeDAO.updateEmployee(sameEmployeeLocal.get(i));
									break;
								}
							}
							
						}
					}
				}
			}
			message.setSuccess(true);
			message.setMsg("同步成功");
		}
		return message;
	}
	@Override
	public Pagination downloadExcel(Pagination pagination, Employee employee,
			String page, String rows, HttpServletRequest request,
			HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer stringBuffer = new StringBuffer();
		List<EasyUITree> departmentComboboxTree = getDepartmentComboboxTree(session);
		String forList = this.forList(departmentComboboxTree,stringBuffer);
		forList.lastIndexOf(",");
		String substring = forList.substring(0, forList.lastIndexOf(","));
		map.put("authority", substring);
		map.put("employee", employee);
		return employeeDAO.downloadExcel(pagination, map);
	}
	
	
	//每天晚上12点定时更新smp员工信息
	@Override
	public void dispatcher() throws Exception{
		String flag="2";
		log.debug("************同步smp员工开始");
		Message synchronizationData = this.synchronizationData(flag);
		log.debug(JsonUtil.getJackson(this.synchronizationData(flag)));
		
		log.debug("************同步smp员工结束");
		
	}

}
