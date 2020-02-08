<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>${fns:getConfig('productName')} 登录</title>
	<meta name="decorator" content="blank"/>
	<link type="text/css" rel="stylesheet" href="${ctxStatic}/layim/layui/css/layui.css" />
	<script type="text/javascript" src="${ctxStatic}/layim/layui/layui.js"></script>
	<style type="text/css">
		*{
			margin: 0px;
			padding: 0px;
		}
		html, body {
			width: 100%;
			height: 100%;
			background-repeat: no-repeat;
			background-position: center;
			background-size: 100% 100%;
			overflow: hidden !important;
		}
		
		.form-signin-heading {
			font-family:Microsoft YaHei;
			font-size: 40px;
			color: #1d4696;
			width: 67%;
			font-weight:bold;
			/*text-align: center;*/
			position: absolute;
			line-height:12px;
			letter-spacing:2px;
			top: 37px;
			right: -30px;
		}
		
		.form-signin {
			position: relative;
			left: 65%;
			width: 350px;
			height: 350px;
			background: url('${ctxStatic}/images/login_form.png') no-repeat center;
			background-size: 100% 100%;
			overflow: hidden;
			border-radius: 5px;
			top: 25%;
		}
		
		.mid {
			color: #fff;
			font-size: 28px;
			line-height: 23px;
			margin-left: 60px;
			font-weight: 600;
			vertical-align: middle;
		}
		
		.header {
			height: 80px;
			padding-top: 20px;
		}
		
		.alert {
			position: relative;
			z-index: 999;
			width: 300px;
			margin: 0 auto;
			*padding-bottom: 0px;
		}
		
		label.error {
			background: none;
			width: 270px;
			font-weight: normal;
			color: inherit;
			margin: 0;
		}
		input[type="text"]{
			border:1px solid rgba(238,238,238,1)!important;
		}
		input[type="password"]{
			border:1px solid rgba(238,238,238,1)!important;
		}
		.top {
			position: absolute;
			top: 0px;
			width: 100%;
			background-image: linear-gradient(rgba(255,255,255,0.9), rgba(255, 255, 255, 0));
		}

		.form-top {
			color: #1b8ef4;
			font-weight: normal;
			position: relative;
			top: 20px;
			left: 33%;
		}
		
		.input-block-level {
			font-size: 18px !important;
			border: none !important;
			width: 280px;
			height: 50px !important;
			background-color: transparent !important;
			padding: 0px !important;
			margin-left: 35px;
			padding-left: 60px !important;
		}

		.username {
			<%--background: url('${ctxStatic}/images/login_form_user.png') no-repeat center;--%>
			margin-bottom: 20px !important;
			margin-top: 50px;

		}

		.password {
			/*background: #ffffff !important;*/
			<%--background: url('${ctxStatic}/images/login_form_password.png') no-repeat center;--%>
		}
		
		input::-webkit-input-placeholder {
			/* placeholder颜色  color: #e5e5e5;*/
			/* placeholder字体大小  */font-size: 14px;
			/* placeholder位置  text-align: center;*/
		}

		.form-signin .btn.btn-large {
			font-family: 微软雅黑;

			background:#205cd4;
			font-size: 16px;
			border: none;
			margin-left: 35px;
			margin-top: 30px;
			width: 280px !important;
		}
		
		.bodyBg {
			position: absolute;
			z-index: -999;
			width: 100%;
			height: 100%;
		}
		
		.bodyBg img {
			width: 100%;
			height: 100%;
		}
		.img-bj{
			position: absolute;
			top: 90px;
			right: 268px;
		}
		.img-bj2{
			position: absolute;
			top: 161px;
			right: 268px;
		}
    </style>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#loginForm").validate({
				rules: {
					validateCode: {remote: "${pageContext.request.contextPath}/servlet/validateCodeServlet"}
				},
				messages: {
					username: {required: "请填写用户名."},password: {required: "请填写密码."},
					validateCode: {remote: "验证码不正确.", required: "请填写验证码."}
				},
				errorLabelContainer: "#messageBox",
				errorPlacement: function(error, element) {
					error.appendTo($("#loginError").parent());
				} 
			});
			//背景图切换
			layui.use(['carousel'], function(){
				var carousel = layui.carousel;
				carousel.render({
					elem: '#bgDiv',
					interval: 5000,
					anim: 'fade',
					height:'100%',
					width:'100%',
					indicator:'none'
				});
			});
		});
		// 如果在框架或在对话框中，则弹出提示并跳转到首页
		if(self.frameElement && self.frameElement.tagName == "IFRAME" || $('#left').length > 0 || $('.jbox').length > 0){
			alert('未登录或登录超时。请重新登录，谢谢！');
			top.location = "${ctx}";
		}
	</script>
