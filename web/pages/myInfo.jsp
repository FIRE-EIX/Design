<%--
  Created by IntelliJ IDEA.
  User: EIX
  Date: 2020/6/26
  Time: 12:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>个人中心</title>
    <base href="http://localhost:8080/Design/pages/" />
    <link rel="stylesheet" type="text/css" href="../easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="../easyui/themes/icon.css">
    <script type="text/javascript" src="../easyui/jquery.min.js"></script>
    <script type="text/javascript" src="../easyui/jquery.easyui.min.js"></script>
</head>
<body>
<div style="height: 100%;width: 100%;background-color: white" align="center">
    <div style="width: 1200px;height: 100%; background-color: #95B8E7">
        <div style="height:130px;width: 100%;background-color: royalblue" border=false>
            </br>
            <h1 align="center" style="color: white">个人中心</h1>
            <div style="margin-top:-10px;margin-right: 140px; float: right;width:300px;">
                <a href="index.jsp" class="easyui-linkbutton" iconCls="icon-undo">返回主页</a>&nbsp;&nbsp;
                <a href="javascript:location.reload();" class="easyui-linkbutton" iconCls="icon-reload">刷新页面</a>&nbsp;&nbsp;
            </div>
        </div>
        </br></br>
        <div align="center">
            <table border="1" height="400px" width="800px" style="color: black" bordercolor="white">
                <tr>
                    <th width="400">账号</th>
                    <th width="500">${student.id}</th>
                </tr>
                <tr>
                    <th>姓名</th>
                    <th>${student.studentName}</th>
                </tr>
                <tr>
                    <th>姓别</th>
                    <th>${student.sex}</th>
                </tr>
                <tr>
                    <th>入学年份</th>
                    <th>${student.entranceYear}</th>
                </tr>
                <tr>
                    <th>班级</th>
                    <th>${student.classNumber}</th>
                </tr>
            </table>
        </div>
    </div>
</div>
</body>
</html>

