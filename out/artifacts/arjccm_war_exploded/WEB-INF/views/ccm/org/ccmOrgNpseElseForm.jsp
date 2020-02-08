<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>其他重点管理</title>
	<link href="${ctxStatic}/form/css/form.css" rel="stylesheet" />
	<meta name="decorator" content="default"/>
	<!-- 鱼骨图 -->
<link rel="stylesheet" href="${ctxStatic}/ccm/event/css/fishBonePop.css" />
<script type="text/javascript" src="${ctxStatic}/ccm/event/js/fishBonePop.js"></script>
<script type="text/javascript" src="${ctxStatic}/ccm/event/js/jquery.SuperSlide.2.1.1.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			//关闭弹框事件
			$('#btnCancel').click(function() {
				parent.layer.close(parent.layerIndex);
			})
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
			$("td").css({"padding":"8px"});
			$("td").css({"border":"0px dashed #CCCCCC"});
			//跟踪信息
			var jsonString = '${documentList}';
            data = JSON.parse(jsonString);  
			$(".fishBone1").fishBone(data, '${ctx}','deal');
			$(".fishBone2").fishBone(data, '${ctx}','read');
		});
		
		function saveForm() {
			var area = $("#areaId").val();
			var html1 = '<label for="" class="error">必选字段 <label>';
			//alert(officeId.length);
			if (area.length != 0) {
				$("#show1").html("");
			} else {
				$("#show1").html(html1);
			}

			if (area.length != 0) {
				$("#inputForm").submit();
			}

		}
	</script>
	<link href="/arjccm/static/bootstrap/2.3.1/css_input/input_Custom.css" type="text/css" rel="stylesheet">
