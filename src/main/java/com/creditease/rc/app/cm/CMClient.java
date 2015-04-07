package com.creditease.rc.app.cm;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CMClient {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context 
        = new ClassPathXmlApplicationContext(new String[] {"config/spring/cm/applicationContext-cm.xml"}); 

//		CreditCMClientWS cbp = (CreditCMClientWS)context.getBean("creditCMClientWS");
		CreditCMClientWSPortType ws = (CreditCMClientWSPortType)context.getBean("creditCMClientWSPortType");
//		CreditCMClientWS ws = (CreditCMClientWS)context.getBean("creditCMClientWSPortType");
		String res = ws.getImgAmount("13JN0827000012", "CM_U_YINONGDAI2_YW");
		System.out.println(res);
		System.out.println(res.charAt(6));
	}
}