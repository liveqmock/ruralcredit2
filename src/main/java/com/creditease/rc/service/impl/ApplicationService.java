package com.creditease.rc.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.creditease.rc.dao.IApplicationDao;
import com.creditease.rc.dao.IBorrowInfoDAO;
import com.creditease.rc.dao.IBorrowerServiceAppDAO;
import com.creditease.rc.dao.IContactDAO;
import com.creditease.rc.dao.ICreditCardInfoDAO;
import com.creditease.rc.dao.ICreditOrganizationDAO;
import com.creditease.rc.dao.IJobInfoDAO;
import com.creditease.rc.domain.Application;
import com.creditease.rc.domain.BorrowInfo;
import com.creditease.rc.domain.BorrowerServiceApp;
import com.creditease.rc.domain.Contact;
import com.creditease.rc.domain.CreditCardInfo;
import com.creditease.rc.domain.CreditOrganization;
import com.creditease.rc.domain.JobInfo;
import com.creditease.rc.service.IApplicationService;
import com.creditease.rc.vo.ApplicationTableVo1;
/**
 * 
 * @author zhangman
 *
 */
@Service
public class ApplicationService implements IApplicationService {
	@Resource
	private IApplicationDao applicationDao;
	
	
	@Resource
	private IJobInfoDAO jobDAO;
	
	@Resource
	private IContactDAO contactDAO;

	@Resource
	private ICreditOrganizationDAO creditOrganizationDAO;

	@Resource
	private IBorrowInfoDAO borrowInfoDAO;

	@Resource
	private ICreditCardInfoDAO creditCardInfoDAO;

	@Resource
	private IBorrowerServiceAppDAO borrowerServiceAppDAO;
	

//	@Override
//	public boolean addApplication(ApplicationTableVo1 applicationList) {
//		
//		try {
//			borrowerServiceAppService.addOrUpdateBorrowerServiceApp(applicationList.getBorrowerServiceApp());
//			borrowInfoService.addOrUpdateBorrowInfo(applicationList.getBorrowInfos());
//			CreditCardInfoVo creditCardInfoVo = new CreditCardInfoVo();
//			creditCardInfoVo.setCreditCardInfo(applicationList.getCreditCardInfo());
//			creditCardInfoVo.setCreditOrganization(applicationList.getCreditOrganization());
//			creditCardInfoService.addOrUpdateCreditCardInfo(creditCardInfoVo);
//			jobInfoService.addOrUpdateJobInfo(applicationList.getJobInfo());
//			ContactVo contactVo =new ContactVo();
//			contactVo.setContacts(applicationList.getContacts());
//			contactService.addOrUpdateContact(contactVo);
//			return true;
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return false;
//		}
//		
//	}

	@Override
	public ApplicationTableVo1 selectApplication(int borrowerServiceAppId){
		ApplicationTableVo1 applicationList = new ApplicationTableVo1();
			try {
				BorrowerServiceApp borrowerServiceApp= borrowerServiceAppDAO.selectByBorrowerServiceAppId(borrowerServiceAppId);
				
				List<BorrowInfo> borrowInfos = new ArrayList<BorrowInfo>();
				borrowInfos = borrowInfoDAO.selectBorrowInfo(borrowerServiceAppId);
				
				CreditCardInfo creditCardInfo = creditCardInfoDAO
				.selectByBorrowerServiceAppId(borrowerServiceAppId);

				List<CreditOrganization> creditOrganizations = new ArrayList<CreditOrganization>();
				if (creditCardInfo != null) {
						creditOrganizations = creditOrganizationDAO
								.selectCreditOrganization(creditCardInfo.getCreditCardInfoId());
				}
				JobInfo jobInfo =jobDAO.selectByBorrowerServiceAppId(borrowerServiceAppId);
				
				List<Contact> contacts = new ArrayList<Contact>();
				if (borrowerServiceApp != null) {
					contacts = contactDAO.selectContact(borrowerServiceApp.getBorrowerServiceAppId());
					applicationList.setBorrowerServiceApp(borrowerServiceApp);
				}
				if (borrowInfos != null) {
					applicationList.setBorrowInfos(borrowInfos);
				}

				applicationList.setCreditCardInfo(creditCardInfo);
				applicationList.setCreditOrganization(creditOrganizations);
				applicationList.setJobInfo(jobInfo);
				applicationList.setContacts(contacts);
				
				return applicationList;
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
	}


	@Override
	public boolean deleteBorrowerServiceApp(int borrowerServiceAppId) {

		List<CreditCardInfo> creditCardInfoList = creditCardInfoDAO
				.selectCreditCardInfo(borrowerServiceAppId);
		CreditCardInfo creditCardInfo = new CreditCardInfo();
		if (creditCardInfoList != null && creditCardInfoList.size() > 0) {
			creditCardInfo = creditCardInfoList.get(0);
		}

		try {
			if (creditCardInfo.getCreditCardInfoId() != null
					&& "".equals(creditCardInfo.getCreditCardInfoId())) {
				creditOrganizationDAO.deleteCreditOrganization(creditCardInfo
						.getCreditCardInfoId());
			}

			creditCardInfoDAO.deleteCreditCradInfo(borrowerServiceAppId);
			borrowInfoDAO.deleteBorrowInfo(borrowerServiceAppId);
			jobDAO.deleteJobInfo(borrowerServiceAppId);
			contactDAO.deleteContact(borrowerServiceAppId);
			borrowerServiceAppDAO.deleteBorrowerServiceApp(borrowerServiceAppId);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public BorrowerServiceApp selectByBorrowerServiceAppId(
			int borrowerServiceAppId) {
		// TODO Auto-generated method stub
		return borrowerServiceAppDAO
				.selectByBorrowerServiceAppId(borrowerServiceAppId);
	}


	// 作者：郝强
	// 用途：通过borrowerServiceAppId得到所对应的BorrowerServiceApp
	@Override
	public BorrowerServiceApp searchBorrowerServiceApp(int borrowerServiceAppId) {
		// TODO Auto-generated method stub
		return borrowerServiceAppDAO
				.selectByBorrowerServiceAppId(borrowerServiceAppId);
	}
	
	///*******************************新版个人申请*************************************/
	@Override
	public boolean addApplication(Application application) {
		try {
			applicationDao.insert("RL_APPLICATION.abatorgenerated_insert", application);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	@Override
	public boolean updateApplication(Application application) {
		applicationDao.update("", application);
		return false;
	}
	@Override
	public List<Application> searchApplications(int borrowerServiceAppId) {
		// TODO Auto-generated method stub
		return null;
	}
}
