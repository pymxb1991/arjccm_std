<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>辖区信息管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(function() {

	});
	function page(n, s) {
		$("#pageNo").val(n);
		$("#pageSize").val(s);
		$("#searchForm").submit();
		return false;
	}

	$(function () {

/*        if ($('.userImg').attr('src').length < 1) {
            $('.userImg').attr('src','../../static/images/user_bg.jpg');
        }*/

        $('img').each(function(){
            if($(this).attr('src').length < 1){
                if (this.id == 'areaIcon') {
                    $(this).attr('src','../../static/images/login_bg/login_bg1.png')
                } else {
                    $(this).attr('src','../../static/images/user_bg.jpg')
                }
            }
        });

    })

</script>
<style>
h4.nav {
	border-left: 3px solid #dddddd;
	background-color: #f5f5f5;
	padding: 5px;
	margin: 10px 0 5px;
}

h4.nav2 {
	border-left: 3px solid #dddddd;
	background-color: #f5f5f5;
	padding: 5px;
	margin: 10px 10px 5px 10px;
}

.span3.nav1, .span9.nav1 {
	padding-left: 15px;
}

.row-fluid.nav2 .span3 {
	margin-left: 20px;
	margin-bottom: 15px;
}

.row-fluid.nav2 .span3 .remarks {
	height: 130px;
	overflow: auto;
}
</style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="javascript:window.navigate(location)">辖区信息</a></li>
	</ul>
	<div class=" row-fluid">
		<div class="span3 nav1">
			<a href="${ccmOrgArea.icon}" data-lightbox="image-1" data-title="辖区信息"><img id="areaIcon" style="height: 250px;width: 407px;margin-bottom: 5px" class="example-image" src="${ccmOrgArea.icon}"></a>
		</div>
		<div class="span9 nav1">
			<h4 class="nav">辖区说明</h4>
			<p style="margin-top: 10px;" class="nav form-actions">${ccmOrgArea.areainfor}</p>
		</div>
	</div>
	<div class=" row-fluid nav2">
		<h4 class="nav2">管理人员简介</h4>
		<c:forEach items="${ListVccTeam}" var="user">
			<div class="span3">
				<div class="span4">
					<a href="${user.photo}" data-lightbox="image-1" data-title="人员信息"><img style="height: 150px;width: 124px" class="example-image" src="${user.photo}"></a>
				</div>
				<div class="span8 remarks">
					<p>${user.name}(${fns:getDictLabel(user.sex,'sex',"")})</p>
					<p>${fns:getDictLabel(user.nation,'sys_volk',"")}
						${fns:getDictLabel(user.politics,'sys_ccm_poli_stat',"")}
						${user.remarks}</p>
				</div>
			</div>
		</c:forEach>
	</div>
</body>
</html>