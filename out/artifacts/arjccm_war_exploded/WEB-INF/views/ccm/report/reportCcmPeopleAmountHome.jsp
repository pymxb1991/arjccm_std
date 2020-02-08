<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>门户人口统计</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(function() {

	});
</script>
<style type="text/css">
.alarm-body {
	margin-top: 12px;
}

.alarm-body table {
	margin-top: 0px;
	margin-bottom: 0px;
}

.alarm-body .table  td {
	padding: 8px;
	border: 0px;
	height: 29px;
}

.alarm-body .table  .firsttr td {
	padding-bottom: 0px;
}

.ti {
	font-size: 15px;
	color: #08c !important;
	font-weight: 800;
}

.tin {
	font-size: 20px;
	color: red;
}

.bon {
	font-size: 15px;
	color: red;
}
</style>
</head>
<div class="alarm-body">

	<table id="" class="table">
		<tbody>
			<tr class="firsttr">
				<td class="titletd" rowspan="2" width="150px"
					style="text-align: center;"><span class="tin">2585</span><br>
					<span class="ti">实有人口</span></td>
				<td>人户一致：<span class="bon">1953</span></td>
				<td>人户分离：<span class="bon">542</span></td>
				<td>未落户(常住)人口：<span class="bon">90</span></td>
			</tr>
			<tr>

				<td>户籍人口：<span class="bon">2095</span></td>
				<td>流动人口：<span class="bon">442</span></td>
				<td>境外人口：<span class="bon">48</span></td>
			</tr>
		</tbody>
	</table>
	<table id="" class="table">
		<tbody>
			<tr class="firsttr">
				<td class="titletd" rowspan="3" width="150px"
					style="text-align: center;"><span class="tin">32</span><br>
					<span class="ti">治安重点人员</span></td>
				<td>严重刑事犯罪活动嫌疑：<span class="bon">5</span></td>
				<td>吸食毒品人员：<span class="bon">9</span></td>
				<td>肇事肇祸精神病人：<span class="bon">2</span></td>
				
			</tr>
			<tr class="firsttr">
			    <td>非正常上访人员：<span class="bon">4</span></td>
				<td>危害国家安全活动嫌疑：<span class="bon">6</span></td>
				
				<td>闹事行凶报复嫌疑：<span class="bon">2</span></td>
			</tr>
			<tr>
			<td>故意违法刑释不足5年：<span class="bon">5</span></td>
			</tr>
		</tbody>
	</table>
	<table id="" class="table">
		<tbody>
			<tr class="firsttr">
				<td class="titletd" rowspan="2" width="150px"
					style="text-align: center;"><span class="tin">861</span><br>
					<span class="ti">帮扶关注人员</span></td>
				<td>留守人员：<span class="bon">43</span></td>
				<td>重点青少年：<span class="bon">321</span></td>
				<td>老年人：<span class="bon">414</span></td>
			</tr>
			<tr>
				<td>特殊关怀人员：<span class="bon">83</span></td>
				<td></td>
				<td></td>
			</tr>
		</tbody>
	</table>
	<table id="" class="table">
		<tbody>
			<tr class="firsttr">
				<td class="titletd" rowspan="2" width="150px"
					style="text-align: center;"><span class="tin">382<br></span><span
					class="ti">实有单位</span></td>
				<td>一般单位：<span class="bon">223</span></td>
				<td>娱乐服务场所：<span class="bon">94</span></td>
				<td>特种行业：<span class="bon">11</span></td>
			</tr>
			<tr>
				<td>办保单位：<span class="bon">22</span></td>
				<td>流动摊位：<span class="bon">32</span></td>
				<td>从业人员：<span class="bon">2087</span></td>
			</tr>
		</tbody>
	</table>
	<table id="" class="table">
		<tbody>
			<tr>
				<td class="titletd" style="text-align: center;"><span
					class="ti">实有房屋：</span><span class="tin">2537</span></td>
				<td>楼栋：<span class="bon">12</span></td>
				<td class="titletd">单元：<span class="bon">38</span></td>
				<td style="text-align: center;"><span class="ti">出租房：</span><span
					class="tin">121</span></td>
			</tr>
		</tbody>
	</table>
	<table id="" class="table">
		<tbody>
			<tr>
				<td class="titletd" style="text-align: center;"><span
					class="ti">昨日案件：</span><span class="tin">4</span></td>
				<td>行政案件：<span class="bon">3</span></td>
				<td class="titletd">刑事案件：<span class="bon">1</span></td>
				<td style="text-align: center;"><span class="ti">昨日警情：</span><span
					class="tin">5</span></td>
			</tr>
		</tbody>
	</table>
</div>
</html>