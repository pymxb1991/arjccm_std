<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/include/head.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="renderer" content="webkit|ie-comp|ie-stand" />
	<title>社会网格化管理信息平台</title>
	
	<script src="${ctxStatic}/jquery/jquery-1.9.1.min.js"></script>
	<script src="${ctxStatic}/jquery/jquery.cookie.js" type="text/javascript"></script>
	
	<script src="${ctxStatic}/common/index/Scripts/js/echarts.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/echarts/echarts.common.min.js"></script>
	
	<link rel="stylesheet" href="${ctxStatic}/bootstrap/bootstrap3.0/css/bootstrap.min.css">
	<link rel="stylesheet" href="${ctxStatic}/common/index/css/${not empty cookie.theme.value && cookie.theme.value != 'black' ? '' : 'css_black/'}indexZongZhi.css">
	<link rel="stylesheet" href="${ctxStatic}/iconfont/iconfont.css">
	
	<script src="${ctxStatic}/bootstrap/bootstrap3.0/js/bootstrap.min.js"></script>
	
	<script type="text/javascript">
		var ctxStatic = '${ctxStatic}', ctx = '${ctx}';
		var _menuUrl="${url}";
		var _menuId="${id}";
		var $menu=$('#menu a.menu,#menu1 a.menu');
	</script>
	
	<script src="${ctxStatic}/common/index/Scripts/js/indexZongZhi.js"></script>


</head>
<body>
	<img src="${ctxStatic}/images/shouyedaohang.png"; style="width: 16px;
    height: 14px;
    margin-left: 33px;
    top: 13px;
    position: relative;">
	<%--<span class="danghang">当前位置 ：</span><span class="danghang" style="margin-left: -3px"><%=session.getAttribute("activeMenuName")%>></span><span class="danghang" style="margin-left: 0px">首页</span>&lt;%&ndash;<span class="danghang" style="margin-left: -3px"><%=session.getAttribute("firstMenuName")%>></span>&ndash;%&gt;--%>
