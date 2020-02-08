$(document).ready(function() {
  // project-Path
  var context = $(".context").attr("content");
  // 初始化 Echarts组件
  var myChart = echarts.init(document.getElementById('ech'),"walden");
  // 标题
  var title = [ "list" ];
  $("#query").on('click', function(){
	  showEchart();
  });
  
  // 初始化页面方法
  showEchart();
  // 展示 页面的方法
  function showEchart() {
    // GetJson 获取数据
	  var beginTime = $('#beginTime').val();
	  var endTime = $('#endTime').val();
    $.getJSON(context + "/report/getDepartmentPeriod", {
	     title : "",
	     type : 1,
	     beginTime:beginTime,
	     endTime:endTime
    }, function(data) {// // data = {};
      // 图表1 数据
      console.log(data.list2);
      var ajaxDataX = $.getxAxisDate(data, title), ajaxDataYALL = $.getyAxisDate(data, title);
      // 当前的图表
      /**
       * myChart - 元素 title - 标题 ajaxDataX - 横向标题数组 ajaxDataYALL 数值数组
       */
      $.myChartSigle(myChart, "党支部学习统计", ajaxDataX, ajaxDataYALL,'line')
      $.TableListPeople("echList", ajaxDataYALL, ajaxDataX);
      var departmentPeriodData = data.list;
	  var departmentPeriodHtml = '';
	  if(departmentPeriodData.length>0){
		  for(var i = 0;i<departmentPeriodData.length;i++){
			  departmentPeriodHtml += '<tr id="'+departmentPeriodData[i].id+'" onclick="getPersonnelPeriod(this)"><td>'+departmentPeriodData[i].name+'</td><td>'+departmentPeriodData[i].value+'</td></tr>';
		  }
	  }
		$('#departmentPeriod').html(departmentPeriodHtml);
		$('#departmentPeriodTable tbody tr:eq(0)').click();
    });
  }
});