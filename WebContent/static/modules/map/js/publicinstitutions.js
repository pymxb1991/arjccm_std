$(function(){
	
	$('.pubMapDialog-close').click(function(){
		$('.pubMapDialog').hide()
	})
	
    /*布局改变*/
    $(".relevance-bg").click(function(){
        $(".relevance").animate({"width":"400px","height":"150px"},300)
        $(".relevance-bg").hide();
        $('.unfold').css('bottom','0');
        $('.unfold').css('left','0')
        $('.map-2').hide();
        $('.map-4').show();
        punMapFun()
    })
    $(".re-close").click(function(){
        $(".relevance").animate({"width":0,"height":0},300)
        $(".relevance-bg").show()
         $('.unfold').css('top','63%');
        $('.unfold').css('left','98px')
        $('.map-4').hide();
        $('.map-2').show();
        /*mapShequ();*/
    })
})
var Map1;
var map;
var thisMap;
//加载公共机构
function  punMapFun(){
	// 地图默认数据设置
	var defaultPrams = {
		id : 'pubMap',//34.5363170808,113.3659613562
		centerCoordinate : [ 113.3595613562, 34.5373170808 ],
		zoom : 15,
		maxZoom : 19,
		minZoom : 9.8,		
		baseUrl:baseUrlT,
		zoomShowOrHide : false,// 缩小放大
		overviewMap : false,
		selectPointerFlag:false
	// 鹰眼图
	}
    Map1 = new ArjMap.Map(defaultPrams);
	// 加载地图
	Map1.drawMapSituation();
	//mapData()
	 map=Map1.map;
	thisMap=Map1;
}
function mapData(){
	$.getJSON('' + ctx + '/sys/map/buildmanageMapType?type=1',
			function(data) {
		
		
		var Data={
				"type": "FeatureCollection",
				"centpoint": ["113.341822", "34.541205"],
				"features": [{
					"type": "Feature",
					"id": "01c24f6e637942a48557af0000000031",
					"properties": {
						"name": "新密市人和路小学",
						"icon": "xuexiao3.png",
						"info": {
							"单元数": "2",
							"层数": "30",
							"楼栋名称": "10号楼",
							"电话": "17603711728",
							"地址": "新密市西大街88号"
						},
						"coordinateCentre": ["113.33453511647842", "34.53860150834096"]
					},
					"geometry": {
						"type": "Polygon",
						"coordinates": [
							[
								["113.33453511647842", "34.53860150834096"],
								["113.33453511647842", "34.53860150834096"],
							]
						]
					}
				},{
					"type": "Feature",  
					"id": "01c24f6e637942a48557ae0000000030",
					"properties": {
						"name": "家园网吧",
						"icon": "yule3.png",
						"info": {
							"单元数": "1",
							"层数": "6",
							"楼栋名称": "5号楼",
							"电话": "",
							"地址": "政通路附近"
						},  
						"coordinateCentre": ["113.34599168938928", "34.535446514079794"],
					},
					"geometry": {
						"type": "Polygon",
						"coordinates": [
							[
								["113.34599168938928", "34.535446514079794"],
								["113.34599168938928", "34.535446514079794"],
							]
						]
					}
				},]
			}
		console.log(data)
		Map1.addJSON3([ {
					'type' : 'keyPerson',
					'id':'sheweishebao',
					'data' : Data,
					'isShow' : true
				} ])
			})
}

var jingwushiFlag=true;
function jingwushiFun(_this){
	
	
	var Data={
			"type": "FeatureCollection",
			"centpoint": ["113.341822", "34.541205"],
			"features": [{
				"type": "Feature",  
				"id": "01c24f6e637942a48557ae0000000041",
				"properties": {
					"name": "长乐路警务室",
					"icon": "gongan3.png",
					"type":'警务室',
					"info": {
						"民警": "1",
						"层数": "6",
						"楼栋名称": "5号楼",
						"电话": "",
						"地址": "新密市西大街599号"
					},
					"coordinateCentre": ["113.3586326175677", "34.53555866426642"],
				},
				"geometry": {
					"type": "Polygon",
					"coordinates": [
						[
							["113.3586326175677", "34.53555866426642"],
							["113.3586326175677", "34.53555866426642"],
						]
					]
				}
			},]
		}
	
	if(jingwushiFlag){
		Map1.addJSON3([ {
			'type' : 'keyPerson',
			'id':'jingwushiFlag',
			'data' : Data,
			'isShow' : true
		} ])
	$(_this).css('border','1px solid #0e54a9')
	
	
	Map1.goTo(["113.3586326175677", "34.53555866426642"])
	}else{
		map.removeOverlay(thisMap['01c24f6e637942a48557ae0000000041Overlay'])
		$(_this).css('border','1px solid transparent')
		Map1.removeLayer('jingwushiFlag');
	}

	jingwushiFlag=!jingwushiFlag;

}

var gongzuozhanFlag=true;
function gongzuozhanFun(_this){
	
	
	var Data={
			"type": "FeatureCollection",
			"centpoint": ["113.36162493243724", "34.532568814236114"],
			"features": [{
				"type": "Feature",  
				"id": "01c24f6e637942a48557ae0000000090",
				"properties": {
					"name": "于家岗警务工作站",
					"icon": "gongan3.png",
					"type":'工作站',
					"info": {
						"民警": "1",
						"层数": "6",
						"楼栋名称": "5号楼",
						"电话": "",
						"地址": "新密市西大街599号"
					},
					"coordinateCentre": ["113.36162493243724", "34.532568814236114"],
				},
				"geometry": {
					"type": "Polygon",
					"coordinates": [
						[
							["113.36162493243724", "34.532568814236114"],
							["113.36162493243724", "34.532568814236114"],
						]
					]
				}
			},{
				"type": "Feature",  
				"id": "01c24f6e637942a48557ae0000000091",
				"properties": {
					"name": "嵩阳路警务工作站",
					"icon": "gongan3.png",
					"type":'工作站',
					"info": {
						"民警": "1",
						"层数": "6",
						"楼栋名称": "5号楼",
						"电话": "",
						"地址": "新密市西大街599号"
					},
					"coordinateCentre": ["113.36563953939262", "34.53482475837481"],
				},
				"geometry": {
					"type": "Polygon",
					"coordinates": [
						[
							 ["113.36563953939262", "34.53482475837481"],
							 ["113.36563953939262", "34.53482475837481"],
						]
					]
				}
			},{
				"type": "Feature",  
				"id": "01c24f6e637942a48557ae0000000092",
				"properties": {
					"name": "广场警务工作站",
					"icon": "gongan3.png",
					"type":'工作站',
					"info": {
						"民警": "1",
						"层数": "6",
						"楼栋名称": "5号楼",
						"电话": "",
						"地址": "新密市西大街599号"
					},
					"coordinateCentre": ["113.36052561412437", "34.53337851393517"],
				},
				"geometry": {
					"type": "Polygon",
					"coordinates": [
						[
							["113.36052561412437", "34.53337851393517"],
							["113.36052561412437", "34.53337851393517"],
						]
					]
				}
			}]
		}
	
	if(gongzuozhanFlag){
		Map1.addJSON3([ {
			'type' : 'keyPerson',
			'id':'gongzuozhan',
			'data' : Data,
			'isShow' : true
		} ])
	$(_this).css('border','1px solid #0e54a9')
	
		Map1.goTo(["113.36324926151391", "34.5347354079514"])
	}else{
		map.removeOverlay(thisMap['01c24f6e637942a48557ae0000000092Overlay'])
		map.removeOverlay(thisMap['01c24f6e637942a48557ae0000000091Overlay'])
		map.removeOverlay(thisMap['01c24f6e637942a48557ae0000000090Overlay'])
		$(_this).css('border','1px solid transparent')
		Map1.removeLayer('gongzuozhan');
	}
	gongzuozhanFlag=!gongzuozhanFlag;

}

