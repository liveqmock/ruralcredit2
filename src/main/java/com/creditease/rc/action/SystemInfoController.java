package com.creditease.rc.action;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.creditease.rc.domain.AmountConfirm;
import com.creditease.rc.domain.BorrowerService;
import com.creditease.rc.domain.BorrowerServiceApp;
import com.creditease.rc.domain.CreditApplication;
import com.creditease.rc.domain.CustomerBasicInfo;
import com.creditease.rc.domain.GroupLoanRegist;
import com.creditease.rc.domain.Message;
import com.creditease.rc.domain.RlApplyAuditTable;
import com.creditease.rc.domain.SystemAnnouncement;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.service.IAmountConfirmService;
import com.creditease.rc.service.IBorrowerServiceAppService;
import com.creditease.rc.service.ICreditApplicationService;
import com.creditease.rc.service.ICustomerBasicInfoService;
import com.creditease.rc.service.IGroupLoanRegistService;
import com.creditease.rc.service.IRlApplyAuditTableService;
import com.creditease.rc.service.ISystemInfoService;
import com.creditease.rc.util.DESPlus;
import com.creditease.rc.vo.ReceiveCreditObj;

/**
 * 
 * @author liuli
 * 系统信息
 *
 */
@Controller
@RequestMapping(value="systemInfo")
public class SystemInfoController {
	@Resource
	private ICustomerBasicInfoService customerService;
	@Resource
	private ISystemInfoService systemInfoService;
	@Resource
	private ICreditApplicationService creditApplicationService;
	@Resource
	private IAmountConfirmService amountConfirmService;
	@Resource
	private IGroupLoanRegistService groupLoanRegistService;
	@Resource
	private IRlApplyAuditTableService applyAuditTableService;
	@Resource
	private IBorrowerServiceAppService borrowerServiceAppService;
	
	private static final String UPLOADID_SUFFIX = "xtgg";
	
