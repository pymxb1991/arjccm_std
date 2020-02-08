var Map,Pubmap,currPage=1,pageSize =10; //每页出现数量;
var pTypeObj={},placeTypeObj={},placeImportantObj={},importantTypeObj={};
$(function(){
	//加载地图
	initMap();
	//初始化人员类型，场所类型，重点类型
	initType();
    var todayDate=today();
    $('#beginHappenDateEvent').val(todayDate+' 00:00:00');
	$('#endHappenDateEvent').val(todayDate+' 23:59:59');
	QueryEvents();
    $('#btnSubmitEvent').click(function(){
    	currPage=1;
    	QueryEvents();
    });
    $('#btnSubmitBuild').click(function(){
    	currPage=1;
    	QueryBuilds();
    });
    $('#btnSubmitPlac').click(function(){
    	currPage=1;
    	QueryPlaces();
    });
    $('#btnSubmitPeople').click(function(){
    	currPage=1;
    	QueryPeople();
    });
    $('#btnSubmitPeople').click(function(){
    	currPage=1;
    	QueryPeople();
    });
    $('#btnSubmitVideo').click(function(){
    	currPage=1;
    	QueryVideos();
    });
    layui.use('element', function(){
		  var element = layui.element; //Tab的切换功能，
		  element.on('tab(allTab)', function(data){
			  ClearData(data);
			});
		});
 
    
})

