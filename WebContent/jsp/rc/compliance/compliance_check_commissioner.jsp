<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2014-8-5
  Time: 11:03
  合规检查-合规检查录入页面
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<input id="complianceCheckId" type="hidden"/>

<div>
    <%--1.营业部自查记录--%>
    <div id="div_self_outer"
         style="border: solid 1px #9ABBE8;margin-top: 5px;margin-left: 10px;margin-right: 10px;padding: 0px;height: auto">
        <div id="div_self" class="easyui-panel" title="营业部自查记录" style="padding: 10px;" fit="true" border="false">
            <table id="check_self" style="border: solid 1px #9ABBE8;"></table>
        </div>
        <div style="margin-left: 10px">
            <table>
                <tr>
                    <td>
                        备注：
                    </td>
                    <td id="remark_self"></td>
                </tr>
            </table>
        </div>
    </div>

    <%--2.客服检查记录--%>
    <div id="div_customer_outer"
         style="border: solid 1px #9ABBE8;margin-top: 5px;margin-left: 10px;margin-right: 10px;padding: 0px;">
        <div class="easyui-panel" title="客服检查记录" style="padding: 10px;" fit="true" border="false">
            <table id="check_customer" style="border: solid 1px #9ABBE8"></table>
        </div>
        <div style="margin-left: 10px">
            <table>
                <tr>
                    <td>
                        备注和意见：
                    </td>
                    <td id="remark_customer"></td>
                </tr>
            </table>
        </div>
    </div>
    <%--3.合规检查记录--%>
    <div title="ererer" style="border: solid 1px #9ABBE8;margin-top: 5px;margin-left: 10px;margin-right: 10px;padding: 0px;height: auto;">
        <div class="easyui-panel" title="合规检查记录" style="padding: 10px;height: 330px" fit="true" border="false;">
            <table id="check_commissioner" style="border: solid 1px #9ABBE8"></table>
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
                        <textarea id="remark_commissioner"
                                  style="border-color: #9ABBE8;resize: none"
                                  maxlength="200" validType="maxLength[200]"
                                  onkeydown="textCount('remark_commissioner','counter',200);$('#counter').show()"
                                  onkeyup="textCount('remark_commissioner','counter',200);$('#counter').show()"></textarea>
                        <br/>
                        <span id="counter">还可以输入200个字</span>
                        <script type="text/javascript">
                            /*调整文本域宽度*/
                            var w = $('#div_self')[0].clientWidth, h = 155;
                            $('#remark_commissioner').width(w - 65).height(h);
                        </script>
                        <br/><br/>
                    </td>
                </tr>
                <tr>
                    <td align="right" nowrap="nowrap" colspan="2">
                        <a class="easyui-linkbutton abut" onclick="commissionSave()">保存</a>
                        <a class="easyui-linkbutton abut" onclick="commissionCancel()">取消</a>
                    </td>
                </tr>
            </table>
        </div>
    </div>
</div>