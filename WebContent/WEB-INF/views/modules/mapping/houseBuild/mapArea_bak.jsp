<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<script src="${ctxStatic}/jquery/jquery-1.9.1.min.js"></script>
<script src="${ctxStatic}/common/index/Scripts/js/echarts.min.js"></script>
<style type="text/css">
	td{
	 padding: 2px;
	 padding-left: 20px;
	 white-space: nowrap;
	 font-size:14px;
	}
	.color{color:white; text-decoration:none;font-weight:bold;}/*链接设置*/
	.color:hover{color:#555555; text-decoration:underline;font-weight:bold;}/*鼠标放上的链接设置*/

.td-border{
   border-right:2px solid grey
}
@media screen and (min-width: 1681px){ 
 td{
   font-size:14px;
  }
  .title-fontsize td {
  font-size:20px;
}
.color{
font-size:26px;
}
  .fontsize-common{
      font-size:30px;
      color: #eea807;
   
  }
  #detailsDialog {
    height: 38%;
 }
 #detailsDialog  .nav {
    margin-bottom: 20px;
}
} 
@media screen and (min-width: 1601px) and (max-width: 1680px){ 
 td{
   font-size:14px;
  }
  .title-fontsize td {
  font-size:20px;
}
.color{
font-size:24px;
}
  .fontsize-common{
      font-size:28px;
      color: #eea807;
   
  }
  #detailsDialog {
    height: 38%;
 }
 #detailsDialog  .nav {
    margin-bottom: 20px;
}
} 

@media screen and (min-width: 1441px) and (max-width: 1600px){ 
 td{
   font-size:13px;
  }
  .title-fontsize td {
  font-size:18px;
}
.color{
font-size:20px;
}
  .fontsize-common{
      font-size:20px;
      color: #eea807;
   
  }
 
} 

