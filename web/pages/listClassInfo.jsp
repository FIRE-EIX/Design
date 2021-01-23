<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: EIX
  Date: 2020/12/21
  Time: 21:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page import="java.sql.*" %>
<%@ page import="Utils.Tools" %>
<html>
<head>
    <title>学生成绩</title>
    <base href="http://localhost:8080/Design/pages/"/>
    <link rel="stylesheet" type="text/css" href="../easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="../easyui/themes/icon.css">
    <script type="text/javascript" src="../easyui/jquery.min.js"></script>
    <script type="text/javascript" src="../easyui/jquery.easyui.min.js"></script>
</head>
<body>
<table id="dg" title="班级信息" class="easyui-datagrid" toolbar="#toolbar580" style="width:99%;height:85%" fitColumns="true">
    <thead>
    <tr>
<%--        <th field="id" width="100">账号</th>--%>
        <th field="cno" width="100">班号</th>
        <th field="score" width="100">名称</th>
    </tr>
    <c:forEach var="classInfos" items="${requestScope.classInfos}">
        <tr>
            <th field="cno">${classInfos.classNumber}</th>
            <th field="score">${classInfos.className}</th>
        </tr>
    </c:forEach>
    </thead>
</table>
<div id="win666" class="easyui-dialog" title="新增班级" style="width: 400px; padding: 10px 20px; height: 410px;"
     closed="true">
    <form action="../addClassInfoServlet" method="post" style="margin-top: 20px; margin-left: 20px;">
        <div class="fitem">
            <input name="input666" placeholder="请输入班号" id="classid" style="width: 350px;height: 40px;" autocomplete="off"
                   required/><br><br>
            <input name="input656" placeholder="请输入班名" id="classname" style="width: 350px;height: 40px;" autocomplete="off"
                   required/><br><br>
        </div>
        <input id="submit666" name="submit" type="submit" class="easyui-linkbutton c6" iconcls="icon-ok"
               style="width: 90px;height: 40px;float: right; margin-right: 28px;"/>
    </form>
</div>
<div id="toolbar580" style="width: 100%">
    <a id="name_add_but666" href="#" data-options="iconCls:'icon-add'" class="my_but">添加班级</a>
</div>
<script>
    var screenHeight = $(window).height(); //当前浏览器窗口的高
    var hi = (screenHeight - 500) / 2;         //弹出窗口与顶部的距离
    $('#name_add_but666').linkbutton({
        onClick: function () {
            addFile666();
        }
    });

    function addFile666() {
        $('#win666').dialog({
            title: '添加班级',
            width: 450,
            height: 430,
            top: hi,
            closed: false,//显示对话框
            cache: false,
            modal: true
        });
    }
</script>
</body>
</html>
