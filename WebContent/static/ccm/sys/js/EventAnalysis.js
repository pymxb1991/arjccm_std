//实时态势
//图例
var greenMax,yellowMin,yellowMax,orangeMin,orangeMax,redMin, greenMaxCache,yellowMinCache,yellowMaxCache,orangeMinCache,orangeMaxCache,redMinCache;
$(function(){
	//加载地图
	initMap();
	//案件类型
	getCaseType();
	//时间
	changeTime();
})
function initMap(){
	// 地图默认数据设置
	var defaultPrams = {
		id : 'map',
		centerCoordinate : centerCoordinate,
		zoom : zoomIndex,
		maxZoom : maxZoom,
		minZoom : minZoom,
		baseUrl : baseUrlT,
		zoomShowOrHide : false,// 缩小放大
		overviewMap : false,
		selectPointerFlag : true
	// 鹰眼图
	}
	Map = new ArjMap.Map(defaultPrams);
	// 加载地图
	Map.init();
	map=Map.map;
	//四色图例
	FourColorLegend()
}

//全选
function selectAll(id,name) {
    if ($("#"+id).is(":checked")) {//判断一个checkbox多选框是否选中
        $("input:checkbox[name="+name+"]").prop("checked", "checked");
        var allValue = queryCheckedValue(name);
    } else {
        $("input:checkbox[name="+name+"]").prop("checked", "");
        var str = noCheckedValue(name);
    }
}
//获取所有选中checkbox的值
function queryCheckedValue(name) {
    var str = "";
    $("input:checkbox[name="+name+"]:checked").each(function(i) {
        var val = $(this).val();
        str = str + val + "-";
    });
    return str;
}
//所有的name为‘selected’的checkbox的值
function noCheckedValue(name) {
    var str = "";
    $("input:checkbox[name="+name+"]").each(function(i) {
        var val = $(this).val();
        str = str + "-" + val;
    });
    return str;
}

