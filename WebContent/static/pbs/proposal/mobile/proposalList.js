/**
 * V 1.0 2018-3-27 13:36:50
 */
$(function() {

  $("#sAreaid").load(ctx + "/proposal/pbsProposalarea/namelist");
  
  /*$("#sPartment").load(ctx + "/sys/pbsDepartmentetc/namelist?type=2",
      function() {
        var selectString = $(this).val();
  //  查询学员
        $("#sAcceptermem").load(ctx + "/person/pbsPartymem/findPbsListByOffice?officeid="
                +selectString);
      });
  // 修改 部门
  $("#sPartment").change(function() {
      $("#sAcceptermem").load(ctx + "/person/pbsPartymem/findPbsListByOffice", {
          "officeid" : $(this).val()
      }, function() {
          $("#sAcceptermem").val("");
      });
  });*/

  var $form = $("#applysubmit");
  // 转发
  $('#acceptSubmit').click(function() {
    // 必须接收人
    var sAreaid = $form.find("#sAreaid").val();
    var sContent = $form.find("#sContent").val();
    var sShowtype = $form.find("#sShowtype").val();
    /*var sAcceptermem = $form.find("#sAcceptermem").val();*/
    var sTitle =$form.find("#sTitle").val();
    if(sTitle == "" || sTitle == undefined){
    	mui.toast('标题不能为空', {
            duration : 'short',
            type : 'div'
         });
    	return;
    }
    // 提交申请
    $.post(ctx + "/proposal/pbsProposal/proposalSubmit", {
      "sAreaid" : sAreaid,
      "sTitle" : sTitle,
      "sShowtype" : sShowtype,
      /*"sAcceptermem":sAcceptermem,*/
      "sContent" : sContent,
    }, function(data) {
      if (data == "success") {
        mui.toast('接收成功', {
          duration : 'short',
          type : 'div'
        });
        window.location.href = ctx + "/proposal/pbsProposal/proposalList";
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