<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>事件分类统计</title>
	<meta name="decorator" content="default"/>
	<script src="${ctxStatic}/jquery/jquery-1.9.1.min.js"></script>
	<link href="${ctxStatic}/bootstrap/2.3.1/css_${not empty cookie.theme.value ? cookie.theme.value : 'black'}/bootstrap.min.css" type="text/css" rel="stylesheet" />
	<link href="${ctxStatic}/bootstrap/2.3.1/css_${not empty cookie.theme.value ? cookie.theme.value : 'black'}/custom.css" type="text/css" rel="stylesheet" />
	<script src="${ctxStatic}/bootstrap/2.3.1/js/bootstrap.min.js"></script>
	<link rel="stylesheet"
		  href="${ctxStatic}/bootstrap/2.3.1/awesome/font-awesome.min.css">
	<!--[if lte IE 7]>
	<link rel="stylesheet" href="../bootstrap/2.3.1/awesome/font-awesome-ie7.css">
	<![endif]-->
	<!--[if lte IE 6]>
	<link rel="stylesheet" href="../bootstrap/bsie/css/bootstrap-ie6.min.css">
	<script src="../bootstrap/bsie/js/bootstrap-ie.min.js"></script>
	<![endif]-->
	<%--<script src="${ctxStatic}/common/index/Scripts/js/echarts2.2.7/echarts-all.js"></script>--%>

	<script src="${ctxStatic}/common/index/Scripts/js/echarts.min.js"></script>
	<script src="${ctxStatic}/dma/eventheme/eventype.js"></script>
    <link rel="stylesheet" href="${ctxStatic}/laydate/theme/default/laydate.css" media="all">
    <script src="${ctxStatic}/laydate/laydate.js"></script>
    <script src="${ctxStatic}/echarts/theme/${not empty cookie.theme.value ? cookie.theme.value : 'black'}.js"></script>
    <script type="text/javascript"
            src="${ctxStatic}/plm/storage/ajaxMessageAlert.js"></script>
	<style>
		.common-pading{width:100%;height:750px;padding:5px;}
		.echarts{width:100%;height:100%;}
		.width50{width: 50%;float: left;}
        .demo-input{padding-left: 10px; height: 38px; min-width: 262px; line-height: 38px; border: 1px solid #e6e6e6;  background-color: #fff;  border-radius: 2px;}
	    .title{border-color: #eee!important;    border: 1px solid;}
	    .title-div{margin-top: 20px;margin-left: 20px;}
	    .title_font{ font-size: large;}
        .condition{margin-left: 20px;font-size: 15px;}
        .condition_input{margin-left: 50px;}

    </style>
	<script type="text/javascript">
		$(document).ready(function() {

		});
	</script>
</head>
<body>
    <div class="title">
        <div class="title-div">
            <label   class="title_font" > 事件分类统计 </label>
            <hr style="  width: 99%;">
        </div>
        <div class="layui-inline" > <!-- 注意：这一层元素并不是必须的 -->
            <li class="condition" > 统计时间 ：
                <input class="condition_input"  type="text" class="demo-input" placeholder="统计开始时间" id="beginHappenDate" readonly="">
            -  <input type="text" class="demo-input" placeholder="统计结束时间" id="endHappenDate" readonly="">
            <input id="btnSubmit" class="btn btn-primary" onclick="queryEventInfo()" value="查询" style=" margin-left: 50px; margin-bottom: 15px;"/>
            </li>
        </div>
        <div class="container-fluid" style="height: 100%; overflow: hidden"
             id="main">
            <div class="context" content="${ctx}"></div>
            <div class="row-fluid height100">
                <div class="width50 shadow" >
                    <%--事件报警量统计--%>
                    <div class="common-pading">
                        <div id="eventPreviewEcharts" class="echarts" style="width: 80%;height: 80%;"></div>
                    </div>
                </div>
                <div class="width50 shadow" >
                    <%--事件实际发生量统计--%>
                    <div class="common-pading">
                        <div id="eventEcharts" class="echarts" style="width: 80%;height: 80%;"></div>
                    </div>
                </div>
            </div>

        </div>
    </div>
</body>
</html>