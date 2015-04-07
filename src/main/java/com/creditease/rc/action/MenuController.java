package com.creditease.rc.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.creditease.core.security.SpringSecurityUtils;
import com.creditease.core.security.User;
import com.creditease.core.ws.client.UserService;
import com.creditease.rc.common.Constants;
import com.creditease.rc.domain.Message;
import com.creditease.rc.util.DateUtil;
import com.creditease.rc.util.JsonUtil;

/**
 * Title:
 * Description: 农村商贷系统研发
 * Copyright: Copyright (c) 2012
 * Company: 普信恒业科技发展（北京）有限公司
 * Date: 2013-3-28
 * 
 * @author: 解兵丰
 * @version: v1.0
 */
@Controller
@RequestMapping("menu")
public class MenuController {

	@Resource
	private UserService userAutService;

	@RequestMapping(value = "makeMenu")
	public String makeMenu(HttpServletRequest request, HttpServletResponse response) {
		// 清空UC中权限缓存
		ServletContext servletContext = request.getSession().getServletContext();
		if (servletContext.getAttribute("control") != null) {
			servletContext.removeAttribute("control");
		}
		response.setContentType("text/xml;charset=utf-8"); // （1）一定要在（2）的前面，不然会乱码
		response.setCharacterEncoding("UTF-8"); // （2）
		response.setHeader("Cache-Control", "no-cache");
		String menucredit = userAutService.getManagerMenu(Integer.parseInt(Constants.SYSTEM_SIGN),
				SpringSecurityUtils.getCurrentUserName());
//		String menuICP = userAutService.getManagerMenu(Integer.parseInt(Constants.SYSTEM_SIGN),
//				SpringSecurityUtils.getCurrentUserName());
		User user = SpringSecurityUtils.getCurrentUser();
		System.out.println("登录时间：" + DateUtil.dateConvertStringTime(new Date()));
		System.err.println("登录人基本信息：" + JsonUtil.getJackson(user));
		List<String> authList = SpringSecurityUtils.getAuthList(request.getSession());
		System.err.println("数据权限组：" + JsonUtil.getJackson(authList));

		// 手动输出xml文件，防止springmvc对返回参数做页面匹配
		try {
			PrintWriter out = response.getWriter();
			out.print(menucredit);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "makeMenuICP")
	public String makeMenuICP(HttpServletRequest request, HttpServletResponse response) {
		// 清空UC中权限缓存
		ServletContext servletContext = request.getSession().getServletContext();
		if (servletContext.getAttribute("control") != null) {
			servletContext.removeAttribute("control");
		}
		response.setContentType("text/xml;charset=utf-8"); // （1）一定要在（2）的前面，不然会乱码
		response.setCharacterEncoding("UTF-8"); // （2）
		response.setHeader("Cache-Control", "no-cache");
//		String menucredit = userAutService.getManagerMenu(Integer.parseInt(Constants.SYSTEM_SIGN),
//				SpringSecurityUtils.getCurrentUserName());

		String menuICP = userAutService.getManagerMenu(Integer.parseInt(Constants.SYSTEM_SIGN_ICP),
				SpringSecurityUtils.getCurrentUserName());
		User user = SpringSecurityUtils.getCurrentUser();
		System.out.println("登录时间：" + DateUtil.dateConvertStringTime(new Date()));
		System.err.println("登录人基本信息：" + JsonUtil.getJackson(user));
		List<String> authList = SpringSecurityUtils.getAuthList(request.getSession());
		System.err.println("数据权限组：" + JsonUtil.getJackson(authList));

		// 手动输出xml文件，防止springmvc对返回参数做页面匹配
		try {
			PrintWriter out = response.getWriter();
			out.print(menuICP);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "updatePassword")
	@ResponseBody
	public Message updatePassword(String password) {
		String result = "";
		Message message = new Message();
		message.setSuccess(true);
// System.err.println(password);
		Integer userid = Integer.valueOf(SpringSecurityUtils.getCurrentUser().getUserId());
		Md5PasswordEncoder md5 = new Md5PasswordEncoder();
// result = userAutService.updateEmployeePwdById(userid.intValue(), md5.encodePassword("password", password));
		result = userAutService.updateEmployeePwdById(userid.intValue(), password);
		message.setMsg(result);
		return message;
	}

}