var binguanFlag=true;
function binguanFun(_this){
	var Data={
			"type": "FeatureCollection",
			"centpoint": ["113.341822", "34.541205"],
			"features": [{   
				"type": "Feature",
				"id": "01c24f6e637942a48557af0000000026",
				"properties": { 
					"name": "福海商务宾馆",
					"icon": "binguan3.png",
					"info": {
						"单元数": "2",
						"层数": "30",
						"楼栋名称": "10号楼",
						"电话": "17603711728",
						"地址": "新密市鑫苑花园东楼  "
					},
					"coordinateCentre": ["113.35323708441324", "34.53704003266561"],
				},
				"geometry": {
					"type": "Polygon",
					"coordinates": [
						[
							 ["113.35323708441324", "34.53704003266561"],
							 ["113.35323708441324", "34.53704003266561"],
						]
					]
				}
			},{   
				"type": "Feature",
				"id": "01c24f6e637942a48557af0000000027",
				"properties": { 
					"name": "承誉德大酒店",
					"icon": "binguan3.png",
					"info": {
						"单元数": "2",
						"层数": "30",
						"楼栋名称": "10号楼",
						"电话": "17603711728",
						"地址": "新密市西大街88号 "
					},
					"coordinateCentre": ["113.34404200152944", "34.53673316017692"],
				},
				"geometry": {
					"type": "Polygon",
					"coordinates": [
						[
							["113.34404200152944", "34.53673316017692"],
							["113.34404200152944", "34.53673316017692"],
						]
					]
				}
			},{
				"type": "Feature",
				"id": "01c24f6e637942a48557af0000000021",
				"properties": {
					"name": "鑫海湾假日酒店",
					"icon": "binguan3.png",
					"info": {
						"单元数": "2",
						"层数": "30",
						"楼栋名称": "10号楼",
						"电话": "17603711728",
						"id": "01c24f6e637942a48557af0000000020"
					},
					"coordinateCentre": ["113.3512011271791", "34.539242366550255"]
				},
				"geometry": {
					"type": "Polygon",
					"coordinates": [
						[
							["113.3512011271791", "34.539242366550255"],
							["113.3512011271791", "34.539242366550255"]
						]
					]
				}
			},]
		}
	
	if(binguanFlag){
		Map1.addJSON3([ {
			'type' : 'keyPerson',
			'id':'binguan',
			'data' : Data,
			'isShow' : true
		} ])
	$(_this).css('border','1px solid #0e54a9')
	
	
	Map1.goTo(["113.34798143088717", "34.5374695309078"])
	}else{
		map.removeOverlay(thisMap['01c24f6e637942a48557af0000000026Overlay'])
		map.removeOverlay(thisMap['01c24f6e637942a48557af0000000027Overlay'])
		map.removeOverlay(thisMap['01c24f6e637942a48557af0000000021Overlay'])
		$(_this).css('border','1px solid transparent')
		Map1.removeLayer('binguan');
	}

	binguanFlag=!binguanFlag;
}

var xuexiaoFlag=true;
function xuexiaoFun(_this){
	var Data={
			"type": "FeatureCollection",
			"centpoint": ["113.341822", "34.541205"],
			"features": [  {
				"type": "Feature",
				"id": "01c24f6e637942a48557af0000000021",
				"properties": {
					"name": "金凤路初中",
					"icon": "xuexiao3.png",
					"info": {
						"单元数": "2",
						"层数": "30",
						"楼栋名称": "10号楼",
						"电话": "17603711728",
						"id": "01c24f6e637942a48557af0000000021"
					},
					"coordinateCentre": ["113.33454559204532", "34.54006993633399"]
				},
				"geometry": {
					"type": "Polygon",
					"coordinates": [
						[
							["113.33454559204532", "34.54006993633399"],
							["113.33454559204532", "34.54006993633399"],
						]
					]
				}
			}, {
				"type": "Feature",
				"id": "01c24f6e637942a48557af0000000022",
				"properties": {
					"name": "金凤路小学",
					"icon": "xuexiao3.png",
					"info": {
						"单元数": "2",
						"层数": "30",
						"楼栋名称": "10号楼",
						"电话": "17603711728",
						"id": "01c24f6e637942a48557af0000000022"
					},
					"coordinateCentre": ["113.33453511647842", "34.53860150834096"]
				},
				"geometry": {
					"type": "Polygon",
					"coordinates": [
						[
							["113.33453511647842", "34.53860150834096"],
							["113.33453511647842", "34.53860150834096"],
						]
					]
				}
			}, {
				"type": "Feature",
				"id": "01c24f6e637942a48557af0000000101",
				"properties": {
					"name": "新密市市直第三幼儿园",
					"icon": "xuexiao3.png",
					"info": {
						"单元数": "2",
						"层数": "30",
						"楼栋名称": "10号楼",
						"电话": "17603711728",
					},
					"coordinateCentre": ["113.33750216674552", "34.542289524093505"]
				},
				"geometry": {
					"type": "Polygon",
					"coordinates": [
						[
							["113.33750216674552", "34.542289524093505"],
							["113.33750216674552", "34.542289524093505"],
						]
					]
				}
			}]
		}
	
	if(xuexiaoFlag){
		Map1.addJSON3([ {
			'type' : 'keyPerson',
			'id':'xuexiao',
			'data' : Data,
			'isShow' : true
		} ])
	$(_this).css('border','1px solid #0e54a9')
	
	
	Map1.goTo(["113.33587660524924", "34.54076009132863"])
	}else{
		map.removeOverlay(thisMap['01c24f6e637942a48557af0000000021Overlay'])
		map.removeOverlay(thisMap['01c24f6e637942a48557af0000000022Overlay'])
		map.removeOverlay(thisMap['01c24f6e637942a48557af0000000101Overlay'])
		$(_this).css('border','1px solid transparent')
		Map1.removeLayer('xuexiao');
	}
	xuexiaoFlag=!xuexiaoFlag;
}


