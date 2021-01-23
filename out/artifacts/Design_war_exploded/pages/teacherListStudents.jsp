<%--
  Created by IntelliJ IDEA.
  User: EIX
  Date: 2020/12/25
  Time: 12:32
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
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
<br>
<center>
    <div>
        <%--        <form id="newArticleForm" name="newArticleForm" action="../findStudentServlet" method="post" onsubmit="openTabs('成绩查看','../findStudentServlet')">--%>
        <form action="../teacherFindStudentServlet" method="post">
            <select class="easyui-combobox" name="searchCol" id="searchCol" style="width: 100px">
                <option value="id">学号</option>
                <option value="name">姓名</option>
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
<table id="dg" title="成绩列表" class="easyui-datagrid" toolbar="#toolbar47" style="width:99%;height:85%" fitColumns="true">
    <thead>
    <tr>
        <th field="id" width="100">学号</th>
        <th field="studentName" width="100">姓名</th>
        <th field="courseNumber" width="100">课程号</th>
        <th field="teacherName" width="100">课程名</th>
        <th field="term" width="100">学期</th>
        <th field="score" width="100">分数</th>
        <th field="confirm" width="50">状态</th>
        <th field="option" width="100">操作</th>
    </tr>
    <c:forEach var="scoreInfos" items="${requestScope.scoreInfos}">
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
                <c:if test="${!'审核通过'.equals(scoreInfos.confirm)}">
                    <a id="${scoreInfos.id}" href="#" data-options="iconCls:'icon-edit'" class="easyui-linkbutton" iconCls="icon-edit"
                       onclick="addFile49(this)">成绩送审</a>
                    <a id="${scoreInfos.id}" href="#" class="easyui-linkbutton" iconCls="icon-edit"
                       onclick="addFile48(this)">修改</a>
                </c:if>
            </th>
        </tr>
    </c:forEach>
    </thead>
</table>
<div id="win47" class="easyui-dialog" title="成绩录入" style="width: 400px; padding: 10px 20px; height: 410px;"
     closed="true">
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
<div id="win48" class="easyui-dialog" title="修改成绩" style="width: 400px; padding: 10px 20px; height: 410px;"
     closed="true">
    <form action="../editScoreServlet" method="post" style="margin-top: 20px; margin-left: 20px;">
        <div class="fitem">
            <input name="id48" id="id48" class="textbox" type="text"
                   style="width: 350px;height: 40px;" readonly="readonly"/><br><br>
            <input name="cno1" id="cno1" placeholder="课程号" class="textbox" type="text"
                   style="width: 350px;height: 40px;" required/><br><br>
            <input name="score1" id="score1" placeholder="得分" class="textbox" type="text"
                   style="width: 350px;height: 40px;" required/><br><br>
            <select class="easyui-combobox" name="term1" id="term1" style="width: 100px">
                <option value="20211">20211</option>
                <option value="20202">20202</option>
                <option value="20201">20201</option>
                <option value="20192">20192</option>
                <option value="20191">20191</option>
            </select>
        </div>
        <input id="submit1" name="submit" type="submit" class="easyui-linkbutton c6"
               style="width: 90px;height: 40px;float: right; margin-right: 28px;"/>
    </form>
</div>
<div id="win49" class="easyui-dialog" title="成绩送审" style="width: 400px; padding: 10px 20px; height: 410px;"
     closed="true">
    <form action="../confirmScoreServlet" method="post" style="margin-top: 20px; margin-left: 20px;">
        <div class="fitem">
            <input name="id49" id="id49" placeholder="学号" class="textbox" type="text"
                   style="width: 350px;height: 40px;" required/><br><br>
            <input name="cno49" id="cno49" placeholder="课程号" class="textbox" type="text"
                   style="width: 350px;height: 40px;" required/><br><br>
        </div>
        <input id="submit49" name="submit" type="submit" class="easyui-linkbutton c6"
               style="width: 90px;height: 40px;float: right; margin-right: 28px;"/>
    </form>
</div>
<div id="win60" class="easyui-dialog" title="统计" style="width: 400px; padding: 10px 20px; height: 410px;"
     closed="true">
    <form action="../statisticsServlet" method="post" style="margin-top: 20px; margin-left: 20px;">
        <div class="fitem">
            <input name="cno60" id="cno60" placeholder="课程号" class="textbox" type="text"
                   style="width: 350px;height: 40px;" required/><br><br>
        </div>
        <input id="submit60" name="submit" type="submit" class="easyui-linkbutton c6"
               style="width: 90px;height: 40px;float: right; margin-right: 28px;"/>
    </form>
</div>
<div id="toolbar47" style="width: 100%">
    <a id="name_add_but47" href="#" data-options="iconCls:'icon-add'" class="my_but">成绩录入</a>
    <a href="../listFailuresServlet" data-options="iconCls:'icon-add'" class="easyui-linkbutton" iconCls="icon-no">不及格名单</a>
    <a href="../exportExcelServlet" data-options="iconCls:'icon-add'" class="easyui-linkbutton" iconCls="icon-save">导出到Excel</a>
    <a id="name_add_but60" href="#" data-options="iconCls:'icon-add'" class="my_but">统计</a>
</div>
<script>
    var screenHeight = $(window).height(); //当前浏览器窗口的高
    var hi = (screenHeight - 500) / 2;         //弹出窗口与顶部的距离
    $('#name_add_but47').linkbutton({
        onClick: function () {
            addFile47();
        }
    });

    $('#name_add_but48').linkbutton({
        onClick: function () {
            addFile48();
        }
    });

    $('#name_add_but49').linkbutton({
        onClick: function () {
            addFile49();
        }
    });

    $('#name_add_but60').linkbutton({
        onClick: function () {
            addFile60();
        }
    });

    function addFile47() {
        $('#win47').dialog({
            title: '成绩录入',
            width: 450,
            height: 430,
            top: hi,
            closed: false,//显示对话框
            cache: false,
            modal: true
        });
    }

    function addFile48(obj) {
        var id = obj.id;
        document.getElementById("id48").value = id;
        $('#win48').dialog({
            title: '修改成绩',
            width: 450,
            height: 500,
            top: 150,
            closed: false,//显示对话框
            cache: false,
            modal: true
        });
    }

    function addFile49(obj) {
        var id = obj.id;
        document.getElementById("id49").value = id;
        $('#win49').dialog({
            title: '成绩送审',
            width: 450,
            height: 300,
            top: 150,
            closed: false,//显示对话框
            cache: false,
            modal: true
        });
    }

    function addFile60() {
        $('#win60').dialog({
            title: '统计',
            width: 450,
            height: 430,
            top: hi,
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
