<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>会议安排</title>
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
	</script>
	<style type="text/css">
        img {
		   	width: 130px;
		    max-width: 150px;
		}
		p {
		    margin: 0 27px 10px;
		}
    </style>	
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/logistics/plmRoomMeetingApply/getroombyuserId">附件列表</a></li>
	</ul>
	<sys:message content="${message}"/>
	<div id="prodInfo_List">
		<table id="contentTable" class="table table-striped table-bordered table-condensed" >
			<thead>
				<tr>
					<th>附件名称</th>
					<th>上传时间</th>
					<shiro:hasPermission name="logistics:plmRoom:edit"><th>操作</th></shiro:hasPermission>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${page.list}" var="plmRoom">
				<tr>
					<td>
						${fns:getDictLabel(plmRoom.resourceName, 'plm_room_resource_type', '')}
					</td>
					<td>
						<fmt:formatDate value="${plmRoom.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</td>
					<td>
					<shiro:hasPermission name="logistics:plmRoom:edit">
						<input type="hidden" value="${plmRoom.resourceAddress}">
						<input type="hidden" value="${ctx}">
						<input type="hidden" value="${fn:substringAfter(plmRoom.resourceAddress, '.')}">
    				    <%-- <a class="btnList" onclick="getdownload('${plmRoom.id}')" title="下载"><i class="icon-download"></i></a> --%>
    				    <c:if test="${not empty plmRoom.resourceAddress}">
    				    	<a class="btnList" href="${path}${fn:replace(plmRoom.resourceAddress,'|','')}" download="${fns:getDictLabel(plmRoom.resourceName, 'plm_room_resource_type', '')}.${fn:substringAfter(plmRoom.resourceAddress, '.')}" title="下载"><i class="icon-download"></i></a>
    				    </c:if>	 
    				    <a class="btnList" href="${ctx}/logistics/plmRoomMeetingApplyResource/deletesource?id=${plmRoom.id}"><i title="删除" class="icon-trash"></i></a>
					</shiro:hasPermission>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
	<div class="pagination">${page}</div>
</body>
</html>