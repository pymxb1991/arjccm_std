<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>绩效主观KPI打分</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
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
<%--<span class="nav-position">当前位置 ：</span><span class="nav-menu"><%=session.getAttribute("activeMenuName")%>></span><span class="nav-menu2">绩效考核</span>--%>
<div class="back-list">
	<ul class="nav nav-tabs">
		<li class="active" style="width: 140px"><a class="nav-head" href="${ctx}/score/kpiSchemeScoreSubjective/">数据列表</a></li>
	</ul>
	
	<div style="font-size:12px;margin:0 0 4px 0;padding:0;height:24px;background-image:url(images/nav_bg.gif);vertical-align:bottom;">
		<span>
			<B>
				<font color="#DB5151">&nbsp;&nbsp;&nbsp;&nbsp;【提示】：1、请公正打分，不能恶意评分！&nbsp;&nbsp;&nbsp;&nbsp;
					2、评分时不能填写小数，只能填写整数！&nbsp;&nbsp;&nbsp;&nbsp;
					3、给某人评分时，必须给所有单项评分！&nbsp;&nbsp;&nbsp;&nbsp;
					4、评分项为百分制！
				</font>
			</B>
		</span>
	</div>

	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed table-gradient">
		<thead>
			<tr>
				<th>方案名称</th>
				<th>方案状态</th>
				<th>kpi名称</th>
				<th>kpi描述</th>
				<shiro:hasPermission name="score:kpiSchemeScoreSubjective:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="kpiSchemeKpi">
			<tr>
				<td style="height: 50px">
					${kpiSchemeKpi.schemeName}
				</td>
				<c:if test="${kpiSchemeKpi.schemeState eq '1'}">
					<td style='color:#D69601;height: 50px'>${fns:getDictLabel(kpiSchemeKpi.schemeState, 'kpi_scheme_state', '')}</td>
				</c:if>
				<c:if test="${kpiSchemeKpi.schemeState eq '2'}">
					<td style='color:red;height: 50px'>${fns:getDictLabel(kpiSchemeKpi.schemeState, 'kpi_scheme_state', '')}</td>
				</c:if>
				<c:if test="${kpiSchemeKpi.schemeState eq '' or empty kpiSchemeKpi.schemeState or kpiSchemeKpi.schemeState eq '0' }">
					<td style="height: 50px">${fns:getDictLabel(kpiSchemeKpi.schemeState, 'kpi_scheme_state', '')}</td>
				</c:if>
				<td style="height: 50px">
					${kpiSchemeKpi.name}
				</td>
				<td style="height: 50px">
					${kpiSchemeKpi.remarks}
				</td>
				
				<shiro:hasPermission name="score:kpiSchemeScoreSubjective:edit"><td style="height: 50px">
					<c:if test="${kpiSchemeKpi.schemeState != '' and not empty kpiSchemeKpi.schemeState and kpiSchemeKpi.schemeState != '0' }">
						<a class="btnList" href="${ctx}/score/kpiSchemeScoreSubjective/form?id=${kpiSchemeKpi.id}" title="数据录入"><i class="icon-pencil"></i></a>
					</c:if>
					<c:if test="${kpiSchemeKpi.schemeState eq '' or empty kpiSchemeKpi.schemeState or kpiSchemeKpi.schemeState eq '0' }">
						未发布
					</c:if>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination" style="float: right; margin-top: 12px">${page}</div>
</body>
</html>