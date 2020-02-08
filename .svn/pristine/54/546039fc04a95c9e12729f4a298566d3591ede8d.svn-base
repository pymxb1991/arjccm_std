		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					$("#btnSubmit").attr("disabled", true);
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#btnSubmit").removeAttr('disabled');
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
			
			//获取会议室
			$.getJSON(ctx + "/logistics/plmRoom/selectList",{ category: "01" }, function(
					data) {
				$("#roomIds").select2({
					placeholder: "请选择会议室",
				    allowClear: true,
				    data: data
				 });

			});
			//根据会议发起人自动获取user表中联系方式
			
		});
		//详情弹框--不刷新父页面
		function LayerDialog(src, title, height, width) {
			parent.layer.open({
				type : 2,
				title : title,
				area : [ height, width ],
				fixed : true, //固定
				maxmin : true,
				//btn: ['确定', '关闭'], //可以无限个按钮
				content : src,
			});
		}