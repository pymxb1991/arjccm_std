<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>个人积分榜</title>
<meta name="decorator" content="default"/>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a>个人积分榜列表</a></li>
		<li ><a href="${ctx}/exam/pbsFractionPCController/integralOfcList">部门积分榜列表</a></li>
	</ul>
	<form:form method="post" class="breadcrumb form-search">
		<ul class="ul-form">
			<li><label>当前学员：</label> 
				<input class="input-medium" value="${user.SName}" readonly="readonly" type="text"/></li>
			<li>
			<li><label>分数：</label> 
				<input class="input-medium" value="<c:if test='${! empty usertime}'>${usertime} </c:if>
					<c:if test='${ empty usertime}'>您未参加此次考试。 </c:if>" readonly="readonly" type="text"/>
				</li>
			<li>
		</ul>
	</form:form>
	<table class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>序号</th>
				<th>头像</th>
				<th>名称</th>
				<th>组织机构</th>
				<th>分数</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${examSelfList}" var="examaction" varStatus="status">
				<tr>
					<td>${status.index+1}</td>
					<td>
						<img onerror='this.src="${ctxStatic}/wechat/img/head.png"' src="${examaction.sExaminee.SPhoto}" style="width:40px;height:40px"> 
					</td>
					<td>${examaction.sExaminee.SName}</td>
                    <td>${examaction.sDepartment.name}</td>
					<td>${examaction.times}</td>
				</tr> 
			</c:forEach>
		</tbody>
	</table>
</body>
</html>