</head>
<body>
	<!--[if lte IE 6]><br/><div class='alert alert-block' style="text-align:left;padding-bottom:10px;"><a class="close" data-dismiss="alert">x</a><h4>温馨提示：</h4><p>你使用的浏览器版本过低。为了获得更好的浏览体验，我们强烈建议您 <a href="http://browsehappy.com" target="_blank">升级</a> 到最新版本的IE浏览器，或者使用较新版本的 Chrome、Firefox、Safari 等。</p></div><![endif]-->
	<div class="bodyBg">
		<div class="layui-carousel" id="bgDiv">
			<div carousel-item="">
				<div><img src="${ctxStatic}/images/login_bg/login_bg1.png"></div>
				<div><img src="${ctxStatic}/images/login_bg/login_bg2.png"></div>
				<div><img src="${ctxStatic}/images/login_bg/login_bg3.png"></div>
				<div><img src="${ctxStatic}/images/login_bg/login_bg4.png"></div>
				<div><img src="${ctxStatic}/images/login_bg/login_bg5.png"></div>
				<div><img src="${ctxStatic}/images/login_bg/login_bg6.png"></div>
				<%--<div><img src="${ctxStatic}/images/login_bg/login_bg7.png"></div>--%>
			</div>
		</div>
	</div>
	<div class="header">
		<div id="messageBox" class="alert alert-error ${empty message ? 'hide' : ''}"><button data-dismiss="alert" class="close">×</button>
			<label id="loginError" class="error">${message}</label>
		</div>
	</div>
	<div class="top">
		<div  style="width: 60px;height: 80px ;margin-left: 30.5%;margin-top: 0.6%;">
			<img src="${ctxStatic}/images/logo1.png">
			<h1 class="form-signin-heading">${fns:getConfig('showName')}</h1>
		</div>

	</div>
	<form id="loginForm" class="form-signin" action="${ctx}/login" method="post">
		<h1 class="form-top">账号登录</h1>
		<div>
			<input type="text" style="color:#000;background: #ffffff !important;" name="username" class="username input-block-level required" value="${username}" placeholder="请输入用户名">
			<img class="img-bj" src="${ctxStatic}/images/login_form_user.png">
		</div>
		<div>
			<input type="password"  style="color:#000;background: #ffffff !important;" name="password" class="password input-block-level required" placeholder="请输入密码">
			<img class="img-bj2" src="${ctxStatic}/images/login_form_password.png">
		</div>
		<c:if test="${isValidateCodeLogin}">
			<div class="validateCode">
				<label class="mid" for="validateCode">验证码：</label>
				<sys:validateCode name="validateCode" inputCssStyle="margin-bottom:0;" />
			</div>
		</c:if>
		<input class="btn btn-large btn-primary" type="submit" value="登&nbsp;&nbsp;&nbsp;&nbsp;录" />
	</form>
	<div class="footer" style="display: none">
		Copyright &copy; 2015-${fns:getConfig('copyrightYear')} <a href="${pageContext.request.contextPath}${fns:getFrontPath()}">${fns:getConfig('productName')}</a> - Powered By <a href="http://arjjs.com" target="_blank">ARJJS</a> ${fns:getConfig('version')} 
	</div>
	<%-- <script src="${ctxStatic}/flash/zoom.min.js" type="text/javascript"></script> --%>
</body>
</html>