var yiyuanFlag=true;
function yiyuanFun(_this){
	var Data={
			"type": "FeatureCollection",
			"centpoint": ["113.341822", "34.541205"],
			"features": [{
				"type": "Feature",
				"id": "01c24f6e637942a48557af0000000102",
				"properties": { 
					"name": "骨科医院",
					"icon": "yiyuan3.png",
					"info": {
						"单元数": "2",
						"层数": "30",
						"楼栋名称": "10号楼",
						"电话": "17603711728",
						"地址": "新密市西大街308号 "
					},
					"coordinateCentre": ["113.36517553340067", "34.53117618005054"],
				},
				"geometry": {
					"type": "Polygon",
					"coordinates": [
						[
							["113.36517553340067", "34.53117618005054"],
							["113.36517553340067", "34.53117618005054"],
						]
					]
				}
			},{
				"type": "Feature",
				"id": "01c24f6e637942a48557af0000000103",
				"properties": { 
					"name": "眼科医院",
					"icon": "yiyuan3.png",
					"info": {
						"单元数": "2",
						"层数": "30",
						"楼栋名称": "10号楼",
						"电话": "17603711728",
						"地址": "新密市西大街308号 "
					},
					"coordinateCentre": ["113.36611093990234", "34.5313524160581"],
				},
				"geometry": {
					"type": "Polygon",
					"coordinates": [
						[
							["113.36611093990234", "34.5313524160581"],
							["113.36611093990234", "34.5313524160581"],
						]
					]
				}
			}]
		}
	
	if(yiyuanFlag){
		Map1.addJSON3([ {
			'type' : 'keyPerson',
			'id':'yiyuan',
			'data' : Data,
			'isShow' : true
		} ])
	$(_this).css('border','1px solid #0e54a9')
	
	
	Map1.goTo(["113.36576216514614", "34.531276006040834"])
	}else{
		map.removeOverlay(thisMap['01c24f6e637942a48557af0000000102Overlay'])
		map.removeOverlay(thisMap['01c24f6e637942a48557af0000000103Overlay'])
		$(_this).css('border','1px solid transparent')
		Map1.removeLayer('yiyuan');
	}
	yiyuanFlag=!yiyuanFlag;
}


var jingcheFlag=true;
function jingcheFun(_this){
	var Data={
			"type": "FeatureCollection",
			"centpoint": ["113.35613943264957", "34.53732533781072"],
			"features": [{
				"type": "Feature",
				"id": "01c24f6e637942a48557af0000000131",
				"properties": { 
					"name": "豫A0S81Q",
					"icon": "c1.png",
                    "src":"",
					"info": {
						"单元数": "2",
						"层数": "30",
						"楼栋名称": "10号楼",
						"电话": "17603711728",
						"地址": "新密市西大街308号 "
					},
					"coordinateCentre":["113.35613943264957", "34.53732533781072"],
				},
				"geometry": {
					"type": "Polygon",
					"coordinates": [
						[
							["113.35613943264957", "34.53732533781072"],
							["113.35613943264957", "34.53732533781072"],
						]
					]
				}
			},{
				"type": "Feature",
				"id": "01c24f6e637942a48557af0000000132",
				"properties": { 
					"name": "豫A0519H",
					"icon": "c1.png",
					"src":"",
					"info": {
						"单元数": "2",
						"层数": "30",
						"楼栋名称": "10号楼",
						"电话": "17603711728",
						"地址": "新密市西大街308号 "
					},
					"coordinateCentre": ["113.35671620503794", "34.53728590038246"],
				},
				"geometry": {
					"type": "Polygon",
					"coordinates": [
						[
							["113.35671620503794", "34.53728590038246"],
							["113.35671620503794", "34.53728590038246"],
						]
					]
				}
			}]
		}
	
	if(jingcheFlag){
		Map1.addJSON3([ {
			'type' : 'DanDian',
			'data' : Data,
			'id':'jingche',
			'isShow' : true
		} ])
	$(_this).css('border','1px solid #0e54a9')
	
	
	Map1.goTo(["113.35671620503794", "34.53728590038246"])
	}else{
		map.removeOverlay(thisMap['01c24f6e637942a48557af0000000131Overlay'])
		map.removeOverlay(thisMap['01c24f6e637942a48557af0000000132Overlay'])
		$(_this).css('border','1px solid transparent')
		Map1.removeLayer('jingche');
	}
	jingcheFlag=!jingcheFlag;
}


var jingyuanFlag=true;
function jingyuanFun(_this){
	var Data={
			"type": "FeatureCollection",
			"centpoint": ["113.35583132774127", "34.5367103604137"],
			"features": [{
				"type": "Feature",
				"id": "01c24f6e637942a48557af0000000141",
				"properties": { 
					"name": "吕一凡",
					"icon": "p1.png",
                    "src":"",
					"info": {
						"单元数": "2",
						"层数": "30",
						"楼栋名称": "10号楼",
						"电话": "17603711728",
						"地址": "新密市西大街308号 "
					},
					"coordinateCentre":["113.35583132774127", "34.5367103604137"],
				},
				"geometry": {
					"type": "Polygon",
					"coordinates": [
						[
							["113.35583132774127", "34.5367103604137"],
							["113.35583132774127", "34.5367103604137"],
						]
					]
				}
			},{
				"type": "Feature",
				"id": "01c24f6e637942a48557af0000000142",
				"properties": { 
					"name": "王庆豪",
					"icon": "p1.png",
					"src":"",
					"info": {
						"单元数": "2",
						"层数": "30",
						"楼栋名称": "10号楼",
						"电话": "17603711728",
						"地址": "新密市西大街308号 "
					},
					"coordinateCentre": ["113.35583995467869", "34.53623711127454"],
				},
				"geometry": {
					"type": "Polygon",
					"coordinates": [
						[
							["113.35583995467869", "34.53623711127454"],
							["113.35583995467869", "34.53623711127454"],
						]
					]
				}
			}]
		}
	
	if(jingyuanFlag){
		Map1.addJSON3([ {
			'type' : 'DanDian',
			'id':'jingyuan',
			'data' : Data,
			'isShow' : true
		} ])
	$(_this).css('border','1px solid #0e54a9')
	
	
	Map1.goTo(["113.35583995467869", "34.53623711127454"])
	}else{
		map.removeOverlay(thisMap['01c24f6e637942a48557af0000000141Overlay'])
		map.removeOverlay(thisMap['01c24f6e637942a48557af0000000142Overlay'])
		$(_this).css('border','1px solid transparent')
		Map1.removeLayer('jingyuan');
	}
	jingyuanFlag=!jingyuanFlag;
}




var jiayouzhanFlag=true;
function jiayouzhanFun(_this){
	var Data={
			"type": "FeatureCollection",
			"centpoint": ["113.341822", "34.541205"],
			"features": [{
				"type": "Feature",  
				"id": "01c24f6e637942a48557ae0000000029",
				"properties": {
					"name": "郑交运集团加油站(新密分公司)",
					"icon": "jiayouzhan3.png",
					"info": {
						"单元数": "1",
						"层数": "6",
						"楼栋名称": "5号楼",
						"电话": "",
						"地址": "新密市嵩山大道864"
					},
					"coordinateCentre": ["113.35403507612577", "34.52951795943396"],
				},
				"geometry": {
					"type": "Polygon",
					"coordinates": [
						[
							["113.35403507612577", "34.52951795943396"],
							["113.35403507612577", "34.52951795943396"],
						]
					]
				}
			},{ 
				"type": "Feature",  
				"id": "01c24f6e637942a48557ae0000000111",
				"properties": {
					"name": "亚立石化加油站(开阳路)",
					"icon": "jiayouzhan3.png",
					"info": {
						"单元数": "1",
						"层数": "6",
						"楼栋名称": "5号楼",
						"电话": "",
						"地址": "新密市嵩山大道864"
					},
					"coordinateCentre": ["113.37272410365445", "34.53263536489631"],
				},
				"geometry": {
					"type": "Polygon",
					"coordinates": [
						[
							 ["113.37272410365445", "34.53263536489631"],
							 ["113.37272410365445", "34.53263536489631"],
						]
					]
				}
			},]
		}
	
	if(jiayouzhanFlag){
		Map1.addJSON3([ {
			'type' : 'keyPerson',
			'id':'jiayouzhan',
			'data' : Data,
			'isShow' : true
		} ])
	$(_this).css('border','1px solid #0e54a9')

	
	Map1.goTo(["113.37127662679515", "34.53244865332188"])
	}else{
		map.removeOverlay(thisMap['01c24f6e637942a48557ae0000000029Overlay'])
		map.removeOverlay(thisMap['01c24f6e637942a48557ae0000000111Overlay'])
		$(_this).css('border','1px solid transparent')
		Map1.removeLayer('jiayouzhan');
	}
	jiayouzhanFlag=!jiayouzhanFlag;
}

