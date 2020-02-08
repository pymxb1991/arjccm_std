<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html style="height:100%;width:100%;">
<head>
<title>今日警情摘要</title>
</head>
<body>
<div id="TodayAlarm" style="width:100%;height:100%"></div>
<script src="${ctxStatic}/common/index/Scripts/js/echarts.min.js"></script>
<script type="text/javascript">
$('html,body').css({'height':'100%'});
		var alarmData=[];
		<c:forEach items="${jsonData.data}" var="data">
               alarmData.push({
            	   value:' ${data.num}',
            	   name:'${data.name}',
            	   typeCode:'${data.type}'
               })
		</c:forEach>
		// 基础颜色表
	    var color = ['#feb155', '#5ec4ea', '#9c7bd4', '#ff6769', '#ffe26c', '#db843d', '#93a9d0', '#d09392', '#b9ce96', '#a99bbc', '#92c3d4', '#ffdf5f'];
		var option = {
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
        var Piechart = echarts.init(document.getElementById('TodayAlarm'));
        Piechart.on('click', function (params) {
           // var typeCode=params.data.typeCode;
           // parent.window.location.href="${ctx}/alarm/bphAlarmInfo/statPercentByAlarmType?typeCode="+typeCode
           parent.window.location.href="${ctx}/alarm/bphAlarmInfo/statCountGroupOffice"
        });
        // 填充数据
        Piechart.setOption(option);
      
</script>
</body>
</html>