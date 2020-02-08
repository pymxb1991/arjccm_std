<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>社会组织管理</title>
	<meta name="decorator" content="default"/>
	<!-- 鱼骨图 -->
<link rel="stylesheet" href="${ctxStatic}/ccm/event/css/fishBonePop.css" />
<script type="text/javascript" src="${ctxStatic}/ccm/event/js/fishBonePop.js"></script>
<script type="text/javascript" src="${ctxStatic}/ccm/event/js/jquery.SuperSlide.2.1.1.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
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

		function ThisLayerDialog(src, title, height, width){
			parent.drawForm=parent.layer.open({
				type: 2,
				title: title,
				area: [height, width],
				fixed: true, //固定
				maxmin: false,
				/*   btn: ['关闭'], //可以无限个按钮 */
				content: src,
				zIndex:'1992',
				cancel:function(){
					//防止取消和删除效果一样
					window.isCancel = true;
				},
				end:function(){
					if(!window.isCancel){
						$("#areaPoint")[0].value = parent.areaPoint;
						parent.areaPoint = null;
						$("#areaMap")[0].value = parent.areaMap;
						parent.areaMap = null;
					}
					window.isCancel = null;
				}
			});
		}
	</script>
	<link href="${ctxStatic}/form/css/form.css" rel="stylesheet" />
	<link href="/arjccm/static/bootstrap/2.3.1/css_input/input_Custom.css" type="text/css" rel="stylesheet">
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a style="width: 140px;text-align:center" href="${ctx}/org/ccmOrgSocialorg/">数据列表</a></li>
		<li class="active" style="width: 140px"><a class="nav-head" href="${ctx}/org/ccmOrgSocialorg/form?id=${ccmOrgSocialorg.id}">数据<shiro:hasPermission name="org:ccmOrgSocialorg:edit">${not empty ccmOrgSocialorg.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="org:ccmOrgSocialorg:edit">查看</shiro:lacksPermission></a></li>
		<%-- <c:if test="${not empty ccmOrgSocialorg.id}">
			<li><a href="${ctx}/log/ccmLogTail/formPro?relevance_id=${ccmOrgSocialorg.id}&relevance_table=ccm_org_socialorg">跟踪信息<shiro:hasPermission name="log:ccmLogTail:edit">${not empty ccmLogTail.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="log:ccmLogTail:edit">查看</shiro:lacksPermission></a></li>
		</c:if> --%>
	</ul>
	<form:form id="inputForm" modelAttribute="ccmOrgSocialorg" action="${ctx}/org/ccmOrgSocialorg/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>

		<table border="0px"
			style="border-color: #CCCCCC; border: 0px solid #CCCCCC; padding: 10px; width: 100%;">
			
			<tr>
				<td style="width: 33%">
					<div>
						<label class="control-label"><span class="help-inline"><font color="red">*</font> </span>统一社会信用代码：</label>
						<div class="controls">
							<form:input path="creditCode" htmlEscape="false" maxlength="18" class="input-xlarge required"/>

						</div>
					</div>
				</td>
				<td style="width: 33%">
					<div>
						<label class="control-label"><span class="help-inline"><font color="red">*</font> </span>登记证号：</label>
						<div class="controls">
							<form:input path="regiNum" htmlEscape="false" maxlength="32" class="input-xlarge required"/>
						</div>
					</div>
				</td>
				<td style="width: 33%"></td>
			</tr>
			<td>
				<div>
					<label class="control-label"><span class="help-inline"><font color="red">*</font> </span>社会组织名称：</label>
					<div class="controls">
						<form:input path="orgName" htmlEscape="false" maxlength="100" class="input-xlarge required"/>
					</div>
				</div>
			</td>
			<td>
				<div>
					<label class="control-label"><span class="help-inline"><font color="red">*</font> </span>登记管理机关代码：</label>
					<div class="controls">
						<form:input path="regiPlaceNum" htmlEscape="false" maxlength="6" class="input-xlarge required number"/>
					</div>
				</div>
			</td>
			<td>
				<div>
					<label class="control-label"><span class="help-inline"><font color="red">*</font> </span>批准日期：</label>
					<div class="controls">
						<input name="apprDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
							   value="<fmt:formatDate value="${ccmOrgSocialorg.apprDate}" pattern="yyyy-MM-dd"/>"
							   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
					</div>
				</div>
			</td>

			<tr>
				<td >
					<div>
						<label class="control-label">社会组织照片：</label>
						<div class="controls">
							<form:hidden id="images" path="images" htmlEscape="false" maxlength="255" class="input-xlarge"/>
							<sys:ckfinder input="images" type="images" uploadPath="/photo/SheHuiZuZhi" selectMultiple="false" maxWidth="240" maxHeight="360"/>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div>
						<label class="control-label"> <span class="help-inline"><font color="red">*</font> </span>社会组织类型：</label>
						<div class="controls">
							<form:select path="orgType" class="input-xlarge required">
								<form:option value="" label=""/>
								<form:options items="${fns:getDictList('ccm_sosr_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						    </form:select>
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label"><span class="help-inline"><font color="red">*</font> </span>办公地址：</label>
						<div class="controls">
							<form:input path="workAdd" htmlEscape="false" maxlength="200" class="input-xlarge required"/>

						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label"><span class="help-inline"><font color="red">*</font> </span>法定代表人姓名：</label>
						<div class="controls">
							<form:input path="legalReprName" htmlEscape="false" maxlength="80" class="input-xlarge required"/>

						</div>
					</div>
				</td>
			</tr>

			<tr>
				<td>
					<div>
						<label class="control-label"><span class="help-inline"><font color="red">*</font> </span>住所：</label>
						<div class="controls">
							<form:input path="place" htmlEscape="false" maxlength="200" class="input-xlarge required"/>
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label"><span class="help-inline"><font color="red">*</font> </span>负责人证件类型：</label>
						<div class="controls">
							<form:select path="prinCode" class="input-xlarge required" items="${fns:getDictList('legal_person_certificate_type')}"
										 itemLabel="label" itemValue="value" htmlEscape="false">
							</form:select>
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label"><span class="help-inline"><font color="red">*</font> </span>负责人证件号码：</label>
						<div class="controls">
							<form:input path="prinId" htmlEscape="false" maxlength="50" class="input-xlarge required ident1 card"/>
						</div>
					</div>
				</td>
			</tr>

			<tr>
				<td>
					<div>
						<label class="control-label"><span class="help-inline"><font color="red">*</font> </span>负责人姓名：</label>
						<div class="controls">
							<form:input path="prinName" htmlEscape="false" maxlength="80" class="input-xlarge required"/>
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label"><span class="help-inline"><font color="red">*</font> </span>负责人联系方式：</label>
						<div class="controls">
							<form:input path="prinTel" htmlEscape="false" maxlength="50" class="input-xlarge required phone" />
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label"><span class="help-inline"><font color="red">*</font> </span>治保负责人姓名：</label>
						<div class="controls">
							<form:input path="secuName" htmlEscape="false" maxlength="50" class="input-xlarge required"/>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div>
						<label class="control-label"><span class="help-inline"><font color="red">*</font> </span>关注程度：</label>
						<div class="controls">
							<form:select path="concernExtent" class="input-xlarge required">
								<form:option value="" label=""/>
								<form:options items="${fns:getDictList('ccm_conc_exte')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
							</form:select>
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label"><span class="help-inline"><font color="red">*</font> </span>治保负责人联系方式：</label>
						<div class="controls">
							<form:input path="secuTel" htmlEscape="false" maxlength="50" class="input-xlarge required phone"/>

						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label">资金来源：</label>
						<div class="controls">
							<form:input path="capiSour" htmlEscape="false" maxlength="100" class="input-xlarge "/>
						</div>
					</div>
				</td>

			</tr>

			<tr>
				<td>
					<div>
						<label class="control-label"><span class="help-inline"><font color="red">*</font> </span>是否有中共党组织：</label>
						<div class="controls">
							<form:radiobuttons path="estaOrg" items="${fns:getDictList('yes_no')}" itemLabel="label"
											   itemValue="value" htmlEscape="false" class="required"/>

						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label"><span class="help-inline"><font color="red">*</font> </span>是否具备建立中共党组织条件：</label>
						<div class="controls">
							<form:radiobuttons path="estaOrgCond" items="${fns:getDictList('yes_no')}"
											   itemLabel="label" itemValue="value" htmlEscape="false" class="required"/>
						</div>
					</div>
				</td>

				<td>
					<div>
						<label class="control-label"><span class="help-inline"><font color="red">*</font> </span>中共党员数量：</label>
						<div class="controls">
							<form:input path="partyMem" htmlEscape="false" maxlength="6" class="input-xlarge  digits required" type="number"/>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div>
						<label class="control-label"><span class="help-inline"><font color="red">*</font> </span>是否有工会：</label>
						<div class="controls">
							<form:radiobuttons path="laborUnion" items="${fns:getDictList('yes_no')}" itemLabel="label" 
							itemValue="value" htmlEscape="false" class="required"/>
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label"><span class="help-inline"><font color="red">*</font> </span>工会会员数量：</label>
						<div class="controls">
							<form:input path="laborUnionNum" htmlEscape="false" maxlength="6" class="input-xlarge required  digits" type="number"/>
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label">区域图：</label>
						<div class="controls">
							<form:input path="areaMap" htmlEscape="false" maxlength="2000" class="input-xlarge " disabled="true"/>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div>
						<label class="control-label"><span class="help-inline"><font color="red">*</font> </span>是否有共青团组织：</label>
						<div class="controls">
							<form:radiobuttons path="youthLeagOrg" items="${fns:getDictList('yes_no')}" itemLabel="label" 
							itemValue="value" htmlEscape="false" class="required"/>
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label"><span class="help-inline"><font color="red">*</font> </span>共青团团员数量：</label>
						<div class="controls">
							<form:input path="youthLeagOrgNum" htmlEscape="false" maxlength="6" class="input-xlarge required  digits" type="number"/>
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label">中心点：</label>
						<div class="controls">
							<form:input path="areaPoint" htmlEscape="false" maxlength="40" class="input-xlarge " disabled="true"/>
							<a onclick="ThisLayerDialog('${ctx}/event/ccmEventIncident/drawForm?areaMap='+$('#areaMap').val()+'&areaPoint='+$('#areaPoint').val(), '标注', '1100px', '700px');"
							   class="btn btn-primary">标 注</a>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div>
						<label class="control-label"><span class="help-inline"><font color="red">*</font> </span>是否有妇联组织：</label>
						<div class="controls">
							<form:radiobuttons path="womenOrg" items="${fns:getDictList('yes_no')}" itemLabel="label" 
							itemValue="value" htmlEscape="false" class="required"/>
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label"><span class="help-inline"><font color="red">*</font> </span>妇女数量：</label>
						<div class="controls">
							<form:input path="womenNum" htmlEscape="false" maxlength="6" class="input-xlarge required  digits" type="number"/>
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label">图标：</label>
						<div class="controls">
							<sys:iconselect id="icon" name="icon" value="${ccmOrgSocialorg.icon}"/>
						</div>
					</div>
				</td>
			</tr>
			<tr>

				<td>
					<div>
						<label class="control-label"><span class="help-inline"><font color="red">*</font> </span>是否有境外背景：</label>
						<div class="controls">
							<form:radiobuttons path="overBack" items="${fns:getDictList('yes_no')}" itemLabel="label" 
							itemValue="value" htmlEscape="false" class="required"/>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="3">
					<div>
						<label class="control-label">备注信息：</label>
						<div class="controls">
							<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xlarge " />
						</div>
					</div>
				</td>
				<td></td>
				<td></td>
			</tr>


		</table>
	<%-- 	
		<table border="1px" style="border-color: #CCCCCC; border: 1px solid #CCCCCC; padding: 10px; width: 100%;">
	        <%@include file="/WEB-INF/views/include/ccmlog.jsp" %>
	    </table> --%>
		
		
		<%-- <div class="control-group">
			<label class="control-label">状态：</label>
			<div class="controls">
				<form:input path="type" htmlEscape="false" maxlength="1" class="input-xlarge  digits"/>
			</div>
		</div> --%>
		<div class="form-actions">
			<shiro:hasPermission name="org:ccmOrgSocialorg:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
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