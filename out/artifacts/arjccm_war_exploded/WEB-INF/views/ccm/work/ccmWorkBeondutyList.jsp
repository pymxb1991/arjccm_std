<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>值班表管理</title>
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
								$("#searchForm").attr("action", "${ctx}/work/ccmWorkBeonduty/exportWorkBeonduty");
								$("#searchForm").submit();
								// 还原查询action
								$("#searchForm").attr("action", "${ctx}/work/ccmWorkBeonduty");
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
		<li class="active" style="width: 140px"><a class="nav-head" href="${ctx}/work/ccmWorkBeonduty/">数据列表</a></li>
		<shiro:hasPermission name="work:ccmWorkBeonduty:edit"><li><a style="width: 140px;text-align:center" href="${ctx}/work/ccmWorkBeonduty/form?isinsert=1">数据添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="ccmWorkBeonduty" action="${ctx}/work/ccmWorkBeonduty/" method="post" class="breadcrumb form-search clearfix">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form pull-left">
			<li class="first-line"><label>归属部门：</label>
				<sys:treeselect id="office" name="office" value="${ccmWorkBeonduty.office.id}" labelName="office.name" labelValue="${ccmWorkBeonduty.office.name}"
					title="部门"   url="/sys/office/treeData?type=2" cssClass="input-medium" allowClear="true" notAllowSelectParent="true"/>
			</li>
			<li class="first-line"><label>值班负责人：</label>
				<sys:treeselect id="principal" name="principal" value="${ccmWorkBeonduty.principal.id}" labelName="principal.name" labelValue="${ccmWorkBeonduty.principal.name}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="input-medium" allowClear="true" notAllowSelectParent="true"/>
			</li>
			<li class="first-line"><label>值班队伍：</label>
				<form:input path="principalMans" htmlEscape="false" maxlength="1000" class="input-medium"/>
			</li>
			<li class="first-line"><label>值班地点：</label>
				<form:input path="adds" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<%--<li class="clearfix"></li>--%>
			<li class="first-line"><label>开始年月：</label>
				<input name="beginMonths" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${ccmWorkBeonduty.beginMonths}" pattern="yyyy-MM"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM',isShowClear:false});"/> </li>
			<li class="second-line"><label>结束年月：</label>	<input name="endMonths" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${ccmWorkBeonduty.endMonths}" pattern="yyyy-MM"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM',isShowClear:false});"/>
			</li>
			<li class="second-line"><label>开始时间段：</label>
			<input name="beginDatas" id="beginDatas" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${ccmWorkBeonduty.beginDatas}" pattern="HH:mm"/>"
				   onclick="WdatePicker({dateFmt:'HH:mm',isShowClear:false});"/> </li>
			 <li class="second-line"><label>结束时间段：</label>
				 <input name="endDatas" id="endDatas" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${ccmWorkBeonduty.endDatas}" pattern="HH:mm"/>"
					onclick="WdatePicker({dateFmt:'HH:mm',isShowClear:false});"/>
				<%-- <form:input path="datas" htmlEscape="false" maxlength="100" class="input-medium"/> --%>
			</li>
			<!-- <li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li> -->

<%--			<li class="btns">--%>
<%--				<a href="javascript:;" id="btnExport" class="btn btn-export">--%>
<%--					<i class=" icon-reply"></i> 导出Excel--%>
<%--				</a>--%>
<%--			</li>--%>
<%--			<li class="clearfix"></li>--%>
		</ul>
	<div class="clearfix pull-right btn-box">
            <a style="width: 49px;margin-right: 14px;
    margin-bottom: 20px;/*margin-top: 25px;*/display:inline-block;float: right;margin-left: 20px" class="btn btn-export" onclick="parent.LayerDialog1('','${ctx}/work/ccmWorkBeonduty/ccmWorkBeondutyPage', '值班批量添加', '500px', '300px')">
                批量添加</a>
			<a href="javascript:;" id="btnSubmit" style="width: 49px;margin-right: 14px;
    margin-bottom: 20px;/*margin-top: 25px;*/display:inline-block;float: right;margin-left: 20px" class="btn btn-primary">
					<%--<i class="icon-search"></i> --%><span style="font-size: 12px">查询</span> </a>
	</div>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed table-gradient">
		<thead>
			<tr>
				<th>年月</th>
				<th>时间段</th>
				<th>归属部门</th>
				<th>值班负责人</th>
				<th>值班队伍</th>
				<th>值班地点</th>
				<shiro:hasPermission name="work:ccmWorkBeonduty:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="ccmWorkBeonduty">
			<tr>
				<td style="height: 50px"><a href="${ctx}/work/ccmWorkBeonduty/form?id=${ccmWorkBeonduty.id}">
					<fmt:formatDate value="${ccmWorkBeonduty.months}" pattern="yyyy-MM"/>
				</a></td>
				<td style="height: 50px">
					${ccmWorkBeonduty.datas}
				</td>
				<td style="height: 50px">
					${ccmWorkBeonduty.office.name}
				</td>
				<td style="height: 50px">
					${ccmWorkBeonduty.principal.name}
				</td>
				<td style="height: 50px">
					${ccmWorkBeonduty.principalMans}
				</td>
				<td style="height: 50px">
					${ccmWorkBeonduty.adds}
				</td>
				<shiro:hasPermission name="work:ccmWorkBeonduty:edit"><td style="height: 50px">
    				<a class="btnList"  href="${ctx}/work/ccmWorkBeonduty/form?id=${ccmWorkBeonduty.id}" title="修改"><i class="icon-pencil"></i></a>
					<a class="btnList"  href="${ctx}/work/ccmWorkBeonduty/delete?id=${ccmWorkBeonduty.id}" onclick="return confirmx('确认要删除该值班表吗？', this.href)" title="删除"><i class="icon-remove-sign"></i></a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination" style="float: right; margin-top: 12px">${page}</div>
</body>
</html>