@media screen and (min-width: 1367px) and (max-width: 1440px){ 
  td{
   font-size:12px;
  }
  .title-fontsize td{
  font-size:16px;
}
.color{
  font-size:18px;
}
  .fontsize-common{
      font-size:18px;
      color: #eea807;
  }
 
} 
@media screen and (max-width: 1366px){ 
  td{
   font-size:12px;
  }
  .title-fontsize td{
  font-size:14px;
}
.color{
  font-size:14px;
}
  .fontsize-common{
      font-size:16px;
      color: #eea807;
  }
 
} 
/*

取消下划线只要把text-decoration:underline修改成text-decoration:none
文字加粗font-weight:bold 如不需要加粗显示，那么删除font-weight:bold;就可以了
.color:visited{color:#3399CC; text-decoration:none;font-weight:bold;}访问过的链接设置
其它更多的参数设置参考：css2.0手册 其中的"伪类"说明
*/
</style>
<div id="myTabContent" class="tab-content">
<div class="context" content="${ctx}"></div>
	<ul class="nav nav-tabs" class="title-fontsize">
		<li id="clickHomePop"><a class="color" onclick="showHomePop()">&nbsp;&nbsp;&nbsp;${area.name}概况&nbsp;&nbsp;&nbsp;</a></li>
		<li id="clickStructurePop"><a class="color" onclick="showStructurePop()">&nbsp;&nbsp;&nbsp;人口结构&nbsp;&nbsp;&nbsp;</a></li>
		<li id="clickEmphasisPop"><a class="color" onclick="showEmphasisPop()">&nbsp;&nbsp;&nbsp;特殊人群&nbsp;&nbsp;&nbsp;</a></li>
		<li id="clickFloatingPop"><a class="color" onclick="showFloatingPop()">&nbsp;&nbsp;&nbsp;流动分析&nbsp;&nbsp;&nbsp;</a></li>
		
	</ul>
	
	<!-- 概况homePop -->
	<div class="tab-pane fade in active" id="homePop" align="center">
		<table class="" style="color: white; width: 98%;height:70%; font-size:15px;">
			<tr class="title-fontsize">
				<!-- 
				<td align="center"  colspan="2" width="25%">总人数：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="font-size: 35px">${ccmOrgArea.mannum}&nbsp;</span>人</td>
			     -->
				<td align="center"  colspan="3" width="25%">总人数：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="fontsize-common">${popAll}&nbsp;</span>人</td>
			    <td align="center" width="25%" colspan="2">概况</td>
				<td align="center" width="50%">总人口变化趋势</td>
			</tr>
			<tr>
				<td width="5%"  align="right">户籍人口：</td>
				<td width="4%"><span   class="fontsize-common">${not empty ccmPeopleAmount.personAmount?ccmPeopleAmount.personAmount:'0'}&nbsp;</span>人</td>
				
				<td rowspan="4" align="center" class="td-border">
					<div class="common-pading">
						<div id="popTypeArea" class="echarts" ></div>
					</div>
				</td>
				<td width="10%"  align="right">区域面积：</td>
				<td width="15%" class="td-border"><span  class="fontsize-common">${areaNew}&nbsp;</span>km<sup>2</sup></td>
				
				<td rowspan="3" align="center">
					<div class="common-pading">
						<div id="popChangeArea" class="echarts" ></div>
					</div>
				</td>
			</tr>
			<tr>
				<td width="5%"  align="right">流动人口：</td>
				<td width="4%"><span   class="fontsize-common">${not empty ccmPeopleAmount.floatAmount?ccmPeopleAmount.floatAmount:'0'}&nbsp;</span>人</td>

				<td  align="right">人口密度：</td>
				<td  class="td-border"><span  class="fontsize-common">${popArea}&nbsp;</span>人/km<sup>2</sup></td>
				
			</tr>
			<tr>
				<td width="5%"  align="right">外籍人口：</td>
				<td width="4%"><span   class="fontsize-common">${not empty ccmPeopleAmount.overseaAmount?ccmPeopleAmount.overseaAmount:'0'}&nbsp;</span>人</td>
				
				<td  align="right">楼栋总面积：</td>
				<td  class="td-border"><span  class="fontsize-common">${BuildmanageArea}&nbsp;</span>m<sup>2</sup></td>
				<td></td>
			</tr>
			<tr>
				<td width="5%"  align="right">未落户人口：</td>
				<td width="4%"><span   class="fontsize-common">${not empty ccmPeopleAmount.unsettleAmount?ccmPeopleAmount.unsettleAmount:'0'}&nbsp;</span>人</td>
				
				<td  align="right">人均住房面积：</td>
				<td  class="td-border"><span  class="fontsize-common">${BuildmanageAreaPop}&nbsp;</span>m<sup>2</sup>/人</td>
								<td><span   class="fontsize-common" style="color:#fff">预测人口：&nbsp;</span>下个月新增人口<span  class="fontsize-common">&nbsp;${subForecast}&nbsp;</span>人，总数达<span  class="fontsize-common">&nbsp;${forecast}&nbsp;</span>人</td>

			</tr>
		</table>
	</div>
	
	<!-- 人口结构structurePop -->
	<div class="tab-pane fade in active" id="structurePop" align="center">
		<table class="" style="color: white; width: 98%;height:70%; font-size:15px;">
			<tr  class="title-fontsize">
				<td align="center" width="50%" colspan="3">年龄结构分析</td>
				<td align="center" width="25%" colspan="3">性别比例分析</td>
				<td align="center" width="25%" colspan="3">教育程度对比</td>
			
			</tr>
			<tr>
				<td width="5%" align="right">0-15岁：</td>
				<td width="4%"><span   class="fontsize-common">${not empty ccmPeopleAmount.ageChild?ccmPeopleAmount.ageChild:'0'}&nbsp;</span>人</td>
				<td align="center" width="38%" rowspan="5" class="td-border">
					<div class="common-pading">
						<div id="popAgeArea" class="echarts"  ></div>
					</div>
				</td>
				<td width="5%"  align="right">男性：</td>
				<td width="4%"><span   class="fontsize-common">${not empty ccmPeopleAmount.sexMale?ccmPeopleAmount.sexMale:'0'}&nbsp;</span>人</td>
				
				<td align="center" width="12%" rowspan="5" class="td-border">
					<div class="common-pading">
						<div id="popSexArea" class="echarts" ></div>
					</div>
				</td>
				<td width="5%"  align="right">初中及以下：</td>
				<td width="4%"><span   class="fontsize-common">${not empty ccmPeopleAmount.eduJunior?ccmPeopleAmount.eduJunior:'0'}&nbsp;</span>人</td>
			
				<td align="center" width="15%" rowspan="5">
					<div class="common-pading">
						<div id="popEduArea" class="echarts" ></div>
					</div>
				</td>
				
				<!-- <td align="center" width="15%" rowspan="5">
					<div class="common-pading">
						<div id="popTypeArea" class="echarts" ></div>
					</div>
				</td> -->
			</tr>
			<tr>
				<td width="5%"  align="right">16-40岁：</td>
				<td width="4%"><span   class="fontsize-common">${not empty ccmPeopleAmount.ageAdult?ccmPeopleAmount.ageAdult:'0'}&nbsp;</span>人</td>
				
				<td width="5%"  align="right">女性：</td>
				<td width="4%"><span  class="fontsize-common">${not empty ccmPeopleAmount.sexFemale?ccmPeopleAmount.sexFemale:'0'}&nbsp;</span>人</td>
				
				<td width="5%"  align="right">高中：</td>
				<td width="4%"><span   class="fontsize-common">${not empty ccmPeopleAmount.eduSenior?ccmPeopleAmount.eduSenior:'0'}&nbsp;</span>人</td>
			
			</tr>
			<tr>
				<td width="5%"   align="right">41-64岁：</td>
				<td width="4%"><span   class="fontsize-common">${not empty ccmPeopleAmount.ageMiddle?ccmPeopleAmount.ageMiddle:'0'}&nbsp;</span>人</td>
				
				<td width="5%"   align="right">男女比例：</td>
				<td width="4%"><span  class="fontsize-common">${sexScale}&nbsp;</span>/1</td>
				
				<td width="5%"   align="right">大学：</td>
				<td width="4%"><span   class="fontsize-common">${not empty ccmPeopleAmount.eduCollege?ccmPeopleAmount.eduCollege:'0'}&nbsp;</span>人</td>
			
			</tr>
			<tr>
				<td width="5%"   align="right">65岁及以上：</td>
				<td width="4%"><span   class="fontsize-common">${not empty ccmPeopleAmount.ageOld?ccmPeopleAmount.ageOld:'0'}&nbsp;</span>人</td>
				
				<td width="5%"   align="right">未婚男性：</td>
				<td width="4%"><span  class="fontsize-common">${not empty v?ccmPeopleAmount.sexMaleSingle:'0'}&nbsp;</span>人</td>
				
				<td width="5%"   align="right">研究生：</td>
				<td width="4%"><span   class="fontsize-common">${not empty ccmPeopleAmount.eduMaster?ccmPeopleAmount.eduMaster:'0'}&nbsp;</span>人</td>
				
				<!-- <td width="10%"></td> -->
			</tr>
			<tr>
				<td width="5%"  align="right">新生儿：</td>
				<td width="4%"><span   class="fontsize-common">${not empty ccmPeopleAmount.ageNewborn?ccmPeopleAmount.ageNewborn:'0'}&nbsp;</span>人</td>
				
				<td width="5%" align="right">未婚女性：</td>
				<td width="4%"><span class="fontsize-common" >${not empty ccmPeopleAmount.sexFemaleSingle?ccmPeopleAmount.sexFemaleSingle:'0'}&nbsp;</span>人</td>
				
				<td width="5%" align="right">博士及以上：</td>
				<td width="4%"><span   class="fontsize-common">${not empty ccmPeopleAmount.eduDoctor?ccmPeopleAmount.eduDoctor:'0'}&nbsp;</span>人</td>
				
				<!-- <td width="10%"></td> -->
			</tr>
		</table>	
	</div>
	
	<!-- 重点人群emphasisPop -->
	<div class="tab-pane fade in active" id="emphasisPop" align="center">
		<table class="" style="color: white; width: 98%;height:70%; font-size:15px;">
			<tr  class="title-fontsize">
				<td align="center" width="20%"  colspan="2">数据一览</td>
				<td align="center" width="20%">关注程度</td>
				<td  width="60%" colspan="2" style="padding-left:17%">特殊人群类型及变化趋势</td>
			<!-- 	<td width="45%"></td> -->
			</tr>
			<tr>
				<td  width="9%" align="right">特殊人群总数：</td>
				<td  class="td-border"><span class="fontsize-common">${emphasisPopAll}&nbsp;</span>人</td>
				
				<td rowspan="3" class="td-border">
					<div class="common-pading">
						<div id="popAtteArea" class="echarts "></div>
					</div>
				</td>
				<td rowspan="3" style="width:20%">
					<div class="common-pading">
						<div id="popArea" class="echarts" ></div>
					</div>
				</td>
				<td rowspan="3"  style="width:40%">
					<div class="common-pading">
						<div id="popEcharts" class="echarts"></div>
					</div>
				</td>
			</tr>
			<tr>
				<td  width="5%" align="right">工作人员数：</td>
				<td  class="td-border"><span  class="fontsize-common">${not empty ccmOrgArea.netPeoNum?ccmOrgArea.netPeoNum:'0'}&nbsp;</span>人</td>
		
			</tr>
			<tr>
				<td  width="5%" align="right">工作负荷指数：</td>
				<td  class="td-border"><span  class="fontsize-common">${popPop}&nbsp;</span>/人</td>
			
			</tr>
		</table>
	</div>
	
	<!-- 流动分析floatingPop -->
	<div class="tab-pane fade in active" id="floatingPop" align="center">
		<table class="" style="color: white; width: 98%;height:70%; font-size:15px;" border="0px">
			<tr  class="title-fontsize">
				<td align="center" width="20%" colspan="2">数据一览</td>
				<td align="center" width="35%" colspan="2">流动人员来源地分析top5</td>
				<td align="center" width="45%">流动人员变化趋势（总数）</td>
			</tr>
			<tr>
				<td align="right"  width="9%">出租屋总数：</td>
				<td class="td-border"><span  class="fontsize-common">${listCcmPopTenantNum}&nbsp;</span>个</td>
				<td rowspan="6"  style="width:17%">
					<div class="common-pading">
						<div id="popFloatingArea" class="echarts" ></div>
					</div>
				</td>
				<td class="td-border"></td>
				<td rowspan="6" >
					<div class="common-pading">
						<div id="popFlowArea" class="echarts" ></div>
					</div>
				</td>
			</tr>
			<tr>
				<td></td>
				<td class="td-border" ></td>
				<td rowspan="5" class="td-border" style="padding-right: 3%;">
				分析说明：<br>
				<p style="border:1px solid grey">
				大量案事件汇总分析显示，某<br/>些犯罪事件呈现犯罪嫌疑人来<br/>源同一地方，所以需要排查和<br/>警惕同一来源地的大量流动人员
				</p>
					
				</td>
			</tr>
			<tr>
				<td  align="right"  width="5%">流动人员数：</td>
				<td  class="td-border"><span  class="fontsize-common">${not empty ccmPeopleAmount.floatAmount?ccmPeopleAmount.floatAmount:'0'}&nbsp;</span>人</td>
			</tr>
			<tr>
				<td  align="right"></td>
				<td  class="td-border"></td>
			</tr>
			<tr>
				<td align="right"  width="5%"> 本月新增：</td>
				<td class="td-border"> <span class="fontsize-common">${ccmPeopleStatAll}&nbsp;</span>人</td>
			</tr>
			<tr>
				<td  align="right"></td>
				<td  class="td-border"></td>
			</tr>
		</table>
	</div>



