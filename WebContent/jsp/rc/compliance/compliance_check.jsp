<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2014-8-5
  Time: 11:03
  合规检查-合规检查列表页面
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    
    //合规检查搜索条件信息 关闭时需要回显 begin 
    //这些参数信息是查看合规检查后关闭回显时 传入的参数 供回显查询使用 
    			String tab_index = request.getParameter("tab_index");
			    String fuzzy = request.getParameter("fuzzy");
			    String branch_name = request.getParameter("branch_name");
			    String business_number = request.getParameter("business_number");
			    String material_man = request.getParameter("material_man");
			    String loan_begin = request.getParameter("loan_begin");
			    String loan_end = request.getParameter("loan_end");
			    String borrower_man = request.getParameter("borrower_man");
			    String pageNumber = request.getParameter("pageNumber");
        //合规检查搜索条件信息 关闭时需要回显 end 
%>
<jsp:include page="/jsp/rc/include/easyui.jsp"/>
<html>
<head>
    <title>合规检查</title>
    <script type="text/javascript">
        var serverName = "<%=path%>";
        var basePath = "<%=basePath%>";
        //合规检查搜索条件信息 关闭时需要回显 begin 
        //回显参数 在拼接URL时使用 
          var tab_index = "<%=tab_index%>";
          var fuzzy = "<%=fuzzy%>";
          var branch_name = "<%=branch_name%>";
          var business_number = "<%=business_number%>";
          var material_man = "<%=material_man%>";
          var loan_begin = "<%=loan_begin%>";
          var loan_end = "<%=loan_end%>";
          var borrower_man = "<%=borrower_man%>";
          var pageNumber = "<%=borrower_man%>";
          //$.messager.alert("提示", fuzzy+"branch_name"+branch_name+"business_number"+business_number+"material_man"+material_man+"loan_begin"+loan_begin+"loan_end"+loan_end+"borrower_man"+borrower_man);
      //    system.out.println(fuzzy+"branch_name"+branch_name+"business_number"+business_number+"material_man"+material_man+"loan_begin"+loan_begin+"loan_end"+loan_end+"borrower_man"+borrower_man);
        //合规检查搜索条件信息 关时需要回显 end 
    </script>
    <script type="text/javascript" src="<%=basePath%>scripts/compliance/compliance_check.js"></script>
</head>
<body class="easyui-layout" fit="true">
<div region="center" id="center_panel">
    <div id="compliance_check_tab" class="easyui-tabs" style="padding: 10px">
        <div id="tab_div1" title="模糊查询" style="padding: 10px;">
            <table>
                <tr>
                    <td>
                        搜索条件：
                    </td>
                    <td>
                        <input id="fuzzy" type="text" style="width: 150px"/>
                    </td>
                    
                    <td>
                        <a class="easyui-linkbutton" href="javascript:fuzzySearch()">搜索</a>
                        <a class="easyui-linkbutton" href="javascript:clearFuzzyBox();">清空</a>
                    </td>
                    <td>
                        <font color="red">
                            (可输入业务单号、或借款人、或客户经理、分公司名称 不完整片段进行搜索)
                        </font>
                    </td>
                </tr>
            </table>
        </div>
        <div id="tab_div2" title="高级查询" style="padding: 10px;">
            <form id="searchForm">
                <table>
                    <tr>
                        <td align="right">
                            分公司名称：
                        </td>
                        <td>
                            <input id="branch_name" name="branch_name" type="text" style="width: 312px"/>
                        </td>
                        <td>
                            &nbsp;&nbsp;
                        </td>
                        <td align="right">
                            业务单号：
                        </td>
                        <td>
                            <input id="business_number" name="business_number" type="text" style="width: 150px"/>
                        </td>
                        <td>
                            &nbsp;&nbsp;
                        </td>
                        <td align="right">
                            资料提交人：
                        </td>
                        <td>
                            <input id="material_man" name="material_man" type="text" style="width: 150px"/>
                        </td>
                        <td>
                            &nbsp;&nbsp;
                        </td>
                        <td>
                            <a class="easyui-linkbutton" href="javascript:search_advanced()">搜索</a>
                            <a class="easyui-linkbutton" href="javascript:clear_advanced();">清空</a>
                        </td>
                    </tr>
                    <tr>
                        <td align="right">
                            放款日期：
                        </td>
                        <td>
                            <input id="loan_begin" name="loan_begin" class="easyui-datebox" style="width: 150px" editable="false"/>
                            -
                            <input id="loan_end" name="loan_end" class="easyui-datebox" style="width: 150px" editable="false"/>
                        </td>
                        <td>
                            &nbsp;&nbsp;
                        </td>
                        <td align="right">
                            借款人姓名：
                        </td>
                        <td colspan="6">
                            <input id="borrower_man" name="borrower_man" type="text" style="width: 150px"/>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </div>

    <div class="easyui-tabs" style="padding: 10px;">
        <div title="合规检查列表">
            <table id="ca_list"></table>
        </div>
    </div>
</div>
</body>
</html>
