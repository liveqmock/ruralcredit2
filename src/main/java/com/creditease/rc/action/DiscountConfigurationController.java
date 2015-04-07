package com.creditease.rc.action;

import com.creditease.rc.app.pdf.*;
import com.creditease.rc.app.pdf.QueryProByDepartResult.ProInfoList;
import com.creditease.rc.app.pdf.QueryProByDepartResult.ProInstList;
import com.creditease.rc.app.smp.EmployeeDTO;
import com.creditease.rc.domain.CreditChannelConfig;
import com.creditease.rc.domain.DiscountConfiguration;
import com.creditease.rc.domain.Message;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.service.IDiscountConfigurationService;
import com.creditease.rc.util.CommonUtil;
import com.creditease.rc.util.JsonUtil;
import com.creditease.rc.util.SmpWSUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("discountConfigurationController")
public class DiscountConfigurationController {

	@Resource
	private IDiscountConfigurationService discountConfigurationService;

	@Resource
	private CeBorrowingProductsWS ceBorrowingProductsWS;

	@Resource
	private SmpWSUtil smpWSUtil;

	private Logger log = Logger.getLogger(CreditApplicationController.class);

	@RequestMapping(value = "returnDiscountConfigurationJSP")
	public ModelAndView returnDiscountConfigurationJSP(HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/jsp/rc/system/discountConfiguration.jsp");
		return modelAndView;
	}

	@RequestMapping(value = "discountConfigurationDateGrid")
	public @ResponseBody
	Pagination discountConfigurationDateGrid(@RequestParam(required = false) String page,
			@RequestParam(required = false) String rows, DiscountConfiguration discountConfiguration,
			HttpSession session) {
		Pagination pagination = new Pagination();
		if (!StringUtils.isBlank(page)) {
			pagination.setPage(Integer.valueOf(page));
		}
		if (!StringUtils.isBlank(rows)) {
			pagination.setPageSize(Integer.valueOf(rows));
		}
		pagination = discountConfigurationService.queryDiscountConfigurationList(discountConfiguration, pagination);
		return pagination;
	}

	@RequestMapping(value = "saveDiscountConfiguration")
	public @ResponseBody
	Message saveDiscountConfiguration(DiscountConfiguration discountConfiguration) {
		Message message = new Message();
		BigDecimal getDiscountRate = discountConfiguration.getDiscountRate();
		if (getDiscountRate != null) {
			getDiscountRate = getDiscountRate.divide(new BigDecimal(100));
			discountConfiguration.setDiscountRate(getDiscountRate);
		}
		message = discountConfigurationService.saveDiscountConfiguration(discountConfiguration);
		return message;
	}

	@RequestMapping(value = "removeDiscountConfigurationByDiscountConfigurationId")
	public @ResponseBody
	Message removeDiscountConfigurationByDiscountConfigurationId(Long discountConfigurationId) {
		Message message = new Message();
		message = discountConfigurationService
				.removeDiscountConfigurationByDiscountConfigurationId(discountConfigurationId);
		return message;
	}

	@RequestMapping(value = "discountRule")
	public @ResponseBody
	Message discountRule(Long creditapplicationId) {
		return discountConfigurationService.discountRule(creditapplicationId);
	}

	@RequestMapping(value = "checkDiscountConfiguration")
	public @ResponseBody
	Map<String, String> checkDiscountConfiguration(Long creditapplicationId) {
		Map<String, String> map = discountConfigurationService.checkDiscountConfiguration(creditapplicationId);
		return map;
	}

