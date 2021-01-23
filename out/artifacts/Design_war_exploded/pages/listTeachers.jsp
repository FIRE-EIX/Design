<%--
  Created by IntelliJ IDEA.
  User: EIX
  Date: 2020/12/24
  Time: 23:02
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page import="java.sql.*" %>
<%@ page import="Utils.Tools" %>
<html>
<head>
    <title>教师信息</title>
    <base href="http://localhost:8080/Design/pages/"/>
    <link rel="stylesheet" type="text/css" href="../easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="../easyui/themes/icon.css">
    <script type="text/javascript" src="../easyui/jquery.min.js"></script>
    <script type="text/javascript" src="../easyui/jquery.easyui.min.js"></script>
</head>
<body>
<%--<br>
<center>
    <div>
        <form action="../findStudentServlet" method="post">
            <select class="easyui-combobox" name="searchCol" id="searchCol" style="width: 100px">
                <option value="id">学号</option>
                <option value="name">姓名</option>
            </select>
            <input type="text" class="textbox" name="searchValue"
                   style="width:500px;height: 30px;" id="searchValue"
                   placeholder="请输入对应的搜索关键词">
            <button type="submit" class="easyui-linkbutton c6">查询</button>
        </form>
    </div>
</center>--%>
<table id="dg" title="教师列表" class="easyui-datagrid" toolbar="#toolbar1" style="width:99%;height:85%" fitColumns="true">
    <thead>
    <tr>
        <th field="id" width="100">账号</th>
        <th field="cno" width="100">姓名</th>
        <th field="score" width="100">性别</th>
        <th field="term" width="100">教授班级</th>
        <th field="option" width="50">操作</th>
    </tr>
    <c:forEach var="teachers" items="${requestScope.teachers}">
        <tr>
            <th field="id">${teachers.id}</th>
            <th field="cno">${teachers.teacherName}</th>
            <th field="score">${teachers.sex}</th>
            <th field="term">${teachers.classNumber}</th>
            <th field="option"><a id="${teachers.id}" href="#" data-options="iconCls:'icon-edit'"
                                  class="easyui-linkbutton" iconCls="icon-cancel" onclick="addFile678(this)">删除</a></th>
        </tr>
    </c:forEach>
    </thead>
</table>
<div id="win53" class="easyui-dialog" title="添加教师" style="width: 400px; padding: 10px 20px; height: 410px;"
     closed="true">
    <form action="../addTeacherServlet" method="post" style="margin-top: 20px; margin-left: 20px;">
        <div class="fitem">
            <input placeholder="请输入账号" id="id53" name="id53" style="width: 350px;height: 40px;" autocomplete="off"
                   required/><br><br>
            <input placeholder="请输入姓名" id="name53" name="name53" style="width: 350px;height: 40px;" autocomplete="off"
                   required/><br><br>
            性别选择：
            <select class="easyui-combobox" name="sex53" id="sex53" style="width: 100px">
                <option value="男">男</option>
                <option value="女">女</option>
            </select><br><br>
            <input placeholder="请输入教授班级" name="class53" id="class53" style="width: 350px;height: 40px;"
                   autocomplete="off"
                   required/><br><br>
            <input id="submit53" name="submit53" type="submit" class="easyui-linkbutton c6" iconcls="icon-ok"
                   style="width: 90px;height: 40px;float: right; margin-right: 28px;"/>
        </div>
    </form>
</div>
<div id="win678" class="easyui-dialog" title="删除教师" style="width: 400px; padding: 10px 20px; height: 410px;"
     closed="true">
    <form action="../deleteTeacherServlet" method="post" style="margin-top: 20px; margin-left: 20px;">
        <div class="fitem">
            <input name="id678" id="id678" class="textbox" type="text"
                   style="width: 350px;height: 40px;" required/><br><br>
        </div>
        <input id="submit678" name="submit678" type="submit" class="easyui-linkbutton c6"
               style="width: 90px;height: 40px;float: right; margin-right: 28px;"/>
    </form>
</div>
<div id="toolbar1" style="width: 100%">
    <a id="name_add_but53" href="#" data-options="iconCls:'icon-add'" class="my_but">添加教师</a>
    <%--    <a id="name_add_but54" href="#" data-options="iconCls:'icon-edit'" class="my_but">删除教师</a>--%>
</div>
<script>
    var screenHeight = $(window).height(); //当前浏览器窗口的高
    var hi = (screenHeight - 500) / 2;         //弹出窗口与顶部的距离
    $('#name_add_but53').linkbutton({
        onClick: function () {
            addFile53();
        }
    });

    $('#name_add_but678').linkbutton({
        onClick: function () {
            addFile678();
        }
    });

    function addFile53() {
        $('#win53').dialog({
            title: '添加教师',
            width: 450,
            height: 400,
            top: hi,
            closed: false,//显示对话框
            cache: false,
            modal: true
        });
    }

    function addFile678(obj) {
        var id = obj.id;
        document.getElementById("id678").value = id;
        $('#win678').dialog({
            title: '删除教师',
            width: 450,
            height: 300,
            top: 150,
            closed: false,//显示对话框
            cache: false,
            modal: true
        });
    }
</script>
</body>
</html>

