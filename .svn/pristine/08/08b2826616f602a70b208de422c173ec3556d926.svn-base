<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>社交关系</title>
<meta name="decorator" content="default" />
<script type="text/javascript"
	src="${ctxStatic}/ccm/house/js/ccmHouseReleaseInfo.js">
</script>
<script type="text/javascript"
		src="${ctxStatic}/ccm/pop/js/ccmCommon.js"></script>
<script src="${ctxStatic}/common/index/Scripts/js/echarts.min.js"></script>
<script type="text/javascript">
    $(function(){
		$(".pimg").click(function(){
			var _this = $(this);//将当前的pimg元素作为_this传入函数
			imgShow("#outerdiv", "#innerdiv", "#bigimg", _this);
		});
	});
	function imgShow(outerdiv, innerdiv, bigimg, _this){
		var src = _this.attr("src");//获取当前点击的pimg元素中的src属性
		$(bigimg).attr("src", src);//设置#bigimg元素的src属性
		/*获取当前点击图片的真实大小，并显示弹出层及大图*/
		$("<img/>").attr("src", src).load(function(){
			var windowW = $(window).width();//获取当前窗口宽度
			var windowH = $(window).height();//获取当前窗口高度
			var realWidth = this.width;//获取图片真实宽度
			var realHeight = this.height;//获取图片真实高度
			var imgWidth, imgHeight;
			var scale = 0.8;//缩放尺寸，当图片真实宽度和高度大于窗口宽度和高度时进行缩放
			if(realHeight>windowH*scale) {//判断图片高度
				imgHeight = windowH*scale;//如大于窗口高度，图片高度进行缩放
				imgWidth = imgHeight/realHeight*realWidth;//等比例缩放宽度
				if(imgWidth>windowW*scale) {//如宽度扔大于窗口宽度
					imgWidth = windowW*scale;//再对宽度进行缩放
				}
			} else if(realWidth>windowW*scale) {//如图片高度合适，判断图片宽度
				imgWidth = windowW*scale;//如大于窗口宽度，图片宽度进行缩放
				imgHeight = imgWidth/realWidth*realHeight;//等比例缩放高度
			} else {//如果图片真实高度和宽度都符合要求，高宽不变
				imgWidth = realWidth;
				imgHeight = realHeight;
			}
			$(bigimg).css("width",imgWidth);//以最终的宽度对图片缩放
			var w = (windowW-imgWidth)/2;//计算图片与窗口左边距
			var h = (windowH-imgHeight)/2;//计算图片与窗口上边距
			$(innerdiv).css({"top":h, "left":w});//设置#innerdiv的top和left属性
			$(outerdiv).fadeIn("fast");//淡入显示#outerdiv及.pimg
		});
		$(outerdiv).click(function(){//再次点击淡出消失弹出层
			$(this).fadeOut("fast");
		});
	}
