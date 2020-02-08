<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>


<c:forEach items="${histoicFlowList}" var="act">
	<c:if test="${act.histIns.activityId!='start'}">
		<c:if test="${not empty act.assigneeName}" >
		<tr>
			<td class="trtop" colspan="${titleColspan}">${act.histIns.activityName}</td>
			<td style="text-align: left;" colspan="${colspan}">
				<c:if test="${not empty act.histIns.endTime}" var="e">
					审批意见：${act.comment} <br />
					<div style="float: right;">
						<div style="text-align: left; width: 190px">
							审批人：${act.assigneeName} <br />日期：
							<fmt:formatDate value="${act.histIns.endTime}" pattern="yyyy-MM-dd " />
						</div>
					</div>
				</c:if> 
				<c:if test="${!e}">
					进行中.... <br />
					<div style="float: right;">
						<div style="text-align: left; width: 190px">
								审批人：${act.assigneeName} <br />
						</div>
					</div>
				</c:if>
			</td>
		</tr>
		</c:if>
	</c:if>
</c:forEach>
