package com.creditease.rc.action;

import com.creditease.core.security.SpringSecurityUtils;
import com.creditease.rc.common.DepartmentEntity;
import com.creditease.rc.domain.Message;
import com.creditease.rc.domain.RollingForecast;
import com.creditease.rc.domain.SalesPlanning;
import com.creditease.rc.report.excel.ExcelUtils;
import com.creditease.rc.report.excel.TableData;
import com.creditease.rc.service.*;
import com.creditease.rc.util.CommonUtil;
import com.creditease.rc.util.DateUtil;
import com.creditease.rc.util.DepartmentUtil;
import com.creditease.rc.util.SmpWSUtil;
import com.creditease.rc.vo.*;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;

@Controller
@RequestMapping("salesController")
public class SalesController {
	@Resource
	private ISalesPlanningService salesPlanningService;
	@Resource
	private IRollingForecastSerivce rollingForecastSerivce;
	@Resource
	IStatisticalinfoService statisticalinfoService;
	@Resource
	private SmpWSUtil smpWSUtil;
    @Resource
    private IJsGridReportBaseXLSXService jsGridReportBaseXLSXService;

    @Resource
	private ILaonBalanceDataService laonBalanceDataService;

	@Resource
	private IEasyUIService easyUIService;

	/**
	 * 
	 * @param binder
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dateFormat.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
		binder.registerCustomEditor(Date.class, "receivedTime", new CustomDateEditor(dateFormat2, true));
	}
	/** 以下为销售计划方法 begin **/
	@RequestMapping(value = "salesPlanningTable")
	public ModelAndView salesPlanningTable(String areaDepartmentIds, String year, HttpSession session) {
		List<String> authList = SpringSecurityUtils.getAuthList(session);
		Map<String, String> quanxianmap = new HashMap<String, String>();
		Map<String, List<SalesPlanningTable>> fenzhongxinMap = new HashMap<String, List<SalesPlanningTable>>();//存放分中心和营业部
		Map<DepartmentEntity, Set<String>> mapTemp = new HashMap<DepartmentEntity, Set<String>>();//临时存放，分中心和营业部
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
		quanxianmap.put("authList", sqlsid);
		System.out.println(areaDepartmentIds);
		System.out.println(year);
		if ("null".equals(year)) {
			year = null;
		}
		if ("null".equals(areaDepartmentIds)) {
			areaDepartmentIds = "";
		}
		if (CommonUtil.isEmpty(year)) {
			Calendar calendar = Calendar.getInstance();
			year = String.valueOf(calendar.get(calendar.YEAR));
		}
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/jsp/rc/sales/salesPlanningTable.jsp");
		List<SalesPlanningTable> salesPlanningTables = new ArrayList<SalesPlanningTable>();
		if (CommonUtil.isEmpty(areaDepartmentIds)) {
			Set<Entry<String, DepartmentEntityVo>> entries = DepartmentUtil.getDepartmentMapByAuthority().entrySet();
			Map<String, DepartmentEntityVo> departmentMap = DepartmentUtil.departmentMap;//得到所有分中心和营业部
			Set<Entry<String,DepartmentEntityVo>> entrySet2 = departmentMap.entrySet();
			if (CommonUtil.isEmpty(entries)) {
				modelAndView.setViewName("/jsp/rc/sales/saleMessage.jsp");
				return modelAndView;
			}
			if (CommonUtil.isNotEmpty(entries)) {
				List<DepartmentEntity> departmentEntities = new ArrayList<DepartmentEntity>();
				//Set<String> setTemp = new HashSet<String>();//存放营业部id临时set
				for (Entry<String, DepartmentEntityVo> entry : entries) {
					DepartmentEntityVo departmentEntityVo = entry.getValue();
					if (CommonUtil.isEmpty(departmentEntityVo.getDepartmentIds())) {
						String getDepartmentId = departmentEntityVo.getDepartmentId();
						String getDepartmentName = departmentEntityVo.getDepartmentName();
						areaDepartmentIds = null != areaDepartmentIds ? getDepartmentId : areaDepartmentIds + getDepartmentId + ",";
						areaDepartmentIds = null != areaDepartmentIds ? getDepartmentId : areaDepartmentIds + getDepartmentId + ",";
						DepartmentEntity departmentEntity = new DepartmentEntity();
						departmentEntity.setDepartmentId(getDepartmentId);
						departmentEntity.setDepartmentName(getDepartmentName);
						departmentEntities.add(departmentEntity);
						//将营业部添加到分中心里面*********************
						/**
						 * 思路：1.首先得到所有分中心和营业部，
						 * 2.判断该节点是否是分中心，
						 * 3.将全部的那个节点去掉，
						 * 4.然后判断该营业部是否在该分中心下，如果在，将分中心和营业部id存入临时的map中
						 */
						for (Entry<String, DepartmentEntityVo> entry2 : entrySet2) {
							String departmentName = entry2.getValue().getDepartmentName();
							if (CommonUtil.isNotEmpty(entry2.getValue().getDepartmentIds())){
								if(!"0".equals(entry2.getValue().getDepartmentId())){
									List<String> departmentIds = entry2.getValue().getDepartmentIds();
									Set<String> departmentSet = new HashSet<String>(departmentIds);
									if(departmentSet.contains(departmentEntityVo.getDepartmentId())){
										DepartmentEntity departmentEntity2 = new DepartmentEntity();
										departmentEntity2.setDepartmentId(entry2.getValue().getDepartmentId());
										departmentEntity2.setDepartmentName(entry2.getValue().getDepartmentName());
										Set<String> departmentSet2 = new HashSet<String>(entry2.getValue().getDepartmentIds());
										mapTemp.put(departmentEntity2, departmentSet2);
									}
									
								}
							}
						}
					}else{//用于判断添加分中心
						if(!"0".equals(departmentEntityVo.getDepartmentId())){
							String getDepartmentId = departmentEntityVo.getDepartmentId();
							String getDepartmentName = departmentEntityVo.getDepartmentName();
							DepartmentEntity departmentEntity = new DepartmentEntity();
							departmentEntity.setDepartmentId(getDepartmentId);
							departmentEntity.setDepartmentName(getDepartmentName);
							List<String> departmentIds = departmentEntityVo.getDepartmentIds();
							Set<String> setTemp2 = new HashSet<String>(departmentIds);
							mapTemp.put(departmentEntity, setTemp2);
						}
						
					}
				}
				
				
				areaDepartmentIds = areaDepartmentIds.substring(0, areaDepartmentIds.length() - 1);
				Map<String, SalesPlanningTable> salesPlanningTablesMap = salesPlanningService.querySalesPlanningTable(
						areaDepartmentIds, year);
				// 重载sort方法
				Collections.sort(departmentEntities, new Comparator<DepartmentEntity>() {
					@Override
					public int compare(DepartmentEntity o1, DepartmentEntity o2) {
						// TODO Auto-generated method stub
						return o1.getDepartmentId().compareTo(o2.getDepartmentId());
					}
				});
				// 第一次循环找出type=“0” 的 数据
				for (DepartmentEntity departmentEntity : departmentEntities) {
					String getDepartmentIdKey = departmentEntity.getDepartmentId() + "|0";
					SalesPlanningTable salesPlanningTable = salesPlanningTablesMap.get(getDepartmentIdKey);
					if (salesPlanningTable == null) {
						salesPlanningTable = new SalesPlanningTable();
						salesPlanningTable.setYear(Integer.valueOf(year));
						salesPlanningTable.setAreaDepartmentId(Long.valueOf(departmentEntity.getDepartmentId()));
						salesPlanningTable.setAreaDepartmentName(departmentEntity.getDepartmentName());
						salesPlanningTable.setType("0");
					}
					salesPlanningTables.add(salesPlanningTable);
				}
				// 第一次循环找出type=“1” 的 数据
				for (DepartmentEntity departmentEntity : departmentEntities) {
					String getDepartmentIdKey = departmentEntity.getDepartmentId() + "|1";
					SalesPlanningTable salesPlanningTable = salesPlanningTablesMap.get(getDepartmentIdKey);
					if (salesPlanningTable == null) {
						salesPlanningTable = new SalesPlanningTable();
						salesPlanningTable.setYear(Integer.valueOf(year));
						salesPlanningTable.setAreaDepartmentId(Long.valueOf(departmentEntity.getDepartmentId()));
						salesPlanningTable.setAreaDepartmentName(departmentEntity.getDepartmentName());
						salesPlanningTable.setType("1");
					}
					salesPlanningTables.add(salesPlanningTable);
				}
				
				//将数据按营业部分类
				Set<Entry<DepartmentEntity,Set<String>>> entrySet = mapTemp.entrySet();
				
				for (Entry<DepartmentEntity, Set<String>> entry : entrySet) {
					Set<String> value = entry.getValue();
					List<SalesPlanningTable> salesPlanningTableList = new ArrayList<SalesPlanningTable>();
					for(SalesPlanningTable salesPlanningTable : salesPlanningTables){
						if(value.contains(salesPlanningTable.getAreaDepartmentId().toString())){
							salesPlanningTableList.add(salesPlanningTable);
						}
					}
					fenzhongxinMap.put(entry.getKey().getDepartmentName(), salesPlanningTableList);
				}
			}
		} else {
			Map<String, String> getAllAreaDepartmentNameMap = smpWSUtil.getAllAreaDepartmentNameMap();
			Map<String, SalesPlanningTable> salesPlanningTablesMap = salesPlanningService.querySalesPlanningTable(
					areaDepartmentIds, year);
			String[] areaDepartmentIdArray = areaDepartmentIds.split(",");
			List<String> areaDepartmentIdListTemp = Arrays.asList(areaDepartmentIdArray);
			List<String> areaDepartmentIdList = new ArrayList<String>();

			for (String sareaDepartmentIdTemp : areaDepartmentIdListTemp) {
				DepartmentEntityVo departmentEntityVo = DepartmentUtil.departmentMap.get(sareaDepartmentIdTemp);
				if (departmentEntityVo != null && CommonUtil.isEmpty(departmentEntityVo.getDepartmentIds())) {
					areaDepartmentIdList.add(departmentEntityVo.getDepartmentId());
					//***********用于选择营业部添加分中心start*********************
					Map<String, DepartmentEntityVo> departmentMap = DepartmentUtil.departmentMap;//得到所有分中心和营业部
					Set<Entry<String,DepartmentEntityVo>> entrySet2 = departmentMap.entrySet();
					for (Entry<String, DepartmentEntityVo> entry2 : entrySet2) {
						String departmentName = entry2.getValue().getDepartmentName();
						if (CommonUtil.isNotEmpty(entry2.getValue().getDepartmentIds())){
							if(!"0".equals(entry2.getValue().getDepartmentId())){
								List<String> departmentIds = entry2.getValue().getDepartmentIds();
								Set<String> departmentSet = new HashSet<String>(departmentIds);
								if(departmentSet.contains(departmentEntityVo.getDepartmentId())){
									DepartmentEntity departmentEntity2 = new DepartmentEntity();
									departmentEntity2.setDepartmentId(entry2.getValue().getDepartmentId());
									departmentEntity2.setDepartmentName(entry2.getValue().getDepartmentName());
									Set<String> departmentSet2 = new HashSet<String>(entry2.getValue().getDepartmentIds());
									mapTemp.put(departmentEntity2, departmentSet2);
								}
								
							}
						}
					}
					
					Set<Entry<String, DepartmentEntityVo>> entries = DepartmentUtil.getDepartmentMapByAuthority().entrySet();
					for (Entry<String, DepartmentEntityVo> entry : entries) {
						DepartmentEntityVo departmentEntityVo2 = entry.getValue();
						if (CommonUtil.isNotEmpty(departmentEntityVo2.getDepartmentIds())) {
							if(!"0".equals(departmentEntityVo2.getDepartmentId())){
								String getDepartmentId = departmentEntityVo2.getDepartmentId();
								String getDepartmentName = departmentEntityVo2.getDepartmentName();
								DepartmentEntity departmentEntity = new DepartmentEntity();
								departmentEntity.setDepartmentId(getDepartmentId);
								departmentEntity.setDepartmentName(getDepartmentName);
								List<String> departmentIds = departmentEntityVo2.getDepartmentIds();
								Set<String> setTemp2 = new HashSet<String>(departmentIds);
								mapTemp.put(departmentEntity, setTemp2);
							}
						}
					}
					//***********用于选择营业部添加分中心end*********************
				}
				//用于添加分中心
				if(departmentEntityVo != null && CommonUtil.isNotEmpty(departmentEntityVo.getDepartmentIds())){
					if(!"0".equals(departmentEntityVo.getDepartmentId())){
						String getDepartmentId = departmentEntityVo.getDepartmentId();
						String getDepartmentName = departmentEntityVo.getDepartmentName();
						DepartmentEntity departmentEntity = new DepartmentEntity();
						departmentEntity.setDepartmentId(getDepartmentId);
						departmentEntity.setDepartmentName(getDepartmentName);
						List<String> departmentIds = departmentEntityVo.getDepartmentIds();
						Set<String> setTemp2 = new HashSet<String>(departmentIds);
						mapTemp.put(departmentEntity, setTemp2);
					}
				}
			}

			Collections.sort(areaDepartmentIdList);
			// 构建一个departmentEntities
			List<DepartmentEntity> departmentEntities = new ArrayList<DepartmentEntity>();
			for (String areaDepartmentId : areaDepartmentIdList) {
				DepartmentEntity departmentEntity = new DepartmentEntity();

				String areaDepartmentName = getAllAreaDepartmentNameMap.get(areaDepartmentId);
				departmentEntity.setDepartmentId(areaDepartmentId);
				departmentEntity.setDepartmentName(areaDepartmentName);
				departmentEntities.add(departmentEntity);
			}
			// 第一次循环找出type=“0” 的 数据
			for (DepartmentEntity departmentEntity : departmentEntities) {
				String getDepartmentIdKey = departmentEntity.getDepartmentId() + "|0";
				SalesPlanningTable salesPlanningTable = salesPlanningTablesMap.get(getDepartmentIdKey);
				if (salesPlanningTable == null) {
					salesPlanningTable = new SalesPlanningTable();
					salesPlanningTable.setYear(Integer.valueOf(year));
					salesPlanningTable.setAreaDepartmentId(Long.valueOf(departmentEntity.getDepartmentId()));
					salesPlanningTable.setAreaDepartmentName(departmentEntity.getDepartmentName());
					salesPlanningTable.setType("0");
				}
				salesPlanningTables.add(salesPlanningTable);
			}
			// 第二次循环找出type=“1” 的 数据
			for (DepartmentEntity departmentEntity : departmentEntities) {
				String getDepartmentIdKey = departmentEntity.getDepartmentId() + "|1";
				SalesPlanningTable salesPlanningTable = salesPlanningTablesMap.get(getDepartmentIdKey);
				if (salesPlanningTable == null) {
					salesPlanningTable = new SalesPlanningTable();
					salesPlanningTable.setYear(Integer.valueOf(year));
					salesPlanningTable.setAreaDepartmentId(Long.valueOf(departmentEntity.getDepartmentId()));
					salesPlanningTable.setAreaDepartmentName(departmentEntity.getDepartmentName());
					salesPlanningTable.setType("1");
				}
				salesPlanningTables.add(salesPlanningTable);
			}
			
			//将数据按营业部分类
			Set<Entry<DepartmentEntity,Set<String>>> entrySet = mapTemp.entrySet();
			for (Entry<DepartmentEntity, Set<String>> entry : entrySet) {
				Set<String> value = entry.getValue();
				List<SalesPlanningTable> salesPlanningTableList = new ArrayList<SalesPlanningTable>();
				for(SalesPlanningTable salesPlanningTable : salesPlanningTables){
					if(value.contains(salesPlanningTable.getAreaDepartmentId().toString())){
						salesPlanningTableList.add(salesPlanningTable);
					}
				}
				fenzhongxinMap.put(entry.getKey().getDepartmentName(), salesPlanningTableList);
			}
		}
		//判断fenzhongxinMap的value是否是空
		Set<Entry<String, List<SalesPlanningTable>>> keySet = fenzhongxinMap.entrySet();
		Iterator iterator = keySet.iterator();
		while (iterator.hasNext()) {
			Map.Entry next = (Map.Entry)iterator.next();
			if(CommonUtil.isEmpty((List)next.getValue())){
				iterator.remove();
			}
		}
		//排序
		List<Map.Entry<String, List<SalesPlanningTable>>> list = new ArrayList<Map.Entry<String,List<SalesPlanningTable>>>();
		Collections.sort(list, new Comparator<Map.Entry<String, List<SalesPlanningTable>>>(){

			@Override
			public int compare(Entry<String, List<SalesPlanningTable>> o1,
					Entry<String, List<SalesPlanningTable>> o2) {
				return o1.getKey().compareTo(o2.getKey());
			}

		});
		
		List<Map<String, List<SalesPlanningTable>>> total = new ArrayList<Map<String,List<SalesPlanningTable>>>();
		total.add(fenzhongxinMap);
		modelAndView.addObject("total", total);
		//modelAndView.addObject("salesPlanningTables", salesPlanningTables);
		modelAndView.addObject("selectYear", year + "年");

		/*大区销售计划*/
		modelAndView.addObject("regions", DepartmentUtil.retMapByCategory.get("region"));
		modelAndView.addObject("subs", DepartmentUtil.retMapByCategory.get("subs"));
		modelAndView.addObject("departs", DepartmentUtil.retMapByCategory.get("departs"));
		modelAndView.setViewName("/jsp/rc/sales/salesPlanningTable_multiple.jsp");
		modelAndView.addObject("departmentMap", DepartmentUtil.departmentMap);
		return modelAndView;
	}

