<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>人员管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
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
		<li class="active"><a href="${ctx}/org/ccmOrgPerson/">人员列表</a></li>
		<shiro:hasPermission name="org:ccmOrgPerson:edit"><li><a href="${ctx}/org/ccmOrgPerson/form">人员添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="ccmOrgPerson" action="${ctx}/org/ccmOrgPerson/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>姓名：</label>
				<form:input path="name" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>所属社区：</label>
				<sys:treeselect id="area" name="area.id" value="${ccmOrgPerson.area.id}" labelName="area.name" labelValue="${ccmOrgPerson.area.name}"
					title="区域" url="/sys/area/treeData" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
			</li>
			<li><label>性别：</label>
				<form:radiobuttons path="sex" items="${fns:getDictList('member_sex')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</li>
			<li><label>学历：</label>
				<form:select path="edu" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('education_background')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>年龄：</label>
				<form:input path="age" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>民族：</label>
				<form:input path="nation" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>人员图片</th>
				<th>姓名</th>
				<th>所属社区</th>
				<th>所属组织</th>
				<th>性别</th>
				<th>学历</th>
				<th>年龄</th>
				<th>民族</th>
				<th>更新时间</th>
				<th>工作内容</th>
				<shiro:hasPermission name="org:ccmOrgPerson:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="ccmOrgPerson">
			<tr>
				<td width="100px">
					<img src="${ccmOrgPerson.images}" style="height:50px;" class="pimg"/>
				</td>
				<td><a href="${ctx}/org/ccmOrgPerson/form?id=${ccmOrgPerson.id}">
					${ccmOrgPerson.name}
				</a></td>
				<td>
					${ccmOrgPerson.area.name}
				</td>
				<td>
					${ccmOrgPerson.organizeName}
				</td>
				<td>
					${fns:getDictLabel(ccmOrgPerson.sex, '', '')}
				</td>
				<td>
					${fns:getDictLabel(ccmOrgPerson.edu, '', '')}
				</td>
				<td>
					${ccmOrgPerson.age}
				</td>
				<td>
					${ccmOrgPerson.nation}
				</td>
				<td>
					<fmt:formatDate value="${ccmOrgPerson.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${ccmOrgPerson.remarks}
				</td>
				<shiro:hasPermission name="org:ccmOrgPerson:edit"><td>
    				<a class="btnList"
							onclick="parent.LayerDialog('${ctx}/org/ccmOrgPerson/form?id=${ccmOrgPerson.id}', '编辑', '1100px', '700px')"
							title="修改"><i class="icon-pencil"></i></a> <a class="btnList"
							href="${ctx}/org/ccmOrgPerson/delete?id=${ccmOrgPerson.id}"
							onclick="return confirmx('确认要删除该车辆布控记录吗？', this.href)" title="删除"><i
								class="icon-trash"></i></a>
    			<%-- 	
    				<a href="${ctx}/org/ccmOrgPerson/form?id=${ccmOrgPerson.id}">修改</a>
					<a href="${ctx}/org/ccmOrgPerson/delete?id=${ccmOrgPerson.id}" onclick="return confirmx('确认要删除该人员吗？', this.href)">删除</a> --%>
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