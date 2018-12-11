<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@page import="org.apache.commons.lang.ArrayUtils"%>
<%@page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="com.lczx.util.Setting"%>
<%@page import="com.lczx.util.SettingUtils"%>
<%@page import="com.lczx.util.SpringUtils"%>
<%@page import="com.lczx.util.Setting.AccountLockType"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page isELIgnored="false" %>

<%
String base = request.getContextPath();
ApplicationContext applicationContext = SpringUtils.getApplicationContext();
Setting setting = SettingUtils.get();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
  <head>
    <meta charset="utf-8">

<%
if (applicationContext != null) {
	
%>

    <title><%=SpringUtils.getMessage("admin.login.title")%></title>
    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="${setting.siteName}">
    <meta name="author" content="${setting.siteName}">

    <link rel="stylesheet" type="text/css" href="<%=base%>/resources/admin2/lib/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="<%=base%>/resources/admin2/stylesheets/theme.css">
    <link rel="stylesheet" href="<%=base%>/resources/admin2/lib/font-awesome/css/font-awesome.css">
    <script src="<%=base%>/resources/admin2/lib/jquery-1.7.2.min.js" type="text/javascript"></script>

<link rel="stylesheet" type="text/css" href="${base}/resources/dist/layuiadmin/layui/css/layui.css" media="all"> 
<link rel="stylesheet" type="text/css" href="${base}/resources/dist/layuiadmin/style/admin.css" media="all"> 
<link rel="stylesheet" type="text/css" href="${base}/resources/dist/layuiadmin/style/login.css" media="all"> 

<link rel="stylesheet" type="text/css" href="${base}/resources/src/layuiadmin/layui/css/layui.css" media="all"> 
<link rel="stylesheet" type="text/css" href="${base}/resources/src/layuiadmin/style/admin.css" media="all"> 
<link rel="stylesheet" type="text/css" href="${base}/resources/src/layuiadmin/style/login.css" media="all"> 


<link href="<%=base%>/resources/admin2/custom/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=base%>/resources/admin2/custom/common.js"></script>
<script type="text/javascript">

</script>
<script type="text/javascript">
	$().ready( function() {
		
		var $loginForm = $("#loginForm");
		var $username = $("#username");
		var $password = $("#password");
		var $isRememberUsername = $("#isRememberUsername");
		
		// 记住用户名
		if(getCookie("adminUsername") != null) {
			$isRememberUsername.prop("checked", true);
			$username.val(getCookie("adminUsername"));
			$password.focus();
		} else {
			$isRememberUsername.prop("checked", false);
			$username.focus();
		}
		
		// 表单验证、记住用户名
		$loginForm.submit( function() {
			if ($username.val() == "") {
				$.message("warn", "<%=SpringUtils.getMessage("admin.login.usernameRequired")%>");
				return false;
			}
			if ($password.val() == "") {
				$.message("warn", "<%=SpringUtils.getMessage("admin.login.passwordRequired")%>");
				return false;
			}
			if ($isRememberUsername.prop("checked")) {
				addCookie("adminUsername", $username.val(), {expires: 7 * 24 * 60 * 60});
			} else {
				removeCookie("adminUsername");
			}
			
		});
		
		var message = "${message}";
		if( message != ""){
			$.message("error", message);
		}
		
		$("#imageChange").click(function(){
			 var img=document.getElementById("image");
			    //切换验证码的原理是点击就重新将src设置一下，但是浏览器有缓存，所以我们需要在后面添加                     一个参数来让浏览器不断发送请求，后面加的参数为时间，因为时间是不断变化的
			    img.src="/admin/common/verificode.jhtml?"+Math.random();
			
		});
			
		
	});
	
	 
</script>

<%} else {%>

<title>提示信息 ${setting.siteName}</title>
<meta http-equiv="expires" content="0" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Cache-Control" content="no-cache" />
<meta name="author" content="${setting.siteName}" />
<meta name="copyright" content="${setting.siteName}" />
<link rel="stylesheet" type="text/css" href="<%=base%>/resources/admin2/lib/bootstrap/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="<%=base%>/resources/admin2/stylesheets/theme.css">
<link rel="stylesheet" href="<%=base%>/resources/admin2/lib/font-awesome/css/font-awesome.css">
<script src="<%=base%>/resources/admin2/lib/jquery-1.7.2.min.js" type="text/javascript"></script>

<%}%>
    <!-- Demo page code -->

    <style type="text/css">
        #line-chart {
            height:300px;
            width:800px;
            margin: 0px auto;
            margin-top: 1em;
        }
        .brand { font-family: georgia, serif; }
        .brand .first {
            color: #ccc;
            font-style: italic;
        }
        .brand .second {
            color: #fff;
            font-weight: bold;
        }
    </style>

    

    <!-- Le fav and touch icons -->
    <link rel="shortcut icon" href="../assets/ico/favicon.ico">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="<%=base%>/resources/admin2/lib/font-awesome/docs/assets/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="<%=base%>/resources/admin2/lib/font-awesome/docs/assets/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="<%=base%>/resources/admin2/lib/font-awesome/docs/assets/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="<%=base%>/resources/admin2/lib/font-awesome/docs/assets/ico/apple-touch-icon-57-precomposed.png">
  </head>

  
  <body class=""> 