<%--	<img src="${ctxStatic}/images/atteType_red.png" />--%>
	<div class="container-fluid">
		<div class="row" style="height: 30%;padding-right: 1%;">
			<div class="col-md-3 common" style="height: 100%;">
				<div class="center" style="background-color: white;margin-top: -30px">
					<div class="center-header zongzhi-color1">
						<a href="${ctx}/pop/ccmPeople/">实有人口</a>
					</div>
					<div class="center-main">
						<div class="col-md-6 dataDiv">
							<div class="col-md-6 labelDiv" style="padding-right: 1px">户籍信息</div>
							<div class="col-md-3 textDiv" id="CountByType10">0</div>
							<div class="col-md-2 labelDiv">人</div>
						</div>
						<div class="col-md-6 dataDiv">
							<div class="col-md-6 labelDiv" style="padding-right: 1px">流动信息</div>
							<div class="col-md-3 textDiv" id="CountByType20">0</div>
							<div class="col-md-2 labelDiv">人</div>
						</div>
						<div class="hrMargin"></div>
						<div class="hrLine"></div>
						<div class="col-md-6 dataDiv">
							<div class="col-md-6 labelDiv" style="padding-right: 1px">境外信息</div>
							<div class="col-md-3 textDiv" id="CountByType30">0</div>
							<div class="col-md-2 labelDiv">人</div>
						</div>
						<div class="col-md-6 dataDiv">
							<div class="col-md-6 labelDiv" style="padding-right: 1px">未落户</div>
							<div class="col-md-3 textDiv" id="CountByType40">0</div>
							<div class="col-md-2 labelDiv">人</div>
						</div>
						<div class="hrMargin"></div>
						<div class="hrLine"></div>
						<div class="col-md-6 dataDiv">
							<div class="col-md-6 labelDiv" style="padding-right: 1px">人户一致</div>
							<div class="col-md-3 textDiv" id="uniformlogo01">0</div>
							<div class="col-md-2 labelDiv">人</div>
						</div>
						<div class="col-md-6 dataDiv">
							<div class="col-md-6 labelDiv" style="padding-right: 1px">人户分离</div>
							<div class="col-md-3 textDiv" id="uniformlogo02">0</div>
							<div class="col-md-2 labelDiv">人</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-3 common" style="height: 100%;">
				<div class="center" style="background-color: white;margin-top: -30px">
					<div class="center-header zongzhi-color2">
						<a href="${ctx}/pop/ccmPopTenant">实有房屋</a>
					</div>
					<div class="center-main">
						<div class="col-md-6 dataDiv">
							<div class="col-md-6 labelDiv">自住房</div>
							<div class="col-md-3 textDiv" id="houseType01">0</div>
							<div class="col-md-2 labelDiv">个</div>
						</div>
						<div class="col-md-6 dataDiv">
							<div class="col-md-6 labelDiv">空置房</div>
							<div class="col-md-3 textDiv" id="houseType03">0</div>
							<div class="col-md-2 labelDiv">个</div>
						</div>
						<div class="hrMargin"></div>
						<div class="hrLine"></div>
						<div class="col-md-6 dataDiv">
							<div class="col-md-6 labelDiv">出租房</div>
							<div class="col-md-3 textDiv" id="houseType02">0</div>
							<div class="col-md-2 labelDiv">个</div>
						</div>
						<div class="col-md-6 dataDiv">
							<div class="col-md-6 labelDiv">其他</div>
							<div class="col-md-3 textDiv" id="houseType99">0</div>
							<div class="col-md-2 labelDiv">个</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-3 common" style="height: 100%;">
				<div class="center" style="background-color: white;margin-top: -30px">
					<div class="center-header zongzhi-color3">
						<a href="${ctx}/house/ccmHouseSchoolrim/list">社会治安</a>
					</div>
					<div class="center-main">
						<div class="col-md-6 dataDiv">
							<div class="col-md-6 labelDiv">学校</div>
							<div class="col-md-3 textDiv" id="schoolNumber">0</div>
							<div class="col-md-2 labelDiv">个</div>
						</div>
						<div class="col-md-6 dataDiv">
							<div class="col-md-6 labelDiv" style="padding-right: 1px">物流安全</div>
							<div class="col-md-3 textDiv" id="findKeyPlace1">0</div>
							<div class="col-md-2 labelDiv">个</div>
						</div>
						<div class="hrMargin"></div>
						<div class="hrLine"></div>
						<div class="col-md-6 dataDiv">
							<div class="col-md-7 labelDiv" style="padding-right: 1px">安全生产重点</div>
							<div class="col-md-2 textDiv" id="findKeyPlace2">0</div>
							<div class="col-md-2 labelDiv">个</div>
						</div>
						<div class="col-md-6 dataDiv">
							<div class="col-md-6 labelDiv" style="padding-right: 1px">消防重点</div>
							<div class="col-md-3 textDiv" id="findKeyPlace3">0</div>
							<div class="col-md-2 labelDiv">个</div>
						</div>
						<div class="hrMargin"></div>
						<div class="hrLine"></div>
						<div class="col-md-6 dataDiv">
							<div class="col-md-6 labelDiv" style="padding-right: 1px">治安重点</div>
							<div class="col-md-3 textDiv" id="findKeyPlace4">0</div>
							<div class="col-md-2 labelDiv">个</div>
						</div>
						<div class="col-md-6 dataDiv">
							<div class="col-md-6 labelDiv" style="padding-right: 1px">其他重点</div>
							<div class="col-md-3 textDiv" id="findKeyPlace5">0</div>
							<div class="col-md-2 labelDiv">个</div>
						</div>
						<div class="hrMargin"></div>
						<div class="hrLine"></div>
						<div class="col-md-6 dataDiv">
							<div class="col-md-7 labelDiv" style="padding-right: 1px">人员密集重点</div>
							<div class="col-md-2 textDiv" id="findKeyPlace6">0</div>
							<div class="col-md-2 labelDiv">个</div>
						</div>
						<div class="col-md-6 dataDiv">
							<div class="col-md-8 labelDiv" style="padding-right: 1px">场所特业服务重点</div>
							<div class="col-md-1 textDiv" id="findKeyPlace7">0</div>
							<div class="col-md-2 labelDiv">个</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-3 common" style="height: 100%;">
				<div class="center" style="background-color: white;margin-top: -30px">
					<div class="center-header zongzhi-color4">
						<a href="${ctx}/report/ccmReportOthers/organization">两新组织</a>
					</div>
					<div class="center-main">
						<div class="col-md-6 dataDiv">
							<div class="col-md-6 labelDiv" style="padding-right: 1px">法人企业</div>
							<div class="col-md-3 textDiv" id="compType01">0</div>
							<div class="col-md-2 labelDiv">个</div>
						</div>
						<div class="col-md-6 dataDiv">
							<div class="col-md-6 labelDiv" style="padding-right: 1px">个体工商户</div>
							<div class="col-md-3 textDiv" id="compType02">0</div>
							<div class="col-md-2 labelDiv">个</div>
						</div>
						<div class="hrMargin"></div>
						<div class="hrLine"></div>
						<div class="col-md-6 dataDiv">
							<div class="col-md-6 labelDiv" style="padding-right: 1px">机关单位</div>
							<div class="col-md-3 textDiv" id="compType03">0</div>
							<div class="col-md-2 labelDiv">个</div>
						</div>
						<div class="col-md-6 dataDiv">
							<div class="col-md-6 labelDiv" style="padding-right: 1px">事业单位</div>
							<div class="col-md-3 textDiv" id="compType04">0</div>
							<div class="col-md-2 labelDiv">个</div>
						</div>
						<div class="hrMargin"></div>
						<div class="hrLine"></div>
						<div class="col-md-6 dataDiv">
							<div class="col-md-6 labelDiv" style="padding-right: 1px">社团组织</div>
							<div class="col-md-3 textDiv" id="compType99">0</div>
							<div class="col-md-2 labelDiv">个</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="row" style="height: 33%;padding-right: 1%;">
			<div class="col-md-6 common" style="height: 100%">
				<div class="center" style="width:${cookie.theme.value eq 'gradient' ? '96%' : '100%'};height: ${cookie.theme.value eq 'gradient' ? '301px' : '305px'}; top: ${cookie.theme.value eq 'gradient' ? '-13px' : '0px'}; position: relative; background-color: white;margin-top: -35px" >
					<div class="center-header center-echarts-name" style="padding-left:3%;">
						<div class="echartsLabel">人口统计情况</div>
						<ul class="nav nav-tabs" id="PeopleStatistics">
							<li class="active" title="户籍" type="0"><a href="javascript:;">户籍人口</a></li>
							<li title="流动" type="2"><a 	href="javascript:;">流动人口</a></li>
							<li title="境外" type="1"><a href="javascript:;">境外人口</a></li>
							<li title="未落户" type="10"><a href="javascript:;">未落户人口</a></li>
						</ul>
					</div>
					<div class="center-main">
						<div id="CaiJi" style="width:100%;height:220px;"></div>
					</div>
				</div>
			</div>
			<div class="col-md-6 common" style="height: 100%">
				<div class="center" style="width:${cookie.theme.value eq 'gradient' ? '96%' : '100%'};height: ${cookie.theme.value eq 'gradient' ? '301px' : '305px'}; top: ${cookie.theme.value eq 'gradient' ? '-13px' : '0px'}; position: relative; background-color: white;margin-top: -35px">
					<div class="center-header center-echarts-name" style="padding-left:3%;">
						<div class="echartsLabel">矛盾排查处理</div>
					</div>
					<div class="center-main">
						<div id="MaoDun" style="width:100%;height:220px;"></div>
					</div>
				</div>
			</div>
		</div>
		<div class="row" style="height: 30%;padding-right: 1%;">
			<div class="col-md-3 common" style="height: 100%;">
				<div class="center" style="top: -20px; position: relative; background-color: white">
					<div class="center-header zongzhi-color1">
						<a href="${ctx}/house/ccmHouseKym">重点青年</a>
					</div>
					<div class="center-main">
						<div class="col-md-6 dataDiv">
							<div class="col-md-6 labelDiv" style="padding-right: 1px">闲散青少年</div>
							<div class="col-md-3 textDiv" id="PopKey01">0</div>
							<div class="col-md-2 labelDiv">人</div>
						</div>
						<div class="col-md-6 dataDiv">
							<div class="col-md-7 labelDiv" style="padding-right: 1px">流浪乞讨未成年</div>
							<div class="col-md-2 textDiv" id="PopKey03">0</div>
							<div class="col-md-2 labelDiv">人</div>
						</div>
						<div class="hrMargin"></div>
						<div class="hrLine"></div>
						<div class="col-md-6 dataDiv">
							<div class="col-md-8 labelDiv">农村留守儿童</div>
							<div class="col-md-1 textDiv" id="PopKey05">0</div>
							<div class="col-md-2 labelDiv">人</div>
						</div>
						<div class="col-md-6 dataDiv">
							<div class="col-md-6 labelDiv">其他</div>
							<div class="col-md-3 textDiv" id="PopKey99">0</div>
							<div class="col-md-2 labelDiv">人</div>
						</div>
						<div class="hrMargin"></div>
						<div class="hrLine"></div>
						<div class="col-md-12 dataDiv">
							<div class="col-md-6 labelDiv">服刑人员未成年子女</div>
							<div class="col-md-4 textDiv"style="margin-left: 20px" id="PopKey04">0</div>
							<div class="col-md-1 labelDiv">人</div>
						</div>
						<div class="hrMargin"></div>
						<div class="hrLine"></div>
						<div class="col-md-12 dataDiv">
							<div class="col-md-7 labelDiv">有不良行为或严重不良行为青少年</div>
							<div class="col-md-3 textDiv" style="margin-left: 20px"  id="PopKey02">0</div>
							<div class="col-md-1 labelDiv">人</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-3 common" style="height: 100%;">
				<div class="center" style="top: -20px; position: relative; background-color: white">
					<div class="center-header zongzhi-color2">
						<a href="${ctx}/pop/ccmPopTenant/list/rent">出租房屋</a>
					</div>
					<div class="center-main">
						<div class="col-md-6 dataDiv">
							<div class="col-md-6 labelDiv">宿舍</div>
							<div class="col-md-3 textDiv" id="HousePrup01">0</div>
							<div class="col-md-2 labelDiv">个</div>
						</div>
						<div class="col-md-6 dataDiv">
							<div class="col-md-6 labelDiv">住房</div>
							<div class="col-md-3 textDiv" id="HousePrup02">0</div>
							<div class="col-md-2 labelDiv">个</div>
						</div>
						<div class="hrMargin"></div>
						<div class="hrLine"></div>
						<div class="col-md-6 dataDiv">
							<div class="col-md-6 labelDiv">商铺</div>
							<div class="col-md-3 textDiv" id="HousePrup03">0</div>
							<div class="col-md-2 labelDiv">个</div>
						</div>
						<div class="col-md-6 dataDiv">
							<div class="col-md-6 labelDiv">办公</div>
							<div class="col-md-3 textDiv" id="HousePrup04">0</div>
							<div class="col-md-2 labelDiv">个</div>
						</div>
						<div class="hrMargin"></div>
						<div class="hrLine"></div>
						<div class="col-md-6 dataDiv">
							<div class="col-md-6 labelDiv">仓库</div>
							<div class="col-md-3 textDiv" id="HousePrup05">0</div>
							<div class="col-md-2 labelDiv">个</div>
						</div>
						<div class="col-md-6 dataDiv">
							<div class="col-md-6 labelDiv">厂房</div>
							<div class="col-md-3 textDiv" id="HousePrup06">0</div>
							<div class="col-md-2 labelDiv">个</div>
						</div>
						<div class="hrMargin"></div>
						<div class="hrLine"></div>
						<div class="col-md-6 dataDiv">
							<div class="col-md-6 labelDiv">其他</div>
							<div class="col-md-3 textDiv" id="HousePrup99">0</div>
							<div class="col-md-2 labelDiv">个</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-3 common" style="height: 100%;">
				<div class="center" style="top: -20px; position: relative;background-color: white">
					<div class="center-header zongzhi-color3">
						<a href="${ctx}/event/ccmEventIncident/list">事件</a>
					</div>
					<div class="center-main">
						<div class="col-md-6 dataDiv">
							<div class="col-md-6 labelDiv">全部</div>
							<div class="col-md-3 textDiv" id="DisputesStatusAll">0</div>
							<div class="col-md-2 labelDiv">个</div>
						</div>
						<div class="col-md-6 dataDiv">
							<div class="col-md-6 labelDiv">未完结</div>
							<div class="col-md-3 textDiv" id="DisputesStatus0">0</div>
							<div class="col-md-2 labelDiv">个</div>
						</div>
						<div class="hrMargin"></div>
						<div class="hrLine"></div>
						<div class="col-md-6 dataDiv">
							<div class="col-md-6 labelDiv">完结归档</div>
							<div class="col-md-3 textDiv" id="DisputesStatus1">0</div>
							<div class="col-md-2 labelDiv">个</div>
						</div>
						<%--						<div class="col-md-6 dataDiv">--%>
						<%--							<div class="col-md-6 labelDiv">已处理</div>--%>
						<%--							<div class="col-md-4 textDiv" id="DisputesStatus2">0</div>--%>
						<%--							<div class="col-md-2 labelDiv">个</div>--%>
						<%--						</div>--%>
						<%--						<div class="hrMargin"></div>--%>
						<%--						<div class="hrLine"></div>--%>
						<%--						<div class="col-md-6 dataDiv">--%>
						<%--							<div class="col-md-6 labelDiv">已通过</div>--%>
						<%--							<div class="col-md-4 textDiv" id="DisputesStatus3">0</div>--%>
						<%--							<div class="col-md-2 labelDiv">个</div>--%>
						<%--						</div>--%>
						<%--						<div class="col-md-6 dataDiv">--%>
						<%--							<div class="col-md-6 labelDiv">已拒绝</div>--%>
						<%--							<div class="col-md-4 textDiv" id="DisputesStatus4">0</div>--%>
						<%--							<div class="col-md-2 labelDiv">个</div>--%>
						<%--						</div>--%>
					</div>
				</div>
			</div>
			<div class="col-md-3 common" style="height: 100%;">
				<div class="center" style="top: -20px; position: relative; background-color: white">
					<div class="center-header zongzhi-color4">
						<a href="${ctx}/report/ccmReportOthers/houseAndBuild">地址库</a>
					</div>
					<div class="center-main">
						<div class="col-md-6 dataDiv">
							<div class="col-md-6 labelDiv">建筑物</div>
							<div class="col-md-3 textDiv" id="construction">0</div>
							<div class="col-md-2 labelDiv">个</div>
						</div>
						<div class="col-md-6 dataDiv">
							<div class="col-md-6 labelDiv">街路巷</div>
							<div class="col-md-3 textDiv" id="streetRoadLane">0</div>
							<div class="col-md-2 labelDiv">个</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="row" style="height: 36%;padding-right: 1%;margin-bottom: 4%;display:none;">
			<div class="col-md-3 common" style="height: 100%;">
				<div class="center">
					<div class="center-header zongzhi-color1">
						<a href="${ctx}/line/ccmLineProtect">护路护线</a>
					</div>
					<div class="center-main">
						<div class="col-md-12 dataDiv">
							<div class="col-md-6 labelDiv">线路信息</div>
							<div class="col-md-5 textDiv" id="findCountLine">0</div>
							<div class="col-md-1 labelDiv">个</div>
						</div>
						<div class="hrMargin"></div>
						<div class="hrLine"></div>
						<div class="col-md-12 dataDiv">
							<div class="col-md-6 labelDiv">涉及线、路案件</div>
							<div class="col-md-5 textDiv" id="findCountLineProtect">0</div>
							<div class="col-md-1 labelDiv">个</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-3 common" style="height: 100%;">
				<div class="center">
					<div class="center-header zongzhi-color2">
						<a href="${ctx}/house/ccmHouseSchoolrim/popIndex">校园安全</a>
					</div>
					<div class="center-main">
						<div class="col-md-6 dataDiv">
							<div class="col-md-6 labelDiv">学校信息</div>
							<div class="col-md-4 textDiv" id="findCountSchool">0</div>
							<div class="col-md-2 labelDiv">个</div>
						</div>
						<div class="col-md-6 dataDiv">
							<div class="col-md-6 labelDiv">师生安全</div>
							<div class="col-md-4 textDiv" id="findCountSchoolPeople">0</div>
							<div class="col-md-2 labelDiv">个</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-3 common" style="height: 100%;">
				<div class="center">
					<div class="center-header zongzhi-color3">
						<a href="${ctx}/house/ccmHouseAreainfor">机构队伍</a>
					</div>
					<div class="center-main">
						<div class="col-md-6 dataDiv">
							<div class="col-md-6 labelDiv">综治机构</div>
							<div class="col-md-4 textDiv" id="OfficeCount">0</div>
							<div class="col-md-2 labelDiv">个</div>
						</div>
						<div class="col-md-6 dataDiv">
							<div class="col-md-6 labelDiv">机构队伍</div>
							<div class="col-md-4 textDiv" id="TeamCoun">0</div>
							<div class="col-md-2 labelDiv">个</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-3 common" style="height: 100%;">
				<div class="center">
					<div class="center-header center-echarts-name zongzhi-color4">
						<a href="${ctx}/house/ccmHouseKym/list" style="margin-left: 1%;">重点人员</a>
						<ul class="nav nav-tabs" id="peopleKym">
							<li class="active" title="治安重点" type="1">
								<a href="javascript:;">治安重点</a>
							</li>
							<li title="综治重点" type="2">
								<a href="javascript:;">综治重点</a>
							</li>
						</ul>
					</div>
					<div class="center-main" id="securityPeopleKym">
						<div class="col-md-6 dataDiv">
							<div class="col-md-6 labelDiv">吸毒人员</div>
							<div class="col-md-4 textDiv" id="drugs">0</div>
							<div class="col-md-2 labelDiv">人</div>
						</div>
						<div class="col-md-6 dataDiv">
							<div class="col-md-8 labelDiv">非正常上访人员</div>
							<div class="col-md-2 textDiv" id="petition">0</div>
							<div class="col-md-2 labelDiv">人</div>
						</div>
						<div class="hrMargin"></div>
						<div class="hrLine"></div>
						<div class="col-md-6 dataDiv">
							<div class="col-md-6 labelDiv">肇事肇祸精神病人员</div>
							<div class="col-md-4 textDiv" id="psychogeny">0</div>
							<div class="col-md-2 labelDiv">人</div>
						</div>
						<div class="col-md-6 dataDiv">
							<div class="col-md-8 labelDiv">严重刑事犯罪活动嫌疑人员</div>
							<div class="col-md-2 textDiv" id="serious">0</div>
							<div class="col-md-2 labelDiv">人</div>
						</div>
						<div class="hrMargin"></div>
						<div class="hrLine"></div>
						<div class="col-md-6 dataDiv">
							<div class="col-md-6 labelDiv">在逃人员</div>
							<div class="col-md-4 textDiv" id="escape">0</div>
							<div class="col-md-2 labelDiv">人</div>
						</div>
						<div class="col-md-6 dataDiv">
							<div class="col-md-6 labelDiv">矛盾纠纷</div>
							<div class="col-md-4 textDiv" id="dispute">0</div>
							<div class="col-md-2 labelDiv">人</div>
						</div>
						<div class="hrMargin"></div>
						<div class="hrLine"></div>
						<div class="col-md-12 dataDiv">
							<div class="col-md-6 labelDiv">危害国家安全活动嫌疑人员</div>
							<div class="col-md-5 textDiv" id="security">0</div>
							<div class="col-md-1 labelDiv">人</div>
						</div>
						<div class="hrMargin"></div>
						<div class="hrLine"></div>
						<div class="col-md-12 dataDiv">
							<div class="col-md-6 labelDiv">故意违法刑释不足5年人员</div>
							<div class="col-md-5 textDiv" id="illegal">0</div>
							<div class="col-md-1 labelDiv">人</div>
						</div>
					</div>
					<div class="center-main" id="allPeopleKym" style="display:none;">
						<div class="col-md-12 dataDiv">
							<div class="col-md-6 labelDiv">刑释教解人员</div>
							<div class="col-md-5 textDiv" id="release">0</div>
							<div class="col-md-1 labelDiv">人</div>
						</div>
						<div class="hrMargin"></div>
						<div class="hrLine"></div>
						<div class="col-md-12 dataDiv">
							<div class="col-md-6 labelDiv">社区矫正人员</div>
							<div class="col-md-5 textDiv" id="rectification">0</div>
							<div class="col-md-1 labelDiv">人</div>
						</div>
						<div class="hrMargin"></div>
						<div class="hrLine"></div>
						<div class="col-md-12 dataDiv">
							<div class="col-md-6 labelDiv">艾滋病危险人员</div>
							<div class="col-md-5 textDiv" id="aids">0</div>
							<div class="col-md-1 labelDiv">人</div>
						</div>
						<div class="hrMargin"></div>
						<div class="hrLine"></div>
						<div class="col-md-12 dataDiv">
							<div class="col-md-6 labelDiv">涉教人员</div>
							<div class="col-md-5 textDiv" id="heresy">0</div>
							<div class="col-md-1 labelDiv">人</div>
						</div>
						<div class="hrMargin"></div>
						<div class="hrLine"></div>
						<div class="col-md-12 dataDiv">
							<div class="col-md-6 labelDiv">危险品从业人员</div>
							<div class="col-md-5 textDiv" id="dangerous">0</div>
							<div class="col-md-1 labelDiv">人</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>