	/** ====================================================================================================================================== **/
	/**
	 * 查询产品列表
	 *
	 * @return 查询产品列表
	 */
	@RequestMapping(value = "selectProInfo")
	public @ResponseBody
	List<Map> selectProInfo(String areaDepartmentId) {

		QueryProByDepartResult queryProByDepartResult = null;
		List<Map> list = new ArrayList<Map>();
		List<ProInfo> proInfos = null;
		List<ProductInst> productInsts = null;
		// 登录人的部门id

		if (CommonUtil.isEmpty(areaDepartmentId)) {
			System.out.println("areaDepartmentId是Null！");
			return list;
		}
		List<EmployeeDTO> employeeDTOs = smpWSUtil.getEmployeesListByRole_DepartmentId(areaDepartmentId,
				"ROLE_LOAN_OFFICER");

		if (CommonUtil.isEmpty(employeeDTOs)) {
			System.out.println("该营业部下没有信贷员！");
			return list;
		}
		int departMentId = 0;
		for (EmployeeDTO employeeDTO : employeeDTOs) {
			Integer getComEmpId = employeeDTO.getComEmpId();
			EmployeeDTO temp = smpWSUtil.getEmployee(getComEmpId.toString());
			Integer getDepartmentId = temp.getDepartmentId();
			System.out.println(getDepartmentId);
			if (getDepartmentId != null) {
				departMentId = getDepartmentId;
				break;
			}
		}
		log.info("ceBorrowingProductsWS.queryProByDepart(departMentId) request_params:******");
		log.info(JsonUtil.getJackson(departMentId));
		ProListParam proListParam = new ProListParam();
		proListParam.setDepartmentId(departMentId);
		queryProByDepartResult = ceBorrowingProductsWS.queryProByDepart(proListParam);
		if (null != queryProByDepartResult) {
			log.info("ceBorrowingProductsWS.queryProByDepart(departMentId) response_params:******");
			log.info(JsonUtil.getJackson(queryProByDepartResult));
			if ("0".equals(queryProByDepartResult.getResultCode())) {
				ProInfoList proInfoList = queryProByDepartResult.getProInfoList();
				ProInstList proInstList = queryProByDepartResult.getProInstList();

				if (null != proInfoList) {
					proInfos = proInfoList.getProInfo();
					if (proInfos.size() == 0) {
						System.out.println("proInfoList中的List<ProInfo> 长度是 0：proInfoList.getProInfoList():"
								+ proInfoList.getProInfo());
					}
					productInsts = proInstList.getProductInst();
					if (productInsts.size() == 0) {
						System.out.println("proInfoList中的List<ProductInst> 长度是 0：proInstList.getProductInst():"
								+ proInstList.getProductInst());
					}
                    //存储产品类型大类
                    Map<String,String> cateGoryIds = new HashMap<String,String>();
                    Map<Long,Map> catas = new HashMap<Long, Map>();
                    //循环产品类型大类
                    for (int i = 0; i < proInfos.size(); i++) {
                        ProInfo proInfo =  proInfos.get(i);
                        //循环产品
                        for (int j = 0; j < productInsts.size(); j++) {
                            if (proInfos.get(i).getProductInfoId().equals(productInsts.get(j).getProductInfoId())) {
                                //循环产品 把产品的还款方式添加到对应的产品大类上
                                if(catas.containsKey(proInfo.getProductInfoId())){
                                    //   大类已经包含 循环拼接出期数   还款方式
                                    Map map = catas.get(proInfo.getProductInfoId());
                                    String   repaymentTypeStr = (String)map.get("repaymentType");
                                    repaymentTypeStr += "," +  productInsts.get(j).getRepaymentType();
                                    map.remove("repaymentType");
                                    map.put("repaymentType",repaymentTypeStr);

                                    catas.remove(proInfo.getProductInfoId())  ;
                                    catas.put(proInfo.getProductInfoId(),map);
                                }else{
                                    //大类未包含 存入数据里面
                                    Map product = new HashMap();
                                    product.put("productInfoId", proInfo.getProductInfoId());
                                    product.put("productName", proInfo.getProductName());
                                    product.put("instalments", proInfo.getInstalments());
                                    product.put("producttypeid", proInfo.getProductCategoryId()); // 产品分类编号
                                    product.put("repaymentType", productInsts.get(j).getDefaultRepaymentType());
                                    product.put("capitalUpperLimit", convertBigDecimal(proInfo
                                            .getCapitalUpperLimit()));
                                    product.put("capitalLowerLimit", convertBigDecimal(proInfo
                                            .getCapitalLowerLimit()));
                                    catas.put(proInfo.getProductInfoId(),product);
                                    //list.add(product);
                                }
                            }
                        }
                    }
                    //循环产品大类 放入返回列表
                    for(Map.Entry<Long,Map> entry:catas.entrySet()) {
                        list.add(entry.getValue());
                    }
                   // System.out.println(list.toString());
				} else {
					System.out.println("proInfoList 是 null：proInfoList:" + proInfoList);
				}
			} else {
				System.out.println("产品返回接口返回结果失败：queryProByDepartResult.getResultCode():"
						+ queryProByDepartResult.getResultCode());
			}
		} else {
			System.out.println("产品返回接口返回数据是null：ceBorrowingProductsWS.queryProByDepart:"
					+ ceBorrowingProductsWS.queryProByDepart(proListParam));
		}
		return list;
	};

	public String convertBigDecimal(BigDecimal decimal) {
		StringBuffer value = new StringBuffer();
		if (null == decimal) {
			value.append("0");
		} else {
			value.append(decimal.toString());
		}
		return value.toString();
	}

    /*查询所有营业部业务单渠道配置信息*/
    @RequestMapping(value = "creditChannelConfig")
    public
    @ResponseBody
    Pagination creditChannelConfig(String page, String rows, String sort, String order, HttpServletRequest req) {
        Pagination pagination = new Pagination();
        if (!StringUtils.isBlank(page)) {
            pagination.setPage(Integer.valueOf(page));
        }
        if (!StringUtils.isBlank(rows)) {
            pagination.setPageSize(Integer.valueOf(rows));
        }
        pagination = discountConfigurationService.queryCreditChannelConfig(req.getParameter("ids"), pagination);
        return pagination;
    }

	/*保存/更新 营业部业务单渠道配置信息*/
	@RequestMapping(value = "saveCreditChannelConfig")
	public
	@ResponseBody
	Message saveCreditChannelConfig(CreditChannelConfig config) {
		return discountConfigurationService.saveCreditChannelConfig(config);
	}

	/*删除营业部业务单渠道配置信息*/
	@RequestMapping(value = "removeCreditChannelConfigById")
	public
	@ResponseBody
	Message removeCreditChannelConfigById(Long configId) {
		Message message = discountConfigurationService.removeCreditChannelConfigById(configId);
		return message;
	}

	/*判断存在性 业务单渠道配置信息*/
	@RequestMapping(value = "checkExistOfConfig")
	public
	@ResponseBody
	boolean checkExistOfConfig(String departmentId) {
		return discountConfigurationService.checkExistOfConfig(departmentId);
	}

	/*查询业务单渠道配置信息*/
	@RequestMapping(value = "checkIfConfigureById")
	public
	@ResponseBody
	boolean checkIfConfigureById(Long creditApplicationId) {
		return StringUtils.isNotEmpty(discountConfigurationService.queryIsZhxindai(creditApplicationId)) ? true : false;
	}
}
