/**
 * V 1.0 2018-4-20
 */
$(function() {

  $('#applySubmit').click(
      function() {
        var $form = $("#Applyform");
        var values  = "";
        $(".SelectIds").each(function(i,e){
          var newString =  $(e).attr("memId")+","+ $(e).val();
          if(i>0){
            values   += ";"+newString;  
          }else{
            values   +=newString;  
          }
        });
        console.log(values);
        // 提交申请
        $.post(ctx + "/Activity/attend/valueAction", {
          "id": $("#pbsActivityrecId").attr("attrid"),
          "values":values
        }, function(data) {
          if (data == "success") {
            mui.toast('提交成功', {
              duration : 'short',
              type : 'div'
            });
            // console.log(ctx + "/flow/deal/dealtList");
            window.location.href = ctx
                + "/Activity/attend/SendActivityList";
          } else {
            mui.toast('提交失败', {
              duration : 'short',
              type : 'div'
            });
          }
        });  
      });

  

//判断为空
function isEmpty(obj) {
  if (typeof obj == "undefined" || obj == null || obj == "") {
    return true;
  } else {
    return false;
  }
}

});

