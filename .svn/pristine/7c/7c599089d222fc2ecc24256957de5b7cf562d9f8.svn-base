/**
 * V 1.0 2018-3-27 13:36:50
 */
$(function() {

  // 检测 点击 回复意见的 事件
  document.getElementById("prompt").addEventListener('tap', function(e) {
    e.detail.gesture.preventDefault(); // 修复iOS
    // 8.x平台存在的bug，使用plus.nativeUI.prompt会造成输入法闪一下又没了
    var btnArray = [ '取消', '确定' ];
    mui.prompt('请输入你的回复：', '回复内容', '', btnArray, function(e) {
      if (e.index == 1) {
        // 获取当前 建议内容 
        var sProposalid = $("#prompt").attr("areaid");
        var sContent  = e["value"];
        if(isEmpty(sContent)){
        	mui.toast('回复内容不能为空',{
        		duration:'short',
        		type:'div'
        	});
        	return false;
        }
        $.post(ctx + "/proposal/pbsProposal/proposalReplyAction", {
          "sProposalid" : sProposalid,
          "sContent" : sContent,
          // 回复类型为 0
          "sOpttype":"0",
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
      } else {

      }
    })
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