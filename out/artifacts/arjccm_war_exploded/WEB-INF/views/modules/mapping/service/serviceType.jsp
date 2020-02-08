<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<c:if test="${purposeType eq 'list'}">
	<option value="" label="">全部</option>
</c:if>
<c:forEach items="${type}" var="round">
	<option value="${round.value}" label="${round.label}">${round.label}</option>
</c:forEach>
