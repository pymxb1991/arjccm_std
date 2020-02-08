<!DOCTYPE html>

<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<html lang="en">
<head>
  <meta charset="UTF-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
   <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta name="renderer" content="webkit|ie-comp|ie-stand" /> 
  <title>杭州道街综合智慧指挥系统</title>
  
<link rel="stylesheet" href="${ctxStatic}/common/index/css/win10/reset.css">
<link rel="stylesheet" href="${ctxStatic}/common/index/css/win10/menu-1.css">
  <!--[if lt IE 9]>
     <script src="/arjccm/static/jquery/html5shiv.js" type="text/javascript"></script>
  <![endif]-->
</head>
<body>
<div class="container">
  <div class="header">
    <div class="header-left clearfix">
      <div class="dates">2017年12月07日</div>
    </div>
    <div class="header-right clearfix">
      <div></div>
      <q id="pTitle">您好,欢迎来到杭州道街综合智慧指挥系统！</q>
      <span><a href="${ctx}/logout"><i></i><font color="white" >退出</font></a></span>
       </div>
  </div>
  <div class="main">
    <ul>
   
      <li>
        <h3 class="fatigue-duty">系统链接</h3>
										
        <div class="statistics"><img src="${ctxStatic}/common/index/images/win10/wanggehua.png"/><a href="${ctx}/sys/map/statIndexCom" target="_self"><b>网格化</b></a></div>
        <div class="effectiveness"><img src="${ctxStatic}/common/index/images/win10/dangjian.png"/><a href="${fn:escapeXml(param.service)}${fn:indexOf(param.service, '?') eq -1 ? '?' : '&'}ticket=${serviceTicketId}" target="_self"><b>智慧党建</b></a></div>
        <div class="scheduling"><img src="${ctxStatic}/common/index/images/win10/dianzishapan.png"/><a href="${fn:escapeXml(param.service)}${fn:indexOf(param.service, '?') eq -1 ? '?' : '&'}ticket=${serviceTicketId}" target="_self"><b>电子沙盘</b></a></div>
        <div class="district"><img src="${ctxStatic}/common/index/images/win10/shipinronghe.png"/><a href="${fn:escapeXml(param.service)}${fn:indexOf(param.service, '?') eq -1 ? '?' : '&'}ticket=${serviceTicketId}" target="_self"><b>视频融合</b></a></div>
      </li>
      <li>
        <h3 class="system">杭州道街"六大板块"</h3>
        <div class="users"><img src="${ctxStatic}/common/index/images/win10/minsheng.png"/><a href="${ctx}/sys/map/statLivelihood" target="_self"><b>民计民生</b></a></div>
        <div class="daily"><img src="${ctxStatic}/common/index/images/win10/pingan.png"/><a href="${ctx}/sys/map/statPublicSecurity" target="_self"><b>平安建设</b></a></div>
        <div class="install"><img src="${ctxStatic}/common/index/images/win10/shengchan.png"/><a href="${ctx}/sys/map/statNpseSafe" target="_self"><b>安全生产</b></a></div>
        <div class="eoms"><img src="${ctxStatic}/common/index/images/win10/jingji.png"/><a href="${ctx}/sys/map/statConstructionEconomics" target="_self"><b>经济建设</b></a></div>
        <div class="authority"><img src="${ctxStatic}/common/index/images/win10/chengshi.png"/><a href="${ctx}/sys/map/statCityManage" target="_self"><b>城市管理</b></a></div>
        <div class="help"><img src="${ctxStatic}/common/index/images/win10/dangjian2.png"/><a href="${ctx}/sys/map/statParty" target="_self"><b>智慧党建</b></a></div>
      </li>
    </ul>
  </div>
</div>
<!-- <script src="/cas/js/jquery-2.2.4.min.js"></script> -->
<script src="${ctxStatic}/jquery/jquery-2.2.4.min.js"></script>
<script type="text/javascript">
$(document).ready(function(e) {
    $('.container').height($(document).height());
	$('.main ul').width($(window).width());
	$('.main ul').height($(document).height()-$('.header').height());
	
	/*   $('#pTitle').text("您好" + "李川" + "，欢迎来到杭州道街综合智慧指挥系统！"); */
		$('.dates').text(showTime())
		
		
});
function showTime() {
    var str, time, month, getDate,getHours, getMinutes, getSeconds;
    time = new Date();
    month = (time.getMonth() + 1);
    getDate = time.getDate();
    getHours = time.getHours();
    getMinutes = time.getMinutes();
    getSeconds = time.getSeconds();
    if (month < 10) {
        month = '0' + month;
    }
    if (getDate < 10) {
        getDate = '0' + getDate;
    }
    if (getHours < 10) {
        getHours = '0' + getHours;
    }
    if (getMinutes < 10) {
        getMinutes = '0' + getMinutes;
    }
    if (getSeconds < 10) {
        getSeconds = '0' + getSeconds;
    }
    str = time.getFullYear() + "年" + month + "月" + getDate + "日";
    return str;
}
</script>

