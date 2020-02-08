var Map, map;
$(document)
		.ready(
				function() {
				
		// 地图默认数据设置
		var defaultPrams = {
			id : 'map',
			centerCoordinate :   centerCoordinate,
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
		var planid = $("#ccmPatrolResultInformation").attr("planid");
		var begindate = $("#ccmPatrolResultInformation").attr("attrbegindate");
		var enddate = $("#ccmPatrolResultInformation").attr("attrenddate");
		console.log('' + ctx + '/patrol/ccmPatrolResult/patrolPlanMap?planId='+planid+'&begindate='+begindate+'&enddate='+enddate)
		$.getJSON('' + ctx + '/patrol/ccmPatrolResult/patrolPlanMap?planId='+planid+'&begindate='+begindate+'&enddate='+enddate,
				function(data) {
			var Data=data.features;
	        var DataLen=Data.length;
	        if(DataLen>0){	
	            var html='';
	            var cont=0;
	        	for(var i=0;i<DataLen;i++){	
	        		
	        		if(Data[i].properties.info.type=="LineString"){
	        			 var FontColr=(Data[i].properties.info.flag)?'black':'#aeaebb';
	 	        	    var name=Data[i].properties.name;
	 	        	    var len=name.length;
	 	        	    if(len>5){
	 	        	    	name=name.substring(0,5)+'...'
	 	        	    }
	 	        		html+='<div class="row-fluid ">';
	 	        		html+='<div class="span9">';
	 	        		html+='<div class="checkbox checkbox-success">';
	 	        		html+='<input type="checkbox" checked value="'+Data[i].id+'"  id="'+Data[i].id+'"  class="type-input" name="popname" colorAttr="'+Data[i].properties.info.color+'"/>';	
	         			html+='<label for="'+Data[i].id+'" style="color:'+FontColr+' " title="'+Data[i].properties.name+'">'+name+'</label';
	 	        		html+='</div>';
	 	        		html+='</div>';
	         			html+='</div>';
	         			html+='<div class="span3">';
	         			html+='<div class="type-line" style="background:'+Data[i].properties.info.color+'"></div>';	
	         			html+='</div>';
	         			html+='</div>';
	         		
	        		}
	        	   
	        	}
               $('#poplist').html(html)
	        }
			
			
			//地图数据			
					Map.addJSON2([ {
						'type' : 'resultCheck',
						'data' : data,
						'isShow' : true
					} ])
					
			
					
				});
		// 全选
		$("#allcheck").click(function(){   
		    if(this.checked){   
		        $("#poplist :checkbox").each(function () { 
		        	var val=$(this).val();
					var color=$(this).attr('colorAttr');
		        	$(this).prop("checked", true); 
		        	Map.patrolResultIsShow(val,color,true)
		        })
		        
		    }else{   
		        $("#poplist :checkbox").each(function () { 
		        	var val=$(this).val();
					var color=$(this).attr('colorAttr');
		        	$(this).prop("checked", false); 
		        	Map.patrolResultIsShow(val,color,false)
		        })
		    }   
		  
		});
		$('#Pop-List').on('change','#poplist :checkbox',function(){
			var chknum = $("#poplist :checkbox").size();// 选项总个数
			var chk = 0;
			$("#poplist :checkbox").each(function () { 
				var val=$(this).val();
				var color=$(this).attr('colorAttr');
		        if($(this).prop("checked")==true){
		        	
					chk++;
					Map.patrolResultIsShow(val,color,true)
				}else{
					Map.patrolResultIsShow(val,color,false)
				}
		    });
			if(chknum==chk){// 全选
				$("#allcheck").prop("checked",true);
			}else{// 不全选
				$("#allcheck").prop("checked",false);
			}
		})
		
});
