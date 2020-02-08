<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/include/head.jsp"%>
<html>
<head>
	<title>历史轨迹查询</title>
	<meta name="decorator" content="default"/>
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="expires" content="0">
	<%@include file="/WEB-INF/views/include/treeview.jsp" %>
	<style type="text/css">
		.ztree {overflow:auto;margin:0;_margin-top:10px;padding:10px 0 0 10px;}
		#left{
			background:none
		}
		.accordion-heading{
			background-image: none
		}
		.table th, .table td {
			vertical-align:middle;
 			height:auto;
		}
	</style>
	<script src="${ctxStatic}/common/common.js" type="text/javascript"></script>
	<!-- 地图资源引入 -->
	<link rel="stylesheet" href="${ctxStatic}/modules/map/css/map.css" type="text/css">
	<link rel="stylesheet" href="${ctxStatic}/ol/ol.css" type="text/css">
	<script src="${ctxStatic}/ol/ol.js"></script>
	<script src="${ctxStatic}/modules/map/js/commonMap.js"></script>
	<script src="${ctxStatic}/modules/map/js/mapconfig.js"></script>
	<script src="${ctxStatic}/sps/data/js/spsTrajectoryPointIndex.js"></script>
	<!-- 地图资源引入 -->
	<script src="${ctxStatic}/sps/data/js/ztreeSech.js"></script>
	<link rel="stylesheet" href="${ctxStatic}/DataTables/css/jquery.dataTables.css" type="text/css">
	<script src="${ctxStatic}/DataTables/js/jquery.dataTables.js"></script>
