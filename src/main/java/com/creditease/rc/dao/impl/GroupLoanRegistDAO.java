package com.creditease.rc.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.creditease.rc.dao.IGroupLoanRegistDAO;
import com.creditease.rc.domain.GroupLoanRegist;
import com.creditease.rc.framework.dao.impl.BaseDao;
import com.creditease.rc.vo.GroupLoanRegistVo;

/**
 * 放款登记实现类
 * @author zhangman
 *
 */
@Repository
public class GroupLoanRegistDAO extends BaseDao implements IGroupLoanRegistDAO {
	
	@Override
	public int addGroupLoanRegist(GroupLoanRegist groupLoanRegist) {
		return (Integer) insertObject("GROUPLOANREGIST.insert", groupLoanRegist);
	}
	
	@Override
	public int addGroupLoanRegist2(GroupLoanRegist groupLoanRegist) {
		return (Integer) insertObject("GROUPLOANREGIST.insert2", groupLoanRegist);
	}
	
	@Override
	public List<GroupLoanRegist> searchHistory(GroupLoanRegist groupLoanRegist) {
		// TODO Auto-generated method stub
		return (List<GroupLoanRegist>) queryList("GROUPLOANREGIST.searchByRegistStatus", groupLoanRegist);
	}
	@Override
	public int updateGroupLoanRegist(GroupLoanRegist groupLoanRegist) {
		// TODO Auto-generated method stub
		return update("GROUPLOANREGIST.update", groupLoanRegist);
	}
	@Override
	public GroupLoanRegist selectDengji(GroupLoanRegist groupLoanRegist) {
		return (GroupLoanRegist) queryUnique("GROUPLOANREGIST.selectDengji", groupLoanRegist);
	}
	/**
	 * 
	 * @author 韩大年  
	 * @Description:  放款确认回退
	 * @param groupLoanRegist
	 * @return 
	 * @version v1.0 
	 * 2013-5-17
	 */
	public int updateGroupLoanAffirmRollback(GroupLoanRegist groupLoanRegist) {
		// TODO Auto-generated method stub
		return update("GROUPLOANREGIST.updateGroupLoanAffirmRollback", groupLoanRegist);
	}
	
	/**
	 * 根据业务单号查询放款登记数据
	 * liuli 2013-05-20
	 * @return
	 */
	@Override
	public List<GroupLoanRegist> selectByBusinessNum(String businessNum) {
		return (List<GroupLoanRegist>) queryList("GROUPLOANREGIST.selectByBusinessNum", businessNum);
	}
	
	@Override
	public List<GroupLoanRegistVo> searchConfirm(GroupLoanRegist groupLoanRegist) {
		// TODO Auto-generated method stub
		return (List<GroupLoanRegistVo>) queryList("GROUPLOANREGIST.searchConfirm", groupLoanRegist);
	}
	
	@Override
	public GroupLoanRegist selectById(Integer groupLoanRegistId) {
		// TODO Auto-generated method stub
		return   (GroupLoanRegist) queryUnique("GROUPLOANREGIST.selectById", groupLoanRegistId);
	}
	@Override
	public int updateGroupLoanRegistConfirm(GroupLoanRegist groupLoanRegist){
		return update("GROUPLOANREGIST.updateGroupLoanRegistConfirm",groupLoanRegist);
	}
	@Override  
	public GroupLoanRegist searchByCreditApplicationId(GroupLoanRegist groupLoanRegist){
		return (GroupLoanRegist) queryUnique("GROUPLOANREGIST.searchByCreditApplicationId",groupLoanRegist);
	}

	@Override
	public GroupLoanRegist searchForGroupLoanRegist(Long creditapplicationId) {
		 return (GroupLoanRegist) queryUnique("GROUPLOANREGIST.searchForGroupLoanRegist",creditapplicationId);
	}
	
	/** add by ygx 2014-09-09
	 * 放款列表的查看
	 * @param creditapplicationId
	 * @return
	 */
	@Override
	public GroupLoanRegist searchForGroupLoanRegistForGroupLoanRegist(Long creditapplicationId){
		 return (GroupLoanRegist) queryUnique("GROUPLOANREGIST.searchForGroupLoanRegistForGroupLoanRegist",creditapplicationId);
	}

	@Override
	public String selectGroupLoanRegistId(String uuid) {
		return (String) queryUnique("GROUPLOANREGIST.selectGroupLoanRegistId", uuid);
	}

	@Override
	public int updateIcp(String creditapplicationId) {
		return update("GROUPLOANREGIST.updateIcp", creditapplicationId);
	}
	
}
