package com.creditease.rc.service.impl;

import com.creditease.rc.dao.ISalesPlanningDao;
import com.creditease.rc.domain.Message;
import com.creditease.rc.domain.SalesPlanning;
import com.creditease.rc.service.ISalesPlanningService;
import com.creditease.rc.util.CommonUtil;
import com.creditease.rc.vo.SalesPlanningTable;
import com.creditease.rc.vo.SalesPlanningTableVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.*;

@Service
public class SalesPlanningService implements ISalesPlanningService {

	@Resource
	private ISalesPlanningDao salesPlanningDao;

	@Override
	public boolean insert(SalesPlanning salesPlanning) {
		// TODO Auto-generated method stub
		return salesPlanningDao.insert(salesPlanning);
	}

	@Override
	public boolean update(SalesPlanning salesPlanning) {
		// TODO Auto-generated method stub
		return salesPlanningDao.update(salesPlanning);
	}

	@Override
	public Map<String, SalesPlanningTable> querySalesPlanningTable(String areaDepartmentIds, String year) {
		// TODO Auto-generated method stub
		// 将参数转换成map
		Map<String, String> map = new HashMap<String, String>();
		map.put("areaDepartmentIds", areaDepartmentIds);
		map.put("year", year);
		List<SalesPlanningTable> salesPlanningTables = salesPlanningDao.querySalesPlanningTable(map);
		// 将结果 转换成Map
		Map<String, SalesPlanningTable> resultMap = transforMap(salesPlanningTables);
		return resultMap;
	}

	private Map<String, SalesPlanningTable> transforMap(List<SalesPlanningTable> salesPlanningTables) {
		Map<String, SalesPlanningTable> map = new HashMap<String, SalesPlanningTable>();
		if (CommonUtil.isNotEmpty(salesPlanningTables)) {
			for (SalesPlanningTable salesPlanningTable : salesPlanningTables) {
				String key = salesPlanningTable.getAreaDepartmentId() + "|" + salesPlanningTable.getType();
				map.put(key, salesPlanningTable);
			}
		}

		return map;
	}

	@Override
	public boolean batchInsert(List<SalesPlanning> salesPlannings) {
		// TODO Auto-generated method stub
		return salesPlanningDao.batchInsert(salesPlannings);
	}

	@Override
	public Message saveSalesPlanningTableVo(SalesPlanningTableVo salesPlanningTableVo) {
		// TODO Auto-generated method stub
		Message message = new Message();
		if (salesPlanningTableVo != null) {
			List<SalesPlanningTable> salesPlanningTables = salesPlanningTableVo.getSalesPlanningTables();
			if (CommonUtil.isNotEmpty(salesPlanningTables)) {
				List<SalesPlanning> salesPlanningAll = new ArrayList<SalesPlanning>();
				for (SalesPlanningTable salesPlanningTable : salesPlanningTables) {
					List<SalesPlanning> salesPlannings = transforSalesPlanningList(salesPlanningTable);
					salesPlanningAll.addAll(salesPlannings);
				}
				if (CommonUtil.isNotEmpty(salesPlanningAll)) {
					List<SalesPlanning> insertList = new ArrayList<SalesPlanning>();
					List<SalesPlanning> updateList = new ArrayList<SalesPlanning>();
					for (SalesPlanning salesPlanning : salesPlanningAll) {
						Long getSalesPlanningId = salesPlanning.getSalesPlanningId();
						String getType = salesPlanning.getType();
						BigDecimal getValue = salesPlanning.getValue();
						if ("1".equals(getType)) {
							if (getValue != null) {
								getValue = getValue.multiply(new BigDecimal(10000));
							}
							salesPlanning.setValue(getValue);
						}
						if (getSalesPlanningId == null) {
							insertList.add(salesPlanning);
						} else {
							updateList.add(salesPlanning);
						}
					}
					this.insertSalesPlanningList(insertList);
					this.updateSalesPlanningList(updateList);
					message.setSuccess(true);
					message.setMsg("保存成功！");
				} else {
					message.setSuccess(false);
					message.setMsg("salesPlanningAll集合为空！");
				}
			} else {
				message.setSuccess(false);
				message.setMsg("salesPlanningTables集合为空！");
			}
		} else {
			message.setSuccess(false);
			message.setMsg("salesPlanningTableVo对象为空！");
		}
		return message;
	}

