var Map,Pubmap;
var zTreeObjLeft
var streetFlag ,communityFlag ,gridFlag ,buildFlag ,eventFlag ,partsFlag , landsFlag,videoFlag ,schoolPlaceFlag ,keyPlaceFlag , keyPersonFlag, rentingPersonFlag ,publicPlaceFlag ,popLocationFlag ;
$(function() {
	// 滨海新区 centerCoordinate : [ 117.655020, 39.035450 ],
	// 津南区centerCoordinate : [117.29900836944581,39.03772830963135],
	// http://localhost:6080/arcgis/rest/services/hzd1/ImageServer
	
	
	// 地图默认数据设置
	var defaultPrams = {
		id : 'map',
		centerCoordinate : centerCoordinate,
		zoom : zoomIndex,
		maxZoom : 18,
		minZoom : 9.8,
		baseUrl : baseUrlT,
		zoomShowOrHide : false,// 缩小放大
		overviewMap : false,
		selectPointerFlag : true
	// 鹰眼图
	}
	Map = new ArjMap.Map(defaultPrams);
	// 加载地图
	Map.init();
	Pubmap=Map.map;
	
	//地图切换-影像图和普通地图相互切换
	function switchMap(_Map){
		var map=_Map.map;
		var baseUrl = _Map.baseUrl;
		var len=baseUrl.length;
		if(len>0){
			for(var i=0;i<len;i++){
				var id=baseUrl[i].id;
				//设置底图图层显示隐藏
				_Map[id+'Tile'].setVisible(!_Map[id+'Tile'].getVisible())
				
				}
			}
	}
	switchMap(Map);
	Map.map.getView().setZoom(11.7);	
	// 框选查询初始化
	Map.selectQueryInit();
	window.LayUIMap = Map;// 案事件调用LayUIMap
	// 加载数据
	var PopData = {
		"type" : "FeatureCollection",
		"centpoint" : [ "117.64277015018463", "39.03320166873932" ],
		"features" : [
				{
					"type" : "Feature",
					"id" : "4",
					"properties" : {
						"name" : "pjq13",
						"icon" : "location48.png",
						"info" : {
							"设备唯一标识码" : "rrrr312351532",
							"是否授权使用" : "20",
							"设备编号" : "4",
							"登录用户" : "pjq13"
						},
						"coordinateCentre" : [ "117.63877015018463",
								"39.03220166873932" ]
					},
					"geometry" : {
						"type" : "Point",
						"coordinates" : [ "117.63877015018463",
								"39.03220166873932" ]
					}
				}, {
					"type" : "Feature",
					"id" : "8",
					"properties" : {
						"name" : "系统管理员",
						"icon" : "location48.png",
						"info" : {
							"设备唯一标识码" : "aaaa312351532",
							"是否授权使用" : "30",
							"设备编号" : "8",
							"登录用户" : "系统管理员"
						},
						"coordinateCentre" : [ "117.654960", "39.029190" ]
					},
					"geometry" : {
						"type" : "Point",
						"coordinates" : [ "117.654960", "39.029190" ]
					}
				} ]
	}

	// 视频监控弹框---liveVideo
	/*
	 * $("#popup").on("click", ".click", function() { var type =
	 * $(this).attr('videoType'); var url = $(this).attr('videoUrl');
	 * videoDialog(type, url);
	 * 
	 *  }) function videoDialog(type, url) { $('#videoBtn').click();
	 * videoPlay(type, url) }
	 */
	//快捷切换查询
	$('.pubMapDialog-close').click(function(){
		$('.pubMapDialog').hide()
	})
	
    /*布局改变*/
    $(".relevance-bg").click(function(){
        $(".relevance").animate({"width":"400px","height":"150px"},300)
        $(".relevance-bg").hide();
        $('.unfold').css('bottom','3px');
        $('.unfold').css('left','3px')
        $('.map-2').hide();
        $('.map-4').show();
    })
    $(".re-close").click(function(){
        $(".relevance").animate({"width":0,"height":0},300)
        $(".relevance-bg").show()
        $('.unfold').css('bottom','3px');
        $('.unfold').css('left','3px')
        $('.map-4').hide();
        $('.map-2').show();
    })

	
	

	
	// ztreeLeft
	var settingLeft = {
		check : {
			enable : true
		},
		data : {
			simpleData : {
				enable : true
			}
		},
		callback : {
			onCheck : getCheckedNodesLeft
		}
	};


	
	

	/*--------------------------------------------------区域------------------------------------------------*/
	var zNodesleft = [{
		id : "communityGrid",
		pId : 0,
		name : "区域",
		open : true,
		checked : true
	}, {
		id : "street",
		pId : "communityGrid",
		name : "派出所",
		type : 4,
		icon : ctxStatic + '/modules/map/images/tree_jiedao.png',
		checked : true
	}, {
		id : "community",
		pId : "communityGrid",
		name : "警务室",
		type : 1,
		icon : ctxStatic + '/modules/map/images/tree_shequ.png',
		checked : true
	}, {
		id : "grid",
		pId : "communityGrid",
		name : "工作站",
		type : 2,
		icon : ctxStatic + '/modules/map/images/tree_grid.png',
		checked : true
	}/*, {
		id : "build",
		pId : "communityGrid",
		name : "楼栋",
		type : 0,
		typeVal : 1,
		icon : ctxStatic + '/modules/map/images/tree_shequ.png',
		checked : true
	}*/];
	
	
	var communityGridVal = 0;
	var streetVal = 0;
	var communityVal = 0;
	var gridVal = 0;
	var buildVal = 0;
	
	
	 eventFlag = true;
	 streetFlag = true;
	 communityFlag = false;
	 gridFlag = false;
	 buildFlag = false;
	
	
	
	
	
   
	$.fn.zTree.init($("#treeLeft"), settingLeft, zNodesleft);
	 zTreeObjLeft = $.fn.zTree.getZTreeObj("treeLeft");
	
//	$.fn.zTree.init($("#tree"), setting, zNodes);
//	var zTreeObj = $.fn.zTree.getZTreeObj("tree");
	
	
	
	
	
	getCheckedNodesLeft();
function getCheckedNodesLeft() {				
		var checked = "";
		var checkedNodes = zTreeObjLeft.getCheckedNodes(true);
		if (checkedNodes.length != 0) {
			var communityGridData = 0;
			var streetData = 0;
			var communityData = 0;
			var gridData = 0;
			var buildData = 0;
			
		
			for (var i = 0; i < checkedNodes.length; i++) {
				if (!checkedNodes[i].isParent) {
					checked += checkedNodes[i].id + ",";
					if (checkedNodes[i].pId == 'communityGrid') { // 街道社区网格
						communityGridData += parseInt(checkedNodes[i].type);
						if (checkedNodes[i].id == 'build') { // 楼栋
							buildData += parseInt(checkedNodes[i].typeVal)
						}
					}

					

				}
			}
			if (communityGridVal != communityGridData && communityGridData != 0) {
				communityGridVal = communityGridData;
				// alert('社区网格图层加载' + communityGridVal)
				Map.removeLayer('streets');
				Map.removeLayer('communitys');
				Map.removeLayer('grids');
				if (communityGridVal == '1') {
				
					$.getJSON('' + ctx + '/sys/map/orgAreaMap?type='
							+ communityGridVal + '', function(data) {
						Map.addJSON1([ {
							'type' : 'communitys',
							'data' : data,
							'isShow' : communityFlag
						} ])

					})
					console.log()
				} else if (communityGridVal == '2') {
					$.getJSON('' + ctx + '/sys/map/orgAreaMap?type='
							+ communityGridVal + '', function(data) {
						Map.addJSON1([ {
							'type' : 'grids',
							'data' : data,
							'isShow' : gridFlag
						} ])

					})
				} else if (communityGridVal == '4') {
					$.getJSON('' + ctx + '/sys/map/orgAreaMap?type='
							+ communityGridVal + '', function(data) {
						console.log(data)
						Map.addJSON1([ {
							'type' : 'streets',
							'data' : data,
							'isShow' : streetFlag
						} ])

					})
				} else if (communityGridVal == '3') {
					$.getJSON('' + ctx + '/sys/map/orgAreaMap?type=1',
							function(data) {
								Map.addJSON1([ {
									'type' : 'communitys',
									'data' : data,
									'isShow' : communityFlag
								} ])

							})
					$.getJSON('' + ctx + '/sys/map/orgAreaMap?type=2',
							function(data) {
								Map.addJSON1([ {
									'type' : 'grids',
									'data' : data,
									'isShow' : gridFlag
								} ])

							})
				} else if (communityGridVal == '5') {
					$.getJSON('' + ctx + '/sys/map/orgAreaMap?type=1',
							function(data) {
								Map.addJSON1([ {
									'type' : 'communitys',
									'data' : data,
									'isShow' : communityFlag
								} ])

							})
					$.getJSON('' + ctx + '/sys/map/orgAreaMap?type=4',
							function(data) {
								Map.addJSON1([ {
									'type' : 'streets',
									'data' : data,
									'isShow' : streetFlag
								} ])

							})

				} else if (communityGridVal == '6') {
					$.getJSON('' + ctx + '/sys/map/orgAreaMap?type=2',
							function(data) {
								Map.addJSON1([ {
									'type' : 'grids',
									'data' : data,
									'isShow' : gridFlag
								} ])

							})
					$.getJSON('' + ctx + '/sys/map/orgAreaMap?type=4',
							function(data) {
								Map.addJSON1([ {
									'type' : 'streets',
									'data' : data,
									'isShow' : streetFlag
								} ])

							})

				} else if (communityGridVal == '7') {
					
					$.getJSON('' + ctx + '/sys/map/orgAreaMap?type=1 ',
							function(data) {
					
								Map.addJSON1([ {
									'type' : 'communitys',
									'data' : data,
									'isShow' : communityFlag
								} ])

							})
					$.getJSON('' + ctx + '/sys/map/orgAreaMap?type=2',
							function(data) {
								Map.addJSON1([ {
									'type' : 'grids',
									'data' : data,
									'isShow' : gridFlag
								} ])

							})
					$.getJSON('' + ctx + '/sys/map/orgAreaMap?type=4',
							function(data) {
								Map.addJSON1([ {
									'type' : 'streets',
									'data' : data,
									'isShow' : streetFlag
								} ])

							})

				}

			} else if (communityGridVal != communityGridData
					&& communityGridData == 0) {
				communityGridVal = communityGridData;
				// alert('社区网格图层清除')
				Map.removeLayer('streets');
				Map.removeLayer('communitys');
				Map.removeLayer('grids');
			} else {
				// alert('社区网格图层不变')
			}
			if (buildVal != buildData && buildData != 0) {
				buildVal = buildData;
				// alert('案事件图层加载')
				Map.removeLayer('builds');

				$.getJSON('' + ctx + '/sys/map/buildmanageMap', function(data) {
					Map.addJSON1([ {
						'type' : 'builds',
						'data' : data,
						'isShow' : buildFlag,
					} ])
				})

			} else if (buildVal != buildData && buildData == 0) {
				buildVal = buildData;
				// alert('案事件图层清除')
				Map.removeLayer('builds');
			} else {
				// alert('案事件图层不变')
			}

			
		} else {

			Map.clearOverlays();
			// alert('清空图层')
			Map.removeLayer('communitys');
			Map.removeLayer('streets');
			Map.removeLayer('grids');
			Map.removeLayer('builds');
			$.getJSON('' + ctx + '/sys/map/ccmPatrolPointPlanMap', function(
					data) {
				var len = data.length;
				if (len > 0) {
					for (var i = 0; i < len; i++) {
						var id = data[i].type
						Map.removeLayer('' + id + '');
					}
				}
			})
			clearInterval(PopLocationTime);
			communityGridVal = 0;
			buildtVal = 0;
		}

	}
	
	// 监听地图层级变化

	var map = Map.map;// change:resolution
	map.getView().on('change:resolution', checkZoom);// checkZoom为调用的函数
	// 案事件change:resolution
	function checkZoom() {
		// console.log(map.getView().getZoom());
		var zoom = map.getView().getZoom();
		if (map.getView().getZoom() <= 12) {
			Map.layersIsShow('communitys', false);
			Map.layersIsShow('streets', true);
			Map.layersIsShow('grids', false);
			Map.layersIsShow('builds', false);
			Map.layersIsShow('schoolPlace', false);
			Map.layersIsShow('keyPlace', false);
			Map.layersIsShow('events', false);
			Map.layersIsShow('keyPerson', false);
			Map.layersIsShow('rentingPerson', false);
			Map.layersIsShow('lands', false);
			Map.layersIsShow('videos', false);
			// Map.layersIsShow('heatMap', true);
			// Map.layersIsShow('PopLocation',false);
			Map.layersIsShow('parts', false);
			Map.layersIsShow('publicPlace', false);
			communityFlag = false;
			streetFlag = true;
			gridFlag = false;
			buildFlag = false;
			eventFlag = false;
			partsFlag = false;
			landsFlag = false;
			videoFlag = false;
			PopulationDensityFlg = false;
			schoolPlaceFlag = false;
			keyPlaceFlag = false;
			keyPersonFlag = false;
			rentingPersonFlag = false;
			publicPlaceFlag = false;

			// Map.clearOverlays();

		} else if (map.getView().getZoom() <= 13
				&& map.getView().getZoom() > 12) {
			Map.layersIsShow('communitys', true);
			Map.layersIsShow('streets', false);
			Map.layersIsShow('grids', false);
			Map.layersIsShow('builds', false);
			Map.layersIsShow('schoolPlace', false);
			Map.layersIsShow('keyPlace', false);
			Map.layersIsShow('events', true);
			Map.layersIsShow('keyPerson', false);
			Map.layersIsShow('rentingPerson', false);
			Map.layersIsShow('lands', false);
			Map.layersIsShow('videos', false);
			// Map.layersIsShow('heatMap', true);
			// Map.layersIsShow('PopLocation',true);
			Map.layersIsShow('parts', false);
			Map.layersIsShow('publicPlace', false);
			communityFlag = true;
			streetFlag = false;
			gridFlag = false;
			buildFlag = false;
			eventFlag = true;
			partsFlag = false;
			landsFlag = false;
			videoFlag = false;
			PopulationDensityFlg = true;
			schoolPlaceFlag = false;
			keyPlaceFlag = false;
			keyPersonFlag = false;
			rentingPersonFlag = false;
			publicPlaceFlag = false;

			// Map.clearOverlays();

		} else if (map.getView().getZoom() > 13
				&& map.getView().getZoom() <= 15) {
			Map.layersIsShow('communitys', false);
			Map.layersIsShow('streets', false);
			Map.layersIsShow('grids', true);
			Map.layersIsShow('builds', false);
			Map.layersIsShow('schoolPlace', false);
			Map.layersIsShow('keyPlace', false);
			Map.layersIsShow('events', true);
			Map.layersIsShow('keyPerson', true);
			Map.layersIsShow('rentingPerson', true);
			Map.layersIsShow('lands', true);
			Map.layersIsShow('videos', true);
			// Map.layersIsShow('heatMap', true);
			// Map.layersIsShow('PopLocation',true);
			Map.layersIsShow('parts', false);
			Map.layersIsShow('publicPlace', false);

			communityFlag = false;
			streetFlag = false;
			gridFlag = true;
			buildFlag = false;
			eventFlag = true;
			partsFlag = false;
			landsFlag = true;
			videoFlag = true;
			PopulationDensityFlg = true;
			schoolPlaceFlag = true;
			keyPlaceFlag = true;
			keyPersonFlag = false;
			rentingPersonFlag = true;
			publicPlaceFlag = false;
			// Map.clearOverlays();

		} else if (map.getView().getZoom() > 15) {
			Map.layersIsShow('communitys', false);
			Map.layersIsShow('streets', false);
			Map.layersIsShow('grids', false);
			Map.layersIsShow('builds', true);
			Map.layersIsShow('schoolPlace', true);
			Map.layersIsShow('keyPlace', true);
			Map.layersIsShow('events', true);
			Map.layersIsShow('keyPerson', true);
			Map.layersIsShow('rentingPerson', true);
			Map.layersIsShow('lands', true);
			Map.layersIsShow('videos', true);
			Map.layersIsShow('parts', true);
			Map.layersIsShow('publicPlace', true);
			Map.layersIsShow('PopLocation', true);
			communityFlag = false;
			streetFlag = false;
			gridFlag = false;
			buildFlag = true;
			eventFlag = true;
			partsFlag = true;
			landsFlag = true;
			videoFlag = true;
			schoolPlaceFlag = true;
			keyPlaceFlag = true;
			keyPersonFlag = true;
			rentingPersonFlag = true;
			publicPlaceFlag = true;
			// Map.clearOverlays();

		}
	}


});