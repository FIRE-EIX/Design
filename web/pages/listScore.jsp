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
<br>
<center>
    <div>
        <%--        <form id="newArticleForm" name="newArticleForm" action="../findStudentServlet" method="post" onsubmit="openTabs('成绩查看','../findStudentServlet')">--%>
        <form action="../findStudentServlet" method="post">
            <select class="easyui-combobox" name="searchCol" id="searchCol" style="width: 100px">
                <option value="course_name">课程名</option>
                <option value="term">学期</option>
            </select>
            <input type="text" class="textbox" name="searchValue"
                   style="width:500px;height: 30px;" id="searchValue"
                   placeholder="请输入对应的搜索关键词">
            <button type="submit" class="easyui-linkbutton c6">查询</button>
            <%--            <input type="button" name="submit" value="查询" onclick="openTabs('成绩查看','../findStudentServlet')"/>--%>
            <%--            <button type="submit" class="easyui-linkbutton c6" onclick="openTabs('成绩查看','../findStudentServlet')">查询</button>--%>
        </form>
    </div>
</center>
<table id="dg" title="成绩查询" class="easyui-datagrid" toolbar="#toolbar0" style="width:99%;height:85%" fitColumns="true">
    <thead>
    <tr>
<%--        <th field="id" width="100">账号</th>--%>
        <th field="cno" width="100">课程名</th>
        <th field="score" width="100">得分</th>
        <th field="term" width="100">学期</th>
    </tr>
    <c:forEach var="scoreInfo" items="${requestScope.score}">
        <tr>
<%--            <th field="id">${scoreInfo.id}</th>--%>
            <th field="cno">${scoreInfo.courseNumber}</th>
            <th field="score">${scoreInfo.score}</th>
            <th field="term">${scoreInfo.term}</th>
        </tr>
    </c:forEach>
    </thead>
</table>
<div id="toolbar" style="width: 100%">
<%--    <a id="name_add_but46" href="#" data-options="iconCls:'icon-add'" class="my_but">成绩录入</a>--%>
<%--    <a id="name_add_but35" href="#" data-options="iconCls:'icon-edit'" class="my_but">修改信息</a>--%>
</div>
<script>
    var screenHeight = $(window).height(); //当前浏览器窗口的高
    var hi = (screenHeight - 500) / 2;         //弹出窗口与顶部的距离
    $('#name_add_but46').linkbutton({
        onClick: function () {
            addFile46();
        }
    });

    $('#name_add_but35').linkbutton({
        onClick: function () {
            addFile35();
        }
    });


    function addFile46() {
        $('#win').dialog({
            title: '成绩录入',
            width: 450,
            height: 430,
            top: hi,
            closed: false,//显示对话框
            cache: false,
            modal: true
        });
    }

    function confirm() {
        var fconfirm = window.confirm("确定送审？");
        if (fconfirm) {
            return true;
        } else {
            return false;
        }
    }

    function addFile35(obj) {
        var id = obj.id;
        var cid = document.getElementById("ST0119").getElementsByTagName('a').length;
        document.getElementById("id35").value = id;
        document.getElementById("cno1").value = cid;
        // alert(cid);
        $('#win35').dialog({
            title: '修改成绩',
            width: 450,
            height: 500,
            top: 150,
            closed: false,//显示对话框
            cache: false,
            modal: true
        });
    }
    /*function openTabs(title, url) {
        console.log(title);
        // 判断选项卡是否存在
        var exists = $("#tt").tabs("exists", title);
        if (exists) { //如果返回true，说明选项卡存在，选中当前选项卡
            var myform1 = document.getElementById('newArticleForm');
            var searchCol = document.getElementById("searchCol").value;
            var searchValue = document.getElementById("searchValue").value;
            console.log(form1);
            alert(myform1.searchValue);
            $("#tt").tabs('close', title);
            $('#tt').tabs('add', {
                title: title,
                selected: true,
                closable: true,
                iconCls: 'icon-edit',
                href: url
            });
            document.getElementById("myform1").submit();
        }
        else { //如果返回false，说明选项卡不存在，新建选项卡
            $('#tt').tabs('add', {
                title: title,
                selected: true,
                closable: true,
                iconCls: 'icon-edit',
                href: url
            });
        }
    }*/
</script>
</body>
</html>
