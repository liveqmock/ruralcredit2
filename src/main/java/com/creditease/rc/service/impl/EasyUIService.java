package com.creditease.rc.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.creditease.core.security.SpringSecurityUtils;
import com.creditease.rc.common.DepartmentEntity;
import com.creditease.rc.service.IEasyUIService;
import com.creditease.rc.util.CommonUtil;
import com.creditease.rc.util.DepartmentUtil;
import com.creditease.rc.util.SmpWSUtil;
import com.creditease.rc.vo.DepartmentEntityVo;

@Service
public class EasyUIService implements IEasyUIService {
	@Resource
	private SmpWSUtil smpWSUtil;

    @PostConstruct
    public void constructDepartmentMap() {
        System.out.println("启动时调用smp构建营业部MAP");
        /*定制树形菜单*/
        customizeTree();
        /*输出到控制台*/
        System.out.println("==============================================================================");
        Set<Entry<String, DepartmentEntityVo>> entries = DepartmentUtil.departmentMap.entrySet();
        for (Entry<String, DepartmentEntityVo> entry : entries) {
            String key = entry.getKey();
            DepartmentEntityVo departmentEntityVo = entry.getValue();
            String getDepartmentId = departmentEntityVo.getDepartmentId();
            String getDepartmentName = departmentEntityVo.getDepartmentName();
            List<String> getDepartmentIds = departmentEntityVo.getDepartmentIds();
            String a = "";
            if (CommonUtil.isNotEmpty(getDepartmentIds)) {
                a = getDepartmentIds.toString();
            }
            System.out.println("key=" + key + "  DepartmentId=" + getDepartmentId + "  DepartmentName" + getDepartmentName + "  children=" + a);

        }
        System.out.println("==============================================================================");
        System.out.println("营业部MAP构建完毕");
    }

	@Override
	public Map<String, DepartmentEntityVo> getDepartmentMapByAuthority() {
		// TODO Auto-generated method stub
		Integer getAreaDepartmentId = SpringSecurityUtils.getCurrentUser().getAreaDepartmentId();
		Map<String, DepartmentEntityVo> departmentMap = new HashMap<String, DepartmentEntityVo>();
		if (getAreaDepartmentId == null) {
			// 如果没有getAreaDepartmentId说明是总部人员，或者大区经理，或者风险经理
			Integer getCityDepartmentId = SpringSecurityUtils.getCurrentUser().getCityDepartmentId();
			if (getCityDepartmentId == null) {
				// 如果没有getCityDepartmentId说明是总部人员
				departmentMap = constructMap("0");
			} else {
				// 如果有getCityDepartmentId说明是区域级别的经理或者风险经理
				departmentMap = constructMap(getCityDepartmentId.toString());
			}
		} else {
			// 如果有getAreaDepartmentId说明是 营业部经理或者信贷员
			departmentMap = constructMap(getAreaDepartmentId.toString());
		}
		return departmentMap;
	}

	private Map<String, DepartmentEntityVo> constructMap(String departmentId) {
		Map<String, DepartmentEntityVo> map = new HashMap<String, DepartmentEntityVo>();
		DepartmentEntityVo departmentEntityVo = DepartmentUtil.departmentMap.get(departmentId);
		map.put(departmentId, departmentEntityVo);
		List<String> getDepartmentIds = departmentEntityVo.getDepartmentIds();
		if (CommonUtil.isNotEmpty(getDepartmentIds)) {
			for (String getDepartmentId : getDepartmentIds) {
				Map<String, DepartmentEntityVo> temap = constructMap(getDepartmentId);
				map.putAll(temap);
			}

		}
		return map;
	}