var shangchangFlag=true;
function shangchangFun(_this){
	var Data={
			"type": "FeatureCollection",
			"centpoint": ["113.341822", "34.541205"],
			"features": [{
				"type": "Feature",  
				"id": "01c24f6e637942a48557ae0000000120",
				"properties": {
					"name": "金博大购物中心",
					"icon": "shangchang3.png",
					"info": {
						"单元数": "1",
						"层数": "6",
						"楼栋名称": "5号楼",
						"电话": "",
						"地址": "新密市西大街青屏广场对面"
					},
					"coordinateCentre": ["113.36030870826892", "34.53834270021797"],
				},
				"geometry": {
					"type": "Polygon",
					"coordinates": [
						[
							["113.36030870826892", "34.53834270021797"],
							["113.36030870826892", "34.53834270021797"],
						]
					]
				}
			}]
		}
	
	if(shangchangFlag){
		Map1.addJSON3([ {
			'type' : 'keyPerson',
			'id':'shangchang',
			'data' : Data,
			'isShow' : true
		} ])
	$(_this).css('border','1px solid #0e54a9')

	
	Map1.goTo(["113.36030870826892", "34.53834270021797"])
	}else{
		map.removeOverlay(thisMap['01c24f6e637942a48557ae0000000120Overlay'])
		$(_this).css('border','1px solid transparent')
		Map1.removeLayer('shangchang');
	}
	shangchangFlag=!shangchangFlag;
}

var yuleFlag=true;
function yuleFun(_this){
	var Data={
			"type": "FeatureCollection",
			"centpoint": ["113.341822", "34.541205"],
			"features": [{
				"type": "Feature",  
				"id": "01c24f6e637942a48557ae0000000121",
				"properties": {
					"name": "麦克疯量贩KTV",
					"icon": "yule3.png",
					"info": {
						"单元数": "1",
						"层数": "6",
						"楼栋名称": "5号楼",
						"电话": "",
						"地址": "新密市西大街青屏广场对面"
					},
					"coordinateCentre": ["113.36035554021498", "34.53894658583827"],
				},
				"geometry": {
					"type": "Polygon",
					"coordinates": [
						[
							["113.36035554021498", "34.53894658583827"],
							["113.36035554021498", "34.53894658583827"],
						]
					]
				}
			},{
				"type": "Feature",  
				"id": "01c24f6e637942a48557ae0000000122",
				"properties": {
					"name": "糖果娱乐空间",
					"icon": "yule3.png",
					"info": {
						"单元数": "1",
						"层数": "6",
						"楼栋名称": "5号楼",
						"电话": "",
						"地址": "新密市西大街368号院"
					},
					"coordinateCentre": ["113.35831711814157", "34.53792737480156"],
				},
				"geometry": {
					"type": "Polygon",
					"coordinates": [
						[
							["113.35831711814157", "34.53792737480156"],
							["113.35831711814157", "34.53792737480156"],
						]
					]
				}
			}]
		}
	
	if(yuleFlag){
		Map1.addJSON3([ {
			'type' : 'keyPerson',
			'id':'yule',
			'data' : Data,
			'isShow' : true
		} ])
	$(_this).css('border','1px solid #0e54a9')

	
	Map1.goTo(["113.35930675110708", "34.53831681940568"])
	}else{
		map.removeOverlay(thisMap['01c24f6e637942a48557ae0000000121Overlay'])
		map.removeOverlay(thisMap['01c24f6e637942a48557ae0000000122Overlay'])
		$(_this).css('border','1px solid transparent')
		Map1.removeLayer('yule');
	}
	yuleFlag=!yuleFlag;
}

var sheweishebaoFlag=true;
function sheweishebaoFun(_this){
	var Data={
			"type": "FeatureCollection",
			"centpoint": ["113.34541245216164", "34.526030828081645"],
			"features": [{
				"type": "Feature",  
				"id": "01c24f6e637942a48557ae0000000123",
				"properties": {
					"name": "烟花爆竹专营店(新华路三店)",
					"icon": "yule3.png",
					"info": {
						"单元数": "1",
						"层数": "6",
						"楼栋名称": "5号楼",
						"电话": "",
						"地址": "嵩山大道附近"
					},
					"coordinateCentre": ["113.38828833120301", "34.52308205870578"],
				},
				"geometry": {
					"type": "Polygon",
					"coordinates": [
						[
							["113.38828833120301", "34.52308205870578"],
							["113.38828833120301", "34.52308205870578"],
						]
					]
				}
			}]
		}
	
	if(sheweishebaoFlag){
		Map1.addJSON3([ {
			'type' : 'keyPerson',
			'id':'sheweishebao',
			'data' : Data,
			'isShow' : true
		} ])
	$(_this).css('border','1px solid #0e54a9')

	
	Map1.goTo(["113.38828833120301", "34.52308205870578"])
	}else{
		map.removeOverlay(thisMap['01c24f6e637942a48557ae0000000123Overlay'])
		$(_this).css('border','1px solid transparent')
		Map1.removeLayer('sheweishebao');
	}
	sheweishebaoFlag=!sheweishebaoFlag;
}

