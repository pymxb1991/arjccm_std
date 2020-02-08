/**
 * 地图地址等配置文件
 */

/**
 * *******************************************滨海新区,超算发布*******************************************
 */

/*// 分辨率 卫星地图
var resolutions = [ 0.00243656806990755,0.00121828403495378,0.000609142017476888,0.000304571008738444,0.000152285504369222,7.6142752184611E-05,3.80713760923055E-05,1.90356880461528E-05,9.51784402307638E-06,4.75892201153819E-06,2.37946100576909E-06,1.18973050288455E-06 ];
// 分辨率 普通地图
var resolutions1 = [ 1.40625, 0.703125, 0.3515625, 0.17578125, 0.087890625,
		0.0439453125, 0.02197265625, 0.010986328125, 0.0054931640625,
		0.00274658203125, 0.001373291015625, 0.0006866455078125,
		0.00034332275390625, 0.000171661376953125, 8.58306884765625E-05,
		4.29153442382813E-05, 2.14576721191406E-05, 1.07288360595703E-05,
		5.36441802978516E-06, 2.68220901489258E-06 ];
// 影像图和普通地图加载
var baseUrlD = [{
		'type' : '1',
		'id' : 'yingxiang',
		'name' : '影像图 ',
		'url' : 'http://59.193.204.40/51b153b49e2542550cd9cfc43d84201408f44ac0/Tile/ArcGISRest/BHRHDOMCGCS2000.gis/tile/{z}/{y}/{x}',
		'isShow' : true,
		'resolutions' : resolutions,
		'tileSize' : '512',
		'origin' : [ -400.0, 400.0 ],
	},
	{
		'type' : '1',
		'id' : 'putong',
		'name' : '普通 ',
		'url' : 'http://59.193.204.40/3f2b9d6f43ef580cd3e83b23023deb0be2189d9e/Tile/ArcGISRest/BHMAPCGCS2000.gis/tile/{z}/{y}/{x}',
		'isShow' : false,
		'resolutions' : resolutions1,
		'tileSize' : '256',
		'origin' : [ -180.0, 90.0 ],
	}];
// 影像图加载
var baseUrlY = [ {
	'type' : '1',
	'id' : 'yingxiang',
	'name' : '影像图 ',
	'url' : 'http://59.193.204.40/51b153b49e2542550cd9cfc43d84201408f44ac0/Tile/ArcGISRest/BHRHDOMCGCS2000.gis/tile/{z}/{y}/{x}',
	'isShow' : true,
	'resolutions' : resolutions,
	'tileSize' : '512',
	'origin' : [ -400.0, 400.0 ],
} ];*/
/**
 * *******************************************天地地图
 */
/*var baseUrlT1 = [ {
	'type' : '2',
	'id' : 'yingxiang',
	'name' : '影像图 ',
	'url' : 'http://t0.tianditu.com/DataServer?T=img_w&x={x}&y={y}&l={z}',
	'isShow' : true,
}, {
	'type' : '2',
	'id' : 'putong',
	'name' : '普通 ',
	'url' : 'http://t0.tianditu.com/DataServer?T=vec_w&x={x}&y={y}&l={z}',
	'isShow' : false,
} ];
// 叠加银川地图
var urlArr = [ {
	'name' : '三级网格 ',
	'url' : 'http://localhost:6080/arcgis/rest/services/ThreeGrid/MapServer',
	'isShow' : true
} ];*/


/**
 * *******************************************滨海新区*******************************************
 */

/**
 * *******************************************杭州道*******************************************
 */
