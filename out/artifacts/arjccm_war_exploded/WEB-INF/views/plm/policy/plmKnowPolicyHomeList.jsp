<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<table id="contentTable" style="table-layout: fixed;">
	<c:forEach var="plmKnowPolicy" items="${list}" end="${line-1}">
		<tr>
			<td width="40%">标题：<a
				href="${ctx}/policy/plmKnowPolicy/one?id=${plmKnowPolicy.id}">
					${plmKnowPolicy.name} </a></td>
			<td width="28%">发布部门：${plmKnowPolicy.relDept}</td>
			<td width="32%">发布时间：<fmt:formatDate value="${plmKnowPolicy.updateDate}"
					pattern="yyyy年MM月dd日" /></td>
		</tr>
	</c:forEach>
</table>