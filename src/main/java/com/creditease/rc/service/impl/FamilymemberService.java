package com.creditease.rc.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.creditease.rc.dao.IBorrowerserviceapptestDao;
import com.creditease.rc.dao.IFamilymemberDao;
import com.creditease.rc.domain.Familymember;
import com.creditease.rc.service.IFamilymemberService;

/**
 * 
 * @author haoqiang
 * 
 */
@Service
public class FamilymemberService implements IFamilymemberService {

	@Resource
	private IFamilymemberDao familymemberDao;
	@Resource
	private IBorrowerserviceapptestDao borrowerserviceapptestDao;

	@Override
	public boolean insertIntofamilymember(List<Familymember> familymember) {
		// TODO Auto-generated method stub
		familymemberDao.insertIntofamilymember(familymember);
		return true;
	}

	@Override
	public List<Familymember> searchfamilymemberList(Long borrowerSurveyId) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Familymember> searchFamilymembers(Long borrowerServiceAppId) {
		// TODO Auto-generated method stub
		return null;
	}
	public IFamilymemberDao getFamilymemberDao() {
		return familymemberDao;
	}

	public void setFamilymemberDao(IFamilymemberDao familymemberDao) {
		this.familymemberDao = familymemberDao;
	}

	public IBorrowerserviceapptestDao getBorrowerserviceapptestDao() {
		return borrowerserviceapptestDao;
	}

	public void setBorrowerserviceapptestDao(IBorrowerserviceapptestDao borrowerserviceapptestDao) {
		this.borrowerserviceapptestDao = borrowerserviceapptestDao;
	}

	@Override
	public boolean batchInsert(List<Familymember> familymember) {
		// TODO Auto-generated method stub
		familymemberDao.batchInsertfamilymemberList(familymember);
		// familymemberDao.batchInsert("familymember.insertIntofamilymember",familymember);
		return true;
	}


	@Override
	public boolean updateFamilyMemberList(List<Familymember> familymembers) {
		// TODO Auto-generated method stub
		List<Familymember> updateFamilymembers = new ArrayList<Familymember>();
		for (int i = 0; i < familymembers.size(); i++) {
			Familymember temp = familymembers.get(i);
			String name = temp.getName();
			if (name != null && !"".equals(name.trim())) {
				updateFamilymembers.add(temp);
			}
		}
		familymemberDao.batchUpdateFamilyMemberList(updateFamilymembers);
		return true;
	}

	@Override
	public boolean addFamilyMemberList(List<Familymember> familymembers) {
		// TODO Auto-generated method stub
		List<Familymember> addFamilymembers = new ArrayList<Familymember>();
		for (int i = 0; i < familymembers.size(); i++) {
			Familymember temp = familymembers.get(i);
			String name = temp.getName();
			if (name != null && !"".equals(name.trim())) {
				addFamilymembers.add(temp);
			}
		}
		familymemberDao.batchInsertfamilymemberList(addFamilymembers);
		return true;
	}

