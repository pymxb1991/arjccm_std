<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>接待申请管理</title>
	<meta name="decorator" content="default"/>
	<!-- 列表缩略图切换 -->
	<!--自适应  -->
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link href="${ctxStatic}/bootstrap/2.3.1/css_flat/bootstrap-responsive.css" rel="stylesheet">
	<link rel="stylesheet" href="${ctxStatic}/common/list/list.css">
	<script type="text/javascript" src="${ctxStatic}/common/list/list.js"></script>
	<!-- /列表缩略图切换 -->			
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
	</script>
	<script type="text/javascript" src="${ctxStatic}/plm/room/plmRoomReceptionApplyForm.js"></script>
	<style type="text/css">
		img {
		    width: 93px;
		    height: 107px;
		    max-width: 100px;
		    padding-top: 9px;
		}
	</style>
</head>
<body>
	<form:form id="searchForm" modelAttribute="plmRoomApply" action="${ctx}/logistics/plmRoomReceptionApply/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>接待名称：</label>
				<form:input path="subject" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>接待类型：</label>
				<form:select path="type" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('plm_room_rec_apply_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>接待地点：</label>
				<form:input type="hidden" id="roomIds" path="room.id" class="input-medium" />
			</li>
			<li class="btns"><a id="btnSubmit" class="btn btn-primary" href="javascript:;"><i class="icon-search"></i> 查询</a></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<!-- 列表缩略图切换按钮 -->
	<div id="switchbtn">
		<a class="thumbnailbtn"><i class="icon-th "></i></a>&nbsp; <a
			class="listbtn"> <i class="icon-th-list "></i></a>
	</div>
	<!--/列表缩略图切换按钮 -->
	<div id="prodInfo_List">			
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>接待名称</th>
				<th>接待类型</th>
				<th>接待发起人</th>
				<th>发起人联系方式</th>
				<th>接待地点</th>
				<th>开始时间</th>
				<th>结束时间</th>
				<th>更新时间</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="plmRoomApply">
			<tr>
				<td><a href="${ctx}/logistics/plmRoomReceptionApply/form?id=${plmRoomApply.id}">
					${plmRoomApply.subject}
				</a></td>
				<td>
					${fns:getDictLabel(plmRoomApply.type, 'plm_room_rec_apply_type', '')}
				</td>
				<td>
					${plmRoomApply.initiator.name}
				</td>
				<td>
					${plmRoomApply.initiatorTel}
				</td>
				<td>
					${plmRoomApply.room.subject}
				</td>
				<td>
					<fmt:formatDate value="${plmRoomApply.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${plmRoomApply.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${plmRoomApply.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<c:if test="${not empty plmRoomApply.procInsId}" var="condition">
							<a
								href="${ctx}/logistics/plmRoomReceptionApply/form?id=${plmRoomApply.id}"
								title="显示详情"><i class="icon-file"></i></a>
					</c:if>
					<c:if test="${!condition}">
							<a
								href="${ctx}/logistics/plmRoomReceptionApply/form?id=${plmRoomApply.id}"
								title="提交申请"><i class="icon-pencil"></i></a>
							<a class="btnList"
								href="${ctx}/logistics/plmRoomReceptionApply/delete?id=${plmRoomApply.id}"
								onclick="return confirmx('确认要删除该申请吗？', this.href)" title="删除"><i
								class="icon-trash"></i></a>
					</c:if>				
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	</div>
	<!-- 缩略图 -->
 	<div id="prodInfo_small">
		<div class="row">
			<c:forEach items="${page.list}" var="plmRoomApply">
				<div class="span4 spandiv">
					<div class="thumbnail">

						<a href="${ctx}/logistics/plmRoomReceptionApply/form?id=${plmRoomApply.id}">
							<h4 title="${plmRoomApply.code}">申请编号：${plmRoomApply.code}</h4>
						</a>
						<div class="caption row-fluid">

						     
							<div class="spantext  " style="width: 84%; margin-left: 7%">
								<p title="${plmRoomApply.subject}">接待名称：${plmRoomApply.subject}</p>			
							  
								<p title="${fns:getDictLabel(plmRoomApply.type, 'plm_room_rec_apply_type', '')}">接待类型：${fns:getDictLabel(plmRoomApply.type, 'plm_room_rec_apply_type', '')}</p>
								<p title="${plmRoomApply.room.subject}">接待地点：${plmRoomApply.room.subject}</p>																								
							</div>
						</div>
						<div class="footbtn" style="text-align: right;">
							<c:if test="${not empty plmRoomApply.procInsId}" var="condition">
									<a
										href="${ctx}/logistics/plmRoomReceptionApply/form?id=${plmRoomApply.id}"
										title="显示详情"><i class="icon-file"></i></a>
							</c:if>
							<c:if test="${!condition}">
									<a
										href="${ctx}/logistics/plmRoomReceptionApply/form?id=${plmRoomApply.id}"
										title="提交申请"><i class="icon-pencil"></i></a>
									<a class="btnList"
										href="${ctx}/logistics/plmRoomReceptionApply/delete?id=${plmRoomApply.id}"
										onclick="return confirmx('确认要删除该申请吗？', this.href)" title="删除"><i
										class="icon-trash"></i></a>
							</c:if>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</div> 
	<!-- /缩略图 -->				
	<div class="pagination">${page}</div>
</body>
</html>