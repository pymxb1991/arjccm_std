<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/include/head.jsp"%>
<html>
<head>
	<title>越界报警查询</title>
	<meta name="decorator" content="default"/>
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="expires" content="0">
	<style type="text/css">
		.accordion-heading{
			background-image: none
		}
		#left{
			background:none
		}
		.table th, .table td {
			vertical-align:middle;
 			height:auto;
		}
		.table tr {
			background-size: 100% 100%!important;
			background-repeat: no-repeat !important;
		}
	</style>
	<link rel="stylesheet" href="${ctxStatic}/layer-v3.1.1/layer/mobile/need/layer.css" type="text/css">
	<script>
	var ctx = "${ctx}";
    </script>
	<script src="${ctxStatic}/layer-v3.1.1/layer/layer.js"></script>
	<script src="${ctxStatic}/common/common.js" type="text/javascript"></script>
	<!-- 地图资源引入 -->
	<link rel="stylesheet" href="${ctxStatic}/modules/map/css/map.css" type="text/css">
	<link rel="stylesheet" href="${ctxStatic}/ol/ol.css" type="text/css">
	<script src="${ctxStatic}/ol/ol.js"></script>
	<script src="${ctxStatic}/modules/map/js/draw/js/p-ol3.debug.js"></script>
	<script src="${ctxStatic}/modules/map/js/commonMap.js"></script>
	<script src="${ctxStatic}/modules/map/js/mapconfig.js"></script>
	<!-- 地图资源引入 -->
	<link rel="stylesheet" href="${ctxStatic}/DataTables/css/jquery.dataTables.css" type="text/css">
	<script src="${ctxStatic}/DataTables/js/jquery.dataTables.js"></script>
	<script src="${ctxStatic}/sps/data/js/spsWarningIndex.js"></script>
	<script type="text/javascript">
	$(document).ready(function() {
		var data = [];
		<c:forEach items="${page.list}" var="ccmEarlyWarning">
			var option = "<a onclick='HisPlayBack(this,\"${ccmEarlyWarning.idCard}\")'>回放</a>";
			data.push(["${ccmEarlyWarning.name}","${ccmEarlyWarning.idCard}",option]);
		</c:forEach>
		$('#TableConList').html('<table cellpadding="0" cellspacing="0" border="0" class="display table table-striped table-bordered table-condensed" id="contentTableList"></table>');
		$('#contentTableList').dataTable({
			 language: {
			        "sProcessing": "处理中...",
			        "sLengthMenu": "显示 _MENU_ 项结果",
			        "sZeroRecords": "没有匹配结果",
			        "sInfo": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
			        "sInfoEmpty": "显示第 0 至 0 项结果，共 0 项",
			        "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
			        "sInfoPostFix": "",
			        "sSearch": "搜索:",
			        "sUrl": "",
			        "sEmptyTable": "表中数据为空",
			        "sLoadingRecords": "载入中...",
			        "sInfoThousands": ",",
			        "oPaginate": {
			            "sFirst": "首页",
			            "sPrevious": "上页",
			            "sNext": "下页",
			            "sLast": "末页"
			        },
			        "oAria": {
			            "sSortAscending": ": 以升序排列此列",
			            "sSortDescending": ": 以降序排列此列"
			        }
			    },
	        "data": data,	          
	        "searching" : false,
	        "bLengthChange": false,
	        "ordering":  false,// 排序
	        "paging": false, // 禁止分页
	        "scrollY": '550px',
	            
	        "scrollCollapse": true,
	        "columns": [
	            { "title": "姓名" },
	            { "title": "身份证号" },
	            { "title": "操作"}
	        ]
	    });
	    $("#contentTableList_info").hide();
	});
			
	function page(n, s) {
		$("#pageNo").val(n);
		$("#pageSize").val(s);
		var beginDate = $("#beginDate").val();//开始时间
		var endDate = $("#endDate").val();//结束时间
		var beginDateNew = Date.parse(new Date(beginDate.replace(/-/g,"/")));
		var endDateNew = Date.parse(new Date(endDate.replace(/-/g,"/")));
		var time = endDateNew - beginDateNew;
		if(beginDate == null || beginDate == "" || beginDate === undefined || endDate == null || endDate == "" || endDate === undefined){
			$.jBox.tip("请选择查询时间段");
			return;
		}
		if(time < 0){
			$.jBox.tip("开始时间必须小于结束时间","警告");
			return;
		}
		if(time > 24*60*60*1000*10){
			$.jBox.tip("时间差必须小于"+10+"天");
			return;
		}
		$("#searchForm").submit();
		return false;
	}
