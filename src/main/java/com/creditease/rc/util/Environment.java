package com.creditease.rc.util;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.ContextLoader;

import com.creditease.core.security.Authorization;
import com.creditease.rc.app.smp.EmployeeWebService;
import com.creditease.rc.app.smp.OrganizeWebService;



/**
 * 通过此类可以得到一些资源,例如Dao,而后通过DBServce可以得到每个实体对应的dao,还可以到C
 * @author zhangwei
 *
 */
public class Environment {

	private static ApplicationContext applicationContext;

	private static final String[] configLocations = new String[]{"/config/spring/*/*.xml"};
	static {
		applicationContext = ContextLoader.getCurrentWebApplicationContext();
		if(applicationContext == null){
			applicationContext = new ClassPathXmlApplicationContext(configLocations);
		}
			
	}

	/**
	 * 需要通过 Environment 获得的资源,都写在environment_xmlPath所对应的xml中
	 */
	private Environment(){}

	private static Environment env=new Environment();

	public static Environment getInstance(){
		if(env==null)
			env=new Environment();
		return env;
	}

	@SuppressWarnings("unchecked")
	public <E> E getService(Class<E> c) throws Exception{
		//从指定的spring配置文件中读取是否有c类型的（包括其子类型）的bean
		String[] names = applicationContext.getBeanNamesForType(c);

		//配置文件中每个对应的接口只配置一个，所以可以拿第一个，如果有多个，
		//也只拿第一个
		if (names.length >= 1) {
		  return (E) applicationContext.getBean(names[0]);
		}else{
			throw new Exception("没有这样的业务类存在");
		}
	}
	/**
	 * 根据beanId查询对象
	 * @param beanId
	 * @return
	 */
	public Object getBean(String beanId){
		return applicationContext.getBean(beanId);
	}
	/**
	 * 获得userCenter远程接口服务类
	 * @return
	 */
	public EmployeeWebService getEmployeeWebService(){
		EmployeeWebService emService=(EmployeeWebService)applicationContext.getBean("employeeService");
		return emService;
	}
	/**
	 * 获得lender远程接口服务类
	 * @return
	 */
//	public BelongLenderSale getLenderGiftService(){
//		BelongLenderSale service=(BelongLenderSale)applicationContext.getBean("lenderGiftService");
//		return service;
//	}
	
	/************************************************************************/
	
	@SuppressWarnings("unchecked")
	public <E> E getServiceForTestCase(Class<E> c) throws Exception{
		//从指定的spring配置文件中读取是否有c类型的（包括其子类型）的bean
		String[] names = applicationContext.getBeanNamesForType(c);

		//配置文件中每个对应的接口只配置一个，所以可以拿第一个，如果有多个，
		//也只拿第一个
		if (names.length >= 1) {
		  return (E) applicationContext.getBean(names[0]);
		}else{
			throw new Exception("没有这样的业务类存在");
		}
	}
	
	@SuppressWarnings("unchecked")
	public Authorization getAuthorizationService() throws Exception{
		//从指定的spring配置文件中读取是否有c类型的（包括其子类型）的bean
		Authorization service=(Authorization)applicationContext.getBean("authorization");
		return service;
	}
	
	@SuppressWarnings("unchecked")
	public OrganizeWebService getOrganizeService() throws Exception{
		//从指定的spring配置文件中读取是否有c类型的（包括其子类型）的bean
		OrganizeWebService service=(OrganizeWebService)applicationContext.getBean("organizeService");
		return service;
	}
}
