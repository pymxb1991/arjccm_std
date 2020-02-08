/*警力警车图层*/
//警员
var jingyuanFlag = true,jingyuanInterval='';
function jingyuanFun(_this) {
	if (jingyuanFlag) {
		jingyuanFunData();
		jingyuanInterval=setInterval(function(){
			jingyuanFunData();
        },policeTime);
		$(_this).css('border', '1px solid #0e54a9');
	} else {
		$(_this).css('border', '1px solid transparent');
		Map.removeLayer('jingyuan');
		clearInterval(jingyuanInterval);
	}
	jingyuanFlag = !jingyuanFlag;
}
function jingyuanFunData(){
	$.getJSON(ctx + '/alarm/bphAlarmInfo/getPeripheralResources?type=8', function(data) {
		if (data != null && data != '' && data != undefined) {
			var features=data.features;
			if(features){
				var len=features.length;
				if(len>0){
					for(var i=0;i<len;i++){
						data.features[i].properties.type='people'//自定义type类型,判断是有视频流
					}
				}
			}
		}
		Map.removeLayer('jingyuan');
		Map.addJSON1([ {
			'type' : 'PopLocation',
			'id' : 'jingyuan',
			'data' : data,
			'isShow' : true
		} ]);
	});
	Map.layersIsShow('PopLocation', true);
}
var xuexiaoFlag = true;
var idArrxuexiao=[];
function xuexiaoFun(_this) {
	var centpoint=[];
	if (xuexiaoFlag) {
		$.getJSON('' + ctx + '/sys/map/ccmOrgNpseMap?type=32', function(data) {
			if (data != null && data != '' && data != undefined) {
				centpoint=data.centpoint;
				var features=data.features;
				if(features){
					var len=features.length;
					idArrxuexiao=[];
					if(len>0){
						for(var i=0;i<len;i++){
							idArrxuexiao.push(features[i].id);
						}
					}
				}
			}
			Map.addJSON3([ {
				'type' : 'Shortcut',
				'data' : data,
				'id':'xuexiao',
				'isShow' : true
			} ])
		})
		$(_this).css('border', '1px solid #0e54a9')
		//Map.goTo(centpoint)
	} else {
		$(_this).css('border', '1px solid transparent');
		$.each(idArrxuexiao,function(index,val){
			Map.map.removeOverlay(Map[''+val+'Overlay'])
		});
		Map.removeLayer('xuexiao');
	}
	xuexiaoFlag = !xuexiaoFlag;
}
//医院
var yiyuanFlag = true;
var publicPlaceFlag = true;
var idArryiyuan=[];
function yiyuanFun(_this) {
	if (yiyuanFlag) {
		$.getJSON('' + ctx + '/sys/map/ccmOrgCommonalityMap?type=2', function(
				data) {
			if (data != null && data != '' && data != undefined) {
				var features=data.features;
				if(features){
					var len=features.length;
					idArryiyuan=[];
					if(len>0){
						for(var i=0;i<len;i++){
							idArryiyuan.push(features[i].id);
						}
					}
				}
			}
			Map.addJSON3([ {
				'type' : 'Shortcut',
				'data' : data,
				'id':'yiyuan',
				'isShow' : true
			} ])
			
		})
		$(_this).css('border', '1px solid #0e54a9')
	} else {
		$.each(idArryiyuan,function(index,val){
			Map.map.removeOverlay(Map[''+val+'Overlay'])
		});
		$(_this).css('border', '1px solid transparent');
		Map.removeLayer('yiyuan');
	}
	yiyuanFlag = !yiyuanFlag;
}
//警车
var jingcheFlag = true,jingcheInterval='';
function jingcheFun(_this) {
	if (jingcheFlag) {
		jingcheFunData();
		jingcheInterval=setInterval(function(){
			jingcheFunData()
        },policeTime);
		$(_this).css('border', '1px solid #0e54a9');
	} else {
		$(_this).css('border', '1px solid transparent');
		Map.removeLayer('jingche');
		clearInterval(jingcheInterval);
	}
	jingcheFlag = !jingcheFlag;
}
function jingcheFunData(){
	$.getJSON(ctx + '/alarm/bphAlarmInfo/getPeripheralResources?type=11', function(data) {
		Map.removeLayer('jingche');
		if(data.features){
			Map.addJSON1([ {
				'type' : 'DanDian',
				'id' : 'jingche',
				'data' : data,
				'isShow' : true
			} ]);
		}
	})
	//Map.layersIsShow('DanDian', true);
}
var jiayouzhanFlag = true;
var idArrjiayouzhan=[];
function jiayouzhanFun(_this) {
	if (jiayouzhanFlag) {
		$.getJSON('' + ctx + '/sys/map/findMapIndustry?type=1', function(data) {
			if(data!=null){
				var features=data.features;
				if(features){
					var len=features.length;
					idArrjiayouzhan=[];
					if(len>0){
						for(var i=0;i<len;i++){
							idArrjiayouzhan.push(features[i].id);
						}
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
		//Map.goTo([ "113.39035820960999", "34.528061628341675" ])
	} else {
		$.each(idArrjiayouzhan,function(index,val){
			Map.map.removeOverlay(Map[''+val+'Overlay'])
		});
		$(_this).css('border', '1px solid transparent');
		Map.removeLayer('jiayouzhan');
	}
	jiayouzhanFlag = !jiayouzhanFlag;
}
//商场超市
var shangchangFlag = true;
var idArrshangchang=[];
function shangchangFun(_this) {
	if (shangchangFlag) {
		$.getJSON('' + ctx + '/sys/map/findMapIndustry?type=2', function(data) {
			if(data!=null){
				var features=data.features;
				if(features){
					var len=features.length;
					idArrshangchang=[];
					if(len>0){
						for(var i=0;i<len;i++){
							idArrshangchang.push(features[i].id);
						}
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
		$.each(idArrshangchang,function(index,val){
			Map.map.removeOverlay(Map[''+val+'Overlay']);
		});
		$(_this).css('border', '1px solid transparent');
		Map.removeLayer('shangchang');
	}
	shangchangFlag = !shangchangFlag;
}
//娱乐场所
var yuleFlag = true;
var idArryule=[];
function yuleFun(_this) {
	if (yuleFlag) {
		$.getJSON('' + ctx + '/sys/map/findMapIndustry?type=3', function(data) {
			if(data!=null){
				var features=data.features;
				if(features){
					var len=features.length;
					idArryule=[];
					if(len>0){
						for(var i=0;i<len;i++){
							idArryule.push(features[i].id);
						}
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
		$.each(idArryule,function(index,val){
			Map.map.removeOverlay(Map[''+val+'Overlay'])
		});
		$(_this).css('border', '1px solid transparent');
		Map.removeLayer('yule');
	}
	yuleFlag = !yuleFlag;
}
//酒店宾馆
var binguanFlag=true;
var idArrbinguan=[];
function binguanFun(_this){
	var id=0;
	if(binguanFlag){
		$.getJSON('' + ctx + '/sys/map/findMapIndustry?type=4', function(data) {
			if(data!=null){
				id=data.id;
				var features=data.features;
				if(features){
					var len=features.length;
					idArrbinguan=[];
					if(len>0){
						for(var i=0;i<len;i++){
							idArrbinguan.push(features[i].id);
						}
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
	$(_this).css('border','1px solid #0e54a9');
	}else{
		$.each(idArrbinguan,function(index,val){
			Map.map.removeOverlay(Map[''+val+'Overlay'])
		});
		$(_this).css('border','1px solid transparent');
		Map.removeLayer('binguan');
	}
	binguanFlag=!binguanFlag;
}
//涉危涉爆
var sheweishebaoFlag = true;
var idArrsheweishebao=[];
function sheweishebaoFun(_this) {
	if (sheweishebaoFlag) {
		$.getJSON('' + ctx + '/sys/map/findMapIndustry?type=5', function(data) {
			if(data!=null){
				var features=data.features;
				if(features){
					var len=features.length;
					idArrsheweishebao=[];
					if(len>0){
						for(var i=0;i<len;i++){
							idArrsheweishebao.push(features[i].id);
						}
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
		$.each(idArrsheweishebao,function(index,val){
			Map.map.removeOverlay(Map[''+val+'Overlay'])
		});
		$(_this).css('border', '1px solid transparent');
		Map.removeLayer('sheweishebao');
	}
	sheweishebaoFlag = !sheweishebaoFlag;
}
//警务室
var jingwushiFlag = true;
var idArrjingwushi=[];
function jingwushiFun(_this) {
	if (jingwushiFlag) {
		Map.removeLayer('jingwushi');
		$.getJSON('' + ctx + '/sys/map/findMapVCcmOrgType?type=3', function(data) {
			if (data != null && data != '' && data != undefined) {
				var features=data.features;
				if(features){
					var len=features.length;
					idArrjingwushi=[];
					if(len>0){
						for(var i=0;i<len;i++){
							idArrjingwushi.push(features[i].id);
						}
					}
				}
			}
			Map.addJSON3([ {
				'type' : 'Shortcut',
				'id' : 'jingwushi',
				'data' : data,
				'isShow' : true
			} ])
		})
		$(_this).css('border', '1px solid #0e54a9');
	} else {
		$.each(idArrjingwushi,function(index,val){
			Map.map.removeOverlay(Map[''+val+'Overlay'])
		});
		$(_this).css('border', '1px solid transparent');
		Map.removeLayer('jingwushi');
	}
	jingwushiFlag = !jingwushiFlag;
}
//工作站
var gongzuozhanFlag = true;
var idArrgongzuozhan=[];
function gongzuozhanFun(_this) {
	if (gongzuozhanFlag) {
		Map.removeLayer('gongzuozhan');
		$.getJSON('' + ctx + '/sys/map/findMapVCcmOrgType?type=4', function(data) {
			if (data != null && data != '' && data != undefined) {
				var features=data.features;
				if(features){
					var len=features.length;
					idArrgongzuozhan=[];
					if(len>0){
						for(var i=0;i<len;i++){
							idArrgongzuozhan.push(features[i].id);
						}
					}
				}
			}
			Map.addJSON3([ {
				'type' : 'Shortcut',
				'id' : 'gongzuozhan',
				'data' : data,
				'isShow' : true
			} ])
		})
		$(_this).css('border', '1px solid #0e54a9');
	} else {
		$.each(idArrgongzuozhan,function(index,val){
			Map.map.removeOverlay(Map[''+val+'Overlay'])
		});
		$(_this).css('border', '1px solid transparent');
		Map.removeLayer('gongzuozhan');
	}
	gongzuozhanFlag = !gongzuozhanFlag;
}
//视频监控
var shipinjiankongFlag = true;
function shipinjiankongFun(_this) {
	if (shipinjiankongFlag) {
		$.getJSON('' + ctx + '/sys/map/deviceiveMap', function(data) {
			if (data != null && data != '' && data != undefined) {
				var features = data.features;
				if(features){
					var len = features.length;
					if (len > 0) {
						for (var i = 0; i < len; i++) {
							data.features[i].properties.type = 'video'// 自定义type类型,判断是有视频流
							data.features[i].properties.video['id'] =data.features[i].id
						}
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
function PopInfoFun(_this) {
	var popinfo = $(_this).attr('popinfo');
	var popinfoData = JSON.parse(JSON.parse(popinfo));
	// 捕获页
	var len = popinfoData.length;
    var minjing='';
    var fujing='';
	var html = "";
	html += '<div class="layer-common" style="width: 96.3%;height: 92.3%; position: relative;padding: 14px 0 0 0;">'
/*	html += '<div class="layer-common-header" style="top: 1px; left: 36px;display: inline-block; padding: 5px 30px; border: 1px solid #0343a3; transform: skew(-20deg); background: #0343a3;color: #fff; font-weight: bold; position: absolute; z-index: 9999;">'
	html += '<div style=" transform: skew(20deg); white-space: nowrap;font-size: 15px;">人员信息</div>'
	html += '</div>'*/
	html += '<div class="layer-show  layer-common-center" style="padding: 10px 10px 5px 10px; overflow-x:hidden;overflow-y:auto;width: 100%;height: 100%;">'
	html += '<div class="well">'
	minjing+='<ul style="border-bottom:1px dashed #fff;margin-bottom:5px;overflow-y: auto;overflow-x: hidden;width:100%;height:auto;margin:0">民警:';
	minjing+='<li class="clearfix" style="overflow-y: auto;overflow-x: hidden;width:100%;height:auto">';
	fujing+='<ul style="overflow-y: auto;overflow-x: hidden;width:100%;height:auto;margin:0">辅警:';
	fujing+='<li  class="clearfix" style="overflow-y: auto;overflow-x: hidden;width:100%;height:auto">';
	for (var i = 0; i < len; i++) {
		var src=popinfoData[i]['images']||popinfoData[i]['photo'];
		var telephone=popinfoData[i]['telephone'] ||popinfoData[i]['phone'] ;
		if(popinfoData[i]['no']){//新密市警务室、工作站
			var name=popinfoData[i]['name'];
			var nameType=name.split('（');
			if(nameType[1]){
				var nameType1=name.split('（')[1].split('）')[0];
				if(nameType1=="民警"){
					minjing += '<div class="well-item">'
					minjing += '<div class="jingcha-name correct">';
					minjing += '<div class="jiachatouxiang" style="width:100%;height:100%;"><img src="' + src + '" /></div>';
					minjing += ' <p class="test2name">' + name+ '</p>';
					// html+='<p>'+popinfoData[i]['警号']+'</p>';
					minjing += '</div>';
					minjing += '<div class="opposite"><div>';
					minjing += '<table>';
					minjing += '<tr>';
					minjing += '<td align="right">姓名：</td><td align="left">' + popinfoData[i]['name'] + '</td>';
					minjing += '</tr>';
					minjing += '<tr>';
					minjing += '<td align="right">性别：</td><td align="left">' + (popinfoData[i]['sex'] == "1" ? '女' : '男') + '</td>';
					minjing += '</tr>';
					minjing += '<tr>';
					if(popinfoData[i]['service']){
						minjing += '<td align="right">职位：</td><td align="left">' + popinfoData[i]['service'] + '</td>';
					}
		            if(popinfoData[i]['no']){
		            	minjing += '<td align="right">警号：</td><td align="left">' + popinfoData[i]['no'] + '</td>';
					}
		            minjing += '</tr>';
					minjing += '<tr>';
					minjing += '<td align="right">电话：</td><td align="left">' + (telephone?telephone:"") + '</td>';
					minjing += '</tr>';
					minjing += '</table>';
					minjing += '</div></div>';
					minjing += '</div>'
				}else{
					fujing += '<div class="well-item">'
					fujing += '<div  class="jingcha-name correct">';
					fujing += '<div  class="jiachatouxiang"  style="width:100%;height:100%;"><img src="' + src + '" /></div>';
					fujing += ' <p class="test2name">' + name+ '</p>';
					// html+='<p>'+popinfoData[i]['警号']+'</p>';
					fujing += ' </div>';
					fujing += '<div class="opposite"><div>';
					fujing += '<table>';
					fujing += '<tr>';
					fujing += '<td  align="right" >姓名：</td><td align="left">' + popinfoData[i]['name'] + '</td>';
					fujing += '</tr>';
					fujing += '<tr>';
					fujing += '<td align="right" >性别：</td><td align="left">' + (popinfoData[i]['sex'] == "1" ? '女' : '男') + '</td>';
					fujing += '</tr>';
					fujing += '<tr>';
					if(popinfoData[i]['service']){
						fujing += '<td align="right" >职位：</td><td align="left">' + popinfoData[i]['service'] + '</td>';
					}
		            if(popinfoData[i]['no']){
		            	fujing += '<td align="right" >警号：</td><td align="left">' + popinfoData[i]['no'] + '</td>';
					}
		            fujing += '</tr>';
		            fujing += '<tr>';
		            fujing += '<td align="right" >电话：</td><td align="left">' + (telephone?telephone:"")  + '</td>';
		            fujing += '</tr>';
		            fujing += '</table>';
		            fujing += '</div></div>';
		            fujing += '</div>';
				}
			}else{
				fujing += '<div class="well-item">'
				fujing += '<div class="jingcha-name correct">';
				fujing += '<div class="jiachatouxiang"  style="width:100%;height:100%;"><img src="' + src + '" /></div>';
				fujing += '<p class="test2name">' + name+ '</p>';
				// html+='<p>'+popinfoData[i]['警号']+'</p>';
				fujing += ' </div>';
				fujing += '<div class="opposite"><div>';
				fujing += '<table>';
				fujing += '<tr>';
				fujing += '<td align="right">姓名：</td><td align="left">' + popinfoData[i]['name'] + '</td>';
				fujing += '</tr>';
				fujing += '<tr>';
				fujing += '<td align="right">性别：</td><td align="left">' + (popinfoData[i]['sex'] == "1" ? '女' : '男') + '</td>';
				fujing += '</tr>';
				fujing += '<tr>';
				if(popinfoData[i]['service']){
					fujing += '<td align="right">职位：</td><td align="left">' + popinfoData[i]['service'] + '</td>';
				}
	            if(popinfoData[i]['no']){
	            	fujing += '<td align="right">警号：</td><td align="left">' + popinfoData[i]['no'] + '</td>';
				}
	            fujing += '</tr>';
	            fujing += '<tr>';
	            fujing += '<td align="right" >电话：</td><td align="left">' + (telephone?telephone:"")  + '</td>';
	            fujing += '</tr>';
	            fujing += '</table>';
	            fujing += '</div></div>';
	            fujing += '</div>'
			}
		}else{//公共机构
			html += '<div class="well-item">'
		    html += '<div class="jingcha-name correct">';
			html += '<div class="jiachatouxiang"  style="width:100%;height:100%;"><img src="' + src + '" /></div>';
			html += '<p class="test2name">' + popinfoData[i]['name'] + '</p>';
			// html+='<p>'+popinfoData[i]['警号']+'</p>';
			html += '</div>';
			html += '<div class="opposite"><div>';
			html += '<table>';
			html += '<tr>';
			html += '<td align="right">姓名：</td><td align="left">' + popinfoData[i]['name'] + '</td>';
			html += '</tr>';
			html += '<tr>';
			html += '<td align="right" >性别：</td><td align="left">' + (popinfoData[i]['sex'] == "1" ? '女' : '男') + '</td>';
			html += '</tr>';
			html += '<tr>';
			if(popinfoData[i]['service']){
				html += '<td align="right">职位：</td><td align="left">' + popinfoData[i]['service'] + '</td>';
			}
            if(popinfoData[i]['no']){
				html += '<td align="right">警号：</td><td align="left">' + popinfoData[i]['no'] + '</td>';
			}
			html += '</tr>';
			html += '<tr>';
			html += '<td align="right">电话：</td><td align="left">' + (telephone?telephone:"") + '</td>';
			html += '</tr>';
			html += '</table>';
			html += '</div></div>';
			html += '</div>'
		}
	}
	minjing+='</li>';
	fujing+='</li>';
	minjing+='</ul>';
	fujing+='</ul>';
	if(popinfoData[0]['no']){//新密市警务室、工作站
		html+=minjing;
		html+=fujing;
	}
	html += '</div>'
	html += '</div>'
	html += '</div>'
	layer.open({
		type : 1,
		shade : false,
		title : '人员信息', // 不显示标题
		content : html, // 捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响
		area : [ '800px', '610px' ],
		id:"PopInfoLayer",
		cancel : function() {
			// layer.msg('捕获就是从页面已经存在的元素上，包裹layer的结构', {time: 5000, icon:6});
		}
	});
	$(".well-item").hover(function() {
		$(this).find(".correct").children().removeClass('test');
		$(this).find(".correct").children().removeClass('test2');
		$(this).find(".opposite").children().removeClass('test');
		$(this).find(".opposite").children().removeClass('test2');
		$(this).find(".correct").children().addClass("test");
		$(this).find(".opposite").children().addClass('test2');
	}, function() {
		$(this).find(".correct").children().removeClass('test');
		$(this).find(".opposite").children().removeClass('test');
		$(this).find(".correct").children().removeClass("test2");
		$(this).find(".opposite").children().removeClass("test2");
		$(this).find(".correct").children().addClass("test2");
		$(this).find(".opposite").children().addClass('test');
	});
}