	@Override
	public boolean insertFamilyMemberList(List<Familymember> familymembers) {
		// TODO Auto-generated method stub
		try {
			familymemberDao.batchInsertfamilymemberList(familymembers);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}


	/**
	 * 
	 * @param returnFamilymembers 家庭成员List
	 * @param borrowerServiceAppId 信贷申请主键
	 * @return 家庭成员List
	 */
	private List<Familymember> packagingFamilymemberList(List<Familymember> returnFamilymembers,
			Integer borrowerServiceAppId) {
//		Familymember familymember0 = new Familymember();
//		familymember0.setBorrowerServiceAppId(borrowerServiceAppId);
//		returnFamilymembers.add(familymember0);
//
//		Familymember familymember1 = new Familymember();
//		familymember1.setBorrowerServiceAppId(borrowerServiceAppId);
//		returnFamilymembers.add(familymember1);
//
//		Familymember familymember2 = new Familymember();
//		familymember2.setBorrowerServiceAppId(borrowerServiceAppId);
//		returnFamilymembers.add(familymember2);
//
//		Familymember familymember3 = new Familymember();
//		familymember3.setBorrowerServiceAppId(borrowerServiceAppId);
//		returnFamilymembers.add(familymember3);
//
//		Familymember familymember4 = new Familymember();
//		familymember4.setBorrowerServiceAppId(borrowerServiceAppId);
//		returnFamilymembers.add(familymember4);
		return returnFamilymembers;
	}

	// 保存家庭成员的操作
	@Override
	public List<Familymember> saveFamilymembers(List<Familymember> familymemberList) {
		// TODO Auto-generated method stub
		// 创建一个临时的FamilymemberList用来实现后续的操作
//		Integer borrowerServiceAppId = familymemberList.get(0).getBorrowerServiceAppId();
//		List<Familymember> insertFamilymemberList = new ArrayList<Familymember>();
//		List<Familymember> updateFamilymemberList = new ArrayList<Familymember>();
//		List<Integer> deleteFamilymemberList = new ArrayList<Integer>();
//		for (int i = 0; i < familymemberList.size(); i++) {
//			// 把从前Controller传过来的FamilymemberList中的元素get到一个一个去判断
//			Familymember tempFamilymember = familymemberList.get(i);
//			// 得到FamilymemberId
//			Integer familyMemberId = tempFamilymember.getFamilyMemberId();
//			String name = tempFamilymember.getName();
//			String gender = tempFamilymember.getGender();
//			Integer age = tempFamilymember.getAge();
//			String borrowerreRationship = tempFamilymember.getBorrowerreRationship();
//			String education = tempFamilymember.getEducation();
//			String workUnit = tempFamilymember.getWorkUnit();
//			Double yearIncome = tempFamilymember.getYearIncome();
//			String telphone = tempFamilymember.getTelphone();
//			// 如果FamilymemberId==null则做插入操作
//			if (familyMemberId == null) {
//				if ((name == null || "".equals(name.trim())) && (gender == null || "".equals(gender.trim()))
//						&& age == null && (borrowerreRationship == null || "".equals(borrowerreRationship.trim()))
//						&& (education == null || "".equals(education.trim()))
//						&& (workUnit == null || "".equals(workUnit.trim())) && yearIncome == null
//						&& (telphone == null || "".equals(telphone.trim()))) {
//				} else {
//					insertFamilymemberList.add(tempFamilymember);
//				}
//				// 如果FamilymemberId!=null则可能是修改或者删除操作
//			} else {
//				// 如果输入的全是空则是删除操作
//				if ((name == null || "".equals(name.trim())) && (gender == null || "".equals(gender.trim()))
//						&& age == null && (borrowerreRationship == null || "".equals(borrowerreRationship.trim()))
//						&& (education == null || "".equals(education.trim()))
//						&& (workUnit == null || "".equals(workUnit.trim())) && yearIncome == null
//						&& (telphone == null || "".equals(telphone.trim()))) {
//					deleteFamilymemberList.add(tempFamilymember.getFamilyMemberId());
//					// 如果输入的不是空就是修改操作
//				} else {
//					updateFamilymemberList.add(tempFamilymember);
//				}
//			}
//
//		}
//		if (CommonUtil.isNotEmpty(insertFamilymemberList)) {
//			familymemberDao.batchInsert("familymember.insertfamilymember", insertFamilymemberList);
//		}
//		if (CommonUtil.isNotEmpty(updateFamilymemberList)) {
//			familymemberDao.batchUpdate("familymember.updatefamilymemberByPrimaryKey", updateFamilymemberList);
//		}
//		if (CommonUtil.isNotEmpty(deleteFamilymemberList)) {
//			familymemberDao.batchDelete("familymember.deletefamilymemberByPrimaryKey", deleteFamilymemberList);
//		}
//		List<Familymember> returnFamilymembers = searchFamilymembers(borrowerServiceAppId);
		return familymemberList;
	}

    /**
     * 根据
     * @param borrowerServiceAppId
     * @return
     */
    @Override
    public List<Familymember> queryFamilymembers(Long borrowerServiceAppId) {
           return familymemberDao.queryFamilymembers(borrowerServiceAppId);
    }
}