var shipinjiankongFlag=true;
function shipinjiankongFun(_this){
	var Data={
			"type": "FeatureCollection",
			"centpoint": ["113.34541245216164", "34.526030828081645"],
			"features": [{
				"type": "Feature",  
				"id": "01c24f6e637942a48557ae0000000124",
				"properties": {
					"name": "视频监控1",
					"icon": "video.png",
					"src":"video1.mp4",
					"info": {
						"单元数": "1",
						"层数": "6",
						"楼栋名称": "5号楼",
						"电话": "",
						"地址": "嵩山大道附近"
					},
					"coordinateCentre": ["113.3593930204814", "34.53745535808203"],
				},
				"geometry": {
					"type": "Polygon",
					"coordinates": [
						[
							["113.3593930204814", "34.53745535808203"],
							["113.3593930204814", "34.53745535808203"],
						]
					]
				}
			},{
				"type": "Feature",  
				"id": "01c24f6e637942a48557ae0000000125",
				"properties": {
					"name": "视频监控2",
					"icon": "video.png",
					"src":"video2.mp4",
					"info": {
						"单元数": "1",
						"层数": "6",
						"楼栋名称": "5号楼",
						"电话": "",
						"地址": "嵩山大道附近"
					},
					"coordinateCentre": ["113.36030298471454", "34.537288427352905"],
				},
				"geometry": {
					"type": "Polygon",
					"coordinates": [
						[
							["113.36030298471454", "34.537288427352905"],
							["113.36030298471454", "34.537288427352905"],
						]
					]
				}
			},{
				"type": "Feature",  
				"id": "01c24f6e637942a48557ae0000000126",
				"properties": {
					"name": "视频监控3",
					"icon": "video.png",
					"src":"video3.mp4",
					"info": {
						"单元数": "1",
						"层数": "6",
						"楼栋名称": "5号楼",
						"电话": "",
						"地址": "嵩山大道附近"
					},
					"coordinateCentre": ["113.36029225587848", "34.537625312805176"],
				},
				"geometry": {
					"type": "Polygon",
					"coordinates": [
						[
							 ["113.36029225587848", "34.537625312805176"],
							 ["113.36029225587848", "34.537625312805176"],
						]
					]
				}
			},{
				"type": "Feature",  
				"id": "01c24f6e637942a48557ae0000000127",
				"properties": {
					"name": "视频监控4",
					"icon": "video.png",
					"src":"video4.mp4",
					"info": {
						"单元数": "1",
						"层数": "6",
						"楼栋名称": "5号楼",
						"电话": "",
						"地址": "嵩山大道附近"
					},
					"coordinateCentre": ["113.35892111063006", "34.53728413581848"],
				},
				"geometry": {
					"type": "Polygon",
					"coordinates": [
						[
							 ["113.36029225587848", "34.537625312805176"],
							 ["113.36029225587848", "34.537625312805176"],
						]
					]
				}
			}]
		}
	
	if(shipinjiankongFlag){
		Map1.addJSON3([ {
			'type' : 'DanDian',
			'id':'shipinjiankong',
			'data' : Data,
			'isShow' : true
		} ])
	$(_this).css('border','1px solid #0e54a9')

	
	Map1.goTo(["113.3593930204814", "34.53745535808203"])
	}else{
		map.removeOverlay(thisMap['01c24f6e637942a48557ae0000000124Overlay'])
		map.removeOverlay(thisMap['01c24f6e637942a48557ae0000000125Overlay'])
		map.removeOverlay(thisMap['01c24f6e637942a48557ae0000000126Overlay'])
		map.removeOverlay(thisMap['01c24f6e637942a48557ae0000000127Overlay'])
		$(_this).css('border','1px solid transparent')
		Map1.removeLayer('shipinjiankong');
	}
	shipinjiankongFlag=!shipinjiankongFlag;
}




