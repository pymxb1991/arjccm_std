/**
 * V 1.0 2018-3-27 13:36:50
 */
$(function() {

  $("#sPartment").load(  ctx + "/sys/pbsDepartmentetc/namelist?type=2",
      function() {
        var selectString = $(this).val();
        // 查询 学员
        $("#sTransmem").load(
            ctx + "/person/pbsPartymem/findPbsListByOffice?officeid="
                + selectString);
      });
  // 修改 部门
  $("#sPartment").change(function() {
    $("#sTransmem").load(ctx + "/person/pbsPartymem/findPbsListByOffice", {
      "officeid" : $(this).val()
    }, function() {
      $("#sTransmem").val("");
    });
  });

  var $form = $("#forwardform");
  // 转发
  $('#acceptSubmit').click(function() {
    //  必须接收人
    var sTransmem = $form.find("#sTransmem").val();
    var sReportid = $form.find("#acceptSubmit").attr("attrid");
    var sTranscontent  =$form.find("#sTranscontent").val()
    if(isEmpty(sTransmem)){
      mui.toast('请选择接收人', {
          duration : 'short',
          type : 'div'
      });
      return;
    }
    // 提交申请
    $.post(ctx + "/thinkingreport/pbsThinkingreport/thinkRepAction", {
      "sReportid" : sReportid,
      "sType":"1",
      "sTranscontent":sTranscontent,
      "sTransmem": sTransmem,
    }, function(data) {
      if (data == "success") {
        mui.toast('接收成功', {
          duration : 'short',
          type : 'div'
        });
        window.location.href = ctx + "/thinkingreport/pbsThinkingreport/thinkRepList";
      } else {
        mui.toast('接收失败', {
          duration : 'short',
          type : 'div'
        });
      }
    });
  });

  // 判断为空
  function isEmpty(obj) {
    if (typeof obj == "undefined" || obj == null || obj == "") {
      return true;
    } else {
      return false;
    }
  }

});