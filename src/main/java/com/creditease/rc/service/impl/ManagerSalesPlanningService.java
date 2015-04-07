package com.creditease.rc.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.creditease.core.security.SpringSecurityUtils;
import com.creditease.rc.dao.IManagerSalesPlanningDAO;
import com.creditease.rc.domain.ManagerSalesPlanning;
import com.creditease.rc.domain.Message;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.service.IManagerSalesPlanningService;
import com.creditease.rc.util.CommonUtil;
import com.creditease.rc.util.DepartmentUtil;
import com.creditease.rc.util.JsonUtil;
import com.creditease.rc.vo.DepartmentEntityVo;
import com.creditease.rc.vo.EasyUITree;

@Service
public class ManagerSalesPlanningService implements
		IManagerSalesPlanningService {

	@Resource
	private IManagerSalesPlanningDAO managerSalesPlanningDao;
	@Override
	public Long insert(ManagerSalesPlanning record) {
		return managerSalesPlanningDao.insert(record);
	}

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
	@Override
	public Pagination selectManagerSalesPlanningLoanSum(Pagination pagination,
			ManagerSalesPlanning managerSalesPlanning, String page, String rows,
			HttpServletRequest request, HttpSession session,String paramJsonMap,String year) {
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer stringBuffer = new StringBuffer();
		List<EasyUITree> departmentComboboxTree = getDepartmentComboboxTree(session);
		String forList = this.forList(departmentComboboxTree,stringBuffer);
		forList.lastIndexOf(",");
		String substring = forList.substring(0, forList.lastIndexOf(","));
		map.put("authority", substring);
		
		Map<String, String> pramMap = new HashMap<String, String>();
		if (!"".equals(paramJsonMap) && paramJsonMap != null) {
			Map<String, String> temp = JsonUtil.getObject(paramJsonMap);
			String year2 = temp.get("year");
			String areaDepartmentId = temp.get("areaDepartmentId");
			map.put("year", year2);
			map.put("areaDepartmentId", areaDepartmentId);
			
		}else{
			map.put("year",year);
		}
		return managerSalesPlanningDao.selectManagerSalesPlanningLoanSum(pagination, map);
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
	public Message savaOrUpdateInsert(List<ManagerSalesPlanning> managerSalesPlanningList,HttpSession session,String year,String areaDepartmentId) {
		Message message=new Message();
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer stringBuffer = new StringBuffer();
		List<EasyUITree> departmentComboboxTree = getDepartmentComboboxTree(session);
		String forList = this.forList(departmentComboboxTree,stringBuffer);
		forList.lastIndexOf(",");
		String substring = forList.substring(0, forList.lastIndexOf(","));
		map.put("authority", substring);
		map.put("year", year);
		map.put("areaDepartmentId", areaDepartmentId);
		
		//获得数据库中的数据
		List<ManagerSalesPlanning> listanagerSalesPlanning=managerSalesPlanningDao.selectManagerSales(map);
		//获取当前时间
		Date newDate=new Date();
		List<ManagerSalesPlanning> inserList=new ArrayList<ManagerSalesPlanning>();//批量更新
		List<ManagerSalesPlanning> updateList=new ArrayList<ManagerSalesPlanning>();//批量插入
		boolean flag=true;
		if(listanagerSalesPlanning.size()>0){
		for (ManagerSalesPlanning managerSalesPlanning : managerSalesPlanningList) {
			for (ManagerSalesPlanning managerSalesPlanning2 : listanagerSalesPlanning) {
				if(managerSalesPlanning.getComEmpId().toString().equals(managerSalesPlanning2.getComEmpId().toString())){
					updateList.add(managerSalesPlanning);
					flag=false;
					break;
				}else{
					flag=true;
				}
			}
			if(flag){
					managerSalesPlanning.setOperatorId(Long.valueOf(SpringSecurityUtils.getCurrentUser().getUserId()));
					managerSalesPlanning.setOperatorName(SpringSecurityUtils.getCurrentUser().getName_zh());
					managerSalesPlanning.setCreditDate(newDate);
					managerSalesPlanning.setYear(Long.valueOf(year));
					managerSalesPlanning.setType("0");
				inserList.add(managerSalesPlanning);
				message.setSuccess(true);
			}
		
		}
		}else{
			for (ManagerSalesPlanning managerSalesPlanningInsert : managerSalesPlanningList) {
				managerSalesPlanningInsert.setOperatorId(Long.valueOf(SpringSecurityUtils.getCurrentUser().getUserId()));
				managerSalesPlanningInsert.setOperatorName(SpringSecurityUtils.getCurrentUser().getName_zh());
				managerSalesPlanningInsert.setCreditDate(newDate);
				managerSalesPlanningInsert.setYear(Long.valueOf(year));
				managerSalesPlanningInsert.setType("0");
		}
			managerSalesPlanningDao.batchInsert(managerSalesPlanningList);
			message.setSuccess(true);
		}
		
		//判断要修改和要插入的值是否为空
		if(CommonUtil.isNotEmpty(inserList)){
			managerSalesPlanningDao.batchInsert(inserList);
			message.setSuccess(true);
		}
		if(CommonUtil.isNotEmpty(updateList)){
			managerSalesPlanningDao.batchUpdate(updateList);
			message.setSuccess(true);
		}
		return message;
	}

	@Override
	public Pagination selectManagerSalesPlanningContractMoney(
			Pagination pagination, ManagerSalesPlanning managerSalesPlanning, String page,
			String rows, HttpServletRequest request, HttpSession session,String paramJsonMap,String year) {
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer stringBuffer = new StringBuffer();
		List<EasyUITree> departmentComboboxTree = getDepartmentComboboxTree(session);
		String forList = this.forList(departmentComboboxTree,stringBuffer);
		forList.lastIndexOf(",");
		String substring = forList.substring(0, forList.lastIndexOf(","));
		map.put("authority", substring);
		
		Map<String, String> pramMap = new HashMap<String, String>();
		if (!"".equals(paramJsonMap) && paramJsonMap != null) {
			Map<String, String> temp = JsonUtil.getObject(paramJsonMap);
			String year2 = temp.get("year");
			String areaDepartmentId = temp.get("areaDepartmentId");
			map.put("year", year2);
			map.put("areaDepartmentId", areaDepartmentId);
			
		}else{
			map.put("year",year);
		}
		return managerSalesPlanningDao.selectManagerSalesPlanningContractMoney(pagination, map);
	}
	@Override
	public Message saveOrUpdateInsertContractMoney(List<ManagerSalesPlanning> managerSalesPlanningList,HttpSession session,String year,String areaDepartmentId) {
		Message message=new Message();
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer stringBuffer = new StringBuffer();
		List<EasyUITree> departmentComboboxTree = getDepartmentComboboxTree(session);
		String forList = this.forList(departmentComboboxTree,stringBuffer);
		forList.lastIndexOf(",");
		String substring = forList.substring(0, forList.lastIndexOf(","));
		map.put("authority", substring);
		map.put("year", year);
		map.put("areaDepartmentId", areaDepartmentId);
		
		//获得数据库中的数据
		List<ManagerSalesPlanning> listanagerSalesPlanning=managerSalesPlanningDao.selectManagerSalesContractMoney(map);
		//获取当前时间
		Date newDate=new Date();
		List<ManagerSalesPlanning> inserList=new ArrayList<ManagerSalesPlanning>();//批量更新
		List<ManagerSalesPlanning> updateList=new ArrayList<ManagerSalesPlanning>();//批量插入
		boolean flag=true;
		if(listanagerSalesPlanning.size()>0){
		for (ManagerSalesPlanning managerSalesPlanning : managerSalesPlanningList) {
			for (ManagerSalesPlanning managerSalesPlanning2 : listanagerSalesPlanning) {
				if(managerSalesPlanning.getComEmpId().toString().equals(managerSalesPlanning2.getComEmpId().toString())){
					updateList.add(managerSalesPlanning);
					flag=false;
					break;
				}else{
					flag=true;
				}
			}
			if(flag){
					managerSalesPlanning.setOperatorId(Long.valueOf(SpringSecurityUtils.getCurrentUser().getUserId()));
					managerSalesPlanning.setOperatorName(SpringSecurityUtils.getCurrentUser().getName_zh());
					managerSalesPlanning.setCreditDate(newDate);
					managerSalesPlanning.setYear(Long.valueOf(year));
					managerSalesPlanning.setType("1");
				inserList.add(managerSalesPlanning);
				message.setSuccess(true);
			}
		
		}
		}else{
			for (ManagerSalesPlanning managerSalesPlanningInsert : managerSalesPlanningList) {
				managerSalesPlanningInsert.setOperatorId(Long.valueOf(SpringSecurityUtils.getCurrentUser().getUserId()));
				managerSalesPlanningInsert.setOperatorName(SpringSecurityUtils.getCurrentUser().getName_zh());
				managerSalesPlanningInsert.setCreditDate(newDate);
				managerSalesPlanningInsert.setYear(Long.valueOf(year));
				managerSalesPlanningInsert.setType("1");
		}
			managerSalesPlanningDao.batchInsert(managerSalesPlanningList);
			message.setSuccess(true);
		}
		
		//判断要修改和要插入的值是否为空
		if(CommonUtil.isNotEmpty(inserList)){
			managerSalesPlanningDao.batchInsert(inserList);
			message.setSuccess(true);
		}
		if(CommonUtil.isNotEmpty(updateList)){
			managerSalesPlanningDao.batchUpdateContractMoney(updateList);
			message.setSuccess(true);
		}
		return message;
	}
}