	@Override
	public boolean updateSalesPlanningList(List<SalesPlanning> updateList) {
		// TODO Auto-generated method stub
		return salesPlanningDao.updateSalesPlanningList(updateList);
	}

	@Override
	public boolean insertSalesPlanningList(List<SalesPlanning> insertList) {
		// TODO Auto-generated method stub
		return salesPlanningDao.insertSalesPlanningList(insertList);
	}

	private List<SalesPlanning> transforSalesPlanningList(SalesPlanningTable salesPlanningTable) {
		// TODO Auto-generated method stub
		List<SalesPlanning> salesPlannings = new ArrayList<SalesPlanning>();
		Long getAreaDepartmentId = salesPlanningTable.getAreaDepartmentId();
		String getAreaDepartmentName = salesPlanningTable.getAreaDepartmentName();
		String getType = salesPlanningTable.getType();
		Integer getYear = salesPlanningTable.getYear();

		Long getJanId = salesPlanningTable.getJanId();
		Long getFebId = salesPlanningTable.getFebId();
		Long getMarId = salesPlanningTable.getMarId();
		Long getAprId = salesPlanningTable.getAprId();
		Long getMayId = salesPlanningTable.getMayId();
		Long getJunId = salesPlanningTable.getJunId();
		Long getJulId = salesPlanningTable.getJulId();
		Long getAugId = salesPlanningTable.getAugId();
		Long getSepId = salesPlanningTable.getSepId();
		Long getOctId = salesPlanningTable.getOctId();
		Long getNovId = salesPlanningTable.getNovId();
		Long getDecId = salesPlanningTable.getDecId();
		BigDecimal getJan = salesPlanningTable.getJan();
		BigDecimal getFeb = salesPlanningTable.getFeb();
		BigDecimal getMar = salesPlanningTable.getMar();
		BigDecimal getApr = salesPlanningTable.getApr();
		BigDecimal getMay = salesPlanningTable.getMay();
		BigDecimal getJun = salesPlanningTable.getJun();
		BigDecimal getJul = salesPlanningTable.getJul();
		BigDecimal getAug = salesPlanningTable.getAug();
		BigDecimal getSep = salesPlanningTable.getSep();
		BigDecimal getOct = salesPlanningTable.getOct();
		BigDecimal getNov = salesPlanningTable.getNov();
		BigDecimal getDec = salesPlanningTable.getDec();

		SalesPlanning SalesPlanningJan = new SalesPlanning();
		SalesPlanning SalesPlanningFeb = new SalesPlanning();
		SalesPlanning SalesPlanningMar = new SalesPlanning();
		SalesPlanning SalesPlanningApr = new SalesPlanning();
		SalesPlanning SalesPlanningMay = new SalesPlanning();
		SalesPlanning SalesPlanningJun = new SalesPlanning();
		SalesPlanning SalesPlanningJul = new SalesPlanning();
		SalesPlanning SalesPlanningAug = new SalesPlanning();
		SalesPlanning SalesPlanningSep = new SalesPlanning();
		SalesPlanning SalesPlanningOct = new SalesPlanning();
		SalesPlanning SalesPlanningNov = new SalesPlanning();
		SalesPlanning SalesPlanningDec = new SalesPlanning();

		SalesPlanningJan.setAreaDepartmentId(getAreaDepartmentId);
		SalesPlanningJan.setAreaDepartmentName(getAreaDepartmentName);
		SalesPlanningJan.setType(getType);
		SalesPlanningJan.setYear(getYear);
		SalesPlanningJan.setMonth(1);
		SalesPlanningJan.setSalesPlanningId(getJanId);
		SalesPlanningJan.setValue(getJan);

		SalesPlanningFeb.setAreaDepartmentId(getAreaDepartmentId);
		SalesPlanningFeb.setAreaDepartmentName(getAreaDepartmentName);
		SalesPlanningFeb.setType(getType);
		SalesPlanningFeb.setYear(getYear);
		SalesPlanningFeb.setMonth(2);
		SalesPlanningFeb.setSalesPlanningId(getFebId);
		SalesPlanningFeb.setValue(getFeb);

		SalesPlanningMar.setAreaDepartmentId(getAreaDepartmentId);
		SalesPlanningMar.setAreaDepartmentName(getAreaDepartmentName);
		SalesPlanningMar.setType(getType);
		SalesPlanningMar.setYear(getYear);
		SalesPlanningMar.setMonth(3);
		SalesPlanningMar.setSalesPlanningId(getMarId);
		SalesPlanningMar.setValue(getMar);

		SalesPlanningApr.setAreaDepartmentId(getAreaDepartmentId);
		SalesPlanningApr.setAreaDepartmentName(getAreaDepartmentName);
		SalesPlanningApr.setType(getType);
		SalesPlanningApr.setYear(getYear);
		SalesPlanningApr.setMonth(4);
		SalesPlanningApr.setSalesPlanningId(getAprId);
		SalesPlanningApr.setValue(getApr);

		SalesPlanningMay.setAreaDepartmentId(getAreaDepartmentId);
		SalesPlanningMay.setAreaDepartmentName(getAreaDepartmentName);
		SalesPlanningMay.setType(getType);
		SalesPlanningMay.setYear(getYear);
		SalesPlanningMay.setMonth(5);
		SalesPlanningMay.setSalesPlanningId(getMayId);
		SalesPlanningMay.setValue(getMay);

		SalesPlanningJun.setAreaDepartmentId(getAreaDepartmentId);
		SalesPlanningJun.setAreaDepartmentName(getAreaDepartmentName);
		SalesPlanningJun.setType(getType);
		SalesPlanningJun.setYear(getYear);
		SalesPlanningJun.setMonth(6);
		SalesPlanningJun.setSalesPlanningId(getJunId);
		SalesPlanningJun.setValue(getJun);

		SalesPlanningJul.setAreaDepartmentId(getAreaDepartmentId);
		SalesPlanningJul.setAreaDepartmentName(getAreaDepartmentName);
		SalesPlanningJul.setType(getType);
		SalesPlanningJul.setYear(getYear);
		SalesPlanningJul.setMonth(7);
		SalesPlanningJul.setSalesPlanningId(getJulId);
		SalesPlanningJul.setValue(getJul);

		SalesPlanningAug.setAreaDepartmentId(getAreaDepartmentId);
		SalesPlanningAug.setAreaDepartmentName(getAreaDepartmentName);
		SalesPlanningAug.setType(getType);
		SalesPlanningAug.setYear(getYear);
		SalesPlanningAug.setMonth(8);
		SalesPlanningAug.setSalesPlanningId(getAugId);
		SalesPlanningAug.setValue(getAug);

		SalesPlanningSep.setAreaDepartmentId(getAreaDepartmentId);
		SalesPlanningSep.setAreaDepartmentName(getAreaDepartmentName);
		SalesPlanningSep.setType(getType);
		SalesPlanningSep.setYear(getYear);
		SalesPlanningSep.setMonth(9);
		SalesPlanningSep.setSalesPlanningId(getSepId);
		SalesPlanningSep.setValue(getSep);

		SalesPlanningOct.setAreaDepartmentId(getAreaDepartmentId);
		SalesPlanningOct.setAreaDepartmentName(getAreaDepartmentName);
		SalesPlanningOct.setType(getType);
		SalesPlanningOct.setYear(getYear);
		SalesPlanningOct.setMonth(10);
		SalesPlanningOct.setSalesPlanningId(getOctId);
		SalesPlanningOct.setValue(getOct);

		SalesPlanningNov.setAreaDepartmentId(getAreaDepartmentId);
		SalesPlanningNov.setAreaDepartmentName(getAreaDepartmentName);
		SalesPlanningNov.setType(getType);
		SalesPlanningNov.setYear(getYear);
		SalesPlanningNov.setMonth(11);
		SalesPlanningNov.setSalesPlanningId(getNovId);
		SalesPlanningNov.setValue(getNov);

		SalesPlanningDec.setAreaDepartmentId(getAreaDepartmentId);
		SalesPlanningDec.setAreaDepartmentName(getAreaDepartmentName);
		SalesPlanningDec.setType(getType);
		SalesPlanningDec.setYear(getYear);
		SalesPlanningDec.setMonth(12);
		SalesPlanningDec.setSalesPlanningId(getDecId);
		SalesPlanningDec.setValue(getDec);

		salesPlannings.add(SalesPlanningJan);
		salesPlannings.add(SalesPlanningFeb);
		salesPlannings.add(SalesPlanningMar);
		salesPlannings.add(SalesPlanningApr);
		salesPlannings.add(SalesPlanningMay);
		salesPlannings.add(SalesPlanningJun);
		salesPlannings.add(SalesPlanningJul);
		salesPlannings.add(SalesPlanningAug);
		salesPlannings.add(SalesPlanningSep);
		salesPlannings.add(SalesPlanningOct);
		salesPlannings.add(SalesPlanningNov);
		salesPlannings.add(SalesPlanningDec);

		return salesPlannings;
	}

