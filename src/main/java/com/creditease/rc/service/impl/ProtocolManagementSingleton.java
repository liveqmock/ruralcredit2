/**
 * Title:ProtocolManagementSingleton.java 
 * Description: 
 */
package com.creditease.rc.service.impl;

import java.text.DecimalFormat;
import java.util.List;

import org.springframework.stereotype.Service;

import com.creditease.rc.dao.IProtocolMappingDAO;
import com.creditease.rc.dao.IProtocolSuffixMappingDAO;
import com.creditease.rc.domain.ProtocolMapping;
import com.creditease.rc.domain.ProtocolSuffixMapping;
import com.creditease.rc.framework.exception.AppBusinessException;
import com.creditease.rc.util.CommonUtil;

/**
 * Title:ProtocolManagementSingleton.java 
 * Description: double-checked locking设计实现单例模式
 * Copyright:Copyright (c) 2011
 * Company:普信恒业科技发展（北京）有限公司
 * @author 韩大年 
 * @version v1.0
 * 2012-12-19
 */
@Service
public class ProtocolManagementSingleton {
	
	private ProtocolManagementSingleton(){
		
	}
	//分公司-本年度中当前合同编号
	private String currentProtocolNumber;
	private static ProtocolManagementSingleton instance=null;
	
	public static ProtocolManagementSingleton getInstance(){
		//首先判断instance是不是为null，如果为null，加锁初始化；如果不为null，直接返回instance
		if(null==instance){
			synchronized (ProtocolManagementSingleton.class){
				if(null==instance){
					instance= new ProtocolManagementSingleton();
				}
			}
		}
		return instance;
	}

	
	/**
	 * 
	 * @author 韩大年  
	 * @Description: 获取下个合同编号:模拟sequence,同时给小组创建合同编号
	 * @return
	 * @version v1.0
	 * 2012-12-19
	 */
	public  synchronized ProtocolMapping saveNextval(ProtocolSuffixMapping protocolSuffixMappingVO,ProtocolMapping protocolMappingVO,IProtocolSuffixMappingDAO protocolSuffixMappingDAO,IProtocolMappingDAO protocolMappingDAO){
		Long nextvalLong=0L;
		String nextvalString="";
		if(null!=protocolSuffixMappingVO && CommonUtil.isNotEmpty(protocolSuffixMappingVO.getBranchofficeId()) && CommonUtil.isNotEmpty(protocolSuffixMappingVO.getSimpleYear())){
			ProtocolSuffixMapping protocolSuffixMapping=null;
			//检查后缀是否存在
			List <ProtocolSuffixMapping> protocolSuffixMappingList =protocolSuffixMappingDAO.selectProtocolSuffixMappingListByCondition(protocolSuffixMappingVO);
			//存在,则编号+1
			if(CommonUtil.isNotEmpty(protocolSuffixMappingList)){
				int len=protocolSuffixMappingList.size();
				if(len==1){
					protocolSuffixMapping=protocolSuffixMappingList.get(0);
					nextvalLong=Long.valueOf(protocolSuffixMapping.getSuffixNumber())+1;
				}else{
					throw new AppBusinessException("同一分公司同一年存在多条后缀数据");
				}
				
			}else{
		    //不存在,创建新的
				protocolSuffixMapping= new ProtocolSuffixMapping();
				protocolSuffixMapping.setBranchofficeId(protocolSuffixMappingVO.getBranchofficeId());
				protocolSuffixMapping.setBranchofficeName(protocolSuffixMappingVO.getBranchofficeName());
				protocolSuffixMapping.setSimpleYear(protocolSuffixMappingVO.getSimpleYear());
				//甘CX1200002     分别代表：机构编码年份当年合同的顺序编号
			    nextvalLong=1L;
			}
			//样式
			nextvalString=LongFormatString("0000",nextvalLong);
			protocolSuffixMapping.setSuffixNumber(nextvalString);
			if(null==protocolSuffixMapping.getSuffixId()){
				protocolSuffixMappingDAO.insert(protocolSuffixMapping);
				
			}else{
				protocolSuffixMappingDAO.updateByPrimaryKeySelective(protocolSuffixMapping);
			}
			//保存小组中合同编号
			String prefixNumber=protocolMappingVO.getProtocolNumber();
			protocolMappingVO.setProtocolNumber(prefixNumber+nextvalString);
			Long mappingId=protocolMappingDAO.insertObject(protocolMappingVO);
			protocolMappingVO.setMappingId(mappingId);
		}else{
			throw new AppBusinessException("传入参数为null");
		}
		
		return protocolMappingVO;
		
	}
	
	/**
	 * 
	 * @author 韩大年  
	 * @Description: 格式化输入字符串
	 * @param pattern 样式
	 * @param number  转换数字
	 * @return
	 * @version v1.0
	 * 2012-12-19
	 */
	
	private String LongFormatString(String pattern,Long number){
		DecimalFormat decimalFormat = new DecimalFormat(pattern);
		return decimalFormat.format(number);
	}
		
	
	public String getCurrentProtocolNumber() {
		return currentProtocolNumber;
	}

	public void setCurrentProtocolNumber(String currentProtocolNumber) {
		this.currentProtocolNumber = currentProtocolNumber;
	}
}
