<%@ page language="java" import="java.util.*" contentType="text/html;charset=UTF-8" pageEncoding="utf-8"%>
<%@ page import="com.soft4j.httpupload4j.SmartUpload,com.soft4j.bo.PhotoMgr"%>
<%
	String pageErrorInfo = null;
	SmartUpload su = null;
	try{
		su = new SmartUpload();
		su.initialize(pageContext);
		su.upload();
		pageErrorInfo = PhotoMgr.fileUpload(su,pageContext);
		if(pageErrorInfo==null){
		//输出必须有successed,在handlers.js中uploadSuccess方法用
			out.print("successed:"+pageContext.getAttribute("upLoadFileName"));
		}
	}catch(Exception e){
		pageErrorInfo = e.getMessage();
	}finally{
		su = null;
		if(pageErrorInfo!=null){
			out.print(pageErrorInfo);
		}
	}
%>

