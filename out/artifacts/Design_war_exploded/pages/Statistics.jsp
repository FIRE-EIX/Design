<%--
  Created by IntelliJ IDEA.
  User: EIX
  Date: 2020/12/26
  Time: 20:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>统计</title>
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
                    <th width="400">最高分</th>
                    <th width="500">${array[0]}</th>
                <tr>
                    <th>最低分</th>
                    <th>${array[1]}</th>
                </tr>
                <tr>
                    <th>平均分</th>
                    <th>${array[2]}</th>
                </tr>
                <tr>
                    <th>及格率</th>
                    <th>${array[3]}%</th>
                </tr>
            </table>
        </div>
    </div>
</div>
</body>
</html>
