<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>派出所分局占比</title>
<meta name="decorator" content="default" />
</head>
<body>
<div class="clearfix" style="width:100%;height:100%">
  <div class="" style="float:left;width:330px;height:100%; border-right: 1px solid #ccc;">
    <div id="TodayAlarm" style="width:300px;height:300px"></div>
  </div>
   <div class="right-conent" style="float:left;height:100%">
     <div id="AlarmPercent" style="width:100%;height:100%"></div>
   </div>
</div>

<script src="${ctxStatic}/common/index/Scripts/js/echarts.min.js"></script>
<script>
$('html,body').css({'height':'100%'});
$('.right-conent').width($(window).width()-350)
var alarmData=[];
var legendName=[];
var nowTypeCode = "${typeCode}";
<c:forEach items="${data}" var="data">
	alarmData.push({
		value:'${data.num}', name:'${data.office.name}',typeCode:nowTypeCode,officeId:'${data.officeId}',
	})
	legendName.push('${data.office.name}')
</c:forEach>
var jsonData=[];
<c:forEach items="${jsonData.data}" var="data">
jsonData.push({
       	   value:' ${data.num}',
       	   name:'${data.name}',
       	   typeCode:'${data.type}'
          })
</c:forEach>	
//基础颜色表
var color = ['#feb155', '#5ec4ea', '#9c7bd4', '#ff6769', '#ffe26c', '#db843d', '#93a9d0', '#d09392', '#b9ce96', '#a99bbc', '#92c3d4', '#ffdf5f'];
var option = {
	  color:color,
	    tooltip : {
	        trigger: 'item',
	        formatter: "{a} <br/>{b} : {c} ({d}%)"
	    },
	    legend: {
	        data:legendName,
	        right: 15,
	        top:40,
	        itemGap: 20,
	        orient: 'vertical'
	    },
	    series : [
	        {
	            name: '警情占比',
	            type: 'pie',
	            radius : '60%',
	            center: ['50%', '50%'],
	            data:alarmData,
	            itemStyle: {
	                emphasis: {
	                    shadowBlur: 10,
	                    shadowOffsetX: 0,
	                    shadowColor: 'rgba(0, 0, 0, 0.5)'
	                }
	            }
	        }
	    ]
	};
   // 实例化 对象
var Piechart = echarts.init(document.getElementById('AlarmPercent'));
Piechart.on('click', function (params) {
     var typeCode=params.data.typeCode;
     var officeId=params.data.officeId
     window.location.href="${ctx}/alarm/bphAlarmInfo/statCountByOfficeAndAlarmType?typeCode="+typeCode+"&officeId="+officeId
});
// 填充数据
Piechart.setOption(option);


var TodayOption = {
		  color:color,
		    tooltip : {
		        trigger: 'item',
		        formatter: "{a} <br/>{b} : {c} ({d}%)"
		    },
		    series : [
		        {
		            name: '今日警情摘要',
		            type: 'pie',
		            radius : '60%',
		            center: ['50%', '50%'],
		            data:jsonData,
		            itemStyle: {
		                emphasis: {
		                    shadowBlur: 10,
		                    shadowOffsetX: 0,
		                    shadowColor: 'rgba(0, 0, 0, 0.5)'
		                }
		            }
		        }
		    ]
		};
	   // 实例化 对象
  var TodayPiechart = echarts.init(document.getElementById('TodayAlarm'));
  TodayPiechart.on('click', function (params) {
       var typeCode=params.data.typeCode;
       window.location.href="${ctx}/home/plmHome/list";
  });
  // 填充数据
  TodayPiechart.setOption(TodayOption);
</script>
</body>
</html>