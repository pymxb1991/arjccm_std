<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>实时态势</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	var ctx = '${ctx}', ctxStatic = '${ctxStatic}';
    </script>
<link rel="stylesheet" href="${ctxStatic}/ol/ol.css" type="text/css">
<link rel="stylesheet" href="${ctxStatic}/modules/map/js/draw/css/p-ol3.min.css" type="text/css">
<link rel="stylesheet" href="${ctxStatic}/modules/map/css/mapFlat.css" type="text/css">
<link rel="stylesheet" href="${ctxStatic}/flat/realtimeSituation/css/realtimeSituation.css" type="text/css">
<script src="${ctxStatic}/ol/ol.js" type="text/javascript"></script>
<script src="${ctxStatic}/modules/map/js/mapconfig.js" type="text/javascript"></script>
<script src="${ctxStatic}/modules/map/js/commonMapFlat.js" type="text/javascript"></script>
<script src="${ctxStatic}/common/common.js" type="text/javascript"></script>
<script src="${ctxStatic}/common/alarm.js" type="text/javascript"></script>
<script src="${ctxStatic}/flat/realtimeSituation/realtimeSituation.js" type="text/javascript"></script>
</head>
<body>
<div id="content" class="row-fluid">
    <div id="right">
        <div id="map" class="map"></div>
        <div id="map-left" class="nav-common nav-common-left">
            <div class="">
                <div class="nav-common-header">
                	<h5>报警案件分析</h5>
           		</div>
                <div class="nav-common-center">
	            	<label style="color:#0687fd;margin-left:10px;margin-top:10px;">查询条件</label>
	              	<div style="margin: 5px;" class="query-criteria">
		              	<div style="margin: 10px;">
		                  	<div class="radio  radio-success" style="display: inline-block;">
		                  		<input type="radio" id="TodayRadio" value="1" name="time" checked="checked">
		                  		<label for="TodayRadio" ><span style="border: 1px solid #04c629;color:#04c629; padding: 2px;">今日</span></label>
		              		</div>
		              		<div class="radio  radio-success" style="display: inline-block;">
		                  		<input type="radio" id="WeekRadio" value="2" name="time" >
		                  		<label for="WeekRadio" ><span style="border: 1px solid #0687fd;color:#0687fd; padding: 2px;">本月</span></label>
		              		</div>
		                	<div class="radio  radio-success" style="display: inline-block;">
		                  		<input type="radio" id="YearRadio" value="3" name="time" >
		                  		<label for="YearRadio" ><span style="border: 1px solid #03d9d1;color:#03d9d1; padding: 2px;">本年</span></label>
		              		</div>
	                 	</div>
                 		<input name="beginOrEndTime" id="beginTime" type="text" readonly="readonly" maxlength="20" style="width: 110px;margin-bottom: 0; margin-left: 10px;" class="input-medium Wdate" value="" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false,onpicked:getTimeChange,maxDate:'#F{$dp.$D(\'endTime\')||\'%y-%M\'}'});">
						- 
						<input name="beginOrEndTime" id="endTime" type="text"  style="width: 110px;margin-bottom: 0;" readonly="readonly" maxlength="20" class="input-medium Wdate" value="" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false,onpicked:getTimeChange,minDate:'#F{$dp.$D(\'beginTime\')}',maxDate:'%y-%M'});">
	                	<div style="margin: 5px 5px 10px 10px;">
	               		<label>案件类型</label><br />
		                 	<div class="checkbox checkbox-success radio-div tab-content-bg" style="display: inline-block;margin-bottom: 5px;padding-top: 5px;padding-bottom: 5px;">
								<input type="checkbox" value="16" class="type-input" id="warninput" onchange="selectAll('warninput', 'warnCheckbox')" />
								<label	for="warninput" style="padding:0;width:80px;" >所有</label>
							</div>
							<div id="CaseTYpe" class="clearfix">
							
							</div>
						</div>	
	              	</div>
	               	<label style="color:#0687fd;margin-left:10px;">呈现方式</label>
	               	<div style="margin: 5px;" class="presentation-mode">
	                    <div style="margin: 10px;">
	                        <div class="radio  radio-success" style="display: inline-block;">
			                    <input type="radio" id="HeatRadio" value="1" name="chartType" checked="checked">
			                    <label for="HeatRadio">热力图</label>
			                </div>
			                <div class="radio  radio-success" style="display: inline-block;">
			                    <input type="radio" id="PolymerizationRadio" value="2" name="chartType" >
			                    <label for="PolymerizationRadio">聚合图</label>
			                </div>
			                <div class="radio  radio-success" style="display: inline-block;">
			                    <input type="radio" id="FourColorsRadio" value="3" name="chartType" >
			                    <label for="FourColorsRadio">四色图</label>
			                </div>
	                    </div>
	                    <div>
	                        <div style="margin:10px" class="clearfix">
	                            <div class="fourColorsLegend"><div class="color-bg color-bg1"></div><span id="FourColor1">0-100</span></div>
	                            <div class="fourColorsLegend"><div class="color-bg color-bg2"></div><span id="FourColor2">101-200</span></div>
	                            <div class="fourColorsLegend"><div class="color-bg color-bg3"></div><span id="FourColor3">201-300</span></div>
	                            <div class="fourColorsLegend"><div class="color-bg color-bg4"></div><span id="FourColor4">301-400</span></div>
	                        </div>
	                    </div>
	                </div>
	                <div style="margin:5px">
	                    <a id="okBtn" class="btn btn-primary" style="background: #0687fd !important;" href="javascript:;" onclick="okFun()"><i class="icon-plus"></i>生成</a>
	                    <a id="removeBtn" class="btn btn-primary" style="background: #f50b4b !important;" href="javascript:;" onclick="removeBtnFun()"><i class="icon-remove"></i>清除</a>
	                </div>
            	</div>
            </div>
        </div>
    </div>
    <!--<div id="map-right" class="nav-common nav-common-right">
        <div>
           <div class="nav-common-header">
             <h5>警情统计图表</h5>
         </div>
        </div>
     </div> -->
</div>
<script type="text/javascript">		
var leftWidth = 0; // 左侧窗口大小
var htmlObj = $("html"), mainObj = $("#main"),frameObj = $("#right"),headObj=$("#header");
function wSize(){
	var strs = getWindowSize().toString().split(",");
	frameObj.height(strs[0] - headObj.height());
	//var leftWidth = ($("#left").width() < 0 ? 0 : $("#left").width());
	frameObj.width($("#content").width()- leftWidth);
}
</script>
<script src="${ctxStatic}/common/wsize.min.js" type="text/javascript"></script>
</body>
</html>