//中心点坐标
/*var centerCoordinate = [ 117.655020, 39.035450 ],centerCoordinateSituation=[117.655020,39.035450],centerCoordinatePop=[117.655020,39.03250];//人口首页

var zoomIndex=15.5;//当前层级-地图首页
var zoomApp=18;//当前层级-地图标注
var zoomPop =14.4;//当前层级-人口
var baseUrlT = [ {
	'type' : '3',
	'id' : 'yingxiang',
	'name' : '影像图 ',
	'url' : 'http://192.168.1.106:6080/arcgis/rest/services/hzdCaiJian/MapServer',
	'isShow' : true,
}, {
	'type' : '3',
	'id' : 'putong',
	'name' : '普通 ',
	'url' : 'http://192.168.1.106:6080/arcgis/rest/services/hzd2/MapServer',
	'isShow' : false,
}]*/

/**
 * *******************************************津南区*******************************************
 */
//中心点坐标
/*var centerCoordinate = [117.29900836944581,39.03772830963135],centerCoordinateSituation=[117.29900836944581,39.03772830963135];

var zoomIndex=15.5;//当前层级-地图首页
var zoomApp=18;//当前层级-地图标注
var zoomPop =14.4;//当前层级-人口
var baseUrlT = [ {
	'type' : '2',
	'id' : 'yingxiang',
	'name' : '影像图 ',
	'url' : 'http://t0.tianditu.com/DataServer?T=img_w&x={x}&y={y}&l={z}',
	'isShow' : true,
}, {
	'type' : '2',
	'id' : 'putong',
	'name' : '普通 ',
	'url' : 'http://t0.tianditu.com/DataServer?T=vec_w&x={x}&y={y}&l={z}',
	'isShow' : false,
}];*/

/**
 * *******************************************新密*******************************************
 */

