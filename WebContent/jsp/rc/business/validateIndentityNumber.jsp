<%@page import="com.creditease.rc.util.DateUtil"%>
<%@page import="com.creditease.rc.util.DESPlus"%>
<%@ page language="java" contentType="text/html;charset=UTF-8" import="java.util.*,com.creditease.rc.util.PropertiesUtil,com.creditease.core.security.SpringSecurityUtils" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";

	Properties properties = PropertiesUtil.loadProperties("spring/cm/cm.properties");
	String cmUrl = properties.getProperty("cm.url");
	String isURL = request.getRequestURL().toString();
	if(isURL.indexOf(".cn")>0){
		if(cmUrl.indexOf(".corp")>0){
			cmUrl=cmUrl.replaceAll(".corp",".cn");
		}
	}else if(isURL.indexOf(".corp")>0){
		if(cmUrl.indexOf(".cn")>0){
			cmUrl=cmUrl.replaceAll(".cn",".corp");
		}
	}
	String userId = SpringSecurityUtils.getCurrentUser().getUserId();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<base href="<%=basePath%>"/>
		<jsp:include page="../include/easyui.jsp"></jsp:include>
		<script type="text/javascript">
		 var serverName="<%=path%>";
		 var cmUrl = "<%=cmUrl%>";
		 var countryIdUser = <%=SpringSecurityUtils.getCurrentUser().getAreaCode()%>;
		 var userId = "<%=userId%>";
         var customerConsultId = "${param.customerConsultId}";
         var consultPoolId = "${param.consultPoolId}";
        //查看参数是否传递成功
           // console.info("--------------customerConsultId---------------"+customerConsultId);
           // console.info("--------------consultPoolId---------------"+consultPoolId);
		</script>

        <script type="text/javascript" src="<%=basePath%>scripts/business/validateIndentityNumber_draw.js"></script>
        <script type="text/javascript" src="<%=basePath%>scripts/business/validateIndentityNumber_operate.js"></script>

    </head>
	<body class="easyui-layout">

    <div region="center">
			<div id="tt" class="easyui-tabs" width="100%" height="500">
                <form id="myForm" method="post">
                <div title="身份验证">
					<fieldset>
						<legend>
							身份验证
						</legend>
						<table>
							<tr>
								<td >

									请输入借款人身份证 ：
									<input style="width:200px;" id="identityForBorrower"  class="easyui-validatebox" required="true" validType="length[18,18]" onblur="validateBorrowerIndentityInfo()" invalidMessage="输入长度为18位" missingMessage="身份证号必须为18位"/>

								</td>
								<td >
									<%--<a id="validateBorrowerIndentity" class="easyui-linkbutton" plain="false"   onclick="validateBorrowerIndentityInfo()" value="借款人身份验证"> 借款人身份验证</a>--%>
									<input id="borrowerIdNumber" type="hidden"/>
									<input id="borrowerFlag" type="hidden" value="false"/>
									<div id="invalIdBorrowerIdNumberDiv"></div>
								</td>
								<td rowspan="2" >
									<span id="goToCreditApplication" style="display:none;">
									  &nbsp;&nbsp;&nbsp;<input   type="button"  style="font-size: 16px;align:right" style="width:100px;" onclick="goToCreditApplicationFun()" value="添加" />
									</span>
								</td>
							</tr>
							<tr>
								<td >
									请输入共借人身份证 ：
									<input style="width:200px;" id="identityForCoBorrower"  class="easyui-validatebox" onblur="validateMateIndentityInfo()" required="true" validType="length[18,18]" invalidMessage="输入长度为18位" missingMessage="身份证号必须为18位"/>
								</td>
								<td >
									<%--<a id="validateMateIndentity"  class="easyui-linkbutton" plain="false"    onclick="validateMateIndentityInfo()" value="共借人身份验证">   共借人身份验证</a>
									--%><input id="mateIdNumber" type="hidden"/>
									<input id="mateFlag" type="hidden" value="false"/>
									<div id="invalIdCoBorrowerIdNumberDiv" ></div>
								</td>
							</tr>
						</table>

					</fieldset>

					<br />
					<fieldset>
						<legend>
							借款人验证结果
						</legend>
						<div id="borrowerValidateRS">
						 <input style="width:700px;height:30px;" id="borrowerValidateRSinfo" type="text" readonly="readonly" />
                         <input type="hidden" id="borrowerValidateFalseMsg" value="false" readonly="readonly"/>
                         <input type="hidden" id="borrowerValidateMsgFlag" value="true"/>
						</div>
						<table id="borrowerList"></table>
					</fieldset>
					<fieldset>
						<legend>
                            共借人身份验证结果
						</legend>
						<div id="mateValidateRS">
							<input style="width:700px;height:30px;" id="mateValidateRSinfo" type="text"  readonly="readonly" />
                            <input type="hidden" id="mateValidateFalseMsg" value="false"  readonly="readonly"/>
                            <input type="hidden" id="mateValidateMsgFlag" value="true"/>
						</div>
						<table id="mateList"></table>
					</fieldset>

				</div>
                </form>
			</div>

     </div>

	</body>
    <script type="text/javascript">
        //处理键盘事件
        //        function doKey(e){
        //            console.info("---------------------key------------");
        //            var ev = e || window.event;//获取event对象
        //            var obj = ev.target || ev.srcElement;//获取事件源
        //            var t = obj.type || obj.getAttribute('type');//获取事件源类型
        //            if(ev.keyCode == 8 && t != "password" && t != "text" && t != "textarea"){
        //                ev.preventDefault();
        //                return false;
        //            }
        //        }
        //        //禁止后退键 作用于Firefox、Opera
        //        document.onkeypress=doKey;
        //        //禁止后退键  作用于IE、Chrome
        //        document.onkeydown=doKey;

        $(document).on("keydown", function (e) {
            if (e.which === 8 && !$(e.target).is("input, textarea")) {
                e.preventDefault();
            }
        });
        $(document).on("keypress", function (e) {
            if (e.which === 8 && !$(e.target).is("input, textarea")) {
                e.preventDefault();
            }
        });

    </script>
</html>
