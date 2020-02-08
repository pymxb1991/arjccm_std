<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>特殊车辆服务管理管理</title>
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
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/citycomponents/ccmCityCar/">数据列表</a></li>
		<shiro:hasPermission name="citycomponents:ccmCityCar:edit"><li><a href="${ctx}/citycomponents/ccmCityCar/form">数据添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="ccmCityCar" action="${ctx}/citycomponents/ccmCityCar/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>车牌号码：</label>
				<form:input path="number" htmlEscape="false" maxlength="12" class="input-medium"/>
			</li>
			<li><label>车身颜色：</label>
				<form:select path="colorCar" class="input-medium">
					<form:option value="" label="全部"/>
					<form:options items="${fns:getDictList('color_car')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>车辆类型：</label>
				<form:select path="type" class="input-medium">
					<form:option value="" label="全部"/>
					<form:options items="${fns:getDictList('ccm_city_car_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>车牌颜色：</label>
				<form:select path="colorCarPlate" class="input-medium">
					<form:option value="" label="全部"/>
					<form:options items="${fns:getDictList('color_car_plate')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>特种运输类型：</label>
				<form:select path="transportType" class="input-medium">
					<form:option value="" label="全部"/>
					<form:options items="${fns:getDictList('ccm_city_car_transport_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>车主姓名：</label>
				<form:input path="name" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<!-- <li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li> -->
			<li class="btns">
			<a href="javascript:;" id="btnSubmit" class="btn btn-primary">
                <i class="icon-search"></i> 查询 </a>
			</li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>车辆图片</th>
				<th>车牌号码</th>
				<th>车身颜色</th>
				<th>车辆类型</th>
				<th>车牌颜色</th>
				<th>特种运输类型</th>
				<th>车主姓名</th>
				<th>联系电话</th>
				<shiro:hasPermission name="citycomponents:ccmCityCar:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="ccmCityCar">
			<tr>
				<td width="200px">
					<img src="${ccmCityCar.images}" style="height:50px;" class="pimg"/>
				</td>
				<td><a href="${ctx}/citycomponents/ccmCityCar/form?id=${ccmCityCar.id}">
					${ccmCityCar.number}
				</a></td>
				<td>
					${fns:getDictLabel(ccmCityCar.colorCar, 'color_car', '')}
				</td>
				<td>
					${fns:getDictLabel(ccmCityCar.type, 'ccm_city_car_type', '')}
				</td>
				<td>
					${fns:getDictLabel(ccmCityCar.colorCarPlate, 'color_car_plate', '')}
				</td>
				<td>
					${fns:getDictLabel(ccmCityCar.transportType, 'ccm_city_car_transport_type', '')}
				</td>
				<td>
					${ccmCityCar.name}
				</td>
				<td>
					${ccmCityCar.tel}
				</td>
				<shiro:hasPermission name="citycomponents:ccmCityCar:edit"><td>
    				<a class="btnList" href="${ctx}/citycomponents/ccmCityCar/form?id=${ccmCityCar.id}" title="修改"><i class="icon-pencil"></i></a>
					<a class="btnList" href="${ctx}/citycomponents/ccmCityCar/delete?id=${ccmCityCar.id}" onclick="return confirmx('确认要删除该特殊车辆服务管理吗？', this.href)" title="删除"><i class="icon-remove-sign"></i></a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
		<div id="outerdiv" style="position:fixed;top:0;left:0;background:rgba(0,0,0,0.7);z-index:2;width:100%;height:100%;display:none;">
			<div id="innerdiv" style="position:absolute;">
				<img id="bigimg" style="border:5px solid #fff;" src="" />
			</div>
		</div>
	<div class="pagination">${page}</div>
</body>
</html>