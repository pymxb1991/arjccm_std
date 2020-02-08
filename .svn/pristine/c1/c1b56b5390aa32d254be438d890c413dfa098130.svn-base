<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>活动详细信息</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
$(function() {
    var $form = $("#commentForm");
    var recid = "${pbsActivityrec.id}";
    // 点击进行提交
    $form.find("#fromsave").click(function() {
        // 显示 form 表单信息
        var sContent = $form.find("#sContent").val();
        var sActivityid = document.getElementById("courseid").value;
        // post请求
        $.post(ctx + "/work/pbsComment/commentSave", {
            "sContent" : sContent,
            "sActivityid" : sActivityid,
        }, function(data) {
            // 成功
            if (data == "sucess") {
                alertx("发布成功");
                window.location.href = ctx+ "/work/pbsComment/ActivityNewsInfo?id="+recid;
            } else {
            	alert("请填写评价内容");
            }
        })

    });

    // 点击事件
    $('#signInApp').click(
        function() {
           $.post(ctx + "/work/pbsComment/signinAction?id=${pbsActivityrec.id}",
              function() {
              	window.location.href = ctx+ "/work/pbsMeeting/list";
              });
        });
    var evaluateFlag = "${evaluateFlag}";
    var dealflag = "${Dealflag}";
    if (evaluateFlag == "false" && dealflag == "1") {
       document.getElementById("leaveApp").addEventListener(
          'click',
          function() {
        	  var reason = prompt("请假事由");
              $.post(ctx + "/work/pbsComment/leaveAction?id=" + recid
                  + "&reason=" + reason, function() {
                window.location.href = ctx
                    + "/work/pbsMeeting/list";
              });
          });
     }
  });
  
  function submitGra2(){
	  window.location.href = ctx + "/work/pbsComment/SendActivityValue?id=${pbsActivityrec.id}";
  }
  
  function submitGra(){
	  window.location.href = ctx + "/work/pbsComment/SendActivityValue?id=${pbsActivityrec.id}";
  }
</script>
</head>
<body>
	<c:set var="group" value="${group}"></c:set>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/work/pbsMeeting/">活动信息列表</a></li>
		<li class="active"><a href="">详细信息</a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="pbsActivityrec"
		action="${ctx}/work/pbsMeeting/save" method="post"
		class="form-horizontal">
		<form:hidden path="id" />
		<div class="control-group">
			<label class="control-label">活动类别：</label>
			<div class="controls">
				<form:select path="" class="input-xlarge" onchange="changeContent()" id="selectChange" disabled="true">
					<form:option value="" label="${fns:getDictList('actdefinitiontype')[group]}" />
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">活动标题：</label>
			<div class="controls">
				<form:input path="sTitle" htmlEscape="false" maxlength="30"
					class="input-xlarge required"  readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">活动类型：</label>
			<div class="controls">
				<form:input path="sType.sName" htmlEscape="false" maxlength="200"
					class="input-xlarge " readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">活动地点：</label>
			<div class="controls">
				<form:input path="sPlace" htmlEscape="false" maxlength="200"
					class="input-xlarge" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">开始时间：</label>
			<div class="controls">
				<input name="dtStarttime" type="text" readonly="readonly" maxlength="20" 
					class="input-medium Wdate"
					value="<fmt:formatDate value="${pbsActivityrec.getDtStarttime()}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">结束日期：</label>
			<div class="controls">
				<input name="dtStarttime" type="text" readonly="readonly" maxlength="20" 
					class="input-medium Wdate"
					value="<fmt:formatDate value="${pbsActivityrec.getDtEndtime()}" pattern="yyyy-MM-dd HH:mm:ss"/>" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">参与人员：</label>
			<div class="controls">
			<form:input path="sAttendants" htmlEscape="false" maxlength="500"
					class="input-xlarge" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">活动简介：</label>
			<div class="controls">
				<form:textarea path="sContent" htmlEscape="false" rows="4"
					maxlength="255" class="input-xxlarge" readonly="true"/>
			</div>
		</div>
	</form:form>
	<form  style="height: 100%;" id="commentForm">
	<c:if test="${evaluateFlag eq false}">
		<c:choose>
			<c:when test="${Dealflag eq '0'}">
				<input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" style="margin-left:11%"/>
			</c:when>
			<c:when test="${Dealflag eq '1'}">
				<c:if test="${operateflag eq false}">
					<input id="signInApp" class="btn btn-primary" type="button" value="签到" style="margin-left:11%" />
					<input id="leaveApp" class="btn btn-primary" type="button" value="请假" />
				</c:if>
				<c:if test="${operateflag eq true}">
					<input id="btnCancel" class="btn" type="button" value="返 回"
					onclick="history.go(-1)" style="margin-left:11%"/>
				</c:if>
			</c:when>
			<c:otherwise>
				 
	        		<input type="text"  style="display:none" value="${pbsActivityrec.id}" id="courseid"/>
	            	<div style="height: 100%;">
	               		<input type="text" placeholder="说点什么吧" id="sContent"
	                    style="width: 50%; border: 1px solid rgba(0, 0, 0, .2); border-radius: 20px; margin-top: 5px;width:26%;margin-left:11%">
	                	<a href="${ctx}/work/pbsComment/comment?id=${pbsActivityrec.getId()}" style="font-size: 14px;">共${comments}条评论</a>
	            	</div>
	            	<input id="fromsave" class="btn btn-primary" type="button" value="提交" style="margin-left:11%;" />
	        	
			</c:otherwise>
		</c:choose>
	</c:if>
	<!-- 非评价功能 -->
	<c:if test="${evaluateFlag eq true}">
		<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)" style="margin-left:11%"/>
	</c:if>
	<c:if test="${ userFlag eq 'true'}">
		<c:if test="${activityflag ne '2' }">
			<c:if test="${valueflag eq true}">
				<input onclick="submitGra()" class="btn btn-primary" type="button" value="评分"/>
			</c:if>
		</c:if>
		<c:if test="${activityflag eq '2' }">
			<c:if test="${valueflag eq true}">
				<input onclick="submitGra2()" class="btn btn-primary" type="button" value="评分"/>
			</c:if>
		</c:if>
	</c:if>
	</form>
</body>
</html>

