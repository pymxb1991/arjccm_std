$(function() {
  // project-Path
  var context = $(".context").attr("content");
  // 初始化 Echarts组件
  var myChart1 = echarts.init(document.getElementById('ech1'),"walden");
  var myChart2 = echarts.init(document.getElementById('ech2'),"walden");
  // 标题
  var title1 = [ "学员活动变化曲线" ];
  var title2 = [ "学员数量变化曲线" ];
  // 初始化页面方法
  showEchart();
  // 展示 页面的方法
  function showEchart() {
    // GetJson 获取数据
    $.getJSON(context + "/report/getNum", {
     title : "",
     type : 1
    }, function(data) {// // data = {};
      // 图表1 数据
      console.log(data);
      var ajaxDataX1 = $.getxAxisDate(data, title1), ajaxDataYALL1 = $
          .getyAxisDate(data, title1);
      var ajaxDataX2 = $.getxAxisDate(data, title2), ajaxDataYALL2 = $
      .getyAxisDate(data, title2);

      // 当前的图表
      /**
       * myChart1 - 元素 title1 - 标题 ajaxDataX1 - 横向标题数组 ajaxDataYALL1 数值数组
       */
      $.myChartSigle(myChart1, title1, ajaxDataX1, ajaxDataYALL1,'line')
      $.TableListPeople("echList1", ajaxDataYALL1, ajaxDataX1);

      $.myChartSigle(myChart2, title2, ajaxDataX2, ajaxDataYALL2)
      $.TableListPeople("echList2", ajaxDataYALL2, ajaxDataX2);
    });
  }

});