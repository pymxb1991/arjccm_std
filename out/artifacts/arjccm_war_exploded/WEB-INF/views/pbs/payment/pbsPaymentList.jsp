<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>缴费信息</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$('#btnExport').click(function(){
				top.$.jBox.confirm("确认要到处党费数据吗？","系统提示",function(v,h,f){
					if(v == 'ok'){
						$('#searchForm').attr("action","${ctx}/payment/pbsPayment/export");
						$('#searchForm').submit();
					}
				},{buttonsFocus:1});
				top.$('.jbox-body .jbox-icon').css('top','55px');
			});
			$('#btnImport').click(function(){
				$.jBox($("#importBox").html(), {title:"导入数据", buttons:{"关闭":true}, 
					bottomText:"导入文件不能超过5M，仅允许导入“xls”或“xlsx”格式文件！"});
			});
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<div id="importBox" class="hide">
		<form id="importForm" action="${ctx}/payment/pbsPayment/import" method="post" enctype="multipart/form-data"
			class="form-search" style="padding-left:20px;text-align:center;" onsubmit="loading('正在导入，请稍等...');"><br/>
			<input id="uploadFile" name="file" type="file" style="width:330px"/><br/><br/>　　
			<input id="btnImportSubmit" class="btn btn-primary" type="submit" value="   导    入   "/>
			<a href="${ctx}/payment/pbsPayment/import/template">下载模板</a>
		</form>
	</div>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/payment/pbsPayment/">缴费信息</a></li>
		<shiro:hasPermission name="payment:pbsPayment:edit"><li><a href="${ctx}/payment/pbsPayment/form">缴费信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="pbsPayment" action="${ctx}/payment/pbsPayment/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li>
				<label>学员：</label>
				<input id="pbspartymem.sName" name="pbspartymem.sName" class="input-medium" type="text" value="${pbsPayment.pbspartymem.SName}">
			</li>
			<li><label>缴费类别：</label> <form:select path="sType"
				class="input-medium">
				<form:option value="" label="全部" />
				<form:options items="${fns:getDictList('pay_type')}"
					itemLabel="label" itemValue="value" htmlEscape="false" />
			</form:select></li>
			<li><label>缴费日期：</label> <input name="startPayTime" type="text"
				readonly="readonly" maxlength="20" class="input-medium Wdate"
				value="<fmt:formatDate value="${pbsPayment.startPayTime}" pattern="yyyy-MM-dd"/>"
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});" />
				- <input name="endPayTime" type="text" readonly="readonly"
				maxlength="20" class="input-medium Wdate"
				value="<fmt:formatDate value="${pbsPayment.endPayTime}" pattern="yyyy-MM-dd"/>"
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});" />
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input id="btnExport" class="btn btn-primary" type="button" value="导出"/></li>
			<li class="btns"><input id="btnImport" class="btn btn-primary" type="button" value="导入"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th style="text-align:center">学员头像</th>
				<th>学员</th>
				<th>缴费金额</th>
				<th>缴费类型</th>
				<th>缴费日期</th>
				<th>备注信息</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="pbsPayment">
			<tr>
				<td style="width:60px;text-align:center">
					<img onerror="this.src='${ctxStatic}/images/headPC.png'" src="${pbsPayment.pbspartymem.SPhoto}" style="width:40px;height:40px"/>
				</td>
				<td><a href="${ctx}/payment/pbsPayment/form?id=${pbsPayment.id}">${pbsPayment.pbspartymem.SName}</a></td>
				<td>${pbsPayment.money}</td>
				<td>${fns:getDictLabel(pbsPayment.SType, 'pay_type', '')}</td>
				<td><fmt:formatDate value="${pbsPayment.paymentTime}" pattern="yyyy-MM-dd"/></td>
				<td class="tp">${pbsPayment.remarks}</td>
				<shiro:hasPermission name="payment:pbsPayment:edit"><td>
    				<a href="${ctx}/payment/pbsPayment/form?id=${pbsPayment.id}">修改</a>
					<a href="${ctx}/payment/pbsPayment/delete?id=${pbsPayment.id}" onclick="return confirmx('确认要删除该学员缴费信息管理吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>