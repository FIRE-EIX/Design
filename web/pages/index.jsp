<%--
  Created by IntelliJ IDEA.
  User: EIX
  Date: 2020/12/20
  Time: 20:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>首页</title>
    <link rel="stylesheet" type="text/css" href="../easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="../easyui/themes/icon.css">
    <script type="text/javascript" src="../easyui/jquery.min.js"></script>
    <script type="text/javascript" src="../easyui/jquery.easyui.min.js"></script>

    <style type="text/css">
        .weather {
            border: 1px solid #95B8E7;
            background-color: #E0ECFF;
            height: 88px;
            padding-top: 10px;
            padding-left: 20%;
        }
    </style>

    <script type="text/javascript">
        //参数一：字符串类型的事件，多个事件之间通过空格分隔，事件名称符合jquery事件名写法
        //参数二：处理函数
        $("#btn-add").bind("click dblclick", function () {
            //将该按失效
            $("#btn-add").linkbutton("disable");
        });
    </script>
</head>
<body class="easyui-layout">
<!--上面 logo start -->
<div data-options="region:'north'" style="height:80px;width: 100%;" border=false>
    <!--<div data-options="region:'north',split:true" style="height:100px;" border=false>-->
    <img src="../img/topLogo.png" style="height:74px;width: 400px;"/>

    <div style="padding-top:40px;padding-right: 20px;float: right;width:500px">
        欢迎您
        <c:if test="${!('ST'.equals(id.substring(0,2))||'st'.equals(id.substring(0,2)))}">
            :${name}
        </c:if>
        <c:if test="${'ST'.equals(id.substring(0,2))||'st'.equals(id.substring(0,2))}">
            :<a href="../myInfoServlet" style='text-decoration:none;'>${name}</a>
        </c:if>
        <a href="../userLogout" class="easyui-linkbutton" iconCls="icon-clear">退出登录</a>
        <a href="javascript:location.reload();" class="easyui-linkbutton" iconCls="icon-reload">刷新页面</a>
        <a id="name_add_but30" href="#" class="easyui-linkbutton" iconCls="icon-edit">修改密码</a>
        <a id="printinfo" href="#" class="easyui-linkbutton" iconCls="icon-print">打印信息</a>
    </div>
</div>
<!--上面 logo end -->

<!--底部 版权信息栏-->
<div data-options="region:'south',split:true" style="height:70px;text-align: center;font-size: 15px;color: gray;">
    地址：广西壮族自治区南宁市西乡塘区大学东路188号 咨询电话：18648869609
    <br/>
    Copyright 2000-2099 广西民族大学
    <br/>
    谢延炎 版权所有
    <c:set var="salary" scope="session" value="${2000*2}"/>
</div>
<!--<div data-options="region:'east',iconCls:'icon-reload',title:'East',split:true" style="width:100px;"></div>  -->

<!--左边 功能导航栏 start-->
<div data-options="region:'west',title:'功能导航栏',split:true" border=false style="width:200px;">
    <div id="aa" class="easyui-accordion" fit=true animate=true>
        <c:if test="${'ST'.equals(id.substring(0,2))||'st'.equals(id.substring(0,2))}">
            <div title="成绩管理" data-options="iconCls:'icon-tip'" style="padding:10px;">
                <a href="#" class="easyui-linkbutton" plain=true iconCls="icon-edit"
                   onclick="openTabs('我的成绩','../listScoreServlet')">我的成绩</a>
            </div>
        </c:if>
        <c:if test="${'TE'.equals(id.substring(0,2))||'te'.equals(id.substring(0,2))}">
            <div title="数据查看" data-options="iconCls:'icon-tip'">
                <a href="#" class="easyui-linkbutton" plain=true iconCls="icon-edit"
                   onclick="openTabs('成绩查看','../teacherListStudentServlet')">成绩查看</a> <br>
                    <%--<a href="#" class="easyui-linkbutton" plain=true iconCls="icon-edit"
                       onclick="addFile61()">数据统计</a>--%>
            </div>
        </c:if>
        <c:if test="${'AD'.equals(id.substring(0,2))||'ad'.equals(id.substring(0,2))}">
            <%--        管理员的功能--%>
            <div title="人员管理" data-options="iconCls:'icon-tip'" style="padding:10px;">
                <a href="#" class="easyui-linkbutton" plain=true iconCls="icon-edit"
                   onclick="openTabs('教师列表','../listTeacherServlet')">教师列表</a><br>
                <a href="#" class="easyui-linkbutton" plain=true iconCls="icon-edit"
                   onclick="openTabs('学生列表','../listStudentServlet')">学生列表</a><br>
                <a href="#" class="easyui-linkbutton" plain=true iconCls="icon-edit"
                   onclick="openTabs('课程列表','../listCourseInfoServlet')">课程列表</a><br>
                <a href="#" class="easyui-linkbutton" plain=true iconCls="icon-edit"
                   onclick="openTabs('班级列表','../listClassInfoServlet')">班级列表</a><br>
            </div>
            <div title="成绩审核" data-options="iconCls:'icon-tip'" style="padding:10px;">
                <a href="#" class="easyui-linkbutton" plain=true iconCls="icon-edit"
                   onclick="openTabs('数据维护','../adminListStudentServlet')">成绩审核</a> <br>
                <%--<a href="#" class="easyui-linkbutton" plain=true iconCls="icon-edit"
                   onclick="openTabs('信息录入','../enterInformation')">信息录入0</a> <br>--%>
                    <%--<a href="#" class="easyui-linkbutton" plain=true iconCls="icon-edit"
                       onclick="openTabs('成绩审核','SalaryView.jsp')">成绩审核</a><br>--%>
            </div>
        </c:if>
        <%--        学生的功能
        <div>
            <a href="#" class="easyui-linkbutton" plain=true iconCls="icon-edit"
               onclick="openTabs('课程成绩','../myInfoServlet')">课程成绩</a><br>
        </div>--%>
    </div>
