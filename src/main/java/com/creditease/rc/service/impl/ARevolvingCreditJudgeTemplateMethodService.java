package com.creditease.rc.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.creditease.rc.dao.ICreditApplicationDAO;
import com.creditease.rc.dao.IFirstSystemDao;
import com.creditease.rc.service.ARevolvingCreditJudgeTemplateMethod;

@Service
public class ARevolvingCreditJudgeTemplateMethodService extends ARevolvingCreditJudgeTemplateMethod {
	@Resource
	private ICreditApplicationDAO creditApplicationDAO; 
	@Resource
	private IFirstSystemDao firstSystemDao;
	
//	二期：是否是借款人 是以前的已完成借款的借款人
	@Override
	public Boolean isRural2BorrowerCredentialsNumber(String credentialsNumber) {
		List<Map> creditApplicationIds = creditApplicationDAO.selectFinishCreditApplicationIdByBorrower(credentialsNumber);
		if(creditApplicationIds != null){
			if(creditApplicationIds.size() > 0){
				return true;
			}else{
				return false; 
			}
		}else{
				return false;
		}
	}

//	判断新借款人是不是以前已经完成借款的借款人的配偶，且本次共同借款人为以前 同一笔业务的借款人（子类必须重写该方法）
	@Override
	public Boolean isRural2MateCredentialsNumber(String borrowerCredentialsNumber, String mateCredentialsNumber) {
		Map<String, String> map = new HashMap<String, String>();
		if(borrowerCredentialsNumber!= null){
			map.put("credentialsNumber", mateCredentialsNumber);
		}
		if(mateCredentialsNumber != null){
			map.put("idNumber", borrowerCredentialsNumber);
		}
		List<Map> creditApplicationIds = new ArrayList<Map>();
		if(map.get("credentialsNumber")== null || map.get("idNumber")== null || ("".equals(map.get("credentialsNumber")))|| "".equals(map.get("idNumber"))){
		}else{
			creditApplicationIds = creditApplicationDAO.selectFinishCreditApplicationIdByBoth(map);
		}
		if(creditApplicationIds != null){
			if(creditApplicationIds.size() > 0){
				return true;
			}else{
				return false; 
			}
		}else{
				return false;
		}
	}

//	判断借款人是否是一期中的借款人（还款中，还款完成，提前还款完成）
	@Override
	public Boolean isRural1BorrowerCredentialsNumber(String credentialsNumber) {
		int count=0;
		try {
			count = firstSystemDao.selectBorrowerCredentialsNumberCount(credentialsNumber);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(count >0){
			return true;
		}else{
			return false;
		}
	}
	
//	判断ii.	借款人不同，判断新借款人是不是以前借款人的配偶，且本次借款人配偶为以前借款人；
	@Override
	public Boolean isRural1MateCredentialsNumber(String borrowerCredentialsNumber, String mateCredentialsNumber) {
		int count=0;
		
		try {
			count =firstSystemDao.selectSpouseCredentialsNumberCount(borrowerCredentialsNumber,mateCredentialsNumber);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		if(count>0){
			return true;
		}else{
			return false;
		}
	}
}
