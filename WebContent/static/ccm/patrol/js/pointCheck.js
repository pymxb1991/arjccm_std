var Map, map;
$(document).ready(function() {
		// 地图默认数据设置
		var defaultPrams = {
			id : 'map',
			centerCoordinate :centerCoordinate,
			zoom : zoomIndex,
			maxZoom : 20,
			minZoom : 2,
			baseUrl : baseUrlT,
			zoomShowOrHide : false,// 缩小放大
			overviewMap : false
		// 鹰眼图
		};
		Map = new ArjMap.Map(defaultPrams);
		// 加载地图
		Map.init();
		map = Map.map;
		// 计划id
		var planid = $("#ccmPatrolPlanID").attr("attrid");
		// 点位
		var pointSort = $("#ccmPatrolPlanID").attr("attrpoint");
		console.log(planid);
		$.getJSON('' + ctx + '/patrol/ccmPatrolPlan/patrolPlanMap?planId='+planid+'&pointSort='+pointSort,
				function(data) {
					Map.addJSON2([ {
						'type' : 'PatrolPlan',
						'data' : data,
						'isShow' : true
					} ])
					
					var PlanArr=[];
					var coordinatesArr=[];
					var Data=data.features;
					var len=Data.length;
					if(len>0){
						for(var i=0;i<len;i++){
							if(Data[i].properties.info.flag){
								var sort=parseInt(Data[i].properties.info.sort);
								var id=Data[i].id;
								var coordinates=Data[i].geometry.coordinates;
								PlanArr[sort-1]={
									id:id,
									coordinates:coordinates
								}
								coordinatesArr[sort-1]=[Number(coordinates[0]),Number(coordinates[1])]
								Map.addOverlayLabel(sort,coordinates,id);
							
							}
						}
						Map.planFlagInfo(PlanArr);
						Map.drakLine(coordinatesArr);
						//范围-不显示路线，
						
						var val=$('#pointType option:selected').val();
						if(val=="2"){
							Map.layersIsShow('planFlagLine', false);
						}else{
							Map.layersIsShow('planFlagLine', true);
						}
					}
					
				});
		$('.tool-list-sign').click(function(){
			$('.tool-list-sign').addClass('active').children('.tool-sign').addClass('active');
			$('.tool-list-remove').removeClass('active').children('.tool-remove').removeClass('active');
		})
		
		$('.tool-list-remove').click(function(){
			$('.tool-list-remove').addClass('active').children('.tool-remove').addClass('active');
			$('.tool-list-sign').removeClass('active').children('.tool-sign').removeClass('active');
		})
		
		$('#pointType').change(function(){
			//范围-不显示路线，
			
			var val=$('#pointType option:selected').val();
			if(val=="2"){
				Map.layersIsShow('planFlagLine', false);
			}else{
				Map.layersIsShow('planFlagLine', true);
			}
		})
		
		});
