<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
	<sys:message content="${message}"/>
	<select name="brandlist" id="brand_sel" multiple="true" style="width:100%;" ondblclick="javascript:rightMove();">
		<c:forEach items="${list}" var="user">
			<option value="${user.id}">${user.name}</option>
		</c:forEach>
	</select>
