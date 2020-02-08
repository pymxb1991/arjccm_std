<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>门户方案管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$('#btnSubmit').click(function(){
				$('#searchForm').submit();
			});
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		//编辑内容
		var qid=null
		function bj(id){  
		  	  qid=id
		  	 
		  			jBox('iframe:${ctx}/statistics/plmStatisticsPlan/form?id='+qid, { 
		  			    title: "编辑门户内容", 
		  			    width: 600, 
		  			    height: 450, 
		  			    buttons: {},  //为了不出现底部的按钮这里特别要这样填写
		  			    closed: function(){  //关闭时发生，为了刷新父级页面
		  			    	search();
		  			    },
		  			    loaded : function(h) {   //隐藏滚动条
		  			    	$(".jbox-content").css( "overflow", "inherit");   			   
		  			    }
		  			}); 			  				 		 		
		  		  
		   }
	</script>
</head>
<body>
	<%-- <ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/statistics/plmStatisticsPlan/">门户方案列表</a></li>
		<shiro:hasPermission name="statistics:plmStatisticsPlan:edit"><li><a href="${ctx}/statistics/plmStatisticsPlan/form">门户方案添加</a></li></shiro:hasPermission>
	</ul> --%>
	<form:form id="searchForm" modelAttribute="plmStatisticsPlan" action="${ctx}/statistics/plmStatisticsPlan/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>方案名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="256" class="input-medium"/>
			</li>
			
			<li class="btns"><a id="btnSubmit" class="btn btn-primary" href="javascript:;"><i class="icon-search"></i> 查询</a>
			 <a id="btnSubmit" class="btn btn-primary" href="javascript:;" onclick="bj('')"><i class="icon-plus"></i>添加</a></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>方案名称</th>
				<th>方案标题</th>
				<th>说明</th>
				<th>每行列数</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="statistics:plmStatisticsPlan:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="plmStatisticsPlan">
			<tr>
				<td><a onclick="bj('${plmStatisticsPlan.id}')">
					${plmStatisticsPlan.name}
				</a></td>
				<td>
					${plmStatisticsPlan.pageTitle}
				</td>
				<td>
					${plmStatisticsPlan.introduce}
				</td>
				<td>
					${plmStatisticsPlan.type}
				</td>
				<td>
					<fmt:formatDate value="${plmStatisticsPlan.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				 <td>
					${plmStatisticsPlan.remarks}
				</td> 
				<shiro:hasPermission name="statistics:plmStatisticsPlan:edit"><td>
				   <a href="${ctx}/statistics/plmStatisticsDetail/list?pid=${plmStatisticsPlan.id}" title="方案明细"><i class="icon-edit"></i></a>
    				<a  onclick="bj('${plmStatisticsPlan.id}')" title="修改"><i class="icon-pencil"></i></a>
					<a class="btnList" href="${ctx}/statistics/plmStatisticsPlan/delete?id=${plmStatisticsPlan.id}" title="删除"  onclick="return confirmx('确认要删除该门户方案吗？', this.href)">
					<i class="icon-remove-sign"></i></a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>