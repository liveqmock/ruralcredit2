package com.creditease.rc.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.creditease.core.security.SpringSecurityUtils;
import com.creditease.core.security.User;
import com.creditease.rc.service.ICreditApplicationService;
import com.creditease.rc.util.CommonUtil;
import com.creditease.rc.util.SmpWSUtil;
import com.creditease.rc.vo.CreditCoBorrowerSpe;
import com.creditease.rc.vo.IDInfoVo;
import net.sf.json.JSON;
import net.sf.json.util.JSONUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.creditease.rc.common.Constants;
import com.creditease.rc.service.IBorrowerApplicationService;
import com.creditease.rc.vo.HouseServeyVo;

/**
 * 个人申请单
 * 
 * @author zhangman
 * 
 */
@Controller
@RequestMapping(value = "application")
public class ApplicationContraller {
	@Resource
	private IBorrowerApplicationService borrowerApplicationService;
    @Resource
    private ICreditApplicationService creditApplicationService;
    @Resource
    private SmpWSUtil smpWSUtil;
	/**
	 * 
	 * @param binder
	 *            WebDataBinder对象
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dateFormat.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
		binder.registerCustomEditor(Date.class, "borrowerService.applyDate", new CustomDateEditor(dateFormat2, true));
	}

	/**
	 * 新曾
	 * 
	 * @param houseServeyVo 申请单 分装类
	 * @return boolean 成功失败
	 */
	@RequestMapping(value = "add")
	public @ResponseBody
	boolean addApplication(HouseServeyVo houseServeyVo) {
		// 循环贷判断修改到申请单全部保存2014.5.7

		try {
			// return borrowerApplicationService.addApplication(houseServeyVo, Constants.DO_SAVE_STATUS);
			return borrowerApplicationService.addApplicationIncludeRevolvingAndDiscount(houseServeyVo,
					Constants.DO_SAVE_STATUS);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("保存申请单信息失败");
			e.printStackTrace();
			return false;
		}
	};

	/**
	 * 暂存
	 * 
	 * @param houseServeyVo 申请单 分装类
	 * @return boolean 成功失败
	 */
	@RequestMapping(value = "tempAdd")
	public @ResponseBody
	boolean tempAddApplication(HouseServeyVo houseServeyVo) {
		try {
			return borrowerApplicationService.addApplication(houseServeyVo, Constants.TEMP_SAVE_STATUS);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("暂存申请单信息失败");
			e.printStackTrace();
			return false;
		}
	};

	/**
	 * 
	 * @param borrowerServiceAppId 借款id
	 * @return HouseServeyVo 申请单 分装类
	 */
	@RequestMapping(value = "select")
	public @ResponseBody
	HouseServeyVo selectApplication(Long borrowerServiceAppId) {
		return borrowerApplicationService.selectApplication(borrowerServiceAppId);
	};

	/**
	 * 
	 * @param borrowerServiceAppId 借款id
	 * @param businessNumber 业务单号
	 * @return ModelAndView 页面
	 */
	@RequestMapping(value = "selectHouseServeyVo")
	public @ResponseBody
	ModelAndView selectHouseServeyVo(Long borrowerServiceAppId, String businessNumber) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/jsp/rc/business/bakborrowerServiceApp.jsp");
		HouseServeyVo houseServeyVo = borrowerApplicationService.selectApplication(borrowerServiceAppId);
		if (houseServeyVo != null) {
			houseServeyVo.getBorrowerService().setBusinessNumber(businessNumber);
		}
		modelAndView.addObject(houseServeyVo);
		return modelAndView;
	};
    /**
     *   add by ygx
     * 信贷申请表初始化查询         是否试点营业部
     * @param borrowerServiceAppId 借款id
     * @param businessNumber 业务单号
     * @return ModelAndView 页面
     */
    @RequestMapping(value = "queryHouseServeyVo")
    public @ResponseBody
    ModelAndView queryHouseServeyVo(Long borrowerServiceAppId, String businessNumber) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/jsp/rc/business/bakborrowerServiceApp.jsp");
        HouseServeyVo houseServeyVo = borrowerApplicationService.queryApplicationVOInfo(borrowerServiceAppId);
        if (houseServeyVo != null) {
            houseServeyVo.getBorrowerService().setBusinessNumber(businessNumber);
        }
        String depId = String.valueOf(SpringSecurityUtils.getCurrentUser().getAreaDepartmentId());
        CreditCoBorrowerSpe creditCoBorrowerSpe = new CreditCoBorrowerSpe();
        creditCoBorrowerSpe.setSpecDep("false");
        if(CommonUtil.isEmpty(depId) || depId=="null") {

        }   else{
            if(borrowerApplicationService.isSpecDep(Long.valueOf(depId))){
                //是否试点营业部
                creditCoBorrowerSpe.setSpecDep("true");
            }
        }
        houseServeyVo.setCreditCoBorrowerSpe(creditCoBorrowerSpe);
        modelAndView.addObject(houseServeyVo);
        return modelAndView;
    };

    /**
     *  根据业务编号查询借款人身份证号 担保人身份证号 共借人身份证号 信息
     * @param businessNumber
     * @return
     */
    @RequestMapping(value = "queryIDInfoVo")
    public @ResponseBody
    ModelAndView queryIDInfoVo(String businessNumber) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/jsp/rc/system/idChange.jsp");
        IDInfoVo idInfoVo = borrowerApplicationService.queryIDInfoVo(businessNumber);
