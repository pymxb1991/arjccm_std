<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/include/head.jsp"%>
<html style=" width: 100%;height: 100%;overflow: hidden">
<head>
	<title>安全可视化呈现</title>
<link rel="stylesheet" href="${ctxStatic}/ol/ol.css" type="text/css">
<link rel="stylesheet" href="${ctxStatic}/modules/map/css/map.css" type="text/css">
<link rel="stylesheet" href="${ctxStatic}/modules/map/css/house.css" type="text/css">
<link href="${ctxStatic}/form/css/form.css" rel="stylesheet" />
<link rel="stylesheet" href="${ctxStatic}/modules/map/js/draw/css/defaults.css" type="text/css">
<style>
	.map{
		background: transparent;
	}
</style>
</head>
<body onload="init()">
	<div id="map" class="map">
	<!--工具栏  -->
	<div id="tool">
		<div id="toolCenter">
		  	<div class="row-fluid tool-container">
			  <div class="span12  tool-list"  id="" style="margin-top: 12px;">
				<div class="checkbox checkbox-success radio-div">
					<input type="checkbox" value="9" class="type-input" name="repairId" id="chuzu"  /> <label for="chuzu">出租房</label>
				</div>
			</div>
			</div>
		  	<div class="row-fluid tool-container">
		  	<div class="span12  tool-list"  id="" >
				<div class="checkbox checkbox-success radio-div">
					<input type="checkbox" value="9" class="type-input" name="repairId" id="zhian" /> <label for="zhian">治安重点场所</label>
				</div>
			</div>
			</div>
		  	<div class="row-fluid tool-container">	
			    <div class="span12 tool-list" id="">
					<div class="checkbox checkbox-success radio-div">
						<input type="checkbox" value="9" class="type-input" name="repairId" id="zhongdian" /> <label for="zhongdian">重点人员楼栋</label>
					</div>
				</div>
				<div>
			
				</div>
			</div>		
	</div>
</div>
<!--工具栏  -->
<div id="popup" class="ol-popup  bb" >
		<div class="">
			<a href="#" id="popup-closer" class="ol-popup-closer  icon-remove "
				title="关闭" onmouseover="$(this).addClass('jbox-close-hover');"
				onmouseout="$(this).removeClass('jbox-close-hover');"
				style="position: absolute; display: block; cursor: pointer; top: 4px; right: 11px; width: 15px; height: 15px;color:#fff" ></a>
			<div class="jbox-title-panel" style="height: 25px;">
				<div class="jbox-title">信息</div>
			</div>
			<div class="jbox-state">
				<div id="popup-content" style="padding: 8px 15px;"></div>
			</div>
		</div>
	</div>

	<input type="hidden" value="${ccmHouseSchoolrim.areaMap }" id="ccmHouseSchoolrimareaMap"/>
	<input type="hidden" value="${ccmHouseSchoolrim.areaPoint}" id="ccmHouseSchoolrimareaPoint"/>
	<input type="hidden" value="${ccmHouseSchoolrim.schoolName}" id="ccmHouseSchoolrimschoolName"/>
	<input type="hidden" value="${ccmHouseSchoolrim.schoolAdd}" id="ccmHouseSchoolrimschoolAdd"/>
	<input type="hidden" value="${ccmHouseSchoolrim.area}" id="ccmHouseSchoolrimarea"/>
	<input type="hidden" value="${ccmHouseSchoolrim.schoolNum}" id="ccmHouseSchoolrimschoolNum"/>
	<input type="hidden" value="${ccmHouseSchoolrim.schoolEducDepa}" id="ccmHouseSchoolrimschoolEducDepa"/>
<!-- 楼栋住户信息 -->
	<button type="button" data-toggle="modal" data-target="#myModal" id="buildBtn" style="display: none"></button>
	<div id="myModal" class="modal hide fade jbox" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="width: 1200px;margin-left: -48.2%; z-index: 9999999999;">
		<div class="jbox-container">
			<a href="#" id="popup-closer" class="ol-popup-closer jbox-close" title="关闭" onmouseover="$(this).addClass('jbox-close-hover');" onmouseout="$(this).removeClass('jbox-close-hover');" style="position: absolute; display: block; cursor: pointer; top: 4px; right: 11px; width: 15px; height: 15px;" data-dismiss="modal" aria-hidden="true"></a>
			<div class="jbox-title-panel" style="height: 25px;">
				<div class="jbox-title">信息</div>
			</div>
			<!-- 楼栋 -->
			<div class="jbox-state">
				<div id="popup-content1">
					<div class="modal-body" id="build-details" style="padding: 3px 0px 0 0;"></div>
					<!-- 房屋 -->
					<div class="modal-body" id="house-details" style="padding: 3px 15px;"></div>
					<!-- 人口 -->
					<div class="modal-body" id="pop-details"></div>
				</div>
				<div class="jbox-button-panel" style="height: 4px; padding: 0 15px 20px;text-align: right;" id="modal-footer">
					<button class="jbox-button" style="padding: 0px 10px 0px 10px;" data-dismiss="modal" aria-hidden="true">关闭</button>
				</div>
			</div>
		</div>

	</div>

</body>
<script src="${ctxStatic}/jquery/jquery-1.9.1.min.js"></script>
<link href="${ctxStatic}/jquery-jbox/2.3/Skins/Bootstrap/jbox.min.css" rel="stylesheet" />
<script src="${ctxStatic}/jquery-jbox/2.3/jquery.jBox-2.3.min.js" type="text/javascript"></script>
<script type="text/javascript">
	var ctx = '${ctx}', ctxStatic = '${ctxStatic}';
</script>
<script src="${ctxStatic}/layer-v3.1.1/layer/layer.js"></script>
<script src="${ctxStatic}/ol/ol.js"></script>
<script src="${ctxStatic}/modules/map/js/commonMap.js"></script>
<script src="${ctxStatic}/modules/map/js/mapconfig.js"></script>
<script src="${ctxStatic}/modules/map/js/draw/js/appSchool.js"></script>
<script src="${ctxStatic}/modules/map/js/mapBuildSpe.js"></script>
</html>