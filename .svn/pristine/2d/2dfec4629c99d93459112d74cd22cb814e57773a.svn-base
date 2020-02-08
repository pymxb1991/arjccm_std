<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>盘点详细管理</title>
	<meta name="decorator" content="default"/>
	<link rel="stylesheet" href="${ctxStatic}/jquery-ui-1.12.1/jquery-ui.min.css">
	<script src="${ctxStatic}/jquery-ui-1.12.1/jquery-ui.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/plm/storage/ajaxMessageAlert.js"></script>
	<style type="text/css">
		.importLoading{
			background-image: "${ctxStatic}/plm/storage/img/loading.gif";
		}
	</style>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#inputCode").keyup(function(event) {
				if (event.keyCode == 13) {
					$.ajax({
						url : "${ctx}/storage/plmCheck/getCheckInfo",
						type : "post",
						data : {
							"code" : $(this).val(),
							"parent" : $("#checkId").val()
						},
						async : true,
						cache : false,
						timeout : 30000,
						success : function(data) {
							if (data === undefined || data == null
									|| data == "") {
								messageAlert("盘点验证失败！", "error");
								return;
							}
							if (data.ret != 0) {
								messageAlert(data.message, "error");
								return;
							}
							messageAlert("盘点验证成功！", "success");
							$("#addEqu").append(data.result);
						},
						error : function(jqXHR, textStatus, errorThrown) {
							messageAlert("盘点验证失败！", "error");
							console.error("jqXHR:", jqXHR);
							console.error("textStatus:", textStatus);
							console.error("errorThrown:", errorThrown);
							top.$.jBox.closeTip();
						}
					});
					$("#inputCode").attr("value", "");
				}
			});
			$( "#fileImport" ).dialog({
			    autoOpen: false,
			    closeOnEscape: false,
			    height: 170,
			    width: 300,
			    modal: true,
			    title:"选择导入编号文本文件",
			    buttons:{
			    	"导入":function(){
			    		var formData = new FormData();
					    var name = $("#file").val();
					    formData.append("file",$("#file")[0].files[0]);
					    formData.append("name",name);
					    $('#loading').show();
						    $.ajax({
						        url : '${ctx}/storage/plmCheck/importCheckCode',
						        type : 'POST',
						        async : true,
						        data : formData,
						        // 告诉jQuery不要去处理发送的数据
						        processData : false,
						        // 告诉jQuery不要去设置Content-Type请求头
						        contentType : false,
						        success : function(data) {
						        	var content = jQuery.parseJSON(data);
									if (content === undefined || content == null
											|| content == "") {
										messageAlert("导入验证失败！", "error");
										  $('#loading').hide();
										return;
									}
									if (content.ret != 0) {
										messageAlert(content.message, "error");
										  $('#loading').hide();
										return;
									}
									messageAlert("导入验证成功！", "success");
									$("#fileImport").dialog( "close" );
									  $('#loading').hide();
									for (var i = 0; i < content.result.length; i++) {
										$("#addEqu").append(content.result[i]);
									}
						        },
						        error : function(jqXHR, textStatus, errorThrown) {
									messageAlert("导入验证失败！", "error");
									 $('#loading').hide();
									console.error("jqXHR:", jqXHR);
									console.error("textStatus:", textStatus);
									console.error("errorThrown:", errorThrown);
									top.$.jBox.closeTip();
								}
						    });
			    	},
			    	"取消":function(){
			    		$( this ).dialog( "close" );
			    	}
			    },
			    close: function() {
			    	$( this ).dialog( "close" );
			    }
			  });
            $("#import").on("click", function(){
				$("#fileImport").dialog( "open" );
            });
            $("#complete").on("click", function(){
            	 $.ajax({
					url : "${ctx}/storage/plmCheck/checkComplete",
					type : "post",
					data : {
						"parent" : $("#checkId").val()
					},
					async : true,
					cache : false,
					timeout : 30000,
					success : function(data) {
						var content = jQuery.parseJSON(data);
						if (content === undefined || content == null
								|| content == "") {
							messageAlert("确认完成失败！", "error");
							
							return;
						}
						if (content.ret != 0) {
							messageAlert(content.message, "error");
							return;
						}
						$("#fileImport").dialog("close");
						messageAlert("确认完成成功！请滚动列表查看异常记录！", "success");
						$("#addEqu").append(content.result);
					},
					error : function(jqXHR, textStatus, errorThrown) {
						messageAlert("确认完成失败！", "error");
						console.error("jqXHR:", jqXHR);
						console.error("textStatus:", textStatus);
						console.error("errorThrown:", errorThrown);
						top.$.jBox.closeTip();
					}
				}); 
            });
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs" style="position: fixed;">
		<li class="active"><a>盘点详细列表</a></li>
		<c:if test="${plmCheck.status != '3'}">
		<li><input id="inputCode" type="text" placeholder="请扫描二维码或输入物资编号..." style="margin-left: 50px;margin-top: 5px;"/></li>
		<li><input id="import" class="btn btn-primary" type="button" value="导入" style="margin-left: 10px;margin-top: 7px;"/></li>
		<li><input id="btnImportTemplate" class="btn btn-primary"  type="button" style="margin-left: 10px;margin-top: 7px;" value="模板下载 " onclick="location.href='${ctxStatic}/template/excel/checkMaterial.xlsx'"/></li>
		<input id="checkId" type="hidden" value="${plmCheck.id}">
		<li style="margin-left: 10px;margin-top: 7px;"><input id="complete" class="btn btn-primary" type="button" value="完成盘点"/></li>
		</c:if>
	</ul>
	<sys:message content="${message}"/>
	<div style="position: fixed;margin-top: 45px;"><h3>盘点结果列表</h3></div>
	<div style="height: 85px;"></div>
	<div style="overflow: auto;height: 350px;">
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>物资编号</th>
				<th>物资名称</th>
				<th>规格型号</th>
				<th>物资类别</th>
				<th>物资子类别</th>
				<th>物资数量</th>
				<th>计量单位</th>
				<th>盘点结果</th>
			</tr>
		</thead>
		<tbody id="addEqu">
		<c:forEach items="${detailList}" var="plmCheckDetial">
			<tr id="${plmCheckDetial.code}">
				<td>
					${plmCheckDetial.code}
				</td>
				<td>
					${plmCheckDetial.name}
				</td>
				<td>
					${plmCheckDetial.spec}
				</td>
				<td>
					${fns:getDictLabel(plmCheckDetial.typeId, 'plm_equipment_type', '不限')}
				</td>
				<td>
					${fns:getDictLabel(plmCheckDetial.typeChild, 'plm_equipment_type_child', '不限')}
				</td>
				<td>
					${plmCheckDetial.remainingQuantity}
				</td>
				<td>
					${plmCheckDetial.unit}
				</td>
				<td>
					${plmCheckDetial.status}
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	</div>
	<div id="fileImport">
		<input id="file" type="file"/>
		<div id="loading" style="display: none; color: red;"><img   src="${ctxStatic}/plm/storage/img/loading.gif"/>
		       正在导入请勿关闭弹窗
		</div>
		
	</div>
</body>
</html>