<div class="layadmin-user-login layadmin-user-display-show" id="LAY-user-login" style="display: none;">

    <div class="layadmin-user-login-main">
      <div class="layadmin-user-login-box layadmin-user-login-header">
        <h2>小孙专车运营管理平台</h2>
      </div>
 <div
					class="layadmin-user-login-box layadmin-user-login-body layui-form">   

<%if (applicationContext != null) {%>   
        <form id="loginForm" action="/admin/login.jhtml" method="post">
				
					<div class="layui-form-item">
						<label
							class="layadmin-user-login-icon layui-icon layui-icon-username"
							for="LAY-user-login-username"></label> <input type="text"
							name="username" id="username" lay-verify="required"
							placeholder="用户名" class="layui-input"  style="height: 40px">
					</div>
					<div class="layui-form-item">
						<label
							class="layadmin-user-login-icon layui-icon layui-icon-password"
							for="LAY-user-login-password"></label> <input type="password"
							name="password" id="password" lay-verify="required"
							placeholder="密码" class="layui-input"   style="height: 40px">
					</div>
					<div class="layui-form-item">
						<div class="layui-row">
							<div class="layui-col-xs7">
								<label
									class="layadmin-user-login-icon layui-icon layui-icon-vercode"
									for="LAY-user-login-vercode"></label> <input type="text"
									name="vcode" id="vcode" lay-verify="required"
									placeholder="图形验证码" class="layui-input"  style="height: 40px">
							</div>
							<div class="layui-col-xs5">
								<div style="margin-left: 10px;">
									<img src="/admin/common/verificode.jhtml"
										class="layadmin-user-login-codeimg" id="image"> <a
										href="#" id="imageChange">看不清，换一张</a>
								</div>
							</div>
						</div>
					</div>
					<div class="layui-form-item">

						<button class="layui-btn layui-btn-fluid" lay-submit
							lay-filter="LAY-user-login-submit">登 入</button>
					</div>

			</form>
				</div>                
</div>
    
 <!--<div class="layui-trans layadmin-user-login-footer">
      
      <p>© 2018 <a href="http://www.layui.com/" target="_blank">layui.com</a></p>
      <p>
        <span><a href="http://www.layui.com/admin/#get" target="_blank">获取授权</a></span>
        <span><a href="http://www.layui.com/admin/pro/" target="_blank">在线演示</a></span>
        <span><a href="http://www.layui.com/admin/" target="_blank">前往官网</a></span>
      </p>
    </div>
 -->                
   </div>               
               
<%} else {%>
		
	<%}%>
    
    <script src="<%=base%>/resources/admin2/lib/bootstrap/js/bootstrap.js"></script>
    <script type="text/javascript">
        $("[rel=tooltip]").tooltip();
        $(function() {
            $('.demo-cancel-click').click(function(){return false;});
        });
    </script>
    
  </body>
</html>


