<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>过车信息管理</title>
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
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/carpass/ccmCarPass/">以图搜图</a></li>
		<%-- <shiro:hasPermission name="carpass:ccmCarPass:edit"><li><a href="${ctx}/carpass/ccmCarPass/form">过车信息添加</a></li></shiro:hasPermission> --%>
	</ul>
	<form id="searchForm" modelAttribute="ccmCarPass" action="" method="post" class="breadcrumb form-search" style="height:60px;">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
		<li> <div class="layui-inline">
      <label class="layui-form-label" style="margin-left: 0px;left: 0px;width: 40px;">抓拍机</label>
      <div class="layui-input-inline">
        <select name="modules" lay-verify="required" lay-search="" class="input-medium">
          <option value=""></option>
          <option value="1">全部</option>
          <option value="2">form</option>
          <option value="3">layim</option>
          <option value="4">element</option>
          <option value="5">laytpl</option>
          <option value="6">upload</option>
          <option value="7">laydate</option>
          <option value="8">laypage</option>
          <option value="9">flow</option>
          <option value="10">util</option>
          <option value="11">code</option>
          <option value="12">tree</option>
          <option value="13">layedit</option>
          <option value="14">nav</option>
          <option value="15">tab</option>
          <option value="16">table</option>
          <option value="17">select</option>
          <option value="18">checkbox</option>
          <option value="19">switch</option>
          <option value="20">radio</option>
        </select>
      </div>
    </div></li>
 
   <li><div class="layui-form-item">
    <div class="layui-inline">
      <label class="layui-form-label">模型对比阈值段</label>
      <div class="layui-input-inline">
        <input type="text" name="price_min" placeholder="" autocomplete="off" class="input-medium">
      </div>
      <div class="layui-form-mid">-</div>
      <div class="layui-input-inline" style="margin-left: 10px;">
        <input type="text" name="price_max" placeholder="" autocomplete="off" class="input-medium">
      </div>
    </div></div></li>
    <li>
    <div class="layui-form-item">
    <div class="layui-inline">
      <label class="layui-form-label" style="width: 78px;">抓拍起始日期</label>
      <div class="layui-input-block">
        <input type="text" name="date" id="dateStart" placeholder="请选择开始时间" autocomplete="off" class="input-medium Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});">
      </div>
    </div></div></li>
    <li>
    <div class="layui-form-item">
    <div class="layui-inline">
      <label class="layui-form-label" style="width: 78px;">抓拍终止日期</label>
      <div class="layui-input-block">
        <input type="text" name="date" id="dateEnd" autocomplete="off" placeholder="请选择结束时间" class="input-medium Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});">
      </div>
    </div>
  </div></li>
<%--  <div class="layui-upload">--%>

  <li><button type="button" class="layui-btn" id="test1">上传图片</button></li>
  <li><div class="layui-upload-list">
    <img class="layui-upload-img" id="demo1">
    <p id="demoText"></p>
  </div></li>

</div>


			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="检索"/></li>
			<li class="clearfix"></li>
		</ul>
	</form>

	<div class="pagination">${page}</div>
</body>
</html>