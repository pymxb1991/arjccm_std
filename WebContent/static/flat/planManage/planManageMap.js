//目标-设置目标
function addTarget(){
	Map.commandAddTarget(addTargetPoint);
}
//目标-获取目标坐标点
function addTargetPoint(point){
	var html='';
	html+='<div style="margin:5px 10px 5px 20px;">';
	html+='<div style="line-height:24px;">经度：'+point[0]+'</div>';
	html+='<div style="line-height:24px;">纬度：'+point[1]+'</div>';
	html+='<div style="line-height:24px;">确定该位置设定目标？</div>';
	html+='</div>';
	layer.open({
		type : 1,
		title : "设置目标",
		area : [ "260px", "180px" ],
		maxmin : false,
		btn : [ "确定", "取消" ], //
		id:'point',
		content :html,
		end : function() {
		},
		yes : function(index, layero) {
			drawTargetPoint(point);
			layer.close(index); // 如果设定了yes回调，需进行手工关闭
		}
	})
}
var cont=0;
var pointArr=[];
//目标-画点
function drawTargetPoint(point){
	cont++;
	var x=point[0];
	var y=point[1];
	pointArr.push(point);
	var pointData = {
		"type":"FeatureCollection",
        "features":[{
        	"type":"Feature",
        	"id":'marker',
        	"properties":{
        		"name": '目标'+cont,
        		"icon": 'marker.png',
        	},
        	"geometry":{
        		"type":"Point",
        		"coordinates":point
        	}
        }]
	}
	Map.addJSON1([ {
		'type' : 'marker',
		'data' : pointData,
		'isShow' : true
	} ])
	if(pointArr.length==2){
		Map.drakLine(pointArr);
	}
}