function ClearData(data){
	//清除框选查询框
	if(Map.draw){
		map.removeInteraction(Map.draw);
	}
	Map.drawVector.getSource().clear();
	currPage=1;
	 if(Map.overlayGISDialog){
			Map.overlayGISDialog.setPosition(undefined);
		}
		Map.clearOverlays();
		 $('#pageNum').html('0');
 	 $('#datalist').html('');
 	 paged('0');
 	 $('.input-medium').val('');
 	 if(data.index==0){
         var todayDate=today();
         $('#beginHappenDateEvent').val(todayDate+' 00:00:00');
         $('#endHappenDateEvent').val(todayDate+' 23:59:59');
     }
}
function initType(){
	//人员类型
	$.getJSON(ctx+'/sys/dict/listData?type=sys_ccm_people',function(datas) {
	for (var i = 0; i < datas.length; i++) {
		$('#pType').append("<option value='" + datas[i].value + "'>"+ datas[i].label+ "</option>");
		pTypeObj[datas[i].value]=datas[i].label;
	}
	})
	
	//重点人员类型
	$.getJSON(ctx+'/sys/dict/listData?type=emphasis_people_type',function(datas) {
		for (var i = 0; i < datas.length; i++) {
			$('#importantType').append("<option value='" + datas[i].value + "'>"+ datas[i].label+ "</option>");
			importantTypeObj[datas[i].value]=datas[i].label;
		}
	})
	//场所类型
	
	$.getJSON(ctx+'/sys/dict/listData?type=place_types',function(datas) {
		for (var i = 0; i < datas.length; i++) {
			placeTypeObj[datas[i].value]=datas[i].label;
		}
	})
	//重点类型
	$.getJSON(ctx+'/sys/dict/listData?type=ccm_place_important',function(datas) {
		for (var i = 0; i < datas.length; i++) {
			placeImportantObj[datas[i].value]=datas[i].label;
		}
	})
}
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
		overviewMap : false,	// 鹰眼图
		selectPointerFlag : true
	}
	Map = new ArjMap.Map(defaultPrams);
	// 加载地图
	Map.init();
	map=Map.map;
	// 框选查询初始化
	Map.selectQueryInit();
	
	Pubmap = Map.map;
}
function Tab(obj) {
    $(obj).addClass("active").siblings().removeClass("active");
    var index = $(obj).index(); //获取索引号
    $(".tab-item").eq(index).addClass("active").siblings().removeClass("active");
}
//分页
function paged(pageCount){
	layui.use(['laypage', 'layer'], function(){
        var laypage = layui.laypage, layer = layui.layer;
        laypage.render({
        	elem:'pageList' // 容器id
            , count: pageCount //总页数
            , curr: currPage
            ,groups: 1 //只显示 3 个连续页码
            ,layout: [ 'prev', 'page', 'next']
            , jump: function (obj,first) {
                currPage =obj.curr;  //这里是后台返回给前端的当前页数
                if(!first){ //点击跳页触发函数自身，并传递当前页：obj.curr  ajax 再次请求
                	$('#GIStab li').each(function(){
                		if($(this).hasClass('layui-this')){
                			var type=$(this).attr('type');
                			if(type=='event'){
                				QueryEvents()
                			}else if(type=='build'){
                				QueryBuilds()
                			}else if(type=='place'){
                				QueryPlaces()
                			}else if(type=='people'){
                				QueryPeople()
                			}else if(type=='video'){
                				QueryVideos()
                			}
                		}
                	})
                }
            }
        });
    });
}
//事件
function QueryEvents(){
	var caseName=$('#caseName').val();
	var beginHappenDateEvent=$('#beginHappenDateEvent').val();
	var endHappenDateEvent=$('#endHappenDateEvent').val();
	var areaEvent=$('#areaEventId').val();
	if(Map.overlayGISDialog){
		Map.overlayGISDialog.setPosition(undefined);
	}
	Map.clearOverlays();
	$.getJSON(ctx+'/sys/map/queryEventIncidentMap?beginHappenDate='+beginHappenDateEvent+'&endHappenDate='+endHappenDateEvent+'&caseName='+encodeURIComponent(caseName)+'&area.id='+areaEvent+'&pageNo='+currPage+'&pageSize='+pageSize+"&num="+Math.random(),function(data){
	
		if(data==null||data==""||data==undefined){
        	 $.jBox.tip('暂无数据！');
        	 $('#pageNum').html('0');
        	 $('#datalist').html('');
        	 paged('0');
             return ;
         }
		var featuresData=data.features;
		var count=data.count;
		paged(count);
		$('#pageNum').html(count);
		var html='';
		for(var i in featuresData){
			var id=featuresData[i].id;
			var x='',y='';
			if(featuresData[i].geometry.coordinates){
				 x=featuresData[i].geometry.coordinates[0];
				 y=featuresData[i].geometry.coordinates[1];
			}
			var properties=featuresData[i].properties;
			var icon=properties.icon;
			if(icon==''||icon==null){
				icon=ctxStatic+'/images/no.png'
			}
			html+='<li class="datalist-li" id="map_li'+id+'" data-id="'+id+'" onclick="goToDetail('+x+','+y+',\''+id+'\','+JSON.stringify(properties).replace(/"/g, '&quot;')+')">';
			html+='<div class="map_list_data" data-id="'+id+'" >';
			html+='<div class="col-left" data-id="'+id+'"> <a class="map_markers" href="javascript:;" data-id="'+id+'">'+(Number(i)+1)+'</a></div>';
			html+='<div class="col-right"><img src="'+icon+'" style="width:auto;height:58px;" class="pimg" onclick="imgShow(\'#outerdiv\', \'#innerdiv\', \'#bigimg\', this)"></div>';
			html+='<div class="col-center" style="margin-right: 50px;">';
			html+='<div class="col-row" data-id="'+id+'"> <span class="n-blue" data-id="'+id+'">'+data.features[i].properties.info['事件名称']+'</span></div>';
			if(x==''||y==''||featuresData[i].geometry.coordinates.length==0){
				html+='<div class="col-row" data-id="'+id+'"><span style="color:red;font-size: 8px;margin-left: -7px;" data-id="'+id+'">〔暂无坐标〕</span></div>';
			}
			html+='<div class="col-row" data-id="'+id+'">'+data.features[i].properties.info['事发地址']+'</div>';
			html+='<div class="col-row" data-id="'+id+'">'+data.features[i].properties.info['事发时间']+'</div>';
			html+='</div>';
			html+='</div>';
			html+='</li>';
		}
		$('#datalist').html(html);
		paged(count);
		onHoverLIst();
		for (var i in data.features){
			data.features[i].properties['nameNum']=''+(Number(i)+1)+'';
		}
		Map.addGIS([ {
			'type' : 'GIS',
			'id' : 'GIS',
			'data' : data,
			'isShow' : true
		} ])
	})
}
//框选查询
function selectQuery(type,content){
	var map=Map.map;
	if(!type){
		return;
	}
	//selectedFeatures.clear();
	if(Map.draw){
		map.removeInteraction(Map.draw);
	}
	Map.drawVector.getSource().clear();
	if(type=="Box"){
		Map.draw = new ol.interaction.Draw({
			source: Map.drawSource,
			type:"Circle",
			style:Map.drawStyle,
			geometryFunction:ol.interaction.Draw.createBox()
		});
	}else{
		Map.draw = new ol.interaction.Draw({
			source: Map.drawSource,
			type:type,
			style:Map.drawStyle,
		});
	}
	map.addInteraction(Map.draw);
	Map.draw.on('drawend',function(evt){
		// $(".selectFeature").removeClass("active");
		// if(type=='Point'){
		// 	var circleCenter = evt.feature.getGeometry().getCoordinates();
		// 	layer.confirm('<input id="RangeMeter" type="number" min="1" value="1000" style="margin: 0; width: 168px; margin-right: 5px;"/>米', {
		// 		btn: ['确定','取消'] //按钮
		// 	}, function(index){
		// 		var val=$('#RangeMeter').val();
		// 		Map.showCircleFromPoint(circleCenter,val)
		// 		layer.close(index);
		// 		$('.pointSelect').removeClass('active')
		// 	}, function(){
		// 		$('.pointSelect').removeClass('active')
		//
		// 	});
		// }else
			if(type=='Circle'){

			var polygon = evt.feature.getGeometry();
			var center = polygon.getCenter(),radius = polygon.getRadius(),extent = polygon.getExtent();
			$('.circleSelect').removeClass('active')
			var centerX=center[0],centerY=center[1];
			var val= radius/360*(2 * Math.PI * 6378137.0)//将纬度转换为米
			var data={'radius':val,'centerX':centerX,'centerY':centerY}//实时监控
			if(content == "event") {
				QueryEventsBySelection(data,"circle");
			}else if(content == "build"){
				QueryBuildsBySelection(data,"circle");
			}else if(content == "place"){
				QueryPlacesBySelection(data,"circle");
			}else if(content == "people"){
				QueryPeoplesBySelection(data,"circle");
			}else if(content == "video"){
				QueryVideosBySelection(data,"circle");
			}

			/* setTimeout(function(){
                 Map.getCircleSelect(polygon);
             },300)  */            //如果不设置延迟，范围内要素选中后自动取消选中，具体原因不知道
		}else if(type=='Polygon'||type=='Box'){
			var polygon = evt.feature.getGeometry();
			var coordinates=polygon.getCoordinates();
			var len = coordinates[0].length;
			var xSum = null, ySum = null, x = null, y = null;
			var xList='',yList='',xyList='';
			xyList=coordinates[0].join(';')
			for (var i = 0; i < len; i++) {
				xList+=coordinates[0][i][0]+',';
				yList+=coordinates[0][i][1]+',';

				xSum += Number(coordinates[0][i][0]);
				ySum += Number(coordinates[0][i][1]);

			}
			xList=xList.substring(0,xList.length-1);
			yList=yList.substring(0,yList.length-1);
			//中心点
			x = xSum / len;
			y = ySum / len;
			var areaPointDraw = x + ',' + y;
			console.log(areaPointDraw)
			var  geoStrDraw = coordinates[0].join(";");
			console.log(geoStrDraw)
			$('.polygonSelect').removeClass('active')
			var data={'xList':xList,'yList':yList,'xyList':xyList};//实时监控
			if(content == "event") {
				QueryEventsBySelection(data,"polygon");
			}else if(content == "build"){
				QueryBuildsBySelection(data,"polygon");
			}else if(content == "place"){
				QueryPlacesBySelection(data,"polygon");
			}else if(content == "people"){
				QueryPeoplesBySelection(data,"polygon");
			}else if(content == "video"){
				QueryVideosBySelection(data,"polygon");
			}
			/*   setTimeout(function(){
                   Map.getPolygonSelect(polygon);
               },300)*/
		}
		map.removeInteraction(Map.draw);
	})

}
//事件
function QueryEventsBySelection(data,selectionMode){
	var caseName=$('#caseName').val();
	var beginHappenDateEvent=$('#beginHappenDateEvent').val();
	var endHappenDateEvent=$('#endHappenDateEvent').val();
	var areaEvent=$('#areaEventId').val();
	if(Map.overlayGISDialog){
		Map.overlayGISDialog.setPosition(undefined);
	}
	Map.clearOverlays();
	var url ="";
	if (selectionMode=="circle"){
		url = ctx+'/sys/map/queryEventIncidentMapBySelection?beginHappenDate='+beginHappenDateEvent+'&endHappenDate='+endHappenDateEvent+'&caseName='+encodeURIComponent(caseName)+'&area.id='+areaEvent+'&radius='+data.radius+'&x='+data.centerX+'&y='+data.centerY+'&selectionMode='+selectionMode;
	} else if(selectionMode=="polygon"){
		url = ctx+'/sys/map/queryEventIncidentMapBySelection?beginHappenDate='+beginHappenDateEvent+'&endHappenDate='+endHappenDateEvent+'&caseName='+encodeURIComponent(caseName)+'&area.id='+areaEvent+'&pointsString='+data.xyList+'&selectionMode='+selectionMode;
	}
	$.getJSON(url,function(data){

		if(data==null||data==""||data==undefined){
			$.jBox.tip('暂无数据！');
			$('#pageNum').html('0');
			$('#datalist').html('');
			paged('0');
			return ;
		}
		var featuresData=data.features;
		var count=featuresData.length;
		paged('0');
		$('#pageNum').html(count);
		var html='';
		for(var i in featuresData){
			var id=featuresData[i].id;
			var x='',y='';
			if(featuresData[i].geometry.coordinates){
				x=featuresData[i].geometry.coordinates[0];
				y=featuresData[i].geometry.coordinates[1];
			}
			var properties=featuresData[i].properties;
			var icon=properties.icon;
			if(icon==''||icon==null){
				icon=ctxStatic+'/images/no.png'
			}
			html+='<li class="datalist-li" id="map_li'+id+'" data-id="'+id+'" onclick="goToDetail('+x+','+y+',\''+id+'\','+JSON.stringify(properties).replace(/"/g, '&quot;')+')">';
			html+='<div class="map_list_data" data-id="'+id+'">';
			html+='<div class="col-left" data-id="'+id+'"> <a class="map_markers" href="javascript:;" data-id="'+id+'">'+(Number(i)+1)+'</a></div>';
			html+='<div class="col-right"><img src="'+icon+'" style="width:auto;height:58px;" class="pimg"></div>';
			html+='<div class="col-center" style="margin-right: 50px;">';
			html+='<div class="col-row" data-id="'+id+'"> <span class="n-blue" data-id="'+id+'">'+data.features[i].properties.info['事件名称']+'</span></div>';
			if(x==''||y==''||featuresData[i].geometry.coordinates.length==0){
				html+='<div class="col-row" data-id="'+id+'"><span style="color:red;font-size: 8px;margin-left: -7px;" data-id="'+id+'">〔暂无坐标〕</span></div>';
			}
			html+='<div class="col-row" data-id="'+id+'">'+data.features[i].properties.info['事发地址']+'</div>';
			html+='<div class="col-row" data-id="'+id+'">'+data.features[i].properties.info['事发时间']+'</div>';
			html+='</div>';
			html+='</div>';
			html+='</li>';
		}
		$('#datalist').html(html);
		onHoverLIst();
		for (var i in data.features){
			data.features[i].properties['nameNum']=''+(Number(i)+1)+'';
		}

		Map.addGIS([ {
			'type' : 'GIS',
			'id' : 'GIS',
			'data' : data,
			'isShow' : true
		} ])
	})
}
//房屋-楼栋
function QueryBuilds(){
	var buildname=$('#buildname').val();
	var buildPname=$('#buildPname').val();
	var buildaddress=$('#buildaddress').val();
	var areaBuild=$('#areaBuildId').val();
	if(Map.overlayGISDialog){
		Map.overlayGISDialog.setPosition(undefined);
	}
	Map.clearOverlays();
	$.getJSON(ctx+'/sys/map/queryBuildMap?buildname='+encodeURIComponent(buildname)+'&buildPname='+encodeURIComponent(buildPname)+'&residencedetail='+encodeURIComponent(buildaddress)+'&area.id='+areaBuild+'&pageNo='+currPage+'&pageSize='+pageSize,function(data){
		if(data==null||data==""||data==undefined){
        	 $.jBox.tip('暂无数据！');
        	 $('#pageNum').html('0');
        	 $('#datalist').html('');
             return ;
         }
		var featuresData=data.features;
		var count=data.count;
		$('#pageNum').html(count);
		paged(count);
		var html='';
		for(var i in featuresData){
			var id=featuresData[i].id;
			var x='',y='';
			if(featuresData[i].geometry.coordinates){
				 x=featuresData[i].geometry.coordinates[0];
				 y=featuresData[i].geometry.coordinates[1];
			}
			var properties=featuresData[i].properties;
			var icon=properties.icon;
			if(icon==''||icon==null){
				icon=ctxStatic+'/images/no.png'
			}
			html+='<li class="datalist-li" id="map_li'+id+'" data-id="'+id+'" onclick="goToDetail('+x+','+y+',\''+id+'\','+JSON.stringify(properties).replace(/"/g, '&quot;')+')">';
			html+='<div class="map_list_data" data-id="'+id+'">';
			html+='<div class="col-left" data-id="'+id+'"> <a class="map_markers" href="javascript:;" data-id="'+id+'">'+(Number(i)+1)+'</a></div>';
			html+='<div class="col-right"><img src="'+icon+'" style="width:auto;height:58px;" class="pimg" class="pimg" onclick="imgShow(\'#outerdiv\', \'#innerdiv\', \'#bigimg\', this)"></div>';
			html+='<div class="col-center" style="margin-right: 50px;">';
			html+='<div class="col-row" data-id="'+id+'"> <span class="n-blue" data-id="'+id+'">'+data.features[i].properties.info['楼栋名称']+'</span></div>';
			if(x==''||y==''||featuresData[i].geometry.coordinates.length==0){
				html+='<div class="col-row" data-id="'+id+'"><span style="color:red;font-size: 8px;margin-left: -7px;" data-id="'+id+'">〔暂无坐标〕</span></div>';
			}
			html+='<div class="col-row" data-id="'+id+'">'+data.features[i].properties.info['楼栋长']+'</div>';
			html+='<div class="col-row" data-id="'+id+'">'+data.features[i].properties.info['所属小区']+'</div>';
			html+='</div>';
			html+='</div>';
			html+='</li>';
		}
		$('#datalist').html(html);
		onHoverLIst();
		for (var i in data.features){
			data.features[i].properties['nameNum']=''+(Number(i)+1)+'';
		}
		Map.addGIS([ {
			'type' : 'GIS',
			'id' : 'GIS',
			'data' : data,
			'isShow' : true
		} ])
	})
}
//房屋-楼栋(框选）
function QueryBuildsBySelection(data,selectionMode){
	var buildname=$('#buildname').val();
	var buildPname=$('#buildPname').val();
	var buildaddress=$('#buildaddress').val();
	var areaBuild=$('#areaBuildId').val();
	if(Map.overlayGISDialog){
		Map.overlayGISDialog.setPosition(undefined);
	}
	Map.clearOverlays();
	var url ="";
	if (selectionMode=="circle"){
		url = ctx+'/sys/map/queryBuildMapBySelection?buildname='+encodeURIComponent(buildname)+'&buildPname='+encodeURIComponent(buildPname)+'&residencedetail='+encodeURIComponent(buildaddress)+'&area.id='+areaBuild+'&radius='+data.radius+'&x='+data.centerX+'&y='+data.centerY+'&selectionMode='+selectionMode;
	} else if(selectionMode=="polygon"){
		url = ctx+'/sys/map/queryBuildMapBySelection?buildname='+encodeURIComponent(buildname)+'&buildPname='+encodeURIComponent(buildPname)+'&residencedetail='+encodeURIComponent(buildaddress)+'&area.id='+areaBuild+'&pointsString='+data.xyList+'&selectionMode='+selectionMode;
	}
	$.getJSON(url,function(data){
		if(data==null||data==""||data==undefined){
			$.jBox.tip('暂无数据！');
			$('#pageNum').html('0');
			$('#datalist').html('');
			return ;
		}
		var featuresData=data.features;
		var count=featuresData.length;
		$('#pageNum').html(count);
		paged('0');
		var html='';
		for(var i in featuresData){
			var id=featuresData[i].id;
			var x='',y='';
			if(featuresData[i].geometry.coordinates){
				x=featuresData[i].geometry.coordinates[0];
				y=featuresData[i].geometry.coordinates[1];
			}
			var properties=featuresData[i].properties;
			var icon=properties.icon;
			if(icon==''||icon==null){
				icon=ctxStatic+'/images/no.png'
			}
			html+='<li class="datalist-li" id="map_li'+id+'" data-id="'+id+'" onclick="goToDetail('+x+','+y+',\''+id+'\','+JSON.stringify(properties).replace(/"/g, '&quot;')+')">';
			html+='<div class="map_list_data" data-id="'+id+'">';
			html+='<div class="col-left" data-id="'+id+'"> <a class="map_markers" href="javascript:;" data-id="'+id+'">'+(Number(i)+1)+'</a></div>';
			html+='<div class="col-right"><img src="'+icon+'" style="width:auto;height:58px;"></div>';
			html+='<div class="col-center" style="margin-right: 50px;">';
			html+='<div class="col-row" data-id="'+id+'"> <span class="n-blue" data-id="'+id+'">'+data.features[i].properties.info['楼栋名称']+'</span></div>';
			if(x==''||y==''||featuresData[i].geometry.coordinates.length==0){
				html+='<div class="col-row" data-id="'+id+'"><span style="color:red;font-size: 8px;margin-left: -7px;" data-id="'+id+'">〔暂无坐标〕</span></div>';
			}
			html+='<div class="col-row" data-id="'+id+'">'+data.features[i].properties.info['楼栋长']+'</div>';
			html+='<div class="col-row" data-id="'+id+'">'+data.features[i].properties.info['所属小区']+'</div>';
			html+='</div>';
			html+='</div>';
			html+='</li>';
		}
		$('#datalist').html(html);
		onHoverLIst();
		for (var i in data.features){
			data.features[i].properties['nameNum']=''+(Number(i)+1)+'';
		}
		Map.addGIS([ {
			'type' : 'GIS',
			'id' : 'GIS',
			'data' : data,
			'isShow' : true
		} ])
	})
}
//场所
function QueryPlaces(){
	var placeName=$('#placeName').val();
	var keyPointList=$('#keyPointList').val();
	var placeType=$('#placeType').val();
	var areaPlace=$('#areaPlaceId').val();
	if(Map.overlayGISDialog){
		Map.overlayGISDialog.setPosition(undefined);
	}
	Map.clearOverlays();
	$.getJSON(ctx+'/sys/map/queryPlaceMap?placeName='+encodeURIComponent(placeName)+'&area.id='+areaPlace+'&pageNo='+currPage+'&pageSize='+pageSize,function(data){
		if(data==null||data==""||data==undefined){
        	 $.jBox.tip('暂无数据！');
        	 $('#pageNum').html('0');
        	 $('#datalist').html('');
        	 paged('0');
             return ;
         }
		var featuresData=data.features;
		var count=data.count;
		$('#pageNum').html(count);
		paged(count);
		var html='';
		for(var i in featuresData){
			var id=featuresData[i].id;
			var x='',y='';
			if(featuresData[i].geometry.coordinates){
				 x=featuresData[i].geometry.coordinates[0];
				 y=featuresData[i].geometry.coordinates[1];
			}
			
			var properties=featuresData[i].properties;
			var icon=properties.icon;
			if(icon==''||icon==null){
				icon=ctxStatic+'/images/no.png'
			}
			html+='<li class="datalist-li" id="map_li'+id+'" data-id="'+id+'" onclick="goToDetail('+x+','+y+',\''+id+'\','+JSON.stringify(properties).replace(/"/g, '&quot;')+')">';
			html+='<div class="map_list_data" data-id="'+id+'">';
			html+='<div class="col-left" data-id="'+id+'"> <a class="map_markers" href="javascript:;" data-id="'+id+'">'+(Number(i)+1)+'</a></div>';
			html+='<div class="col-right"><img src="'+icon+'" style="width:auto;height:58px;" class="pimg" onclick="imgShow(\'#outerdiv\', \'#innerdiv\', \'#bigimg\', this)"></div>';
			html+='<div class="col-center" style="margin-right: 50px;">';
			html+='<div class="col-row" data-id="'+id+'"> <span class="n-blue" data-id="'+id+'">'+data.features[i].properties.info['场所名称']+'</span></div>';
			if(x==''||y==''||featuresData[i].geometry.coordinates.length==0){
				html+='<div class="col-row" data-id="'+id+'"><span style="color:red;font-size: 8px;margin-left: -7px;" data-id="'+id+'">〔暂无坐标〕</span></div>';
			}
			html+='<div class="col-row" data-id="'+id+'">'+data.features[i].properties.info['地址']+'</div>';
			html+='<div class="col-row" data-id="'+id+'">'+data.features[i].properties.info['负责人姓名']+'</div>';
			html+='</div>';
			html+='</div>';
			html+='</li>';
		}
		$('#datalist').html(html);
		onHoverLIst();
		for (var i in data.features){
			data.features[i].properties['nameNum']=''+(Number(i)+1)+'';
		}
		Map.addGIS([ {
			'type' : 'GIS',
			'id' : 'GIS',
			'data' : data,
			'isShow' : true
		} ])
	})
}
//场所（框选）
function QueryPlacesBySelection(data,selectionMode){
	var placeName=$('#placeName').val();
	var keyPointList=$('#keyPointList').val();
	var placeType=$('#placeType').val();
	var areaPlace=$('#areaPlaceId').val();
	if(Map.overlayGISDialog){
		Map.overlayGISDialog.setPosition(undefined);
	}
	Map.clearOverlays();
	var url ="";
	if (selectionMode=="circle"){
		url = ctx+'/sys/map/queryPlaceMapBySelection?placeName='+encodeURIComponent(placeName)+'&area.id='+areaPlace+'&radius='+data.radius+'&x='+data.centerX+'&y='+data.centerY+'&selectionMode='+selectionMode;
	} else if(selectionMode=="polygon"){
		url = ctx+'/sys/map/queryPlaceMapBySelection?placeName='+encodeURIComponent(placeName)+'&area.id='+areaPlace+'&pointsString='+data.xyList+'&selectionMode='+selectionMode;
	}
	$.getJSON(url,function(data){
		if(data==null||data==""||data==undefined){
			$.jBox.tip('暂无数据！');
			$('#pageNum').html('0');
			$('#datalist').html('');
			paged('0');
			return ;
		}
		var featuresData=data.features;
		var count=featuresData.length;
		$('#pageNum').html(count);
		paged('0');
		var html='';
		for(var i in featuresData){
			var id=featuresData[i].id;
			var x='',y='';
			if(featuresData[i].geometry.coordinates){
				x=featuresData[i].geometry.coordinates[0];
				y=featuresData[i].geometry.coordinates[1];
			}

			var properties=featuresData[i].properties;
			var icon=properties.icon;
			if(icon==''||icon==null){
				icon=ctxStatic+'/images/no.png'
			}
			html+='<li class="datalist-li" id="map_li'+id+'" data-id="'+id+'" onclick="goToDetail('+x+','+y+',\''+id+'\','+JSON.stringify(properties).replace(/"/g, '&quot;')+')">';
			html+='<div class="map_list_data" data-id="'+id+'">';
			html+='<div class="col-left" data-id="'+id+'"> <a class="map_markers" href="javascript:;" data-id="'+id+'">'+(Number(i)+1)+'</a></div>';
			html+='<div class="col-right"><img src="'+icon+'" style="width:auto;height:58px;"></div>';
			html+='<div class="col-center" style="margin-right: 50px;">';
			html+='<div class="col-row" data-id="'+id+'"> <span class="n-blue" data-id="'+id+'">'+data.features[i].properties.info['场所名称']+'</span></div>';
			if(x==''||y==''||featuresData[i].geometry.coordinates.length==0){
				html+='<div class="col-row" data-id="'+id+'"><span style="color:red;font-size: 8px;margin-left: -7px;" data-id="'+id+'">〔暂无坐标〕</span></div>';
			}
			html+='<div class="col-row" data-id="'+id+'">'+data.features[i].properties.info['地址']+'</div>';
			html+='<div class="col-row" data-id="'+id+'">'+data.features[i].properties.info['负责人姓名']+'</div>';
			html+='</div>';
			html+='</div>';
			html+='</li>';
		}
		$('#datalist').html(html);
		onHoverLIst();
		for (var i in data.features){
			data.features[i].properties['nameNum']=''+(Number(i)+1)+'';
		}
		Map.addGIS([ {
			'type' : 'GIS',
			'id' : 'GIS',
			'data' : data,
			'isShow' : true
		} ])
	})
}
//人员
function QueryPeople(){
	var pType=$('#pType').val();
	var pIdent=$('#pIdent').val();
	var pName=$('#pName').val();
	var areaPeople=$('#areaPeopleId').val();
	var importantType=$('#importantType').val();
	var pageNo=1;
	if(Map.overlayGISDialog){
		Map.overlayGISDialog.setPosition(undefined);
	}
	Map.clearOverlays();
	$.getJSON(ctx+'/sys/map/queryPeopleMap?name='+encodeURIComponent(pName)+'&ident='+pIdent+'&type='+pType+'&areaGridId.id='+areaPeople+'&importantType='+importantType+'&pageNo='+currPage+'&pageSize='+pageSize,function(data){
		if(data==null||data==""||data==undefined){
        	 $.jBox.tip('暂无数据！');
        	 $('#pageNum').html('0');
        	 $('#datalist').html('');
        	 paged('0');
             return ;
         }
		var featuresData=data.features;
		var count=data.count;
		$('#pageNum').html(count);

		paged(count);
		var html='';
		for(var i in featuresData){
			var id=featuresData[i].id;
			var x='',y='';
			if(featuresData[i].geometry.coordinates){
				 x=featuresData[i].geometry.coordinates[0];
				 y=featuresData[i].geometry.coordinates[1];
			}
			var properties=featuresData[i].properties;
			var icon=properties.icon;
			if(icon==''||icon==null){
				icon=ctxStatic+'/images/no.png'
			}
			html+='<li class="datalist-li" id="map_li'+id+'" data-id="'+id+'" onclick="goToDetail('+x+','+y+',\''+id+'\','+JSON.stringify(properties).replace(/"/g, '&quot;')+')">';
			html+='<div class="map_list_data" data-id="'+id+'">';
			html+='<div class="col-left" data-id="'+id+'"> <a class="map_markers" href="javascript:;" data-id="'+id+'">'+(Number(i)+1)+'</a></div>';
			html+='<div class="col-right"><img src="'+icon+'" style="width:auto;height:58px;" class="pimg" onclick="imgShow(\'#outerdiv\', \'#innerdiv\', \'#bigimg\', this)"></div>';
			html+='<div class="col-center" style="margin-right: 50px;">';
			html+='<div class="col-row" data-id="'+id+'"> <span class="n-blue" data-id="'+id+'">'+data.features[i].properties.info['姓名']+'</span></div>';
			if(x==''||y==''||featuresData[i].geometry.coordinates.length==0){
				html+='<div class="col-row" data-id="'+id+'"><span style="color:red;font-size: 8px;margin-left: -7px;" data-id="'+id+'">〔暂无坐标〕</span></div>';
			}
			html+='<div class="col-row" data-id="'+id+'">'+data.features[i].properties.info['身份号码']+'</div>';
			html+='<div class="col-row" data-id="'+id+'">'+data.features[i].properties.info['户籍详址']+'</div>';
			html+='</div>';
			html+='</div>';
			html+='</li>';
		}
		$('#datalist').html(html);
		onHoverLIst();
		for (var i in data.features){
			data.features[i].properties['nameNum']=''+(Number(i)+1)+'';
		}
		Map.addGIS([ {
			'type' : 'GIS',
			'id' : 'GIS',
			'data' : data,
			'isShow' : true
		} ])
	})
}
//人员(框选）
function QueryPeoplesBySelection(data,selectionMode){
	var pType=$('#pType').val();
	var pIdent=$('#pIdent').val();
	var pName=$('#pName').val();
	var areaPeople=$('#areaPeopleId').val();
	var importantType=$('#importantType').val();
	var pageNo=1;
	if(Map.overlayGISDialog){
		Map.overlayGISDialog.setPosition(undefined);
	}
	Map.clearOverlays();
	var url ="";
	if (selectionMode=="circle"){
		url = ctx+'/sys/map/queryPeopleMapBySelection?name='+encodeURIComponent(pName)+'&ident='+pIdent+'&type='+pType+'&areaGridId.id='+areaPeople+'&radius='+data.radius+'&x='+data.centerX+'&y='+data.centerY+'&selectionMode='+selectionMode+'&importantType='+importantType;
	} else if(selectionMode=="polygon"){
		url = ctx+'/sys/map/queryPeopleMapBySelection?name='+encodeURIComponent(pName)+'&ident='+pIdent+'&type='+pType+'&areaGridId.id='+areaPeople+'&pointsString='+data.xyList+'&selectionMode='+selectionMode+'&importantType='+importantType;
	}
	$.getJSON(url,function(data){
		if(data==null||data==""||data==undefined){
			$.jBox.tip('暂无数据！');
			$('#pageNum').html('0');
			$('#datalist').html('');
			paged('0');
			return ;
		}
		var featuresData=data.features;
		var count=featuresData.length;
		$('#pageNum').html(count);
		paged('0');
		var html='';
		for(var i in featuresData){
			var id=featuresData[i].id;
			var x='',y='';
			if(featuresData[i].geometry.coordinates){
				x=featuresData[i].geometry.coordinates[0];
				y=featuresData[i].geometry.coordinates[1];
			}
			var properties=featuresData[i].properties;
			var icon=properties.icon;
			if(icon==''||icon==null){
				icon=ctxStatic+'/images/no.png'
			}
			html+='<li class="datalist-li" id="map_li'+id+'" data-id="'+id+'" onclick="goToDetail('+x+','+y+',\''+id+'\','+JSON.stringify(properties).replace(/"/g, '&quot;')+')">';
			html+='<div class="map_list_data" data-id="'+id+'">';
			html+='<div class="col-left" data-id="'+id+'"> <a class="map_markers" href="javascript:;" data-id="'+id+'">'+(Number(i)+1)+'</a></div>';
			html+='<div class="col-right"><img src="'+icon+'" style="width:auto;height:58px;"></div>';
			html+='<div class="col-center" style="margin-right: 50px;">';
			html+='<div class="col-row" data-id="'+id+'"> <span class="n-blue" data-id="'+id+'">'+data.features[i].properties.info['姓名']+'</span></div>';
			if(x==''||y==''||featuresData[i].geometry.coordinates.length==0){
				html+='<div class="col-row" data-id="'+id+'"><span style="color:red;font-size: 8px;margin-left: -7px;" data-id="'+id+'">〔暂无坐标〕</span></div>';
			}
			html+='<div class="col-row" data-id="'+id+'">'+data.features[i].properties.info['身份号码']+'</div>';
			html+='<div class="col-row" data-id="'+id+'">'+data.features[i].properties.info['户籍详址']+'</div>';
			html+='</div>';
			html+='</div>';
			html+='</li>';
		}
		$('#datalist').html(html);
		onHoverLIst();
		for (var i in data.features){
			data.features[i].properties['nameNum']=''+(Number(i)+1)+'';
		}
		Map.addGIS([ {
			'type' : 'GIS',
			'id' : 'GIS',
			'data' : data,
			'isShow' : true
		} ])
	})
}
//监控
function QueryVideos(){
	var videoIP=$('#videoIP').val();
	var videoName=$('#videoName').val();
	var areaVideo=$('#areaVideoId').val();
	if(Map.overlayGISDialog){
		Map.overlayGISDialog.setPosition(undefined);
	}
	Map.clearOverlays();
	$.getJSON(ctx+'/sys/map/queryDeviceMap?name='+encodeURIComponent(videoName)+'&ip='+videoIP+'&area.id='+areaVideo+'&pageNo='+currPage+'&pageSize='+pageSize,function(data){
		if(data==null||data==""||data==undefined){
        	 $.jBox.tip('暂无数据！');
        	 $('#pageNum').html('0');
        	 $('#datalist').html('');
        	 paged('0');
             return ;
         }
		var featuresData=data.features;
		var count=data.count;
		paged(count);
		$('#pageNum').html(count);
		var html='';
		for(var i in featuresData){
			var id=featuresData[i].id;
			var x='',y='';
			if(featuresData[i].geometry.coordinates){
				 x=featuresData[i].geometry.coordinates[0];
				 y=featuresData[i].geometry.coordinates[1];
			}
			var properties=featuresData[i].properties;
			html+='<li class="datalist-li" id="map_li'+id+'" data-id="'+id+'" onclick="goToDetail('+x+','+y+',\''+id+'\','+JSON.stringify(properties).replace(/"/g, '&quot;')+')">';
			html+='<div class="map_list_data" data-id="'+id+'">';
			html+='<div class="col-left" data-id="'+id+'"> <a class="map_markers" href="javascript:;" data-id="'+id+'">'+(Number(i)+1)+'</a></div>';
			html+='<div class="col-right"><img src="'+ctxStatic+'/images/gisVideo.png" style="width:auto;height:58px;"></div>';
			html+='<div class="col-center" style="margin-right: 50px;">';
			html+='<div class="col-row" data-id="'+id+'"> <span class="n-blue" data-id="'+id+'">'+data.features[i].properties.name+'</span></div>';
			if(x==''||y==''||featuresData[i].geometry.coordinates.length==0){
				html+='<div class="col-row" data-id="'+id+'"><span style="color:red;font-size: 8px;margin-left: -7px;" data-id="'+id+'">〔暂无坐标〕</span></div>';
			}
			html+='<div class="col-row" data-id="'+id+'">'+data.features[i].properties.info['IP地址']+'</div>';
			html+='<div class="col-row" data-id="'+id+'">'+data.features[i].properties.info['安装位置']+'</div>';
			html+='</div>';
			html+='</div>';
			html+='</li>';
		}
		$('#datalist').html(html);
		paged(count);
		onHoverLIst();
		for (var i in data.features){
			data.features[i].properties['nameNum']=''+(Number(i)+1)+'';
		}
		
		Map.addGIS([ {
			'type' : 'GIS',
			'id' : 'GIS',
			'data' : data,
			'isShow' : true
		} ])
	})
}
//监控(框选)
function QueryVideosBySelection(data,selectionMode){
	var videoIP=$('#videoIP').val();
	var videoName=$('#videoName').val();
	var areaVideo=$('#areaVideoId').val();
	if(Map.overlayGISDialog){
		Map.overlayGISDialog.setPosition(undefined);
	}
	Map.clearOverlays();
	var url ="";
	if (selectionMode=="circle"){
		url = ctx+'/sys/map/queryDeviceMapBySelection?name='+encodeURIComponent(videoName)+'&ip='+videoIP+'&area.id='+areaVideo+'&radius='+data.radius+'&x='+data.centerX+'&y='+data.centerY+'&selectionMode='+selectionMode;
	} else if(selectionMode=="polygon"){
		url = ctx+'/sys/map/queryDeviceMapBySelection?name='+encodeURIComponent(videoName)+'&ip='+videoIP+'&area.id='+areaVideo+'&pointsString='+data.xyList+'&selectionMode='+selectionMode;
	}
	$.getJSON(url,function(data){
		if(data==null||data==""||data==undefined){
			$.jBox.tip('暂无数据！');
			$('#pageNum').html('0');
			$('#datalist').html('');
			paged('0');
			return ;
		}
		var featuresData=data.features;
		var count=featuresData.length;
		paged('0');
		$('#pageNum').html(count);
		var html='';
		for(var i in featuresData){
			var id=featuresData[i].id;
			var x='',y='';
			if(featuresData[i].geometry.coordinates){
				x=featuresData[i].geometry.coordinates[0];
				y=featuresData[i].geometry.coordinates[1];
			}
			var properties=featuresData[i].properties;
			html+='<li class="datalist-li" id="map_li'+id+'" data-id="'+id+'" onclick="goToDetail('+x+','+y+',\''+id+'\','+JSON.stringify(properties).replace(/"/g, '&quot;')+')">';
			html+='<div class="map_list_data" data-id="'+id+'">';
			html+='<div class="col-left" data-id="'+id+'"> <a class="map_markers" href="javascript:;" data-id="'+id+'">'+(Number(i)+1)+'</a></div>';
			html+='<div class="col-right"><img src="'+ctxStatic+'/images/gisVideo.png" style="width:auto;height:58px;"></div>';
			html+='<div class="col-center" style="margin-right: 50px;">';
			html+='<div class="col-row" data-id="'+id+'"> <span class="n-blue" data-id="'+id+'">'+data.features[i].properties.name+'</span></div>';
			if(x==''||y==''||featuresData[i].geometry.coordinates.length==0){
				html+='<div class="col-row" data-id="'+id+'"><span style="color:red;font-size: 8px;margin-left: -7px;" data-id="'+id+'">〔暂无坐标〕</span></div>';
			}
			html+='<div class="col-row" data-id="'+id+'">'+data.features[i].properties.info['IP地址']+'</div>';
			html+='<div class="col-row" data-id="'+id+'">'+data.features[i].properties.info['安装位置']+'</div>';
			html+='</div>';
			html+='</div>';
			html+='</li>';
		}
		$('#datalist').html(html);
		onHoverLIst();
		for (var i in data.features){
			data.features[i].properties['nameNum']=''+(Number(i)+1)+'';
		}

		Map.addGIS([ {
			'type' : 'GIS',
			'id' : 'GIS',
			'data' : data,
			'isShow' : true
		} ])
	})
}
//点击定位
function goToDetail(x,y,id,info){
	var coordinates=[x,y];
	Map.map.getView().setZoom(18);
	Map.goTo(coordinates);
	Map.selectGISPointer(id,info,coordinates);
	 $('.gis-marker').removeClass('activeMax');
	 $('#overlay_'+id).addClass('activeMax');
	 $('.gis-marker').parent().removeClass('activeMax');
	 $('#overlay_'+id).parent().addClass('activeMax');
}

function onHoverLIst(){
	var $document = $(document);
	  $document.on("mouseenter mouseleave",".datalist-li",function(event){
		  var id=event.target.getAttribute('data-id');
	  　　if(event.type == "mouseenter"){
		    $('.gis-marker').removeClass('active');
			$('#overlay_'+id).addClass('active');
			 $('.gis-marker').parent().removeClass('active');
			 $('#overlay_'+id).parent().addClass('active');
	  　　}else if(event.type == "mouseleave"){
		  $('.gis-marker').removeClass('active');
		  $('#overlay_'+id).parent().removeClass('active');
	  　　};
	  });
}



//学校
var map;
var xuexiaoFlag = true;
var idArrxuexiao = [];
function xuexiaoFun(_this) {
	var centpoint = [];
	if (xuexiaoFlag) {
		$.getJSON('' + ctx + '/sys/map/ccmOrgNpseMap?type=32', function(data) {
			centpoint = data.centpoint;
			var features = data.features;
			var len = features.length;
			idArrxuexiao = [];
			if (len > 0) {
				for (var i = 0; i < len; i++) {
					idArrxuexiao.push(features[i].id);
				}
			}
			Map.addJSON3([ {
				'type' : 'Shortcut',
				'data' : data,
				'id' : 'xuexiao',
				'isShow' : true
			} ])

		})
		$(_this).css('border', '1px solid #0e54a9')
		// Map.goTo(centpoint)
	} else {
		$(_this).css('border', '1px solid transparent');
		$.each(idArrxuexiao, function(index, val) {
			Pubmap.removeOverlay(Map['' + val + 'Overlay'])
		});
		Map.removeLayer('xuexiao');
	}
	xuexiaoFlag = !xuexiaoFlag;
}
//医院
var yiyuanFlag = true;
var publicPlaceFlag = true;
var idArryiyuan = [];
function yiyuanFun(_this) {
	if (yiyuanFlag) {
		$.getJSON('' + ctx + '/sys/map/ccmOrgCommonalityMap?type=2', function(
				data) {
			var features = data.features;
			var len = features.length;
			idArryiyuan = [];
			if (len > 0) {
				for (var i = 0; i < len; i++) {
					idArryiyuan.push(features[i].id);
				}
			}
			Map.addJSON3([ {
				'type' : 'Shortcut',
				'data' : data,
				'id' : 'yiyuan',
				'isShow' : true
			} ])

		})
		$(_this).css('border', '1px solid #0e54a9')
		/* Map.goTo([ "113.36105768169675", "34.54275331326893" ]) */
	} else {
		$.each(idArryiyuan, function(index, val) {
			Pubmap.removeOverlay(Map['' + val + 'Overlay'])
		});
		$(_this).css('border', '1px solid transparent');
		Map.removeLayer('yiyuan');
	}
	yiyuanFlag = !yiyuanFlag;
}

var jingcheFlag = true;
function jingcheFun(_this) {
	if (jingcheFlag) {
		$.getJSON('' + ctx + '/sys/map/ccmOrgCommonalityMap?type=2', function(
				data) {
			Map.addJSON1([ {
				'type' : 'DanDian',
				'data' : data,
				'id' : 'jingche',
				'isShow' : true
			} ])
		})

		$(_this).css('border', '1px solid #0e54a9')
	} else {
		$(_this).css('border', '1px solid transparent')
		Map.removeLayer('jingche');
	}
	jingcheFlag = !jingcheFlag;
}
//警员
var jingyuanFlag = true;
function jingyuanFun(_this) {
	if (jingyuanFlag) {
		$.getJSON('' + ctx + '/sys/map/deviceMobileMap', function(data) {
			Map.addJSON1([ {
				'type' : 'PopLocation',
				'id' : 'jingyuan',
				'data' : data,
				'isShow' : true
			} ]);
		})
		Map.layersIsShow('PopLocation', true);
		$(_this).css('border', '1px solid #0e54a9');
	} else {
		$(_this).css('border', '1px solid transparent')
		Map.removeLayer('jingyuan');
	}
	jingyuanFlag = !jingyuanFlag;
}
//加油站
var jiayouzhanFlag = true;
var idArrjiayouzhan = [];
function jiayouzhanFun(_this) {
	if (jiayouzhanFlag) {
		$.getJSON('' + ctx + '/sys/map/findMapIndustry?type=1', function(data) {
			if (data != null) {
				var features = data.features;
				var len = features.length;
				idArrjiayouzhan = [];
				if (len > 0) {
					for (var i = 0; i < len; i++) {
						idArrjiayouzhan.push(features[i].id);
					}
				}
			}
			Map.addJSON3([ {
				'type' : 'Shortcut',
				'id' : 'jiayouzhan',
				'data' : data,
				'isShow' : true
			} ])
		})

		$(_this).css('border', '1px solid #0e54a9');
		// Map.goTo([ "113.39035820960999", "34.528061628341675" ])
	} else {
		$.each(idArrjiayouzhan, function(index, val) {
			Pubmap.removeOverlay(Map['' + val + 'Overlay'])
		});
		$(_this).css('border', '1px solid transparent')
		Map.removeLayer('jiayouzhan');
	}
	jiayouzhanFlag = !jiayouzhanFlag;
}
//商场超市
var shangchangFlag = true;
var idArrshangchang = [];
function shangchangFun(_this) {
	if (shangchangFlag) {
		$.getJSON('' + ctx + '/sys/map/findMapIndustry?type=2', function(data) {
			if (data != null) {
				var features = data.features;
				var len = features.length;
				idArrshangchang = [];
				if (len > 0) {
					for (var i = 0; i < len; i++) {
						idArrshangchang.push(features[i].id);
					}
				}
			}
			Map.addJSON3([ {
				'type' : 'Shortcut',
				'id' : 'shangchang',
				'data' : data,
				'isShow' : true
			} ])
		})
		$(_this).css('border', '1px solid #0e54a9');
	} else {
		$.each(idArrshangchang, function(index, val) {
			Pubmap.removeOverlay(Map['' + val + 'Overlay'])
		});
		$(_this).css('border', '1px solid transparent')
		Map.removeLayer('shangchang');
	}
	shangchangFlag = !shangchangFlag;
}
//娱乐场所
var yuleFlag = true;
var idArryule = [];
function yuleFun(_this) {
	if (yuleFlag) {
		$.getJSON('' + ctx + '/sys/map/findMapIndustry?type=3', function(data) {
			if (data != null) {
				var features = data.features;
				var len = features.length;
				idArryule = [];
				if (len > 0) {
					for (var i = 0; i < len; i++) {
						idArryule.push(features[i].id);
					}
				}
			}
			Map.addJSON3([ {
				'type' : 'Shortcut',
				'id' : 'yule',
				'data' : data,
				'isShow' : true
			} ])
		})
		$(_this).css('border', '1px solid #0e54a9');
	} else {
		$.each(idArryule, function(index, val) {
			Pubmap.removeOverlay(Map['' + val + 'Overlay'])
		});
		$(_this).css('border', '1px solid transparent')
		Map.removeLayer('yule');
	}
	yuleFlag = !yuleFlag;
}
//酒店宾馆
var binguanFlag = true;
var idArrbinguan = [];
function binguanFun(_this) {
	var id = 0;
	if (binguanFlag) {
		$.getJSON('' + ctx + '/sys/map/findMapIndustry?type=4', function(data) {
			if (data != null) {
				id = data.id;
				var features = data.features;
				var len = features.length;
				idArrbinguan = [];
				if (len > 0) {
					for (var i = 0; i < len; i++) {
						idArrbinguan.push(features[i].id);
					}
				}
			}
			Map.addJSON3([ {
				'type' : 'Shortcut',
				'id' : 'binguan',
				'data' : data,
				'isShow' : true
			} ])
		})
		$(_this).css('border', '1px solid #0e54a9');
	} else {
		$.each(idArrbinguan, function(index, val) {
			Pubmap.removeOverlay(Map['' + val + 'Overlay'])
		});
		$(_this).css('border', '1px solid transparent');
		Map.removeLayer('binguan');
	}

	binguanFlag = !binguanFlag;
}
//涉危涉爆
var sheweishebaoFlag = true;
var idArrsheweishebao = [];
function sheweishebaoFun(_this) {
	if (sheweishebaoFlag) {
		$.getJSON('' + ctx + '/sys/map/findMapIndustry?type=5', function(data) {
			if (data != null) {
				var features = data.features;
				var len = features.length;
				idArrsheweishebao = [];
				if (len > 0) {
					for (var i = 0; i < len; i++) {
						idArrsheweishebao.push(features[i].id);
					}
				}
			}
			Map.addJSON3([ {
				'type' : 'Shortcut',
				'id' : 'sheweishebao',
				'data' : data,
				'isShow' : true
			} ])
		})
		$(_this).css('border', '1px solid #0e54a9');
	} else {
		$.each(idArrsheweishebao, function(index, val) {
			Pubmap.removeOverlay(Map['' + val + 'Overlay'])
		});
		$(_this).css('border', '1px solid transparent')
		Map.removeLayer('sheweishebao');
	}
	sheweishebaoFlag = !sheweishebaoFlag;
}
//警务室
var jingwushiFlag = true;
var idArrjingwushi = [];
function jingwushiFun(_this) {
	if (jingwushiFlag) {
		Map.removeLayer('policeroom');
		// $.getJSON('' + ctx + '/sys/map/findMapVCcmOrgType?type=3', function(
		// 		data) {
		$.getJSON('' + ctx + '/sys/map/orgCommonlityMap?type=10', function(data) {
			var features = data.features;
			var len = features.length;
			idArrjingwushi = [];
			if (len > 0) {
				for (var i = 0; i < len; i++) {
					idArrjingwushi.push(features[i].id);
				}
			}
			Map.addJSON1([ {
				'type' : 'policeroom',
				'id' : 'jingwushi',
				'data' : data,
				'isShow' : true
			} ])
		})
		$(_this).css('border', '1px solid #0e54a9');
	} else {
		$.each(idArrjingwushi, function(index, val) {
			Pubmap.removeOverlay(Map['' + val + 'Overlay'])
		});
		$(_this).css('border', '1px solid transparent');
		Map.removeLayer('policeroom');
	}
	jingwushiFlag = !jingwushiFlag;
}
//工作站
var gongzuozhanFlag = true;
var idArrgongzuozhan = [];
function gongzuozhanFun(_this) {
	if (gongzuozhanFlag) {
		Map.removeLayer('workstation');
		// $.getJSON('' + ctx + '/sys/map/findMapVCcmOrgType?type=4', function(
		// 		data) {
		$.getJSON('' + ctx + '/sys/map/orgCommonlityMap?type=11', function(data) {
			var features = data.features;
			var len = features.length;
			idArrgongzuozhan = [];
			if (len > 0) {
				for (var i = 0; i < len; i++) {
					idArrgongzuozhan.push(features[i].id);
				}
			}
			Map.addJSON1([ {
				'type' : 'workstation',
				'id' : 'gongzuozhan',
				'data' : data,
				'isShow' : true
			} ])
		})
		$(_this).css('border', '1px solid #0e54a9');
	} else {
		$.each(idArrgongzuozhan, function(index, val) {
			Pubmap.removeOverlay(Map['' + val + 'Overlay'])
		});
		$(_this).css('border', '1px solid transparent')
		Map.removeLayer('workstation');
	}

	gongzuozhanFlag = !gongzuozhanFlag;

}
//视频监控
var shipinjiankongFlag = true;
function shipinjiankongFun(_this) {
	if (shipinjiankongFlag) {
		$.getJSON('' + ctx + '/sys/map/deviceiveMap', function(data) {
			Map.addJSON1([ {
				'type' : 'videos',
				'id' : 'shipinjiankong',
				'data' : data,
				'isShow' : true
			} ])
		})
		$(_this).css('border', '1px solid #0e54a9');
	} else {
		$(_this).css('border', '1px solid transparent');
		Map.removeLayer('shipinjiankong');
	}
	shipinjiankongFlag = !shipinjiankongFlag;
}
//广播站
var broadcastFlag = true;
function guangbozhanFun(_this) {
    if (broadcastFlag) {
        $.getJSON('' + ctx + '/sys/map/deviceBroadcastMap', function(data) {
            Map.addJSON1([ {
                'type' : 'broadcast',
                'id' : 'guangbozhan',
                'data' : data,
                'isShow' : true
            } ])
        })
        $(_this).css('border', '1px solid #0e54a9');
    } else {
        $(_this).css('border', '1px solid transparent');
        Map.removeLayer('guangbozhan');
    }
    broadcastFlag = !broadcastFlag;
}
//机顶盒
var SetTopBoxFlag = true;
function SetTopBoxFun(_this) {
	if (SetTopBoxFlag) {
		$.getJSON('' + ctx + '/sys/map/buildBox', function(data) {
			Map.addJSON1([ {
				'type' : 'topBox',
				'id' : 'SetTopBoxFlag',
				'data' : data,
				'isShow' : true
			} ])
		})
		$(_this).css('border', '1px solid #0e54a9');
	} else {
		$(_this).css('border', '1px solid transparent');
		Map.removeLayer('SetTopBoxFlag');
	}
	SetTopBoxFlag = !SetTopBoxFlag;
}

$(function(){
    $(".pimg").click(function(){
        var _this = $(this);//将当前的pimg元素作为_this传入函数
        imgShow("#outerdiv", "#innerdiv", "#bigimg", _this);
    });
});
function imgShow(outerdiv, innerdiv, bigimg, _this){
    // var src = _this.attr("src");//获取当前点击的pimg元素中的src属性
	var src = _this.src;
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
        }
        imgHeight = realHeight;
        $(bigimg).css("width",imgWidth);//以最终的宽度对图片缩放
        var w = (windowW-imgWidth)/2;//计算图片与窗口左边距
        var h = (windowH-imgHeight)/2;//计算图片与窗口上边距
        $(innerdiv).css({"top":h, "left":w});//设置#innerdiv的top和left属性
        $(outerdiv).fadeIn("fast");//淡入显示#outerdiv及.pimg
    });
    $(outerdiv).click(function(){//再次点击淡出消失弹出层
        $(this).fadeOut("fast");
    });
    event.cancelBubble=true;
}
