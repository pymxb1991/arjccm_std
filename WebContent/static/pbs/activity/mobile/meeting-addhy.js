/**
 * V 1.0 2018-4-20
 */
$(function() {

  $("#sType").load(ctx + "/activity/pbsActivitytype/namelist?sGroup=1");

  // 获取 所有部门人员
  $.post(ctx + "/sys/Mobile/getMemberList", function(datas) {
    // console.log(data);
    var slidownSelectOptionse = {
      "el" : 'mainselect', // 容器名称
      "type" : 'more', // 插件类型
      "width" : '120px', // 内容显示宽度
      "height" : '40px', // 内容显示高度
      "background" : '#ffffff', // 默认背景色
      "color" : '#000000', // 默认字体颜色
      "selectBackground" : '#FF6633', // 选中背景色
      "selectColor" : '#ffffff', // 选中字体颜色
      "show" : 'false', // 是否展开
      "content" : '请选择', // 要显示的内容
      "data" : datas
    // 数据
    }

    rltTools.slidownSelect(slidownSelectOptionse);
  });

  $('#applySubmit').click(
      function() {
        var $form = $("#Applyform");
        var sTitle = $form.find("#sTitle").val();
        var sType = $form.find("#sType").val();
        var dtStarttime = $form.find("#dtStarttime").val();
        var dtEndtime = $form.find("#dtEndtime").val();
        var sPlace = $form.find("#sPlace").val();
        var sContent =  $form.find("#sContent").val();
        var memids = $form.find("#mainselect").attr("attrid");
        var sAttendants = $form.find("#showCityName").text();
        // 对比时间
        var starttime = dtStarttime.replace("-","/");// 替换字符，变成标准格式
        var endtime = dtEndtime.replace("-","/");
        var d1 = new Date(Date.parse(starttime));  
        var d2 = new Date(Date.parse(endtime));  
        var publishType = $form.find("#publishType").val();
        var currentTime = new Date().getTime();
        // 补录状态
        if(publishType == 2){
            var subTime = currentTime-d1;
            var ThreeDay = 3*24*60*60*1000;
            if(subTime > ThreeDay){
                mui.toast('只能补录三天的数据', {
                  duration : 'short',
                  type : 'div'
                });
                return;
            }
            // 补录 不能超过当前的时间
            if(d2 > currentTime){
              mui.toast('补录结束时间不能大于当前时间', {
                duration : 'short',
                type : 'div'
              });
              return;
            }
        }else{
          if(d1<currentTime){
            mui.toast('开始时间不能小于当前时间', {
              duration : 'short',
              type : 'div'
            });
            return;
          
          }
        }
       
        // 开始时间大于结束时间
        if(d1>d2){  
          mui.toast('开始时间大于结束时间', {
            duration : 'short',
            type : 'div'
          });
          return;
        }  
        // 如果为空
        if (isEmpty(sTitle) || isEmpty(sContent)||isEmpty(sPlace)) {
          mui.toast('请填写全所有信息', {
            duration : 'short',
            type : 'div'
          });
          return;
        }
        if (isEmpty(memids)) {
          mui.toast('请选择接收人', {
            duration : 'short',
            type : 'div'
          });
          return;
        }
        // 提交申请
        $.post(ctx + "/Activity/attend/applyAction", {
          "sTitle" : sTitle,
          "sType" : sType,
          "sStat":publishType,
          "dtStarttime" : dtStarttime,
          "dtEndtime" : dtEndtime,
          "sPlace" : sPlace,
          "sAttendants":sAttendants,
          "PbsActinotificationIds" : memids,
          "sContent":sContent
        }, function(data) {
          if (data == "success") {
            mui.toast('提交成功', {
              duration : 'short',
              type : 'div'
            });
            // console.log(ctx + "/flow/deal/dealtList");
            window.location.href = ctx
                + "/sys/Mobile/pageTurn?pageTo=Nav-Study/meeting/meeting";
          } else {
            mui.toast('提交失败', {
              duration : 'short',
              type : 'div'
            });
          }
        });
      });

  
// mui.init();
  var result=mui("#dtEndtime")[0];
  var result2=mui("#dtStarttime")[0];
  
　result.addEventListener('click', function() {
　　　var optionsJson = this.getAttribute('data-options') || '{}';
　　　var options = JSON.parse(optionsJson);
　　　var picker = new mui.DtPicker(options);
　　　picker.show(function(rs) {
      $("#dtEndtime").val(rs.text);
      // result.innerText = ;
      // picker.dispose();
　　　});
　}, false);

result2.addEventListener('click', function() {
　　var optionsJson = this.getAttribute('data-options') || '{}';
　　var options = JSON.parse(optionsJson);
　　var picker = new mui.DtPicker(options);
　　picker.show(function(rs) {
     $("#dtStarttime").val(rs.text);
     // result.innerText = ;
     // picker.dispose();
　　});
}, false);

// 判断为空
function isEmpty(obj) {
  if (typeof obj == "undefined" || obj == null || obj == "") {
    return true;
  } else {
    return false;
  }
}

});