function XiangQingFun(type){
	$('.pubMapDialog').show()
	var XiangQingData={
			"长乐路警务室":{
				"民警":[{'姓名':'慕观卿','警号':'103802','性别':'男','电话':'17603711723',"src":'muguanqing.JPG'},{'姓名':'许小五','警号':'103803','性别':'男','电话':'17603711728',"src":'xuxiaowu.jpg'},{'姓名':'郑首守','警号':'103804','性别':'男','电话':'17503711759',"src":'zhebgshoushou.jpg'}],
	            "辅警":[{'姓名':'蒋尚涵','警号':'103805','性别':'男','电话':'13674919298',"src":'jiangshanghan.jpg'},{'姓名':'李格','警号':'103806','性别':'女','电话':'13509856056',"src":'lige.JPG'},{'姓名':'许伟强','警号':'103807','性别':'男','电话':'13603986786',"src":'xuweiqiang.jpg'}]
				},
			"于家岗警务工作站":{
				"民警":[{'姓名':'郭亚凯','警号':'103808','性别':'男','电话':'15515991957',"src":'guoyakai.jpg'},{'姓名':'慕观卿','警号':'103802','性别':'男','电话':'17603711723',"src":'muguanqing.JPG'},{'姓名':'吕一帆','警号':'103809','性别':'男','电话':'13673992608','src':'lvyifan.jpg'}],
	            "辅警":[{'姓名':'梁炎龙','警号':'103810','性别':'男','电话':'18539436116',"src":'lianyanlong.jpg'},{'姓名':'楚褤铭','警号':'103811','性别':'男','电话':'15803817833',"src":'chuming.jpg'},{'姓名':'申奥','警号':'103812','性别':'男','电话':'15890009001',"src":'shenao.jpg'}]
				},
			"嵩阳路警务工作站":{
				"民警":[{'姓名':'蒋尚涵','警号':'103805','性别':'男','电话':'13674919298',"src":'jiangshanghan.jpg'},{'姓名':'马双科','警号':'103813','性别':'男','电话':'15093337726',"src":'mashuangke.JPG'},{'姓名':'侯鹏','警号':'103814','性别':'男','电话':'18437188688',"src":'houpeng.JPG'}],
	            "辅警":[{'姓名':'高志阳','警号':'103815','性别':'男','电话':'15136205957',"src":'gaozhiyang.jpg'},{'姓名':'楚褤铭','警号':'103811','性别':'男','电话':'15803817833',"src":'chuming.jpg'},{'姓名':'侯林山','警号':'103817','性别':'男','电话':'15290869958','src':'houlinshan.jpg'}]
				},
			"广场警务工作站":{
				"民警":[{'姓名':'吕一帆','警号':'103809','性别':'男','电话':'13673992608','src':'lvyifan.jpg'},{'姓名':'慕观卿','警号':'103802','性别':'男','电话':'17603711723',"src":'muguanqing.JPG'},{'姓名':'郭亚凯','警号':'103808','性别':'男','电话':'15515991957',"src":'guoyakai.jpg'}],
	            "辅警":[{'姓名':'梁炎龙','警号':'103810','性别':'男','电话':'18539436116',"src":'lianyanlong.jpg'},{'姓名':'高志阳','警号':'103815','性别':'男','电话':'15136205957',"src":'gaozhiyang.jpg'},{'姓名':'侯林山','警号':'103817','性别':'男','电话':'15290869958','src':'houlinshan.jpg'}]
				}
			}
   if(type=='长乐路警务室'||type=='于家岗警务工作站'||type=='嵩阳路警务工作站'||type=='广场警务工作站'){
	   var data=XiangQingData[type];
		console.log(data)
		var data1=data['民警'];
		var data2=data['辅警'];
		var len1=data1.length;
		var len2=data2.length;
		var html="";
		html+='<div class="pubMapDialog-center1"><div> 民警： </div>';
		for(var i=0;i<len1;i++){
			html+='<div class="well-item">'
			html+='<div  class="jingcha-name correct">';
			html+='<div  class="jiachatouxiang"><img src="/arjccm/static/modules/map/images/pub/'+data1[i]['src']+'" /></div>';
			html+=' <p>'+data1[i]['姓名']+'</p>';
			html+='<p>'+data1[i]['警号']+'</p>';
			html+=' </div>'
			html+='<div class="opposite"><div>';
			html+='<table>';
			html+='<tr>';
			html+='<td align="right" >姓名：</td><td align="left" >'+data1[i]['姓名']+'</td>';
			html+='</tr>';
			html+='<tr>';
			html+='<td align="right" >性别：</td><td align="left">'+data1[i]['性别']+'</td>';
			html+='</tr>';
			html+='<tr>';
			html+='<td align="right" >警号：</td><td align="left">'+data1[i]['警号']+'</td>';
			html+='</tr>';
			html+='<tr>';
			html+='<td align="right" >电话：</td><td align="left">'+data1[i]['电话']+'</td>';
			html+='</tr>';
			html+='</table>';
			html+='</div></div>';
			html+='</div>'
		}
		html+=' </div>'
		html+='<div  class="pubMapDialog-center2"><div>辅警： </div>';
	    for(var i=0;i<len2;i++){
	    	html+='<div class="well-item">'
	    	html+='<div  class="jingcha-name correct">';
			html+='<div  class="jiachatouxiang"><img src="/arjccm/static/modules/map/images/pub/'+data2[i]['src']+'" /></div>';
			html+=' <p>'+data2[i]['姓名']+'</p>';
			html+='<p>'+data2[i]['警号']+'</p>';
			html+=' </div>';
			html+='<div class="opposite"><div>';
			html+='<table>';
			html+='<tr>';
			html+='<td  align="right" >姓名：</td><td align="left">'+data2[i]['姓名']+'</td>';
			html+='</tr>';
			html+='<tr>';
			html+='<td align="right" >性别：</td><td align="left">'+data2[i]['性别']+'</td>';
			html+='</tr>';
			html+='<tr>';
			html+='<td align="right" >警号：</td><td align="left">'+data2[i]['警号']+'</td>';
			html+='</tr>';
			html+='<tr>';
			html+='<td align="right" >电话：</td><td align="left">'+data2[i]['电话']+'</td>';
			html+='</tr>';
			html+='</table>';
			html+='</div></div>';
			html+='</div>'
		}
	    html+=' </div>'
		  
	    $('.pubMapDialog-center-center').html(html)
	   

		$(".well-item").hover(function(){
			$(this).find(".correct").children().removeClass('test');
			$(this).find(".correct").children().removeClass('test2');
			$(this).find(".opposite").children().removeClass('test');
			$(this).find(".opposite").children().removeClass('test2');
			$(this).find(".correct").children().addClass("test");
			$(this).find(".opposite").children().addClass('test2');
		},function(){
			$(this).find(".correct").children().removeClass('test');
			$(this).find(".opposite").children().removeClass('test');
			$(this).find(".correct").children().removeClass("test2");
			$(this).find(".opposite").children().removeClass("test2");
			$(this).find(".correct").children().addClass("test2");
			$(this).find(".opposite").children().addClass('test');
		});
		
   }else if(type=='骨科医院'||type=="眼科医院"){
	   var data={
			   "骨科医院":{
					"级别":"一级医院",
		            "经营性质":"县市区直属",
		            "重点部位":"化验生化室、放射科",
		            "基本情况":"新密市骨科医院是经郑州市卫生局批准执业的骨科专业机构，是以骨科、显微外科、普外为特色优势，集创伤急救、医疗、保健、功能康复为一体的专科医院。",
		            'src':'guke.jpg',
					},
			 "眼科医院":{
					"级别":"一级医院",
		            "经营性质":"县市区直属",
		            "重点部位":"病房、治疗室",
		            "基本情况":"新密市眼科医院是全省最大的一所县（市）级眼科专科医院。该医院位于新密市嵩山大道123号总建筑面积6000余平方米，医务人员100多人，其中副主任医师4人，中级职称人员30余人。 ",
		            'src':'yanke.jpg',	
					},
	   }
	   var html="";
	   html+='<table style="height:100%">';
	   html+='<tr>';
	   html+='<td  class="textcenterright" style="width:135px"><img src="/arjccm/static/modules/map/images/pub/'+data[type]["src"]+'" /></td>';
	   html+='<td  class="textcenterleft" style="font-size:20px;padding-left:24px">'+type+'</td>';
	   html+='</tr>';
	   html+='<tr class="l-grid-row-alt">';
	   html+='<td class="textcenterright">级别：</td>';
	   html+='<td class="textcenterleft">'+data[type]["级别"]+'</td>';
	   html+='</tr>';
	   html+='<tr>';
	   html+='<td  class="textcenterright">经营性质：</td>';
	   html+='<td class="textcenterleft">'+data[type]["经营性质"]+'</td>';
	   html+='</tr>';
	   html+='<tr class="l-grid-row-alt">';
	   html+='<td  class="textcenterright">重点部位：</td>';
	   html+='<td class="textcenterleft">'+data[type]["重点部位"]+'</td>';
	   html+='</tr>';
	   html+='<tr>';
	   html+='<td  class="textcenterright">基本情况：</td>';
	   html+='<td class="textcenterleft">'+data[type]["基本情况"]+'</td>';
	   html+='</tr>';

	 
	   html+='</table>';
	   $('.pubMapDialog-center-center').html(html)
   }else if(type=='金凤路小学'||type=="金凤路初中"){
	   var data={
			   "金凤路小学":{
					"性质":"消防重点单位",
		            "学校地址":"西大街与金凤路交叉口北150米",
		            "重点部位":"教室、办公室",
		            "基本情况":"新密市金凤路小学是隶属于新密市教体局的一所市直学校,占地面积近24亩,建筑面积10398平方米。学校环境优美,教学设施齐全,设有梦想教室、美术教室等",
		            'src':'xiaoxue.jpg'
					},
			 "金凤路初中":{
					"性质":"消防重点单位",
		            "学校地址":"金凤路与青屏大街交叉口西南150米",
		            "重点部位":"教室、办公室",
		            "基本情况":"金凤路初中学校建有教学综合楼、科技实验楼、综合办公楼、体育馆、运动场占地四十余亩，是一所教育教学设施一流的现代化的城市初级中学 ",
		            'src':'xuexiao2.png'
			 },
	   }
	   var html="";
	   html+='<table style="height:100%">';
	   html+='<tr>';
	   html+='<td  class="textcenterright" style="width:135px"><img src="/arjccm/static/modules/map/images/pub/'+data[type]["src"]+'" /></td>';
	   html+='<td  class="textcenterleft" style="font-size:20px;padding-left:24px">'+type+'</td>';
	   html+='</tr>';
	   html+='<tr class="l-grid-row-alt">';
	   html+='<td class="textcenterright">性质：</td>';
	   html+='<td class="textcenterleft">'+data[type]["性质"]+'</td>';
	   html+='</tr>';
	   html+='<tr>';
	   html+='<td  class="textcenterright">重点部位：</td>';
	   html+='<td class="textcenterleft">'+data[type]["重点部位"]+'</td>';
	   html+='</tr>';
	   html+='<tr class="l-grid-row-alt">';
	   html+='<td  class="textcenterright">学校地址：</td>';
	   html+='<td class="textcenterleft">'+data[type]["学校地址"]+'</td>';
	   html+='</tr>';
	   html+='<tr>';
	   html+='<td  class="textcenterright">基本情况：</td>';
	   html+='<td class="textcenterleft">'+data[type]["基本情况"]+'</td>';
	   html+='</tr>';
	   html+='</table>';
	   $('.pubMapDialog-center-center').html(html)
   }else if(type=='郑交运集团加油站(新密分公司)'||type=="亚立石化加油站(开阳路)"){
	   var data={
			   "郑交运集团加油站(新密分公司)":{
					"性质":"消防重点单位",
		            "重点部位":"油库、加油作业区",
		            "单位地址":"郑交运集团加油站(新密分公司)",
		            "基本情况":"成立于1996年7月。主要经营汽油、柴油、润滑油销售等。",
		            'src':'zhengjiao.jpg'
					},
			 "亚立石化加油站(开阳路)":{
					"性质":"消防重点单位",
		            "重点部位":"油库、加油作业区",
		            "单位地址":"河南省郑州市新密市嵩山大道669",
		            "基本情况":"河南亚立石油化工有限公司，成立于2001年9月。主要经营车用乙醇汽油、柴油、煤油零售与批发，预包装食品零售，化工产品经销，仓储，燃料油、润滑油、日用百货的销售，经营进出口贸易等业务。 ",
		            'src':'yali.jpg'
					},
	   }
	   var html="";
	   html+='<table style="height:100%">';
	   html+='<tr>';
	   html+='<td  class="textcenterright" style="width:135px"><img src="/arjccm/static/modules/map/images/pub/'+data[type]["src"]+' " /></td>';
	   html+='<td  class="textcenterleft" style="font-size:20px;padding-left:24px">'+type+'</td>';
	   html+='</tr>';
	   html+='<tr class="l-grid-row-alt">';
	   html+='<td class="textcenterright">性质：</td>';
	   html+='<td class="textcenterleft">'+data[type]["性质"]+'</td>';
	   html+='</tr>';
	   html+='<tr>';
	   html+='<td  class="textcenterright">重点部位：</td>';
	   html+='<td class="textcenterleft">'+data[type]["重点部位"]+'</td>';
	   html+='</tr>';
	   html+='<tr class="l-grid-row-alt">';
	   html+='<td  class="textcenterright">单位地址：</td>';
	   html+='<td class="textcenterleft">'+data[type]["单位地址"]+'</td>';
	   html+='</tr>';
	   html+='<tr>';
	   html+='<td  class="textcenterright">基本情况：</td>';
	   html+='<td class="textcenterleft">'+data[type]["基本情况"]+'</td>';
	   html+='</tr>';
	   html+='</table>';
	   $('.pubMapDialog-center-center').html(html)
   }
   else if(type=='承誉德大酒店'||type=="鑫海湾假日酒店"||type=="福海商务宾馆"){
	   var data={
			   "承誉德大酒店":{
					"性质":"消防重点单位",
		            "单位地址":"郑州市新密市西大街88号",
		            "重点部位":"客房、餐厅",
		            "基本情况":"郑州承誉德大酒店是一家集餐饮、客房、娱乐、休闲洗浴及商务会议活动为一体的多功能大型商务涉外酒店。",
		            'src':'chengyude.png',
					},
			 "鑫海湾假日酒店":{
				 "性质":"消防重点单位",
				 "单位地址":"郑州市新密市平安路225号",
		            "重点部位":"客房、餐厅",
		            "基本情况":"新密鑫海湾假日酒店融顶尖豪华酒店设计理念、艺术构筑于一体，采用新型绿色环保材料、安全健康科技的人文智能控制系统，拥有各式舒适客房，并设有茶室、棋牌室、足浴等配套服务设施。",
		            'src':'xinhaiwan.jpg',
					}, 
			"福海商务宾馆":{
				"性质":"消防重点单位",
				 "单位地址":"郑州市新密市鑫苑花园东楼",
	            "重点部位":"客房、餐厅",
	            "基本情况":"福海商务宾馆，于2008年正式营业，是一家住宿、休闲、餐饮为一体的经营式现代企业。",
	            'src':'fuhai.jpg',
				},
	   }
	   var html="";
	   html+='<table style="height:100%">';
	   html+='<tr>';
	   html+='<td  class="textcenterright" style="width:135px"><img src="/arjccm/static/modules/map/images/pub/'+data[type]["src"]+'" /></td>';
	   html+='<td  class="textcenterleft" style="font-size:20px;padding-left:24px">'+type+'</td>';
	   html+='</tr>';
	   html+='<tr class="l-grid-row-alt">';
	   html+='<td class="textcenterright">性质：</td>';
	   html+='<td class="textcenterleft">'+data[type]["性质"]+'</td>';
	   html+='</tr>';
	   html+='<tr>';
	   html+='<td  class="textcenterright">重点部位：</td>';
	   html+='<td class="textcenterleft">'+data[type]["重点部位"]+'</td>';
	   html+='</tr>';
	   html+='<tr class="l-grid-row-alt">';
	   html+='<td  class="textcenterright">单位地址：</td>';
	   html+='<td class="textcenterleft">'+data[type]["单位地址"]+'</td>';
	   html+='</tr>';
	   html+='<tr>';
	   html+='<td  class="textcenterright">基本情况：</td>';
	   html+='<td class="textcenterleft">'+data[type]["基本情况"]+'</td>';
	   html+='</tr>';
	   html+='</table>';
	   $('.pubMapDialog-center-center').html(html)
   }
   else if(type=='金博大购物中心'){
	   var data={
			   "金博大购物中心":{
					"性质":"消防重点单位",
		            "商场地址":"郑州市新密市西大街276号",
		            "重点部位":"仓库、逃生通道",
		            "基本情况":"新密金博大购物中心有限公司于2007年06月14日在新密市工商行政管理局登记成立。法定代表人楚文杰，公司经营范围包括百货、家用电器、服装、鞋帽、五金交电、皮革制品等。",
		            'src':'jingboda.jpg'
					}
	   }
	   var html="";
	   html+='<table style="height:100%">';
	   html+='<tr>';
	   html+='<td  class="textcenterright" style="width:135px"><img style="width:48px;height:48px" src="/arjccm/static/modules/map/images/pub/'+data[type]["src"]+'" /></td>';
	   html+='<td  class="textcenterleft" style="font-size:20px;padding-left:24px">'+type+'</td>';
	   html+='</tr>';
	   html+='<tr class="l-grid-row-alt">';
	   html+='<td class="textcenterright">性质：</td>';
	   html+='<td class="textcenterleft">'+data[type]["性质"]+'</td>';
	   html+='</tr>';
	   html+='<tr>';
	   html+='<td  class="textcenterright">重点部位：</td>';
	   html+='<td class="textcenterleft">'+data[type]["重点部位"]+'</td>';
	   html+='</tr>';
	   html+='<tr class="l-grid-row-alt">';
	   html+='<td  class="textcenterright">商场地址：</td>';
	   html+='<td class="textcenterleft">'+data[type]["商场地址"]+'</td>';
	   html+='</tr>';
	   html+='<tr>';
	   html+='<td  class="textcenterright">基本情况：</td>';
	   html+='<td class="textcenterleft">'+data[type]["基本情况"]+'</td>';
	   html+='</tr>';
	   html+='</table>';
	   $('.pubMapDialog-center-center').html(html)
   }
   else if(type=='麦克疯量贩KTV'||type=='糖果娱乐空间'){
	   var data={
			   "麦克疯量贩KTV":{
					"性质":"治安消防单位",
		            "单位地址":"新密市长乐路515号",
		            "重点部位":"客房、配电室",
		            "基本情况":"麦克疯量贩KTV新密店以人性化的布局，奢华的装饰装潢，让消费者感受贴心的服务和自由的欢唱；麦克疯量贩KTV新密店本着独特的消费方式及完善的服务理念，致力于为您打造价格低、音响好、歌曲全、服务优的健康纯K歌场所。",
		            'src':'maikefeng.jpg'
					},
			 "糖果娱乐空间":{
				   "性质":"治安消防单位",
			     	"单位地址":"新密市西大街368号",
		            "重点部位":"客房、配电室",
		            "基本情况":"糖果娱乐空间内部装饰时尚、典雅、舒适，消费价格低廉。是好友聚会、家人娱乐、生日庆典、商务休闲的一个极佳的文化娱乐场所。 ",
		            'src':'tanguo.jpg'
					},
	   }
	   var html="";
	   html+='<table style="height:100%">';
	   html+='<tr>';
	   html+='<td  class="textcenterright" style="width:135px"><img src="/arjccm/static/modules/map/images/pub/'+data[type]["src"]+'" /></td>';
	   html+='<td  class="textcenterleft" style="font-size:20px;padding-left:24px">'+type+'</td>';
	   html+='</tr>';
	   html+='<tr class="l-grid-row-alt">';
	   html+='<td class="textcenterright">性质：</td>';
	   html+='<td class="textcenterleft">'+data[type]["性质"]+'</td>';
	   html+='</tr>';
	   html+='<tr>';
	   html+='<td  class="textcenterright">重点部位：</td>';
	   html+='<td class="textcenterleft">'+data[type]["重点部位"]+'</td>';
	   html+='</tr>';
	   html+='<tr class="l-grid-row-alt">';
	   html+='<td  class="textcenterright">单位地址：</td>';
	   html+='<td class="textcenterleft">'+data[type]["单位地址"]+'</td>';
	   html+='</tr>';
	   html+='<tr>';
	   html+='<td  class="textcenterright">基本情况：</td>';
	   html+='<td class="textcenterleft">'+data[type]["基本情况"]+'</td>';
	   html+='</tr>';
	   html+='</table>';
	 
	   $('.pubMapDialog-center-center').html(html)
   }else if(type=='烟花爆竹专营店(新华路三店)'){
	   var data={
			   "烟花爆竹专营店(新华路三店)":{
					"性质":"涉危涉爆重点单位",  
		            "重点部位":"仓库",
		            "单位地址":"郑州市新密市南密新路168号",
		            "基本情况":"烟花爆竹专营店已取得取得《烟花爆竹经营（批发）许可证》和《烟花爆竹经营（零售）许可证》的规范专营店",
		            'src':'baozhu.jpg'
					}
	   }
	   var html="";
	   html+='<table style="height:100%">';
	   html+='<tr>';
	   html+='<td  class="textcenterright" style="width:135px"><img src="/arjccm/static/modules/map/images/pub/'+data[type]["src"]+'" /></td>';
	   html+='<td  class="textcenterleft" style="font-size:20px;padding-left:24px">'+type+'</td>';
	   html+='</tr>';
	   html+='<tr class="l-grid-row-alt">';
	   html+='<td class="textcenterright">性质：</td>';
	   html+='<td class="textcenterleft">'+data[type]["性质"]+'</td>';
	   html+='</tr>';
	   html+='<tr>';
	   html+='<td  class="textcenterright">重点部位：</td>';
	   html+='<td class="textcenterleft">'+data[type]["重点部位"]+'</td>';
	   html+='</tr>';
	   html+='<tr class="l-grid-row-alt">';
	   html+='<td  class="textcenterright">单位地址：</td>';
	   html+='<td class="textcenterleft">'+data[type]["单位地址"]+'</td>';
	   html+='</tr>';
	   html+='<tr>';
	   html+='<td  class="textcenterright">基本情况：</td>';
	   html+='<td class="textcenterleft">'+data[type]["基本情况"]+'</td>';
	   html+='</tr>';
	   html+='</table>';
	   $('.pubMapDialog-center-center').html(html)
   }
	
}

