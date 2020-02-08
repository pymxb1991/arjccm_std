<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<html>
<head>
	<title>校园及周边安全</title>
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
	
	
	</style>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnSubmit").on("click" ,function(){
				$("#searchForm").submit();
			})
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/event/ccmEventAmbi/map">矛盾纠纷统计</a></li>
<%-- 		<li><a href="${ctx}/event/ccmEventAmbi/">矛盾纠纷列表</a></li> --%>
	</ul>
	<sys:message content="${message}"/>
	<br>
	    <div class="row-fluid">
	    	  <div class="span6" ><h4>周边人员政治面貌统计</h4>
		      <div class="common-pading common-pading1" style="height:350px">
					<div id="popNumAllByPolitics" class="echarts" style="height:330px;margin-top: 50px;"></div>
				</div>
		      </div>
		      
		      <div class="span6" ><h4>师生安全案件性质统计</h4>
		      <div class="common-pading common-pading1" style="height:350px">
					<div id="getItemByPropertyec" class="echarts" style="height:330px;margin-top: 50px;"></div>
				</div>
		      </div>
		     
	    </div>
	    
	    <br>
	     <div class="row-fluid height100" style="height: 100%; overflow: hidden>
		      <div class="span6" ><h4>校园区域统计</h4>
		      <div class="common-pading common-pading1" style="height:350px">
					<div id="schoolNumAllByOfficeAjax" class="echarts" style="height:240px;margin-top: 50px;"></div>
				</div>
		      </div>
		 <!--      <div class="span6" ><h4>矛盾纠纷近六个月上报趋势图</h4>
		         <div class="common-pading common-pading1" style="height:350px">
					<div id="ccmEventAmbiLine" class="echarts"style="height:240px;margin-top: 50px;" ></div>
				 </div>
		      </div> -->
	    </div>
<script src="${ctxStatic}/jquery/jquery-1.9.1.min.js"></script>
<script src="${ctxStatic}/jquery/jquery.cookie.js" type="text/javascript"></script>
<script src="${ctxStatic}/common/index/Scripts/js/echarts.min.js"></script>
<script src="${ctxStatic}/echarts/theme/${not empty cookie.theme.value ? cookie.theme.value : 'black'}.js"></script>
<script>
	
