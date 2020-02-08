/**
 * V 1.0 2018-7-26 
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

  // 单选按钮
  $('input:radio').each(function() {
    var $answer = $(this).parents("ul.list").find(".answer");
    var result = $answer.attr("result");
    $(this).bind('is.Checked', function(event) {
      // 返回选择
      $answer.attr("result", this.value);
    })
  });


  // 当前的是否已经添加过
  var userCheck = $(".userCheck").attr("attrflag");
  if (userCheck == "true") {
    $(".list input").attr("disabled", "true");
  }

  // 复选框
  $('input:checkbox').each(function() {
    var $answer = $(this).parents("ul.list").find(".answer");
    $(this).bind('is.Checked is.Unchecked', function(event) {
      // 返回选择
      
      var result = $answer.attr("result");
      var topid = this.value;
      // 添加状态
      if (event.namespace == "Checked") {
        if (isEmpty(result)) {
          result += topid;
        } else {
          result += "、" + topid;
        }
      }
      // 去除状态
      if (event.namespace == "Unchecked") {
        result = result.replace("," + topid, "");
        result = result.replace(topid, "");
      }
      $answer.attr("result", result);
    })
  });

  // 保存按钮
  $("#btnSubmit").click(function() {
    var id = $(".gztt").attr("attrid");
    var sDepartment = $(this).attr("sDepartment");
    // 当前是否有未添加答案的 结果
    var flag = false;
    // 最终的答案
    var _doanswer = "";
    // answer
    $("#inputForm").find(".answer").each(function() {
      // 获取的结果
      var itemResult = $(this).attr("attrid") + "," + $(this).attr("result");
      // 新增当前的内容
      _doanswer += (_doanswer == "") ? itemResult : (";" + itemResult);
      if (isEmpty($(this).attr("result"))) {
        flag = true;
      }
    });
    // 如果当前的结果为真
    if (flag) {
      alertx("请您完成全部的结果！");
      return;
    }
    console.log(_doanswer);
    // 提交投票信息
    $.post(ctx + "/exam/testPC/examAction", {
      "id" : id,
      "sDepartment" : sDepartment,
      "results" : _doanswer,
    }, function(data) {
      console.log(ctx + data);
      if (data == "success") {
        window.location = ctx + "/exam/testPC/selftestList";
      }
    });
    // // savevote
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
