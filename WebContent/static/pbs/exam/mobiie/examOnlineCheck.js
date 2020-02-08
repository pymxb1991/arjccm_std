/**
 * V 1.0 2018-6-12
 */
$(function() {
	var height = document.documentElement.clientHeight
			|| document.body.clientHeight;
	$(".item-logo").height(height)

	// 默认开始了
	// $('.topbox').css('display', 'none');
	$('.test:first').css('display', 'block');

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
		// 隐藏当前的选择题
		$(this).parents('div.test').css('display', 'none');
		// 显示下一个选择题
		$(this).parents('div.test').next('div.test').css('display', 'block');
	});

	// 点击上一个按钮
	$('.prevousTest').click(function() {
		// 隐藏当前的值
		$(this).parents('div.test').css('display', 'none');
		// 显示下一个选择题
		$(this).parents('div.test').prev('div.test').css('display', 'block');

	});

	// 结束按钮
	$('.endTest').click(function() {
	  var sDepartment =$("#sDepartment").attr("attrid");
		window.location.href = ctx + "/exam/selftest/onlineList?sDepartment="+sDepartment;
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