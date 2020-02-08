/**
 * V 1.0 2018-3-27 13:36:50
 */
$(function() {
	var satellite_layer = new ol.layer.Tile({
		title: "影像图",
		source: new ol.source.TileArcGISRest({
			url: 'http://map.geoq.cn/arcgis/rest/services/ChinaOnlineCommunity/MapServer'
		})
	});
	var map = new ol.Map({
		target: 'map',
		layers: [satellite_layer],
		view: new ol.View({
			zoom:16.3,
			maxZoom:18,
			minZoom: 14.3,
			center : ol.proj.transform([ 117.654420, 39.035450], 'EPSG:4326', 'EPSG:3857')
		})
	}); 
	
	map.on('singleclick', function(e){
		var lonlat = map.getCoordinateFromPixel(e.pixel);
		var coordinate = ol.proj.transform(lonlat,"EPSG:3857", "EPSG:4326")
		$("#sLongitude").attr("value", "");
		$("#sLongitude").attr("value", coordinate[0]);
		$("#sLatitude").attr("value", "");
		$("#sLatitude").attr("value", coordinate[1]);
    })
});
//腾讯地图
/*$(function() {
	var geocoder, citylocation, map, marker = null;
	var markersArray = [];
	var x = $("#sLongitude").val();
	var y = $("#sLatitude").val();
	var center = new qq.maps.LatLng(y, x);
	map = new qq.maps.Map(document.getElementById('container'), {
		center : center,
		zoom : 13
	});
	geocoder = new qq.maps.Geocoder({
		complete : function(result) {
			map.setCenter(result.detail.location);
			var marker = new qq.maps.Marker({
				map : map,
				position : result.detail.location
			});
		}
	});
	marker = new qq.maps.Marker({
		position : new qq.maps.LatLng(y, x),
		map : map
	});
	// 获取城市列表接口设置中心点
	if (y == '' || x == '') {
		citylocation = new qq.maps.CityService({
			complete : function(result) {
				map.setCenter(result.detail.latLng);
			}
		});
		// 调用searchLocalCity();方法 根据用户IP查询城市信息。
		citylocation.searchLocalCity();
	}

	// 绑定单击事件添加参数
	qq.maps.event.addListener(map, 'click', function(event) {
		// alert('您点击的位置为: [' + event.latLng.getLat() + ', ' +
		// event.latLng.getLng() + ']');
		qq.maps.event.addListener(map, 'click', function(event) {
			marker.setMap(null);
			$("#sLongitude").attr("value", "");
			$("#sLongitude").attr("value", event.latLng.getLng());
			$("#sLatitude").attr("value", "");
			$("#sLatitude").attr("value", event.latLng.getLat());
			marker = new qq.maps.Marker({
				position : new qq.maps.LatLng(event.latLng.getLat(),
						event.latLng.getLng()),
				map : map
			});
		});
	});
	geocoder = new qq.maps.Geocoder({
		complete : function(result) {
			marker.setMap(null);
			map.setCenter(result.detail.location);
			marker = new qq.maps.Marker({
				map : map,
				position : result.detail.location
			});
			$("#sLatitude").attr("value", marker.position.lat);
			$("#sLongitude").attr("value", marker.position.lng);
		}
	});
});*/