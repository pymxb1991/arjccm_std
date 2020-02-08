<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/include/head.jsp"%>
<html>
<head>
<meta charset="UTF-8">
<title>地图</title>
<script type="text/javascript">
	var ctx = '${ctx}', ctxStatic = '${ctxStatic}';
</script>

</head>
<body>
	<a href="${ctx}/a/index"> 返回地图显示</a>
	<div id="layerMessage">暂无相关信息上报</div>
	<div id="layerTest">
	 <a
			href="${ctx}/sys/map/layMap?id=0b09b7efaf184d81866da03ea121f8de&areaPoint=117.632800,39.037190&caseName=案事件按键键">测试</a>
	</div>
</body>
</html>