</head>
<body>
	<ul class="nav nav-tabs">
		<%-- <li><a href="${ctx}/org/ccmOrgNpseElse/">其他重点列表</a></li> 
		<li class="active"><a href="${ctx}/org/ccmOrgNpseElse/form?id=${ccmOrgNpse.id}">其他重点<shiro:hasPermission name="org:ccmOrgNpseElse:edit">${not empty ccmOrgNpse.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="org:ccmOrgNpseElse:edit">查看</shiro:lacksPermission></a></li> --%>
	<%-- <c:if test="${not empty ccmOrgNpse.id}">
			<li><a
				href="${ctx}/log/ccmLogTail/formPro?relevance_id=${ccmOrgNpse.id}&relevance_table=ccm_org_npseElse">跟踪信息<shiro:hasPermission
						name="log:ccmLogTail:edit">${not empty ccmLogTail.id?'修改':'添加'}</shiro:hasPermission>
					<shiro:lacksPermission name="log:ccmLogTail:edit">查看</shiro:lacksPermission></a>
			</li>
		</c:if> --%>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="ccmOrgNpse" action="${ctx}/org/ccmOrgNpseElse/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<input type="hidden" name="compImpoType" value="05">
		<sys:message content="${message}"/>	
		<table border="0px"
			style="border-color: #CCCCCC; border: 0px solid #CCCCCC; padding: 10px; width: 100%;">
			
			<tr>
				<td>
					<div>
						<label class="control-label"><span class="help-inline"><font color="red">*</font></span>所属网格：</label>
						<div class="controls">
							<sys:treeselect id="area" name="area.id" value="${ccmOrgNpse.area.id}" labelName="area.name" labelValue="${ccmOrgNpse.area.name}"
							title="区域" url="/tree/ccmTree/treeDataArea?type=7&areaid=" allowClear="true" notAllowSelectParent="true" cssClass=""/>
							<span class="help-inline"><font color="red" id="show1"></font></span>
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label"><span class="help-inline"><font color="red">*</font> </span>名称：</label>
						<div class="controls">
							<form:input path="compName" htmlEscape="false" maxlength="100" class="input-xlarge required"/>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div>
						<label class="control-label"><span class="help-inline"><font color="red">*</font> </span>类别：</label>
						<div class="controls">
							<form:select path="compType" class="input-xlarge required">
								<form:options items="${fns:getDictList('ccm_buss_cate')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						    </form:select>
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label"><span class="help-inline"><font color="red">*</font> </span>场所地址：</label>
						<div class="controls">
							<form:input path="compAdd" htmlEscape="false" maxlength="200" class="input-xlarge required"/>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div>
						<label class="control-label"><span class="help-inline"><font color="red">*</font> </span>工商执照注册号：</label>
						<div class="controls">
							<form:input path="compId" htmlEscape="false" maxlength="64" class="input-xlarge required" />
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label">登记注册日期：</label>
						<div class="controls">
							<input name="registerDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
							value="<fmt:formatDate value="${ccmOrgNpse.registerDate}" pattern="yyyy-MM-dd"/>"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div>
						<label class="control-label"><span class="help-inline"><font color="red">*</font> </span>负责人姓名：</label>
						<div class="controls">
							<form:input path="entePrinName" htmlEscape="false" maxlength="50" class="input-xlarge required"/>
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label">负责人联系方式：</label>
						<div class="controls">
							<form:input path="entePrincipalTl" htmlEscape="false" maxlength="50" class="input-xlarge phone" />
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div>
						<label class="control-label"><span class="help-inline"><font color="red">*</font> </span>关注程度：</label>
						<div class="controls">
							<form:select path="concExte" class="input-xlarge required">
								<form:options items="${fns:getDictList('ccm_conc_exte')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						    </form:select>
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label">风险级别：</label>
						<div class="controls">
							<form:select path="riskRank" class="input-xlarge ">
								<form:options items="${fns:getDictList('ccm_npse_risk_rank')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						    </form:select>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div>
						<label class="control-label"><span class="help-inline"><font color="red">*</font> </span>安全隐患类型：</label>
						<div class="controls">
							<form:select path="safeHazaType" class="input-xlarge required">
								<form:options items="${fns:getDictList('ccm_pori_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						    </form:select>
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label">中心点：</label>
						<div class="controls">
							<form:input path="areaPoint" htmlEscape="false" maxlength="40" class="input-xlarge " />
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<%-- <td>
					<div>
						<label class="control-label">图标：</label>
						<div class="controls">
							<sys:iconselect id="icon" name="icon" value="${ccmOrgNpse.icon}"/>
						</div>
					</div>
				</td> --%>
				<td>
					<div>
						<label class="control-label">备注信息：</label>
						<div class="controls">
							<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xlarge " />
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label">图片</label>
						<div class="controls">
							<form:hidden id="images" path="images" htmlEscape="false" maxlength="255" class="input-xlarge"/>
							<sys:ckfinder input="images" type="images" uploadPath="/org/FeiGongYouZhiZuZhi" selectMultiple="false" maxWidth="240" maxHeight="360"/>	
						</div>
					</div>
				</td>
			</tr>
		</table>
		
		<%-- <table border="1px" style="border-color: #CCCCCC; border: 1px solid #CCCCCC; padding: 10px; width: 100%;">
	          <%@include file="/WEB-INF/views/include/ccmlog.jsp" %>
		</table> --%>
		<div class="form-actions">
			<shiro:hasPermission name="org:ccmOrgNpseElse:edit">
			<input id="btnSubmit" class="btn btn-primary" onclick="saveForm()" type="button" value="保 存"/>&nbsp;<input id="btnCancel" class="btn btn-danger" type="button"
						value="关闭" /></shiro:hasPermission>
		</div>
	</form:form><br>
	<c:if test="${documentNumber > 0}">
		<shiro:hasPermission name="log:ccmLogTail:edit">
			<h4>&nbsp;跟踪信息：</h4>
			<br>
			<div class="fishBone1" ></div>
		</shiro:hasPermission>
		<shiro:lacksPermission name="log:ccmLogTail:edit">
			<h4>&nbsp;查看跟踪信息：</h4>
			<br>
			<div class="fishBone2" ></div>
		</shiro:lacksPermission> 
	</c:if>
</body>
</html>