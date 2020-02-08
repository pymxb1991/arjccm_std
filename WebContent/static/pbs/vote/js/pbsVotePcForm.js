/**
 * V 1.0 2018-3-27 13:36:50
 */
$(function() {
  // 添加
  $('input[type="checkbox"],input[type="radio"]').iCheck({
    labelHover : false,
    cursor : true,
    checkboxClass : 'icheckbox_flat-red',
    radioClass : 'iradio_flat-red',
    increaseArea : '20%'
  });

  $('input:radio').each(function() {
    var $list = $(this).parents("ul.list");
    var result = $list.attr("attrid");
    $(this).bind('is.Checked', function(event) {
      // 返回选择
      $list.attr("attrid", this.id);
    })

  });

  $('input:checkbox').each(function() {
    var $list = $(this).parents("ul.list");
    $(this).bind('is.Checked is.Unchecked', function(event) {
      var result = $list.attr("attrid");
      var topid = this.id;
      // 添加状态
      if (event.namespace == "Checked") {
        if (isEmpty(result)) {
          result += topid;
        } else {
          result += "," + topid;
        }
      }
      // 去除状态
      if (event.namespace == "Unchecked") {
        result = result.replace("," + topid, "");
        result = result.replace(topid, "");
      }
      $list.attr("attrid", result);
    })
  });

  // 对比图
  $('.skillbar').each(function() {
    $(this).find('.skillbar-bar').animate({
      width : $(this).attr('data-percent')
    }, 2000);
  });

  // 当前的是否已经添加过
  var userCheck = $(".userCheck").attr("attrflag");
  if (userCheck == "true") {
    $(".list input").attr("disabled", "true");
  }

  // 保存按钮
  $("#btnSubmit").click(function() {
    var topicid = $("ul.list").attr("topicid");
    var subjectid = $("ul.list").attr("subjectid");
    var itemid = $("ul.list").attr("attrid");
    // 判断 是否已经过期
    var deadlineflag = $(".deadlineflag").attr("attr");
    // 
    if (deadlineflag == "true") {
      alertx("当前的投票已经过期");
      return;
    }
    // 如果选项为空
    if (isEmpty(itemid)) {
      alertx("请您选择一个投票选项！");
      return;
    }
    // 提交投票信息
    $.post(ctx + "/vote/pbsVotePc/savevote", {
      "topicid" : topicid,
      "subjectid" : subjectid,
      "itemid" : itemid,
    }, function(data) {
      window.location = ctx + data;
    });
    // savevote
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
