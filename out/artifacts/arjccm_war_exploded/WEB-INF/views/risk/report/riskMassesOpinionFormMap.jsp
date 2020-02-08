<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<html>
<head>
<title>社情民意分析管理</title>
<meta name="decorator" content="default" />
<style>
.common-pading {
	width: 100%;
	height: 200px;
	padding: 5px;
}

.echarts {
	width: 100%;
	height: 100%;
}

.progressbarDiv {
	background: #E8E8E8;
	overflow: hidden;
	width: 180px;
	height: 16px;
	float: right;
}

.suQuTable-tbody tr {
	border-top: 1px dashed #c5b6b6;
}

.suQuTable-tbody tr:FIRST-CHILD {
	border-top: none;
}

.suQuTable {
	width: 100%
}

.ui-progressbar .ui-progressbar-value {
	margin: -1px;
	height: 100%;
}

.progressbarDiv .ui-widget-header {
	background: #65CBE9 ! important;
	border-bottom: 1px solid #65CBE9;
}
</style>
<script src="${ctxStatic}/jquery/jquery-1.9.1.min.js"></script>
<script src="${ctxStatic}/common/index/Scripts/js/echarts.min.js"></script>
<script type="text/javascript">
		$(document).ready(function() {
			
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
		<li><a href="${ctx}/report/riskMassesOpinion/listMap">社情民意分析列表</a></li>
		<li class="active"><a
			href="${ctx}/report/riskMassesOpinion/formMap?id=${riskMassesOpinion.id}">社情民意分析统计图</a></li>
	</ul>
	<br />
	<sys:message content="${message}" />
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<td>问卷名称：${riskMassesOpinion.fileName}</td>
				<td>所属重大事项：${riskMassesOpinion.riskEventGreat.name}</td>
				<td>类型：${fns:getDictLabel(riskMassesOpinion.type, 'risk_masses_opinion_type', '')}</td>
				<td width="50%">参与人数：${popNum}人</td>
			</tr>
		</thead>

		<c:forEach items="${importExeclMapList}" var="importExeclMap">
			<tbody>
				<tr>
					<td colspan="4">${importExeclMap.title}
						[${importExeclMap.type}]</td>
				</tr>
				<tr>
					<td colspan="4">
						<!-- <div id="importExeclMapData" class="echarts" ></div> -->
						<table class="suQuTable">
							<tbody class="suQuTable-tbody" id="${importExeclMap.id}">
							</tbody>
							<thead>
								<script type="text/javascript">
							var data=${importExeclMap.listJson};
							var len=data.length;
							if(len>0){
								var anCount=0;
								var bfbFloat=0;
								var bfbVal = 0;
								var html="";
								for(var i=0;i<len;i++){
									
									anCount+=parseInt(data[i].value);
								}
								for(var i=0;i<len;i++){
									var count=parseInt(data[i].value);
									if(anCount==0){
										bfbVal=0;
									}else{
										bfbFloat=count/anCount*100;
										bfbVal = bfbFloat.toFixed(2);
									}
									
									html+='<tr>'; 
									html+='<td  style="width:40%;">'+data[i].type+'</td>';
									html+='<td style="width:200px;">';
									html+='<div class="progressbarDiv  ui-progressbar">';	
									html+='<div class="ui-progressbar-value ui-widget-header" style="width: '+bfbVal+'%;"></div>';		
									html+='</div>';
									html+='</td>';
									html+='<td  align="right"  style="width:100px;">'+bfbVal+'%</td>';	
									html+='<td align="left" >'+count+'次</td>';	
									html+='</tr>';
	
								}
								var id="${importExeclMap.id}";
								$('#'+id).html(html);
								
							}
							
							
						</script>
							</thead>
						</table>


					</td>
				</tr>

				<tr>
					<td colspan="4">
						<div class="row-fluid">
							<div class="span11">
								<div class="common-pading">
									<div id="${importExeclMap.id}echarts" class="echarts"></div>
								</div>
							</div>
						</div> 
						
					<script>
					
					//折现图统计情况
						var color = ['#4573a7', '#89a54e', '#71588f', '#4298af', '#db843d', '#93a9d0', '#d09392', '#b9ce96', '#a99bbc', '#92c3d4', '#ffdf5f','#aa4644'];

					$.GetChangeSheets =function(model,data){
						var type=[];
						var value=[];
						for(var i=0;i<data.length;i++){ 
							type.push(data[i]['type']);
							value.push(data[i]['value']);
						}

						var  option = {
								tooltip: {
									trigger: 'axis',
								},
								color:color,
								toolbox: {
									show : true,
									feature : {
										dataView : {show: true, readOnly: false},
										magicType : {show: true, type: ['line', 'bar']},
										restore : {show: true},
										saveAsImage : {show: true}
									}
								},
								 /*    grid: {
								        left: '3%',
								        right: '4%',
								        bottom: '3%',
								        containLabel: true
								    }, */
								xAxis: [{
									axisLine: {
										lineStyle: {
											color: '#ccc'
										}
									},
									axisLabel: {
										textStyle: {
											fontSize: 14,
											color: '#999'
										}
									},
									data: type
								}],
								yAxis: [{
									type: 'value',
									name: '',
									axisTick: {
										show: false
									},
									axisLabel: {
										margin: 10,
										textStyle: {
											fontSize: 14,
											color: '#999'
										}
									},
									splitLine: {
										show: false
									}
								}],
								series: [{
									
									type: 'bar',
									data: value
								},]
							}

						    var Barchart = echarts.init(document.getElementById(model));
					        Barchart.setOption(option);
					   }
					
						var data=${importExeclMap.listJson};
						$.GetChangeSheets("${importExeclMap.id}echarts", data);
				
			
				
					</script>
					</td>
				</tr>
			</tbody>
		</c:forEach>

	</table>




	<script>
	var color = ['#4573a7', '#89a54e', '#71588f', '#4298af', '#db843d', '#93a9d0', '#d09392', '#b9ce96', '#a99bbc', '#92c3d4', '#ffdf5f','#aa4644'];
	//基础参数
	var windowsHeight, _fontSize = 14,

	_fontSize1 = 26,
	breakData = 8;
	legendTop = '30%',
	radiusData = [90, 65],
	lengthECharts = 30;
	var context = $(".context").attr("content");
	
	
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
	   
		
		

		
		
	})	
	
	
	
 	// 饼图pingJson况
