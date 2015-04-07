<%--
    0	待分配 1 已退回 2 无营业网点 3 已分配 4 拒绝 5 无效客户 6 未联系上客户
--%>
<%@ page language="java" contentType="text/html;charset=UTF-8"
	import="java.util.*,com.creditease.rc.util.*,com.creditease.core.security.SpringSecurityUtils"
	pageEncoding="utf-8"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>营销咨询列表</title>
		<jsp:include page="/jsp/rc/include/easyui.jsp"></jsp:include>
		<script type="text/javascript">
       	var serverName = "<%=path%>";

        function importExcel() {
            $('#uploadFileForm').form('submit', {
                url: serverName + '/customerConsultPoolController/uploadExcel.do',
                type: "post",
                onSubmit: function () {
                    var fileName = $('#File1').val();
                    if (fileName == '') {
                        $.messager.alert('提示', '请选择需要上传的文件', 'info');
                        return false;
                    }
                    if (fileName != '') {
                        if (fileName.indexOf('.xlsx') == -1 && fileName.indexOf('.xls') == -1) {
                            $.messager.alert('提示', '文件格式不正确，请选择正确的Excel文件', 'info');
                            return false;
                        }
                    }
                    return true;
                },
                success: function (data) {
                    if (data.indexOf("成功上传") > 0) {
                        var dataSuccessMes = data.substring(1, data.length - 1);
                        dataSuccessMes = dataSuccessMes.replace(/\"/g, "");
                        $.messager.show({
                            showType: "show",
                            timeout: 2000,
                            title: '消息',
                            height: 150,
                            width: 300,
                            msg: dataSuccessMes
                        });
                        searchSaleInquireInfo();
                    } else {
                        var dataMes = data.replace(/aa/g, "<br/>");
                        dataMes = dataMes.substring(1, dataMes.length - 1);
                        dataMes = dataMes.replace(/\"/g, "");
                        $.messager.show({
                            showType: "show",
                            timeout: 50000,
                            title: '消息',
                            height: 150,
                            width: 300,
                            msg: dataMes
                        });
                    }
                    $('#importExcelDiv').dialog('close');
                }
            });
            //清空上传文件信息显示
            $("#File1").val('');
        }
        //------------------------
        function addWan(value, row, index) {
            if (value == null) {
                return "";
            }
            return value + '万';
        }
        <%--查看详情、编辑--%>
        function inquirePoolOperate(value, row, index) {
            var consultPoolId = row.CONSULT_POOL_ID;
            var marketConsultState = row.MARKET_CONSULT_STATE;
            var mobilePhone = row.MOBILE_PHONE;
            var link;
            if (consultPoolId) {

                link = " <a href='#' onclick='registerInquireView(\"" + consultPoolId + "\",\"" + mobilePhone + "\");'>查看详情 </a>";

                if (marketConsultState == '待分配' || marketConsultState == '已退回' || marketConsultState == '无营业网点') {
                    link += "<a href='#' onclick='editregisterInquire(\"" + consultPoolId + "\")'>编辑</a>";
                }
            }
            return link;
            <%--<sec:authorize ifAnyGranted="${control.saleInquireList_view}">
            link = link + " <a href='#' onclick='registerInquireView(\"" + row.CONSULT_POOL_ID + "\");'>查看详情 | </a>";
            </sec:authorize>

            if (marketConsultState == "0" || marketConsultState == "1" || marketConsultState == "2") {
                <sec:authorize ifAnyGranted="${control.saleInquireList_edit}">
                link += "<a href='#' onclick='editregisterInquire(\"" + consultPoolId + "\")'>编辑</a>";
                </sec:authorize>
                //该状态下可以做废弃操作
                <sec:authorize ifAnyGranted="${control.saleInquireList_drop}">
                link += "<a href='#' onclick='dropregisterInquire(\"" + consultPoolId + "\")'>废弃</a> |";
                </sec:authorize>
            }--%>
        }
        /*营销咨询-查看详情：查看当前联系电话关联的所有咨询记录*/
        function registerInquireView(poolId, phoneNumber) {
            var param;
            $("#registerInquireViewIframe")[0].src = "<%=basePath%>jsp/rc/CustomerConsultPool/registerInquireView.jsp?poolId=" + poolId + "&phoneNumber=" + phoneNumber;
            $("#registerInquireViewDiv").dialog({
                title: "查看咨询记录",
                buttons: [
                    {id: "registerInquireViewButton", text: "关闭", handler: function () {
                        $("#registerInquireViewDiv").dialog("close");
                    }}
                ],
                closed: true,
                draggable: true,
                width: $('#centerPanel')[0].clientWidth - 100,
                height: 300,
                onClose : function() {
                    $("#registerInquireViewIframe").attr('src', "");
                }
            });
            <%--$("#registerInquireViewIframe")[0].src = "<%=basePath%>saleInquireController/registerInquireView.do?" + param--%>
            $("#registerInquireViewDiv").dialog("open");
        }

        // 编辑客户咨询
        function editregisterInquire(consultPoolId) {
            $('#registerInquireEditFrame').attr('src', serverName + '/saleInquireController/editFrame.do?consultPoolId=' + consultPoolId);
            $("#registerInquireEdit").dialog({
                title: "编辑咨询记录",
                close: true,
                modal: true,
                draggable: true,
                cache : false,
                onClose : function() {
                    $("#registerInquireEditFrame").attr('src', "");
                }
            });

            $('#registerInquireEdit').dialog("open");
        }

    </script>
		<script type="text/javascript"
			src="<%=basePath%>scripts/CustomerConsultPool/saleInquire.js"></script>
	</head>

	<body class="easyui-layout">

		<div region="center" id="centerPanel">
			<div class="easyui-tabs" id="inquirePoolTab"
				data-options="plain:true" style="padding: 5px; width: auto; height: auto; margin-top: 5px; margin-bottom: 4px;">
				<div title="搜索条件" style="padding: 10px;" id="tabDiv1">
					<%--<legend>搜索</legend>--%>
					<table>
						<tbody>
							<tr>
								<td>
									客户姓名：
								</td>
								<td>
									<input type="text" style="width: 150px;" id="customerName" name="customerName">
								</td>
								<td>
									咨询方式：
								</td>
								<td>
									<select id="channel" name="channel" class="easyui-combobox"
										style="width: 150px;" data-options="required:false">
									</select>
								</td>
								<td>
									&nbsp;&nbsp;&nbsp;&nbsp;
								</td>
								<td>
									咨询日期：
								</td>
								<td>
									<input class="easyui-datebox" type="text" id="registDate1"
										name="registDate1" data-options="required:false"
										style="width: 150px;" editable="false" />
									-
									<input class="easyui-datebox" type="text" id="registDate2"
										name="registDate2" data-options="required:false"
										style="width: 150px;" editable="false" />
									<a href="#" class="easyui-linkbutton"
										data-options="iconCls:'icon-search'"
										onclick="searchSaleInquireInfo();">搜索</a>
									<a href="#" class="easyui-linkbutton"
										data-options="iconCls:'icon-reload'" onclick="clearAll()">清空</a>
								</td>
							</tr>
							<tr>
								<td>
									联系方式：
								</td>
								<td>
									<input type="text" style="width: 150px;" id="mobilePhone"
										name="mobilePhone">
								</td>

								<td>
									状态：
								</td>
								<td>
									<select id="marketConsultState" class="easyui-combobox"
										name="marketConsultState" style="width: 150px;"
										data-options="required:false">
									</select>
								</td>
								<td>
									&nbsp;&nbsp;&nbsp;&nbsp;
								</td>
								<td>
									分配日期：
								</td>
								<td>
									<input class="easyui-datebox" type="text"
										id="distributionDate1" name="distributionDate1"
										editable="false" data-options="required:false"
										style="width: 150px;" />
									-
									<input class="easyui-datebox" type="text" editable="false"
										id="distributionDate2" name="distributionDate2"
										data-options="required:false" style="width: 150px;" />
								</td>
							</tr>
							<tr>
								<td>
									户籍地址：
								</td>
								<td colspan="3">
									<select id="province" class="easyui-combobox" name="province"
										style="width: 120px;">
									</select>
									<select id="city" class="easyui-combobox" name="city"
										style="width: 120px;">
									</select>
									<select id="area" class="easyui-combobox" name="area"
										style="width: 125px;">
									</select>
								</td>
								<td>
									&nbsp;&nbsp;&nbsp;&nbsp;
								</td>
								<%--<td>
									历史：
								</td>
								<td>
									<select id="history" class="easyui-combobox" name="history"
										style="width: 152px;">
									</select>
								</td>--%>
								<%-- <td colspan="2">
                         <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'"
                            onclick="searchSaleInquireInfo();">搜索</a>
                         <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-reload'"
                            onclick="clearAll()">清空</a>
                     </td>--%>
							</tr>
						</tbody>
					</table>
				</div>
<sec:authorize ifAnyGranted="${control.saleInquireList_allotTab}">	
				<div title="分配" style="padding: 10px;" id="tabDiv2">
					<table>
						<tbody>
							<tr>
								<td>
									分配营业部：
								</td>
								<td>
									<input id="distributeDepartment"
										name="distributeTeamDepartment" style="width: 249px" />
								</td>
								<td>
									<sec:authorize ifAnyGranted="${control.saleInquireList_allot}">
									<a href="#" id="fenpei" class="easyui-linkbutton"
										onclick="distribute();">分配</a>
									</sec:authorize>
								</td>
								<td>

									<sec:authorize ifAnyGranted="${control.saleInquireList_bachAllot}">	
									<a href="#" id="piliangfenpei" class="easyui-linkbutton"
										data-options="iconCls:'icon-ok'" onclick="autoDistribute();">批量自动分配</a>
									</sec:authorize>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				</sec:authorize>
			</div>
			<%--营销咨询池列表--%>
			<div id="inquire_poolDiv" style="padding: 5px;" >
				<table id="inquire_pool" toolbar="#toolbar1"  class="easyui-datagrid">
				</table>
			</div>
		</div>
			<%--工具栏：登记客户咨询、导入Excel--%>
			<div id="toolbar1">
				<table >
					<tr>
						<td align="left">
							<a href="javascript:void(0)" class="easyui-linkbutton"
								iconCls="icon-addOne" plain="true" onclick="registerInquire();">登记客户咨询</a>
						</td>
						<td align="right">
							<a href="javascript:void(0)" class="easyui-linkbutton"
								iconCls="icon-addOne" plain="true" onclick="importExcelDialog()">导入Excel</a>
						</td>
					</tr>
				</table>
			</div>

			<%--新增登记咨询--%>
			<div id="registerInquire"
				style="width: 800px; height: 500px; padding: 10px;"
				buttons="#opt_btn">
				<iframe id="registerInquireFrame" name="registerInquireFrame" src=""
					scrolling="yes" frameborder="0" style="width: 100%; height: 100%">
				</iframe>
				<div id="opt_btn" class="toolbar" style="text-align: right;">
					<a  id="registSave" class="easyui-linkbutton" iconCls="icon-ok"
						onclick="submitInquire();">保存</a>
					<a class="easyui-linkbutton" iconCls="icon-undo"
						onclick="undoSubmitInquire();">取消</a>
				</div>
			</div>
			<%--编辑--%>
			<div id="registerInquireEdit"
				style="width: 800px; height: 500px; padding: 10px;"
				buttons="#edit_btn">
				<iframe id="registerInquireEditFrame"
					name="registerInquireEditFrame" src="" scrolling="yes"
					frameborder="0" style="width: 100%; height: 100%;">
				</iframe>
				<div id="edit_btn" class="toolbar">
					<a id="editCus" class="easyui-linkbutton" iconCls="icon-ok"
						onclick="submitInquireEdit();">保存</a>
					<a class="easyui-linkbutton" iconCls="icon-undo"
						onclick="undoEditSubmitInquire();">取消</a>
				</div>
			</div>
			<%--查看详情--%>
			<div id="registerInquireViewDiv" class="easyui-dialog" closed="true"
				style="padding: 0px;">
				<iframe id="registerInquireViewIframe" src="" scrolling="no"
					frameborder="0" style="width: 100%; height: 100%;"></iframe>
			</div>
			<div id="registerInquireView" data-options="iconCls:'icon-search'"
				title="查看客户咨询" style="padding: 5px; width: 800px; height: 460px;">
			</div>
			<%--<sec:authorize ifAnyGranted="${control.saleInquireList_upload}"> 	--%>
			<%--导入 Excel--%>
			<div id="importExcelDiv" style="width: 500px; height: 130px;">
				<form id="uploadFileForm" action="" method="post"
					enctype="multipart/form-data" runat="server">
					<table style="width: 100; height: 100%;">
						<tr style="height: 30px;">
							<td>
								批量上传渠道咨询信息（请上传Excel文件）
							</td>
						</tr>
						<tr style="height: 50px;">
							<td align="center">
								<input id="File1" runat="server" name="UpLoadFile" type="file" />
							</td>
							<td>
								<input id="button" type="button" value="上 传"
									onclick="importExcel();" />
							</td>
						</tr>
					</table>
				</form>
			</div>
			<%--</sec:authorize>--%>
		
	</body>
</html>





