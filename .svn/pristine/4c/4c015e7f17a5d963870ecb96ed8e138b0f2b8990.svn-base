<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>学校管理</title>
	<meta name="decorator" content="default"/>
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
<body class="body">
<%--<img  src="${ctxStatic}/images/shouyedaohang.png"; class="nav-home">--%>
<%--<span class="nav-position">当前位置 ：</span><span class="nav-menu"><%=session.getAttribute("activeMenuName")%>></span><span class="nav-menu2">校园安全</span>--%>
<ul class="back-list">
	<ul class="nav nav-tabs">
		<li class="active" style="width: 112px"><a class="nav-head" href="${ctx}/house/ccmHouseSchoolrim/list">学校列表</a></li>
		<shiro:hasPermission name="house:ccmHouseSchoolrim:edit"><li style="width: 112px"><a href="${ctx}/house/ccmHouseSchoolrim/form" style="text-align: center">学校添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="ccmHouseSchoolrim" action="${ctx}/house/ccmHouseSchoolrim/list" method="post" class="breadcrumb form-search clearfix">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form pull-left">
			<li class="first-line"><label class="title-text">学校名称：</label>
				<form:input path="schoolName" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li class="first-line"><label class="title-text">所属社区：</label>
				<sys:treeselect id="area" name="area.id" value="${ccmHouseSchoolrim.area.id}" labelName="area.name" labelValue="${ccmHouseSchoolrim.area.name}" title="区域" url="/tree/ccmTree/treeDataArea?type=6" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
			</li>					
			<li class="first-line"><label class="title-text">学校办学类型：</label>
				<form:select path="schoolType" class="input-medium">
					<form:option value="" label="全部" />
					<form:options items="${fns:getDictList('ccm_schol_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>		
			<li class="first-line"><label class="title-text">学校地址：</label>
				<form:input path="schoolAdd" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<!-- <li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li> -->

<%--			<li class="clearfix"></li>--%>
		</ul>


<div class="clearfix pull-right btn-box">

		<a href="javascript:;" id="btnSubmit" class="btn btn-primary"  style="width: 49px;display:inline-block;float: right;">
			<i></i><span style="font-size: 12px">查询</span>  </a>

</div>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed table-gradient">
		<thead>
			<tr>
				<th>学校图片</th>
				<th>学校名称</th>
				<th>所属社区</th>
				<th>学校办学类型</th>
				<th>学校地址</th>
				<th>校长</th>
				<th>校长联系方式</th>
				<shiro:hasPermission name="house:ccmHouseSchoolrim:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="ccmHouseSchoolrim">
			<tr>
				<td width="150px"><img src="${ccmHouseSchoolrim.images}"  class="pic-size pimg"/></td>
				<td><a href="${ctx}/house/ccmHouseSchoolrim/form?id=${ccmHouseSchoolrim.id}">${ccmHouseSchoolrim.schoolName}</a></td>
				<td>${ccmHouseSchoolrim.area.name}</td>
				<td>${fns:getDictLabel(ccmHouseSchoolrim.schoolType, 'ccm_schol_type', '')}</td>
				<td>${ccmHouseSchoolrim.schoolAdd}</td>
				<td>${ccmHouseSchoolrim.headName}</td>
				<td>${ccmHouseSchoolrim.headTl}</td>
				<td><shiro:hasPermission name="house:ccmHouseSchoolrim:edit">
    				<a class="btnList" href="${ctx}/house/ccmHouseSchoolrim/form?id=${ccmHouseSchoolrim.id}"  title="修改"><i class="icon-pencil"></i></a>
					<a class="btnList" href="${ctx}/house/ccmHouseSchoolrim/delete?id=${ccmHouseSchoolrim.id}" onclick="return confirmx('确认要删除该学校吗？', this.href)"  title="删除"><i class="icon-remove-sign"></i></a>
					</shiro:hasPermission> <shiro:hasPermission name="log:ccmLogTail:edit">
					<a class="btnList" onclick="parent.LayerDialog('${ctx}/log/ccmLogTail/list?relevance_id=${ccmHouseSchoolrim.id}&relevance_table=ccm_house_school', '记录信息', '800px', '660px')" 
								  title="记录信息"><i class="icon-print" style="color: cornflowerblue;"></i></a>
					<a class="btnList" onclick="parent.LayerDialog('${ctx}/log/ccmLogTail/formPro?relevance_id=${ccmHouseSchoolrim.id}&relevance_table=ccm_house_school', '添加记录', '800px', '660px')" title="添加记录"><i class="icon-plus"></i></a>
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
</ul>
</body>
</html>