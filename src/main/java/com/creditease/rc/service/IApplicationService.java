package com.creditease.rc.service;

//import com.creditease.rc.domain.Application;
import java.text.ParseException;
import java.util.List;

import com.creditease.rc.domain.Application;
import com.creditease.rc.domain.BorrowerServiceApp;
import com.creditease.rc.domain.JobInfo;
import com.creditease.rc.domain.Message;
import com.creditease.rc.vo.ApplicationTableVo1;
import com.creditease.rc.vo.BorrowInfoVo;
import com.creditease.rc.vo.BorrowerInfoVo;
import com.creditease.rc.vo.ContactVo;
import com.creditease.rc.vo.CreditCardInfoVo;
import com.creditease.rc.vo.HouseServeyVo;
/**
 * 
 * @author zhangman
 *
 */
/**
 * @author zhangman
 *
 */
public interface IApplicationService {
//	/**
//	 *  新增入户调查表一
//	 * @param applicationList 借款服务申请vo 封装类
//	 * @return true:成功，false：失败
//	 */
//	public boolean addApplication(ApplicationTableVo1 applicationList);

	

	/**
	 * 按借款申请id 删除
	 * 
	 * @param borrowerServiceAppId
	 *            借款申请id
	 * @return true:成功，false：失败
	 */
	public boolean deleteBorrowerServiceApp(int borrowerServiceAppId);

	/**
	 * 按 借款申请 id 查询
	 * 
	 * @param borrowerServiceAppId
	 *            借款申请 id
	 * @return 借款申请列表
	 */
	public BorrowerServiceApp selectByBorrowerServiceAppId(
			int borrowerServiceAppId);

	/**
	 * 回显入户调查表一
	 * @param borrowerServiceAppId 借款服务申请id
	 * @return 借款服务申请vo 封装类
	 */
	public ApplicationTableVo1 selectApplication(int borrowerServiceAppId) ;
	/**
	 * 得到BorrowerServiceApp中的数据
	 * 
	 * @author 郝强
	 * @param borrowerServiceAppId 借款服务申请id
	 * @return 借款服务申请
	 */
	public BorrowerServiceApp searchBorrowerServiceApp(int borrowerServiceAppId);
	
	
	/*******************************新版个人申请*************************************/
	/**
	 * @param application  
	 * @return boolean
	 */
	public boolean addApplication(Application application);
	
	/**
	 * 
	 * @param application  
	 * @return boolean
	 */
	public boolean updateApplication(Application application);
	/**
	 * 
	 * @param borrowerServiceAppId  
	 * @return List<Application> 
	 */
	public List<Application> searchApplications(int borrowerServiceAppId);
}
