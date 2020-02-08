$(document).ready(
		function() {
			$("#inputForm").validate(
					{
						rules : {
							productDate : "required",
							buyDate : {
								required : true,
								compareDate : "#productDate"
							},
							annualDate : {
								required : true,
								compareDate : "#productDate"
							},
							inspectionDate : {
								required : true,
								compareDate : "#annualDate",
								compareDate : "#buyDate"
							}
						},
						messages : {
							productDate : "必填信息",
							buyDate : {
								required : "必填信息",
								compareDate : "购车日期应大于出厂日期"
							},
							annualDate : {
								required : "必填信息",
								compareDate : "年检日期应大于出厂日期"
							},
							inspectionDate : {
								required : "必填信息",
								compareDate : "下次年检日期应大于年检日期",
								compareDate : "下次年检日期应大于购车日期"
							}
						},

						submitHandler : function(form) {
							$("#btnSubmit").attr("disabled", true);
					loading('正在提交，请稍等...');
							form.submit();
						},
						errorContainer : "#messageBox",
						errorPlacement : function(error, element) {
							$("#btnSubmit").removeAttr('disabled');
					$("#messageBox").text("输入有误，请先更正。");
							if (element.is(":checkbox") || element.is(":radio")
									|| element.parent().is(".input-append")) {
								error.appendTo(element.parent().parent());
							} else {
								error.insertAfter(element);
							}
						}
					});

		});