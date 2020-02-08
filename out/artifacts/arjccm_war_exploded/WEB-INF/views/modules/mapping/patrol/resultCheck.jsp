<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<link rel="stylesheet" href="${ctxStatic}/ol/ol.css" type="text/css">
<link rel="stylesheet" href="${ctxStatic}/modules/map/css/map.css"
	type="text/css">
<script src="${ctxStatic}/ol/ol.js"></script>
<script src="${ctxStatic}/modules/map/js/mapconfig.js"></script>
<script src="${ctxStatic}/modules/map/js/commonMap.js"></script>
<script src="${ctxStatic}/modules/map/js/mapconfig.js"></script>
<style>
.checkPoint {
	height: 300px;
	overflow:hidden;
}

#FullBody {
	width: 100%;
	height: 100%;
}

#map {
	width: 100%;
	height: 100%;
	position: relative;
}

.map {
	overflow: auto;
}

#toolCenter {
	width: 57px;
}

.pop-list {
	position: absolute;
	width: 160px;
	max-height: 260px;
	height:auto;
	padding: 10px;
	left: 12px;
	top: 12px;
	z-index: 2001;
    background: #fff;
    border-radius: 4px;
    box-shadow: 1px 2px 1px rgba(0, 0, 0, .15);
    z-index: 9999999999;
    padding-left: 9px;
    overflow:hidden;
}
.type-line{
    width: 30px;
    height: 8px;
    background: yellow;
    margin-top: 5px;
}
.pop-center{
  overflow: auto;
  max-height: 230px;
  height:auto;
}
.pop-center label{
white-space: nowrap;
}
</style>
<div id="FullBody">
	<div id="map" class="map">
		<!--  工具栏-->
		<div id="tool">
			<div id="toolCenter">
				<div class="row-fluid tool-container">
					<div class="span12  tool-list" onclick="Map.fullScreen()"
						id="fullScreen">
						<i class="tool-icon tool-full"></i><span>全屏</span>
					</div>
				</div>
			</div>
		</div>
		<!--  工具栏-->
		<!-- 人员列表 -->
		<div id="Pop-List" class="pop-list">
			<div class="all-check">
				<div class="row-fluid ">
					<div class="span12 ">
					<div class="checkbox checkbox-success ">
								<input type="checkbox" checked value=" " class="type-input styled " name="popname"  id="allcheck" />
		                        <label for="allcheck">全选</label>
		                    </div>
					</div>
				</div>
			</div>
			<div class="pop-center">
			  <div  id="poplist"></div>
			</div>
		</div>

	</div>
	<!-- 人员列表 -->
</div>
</div>
<script type="text/javascript"
	src="${ctxStatic}/ccm/patrol/js/resultCheck.js"></script>
