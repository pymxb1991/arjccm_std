<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>建筑物管理</title>
<meta name="decorator" content="blank" />
<script type="text/javascript">
	var ctx = '${ctx}', ctxStatic = '${ctxStatic}';
</script>
<link href="${ctxStatic}/jquery-ztree/3.5.12/css/zTreeStyle/zTreeStyle.min.css"rel="stylesheet" type="text/css" />
<link href="${ctxStatic}/iconfont/roomfont/iconfont.css" type="text/css" rel="stylesheet" />
	<link href="${ctxStatic}/iconfont/newfont/iconfont.css" type="text/css" rel="stylesheet" />
<script src="${ctxStatic}/jquery/jquery-1.8.3.min.js" type="text/javascript"></script>
<script src="${ctxStatic}/jquery/jquery.cookie.js" type="text/javascript"></script>
<link href="${ctxStatic}/layim/layui/css/layui.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="${ctxStatic}/layim/layui/layui.js"></script>
<script src="${ctxStatic}/jquery-ztree/3.5.12/js/jquery.ztree.all-3.5.js" type="text/javascript"></script>
<link href="${ctxStatic}/layer-v3.1.1/layer/theme/default/layer.css" rel="stylesheet" />
<script src="${ctxStatic}/layer-v3.1.1/layer/layer.js"></script>
<script type="text/javascript" src="${ctxStatic}/ccm/house/js/ccmBuildmanageInfo.js"></script>
<script type="text/javascript">
    $(function(){
		$(".pimg").click(function(){
			var _this = $(this);//将当前的pimg元素作为_this传入函数
			imgShow("#outerdiv", "#innerdiv", "#bigimg", _this);
		});
	});
	function imgShow(outerdiv, innerdiv, bigimg, _this){
		var src = _this.attr("src");//获取当前点击的pimg元素中的src属性
		$(bigimg).attr("src", src);//设置#bigimg元素的src属性
		/*获取当前点击图片的真实大小，并显示弹出层及大图*/
		$("<img/>").attr("src", src).load(function(){
			var windowW = $(window).width();//获取当前窗口宽度
			var windowH = $(window).height();//获取当前窗口高度
			var realWidth = this.width;//获取图片真实宽度
			var realHeight = this.height;//获取图片真实高度
			var imgWidth, imgHeight;
			var scale = 0.8;//缩放尺寸，当图片真实宽度和高度大于窗口宽度和高度时进行缩放
			if(realHeight>windowH*scale) {//判断图片高度
				imgHeight = windowH*scale;//如大于窗口高度，图片高度进行缩放
				imgWidth = imgHeight/realHeight*realWidth;//等比例缩放宽度
				if(imgWidth>windowW*scale) {//如宽度扔大于窗口宽度
					imgWidth = windowW*scale;//再对宽度进行缩放
				}
			} else if(realWidth>windowW*scale) {//如图片高度合适，判断图片宽度
				imgWidth = windowW*scale;//如大于窗口宽度，图片宽度进行缩放
				imgHeight = imgWidth/realWidth*realHeight;//等比例缩放高度
			} else {//如果图片真实高度和宽度都符合要求，高宽不变
				imgWidth = realWidth;
				imgHeight = realHeight;
			}
			$(bigimg).css("width",imgWidth);//以最终的宽度对图片缩放
			var w = (windowW-imgWidth)/2;//计算图片与窗口左边距
			var h = (windowH-imgHeight)/2;//计算图片与窗口上边距
			$(innerdiv).css({"top":h, "left":w});//设置#innerdiv的top和left属性
			$(outerdiv).fadeIn("fast");//淡入显示#outerdiv及.pimg
		});
		$(outerdiv).click(function(){//再次点击淡出消失弹出层
			$(this).fadeOut("fast");
		});
	}
</script>
<style>
.build {
	width: 300px;
	height: auto;
	position: relative;
	margin-left: 70px;
}