/*var centerCoordinate = [113.44843243227326,34.49770899153992],centerCoordinateSituation=[113.44843243227326,34.49770899153992],centerCoordinatePop=[113.44843243227326,34.49770899153992];//人口首页
var zoomIndex=12;//当前层级-地图首页
var zoomApp=18;//当前层级-地图标注
var zoomPop =14.4;//当前层级-人口

var baseUrlT = [ {
	'type' : '2',
	'id' : 'yingxiang',
	'name' : '影像图 ',
	'url' : 'http://t0.tianditu.com/DataServer?T=img_w&x={x}&y={y}&l={z}',
	'isShow' : true,
}, {
	'type' : '2',
	'id' : 'putong',
	'name' : '普通 ',
	'url' : 'http://t0.tianditu.com/DataServer?T=vec_w&x={x}&y={y}&l={z}',
	'isShow' : false,
}];*/
//地图配置信息
//中心点坐标
var centerCoordinate=[],zoomIndex=null,zoomApp=null,zoomPop=null,resolutions=[],tileSize=null,origin=[],isShow='',baseId="",baseUrlT=[],centerCoordinateSituation=[],centerCoordinatePop=[];
$.ajax({
		url: ctx+'/sys/sysConfig/getMapConfigWithAJAX', 
		async: false,
		success: function(data){

		var sysMapConfig=data.sysMapConfig;
		centerCoordinate=[Number(sysMapConfig.center.split(',')[0]),Number(sysMapConfig.center.split(',')[1])];
		centerCoordinateSituation=centerCoordinate;
		centerCoordinatePop=centerCoordinate;
		zoomIndex=Number(sysMapConfig.zoom);
		zoomApp=Number(sysMapConfig.zoom);
		zoomPop=Number(sysMapConfig.zoom);
		minZoom=Number(sysMapConfig.min);
		maxZoom=Number(sysMapConfig.max);
		var showType=sysMapConfig.showType;
		var ImgShow=false,elecShow=false;
		if(showType=='0'){//默认显示影像图
			ImgShow=true;
			elecShow=false;
		}else{//默认显示电子地图
			ImgShow=false;
			elecShow=true;
		}
		if(sysMapConfig.imageMapUrl!=""){//影像图
			if(sysMapConfig.imgType=="1"){//瓦片地图
				var imgResolutionsData=sysMapConfig.imgResolutions.split(',');
				var len=imgResolutionsData.length;
				var imgResolutionsArr=[];
				if(len>0){
					for(var i=0;i<len;i++){
						imgResolutionsArr.push(Number(imgResolutionsData[i]))
					}
				}

				var imgOrigin=sysMapConfig.imgOrigin.split(',');
				var imgOriginLen=imgOrigin.length;
				var imgOriginArr=[];
				if(imgOriginLen>0){
					for(var i=0;i<imgOriginLen;i++){
						imgOriginArr.push(Number(imgOrigin[i]))
					}
				}

				var imgTileSize=sysMapConfig.imgTileSize;
				
				baseUrlT.push({
					'type' : sysMapConfig.imgType,
					'id' : 'yingxiang',
					'name' : '影像图 ',
					'url' : sysMapConfig.imageMapUrl,
					'isShow' : ImgShow,
					'resolutions' : imgResolutionsArr,
					'tileSize' : imgTileSize,
					'origin' : imgOriginArr,
				})
			}else if(sysMapConfig.imgType=="2"){//天
				baseUrlT.push({
					'type' : sysMapConfig.imgType,
					'id' : 'yingxiang',
					'name' : '影像图 ',
					'url' : sysMapConfig.imageMapUrl,
					'isShow' : ImgShow,
				})
				baseUrlT.push({
					'type' : sysMapConfig.elcType,
					'id' : 'yingxiang1',
					'name' : '影像图 ',
					'url' :"http://t0.tianditu.com/DataServer?T=cva_w&x={x}&y={y}&l={z}&tk=b9d8e3bba847d276a756cde1a669e38d",
					'isShow' : true,
				})
			}else if(sysMapConfig.imgType=="3"){//矢量图
				baseUrlT.push({
					'type' : sysMapConfig.imgType,
					'id' : 'yingxiang',
					'name' : '影像图 ',
					'url' : sysMapConfig.imageMapUrl,
					'isShow' : ImgShow,
				})
			}
			
		}
			
		if(sysMapConfig.electronicMapUrl!=""){//电子地图
			if(sysMapConfig.elcType=="1"){//瓦片地图
				var elcResolutions=sysMapConfig.elcResolutions.split(',');
				var elcOrigin=sysMapConfig.elcOrigin.split(',');
				var elcTileSize=sysMapConfig.elcTileSize;
				var len=elcResolutions.length;
				var elcResolutionsArr=[];
				if(len>0){
					for(var i=0;i<len;i++){
						elcResolutionsArr.push(Number(elcResolutions[i]))
					}
				}

				var elcOriginLen=elcOrigin.length;
				var elcOriginArr=[];
				if(elcOriginLen>0){
					for(var i=0;i<elcOriginLen;i++){
						elcOriginArr.push(Number(elcOrigin[i]))
					}
				}

				baseUrlT.push({
					'type' : sysMapConfig.elcType,
					'id' : 'dianzi',
					'name' : '电子地图 ',
					'url' : sysMapConfig.electronicMapUrl,
					'isShow' : elecShow,
					'resolutions' : elcResolutionsArr,
					'tileSize' : elcTileSize,
					'origin' : elcOriginArr,
				})
			}else if(sysMapConfig.elcType=="2"){//天
				baseUrlT.push({
					'type' : sysMapConfig.elcType,
					'id' : 'dianzi',
					'name' : '电子地图 ',
					'url' : sysMapConfig.electronicMapUrl,
					'isShow' : elecShow,
				})
				baseUrlT.push({
					'type' : sysMapConfig.elcType,
					'id' : 'dianzi1',
					'name' : '电子地图 ',
					'url' :"http://t0.tianditu.com/DataServer?T=cva_w&x={x}&y={y}&l={z}&tk=b9d8e3bba847d276a756cde1a669e38d",
					'isShow' : true,
				})
			}else if(sysMapConfig.elcType=="3"){//矢量图
				baseUrlT.push({
					'type' : sysMapConfig.elcType,
					'id' : 'dianzi',
					'name' : '电子地图 ',
					'url' : sysMapConfig.electronicMapUrl,
					'isShow' : elecShow,
				})
			}
		 }

		if(sysMapConfig.keshihuaMapUrl!=""){
			baseUrlT.push({
				'url':sysMapConfig.keshihuaMapUrl
			})
		}

	  }
	})
