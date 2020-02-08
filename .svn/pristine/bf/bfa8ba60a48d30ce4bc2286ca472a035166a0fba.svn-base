var ajax_option = {},validateForm = null;
$(function(){
		//关闭弹框事件
		$('#btnCancel').click(function() {
			parent.layer.close(parent.layerIndex);
		})
		//默认最大化
		parent.layer.full(parent.layerIndex);
		//隐藏掉最小化按钮
		parent.layer.style(parent.layerIndex, {
			maxmin: false
		});
		init();
		$("#startTime").click(function () {
		    WdatePicker({
		        el: "startTime", //点击对象id，一般可省略el
		        lang: 'auto', //语言选择，一般用auto
		        dateFmt: 'yyyy-MM-dd HH:mm:ss', //时间显示格式，年月日 时分秒，年月日就是yyyy-MM-dd
		        minDate: '%y-%M-%d', //最小日期
		        readOnly: true, //是否只读
		        isShowClear: true, //是否显示“清空”按钮
		        isShowOK: true, //是否显示“确定”按钮
		        isShowToday: true, //是否显示“今天”按钮
		        autoPickDate: true //为false时 点日期的时候不自动输入,而是要通过确定才能输入，为true时 即点击日期即可返回日期值，为null时(推荐使用) 如果有时间置为false 否则置为true
		    })
		});
		$("#endTime").click(function () {
		    WdatePicker({
		        el: "endTime", //点击对象id，一般可省略el
		        lang: 'auto', //语言选择，一般用auto
		        dateFmt: 'yyyy-MM-dd HH:mm:ss', //时间显示格式，年月日 时分秒，年月日就是yyyy-MM-dd
		        minDate: '#F{$dp.$D(\'startTime\')}',
		        readOnly: true, //是否只读
		        isShowClear: true, //是否显示“清空”按钮
		        isShowOK: true, //是否显示“确定”按钮
		        isShowToday: true, //是否显示“今天”按钮
		        autoPickDate: true //为false时 点日期的时候不自动输入,而是要通过确定才能输入，为true时 即点击日期即可返回日期值，为null时(推荐使用) 如果有时间置为false 否则置为true
		    })
		});

		ajax_option={
			type: 'POST',
			url:ctx +"/fence/ccmElectronicFence/save",
			dataType: 'json',
			success:function(data){
				window.parent.document.getElementById("mainFrame").setAttribute("src",ctx+"/fence/ccmElectronicFence/");
				parent.layer.close(parent.layerIndex);
			}
		};
	    //验证
		validateForm = $("#inputForm").validate(
			{
				errorContainer : "#messageBox",
				errorPlacement : function(error, element) {
					$("#btnSubmit").removeAttr('disabled');
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")
							|| element.is(":radio")
							|| element.parent().is(
									".input-append")) {
						error.appendTo(element.parent()
								.parent());
					} else {
						error.insertAfter(element);
					}
					return false;
				}
			}
		);
		initData();
})
var subInfo = {},newSubInfo = {};
function initData(){
	subInfo = {},
	t = $('#inputForm').serializeArray();
	$.each(t, function () {
		subInfo[this.name] = this.value;
	});
	if(subInfo.id !== ""){//添加
		Map.markInfo(subInfo.id,"elecFence",[subInfo]);//编辑地图范围
		if(subInfo.gravity=="LineString"){
			subInfo.gravity="Polygon";
		}
		Map.addGraphical(subInfo.gravity);
	}else{
		Map.markInfoType = "elecFence";
	}
};
$('#btnSubmit').click(function(){
	if(validateForm.form()){
		$('#inputForm').ajaxSubmit(ajax_option);
		return false;
	}
});
