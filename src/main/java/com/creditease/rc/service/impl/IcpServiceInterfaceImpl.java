package com.creditease.rc.service.impl;

import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.log4j.Logger;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;

import com.creditease.rc.app.icp.rollback.ContractReturnInfo;
import com.creditease.rc.app.icp.rollback.ContractReturnReq;
import com.creditease.rc.app.icp.rollback.ContractReturnRes;
import com.creditease.rc.app.icp.rollback.ICPContractServiceWS;
import com.creditease.rc.service.IcpServiceInterface;
import com.creditease.rc.util.DateUtil;
import com.creditease.rc.util.JsonUtil;
@Service
public class IcpServiceInterfaceImpl implements IcpServiceInterface {

	@Resource
	private ICPContractServiceWS icpContractServiceWS;
	private Logger log = Logger.getLogger(IcpServiceInterfaceImpl.class);
	
	@Override
	public ContractReturnRes clicContractReturnRuralReq(ContractReturnReq contractReturnReq) {
		// 记Log日志
		log.info("**************icpContractServiceWS.clicContractReturnRuralReq(contractReturnReq) 入参******************");
		log.info(JsonUtil.getJackson(contractReturnReq));
		ContractReturnRes clicContractReturnRuralReq = icpContractServiceWS.clicContractReturnRuralReq(contractReturnReq);
		log.info("**************icpContractServiceWS.clicContractReturnRuralReq(contractReturnReq) 出参******************");
		log.info(JsonUtil.getJackson(clicContractReturnRuralReq));
		return clicContractReturnRuralReq;
	}
	
	public ContractReturnReq getContractReturnReq(Long creditapplicationId){
		ContractReturnReq contractReturnReq = new ContractReturnReq();
		String date = DateUtil.dateConvertString(new Date());
		// MD5加密两次
		String str = new Md5PasswordEncoder().encodePassword(date + "rural", null);
		str = new Md5PasswordEncoder().encodePassword(str, null);
		str = str.substring(0, 16);
		contractReturnReq.setSignInfo(str);//系统签名标识（yyyy-mm-dd+密钥，MD5加密两次取前16位的结果）
		contractReturnReq.setOperateTime(dateToXmlDate(new Date()));//操作时间(yyyy-MM-dd hh:mm:ss)
		ContractReturnInfo contractReturnInfo = new ContractReturnInfo();
		contractReturnInfo.setApplyId(creditapplicationId.toString());
		contractReturnReq.setContractReturnInfo(contractReturnInfo);//进件编号
		return contractReturnReq;
	}
	
	
	/**
	 * date转成XMLGregorianCalendar格式
	 */
	private XMLGregorianCalendar dateToXmlDate(Date date){  
       Calendar cal = Calendar.getInstance();  
        cal.setTime(date);  
        DatatypeFactory dtf = null;  
         try {  
            dtf = DatatypeFactory.newInstance();  
        } catch (Exception e) { 
        	e.printStackTrace();
        }  
        XMLGregorianCalendar dateType = dtf.newXMLGregorianCalendar();  
        dateType.setYear(cal.get(Calendar.YEAR));  
        //由于Calendar.MONTH取值范围为0~11,需要加1  
        dateType.setMonth(cal.get(Calendar.MONTH)+1);  
        dateType.setDay(cal.get(Calendar.DAY_OF_MONTH));  
        dateType.setHour(cal.get(Calendar.HOUR_OF_DAY));  
        dateType.setMinute(cal.get(Calendar.MINUTE));  
        dateType.setSecond(cal.get(Calendar.SECOND));  
        return dateType;  
	}

}