</script>
</head>
<body>
<div id="content" class="row-fluid" style="height: 97%;">
	<div id="left" class="accordion-group" style="height:50%;">
		<form:form id="searchForm" modelAttribute="ccmEarlyWarning" action="${ctx}/warning/ccmEarlyWarning/spswaringList" method="post" class="breadcrumb form-search">
			<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
			<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
			<ul class="ul-form">
				<li><label>时间：</label>
				<input id="beginDate" style="width:146px;" name="beginDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate" value="<fmt:formatDate value="${ccmEarlyWarning.beginDate}" pattern="yyyy-MM-dd HH:mm:ss"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
				&nbsp; - &nbsp;<input id="endDate" style="width:146px;" name="endDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate" value="<fmt:formatDate value="${ccmEarlyWarning.endDate}" pattern="yyyy-MM-dd HH:mm:ss"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/></li>
				<li><label>姓名：</label>
					<form:input path="name" htmlEscape="false" maxlength="64" class="input-medium"/>
				</li>
				<li><label>身份证：</label>
					<form:input path="idCard" htmlEscape="false" maxlength="64" class="input-medium"/>
				</li>
				<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="button" value="查询" onclick="page();"/><li/>
				<li class="clearfix"></li>
			</ul>
		</form:form>
		<sys:message content="${message}"/>
		<div id="TableConList" style="width: 100%;"></div>
		<div class="pagination">${page}</div>
	</div>
	<div id="openClose" class="close">&nbsp;</div>
	<div id="right">
		<div id="map" class="map"></div>
		<!-- 展开按钮 -->
		<div style="position: absolute;bottom: 6px;right: 168px;transition: bottom 1s;" id="showDiv">
			<input onclick="ShowDiv()" style="background-color: #19a7f0 !important;" class="btn btn-primary" type="button" value="展开"/>
		</div>
		<!-- 底部grid列表数据 -->
		<div id="hideOrShowDiv" style="position: absolute;bottom: 6px;right: 3px;height: 0px;transition: height 1s;">
			<!-- 收起按钮 -->
			<div style="position: absolute;right: 0px;bottom: -30px;transition: bottom 1s;" id="hideDiv">
				<input onclick="HideDiv()" style="background-color: #3b6e8e !important;border: 0px;" class="btn btn-primary" type="button" value="收起"/>
			</div>
			<!-- 倍速回放 -->
			<div style="position: absolute;left: 3px;bottom: -30px;transition: bottom 1s;" id="playBack">
					<input  id="speed" name="" class="input-medium" type="number" min="1" max="1000" value="100" style="width: 50px; margin: 0;margin-right: 5px;">
					<input  id="start" style="background-color: #3b6e8e !important;border: 0px;" class="btn btn-primary" type="button" value="开始"/>
					<input  id="stop" style="background-color: #3b6e8e !important;border: 0px;" class="btn btn-primary" type="button" value="停止"/>
				    <input  id="tempstop" style="background-color: #3b6e8e !important;border: 0px;" class="btn btn-primary" type="button" value="暂停"/>
				    <input  id="next" style="background-color: #3b6e8e !important;border: 0px;" class="btn btn-primary" type="button" value="前进"/>
				    <input  id="prev" style="background-color: #3b6e8e !important;border: 0px;" class="btn btn-primary" type="button" value="后退"/>
			</div>
			<!-- grid列表 -->
		 	<div id="TableCon" style="width: 100%;"></div>
		</div>
	</div>
	<script type="text/javascript">
		var leftWidth = 585; // 左侧窗口大小
		var htmlObj = $("html"), mainObj = $("#main");
		var frameObj = $("#left, #openClose, #right, #right iframe");
		function wSize(){
			var strs = getWindowSize().toString().split(",");
			htmlObj.css({"overflow-x":"hidden", "overflow-y":"hidden"});
			mainObj.css("width","auto");
			frameObj.height(strs[0] - 0);
			var leftWidth = ($("#left").width() < 0 ? 0 : $("#left").width());
			$("#right").width($("#content").width()- leftWidth - $("#openClose").width() -2);
			$("#hideOrShowDiv").width($("#content").width()- leftWidth - $("#openClose").width() -2);
			//防止地图拉伸
			if(Map.map){
				var size=[$('#map').width(),$('#map').height()]
				Map.map.setSize(size);
			}
		}
	</script>
	<script src="${ctxStatic}/common/wsize.min.js" type="text/javascript"></script>
</div>
</body>
</html>