	/**
	 * 
	 * @param binder WebDataBinder对象
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
	}
	
	/**
	 * 添加系统公告
	 * @param systemAnnouncement
	 * @return
	 */
	@RequestMapping(value="addSystemAnnouncement")
	public @ResponseBody SystemAnnouncement addSystemAnnouncement(SystemAnnouncement systemAnnouncement,String via){
		int systemAnnouncementId = -1;
		if(null!=systemAnnouncement && systemAnnouncement.getSystemAnnouncementId()==null){
			systemAnnouncement.setTitleFlag(via);
			systemAnnouncementId = systemInfoService.addSystemAnnouncement(systemAnnouncement);
//			System.out.println(systemAnnouncement.getSystemAnnouncementId()+"-----------");
//			systemAnnouncement.setSystemAnnouncementId(systemAnnouncementId);
			DESPlus des;
			String desId = "";
			//生成附件id
			try {
				des = new DESPlus();
				desId = des.encrypt(systemAnnouncement.getSystemAnnouncementId().toString()+SystemInfoController.UPLOADID_SUFFIX);
				systemAnnouncement.setAttachmentId(desId);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return systemAnnouncement;
	}
	
	/**
	 * 查看系统公告
	 * @param systemAnnouncement
	 * @return
	 */
	@RequestMapping(value="viewSystemAnnouncement")
	public @ResponseBody SystemAnnouncement viewSystemAnnouncement(String param){
		SystemAnnouncement sa = null;
		sa = systemInfoService.getSystemAnnouncementById(Integer.valueOf(param));
		DESPlus desPlus;
		int imageNum = 0;
		try {
			desPlus = new DESPlus();
			if(null!=sa){
				//该id不加密，用于向内容管理平台获取附件数量
				String clientId1 = sa.getSystemAnnouncementId().toString()+SystemInfoController.UPLOADID_SUFFIX;
				String clientId = desPlus.encrypt(clientId1);
				sa.setAttachmentId(clientId);
				imageNum = creditApplicationService.getImgAmount(clientId1);
				sa.setImageNum(imageNum);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sa;
	}
	
	/**
	 * 保存编辑系统公告
	 * @param systemAnnouncement
	 * @return
	 */
	@RequestMapping(value="updateSystemAnnouncement")
	public @ResponseBody Message updateSystemAnnouncement(SystemAnnouncement systemAnnouncement,String via){
		Message message = new Message();
//		System.out.println(systemAnnouncement.getContent());
		
		systemAnnouncement.setTitleFlag(via);
		systemInfoService.updateSystemAnnouncement(systemAnnouncement);
		message.setSuccess(true);
//		sa = systemInfoService.getSystemAnnouncementById(Integer.valueOf(param));
		return message;
	}
	
	/**
	 * 删除公告
	 * @param saId
	 * @return
	 */
	@RequestMapping(value="deleteSystemAnnouncement")
	public @ResponseBody Message deleteSystemAnnouncement(int saId){
		Message message = new Message();
		int i = systemInfoService.deleteSystemAnnouncement(saId);
		if(i==1){
			message.setSuccess(true);
		}
		return message;
	}
	
	/**
	 * 分页查询客户咨询列表
	 * @param customerConsult 客户咨询对象
	 * @param rows 显示行数
	 * @param page  当前页
	 * @param fuzzyValue 模糊查询条件
	 * @return 分页列表
	 */
	@RequestMapping(value="searchPagnation")
	public @ResponseBody 
	Pagination searchCustomerConsults(SystemAnnouncement systemAnnouncement,String page, String rows,String fuzzyValue, String sort,String order,HttpServletRequest request){
		Pagination pagination = new Pagination();
		if (!StringUtils.isBlank(page)){
			pagination.setPage(Integer.valueOf(page));	
		}
		if (!StringUtils.isBlank(rows)){
			pagination.setPageSize(Integer.valueOf(rows));
		}
//		return pagination;
//		System.out.println("sort-----"+sort+"--order=="+order+"--page=="+page);
		pagination = systemInfoService.searchSystemAnnouncements(systemAnnouncement, pagination, fuzzyValue, sort, order, request.getSession());
//		List l = pagination.getRows();
//		for(int i=0;i<l.size();i++){
//			SystemAnnouncement s = (SystemAnnouncement)l.get(i);
//			System.out.println(s.getSystemAnnouncementId());
//		}
		return pagination;
	}
	
	/**
	 * 更新rl_credit_application表中的 AUDIT_STATUS字段值
	 * 用于在线上系统出现异常时进行应急操作
	 * liuli 2013-05-17
	 */
	@RequestMapping(value="updateAuditStatus")
	public @ResponseBody int updateCreditAppAuditStatus(String businessNumber,String auditStatus){
		//是否在后台也判断权限?
//		System.out.println("----"+businessNumber+"--auditStatus---"+auditStatus);
		int i = systemInfoService.updateCreditAppAuditStatus(businessNumber,auditStatus);
		System.out.println(new Date()+"将businessNumber："+businessNumber+"的状态修改为："+auditStatus);
		return i;
	}
	
	/**
	 * 根据业务单号查询额度确认记录
	 * @return
	 */
	@RequestMapping(value="searchApproveHistoryList")
	public @ResponseBody List<RlApplyAuditTable> searchApproveHistoryList(String businessNum){
		List<RlApplyAuditTable> l = new ArrayList<RlApplyAuditTable>();
		CreditApplication creditApplication = creditApplicationService.selectByGroupNumber(businessNum);
		//如果申请单存再进行下面操作
		if(creditApplication!=null){
			l = applyAuditTableService.selectApplyAuditTable(creditApplication.getCreditapplicationId());
		}
		return l;
	}
	
	/**
	 * 根据业务单号查询额度确认记录
	 * @return
	 */
	@RequestMapping(value="setApproveToHistoryData")
	public @ResponseBody Message setApproveToHistoryData(String applyaudittableId){
		Message message = new Message();
		int i = applyAuditTableService.setToHistoryDataById(Integer.valueOf(applyaudittableId));
//		System.out.println("applyaudittableId---------"+applyaudittableId);
		if(i>0){
			message.setSuccess(true);
		}
		return message;
	}
	
	/**
	 * 根据业务单号查询额度确认记录
	 * @return
	 */
	@RequestMapping(value="searchAmountConfirm")
	public @ResponseBody List<AmountConfirm> searchAmountConfirm(String businessNum){
		List l = new ArrayList();
//		System.out.println("businessNum------"+businessNum);
		CreditApplication creditApplication = creditApplicationService.selectByGroupNumber(businessNum);
		//如果申请单存在则取申请单id再查询额度确认表
		if(creditApplication!=null){
			l = amountConfirmService.selectAmountsByCreditAppId(creditApplication.getCreditapplicationId());
		}
		return l;
	}
	
	/**
	 * 根据amountConfirmId置历史标志位为1
	 */
	@RequestMapping(value="setAmountConfirmToHis")
	public @ResponseBody Message setAmountConfirmToHis(String amountConfirmId){
		Message message = new Message();
		int i = amountConfirmService.updateByAmountConfirmId(amountConfirmId);
		if(i>0){
			System.out.println(new Date()+"将amountConfirmId："+amountConfirmId+"修改为历史");
			message.setSuccess(true);
		}
		return message;
	}
	
	/**
	 * 根据业务单号查询放款登记列表
	 */
	@RequestMapping(value="searchReleaseMoney")
	public @ResponseBody List<GroupLoanRegist> searchReleaseMoney(String businessNum){
		List<GroupLoanRegist> l = new ArrayList<GroupLoanRegist>();
		l = groupLoanRegistService.selectByBusinessNum(businessNum);
		return l;
	}
	
	/**
	 * 根据id修改指定的状态
	 */
	@RequestMapping(value="modifyGroupLoanRegistStatus")
	public @ResponseBody Message modifyGroupLoanRegistStatus(String groupLoanRegistId,String status){
		Message message = new Message();
		GroupLoanRegist groupLoanRegist = new GroupLoanRegist();
		groupLoanRegist.setGroupLoanRegistId(Integer.valueOf(groupLoanRegistId));
		groupLoanRegist.setRegistStatus(status);
		int i = groupLoanRegistService.changeRegistStatus(groupLoanRegist);
		if(i>0){
			System.out.println(new Date()+"将groupLoanRegistId："+groupLoanRegistId+"修改为"+status);
			message.setSuccess(true);
		}
		return message;
	}
	
	/**
     * 查询所有借款申请
     * @param borrowerService 借款申请
     * @return list
     */
	@RequestMapping(value = "selectBorrowerServiceApp")
    public @ResponseBody List<Map> select(BorrowerService borrowerService){
    	// TODO Auto-generated method stub
		borrowerService.setValidState("");
		return borrowerServiceAppService.select(borrowerService);
    };
    /**
	 * 删除客户基本信息（同时删除历史信息)
	 * @param customerBasicInfoId  客户基本信息id 
	 * @return true:成功。false:失败
	 */
	@RequestMapping(value = "deleteCustomerBasicInfo")
	public @ResponseBody Message deleteCustomerBasicInfo(Integer customerBasicInfoId){
		Message message = new Message();
		boolean flag = false;
		if(customerBasicInfoId != null){
			  return  customerService.deleteCustomerBasicInfo(customerBasicInfoId);
		}else{
			return message;
		}
	};
	
	/**
	 *  修改借款申请
	 *  zhangman
	 * @param borrowerServiceApp
	 * @return 借款服务申请id
	 */
	@RequestMapping(value = "updateborrowerServiceApp")
	public @ResponseBody
	Message  updateborrowerServiceApp(BorrowerServiceApp borrowerServiceApp) {
	  return borrowerServiceAppService.updateborrowerServiceApp(borrowerServiceApp);
	};
	/**
	 *  修改客户基本信息
	 *  zhangman
	 * @param borrowerServiceApp
	 * @return 借款服务申请id
	 */
	@RequestMapping(value = "updateCustomerList")
	public @ResponseBody
	Message  updateCustomerList(CustomerBasicInfo customerBasicInfo) {
	  return customerService.updateCustomerBasicInfoForSystem(customerBasicInfo);
	};
	/**
	 * 模拟贷后发送放款通知
	 * @author luohongjie
	 * @param 
	 * @return
	 * 
	 */
	@RequestMapping(value="updateStatusByReceiveCreditMsg")
	public @ResponseBody
	Message updateStatusByReceiveCreditMsg(ReceiveCreditObj receiveCreditObj) throws ParseException{
		//根据业务单号查询相对应的 32位的业务编号
		System.out.println(receiveCreditObj.getApplyId());
		String businessNumber=receiveCreditObj.getApplyId();
		String UUID=customerService.selBusinessNumberByUUID(businessNumber);
		receiveCreditObj.setApplyId(UUID);
		System.out.println("--------------comein----------");
		List<ReceiveCreditObj> receiveCreditObjs = new ArrayList<ReceiveCreditObj>();
		receiveCreditObjs.add(receiveCreditObj);
	//	System.out.println(receiveCreditObj.get(0).getApplyId());
		
		return customerService.updateStatusByReceiveCreditMsg(receiveCreditObjs);
	}
}
