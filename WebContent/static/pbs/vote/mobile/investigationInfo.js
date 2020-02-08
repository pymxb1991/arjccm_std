/**
 */
var height = document.documentElement.clientHeight || document.body.clientHeight;
var bg = document.getElementById('bg');
var end = document.getElementById('end');
bg.style.cssText = "height: " + height + "px";
end.style.cssText = "height: " + height + "px";
// 结果
var sumScore = '';
$(function() {
	// 显示 程度
	mui(".mui-progressbar").each(function() {
		mui(this).progressbar({
			progress : this.getAttribute("data-progress")
		}).show();
	});
    // 进入 页面
    $('#begin_2').click(function() {
        $('.topbox').css('display', 'none');
        $('.test:first').css('display', 'block');
    });
    // 进入 页面
    $('#backList').click(function() {
      window.location.href = ctx + "/vote/pbsVoteMobile/investigationlist";
    });
    
    // 提交按钮
    $('#Submit').click(function() {
        var topicid = $("#begin_2").attr("topid");
        $.post(ctx + "/vote/pbsVoteMobile/saveinvestigation", {
            "topicid": topicid,
            "results": sumScore
        },
        function(data) {
            if (data == "true") {
                mui.toast('提交成功', {
                    duration: 'short',
                    type: 'div'
                });
            }else{
            	 mui.toast('提交失败', {
                     duration: 'short',
                     type: 'div'
                 });
            }
            window.location.href = ctx + "/vote/pbsVoteMobile/investigationlist";
        });
    });
    //  返回按钮
    $('#Back').click(function() {
    	 window.location.href = ctx + "/vote/pbsVoteMobile/investigationlist";
    });
});
// 单选按钮
function radio(obj) {
    $(obj).parents('ul').children('li').removeClass('on');
    $(obj).parents('li').addClass('on');
};
// 多选按钮
function checkbox(obj) {
    $(obj).parents('li').toggleClass('on');
};

// 下一个按钮
function next(obj) {
    var selects = new Array();
    var id = $(obj).parents('div.item_con').siblings('div.tit')[0].id;
    var length = $(obj).siblings('ul').children('.on').length;
    if (length > 1) {
        for (i = 0; i < length; i++) {
            var select = $(obj).siblings('ul').children('.on').eq(i).children('input').val();
            selects.push(select);
        }
    } else {
        var selects = $(obj).siblings('ul').children('.on').children('input').val();
    }
    // 获取当前 是否已经 添加过 是否已经过期
    var userCheck = $("#begin_2").attr("userCheck");
    var deadlineflag = $("#begin_2").attr("deadlineflag");
    if ((selects == undefined) && (userCheck == "false")&& (deadlineflag == "false")) {
        mui.toast('请选择一个', {
            duration: 'short',
            type: 'div'
        });
    } else {
        if ((userCheck == "false") && (deadlineflag == "false") ) {
            // 获取一个
            select = selects.toString();
            var result = id + ":" + select + ";";
            sumScore += result;
        }
        $(obj).parents('div').css('display', 'none');
        if ($(obj).parents('div').next('div').hasClass('end')) {
            $('#end').css('display', 'block');
        } else {
            $(obj).parents('div').next('div').css('display', 'block');
        }
    }
}
