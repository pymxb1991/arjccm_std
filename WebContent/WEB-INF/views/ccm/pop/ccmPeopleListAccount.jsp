<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>实有人口管理</title>
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
		function show(){
			var s = $("#che").prop('checked');
			if(s){
				$("#show").css("display","block");
			}else{
				$("#show").css("display","none");
			}
		}
	</script>
	<style type="text/css">
		#show{display: none;}
		
	</style>
</head>
<body>
 
	
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/pop/ccmPeople/">实有人口列表</a></li>
		<li class="active"><a href="">户籍家庭人员列表</a></li>
		<shiro:hasPermission name="pop:ccmPeople:edit"><li><a href="${ctx}/pop/ccmPeople/formAccount?account=${ccmPeopleAccount.account}">户籍家庭人员添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="ccmPeople" action="${ctx}/pop/ccmPeople/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
<!-- 		<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" style="float: right;margin-right: 3%"/> -->
	
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>姓名</th>
				<th>人口类型</th>
				<th>出生日期</th>
				<th>户号</th>
				<th>户主姓名</th>
				<th>与户主关系</th>
				<th>公民身份号码</th>				
				<th>更新时间</th>
				<shiro:hasPermission name="pop:ccmPeople:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="ccmPeople">
			<tr>
				<td>
					<a href="${ctx}/pop/ccmPeople/formAccount?id=${ccmPeople.id}">${ccmPeople.name}</a>
				</td>
				<td>
					${fns:getDictLabel(ccmPeople.type, 'sys_ccm_people', '')}
				</td>
				<td>
					<fmt:formatDate value="${ccmPeople.birthday}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					${ccmPeople.account}
				</td>
				<td>
					${ccmPeople.accountname}
				</td>
				<td>
					${fns:getDictLabel(ccmPeople.accountrelation, 'sys_ccm_fami_ties', '')}
				</td>
				<td>
					${ccmPeople.ident}
				</td>				
				<td>
					<fmt:formatDate value="${ccmPeople.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td><shiro:hasPermission name="pop:ccmPeople:edit">
						<a class="btnList"  href="${ctx}/pop/ccmPeople/formAccount?id=${ccmPeople.id}" title="修改"><i class="icon-pencil"></i></a>
				    	<a class="btnList" href="${ctx}/pop/ccmPeople/deleteAccount?id=${ccmPeople.id}" onclick="return confirmx('确认要删除该户籍人口吗？', this.href)"  title="删除"><i class="icon-remove-sign"></i></a>
				        	    			  
	    			   <%-- <a class="btn btn-success" href="${ctx}/pop/ccmPeople/specialform?id=${ccmPeople.id}">人员标记</a>
						<a class="btn btn-danger" href="${ctx}/pop/ccmPeople/delete?id=${ccmPeople.id}" onclick="return confirmx('确认要删除该实有人口吗？', this.href)">删除</a>
				    </shiro:hasPermission> 
				    <shiro:hasPermission name="log:ccmLogTail:edit">
		  				<a	class="btn btn-success" href="${ctx}/log/ccmLogTail/formPro?relevance_id=${ccmPeople.id}&relevance_table=ccm_people">添加记录</a> --%>
				  	</shiro:hasPermission>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>