</div>
<%--</c:if>--%>
<!--左边 菜单导航栏 end-->


<div data-options="region:'center'" style="padding:0px;" border=false>

    <div id="tt" class="easyui-tabs" fit=true>
        <div title="欢迎页" style="padding:20px;">
            <div class="easyui-layout" style="width:70%;height:100%;float: left;">
                <div id="content" region="center" title="公告栏" style="padding:5px;">
                    <!--添加公告按钮-->
                    <a id="btn0" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'"
                       style="float: right">添加公告</a>
                </div>
            </div>
            <div style="height: 100%;width: 28%;float: left;margin-left: 20px;">
                <div class="weather">
                    <iframe width="100%" scrolling="no" height="100%" frameborder="0" allowtransparency="true"
                            src="http://i.tianqi.com/index.php?c=code&id=12&icon=1&num=1">
                    </iframe>
                </div>
                <div id="cal" class="easyui-calendar" style="width:100%;height:333px;margin-top: 10px;"></div>
            </div>

        </div>
    </div>
</div>
<div id="win30" title="修改密码" style="width: 400px; padding: 10px 20px; height: 410px;" closed="true">
    <form action="http://localhost:8080/Design/editPassword" method="post" style="margin-top: 20px; margin-left: 20px;">
        <div class="fitem">
            <input name="input0" id="opw" placeholder="输入旧密码" class="password" type="password"
                   style="width: 350px;height: 40px;" onkeyup="checkoldpassword()"><span id="tishi1"></span></input><br><br>
            <input name="input1" id="pw" placeholder="输入新密码" class="password" type="password"
                   style="width: 350px;height: 40px;" required/><br><br>
            <input name="input2" id="repw" placeholder="请确认密码" class="password" type="password"
                   style="width: 350px;height: 40px;" onkeyup="checkpassword()"><span id="tishi"></span></input><br><br>
        </div>
        <input id="submit1" name="submit" type="submit" class="easyui-linkbutton c6"
               style="width: 90px;height: 40px;float: right; margin-right: 28px;"/>
    </form>
</div>
<div id="win61" class="easyui-dialog" title="统计" style="width: 400px; padding: 10px 20px; height: 410px;"
     closed="true">
    <form action="../statisticsServlet" method="post" style="margin-top: 20px; margin-left: 20px;"
          onsubmit=openTabs("统计","../statisticsServlet")>
        <div class="fitem">
            <input name="cno61" id="cno61" placeholder="课程号" class="textbox" type="text"
                   style="width: 350px;height: 40px;" required/><br><br>
        </div>
        <input id="submit61" name="submit" type="submit" class="easyui-linkbutton c6"
               style="width: 90px;height: 40px;float: right; margin-right: 28px;"/>
    </form>
</div>
</body>
<script type="text/javascript">
    $('#name_add_but30').linkbutton({
        onClick: function () {
            addFile30();
        }
    });
    $('#printinfo').linkbutton({
        onClick: function () {
            alert("请连接打印机！");
        }
    });
    $('#view').linkbutton({
        onClick: function () {
            alert("该功能正在开发中，敬请期待！");
        }
    });
    $('#btn0').linkbutton({
        onClick: function () {
            alert("该功能正在开发中，敬请期待！");
        }
    });

    /**
     * 打开选项卡
     *         判断选项卡是否存在   exists 表明指定的面板是否存在，'which'参数可以是选项卡面板的标题或索引。
     *         不存在 新建选项卡
     *         如果存在 选中当前选项卡 select 选择一个选项卡面板，'which'参数可以是选项卡面板的标题或者索引。
     * @param {Object} title
     */
    function openTabs(title, url) {
        console.log(title);
        // 判断选项卡是否存在
        var exists = $("#tt").tabs("exists", title);
        if (exists) { //如果返回true，说明选项卡存在，选中当前选项卡
            $("#tt").tabs("select", title);
        } else { //如果返回false，说明选项卡不存在，新建选项卡
            $('#tt').tabs('add', {
                title: title,
                selected: true,
                closable: true,
                iconCls: 'icon-edit',
                href: url
            });
        }
    }

    function addFile30() {
        $('#win30').dialog({
            title: '修改密码',
            width: 450,
            height: 350,
            top: 150,
            closed: false,//显示对话框
            cache: false,
            modal: true
        });
    }

    function addFile61() {
        $('#win61').dialog({
            title: '统计',
            width: 450,
            height: 200,
            top: hi,
            closed: false,//显示对话框
            cache: false,
            modal: true
        });
    }

    function checkoldpassword() {
        var oldpassword = "${password}"
        var opassword = document.getElementById("opw").value;

        if (oldpassword === opassword) {
            document.getElementById("tishi1").innerHTML = "<br><font color='green'>旧密码正确</font>";
            document.getElementById("submit1").disabled = false;
        } else {
            document.getElementById("tishi1").innerHTML = "<br><font color='red'>旧密码错误</font>";
            document.getElementById("submit1").disabled = true;
        }
    }

    function checkpassword() {
        var password = document.getElementById("pw").value;
        var repassword = document.getElementById("repw").value;

        if (password === repassword) {
            document.getElementById("tishi").innerHTML = "<br><font color='green'>两次密码输入一致</font>";
            document.getElementById("submit1").disabled = false;

        } else {
            document.getElementById("tishi").innerHTML = "<br><font color='red'>两次输入密码不一致!</font>";
            document.getElementById("submit1").disabled = true;
        }
    }
</script>
</html>