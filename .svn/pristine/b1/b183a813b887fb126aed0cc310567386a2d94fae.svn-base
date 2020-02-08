/**
 * V 1.0 2018-3-27 13:36:50
 */
$(function() {

  $('#acceptSubmit').click(function() {

    var proposalid = $(this).attr("proposalid");
    var sOpresult = $("#sOpresult").val();
    var sContent = $("#sContent").val();
    $.post(ctx + "/proposal/pbsProposal/proposalHandleAction", {
      "sProposalid" : proposalid,
      "sContent" : sContent,
      "sOpresult" :sOpresult,
      // 审核类型为 1
      "sOpttype":"1",
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