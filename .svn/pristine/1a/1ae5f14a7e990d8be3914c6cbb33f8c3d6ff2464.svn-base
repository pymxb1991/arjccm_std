<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<html>
<head>
	<title>矛盾纠纷统计</title>
	<meta name="decorator" content="default"/>
	<style>
	.common-pading{
	  width:100%;
	  height:200px;
	  padding:5px;
	}
	.echarts{
	  width:100%;
	  height:100%;
	}
	
	.breadcrumb{
		margin-bottom : -58px;
	}
	</style>
	<script type="text/javascript">
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		
	</script>
</head>
<body>
	<div class="context" content="${ctx}"></div>
	<form:form id="searchForm" modelAttribute="ccmEventAmbi" action="${ctx}/event/ccmEventAmbi/map" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input id="areaId"  type="hidden" value="${ccmEventAmbi.area.id}"/>
		<ul class="ul-form">
			<li><input name="beginSendDate" type="hidden"  id="beginSendDate"
				value="<fmt:formatDate value="${ccmEventAmbi.beginSendDate}" pattern="yyyy-MM-dd"/>" />
				<input name="endSendDate" type="hidden"  id="endSendDate"
				value="<fmt:formatDate value="${ccmEventAmbi.endSendDate}" pattern="yyyy-MM-dd"/>" />
			</li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<br>
	    <div class="row-fluid">
		      <div class="span6" ><h4>矛盾化解成功</h4>
		      <div class="common-pading common-pading1" style="height:350px">
					<div id="ccmEventAmbiType" class="echarts" style="height:240px;margin-top: 50px;"></div>
				</div>
		      </div>
		     <div class="span6" ><h4>矛盾纠纷规模</h4>
		     <div class="common-pading common-pading1" style="height:350px">
					<div id="ccmEventAmbiScale" class="echarts" style="height:240px;margin-top: 50px;"></div>
				</div>
		     </div>
		     
	    </div>
	    <br>
	     <div class="row-fluid">
		  <div class="span6" ><h4>矛盾纠纷处理状态</h4>
		      <div class="common-pading common-pading1" style="height:350px">
					<div id="ccmEventAmbiStatus" class="echarts" style="height:240px;margin-top: 50px;"></div>
				</div>
		      </div>
		      <div class="span6" ><h4>矛盾纠纷近六个月上报趋势图</h4>
		         <div class="common-pading common-pading1" style="height:350px">
					<div id="ccmEventAmbiLine" class="echarts"style="height:240px;margin-top: 50px;" ></div>
				 </div>
		      </div>
	    </div>
