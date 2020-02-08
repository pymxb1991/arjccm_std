<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>考试试卷详细信息</title>
<meta name="decorator" content="default" />
<script type="text/javascript"
	src="${ctxStatic}/echarts/echarts.common.min.js"></script>
<script type="text/javascript" src="${ctxStatic}/echarts/walden.js"></script>
<script type="text/javascript"
	src="${ctxStatic}/echarts/echarsCommonPbs.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	// 初始化 Echarts组件
	var myChart = echarts.init(document.getElementById('ech1'));
	// 标题
	var title = [ "学员成绩占比" ];
	// 初始化页面方法
	showEchart();
	// 展示 页面的方法
	function showEchart() {
		// GetJson 获取数据
		$.getJSON(ctx+ "/exam/pbsExampaper/paperDetailStrut", {
			id:"${pbsExampaper.id}"
	    }, function(data) {
	      var ajaxData = $.getxAxisDate(data, title);
	      $.myChartSiglePie2(myChart, title, ajaxData, data);
	    });
	 }
}); 

</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/exam/pbsExampaper/?sWay=0">在线试卷列表</a></li>
		<li class="active"><a href="">在线考试信息</a></li>
	</ul>
	<div class="breadcrumb form-search">
		<ul class="ul-form" style="font-size:16px; text-align:center">
			<li style="margin-left:5%;margin-right:5%;"><label style="font-size:16px;width:100%">试卷最高成绩：${maxScore}</label></li>
			<li style="margin-right:5%;"><label style="font-size:16px;width:100%">试卷最低成绩：${minScore}</label></li>
			<li style="margin-right:5%;"><label style="font-size:16px;width:100%">试卷平均成绩：${averageScore}</label></li>
		</ul>
	</div>
	<table id="contentTable" class="table table-striped table-bordered table-condensed" style="width:50%;float:left;margin-left:30px;margin-top:15px">
		<thead>
			<tr>
				<th>学员头像</th>
				<th>学员名称</th>
				<th>考试标题</th>
				<th>试卷名称</th>
				<th>考试分数</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${pbsExamactionList}" var="pbsExamaction">
				<tr>
					<td style="width:60px;text-align:center">
						<img onerror="this.src='${ctxStatic}/images/headPC.png'" src="${pbsPartymem.getSPhoto()}" style="width:40px;height:40px"/>
					</td>
					<td>${pbsExamaction.sExaminee.SName}</td>
					<td>${pbsExamaction.sExampaper.STitle}</td>
					<td>${pbsExamaction.sExampaper.SName}</td>
					<td>${pbsExamaction.IReport}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="row-fluid" style="width:45%;margin-left:30px; float:left;margin-top:5%">
		<div id="ech1" class="span9" style="width:100%;height:500px;"></div>
	</div>
</body>
</html>