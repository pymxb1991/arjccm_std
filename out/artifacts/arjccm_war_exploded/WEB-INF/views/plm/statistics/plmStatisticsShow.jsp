<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title></title>
<script src="${ctxStatic}/jquery/jquery-1.8.3.min.js" type="text/javascript"></script>
<link href="${ctxStatic}/bootstrap/2.3.1/css_${not empty cookie.theme.value ? cookie.theme.value : 'black'}/bootstrap.min.css" type="text/css" rel="stylesheet" />
<link href="${ctxStatic}/bootstrap/2.3.1/css_${not empty cookie.theme.value ? cookie.theme.value : 'black'}/custom.css" type="text/css" rel="stylesheet" />
<script src="${ctxStatic}/bootstrap/2.3.1/js/bootstrap.min.js"></script> 
<link href="${ctxStatic}/jquery-jbox/2.3/Skins/Bootstrap/jbox.min.css" rel="stylesheet" />
<script src="${ctxStatic}/jquery-jbox/2.3/jqueryc.jBox-2.3.min.js" type="text/javascript"></script>
<link rel="stylesheet" href="${ctxStatic}/bootstrap/2.3.1/awesome/font-awesome.min.css">
<!--[if lte IE 7]>
    <link rel="stylesheet" href="../bootstrap/2.3.1/awesome/font-awesome-ie7.css">
    <![endif]-->
<!--[if lte IE 6]>
    <link rel="stylesheet" href="../bootstrap/bsie/css/bootstrap-ie6.min.css">
    <script src="../bootstrap/bsie/js/bootstrap-ie.min.js"></script>
    <![endif]-->
<link rel="stylesheet" href="${ctxStatic}/asidenav/asidenav.css">
<script src="${ctxStatic}/asidenav/asidenav.js"></script>
<script src="${ctxStatic}/asidenav/jquery.min.js"></script>
<link rel="stylesheet" href="${ctxStatic}/common/index/css/indexCommonc.css">
<link rel="stylesheet" href="${ctxStatic}/common/index/css/indexc.css">
<link rel="stylesheet" href="${ctxStatic}/common/index/css/indexStatisc.css">
<link rel="stylesheet" href="${ctxStatic}/common/index/pageSwitching/css/animations.css">
<link rel="stylesheet" href="${ctxStatic}/common/index/pageSwitching/css/component.css">
<link rel="stylesheet" href="${ctxStatic}/common/index/pageSwitching/css/multilevelmenu.css">
<%-- <link rel="stylesheet" href="${ctxStatic}/common/index/pageSwitching/css/default.css"> --%>
<script src="${ctxStatic}/common/index/Scripts/js/echarts.min.js"></script>
<script src="${ctxStatic}/common/index/Scripts/js/shine.js"></script>
<script src="${ctxStatic}/common/index/Scripts/js/scroll.js"></script>
<script src="${ctxStatic}/common/index/Scripts/js/indexCommonc.js"></script>
<script src="${ctxStatic}/common/index/Scripts/js/indexc.js"></script>
<script src="${ctxStatic}/common/index/pageSwitching/js/jquery.dlmenu.js"></script>
<script type="text/javascript">
	$(function() {
		$('#ratioEquipmentByType').height($('#ratioEquipmentByType').parent().height() - 55);
		$('#countEquipmentByStorage').height($('#ratioEquipmentByType').parent().height() - 55);
		$('#numByVtype').height($('#numByVtype').parent().height() - 45);
	});
