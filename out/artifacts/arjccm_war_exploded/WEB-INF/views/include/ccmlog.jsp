<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="shiro" uri="/WEB-INF/tlds/shiros.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<shiro:hasPermission name="log:ccmLogTail:edit">
 <tr>
     <td class="pad">跟踪记录信息：</td>
     <td class="pad" colspan="3">
         <table>
         	<tr>
             	<c:forEach items="${ccmLogTailList}" var="logList">
                 
                   <li style="list-style-type: none;">
                     <%-- <a href="${ctx}/log/ccmLogTail/formPro?id=${logList.id}">
                             ${logList.tailPerson}&nbsp; 于&nbsp; <fmt:formatDate value="${logList.updateDate}" pattern="yyyy-MM-dd HH:mm:ss" />&nbsp; 跟踪记录信息
                     </a> --%>
                     <a onclick="parent.LayerDialog('${ctx}/log/ccmLogTail/formPro?id=${logList.id}', '跟踪记录', '800px', '660px')" >
                     	${logList.tailPerson}&nbsp; 于&nbsp; <fmt:formatDate value="${logList.updateDate}" pattern="yyyy-MM-dd HH:mm:ss" />&nbsp; 跟踪记录信息
                     </a>
                   </li>
                 
             	</c:forEach>
             </tr>
         </table>
     </td>
 </tr>
</shiro:hasPermission>