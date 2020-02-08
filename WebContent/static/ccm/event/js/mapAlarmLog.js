var map,Map, plotDraw, plotEdit, drawOverlay, drawStyle;

var center = [ 117.648920, 39.034450 ];

function init(){

	// 地图默认数据设置
	var defaultPrams = {
		id : 'map',
		centerCoordinate : centerCoordinate,
		zoom : zoomIndex,
		maxZoom : 20,
		minZoom : 2,
		baseUrl :baseUrlT,
		zoomShowOrHide : false,// 缩小放大
		overviewMap : true
	// 鹰眼图
	}
    　	Map = new ArjMap.Map(defaultPrams);
	// 加载地图
	Map.init();

    map=Map.map;
	
	
	/*工具栏*/
    $('.tag-panl-span').click(function(){
    	$(this).addClass('active').parent().siblings().children('.tag-panl-span').removeClass('active');
    })
    $('.tag-panl-close').click(function(){
    	$('#TagPanl').hide();
    })
    
    /*轨迹点位*/
    var pushArr = [[117.63434886932373, 39.02674198150635],
    	[117.64155864715576, 39.02519702911377],
    	[117.6436185836792,  39.031291007995605],
    	[117.6436185836792,  39.031291007995605],
    	[117.65469757843018, 39.028871886291504]];
    var objId ;
    objId=$("#objId").val();//id
    var createDate;
    createDate = $("#createDate").val();//时间
    
    //获取轨迹点位和电子围栏
    $.getJSON("/arjccm/a/sys/map/deviceMobileAlarm?deviceId="+objId+"&beginCurDate="+createDate+"&endCurDate="+createDate,
			function(data) {
				// 轨迹点位
    			var Data = data[0];
    			if(Data.length!=0){
    				var str=Data.substring(0,Data.length-1)
        			var routeCoords=str.split(';')
        		    var len=routeCoords.length;
        		    var pushArr=[]
        		    for(var i=0;i<len;i++){
        		    	var value=[];
        		    	 value[0]=Number(routeCoords[i].split(',')[0]);
        		    	 value[1]=Number(routeCoords[i].split(',')[1]);
        		    	 pushArr.push(value);
        		    }
    			}
    		    console.log(pushArr)
    		    Map.trackReplay('start-animation',5,pushArr);
    		    //电子围栏
    		    var ElectronicFence=data[1]
    		    Map.addElectronicFence('111','Polygon',ElectronicFence)
    			//定位到中心
    		    var areaPoint=data[2];
    		    if(areaPoint.length!=0){
	    		    var aps=[];
	    		    aps[0]=Number(areaPoint.split(',')[0]);
	    		    aps[1]=Number(areaPoint.split(',')[1]);
	    		    Map.goTo(aps);
    		    }
    			
	});
 
}

//标注绘制
function drawMark(type){
	Map.drawMark(type)
}

//工具栏
function HasChildren(){
	$('#TagPanl').show()
}
