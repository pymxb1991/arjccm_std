<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html style="height: 100%">
<head>
<title>统计信息</title>
<meta name="decorator" content="default" />
<link href="${ctxStatic}/ccm/pop/css/ccmPepInfo.css" rel="stylesheet" />
<script type="text/javascript"
	src="${ctxStatic}/echarts/echarts.common.min.js"></script>
<script type="text/javascript" src="${ctxStatic}/echarts/walden.js"></script>
<script type="text/javascript"
	src="${ctxStatic}/echarts/echarsCommonPbs.js"></script>
<script type="text/javascript" src="${ctxStatic}/pbs/report/js/reportEchartsDepartmentPeriod.js"></script>
<style type="text/css">

table tbody {
    display:block;
    height:240px;
    overflow-y:scroll;
}
table thead, tbody tr {
    display:table;
    width:100%;
    table-layout:fixed;
}

table thead {
    width: calc( 100% - 1em )
}
table thead th{ background:#ccc;}
table tbody::-webkit-scrollbar {
    display: none; // 隐藏滚动条
}
</style>
</head>
<body  style="height: 100%;overflow: hidden;">
	<div class="context" content="${ctx}"></div>
	<div class="row-fluid">
	<ul class="ul-form breadcrumb form-search">
		<li><label>开始时间：</label> <input id="beginTime" type="text"
				readonly="readonly" maxlength="20" class="input-medium Wdate"
				value="<fmt:formatDate value="${pbsTaskrec.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});" />
		</li>
		<li><label>结束时间：</label> <input id="endTime" type="text"
				readonly="readonly" maxlength="20" class="input-medium Wdate"
				value="<fmt:formatDate value="${pbsTaskrec.sDeadline}" pattern="yyyy-MM-dd HH:mm:ss"/>"
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});" />
		</li>		
		<li class="btns" style="padding-left: 260px;">
			<input class="btn btn-primary" type="button" value="查询" id="query"/>
		</li>
		<li class="clearfix"></li>
	</ul>
		<div id="ech" class="span9" style="margin-bottom: 0px;"></div>
	</div>
	<div class="row-fluid">
		<div id="echList" class="span3" style="padding-left: 200px;height: 200px;">
			<h4 style=" padding-bottom: 10px;">党支部学习统计</h4>
			<div >
				<table id="departmentPeriodTable"  class="table table-striped">
					<thead>
						<tr>
							<td>人员姓名</td>
							<td>学时</td>
						</tr>
					</thead>
					<tbody id="departmentPeriod">
						
					</tbody>
				</table>
			</div>
		</div>
		<div style="padding-left: 800px;width:260px;height:200px;">
			<h4 style=" padding-bottom: 10px;">人员学习统计</h4>
			<div>
				<table class="table table-striped">
					<thead>
						<tr>
							<td>所在部门</td>
							<td>姓名</td>
							<td>学时</td>
						</tr>
					</thead>
					<tbody id="personnelPeriod"></tbody>
				</table>
			</div>
		</div>
	</div>
	<script type="text/javascript">
	var context = $(".context").attr("content");
	 function getPersonnelPeriod(_this){
		  var id = $(_this).attr('id')
		  var beginTime = $('#beginTime').val();
		  var endTime = $('#endTime').val();
		  $.getJSON(context + "/report/getPersonnelPeriod", {
			     beginTime:beginTime,
			     endTime:endTime,
			     oId:id
		    }, function(data) {
		    	  var personnelPeriodData = data.list;
				  var personnelPeriodHtml = '';
				  if(personnelPeriodData.length>0){
					  for(var i = 0;i<personnelPeriodData.length;i++){
						  personnelPeriodHtml += '<tr><td>'+personnelPeriodData[i].oName+'</td><td>'+personnelPeriodData[i].u8Name+'</td><td>'+personnelPeriodData[i].itime+'</td></tr>';
					  }
				  }
					$('#personnelPeriod').html(personnelPeriodHtml);
		    })
	  }
	</script>
</body>
</html>