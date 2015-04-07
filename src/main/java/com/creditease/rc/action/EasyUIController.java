package com.creditease.rc.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.creditease.core.security.SpringSecurityUtils;
import com.creditease.rc.app.smp.EmployeeDTO;
import com.creditease.rc.domain.Message;
import com.creditease.rc.service.IEasyUIService;
import com.creditease.rc.util.CommonUtil;
import com.creditease.rc.util.DepartmentUtil;
import com.creditease.rc.util.SmpWSUtil;
import com.creditease.rc.vo.Combobox;
import com.creditease.rc.vo.DepartmentEntityVo;
import com.creditease.rc.vo.EasyUITree;

@Controller
@RequestMapping("easyUIController")
public class EasyUIController {

	@Resource
	private SmpWSUtil smpWSUtil;

	@Resource
	private IEasyUIService easyUIService;// 这个千万不能删在这里启动的时候调用这个类的初始化方法加载营业部tree

	@RequestMapping(value = "getDepartmentComboboxTree")
	public @ResponseBody
	List<EasyUITree> getDepartmentComboboxTree(HttpSession session) {

// Set<Entry<String, DepartmentEntityVo>> entries = DepartmentUtil.departmentMap.entrySet();
//
// for (Entry<String, DepartmentEntityVo> entry : entries) {
// String key = entry.getKey();
// DepartmentEntityVo departmentEntityVo = entry.getValue();
// String getDepartmentId = departmentEntityVo.getDepartmentId();
// String getDepartmentName = departmentEntityVo.getDepartmentName();
// List<String> getDepartmentIds = departmentEntityVo.getDepartmentIds();
// String a = "";
// if (CommonUtil.isNotEmpty(getDepartmentIds)) {
// a = getDepartmentIds.toString();
// }
// System.out.println("key=" + key + "  DepartmentId=" + getDepartmentId + "  DepartmentName"
// + getDepartmentName + "  children=" + a);
//
// }
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

	@RequestMapping(value = "constructCityDepartmentCombobox")
	public @ResponseBody
	List<Combobox> constructCityDepartmentCombobox(HttpSession session) {

		List<Combobox> comboboxs = new ArrayList<Combobox>();
		Integer getCityDepartmentId = SpringSecurityUtils.getCurrentUser().getCityDepartmentId();
		if (getCityDepartmentId == null) {
			// 总部人员
			DepartmentEntityVo departmentEntityVo = DepartmentUtil.departmentMap.get("");
			List<String> cityDepartmentIds = departmentEntityVo.getDepartmentIds();

			for (String cityDepartmentId : cityDepartmentIds) {
				DepartmentEntityVo departmentEntityVoCity = DepartmentUtil.departmentMap.get(cityDepartmentId);
				Combobox combobox = new Combobox();
				combobox.setCode(departmentEntityVoCity.getDepartmentId());
				combobox.setValue(departmentEntityVoCity.getDepartmentName());
				comboboxs.add(combobox);
			}
		} else {
			DepartmentEntityVo departmentEntityVoCity = DepartmentUtil.departmentMap
					.get(getCityDepartmentId.toString());
			Combobox combobox = new Combobox();
			combobox.setCode(departmentEntityVoCity.getDepartmentId());
			combobox.setValue(departmentEntityVoCity.getDepartmentName());
			comboboxs.add(combobox);
		}

		return comboboxs;
	}

	@RequestMapping(value = "constructAreaDepartmentCombobox")
	public @ResponseBody
	List<Combobox> constructAreaDepartmentCombobox(String cityDepartmentIdstr) {

		List<Combobox> comboboxs = new ArrayList<Combobox>();

		if ("".equals(cityDepartmentIdstr)) {
			return comboboxs;
		}

		String[] cityDepartmentArray = cityDepartmentIdstr.split(",");
		List<String> tempList = Arrays.asList(cityDepartmentArray);
		List<String> cityDepartmentIds = new ArrayList<String>();
		cityDepartmentIds.addAll(tempList);

		for (String cityDepartmentId : cityDepartmentIds) {
			DepartmentEntityVo departmentEntityVoCity = DepartmentUtil.departmentMap.get(cityDepartmentId);
			List<String> areaDepartmentIds = departmentEntityVoCity.getDepartmentIds();
			for (String areaDepartmentId : areaDepartmentIds) {
				DepartmentEntityVo areaDepartmentEntityVo = DepartmentUtil.departmentMap.get(areaDepartmentId);
				Combobox combobox = new Combobox();
				combobox.setCode(areaDepartmentEntityVo.getDepartmentId());
				combobox.setValue(areaDepartmentEntityVo.getDepartmentName());
				comboboxs.add(combobox);
			}
		}

		return comboboxs;
	}

	@SuppressWarnings("finally")
	@RequestMapping(value = "syncDepartmentMap")
	public @ResponseBody
	Message syncDepartmentMap() {
		Message message = new Message();
		message.setSuccess(false);
		message.setMsg("同步营业部MAP失败！");
		try {
			DepartmentUtil.departmentMap.clear();
			easyUIService.constructDepartmentMap();
			Set<Entry<String, DepartmentEntityVo>> entries = DepartmentUtil.departmentMap.entrySet();

			Iterator<Entry<String, DepartmentEntityVo>> iterator = entries.iterator();
			System.out.println("********************************************************************************");
			while (iterator.hasNext()) {
				Entry<String, DepartmentEntityVo> entry = iterator.next();
				String key = entry.getKey();
				DepartmentEntityVo departmentEntityVo = entry.getValue();
				String getDepartmentId = departmentEntityVo.getDepartmentId();
				String getDepartmentName = departmentEntityVo.getDepartmentName();
				List<String> getDepartmentIds = departmentEntityVo.getDepartmentIds();
				String a = "";
				if (CommonUtil.isNotEmpty(getDepartmentIds)) {
					a = getDepartmentIds.toString();
				}
				System.out.println("key=" + key + "  DepartmentId=" + getDepartmentId + "  DepartmentName"
						+ getDepartmentName + "  children=" + a);
			}
			System.out.println("********************************************************************************");
			message.setSuccess(true);
			message.setMsg("同步营业部MAP成功！");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			return message;
		}

	}

	@RequestMapping(value = "getcustomerManagerBydepartmentId")
	public @ResponseBody
	List<Combobox> getcustomerManagerBydepartmentId(String departmentId) {
		List<Combobox> comboboxs = new ArrayList<Combobox>();
		List<EmployeeDTO> employeeDTOs = smpWSUtil.getEmployeesListByRole_DepartmentId(departmentId,
				"ROLE_LOAN_OFFICER");

		for (EmployeeDTO employeeDTO : employeeDTOs) {
			Combobox combobox = new Combobox();
			Integer getComEmpId = employeeDTO.getComEmpId();
			String getName = employeeDTO.getName();
			combobox.setCode(getComEmpId.toString());
			combobox.setValue(getName);
			comboboxs.add(combobox);
		}
		return comboboxs;
	}

    /**
     * 分别获取（操作人-对应）大区、分中心、营业部
     *
     * @param request
     * @return 大区 或 分中心 或 营业部
     */
    @RequestMapping(value = "departmentTreeByType")
    public
    @ResponseBody
    List<EasyUITree> departmentTreeByType(HttpServletRequest request) {
        String wantType = StringUtils.isNumeric(request.getParameter("wantType")) ? StringUtils.isNotEmpty(request.getParameter("wantType")) ? request.getParameter("wantType") : "1" : "1";
        String role = StringUtils.isNotEmpty(request.getParameter("role")) ? request.getParameter("role") : "admin";
        List<EasyUITree> trees = this.getDepartmentComboboxTree(request.getSession());
        for (EasyUITree tree : trees) {
            for (EasyUITree tree1 : tree.getChildren()) {
                if (role.equals("admin")) {
                    if (Integer.valueOf(wantType) == 1) {
                        tree1.setChildren(new ArrayList<EasyUITree>());
                    } else if (Integer.valueOf(wantType) == 2) {
                        for (EasyUITree tree2 : tree1.getChildren()) {
                            tree2.setChildren(new ArrayList<EasyUITree>());
                        }
                    } else if (Integer.valueOf(wantType) == 3) {
                        for (EasyUITree tree2 : tree1.getChildren()) {
                            for (EasyUITree tree3 : tree2.getChildren()) {
                                tree3.setChildren(new ArrayList<EasyUITree>());
                            }
                        }
                    } else {
                        return trees;
                    }
                } else {
                    if (Integer.valueOf(wantType) == 2) {
                        tree1.setChildren(new ArrayList<EasyUITree>());
                    } else if (Integer.valueOf(wantType) == 3) {
                        for (EasyUITree tree2 : tree1.getChildren()) {
                            tree2.setChildren(new ArrayList<EasyUITree>());
                        }
                    }
                }
            }
        }
        return trees;
    }
}