    /*定制树形菜单
    * 1 新增大区 （注：在 smp 配置此部门时，需配置为城市一级部门）
    * 2 涉及到办公室部门的合并
    * 3 返回 所有大区 所有办公室 所有分中心 所有营业部
    */
    private void customizeTree() {
        String departmentId;
        String departmentName;
        DepartmentEntityVo entityVo;
        List<DepartmentEntity> departments;

        List<DepartmentEntity> regions = new ArrayList<DepartmentEntity>();
        List<String> regionIds = new ArrayList<String>();
        List<DepartmentEntity> offices = new ArrayList<DepartmentEntity>();
        List<String> officeIds = new ArrayList<String>();
        List<DepartmentEntity> subs = new ArrayList<DepartmentEntity>();
        List<String> subIds = new ArrayList<String>();
        List<DepartmentEntity> departs = new ArrayList<DepartmentEntity>();
        List<String> departIds = new ArrayList<String>();

        /*全部*/
        entityVo = new DepartmentEntityVo();
        entityVo.setDepartmentId("0");
        entityVo.setDepartmentName("全部");
        List<String> rootList = new ArrayList<String>();
        entityVo.setDepartmentIds(rootList);
        DepartmentUtil.departmentMap.put("0", entityVo);

        /*获取所有城市一级部门*/
        Map<String, String> allCityDepartMap = smpWSUtil.getAllCityDepart();
        Set<Entry<String, String>> allCityDepartmentSet = allCityDepartMap.entrySet();
        for (Entry<String, String> cityDepartment : allCityDepartmentSet) {
            departmentId = cityDepartment.getKey();
            departmentName = cityDepartment.getValue();

            /*大区*/
            if (departmentName.endsWith("区")) {
                rootList.add(departmentId);
                entityVo = new DepartmentEntityVo();
                entityVo.setDepartmentId(departmentId);
                entityVo.setDepartmentName(departmentName);
                List<String> subCenterDepartmentIdList = new ArrayList<String>();
                entityVo.setDepartmentIds(subCenterDepartmentIdList);
                DepartmentUtil.departmentMap.put(departmentId, entityVo);
                regions.add(entityVo);
                regionIds.add(departmentId);

                /*办公室（需求规则：此菜单不显示）*/
                departments = smpWSUtil.getSubordinateDepartListByDepartId(departmentId);
                for (DepartmentEntity entity : departments) {
                    departmentId = entity.getDepartmentId();
                    departmentName = entity.getDepartmentName();
                    entityVo = new DepartmentEntityVo();
                    entityVo.setDepartmentId(departmentId);
                    entityVo.setDepartmentName(departmentName);
                    offices.add(entityVo);
                    officeIds.add(departmentId);

                    /*分中心*/
                    departments = smpWSUtil.getSubordinateDepartListByDepartId(departmentId);
                    for (DepartmentEntity _entity : departments) {
                        departmentId = _entity.getDepartmentId();
                        departmentName = _entity.getDepartmentName();
                        subCenterDepartmentIdList.add(departmentId);
                        entityVo = new DepartmentEntityVo();
                        entityVo.setDepartmentId(departmentId);
                        entityVo.setDepartmentName(departmentName);
                        List<String> salesDepartmentIdList = new ArrayList<String>();
                        entityVo.setDepartmentIds(salesDepartmentIdList);
                        DepartmentUtil.departmentMap.put(departmentId, entityVo);
                        subs.add(entityVo);
                        subIds.add(departmentId);

                        /*营业部*/
                        departments = smpWSUtil.getSubordinateDepartListByDepartId(departmentId);
                        for (DepartmentEntity __entity : departments) {
                            departmentId = __entity.getDepartmentId();
                            departmentName = __entity.getDepartmentName();
                            salesDepartmentIdList.add(departmentId);
                            entityVo = new DepartmentEntityVo();
                            entityVo.setDepartmentId(departmentId);
                            entityVo.setDepartmentName(departmentName);
                            DepartmentUtil.departmentMap.put(departmentId, entityVo);
                            departs.add(entityVo);
                            departIds.add(departmentId);
                        }
                    }

                }
            }
        }
        Object[][] ret = {{"region", regions, regionIds}, {"offices", offices, officeIds}, {"subs", subs, subIds}, {"departs", departs, departIds}};
        for (Object[] item : ret) {
            DepartmentUtil.retMapByCategory.put((String) item[0], item[1]);
            DepartmentUtil.retMapByCategory.put(item[0] + "Ids", item[2]);
        }
    }

}
