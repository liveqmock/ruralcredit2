package com.creditease.rc.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.creditease.rc.common.DepartmentEntity;
import com.creditease.rc.service.IStatisticalinfoService;
import com.creditease.rc.vo.Combobox;

/**
 * Title: Description: 宜农贷2.0系统开发 Copyright:Copyright (c) 2013
 * Company:普信恒业科技发展（北京）有限公司 2013-12-23
 * 
 * @author 郝强
 * @version v1.0
 */
@Service
public class ComboUtil {

	@Resource
	private IStatisticalinfoService statisticalinfoService;

	private ComboUtil() {

	}

	public List<Combobox> localDepartmentCombo(List<String> authList) {

		Map<String, String> authorityMap = new HashMap<String, String>();
		String sqlsid = "";
		if (authList.size() > 0) {
			for (String e : authList) {
				if (sqlsid != null) {
					sqlsid = sqlsid + "'" + e + "'" + ",";
				} else {
					sqlsid = "'" + e + "'" + ",";
				}
			}
			sqlsid = sqlsid.substring(0, sqlsid.length() - 1);
		}
		authorityMap.put("authList", sqlsid);

		List<Combobox> comboboxs = new ArrayList<Combobox>();
		Combobox comboboxFirst = new Combobox();
		comboboxFirst.setCode("");
		comboboxFirst.setValue("请选择");
		comboboxFirst.setSelected(true);
		comboboxs.add(comboboxFirst);
		List<DepartmentEntity> departmentEntities = statisticalinfoService
				.queryDepartmentEntities(authorityMap);
		if (CommonUtil.isNotEmpty(departmentEntities)) {
			// 重载sort方法
			Collections.sort(departmentEntities,
					new Comparator<DepartmentEntity>() {
						@Override
						public int compare(DepartmentEntity o1,
								DepartmentEntity o2) {
							// TODO Auto-generated method stub
							return o1.getDepartmentId().compareTo(
									o2.getDepartmentId());
						}
					});
			for (DepartmentEntity departmentEntity : departmentEntities) {
				Combobox combobox = new Combobox();
				combobox.setCode(departmentEntity.getDepartmentId());
				combobox.setValue(departmentEntity.getDepartmentName());
				combobox.setSelected(false);
				comboboxs.add(combobox);
			}
		}
		return comboboxs;
	}

	public List<Combobox> todayAndYesterday() {
		List<Combobox> comboboxs = new ArrayList<Combobox>();
		Calendar calendarY = Calendar.getInstance();
		calendarY.add(Calendar.DATE, -1);
		Calendar calendarT = Calendar.getInstance();

		int month1=calendarY.get(Calendar.MONTH) + 1;
		int month2=(calendarT.get(Calendar.MONTH) + 1);
		
		int day1=calendarT.get(Calendar.DATE);
		int day2=calendarY.get(Calendar.DATE);
		
		String yesterday = calendarY.get(Calendar.YEAR)
				+ "-"+ ((month1) < 10 ? "0"
						+ (month1) : 
							(month1)) + "-"
				+((day1)<10?"0"+(day1):(day1));
		
		String today = calendarT.get(Calendar.YEAR)
				+ "-"
				+ ((month2) < 10 ? "0"
						+ (month2) : 
							(month2)) + "-"
				+((day2)<10?"0"+(day2):(day2));
		Combobox combobox0 = new Combobox();
		combobox0.setCode("");
		combobox0.setValue("请选择");

		Combobox combobox1 = new Combobox();
		combobox1.setCode(today);
		combobox1.setValue(today);

		Combobox combobox2 = new Combobox();
		combobox2.setCode(yesterday);
		combobox2.setValue(yesterday);

		comboboxs.add(combobox0);
		comboboxs.add(combobox1);
		comboboxs.add(combobox2);
		return comboboxs;
	}
}
