var Map,currPage=1,pageSize =10; //每页出现数量;
var parentId='',placeTypeObj={},placeImportantObj={};
$(function(){
	//加载地图
	initMap();
	$('#btnSubmitQueryCollect').click(function(){
		parentId='';
		QueryCollect();
	});
	$('#ClearSubmit').click(function(){
		parentId='';
		$('#FourColor1').html('>=301');
		$('#FourColor2').html('201-300');
		$('#FourColor3').html('101-200');
		$('#FourColor4').html('0-100');
		 Map.removeLayer('DataAnalysisLayer');
		 $('#pType').val('1');
		 $('#IncidentList').html('');
	})
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
}


function QueryCollect(){
	var pType=$('#pType').val();
	$.getJSON(ctx+'/sys/map/queryCollectStat?queryType=' + pType + '&parentId='+parentId,function(data) {
		if(data==null||data==""||data==undefined){
       	 $.jBox.tip('暂无数据！');
            return ;
        };
	
		var numArr=[];
		var featuresData=data.features;
		var lendData=[];
		var echartsData=[];
		for(var i in featuresData){
			lendData.push(featuresData[i].properties.name);
			echartsData.push(featuresData[i].properties.info['数量']);
		}
		AreaEcharts(lendData,echartsData);
		var maxN=maxNum(echartsData);
		var greenMax=Math.ceil(maxN/4)   
			var yellowMin=Number(greenMax)+1;
			var yellowMax=greenMax*2;
			var orangeMin=Number(yellowMax)+1;
			var orangeMax=greenMax*3;
			var redMin=Number(orangeMax)+1;
		if(maxN!=''){
			$('#FourColor1').html('>='+redMin);
			$('#FourColor2').html(''+orangeMin+'-'+orangeMax+'');
			$('#FourColor3').html(''+yellowMin+'-'+yellowMax+'');
			$('#FourColor4').html('0-'+greenMax);
		}
      
	
		var colorLevel={
				'greenMax':greenMax,
				'yellowMin':yellowMin,
				'yellowMax':yellowMax,
				'orangeMin':orangeMin,
				'orangeMax':orangeMax,
				'redMin':redMin
			};

            Map.removeLayer('DataAnalysisLayer');
            Map.DataAnalysisMap(data,colorLevel);
        	Map.goTo([Number(data.centpoint[0]),Number(data.centpoint[1])]);//定位
	})
}
//读取	sCookie
function getCookie(name)
{
	var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
	if(arr=document.cookie.match(reg))
		return unescape(arr[2]);
	else
		return null;
}
function AreaEcharts(lendData,echartsData){
	$('#IncidentList').html('<div class="datalist" id="AreaList"></dvi>')
	var theme = getCookie("theme");
	var lineStyle = {};
	if(theme=="black"){
		lineStyle = {color:'#FFF'};
	}
	var option = {
			color:['#4ccbff'],
		    tooltip : {
		        trigger: 'axis',
		        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
		            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
		        }
		    },
		    grid: {
		        left: '3%',
		        right: '5%',
		        bottom: '3%',
		        containLabel: true
		    },
		    xAxis:  {
		        type: 'value',
				axisLine:{
					lineStyle:lineStyle
				}
		    },
		    yAxis: {
		        type: 'category',
		        data: lendData,
				axisLine:{
					lineStyle: lineStyle
				}
		    },
		    series: [
		        {
		            name: '数据采集对比分析',
		            type: 'bar',
		            label: {
		                normal: {
		                    show: true,
		                    position: 'insideRight',
		                    color: '#000'
		                }
		            },
		            data:echartsData
		        }
		    ]
		};
	   // 实例化 对象
    var Barchart = echarts.init(document.getElementById('AreaList'));
    // 填充数据
    Barchart.setOption(option);
}
 function maxNum(x) {
	var max = x[0]; //首先定义第一个数为最大数
	if (x.length < 2) {
		return max;
	}
	for (var i = 0; i < x.length; i++) {
		if (x[i] > max) {
			max = x[i];
		}
	}
	return max;
}
