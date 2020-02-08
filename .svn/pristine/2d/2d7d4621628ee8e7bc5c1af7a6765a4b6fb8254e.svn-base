$(function() {
	/*-----------------------------------------------业务---------------------------------------------------------*/
	var   businessList =[{id : "keyPerson",name : "重点人员楼栋",icon : ctxStatic + '/modules/map/images/mapBusiness/keyPerson3.png'},
		                // {id : "keyPersonCountry",name : "重点人员分布",icon : ctxStatic + '/modules/map/images/mapBusiness/keyPersonCountry.png'}, 
		                 {id : "keyPersonHandle",name : "重点人员管控",icon : ctxStatic + '/modules/map/images/mapBusiness/keyPersonHandle.png'}, 
		                 {id : "rentingPerson",name : "出租屋楼栋",icon : ctxStatic + '/modules/map/images/mapBusiness/rentingPerson.png'}, 
		                 {id : "event",name : "案事件",icon : ctxStatic + '/modules/map/images/mapBusiness/event1.png'},
		                 {id : "PopulationDensity",name : "人口密度",icon : ctxStatic + '/modules/map/images/mapBusiness/PopulationDensity.png'},
		                 {id : "PopLocation",name : "实时定位",icon : ctxStatic + '/modules/map/images/mapBusiness/PopLocation.png'} 
		                
		                ]                  
	  /* -------------------重点人员楼栋子集------------------  */                
	var keyPersonlist = [{id : "xingman",pId : "keyPerson",name : "安置帮教人员",icon : ctxStatic + '/modules/map/images/mapBusiness/bangpai1.png',flag : "5",},
		                 {id : "shequ",pId : "keyPerson",name : "社区矫正人员",icon : ctxStatic + '/modules/map/images/mapBusiness/jiaozheng1.png',flag : "6",}, 
		                 {id : "jingshen",pId : "keyPerson",name : "肇事肇祸等严重精神障碍患者",icon : ctxStatic + '/modules/map/images/mapBusiness/tree_psy1.png',flag : "2",},
		                 {id : "xidu",pId : "keyPerson",name : "吸毒人员",icon : ctxStatic + '/modules/map/images/mapBusiness/drug1.png',flag : "4",}, 
		                 {id : "aizi",pId : "keyPerson",name : "艾滋病危险人员",icon : ctxStatic + '/modules/map/images/mapBusiness/aids1.png',flag : "1",},
		                 {id : "shangfang",pId : "keyPerson",name : "重点上访人员",icon : ctxStatic + '/modules/map/images/mapBusiness/shangfang1.png',flag : "8",},
                         {id : "shejiao",pId : "keyPerson",name : "涉教人员",icon : ctxStatic + '/modules/map/images/mapBusiness/shejiao1.png',flag : "9",}, 
                         {id : "weixian",pId : "keyPerson",name : "危险品工作人员",icon : ctxStatic + '/modules/map/images/mapBusiness/weixianpin1.png',flag : "10",}, 
                         {id : "liushou",pId : "keyPerson",name : "留守人员",icon : ctxStatic + '/modules/map/images/mapBusiness/liushou1.png',flag : "7",}, 
                         {id : "qingshaonian",pId : "keyPerson",name : "重点青少年",icon : ctxStatic + '/modules/map/images/mapBusiness/boy1.png',flag : "3",} 
                         ];
	  /* -------------------重点人员分布子集------------------     */    
var	keyPersonCountrylist =[{id : "aiziCountry",pId : "keyPersonCountry",name : "艾滋病危险人员",icon : ctxStatic + '/modules/map/images/mapBusiness/aids2.png',flag : "1",},
		                   {id : "jingshenCountry",pId : "keyPersonCountry",name : "肇事肇祸等严重精神障碍患者",icon : ctxStatic + '/modules/map/images/mapBusiness/tree_psy2.png',flag : "2",},
		                   {id : "qingshaonianCountry",pId : "keyPersonCountry",name : "重点青少年",icon : ctxStatic + '/modules/map/images/mapBusiness/boy2.png',flag : "3",}, 
		                   {id : "xiduCountry",pId : "keyPersonCountry",name : "吸毒人员",icon : ctxStatic + '/modules/map/images/mapBusiness/drug2.png',flag : "4",},
		                   {id : "xingmanCountry",pId : "keyPersonCountry",name : "安置帮教人员",icon : ctxStatic + '/modules/map/images/mapBusiness/bangpai2.png',flag : "5",},
		                   {id : "shequCountry",pId : "keyPersonCountry",name : "社区矫正人员",icon : ctxStatic + '/modules/map/images/mapBusiness/jiaozheng2.png',flag : "6",},
		                   {id : "liushouCountry",pId : "keyPersonCountry",name : "留守人员",icon : ctxStatic + '/modules/map/images/mapBusiness/liushou2.png',flag : "7",},
		                   {id : "shangfangCountry",pId : "keyPersonCountry",name : "重点上访人员",icon : ctxStatic + '/modules/map/images/mapBusiness/shangfang2.png',flag : "8",}, 
		                   {id : "shejiaoCountry",pId : "keyPersonCountry",name : "涉教人员",icon : ctxStatic + '/modules/map/images/mapBusiness/shejiao2.png',flag : "9",}, 
		                   {id : "weixianCountry",pId : "keyPersonCountry",name : "危险品工作人员",icon : ctxStatic + '/modules/map/images/mapBusiness/weixianpin2.png',flag : "10",}
		                   ];
	
	businessStart();
	function  businessStart() {
		//重点人员楼栋 子窗口
		var keyPerson="<div class='detail-box ' id='keyPerson_div' style='display:none;'>"+			
		 " <div class='keyPersonTypeBtn' style=' margin:4px 0 10px  0;'>" +
		 "<input id='keyPersonCheckbox' class='input-medium' style=' margin: 0px 0px 0 8px;' type='checkbox' >&nbsp;全选&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
		 "<a id='keyPersonClose' style='float:right;font-size:16px; margin: 0px 6px 0 0;' ><i class=' icon-remove' ></i></a>"+
		 "<div id='keyPerson_indiv'> </div>"+
		 "</div>"+
       "</div>";
		//重点人员分布 子窗口
		var keyPersonCountry="<div class='detail-box ' id='keyPersonCountry_div' style='display:none;'>"+			
		 " <div class='keyPersonCountryTypeBtn' style=' margin:4px 0 10px 0;'>" +
		 "<input id='keyPersonCountryCheckbox' class='input-medium' style=' margin: 0px 0px 0 8px;' type='checkbox' >&nbsp;全选&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
		 "<a id='keyPersonCountryClose' style='float:right;font-size:16px; margin: 0px 6px 0 0;' ><i class=' icon-remove' ></i></a>"+
		 "<div id='keyPersonCountry_indiv'> </div>"+
		 "</div>"+
      "</div>";
		
		//$("#businessZtree").html(keyPerson+keyPersonCountry)
		$("#businessZtree").html(keyPerson)
		//重点人员楼栋 子窗口 关闭
		$("#keyPersonClose").on("click", function(){
			$("#keyPerson_div").hide()
		});
		//重点人员楼栋 子窗口  加入子内容
		for ( var i in keyPersonlist) {
			  var keyPersonSpantxt=keyPersonlist[i].name;
				if(keyPersonSpantxt.length>4){
					keyPersonSpantxt=keyPersonSpantxt.substring(0,4)+"...";				
				}
			keyPersonlistDiv = '<div id="keyPerson_list_div_'+keyPersonlist[i].id+'"   dateflag="'+keyPersonlist[i].flag+'"  datepid="'+keyPersonlist[i].pid+'"  dateid="'+keyPersonlist[i].id+'" title="'+keyPersonlist[i].name+'"  class="keyPerson_list_div "><div class="keyPerson_list_img" style=" background: url('+keyPersonlist[i].icon+')  no-repeat center; background-size: 90% 90%;"></div>'+keyPersonSpantxt+'</div>'
			$("#keyPerson_indiv").append(keyPersonlistDiv);
			$("#keyPerson_list_div_"+keyPersonlist[i].id).on("click", function () {
				if($(this).hasClass('keyPerson_choose')){
					$(this).removeClass('keyPerson_choose');
				}else{
					$(this).addClass('keyPerson_choose');
				}
				getkeyPersonChoose();
			});
		   }
		//重点人员楼栋 子窗口 全选
		 $("#keyPersonCheckbox").on("click", function () {
			 
				if($(this).prop("checked")){
					$(".keyPerson_list_div").addClass('keyPerson_choose');
				}else{
					$(".keyPerson_list_div").removeClass('keyPerson_choose');
				}
				getkeyPersonChoose();
			}); 
		 //重点人员分布 子窗口 关闭
			$("#keyPersonCountryClose").on("click", function(){
				$("#keyPersonCountry_div").hide()
			});
		 //重点人员分布 子窗口加入子内容
			for ( var i in keyPersonCountrylist) {
				  var keyPersonCountrySpantxt=keyPersonCountrylist[i].name;
					if(keyPersonCountrySpantxt.length>4){
						keyPersonCountrySpantxt=keyPersonCountrySpantxt.substring(0,4)+"...";				
					}
				keyPersonCountrylistDiv = '<div id="keyPersonCountry_list_div_'+keyPersonCountrylist[i].id+'"   dateflag="'+keyPersonCountrylist[i].flag+'"  datepid="'+keyPersonCountrylist[i].pid+'"  dateid="'+keyPersonCountrylist[i].id+'" title="'+keyPersonCountrylist[i].name+'"  class="keyPersonCountry_list_div "><div class="keyPersonCountry_list_img" style=" background: url('+keyPersonCountrylist[i].icon+')  no-repeat center; background-size: 90% 90%;"></div>'+keyPersonCountrySpantxt+'</div>'
				$("#keyPersonCountry_indiv").append(keyPersonCountrylistDiv);
				$("#keyPersonCountry_list_div_"+keyPersonCountrylist[i].id).on("click", function () {
					if($(this).hasClass('keyPersonCountry_choose')){
						$(this).removeClass('keyPersonCountry_choose');
					}else{
						$(this).addClass('keyPersonCountry_choose');
					}
					getkeyPersonCountryChoose();
				});
			   }
			//重点人员分布 子窗口 全选
			 $("#keyPersonCountryCheckbox").on("click", function () {
					if($(this).prop("checked")){
						$(".keyPersonCountry_list_div").addClass('keyPersonCountry_choose');
					}else{
						$(".keyPersonCountry_list_div").removeClass('keyPersonCountry_choose');
					}
					getkeyPersonCountryChoose();
				}); 
		
	var businessListDiv="";	   
	for ( var i in businessList) {
		  var spantxt=businessList[i].name;
			if(spantxt.length>6){
				spantxt=spantxt.substring(0,6)+"...";				
			}
		businessListDiv = '<div id="business_list_div_'+businessList[i].id+'"  dateid="'+businessList[i].id+'"  class="business_list_div " title="'+businessList[i].name+'" ><div class="business_list_img" style=" background: url('+businessList[i].icon+')  no-repeat center; background-size: 90% 90%;"></div>'+spantxt+'</div>'
		$("#businessZtree").append(businessListDiv);
		$("#business_list_div_"+businessList[i].id).on("click", function () {
			 keyPersonHandleDate=0;
			 if($(this).attr("dateid") == "keyPerson"){
				//alert('重点人员楼栋')			
				$("#keyPerson_div").show();
				
			}else if($(this).attr("dateid") == "keyPersonCountry"){
				//alert('重点人员分布')
				$("#keyPersonCountry_div").show();
				
			}else{
				if($(this).hasClass('business_choose')){
					$(this).removeClass('business_choose');
					if($(this).attr("dateid") == "keyPersonHandle"){
						$("#business_list_div_PopLocation").removeClass('business_choose');
					}
				}else{
					$(this).addClass('business_choose');
					if($(this).attr("dateid") == "keyPersonHandle"){
						$("#business_list_div_PopLocation").addClass('business_choose');
						keyPersonHandleDate=1;
					}
				}
				getBusinessChoose();
			}
			
		});
	
	}
	
	}
	var keyPersonHandleDate
	var PopLocationTime;
	function getBusinessChoose() {
		Map.removeLayer('events');
		Map.removeLayer('heatMap');
		Map.removeLayer('rentingPerson');
		// alert('重点人员管控清除')
		Map.removeLayer('ElectronicFence');
		Map.removeLayer('trackReplay');
		Map.clearOverlays();
		Map.removeLayer('publicPlace');
		$.getJSON('' + ctx + '/sys/map/ccmPatrolPointPlanMap',
				function(data) {
					var len = data.length;
					if (len > 0) {
						for (var i = 0; i < len; i++) {
							var id = data[i].type
							Map.removeLayer('' + id + '');
						}
					}
				})
		//实时定位清除
		Map.removeLayer('PopLocation');
		clearInterval(PopLocationTime);
		
		var businessChooseList = $("#businessDiv .businessZtree .business_choose");
		if(businessChooseList.size()>0){
		for (var i = 0; i < businessChooseList.size(); i++) {		
			var businessChoose = businessChooseList[i];
			var businessChooseId=$(businessChoose).attr("dateid");
			
			function GetPopLocation() {
				Map.removeLayer('PopLocation');
				$.getJSON('' + ctx + '/sys/map/deviceMobileMap',
						function(data) {
							Map.addJSON1([ {
								'type' : 'PopLocation',
								'data' : data,
								'isShow' : popLocationFlag 
							} ]);
						})

				Map.layersIsShow('PopLocation', true);
			}
			if(businessChooseId == "keyPersonHandle" ){
				//alert("重点人员管控")
				// alert('公共机构加载-')
					Map.removeLayer('publicPlace');
					$.getJSON('' + ctx + '/sys/map/ccmOrgCommonalityMap?type='
							+ "1" + '', function(data) {
						Map.addJSON1([ {
							'type' : 'publicPlace',
							'data' : data,
							'isShow' : publicPlaceFlag
						} ])
					})
					$.getJSON(
									'' + ctx + '/sys/map/ccmPatrolPointPlanMap',
									function(data) {
										var lenData = data.length;
										var color = [ "#62c000", "#8e7513",
												"#3268ff", "#e73400", "#72fffd",
												"#c4c501", "#ffff80", "#fd6d01",
												"#951167", "#e822e7", "#804040" ]
										if (lenData > 0) {
											for (var i = 0; i < lenData; i++) {
												var str = data[i].value;
												var routeCoords = str.split(';')
												var len = routeCoords.length;
												var id = data[i].type
												var colorLine = color[i]
												Map.removeLayer('' + id + '');
												var pushArr = []
												for (var j = 0; j < len; j++) {
													var value = [];
													value[0] = Number(routeCoords[j]
															.split(',')[0]);
													value[1] = Number(routeCoords[j]
															.split(',')[1]);
													pushArr.push(value);
												}
												Map.trackReplayLu(' ', 5, pushArr,
														id, colorLine);
											}
										}
									})
				if(keyPersonHandleDate == 1){	
					keyPersonHandleLayerFun()
				}else{					
					keyPersonHandleLayerClose()
				}				
			}else if(businessChooseId == "rentingPerson"){
				//alert('出租屋楼栋')
				Map.removeLayer('rentingPerson');
				$.getJSON('' + ctx + '/sys/map/buildmanageMapType?type=2',
						function(data) {
							Map.addJSON1([ {
								'type' : 'rentingPerson',
								'data' : data,
								'isShow' : rentingPersonFlag 
							} ])
						})
			}else if(businessChooseId == "event"){
				//alert("案事件")
				Map.removeLayer('events');
				$.getJSON('' + ctx + '/sys/map/eventIncidentMap?type='
						+ '1' + '', function(data) {
					Map.addJSON1([ {
						'type' : 'events',
						'data' : data,
						'isShow' : eventFlag ,
					} ])
				})
			}else if(businessChooseId == "PopulationDensity"){
				//alert('人口密度')
				Map.removeLayer('heatMap');
				/* 热力图 */
				$.getJSON('' + ctx + '/sys/map/buildHeatMap', function(
						heatmapData) {
					Map.removeLayer('heatMap')
					Map.heatMap1(heatmapData);
					Map.layersIsShow('heatMap', true);
					// //Map.layersIsShow('heatMap',true);
				})
			}else if(businessChooseId == "PopLocation"){
				//alert('实时定位')
				GetPopLocation()
				PopLocationTime = setInterval(function() // 开启循环：每秒出现一次提示框
				{
					GetPopLocation()
				}, 15000);
			}
		 }
		}else{
			Map.clearOverlays();
			Map.removeLayer('PopLocation');
			Map.removeLayer('events');
			Map.removeLayer('heatMap');
			Map.removeLayer('rentingPerson');
			Map.removeLayer('publicPlace');
			// alert('重点人员管控清除')
			$.getJSON('' + ctx + '/sys/map/ccmPatrolPointPlanMap',
					function(data) {
						var len = data.length;
						if (len > 0) {
							for (var i = 0; i < len; i++) {
								var id = data[i].type
								Map.removeLayer('' + id + '');
							}
						}
					})
			keyPersonHandleLayerClose()
			//实时定位清除
	        Map.removeLayer('ElectronicFence');
			Map.removeLayer('trackReplay');
			Map.removeLayer('PopLocation');
			clearInterval(PopLocationTime);
		}
          
	}
	// 重点人员楼栋
	var keyPersonVal =""
	function getkeyPersonChoose() {
		var keyPersonChooseList = $("#businessZtree  .keyPerson_choose");
		if(keyPersonChooseList.size()>0){
			$("#business_list_div_keyPerson").addClass('business_choose');
			var keyPersonStr=""
			for (var i = 0; i < keyPersonChooseList.size(); i++) {			
				var keyPersonChoose = $(keyPersonChooseList[i]);
				var keyPersonChooseflag=keyPersonChoose.attr("dateflag");
				keyPersonStr += keyPersonChooseflag + ","
			}
			
			if (keyPersonStr != "" && keyPersonVal != keyPersonStr) {
				// alert('重点人员加载')
				keyPersonVal = keyPersonStr;
				$.getJSON('' + ctx + '/sys/map/buildmanageMapType?type=1&flag='
						+ keyPersonStr, function(data) {
					Map.removeLayer('keyPerson');
					Map.addJSON1([ {
						'type' : 'keyPerson',
						'data' : data,
						'isShow' : keyPersonFlag
					} ])
				})
			} else if (keyPersonStr == "" && keyPersonVal != keyPersonStr) {
				// alert('重点人员清除')
				keyPersonVal = keyPersonStr;
				Map.removeLayer('keyPerson');
			} else{
				// alert('重点人员不变')
			}
		}else{
			keyPersonVal = "";
			$("#business_list_div_keyPerson").removeClass('business_choose');
			Map.removeLayer('keyPerson');
		}
	}
	
	
	
	// 重点人员分布
	var keyPersonCountryVal =""
		function getkeyPersonCountryChoose() {
			var keyPersonCountryChooseList = $("#businessZtree  .keyPersonCountry_choose");
			if(keyPersonCountryChooseList.size()>0){
				$("#business_list_div_keyPersonCountry").addClass('business_choose');
				var keyPersonCountryStr=""
				for (var i = 0; i < keyPersonCountryChooseList.size(); i++) {			
					var keyPersonCountryChoose = $(keyPersonCountryChooseList[i]);
					var keyPersonCountryChooseflag=keyPersonCountryChoose.attr("dateflag");
					keyPersonCountryStr += keyPersonCountryChooseflag + ","
				}
				
				// 重点人员分布
				keyPersonCountryStr = keyPersonCountryStr.substring(0,
						keyPersonCountryStr.length - 1)
				if (keyPersonCountryStr != "" && keyPersonCountryVal != keyPersonCountryStr) {
					// alert('重点人员分布')

					keyPersonCountryVal = keyPersonCountryStr;
					$.getJSON('' + ctx + '/sys/map/buildHeatMap?type='
							+ keyPersonCountryStr, function(data) {
						Map.removeLayer('heatMap')
						/* 热力图 */
						Map.heatMap1(data);
						Map.layersIsShow('heatMap', true);
					})
				} else if (keyPersonCountryStr == ""
						&& keyPersonCountryVal != keyPersonCountryStr) {
					// alert('重点人员分布清除')
					keyPersonCountryVal = keyPersonStrCon;
					Map.removeLayer('heatMap')
				} else {
					// alert('重点人员分布不变')
				}
			}else{
				keyPersonCountryVal = "";
				$("#business_list_div_keyPersonCountry").removeClass('business_choose');
				Map.removeLayer('heatMap')
			}
		}
	
	
	

	keyPersonHandleLayerFun = function() {

		var html = "";
		html += '<div class="layer-common" style=" width: 97.75%;;height: 95.3%; position: relative;padding: 14px 0 0 0;">'
		html += '<div class="layer-common-header" style="top: 1px; left: 36px;display: inline-block; padding: 5px 30px; border: 1px solid #0343a3; transform: skew(-20deg); background: #0343a3;color: #fff; font-weight: bold; position: absolute; z-index: 9999;">'
		html += '<div style=" transform: skew(20deg); white-space: nowrap;font-size: 15px;">重点人员管控</div>'
		html += '</div>'
		html += '<div class="layer-show  layer-common-center" style="padding: 10px 10px 5px 10px; width: 100%;height: 100%; overflow: hidden; border: 1px solid #10559a;background: url('
				+ ctxStatic
				+ '/common/index/images/showbg.png);background-size: 100% 100%;">'
		html += '	<iframe  name="mainFrame" src="'
				+ ctx
				+ '/event/ccmEventIncident/findEventMapJump" style="overflow: hidden;" scrolling="yes" frameborder="no" width="980" height="630" allowfullscreen="true" allowtransparency="true"></iframe>'
		html += '</div>'
		html += '</div>'
		keyPersonHandleLayer = layer.open({
			type : 1,
			shade : false,
			title : false, // 不显示标题
			area : [ "1000px", "650px" ],
			move : '.layer-common-header',
			resize : false,
			fixed : false,
			content : html,
			id : 'keyPersonHandleLayer',
			cancel : function() {
				// 关闭事件
				// layer.msg('捕获就是从页面已经存在的元素上，包裹layer的结构', {time: 5000,
				// icon:6});
			}
		});
	}
	keyPersonHandleLayerClose = function() {
		layer.close(keyPersonHandleLayer)
	}
});