$(function() {
	//基础参数
	var color1;
    if(getCookie("theme")=="black"||!getCookie("theme")){
	$('.container-fluid').css("background-color",getCookie("theme"));
	color1="#fff"
    }else if(getCookie("theme")=="gradient"){
    	    	$('.container-fluid').css("background-color",getCookie("theme"));
    	    	color1="#000"}
    
    
/*    if(getCookie("theme")=="cerulean" || !getCookie("theme")){
    	$('.container-fluid').css("background-color","white");
    }else{
    	$('.container-fluid').css("background-color",getCookie("theme"));
    }*/
    function getCookie(cname)
    {
      var name = cname + "=";
      var ca = document.cookie.split(';');
      for(var i=0; i<ca.length; i++) 
      {
        var c = ca[i].trim();
        if (c.indexOf(name)==0) return c.substring(name.length,c.length);
      }
      return "";
    }
	var windowsHeight, _fontSize = 14,

	_fontSize1 = 26,
	breakData = 8;
	legendTop = '30%',
	radiusData = [90, 65],
	lengthECharts = 30;
    var context = "/arjccm/a"
	/* var	context = $(".context").attr("content"); */
	var FontColor="#999",backgroundColor="#fff";
	var theme=$.cookie('theme'); 
	if(theme=="black"){
		FontColor="#fff";
		backgroundColor="#0e2a4c";
	}
    if(theme=='gradient'){
	var color = [ '#1F8BFA', '#E84442', '#FAB736', '#2CC189', '#F9A388', '#77E7F1', '#9E56E9', '#FF7453', '#16DDD3', '#FDB733'];
    }
    else{
    	var color = ['#4573a7', '#aa4644', '#89a54e', '#db843d','#4298af', '#93a9d0', '#b9ce96', '#d09392','#a99bbc', '#92c3d4', '#ffdf5f', '#71588f'];
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
	    
	  //校园区域统计
		$.getJSON(context + "/house/ccmHouseSchoolrim/schoolNumAllByOfficeAjax", function(
				data) {
			// 接收参数
			$.SchoolNumAllByOfficeAjax("schoolNumAllByOfficeAjax", data);
		});
	    //alert(areaId);
		//化解是否成功统计
	 	//$.getJSON(context + "/event/ccmEventAmbi/findSuccessMap?area.id="+areaId+"&beginSendDate="+beginSendDate+"&endSendDate="+endSendDate, function(
			//	data) {
			// 接收参数
			//$.GetWorkSheets("ccmEventAmbiType", $.ToConvertA(data));
		//});
		
		//矛盾纠纷规模统计
/* 		$.getJSON(context + "/house/ccmHouseSchoolrim/schoolEventAmbiScale", function(
				data) {
			// 接收参数
			$.GetWorkSheets("schoolEventAmbiScale", $.ToConvertA(data));
		}); */
		
		$.getJSON(context + "/event/ccmEventIncident/getItemByPropertyec",
		        function(data) {
		            // 填充数据
		            $.getItemByPropertyec("getItemByPropertyec", $.ToConvertA(data));
		        });
		    $(window).resize(function(){
		        window.location.reload()
		    })
		    
		$.getJSON(context + "/pop/ccmPeople/popNumAllByPolitics", function (
    data) {
    // 接收参数
    $.PopNumAllByPolitics("popNumAllByPolitics", $.ToConvertA(data));
})

		
		//处理状态统计
		/*$.getJSON(context + "/event/ccmEventAmbi/findStatusMap?area.id="+areaId+"&beginSendDate="+beginSendDate+"&endSendDate="+endSendDate, function(
				data) {
			// 接收参数
			$.GetWorkSheets("ccmEventAmbiStatus", $.ToConvertA(data));
		});
		
		//总数统计
		$.getJSON(context + "/event/ccmEventAmbi/findLineMap?area.id="+areaId+"&beginSendDate="+beginSendDate+"&endSendDate="+endSendDate, function(
				data) {
			// 接收参数
			$.GetChangeSheets("ccmEventAmbiLine", data);
		}); */
		
		
		
		
		
	})	
//校园区域统计
	$.SchoolNumAllByOfficeAjax=function(model,data){
    	
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
	        	        formatter: "{b} :<br/> {c}",
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
					 formatter: '{value}',
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
	
	//饼状图：师生安全事件性质统计
$.getItemByPropertyec = function(model,data) {
        var nameArr = [],
            DataArr = [];

        var option = {
            tooltip: {
                trigger: 'item',
                formatter: "{a} <br/>{b} : {c} ({d}%)",
                confine:true
            },
            legend: {
                type: 'scroll',
                orient: 'vertical',
                top: legendTop,
                right: legendRight,
                textStyle: {
                    color: FontColor,
                    fontSize: _fontSize,
                },
                data: data
                // data: nameArr
            },
            series: [{
                name: '案事件类型',
                type: 'pie',
                radius: '80%',
                center: ['50%', '50%'],
                color: color,
                label: {
                    normal: {
                        show: false
                    }
                },
                labelLine: {
                    normal: {
                        show: false
                    }
                },
                data: data 
            }]
        };
        var Barchart = echarts.init(document.getElementById(model));
        Barchart.setOption(option);
        Barchart.on('click',
            function(params) {
        });
    }
	
//根据学校周边重点人员政治面貌统计情况

$.PopNumAllByPolitics = function (model, data) {
    var nameArr = [],
    DataArr = [];

var option = {
    tooltip: {
        trigger: 'item',
        formatter: "{a} <br/>{b} : {c} ({d}%)",
        confine:true
    },
    legend: {
        type: 'scroll',
        orient: 'vertical',
        top: legendTop,
        right: legendRight,
        textStyle: {
            color: FontColor,
            fontSize: _fontSize,
        },
        data: data
        // data: nameArr
    },
    series: [{
        name: '案事件类型',
        type: 'pie',
        radius: '80%',
        center: ['50%', '50%'],
        color: color,
        label: {
            normal: {
                show: false
            }
        },
        labelLine: {
            normal: {
                show: false
            }
        },
        data: data 
    }]
};
var Barchart = echarts.init(document.getElementById(model));
Barchart.setOption(option);
Barchart.on('click',
    function(params) {
});
}
	
	function getCookie(name)
	{
	var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
	if(arr=document.cookie.match(reg))
	return unescape(arr[2]);
	else
	return null;
	}
    // EchartType 转换成 Echart所需要的类型
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
    
 // EchartType 转换成 Echart所需要的类型
    $.ToConvertB = function(object) {
        var ajaxData = new Array();
        for (var one in object) {
            ajaxData.push({
                "name": object[one]["typeO"],
                "value": Number(object[one]["value"])
            });
        }
        return ajaxData;
    }
 // EchartType 转换成 Echart legend  所需要的类型
    $.ToConvertLegendA = function(object) {
        var ajaxData = new Array();
        for (var one in object) {
            ajaxData.push(
               object[one]["type"]
            );
        }
        return ajaxData;
    }
    // EchartType 转换成 Echart Series  所需要的类型
    $.ToConvertSeriesA = function(object) {
        var ajaxData = new Array();
        for (var one in object) {
            ajaxData.push(
               object[one]["value"]
            );
        }
       
        return ajaxData;
    }   
/*     $.ToConvertA = function(object) {
        var ajaxData = new Array();
        for (var one in object) {
            ajaxData.push({
                "name": object[one]["type"],
                "value": Number(object[one]["value"])
            });
        }
        return ajaxData;
    }  */
    // EchartType 转换成 Echart Series  所需要的类型
    $.ToConvertSeriesB = function(object) {
        var ajaxData = new Array();
        for (var one in object) {
            ajaxData.push(
               object[one]["typeO"]
            );
        }
        return ajaxData;
    }
    // EchartType 转换成 Echart Series  所需要的类型
    $.ToConvertSeriesC = function(object) {
        var ajaxData = new Array();
        for (var one in object) {
            ajaxData.push(
               object[one]["value2"]
            );
        }
        return ajaxData;
    }
   
    
    $.updateSearchTable = function(model, originalData) {
        // echartsAlarm-value
        // 拼接 table内容
        var tableContent = "";
        // 获取 按月类型值
        for (var i in originalData) {
            // 获取每一个值
            tableContent += "<tr><td>"+i+"</td><td>"+originalData[i]["value1"]+"</td><td>"+originalData[i]["value2"]+"</td> <td>"+originalData[i]["value3"]+"</td> <td>"+originalData[i]["value4"]+"</td></tr>"
        }
        // 添加内容 到页面
        $ (model).html(tableContent);
    }
/* 	// 饼图pingJson
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
	   } */
	 
});
</script>
</body>

</html>