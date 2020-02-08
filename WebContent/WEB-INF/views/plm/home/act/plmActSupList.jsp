<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
	<table id="contentTable" class="table table-striped table-bordered table-condensed" style="table-layout: fixed;">
		<thead>
			<tr>
				<th style="width: 40%">标题</th>
				<th>状态</th>
				<th style="width: 30%">类型</th>
				<th>发起人</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="plmAct" end="${line-1}">
			<tr>
				<td><a href="${ctx}/act/plmAct/form?id=${plmAct.id}">
					${plmAct.title}
				</a></td>
				<td>
					${fns:getDictLabel(plmAct.status, 'plm_act_status', '')}
				</td>
				<td>
					${fns:getDictLabel(plmAct.type, 'act_type', '')}
				</td>
				<td>
					${plmAct.supIni.name}
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<%-- <div class="pagination">${page}</div> --%>
