package com.creditease.rc.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.creditease.rc.common.Constants;
import com.creditease.rc.domain.CodeTable;
import com.creditease.rc.domain.Message;
import com.creditease.rc.service.ICreditApplicationService;
import com.creditease.rc.service.IDataDictionaryService;
import com.creditease.rc.service.IGuaranorProfileService;
import com.creditease.rc.util.DESPlus;
import com.creditease.rc.vo.BorrowerInfoVo;
import com.creditease.rc.vo.CustomerBasicInfoVo;
import com.creditease.rc.vo.GuaranorProfile;

/**
 * 
 * @author haoqiang
 * 
 */
@Controller
@RequestMapping("guaranorProfile")
public class GuaranorProfileController {

	@Resource
	private IGuaranorProfileService guaranorProfileService;

	@Resource
	private IDataDictionaryService dataDictionaryService;
	
	@Resource
	private ICreditApplicationService creditApplicationService;

	/**
	 * 
	 * @param binder 拦截
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

	/**
	 * 返回担保人页面
	 * 
	 * @param borrowerServiceAppId 信息主键
	 * @param param 0-新增担保人；1-修改担保人
	 * @param businessNumber 业务单号
	 * @return 担保人信息页面
	 */
	@RequestMapping(value = "guaranorProfileJSP")
	public ModelAndView returnGuaranorProfile(Long borrowerServiceAppId, String param, String businessNumber) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/jsp/rc/business/guaranorProfile.jsp");
		String desId = null;
		if(borrowerServiceAppId!=null){
			try {
				DESPlus desPlus = new DESPlus();
				desId = desPlus.encrypt(String.valueOf(borrowerServiceAppId));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
//		if ("0".equals(param)) {
//			// 0代表是新增担保人
//			GuaranorProfile guaranorProfile = guaranorProfileService.selectBorrowerService(borrowerServiceAppId);
//			guaranorProfile.getBorrowerService().setBusinessNumber(businessNumber);
//			guaranorProfile.getBorrowerService().setBorrowerServiceAppDESId(desId);
//			modelAndView.addObject(guaranorProfile);
//		} else if ("1".equals(param)) {
//			// 1代表是修改担保人
//		}
		GuaranorProfile guaranorProfile = guaranorProfileService.selectGuaranorProfile(borrowerServiceAppId);
		guaranorProfile.getBorrowerService().setBusinessNumber(businessNumber);
		guaranorProfile.getBorrowerService().setBorrowerServiceAppDESId(desId);
		modelAndView.addObject(guaranorProfile);
		return modelAndView;
	}

	/**
	 * nothing
	 * 
	 * @param s nothing
	 * @return nothing
	 */
	@RequestMapping(value = "nothing")
	public @ResponseBody
	boolean nothing(String s) {

		for (int i = 0; i < 100000000; i++) {
		}
		return true;
	}

	/**
	 * 担保人信息对象
	 * 
	 * @param borrowerServiceAppId 担保人信息主键
	 * @param param 0-新增担保人；1-修改担保人
	 * @return 担保人对象
	 */
	@RequestMapping(value = "show")
	public @ResponseBody
	GuaranorProfile show(Long borrowerServiceAppId, String param) {
		GuaranorProfile guaranorProfile = new GuaranorProfile();
		if ("0".equals(param)) {
			// 0代表是新增担保人
			guaranorProfile = guaranorProfileService.selectBorrowerService(borrowerServiceAppId);

		} else if ("1".equals(param)) {
			// 1代表是修改担保人
			guaranorProfile = guaranorProfileService.selectGuaranorProfile(borrowerServiceAppId);
		}
		return guaranorProfile;
	}

	/**
	 * 保存担保人对象
	 * 
	 * @param guaranorProfile 担保人对象
	 * @return 是否保存成功
	 */
	@RequestMapping(value = "save")
	public @ResponseBody
	Message saveGuaranorProfile(GuaranorProfile guaranorProfile) {

		Message message = new Message();

		boolean isSuccess = false;

		isSuccess = guaranorProfileService.saveUpdateGuaranorProfile(guaranorProfile,Constants.DO_SAVE_STATUS);

		message.setSuccess(isSuccess);

		return message;
	}
	/**
	 * zancun担保人对象
	 * 
	 * @param guaranorProfile 担保人对象
	 * @return 是否保存成功
	 */
	@RequestMapping(value = "tempSave")
	public @ResponseBody
	Message tempSaveGuaranorProfile(GuaranorProfile guaranorProfile) {

		Message message = new Message();

		boolean isSuccess = false;

		isSuccess = guaranorProfileService.saveUpdateGuaranorProfile(guaranorProfile,Constants.TEMP_SAVE_STATUS);

		message.setSuccess(isSuccess);

		return message;
	}
	/**
	 * 更新担保人对象
	 * 
	 * @param guaranorProfile 担保人对象
	 * @return 是否更新成功
	 */
	@RequestMapping(value = "tempUpdate")
	public @ResponseBody
	Message tempUpdateGuaranorProfile(GuaranorProfile guaranorProfile) {

		Message message = new Message();

		boolean isSuccess = false;

		isSuccess = guaranorProfileService.saveUpdateGuaranorProfile(guaranorProfile,Constants.TEMP_SAVE_STATUS);

		message.setSuccess(isSuccess);

		return message;
	}
	/**
	 * 更新担保人对象
	 * 
	 * @param guaranorProfile 担保人对象
	 * @return 是否更新成功
	 */
	@RequestMapping(value = "update")
	public @ResponseBody
	Message updateGuaranorProfile(GuaranorProfile guaranorProfile) {

		Message message = new Message();

		boolean isSuccess = false;

		isSuccess = guaranorProfileService.saveUpdateGuaranorProfile(guaranorProfile,Constants.DO_SAVE_STATUS);

		message.setSuccess(isSuccess);

		return message;
	}

	/**
	 * 民族下拉框
	 * 
	 * @param q input值
	 * @return 民族List
	 */
	@RequestMapping(value = "nationalCombobox")
	public @ResponseBody
	List<CodeTable> nationalCombobox(String q) {
		return dataDictionaryService.nationalCombobox(q);

	}

	/**
	 * 担保人信息回显
	 * 
	 * @param borrowerServiceAppId 担保人信息主键
	 * @param param 新增还是修改担保人
	 * @param businessNumber 业务单号
	 * @return 页面
	 */
	@RequestMapping(value = "guaranorProfileJSPBak")
	public ModelAndView returnGuaranorProfileBak(Long borrowerServiceAppId, String param, String businessNumber) {
		ModelAndView modelAndView = returnGuaranorProfile(borrowerServiceAppId, param, businessNumber);
		modelAndView.setViewName("/jsp/rc/business/guaranorProfileBak.jsp");
		return modelAndView;
	}
	/**
	 * 
	* checkstyle
	* @author wyf   
	* @param borrowerService 
	* @param q 
	* @return    
	* List<CustomerBasicInfoVo>
	 */
	@RequestMapping(value = "guaranorComboGrid")
	public @ResponseBody
	List<CustomerBasicInfoVo> guaranorComboGrid(BorrowerInfoVo borrowerService, String q) {
		List<CustomerBasicInfoVo> customers = new ArrayList<CustomerBasicInfoVo>();
		String tempQ = null;
		if (q != null || "".equals(q)) {
			tempQ = q.trim();
		}
		borrowerService.setCredentialsNumber(tempQ);
		String getCredentialsNumber = borrowerService.getCredentialsNumber();
		if (getCredentialsNumber != null && getCredentialsNumber.trim().length() == 18) {
			customers = guaranorProfileService.selectCustomerBasicInfoForGuaranorProfile(borrowerService);
		}

		return customers;
	}

	/**
	 * liuli 2013-05-07
	 * 查看担保人
	 * 
	 * @param borrowerServiceAppId
	 * @param param 
	 * @param businessNumber
	 * @return ModelAndView 
	 */
	@RequestMapping(value = "guaranorProfileView")
    public ModelAndView guaranorProfileView(HttpServletRequest request, String param) {

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/jsp/rc/business/guaranorProfileView.jsp");

		GuaranorProfile guaranorProfile = guaranorProfileService.selectGuaranorProfile(Long.valueOf(param));
		guaranorProfileService.displayNameByCode(guaranorProfile);
		modelAndView.addObject(guaranorProfile);

        /*合规检查：查看担保人时，添加查看担保人附件*/
        if (StringUtils.isNotEmpty(request.getParameter("showAttach"))) {
            modelAndView.addObject("showAttach", "yes");
            modelAndView.addObject("orderNum", request.getParameter("orderNum"));
        }

		return modelAndView;
	}
	/**
	 * 
	* 上传资料数量
	* @author wyf   
	* @param borrowerServiceAppId 
	* @return    
	* int
	 * @throws Exception 
	 */
	@RequestMapping(value = "guaranorImgCount")
	@ResponseBody
	public int getImgCount(String borrowerServiceAppId) throws Exception{
		int count = creditApplicationService.getImgAmount(borrowerServiceAppId);
		return count;
	}
	
}
