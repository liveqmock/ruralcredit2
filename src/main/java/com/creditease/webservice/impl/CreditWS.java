package com.creditease.webservice.impl;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.jws.WebService;

import org.apache.log4j.Logger;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

import com.creditease.rc.dao.IRural2CreditDao;
import com.creditease.rc.domain.Message;
import com.creditease.rc.service.impl.Rural2CreditService;
import com.creditease.rc.util.CommonUtil;
import com.creditease.rc.util.DateUtil;
import com.creditease.rc.util.JsonUtil;
import com.creditease.rc.vo.ReceiveCreditMsg;
import com.creditease.rc.vo.ReceiveCreditObj;
import com.creditease.rc.vo.ReceiveCreditObjList;
import com.creditease.webservice.ICreditWS;

@WebService
public class CreditWS implements ICreditWS {
	@Resource
	private IRural2CreditDao rural2CreditDao;

	Logger log = Logger.getLogger(Rural2CreditService.class);

	@Override
	public Message updateStatusByReceiveCreditMsg(ReceiveCreditMsg receiveCreditMsg) throws ParseException {
		// TODO Auto-generated method stub
		log.info("**************CreditWS.updateStatusByReceiveCreditMsg(receiveCreditMsg) requestParam******************");
		log.info(JsonUtil.getJackson(receiveCreditMsg));
		Message message = new Message();
		String getSignInfo = receiveCreditMsg.getSignInfo();
		String signInfo = signInfoEncrypt(new Date());
		String getSysId = receiveCreditMsg.getSysId();
		ReceiveCreditObjList receiveCreditObjList = receiveCreditMsg.getReceiveCreditObjList();
		if ("".equals(getSignInfo) || getSignInfo == null) {
			message.setSuccess(false);
			message.setCode("1");
			message.setMsg("加密信息为空！");
			log.info("**************CreditWS.updateStatusByReceiveCreditMsg(message) responseParam******************");
			log.info(JsonUtil.getJackson(message));
			return message;
		}
		if (!signInfo.equals(getSignInfo)) {
			message.setSuccess(false);
			message.setCode("2");
			message.setMsg("加密结果不一致！");
			log.info("**************CreditWS.updateStatusByReceiveCreditMsg(message) responseParam******************");
			log.info(JsonUtil.getJackson(message));
			return message;
		}
		if ("".equals(getSysId) || getSysId == null) {
			message.setSuccess(false);
			message.setCode("3");
			message.setMsg("系统标识为空！");
			log.info("**************CreditWS.updateStatusByReceiveCreditMsg(message) responseParam******************");
			log.info(JsonUtil.getJackson(message));
			return message;
		}
		if (!getSysId.equals("rural2")) {
			message.setSuccess(false);
			message.setCode("4");
			message.setMsg("系统标识错误！");
			log.info("**************CreditWS.updateStatusByReceiveCreditMsg(message) responseParam******************");
			log.info(JsonUtil.getJackson(message));
			return message;
		}
		if (receiveCreditObjList == null) {
			message.setSuccess(false);
			message.setCode("5");
			message.setMsg("receiveCreditObjList == null！");
			log.info("**************CreditWS.updateStatusByReceiveCreditMsg(message) responseParam******************");
			log.info(JsonUtil.getJackson(message));
			return message;
		}
		List<ReceiveCreditObj> receiveCreditObjs = receiveCreditObjList.getReceiveCreditObjs();
		if (CommonUtil.isEmpty(receiveCreditObjs)) {
			message.setSuccess(false);
			message.setCode("6");
			message.setMsg("receiveCreditObjs对象集合为空！");
			log.info("**************CreditWS.updateStatusByReceiveCreditMsg(message) responseParam******************");
			log.info(JsonUtil.getJackson(message));
			return message;
		}
		message = rural2CreditDao.updateStatusByReceiveCreditMsg(receiveCreditObjs);
		log.info("**************CreditWS.updateStatusByReceiveCreditMsg(message) responseParam******************");
		log.info(JsonUtil.getJackson(message));
		return message;
	}

	/**
	 * 传入信贷系统销售编号加密properties为signInfo两次
	 * 
	 * @author 郝强
	 * @param paramDate 日期
	 * @return signInfoEncrypt 加密后的结果
	 */
	private String signInfoEncrypt(Date paramDate) {
		// 得到signInfo属性
		// Properties properties = PropertiesUtil.loadProperties("spring/credit/credit.properties");
		String signInfo = "credit";

		String date = DateUtil.dateConvertString(paramDate);

		// MD5加密两次

		// System.out.println("原始==========================" + date + signInfo);
		String signInfoEncrypt = new Md5PasswordEncoder().encodePassword(date + signInfo, null);

		// System.out.println("加密1==========================" + signInfoEncrypt);

		signInfoEncrypt = new Md5PasswordEncoder().encodePassword(signInfoEncrypt, null);

		// System.out.println("加密2==========================" + signInfoEncrypt);

		signInfoEncrypt = signInfoEncrypt.substring(0, 16);

		System.out.println("取前16位的结果============" + signInfoEncrypt);

		// 返回加密后的结果
		return signInfoEncrypt;
	}
}
