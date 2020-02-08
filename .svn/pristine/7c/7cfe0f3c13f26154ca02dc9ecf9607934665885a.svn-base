/**
 * V 1.0 2018-5-9 15:49:13
 */
$(function() {
  // 获取 taskid
  var taskid = $(".feedbackoprecsFlag").attr("taskid");
  // 获取 是否反馈了
  var taskValueFlage = $(".feedbackoprecsFlag").attr("flag");
  // 是否存在
  var existFlag = $(".feedbackoprecsFlag").attr("SStatus");

  // 判断 当前的 状态
  if (taskValueFlage == "true") {
    if (existFlag == '99') {
      return;
    }
    // 添加 评价与 撤销按钮
    document.getElementById("confirmBtn").addEventListener(
        'tap',
        function() {
          var btnArray = [ '评价', '关闭' ];
          var btnConfirm = [ '确定', '取消' ];
          mui.confirm('评价任务或是关闭任务？', '任务关闭', btnArray, function(e) {
            if (e.index == 1) {
              mui.confirm('确定关闭任务？', '确认关闭', btnConfirm, function(e) {
                if (e.index == 0) {
                  // 取消 任务
                  $.post(ctx + "/task/pbsTaskrec/cancelTask?id=" + taskid,
                      function() {
                        window.location.href = ctx
                            + "/task/pbsTaskrec/distributedList";
                      });
                }
              })
            } else {
              location.replace(ctx + "/task/pbsTaskrec/distributedfeedback?id="
                  + taskid);
            }
          })
        });
  } else if (taskValueFlage == "false") {
    if (existFlag == '99') {
      return;
    }
    // cancelBtn
    document.getElementById("cancelBtn").addEventListener('tap', function() {
      var btnConfirm = [ '确定', '取消' ];
      mui.confirm('确定关闭任务？', '任务取消', btnConfirm, function(e) {
        if (e.index == 0) {
          // 取消 任务
          $.post(ctx + "/task/pbsTaskrec/cancelTask?id=" + taskid, function() {
            window.location.href = ctx + "/task/pbsTaskrec/distributedList";
          });
        }
      })
    });
  }

});