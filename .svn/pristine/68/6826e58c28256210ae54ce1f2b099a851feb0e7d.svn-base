/**
 * V 1.0 2018-3-27 13:36:50
 */
$(function() {
	// 任务id
	$("#sPartment").load(ctx + "/sys/pbsDepartmentetc/namelist?type=2",
		function() {
		  var selectString = $(this).val();
    //  查询 学员
		  $("#sMember").load(ctx + "/person/pbsPartymem/findPbsListByOffice?officeid="
				  +selectString);
		});
	// 修改 部门
	$("#sPartment").change(function() {
		$("#sMember").load(ctx + "/person/pbsPartymem/findPbsListByOffice", {
			"officeid" : $(this).val()
		}, function() {
			$("#sMember").val("");
		});
	});
	
	// 任务类型
	$("#sType").load(ctx + "/task/pbsTasktype/namelist");
	
	// 上级任务
	$("#sSuperiorid").load(ctx + "/task/pbsTaskrec/namelist?getType=0");
	
	// 点击
	$('#applySubmit').click(function() {
		var $form = $("#Applyform");
		var sMember = $form.find("#sMember").val();
		var sPartment = $form.find("#sPartment").val();
		var sContent = $form.find("#sContent").val();
		var sSuperiorid = $form.find("#sSuperiorid").val();
		var sType = $form.find("#sType").val();
		var sResume = $form.find("#sResume").val();
		var sDeadline = $form.find("#sDeadline").val();
		// 如果为空
		if (isEmpty(sResume)) {
			mui.toast('请填写标题', {
				duration : 'short',
				type : 'div'
			});
			return;
		}
		if (isEmpty(sMember)) {
			mui.toast('请选择接收人', {
				duration : 'short',
				type : 'div'
			});
			return;
		}
		
		if (isEmpty(sDeadline)) {
          mui.toast('请填写截止时间', {
              duration : 'short',
              type : 'div'
          });
          return;
      }
		
		var date = new Date();
		var deaLine = Date.parse(sDeadline);
		if(deaLine < date.getTime()){
			mui.toast('结束时间不能小于当前时间',{
				duration : 'short',
				type : 'div'
			});
			return;
		}
		// 提交申请
		$.post(ctx + "/task/pbsTaskrec/addapply", {
			"sBindmember":sMember,
			"sExecdepartment":sPartment,
			"sContent":sContent,
			"sSuperiorid":sSuperiorid,
			"sType":sType,
			"sResume":sResume,
			"sDeadline":sDeadline
		}, function(data) {
			if (data == "success") {
				mui.toast('提交成功', {
					duration : 'short',
					type : 'div'
				});
				// console.log(ctx + "/flow/deal/dealtList");
				 window.location.href = ctx + "/sys/Mobile/pageTurn?pageTo=/Nav-personal/arrange/index";
			} else {
				mui.toast('您不能给自己发送工作任务', {
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