    @Override
    public Message saveSalesPlanning() {
        Message m = new Message(false, "保存失败");
        HttpServletRequest r = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        String[] str_arr = r.getParameter("d").substring(1, r.getParameter("d").length() - 1).split("&");
        List<String> l = Arrays.asList(str_arr);

        String[] entry;
        SalesPlanningTable s = null;
        List<SalesPlanningTable> ls = new ArrayList<SalesPlanningTable>();
        String[] types;
        /*
        pType: 大区 ==> "0"、分中心 ==> "1"、营业部==>"2"
		cType: 放款量 ==> "0"、合同金额  ==> "1"
		 */
        String pType, cType;

        List<String> del_department_ids = new ArrayList<String>();

        for (String aL : l) {
            entry = aL.split("=");
            if (entry[0].equals("jan")) {
                s = new SalesPlanningTable();
                s.setJan(entry.length > 1 ? new BigDecimal(entry[1]) : null);
            } else if (entry[0].equals("dec")) {
                s.setDec(entry.length > 1 ? new BigDecimal(entry[1]) : null);
                types = r.getParameter("types").split("_");
                pType = types[0].equals("region") ? "0" : types[0].equals("subs") ? "1" : "2";
                cType = types[1].equals("loan") ? "0" : "1";
                s.setPlanType(pType);
                s.setType(cType);
                s.setYear(Integer.valueOf(r.getParameter("year")));
                ls.add(s);
                del_department_ids.add(s.getAreaDepartmentId().toString());
            } else if (entry[0].equals("feb")) {
                s.setFeb(entry.length > 1 ? new BigDecimal(entry[1]) : null);
            } else if (entry[0].equals("mar")) {
                s.setMar(entry.length > 1 ? new BigDecimal(entry[1]) : null);
            } else if (entry[0].equals("apr")) {
                s.setApr(entry.length > 1 ? new BigDecimal(entry[1]) : null);
            } else if (entry[0].equals("may")) {
                s.setMay(entry.length > 1 ? new BigDecimal(entry[1]) : null);
            } else if (entry[0].equals("jun")) {
                s.setJun(entry.length > 1 ? new BigDecimal(entry[1]) : null);
            } else if (entry[0].equals("jul")) {
                s.setJul(entry.length > 1 ? new BigDecimal(entry[1]) : null);
            } else if (entry[0].equals("aug")) {
                s.setAug(entry.length > 1 ? new BigDecimal(entry[1]) : null);
            } else if (entry[0].equals("sep")) {
                s.setSep(entry.length > 1 ? new BigDecimal(entry[1]) : null);
            } else if (entry[0].equals("oct")) {
                s.setOct(entry.length > 1 ? new BigDecimal(entry[1]) : null);
            } else if (entry[0].equals("nov")) {
                s.setNov(entry.length > 1 ? new BigDecimal(entry[1]) : null);
            } else if (entry[0].equals("departmentId")) {
                s.setAreaDepartmentId(Long.valueOf(entry[1]));
            } else if (entry[0].equals("departmentName")) {
                try {
                    s.setAreaDepartmentName(URLDecoder.decode(entry[1], "utf-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("plan_type", ls.get(0).getPlanType());
        map.put("type", ls.get(0).getType());
        map.put("del", del_department_ids);
        map.put("year", ls.get(0).getYear());
        List<Integer> del_ids = salesPlanningDao.searchSalesPlanningDel(map);

        try {
            boolean ret = this._saveSalesPlanning(ls);
            if (ret) {
                if (!del_ids.isEmpty()) {
                    salesPlanningDao.deleteSalesPlannings(del_ids);
                }
                m = new Message(true, "保存成功");
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return m;
    }

    @Override
    public Map<String, SalesPlanningTable> searchSalesPlanningByCase(SalesPlanning salesPlanning) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("year", salesPlanning.getYear());
        map.put("type", salesPlanning.getType());
        map.put("departmentIds", salesPlanning.getAreaDepartmentIds());
        List<SalesPlanning> plannings = salesPlanningDao.searchSalesPlanningByCase(map);

        Map<String, SalesPlanningTable> _map = new HashMap<String, SalesPlanningTable>();

        Object[][] n2e = {
                {1, "setJan"}, {2, "setFeb"}, {3, "setMar"}, {4, "setApr"}, {5, "setMay"}, {6, "setJun"},
                {7, "setJul"}, {8, "setAug"}, {9, "setSep"}, {10, "setOct"}, {11, "setNov"}, {12, "setDec"}
        };
        SalesPlanningTable table = new SalesPlanningTable();
        Method method = null;
        for (SalesPlanning planning : plannings) {

            if (!_map.containsKey(planning.getAreaDepartmentId().toString())) {
                table = new SalesPlanningTable();
                _map.put(planning.getAreaDepartmentId().toString(), table);
            }

            try {
                method = SalesPlanningTable.class.getDeclaredMethod(String.valueOf(n2e[planning.getMonth() - 1][1]), BigDecimal.class);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }

            try {
                assert method != null;
                method.invoke(table, planning.getValue());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }

        return _map;
    }

    private boolean _saveSalesPlanning(List<SalesPlanningTable> list) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        String[][] n2e = {
                {"1", "getJan"}, {"2", "getFeb"}, {"3", "getMar"}, {"4", "getApr"}, {"5", "getMay"}, {"6", "getJun"},
                {"7", "getJul"}, {"8", "getAug"}, {"9", "getSep"}, {"10", "getOct"}, {"11", "getNov"}, {"12", "getDec"}
        };

        List<SalesPlanning> plannings = new ArrayList<SalesPlanning>();
        SalesPlanning planning;
        SalesPlanning _planning;

        for (SalesPlanningTable table : list) {
            planning = new SalesPlanning();
            planning.setYear(table.getYear());
            planning.setType(table.getType());
            planning.setAreaDepartmentId(table.getAreaDepartmentId());
            planning.setAreaDepartmentName(table.getAreaDepartmentName());
            planning.setPlanType(table.getPlanType());

            for (int i = 0; i < 12; i++) {
                _planning = new SalesPlanning();
                BeanUtils.copyProperties(planning, _planning);
                _planning.setMonth(i + 1);
                Method method = SalesPlanningTable.class.getDeclaredMethod(n2e[i][1]);
                _planning.setValue((BigDecimal) method.invoke(table));
                plannings.add(_planning);
            }
        }

        return salesPlanningDao.saveSalesPlannings(plannings);
    }
}


