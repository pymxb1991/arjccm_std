//视频监控
$(function(){
	//加载地图
	initMap();
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
	//视频监控
	refreshVideoTree();
	//视频监控
	refreshVideo();
	 // 视频树搜索
    $('#videoButton').click(function(){
    	var secVideoVal=$('#secVideo').val();
    	if(secVideoVal==""){
    		refreshVideoTree();
    	}else{
    		filter('ztreeVideo','secVideo');
    	}
    })

}

var settingVideo = {
	data:{simpleData:{enable:true,idKey:"id",pIdKey:"pId",rootPId:'0'}},
	callback:{
		onClick:function(event, treeId, treeNode){
			var id = treeNode.id;
			if(treeNode.type != 'camera') {
				return;
			}
			videoLocation(id);
		}
	}
};
	//全市警力-视频
function refreshVideoTree(){
	$.getJSON(ctx+"/ccmsys/ccmLiveVideo/treeData",function(data){
		$.fn.zTree.init($("#ztreeVideo"), settingVideo, data);
	});
}

function refreshVideo(){
	$.getJSON(ctx + '/sys/map/deviceiveMap', function(data) {
		if (data != null && data != '' && data != undefined) {
			var features = data.features;
			var len = features.length;
			if (len > 0) {
				for (var i = 0; i < len; i++) {
					data.features[i].properties.type = 'video';// 自定义type类型,判断是有视频流
					data.features[i].properties.video['id'] =data.features[i].id;
				}
			}
		}
		Map.addJSON1([ {
			'type' : 'videos',
			'id' : 'shipinjiankong',
			'data' : data,
			'isShow' : true
		} ])
	})
	
}
//视频定位
function videoLocation(id){
	$.getJSON(ctx+"/ccmsys/ccmLiveVideo/findVideoInfo",{'id':id},function(data){
		if(data.coordinate == '' || data.coordinate == undefined || data.coordinate == null){
			$.jBox.tip('暂无当前位置信息');
			return;
		}
		var x=data.coordinate.split(',')[0];
		var y=data.coordinate.split(',')[1];
	/*	var point = {
			"type":"FeatureCollection",
	        "features":[{
	        	"type":"Feature",
	        	"id":data.id,
	        	"properties":{
	        		"name": data.name,
	        		"icon": 'video.png',
	        		'type' : 'video',//自定义type类型,判断是视频监控
             		'video':{
             			"id":data.id,
             			'ip':data.ip,
             		},
            		"info":{
            			'设备名称':data.name,
            			'设备编号':data.code,
            			'IP地址':data.ip,
            			'设备地址':data.address,
            			'经度':x,
            			'维度':y,
            		}
	        	},
	        	"geometry":{
	        		"type":"Point",
	        		"coordinates":[x,y]
	        	 }
	        	}]
	        }
	    Map.removeLayer('video');//清除
		Map.addJSON1([ {
			'type' : 'video',
			'data' : point,
			'isShow' : true
		} ])*/
		Map.goTo([Number(x),Number(y)]);
	});
}
