<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>重点青少年管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript" src="${ctxStatic}/ccm/house/js/ccmHouseKymInfo.js"></script>
<script type="text/javascript" src="${ctxStatic}/ccm/pop/js/ccmCommon.js"></script>
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
<%--<img  src="${ctxStatic}/images/shouyedaohang.png"; class="nav-home">--%>
<%--<span class="nav-position">当前位置 ：</span><span class="nav-menu"><%=session.getAttribute("activeMenuName")%>></span><span class="nav-menu2">事件管理</span>--%>
<div class="back-list">
<div class="context" content="${ctx}"></div>
	<div id="importBox" class="hide">
		<form id="importForm" action="${ctx}/house/ccmHouseKym/import" method="post" enctype="multipart/form-data" class="form-search" style="padding-left: 20px; text-align: center;" onsubmit="loading('正在导入，请稍等...');"><br />
		<input id="uploadFile" name="file" type="file" style="width: 330px" /><br /> <br />
			<input id="btnImportTemplate"
				   class="btn btn-primary"  type="button" value="模板下载 "
				   onclick="location.href='${ctxStatic}/template/excel/kymPeopleTemplate.xlsx'"/>
		<input id="btnImportSubmit" class="btn btn-primary" type="submit" value="导  入 " />
		</form>
	</div>
	<ul class="nav nav-tabs">
		<shiro:hasPermission name="report:ccmPeopleStat:view"><li style="width: 112px;"><a href="${ctx}/report/ccmPeopleStat/statisticsPage?title=ccmPeopleStatTeenager" style="text-align: center">数据统计</a></li></shiro:hasPermission>
		<li class="active"  style="width: 112px"><a  class="nav-head" href="${ctx}/house/ccmHouseKym/">数据列表</a ></li>
		<%-- <shiro:hasPermission name="house:ccmHouseKym:edit">
			<li><a href="${ctx}/house/ccmHouseKym/form">重点青少年添加</a></li>
		</shiro:hasPermission> --%>
	</ul>
	<form:form id="searchForm" modelAttribute="ccmHouseKym" action="${ctx}/house/ccmHouseKym/" method="post" class="breadcrumb form-search clearfix">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}" />
		<ul class="ul-form pull-left">
			<li class="first-line"><label class="title-text">姓名：</label> <form:input path="name" htmlEscape="false" maxlength="50" class="input-medium" />
			</li>
			<li class="first-line"><label class="title-text">性别：</label>
				<form:select path="sex" class="input-medium">
					<form:option value="" label="全部" />
					<form:options items="${fns:getDictList('sex')}" itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</li>
			<li class="first-line"><label class="title-text">关注程度：</label>
				<form:select path="atteType" class="input-medium">
					<form:option value="" label="" />
					<form:options items="${fns:getDictList('ccm_conc_exte')}" itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select></li>

