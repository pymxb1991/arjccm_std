<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>评分详细列表</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
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
	        $.post(ctx + "/work/pbsComment/valueAction", {
	          "id": $("#pbsActivityrecId").attr("attrid"),
	          "values":values
	        }, function(data) {
	          if (data == "success") {
	           	alert("提交成功");
	            window.location.href = ctx+ "/work/pbsMeeting/list";
	          } else {
	            alert("提交失败");
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
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/work/pbsMeeting/">活动信息列表</a></li>
		<shiro:hasPermission name="work:pbsActivityrec:edit">
			<li class="active"><a href="">评分详细列表</a></li>
		</shiro:hasPermission>
	</ul>
	<div>
		<div>
			<a href="" style="color:black">评价人员:
				<span id="pbsActivityrecId" attrid="${pbsActivityrec.id}"> ${pbsActivityrec.SMastermem.SName}</span>
			</a>
		</div>
		<table id="contentTable"
			class="table table-striped table-bordered table-condensed">
			<thead>
				<tr>
					<th>人员姓名</th>
					<th>评价等级</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${NeedList}" var="need">
					<tr>
						<td>${need.SName}</td>
						<td>
							<select class="SelectIds" memId="${need.id}">
								<option value="A">优</option>
								<option value="B">良</option>
								<option value="C">中</option>
								<option value="D">差</option>
							</select>
						</td>
					</tr> 
				</c:forEach>
			</tbody>
		</table>
		<input id="applySubmit" class="btn btn-primary" type="button" value="提交评分"/>
	</div>
</body>
</html>