/*	$.ToConvertA = function(object) {
        var ajaxData = new Array();
        for (var one in object) {
            ajaxData.push({
                "name": object[one]["type"],
                "value": Number(object[one]["value"])
            });
        }
        return ajaxData;
    } 
	
	
	
	//折现图统计情况
	$.GetChangeSheets =function(model,data){
		   var type=[];
		   var value=[];
		   for(var i=0;i<data.length;i++){ 
			   type.push(data[i]['type']);
			   value.push(data[i]['value']);
			}

		 var  option = {
				    backgroundColor: '#FAFAFA',
				    title: {
				        text: '近6个月平均拜访次数',
				        show: false,
				        textStyle: {
				            fontWeight: 'normal',
				            fontSize: 16,
				            color: '#333'
				        },
				        left: '6%'
				    },
				    tooltip: {
				        trigger: 'axis',
				        axisPointer: {
				            lineStyle: {
				                color: '#333'
				            }
				        }
				    },
				    legend: {
				        icon: 'rect',
				        itemWidth: 14,
				        itemHeight: 5,
				        itemGap: 13,
				        data: ['近6个月事项报备次数'],
				        right: 'center',
				        textStyle: {
				            fontSize: 12,
				            color: '#333'
				        }
				    },
				    grid: {
				        left: '3%',
				        right: '4%',
				        bottom: '3%',
				        containLabel: true
				    },
				    xAxis: [{
				        type: 'category',
				        boundaryGap: false,
				        axisLine: {
				            lineStyle: {
				                color: '#ccc'
				            }
				        },
				        axisLabel: {
				            margin: 10,
				            textStyle: {
				                fontSize: 14,
				                color: '#999'
				            }
				        },
				        data: type
				    }],
				    yAxis: [{
				        type: 'value',
				        name: '',
				        axisTick: {
				            show: false
				        },
				        axisLine: {
				            lineStyle: {
				                color: '#fff'
				            }
				        },
				        axisLabel: {
				            margin: 10,
				            textStyle: {
				                fontSize: 14,
				                color: '#999'
				            }
				        },
				        splitLine: {
				            lineStyle: {
				                type: 'solid',
				                color: '#ccc'
				            }
				        }
				    }],
				    series: [{
				        name: '近6个月事项报备次数',
				        type: 'line',
				        smooth: true,
				        symbol: 'circle',
				        symbolSize: 5,
				        showSymbol: false,
				        lineStyle: {
				            normal: {
				                width: 1
				            }
				        },
				        areaStyle: {
				            normal: {
				                color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
				                    offset: 0,
				                    color: 'rgba(0, 136, 212, 0.2)'
				                }, {
				                    offset: 1,
				                    color: 'rgba(0, 136, 212, 0)'
				                }], false),
				                shadowColor: 'rgba(0, 0, 0, 0.1)',
				                shadowBlur: 10
				            }
				        },
				        itemStyle: {
				            normal: {
				                color: 'rgb(0,136,212)',
				                borderColor: 'rgba(0,136,212,0.2)',
				                borderWidth: 12

				            }
				        },
				        data: value
				    }, ]
				}

		    var Barchart = echarts.init(document.getElementById(model));
	        Barchart.setOption(option);
	   } */
	 
		
</script>
</body>
</html>