<%--			<li class="clearfix"></li>--%>
			<li class="first-line"><label class="title-text">家庭情况：</label> <form:select path="famiStat" class="input-medium">
					<form:option value="" label="全部" />
					<form:options items="${fns:getDictList('ccm_famy_stat')}" itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</li>
			<li class="first-line"><label class="title-text">是否违法犯罪：</label>
				<form:select path="delinquency" class="input-medium">
					<form:option value="" label="全部" />
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</li>
		</ul>

	<sys:message content="${message}" />
	<div class="clearfix pull-right btn-box">

			<shiro:hasPermission name="house:ccmHouseBuildmanage:edit">
				<!-- <input id="btnImport" class="btn btn-primary" type="button" value="导入" />
				<input id="btnExport" class="btn btn-primary" type="button" value="导出" />  -->
				<a href="javascript:;" id="btnImport" class="btn  btn-export "style="width: 49px;display:inline-block;float: right;">
					<i></i><span style="font-size: 12px">导入</span>
				</a>
				<a href="javascript:;" id="btnExport" class="btn btn-export"style="width: 49px;display:inline-block;float: right;">
					<i></i><span style="font-size: 12px">导出</span>
				</a>
			</shiro:hasPermission>
			<!-- 添加该input的点击方法为 return page();-->
			<!-- <input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" onclick="return page();" /> -->
			<a href="javascript:;" id="btnSubmit" class="btn btn-primary" style="width: 49px;display:inline-block;float: right;" >
				<i></i><span style="font-size: 12px">查询</span>  </a>

	</div>
	</form:form>
	<table id="contentTable" class="table table-striped table-bordered table-condensed table-gradient">
		<thead>
			<tr>
				<th>人员图片</th>
				<th>姓名</th>
				<th>公民身份号码</th>
				<th>性别</th>
				<th>联系方式</th>
				<th>关注程度</th>
				<th>人员类型</th>
				<th>家庭情况</th>
				<th>是否违法犯罪</th>
				<shiro:hasPermission name="house:ccmHouseKym:edit">
					<th>操作</th>
				</shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="ccmHouseKym">
				<tr>
					<td width="100px"><img src="${ccmHouseKym.images}" style="height:50px;" class="pimg"/></td>
					<td><a  onclick="parent.LayerDialog('${ctx}/house/ccmHouseKym/form?id=${ccmHouseKym.id}', '信息', '1200px', '900px')">${ccmHouseKym.name} </a></td>
					<td>${ccmHouseKym.ident}</td>
					<td>${fns:getDictLabel(ccmHouseKym.sex, 'sex', '')}</td>
					<td>${ccmHouseKym.telephone}</td>
					<c:if test="${ccmHouseKym.atteType eq '01'}">
						<td style='color:red'>${fns:getDictLabel(ccmHouseKym.atteType, 'ccm_conc_exte', '')}&nbsp; &nbsp; <img src="${ctxStatic}/images/atteType_red.png" /> </td>
					</c:if>
					<c:if test="${ccmHouseKym.atteType eq '02'}">
						<td style='color:orange'>${fns:getDictLabel(ccmHouseKym.atteType, 'ccm_conc_exte', '')}&nbsp; &nbsp; <img src="${ctxStatic}/images/atteType_orange.png" /> </td>
					</c:if>
					<c:if test="${ccmHouseKym.atteType eq '03'}">
						<td>${fns:getDictLabel(ccmHouseKym.atteType, 'ccm_conc_exte', '')}&nbsp; &nbsp; <img src="${ctxStatic}/images/atteType_green.png" /> </td>
					</c:if>
					<c:if test="${ccmHouseKym.atteType eq '' or empty ccmHouseKym.atteType}">
						<td>${fns:getDictLabel(ccmHouseKym.atteType, 'ccm_conc_exte', '')} </td>
					</c:if>
					<td>${fns:getDictLabel(ccmHouseKym.manType, 'ccm_delp_type', '')}</td>
					<td>${fns:getDictLabel(ccmHouseKym.famiStat, 'ccm_famy_stat', '')}</td>
					<td>${fns:getDictLabel(ccmHouseKym.delinquency, 'yes_no', '')}</td>
					<td><shiro:hasPermission name="house:ccmHouseKym:edit">
						<a class="btnList"  onclick="parent.LayerDialog('${ctx}/house/ccmHouseKym/form?id=${ccmHouseKym.id}', '信息', '1200px', '900px')" title="修改"><i class="icon-pencil"></i></a>
						<a class="btnList" href="${ctx}/house/ccmHouseKym/delete?id=${ccmHouseKym.id}" onclick="return confirmx('确认要删除该重点青少年吗？', this.href)"  title="删除"><i class="icon-remove-sign"></i></a>
						<a class="btnList" href="javascript:;" onclick="LocationOpen('${ccmHouseKym.peopleId}')" title="位置信息"><i class="icon-map-marker "></i></a>
						<a class="btnList" onclick="parent.LayerDialog('${ctx}/pop/ccmPeople/getSocialConnections?id=${ccmHouseKym.peopleId}', '社交关系', '1000px', '700px')" title="社交关系"><i class="icon-group"></i></a>
						<%-- <a class="btnList" onclick="parent.LayerDialog('${ctx}/work/ccmWorkTiming/form', '定时提醒', '700px', '500px')" title="定时提醒"><i class="icon-bell"></i></a> --%>
						</shiro:hasPermission> <shiro:hasPermission name="log:ccmLogTail:edit">
						<a class="btnList" onclick="parent.LayerDialog('${ctx}/log/ccmLogTail/list?relevance_id=${ccmHouseKym.id}&relevance_table=ccm_house_kym', '记录信息', '800px', '660px')" 
								  title="记录信息"><i class="icon-print" style="color: cornflowerblue;"></i></a>
						<a class="btnList" onclick="parent.LayerDialog('${ctx}/log/ccmLogTail/formPro?relevance_id=${ccmHouseKym.id}&relevance_table=ccm_house_kym', '添加记录', '800px', '660px')" title="添加记录"><i class="icon-plus"></i></a>
						</shiro:hasPermission></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
		<div id="outerdiv" style="position:fixed;top:0;left:0;background:rgba(0,0,0,0.7);z-index:2;width:100%;height:100%;display:none;">
			<div id="innerdiv" style="position:absolute;">
				<img id="bigimg" style="border:5px solid #fff;" src="" />
			</div>
		</div>
	<div class="pagination" style="float: right; margin-top: 12px">${page}</div>
</body>
</html>