<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>出库单管理</title>
<meta name="decorator" content="default" />
<link href="${ctxStatic}/common/zztable.css" type="text/css"
	rel="stylesheet">
<!-- 表格试表单css -->
<link href="${ctxStatic}/common/zzformtable.css" type="text/css"
	rel="stylesheet">
<link rel="stylesheet"
	href="${ctxStatic}/jquery-ui-1.12.1/jquery-ui.min.css">
<script src="${ctxStatic}/jquery-ui-1.12.1/jquery-ui.min.js"></script>
<script type="text/javascript"
	src="${ctxStatic}/plm/storage/ajaxMessageAlert.js"></script>
<script type="text/javascript" src="${ctxStatic}/plm/storage/storageValidate.js"></script>
<script type="text/javascript">
	$(function() {
		$("#equipmentCode").keyup(
				function(event) {
					if (event.keyCode == 13) {
						$.ajax({
							url : "${ctx}/storage/plmMinusandAddDetail/getEquipmentGiveBackCode",
							type : "post",
							data : {
								"equipmentCode" : $(this).val()
							},
							async : true,
							cache : false,
							timeout : 30000,
							success : function(data) {
								var content = jQuery.parseJSON(data);
								if (content === undefined || content == null
										|| content == "") {
									messageAlert("添加失败！", "error");
									return;
								}
								if (content.ret != 0) {
									messageAlert(content.message, "error");
									return;
								}
								messageAlert("添加成功！", "success");
								$("#addEqu").append(content.result);
							},
							error : function(jqXHR, textStatus, errorThrown) {
								messageAlert("添加失败！", "error");
								console.error("jqXHR:", jqXHR);
								console.error("textStatus:", textStatus);
								console.error("errorThrown:", errorThrown);
								top.$.jBox.closeTip();
							}
						});
						$("#equipmentCode").attr("value", "");
					}
				});
		var idAndNum = "";
		var flag = true; //是否是添加
		$("#addEqu").on("click", "a", function() {
			if ($(this).attr("title") == "delete") {
				$(this).parent().parent().remove();
			}
			if ($(this).attr("title") == "num") {
				idAndNum = $(this).next().val();
				$(this).next().attr("id", idAndNum);
				$("#editNum").dialog("open");
			}
		});
		$("a[title='numDetail']").on("click", function() {
			var id = $(this).prev().attr("id");
			idAndNum = id;
			flag = false;
			$("#editNum").dialog("open");
		});
		$(".trtop").css({
			"width" : "15%"
		});
	});
</script>
</head>
<body>
	<br />
		<sys:message content="${message}" />
			<div>
				<label class="control-group">物资编号：</label>
				<input type="text" id="equipmentCode" class="input-xlarge"
					style="margin-left: 25px;" placeholder="请扫码或输入具体物资编号..." />
			</div>
			<table id="contentTable"
			class="table table-striped table-bordered table-condensed">
				<thead>
					<tr>
						<th>物资编号</th>
						<th>物资名称</th>
						<th>规格型号</th>
						<th>物资类别</th>
						<th>物资子类</th>
						<th>物资数量</th>
						<th>归还状态</th>
					</tr>
				</thead>
				<tbody id="addEqu">
				</tbody>
			</table>
		
</body>
</html>