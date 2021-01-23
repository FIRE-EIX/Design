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
<table id="dg" title="学生列表" class="easyui-datagrid" toolbar="#toolbar56" style="width:99%;height:85%" fitColumns="true">
    <thead>
    <tr>
        <th field="id" width="100">账号</th>
        <th field="studentName" width="100">姓名</th>
        <th field="sex" width="100">性别</th>
        <th field="entranceYear" width="100">入学年份</th>
        <th field="classNumber" width="100">所在班级</th>
        <th field="option" width="50">操作</th>
    </tr>
    <c:forEach var="students" items="${requestScope.students}">
        <tr>
            <th field="id">${students.id}</th>
            <th field="studentName">${students.studentName}</th>
            <th field="sex">${students.sex}</th>
            <th field="entranceYear">${students.entranceYear}</th>
            <th field="classNumber">${students.classNumber}</th>
            <th field="option"><a id="${students.id}" href="#" data-options="iconCls:'icon-edit'"
                                  class="easyui-linkbutton" iconCls="icon-cancel" onclick="addFile56(this)">删除</a></th>
        </tr>
    </c:forEach>
    </thead>
</table>
<div id="win55" class="easyui-dialog" title="添加学生" style="width: 400px; padding: 10px 20px; height: 410px;"
     closed="true">
    <form action="../addStudentServlet" method="post" style="margin-top: 20px; margin-left: 20px;">
        <div class="fitem">
            <input placeholder="请输入账号" id="id55" name="id55" style="width: 350px;height: 40px;" autocomplete="off"
                   required/><br><br>
            <input placeholder="请输入姓名" id="name55" name="name55" style="width: 350px;height: 40px;" autocomplete="off"
                   required/><br><br>
            性别选择：
            <select class="easyui-combobox" name="sex55" id="sex55" style="width: 100px">
                <option value="男">男</option>
                <option value="女">女</option>
            </select><br><br>
            <input placeholder="请输入入学年份" name="year55" id="year55" style="width: 350px;height: 40px;"
                   autocomplete="off" required/><br><br>
            <input placeholder="请输入入班级号" name="class55" id="class55" style="width: 350px;height: 40px;"
                   autocomplete="off" required/><br><br>
            <input id="submit55" name="submit55" type="submit" class="easyui-linkbutton c6" iconcls="icon-ok"
                   style="width: 90px;height: 40px;float: right; margin-right: 28px;"/>
        </div>
        <input id="submit2" name="submit" type="submit" class="easyui-linkbutton c6" iconcls="icon-ok"
               style="width: 90px;height: 40px;float: right; margin-right: 28px;"/>
    </form>
</div>
<div id="win56" class="easyui-dialog" title="删除学生" style="width: 400px; padding: 10px 20px; height: 410px;"
     closed="true">
    <form action="../deleteStudentServlet" method="post" style="margin-top: 20px; margin-left: 20px;">
        <div class="fitem">
            <input name="id56" id="id56" class="textbox" type="text" style="width: 350px;height: 40px;"
                   required/><br><br>
        </div>
        <input id="submit1" name="submit" type="submit" class="easyui-linkbutton c6"
               style="width: 90px;height: 40px;float: right; margin-right: 28px;"/>
    </form>
</div>
<div id="win70" class="easyui-dialog" title="信息录入" style="width: 400px; padding: 10px 20px; height: 410px;"
     closed="true">
    <form action="../enterInformation" method="post" style="margin-top: 20px; margin-left: 20px;">
        <div class="fitem">
            <input placeholder="请输入学生账号" name="id70" id="id70" class="textbox" type="text" style="width: 350px;height: 40px;"
                   required/><br><br>
            <input placeholder="请输入课程号" name="cid70" id="cid70" class="textbox" type="text" style="width: 350px;height: 40px;"
                   required/><br><br>
            <input placeholder="请输入教师账号" name="tid70" id="tid70" class="textbox" type="text" style="width: 350px;height: 40px;"
                   required/><br><br>
            学期选择：
            <select class="easyui-combobox" name="addTerm70" id="addTerm70" style="width: 100px">
                <option value="20211">20211</option>
                <option value="20202">20202</option>
                <option value="20201">20201</option>
                <option value="20192">20192</option>
                <option value="20191">20191</option>
            </select>
        </div>
        <input id="submit70" name="submit70" type="submit" class="easyui-linkbutton c6"
               style="width: 90px;height: 40px;float: right; margin-right: 28px;"/>
    </form>
</div>
<div id="toolbar56" style="width: 100%">
    <a id="name_add_but55" href="#" data-options="iconCls:'icon-add'" class="my_but">添加学生</a>
<%--    <a id="name_add_but56" href="#" data-options="iconCls:'icon-edit'" class="my_but">修改信息</a>--%>
    <a id="name_add_but70" href="#" data-options="iconCls:'icon-edit'" class="my_but">信息录入</a>
</div>
<script>
    var screenHeight = $(window).height(); //当前浏览器窗口的高
    var hi = (screenHeight - 500) / 2;         //弹出窗口与顶部的距离
    $('#name_add_but55').linkbutton({
        onClick: function () {
            addFile55();
        }
    });

    $('#name_add_but56').linkbutton({
        onClick: function () {
            addFile56();
        }
    });

    $('#name_add_but70').linkbutton({
        onClick: function () {
            addFile70();
        }
    });

    function addFile55() {
        $('#win55').dialog({
            title: '添加学生',
            width: 450,
            height: 430,
            top: hi,
            closed: false,//显示对话框
            cache: false,
            modal: true
        });
    }

    function addFile56(obj) {
        var id = obj.id;
        document.getElementById("id56").value = id;
        $('#win56').dialog({
            title: '删除学生',
            width: 450,
            height: 300,
            top: 150,
            closed: false,//显示对话框
            cache: false,
            modal: true
        });
    }

    function addFile70() {
        $('#win70').dialog({
            title: '信息录入',
            width: 450,
            height: 500,
            top: 150,
            closed: false,//显示对话框
            cache: false,
            modal: true
        });
    }

    function del() {
        var fdel = window.confirm("确定送审？");
        if (fdel) {
            return true;
        } else {
            return false;
        }
    }
</script>
</body>
</html>