//        modelAndView.addObject(idInfoVo);
        modelAndView.addObject("idInfoVo", idInfoVo);
        return  modelAndView;
    };
    /**
     *  判断此业务单号是否存在
     * @param businessNumber
     * @return
     */
    @RequestMapping(value = "existThisBussinessNumber")
    public @ResponseBody
    boolean existThisBussinessNumber(String businessNumber) {
        try {
            int count =   creditApplicationService.queryCountByBussinessNumber(businessNumber.trim()) ;
            if(count>0){
                      return true;
            }else {
                return false;
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            System.out.println("查无此业务单号");
            e.printStackTrace();
            return false;
        }
    };
    /**
     *
     *保存借款人身份证号 担保人身份证号 共借人身份证号信息
     * @param idInfoVo
     * @return
     */
    @RequestMapping(value = "updateIdInfo")
    public @ResponseBody
    boolean updateIdInfo(IDInfoVo idInfoVo) {
        try {
            return borrowerApplicationService.updateIdInfo(idInfoVo);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            System.out.println("保存身份证信息失败");
            e.printStackTrace();
            return false;
        }
    };
	/**
	 * 显示申请单
	 * 
	 * @param borrowerServiceAppId 借款id
	 * @return ModelAndView 页面
	 */
	@RequestMapping(value = "showCreditApp")
	public @ResponseBody
    ModelAndView showCreditApp(HttpServletRequest request, Long borrowerServiceAppId) {
      //  HouseServeyVo serveyVo = borrowerApplicationService.selectApplication(borrowerServiceAppId);
        HouseServeyVo serveyVo = borrowerApplicationService.queryApplicationVOInfo(borrowerServiceAppId) ;
        Map<String, Object> model = new HashMap<String, Object>();
		if (serveyVo != null) {
			model.put("creditApplication", serveyVo.getCreditApplication());
			model.put("borrowerService", serveyVo.getBorrowerService());
			model.put("applicationList", serveyVo.getApplicationList());
			model.put("depositList", serveyVo.getDepositList());
			model.put("familymemberList", serveyVo.getFamilymemberList());
			model.put("jobInfoList", serveyVo.getJobInfoList());
			model.put("surveybusinessinfoList", serveyVo.getSurveybusinessinfoList());
            model.put("creditCoborrower", serveyVo.getCreditCoBorrower());
            /*合规检查：查看申请单信息时，添加查看申请单附件*/
            if (StringUtils.isNotEmpty(request.getParameter("showAttach"))) {
                model.put("showAttach", "yes");
            }
        }
		return new ModelAndView("/jsp/rc/business/borrowerServiceAppView.jsp", model);
	};

	/**
	 * 
	 * @param houseServeyVo 申请单 分装类
	 * @return boolean 成功失败
	 */
	@RequestMapping(value = "update")
	public @ResponseBody
	boolean updateApplication(HouseServeyVo houseServeyVo) {
		try {
			return borrowerApplicationService.updateApplication(houseServeyVo, Constants.DO_SAVE_STATUS);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	};

	/**
	 * 
	 * @param houseServeyVo 申请单 分装类
	 * @return boolean 成功失败
	 */
	@RequestMapping(value = "tempUpdate")
	public @ResponseBody
	boolean tempUpdateApplication(HouseServeyVo houseServeyVo) {
		try {
			return borrowerApplicationService.updateApplication(houseServeyVo, Constants.TEMP_SAVE_STATUS);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	};

	/**
	 * 返回当前时间
	 * 
	 * @return
	 */
	@RequestMapping(value = "returnNowDate", produces = "application/text ; charset=utf-8")
	public @ResponseBody
	String returnNowDate() {
		String dateString = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		return dateString;
	}
}