</script>
</head>
<body>

	<ul id="myTab" class="nav nav-tabs">
		<li ><a href="#SocialRelations" data-toggle="tab">社会关系图谱</a></li>
		<li ><a href="#roomPop" data-toggle="tab">同住人员信息</a></li>
		<li ><a href="#homePop" data-toggle="tab">家庭成员信息</a></li>
	</ul>
	<div id="myTabContent" class="tab-content">
	<div class="tab-pane fade in active" id="SocialRelations" style="align-content:center;width:100%;height:800px;">
		<div id="SocialRelationsEcharts" style="width:90%;height:300px;margin-left:5%;">
		</div>
		<div style="width:100%;height:490px;margin-top:10px;">
			<c:if test="${not empty listSocial}">
				<table class="table table-striped table-bordered table-condensed">
					<tr>
						<td>人员图片</td>
						<td>姓名</td>
						<td>性别</td>
						<td>出生日期</td>
						<td>公民身份号码</td>
						<td>联系方式</td>
						<td>关系</td>
					</tr>
					<c:forEach items="${listSocial}" var="cpp">
						<tr>
							<td width="100px">
								<img src="${cpp.images}" style="height:80px;" class="pimg"/>
							</td>
							<td>${cpp.name}</td>
							<td>${fns:getDictLabel(cpp.sex, 'sex', '')}</td>
							<td><fmt:formatDate value="${cpp.birthday}" pattern="yyyy-MM-dd"/></td>
							<td>${cpp.ident}</td>
							<td>${cpp.telephone}</td>
							<td>${fns:getDictLabel(cpp.cpptype, 'cpp_pop_pop_type', '')}</td>
						</tr>
					</c:forEach>
				</table>
			</c:if>
			<c:if test="${empty listSocial}">
				<div style="text-align:center;width:100%;height:100%;"><h3>暂无数据</h3></div>
			</c:if>
		</div>
	</div>
		
		<div class="tab-pane fade" id="roomPop">
			<c:if test="${not empty listRoomPeople}">
				<table class="table table-striped table-bordered table-condensed">
					<tr>
						<td>人员图片</td>
						<td>姓名</td>
						<td>性别</td>
						<td>出生日期</td>
						<td>公民身份号码</td>
						<td>联系方式</td>
					</tr>
					<c:forEach items="${listRoomPeople}" var="ccmpop">
						<tr>
							<td width="100px">
								<img src="${ccmpop.images}" style="height:80px;"/>
							</td>
							<td>${ccmpop.name}</td>
							<td>${fns:getDictLabel(ccmpop.sex, 'sex', '')}</td>
							<td><fmt:formatDate value="${ccmpop.birthday}" pattern="yyyy-MM-dd"/></td>
							<td>${ccmpop.ident}</td>
							<td>${ccmpop.telephone}</td>
						</tr>
					</c:forEach>
				</table>				
			</c:if>
			<c:if test="${empty listRoomPeople}">
				<div style="text-align:center;width:100%;height:100%;"><h3>暂无数据</h3></div>
			</c:if>
		</div>
		<div class="tab-pane fade" id="homePop">
			<c:if test="${not empty listAccount}">
				<table class="table table-striped table-bordered table-condensed">
					<tr>
						<td>人员图片</td>
						<td>姓名</td>
						<td>性别</td>
						<td>出生日期</td>
						<td>公民身份号码</td>
						<td>联系方式</td>
						<td>与户主关系</td>
					</tr>
					<c:forEach items="${listAccount}" var="ccmpop">
						<tr>
							<td width="100px">
								<img src="${ccmpop.images}" style="height:80px;"/>
							</td>
							<td>${ccmpop.name}</td>
							<td>${fns:getDictLabel(ccmpop.sex, 'sex', '')}</td>
							<td><fmt:formatDate value="${ccmpop.birthday}" pattern="yyyy-MM-dd"/></td>
							<td>${ccmpop.ident}</td>
							<td>${ccmpop.telephone}</td>
							<td>${fns:getDictLabel(ccmpop.accountrelation,'sys_ccm_fami_ties',"")}
							</td>
						</tr>
					</c:forEach>
				</table>
			</c:if>
			<c:if test="${empty listAccount}">
				<div style="text-align:center;width:100%;height:100%;"><h3>暂无数据</h3></div>
			</c:if>
		</div>
	</div>
	<script>
	   $(function(){
		   var cppPopPopType={};
		   var cppWebType={};
		   var cppVehileType={};
		   var cppPhoneType={};
			$.ajax({
				type : "post",
				url : "${ctx}/sys/dict/listData?type=cpp_pop_pop_type",
				async : false,
				success : function(data){
					for(var i in data){
						cppPopPopType[data[i].value]=data[i].label;
					}
				}
			});
			$.ajax({
				type : "post",
				url : "${ctx}/sys/dict/listData?type=cpp_vehile_type",
				async : false,
				success : function(data){
					for(var i in data){
						cppVehileType[data[i].value]=data[i].label;
					}
				}
			});
			$.ajax({
				type : "post",
				url : "${ctx}/sys/dict/listData?type=cpp_web_type",
				async : false,
				success : function(data){
					for(var i in data){
						cppWebType[data[i].value]=data[i].label;
					}
				}
			});
			$.ajax({
				type : "post",
				url : "${ctx}/sys/dict/listData?type=cpp_phone_type",
				async : false,
				success : function(data){
					for(var i in data){
						cppPhoneType[data[i].value]=data[i].label;
					}
				}
			});
		   $.getJSON('${ctx}/pop/ccmPeople/relation?id=${ccmPeople.id}',function(data){
			   var cppPopPop=data.cppPopPop;
			   var cppPopVehile=data.cppPopVehile;
			   var name="${ccmPeople.name}";
			   var myChartData=[{
	                name: name,
	                draggable: true,
	            }];
			   for(var i in cppPopPop){
				   myChartData.push({
		                name: cppPopPop[i].otherName,
		                type:cppPopPopType[cppPopPop[i].type],
		                category: 1,
		                draggable: true,
		            })
			   }
			   for(var i in cppPopVehile){
				   if(cppPopVehile[i].type=='01'){
					   myChartData.push({
			                name:cppPopVehile[i].textName,
			                type:cppVehileType[cppPopVehile[i].subType],
			                category: 1,
			                draggable: true,
			            })
				   }else if(cppPopVehile[i].type=='02'){
					   myChartData.push({
			                name:cppPopVehile[i].textName,
			               type:cppPhoneType[cppPopVehile[i].subType],
			                category: 1,
			                draggable: true,
			            })
				   }else if(cppPopVehile[i].type=='03'){
					   myChartData.push({
			                name:cppPopVehile[i].textName,
			                type:cppWebType[cppPopVehile[i].subType],
			                category: 1,
			                draggable: true,
			            })
				   }else if(cppPopVehile[i].type=='04'){
					   myChartData.push({
			                name:cppPopVehile[i].textName,
			                type:cppPopVehile[i].otherTypeName,
			                category: 1,
			                draggable: true,
			            })
				   }
				    
			   }
			   
			   console.log(myChartData)
			   var linksData=[];
			   var len=myChartData.length;
			   var j=1;
			   if(len>0){
				   for(var j=1;j<len;j++){
					   linksData.push({
			                source: 0,
			                target: Number(j),
			                value: myChartData[j].type
			            })
				   }
			   }
			   console.log(linksData)
			   var myChart = echarts.init(document.getElementById("SocialRelationsEcharts"));
			   var option = {
					    title: {
					        text: ''
					    },
					    tooltip: {},
					    animationDurationUpdate: 1500,
					    animationEasingUpdate: 'quinticInOut',
					    label: {
					        normal: {
					            show: true,
					            textStyle: {
					                fontSize: 12
					            },
					        }
					    },
					    series: [

					        {
					            type: 'graph',
					            layout: 'force',
					            symbolSize: 45,
					            focusNodeAdjacency: true,
					            roam: true,
					            categories: [{
					                name: '夫妻',
					                itemStyle: {
					                    normal: {
					                        color: "#009800",
					                    }
					                }
					            }, {
					                name: '战友',
					                itemStyle: {
					                    normal: {
					                        color: "#4592FF",
					                    }
					                }
					            }, {
					                name: '亲戚',
					                itemStyle: {
					                    normal: {
					                        color: "#3592F",
					                    }
					                }
					            }],
					            label: {
					                normal: {
					                    show: true,
					                    textStyle: {
					                        fontSize: 12
					                    },
					                }
					            },
					            force: {
					                repulsion: 1000
					            },
					            edgeSymbolSize: [4, 50],
					            edgeLabel: {
					                normal: {
					                    show: true,
					                    textStyle: {
					                        fontSize: 10
					                    },
					                    formatter: "{c}"
					                }
					            },
					            data:myChartData,
					            links:linksData,
					            lineStyle: {
					                normal: {
					                    opacity: 0.9,
					                    width: 1,
					                    curveness: 0
					                }
					            }
					        }
					    ]
					};
				myChart.setOption(option);
			   
		   })
		   
		  
	   })
	</script>
	<div id="outerdiv" style="position:fixed;top:0;left:0;background:rgba(0,0,0,0.7);z-index:2;width:100%;height:100%;display:none;">
			<div id="innerdiv" style="position:absolute;">
				<img id="bigimg" style="border:5px solid #fff;" src="" />
			</div>
		</div>
</body>
</html>