	@RequestMapping(value = "getComboYear")
	public @ResponseBody
	List<Combobox> getComboYear() {
		// 区当前日期向后倒4年
		Calendar calendar0 = Calendar.getInstance();
		calendar0.set(2011, 0, 1);
		Calendar calendar1 = Calendar.getInstance();
		int year0 = calendar0.get(calendar0.YEAR);
		int year1 = calendar1.get(calendar1.YEAR);
		int year2 = year1 + 5;
		List<Combobox> comboboxs = new ArrayList<Combobox>();
		for (int i = year0; i < year2; i++) {
			Combobox combobox = new Combobox();
			combobox.setCode(String.valueOf(i));
			combobox.setValue(i + "年");
			if (i == year1) {
				combobox.setSelected(true);
			} else {
				combobox.setSelected(false);
			}
			comboboxs.add(combobox);
		}
		return comboboxs;
	}

	@RequestMapping(value = "getComboAreaDepartments")
	public @ResponseBody
	List<Combobox> getComboAreaDepartments(HttpSession session) {
		List<String> authList = SpringSecurityUtils.getAuthList(session);
		Map<String, String> quanxianmap = new HashMap<String, String>();
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
		quanxianmap.put("authList", sqlsid);
		List<Combobox> comboboxs = new ArrayList<Combobox>();
		Combobox comboboxFirst = new Combobox();
		comboboxFirst.setCode("");
		comboboxFirst.setValue("全部");
		comboboxFirst.setSelected(true);
		comboboxs.add(comboboxFirst);
		List<DepartmentEntity> departmentEntities = statisticalinfoService.queryDepartmentEntities(quanxianmap);
		if (CommonUtil.isNotEmpty(departmentEntities)) {
			// 重载sort方法
			Collections.sort(departmentEntities, new Comparator<DepartmentEntity>() {
				@Override
				public int compare(DepartmentEntity o1, DepartmentEntity o2) {
					// TODO Auto-generated method stub
					return o1.getDepartmentId().compareTo(o2.getDepartmentId());
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

	@RequestMapping(value = "saveAll")
	public @ResponseBody
	Message saveAll(SalesPlanningTableVo salesPlanningTableVo) {
		Message message = new Message();
		message = salesPlanningService.saveSalesPlanningTableVo(salesPlanningTableVo);
		return message;
	}

	/** 以上为销售计划方法 end **/

	/** 以下为滚动预测方法 begin **/
	@RequestMapping(value = "rollingForecastJSP")
	public ModelAndView rollingForecastJSP(HttpSession session) {
		List<String> authList = SpringSecurityUtils.getAuthList(session);
		Map<String, String> quanxianmap = new HashMap<String, String>();
		Map<DepartmentEntity, Set<String>> mapTemp = new HashMap<DepartmentEntity, Set<String>>();//临时存放，分中心和营业部
		Map<String, List<RollingForecastVo>> rollingForecastVos0Map = new HashMap<String, List<RollingForecastVo>>();//存放分中心和营业部(放款量)
		Map<String, List<RollingForecastVo>> rollingForecastVos1Map = new HashMap<String, List<RollingForecastVo>>();//存放分中心和营业部(合同金额)
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
		quanxianmap.put("authList", sqlsid);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/jsp/rc/sales/rollingForecast.jsp");
		// 获取当前角色
		String role = null;
		Set<String> userAuthoritiesSet = smpWSUtil.getUserAuthorities();
		if (userAuthoritiesSet.contains("ROLE_DAIZHONGFUWU") || userAuthoritiesSet.contains("ROLE_QVYUJINGLI")) {// 如果当前角色是营业部经理
			role = "0";// 代表更高权限
		} else {
			role = "1";// 代表营业部经理
		}
		// 获取当前日期
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
		// 获得25日
		Calendar calendar25 = Calendar.getInstance();
		calendar25.set(year, month - 1, 25);
		boolean flag25 = false;// false:没到或正好25.true:25已过
		if (calendar.compareTo(calendar25) > 0) {
			flag25 = true;
		}
		System.out.println(year + "年" + month + "月");
		String thisYearAndMonth = year + "年" + month + "月";
		String areaDepartmentIds = null;
		List<DepartmentEntity> departmentEntities = new ArrayList<DepartmentEntity>();

		Set<Entry<String, DepartmentEntityVo>> entries = DepartmentUtil.getDepartmentMapByAuthority().entrySet();
		Map<String, DepartmentEntityVo> departmentMap = DepartmentUtil.departmentMap;
		Set<Entry<String,DepartmentEntityVo>> entrySet2 = departmentMap.entrySet();
		
		
		for (Entry<String, DepartmentEntityVo> entry : entries) {
			DepartmentEntityVo departmentEntityVo = entry.getValue();
            /*过滤大区*/
            if(!departmentEntityVo.getDepartmentName().endsWith("区")){
                if (CommonUtil.isEmpty(departmentEntityVo.getDepartmentIds())) {
                    // DepartmentEntity departmentEntity = new DepartmentEntity();
                    // departmentEntity.setDepartmentId(departmentEntityVo.getDepartmentId());
                    // departmentEntity.setDepartmentName(departmentEntityVo.getDepartmentName());
                    // departmentEntities.add(departmentEntity);

                    departmentEntities.add(departmentEntityVo);
                    //添加分中心************
                    for (Entry<String, DepartmentEntityVo> entry2 : entrySet2) {
                        String departmentName = entry2.getValue().getDepartmentName();
                        if (CommonUtil.isNotEmpty(entry2.getValue().getDepartmentIds())){
                            if(!"0".equals(entry2.getValue().getDepartmentId())){
                                List<String> departmentIds = entry2.getValue().getDepartmentIds();
                                Set<String> departmentSet = new HashSet<String>(departmentIds);
                                if(departmentSet.contains(departmentEntityVo.getDepartmentId())){
                                    DepartmentEntity departmentEntity = new DepartmentEntity();
                                    departmentEntity.setDepartmentId(entry2.getValue().getDepartmentId());
                                    departmentEntity.setDepartmentName(entry2.getValue().getDepartmentName());
                                    Set<String> departmentSet2 = new HashSet<String>(entry2.getValue().getDepartmentIds());
                                    mapTemp.put(departmentEntity, departmentSet2);
                                }

                            }
                        }
                    }
                }else{//用于判断添加分中心
                    if(!"0".equals(departmentEntityVo.getDepartmentId())){
                        String getDepartmentId = departmentEntityVo.getDepartmentId();
                        String getDepartmentName = departmentEntityVo.getDepartmentName();
                        DepartmentEntity departmentEntity = new DepartmentEntity();
                        departmentEntity.setDepartmentId(getDepartmentId);
                        departmentEntity.setDepartmentName(getDepartmentName);
                        List<String> departmentIds = departmentEntityVo.getDepartmentIds();
                        Set<String> setTemp2 = new HashSet<String>(departmentIds);
                        mapTemp.put(departmentEntity, setTemp2);
                    }

                }
            }
		}

		// 重载sort方法
		Collections.sort(departmentEntities, new Comparator<DepartmentEntity>() {
			@Override
			public int compare(DepartmentEntity o1, DepartmentEntity o2) {
				// TODO Auto-generated method stub
				return o1.getDepartmentId().compareTo(o2.getDepartmentId());
			}
		});
		if (CommonUtil.isNotEmpty(departmentEntities)) {
			for (DepartmentEntity departmentEntity : departmentEntities) {
				areaDepartmentIds = areaDepartmentIds + "," + departmentEntity.getDepartmentId();
			}
		}
		Map<String, RollingForecastVo> rollingForecastVoMap = rollingForecastSerivce.queryForRollingForecastVoMap(year,
				month, areaDepartmentIds);
		// 放款量List
		List<RollingForecastVo> rollingForecastVos0 = new ArrayList<RollingForecastVo>();
		// 合同金额List
		List<RollingForecastVo> rollingForecastVos1 = new ArrayList<RollingForecastVo>();

		// 第一次循环找出type=“0” 的 数据
		for (DepartmentEntity departmentEntity : departmentEntities) {
			RollingForecastVo rollingForecastVo = rollingForecastVoMap.get(departmentEntity.getDepartmentId() + "|0");
			if (rollingForecastVo == null) {// 证明没有数据
				rollingForecastVo = new RollingForecastVo();

				if (flag25 && "0".equals(role)) {
					// 可编辑
					rollingForecastVo.setEditFlag("1");
				} else if (!flag25 && "1".equals(role)) {
					// 可编辑
					rollingForecastVo.setEditFlag("1");
				} else {
					// 不可编辑
					rollingForecastVo.setEditFlag("0");
				}
				rollingForecastVo.setAreaDepartmentId(Long.parseLong(departmentEntity.getDepartmentId()));
				rollingForecastVo.setAreaDepartmentName(departmentEntity.getDepartmentName());
				rollingForecastVo.setYear(year);
				rollingForecastVo.setMonth(month);
				rollingForecastVo.setType("0");
				rollingForecastVo.setHistoryFlag("F");
			} else {// 证明有数据
				if ("0".equals(role)) {
					// 可编辑
					rollingForecastVo.setEditFlag("1");
				}

			}
			rollingForecastVos0.add(rollingForecastVo);
		}
		//将数据按营业部分类********************
		Set<Entry<DepartmentEntity,Set<String>>> entrySet = mapTemp.entrySet();
		for (Entry<DepartmentEntity, Set<String>> entry : entrySet) {
			Set<String> value = entry.getValue();
			List<RollingForecastVo> salesPlanningTableList = new ArrayList<RollingForecastVo>();
			for(RollingForecastVo rollingForecastVo : rollingForecastVos0){
				if(value.contains(rollingForecastVo.getAreaDepartmentId().toString())){
					salesPlanningTableList.add(rollingForecastVo);
				}
			}
			rollingForecastVos0Map.put(entry.getKey().getDepartmentName(), salesPlanningTableList);
		}
		//*******************
		// 第二次循环找出type=“1” 的 数据
		for (DepartmentEntity departmentEntity : departmentEntities) {
			RollingForecastVo rollingForecastVo = rollingForecastVoMap.get(departmentEntity.getDepartmentId() + "|1");
			if (rollingForecastVo == null) {
				rollingForecastVo = new RollingForecastVo();
				if ("0".equals(role)) {
					// 可编辑
					rollingForecastVo.setEditFlag("1");
				} else {
					// 不可编辑
					rollingForecastVo.setEditFlag("0");
				}
				rollingForecastVo.setAreaDepartmentId(Long.parseLong(departmentEntity.getDepartmentId()));
				rollingForecastVo.setAreaDepartmentName(departmentEntity.getDepartmentName());
				rollingForecastVo.setYear(year);
				rollingForecastVo.setMonth(month);
				rollingForecastVo.setType("1");
				rollingForecastVo.setHistoryFlag("F");
			} else {
				if ("0".equals(role)) {
					// 可编辑
					rollingForecastVo.setEditFlag("1");
				} else {
					// 可编辑
					rollingForecastVo.setEditFlag("1");
				}
			}
			rollingForecastVos1.add(rollingForecastVo);
		}
		//将数据按营业部分类********************
		for (Entry<DepartmentEntity, Set<String>> entry : entrySet) {
			Set<String> value = entry.getValue();
			List<RollingForecastVo> rollingForecastVosList1 = new ArrayList<RollingForecastVo>();
			for(RollingForecastVo rollingForecastVo : rollingForecastVos1){
				if(value.contains(rollingForecastVo.getAreaDepartmentId().toString())){
					rollingForecastVosList1.add(rollingForecastVo);
				}
			}
			rollingForecastVos1Map.put(entry.getKey().getDepartmentName(), rollingForecastVosList1);
		}
		//分中心排序
		List<Map.Entry<String, List<RollingForecastVo>>> list = new ArrayList<Map.Entry<String,List<RollingForecastVo>>>();
		Collections.sort(list, new Comparator<Map.Entry<String, List<RollingForecastVo>>>() {

			@Override
			public int compare(Entry<String, List<RollingForecastVo>> o1,
					Entry<String, List<RollingForecastVo>> o2) {
				// TODO Auto-generated method stub
				return o1.getKey().compareTo(o2.getKey());
			}
		});
		
		//*******************
		List<Map<String, List<RollingForecastVo>>> rollingForecastVos0List = new ArrayList<Map<String,List<RollingForecastVo>>>();
		rollingForecastVos0List.add(rollingForecastVos0Map);
		List<Map<String, List<RollingForecastVo>>> rollingForecastVos1List = new ArrayList<Map<String,List<RollingForecastVo>>>();
		rollingForecastVos1List.add(rollingForecastVos1Map);
		modelAndView.addObject("rollingForecastVos0List", rollingForecastVos0List);
		modelAndView.addObject("rollingForecastVos1List", rollingForecastVos1List);
		modelAndView.addObject("rollingForecastVos0", rollingForecastVos0);
		modelAndView.addObject("rollingForecastVos1", rollingForecastVos1);
		modelAndView.addObject("thisYearAndMonth", thisYearAndMonth);
		calendar.add(Calendar.MONTH, 1);
		String firstYearAndMonth = calendar.get(Calendar.YEAR) + "年" + (calendar.get(Calendar.MONTH) + 1) + "月";
		calendar.add(Calendar.MONTH, 1);
		String secondYearAndMonth = calendar.get(Calendar.YEAR) + "年" + (calendar.get(Calendar.MONTH) + 1) + "月";
		calendar.add(Calendar.MONTH, 1);
		String thirdYearAndMonth = calendar.get(Calendar.YEAR) + "年" + (calendar.get(Calendar.MONTH) + 1) + "月";
		modelAndView.addObject("firstYearAndMonth", firstYearAndMonth);
		modelAndView.addObject("secondYearAndMonth", secondYearAndMonth);
		modelAndView.addObject("thirdYearAndMonth", thirdYearAndMonth);
		modelAndView.addObject("role", role);
		return modelAndView;
	}

	@RequestMapping(value = "saveRollingForecast")
	public @ResponseBody
	RollingForecastMessage saveRollingForecast(RollingForecast rollingForecast) {
		RollingForecastMessage rollingForecastMessage = new RollingForecastMessage();
		rollingForecast.setOperateTime(new Date());
		rollingForecast.setOperatorId(SpringSecurityUtils.getCurrentUser().getUserId());
		rollingForecast.setOperatorName(SpringSecurityUtils.getCurrentUser().getName_zh());
		String type = rollingForecast.getType();
		if ("1".equals(type)) {
			BigDecimal getFirstMonth = rollingForecast.getFirstMonth();
			BigDecimal getSecondMonth = rollingForecast.getSecondMonth();
			BigDecimal getThirdMonth = rollingForecast.getThirdMonth();
			if (getFirstMonth != null) {
				getFirstMonth = getFirstMonth.multiply(new BigDecimal(10000));
			}
			if (getSecondMonth != null) {
				getSecondMonth = getSecondMonth.multiply(new BigDecimal(10000));
			}

			if (getThirdMonth != null) {
				getThirdMonth = getThirdMonth.multiply(new BigDecimal(10000));
			}
			rollingForecast.setFirstMonth(getFirstMonth);
			rollingForecast.setSecondMonth(getSecondMonth);
			rollingForecast.setThirdMonth(getThirdMonth);
		}
		rollingForecastMessage = rollingForecastSerivce.saveRollingForecast(rollingForecast);
		return rollingForecastMessage;
	}

	@RequestMapping(value = "rorecastHistoryJSP")
	public ModelAndView rorecastHistoryJSP() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/jsp/rc/sales/rorecastHistory.jsp");
		Calendar calendar = Calendar.getInstance();
		modelAndView.addObject("ipym", calendar.get(Calendar.YEAR) + "年" + (calendar.get(Calendar.MONTH) + 1) + "月");
		return modelAndView;
	}

	@RequestMapping(value = "constructRorecastHistoryTable")
	public ModelAndView constructRorecastHistoryTable(String forecsatTimeBegin, String forecsatTimeEnd,
			String areaDepartmentIds, String editHistory, HttpSession session) {

		// 重新生成 areaDepartmentIds
		if (CommonUtil.isNotEmpty(areaDepartmentIds)) {

			String[] areaDepartmentIdArray = areaDepartmentIds.split(",");
			areaDepartmentIds = "";
			for (String string : areaDepartmentIdArray) {
				DepartmentEntityVo departmentEntityVo = DepartmentUtil.departmentMap.get(string);
				if (departmentEntityVo != null && CommonUtil.isEmpty(departmentEntityVo.getDepartmentIds())) {
					areaDepartmentIds = areaDepartmentIds + departmentEntityVo.getDepartmentId() + ",";
				}
			}
			areaDepartmentIds = areaDepartmentIds.substring(0, areaDepartmentIds.length() - 1);
		}

		List<String> authList = SpringSecurityUtils.getAuthList(session);
		Map<String, String> quanxianmap = new HashMap<String, String>();
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
		quanxianmap.put("authList", sqlsid);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/jsp/rc/sales/rorecastHistoryTable.jsp");
		System.out.println(forecsatTimeBegin);
		System.out.println(forecsatTimeEnd);
		System.out.println(areaDepartmentIds);
		System.out.println(editHistory);
		Calendar calendarBegin = Calendar.getInstance();
		Calendar calendarEnd = Calendar.getInstance();
		if (CommonUtil.isNotEmpty(forecsatTimeBegin)) {
			forecsatTimeBegin = forecsatTimeBegin.replaceAll("年", "-").replaceAll("月", "-") + "01";
			Date beginDate = DateUtil.stringConvertDate(forecsatTimeBegin, "yyyy-MM-dd");

			String[] beginArray = forecsatTimeBegin.split("-");
			calendarBegin.set(Integer.parseInt(beginArray[0]), Integer.parseInt(beginArray[1]) - 1,
					Integer.parseInt(beginArray[2]));
			System.out.println(beginDate);
			System.out.println(calendarBegin);
		}
		if (CommonUtil.isNotEmpty(forecsatTimeEnd)) {
			forecsatTimeEnd = forecsatTimeEnd.replaceAll("年", "-").replaceAll("月", "-") + "01";
			Date endDate = DateUtil.stringConvertDate(forecsatTimeEnd, "yyyy-MM-dd");
			String[] endArray = forecsatTimeEnd.split("-");
			calendarEnd.set(Integer.parseInt(endArray[0]), Integer.parseInt(endArray[1]) - 1,
					Integer.parseInt(endArray[2]));
			System.out.println(endDate);
			System.out.println(calendarEnd);
		}
		if (calendarBegin.compareTo(calendarEnd) > 0) {
			return modelAndView;
		}
		List<RollingForecastHistory> rollingForecastHistories0 = new ArrayList<RollingForecastHistory>();
		do {
			RollingForecastHistory rollingForecastHistory = new RollingForecastHistory();
			rollingForecastHistory.setThisYearAndMonth(calendarBegin.get(Calendar.YEAR) + "年"
					+ (calendarBegin.get(Calendar.MONTH) + 1) + "月");
			Calendar temp = Calendar.getInstance();
			temp.set(calendarBegin.get(Calendar.YEAR), calendarBegin.get(Calendar.MONTH), 1);
			temp.add(Calendar.MONTH, 1);
			rollingForecastHistory.setFirstMonths(temp.get(Calendar.YEAR) + "年" + (temp.get(Calendar.MONTH) + 1) + "月");
			temp.add(Calendar.MONTH, 1);
			rollingForecastHistory
					.setSecondMonths(temp.get(Calendar.YEAR) + "年" + (temp.get(Calendar.MONTH) + 1) + "月");
			temp.add(Calendar.MONTH, 1);
			rollingForecastHistory.setThirdMonths(temp.get(Calendar.YEAR) + "年" + (temp.get(Calendar.MONTH) + 1) + "月");
			calendarBegin.add(Calendar.MONTH, 1);
			rollingForecastHistories0.add(rollingForecastHistory);
		} while (calendarBegin.compareTo(calendarEnd) <= 0);

		List<RollingForecastHistory> rollingForecastHistories1 = new ArrayList<RollingForecastHistory>();
		for (RollingForecastHistory rollingForecastHistory : rollingForecastHistories0) {
			RollingForecastHistory forecastHistory = new RollingForecastHistory();
			forecastHistory.setThisYearAndMonth(rollingForecastHistory.getThisYearAndMonth());
			forecastHistory.setFirstMonths(rollingForecastHistory.getFirstMonths());
			forecastHistory.setSecondMonths(rollingForecastHistory.getSecondMonths());
			forecastHistory.setThirdMonths(rollingForecastHistory.getThirdMonths());
			rollingForecastHistories1.add(forecastHistory);
		}
		Map<String, String> queryMap = new HashMap<String, String>();

		if (CommonUtil.isEmpty(areaDepartmentIds)) {
			areaDepartmentIds = "";
			List<DepartmentEntity> departmentEntities = new ArrayList<DepartmentEntity>();

			Set<Entry<String, DepartmentEntityVo>> entries = DepartmentUtil.getDepartmentMapByAuthority().entrySet();
			for (Entry<String, DepartmentEntityVo> entry : entries) {
				DepartmentEntityVo departmentEntityVo = entry.getValue();
				if (CommonUtil.isEmpty(departmentEntityVo.getDepartmentIds())) {
					departmentEntities.add(departmentEntityVo);
				}
			}

			if (CommonUtil.isNotEmpty(departmentEntities)) {
				for (DepartmentEntity departmentEntity : departmentEntities) {
					areaDepartmentIds = areaDepartmentIds + departmentEntity.getDepartmentId() + ",";
				}
				areaDepartmentIds = areaDepartmentIds.substring(0, areaDepartmentIds.length() - 1);
			} else {
				modelAndView.setViewName("/jsp/rc/sales/saleMessage.jsp");
				return modelAndView;
			}
		}
		queryMap.put("forecsatTimeBegin", forecsatTimeBegin);
		queryMap.put("forecsatTimeEnd", forecsatTimeEnd);
		queryMap.put("areaDepartmentIds", areaDepartmentIds);
		queryMap.put("editHistory", editHistory);
		Map<String, RollingForecastHistory> rollingForecastHistoryMap = rollingForecastSerivce
				.queryRollingForecastMapForHistory(queryMap);

		// 第一次循环找出type=“0” 的 数据
		if (CommonUtil.isNotEmpty(rollingForecastHistories0)) {
			for (RollingForecastHistory forecastHistory : rollingForecastHistories0) {
				String thisYearAndMonth = forecastHistory.getThisYearAndMonth();
				String key = thisYearAndMonth + "|0";
				RollingForecastHistory history = rollingForecastHistoryMap.get(key);
				if (history != null) {
					forecastHistory.setYear(history.getYear());
					forecastHistory.setMonth(history.getMonth());
					forecastHistory.setType(history.getType());
					forecastHistory.setFirstMonthTolal(history.getFirstMonthTolal());
					forecastHistory.setSecondMonthTolal(history.getSecondMonthTolal());
					forecastHistory.setThirdMonthTolal(history.getThirdMonthTolal());
				} else {
					String temp = thisYearAndMonth.replaceAll("年", "-").replaceAll("月", "-") + "01";
					String[] tempArray = temp.split("-");
					forecastHistory.setYear(Integer.valueOf(tempArray[0]));
					forecastHistory.setMonth(Integer.valueOf(tempArray[1]));
					forecastHistory.setType("0");
				}
			}
		}
		// 第二次循环找出type=“1” 的 数据
		if (CommonUtil.isNotEmpty(rollingForecastHistories1)) {
			for (RollingForecastHistory forecastHistory : rollingForecastHistories1) {
				String thisYearAndMonth = forecastHistory.getThisYearAndMonth();
				String key = thisYearAndMonth + "|1";
				RollingForecastHistory history = rollingForecastHistoryMap.get(key);
				if (history != null) {
					forecastHistory.setYear(history.getYear());
					forecastHistory.setMonth(history.getMonth());
					forecastHistory.setType(history.getType());
					forecastHistory.setFirstMonthTolal(history.getFirstMonthTolal());
					forecastHistory.setSecondMonthTolal(history.getSecondMonthTolal());
					forecastHistory.setThirdMonthTolal(history.getThirdMonthTolal());
				} else {
					String temp = thisYearAndMonth.replaceAll("年", "-").replaceAll("月", "-") + "01";
					String[] tempArray = temp.split("-");
					forecastHistory.setYear(Integer.valueOf(tempArray[0]));
					forecastHistory.setMonth(Integer.valueOf(tempArray[1]));
					forecastHistory.setType("1");
				}
			}
		}
		modelAndView.addObject("rollingForecastHistories0", rollingForecastHistories0);
		modelAndView.addObject("rollingForecastHistories1", rollingForecastHistories1);
		return modelAndView;
	}

	@RequestMapping(value = "rollingForecastEditHistoryJSP")
	public ModelAndView rollingForecastEditHistoryJSP(Integer year, Integer month, String type, Long areaDepartmentId) {
		System.out.println(year);
		System.out.println(month);
		System.out.println(type);
		System.out.println(areaDepartmentId);
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month - 1, 1);
		String show = "";
		show = year + "年" + month + "月";
		if ("0".equals(type)) {
			show = show + "放款量（单位/笔）";
		} else if ("1".equals(type)) {
			show = show + "合同金额（单位/万元）";
		}
		String first = "";
		String second = "";
		String third = "";
		calendar.add(Calendar.MONTH, 1);
		first = calendar.get(Calendar.YEAR) + "年" + (calendar.get(Calendar.MONTH) + 1) + "月";
		calendar.add(Calendar.MONTH, 1);
		second = calendar.get(Calendar.YEAR) + "年" + (calendar.get(Calendar.MONTH) + 1) + "月";
		calendar.add(Calendar.MONTH, 1);
		third = calendar.get(Calendar.YEAR) + "年" + (calendar.get(Calendar.MONTH) + 1) + "月";
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/jsp/rc/sales/rollingForecastEditHistory.jsp");
		RollingForecast queryRollingForecast = new RollingForecast();
		queryRollingForecast.setYear(year);
		queryRollingForecast.setMonth(month);
		queryRollingForecast.setType(type);
		queryRollingForecast.setAreaDepartmentId(areaDepartmentId);
		List<RollingForecast> queryRollingForecasts = rollingForecastSerivce
				.queryRollingForecastEditHistory(queryRollingForecast);
		modelAndView.addObject("queryRollingForecasts", queryRollingForecasts);
		modelAndView.addObject("show", show);
		modelAndView.addObject("first", first);
		modelAndView.addObject("second", second);
		modelAndView.addObject("third", third);
		return modelAndView;
	}

	@RequestMapping(value = "rorecastHistoryDetailJSP")
	public ModelAndView rorecastHistoryDetailJSP(Integer year, Integer month, String type, String areaDepartmentIds,
			HttpSession session) {
		List<String> authList = SpringSecurityUtils.getAuthList(session);
		Map<String, String> quanxianmap = new HashMap<String, String>();
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
		quanxianmap.put("authList", sqlsid);
		System.out.println(areaDepartmentIds);
		if ("null".equals(areaDepartmentIds)) {
			areaDepartmentIds = null;
		}
		if (CommonUtil.isEmpty(areaDepartmentIds)) {

			List<DepartmentEntity> departmentEntities = new ArrayList<DepartmentEntity>();

			Set<Entry<String, DepartmentEntityVo>> entries = DepartmentUtil.getDepartmentMapByAuthority().entrySet();
			for (Entry<String, DepartmentEntityVo> entry : entries) {
				DepartmentEntityVo departmentEntityVo = entry.getValue();
				if (CommonUtil.isEmpty(departmentEntityVo.getDepartmentIds())) {
					departmentEntities.add(departmentEntityVo);
				}
			}

			if (CommonUtil.isNotEmpty(departmentEntities)) {
				for (DepartmentEntity departmentEntity : departmentEntities) {
					areaDepartmentIds = areaDepartmentIds + departmentEntity.getDepartmentId() + ",";
				}
				areaDepartmentIds = areaDepartmentIds.substring(0, areaDepartmentIds.length() - 1);
			}
		} else {
			String[] departmentIdArray = areaDepartmentIds.split(",");
			areaDepartmentIds = "";
			Map<String, DepartmentEntityVo> map = DepartmentUtil.getDepartmentMapByAuthority();
			for (String dapartmentId : departmentIdArray) {
				DepartmentEntityVo departmentEntityVo = map.get(dapartmentId);
				if (departmentEntityVo != null && CommonUtil.isEmpty(departmentEntityVo.getDepartmentIds())) {
					String getDepartmentId = departmentEntityVo.getDepartmentId();
					areaDepartmentIds = areaDepartmentIds + getDepartmentId + ",";
				}
			}
			areaDepartmentIds = areaDepartmentIds.substring(0, areaDepartmentIds.length() - 1);
		}
		System.out.println(areaDepartmentIds);
		System.out.println(areaDepartmentIds);
		System.out.println(areaDepartmentIds);
		System.out.println(areaDepartmentIds);
		System.out.println(areaDepartmentIds);
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month - 1, 1);
		String show = "";
		show = year + "年" + month + "月";
		String ym = show;
		if ("0".equals(type)) {
			show = show + "放款量（单位/笔）";
		} else if ("1".equals(type)) {
			show = show + "合同金额（单位/万元）";
		}
		String first = "";
		String second = "";
		String third = "";
		calendar.add(Calendar.MONTH, 1);
		first = calendar.get(Calendar.YEAR) + "年" + (calendar.get(Calendar.MONTH) + 1) + "月";
		calendar.add(Calendar.MONTH, 1);
		second = calendar.get(Calendar.YEAR) + "年" + (calendar.get(Calendar.MONTH) + 1) + "月";
		calendar.add(Calendar.MONTH, 1);
		third = calendar.get(Calendar.YEAR) + "年" + (calendar.get(Calendar.MONTH) + 1) + "月";
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/jsp/rc/sales/rorecastHistoryDetail.jsp");
		List<RollingForecast> rollingForecasts = new ArrayList<RollingForecast>();
		Map<String, String> getAllAreaDepartmentNameMap = smpWSUtil.getAllAreaDepartmentNameMap();
		Map<String, RollingForecast> rorecastHistoryDetailMap = rollingForecastSerivce.rorecastHistoryDetailMap(year,
				month, type, areaDepartmentIds);
		String[] areaDepartmentIdArray = areaDepartmentIds.split(",");
		List<String> areaDepartmentIdListTemp = Arrays.asList(areaDepartmentIdArray);
		List<String> areaDepartmentIdList = new ArrayList<String>();
		areaDepartmentIdList.addAll(areaDepartmentIdListTemp);
		Collections.sort(areaDepartmentIdList);
		// 构建一个departmentEntities
		List<DepartmentEntity> departmentEntities = new ArrayList<DepartmentEntity>();
		for (String areaDepartmentId : areaDepartmentIdList) {
			DepartmentEntity departmentEntity = new DepartmentEntity();

			String areaDepartmentName = getAllAreaDepartmentNameMap.get(areaDepartmentId);
			departmentEntity.setDepartmentId(areaDepartmentId);
			departmentEntity.setDepartmentName(areaDepartmentName);
			departmentEntities.add(departmentEntity);
		}
		for (DepartmentEntity departmentEntity : departmentEntities) {
			String getDepartmentIdKey = departmentEntity.getDepartmentId();
			RollingForecast rollingForecast = rorecastHistoryDetailMap.get(getDepartmentIdKey);
			if (rollingForecast == null) {
				rollingForecast = new RollingForecast();
				rollingForecast.setYear(year);
				rollingForecast.setMonth(month);
				rollingForecast.setAreaDepartmentId(Long.valueOf(departmentEntity.getDepartmentId()));
				rollingForecast.setAreaDepartmentName(departmentEntity.getDepartmentName());
				rollingForecast.setType(type);
			}
			rollingForecasts.add(rollingForecast);
		}
		modelAndView.addObject("rollingForecasts", rollingForecasts);
		modelAndView.addObject("show", show);
		modelAndView.addObject("ym", ym);
		modelAndView.addObject("first", first);
		modelAndView.addObject("second", second);
		modelAndView.addObject("third", third);
		return modelAndView;
	}

	@RequestMapping(value = "exportExcel")
	public @ResponseBody
	void exportExcel(String forecsatTimeBegin, String forecsatTimeEnd, String areaDepartmentIds,
			HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
		List<String> authList = SpringSecurityUtils.getAuthList(session);
		Map<String, String> quanxianmap = new HashMap<String, String>();
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
		quanxianmap.put("authList", sqlsid);
		response.setContentType("application/msexcel;charset=UTF-8");
		System.out.println(forecsatTimeBegin);
		System.out.println(forecsatTimeEnd);
		System.out.println(areaDepartmentIds);
		Calendar calendarBegin = Calendar.getInstance();
		Calendar calendarEnd = Calendar.getInstance();
		if (CommonUtil.isNotEmpty(forecsatTimeBegin)) {
			forecsatTimeBegin = forecsatTimeBegin.replaceAll("年", "-").replaceAll("月", "-") + "01";
			Date beginDate = DateUtil.stringConvertDate(forecsatTimeBegin, "yyyy-MM-dd");

			String[] beginArray = forecsatTimeBegin.split("-");
			calendarBegin.set(Integer.parseInt(beginArray[0]), Integer.parseInt(beginArray[1]) - 1,
					Integer.parseInt(beginArray[2]));
			System.out.println(beginDate);
			System.out.println(calendarBegin);
		}
		if (CommonUtil.isNotEmpty(forecsatTimeEnd)) {
			forecsatTimeEnd = forecsatTimeEnd.replaceAll("年", "-").replaceAll("月", "-") + "01";
			Date endDate = DateUtil.stringConvertDate(forecsatTimeEnd, "yyyy-MM-dd");
			String[] endArray = forecsatTimeEnd.split("-");
			calendarEnd.set(Integer.parseInt(endArray[0]), Integer.parseInt(endArray[1]) - 1,
					Integer.parseInt(endArray[2]));
			System.out.println(endDate);
			System.out.println(calendarEnd);
		}
		if (calendarBegin.compareTo(calendarEnd) > 0) {
			return;
		}
		Map<String, String> queryMap = new HashMap<String, String>();

		if (CommonUtil.isEmpty(areaDepartmentIds)) {
			areaDepartmentIds = null;
			List<DepartmentEntity> departmentEntities = new ArrayList<DepartmentEntity>();

			Set<Entry<String, DepartmentEntityVo>> entries = DepartmentUtil.getDepartmentMapByAuthority().entrySet();
			for (Entry<String, DepartmentEntityVo> entry : entries) {
				DepartmentEntityVo departmentEntityVo = entry.getValue();
				if (CommonUtil.isEmpty(departmentEntityVo.getDepartmentIds())) {
					departmentEntities.add(departmentEntityVo);
				}
			}
			if (CommonUtil.isNotEmpty(departmentEntities)) {
				for (DepartmentEntity departmentEntity : departmentEntities) {
					areaDepartmentIds = areaDepartmentIds + departmentEntity.getDepartmentId() + ",";
				}
				areaDepartmentIds = areaDepartmentIds.substring(0, areaDepartmentIds.length() - 1);
			} else {
				List<RollingForecastExcel> rollingForecastExcels = new ArrayList<RollingForecastExcel>();
				RollingForecastExcel excel = new RollingForecastExcel();
				rollingForecastExcels.add(excel);
				String title = "滚动预测列表";
				String[] hearder0 = new String[] { "预测日期", "下一个月", "下二个月", "下三个月" };
				String[] hearder1 = new String[] { "预测日期", "下一个月", "下二个月", "下三个月" };
				String[] fields0 = new String[] { "thisYearAndMonth", "firstMonthTolal0", "secondMonthTolal0",
						"thirdMonthTolal0" };
				String[] fields1 = new String[] { "thisYearAndMonth", "firstMonthTolal1", "secondMonthTolal1",
						"thirdMonthTolal1" };
				TableData td0 = ExcelUtils.createTableData(rollingForecastExcels,
						ExcelUtils.createTableHeader(hearder0), fields0, "放款量（笔）");
				TableData td1 = ExcelUtils.createTableData(rollingForecastExcels,
						ExcelUtils.createTableHeader(hearder1), fields1, "合同金额（万元）");
				List<TableData> tds = new ArrayList<TableData>();
				tds.add(td0);
				tds.add(td1);
			/*	JsGridReportBase report = new JsGridReportBase(request, response);
				report.exportToExcel(title, SpringSecurityUtils.getCurrentUserName(), tds);*/
                jsGridReportBaseXLSXService.jsGridReportBaseXLSXService(request,response);
                jsGridReportBaseXLSXService.exportToExcel(title, SpringSecurityUtils.getCurrentUserName(), tds);
            }
		}
		queryMap.put("forecsatTimeBegin", forecsatTimeBegin);
		queryMap.put("forecsatTimeEnd", forecsatTimeEnd);
		queryMap.put("areaDepartmentIds", areaDepartmentIds);
		Map<String, RollingForecastHistory> rollingForecastHistoryMap = rollingForecastSerivce
				.queryRollingForecastMapForHistory(queryMap);

		List<RollingForecastExcel> rollingForecastExcels = new ArrayList<RollingForecastExcel>();
		do {
			RollingForecastExcel rollingForecastExcel = new RollingForecastExcel();
			rollingForecastExcel.setThisYearAndMonth(calendarBegin.get(Calendar.YEAR) + "年"
					+ (calendarBegin.get(Calendar.MONTH) + 1) + "月");
			calendarBegin.add(Calendar.MONTH, 1);
			rollingForecastExcels.add(rollingForecastExcel);
		} while (calendarBegin.compareTo(calendarEnd) <= 0);

		if (CommonUtil.isNotEmpty(rollingForecastExcels)) {
			for (RollingForecastExcel forecastExcel : rollingForecastExcels) {
				String thisYearAndMonth = forecastExcel.getThisYearAndMonth();
				String key0 = thisYearAndMonth + "|0";
				String key1 = thisYearAndMonth + "|1";
				RollingForecastHistory history0 = rollingForecastHistoryMap.get(key0);
				RollingForecastHistory history1 = rollingForecastHistoryMap.get(key1);
				if (history0 != null) {
					forecastExcel.setFirstMonthTolal0(history0.getFirstMonthTolal());
					forecastExcel.setSecondMonthTolal0(history0.getSecondMonthTolal());
					forecastExcel.setThirdMonthTolal0(history0.getThirdMonthTolal());
				}
				if (history1 != null) {
					forecastExcel.setFirstMonthTolal1(history1.getFirstMonthTolal());
					forecastExcel.setSecondMonthTolal1(history1.getSecondMonthTolal());
					forecastExcel.setThirdMonthTolal1(history1.getThirdMonthTolal());
				}
			}
		}
		String title = "滚动预测列表";
		String[] hearder0 = new String[] { "预测日期", "下一个月", "下二个月", "下三个月" };
		String[] hearder1 = new String[] { "预测日期", "下一个月", "下二个月", "下三个月" };
		String[] fields0 = new String[] { "thisYearAndMonth", "firstMonthTolal0", "secondMonthTolal0",
				"thirdMonthTolal0" };
		String[] fields1 = new String[] { "thisYearAndMonth", "firstMonthTolal1", "secondMonthTolal1",
				"thirdMonthTolal1" };
		TableData td0 = ExcelUtils.createTableData(rollingForecastExcels, ExcelUtils.createTableHeader(hearder0),
				fields0, "放款量（笔）");
		TableData td1 = ExcelUtils.createTableData(rollingForecastExcels, ExcelUtils.createTableHeader(hearder1),
				fields1, "合同金额（万元）");
		List<TableData> tds = new ArrayList<TableData>();
		tds.add(td0);
		tds.add(td1);
	/*	JsGridReportBase report = new JsGridReportBase(request, response);
		report.exportToExcel(title, SpringSecurityUtils.getCurrentUserName(), tds);*/
        jsGridReportBaseXLSXService.jsGridReportBaseXLSXService(request,response);
        jsGridReportBaseXLSXService.exportToExcel(title, SpringSecurityUtils.getCurrentUserName(), tds);
    }

	@RequestMapping(value = "exportExcelDetail")
	public @ResponseBody
	void exportExcelDetail(Integer year, Integer month, String areaDepartmentIds, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception {
		List<String> authList = SpringSecurityUtils.getAuthList(session);
		Map<String, String> quanxianmap = new HashMap<String, String>();
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
		quanxianmap.put("authList", sqlsid);
		response.setContentType("application/msexcel;charset=UTF-8");
		System.out.println(year);
		System.out.println(month);
		System.out.println(areaDepartmentIds);
		if ("null".equals(areaDepartmentIds)) {
			areaDepartmentIds = null;
		}
		if (CommonUtil.isEmpty(areaDepartmentIds)) {
			areaDepartmentIds = "";
			List<DepartmentEntity> departmentEntities = new ArrayList<DepartmentEntity>();

			Set<Entry<String, DepartmentEntityVo>> entries = DepartmentUtil.getDepartmentMapByAuthority().entrySet();
			for (Entry<String, DepartmentEntityVo> entry : entries) {
				DepartmentEntityVo departmentEntityVo = entry.getValue();
				if (CommonUtil.isEmpty(departmentEntityVo.getDepartmentIds())) {
					departmentEntities.add(departmentEntityVo);
				}
			}
			if (CommonUtil.isNotEmpty(departmentEntities)) {
				for (DepartmentEntity departmentEntity : departmentEntities) {
					areaDepartmentIds = areaDepartmentIds + departmentEntity.getDepartmentId() + ",";
				}
				areaDepartmentIds = areaDepartmentIds.substring(0, areaDepartmentIds.length() - 1);
			}
		}
		System.out.println(areaDepartmentIds);
		System.out.println(areaDepartmentIds);
		System.out.println(areaDepartmentIds);
		System.out.println(areaDepartmentIds);
		System.out.println(areaDepartmentIds);
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, (month - 1), 1);
		String show = "";
		show = year + "年" + month + "月";
		String show0 = "";
		show0 = year + "年" + month + "月" + "放款量（单位笔）";
		String show1 = "";
		show1 = year + "年" + month + "月" + "合同金额（单位万元）";

		String first = "";
		String second = "";
		String third = "";
		calendar.add(Calendar.MONTH, 1);
		first = calendar.get(Calendar.YEAR) + "年" + (calendar.get(Calendar.MONTH) + 1) + "月";
		calendar.add(Calendar.MONTH, 1);
		second = calendar.get(Calendar.YEAR) + "年" + (calendar.get(Calendar.MONTH) + 1) + "月";
		calendar.add(Calendar.MONTH, 1);
		third = calendar.get(Calendar.YEAR) + "年" + (calendar.get(Calendar.MONTH) + 1) + "月";
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/jsp/rc/sales/rorecastHistoryDetail.jsp");
		List<RollingForecastVo> rollingForecasts0 = new ArrayList<RollingForecastVo>();
		List<RollingForecastVo> rollingForecasts1 = new ArrayList<RollingForecastVo>();
		Map<String, String> getAllAreaDepartmentNameMap = smpWSUtil.getAllAreaDepartmentNameMap();
		Map<String, RollingForecastVo> rollingForecastVoMap = rollingForecastSerivce.queryForRollingForecastVoMap(year,
				month, areaDepartmentIds);
		String[] areaDepartmentIdArray = areaDepartmentIds.split(",");
		List<String> areaDepartmentIdList = new ArrayList<String>();
		List<String> areaDepartmentIdListTemp = Arrays.asList(areaDepartmentIdArray);
		for (String tempId : areaDepartmentIdListTemp) {
			DepartmentEntityVo departmentEntityVo = DepartmentUtil.departmentMap.get(tempId);
			if (departmentEntityVo != null && CommonUtil.isEmpty(departmentEntityVo.getDepartmentIds())) {
				areaDepartmentIdList.add(departmentEntityVo.getDepartmentId());
			}
		}
		Collections.sort(areaDepartmentIdList);
		// 构建一个departmentEntities
		List<DepartmentEntity> departmentEntities = new ArrayList<DepartmentEntity>();
		for (String areaDepartmentId : areaDepartmentIdList) {
			DepartmentEntity departmentEntity = new DepartmentEntity();

			String areaDepartmentName = getAllAreaDepartmentNameMap.get(areaDepartmentId);
			departmentEntity.setDepartmentId(areaDepartmentId);
			departmentEntity.setDepartmentName(areaDepartmentName);
			departmentEntities.add(departmentEntity);
		}
		for (DepartmentEntity departmentEntity : departmentEntities) {
			String getDepartmentIdKey = departmentEntity.getDepartmentId() + "|0";
			RollingForecastVo rollingForecast = rollingForecastVoMap.get(getDepartmentIdKey);

			if (rollingForecast == null) {
				rollingForecast = new RollingForecastVo();
				rollingForecast.setYear(year);
				rollingForecast.setMonth(month);
				rollingForecast.setAreaDepartmentId(Long.valueOf(departmentEntity.getDepartmentId()));
				rollingForecast.setAreaDepartmentName(departmentEntity.getDepartmentName());

			}
			rollingForecast.setYearAndMonth(show);
			rollingForecasts0.add(rollingForecast);
		}
		for (DepartmentEntity departmentEntity : departmentEntities) {
			String getDepartmentIdKey = departmentEntity.getDepartmentId() + "|1";
			RollingForecastVo rollingForecast = rollingForecastVoMap.get(getDepartmentIdKey);
			if (rollingForecast == null) {
				rollingForecast = new RollingForecastVo();
				rollingForecast.setYear(year);
				rollingForecast.setMonth(month);
				rollingForecast.setAreaDepartmentId(Long.valueOf(departmentEntity.getDepartmentId()));
				rollingForecast.setAreaDepartmentName(departmentEntity.getDepartmentName());
			}
			rollingForecast.setYearAndMonth(show);
			rollingForecasts1.add(rollingForecast);
		}
		String title = show + "滚动预测明细";
		String[] hearder0 = new String[] { "预测日期", "营业部", first, second, third };
		String[] hearder1 = new String[] { "预测日期", "营业部", first, second, third };
		String[] fields0 = new String[] { "yearAndMonth", "areaDepartmentName", "firstMonth", "secondMonth",
				"thirdMonth" };
		String[] fields1 = new String[] { "yearAndMonth", "areaDepartmentName", "firstMonth", "secondMonth",
				"thirdMonth" };
		TableData td0 = ExcelUtils.createTableData(rollingForecasts0, ExcelUtils.createTableHeader(hearder0), fields0,
				show0);
		TableData td1 = ExcelUtils.createTableData(rollingForecasts1, ExcelUtils.createTableHeader(hearder1), fields1,
				show1);
		List<TableData> tds = new ArrayList<TableData>();
		tds.add(td0);
		tds.add(td1);
		/*JsGridReportBase report = new JsGridReportBase(request, response);
		report.exportToExcel(title, SpringSecurityUtils.getCurrentUserName(), tds);*/
        jsGridReportBaseXLSXService.jsGridReportBaseXLSXService(request,response);
        jsGridReportBaseXLSXService.exportToExcel(title, SpringSecurityUtils.getCurrentUserName(), tds);
    }

	/** 以上为滚动预测方法 end **/
	/** 预测对比 begin **/

	@RequestMapping(value = "showComparisonJSP")
	public ModelAndView showComparisonJSP(HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/jsp/rc/sales/forecastComparison.jsp");
		return modelAndView;
	}

	@RequestMapping(value = "showComparisonTable")
	public ModelAndView showComparisonTable(HttpSession session, String areaDepartmentIds) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/jsp/rc/sales/forecastComparisonTable.jsp");
		// 添加权限查询
		List<String> authList = SpringSecurityUtils.getAuthList(session);
		Map<String, String> quanxianmap = new HashMap<String, String>();
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
		quanxianmap.put("authList", sqlsid);
		Map<String, String> quertyMap = new HashMap<String, String>();
		Calendar calendarS = Calendar.getInstance();
		calendarS.set(calendarS.get(Calendar.YEAR), 0, 1);
		Calendar calendarE = Calendar.getInstance();
		calendarE.set(Calendar.DATE, 1);
		System.out.println(calendarE);
		System.out.println(calendarE);
		System.out.println(calendarE);
		System.out.println(calendarE);
// if (CommonUtil.isEmpty(areaDepartmentIds)) {
// areaDepartmentIds = "null";
// }
		if ("null".equals(areaDepartmentIds)) {
			areaDepartmentIds = null;
		}
		if (CommonUtil.isEmpty(areaDepartmentIds)) {
			areaDepartmentIds = "";
			List<DepartmentEntity> departmentEntities = new ArrayList<DepartmentEntity>();

			Set<Entry<String, DepartmentEntityVo>> entries = DepartmentUtil.getDepartmentMapByAuthority().entrySet();
			for (Entry<String, DepartmentEntityVo> entry : entries) {
				DepartmentEntityVo departmentEntityVo = entry.getValue();
				if (CommonUtil.isEmpty(departmentEntityVo.getDepartmentIds())) {
					departmentEntities.add(departmentEntityVo);
				}
			}
			if (CommonUtil.isNotEmpty(departmentEntities)) {
				for (DepartmentEntity departmentEntity : departmentEntities) {
					areaDepartmentIds = areaDepartmentIds + departmentEntity.getDepartmentId() + ",";
				}
				areaDepartmentIds = areaDepartmentIds.substring(0, areaDepartmentIds.length() - 1);
			}
		}
		System.out.println(areaDepartmentIds);
		System.out.println(areaDepartmentIds);
		System.out.println(areaDepartmentIds);
		System.out.println(areaDepartmentIds);
		System.out.println(areaDepartmentIds);
		String calS = calendarS.get(Calendar.YEAR) + "-" + (calendarS.get(Calendar.MONTH) + 1) + "-01";
		String calE = calendarE.get(Calendar.YEAR) + "-" + (calendarE.get(Calendar.MONTH) + 1) + "-01";
		quertyMap.put("authList", sqlsid);
		quertyMap.put("areaDepartmentIds", areaDepartmentIds);
		quertyMap.put("calS", calS);
		quertyMap.put("calE", calE);
		List<LoanRealAndPlanning> loanRealAndPlannings = statisticalinfoService.queryLoanRealAndPlanningList(quertyMap);
		BigDecimal rAmountTotal = new BigDecimal("0");// 实际合同金额
		BigDecimal pAmountTotal = new BigDecimal("0");// 计划合同金额
		BigDecimal rCountTotal = new BigDecimal("0");// 实际放款量
		BigDecimal pCountTotal = new BigDecimal("0");// 计划放款量
		for (LoanRealAndPlanning loanRealAndPlanning : loanRealAndPlannings) {
			rAmountTotal = rAmountTotal.add(loanRealAndPlanning.getrAmount() == null ? new BigDecimal("0")
					: loanRealAndPlanning.getrAmount());
			pAmountTotal = pAmountTotal.add(loanRealAndPlanning.getpAmount() == null ? new BigDecimal("0")
					: loanRealAndPlanning.getpAmount());
			BigDecimal amountRatioTotal = new BigDecimal(0);
			if (pAmountTotal.compareTo(new BigDecimal(0)) != 0) {
				amountRatioTotal = rAmountTotal.divide(pAmountTotal, 4, BigDecimal.ROUND_HALF_EVEN);
			}
			loanRealAndPlanning.setAmountRatioTotal(amountRatioTotal);
			rCountTotal = rCountTotal.add(new BigDecimal(loanRealAndPlanning.getrCount() == null ? 0
					: loanRealAndPlanning.getrCount()));
			pCountTotal = pCountTotal.add(new BigDecimal(loanRealAndPlanning.getpCount() == null ? 0
					: loanRealAndPlanning.getpCount()));
			BigDecimal countRatioTotal = new BigDecimal(0);
			if (pCountTotal.compareTo(new BigDecimal(0)) != 0) {
				countRatioTotal = rCountTotal.divide(pCountTotal, 4, BigDecimal.ROUND_HALF_EVEN);
			}
			loanRealAndPlanning.setCountRatioTotal(countRatioTotal);
			BigDecimal rAmount = loanRealAndPlanning.getrAmount();
			BigDecimal pAmount = loanRealAndPlanning.getpAmount();
			BigDecimal amountRatio = new BigDecimal(0);
			if (pAmount != null && pAmount.compareTo(new BigDecimal(0)) != 0) {
				amountRatio = rAmount.divide(pAmount, 4, BigDecimal.ROUND_HALF_EVEN);
			}
			loanRealAndPlanning.setAmountRatio(amountRatio);
			BigDecimal rCount = new BigDecimal(loanRealAndPlanning.getrCount() == null ? 0
					: loanRealAndPlanning.getrCount());
			BigDecimal pCount = new BigDecimal(loanRealAndPlanning.getpCount() == null ? 0
					: loanRealAndPlanning.getpCount());
			BigDecimal countRatio = new BigDecimal(0);
			if (pCount.compareTo(new BigDecimal(0)) != 0) {
				countRatio = rCount.divide(pCount, 4, BigDecimal.ROUND_HALF_EVEN);
			}
			loanRealAndPlanning.setCountRatio(countRatio);
		}
		modelAndView.addObject("loanRealAndPlannings", loanRealAndPlannings);
		return modelAndView;
	}

	/** 预测对比 end **/
	/** test **/
	@RequestMapping(value = "synchronizationLoan")
	public @ResponseBody
	Message synchronizationLoan() {
		Message message = new Message();
		Long creditapplicationId = null;
		String officeId = null;
		String sellerId = null;
		message = laonBalanceDataService.insertSynchronizationLoan(creditapplicationId, officeId, sellerId);
		return message;
	}

	@RequestMapping(value = "_salesPlanningTable")
	public ModelAndView _salesPlanningTable(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/jsp/rc/sales/salesPlanning.jsp");

		/*根据当前登录人获取不同的菜单数据*/
		Set<String> userAuthoritiesSet = smpWSUtil.getUserAuthorities();
		String me = null;
		List<DepartmentEntityVo> seeByMeEntity = new ArrayList<DepartmentEntityVo>((List<DepartmentEntityVo>)DepartmentUtil.retMapByCategory.get("subs"));
		List<DepartmentEntityVo> seeByMeEntity_depart = new ArrayList<DepartmentEntityVo>((List<DepartmentEntityVo>)DepartmentUtil.retMapByCategory.get("departs"));
		List<DepartmentEntityVo> seeByMeEntity_region = new ArrayList<DepartmentEntityVo>((List<DepartmentEntityVo>)DepartmentUtil.retMapByCategory.get("region"));
		if (userAuthoritiesSet.contains("ROLE_SALES_ADMIN")) {
			me = "admin";
		} else {
			Map<String, DepartmentEntityVo> seeByMeMap = easyUIService.getDepartmentMapByAuthority();
			List<String> regionIds = (List<String>) DepartmentUtil.retMapByCategory.get("regionIds");
			seeByMeEntity.clear();
			seeByMeEntity_depart.clear();
            DepartmentEntityVo entity;
			for (DepartmentEntityVo vo : seeByMeMap.values()) {
				if (!regionIds.contains(vo.getDepartmentId())) {
					entity = new DepartmentEntityVo();
					entity.setDepartmentId(vo.getDepartmentId());
					entity.setDepartmentName(vo.getDepartmentName());
					if (null != vo.getDepartmentIds()) {
						seeByMeEntity.add(entity);
					} else {
						seeByMeEntity_depart.add(entity);
					}
				}
			}
			if (userAuthoritiesSet.contains("ROLE_REGION")) {
				me = "regionMgr";
			} else if (userAuthoritiesSet.contains("ROLE_QVYUJINGLI")) {
				me = "cityMgr";
			}
		}
        int s_year = StringUtils.isNotEmpty(request.getParameter("year")) ? Integer.valueOf(request.getParameter("year")) : Calendar.getInstance().get(Calendar.YEAR);
        String s_departmentIds = StringUtils.isNotEmpty(request.getParameter("departmentIds")) ? request.getParameter("departmentIds") : "";

        if (StringUtils.isNotEmpty(s_departmentIds)) {
            String[] arr = s_departmentIds.split(",");

            List<String> a2l = Arrays.asList(arr);
            List<String> regionIds = (List<String>)DepartmentUtil.retMapByCategory.get("regionIds");
            List<String> intersection = (List<String>)CollectionUtils.intersection(a2l, regionIds);
            if(!intersection.isEmpty()){
                for(String id : intersection){
                    for(String id1 : DepartmentUtil.departmentMap.get(id).getDepartmentIds()){
                        s_departmentIds += "," + id1;
                        for(String id2 : DepartmentUtil.departmentMap.get(id1).getDepartmentIds()){
                            s_departmentIds += "," + id2;
                        }
                    }
                }
            }

            List<DepartmentEntityVo> entities = new ArrayList<DepartmentEntityVo>();
            for (DepartmentEntityVo entity : seeByMeEntity) {
                for (String s : arr) {
                    if (entity.getDepartmentId().equals(s)) {
                        entities.add(entity);
                    }
                }
            }
            if(!entities.isEmpty()){
                seeByMeEntity.clear();
                seeByMeEntity.addAll(entities);
            }
            entities.clear();
            for (DepartmentEntityVo entity : seeByMeEntity_depart) {
                for (String s : arr) {
                    if (entity.getDepartmentId().equals(s)) {
                        entities.add(entity);
                    }
                }
            }
            if(!entities.isEmpty()){
                seeByMeEntity_depart.clear();
                seeByMeEntity_depart.addAll(entities);
            }
            entities.clear();
            for (DepartmentEntityVo entity : seeByMeEntity_region) {
                for (String s : arr) {
                    if (entity.getDepartmentId().equals(s)) {
                        entities.add(entity);
                    }
                }
            }
            if(!entities.isEmpty()){
                seeByMeEntity_region.clear();
                seeByMeEntity_region.addAll(entities);
            }
        }

        modelAndView.addObject("map_loan", salesPlanningService.searchSalesPlanningByCase(new SalesPlanning(s_year, "0", s_departmentIds)));
        modelAndView.addObject("map_contract", salesPlanningService.searchSalesPlanningByCase(new SalesPlanning(s_year, "1", s_departmentIds)));

        modelAndView.addObject("departmentMap", DepartmentUtil.departmentMap);

        modelAndView.addObject("subs", seeByMeEntity);
		modelAndView.addObject("departs", seeByMeEntity_depart);
        modelAndView.addObject("regions", seeByMeEntity_region);

		modelAndView.addObject("me", me);
        modelAndView.addObject("from", !s_departmentIds.equals("") ? s_departmentIds : "");

		return modelAndView;
	}

    @RequestMapping(value = "saveSalesPlanning")
    public
    @ResponseBody
    Message saveSalesPlanning() {
        return salesPlanningService.saveSalesPlanning();
    }

    @RequestMapping(value = "_getComboYear")
    public @ResponseBody
    List<Combobox> _getComboYear() {
        // 区当前日期向后倒4年
        Calendar calendar0 = Calendar.getInstance();
        calendar0.set(2011, 0, 1);
        Calendar calendar1 = Calendar.getInstance();
        int year0 = calendar0.get(calendar0.YEAR);
        int year1 = calendar1.get(calendar1.YEAR);
        int year2 = year1 + 5;
        List<Combobox> comboboxs = new ArrayList<Combobox>();
        for (int i = year0; i < year2; i++) {
            Combobox combobox = new Combobox();
            combobox.setCode(String.valueOf(i));
            combobox.setValue(i + "年");
            if (i == year1) {
//                combobox.setSelected(true);
            } else {
                combobox.setSelected(false);
            }
            comboboxs.add(combobox);
        }
        return comboboxs;
    }
}
