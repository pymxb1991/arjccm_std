<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<style>
.radio-div {
	margin-top: 7px;
	margin-left: 10px;
}

.houseclick.btn {
	background: #BDC7C7;
}
</style>
<div style="display: none" class="curBuildName">楼栋名称: ${buildName}
</div>


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
					<div class="page-left active page-icon">
						<div></div>
					</div>
				</div>
				<div class="span2">
					<div class="page-right active page-icon">
						<div></div>
					</div>
				</div>
			</div>
			<!-- 分页 -->

			<!-- 房子 -->
			<div class="page row-fluid" style="margin-left: 10px;">
				<div class="span1">
					<div class="page-down  page-icon"></div>
				</div>
				<div class="container houseView span10" style="margin-left: 0">
					<div class="row-fluid">
						<div class="span12 house">
							<div class="house-top">
								<div class="house-top-beam"></div>
								<div class="house-top-beam"></div>
								<div class="house-top-beam"></div>
								<div class="house-top-beam"></div>
								<div class="house-top-beam"></div>
							</div>
							<div class="house-center NumView" MaxNum="${maxelemnum-5}"
								Num="0" style="padding-right: 4px;">
								<c:forEach var="elemnum" begin="0" end="${maxelemnum-1}"
									step="4">
									<c:forEach items="${listCcmTent}" varStatus="Tentcount"
										var="TentMap">
										<div
											class="row-fluid ElemNum Elem${elemnum} <c:if test="${elemnum>0}">hide</c:if> <c:if test="${elemnum eq 0}">active</c:if> ">
											<c:forEach items="${TentMap}" var="entry" begin="${elemnum}"
												end="${elemnum+3}" step="1">
												<div class="span3 house-number" style="height: 69px">
													<%--  
													<c:if test="${entry.value.buildDoorNum=='3'}">
													--%>
													<div class="house-number-center">
														<div class="row-fluid">
															<c:if
																test="${empty entry.value.id || (entry.value.houseType=='') }">
																<div class="span6 house-window7"
																	style="min-height: 40px;"></div>
																<!-- 暂无信息 -->
																<div class="span6 house-window7"
																	style="min-height: 40px;"></div>
															</c:if>
															<c:if test="${entry.value.houseType=='03'}">
																<div class="span6 house-window6"
																	style="min-height: 40px;"></div>
																<!-- 空置03 -->
																<div class="span6 house-window6"
																	style="min-height: 40px;"></div>
															</c:if>
															<c:if test="${entry.value.houseType=='01' }">
																<div class="span6 house-window-white"
																	style="min-height: 40px;"></div>
																<!-- 自住01 -->
																<div class="span6 house-window-white"
																	style="min-height: 40px;"></div>
															</c:if>
															<c:if test="${entry.value.houseType=='02' }">
																<div class="span6 house-window2"
																	style="min-height: 40px;"></div>
																<!-- 出租02 -->
																<div class="span6 house-window2"
																	style="min-height: 40px;"></div>
															</c:if>

														</div>
														<div class="row-fluid">
															<div class="span12 house-master  ">

																<a
																	class="houseclick btn  me${entry.value.id}
												 					<%--<c:forEach items="${SpeIDList}" var="SPE" >
																	<c:if test="${  SPE eq entry.value.id}">special</c:if></c:forEach>  "--%>
																	href="
																	###"
																	style="width: 99%; height: 49%; overflow: hidden; text-overflow: ellipsis; padding: 2px 0 8px 0;"
																	buildName="${buildName}" elemNum="${elemNum}"
																	floorNum="${entry.value.floorNum}"
																	houseId="${entry.value.id}" pilesNum="${pilesNum}"
																	doorNum="${entry.value.doorNum}"> <%-- ${pilesNum-Tentcount.index} 层  --%>
																	<c:if test="${!empty entry.value.id}">${entry.value.buildDoorNum}-${entry.value.doorNum}${entry.value.houseName} </c:if>
																	<c:if test="${empty entry.value.id}">暂无信息</c:if>
																</a>

															</div>
														</div>
													</div>
													<%--  
													</c:if>
													--%>
												</div>

											</c:forEach>
										</div>
									</c:forEach>
								</c:forEach>
							</div>
						</div>
					</div>
				</div>
				<div class="span1">
					<div class="page-top  page-icon"></div>
				</div>
			</div>
			<!-- 房子 -->
		</div>
		<div class="span3" style="margin-left: 0">
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

										<!-- 		<div class="row-fluid " style="border-bottom: 1px solid #b6d1d6; height: 32px;">
									<div class="span6 lineght30">计生重点</div>
									<div class="span3">
										<div class="type-pop7" style="background:#800000"></div>
									</div>
									<div class="span3 lineght30">
										<div class="checkbox checkbox-success radio-div">
											<input type="checkbox" value="21" class="type-input"
												name="repairId"  onclick="selectSingle('repairId')" id="jisheng" /> <label for="jisheng"></label>
										</div>
									</div>
								</div> -->


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


										<!-- <div class="row-fluid " style="height: 32px;">
						
									<div class="span6 lineght30"></div>
									<div class="span3 lineght30">全不选</div>
									<div class="span3 lineght30">
										<div class="checkbox checkbox-success radio-div">
											<input type="checkbox" value="0" class="type-input"
												name="repairId"  onclick="selectSingle('repairId')" id="All" /> <label for="All"></label>
										</div>
									</div>
								</div> -->

						</div>
						<!-- <div class="row-fluid tab-center  speHouse"
							style="margin-top: 10px">
							<div class="span12"
								style="height: 465px; overflow-y: auto; overflow-x: hidden;">
							
							<div class="row-fluid "
									style="border-bottom: 1px solid #b6d1d6; height: 32px;">
									<div class="span6 lineght30">重性精神病人员</div>
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
									<div class="span6 lineght30">危害国家安全活动嫌疑人员</div>
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
									<div class="span6 lineght30">严重刑事犯罪嫌疑人员</div>
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
									<div class="span6 lineght30">非正常上访人员</div>
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
									<div class="span6 lineght30">刑满释放不足5年人员</div>
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
									<div class="span6 lineght30">矛盾纠纷激化人员</div>
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
						</div> -->
						<!-- 特殊人群 -->

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
		// 将名称添加导航栏中
		//$(".houseView").parents("#myModal").find(".jbox-title").html(
		//		$(".curBuildName").text());
		// 拉高 整个模拟框的位置
		//$(".houseView").parents("#myModal").css("top", "2%");
		// 添加点击事件
		var myColor = new Array();
		// 基础颜色值
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
				$
						.getJSON(
								"${ctx}/sys/map/getHousesSpeMore?id=${buildId}&type="
										+ colorType,
								function(data) {
									var len = data.length;
									var colorArrLen = colorArr.length;
									// 清除背景颜色 background: #fff;
									$(".houseclick.btn").css("background", "");
									if (len > 0) {
										for (var i = 0; i < len; i++) {
											for (var j = 0; j < colorArrLen; j++) {
												if (data[i]["value"
														+ colorArr[j] + ""] == "1") {
													// 为数据添加相应的值
													$(".me" + data[i].type)
															.css(
																	"background",
																	myColor[colorArr[j]]);
												}
											}

										}
									}
								});
			} else {
				// 清除背景颜色 background: #fff;
				$(".houseclick.btn").css("background", "");
			}
		}

		$(".speHouse :checkbox").change(function() {

			/*  var colorType="";
			 var colorArr=[];
			    $("input[name='repairId']:checked").each(function(){
			    	colorType+=$(this).val()+',';
			    	colorArr.push($(this).val())
			    });
				// 查询指定的颜色
			    colorType=colorType.substring(0,colorType.length-1)
			    
			    if(colorType!=""){
			    	
					// 获取数据
					$.getJSON("${ctx}/sys/map/getHousesSpeMore?id=${buildId}&type="+colorType, function(data) {
						var len=data.length;
						var colorArrLen=colorArr.length;
						// 清除背景颜色 background: #fff;
						$(".houseclick.btn").css("background", "");
						if(len>0){
							for(var i=0;i<len;i++){
								for(var j=0;j<colorArrLen;j++){
									if(data[i]["value"+colorArr[j]+""]=="1"){
										// 为数据添加相应的值
										$(".me" + data[i].type).css("background",
												myColor[colorArr[j]]);
									}
								}
								
								
							}
						}
					});
			    }else{
			    	// 清除背景颜色 background: #fff;
					$(".houseclick.btn").css("background", "");
			    } */

		});
		// 添加点击事件 转换房屋与 人员类型的状态
		$('.tab-switch li').click(
				function() {
					var num = $(".tab-switch li").index(this);
					$(this).addClass('active').siblings().removeClass('active')
					$(".tab-center").eq(num).addClass('active').siblings(
							'.tab-center').removeClass('active');
				});

		//上下滚动

		var mainContainer = $('.house-center'), scrollToContainer = mainContainer
				.find('.ElemNum:last').height();//滚动到<div id="thisMainPanel">中类名为son-panel的最后一个div处
		if (mainContainer.scrollTop() + mainContainer.height() == mainContainer[0].scrollHeight
				|| mainContainer[0].scrollHeight == 0) {

			$('.page-down').addClass('active')
		} else {
			$('.page-down').removeClass('active');
		}
		if (mainContainer.scrollTop() == 0) {
			$('.page-top').addClass('active')
		} else {
			$('.page-top').removeClass('active')
		}
		$('.page-down')
				.click(
						function() {

							var mainContainer = $('.house-center'), scrollToContainer = mainContainer
									.find('.ElemNum:last').height() + 2;//滚动到<div id="thisMainPanel">中类名为son-panel的最后一个div处
							//动画效果
							mainContainer
									.animate(
											{
												scrollTop : scrollToContainer
														+ mainContainer
																.scrollTop()
											},
											"fast",
											function() {

												console.log(mainContainer
														.scrollTop())
												if (mainContainer.scrollTop()
														+ mainContainer
																.height() == mainContainer[0].scrollHeight) {
													$('.page-down').addClass(
															'active')
												} else {
													$('.page-down')
															.removeClass(
																	'active');
												}

												if (mainContainer.scrollTop() == 0) {
													$('.page-top').addClass(
															'active')
												} else {
													$('.page-top').removeClass(
															'active')
												}
											});//2秒滑动到指定位置
						});

		$('.page-top')
				.click(
						function() {

							var mainContainer = $('.house-center'), scrollToContainer = mainContainer
									.find('.ElemNum:last').height() - 1;//滚动到<div id="thisMainPanel">中类名为son-panel的最后一个div处
							if (mainContainer.scrollTop() <= 59) {
								scrollToContainer = 59;
							}
							//动画效果
							mainContainer
									.animate(
											{
												scrollTop : mainContainer
														.scrollTop()
														- scrollToContainer
											},
											"fast",
											function() {
												console.log(mainContainer
														.scrollTop())
												if (mainContainer.scrollTop()
														+ mainContainer
																.height() == mainContainer[0].scrollHeight) {
													$('.page-down').addClass(
															'active')
												} else {
													$('.page-down')
															.removeClass(
																	'active');
												}
												if (mainContainer.scrollTop() == 0) {
													$('.page-top').addClass(
															'active')
												} else {
													$('.page-top').removeClass(
															'active')
												}

											});//2秒滑动到指定位置

						});

	})
</script>
