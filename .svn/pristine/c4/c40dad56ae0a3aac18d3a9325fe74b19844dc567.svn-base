<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>居民用户管理</title>
	<meta name="decorator" content="default"/>
	<link href="${ctxStatic}/layer-v3.1.1/layer/theme/default/layer.css"
	rel="stylesheet" />
	<script src="${ctxStatic}/layer-v3.1.1/layer/layer.js"></script>
	
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnSubmit").on("click" ,function(){
				$("#searchForm").submit();
			})
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		
		function HandleTips(_this, id) {
			var html = '';
			html += '<div style="padding:10px;" >';
			html += '<p style="margin-botom:20px;">请做出选择</p>';
			html += '<div>';
			html += '<input id="btnSuccess" onclick="Pass('
					+ "&quot;"
					+ id
					+ "&quot;"
					+ ')" class="btn btn-primary " type="button" value="通过" style="background: #0687fd">';
			html += '<input id="btnPass"  onclick="NoPass('
					+ "&quot;"
					+ id
					+ "&quot;"
					+ ')" class="btn btn-primary" type="button" value="拒绝" style="background: #f50b4b">';
			html += '</div>';
			html += '</div>';
			tip_index = layer.tips(html, _this, {
				tips : [ 1, '#20c694' ],
				time : 3000,
			});
		}
		function Pass(id) {
			$.post('${ctx}/cms/ccmFontUser/pass?loginFlag=02&id='+ id,function(data){
				if(data.code == '200'){
					$.jBox.tip('操作成功！');
					window.location.reload()
				}
			})
		}
		function NoPass(id) {
			$.post('${ctx}/cms/ccmFontUser/pass?loginFlag=04&id='+ id,function(data){
				if(data.code == '200'){
					$.jBox.tip('操作成功！');
					window.location.reload()
				}
			})
		}
		
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/cms/ccmFontUser/checkList">居民用户管理列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="ccmFontUser" action="${ctx}/cms/ccmFontUser/checkList" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">			
			<li><label>身份证号：</label>
				<form:input path="no" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>姓名：</label>
				<form:input path="name" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>审核状态：</label> <form:select path="loginFlag" class="input-medium">
					<form:option value="" label="全部" />
					<form:options items="${fns:getDictList('font_user_loginFlag')}"
						itemLabel="label" itemValue="value" htmlEscape="false"  />
				</form:select></li>
			<!-- <li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li> -->
			<li class="btns">
			<a href="javascript:;" id="btnSubmit" class="btn btn-primary">
                <i class="icon-search"></i> 查询 </a>
			</li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>登录名</th>
				<th>身份证号</th>
				<th>姓名</th>
				<th>审核状态</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="cms:ccmFontUser:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="ccmFontUser">
			<tr>
				<td><a onclick="parent.parent.LayerDialog('${ctx}/cms/ccmFontUser/form?id=${ccmFontUser.id}', '修改', '700px', '750px')">
					${ccmFontUser.loginName}
				</a></td>
				<td>
					${ccmFontUser.no}
				</td>
				<td>
					${ccmFontUser.name}
				</td>
				<td>
					${fns:getDictLabel(ccmFontUser.loginFlag,'font_user_loginFlag','')}	
					
				</td>
				<td>
					<fmt:formatDate value="${ccmFontUser.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td class="tp">
					${ccmFontUser.remarks}
				</td>
				<shiro:hasPermission name="cms:ccmFontUser:edit"><td>
    				<%-- <a href="${ctx}/cms/ccmFontUser/form?id=${ccmFontUser.id}">修改</a>
					<a href="${ctx}/cms/ccmFontUser/delete?id=${ccmFontUser.id}" onclick="return confirmx('确认要删除该居民用户管理吗？', this.href)">删除</a> --%>
					<%-- <a href="${ctx}/cms/ccmFontUser/check?id=${ccmFontUser.id}">审核</a> --%>
					<c:choose>
			   			<c:when test="${ccmFontUser.loginFlag eq '02'}">
			   				<i class="icon-legal" title="审核" style="margin-left: 5px;"></i>
			   			</c:when>
			   			<c:otherwise>
							<a class="btnList" onclick="HandleTips(this,'${ccmFontUser.id}')" title="审核"><i class="icon-legal"></i></a>
			   			</c:otherwise>
		   		  	</c:choose>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>