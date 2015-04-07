package com.creditease.rc.framework.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
/**
 * Title:字符串处理类
 * Description: 宜农贷2.0系统开发
 * Copyright:Copyright (c) 2011
 * Company:普信恒业科技发展（北京）有限公司
 * 2011-6-30
 * @author wangxinqiang
 * @version v1.0
 */
public class CharacterEncodingFilter implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");   
		response.setCharacterEncoding("UTF-8");
	    chain.doFilter(request, response);   
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}

