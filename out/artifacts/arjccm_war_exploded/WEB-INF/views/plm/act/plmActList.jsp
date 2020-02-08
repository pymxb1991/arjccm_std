<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>业务申请单主表管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctxStatic}/plm/storage/ajaxMessageAlert.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			
						
		});
		
		function del(id){
		
		 top.$.jBox.confirm("确认要删除该业务申请单主表吗？","系统提示",function(v,h,f){
				if(v=="ok"){	
					
					 $.ajax({
						  url:'${ctx}/act/plmAct/delete',
						  data:{"id":id  },
						  type:'post',
						  dataType:'text',
						  error: function(){messageAlert("删除失败！", "error");},
						  success:function(data){
							   
							
								 location.reload(); 			 
								 messageAlert("删除成功！", "success");
								 parent.refreshTree();
						   }
						  });	  				 
				}
			},{buttonsFocus:1});  	
		}
		 
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<form:form id="searchForm" modelAttribute="plmAct" action="${ctx}/act/plmAct/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>标题</th>
				<th>状态</th>
				<th>类型</th>
				<th>是否督办</th>
				<th>督办人</th>
				<!-- <th>发起人</th> -->
				<th>发起时间</th>
				<shiro:hasPermission name="act:plmAct:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="plmAct">
			<tr>
				<td><a href="${ctx}/act/plmAct/form?id=${plmAct.id}">
					${plmAct.title}
				</a></td>
				<td>
					${fns:getDictLabel(plmAct.status, 'plm_act_status', '')}
				</td>
				<td>
				<c:if test="${plmAct.status!='03' && plmAct.status!='04' && plmAct.status!='01'}">
					<a target="_blank" href="${pageContext.request.contextPath}/act/diagram-viewer?processDefinitionId=${plmAct.procDefId}&processInstanceId=${plmAct.procInsId}">
					${fns:getDictLabel(plmAct.type, 'act_type', '')}</a>
				</c:if>
				<c:if test="${plmAct.status=='03' || plmAct.status=='04'}">
					<a target="_blank" href="${pageContext.request.contextPath}/act/diagram-viewer?processDefinitionId=${plmAct.procDefId}">
					${fns:getDictLabel(plmAct.type, 'act_type', '')}</a>
				</c:if>
				<c:if test="${plmAct.status=='01' }">
				${fns:getDictLabel(plmAct.type, 'act_type', '')}
				</c:if>
				</td>
				<td>
					${fns:getDictLabel(plmAct.isSup, 'yes_no', '')}
				</td>
				<td>
					${plmAct.supExe.name}
				</td>
				<%-- <td>
					${plmAct.supIni.name}
				</td> --%>
				<td>
					<fmt:formatDate value="${plmAct.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="act:plmAct:edit"><td>
				
    			<c:if test="${plmAct.status=='01'}" var="e">
    				<a class="btnList"  href="${ctx}/act/plmAct/form?id=${plmAct.id}"title="修改"><i class="icon-pencil"></i></a>
    			</c:if>	
    			 <c:if test="${!e}" >
    				<a class="btnList"  href="${ctx}/act/plmAct/form?id=${plmAct.id}"title="显示详情"><i class="icon-file"></i></a>
    			</c:if>	
    				 <c:if test="${plmAct.status!='02'}" >
					<a class="btnList" <%-- href="${ctx}/act/plmAct/delete?id=${plmAct.id}" --%> onclick="del('${plmAct.id}')"title="删除"><i
										class="icon-trash"></i></a>
					</c:if>						
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>