.floor {
	width: 100%;
	height: 50px;
}

.build table {
	width: 100%;
	height: 100%;
	border-collapse: collapse;
	padding: 0;
	table-layout: fixed;
}

.build table td {
	border: 1px solid #000;
	text-align: center;
}

.floor-add {
	width: 100%;
	text-align: center;
	cursor: pointer;
	background: #f5f3f0;
}

.floor-add i {
	font-size: 42px;
}

.floor-center {
	width: 100%;
	height: 100%;
	position: relative;
	cursor: pointer;
	background: #f5f3f0;
}

.floor-center:hover {
	background: #ffd073!important;
}

.floor-center span {
	line-height: 47px;
}

.remove-floor {
	position: absolute;
	right: 3px;
	top: 3px;
	font-size: 12px;
	font-weight: bold;
	display: none;
}

.floor-add-left {
	position: absolute;
	left: 3px;
	bottom: 3px;
	display: none;
	color: #55ac4f;
}

.floor-add-right {
	position: absolute;
	right: 3px;
	bottom: 3px;
	display: none;
	color: #55ac4f;
}

.unit {
	width: 300px;
	float: left;
	position: relative;
}

.unit-num {
	width: 100%;
	position: relative;
	float: left;
	
}

.unit-num span {
	width: 100%;
	font-weight: bold;
	text-align: center;
	height: 24px;
    line-height: 24px;
	display: block;
	background: -ms-linear-gradient(top, #fff, #e6e6e6); /* IE 10 */
	background: -moz-linear-gradient(top, #fff, #e6e6e6); /*火狐*/
	background: -webkit-gradient(linear, 0% 0%, 0% 100%, from(#fff),
		to(#e6e6e6)); /*谷歌*/
}

.addFloor {
	width: 48px;
	height: 24px;
	line-height: 24px;
	background: -ms-linear-gradient(top, #fff, #e6e6e6); /* IE 10 */
	background: -moz-linear-gradient(top, #fff, #e6e6e6); /*火狐*/
	background: -webkit-gradient(linear, 0% 0%, 0% 100%, from(#fff),
		to(#e6e6e6)); /*谷歌*/
	text-align: center;
	cursor: pointer;
	margin-left: 70px;
}

.addFloor.addFloorBottom {
	height: 48px;
	line-height: 48px;
}

.addFloorBottom {
	position: absolute;
	right: -48px;
	top: -48px;
}

.addUnit {
	position: absolute;
	right: -48px;
	top: 1px;
}

.unit-botom {
	float: left;
	position: relative;
}
ul.ztree.zTreeDragUL{
   z-index: 9999999999999
}
.dom_line {
	margin: 2px;
	border-bottom: 1px gray dotted;
	height: 1px;
	
}

.domBtnDiv {
	display: block;
	padding: 2px;
	border: 1px gray dotted;
	background-color: powderblue;
}

.categoryDiv {
	display: inline-block;
	width: 335px;
}

.domBtn {
	display: inline-block;
	cursor: pointer;
	padding: 2px;
	margin: 2px 10px;
	border: 1px gray solid;
	background-color: #FFE6B0;
}

.domBtn_Disabled {
	display: inline-block;
	cursor: default;
	padding: 2px;
	margin: 2px 10px;
	border: 1px gray solid;
	background-color: #DFDFDF;
	color: #999999;
}

.dom_tmp {
	position: absolute;
	font-size: 12px;
}

/*.active {
	background-color: #93C3CF
}*/
</style>
</head>
<body>
<%--<img  src="${ctxStatic}/images/shouyedaohang.png"; class="nav-home">--%>
<%--<span class="nav-position">当前位置 ：</span><span class="nav-menu"><%=session.getAttribute("activeMenuName")%>></span><span class="nav-menu2">地址库管理</span>--%>
<div class="back-list">
	<!-- 导入、导出模块 -->
	<div id="context" class="hide">
		<form id="importForm" action="${ctx}/house/ccmHouseBuildmanage/import" method="post" enctype="multipart/form-data" class="form-search" style="padding-left: 20px; text-align: center;" onsubmit="loading('正在导入，请稍等...');">
			<br />
			<input id="uploadFile" name="file" type="file" style="width: 330px" /><br /> <br /> <input id="btnImportSubmit" class="btn btn-primary" type="submit" value="导  入 " />
		</form>
	</div>
	<ul class="nav nav-tabs">
		<li style="width: 112px"><a href="${ctx}/report/ccmReportOthers/houseAndBuild" style="text-align: center">数据统计</a></li>
		<li class="active" style="width: 112px"><a class="nav-head" href="${ctx}/house/ccmHouseBuildmanage/">数据列表</a></li>
		<shiro:hasPermission name="house:ccmHouseBuildmanage:edit">
			<li style="width: 112px"><a  href="${ctx}/house/ccmHouseBuildmanage/form" style="text-align: center">数据添加</a></li>
			<%-- 			<li><a href="${ctx}/pop/ccmPopTenant/listBuild?buildingId=${ccmHouseBuildmanage.id}">建筑物内房屋列表</a></li> --%>
		</shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="ccmHouseBuildmanage" action="${ctx}/house/ccmHouseBuildmanage/" method="post" class="breadcrumb form-search clearfix">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}" />
		<ul class="ul-form pull-left">
			<li class="first-line"><label class="title-text">建筑物名称：</label> <form:input path="buildname" htmlEscape="false" maxlength="512" class="input-medium" /></li>
			<li class="first-line"><label class="title-text">所属网格：</label> <sys:treeselect id="area" name="area.id" value="${ccmHouseBuildmanage.area.id}" labelName="area.name" labelValue="${ccmHouseBuildmanage.area.name}" title="网格" url="/tree/ccmTree/treeDataArea?type=7&areaid=" cssClass="input-small" allowClear="true" notAllowSelectParent="true" /></li>
			<li class="first-line"><label class="title-text">小区名称：</label> <form:input path="name" htmlEscape="false" maxlength="512" class="input-medium" /></li>
			<li class="first-line"><label class="title-text">楼栋长姓名：</label> <form:input path="buildPname" htmlEscape="false" maxlength="64" class="input-medium" /></li>
		</ul>


	<div class="clearfix pull-right btn-box">
		<shiro:hasPermission name="house:ccmHouseBuildmanage:edit">
			<!-- <input id="btnImport" class="btn btn-primary" type="button" value="导入" />
			<input id="btnExport" class="btn btn-primary" type="button" value="导出" /> -->
			<!-- 					<a href="javascript:;" id="btnImport" class="btn  btn-export ">
			<i class=" icon-share-alt "></i> 导入
			</a> -->
			<a href="javascript:;" id="btnExport" class="btn btn-export" style="width: 49px;display:inline-block;float: right;">
				<i></i> <span style="font-size: 12px">导出</span>
			</a>
		</shiro:hasPermission>
		<!-- 添加该input的点击方法为 return page();-->
		<!-- <input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" onclick="return page();" /> -->
		<a href="javascript:;" id="btnSubmit" class="btn btn-primary" style="width: 49px;display:inline-block;float: right;"	 >
			<i></i><span style="font-size: 12px">查询</span></a>
	</div>
	</form:form>
	<sys:message content="${message}" />
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed table-gradient">
		<thead>
			<tr>
				<th>建筑物图片</th>
				<th>建筑物名称</th>
				<th>所属网格</th>
				<th>小区（单位）名称</th>
				<th>层数</th>
				<th>楼栋长</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="ccmHouseBuildmanage">
				<tr>
					<td width="150px"><img src="${ccmHouseBuildmanage.images}" class="pic-size pimg"/></td>
					<td><a href="${ctx}/house/ccmHouseBuildmanage/form?id=${ccmHouseBuildmanage.id}">${ccmHouseBuildmanage.buildname} </a></td>
					<td>${ccmHouseBuildmanage.area.name}</td>
					<td>${ccmHouseBuildmanage.name}</td>
					<td>${ccmHouseBuildmanage.pilesNum}</td>
					<td>${ccmHouseBuildmanage.buildPname}</td>
					<td><shiro:hasPermission name="house:ccmHouseBuildmanage:edit">
						<a class="btnList" href="${ctx}/house/ccmHouseBuildmanage/form?id=${ccmHouseBuildmanage.id}" title="修改"><i class="icon-pencil"></i></a>
						<a class="btnList" href="${ctx}/house/ccmHouseBuildmanage/delete?id=${ccmHouseBuildmanage.id}" onclick="return confirmx('确认要删除该建筑物吗？', this.href)" title="删除"><i class="icon-remove-sign"></i></a>
						<a class="btnList" href="${ctx}/pop/ccmPopTenant/listBuild?buildingId.id=${ccmHouseBuildmanage.id}&area.id=${ccmHouseBuildmanage.area.id}" title="住房管理"><i class="iconfont icon-ai-home"></i></a>
						</shiro:hasPermission> <shiro:hasPermission name="log:ccmLogTail:edit">
							<a class="btnList" onclick="parent.LayerDialog('${ctx}/log/ccmLogTail/list?relevance_id=${ccmHouseBuildmanage.id}&relevance_table=ccm_house_buildmanage', '记录信息', '800px', '660px')" 
								  title="记录信息"><i class="icon-print" style="color: cornflowerblue;"></i></a>
							<a class="btnList" onclick="parent.LayerDialog('${ctx}/log/ccmLogTail/formPro?relevance_id=${ccmHouseBuildmanage.id}&relevance_table=ccm_house_buildmanage', '添加记录', '800px', '660px')" title="添加记录"><i class="icon-plus"></i></a>
						</shiro:hasPermission>
						<a class="btnlist" href="javascript:;" title="房间" onclick="house('${ccmHouseBuildmanage.id}','${ccmHouseBuildmanage.area.id}')"><i class="icon-reorder"></i></a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
		<div id="outerdiv" style="position:fixed;top:0;left:0;background:rgba(0,0,0,0.7);z-index:2;width:100%;height:100%;display:none;">
			<div id="innerdiv" style="position:absolute;">
				<img id="bigimg" style="border:5px solid #fff;" src="" />
			</div>
		</div>
	<div class="pagination" style="float: right; margin-top: 12px">${page}</div>
	<!-- <div id="home" style="display: none">
		
	<div class="homeRight" style="width: 1000px; height: auto;float: left">
		<div class="addFloor" onclick="addFloorTop()">
			<i class="icon iconfont icon-iconjia"></i>
		</div>
		<div class="build">
			<div class="unit unit-1">
				<div class="unit-top">
					<div class="floor">
						<table>
							<tr>
								<td>
									<div class="floor-add" onclick="floorAdd(this)">
										<i class="icon iconfont icon-add"></i>
									</div>
								</td>
							</tr>
						</table>
					</div>

				</div>
				<div class="unit-num">
					<table>
						<td><span>1单元</span></td>
					</table>
					<div class="addFloor addUnit" onclick="addUnit()">
						<i class="icon iconfont icon-iconjia"></i>
					</div>
				</div>
			</div>

			<div class="unit-botom">
				<div class="floor">
					<table>
						<tr>
							<td>
								<div class="floor-add" onclick="floorAdd(this)">
									<i class="icon iconfont icon-add"></i>
								</div>
							</td>
						</tr>
					</table>
				</div>
			</div>
			<div class="unit-num unit-botom-num">
				<table>
					<td><span>地基</span></td>
				</table>
				<div class="addFloor addFloorBottom" onclick="addFloorBottom()">
					<i class="icon iconfont icon-iconjia"></i>
				</div>
			</div>
		</div>
	</div> -->
</div>
</body>
</html>