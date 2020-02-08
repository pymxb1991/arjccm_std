<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>值班日志表管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnSubmit").on("click" ,function(){
				$("#searchForm").submit();
			})
			$("#btnExport").click(
				function() {
					top.$.jBox.confirm("确认要导出数据吗？", "系统提示", function(v, h, f) {
						if (v == "ok") {
							// 借用导出action
							$("#searchForm").attr("action", "${ctx}/work/ccmWorkBeondutylog/exportWorkBeondutylog");
							$("#searchForm").submit();
							// 还原查询action
							$("#searchForm").attr("action", "${ctx}/work/ccmWorkBeondutylog");
						}
					}, {
						buttonsFocus : 1
					});
					top.$('.jbox-body .jbox-icon').css('top', '55px');
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
<%--<img  src="${ctxStatic}/images/shouyedaohang.png"; class="nav-home">--%>
<%--<span class="nav-position">当前位置 ：</span><span class="nav-menu"><%=session.getAttribute("activeMenuName")%>></span><span class="nav-menu2">值班管理</span>--%>
<div class="back-list">
	<ul class="nav nav-tabs">
		<li class="active" style="width: 140px"><a class="nav-head" href="${ctx}/work/ccmWorkBeondutylog/">数据列表</a></li>
		<shiro:hasPermission name="work:ccmWorkBeondutylog:edit"><li><a style="width: 140px;text-align:center" href="${ctx}/work/ccmWorkBeondutylog/form">数据添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="ccmWorkBeondutylog" action="${ctx}/work/ccmWorkBeondutylog/" method="post" class="breadcrumb form-search clearfix">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form pull-left">
			<li class="first-line"><label>开始日期：</label>
				<input name="beginDatas" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${ccmWorkBeondutylog.beginDatas}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> </li>
			<li class="first-line"><label>结束日期：</label>	<input name="endDatas" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${ccmWorkBeondutylog.endDatas}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li class="first-line"><label>记录类型：</label>
				<form:select path="type" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('ccm_work_beondutylog_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="first-line"><label>值班地点：</label>
				<form:input path="adds" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li class="first-line"><label>内容：</label>
				<form:input path="details" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li class="second-line"><label>登记部门：</label>
				<sys:treeselect id="office" name="office.id" value="${ccmWorkBeondutylog.office.id}" labelName="office.name" labelValue="${ccmWorkBeondutylog.office.name}"
								title="部门"   url="/sys/office/treeData?type=2" cssClass="input-small" allowClear="true" notAllowSelectParent="false"/>
			</li>
			<!-- <li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li> -->

<%--			<li class="clearfix"></li>--%>
		</ul>
	<div class="clearfix pull-right btn-box">
		<a href="javascript:;" id="btnExport" style="width: 49px;
    /*margin-top: 25px;*/display:inline-block;float: right;" class="btn btn-export">
			<span style="font-size: 12px">导出</span>
		</a>
		<a href="javascript:;" id="btnSubmit" style="width: 49px;
    /*margin-top: 25px;*/display:inline-block;float: right;" class="btn btn-primary">
				<%--<i class="icon-search"></i> --%><span style="font-size: 12px">查询</span> </a>

	</div>
	</form:form>
	<sys:message content="${message}"/>

	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>时间</th>
				<th>人员</th>
				<th>登记部门</th>
				<th>记录类型</th>
				<th>值班地点</th>
				<shiro:hasPermission name="work:ccmWorkBeondutylog:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="ccmWorkBeondutylog">
			<tr>
				<td style="height: 50px"><a href="${ctx}/work/ccmWorkBeondutylog/form?id=${ccmWorkBeondutylog.id}">
					<fmt:formatDate value="${ccmWorkBeondutylog.datas}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</a></td>
				<td style="height: 50px">
					${ccmWorkBeondutylog.updateBy.name}
				</td>
				<td style="height: 50px">
					${ccmWorkBeondutylog.office.name}
				</td>
				<td style="height: 50px">
					${fns:getDictLabel(ccmWorkBeondutylog.type, 'ccm_work_beondutylog_type', '')}
				</td>
				<td style="height: 50px">
					${ccmWorkBeondutylog.adds}
				</td>
				<shiro:hasPermission name="work:ccmWorkBeondutylog:edit"><td style="height: 50px">
    				<a class="btnList"  href="${ctx}/work/ccmWorkBeondutylog/form?id=${ccmWorkBeondutylog.id}" title="修改"><i class="icon-pencil"></i></a>
					<a class="btnList"  href="${ctx}/work/ccmWorkBeondutylog/delete?id=${ccmWorkBeondutylog.id}" onclick="return confirmx('确认要删除该值班日志表吗？', this.href)" title="删除"><i class="icon-remove-sign"></i></a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination"style="float: right; margin-top: 12px">${page}</div>
</body>
</html>