//判断所有的子checkbox全部选中时，总checkbox选中，否则，反之；
function oneToAll(allId,name) {
    var allChecked = 0;//所有选中checkbox的数量
    var all = 0;//所有checkbox的数量
    $("input:checkbox[name="+name+"]").each(function(i) {
        all++;
        if ($(this).is(":checked")) {
            allChecked++;
        }
    });
    if(allChecked==all){//相等时，则所有的checkbox都选中了，否则，反之；
        $("#"+allId).prop("checked",true);
    }else{
        $("#"+allId).prop("checked",false);
    }
}
//案件类型
function getCaseType(){
	$.getJSON(ctx+'/sys/dict/listData?type=ccm_event_kind_parent',function(data){
		var len=data.length;
		if(len>0){
			var html='';
			for(var i=0;i<len;i++){
				html+='<div class="checkbox checkbox-success radio-div tab-content-bg" style="padding-top: 5px;padding-bottom: 5px;" title="'+data[i].label+'">';  
				html+='<input id="'+data[i].id+'" type="checkbox" value="'+data[i].value+'" class="type-input" name="warnCheckbox"    onchange="oneToAll(\'warninput\',\'warnCheckbox\')" />';  
				html+='<label for="'+data[i].id+'" style="padding:0;">'+data[i].label+'</label>';
				html+='</div>'; 
			}
			$('#CaseTYpe').html(html);
		}
	})
}
//今日 本月 本年
function changeTime(){
	var time=today();
	$('#beginTime').val(time);
	$('#endTime').val(time);
	$('input[type="radio"][name="time"]').change(function() {
		if($(this).val()==1){//今日
			var time=today();
			$('#beginTime').val(time);
			$('#endTime').val(time);
			FourColorLegendChange();
		}else if($(this).val()==2){//本周
			var beginTime=GetThisMonthStartDate();
			var endTime=today();
			$('#beginTime').val(beginTime);
			$('#endTime').val(endTime);
			FourColorLegendChange();
		}else{
			var beginTime=getYear();
			var endTime=today();
			$('#beginTime').val(beginTime);
			$('#endTime').val(endTime);
			FourColorLegendChange();
		}
	})
}
//时间input改变事件
function getTimeChange(){
	FourColorLegendChange();
}
//改变图例
function FourColorLegendChange(){
	var beginTime= $('#beginTime').val();
	var endTime=$('#endTime').val();
	//计算相差几天(包含今天)
	var DateDiff=DateDiffFun(beginTime,endTime)+1;
	greenMaxCache=greenMax*DateDiff;
	yellowMinCache=yellowMin*DateDiff;
	yellowMaxCache=yellowMax*DateDiff;
	orangeMinCache=orangeMin*DateDiff;
	orangeMaxCache=orangeMax*DateDiff;
	redMinCache=redMin*DateDiff;
	$('#FourColor1').html('0-'+greenMaxCache)
	$('#FourColor2').html(yellowMinCache+'-'+yellowMaxCache)
	$('#FourColor3').html(orangeMinCache+'-'+orangeMaxCache)
	$('#FourColor4').html('>='+redMinCache)
}
//生成
function okFun(){
	var beginTime=$('#beginTime').val()+' 00:00:00';
	var endTime=$('#endTime').val()+' 23:59:59';
	var id='';
	$('input[name="warnCheckbox"]:checked').each(function(){
		id+=$(this).val()+',';
	})
	id=id.substring(0,id.length-1);
	if(id==''){
       $.jBox.tip('请选择案件类型！');
       return ;
	}
	var type=$('input[name="chartType"]:checked').val();
	if(type=='1'){//热力
		$.getJSON(ctx+'/event/ccmEventIncident/queryAlarmInfoByDateAndAlarmType?beginHappenDate='+beginTime+'&endHappenDate='+endTime+'&eventKind='+id+'',function(data){
            if(data==null||data==""||data==undefined){
            	 $.jBox.tip('暂无数据！');
				 Map.removeLayer('fourColorLayer');
				 Map.removeLayer('heatMap');
				 Map.removeLayer('clusterLayer');
                 return ;
             }
			Map.removeLayer('heatMap');
			Map.removeLayer('clusterLayer');
			Map.removeLayer('fourColorLayer');
			Map.heatMap1(data);
			Map.layersIsShow('heatMap', true);
			Map.goTo([Number(data.centpoint[0]),Number(data.centpoint[1])]);
		})
	}else if(type=='2'){//聚合
		$.getJSON(ctx+'/event/ccmEventIncident/queryAlarmInfoByDateAndAlarmType?beginHappenDate='+beginTime+'&endHappenDate='+endTime+'&eventKind='+id+'',function(data){
			 if(data==null||data==""||data==undefined){
            	 $.jBox.tip('暂无数据！');
				 Map.removeLayer('fourColorLayer');
				 Map.removeLayer('heatMap');
				 Map.removeLayer('clusterLayer');
                 return ;
             }
			Map.removeLayer('heatMap');
			Map.removeLayer('clusterLayer');
			Map.removeLayer('fourColorLayer');
			Map.ClusterMap(data);
			Map.goTo([Number(data.centpoint[0]),Number(data.centpoint[1])]);
		})	
	}else{//四色
		$.getJSON(ctx+'/event/ccmEventIncident/queryAlarmInfoByDateAndAlarmTypeToFourColor?beginHappenDate='+beginTime+'&endHappenDate='+endTime+'&eventKind='+id+'',function(data){
			if(data==null||data==""||data==undefined){
            	 $.jBox.tip('暂无数据！');
				 Map.removeLayer('fourColorLayer');
				 Map.removeLayer('heatMap');
				 Map.removeLayer('clusterLayer');
                 return ;
             }
			if(data.centpoint == undefined){
				Map.removeLayer('fourColorLayer');
				Map.removeLayer('heatMap');
				Map.removeLayer('clusterLayer');
				$.jBox.tip('暂无标注！');
				return ;
			}
			var colorLevel={
				'greenMax':greenMaxCache,
				'yellowMin':yellowMinCache,
				'yellowMax':yellowMaxCache,
				'orangeMin':orangeMinCache,
				'orangeMax':orangeMaxCache,
				'redMin':redMinCache
			};
            Map.removeLayer('fourColorLayer');
			Map.removeLayer('heatMap');
			Map.removeLayer('clusterLayer');
            Map.FourColorMap(data,colorLevel);
        	Map.goTo([Number(data.centpoint[0]),Number(data.centpoint[1])]);//定位
		})	
	}
}
//清除
function removeBtnFun(){
	Map.removeLayer('heatMap');
	Map.removeLayer('clusterLayer');
	Map.removeLayer('fourColorLayer');
}
//四色图例
function FourColorLegend(){
	$.getJSON(ctx+'/sys/sysConfig/getAlarmColorLevelConfig',function(data){
		greenMaxCache=greenMax=data.green;
		yellowMinCache=yellowMin=data.yellowMin;
		yellowMaxCache=yellowMax=data.yellowMax;
		orangeMinCache=orangeMin=data.orangeMin;
		orangeMaxCache=orangeMax=data.orangeMax;
		redMinCache=redMin=data.red;
		$('#FourColor1').html('0-'+greenMax);
		$('#FourColor2').html(yellowMin+'-'+yellowMax);
		$('#FourColor3').html(orangeMin+'-'+orangeMax);
		$('#FourColor4').html('>='+redMin);
	})
}