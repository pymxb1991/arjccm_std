<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html style="height:100%;width:100%;">
<head>
    <title>热点分析</title>
    <link rel="stylesheet" href="${ctxStatic}/ol/ol.css" type="text/css">
    <link rel="stylesheet" href="${ctxStatic}/modules/map/js/draw/css/p-ol3.min.css"	type="text/css">
    <link rel="stylesheet" href="${ctxStatic}/modules/map/css/map.css" type="text/css">
    <style type="text/css">
        .slider, .slider-panel img, .slider-extra {
            width:100%;
            height:100%;
        }

        .slider {
            position: relative;
        }

        .slider-panel, .slider-pre, .slider-next, .slider-nav {
            position: absolute;
            z-index: 8;
        }

        .slider-nav {
            position: absolute;
            left: 48px;
            bottom: 70px;
            margin:0;
        }
        .slider-nav .slider-item-selected {
            background: #5388be;
            color:#fff;
        }
        .slider-nav li {
            background: #fff;
            border:1px solid #99acc3;
            border-radius: 50%;
            cursor: pointer;
            margin-bottom:15px;
            overflow: hidden;
            text-align: center;
            height: 25px;
            line-height: 25px;
            width:25px;
        }
        .slider-nav li::after {
            content:"";
            display:block;
            position:absolute;
            left:12px;
            height:15px;
            width:2px;
            background: #99acc3;
            margin-top:-24px;
        }
        .slider-nav li:last-of-type::after {
            display:none;
        }
        .slider-panel {
            position: absolute;
        }

        .slider-panel img {
            border: none;
        }

        .slider-extra {
            position: relative;
            position:absolute;
        }

        .slider-page a {
            background: rgba(0, 0, 0, 0.2);
            filter: progid:DXImageTransform.Microsoft.gradient(startColorstr=#33000000,endColorstr=#33000000);
            color: #fff;
            text-align: center;
            display: block;
            font-family: "simsun";
            font-size: 22px;
            width: 28px;
            height: 62px;
            line-height: 62px;
            margin-top: -31px;
            position: absolute;
            top: 50%;
        }

        .slider-page a:HOVER {
            background: rgba(0, 0, 0, 0.4);
            filter: progid:DXImageTransform.Microsoft.gradient(startColorstr=#66000000,endColorstr=#66000000);
        }
        .slider-next {
            left: 100%;
            margin-left: -28px;
        }
        .playback {
            position: absolute;
            z-index: 32;
            width: 25px;
            height: 25px;
            background: url("${ctxStatic}/images/stop.png") center no-repeat;
            background-size: 100% 100%;
            border-radius: 50%;
            left: 48px;
            bottom: 50px;
            cursor: pointer;
        }
        .suspend {
            background: url("${ctxStatic}/images/play.png") center no-repeat;
            background-size: 100% 100%;
        }
    </style>
</head>
<body>
<div class="slider map" id="map">
    <div class="playback" id="Controls"></div>
    <div class="slider-extra">
        <ul class="slider-nav">
        </ul>
    </div>
</div>
<script src="${ctxStatic}/ol/ol.js"></script>
<script src="${ctxStatic}/modules/map/js/mapconfig.js"></script>
<script src="${ctxStatic}/modules/map/js/commonMap.js"></script>
<script src="${ctxStatic}/common/common.js"></script>
<script type="text/javascript">
    $('html,body').css({'height':'100%'});
    var length,
        currentIndex = 0,
        interval,
        hasStarted = false,
        t = 3000,
         MonthCont=5;
    $(document).ready(function() {
        //加载地图

        initMap();
        $('#Controls').toggle(function () {
            stop();
            $(this).addClass('suspend');
        }, function () {
            start();
            $(this).removeClass('suspend');
        });
        getAlarmType()
        addHeatMap();
        addMonth();
    });
    /**
     * 获取本周、本季度、本月、上月的开始日期、结束日期
     */


    //格式化日期：yyyy-MM-dd
    function formatDate(date) {
        var myyear = date.getFullYear();
        var mymonth = date.getMonth()+1;
        var myweekday = date.getDate();

        if(mymonth < 10){
            mymonth = "0" + mymonth;
        }
        if(myweekday < 10){
            myweekday = "0" + myweekday;
        }
        return (myyear+"-"+mymonth + "-" + myweekday);
    }
    //格式化日期：MM
    function formatDate1(date) {
        var mymonth = date+1;
        if(mymonth < 10){
            mymonth = "0" + mymonth;
        }
        return mymonth;
    }

    //获得某月的天数
    function getMonthDays(nowYear,myMonth){
        var monthStartDate = new Date(nowYear, myMonth, 1);
        var monthEndDate = new Date(nowYear, myMonth + 1, 1);
        var days = (monthEndDate - monthStartDate)/(1000 * 60 * 60 * 24);
        return days;
    }

    //获得本月的开始日期
    function getMonthStartDate(nowYear,nowMonth){
        var monthStartDate = new Date(nowYear, nowMonth, 1);
        return formatDate(monthStartDate);
    }

    //获得本月的结束日期
    function getMonthEndDate(nowYear,nowMonth){
        var monthEndDate = new Date(nowYear, nowMonth, getMonthDays(nowYear,nowMonth));
        return formatDate(monthEndDate);
    }
    function sliderChange(index){
        var arr=['0','1','2','3','4','5'];
        for(var k in arr){
            var heatMap='heat'+arr[k];
            if(index==arr[k]){
                Map.layersIsShow(heatMap, true);
            }else{
                Map.layersIsShow(heatMap, false);
            }
        }
        var arr1=['5','4','3','2','1','0'];
        $('.slider-item').removeClass('slider-item-selected');
        $('.slider-item').eq(arr1[index]).addClass('slider-item-selected');
        currentIndex=arr1[index];
    }
    function sliderChange1(index){
        var arr=['0','1','2','3','4','5'];
        for(var k in arr){
            var heatMap='heat'+arr[k];
            if(index==arr[k]){
                Map.layersIsShow(heatMap, true);
            }else{
                Map.layersIsShow(heatMap, false);
            }
        }
        var arr1=['5','4','3','2','1','0'];
        $('.slider-item').removeClass('slider-item-selected');
        $('.slider-item').eq(arr1[index]).addClass('slider-item-selected');
    }
    function next() {
        var preIndex = currentIndex;
        currentIndex =Number(currentIndex)+1;
        if(currentIndex>5){
            currentIndex=0;
        }
        play(preIndex, currentIndex);
    }

    function play(preIndex, currentIndex) {
        var arr2=['5','4','3','2','1','0'];
        sliderChange1(arr2[currentIndex])
    }

    function start() {
        if(!hasStarted) {
            hasStarted = true;
            interval = setInterval(next, t);
        }
    }
    function stop() {
        clearInterval(interval);
        hasStarted = false;
    }
    var ArjAlarmType='';
    function getAlarmType(){
        $.ajax({
            type : "post",
            url : ctx+'/sys/dict/listData?type=bph_alarm_info_typecode',
            async : false,
            success : function(data){
                var len=data.length;
                if(len>0){
                    for(var i=0;i<len;i++){
                        ArjAlarmType+=data[i].value+',';
                    }
                    ArjAlarmType=ArjAlarmType.substring(0,ArjAlarmType.length-1);
                }
            }
        });
    }
    function initMap(){
        // 地图默认数据设置
        var defaultPrams = {
            id : 'map',
            centerCoordinate : centerCoordinate,
            //zoom : zoomIndex,
            zoom : 10,
            maxZoom : maxZoom,
            minZoom : minZoom,
            baseUrl : baseUrlT,
            zoomShowOrHide : false,// 缩小放大
            overviewMap : false,
            selectPointerFlag : true
            // 鹰眼图
        }
        Map = new ArjMap.Map(defaultPrams);
        // 加载地图
        Map.init();
    }

    function addMonth(){
        var MonthDate = new Date(); //上月日期
        MonthDate.setDate(MonthCont);
        MonthDate.setMonth(MonthDate.getMonth()-MonthCont);
        var lastYear = MonthDate.getYear();
        lastYear += (lastYear < 2000) ? 1900 : 0;
        var lastMonth = MonthDate.getMonth();
        var Month=formatDate1(lastMonth);
        var  sliderHtml='<li class="slider-item"  onclick="sliderChange('+MonthCont+')">'+Month+'月</li>';
        if(MonthCont==5){
            sliderHtml='<li class="slider-item  slider-item-selected"  onclick="sliderChange('+MonthCont+')">'+Month+'月</li>';
        }
        $('.slider-nav').append(sliderHtml)
        MonthCont--;
        if(MonthCont>=1){
            addMonth();
        }else{
            addMonthNow()
        }
    }
    function addMonthNow(){
        var now = new Date(); //当前日期
        var nowDay = now.getDate(); //当前日
        var nowMonth = now.getMonth(); //当前月
        var nowYear = now.getYear(); //当前年
        nowYear += (nowYear < 2000) ? 1900 : 0;
        var beginTime0=getMonthStartDate(nowYear,nowMonth)+' 00:00:00';
        var endTime0=getMonthEndDate(nowYear,nowMonth)+' 23:59:59';
        var Month0=formatDate1(nowMonth);
        var html='<li class="slider-item" onclick="sliderChange(0)">'+Month0+'月</li>';
        $('.slider-nav').append(html);
        start();

    }
    var cont=5;
    function addHeatMap(){
        var MonthDate = new Date(); //上月日期
        MonthDate.setDate(cont);
        MonthDate.setMonth(MonthDate.getMonth()-cont);
        var lastYear = MonthDate.getYear();
        lastYear += (lastYear < 2000) ? 1900 : 0;
        var lastMonth = MonthDate.getMonth();
        var Month=formatDate1(lastMonth);
        var beginTime=getMonthStartDate(lastYear,lastMonth)+' 00:00:00';
        var endTime=getMonthEndDate(lastYear,lastMonth)+' 23:59:59';
        $.getJSON(ctx+'/alarm/bphAlarmInfo/queryAlarmInfoByDateAndAlarmType?beginAlarmTime='+beginTime+'&endAlarmTime='+endTime+'&typeCode='+ArjAlarmType+'',function(data){
            var flag=false;
            if(cont==5){
                flag==true;
            }
            if(data!=null&&data!=''){
                var heatMap='heat'+cont;
                Map.removeLayer(heatMap)
                Map.heatMap2(data,heatMap);
                Map.layersIsShow(heatMap, flag);
            }
            cont--;
            if(cont>=1){
                addHeatMap();
            }else{
                addHeatMapNow()
            }
        })
    }

    function  addHeatMapNow(){
        var now = new Date(); //当前日期
        var nowDay = now.getDate(); //当前日
        var nowMonth = now.getMonth(); //当前月
        var nowYear = now.getYear(); //当前年
        nowYear += (nowYear < 2000) ? 1900 : 0;
        var beginTime0=getMonthStartDate(nowYear,nowMonth)+' 00:00:00';
        var endTime0=getMonthEndDate(nowYear,nowMonth)+' 23:59:59';
        var Month0=formatDate1(nowMonth);
        $.getJSON(ctx+'/alarm/bphAlarmInfo/queryAlarmInfoByDateAndAlarmType?beginAlarmTime='+beginTime0+'&endAlarmTime='+endTime0+'&typeCode='+ArjAlarmType+'',function(data){
            Map.removeLayer('heat0')
            Map.heatMap2(data,'heat0');
            Map.layersIsShow('heat0', false);

        })

    }
</script>
</body>
</html>