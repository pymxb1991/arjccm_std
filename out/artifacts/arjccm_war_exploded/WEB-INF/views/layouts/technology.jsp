<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<!DOCTYPE html>
<html style="overflow-x:hidden;overflow-y:auto;">
<head>
	<title><sitemesh:title/> - Powered By JeeSite</title>
	<%@include file="/WEB-INF/views/include/head.jsp" %>		
	<!-- Baidu tongji analytics -->
<!-- 	<script>var _hmt=_hmt||[];(function(){var hm=document.createElement("script");hm.src="//hm.baidu.com/hm.js?82116c626a8d504a5c0675073362ef6f";var s=document.getElementsByTagName("script")[0];s.parentNode.insertBefore(hm,s);})();</script>
 -->
	<style>
		::-webkit-scrollbar {
			width: 5px;
		}

		::-webkit-scrollbar {
			height: 5px;
		}

		::-webkit-scrollbar-track {
			background-color: #bee1eb;
		}

		::-webkit-scrollbar-thumb {
			background-color: #00aff0;
		}

		::-webkit-scrollbar-thumb:hover {
			background-color: #9c3;
		}

		::-webkit-scrollbar-thumb:active {
			background-color: #00aff0;
		}
	</style>
 	<sitemesh:head/>
</head>
<body style="background:transparent;color:white;">
	<sitemesh:body/>
	<script type="text/javascript">//<!-- 无框架时，左上角显示菜单图标按钮。
		if(!(self.frameElement && self.frameElement.tagName=="IFRAME")){
			$("body").prepend("<i id=\"btnMenu\" class=\"icon-th-list\" style=\"cursor:pointer;float:right;margin:10px;\"></i><div id=\"menuContent\"></div>");
			$("#btnMenu").click(function(){
				top.$.jBox('get:${ctx}/sys/menu/treeselect;JSESSIONID=<shiro:principal property="sessionid"/>', {title:'选择菜单', buttons:{'关闭':true}, width:300, height: 350, top:10});
				//if ($("#menuContent").html()==""){$.get("${ctx}/sys/menu/treeselect", function(data){$("#menuContent").html(data);});}else{$("#menuContent").toggle(100);}
			});
		}//-->
	</script>
</body>
</html>