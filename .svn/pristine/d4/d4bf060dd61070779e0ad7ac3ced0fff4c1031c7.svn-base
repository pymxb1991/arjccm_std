<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<html>
<head>
	<title>司法专题</title>
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
		<%-- <li class="active"><a href="${ctx}/event/ccmEventAmbi/map">矛盾纠纷统计</a></li> --%>
<%-- 		<li><a href="${ctx}/event/ccmEventAmbi/">矛盾纠纷列表</a></li> --%>
	</ul>
	<sys:message content="${message}"/>
	    <div class="row-fluid height100" style="height: 100%; overflow: hidden>
	    	
		      <div class="span6" ><h4>社区矫正人员统计</h4>
		      <div class="common-pading" style="height:350px">
					<div id="PopNumAllByRectification" class="echarts" style="height:330px;margin-top: 50px;"></div>
				</div>
		      </div>
		 </div>
		 <div class="row-fluid height100" style="height: 100%; overflow: hidden>     
		     <div class="span6" ><h4>矛盾纠纷事件统计</h4>
		     <div class="common-pading" style="height:350px">
					<div id="findAreaMap" class="echarts" style="height:330px;margin-top: 50px;"></div>
				</div>
		     </div>
		     
	    </div>
<script src="${ctxStatic}/jquery/jquery-1.9.1.min.js"></script>
<script src="${ctxStatic}/jquery/jquery.cookie.js" type="text/javascript"></script>
<script src="${ctxStatic}/common/index/Scripts/js/echarts.min.js"></script>
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
    	var color = ['#aa4644', '#4573a7', '#89a54e', '#71588f', '#4298af', '#db843d', '#93a9d0', '#d09392', '#b9ce96', '#a99bbc', '#92c3d4', '#ffdf5f'];
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
	    
	  //社区矫正人员统计
		$.getJSON(context + "/pop/ccmPeople/PopNumAllByRectification", function(
				data) {
			// 接收参数
			$.PopNumAllByRectification("PopNumAllByRectification", data);
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
		
		/* $.getJSON(context + "/event/ccmEventIncident/getItemByPropertyec",
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
}) */

		
		//处理状态统计
		/*$.getJSON(context + "/event/ccmEventAmbi/findStatusMap?area.id="+areaId+"&beginSendDate="+beginSendDate+"&endSendDate="+endSendDate, function(
				data) {
			// 接收参数
			$.GetWorkSheets("ccmEventAmbiStatus", $.ToConvertA(data));
		});
*/
		//总数统计
		$.getJSON(context + "/event/ccmEventAmbi/findAreaMap", function(
				data) {
			// 接收参数
			$.GetChangeSheets("findAreaMap", data);
		}); 
		
		
		
		
		
	})	
//社区矫正人员统计
	$.PopNumAllByRectification=function(model,jsondata){
    	
    	 option = {
    			 /*title : {text: '部门用车次数',
	                     
	    	            x: 'center',
	    	            bottom:10,
	    	            textStyle: {
	    	                color: '#fff',
	    	                fontSize: _fontSizeText
	    	            },
	              },*/
	              color: ['#4E80BC'],
	              tooltip : {
	                  trigger: 'axis',
	                  axisPointer : {            // 坐标轴指示器，坐标轴触发有效
	                      type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
	                  }
	              },
	              grid: {
	            	  top:"10",
	                  left: '3%',
	                  right: '40',
	                  bottom: '40',
	                  containLabel: true
	              },
	              xAxis : [
	                  {
	                      type : 'category',
	                      data : $.ToConvertLegendA(jsondata),
	                      axisLine: {
	                          lineStyle: {
	                        	  color : FontColor
	                          }
	                      },
	                      axisTick: {
	                          alignWithLabel: true
	                      }, 
	                     
	                      axisLabel: { 
	                    	  show: true,
	                    	  interval: 0, //设置为 1，表示『隔一个标签显示一个标签』
	                          rotate:-35,
	                    	  textStyle: {
	                        	  fontSize:_fontSize,
	                        	  color : FontColor
	                          },
                          }  
	                  
	                  }
	              ],
	              yAxis : [
	                  {
	                      type : 'value',
	                      splitLine:{show: false},//去除网格线
	                      axisLine: {
	                          lineStyle: {
	                        	  color : FontColor
	                          }
	                      },
	                      axisLabel: {
	                          show: true,
	                          textStyle: {
	                        	  color : FontColor
	                          }
	                      }
	                  }
	              ],
	              series : [
	                  {
	                      name:'学校数量',
	                      type:'bar',
	                      barWidth: '60%',
	                      data:jsondata,
	                      itemStyle: {
	                          normal: {
	                              lineStyle: {
	                                  color: '#eb9041'
	                              }
	                          }
	                      },
	                  }
	              ]
	          };
    	
    	
    	// 初始化参数
        var myChart = echarts.init(document.getElementById(model));  
        myChart.setOption(option, true); 
    }
	
	//柱状图：矛盾纠纷事件统计
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