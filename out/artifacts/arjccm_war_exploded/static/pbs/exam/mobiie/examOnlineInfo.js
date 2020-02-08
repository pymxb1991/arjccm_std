/**

 * V 1.0 2018-6-12
 */
$(function() {
  // 开始填写时间
  var beginTime = new Date();
  // 页面高度
  var height = document.documentElement.clientHeight
      || document.body.clientHeight;
  $(".item-logo").height(height)

  $('#begin_2').click(function() {
    $('.topbox').css('display', 'none');
    $('.test:first').css('display', 'block');
  });

  $('li.radio').click(function() {
    $(this).parents('ul').children('li').removeClass('on');
    $(this).children(":radio").prop("checked", true);
    $(this).addClass('on');
  });

  $('li.checkbox').click(function() {
    // $(this).children(":radio ").prop("checked", true);
    $(this).toggleClass('on');
  });

  $(':radio').click(function() {
    $(this).parents('ul').children('li').removeClass('on');
    $(this).parents('li').addClass('on');
  });

  // next 按钮 兼具获取并确定选定值的功能
  $(':checkbox').click(function() {
    $(this).parents('li').toggleClass('on');
  });

  $('.nextTest').click(function() {
    // 当前的表单
    var $from = $(this).parents('div.test');
    var _result = "";

    if ($(this).attr("stype") == "1") {
      // 遍历当前的值
      $from.find(".item_con input[type=checkbox]").each(function() {
        if (this.checked) {
          // 获取的值
          var checkval = $(this).val();
          // 获取当前的值
          _result += (_result == "") ? checkval : ("、" + checkval);
        }
      });
    } else {
      _result = $from.find(".item_con input[type='radio']:checked").val();
    }

    console.log(_result);
    if ((_result == undefined)) {
      mui.toast('请选择一个', {
        duration : 'short',
        type : 'div'
      });
      return;
    }
    // 赋值当前的值信息
    $from.find(".answer").attr("result", _result);
    // 隐藏当前的选择题
    $(this).parents('div.test').css('display', 'none');
    // 显示下一个选择题
    $(this).parents('div.test').next('div.test').css('display', 'block');
  });

  // 点击上一个按钮
  $('.prevousTest').click(
      function() {
        // $(this).parents('li').addClass('on');
        // alert( $(this).parents('li').html());
        var select = $(this).siblings('ul').children('.on').children('input')
            .val();
        var answer = $(this).parents('div').children(':hidden').val();
        var sumScore = $('#sumScore').val();
        // 隐藏当前的值
        $(this).parents('div.test').css('display', 'none');
        // 显示下一个选择题
        $(this).parents('div.test').prev('div.test').css('display', 'block');

      });

  // 结束按钮
  $('.endTest').click(function() {
    // 表单
    var $from = $(this).parents('div.test');
    var _result = "";
    if ($(this).attr("stype") == "1") {
      // 遍历当前的值
      $from.find(".item_con input[type=checkbox]").each(function() {
        if (this.checked) {
          // 获取的值
          var checkval = $(this).val();
          // 获取当前的值
          _result += (_result == "") ? checkval : ("、" + checkval);
        }
      });
    } else {
      _result = $from.find(".item_con input[type='radio']:checked").val();
    } 
    $from.find(".answer").attr("result", _result);
    /** 返回的结果 */
    var _doanswer = "";
    // 遍历当前所有的答案结果
    $("#testSelfForm").find(".item_con .answer").each(function(i, element) {
      // 获取的结果
      var itemResult = $(this).attr("attrid") + "," + $(this).attr("result");
      // 新增当前的内容
      _doanswer += (_doanswer == "") ? itemResult : (";" + itemResult);
    });

//    var date = new Date();        
//    window.alert(date.pattern("yyyy-MM-dd hh:mm:ss"));  
    // 接收数据
    var sDepartment = $("#sDepartment").attr("attrid");
    $.post(ctx + "/exam/selftest/onlineAction", {
      "sExampaper":$("#paperid").attr("attrid"),
      "sDepartment":sDepartment,
      "dtStart" : beginTime.format("yyyy-MM-dd hh:mm:ss"),
      "drSumbit":new Date().format('yyyy-MM-dd  hh:mm:ss'),
      "results" : _doanswer
    }, function(data) {
      if (data == "success") {
        mui.toast('提交成功', {
          duration : 'short',
          type : 'div'
        });
        var sDepartment =$("#sDepartment").attr("attrid");
        // console.log(ctx + "/flow/deal/dealtList");
        window.location.href = ctx + "/exam/selftest/onlineList?sDepartment="+sDepartment;
      } else {
        mui.toast('提交失败', {
          duration : 'short',
          type : 'div'
        });
      }
    });
    console.log(_doanswer);
  });

  // 分享按钮
  $('.share').click(function() {
    $('.share_b').css('display', 'block');
    location.href = "#title";
  });

  $('.share_b').click(function() {
    $(this).css('display', 'none');
  });
  $('#close').click(function() {
    $('.guanzhu1').css('display', 'none');
  });

});

//时间 格式化
Date.prototype.format = function(format) { 
    var date = { 
           "M+": this.getMonth() + 1, 
           "d+": this.getDate(), 
           "h+": this.getHours(), 
           "m+": this.getMinutes(), 
           "s+": this.getSeconds(), 
           "q+": Math.floor((this.getMonth() + 3) / 3), 
           "S+": this.getMilliseconds() 
    }; 
    if (/(y+)/i.test(format)) { 
           format = format.replace(RegExp.$1, (this.getFullYear() + '').substr(4 - RegExp.$1.length)); 
    } 
    for (var k in date) { 
           if (new RegExp("(" + k + ")").test(format)) { 
                  format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? date[k] : ("00" + date[k]).substr(("" + date[k]).length)); 
           } 
    } 
    return format; 
} 
  