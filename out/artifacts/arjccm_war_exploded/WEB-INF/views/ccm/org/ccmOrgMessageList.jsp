<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>辖区信息管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(function() {
		var param="";
		if(location.search.indexOf("type=")!=-1){
			param=location.pathname+location.search;
			param=param.substring(0,param.length-2);
		}else{
			param=location.pathname+location.search+"&type=";	
		}
		 
		$("#myUl>li>a").eq(0).attr("href",param+"01")
		$("#myUl>li>a").eq(1).attr("href",param+"02")
		$("#myUl>li>a").eq(2).attr("href",param+"03")
		$("#myUl>li>a").eq(3).attr("href",param+"04")
	}); 
	function page(n, s) {
		$("#pageNo").val(n);
		$("#pageSize").val(s);
		$("#searchForm").submit();
		return false;
	}
</script>
<style>
.img{
width: 200px;
margin: 0 auto;
 padding-top: 260px;
}
</style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="">信息公开</a></li>
	</ul>
	<ul class="" id="myUl">
		<li style="float: right;"><a class="btn btn-danger"
			href="${ctx}/org/ccmOrgPerson/ccmOrgMessageList?">民警</a></li>
		<li style="float: right;"><a class="btn btn-success"
			href="${ctx}/org/ccmOrgPerson/ccmOrgMessageList?">网格员</a></li>
		<li style="float: right;"><a class="btn btn-warning"
			href="${ctx}/org/ccmOrgPerson/ccmOrgMessageList?">志愿者</a></li>
		<li style="float: right;"><a class="btn btn-primary"
			href="${ctx}/org/ccmOrgPerson/ccmOrgMessageList">居委会</a></li>

	</ul>
		
	<div class=" row-fluid">
		<c:forEach items="${list}" var="item" >
			<div class="img">
				
				<img src="${item.images}" style="height:300px;"/>
				<%-- <img alt="hi" style="width: 100px"" src="${ccmOrgPerson.images}"> --%>
				<div style="padding: 0 10px 10px 70px;">${item.name}</div>
				<div style="padding: 0 10px 10px 70px;">${item.area.name}</div>
				<div style="padding: 0 10px 10px 70px;">${item.phone}</div>
			</div>
		</c:forEach>
		
	</div>
</body>
</html>