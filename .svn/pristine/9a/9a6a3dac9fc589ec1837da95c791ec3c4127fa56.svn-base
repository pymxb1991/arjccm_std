$(function() {
	// 地图默认数据设置
	var defaultPrams = {
		id : 'map',
		centerCoordinate : [ 117.632800, 39.037190 ],
		zoom : 13,
		maxZoom : 20,
		minZoom : 2,
		baseUrl : {
			'name' : '底图 ',
			'url' : 'http://t0.tianditu.com/DataServer?T=vec_w&x={x}&y={y}&l={z}',
			'isShow' : true
		},
		urlArr : [ {
			'name' : '银川地图 ',
			'url' : 'http://192.168.1.22:6080/arcgis/rest/services/YinChuan/MapServer',
			'isShow' : true
		} ],
		zoomShowOrHide : true,
		// 缩小放大
		overviewMap : true
	// 鹰眼图
	}

	var Map = new ArjMap.Map(defaultPrams);
	// 加载地图
	Map.drawMap();

	// 加载数据
	$.getJSON('' + ctx + '/sys/map/buildmanageMap', function(data) {
		Map.addJSON([ {
			'name' : '楼栋 ',
			'data' : data,
			'isShow' : true
		} ]);
		// 图层显示隐藏
		Map.layersControl('layerTree');
		// 定位
		// Map.goTo();
	});

	// 楼栋弹框
	$("#popup").on("click", ".bulidclick", function(e) {
		var id = $(this).attr('featureId') || "";
		var pilesNum = $(this).attr('pilesNum') || 0; // 层数
		var elemNum = $(this).attr('elemNum') || 0 ; // 单元数
		var buildName = $(this).attr('buildName') || ""; // 楼栋名称
		var houseId = null;
		var doorNum = null;
		// 加载信息
		$('#buildBtn').trigger("click");
		$("#build-details").load(ctx + "/house/ccmHouseBuildmanage/Maplist", {
			"id" : id,
			"pilesNum" : pilesNum,
			"buildName" : buildName,
			"elemNum" : elemNum
		});
	});

	// 房屋事件
	$("#myModal ")
			.on(
					"click",
					".houseclick",
					function(e) {
						// 取消原本事件
						e.preventDefault();
						var houseId = $(this).attr('houseId') || "";
						var buildName = $(this).attr('buildName'); // 楼栋名称
						var elemNum = $(this).attr('elemNum'); // 单元数
						var pilesNum = $(this).attr('pilesNum'); // 层数
						var doorNum = $(this).attr('doorNum'); // 门牌号
						var popId = null;
						// 判断是否为空 ，为空则不进行出发
						if (houseId == "" || houseId == undefined
								|| houseId == null) {
							alert("暂无数据");
							return false;
						} else {
							// 页面加载
							$("#house-details")
									.load(
											ctx
													+ "/pop/ccmPopTenant/getHouseMapPopList",
											{
												"id" : houseId,
												"pilesNum" : pilesNum,
												"buildName" : buildName,
												"elemNum" : elemNum,
											},
											function() {
												// 隐藏 原本数据
												$("#build-details").hide();
												var html = '';
												html += '<button class="jbox-button closeHouse " style="padding: 0px 10px 0px 10px;">返回</button>';
												html += '<button class="jbox-button" style="padding: 0px 10px 0px 10px;" 	data-dismiss="modal" aria-hidden="true">关闭</button>';
												$('#modal-footer').html(html);
											});
						}
					});

	// 人员信息弹框
	$("#myModal")
			.on(
					"click",
					".popclick",
					function(e) {
						e.preventDefault();
						var popId = $(this).attr('popId');
						var buildName = $(this).attr('buildName'); // 楼栋名称
						var elemNum = $(this).attr('elemNum'); // 单元数
						var pilesNum = $(this).attr('pilesNum'); // 层数
						var doorNum = $(this).attr('doorNum'); // 门牌号
						$("#pop-details").load(
								ctx + "/pop/ccmPeople/getMapPopForm", {
									"id" : popId,
									"pilesNum" : pilesNum,
									"buildName" : buildName,
									"elemNum" : elemNum,
								}, function() {
									$("#house-details").hide();
								});

						var html = '';
						html += '<button class="jbox-button closePop" style="padding: 0px 10px 0px 10px;">返回</button>';
						html += '<button class="jbox-button" style="padding: 0px 10px 0px 10px;" 	data-dismiss="modal" aria-hidden="true">关闭</button>';
						$('#modal-footer').html(html)

					});

	// 定义房屋返回事件
	$("#myModal")
			.on(
					"click",
					".closeHouse",
					function(e) {
						e.preventDefault();
						// 点击返回事件
						var html = '';
						html += '<button class="jbox-button" style="padding: 0px 10px 0px 10px;" 	data-dismiss="modal" aria-hidden="true">关闭</button>';
						$('#modal-footer').html(html)
						$("#build-details").show();
						$("#house-details").empty();
					});

	// 定义人员返回事件
	$("#myModal")
			.on(
					"click",
					".closePop",
					function(e) {
						e.preventDefault();
						// 点击返回事件
						var html = '';
						html += '<button class="jbox-button closeHouse " style="padding: 0px 10px 0px 10px;">返回</button>';
						html += '<button class="jbox-button" style="padding: 0px 10px 0px 10px;" 	data-dismiss="modal" aria-hidden="true">关闭</button>';
						$('#modal-footer').html(html)
						$("#house-details").show();
						$("#pop-details").empty();
					});

	// 定义页面向左跳转事件
	$("#myModal").on("click", ".page-left", function(e) {
		//  当前的 页码数 
		var Num = $(".houseView .house-center.NumView").attr("Num");
		// 当前 数据为 0
		if(Num<=0){
			alert("已无数据");
			return;
		} 
		
		$(".houseView .house-center .Elem"+Number(Num-4)).removeClass("hide");
		$(".houseView .house-center .Elem"+ Num).addClass("hide");
		$(".houseView .house-center.NumView").attr("Num",Number(Num)-4);
	});

	// 定义页面向右跳转事件
	$("#myModal").on("click", ".page-right", function(e) {
		// 当前的 页码数 
		var Num = $(".houseView .house-center.NumView").attr("Num");
		var MaxNum = $(".houseView .house-center.NumView").attr("maxnum");
		// 当前 数据为 0
		if(Num>=MaxNum){
			alert("已无数据");
			return;
		} 
		
		$(".houseView .house-center .Elem"+Number(Num+4)).removeClass("hide");
		$(".houseView .house-center .Elem"+Num).addClass("hide");
		$(".houseView .house-center.NumView").attr("Num",Number(Num)+4);
	});

})