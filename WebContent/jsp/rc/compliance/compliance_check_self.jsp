<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2014-8-5
  Time: 11:03
  合规检查-营业部录入页面
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<input id="complianceCheckId" type="hidden"/>
<%--1.营业部自查记录--%>
<div id="div_self_outer"
     style="height: 320px;border: solid 1px #9ABBE8;margin-top: 5px;margin-left: 10px;margin-right: 20px;">
    <div id="div_self" class="easyui-panel" title="营业部自查记录" fit="true" border="false">
        <table id="check_self" style="border: solid 1px #9ABBE8;"></table>
        <table width="100%">
            <tr>
                <td align="right" colspan="2">
                    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-addOne" plain="true"
                       onclick="addRow();">添加</a>
                </td>
            </tr>
            <tr>
                <td nowrap="nowrap">
                    备注：
                </td>
                <td>
                    <textarea id="remark_self"
                              style="border-color: #9ABBE8;resize: none"
                              maxlength="200" validType="maxLength[200]"
                              onkeydown="textCount('remark_self','counter',200);$('#counter').show()"
                              onkeyup="textCount('remark_self','counter',200);$('#counter').show()"></textarea>
                    <br/>
                    <span id="counter">还可以输入200个字</span>
                    <script type="text/javascript">
                        /*调整文本域宽度*/
                        var w = $('#div_self')[0].clientWidth, h = 155;
                        $('#remark_self').width(w - 65).height(h);
                    </script>
                    <br/><br/>
                </td>
            </tr>
            <tr>
                <td align="right" nowrap="nowrap" colspan="2">
                    <a class="easyui-linkbutton abut" onclick="selfSave()">保存</a>
                    <a class="easyui-linkbutton abut" onclick="selfCancel()">取消</a>
                </td>
            </tr>
        </table>
    </div>
</div>