</script>
</head>
<body style="background-color:#000">
	<div class="container-fluid " style="height: 100%; overflow: hidden" id="mainstatis">
		<div class="context" content="${ctx}" style="display: none"></div>
		<div id="pt-main" class="pt-perspective">
			<div class="pt-page pt-page-0">
				<%-- <div class="row-fluid header">
					<div class="span1" style="position: relative; margin-top: 5px;">
						<!-- 菜单 -->
						<div style="z-index: 9999; display: block; position: absolute;">
							<svg width="0" height="0">
						        <defs>
						            <filter id="goo">
						                <fegaussianblur in="SourceGraphic" stdDeviation="10" result="blur"></fegaussianblur>
						                <fecolormatrix in="blur" mode="matrix" values="1 0 0 0 0  0 1 0 0 0  0 0 1 0 0  0 0 0 19 -9" result="goo"></fecolormatrix>
						                <fecomposite in="SourceGraphic" in2="goo" operator="atop"></fecomposite>
						            </filter>
						        </defs>
						    </svg>
							<div class="aside-nav bounceInUp animated" id="aside-nav">
								<a href="${ctx}/index" title="首页" class="aside-menu">首页</a>
							</div>
						</div>
					</div>
					<div class="span10 backgroundimg">
						<h5>${fns:getConfig('productName')}</h5>
					</div>
					<div class="span1">
						<div class="Logout">
							<span>
								<a href="${ctx}/logout">
									<i class="icon-off align-top bigger-125"></i>
									退出
								</a>
							</span>
						</div>
					</div>
				</div> --%>
				<!-- 日期时间 -->