</head>
<body>
	<sys:message content="${message}"/>
	<div id="content" class="row-fluid">
		<div id="left" class="accordion-group">
			<div class="accordion-heading">
		    	<a class="accordion-toggle">移动设备<i class="icon-refresh pull-right" onclick="refreshTree();"></i></a>
		    </div>
		        <div class="input-append" style="margin-top: 10px">
				<input id="secuDev" name="secuPlace" class="input-medium"
					type="text" value="" maxlength="100"
					style="width: 136px; height: 28px;  padding: 0 5px; margin-left: 1px;">
				<a id="areaButton" href="javascript:" class="btn"
					style="border-radius: 0 14px 14px 0;">&nbsp;<i
					class="icon-search"></i>&nbsp;
				</a>&nbsp;&nbsp;
			</div>
			<div id="ztree" class="ztree"></div>
		</div>
		<div id="openClose" class="close">&nbsp;</div>
		<div id="right">
			<div>
				<form:form id="searchForm" modelAttribute="spsTrajectoryPoint" action="${ctx}/patrol/ccmTracingpoint/listQuery/" method="post" class="breadcrumb form-search">
					<ul class="ul-form">
						<li><label>时间：</label>
							<input id="beginCurDate" name="beginCurDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
								value="<fmt:formatDate value="${spsTrajectoryPoint.beginCurDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> - 
							<input id="endCurDate" name="endCurDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
								value="<fmt:formatDate value="${spsTrajectoryPoint.endCurDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
						</li>
						<li class="btns">
							<!-- <input id="btnSubmit" class="btn btn-primary" type="button" value="查询" onclick="queryHistoryPoint()"/> -->
							<a href="javascript:;" id="btnSubmit" class="btn btn-primary" onclick="queryHistoryPoint()">
                <i class="icon-search"></i> 查询 </a>
							<shiro:hasPermission name="data:spsTrajectoryPoint:edit">
								<!-- <input id="btnExport" class="btn btn-primary" type="button" value="导出"/> -->
					<a href="javascript:;" id="btnExport" class="btn btn-export"> 
						<i class=" icon-reply"></i> 导出
					</a>
							</shiro:hasPermission>
						</li>
						<li class="clearfix"></li>
					</ul>
				</form:form>
			</div>
			<div id="map" class="map"></div>
			<!-- 展开按钮 -->
			<div style="position: absolute;bottom: 10px;right: 3px;transition: bottom 1s;" id="showDiv">
				<input onclick="ShowDiv()" style="background-color: #19a7f0 !important;" class="btn btn-primary" type="button" value="展开"/>
			</div>
			<!-- 底部grid列表数据 -->
			<div id="hideOrShowDiv" style="position: absolute;bottom: 10px;right: 3px;height: 0px;transition: height 1s;">
				<!-- 收起按钮 -->
				<div style="position: absolute;right: 0px;bottom: -30px;transition: bottom 1s;" id="hideDiv">
					<input onclick="HideDiv()" style="background-color: #3b6e8e !important;border: 0px;" class="btn btn-primary" type="button" value="收起"/>
				</div>
				<!-- 倍速回放 -->
				<div style="position: absolute;left: 3px;bottom: -30px;transition: bottom 1s;" id="playBack">
					<input id="speed" name="" class="input-medium" type="number" min="1" max="999" value="1" style="width: 50px; margin: 0;margin-right: 5px;"><input onclick="playBack()" id="start-animation" style="background-color: #3b6e8e !important;border: 0px;" class="btn btn-primary" type="button" value="回放"/>
				</div>
				<!-- grid列表 -->
			 	<div id="TableCon" style="width: 100%;"></div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		var deviceId = "";
		var deviceCode = "";
		var setting = {data:{simpleData:{enable:true,idKey:"id",pIdKey:"pId",rootPId:'0'}},
			callback:{onClick:function(event, treeId, treeNode){
				var id = treeNode.id;
				var code = treeNode.code;
				var typeClass = treeNode.typeClass;
				if(typeClass == "device"){
					deviceId = id;
					deviceCode = code;
				}else{
					deviceId = "";
					deviceCode = "";
				}
			}}
		};
		function refreshTree(){
			$.getJSON("${ctx}/patrol/ccmTracingpoint/treeData",function(data){
				$.fn.zTree.init($("#ztree"), setting, data).expandAll(true);
			});
		}
		refreshTree();
		var leftWidth = 300; // 左侧窗口大小
		var htmlObj = $("html"), mainObj = $("#main");
		var frameObj = $("#left, #openClose, #right, #right iframe");
		function wSize(){
			var strs = getWindowSize().toString().split(",");
			htmlObj.css({"overflow-x":"hidden", "overflow-y":"hidden"});
			mainObj.css("width","auto");
			frameObj.height(strs[0] - 5);
			var leftWidth = ($("#left").width() < 0 ? 0 : $("#left").width());
			$("#right").width($("#content").width()- leftWidth - $("#openClose").width() -5);
			$("#hideOrShowDiv").width($("#content").width()- leftWidth - $("#openClose").width() -5);
			$(".ztree").width(leftWidth - 10).height(frameObj.height() - 101);
		}
	</script>
	<script src="${ctxStatic}/common/wsize.min.js" type="text/javascript"></script>
	<script type="text/javascript">
		init();
	    // 资源树搜索
	    $('#areaButton').click(function(){
	    	filter('ztree','secuDev')
	    })
		//给时间控件赋默认值
		$("#beginCurDate").val(today()+"00:00:00");
		$("#endCurDate").val(today()+"23:59:59");
		var len = null;
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
	    	return false;
	    }
		function ShowDiv(){
			if(len == null || len === undefined || len == "" || len == 0){
				$.jBox.alert("没有数据","提示");
				return;
			}
			$("#hideOrShowDiv").height(200);
			$("#showDiv").css({'bottom':-30});
			$("#hideDiv").css({'bottom':200});
			$("#playBack").css({'bottom':200});
		}
		function HideDiv(){
			$("#hideOrShowDiv").height(0);
			$("#hideDiv").css({'bottom':-30});
			$("#playBack").css({'bottom':-30});
			$("#showDiv").css({'bottom':10});
		}
		function queryHistoryPoint(){
			if(deviceId == null || deviceId == "" || deviceId === undefined){
				$.jBox.alert("请选择设备","警告");
				return;
			}
			var beginCurDate = $("#beginCurDate").val();
			var endCurDate = $("#endCurDate").val();
			if(beginCurDate == null || beginCurDate == "" || beginCurDate === undefined || endCurDate == null || endCurDate == "" || endCurDate === undefined){
				$.jBox.alert("请选择查询时间段","警告");
				return;
			}
			var beginCurDateNew = Date.parse(new Date(beginCurDate.replace(/-/g,"/")));
			var endCurDateNew = Date.parse(new Date(endCurDate.replace(/-/g,"/")));
			var time = endCurDateNew - beginCurDateNew;
			if(time > 24*60*60*1000){
				$.jBox.alert("时间差必须小于一天","警告");
				return;
			}
			$.ajax({
		        type: "POST",
		        url: ctx + "/patrol/ccmTracingpoint/listQuery/",
		        data: {
		        	deviceId:deviceId,
		        	beginCurDate:beginCurDate,
		        	endCurDate:endCurDate
		        },
		        dataType: "json",
		        cache: false,
		        async: true,
		        success: function (data) {
		        	TableInit(data);
		        }, error: function (e,data) {
		        	console.error("e",e);
		        }
		    });
		}
		var routeCoords=[];
		function TableInit(data){
			var Data=data.result;
        	len=Data.length;
        	var tableData=[];
        	Map.removeLayer('trackReplay');//轨迹回放
        	if(len==0){
        		HideDiv();
        		$.jBox.alert("未查询到数据","提示");
        		dataTableInit(tableData)
        		return;
        	}
        	if(len>0){
        		var html='';
        		for(var i=0;i<len;i++){
        			var x = Data[i].areaPoint.split(',')[0];
        			var y = Data[i].areaPoint.split(',')[1];
        			tableData.push([Data[i].deviceId, x, y, (new Date(Data[i].curDate.time)).Format("yyyy-MM-dd hh:mm:ss")]);//第一列就是序号 之后依次是:x,y,h,s,timeOccurs
        			routeCoords.push([Number(x),Number(y)]);
        		}
        		dataTableInit(tableData)
        		Map.trackReplaInit('start-animation','speed',routeCoords);//轨迹回放
        		ShowDiv();
        	}
		}
		function dataTableInit(tableData){
			$('#TableCon').html('<table cellpadding="0" cellspacing="0" border="0" class="display table table-striped table-bordered table-condensed" id="contentTable"></table>');
    	    $('#contentTable').dataTable({
    	        "data": tableData,
    	        "searching" : false,
    	        "bLengthChange": false,
    	        "ordering":  false,// 排序
    	        "paging": false, // 禁止分页
    	        "scrollY": '156px',
    	        "scrollCollapse": true,
    	        "columns": [
    	            { "title": "设备"},
    	            { "title": "经度" },
    	            { "title": "纬度" },
    	            { "title": "时间"}
    	        ]
    	    });
		}
		//回放
		function  playBack(){
			var val = $('#speed').val()
			Map.startAnimation()
		}
		
	</script>
</body>
</html>