<%@ page contentType="text/html;charset=UTF-8" %><meta http-equiv="Content-Type" content="text/html;charset=utf-8" /><meta name="author" content="http://jeesite.com/"/>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta http-equiv="Expires" content="0"><meta http-equiv="Cache-Control" content="no-cache"><meta http-equiv="Cache-Control" content="no-store">
<script src="${ctxStatic}/jquery/jquery-1.8.3.min.js" type="text/javascript"></script>
<script src="${ctxStatic}/jquery/jquery.cookie.js" type="text/javascript"></script>
<link href="${ctxStatic}/bootstrap/2.3.1/css_${not empty cookie.theme.value ? cookie.theme.value : 'black'}/bootstrap.min.css" type="text/css" rel="stylesheet" />
<link href="${ctxStatic}/bootstrap/2.3.1/css_${not empty cookie.theme.value ? cookie.theme.value : 'black'}/custom.css" type="text/css" rel="stylesheet" />
<script src="${ctxStatic}/bootstrap/2.3.1/js/bootstrap.min.js" type="text/javascript"></script>
<link href="${ctxStatic}/layim/layui/css/layui.css" type="text/css" rel="stylesheet" />

<link href="${ctxStatic}/bootstrap/2.3.1/awesome/font-awesome.min.css" type="text/css" rel="stylesheet" />
<link href="${ctxStatic}/bootstrap/checkbox-radio.css" type="text/css" rel="stylesheet" />
<link href="${ctxStatic}/iconfont/iconfont.css" type="text/css" rel="stylesheet" />
<!--[if lte IE 7]><link href="${ctxStatic}/bootstrap/2.3.1/awesome/font-awesome-ie7.min.css" type="text/css" rel="stylesheet" /><![endif]-->
<!--[if lte IE 6]><link href="${ctxStatic}/bootstrap/bsie/css/bootstrap-ie6.min.css" type="text/css" rel="stylesheet" />
<script src="${ctxStatic}/bootstrap/bsie/js/bootstrap-ie.min.js" type="text/javascript"></script><![endif]-->
<link href="${ctxStatic}/jquery-select2/3.4/select2.min.css" rel="stylesheet" />
<script src="${ctxStatic}/stomp-websocket-master/stomp.js"></script>
<script src="${ctxStatic}/jquery-select2/3.4/select2.min.js" type="text/javascript"></script>
<link href="${ctxStatic}/jquery-validation/1.11.0/jquery.validate.min.css" type="text/css" rel="stylesheet" />
<script src="${ctxStatic}/jquery-validation/1.11.0/jquery.validate.min.js" type="text/javascript"></script>
<script src="${ctxStatic}/jquery-validation/1.11.1/jquery.validate.method.js" type="text/javascript"></script>
<script src="${ctxStatic}/jquery-validation/1.11.1/localization/messages_zh.js" type="text/javascript"></script>
<link href="${ctxStatic}/jquery-jbox/2.3/Skins/Bootstrap/jbox.min.css" rel="stylesheet" />
<script src="${ctxStatic}/jquery-jbox/2.3/jquery.jBox-2.3.min.js" type="text/javascript"></script>
<script src="${ctxStatic}/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<script src="${ctxStatic}/common/mustache.min.js" type="text/javascript"></script>
<link href="${ctxStatic}/common/jeesite.css" type="text/css" rel="stylesheet" />
<script src="${ctxStatic}/common/jeesite.js" type="text/javascript"></script>
<link href="${ctxStatic}/common/menu.css" type="text/css" rel="stylesheet" />
<script src="${ctxStatic}/common/pgwmenu.js" type="text/javascript"></script>
<link href="${ctxStatic}/bootstrap/bootstrapTable/css/bootstrap-table.min.css" type="text/css" rel="stylesheet" />
<script src="${ctxStatic}/bootstrap/bootstrapTable/js/bootstrap-table.js" type="text/javascript"></script>
<script src="${ctxStatic}/bootstrap/bootstrapTable/js/bootstrap-table-zh-CN.js" type="text/javascript"></script>
<link href="${ctxStatic}/mCustomScrollbar/jquery.mCustomScrollbar.css" type="text/css" rel="stylesheet" />
<script src="${ctxStatic}/mCustomScrollbar/jquery.mCustomScrollbar.js" type="text/javascript"></script>
<script src="${ctxStatic}/lightbox/js/lightbox.js" type="text/javascript"></script>
<link href="${ctxStatic}/lightbox/css/lightbox.css" type="text/css" rel="stylesheet" />
<script src="${ctxStatic}/Particleground/Particleground.js" type="text/javascript"></script>
<script type="text/javascript">var ctx = '${ctx}', ctxStatic='${ctxStatic}';</script>
<%-- <%@include file="/WEB-INF/views/include/layUI.jsp" %> --%>
<style>
.table td{
	text-align:center;
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
    height: 60px;
}
.table th{
    text-align:center;
}
.tp{
	text-align:left !important;
    width: 150px;
    height: 39px !important;
    white-space:nowrap;
    overflow: hidden;
 /*   white-space:nowrap;*/
}
    ul>li>label{
        font-family: "Microsoft YaHei", "微软雅黑";
        font-size: 12px;
    }
/*     table>tbody>tr>td{
       height: 60px !important;
    } */
    div>ul>li{
        box-sizing: unset !important;
    }
    .layui-layer-content{
        width: 100%;
    }
</style>