<!-- 				<span class="datespan"></span>
				<span class="timespan"></span> -->
				<!--/ 日期时间 -->
				<!-- 箭头切换  -->
				<div id="jiantouright" class="jiantou scroll-down iterateleft">
					<span id="iterateleft">
						<i class="icon-angle-right"></i>
					</span>
				</div>
				<!--/ 箭头切换  -->
				<!--  第一页内容 ----------------------------------------------------------------------------------  -->
				<div class="row-fluid height100" style="margin-top: 20px;">
					<div class="bodydiv" style="width: 99%; margin-left: 1%;">
						<div class="span3 shadow height100">
							<div class="height55 show">
								<div class="common-header">
									<div>装备使用统计TOP10</div>
								</div>
								<div class="common-pading alarm-height">
									<div id="countEquipmentByStats" class="echarts  "></div>
								</div>
							</div>
							<div class="height45 show">
								<div class="common-header">
									<div>装备到期预警TOP10</div>
								</div>
								<div class="common-pading ">
									<!--  <div  id="warningEquipment" class="echarts span6"></div>
						<div  id="warningEquipment2" class="echarts span6"></div> -->
									<div id="warningEquipment" class="black-font">
										<div class="row-fluid alarm-row-fluid2" style="z-index: 9999">
											<div class="black-font">
												<ul class="echartsAlarm-tab clearfix shadow" style="color: #141414">
													<li id='dli' class="active">已过期装备</li>
													<li id='cli'>借用超期装备</li>
													<li id='jli'>即将过期装备</li>
												</ul>
											</div>
										</div>
										<div class="row-fluid alarm-row-fluid3">
											<div id="alarmTable" class="black-font" style="overflow: auto; height: 98%">
												<table id="dtable" class="black-font">
													<thead>
														<tr class="l-grid-row-alt">
															<td>序号</td>
															<td>装备名称</td>
															<td>装备数量</td>
															<td>过期天数</td>
														</tr>
													</thead>
													<tbody id="dwarningEquipment">
													</tbody>
												</table>
												<table id="jtable" class="black-font" style="display: none;">
													<thead>
														<tr class="l-grid-row-alt">
															<td>序号</td>
															<td>装备名称</td>
															<td>装备数量</td>
															<td>即过期天数</td>
														</tr>
													</thead>
													<tbody id="jwarningEquipment">
													</tbody>
												</table>
												<table id="ctable" class="black-font" style="display: none; ">
													<thead>
														<tr class="l-grid-row-alt">
															<td>序号</td>
															<td>装备名称</td>
															<td>装备数量</td>
															<td>超期天数</td>
														</tr>
													</thead>
													<tbody id="cwarningEquipment">
													</tbody>
												</table>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="span6 shadow height100">
							<div class="height55 show">
								<div class="common-header">
									<div>电子流一览</div>
								</div>
								<div class="common-pading height90">
									<div id="countActByStatus" class="black-countfont"></div>
									<div class="height66">
										<!--  <div id="countActByStatusType" class=" echarts height50"></div> -->
										<div id="countActByDay" class="span7 echarts "></div>
										<div style="float: left; width: 1px; height: 80%; background: #34485A;"></div>
										<div id="countActByType" class="span5 "></div>
									</div>
								</div>
							</div>
							<div class="height45 show">
								<div class="common-header">
									<div>仓库数据一览</div>
									
								</div>
								<div class="common-pading height90">
									<div id="countStorage" class="black-countfont"></div>
									<br>
									<div id="ratioEquipmentByType" class="echarts span7" style="width: 57%"></div>
									<div style="float: left; width: 1px; height: 70%; background: #34485A;"></div>
									<div id="countEquipmentByStorage" class=" echarts span5"></div>
								</div>
							</div>
						</div>
						<div class="span3 shadow height100">
							<div class="height55 show">
								<div class="common-header ">
									<div>车辆分析</div>
								</div>
								<div class="common-pading height85">
									<div class="height60 ">
										<div id="countCar"></div>
										<div id="numByVtype" class=" echarts "></div>
									</div>
									<div style="float: left; margin-left: 10%; width: 80%; height: 1px; background: #34485A;"></div>
									<div class="height40 " style="margin-left: 3%;">
										<table style="width: 100%; height: 100%; margin-top: 18px;">
											<tbody>
												<tr>
													<td>
														<div class="row">
															<div class="span3">
																<div class="alarm-rotate" style="position: relative;">
																	<div class="alarm-rotate-div">
																		<div class="alarm-rotate-img alarm-rotate-img1"></div>
																		<div class="alarm-rotate-img alarm-rotate-img2"></div>
																		<div class="alarm-num common-color-common common-color1" id="kongxian">25</div>
																	</div>
																	<div class="countfont-car">空闲</div>
																</div>
															</div>
															<div class="span3">
																<div class="alarm-rotate " style="position: relative;">
																	<div class="alarm-rotate-div">
																		<div class="alarm-rotate-img alarm-rotate-img1"></div>
																		<div class="alarm-rotate-img alarm-rotate-img2"></div>
																		<div class="alarm-num common-color-common" id="lingyong">0</div>
																	</div>
																	<div class="countfont-car">领用</div>
																</div>
															</div>
															<div class="span3">
																<div class="alarm-rotate " style="position: relative;">
																	<div class="alarm-rotate-div">
																		<div class="alarm-rotate-img alarm-rotate-img1"></div>
																		<div class="alarm-rotate-img alarm-rotate-img2"></div>
																		<div class="alarm-num common-color-common common-color3" id="weixiu">0</div>
																	</div>
																	<div class="countfont-car">维修</div>
																</div>
															</div>
															<div class="span3">
																<div class="alarm-rotate " style="position: relative;">
																	<div class="alarm-rotate-div">
																		<div class="alarm-rotate-img alarm-rotate-img1"></div>
																		<div class="alarm-rotate-img alarm-rotate-img2"></div>
																		<div class="alarm-num common-color-common common-color2" id="baofei">0</div>
																	</div>
																	<div class="countfont-car">报废</div>
																</div>
															</div>
														</div>
													</td>
												</tr>
											</tbody>
										</table>
										<div id="countByStatusAjax" class="echarts"></div>
									</div>
								</div>
							</div>
							<div class="height45 show">
								<div class="common-header ">
									<div>车辆使用趋势</div>
								</div>
								<div class="common-pading height90">
									<div id="allNumByMonth" class="height50 echarts "></div>
									<div id="useNumAllByOfficeAjax" class="height50 echarts  "></div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!--  /第一页内容 ----------------------------------------------------------------------------------  -->
			</div>
			<c:forEach items="${listmap}" var="map" varStatus="i">
				<div class="pt-page pt-page-${i.count}">
					<!-- 日期时间 -->
					<!-- <span class="datespan"></span>
					<span class="timespan"></span> -->
					<!--/ 日期时间 -->
					<%-- <div class="row-fluid header">
						<div class="span1" style="position: relative; margin-top: 5px;">
							<!-- 菜单 -->
							<div style="z-index: 9999; display: block; position: absolute;">
								<svg width="0" height="0">
							        <defs>
							            <filter id="goo">
							                <fegaussianblur in="SourceGraphic" stdDeviation="10" result="blur"></fegaussianblur>
							                <fecolormatrix in="blur" mode="matrix" values="1 0 0 0 0  0 1 0 0 0  0 0 1 0 0  0 0 0 19 -9" result="goo"></fecolormatrix>
							                <fecomposite in="SourceGraphic" in2="goo" operator="atop"></fecomposite>
							            </filter>
							        </defs>
							    </svg>
								<div class="aside-nav bounceInUp animated" id="aside-nav">
									<a href="${ctx}/index" title="首页" class="aside-menu">首页</a>
								</div>
							</div>
						</div>
						<div class="span10 backgroundimg">
							<h5>${fns:getConfig('productName')}</h5>
						</div>
						<div class="span1">
							<div class="Logout">
								<span>
									<a href="${ctx}/logout">
										<i class="icon-off align-top bigger-125"></i>
										退出
									</a>
								</span>
							</div>
						</div>
					</div> --%>
					<div class="bodydiv" style="position: relative; width: 100%;">
						<!-- 箭头切换  -->
						<div id="jiantouleft" class="jiantou scroll-down iterateright">
							<span id="iterateright">
								<i class="icon-angle-left"></i>
							</span>
						</div>
						<div id="jiantouright" class="jiantou scroll-down iterateleft"
							<c:if test="${i.last}" var="e">style="display: none;" </c:if>>
							<span id="iterateleft">
								<i class="icon-angle-right"></i>
							</span>
						</div>
						<!--/ 箭头切换  -->
						<script type="text/javascript">
							var bjckid = "-1";//选中窗口id
						</script>
						<!-- 门户首页展示 -->
						<c:forEach var="portlet" items="${map.portletlist}">
							<div id="${portlet.id}" class="portletcolumn shadow "
								longItude="${portlet.longItude}" latItude="${portlet.latItude}"
								style="height:${(fn:length(portlet.longItude)*33)}%;width:${fn:length(portlet.latItude)*93/portlet.type+(fn:length(portlet.latItude)-1)*1}%; top:${(fn:substring(portlet.longItude, 0, 1)-1)*33}%; left:${(fn:substring(portlet.latItude, 0, 1)-1)*(93/portlet.type+1)+(8-portlet.type)/2}%;z-index:${portlet.longItude*portlet.latItude};">
								<c:if test="${portlet.title!=null&&not empty  portlet.title}">
									<div class="portlettitle">
										<span class="titlespan top-header">${portlet.title}</span>
									</div>
								</c:if>
								<div id="portletcontent${portlet.id}" class="portletcontent">
									<div>正在加载...</div>
								</div>
							</div>
							<script type="text/javascript">
								//加载窗口内容信息
								$(function() {
									var porwidth = $("#${portlet.id}").width() - 10;
									var porheigh = $("#${portlet.id}").height() - 10;
									$.ajax({
										url : '${ctx}${portlet.content}',
										type : 'post',
										data : {
											"height" : porheigh,
											"width" : porwidth,
											"content" : "${portlet.content}",
											"sid" : "${portlet.id}"
										},
										dataType : 'html',
										error : function() {
											$("#portletcontent${portlet.id}").html("加载错误")
										},
										success : function(data) {
											$("#portletcontent${portlet.id}").html(data);
										}
									});
								});
							</script>
						</c:forEach>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
	<script src="${ctxStatic}/common/index/pageSwitching/js/modernizr.custom.js"></script>
	<script src="${ctxStatic}/common/index/pageSwitching/js/pagetransitions.js"></script>
	<script type="text/javascript">
		$(function() {
			$("#right", parent.document).css("padding", "0px")
		})
	</script>
</body>
</html>