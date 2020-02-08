<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>收藏夹管理管理</title>
	<meta name="decorator" content="default"/>
	<link rel="stylesheet"
		  href="${ctxStatic}/jquery-ui-1.12.1/jquery-ui.min.css">
	<script src="${ctxStatic}/jquery-ui-1.12.1/jquery-ui.min.js"></script>
	<script src="${ctxStatic}/layim/layui/layui.js" type="text/javascript"></script>
	<script type="text/javascript"	src="${ctxStatic}/plm/storage/ajaxMessageAlert.js"></script>
	<script type="text/javascript" src="${ctxStatic}/plm/storage/storageValidate.js"></script>
	<script type="text/javascript" src='${ctxStatic}/ccm/databaseCollection/js/jquery.min.js'></script>
	<script type="text/javascript" src='${ctxStatic}/ccm/databaseCollection/js/scroll.1.3.js'></script>
	<script type="text/javascript" src='${ctxStatic}/ccm/databaseCollection/js/main.js'></script>
	<script type="text/javascript">
		$(document).ready(function() {
			
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
	<!-- 条目中可以是任意内容，如：<img src=""> -->
	<script>
		layui.use('carousel', function(){
			var carousel = layui.carousel;
			//建造实例
			carousel.render({
				elem: '#test1'
				,width: '30%' //设置容器宽度
				,arrow: 'always' //始终显示箭头
				//,anim: 'updown' //切换动画方式
			});
		});
	</script>
	<style>
		/*左右滚动效果*/
		/*公共部分*/
		.wc1200{margin:0 auto;width:1200px;padding-top: 0px;background:#F7F7F7;}
		.fr{float:right;}
		.mt20{/*margin-top:20px;*/}
		.icon{background:url(${ctxStatic}/ccm/databaseCollection/images/icon.png) no-repeat 0 0;}

		.warp-pic-list li{float:left;display:inline;}
		.warp-pic-list .img_wrap{display:block;font-size:0;overflow:hidden;}
		.warp-pic-list .text-area{background-color:#f2f2f2;line-height:24px;}
		/*全局板块*/
		.row .hd{background:url(${ctxStatic}/ccm/databaseCollection/images/hd-line_01.jpg) no-repeat 0 50px;height:55px;}
		.row .hd .title{font:26px/40px "微软雅黑","Microsoft YaHei","黑体","SimHei";}
		/*全局页签*/
		.tab-T-3{width:66px;}
		.tab-T-3 li{width:12px;height:12px;font-size:0;background-color:#dfdfdf;float:left;
			margin-left:10px;cursor:pointer;display:inline;}
		.tab-T-3 li.cur{background-color:#d81c1b;}
		/**/
		.rowE .warp-pic-list{width:980px;height:180px;overflow:hidden;background:#F7F7F7;margin-top:-24px;}
		.rowE .count li{margin-right:20px;width:220px;height:180px;}
		.rowE .count .img_wrap{width:220px;height:135px;}
		.rowE .count .img_wrap img{width:220px;}
		.rowE .btn{display:block;height:42px;position:absolute;top:15px;width:35px;z-index:200;cursor:pointer;}
		.rowE .prev{ background-position:0 -88px;left:278px;top:44px;}
		.rowE .prev:hover{background-position:0 -144px;}
		.rowE .next{ background-position:0 -200px;right:38px;top:44px;}
		.rowE .next:hover{background-position:0 -256px;}
		.qh_title{line-height: 28px;text-align: center;display: block;font-size: 16px;}
	</style>

</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/collection/ccmDatabaseCollection/">收藏夹管理列表</a></li>
		<%--<shiro:hasPermission name="collection:ccmDatabaseCollection:edit"><li><a href="${ctx}/collection/ccmDatabaseCollection/form">收藏夹管理添加</a></li></shiro:hasPermission>--%>
	</ul>
	<form:form id="searchForm" modelAttribute="ccmDatabaseCollection" action="${ctx}/collection/ccmDatabaseCollection/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
		<li><label>书籍名称：</label>
			<form:input path="bookName" htmlEscape="false" maxlength="1000" class="input-medium"/>
		</li>
		<%--<li><label>收藏人员：</label>
			<sys:treeselect id="user" name="user" value="${ccmDatabaseCollection.user.id}" labelName="" labelValue="${ccmDatabaseCollection.userName}"
							title="用户" url="/collection/ccmDatabaseCollection/treeData?type=3" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
		</li>--%>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>

	<div id="prodInfo_small">
		<div class="row">
			<c:forEach items="${lists}" var="lists">
				<div class="layui-col-xs12" style="width: 100%;height: auto;border-style:solid; border-width:5px;border-color:#d2e6ab;margin:20px;padding:20px;">
				<div class="span4 spandiv">
					<div class="thumbnail" style="width:190px;">

						<div class="caption row-fluid">
							<div class=" spanimg" style="width: 25%">
								<img src="${lists.photo}"
									 onerror='this.src="${ctxStatic}/common/list/images/timg.jpg"'
									 alt="通用的占位符缩略图" />
								<p title="用户名">用户名:${lists.name}</p>
							</div>
							<%--<div class="spantext  " style="width: 63%; margin-left: 7%">
								<p title="用户名">用户名:${lists.name}</p>
							</div>--%>
						</div>
					</div>
				</div>


					<%--<div class="span4 spandiv">
								<div class=" spanimg" style="width: 50%">
									<img src="${lists.photo}"
										 onerror='this.src="${ctxStatic}/common/list/images/timg.jpg"'
										 alt="通用的占位符缩略图" />
									<p title="用户名">用户名:${lists.name}</p>
								</div>
					</div>
					<div class="layui-carousel" id="test1" style="margin-left: 500px">
						<div carousel-item>

							<c:forEach items="${lists.bookData}" var="bookData">
								<div style="margin-right: 50px">
								<div class="span4 spandiv">
								<div class="thumbnail">
								<div class="caption row-fluid">
									<div class="spanimg" style="width: 30%;margin-left: 50px">
										<img src="${bookData.imageurl}"
											 onerror='this.src="${ctxStatic}/common/list/images/timg.jpg"'
											 alt="通用的占位符缩略图" />
									</div>
									<div class="spantext" style="width: 63%; margin-left: 7%">
										<p title="书籍/章节名称"><a href="${ctx}/book/ccmDatabaseBook/index?id=${ccmDatabaseBook.id}" >书籍/章节名称:${bookData.name}</a></p>
										<p title="<fmt:formatDate value="${bookData.createDate}" pattern="yyyy-MM-dd"/>">更新时间:<fmt:formatDate value="${bookData.createDate}" pattern="yyyy-MM-dd"/></p>
									</div>
								</div>
								<div class="footbtn" style="text-align: right;">
									<shiro:hasPermission name="collection:ccmDatabaseCollection:edit"><td>
										<a href="${ctx}/collection/ccmDatabaseCollection/delete?id=${ccmDatabaseBook.id}" onclick="return confirmx('确认要删除该书籍吗？', this.href)"><i class="icon-remove-sign"></i></a>
									</td></shiro:hasPermission>
								</div>
								</div>
								</div>
								</div>
							</c:forEach>

						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>--%>

	<div class="wc1200 row rowE" style="margin-left: 300px">
		<div class="bd mt20" style="margin: 10px;width: 1000px;">
			<div id="sca1" class="warp-pic-list">
				<div id="wrapBox1" class="wrapBox" style="width: 1000px;height: 155px;/* overflow: hidden; */border-top-width: 5px;margin-top: 26px;">
					<ul id="count1" class="count clearfix">
						<c:forEach items="${lists.bookData}" var="bookData">
						<%--<li>
							<a href="#2685" class="img_wrap">
								<img src="${bookData.imageurl}" border="0"></a>
							<span class="qh_title">书籍/章节名称:${bookData.name}</span>
						</li>--%>
							<li>
								<div class="caption row-fluid">
									<div class="spanimg" style="width: 30%;margin-left: 50px">
										<img src="${bookData.imageurl}"
											 onerror='this.src="${ctxStatic}/common/list/images/timg.jpg"'
											 alt="通用的占位符缩略图" />
									</div>
									<div class="spantext" style="width: 63%; margin-left: 7%">
										<p title="书籍/章节名称"><a href="${ctx}/book/ccmDatabaseBook/index?id=${ccmDatabaseBook.id}" >书籍/章节名称:${bookData.name}</a></p>
										<p title="<fmt:formatDate value="${bookData.createDate}" pattern="yyyy-MM-dd"/>">更新时间:<fmt:formatDate value="${bookData.createDate}" pattern="yyyy-MM-dd"/></p>
									</div>
								</div>
								<div class="footbtn" style="text-align: right;">
									<shiro:hasPermission name="collection:ccmDatabaseCollection:edit"><td>
										<a href="${ctx}/collection/ccmDatabaseCollection/delete?id=${ccmDatabaseBook.id}" onclick="return confirmx('确认要删除该书籍吗？', this.href)"><i class="icon-remove-sign"></i></a>
									</td></shiro:hasPermission>
								</div>
				</li>
						</c:forEach>
					</ul>

				</div>
				<a id="right1" class="prev icon btn"></a>
				<a id="left1" class="next icon btn"></a>
			</div>
		</div>
	</div></c:forEach>
			</div>
		</div>
	</div>
	<div class="pagination">${page}</div>
</body>
</html>