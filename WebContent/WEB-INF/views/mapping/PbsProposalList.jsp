 <%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<c:forEach items="${list}" var="round">
    <option value="${round.id}" label="${round.STitle}">${round.STitle}</option>
</c:forEach>

 