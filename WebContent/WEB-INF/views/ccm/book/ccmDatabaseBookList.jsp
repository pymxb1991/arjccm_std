<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>资料库录入管理管理</title>
	<meta name="decorator" content="default"/>
	<link rel="stylesheet"
		  href="${ctxStatic}/jquery-ui-1.12.1/jquery-ui.min.css">
	<script src="${ctxStatic}/jquery-ui-1.12.1/jquery-ui.min.js"></script>
	<script type="text/javascript"	src="${ctxStatic}/plm/storage/ajaxMessageAlert.js"></script>
	<script type="text/javascript" src="${ctxStatic}/plm/storage/storageValidate.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnSubmit").on("click" ,function(){
				var begin = new Date($("[name='beginCreateDate']").val());
				var end = new Date($("[name='endCreateDate']").val());
				if(begin.getTime() > end.getTime()){
					messageAlert("开始时间大于结束时间！", "error");
					return false;
				}
				$("#searchForm").submit();
			})
		});
		$("#addDetailInfo").dialog({

			autoOpen : false,
			closeOnEscape : false,
			height : 500,
			width : 1100,
			modal : true,
			close : function() {
				$(this).dialog("close");
			}
		});
		$("a[title='addDetail']").on("click", function() {
			$("#addDetailInfo").attr("src", this);
			$("#addDetailInfo").dialog("open");
			$("#addDetailInfo").css({
				"width" : "98%"
			});
			return false;
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/book/ccmDatabaseBook/">书籍列表</a></li>
		<%--<shiro:hasPermission name="book:ccmDatabaseBook:edit"><li><a href="${ctx}/book/ccmDatabaseBook/form">资料库录入管理添加</a></li></shiro:hasPermission>--%>
	</ul>
	<form:form id="searchForm" modelAttribute="ccmDatabaseBook" action="${ctx}/book/ccmDatabaseBook/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>书籍名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label >更新时间：</label>
				<input name="beginCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="<fmt:formatDate value="${ccmDatabaseBook.beginCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});" />
				- <input name="endCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
						 value="<fmt:formatDate value="${ccmDatabaseBook.endCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
						 onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});" />
			</li>
			<li class="btns"><a
						onclick="parent.LayerDialog('${ctx}/book/ccmDatabaseBook/form', '添加', '800px', '600px')"
						class="btn btn-success"><i class="icon-plus"></i> 添加 </a></li>
			<li class="btns">
				<a href="javascript:;" id="btnSubmit" class="btn btn-primary">
					<i class="icon-search"></i> 查询 </a>
			</li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<%--<!-- 列表缩略图切换按钮 -->
	<div id="switchbtn">
		<a class="thumbnailbtn"><i class="icon-th "></i></a>&nbsp; <a
			class="listbtn"> <i class="icon-th-list "></i></a>
	</div>

	<div id="prodInfo_List">
		<table id="contentTable" class="table table-striped table-bordered table-condensed">
			<thead>
				<tr>
					<th>书籍名称</th>
					<th>描述信息</th>
					<th>更新时间</th>
					<shiro:hasPermission name="book:ccmDatabaseBook:edit"><th>操作</th></shiro:hasPermission>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${page.list}" var="ccmDatabaseBook">
				<tr>
					<td><a href="${ctx}/book/ccmDatabaseBook/form?id=${ccmDatabaseBook.id}">
						${ccmDatabaseBook.name}
					</a></td>
					<td>
						${ccmDatabaseBook.remarks}
					</td>
					<td>
						<fmt:formatDate value="${ccmDatabaseBook.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</td>
					<shiro:hasPermission name="book:ccmDatabaseBook:edit"><td>
						<a href="${ctx}/book/ccmDatabaseBook/form?id=${ccmDatabaseBook.id}"><i class="icon-pencil"></i></a>
						<a href="${ctx}/book/ccmDatabaseBook/delete?id=${ccmDatabaseBook.id}" onclick="return confirmx('确认要删除该书籍吗？', this.href)"><i class="icon-remove-sign"></i></a>
					</td></shiro:hasPermission>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>--%>
	<!-- 缩略图 -->
	<div id="prodInfo_small">
		<div class="row">
			<c:forEach items="${page.list}" var="ccmDatabaseBook">
				<c:if test="${not empty ccmDatabaseBook.code }">
				<div class="span4 spandiv">
					<div class="thumbnail">
						<a onclick="parent.LayerDialog('${ctx}/book/ccmDatabaseBook/form?id=${ccmDatabaseBook.id}', '编辑', '800px', '600px')">
							<h4 title="${ccmDatabaseBook.code}">书籍编号:${ccmDatabaseBook.code}</h4>
						</a>
						<div class="caption row-fluid">
							<div class=" spanimg" style="width: 30%">
								<img src="${ccmDatabaseBook.imageurl}"
									 onerror='this.src="${ctxStatic}/common/list/images/timg.jpg"'
									 alt="通用的占位符缩略图" />
							</div>
							<div class="spantext  " style="width: 63%; margin-left: 7%">
								<p title="书籍名称"><a href="${ctx}/book/ccmDatabaseBook/index?id=${ccmDatabaseBook.id}" >书籍名称:${ccmDatabaseBook.name}</a></p>
								<p title="描述信息">描述信息:${ccmDatabaseBook.remarks}</p>
								<p title="<fmt:formatDate value="${ccmDatabaseBook.updateDate}" pattern="yyyy-MM-dd"/>">更新时间:<fmt:formatDate value="${ccmDatabaseBook.updateDate}" pattern="yyyy-MM-dd"/></p>
							</div>
						</div>
						<div class="footbtn" style="text-align: right;">
							<shiro:hasPermission name="book:ccmDatabaseBook:edit"><td>
								<%--<a onclick="parent.LayerDialog('${ctx}/book/ccmDatabaseBook/form?id=${ccmDatabaseBook.id}', '编辑', '800px', '600px')"
								   class="btn btn-success"><i class="icon-pencil"></i></a>--%>
								<%--<a href="${ctx}/book/ccmDatabaseBook/form?id=${ccmDatabaseBook.id}"><i class="icon-pencil"></i></a>--%>
								<a href="${ctx}/book/ccmDatabaseBook/delete?id=${ccmDatabaseBook.id}" onclick="return confirmx('确认要删除该书籍吗？', this.href)"><i class="icon-remove-sign"></i></a>
							</td></shiro:hasPermission>
						</div>
					</div>
				</div>
				</c:if>
			</c:forEach>
		</div>
	</div>
	<!-- /缩略图 -->
	<div class="pagination">${page}</div>
	<%--<iframe id="addDetailInfo" src=""></iframe>--%>
</body>
</html>