function shipinjiankongXiangqingFun(src,name){
	$('.pubMapDialog').show()
	if(name=='视频监控1'||name=='视频监控2'||name=='视频监控3'||name=='视频监控4'){
		var html='';
		html+='<video width="440" height="250"  controls="controls" autoplay="autoplay" loop="loop" class="videoBtn">';
		html+='<source src="/arjccm/static/modules/map/images/pub/video/'+src+'" type="video/mp4">';  
		html+='</video>';
		$('.pubMapDialog-center-center').html(html)
	}else if(name=="豫A0S81Q"||name=="豫A0519H"){
		 var data={
				   "豫A0S81Q":{
					   "任务":"治安巡逻",  
			            "重点路线":"长乐路",
			            "所属单位":"广场警务工作站",
			            "目前位置":"新密市西大街西段1号"			
			        },
					"豫A0519H":{
						"任务":"治安巡逻", 
			            "重点路线":"长乐路",
			            "所属单位":"长乐路警务工作站",
			            "目前位置":"新密市西大街西段1号"
						}
		   }
		var html='';
		 html+='<table style="height:100%">';
		   html+='<tr>';
		   html+='<td  class="textcenterright" style="width:135px"><img src="/arjccm/static/modules/map/images/pub/jingche2.png" /></td>';
		   html+='<td  class="textcenterleft" style="font-size:20px;padding-left:24px">'+name+'</td>';
		   html+='</tr>';
		   html+='<tr class="l-grid-row-alt">';
		   html+='<td class="textcenterright">任务：</td>';
		   html+='<td class="textcenterleft">'+data[name]["任务"]+'</td>';
		   html+='</tr>';
		   html+='<tr>';
		   html+='<td  class="textcenterright">重点路线：</td>';
		   html+='<td class="textcenterleft">'+data[name]["重点路线"]+'</td>';
		   html+='</tr>';
		   html+='<tr class="l-grid-row-alt">';
		   html+='<td  class="textcenterright">所属单位：</td>';
		   html+='<td class="textcenterleft">'+data[name]["所属单位"]+'</td>';
		   html+='</tr>';
		   html+='<tr>';
		   html+='<td  class="textcenterright">目前位置：</td>';
		   html+='<td class="textcenterleft">'+data[name]["目前位置"]+'</td>';
		   html+='</tr>';
		   html+='</table>';
		   $('.pubMapDialog-center-center').html(html)
		
	}else if(name=="吕一凡"||name=="王庆豪"){
		 var data={
				   "吕一凡":{
						"任务":"治安巡逻",  
			            "重点路线":"长乐路",
			            "所属单位":"广场警务工作站",
			            "目前位置":"新密市大鸿路159号"
						},
					 "王庆豪":{
							"任务":"治安巡逻",  
				            "重点路线":"长乐路",
				            "所属单位":"长乐路警务工作站",
				            "目前位置":"新密市大鸿路159号"
							}		
		   }
			var html='';
		  html+='<table style="height:100%">';
		   html+='<tr>';
		   html+='<td  class="textcenterright" style="width:135px"><img src="/arjccm/static/modules/map/images/pub/jingcha2.png" /></td>';
		   html+='<td  class="textcenterleft" style="font-size:20px;padding-left:24px">'+name+'</td>';
		   html+='</tr>';
		   html+='<tr class="l-grid-row-alt">';
		   html+='<td class="textcenterright">任务：</td>';
		   html+='<td class="textcenterleft">'+data[name]["任务"]+'</td>';
		   html+='</tr>';
		   html+='<tr>';
		   html+='<td  class="textcenterright">重点路线：</td>';
		   html+='<td class="textcenterleft">'+data[name]["重点路线"]+'</td>';
		   html+='</tr>';
		   html+='<tr class="l-grid-row-alt">';
		   html+='<td  class="textcenterright">所属单位：</td>';
		   html+='<td class="textcenterleft">'+data[name]["所属单位"]+'</td>';
		   html+='</tr>';
		   html+='<tr>';
		   html+='<td  class="textcenterright">目前位置：</td>';
		   html+='<td class="textcenterleft">'+data[name]["目前位置"]+'</td>';
		   html+='</tr>';
		   html+='</table>';
		   $('.pubMapDialog-center-center').html(html)
	}
	
}
