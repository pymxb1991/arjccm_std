<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/include/head.jsp"%>
<link rel="stylesheet" href="/arjccm/static/ol/ol.css" type="text/css">
<link href="/arjccm/static/jquery-ztree/3.5.12/css/zTreeStyle/zTreeStyle.min.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="/arjccm/static/ccm/liveVideo/css/livedemo.css">
<link rel="stylesheet" href="/arjccm/static/ccm/liveVideo/css/video-js.css">
<link rel="stylesheet" href="/arjccm/static/modules/map/js/draw/css/p-ol3.min.css" type="text/css">
<link rel="stylesheet" href="/arjccm/static/modules/map/js/draw/css/defaults.css" type="text/css">
<link rel="stylesheet" href="/arjccm/static/modules/map/css/map.css" type="text/css">
<link rel="stylesheet" href="/arjccm/static/modules/map/css/house.css" type="text/css">
<link href="/arjccm/static/jquery-jbox/2.3/Skins/Bootstrap/jbox.min.css" rel="stylesheet">
<link href="/arjccm/static/modules/map/css/pop-info-animate.css" rel="stylesheet">
<link href="/arjccm/static/layer-v3.1.1/layer/theme/default/layer.css" rel="stylesheet">
<link href="/arjccm/static/bootstrap/2.3.1/awesome/font-awesome.min.css" type="text/css" rel="stylesheet">
<link rel="stylesheet" href="/arjccm/static/modules/map/css/publicinstitutions.css">
<link href="/arjccm/static/layim/layui/css/layui.css" type="text/css" rel="stylesheet">

<style>
.radio-div {
	margin-top: 7px;
	margin-left: 10px;
}

.houseclick.btn {
	background: #BDC7C7;
}
.build {
	width: 400px;
	height: auto;
	position: relative;
}

.floor {
	width: 100%;
	height: 69px;
}

.build table {
	width: 100%;
	height: 100%;
	border-collapse: collapse;
	padding: 0;
	table-layout: fixed;
}

.build table td {
	border: 1px solid #000;
	text-align: center;
}

.floor-add {
	width: 100%;
	text-align: center;
	cursor: pointer;
	background: #f5f3f0;
}

.floor-add i {
	font-size: 42px;
}

.floor-center {
	width: 100%;
	height: 100%;
	position: relative;
	cursor: pointer;
}

/* .floor-center:hover {
	background: #ffd073!important;
} */

.floor-center span {
	line-height: 47px;
}

.remove-floor {
	position: absolute;
	right: 3px;
	top: 3px;
	font-size: 12px;
	font-weight: bold;
	display: none;
}

.floor-add-left {
	position: absolute;
	left: 3px;
	bottom: 3px;
	display: none;
	color: #55ac4f;
}

.floor-add-right {
	position: absolute;
	right: 3px;
	bottom: 3px;
	display: none;
	color: #55ac4f;
}

.unit {
	width: 400px;
	float: left;
	position: relative;
}

.unit-num {
	width: 100%;
	position: relative;
	float: left;
	
}