<script src="${ctxStatic}/jquery/jquery-1.9.1.min.js"></script>
<script src="${ctxStatic}/jquery/jquery.cookie.js" type="text/javascript"></script>
<script src="${ctxStatic}/common/index/Scripts/js/echarts.min.js"></script>
<script>
	var theme=$.cookie('theme');
    if(theme=='gradient'){
	var color = [ '#1F8BFA', '#E84442', '#FAB736', '#2CC189', '#F9A388', '#77E7F1', '#9E56E9', '#FF7453', '#16DDD3', '#FDB733'];
    }
    else{
    	var color = [ '#4573a7', '#89a54e', '#71588f', '#4298af', '#db843d', '#93a9d0', '#d09392', '#b9ce96', '#a99bbc', '#92c3d4', '#ffdf5f','#aa4644'];
    }
	//基础参数
	var windowsHeight, _fontSize = 14,

	_fontSize1 = 26,
	breakData = 8;
	legendTop = '30%',
	radiusData = [90, 65],
	lengthECharts = 30;
	var context = $(".context").attr("content");
	var FontColor="#999",backgroundColor="#fff";
	/* var theme=$.cookie('theme'); */
	if(theme=="black"){
		FontColor="#fff";
		backgroundColor="#0e2a4c";
	}
	
	$(function(){
	    windowsHeight= $(window).width();

	    if (windowsHeight >= 1600) {
	    	
	        _fontSize = 14;
	        legendTop = '15%';
	        radiusData = [90, 65];
	        lengthECharts = 20;
	        _fontSize1 = 26;
	        breakData = 8;
	        legendRight="8%"
	    }else {
	    	
	        _fontSize = 12;
	        legendTop = '15%';
	        radiusData = [60, 45];
	        lengthECharts = 5;
	        _fontSize1 = 12;
	        breakData = 6;
	        legendRight="5%"
	    }
	   
	    var beginSendDate = $("#beginSendDate").val();
	    var endSendDate = $("#endSendDate").val();
	    var areaId = $("#areaId").val();
	    //alert(areaId);
		//化解是否成功统计
		$.getJSON(context + "/event/ccmEventAmbi/findSuccessMap?area.id="+areaId+"&beginSendDate="+beginSendDate+"&endSendDate="+endSendDate, function(
				data) {
			// 接收参数
			$.GetWorkSheets("ccmEventAmbiType", $.ToConvertA(data));
		});
		
		//矛盾纠纷规模统计
		$.getJSON(context + "/event/ccmEventAmbi/findScaleMap?area.id="+areaId+"&beginSendDate="+beginSendDate+"&endSendDate="+endSendDate, function(
				data) {
			// 接收参数
			$.GetWorkSheets("ccmEventAmbiScale", $.ToConvertA(data));
		});
		
		//处理状态统计
		$.getJSON(context + "/event/ccmEventAmbi/findStatusMap?area.id="+areaId+"&beginSendDate="+beginSendDate+"&endSendDate="+endSendDate, function(
				data) {
			// 接收参数
			$.GetWorkSheets("ccmEventAmbiStatus", $.ToConvertA(data));
		});
		
		//总数统计
		$.getJSON(context + "/event/ccmEventAmbi/findLineMap?area.id="+areaId+"&beginSendDate="+beginSendDate+"&endSendDate="+endSendDate, function(
				data) {
			// 接收参数
			$.GetChangeSheets("ccmEventAmbiLine", data);
		});
		
		
		
		
		
	})	
	
	
	
	// 饼图pingJson
	$.ToConvertA = function(object) {
        var ajaxData = new Array();
        for (var one in object) {
            ajaxData.push({
                "name": object[one]["type"],
                "value": Number(object[one]["value"])
            });
        }
        return ajaxData;
    } 
	
	
	//饼图统计情况
	$.GetWorkSheets = function(model,data) {
		
        var nameArr = [],
        DataArr = [];
        
        var option = {
        	backgroundColor: backgroundColor,
            tooltip: {
                trigger: 'item',
                formatter: "{b} :<br/> {c}件 ({d}%)",
		        confine:true
            },
            legend: {
                
                type: 'scroll',
                orient: 'vertical',
                left:'70%',
                top:'middle',
                textStyle: {
                	color: FontColor,
                    fontSize: _fontSize,
                },
                data: data
            }, 
            series: [{
                name: '类型',
                type: 'pie',
                radius : ['50%', '80%'],
                center: ['35%', '50%'],
                color: color,
                label: {
                    normal: {
						formatter: "{b}：{c}",
                        show: true
                    }
                },
                labelLine: {
                    normal: {
                        show: true
                    }
                },
                data: data
            }]
        };
        var Barchart = echarts.init(document.getElementById(model));
        Barchart.setOption(option);

    }
	//柱状图统计情况
	$.GetChangeSheets =function(model,data){
		   var type=[];
		   var value=[];
		   for(var i=0;i<data.length;i++){ 
			   type.push(data[i]['type']);
			   value.push(data[i]['value']);
			}

		 var   option = {
				 backgroundColor: backgroundColor,				 
				   tooltip : {
	        	        trigger: 'item',
	        	        formatter: "{b} :<br/> {c}件",
				        confine:true
	        	    },
	        	     grid: {
		                    left: '3%',
		                    right: '3%',
		                    bottom: '3%',
		                    top: '5%',
		                    containLabel: true
		                },
		                
	   	    yAxis: {
	   	        type: 'value',
		   	     axisLabel : {
					 formatter: '{value}件',
						color : FontColor,
				},
	   	        axisLine: {
	                    lineStyle: {
	                        color: '#808080'
	                    }
	                },
	                splitLine: {
	                    show: false
	                }
	   	    },
	   	    
	   	    xAxis: {
	   	        type: 'category',
	   	        data: type,
	   	 	axisLabel : {
				textStyle : {
					color : FontColor,
				}
			},
	   	        axisLine: {
	                    lineStyle: {
	                        color: '#808080'
	                    }
	                },
	                splitLine: {
	                    show: false
	                }
	   	    },
	   	    series: [{
	   	        data: value,
	   	        type: 'bar',
	   	        barWidth: '40%',
	   	        //配置样式
	   	        itemStyle: {   
	   	            //通常情况下：
	   	            normal:{  
	   	                color: function (params){
	   	                    var colorList = color;
	   	                    return colorList[params.dataIndex];
	   	                }
	   	            },
	   	            //鼠标悬停时：
	   	            emphasis: {
	   	                    shadowBlur: 10,
	   	                    shadowOffsetX: 0,
	   	                    shadowColor: 'rgba(0, 0, 0, 0.5)'
	   	            }
	   	        },
	   	    }]
		   }

		    var Barchart = echarts.init(document.getElementById(model));
	        Barchart.setOption(option);
	   }
	 
		
</script>
</body>

</html>