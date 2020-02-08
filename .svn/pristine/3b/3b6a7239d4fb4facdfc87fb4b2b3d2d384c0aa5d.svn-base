/**
 * V 1.0 2018-7-26
 */
$(function() {
  // 开始填写时间
  var beginTime = new Date();
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

  // 查询部门信息
  $("#sDepartment").load(ctx + "/person/pbsDepartmentbind/namelist",
      function() {
         $("#sDepartment").val(sDepartment).select2();
      });

  // 保存按钮
  $("#btnSubmit").click(function() {
    var id = $(".gztt").attr("attrid");
    var sDepartment = $("#sDepartment").val();
    console.log(sDepartment);
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
    if (isEmpty(sDepartment)) {
      alertx("请您选择一个部门！");
      return;
    }
    // 如果当前的结果为真
    if (flag) {
      alertx("请您完成全部的结果！");
      return;
    }
    console.log(_doanswer);
    // 提交投票信息
    $.post(ctx + "/exam/testPC/onlineAction", {
      "sExampaper" : $("#paperid").attr("attrid"),
      "sDepartment" : sDepartment,
      "results" : _doanswer,
      "dtStart" : beginTime.format("yyyy-MM-dd hh:mm:ss"),
      "drSumbit" : new Date().format('yyyy-MM-dd  hh:mm:ss'),
    }, function(data) {
      console.log(ctx + data);
      if (data == "success") {
        window.location = ctx + "/exam/testPC/onlineList";
      }
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

// 时间 格式化
Date.prototype.format = function(format) {
  var date = {
    "M+" : this.getMonth() + 1,
    "d+" : this.getDate(),
    "h+" : this.getHours(),
    "m+" : this.getMinutes(),
    "s+" : this.getSeconds(),
    "q+" : Math.floor((this.getMonth() + 3) / 3),
    "S+" : this.getMilliseconds()
  };
  if (/(y+)/i.test(format)) {
    format = format.replace(RegExp.$1, (this.getFullYear() + '')
        .substr(4 - RegExp.$1.length));
  }
  for ( var k in date) {
    if (new RegExp("(" + k + ")").test(format)) {
      format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? date[k]
          : ("00" + date[k]).substr(("" + date[k]).length));
    }
  }
  return format;
}
