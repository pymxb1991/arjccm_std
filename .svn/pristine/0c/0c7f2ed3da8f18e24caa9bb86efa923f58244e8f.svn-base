/**
 * V 1.0 2018-3-27 13:36:50
 */
$(function() {
  // 显示 程度
  mui(".mui-progressbar").each(function() {
    mui(this).progressbar({
      progress : this.getAttribute("data-progress")
    }).show();
  });

  // 添加点击事件
  $("#voteButtton").click(function() {
    var itemid = $('input[name="radio1"]:checked').val();
    var subjecid = $(this).attr("subjecid");
    var topicid = $(this).attr("topicid");
    var flag = $(".deadline").attr("attr");
    // 
    if (flag == "true") {
      mui.toast('当前的投票已经过期', {
        duration : 'short',
        type : 'div'
      });
      return;
    }
    // 
    if (isEmpty(itemid)) {
      mui.toast('请选择一个投票内容', {
        duration : 'short',
        type : 'div'
      });
      return;
    }

    $.post(ctx + "/vote/pbsVoteMobile/savevote", {
      "topicid" : topicid,
      "subjectid" : subjecid,
      "itemid" : itemid
    }, function(data) {
      if (data == "yes") {
        mui.toast('添加成功', {
          duration : 'short',
          type : 'div'
        });
        location.reload();
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