.unit-num span {
	width: 100%;
	font-weight: bold;
	text-align: center;
	height: 24px;
    line-height: 24px;
	display: block;
	background: -ms-linear-gradient(top, #fff, #e6e6e6); /* IE 10 */
	background: -moz-linear-gradient(top, #fff, #e6e6e6); /*火狐*/
	background: -webkit-gradient(linear, 0% 0%, 0% 100%, from(#fff),
		to(#e6e6e6)); /*谷歌*/
}

.addFloor {
	width: 48px;
	height: 24px;
	line-height: 24px;
	background: -ms-linear-gradient(top, #fff, #e6e6e6); /* IE 10 */
	background: -moz-linear-gradient(top, #fff, #e6e6e6); /*火狐*/
	background: -webkit-gradient(linear, 0% 0%, 0% 100%, from(#fff),
		to(#e6e6e6)); /*谷歌*/
	text-align: center;
	cursor: pointer;
	margin-left: 70px;
}

.addFloor.addFloorBottom {
	height: 48px;
	line-height: 48px;
}

.addFloorBottom {
	position: absolute;
	right: -48px;
	top: -48px;
}

.addUnit {
	position: absolute;
	right: -48px;
	top: 1px;
}

.unit-botom {
	float: left;
	position: relative;
}

</style>
<c:if test="${ maxelemnum > 0 }">
	<div class="row-fluid">

		<div class="span9">
			<!-- 详情 -->
			<div class="row-fluid">
				<h4 style="padding: 10px 52px;">楼栋名称: ${buildName}</h4>
			</div>
				<!-- 分页 -->
			 <div class="page row-fluid">
				<div class="span2"></div>
				<div class="span2">
					<div class="page-left  page-icon">
						<div></div>
					</div>
				</div>
				<div class="span2">
					<div class="page-right  page-icon">
						<div></div>
					</div>
				</div>
			</div>
			<div class="page row-fluid" style="margin-left: 10px;">
		    	<div class="span1">
					<div class="page-down active page-icon"></div>
				</div> 
				<div class="span10">
				  <div id="roomCenter"></div>
				</div>
			    <div class="span1" style="margin-left:0">
					<div class="page-top active  page-icon"></div>
				</div>
			</div>

		</div>
		<div class="span3" style="margin-left: 0;" >
			<div class="row-fluid">
				<!-- 分类 -->
				<div class="span12" style="padding: 8px 0">
					<div class="pop-house active "
						style="margin-top: 20px; padding: 2px;">
						<div class="row-fluid" style="margin-top: 10px;">
							<div class="span12">
								<ul class=" tab-switch">
									<li class="active">重点人群</li>
									<li>特殊人群</li>
									<li>房屋类型</li>
								</ul>
							</div>
						</div>
						<!--重点人员  -->
						<div class="row-fluid tab-center active" style="margin-top: 10px;">
							<div class="span12">
								<div class="span12" style="">
									<div class="row-fluid "
										style="border-bottom: 1px solid #b6d1d6; height: 32px;">
										<div class="span6 lineght30">留守人员</div>
										<div class="span3">
											<div class="type-pop7"></div>
										</div>
										<div class="span3 lineght30">
											<div class="checkbox checkbox-success radio-div">
												<input type="checkbox" value="7" class="type-input"
													name="repairId" onclick="selectSingle('repairId')" id="LiuShou" /> <label
													for="LiuShou"></label>
											</div>
										</div>
									</div>
									<div class="row-fluid "
										style="border-bottom: 1px solid #b6d1d6; height: 32px;">
										<div class="span6 lineght30">重点青少年</div>
										<div class="span3 ">
											<div class="type-pop3"></div>
										</div>
										<div class="span3 lineght30">
											<div class="checkbox checkbox-success radio-div">
												<input type="checkbox" value="3" class="type-input"
													name="repairId" onclick="selectSingle('repairId')" id="YoungPop" />
												<label for="YoungPop"></label>
											</div>
										</div>
									</div>
									<div class="row-fluid "
										style="border-bottom: 1px solid #b6d1d6; height: 32px;">
										<div class="span6 lineght30">老年人</div>
										<div class="span3">
											<div class="type-pop7" style="background: #ffff80"></div>
										</div>
										<div class="span3 lineght30">
											<div class="checkbox checkbox-success radio-div">
												<input type="checkbox" value="11" class="type-input"
													name="repairId" onclick="selectSingle('repairId')" id="LaoNian" /> <label
													for="LaoNian"></label>
											</div>
										</div>
									</div>
									<div class="row-fluid "
										style="border-bottom: 1px solid #b6d1d6; height: 32px;">
										<div class="span6 lineght30">低保人员</div>
										<div class="span3">
											<div class="type-pop7" style="background: #fd6d01"></div>
										</div>
										<div class="span3 lineght30">
											<div class="checkbox checkbox-success radio-div">
												<input type="checkbox" value="12" class="type-input"
													name="repairId" onclick="selectSingle('repairId')" id="DiBao" /> <label
													for="DiBao"></label>
											</div>
										</div>
									</div>
									<div class="row-fluid "
										style="border-bottom: 1px solid #b6d1d6; height: 32px;">
										<div class="span6 lineght30">残疾人员</div>
										<div class="span3">
											<div class="type-pop7" style="background: #951167"></div>
										</div>
										<div class="span3 lineght30">
											<div class="checkbox checkbox-success radio-div">
												<input type="checkbox" value="13" class="type-input"
													name="repairId" onclick="selectSingle('repairId')" id="canji" /> <label
													for="canji"></label>
											</div>
										</div>
									</div>
									<div class="row-fluid "
										style="border-bottom: 1px solid #b6d1d6; height: 32px;">
										<div class="span6 lineght30">退伍人员</div>
										<div class="span3">
											<div class="type-pop7" style="background: #e822e7"></div>
										</div>
										<div class="span3 lineght30">
											<div class="checkbox checkbox-success radio-div">
												<input type="checkbox" value="14" class="type-input"
													name="repairId" onclick="selectSingle('repairId')" id="tuiwu" /> <label
													for="tuiwu"></label>
											</div>
										</div>
									</div>
									<div class="row-fluid "
										style="border-bottom: 1px solid #b6d1d6; height: 32px;">
										<div class="span6 lineght30">优抚对象</div>
										<div class="span3">
											<div class="type-pop7" style="background: #eeb22a"></div>
										</div>
										<div class="span3 lineght30">
											<div class="checkbox checkbox-success radio-div">
												<input type="checkbox" value="15" class="type-input"
													name="repairId" onclick="selectSingle('repairId')" id="youfu" /> <label
													for="youfu"></label>
											</div>
										</div>
									</div>
									<div class="row-fluid "
										style="border-bottom: 1px solid #b6d1d6; height: 32px;">
										<div class="span6 lineght30">现役军人</div>
										<div class="span3">
											<div class="type-pop7" style="background: #55c723"></div>
										</div>
										<div class="span3 lineght30">
											<div class="checkbox checkbox-success radio-div">
												<input type="checkbox" value="16" class="type-input"
													name="repairId" onclick="selectSingle('repairId')" id="junren" /> <label
													for="junren"></label>
											</div>
										</div>
									</div>
									<div class="row-fluid "
										style="border-bottom: 1px solid #b6d1d6; height: 32px;">
										<div class="span6 lineght30">烈士家属</div>
										<div class="span3">
											<div class="type-pop7" style="background: #ff8080"></div>
										</div>
										<div class="span3 lineght30">
											<div class="checkbox checkbox-success radio-div">
												<input type="checkbox" value="17" class="type-input"
													name="repairId" onclick="selectSingle('repairId')" id="lieshi" /> <label
													for="lieshi"></label>
											</div>
										</div>
									</div>
									<div class="row-fluid "
										style="border-bottom: 1px solid #b6d1d6; height: 32px;">
										<div class="span6 lineght30">失业人员</div>
										<div class="span3">
											<div class="type-pop7" style="background: #0000ff"></div>
										</div>
										<div class="span3 lineght30">
											<div class="checkbox checkbox-success radio-div">
												<input type="checkbox" value="18" class="type-input"
													name="repairId" onclick="selectSingle('repairId')" id="shiye" /> <label
													for="shiye"></label>
											</div>
										</div>
									</div>
									<div class="row-fluid "
										style="border-bottom: 1px solid #b6d1d6; height: 32px;">
										<div class="span6 lineght30">孤儿</div>
										<div class="span3">
											<div class="type-pop7" style="background: #ff0080"></div>
										</div>
										<div class="span3 lineght30">
											<div class="checkbox checkbox-success radio-div">
												<input type="checkbox" value="19" class="type-input"
													name="repairId" onclick="selectSingle('repairId')" id="guer" /> <label
													for="guer"></label>
											</div>
										</div>
									</div>
									<div class="row-fluid "
										style="border-bottom: 1px solid #b6d1d6; height: 32px;">
										<div class="span6 lineght30">救助对象</div>
										<div class="span3">
											<div class="type-pop7" style="background: #804040"></div>
										</div>
										<div class="span3 lineght30">
											<div class="checkbox checkbox-success radio-div">
												<input type="checkbox" value="20" class="type-input"
													name="repairId" onclick="selectSingle('repairId')" id="jiuzhu" /> <label
													for="jiuzhu"></label>
											</div>
										</div>
									</div>
									<div class="row-fluid "
										style="border-bottom: 1px solid #b6d1d6; height: 32px;">
										<div class="span6 lineght30">失独人员</div>
										<div class="span3">
											<div class="type-pop7" style="background: #408080"></div>
										</div>
										<div class="span3 lineght30">
											<div class="checkbox checkbox-success radio-div">
												<input type="checkbox" value="22" class="type-input"
													name="repairId" onclick="selectSingle('repairId')" id="shidu" /> <label
													for="shidu"></label>
											</div>
										</div>
									</div>
								</div>

							</div>
							<div class="row-fluid " style="height: 32px;">
								<div class="span6 lineght30"></div>
								<div class="span3 lineght30">全选</div>
								<div class="span3 lineght30">
									<div class="checkbox checkbox-success radio-div">
										<input type="checkbox" value="4194303" class="type-input"
											name="repairId" onclick="checkAll(this, 'repairId')"
											id="checkId" /> <label for="checkId"></label>
									</div>
								</div>
							</div>
						</div>
						<!--重点人员  -->
						<!-- 特殊人群 -->
						<div class="row-fluid tab-center  speHouse"
							style="margin-top: 10px">
							<div class="span12"
								style="height: 465px; overflow-y: auto; overflow-x: hidden;">
							
							<div class="row-fluid "
									style="border-bottom: 1px solid #b6d1d6; height: 32px;">
									<div class="span6 lineght30">安置帮教人员</div>
									<div class="span3">
										<div class="type-pop5" style="background: #ff0000"></div>
									</div>
									<div class="span3 lineght30">
										<div class="checkbox checkbox-success radio-div">
											<input type="checkbox" value="5" class="type-input"
												name="repairId1" onclick="selectSingle('repairId1')" id="ReleasePop" />
											<label for="ReleasePop"></label>
										</div>
									</div>
								</div>
								<div class="row-fluid "
									style="border-bottom: 1px solid #b6d1d6; height: 32px;">
									<div class="span6 lineght30">社区矫正人员</div>
									<div class="span3">
										<div class="type-pop6"></div>
									</div>
									<div class="span3 lineght30">
										<div class="checkbox checkbox-success radio-div">
											<input type="checkbox" value="6" class="type-input"
												name="repairId1" onclick="selectSingle('repairId1')" id="CorrectPop" />
											<label for="CorrectPop"></label>
										</div>
									</div>
								</div>
							<div class="row-fluid "
									style="border-bottom: 1px solid #b6d1d6; height: 32px;">
									<div class="span6 lineght30">肇事肇祸等严重精神障碍患者</div>
									<div class="span3 ">
										<div class="type-pop2"></div>
									</div>
									<div class="span3 lineght30">
										<div class="checkbox checkbox-success radio-div">
											<input type="checkbox" value="2" class="type-input"
												name="repairId1" onclick="selectSingle('repairId1')" id="MentalPop" />
											<label for="MentalPop"></label>
										</div>
									</div>
								</div>
							<div class="row-fluid "
									style="border-bottom: 1px solid #b6d1d6; height: 32px;">
									<div class="span6 lineght30">吸毒人员</div>
									<div class="span3 ">
										<div class="type-pop4"></div>
									</div>
									<div class="span3 lineght30">
										<div class="checkbox checkbox-success radio-div">
											<input type="checkbox" value="4" class="type-input"
												name="repairId1" onclick="selectSingle('repairId1')" id="DrugPop" /> <label
												for="DrugPop"></label>
										</div>
									</div>
								</div>
								<div class="row-fluid "
									style="border-bottom: 1px solid #b6d1d6; height: 32px;">
									<div class="span6 lineght30">艾滋病危险人员</div>
									<div class="span3">
										<div class="type-pop1"></div>
									</div>
									<div class="span3 lineght30">
										<div class="checkbox checkbox-success radio-div">
											<input type="checkbox" value="1" class="type-input"
												name="repairId1" onclick="selectSingle('repairId1')" id="AIDSPop" /> <label
												for="AIDSPop"></label>
										</div>
									</div>
								</div>
								<div class="row-fluid "
									style="border-bottom: 1px solid #b6d1d6; height: 32px;">
									<div class="span6 lineght30">重点上访人员</div>
									<div class="span3">
										<div class="type-pop8"></div>
									</div>
									<div class="span3 lineght30">
										<div class="checkbox checkbox-success radio-div">
											<input type="checkbox" value="8" class="type-input"
												name="repairId1" onclick="selectSingle('repairId1')" id="ZhongDian" />
											<label for="ZhongDian"></label>
										</div>
									</div>
								</div>
								<div class="row-fluid "
									style="border-bottom: 1px solid #b6d1d6; height: 32px;">
									<div class="span6 lineght30">涉教人员</div>
									<div class="span3">
										<div class="type-pop9"></div>
									</div>
									<div class="span3 lineght30">
										<div class="checkbox checkbox-success radio-div">
											<input type="checkbox" value="9" class="type-input"
												name="repairId1" onclick="selectSingle('repairId1')" id="SheJiao" /> <label
												for="SheJiao"></label>
										</div>
									</div>
								</div>
								<div class="row-fluid "
									style="border-bottom: 1px solid #b6d1d6; height: 32px;">
									<div class="span6 lineght30">危险品从业人员</div>
									<div class="span3">
										<div class="type-pop10"></div>
									</div>
									<div class="span3 lineght30">
										<div class="checkbox checkbox-success radio-div">
											<input type="checkbox" value="10" class="type-input"
												name="repairId1" onclick="selectSingle('repairId1')" id="WeiXian" /> <label
												for="WeiXian"></label>
										</div>
									</div>
								</div>

								<div class="row-fluid " style="height: 32px;">
									<div class="span6 lineght30"></div>
									<div class="span3 lineght30">全选</div>
									<div class="span3 lineght30">
										<div class="checkbox checkbox-success radio-div">
											<input type="checkbox" value="4194303" class="type-input"
												name="repairId1" onclick="checkAll(this, 'repairId1')"
												id="checkId1" /> <label for="checkId1"></label>
										</div>
									</div>
								</div>
							</div>

						</div>

						<!-- 房屋类型 -->
						<div class="row-fluid tab-center" style="margin-top: 10px;">
							<div class="span12">
								<div class="span12" style="">


									<div class="row-fluid" style="border-bottom: 1px solid #b6d1d6">
										<div class="span6 lineght30">自住</div>
										<div class="span6 house-window-white"
											style="min-height: 26px; margin-top: 7px;"></div>

										<!-- 自住01 -->
									</div>
									<div class="row-fluid" style="border-bottom: 1px solid #b6d1d6">
										<div class="span6 lineght30">出租</div>
										<div class="span6 house-window2"
											style="min-height: 26px; margin-top: 7px;"></div>

										<!-- 	出租02 -->
									</div>
									<div class="row-fluid "
										style="border-bottom: 1px solid #b6d1d6">
										<div class="span6 lineght30">空置</div>
										<div class="span6 house-window6"
											style="min-height: 26px; margin-top: 7px;"></div>

										<!-- 空置03 -->
									</div>
									<div class="row-fluid" style="border-bottom: 1px solid #b6d1d6">
										<div class="span6 lineght30">暂无信息</div>
										<div class="span6 house-window7"
											style="min-height: 26px; margin-top: 7px;"></div>

									</div>
								</div>
							</div>
						</div>
						<!--房屋类型  -->
					</div>
				</div>
				<!--分类  -->
			</div>
		</div>
	</div>
</c:if>

<c:if test="${ maxelemnum == 0 }">
	<div
		style="padding: 5px; font-size: medium; font-style: normal; text-align: center;">
		<strong> 暂无数据！ </strong>
	</div>
</c:if>

<script>
	var checkAll, speHouseCheckbox, selectSingle;
    
	$(function() {
		var buildingId="${buildId}";
		house(buildingId)

		var myColor = new Array();

		myColor[1] = "#62c000";
		myColor[2] = "#8e7513";
		myColor[3] = "#3268ff";
		myColor[4] = "#e73400";
		myColor[5] = "#ff0000";
		myColor[6] = "#72fffd";
		myColor[7] = "#c4c501";
		myColor[8] = "#882529";
		myColor[9] = "#367300";
		myColor[10] = "#8e5cff";
		myColor[11] = "#ffff80";
		myColor[12] = "#fd6d01";
		myColor[13] = "#951167";
		myColor[14] = "#e822e7";
		myColor[15] = "#eeb22a";
		myColor[16] = "#55c723";
		myColor[17] = "#ff8080";
		myColor[18] = "#0000ff";
		myColor[19] = "#ff0080";
		myColor[20] = "#804040";
		myColor[21] = "#800000";
		myColor[22] = "#408080";
 
		checkAll = function(e, itemName) {
			var repairId = document.getElementsByName(itemName); //获取全选复选框
			for (var i = 0; i < repairId.length; i++) {
				repairId[i].checked = e.checked; //改变所有复选框的状态为全选复选框的状态
			}
			speHouseCheckbox()
		}
		selectSingle = function(id) {
			var k = 0;
			var oInput = document.getElementsByName(id);
			for (var i = 0; i < oInput.length; i++) {
				if (oInput[i].checked == false) {
					k = 1;
					break;
				}
			}
			if (k == 0) {
				//document.getElementsByName("selid").checked=true;
				document.getElementById("checkId").checked = true;

			} else {
				document.getElementById("checkId").checked = false;
				//document.getElementsByName("selid").checked=false;
			}
			speHouseCheckbox()
		}
		speHouseCheckbox = function() {
			var colorType = "";
			var colorArr = [];
			$("input[name='repairId']:checked").each(function() {
				colorType += $(this).val() + ',';
				colorArr.push($(this).val())
			});
			$("input[name='repairId1']:checked").each(function() {
				colorType += $(this).val() + ',';
				colorArr.push($(this).val())
			});
			// 查询指定的颜色
			colorType = colorType.substring(0, colorType.length - 1)

			if (colorType != "") {
				// 获取数据
				$.getJSON( "${ctx}/sys/map/getHousesSpeMore?id=${buildId}&type=" + colorType, function(data) {
					var len = data.length;
					var colorArrLen = colorArr.length;
					// 清除背景颜色 background: #fff;
					$(".houseclick.btn").css("background", "");
					$(".houseclick.btn").each(function(){
						$(this).html($(this).attr("textValue"));
					});
					if (len > 0) {
						for (var i = 0; i < len; i++) {
							var colorArrDiv = [];
							for (var j = 0; j < colorArrLen; j++) {
								if (data[i]["value" + colorArr[j] + ""] == "1") {
									// 为数据添加相应的值
									colorArrDiv.push(myColor[colorArr[j]]);
									//$(".me" + data[i].type) .css( "background", myColor[colorArr[j]]);
								}
							}
							if(colorArrDiv.length > 0){
								var divWidth = $(".me" + data[i].type).width() / colorArrDiv.length;
								var html = "";
								for (var m = 0; m < colorArrDiv.length; m++) {
									html += "<div style='background-color:"+ colorArrDiv[m] +";height:24px;width:"+ divWidth +"px;display:inherit;'></div>";
								}
								html += "<div style='position: relative;left:0px;top:-24px;'>" + $(".me" + data[i].type).attr("textValue") + "</div>";
								$(".me" + data[i].type).html(html);
							}
						}
					}
				});
			} else {
				// 清除背景颜色 background: #fff;
				$(".houseclick.btn").css("background", "");
				$(".houseclick.btn").each(function(){
					$(this).html($(this).attr("textValue"));
				});
			}
		}

		$(".speHouse :checkbox").change(function() {

		});
		// 添加点击事件 转换房屋与 人员类型的状态
		$('.tab-switch li').click(
				function() {
					var num = $(".tab-switch li").index(this);
					$(this).addClass('active').siblings().removeClass('active')
					$(".tab-center").eq(num).addClass('active').siblings(
							'.tab-center').removeClass('active');
				});

		
		

	})
function TopDown(){
		//上下滚动
		 var mainContainer = $('.build-top'), scrollToContainer = mainContainer
				.find('.ElemNum:last').height();//滚动到<div id="thisMainPanel">中类名为son-panel的最后一个div处
		var uNum=$('.unit-1 .floor').length;
		
		if(uNum>6){
			$('.page-down').removeClass('active');
		}else{
			$('.page-down').addClass('active')
			
		}
		$('.page-down').click(function() {
			var mainContainer = $('.build-top'), scrollToContainer = mainContainer.find('.floor:last').height() + 2;//滚动到<div id="thisMainPanel">中类名为son-panel的最后一个div处
			//动画效果
			mainContainer.animate({
								scrollTop : scrollToContainer+ mainContainer.scrollTop()
							},"fast",function() {
								
								if (mainContainer.scrollTop()+ mainContainer.height() == mainContainer[0].scrollHeight) {
									$('.page-down').addClass('active')
								} else {
									$('.page-down').removeClass('active');
								}

								if (mainContainer.scrollTop() == 0) {
									$('.page-top').addClass('active')
								} else {
									$('.page-top').removeClass('active')
								}
							});//2秒滑动到指定位置
		});

         $('.page-top').click(function() {
			var mainContainer = $('.build-top'), scrollToContainer = mainContainer
					.find('.floor:last').height()+2 ;//滚动到<div id="thisMainPanel">中类名为son-panel的最后一个div处
	
			if (mainContainer.scrollTop() <= 59) {
				scrollToContainer = 59;
			}
			//动画效果
			mainContainer
					.animate({
								scrollTop : mainContainer.scrollTop()- scrollToContainer
							},"fast",function() {

								if (mainContainer.scrollTop()+ mainContainer.height() == mainContainer[0].scrollHeight) {
									$('.page-down').addClass('active')
								} else {
									$('.page-down').removeClass('active');
								}
								if (mainContainer.scrollTop() == 0) {
									$('.page-top').addClass('active')
								} else {
									$('.page-top').removeClass('active')
								}

							});//2秒滑动到指定位置
         });
}	
function LeftRight(){
	//上下滚动
	 var mainContainer = $('.build-main'), scrollToContainer = mainContainer.find('.ElemNum:last').width();//滚动到<div id="thisMainPanel">中类名为son-panel的最后一个div处
	var uNum=$('.unit').length;
	if(uNum==1){
		$('.house-top').css({"width":"84.5%","margin-left":"7.5%"})
	}
	if(uNum>2){
		$('.page-right').addClass('active')
	}else{
		$('.page-right').removeClass('active')
	}
	$('.page-right').click(function() {
		var mainContainer = $('.build-main'), scrollToContainer = mainContainer.find('.floor:last').width() + 2;//滚动到<div id="thisMainPanel">中类名为son-panel的最后一个div处
		//动画效果
		mainContainer.animate({
			          scrollLeft : scrollToContainer+ mainContainer.scrollLeft()
						},"fast",function() {
                          if(uNum>2){
                        	  if (mainContainer.scrollLeft()>0) {
  								$('.page-left').addClass('active')
  							} else {
  								$('.page-left').removeClass('active');
  							}

  							if (mainContainer.scrollLeft() == 0) {
  								$('.page-right').addClass('active')
  							} else {
  								$('.page-right').removeClass('active')
  							}
                          }
							
						});//2秒滑动到指定位置
	});

    $('.page-left').click(function() {
		var mainContainer = $('.build-main'), scrollToContainer = mainContainer.find('.floor:last').width()+2 ;//滚动到<div id="thisMainPanel">中类名为son-panel的最后一个div处
				
		if (mainContainer.scrollLeft() <= 400) {
			scrollToContainer = 400;
		}
		//动画效果
		mainContainer
				.animate({
					scrollLeft : mainContainer.scrollLeft()- scrollToContainer
						},"fast",function() {
							 if(uNum>2){
							if (mainContainer.scrollLeft()>0) {
								$('.page-left').addClass('active')
							} else {
								$('.page-left').removeClass('active');
							}
							if (mainContainer.scrollLeft() == 0) {
								$('.page-right').addClass('active')
							} else {
								$('.page-right').removeClass('active')
							}
							}
						});//2秒滑动到指定位置
        });
}		
function house(buildingId){
    $.getJSON(ctx+'/house/ccmHouseBuildmanageUnit/findListByBuildmanageId?buildmanageId='+buildingId+'',function(data){
    	var residentialUnitArr=[];
   	    var html='';
    	if(data.length>0){
    	 for(var i in data){
    		 var a = data[i].residentialUnit;
    		 if( $.inArray(a, residentialUnitArr)==-1 && a!='bottom'){
    			 residentialUnitArr.push(a)
    		 }
    	 }
    	 residentialUnitArr = residentialUnitArr.sort(sortNumber);
	     var residentialUnitLen = residentialUnitArr.length;
	     var widthU = 400;
	     var widthB = widthU*residentialUnitLen;
    
		 html+='<div id="home">';
		 html+='<div class="homeRight" style="width: auto;  height: auto;float: left; overflow-x:auto;">';

		 var widthA = widthB;
		 if(widthA>=800){
			widthA=800;
		 }
		 html+='<div class="build" style="width:'+widthA+'px">';
		 html+='<div class="house-top">';
		 html+='<div class="house-top-beam"></div>';	
		 html+='<div class="house-top-beam"></div>';
		 html+='<div class="house-top-beam"></div>';
		 html+='<div class="house-top-beam"></div>';
		 html+='<div class="house-top-beam"></div>';
		 html+='</div>';
		 html+='<div class="build-main  clearfix" style="width:'+widthA+'px;position:relative;height: 100px;overflow: hidden;">';
		 html+='<div class="build-top  clearfix" style="width:'+widthB+'px;position:relative;height: 100px;overflow: hidden;">';
    	 for(var j in residentialUnitArr){
    		//几个单元
    		 html+='<div class="unit unit-'+residentialUnitArr[j]+'">';
 			 html+='<div class="unit-top" unitNum="'+residentialUnitArr[j]+'">';
 			 var houseArr=[];
    		 for(var x in data){
    			//找到同一单元有多少楼层
    			 if(residentialUnitArr[j]==data[x].residentialUnit){
    			    var x=data[x].x;
    				if( $.inArray(x, houseArr)==-1){
    					 houseArr.push(x)
    	    		} 
    			 }
    		 }
    		 houseArr=houseArr.sort(sortNumber);
    		 //找到同一单元同一楼层多少房屋
    		 for (var m in houseArr){
    				html+='<div class="floor ElemNum">';	
					html+='<table>';				
					html+='<tr>';
    			for(var n in data){
    				if(residentialUnitArr[j]==data[n].residentialUnit&&houseArr[m]==data[n].x){
    				    var houseType=data[n].houseType;
    					var houseColor="house-window-white";
    					if(houseType=='01'){
    						//房屋状态自住
    						houseColor="house-window-white";
    					}else if(houseType=='02'){
    						//房屋状态出租
    						houseColor="house-window2";
    					}else if(houseType=='03'){
    						//房屋状态空置
    						houseColor="house-window6";
    					}else if(houseType=='99'){
    						//房屋状态其他
    						houseColor="house-window7";
    					}else{
    						//默认
    						houseColor="house-window7";
    					}
    					html+='<td style="padding:5px">';
    					html+='<div class="floor-center" >';
    					html+='<div class="row-fluid">';
    					html+='<div style="min-height:40px" class="span6 '+houseColor+'"></div>';
    					html+='<div style="min-height:40px" class="span6 '+houseColor+'"></div>';
    					html+='</div>';
    					html+='<div class="row-fluid">';
    					html+='<div class="span12 house-master  ">';
    					html+='<a class="houseclick btn  me'+data[n].houseNum+'" href="javascript:;" style="width: 99%; height: 49%; overflow: hidden; text-overflow: ellipsis; padding: 2px 0 8px 0;" buildname="${buildName}" elemnum="'+data[n].bElemNum+'" floornum="'+data[n].floorNum+'" houseid="'+data[n].houseNum+'" pilesnum="'+data[n].bPilesNum+'" doornum="'+(data[n].doorNum||'')+'" textValue="'+residentialUnitArr[j]+'-'+(data[n].doorNum||'')+(data[n].houseName||'')+'"> ';	
   						html+=''+residentialUnitArr[j]+'-'+(data[n].doorNum||'')+(data[n].houseName||'')+' ';	
   						html+='</a>';
   						html+='</div>';
   						html+='</div>';
    					html+='</div>';
    					html+='</td>';		
    				}
    			}
    			html+='</tr>';			
				html+='</table>';		
				html+='</div>';	
    		 }
    		html+='</div>';
			html+='</div>';
    	 }
    	 html+='</div>';
    	 html+='</div>';
    	 var houseBottomArr=[];
    	 //地基
    	 for(var b in data){
    		 var z=data[b].residentialUnit;
    		 if( z=='bottom'){
    			 var x=data[b].x;
 				if( $.inArray(x, houseBottomArr)==-1){
 					houseBottomArr.push(x)
 	    		} 
    		 }
    	 }
    	 houseBottomArr = houseBottomArr.sort(sortNumber);
    	//地基
    	/*
    	 html+='<div class="unit-botom unit-top" unitNum="bottom">';
		 html+='<div  style="width:60px;position:absolute;background:#00a3e4;color:#fff;font-weight: bold;top:0;left:-60px;text-align: center; padding: 15px 0;">地下室</div>';*/

    	 if(houseBottomArr.length>0){
    		//地基
		 for (var h in houseBottomArr){
				html+='<div class="floor">';	
				html+='<table>';				
				html+='<tr>';
			for(var d in data){
				if(data[d].residentialUnit=='bottom'&&houseBottomArr[h]==data[d].x){
					 var houseType=data[n].houseType;
					var houseColor="#2CC189";
					if(houseType=='01'){
						//房屋状态自住
						houseColor="#1F8BFA";
					}else if(houseType=='02'){
						//房屋状态出租
						houseColor="#FDB733";
					}else if(houseType=='03'){
						//房屋状态空置
						houseColor="#FF7453";
					}else if(houseType=='99'){
						//房屋状态其他
						houseColor="#E84442";
					}else{
						//默认
						houseColor="#2CC189";
					}
					
					html+='<td>';
					html+='<div class="floor-center"  style="background:'+houseColor+'"  x="'+data[d].x+'" y="'+data[d].y+'" roomId="'+data[d].houseNum+'" roomName="'+data[d].doorNum+'" roomUnit="bottom">';
					html+='<span>'+(data[d].doorNum||'')+'</span>';
					html+='</div>';
					html+='</td>';		
				}
			}
			html+='</tr>';			
			html+='</table>';		
			html+='</div>';	
		 }
    	 }
    			
    		html+='</div>';	
    /*		html+='<div class="unit-num unit-botom-num">';
				html+='<table>';
				html+='<td><span>地基</span></td>';
				html+='</table>';
    		html+='</div>';	*/
    		html+='</div>';
    		html+='</div>';
    		html+='</div>';
    	}else{
    	 //没有数据时初始化
			html+='<div id="home">请构建房屋绑定数据</div>';
    	}
         $('#roomCenter').html(html)
    		var maxH = 0;
    		 //遍历最大高度赋给maxH
    		 $(".unit").each(function() {
    			 var maxHeight=$(this).height();
    		  if (maxHeight > maxH) {
    			  maxH = maxHeight;
    		  }
    		 });
    		 $(".unit").each(function() {
    			 var maxHeight=$(this).height();
    		     var _thisHeight=$(this).height()-maxH;
    		     $(this).css('bottom',_thisHeight)
    		 });
    		 TopDown();
    		 LeftRight();
	})
}
function sortNumber(a,b){
	  return a - b;
}
</script>
