<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>


<c:forEach items="${list}" var="round">
	<option value="${round.id}" label="${round.SName}">${round.SName}</option>
</c:forEach>

<c:if test="${fn:length(list) eq 0}">
	<option value="">无组织人员</option>
</c:if>