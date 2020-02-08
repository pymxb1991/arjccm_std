<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>活动信息列表</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(function() {
		window.onbeforeunload = function(e) {
			var iTime = Math.floor(videoMedia.currentTime);
			// 显示 form 表单信息
			var courseid = $("#commentForm").attr("courseid");
			// post请求
			$.post(ctx + "/course/pbsCourseoperate/updateOperate", {
				"iTime" : iTime,
				"sParentid" : courseid,
				"sType" : "1",
				"sOpflag" : "1",
				"sOptype" : "1",
				"sOpresult":"1"
			}, function(data) {
			})
		}
		
		// 表单对象
		var $form = $("#commentForm");
		// 点击进行提交
		$form.find("#fromsave").click(function() {
			// 显示 form 表单信息
			var sContent = $form.find("#sContent").val();
			var courseid = $form.attr("courseid");
			// post请求
			$.post(ctx + "/course/pbsCourseoperate/commentSave", {
				"sContent" : sContent,
				"sParentid" : courseid,
				"sType" : "1",
				"sOpflag" : "1",
				"sOptype":"3",
				"sOpresult":"1"
					
			}, function(data) {
				// 成功
				if (data == "sucess") {
					alert("评价成功");
					$form.find("#sContent").val("");
				} else {
					alert("请填写评价内容");
				}
			})

		});

	});
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/question/pbsTestOnlinePC/coursePage">党建视频列表</a></li>
		<li  class="active"><a>视频播放</a></li>
		<li><a href="${ctx}/question/pbsTestOnlinePC/courseComment?id=${pbsCourseinfoEx.id}">评论信息</a></li>
	</ul>
	<div style="width:77%" align="center">
	       <div style="margin-left:20%; text-align: center;">
	       <h3 align="left">${pbsCourseinfoEx.SName}</h3>
			<p align="left">发布时间:<fmt:formatDate value="${pbsCourseinfoEx.createDate}" pattern="yyyy年MM月dd日  HH:mm" /> 
			| 来源：${pbsCourseinfoEx.SContent}</p></div>        
		<div id="videoContainer" style="margin-left:20%;">
			<video id="videoMedia" controls preload="auto" height="600px">
				<source src="${pbsCourseinfoEx.SFileurl}" type='video/mp4'>
				<source src="${pbsCourseinfoEx.SFileurl}" type="video/webm">
	   			<source src="${pbsCourseinfoEx.SFileurl}" type="video/ogg">
	   			<source src="${pbsCourseinfoEx.SFileurl}" type="video/avi">
			</video>
			<form id="commentForm" courseid="${pbsCourseinfoEx.id}">
				<input type="text"  style="display:none" value="${pbsActivityrec.id}" id="courseid"/>
		    	<div align="left">
		       		<input type="text" placeholder="说点什么吧" id="sContent"
		            style="width:80%; border: 1px solid rgba(0, 0, 0, .2); border-radius: 20px; margin-top: 5px;">
		            <a  href="${ctx}/question/pbsTestOnlinePC/courseComment?id=${pbsCourseinfoEx.id}" style="font-size: 14px;">共${commentSum}条评论</a>
		    	</div>
				<input id="fromsave" class="btn btn-primary" type="button" value="提交" style="margin-left:-94%"/>
			</form>
		</div>
	</div>
	
</body>
</html>