//新密+天地图
//{"center":"113.44843243227326,34.49770899153992","elcOrigin":"-180.0,90.0","elcResolutions":"6.866455078154047E-4,3.433227539077022E-4,1.7166137695385112E-4,8.583068847692556E-5,4.291534423846279E-5,2.1457672119231396E-5,1.0728836059615695E-5,5.364418029807847E-6,2.6822090149039237E-6,1.3411045074519619E-6","elcTileSize":"256","elcType":"2","electronicMapUrl":"http://t0.tianditu.com/DataServer?T=vec_w&amp;x={x}&amp;y={y}&amp;l={z}","imageMapUrl":"http://41.35.1.73:6080/arcgis/rest/services/yx/MapServer/tile/{z}/{y}/{x}","imgOrigin":"-180.0,90.0","imgResolutions":"6.866455078154047E-4,3.433227539077022E-4,1.7166137695385112E-4,8.583068847692556E-5,4.291534423846279E-5,2.1457672119231396E-5,1.0728836059615695E-5,5.364418029807847E-6,2.6822090149039237E-6,1.3411045074519619E-6","imgTileSize":"256","imgType":"1","max":"18","min":"14.4","projection":"0","showType":"0","zoom":"12"}
//新密+行政
//{"center":"113.44843243227326,34.49770899153992","elcOrigin":"-180.0,90.0","elcResolutions":"6.866455078154047E-4,3.433227539077022E-4,1.7166137695385112E-4,8.583068847692556E-5,4.291534423846279E-5,2.1457672119231396E-5,1.0728836059615695E-5,5.364418029807847E-6,2.6822090149039237E-6,1.3411045074519619E-6","elcTileSize":"256","elcType":"1","electronicMapUrl":"http://41.35.1.73:6080/arcgis/rest/services/sl/MapServer/tile/{z}/{y}/{x}","imageMapUrl":"http://41.35.1.73:6080/arcgis/rest/services/yx/MapServer/tile/{z}/{y}/{x}","imgOrigin":"-180.0,90.0","imgResolutions":"6.866455078154047E-4,3.433227539077022E-4,1.7166137695385112E-4,8.583068847692556E-5,4.291534423846279E-5,2.1457672119231396E-5,1.0728836059615695E-5,5.364418029807847E-6,2.6822090149039237E-6,1.3411045074519619E-6","imgTileSize":"256","imgType":"1","max":"18","min":"14.4","projection":"0","showType":"0","zoom":"12"}
//滨海
//{"center":"117.655020,39.035450","elcOrigin":"-400.0, 400.0","elcResolutions":"0.00243656806990755,0.00121828403495378,0.000609142017476888,0.000304571008738444,0.000152285504369222,7.6142752184611E-05,3.80713760923055E-05,1.90356880461528E-05,9.51784402307638E-06,4.75892201153819E-06,2.37946100576909E-06,1.18973050288455E-06","elcTileSize":"512","elcType":"2","electronicMapUrl":"http://192.168.1.106:6080/arcgis/rest/services/hzdCaiJian/MapServer","imageMapUrl":"http://192.168.1.106:6080/arcgis/rest/services/hzdCaiJian/MapServer","imgOrigin":"-400.0, 400.0","imgResolutions":"0.00243656806990755,0.00121828403495378,0.000609142017476888,0.000304571008738444,0.000152285504369222,7.6142752184611E-05,3.80713760923055E-05,1.90356880461528E-05,9.51784402307638E-06,4.75892201153819E-06,2.37946100576909E-06,1.18973050288455E-06","imgTileSize":"256","imgType":"3","max":"19","min":"7","projection":"0","showType":"0","zoom":"15.5"}