</div>
<script  type="text/javascript">
	var color = ['#aa4644', '#4573a7', '#89a54e', '#71588f', '#4298af', '#db843d', '#93a9d0', '#d09392', '#b9ce96', '#a99bbc', '#92c3d4', '#ffdf5f'];
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
	    
	    
	    
	    
	    $("#clickHomePop").addClass("active"); 
		$("#clickStructurePop").removeClass("active"); 
		$("#clickEmphasisPop").removeClass("active"); 
		$("#clickFloatingPop").removeClass("active"); 
		$("#homePop").css("display","block");
		$("#structurePop").css("display","none");
		$("#emphasisPop").css("display","none");
		$("#floatingPop").css("display","none"); 
	    
		
		//人口类型统计
		$.GetTypeSheets("popTypeArea");
		//$.getJSON(context + "/report/ccmPeopleAmount/findPopByArea?area.id="+${area.id},
		//	function(data) {
				// 填充数据
		//		$.GetTypeSheets("popTypeArea", data);
		//});
		//总人口变化趋势+${area.id}
		$.getJSON(context + "/report/ccmPeopleAmount/findPopTrendByArea?area.id="+${area.id},
			function(data) {
				// 填充数据
				$.GetChangeSheets("popChangeArea", data);
		});
		

	})	
	
	
	function showHomePop(){
		$("#clickHomePop").addClass("active"); 
		$("#clickStructurePop").removeClass("active"); 
		$("#clickEmphasisPop").removeClass("active"); 
		$("#clickFloatingPop").removeClass("active");
		$("#homePop").css("display","block");
		$("#structurePop").css("display","none");
		$("#emphasisPop").css("display","none");
		$("#floatingPop").css("display","none");
		
		
		
	}
		
	//人口结构
	function showStructurePop(){
		$("#clickHomePop").removeClass("active"); 
		$("#clickStructurePop").addClass("active"); 
		$("#clickEmphasisPop").removeClass("active"); 
		$("#clickFloatingPop").removeClass("active");
		$("#homePop").css("display","none");
		$("#structurePop").css("display","block");
		$("#emphasisPop").css("display","none");
		$("#floatingPop").css("display","none");
		
		
		//年龄结构分析
		$.GetAgeSheets("popAgeArea");
		
		
		//性别比例分析
		$.GetSexSheets("popSexArea");
	
		//教育程度对比
		$.GetEduSheets("popEduArea");
		
		
	}
	
	//重点人群
	function showEmphasisPop(){
		$("#clickHomePop").removeClass("active"); 
		$("#clickStructurePop").removeClass("active"); 
		$("#clickEmphasisPop").addClass("active"); 
		$("#clickFloatingPop").removeClass("active");
		$("#homePop").css("display","none");
		$("#structurePop").css("display","none");
		$("#emphasisPop").css("display","block");
		$("#floatingPop").css("display","none");
		
		//关注程度
		$.GetAtteSheets("popAtteArea");
		

			
		// 特殊人群社区统计情况
		$.getJSON(context + "/report/ccmPeopleAmount/findPopByArea?area.id="+${area.id},
				function(data) {
					// 填充数据
					$.GetWorkSheets("popArea", $.ToConvertA(data));
		});
		//特殊人群类型及变化趋势
		$.getJSON(context + "/report/ccmPeopleAmount/findPopTrendByArea?area.id="+${area.id},
					function(data) {
						// 填充数据
						$.popEcharts("popTrendArea", data);
		});
		
	}
	
	//流动分析
	function showFloatingPop(){
		$("#clickHomePop").removeClass("active"); 
		$("#clickStructurePop").removeClass("active"); 
		$("#clickEmphasisPop").removeClass("active"); 
		$("#clickFloatingPop").addClass("active");
		$("#homePop").css("display","none");
		$("#structurePop").css("display","none");
		$("#emphasisPop").css("display","none");
		$("#floatingPop").css("display","block");
		
		//流动人员来源地分析top5 	
		$.getJSON(context + "/report/ccmPeopleAmount/findPopByArea?area.id="+${area.id},
				function(data) {
					// 填充数据
					$.GetFloatingSheets("popFloatingArea", $.ToConvertA(data));
		});
		//流动人员变化趋势（总数）
		$.getJSON(context + "/report/ccmPeopleAmount/findPopTrendByArea?area.id="+${area.id},
				function(data) {
					// 填充数据
					$.GetFlowSheets("popFlowArea", data);
		});


	}
	
	
	
	// 特殊人群社区统计情况
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
    
    
	// 特殊人群社区统计情况
	$.GetWorkSheets = function(model,data) {
		
        var nameArr = [],
        DataArr = [];
        
        var option = {
            tooltip: {
                trigger: 'item',
                formatter: "{b} :<br/> {c} ({d}%)",
		        confine:true
            },
            legend: {
                
                type: 'scroll',
                orient: 'vertical',
                left:'left',
                top:'middle',
                textStyle: {
                    color: '#fff',
                    fontSize: _fontSize,
                },
                data: data
            }, 
            series: [{
                name: '特殊人群类型',
                type: 'pie',
                radius : ['50%', '80%'],
                center: ['65%', '50%'],
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
	  
   //特殊人群类型及变化趋势
   $.popEcharts=function(model,data){
	   var type=[];
	   var value1=[];
	   var value2=[];
	   var value3=[];
	   var value4=[];
	   var value5=[];
	   var value6=[];
	   var value7=[];
	   var value8=[];
	   for(var i=0;i<data.length;i++){ 
		   type.push(data[i]['type']);
		   value1.push(data[i]['value1']);
		   value2.push(data[i]['value2']);
		   value3.push(data[i]['value3']);
		   value4.push(data[i]['value4']);
		   value5.push(data[i]['value5']);
		   value6.push(data[i]['value11']);
		   value7.push(data[i]['value12']);
		   value8.push(data[i]['value13']);
		}
	   
	   //console.log(data["type"])
	   option = {

			    tooltip: {
			        trigger: 'axis',
			        confine:true
			    },
			    color:color,
		   grid: {
			        left: '3%',
			        right: '3%',
			        bottom: '0%',
			        top:'3%',
			        containLabel: true
			    },
			    xAxis: {
			        type: 'category',
			        boundaryGap: false,
			        //data: ['周一','周二','周三','周四','周五','周六','周日'],\
			        data: type,
			        axisLine: {
	                    lineStyle: {
	                        color: '#fff'
	                    }
	                },
			    },
			    yAxis: {
			        type: 'value',
			        axisLine: {
	                    lineStyle: {
	                        color: '#fff'
	                    }
	                },
	                splitLine: {
	                    show: false
	                }
			    },
			    series: [
			        {
			            name:'社区矫正人员',
			            type:'line',
			            //data:[120, 132, 101, 134, 90, 230, 210]
			            data:value1,
			            smooth: true
			        },
			        {
			            name:'易肇事肇祸精神病患者',
			            type:'line',
			            data:value2,
			            smooth: true
			        },
			        {
			            name:'吸毒人员',
			            type:'line',
			            data:value3,
			            smooth: true
			        },
			        {
			            name:'安置帮教人员',
			            type:'line',
			            data:value4,
			            smooth: true
			        },
			        {
			            name:'艾滋病危险人员',
			            type:'line',
			            data:value5,
			            smooth: true
			        },
			        {
			            name:'重点上访人员',
			            type:'line',
			            data:value6,
			            smooth: true
			        },
			        {
			            name:'涉教人员',
			            type:'line',
			            data:value7,
			            smooth: true
			        },
			        {
			            name:'危险品从业人员',
			            type:'line',
			            data:value8,
			            smooth: true
			        }
			    ]
			};

	    var Barchart = echarts.init(document.getElementById('popEcharts'));
        Barchart.setOption(option);
	}
   
   
	//关注程度
	$.GetAtteSheets = function(model,data) {
	       var nameArr = [],
	       DataArr = [];
	       
	       var option = {
	               tooltip: {
	                   trigger: 'item',
	                   formatter: "{b}:<br/> {c} ({d}%)",
	                   confine:true
	               },
	               color: color,
	               grid: {
	                   left: '3%',
	                   right: '3%',
	                   bottom: '3%',
	                   top: '0%',
	                   containLabel: true
	               },
	               series: [{
	                   name: '',
	                   type: 'pie',
	                   radius: ['55%', '75%'],
	                   labelLine:{    
	                       normal:{    
	                          length:5
	                       }    
	                   }, 
	                   label: {
	                       normal: {
	                           formatter: '{b|{b}：}{c}',
	                           borderRadius: 4,
	                           rich: {
	                               a: {
	                                   color: '#999',
	                                   lineHeight: 22,
	                                   align: 'center'
	                               },
	                               b: {
	                                   fontSize: _fontSize,
	                                   lineHeight: 18
	                               },
	                               per: {
	                                   color: '#eee',
	                                   backgroundColor: '#334455',
	                                   padding: [2, 4],
	                                   borderRadius: 2
	                               }
	                           }
	                       }
	                   },
	                   data:[
	   	                {value:${ccmPeopleAmount.atteHigh}, name:'高'},
	   	                {value:${ccmPeopleAmount.atteMiddle}, name:'中'},
	   	                {value:${ccmPeopleAmount.atteLow}, name:'低'},
	   	            ],
	               }]
	           };
	       var Barchart = echarts.init(document.getElementById(model));
	       Barchart.setOption(option);
	
	}
	
	
	//流动人员来源地分析top5
	$.GetFloatingSheets = function(model,data) {
        var nameArr = [],
        DataArr = [];
        
        var nativeFloatings = [{"place":"无数据","num":"0"}];
       	if(${ccmPeopleAmount.nativeFloating}!="100000"){
       		nativeFloatings = ${ccmPeopleAmount.nativeFloating};
       	}
        var data1 = nativeFloatings;
        for(var i=0;i<data1.length;i++){
        	nameArr.push(data1[i].place)
        	DataArr.push({
        		value:parseInt(data1[i].num),
        		name:data1[i].place
        	})
        }
        console.log(DataArr)
        var option = {
        	 
        	    tooltip : {
        	        trigger: 'item',
        	        formatter: "{b} :<br/> {c} ({d}%)",
			        confine:true
        	    },
        	    legend: {
	                orient: 'vertical',
	                left:'left',
	                top:'middle',
	                textStyle: {
	                    color: '#fff',
	                    fontSize: _fontSize,
	                },
	                data: nameArr
	            }, 
        	    series : [
        	        {
        	            name: '',
        	            type: 'pie',
        	            radius: '80%',
    	                center: ['65%', '50%'],
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
        	            data:DataArr,
        	            itemStyle: {
        	                emphasis: {
        	                    shadowBlur: 10,
        	                    shadowOffsetX: 0,
        	                    shadowColor: 'rgba(0, 0, 0, 0.5)'
        	                }
        	            }
        	        }
        	    ]
        	};
        var Barchart = echarts.init(document.getElementById(model));
        Barchart.setOption(option);
        Barchart.on('click',
        function(params) {
        });

    }

	//流动人员变化趋势（总数）
	$.GetFlowSheets = function(model,data) {
        var nameArr = [],
        DataArr = [];
        var type=[];
		var value6=[];
		for(var i=0;i<data.length;i++){ 
			type.push(data[i]['type']);
			value6.push(data[i]['value6']);
		}
        var option = {
        		   tooltip : {
	        	        trigger: 'item',
	        	        formatter: "{b} :<br/> {c}",
				        confine:true
	        	    },
	        	     grid: {
		                    left: '3%',
		                    right: '3%',
		                    bottom: '3%',
		                    top: '3%',
		                    containLabel: true
		                },
		                
        	    yAxis: {
        	        type: 'value',
        	        axisLine: {
	                    lineStyle: {
	                        color: '#fff'
	                    }
	                },
	                splitLine: {
	                    show: false
	                }
        	    },
        	    
        	    xAxis: {
        	        type: 'category',
        	        data: type,
        	        axisLine: {
	                    lineStyle: {
	                        color: '#fff'
	                    }
	                },
	                splitLine: {
	                    show: false
	                }
        	    },
        	    series: [{
        	        data: value6,
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
        	};
        var Barchart = echarts.init(document.getElementById(model));
        Barchart.setOption(option);
        Barchart.on('click',
        function(params) {
        });

    }
	
	
	//年龄结构分析
	$.GetAgeSheets = function(model,data) {
        var nameArr = [],
        DataArr = [];
        
        var option = {
                tooltip: {
                    trigger: 'axis',
                    formatter: '{b}<br/>{a}: {c0}<br/>{a1}:{c1}%',
			        confine:true
                },
                grid: {
                    top: '10%',
                    left: '3%',
                    right: '3%',
                    bottom: '3%',
                    containLabel: true
                },
                legend: {
                    data: ['人数', '人口类型划分标准'],
                    textStyle: {
                        color: '#fff',
                        fontSize: _fontSize
                    }
                },
                xAxis: [{
                    type: 'category',
                    // data: ydata,
                    data: ['0-15','16-40','41-64','65及以上'],
                    // data:ydata,
                    axisPointer: {
                        type: 'shadow'
                    },
                    axisLine: {
                        lineStyle: {
                            color: '#fff'
                        }
                    },
                    axisLabel: {
                        textStyle: {
                            color: '#fff',
                            fontSize: _fontSize
                        }
                    },
                }],
                yAxis: [{
                    type: 'value',
                    name: '',
                    min: 0,
                    axisLabel: {
                        formatter: '{value} ',
                        textStyle: {
                            color: '#fff',
                            fontSize: _fontSize
                        }
                    },
                    axisLine: {
                        lineStyle: {
                            color: '#fff'
                        }
                    },
                    splitLine: {
                        show: false
                    }
                },
                {
                    type: 'value',
                    name: '',
                    interval: 25,
                    axisLabel: {
                        formatter: '{value} %'
                    },
                    axisLine: {
                        lineStyle: {
                            color: '#fff'
                        }
                    },
                    splitLine: {
                        show: false
                    }
                }],
                series: [{
                    name: '人数',
                    type: 'bar',
                    // data: jdata,
                    data: [${ccmPeopleAmount.ageChild},${ccmPeopleAmount.ageAdult},${ccmPeopleAmount.ageMiddle},${ccmPeopleAmount.ageOld}],
                    itemStyle: {
                        normal: {
                            color: "#14c1c0",
                        },
                        textStyle: {
                            color: '#fff',
                            fontSize: _fontSize
                        }
                    },
                    barWidth: 23,
                    boundaryGap: true
                },
                {
                    name: '人口类型划分标准',
                    type: 'line',
                    yAxisIndex: 1,
                    // data: hdata,
                    data: [30,35,28,7],
                    itemStyle: {
                        normal: {
                            color: "#ffea00"
                        }
                    },
                    textStyle: {
                        color: '#fff',
                        fontSize: _fontSize
                    }
                }]
            };
        var Barchart = echarts.init(document.getElementById(model));
        Barchart.setOption(option);
        Barchart.on('click',
        function(params) {
        });

	} 
	
	
	//性别比例分析
	$.GetSexSheets = function(model,data) {
        var nameArr = [],
        DataArr = [];
        
        var option = {
        	    tooltip: {
        	        trigger: 'item',
        	        formatter: "{b}:<br/> {c} ({d}%)",
			        confine:true
        	    },
        	    series: [
        	        {
        	            name:'',
        	            type:'pie',
        	            radius : ['50%', '80%'],
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
        	            data:[
        	                {value:${ccmPeopleAmount.sexMale}, name:'男'},
        	                {value:${ccmPeopleAmount.sexFemale}, name:'女'}
        	            ]
        	        }
        	    ]
        	};
        var Barchart = echarts.init(document.getElementById(model));
        Barchart.setOption(option);
        Barchart.on('click',
        function(params) {
        });

	} 
	
	//教育程度对比
	$.GetEduSheets = function(model,data) {
        var nameArr = [],
        DataArr = [];
        
        var option = {
        	    tooltip: {
        	        trigger: 'item',
        	        formatter: "{b}:<br/> {c} ({d}%)",
			        confine:true
        	    },
        	    series: [
        	        {
        	            name:'',
        	            type:'pie',
        	            radius : ['50%', '80%'],
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
        	            data:[
        	                {value:${ccmPeopleAmount.eduJunior}, name:'初中及以下'},
        	                {value:${ccmPeopleAmount.eduSenior}, name:'高中'},
        	                {value:${ccmPeopleAmount.eduCollege}, name:'大学'},
        	                {value:${ccmPeopleAmount.eduMaster}, name:'研究生'},
        	                {value:${ccmPeopleAmount.eduDoctor}, name:'博士及以上'}
        	            ]
        	        }
        	    ]
        	};
        var Barchart = echarts.init(document.getElementById(model));
        Barchart.setOption(option);
        Barchart.on('click',
        function(params) {
        });

	} 
	
	//人口类型统计
	$.GetTypeSheets = function(model,data) {
        var nameArr = [],
        DataArr = [];
        var personAmounts = ${ccmPeopleAmount.personAmount};
        var floatAmounts = ${ccmPeopleAmount.floatAmount};
        var overseaAmounts = ${ccmPeopleAmount.overseaAmount};
        var unsettleAmounts = ${ccmPeopleAmount.unsettleAmount};
        
        var option = {
        	    tooltip: {
        	        trigger: 'item',
        	        formatter: "{b}: <br/>{c} ({d}%)",
			        confine:true
        	    },
        	    series: [
        	        {
        	            name:'',
        	            type:'pie',
        	            radius: '90%',
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
        	            data:[
        	                {value:personAmounts, name:'户籍人口'},
        	                {value:floatAmounts, name:'流动人口'},
        	                {value:overseaAmounts, name:'外籍人口'},
        	                {value:unsettleAmounts, name:'未落户人口'}
        	            ]
        	        }
        	    ]
        	};
        var Barchart = echarts.init(document.getElementById(model));
        Barchart.setOption(option);
        Barchart.on('click',
        function(params) {
        });

    } 
	
	
	//总人口变化趋势
	$.GetChangeSheets =function(model,data){
	   var type=[];
	   var value6=[];
	   var value7=[];
	   var value8=[];
	   var value9=[];
	   for(var i=0;i<data.length;i++){ 
		   type.push(data[i]['type']);
		   value6.push(data[i]['value6']);
		   value7.push(data[i]['value7']);
		   value8.push(data[i]['value8']);
		   value9.push(parseInt(data[i]['value6'])+parseInt(data[i]['value7'])+parseInt(data[i]['value8']));
		}

	 var   option = {
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
   	        axisLine: {
                    lineStyle: {
                        color: '#fff'
                    }
                },
                splitLine: {
                    show: false
                }
   	    },
   	    
   	    xAxis: {
   	        type: 'category',
   	        data: type,
   	        axisLine: {
                    lineStyle: {
                        color: '#fff'
                    }
                },
                splitLine: {
                    show: false
                }
   	    },
   	    series: [{
   	        data: value9,
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

	    var Barchart = echarts.init(document.getElementById('popChangeArea'));
        Barchart.setOption(option);
   }
 

	 
	 
	 

</script>