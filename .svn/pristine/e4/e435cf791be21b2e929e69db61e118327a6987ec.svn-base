<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>

	<head>
		<meta name="decorator" content="default" />
		<link rel="stylesheet" href="${ctxStatic}/statis/css/bootstrap.css">
		<link rel="stylesheet" type="text/css" href="${ctxStatic}/statis/css/reset.css" />
		<link rel="stylesheet" type="text/css" href="${ctxStatic}/statis/css/style.css" />
		<link rel="stylesheet" type="text/css" href="${ctxStatic}/statis/css/ol.css">
		<link rel="stylesheet" type="text/css" href="${ctxStatic}/statis/css/map.css">
		<link rel="stylesheet" type="text/css" href="${ctxStatic}/statis/css/progress.css" />
		<link rel="stylesheet" type="text/css" href="${ctxStatic}/statis/css/index.css" />
		<link rel="stylesheet" type="text/css" href="${ctxStatic}/common/jeesite.css" />
		<style type="text/css">
			.tdtitle {
				font-weight: bolder;
				width: 100px;
			}
			
			#contentTable {
				max-width: 500;
			}
			
			#menu a {
				height: 50px;
				width: 50px;
				display: block;
				background: rgba(0, 0, 0, 0.6);
				margin-bottom: 20px;
				border-radius: 50%;
				padding-top: 8px;
				cursor: pointer;
			}
			#menu a.active {
				background: rgba(18, 48, 160, 0.6);
			}
			#menu img {
				width: 35px;
				height: 35px;
			}
			
			#menu p {
				color: #fff;
				font-size: 16px;
			}
			/* Border styles */
			
			#popup-content thead,
			#popup-content tr {
				border-top-width: 1px;
				border-top-style: solid;
				border-top-color: rgb(168, 191, 222);
			}
			
			#popup-content {
				border-bottom-width: 1px;
				border-bottom-style: solid;
				border-bottom-color: rgb(168, 191, 222);
			}
			/* Padding and font style */
			
			#popup-content td,
			#popup-content th {
				padding: 5px 10px;
				font-size: 12px;
				font-family: Verdana;
				color: rgb(94, 125, 190);
			}
			/* Alternating background colors */
			
			#popup-content tr:nth-child(even) {
				background: rgb(211, 223, 237)
			}
			
			#popup-content tr:nth-child(odd) {
				background: #FFF
			}
			
			.ol-popup table td strong {
				color: #eea807;
			}
			
			.ol-popup {
				height: 200px;
				padding: 0px 15px 15px 15px;
			}
			
			.jbox-title-panel {
				height: 36px;
				line-height: 36px;
				color: #CD333B;
			}
			
			.jbox-title-panel img {
				height: 24px;
				width: 24px;
				float: left;
				margin-top: 5px;
			}
			
			.mapcountBox {
				position: absolute;
				width: auto;
				height: 34px;
				overflow: hidden;
				float: left;
				background-color: #eee;
				z-index: 99;
				left: 400px;
				top: 15px;
			}
			
			.mamcountitem {
				width: auto;
				height: auto;
			}
			
			.fl {
				float: left;
			}
			
			.clable {
				width: auto;
				height: auto;
				line-height: 34px;
				font-weight: bold;
				padding: 0 0 0 15px;
			}
			
			.t_num {
				display: inline-block;
				line-height: 13px;
				margin: 2px 4px 0 4px;
			}
			
			/* .t_num i {
				width: 30px;
				height: 30px;
				display: inline-block;
				background: #FFBF00;
				background-position: 0 0;
				line-height: 30px;
				text-align: center;
				font-size: 18px;
				margin-right: 1px;
				overflow: hidden;
				color: #333;
			} */
			
			.clablegray {
				color: #999;
				padding-left: 0px !important;
				padding-right: 10px;
			}
			
			.clear {
				clear: both;
			}
			
			.splitline {
				width: 1px;
				height: 100%;
				border-left: 1px solid #ccc;
				float: left;
				margin-left: 7px;
				margin-right: 7px;
			}
		</style>
		<title></title>
	</head>

	<body>
		<div id="main">
			<div id="ctx" class="hide" ctx="${ctx}"></div>
			<div id="ctxStatic" class="hide" ctx="${ctxStatic}"></div>
			<div class="container-fluid height90">
				<div class="content row height100" style="height: 950px">
					<div class="col-xs-12 height100 Bordereffect" style="overflow: hidden; padding-left: 5px; padding-bottom: 5px; height: 102.5%; margin-top: 7px;">
						<div style="position:absolute;z-index:1;margin-left:15px;margin-top:15px;">
							<div class="controls" style="height:35px" style="display:none" id="departTree">
								<sys:treeselect id="depart" name="depart" value="" labelName="" labelValue="请选择党支部" title="部门" url="/sys/office/treeData?type=2" cssClass="" allowClear="true" cssStyle="height:34px" />
							</div>
							<div class="controls" style="display:none" id="menTree">
								<sys:treeselect id="men" name="men" value="" labelName="" labelValue="请选择学员" title="学员" url="/sys/pbsOffice/treeData?type=4" cssClass="" allowClear="true" cssStyle="height:34px" notAllowSelectParent="true" />
							</div>
						</div>
						<div id="mapcountBox" class="mapcountBox">
							<div class="mamcountitem fl">
								<div class="fl clable">
									活动
								</div>
								<div class="fl clable" >
									<span class="t_num t_num1" id="activityNum">
										<!-- <i>1</i>
										<i>0</i>
										<i>0</i> -->
									</span>
								</div>
								<div class="fl clable clablegray">
									次
								</div>
								<div class="clear"></div>
							</div>
							<div class="splitline"></div>
							<div class="mamcountitem fl">
								<div class="fl clable">
									部门
								</div>
								<div class="fl clable">
									<span class="t_num t_num1"  id="departNum">
										<!-- <i>1</i>
										<i>0</i>
										<i>0</i> -->
									</span>
								</div>
								<div class="fl clable clablegray">
									个
								</div>
								<div class="clear"></div>
							</div>
							<div class="splitline"></div>
							<div class="mamcountitem fl">
								<div class="fl clable">
									学员
								</div>
								<div class="fl clable">
									<span class="t_num t_num1" id="menNum">
										<!-- <i>1</i>
										<i>0</i>
										<i>0</i> -->
									</span>
								</div>
								<div class="fl clable clablegray">
									人
								</div>
								<div class="clear"></div>
							</div>
							<div class="splitline"></div>
							<div class="mamcountitem fl">
								<div class="fl clable">
									工作
								</div>
								<div class="fl clable">
									<span class="t_num t_num1" id="workNum">
										<!-- <i>1</i>
										<i>0</i>
										<i>0</i> -->
									</span>
								</div>
								<div class="fl clable clablegray">
									件
								</div>
								<div class="clear"></div>
							</div>
						</div>
						<div id="menu" style="width:7%;height:100%;position:absolute;z-index:1;
					 text-align:center;margin-left:95%;opacity:0.8;margin-top: 15%">
							<a onclick="getPoint(2)">
								<img src="${ctxStatic}/statis/img/memPosition.jpg" title="学员信息">
								<!--<p>学员信息</p>-->
							</a>

							<a onclick="getPoint(1)">
								<img src="${ctxStatic}/statis/img/departPosition.jpg" title="部门信息">
								<!--<p>部门信息</p>-->
							</a>

							<a onclick="getPoint(3)">
								<img src="${ctxStatic}/statis/img/activityPosition.jpg" title="活动信息">
								<!--<p>活动信息</p>-->
							</a>

							<a onclick="getPoint(4)">
								<img alt="" src="${ctxStatic}/statis/img/workPosition.jpg" title="工作信息">
								<!--<p>工作信息</p>-->
							</a>
						</div>
						<div id="map" class="map"></div>
						<div id="popup" class="ol-popup  bb">
							<div class="">

								<div class="jbox-title-panel">
									<img src="${ctxStatic}/statis/images/dang.png">
									<div class="jbox-title">信息</div>
									<a href="#" id="popup-closer" class="ol-popup-closer  icon-remove " title="关闭" onmouseover="$(this).addClass('jbox-close-hover');" onmouseout="$(this).removeClass('jbox-close-hover');" style="text-decoration:none; position: absolute; cursor: pointer; top: 4px; right: 11px; width: 15px; height: 15px;"></a>
								</div>
								<div class="jbox-state">
									<div id="popup-content"></div>
								</div>
							</div>
							<div id="detail" onclick="clickPbsmemDetail()" style="color:#0077DB;float:right;margin-top: 15px">参与 》</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<%-- <script type="text/javascript"
		src="${ctxStatic}/statis/js/jquery-2.2.4.min.js"></script>
	<script charset="utf-8" src="http://map.qq.com/api/js?v=2.exp"></script> --%>
		<%-- <script src="${ctxStatic}/statis/js/GeographicalStatic.js"></script> --%>
		<script>
			ctxStatic = '${ctxStatic}'
			ctx = '${ctx}'
			
		</script>
		<script type="text/javascript" src="${ctxStatic}/statis/js/jquery-2.2.4.min.js"></script>
		<script type="text/javascript" src="${ctxStatic}/statis/js/ol.js"></script>
		<script type="text/javascript" src="${ctxStatic}/statis/js/commonMap.js"></script>
		<script type="text/javascript" src="${ctxStatic}/echarts/echarts.common.min.js"></script>
		<script type="text/javascript" src="${ctxStatic}/statis/js/Map.js"></script>
        <script>
        	$("#menu a").click(function(){
        		$("#menu a").removeClass('active');
        		$(this).addClass('active');
        	});
        </script>
	</body>

</html>