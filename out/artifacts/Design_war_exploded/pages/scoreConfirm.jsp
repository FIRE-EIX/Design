<%--
  Created by IntelliJ IDEA.
  User: EIX
  Date: 2020/12/25
  Time: 22:41
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page import="java.sql.*" %>
<%@ page import="Utils.Tools" %>
<html>
<head>
    <title>成绩审核</title>
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
        &lt;%&ndash;        <form id="newArticleForm" name="newArticleForm" action="../findStudentServlet" method="post" onsubmit="openTabs('成绩查看','../findStudentServlet')">&ndash;%&gt;
        <form action="../findStudentServlet" method="post">
            <select class="easyui-combobox" name="searchCol" id="searchCol" style="width: 100px">
                <option value="id">学号</option>
                <option value="name">姓名</option>
            </select>
            <input type="text" class="textbox" name="searchValue"
                   style="width:500px;height: 30px;" id="searchValue"
                   placeholder="请输入对应的搜索关键词">
            <button type="submit" class="easyui-linkbutton c6">查询</button>
            &lt;%&ndash;            <input type="button" name="submit" value="查询" onclick="openTabs('成绩查看','../findStudentServlet')"/>&ndash;%&gt;
            &lt;%&ndash;            <button type="submit" class="easyui-linkbutton c6" onclick="openTabs('成绩查看','../findStudentServlet')">查询</button>&ndash;%&gt;
        </form>
    </div>
</center>--%>
<table id="dg" title="成绩审核" class="easyui-datagrid" toolbar="#toolbar150" style="width:99%;height:85%" fitColumns="true">
    <thead>
    <tr>
        <th field="id" width="100">学号</th>
        <th field="studentName" width="100">姓名</th>
        <th field="courseNumber" width="100">课程号</th>
        <th field="teacherName" width="100">授课老师</th>
        <th field="term" width="100">学期</th>
        <th field="score" width="100">分数</th>
        <th field="confirm" width="50">状态</th>
        <th field="option" width="100">操作</th>
    </tr>
    <c:forEach var="scoreInfos" items="${requestScope.scoreInfos}">
        <c:if test="${!'未审核'.equals(scoreInfos.confirm)}">
            <tr>
                <th field="id">${scoreInfos.id}</th>
                <th field="studentName">${scoreInfos.studentName}</th>
                <th field="courseNumber">${scoreInfos.courseNumber}</th>
                <th field="teacherName">${scoreInfos.teacherName}</th>
                <th field="term">${scoreInfos.term}</th>
                <th field="score">${scoreInfos.score}</th>
                <th field="confirm">${scoreInfos.confirm}</th>
                <th field="option" width="100">
                        <%--                <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick=" return confirm()">送审</a>--%>
                        <%--                <a id="${scoreInfos.id}" href="#" class="easyui-linkbutton" iconCls="icon-edit" onclick="addFile50(this)">修改</a>--%>
                    <c:if test="${'审核中'.equals(scoreInfos.confirm)}">
                        <a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="addFile51()">通过</a>
                        <a href="#" class="easyui-linkbutton" iconCls="icon-no" onclick="addFile52()">驳回</a>
                    </c:if>
                </th>
            </tr>
        </c:if>
    </c:forEach>
    </thead>
</table>
<div id="win50" class="easyui-dialog" title="操作" style="width: 400px; padding: 10px 20px; height: 410px;" closed="true">
    <form action="../addScoreServlet" method="post" style="margin-top: 20px; margin-left: 20px;">
        <div class="fitem">
            <input name="input1" placeholder="请输入账号" id="id" style="width: 350px;height: 40px;" autocomplete="off"
                   required/><br><br>
            <input name="input2" placeholder="请输入课程号" id="cno" style="width: 350px;height: 40px;" autocomplete="off"
                   required/><br><br>
            <input name="input3" placeholder="请输入成绩" id="score" style="width: 350px;height: 40px;" autocomplete="off"
                   onkeyup="checksex()"><span id="tishi1"></span></input><br><br>
            <select class="easyui-combobox" name="addTerm" id="addTerm" style="width: 100px">
                <option value="20211">20211</option>
                <option value="20202">20202</option>
                <option value="20201">20201</option>
                <option value="20192">20192</option>
                <option value="20191">20191</option>
            </select>
        </div>
        <input id="submit2" name="submit" type="submit" class="easyui-linkbutton c6" iconcls="icon-ok"
               style="width: 90px;height: 40px;float: right; margin-right: 28px;"/>
    </form>
</div>
<div id="win51" class="easyui-dialog" title="操作" style="width: 400px; padding: 10px 20px; height: 410px;"
     closed="true">
    <form action="../passConfirmScoreServlet" method="post" style="margin-top: 20px; margin-left: 20px;">
        <div class="fitem">
            <input name="id51" id="id51" placeholder="学号" class="textbox" type="text"
                   style="width: 350px;height: 40px;" required/><br><br>
            <input name="cno51" id="cno51" placeholder="课程号" class="textbox" type="text"
                   style="width: 350px;height: 40px;" required/><br><br>
        </div>
        <input id="submit51" name="submit" type="submit" class="easyui-linkbutton c6"
               style="width: 90px;height: 40px;float: right; margin-right: 28px;"/>
    </form>
</div>
<div id="win52" class="easyui-dialog" title="操作" style="width: 400px; padding: 10px 20px; height: 410px;"
     closed="true">
    <form action="../confirmScoreServlet" method="post" style="margin-top: 20px; margin-left: 20px;">
        <div class="fitem">
            <input name="id52" id="id52" placeholder="学号" class="textbox" type="text"
                   style="width: 350px;height: 40px;" required/><br><br>
            <input name="cno52" id="cno52" placeholder="课程号" class="textbox" type="text"
                   style="width: 350px;height: 40px;" required/><br><br>
        </div>
        <input id="submit52" name="submit" type="submit" class="easyui-linkbutton c6"
               style="width: 90px;height: 40px;float: right; margin-right: 28px;"/>
    </form>
</div>
<div id="toolbar150" style="width: 100%">
    <%--    <a id="name_add_but47" href="#" data-options="iconCls:'icon-add'" class="my_but">成绩录入</a>--%>
    <%--    <a id="name_add_but49" href="#" data-options="iconCls:'icon-edit'" class="my_but">成绩送审</a>--%>
</div>
<script>
    var screenHeight = $(window).height(); //当前浏览器窗口的高
    var hi = (screenHeight - 500) / 2;         //弹出窗口与顶部的距离
    $('#name_add_but50').linkbutton({
        onClick: function () {
            addFile50();
        }
    });

    $('#name_add_but51').linkbutton({
        onClick: function () {
            addFile51();
        }
    });

    $('#name_add_but52').linkbutton({
        onClick: function () {
            addFile52();
        }
    });

    function addFile50() {
        $('#win50').dialog({
            title: '操作',
            width: 450,
            height: 300,
            top: hi,
            closed: false,//显示对话框
            cache: false,
            modal: true
        });
    }

    function addFile51() {
        /*var id = obj.id;
        document.getElementById("id48").value = id;*/
        $('#win51').dialog({
            title: '操作',
            width: 450,
            height: 300,
            top: 150,
            closed: false,//显示对话框
            cache: false,
            modal: true
        });
    }

    function addFile52() {
        $('#win52').dialog({
            title: '操作',
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
