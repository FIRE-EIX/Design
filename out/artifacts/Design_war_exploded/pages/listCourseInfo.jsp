<%--
  Created by IntelliJ IDEA.
  User: EIX
  Date: 2020/12/25
  Time: 15:21
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page import="java.sql.*" %>
<%@ page import="Utils.Tools" %>
<html>
<head>
    <title>课程信息</title>
    <base href="http://localhost:8080/Design/pages/"/>
    <link rel="stylesheet" type="text/css" href="../easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="../easyui/themes/icon.css">
    <script type="text/javascript" src="../easyui/jquery.min.js"></script>
    <script type="text/javascript" src="../easyui/jquery.easyui.min.js"></script>
</head>
<body>
<table id="dg" title="课程信息" class="easyui-datagrid" toolbar="#toolbar4" style="width:99%;height:85%" fitColumns="true">
    <thead>
    <tr>
        <th field="id" width="100">课程号</th>
        <th field="cname" width="100">课程名</th>
        <th field="option" width="100">操作</th>
    </tr>
    <c:forEach var="courseInfos" items="${requestScope.courseInfos}">
        <tr>
            <th field="id">${courseInfos.courseId}</th>
            <th field="cname">${courseInfos.courseName}</th>
            <th field="option"><a id="${courseInfos.courseId}" href="#" data-options="iconCls:'icon-edit'"
                                  class="easyui-linkbutton" iconCls="icon-edit" onclick="addFile37(this)">修改信息</a></th>
        </tr>
    </c:forEach>
    </thead>
</table>
<div id="win45" class="easyui-dialog" title="新增课程" style="width: 400px; padding: 10px 20px; height: 410px;"
     closed="true">
    <form action="../addCourseServlet" method="post" style="margin-top: 20px; margin-left: 20px;">
        <div class="fitem">
            <input name="input1" placeholder="请输入课程号" id="id" style="width: 350px;height: 40px;" autocomplete="off"
                   required/><br><br>
            <input name="input2" placeholder="请输入课程名" id="cno" style="width: 350px;height: 40px;" autocomplete="off"
                   required/><br><br>
        </div>
        <input id="submit3" name="submit6" type="submit" class="easyui-linkbutton c6" iconcls="icon-ok"
               style="width: 90px;height: 40px;float: right; margin-right: 28px;"/>
    </form>
</div>
<div id="win37" class="easyui-dialog" title="修改课程信息" style="width: 400px; padding: 10px 20px; height: 410px;"
     closed="true">
    <form action="../editCourseServlet" method="post" style="margin-top: 20px; margin-left: 20px;">
        <div class="fitem">
            <input value="1" name="cid1" id="cid1" class="textbox" type="text"
                   style="width: 350px;height: 40px;" readonly="readonly"/><br><br>
            <input name="cname1" id="cno1" placeholder="课程名" class="textbox" type="text"
                   style="width: 350px;height: 40px;" required/><br><br>
        </div>
        <input id="submit1" name="submit" type="submit" class="easyui-linkbutton c6"
               style="width: 90px;height: 40px;float: right; margin-right: 28px;"/>
    </form>
</div>
<div id="toolbar4" style="width: 100%">
    <a id="name_add_but43" href="#" data-options="iconCls:'icon-add'" class="my_but">新增课程</a>
    <%--    <a id="name_add_but44" href="#" data-options="iconCls:'icon-edit'" class="my_but">修改信息</a>--%>
</div>
<script>
    var screenHeight = $(window).height(); //当前浏览器窗口的高
    var hi = (screenHeight - 500) / 2;         //弹出窗口与顶部的距离
    $('#name_add_but43').linkbutton({
        onClick: function () {
            addFile36();
        }
    });

    $('#name_add_but44').linkbutton({
        onClick: function () {
            addFile37();
        }
    });

    function addFile36() {
        $('#win45').dialog({
            title: '新增课程',
            width: 450,
            height: 430,
            top: hi,
            closed: false,//显示对话框
            cache: false,
            modal: true
        });
    }

    function addFile37(obj) {
        var id = obj.id;
        document.getElementById("cid1").value = id;
        $('#win37').dialog({
            title: '修改课程信息',
            width: 450,
            height: 300,
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
