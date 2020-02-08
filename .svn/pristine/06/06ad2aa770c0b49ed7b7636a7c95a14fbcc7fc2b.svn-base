<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<link rel="stylesheet" type="text/css" href="${ctxStatic}/statis/css/reset.css" />
<link rel="stylesheet" type="text/css" href="${ctxStatic}/statis/css/style.css" />
<link rel="stylesheet" href="${ctxStatic}/statis/css/index.css" />
<script src="${ctxStatic}/statis/js/jquery-2.2.4.min.js" type="text/javascript"></script>
<title>学员信息管理</title>
<meta name="decorator" content="default" />
	<script type="text/javascript">
		 function doPre(){
			 var idValue = $(" #idParam ").val();
			var inputValue =  $(" #input ").val();
			var inputString = inputValue.substring(1,inputValue.length-1);
			var inputObject = inputString.split(",");
			var objId="";
			for(var j = 0 ; j<inputObject.length;j++){
				objId = objId+"&obj="+inputObject[j].trim();
			}
			for(var i = 0 ; i<inputObject.length;i++){
				if(inputObject[i].trim() == idValue.trim()){
					if(i==0){
						alert("已经是本页第一条");
					} else {
						var a = $(" #url ").val();
						window.location.href = a+"/person/pbsPartymem/checkDetail?id="+inputObject[i-1]+objId;
					}
					break;
				}
			}
		}
		function doNext(){
			var idValue = $(" #idParam ").val();
			var inputValue =  $(" #input ").val();
			var inputString = inputValue.substring(1,inputValue.length-1);
			var inputObject = inputString.split(",");
			var objId="";
			for(var i = 0 ; i<inputObject.length;i++){
				objId = objId+"&obj="+inputObject[i].trim();
			}
			for(var j = 0 ; j<inputObject.length;j++){
				if(inputObject[j].trim() == idValue.trim()){
					flag = true;
					if(j == (inputObject.length)-1){
						alert("已经是本页最后一条");
					} else {
						var a = $(" #url ").val();
						window.location.href=a+"/person/pbsPartymem/checkDetail?id="+inputObject[j+1]+objId;
					}
					break;
				}
			}
		}
		function currentPage(){
			var idValue = $(" #idParam ").val();
			var inputValue =  $(" #input ").val();
			var inputString = inputValue.substring(1,inputValue.length-1);
			var inputObject = inputString.split(",");
			var objId="";
			for(var i = 0 ; i<inputObject.length;i++){
				objId = objId+"&obj="+inputObject[i].trim();
			}
			for(var j = 0 ; j<inputObject.length;j++){
				if(inputObject[j].trim() == idValue.trim()){
					flag = true;
					if(j == (inputObject.length)-1){
						alert("已经是本页最后一条");
					} else {
						var a = $(" #url ").val();
						$("#currentPage").attr("href",a+"/person/pbsPartymem/checkDetail?id="+inputObject[j]+objId); 
					}
					break;
				}
			}
		}
	</script>
	
</head>
<body>
	<!-- 计算学习时长 -->
	<c:set var="obj" value="${idList}"></c:set>
	<c:set var="count" value="0"></c:set>
	<c:forEach items="${pbsCourseoperate}" var="pbsCourse">
	    <c:set var="count" value="${count+pbsCourse.getITime()}"></c:set>
	</c:forEach>
	<!-- 性别参数赋值 -->
	<c:set var="sexParam" value="${pbsPartymem.getISex()}"></c:set>
	<!-- 民族参数赋值 -->
	<c:set var="nationParam" value="${pbsPartymem.getINation()}"></c:set>
	<c:set var="idParam" value="${pbsPartymem.getId()}"></c:set>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/person/pbsPartymem/">学员信息列表</a></li>
		<li class="active"><a id="currentPage" onclick="currentPage()">人物信息图</a></li>
	</ul>
	<div class="height100 Bordereffect MemberDetail" style="width: 100%; height:1000px">
		<!--<h4>学员自画像</h4>-->
		<ul class="clearfix">
			<li class="li1">
				<div class="MyStudy" style="margin-top:30%;width:60%;margin-left:40%">
					<h5>资讯学习</h5>
					<table>
						<tr>
							<td>
								学习次数
							</td>
							<td>
								<font>${fn:length(pbsCourseoperate)}次</font>
							</td>
						</tr>
						<tr>
							<td>
								学习时长
							</td>
							<td>
								<font>${count}课时</font>
							</td>
						</tr>
					</table>
				</div>
				<div class="MyLife" style="margin-top:15%;width:60%;margin-left:40%">
					<h5>组织生活</h5>
					<table>
						<tr>
							<td>
								参与活动
							</td>
							<td>
								<font>${activitySum}场次</font>
							</td>
						</tr>
						<c:forEach items="${PbsDepartmentbindList}" begin="0" end="0" var="list">
							<tr>
								<td>
									担任职务
								</td>
								<td>
									<font>${list.getSPost().getSName()}</font>
								</td>
							</tr>
							<tr>
								<td>
									头衔
								</td>
								<td>
									<font>${list.getSPosttitle().getSName()}</font>
								</td>
							</tr>
  								</c:forEach>
						<tr>
							<td>
								所属支部
							</td>
							<td>
								<font>${departName}</font>
							</td>
						</tr>
						<tr>
							<td>
								入党时间
							</td>
							<td>
								<font><fmt:formatDate value="${pbsPartymem.dtAdmission}" pattern="yyyy-MM-dd HH:mm:ss"/></font>
							</td>
						</tr>
						<tr>
							<td>
								转正时间
							</td>
							<td>
								<font><fmt:formatDate value="${pbsPartymem.dtCorrection}" pattern="yyyy-MM-dd HH:mm:ss"/></font>
							</td>
						</tr>
					</table>
				</div>
			</li>
			<li class="li2">
				<!-- <h4 style="margin-right:160px">学员画像</h4> -->
				<c:if test="${pbsPartymem.getISex() eq 0}">
             			<img src="${ctxStatic}/statis/img/huaxiang.png" style="width:250px"/>
         		</c:if> 
				<c:if test="${pbsPartymem.getISex() eq 1}">
             			<img src="${ctxStatic}/statis/img/nvhuaxiang.png" style="width:250px"/>
         		</c:if> 
			</li>
			<li class="li3">
				<div class="MyBasics" style="margin-top:30%;width:60%;margin-left:-25%">
					<h5>基本信息</h5>
					<table>
						<tr>
							<td>
								姓名
							</td>
							<td>
								<font>${pbsPartymem.getSName()}</font>
							</td>
						</tr>
						<tr>
							<td>
								身份证号
							</td>
							<td>
								<font>${pbsPartymem.getSIdnum()}</font>
							</td>
						</tr>
						<tr>
							<td>
								性别
							</td>
							<td>
								<font>${fns:getDictList('sex')[sexParam]}</font>
							</td>
						</tr>
						<tr>
							<td>
								生日
							</td>
							<td>
								<font><fmt:formatDate value="${pbsPartymem.dtBirth}" pattern="yyyy年MM月dd日 "/></font>
							</td>
						</tr>
						<tr>
							<td>
								民族
							</td>
							<td>
								<font>
								${fns:getDictLabel(pbsPartymem.INation, 'sys_volk', '')}</font>
							</td>
						</tr>
						<tr>
							<td>
								学历
							</td>
							<td>
								<font>${fns:getDictLabel(pbsPartymem.getSEducation(), 'sys_degree', "")}</font>
							</td>
						</tr>
						
						<tr>
							<td>
								工作单位
							</td>
							<td>
								<font>${pbsPartymem.getSPost()}</font>
							</td>
						</tr>
						
						<tr>
							<td>
								联系电话
							</td>
							<td>
								<font>${pbsPartymem.getSMobilephone()}</font>
							</td>
						</tr>
						<tr>
							<td>
								家庭住址
							</td>
							<td>
								<font>${pbsPartymem.getSHomeaddr()}</font>
							</td>
						</tr>
					</table>
					
				</div>
			</li>
		</ul>
		<div style="width:92%;text-align:center; margin-top:2%">
			<input id = "input" type = "hidden" value="${obj}" style="display:none"/>
			<input id = "idParam" type = "hidden" value="${idParam}" style="display:none"/>
			<input id = "url" type = "hidden" value="${ctx}" style="display:none"/>
			<input type="button" class="btn btn-primary" value="上一条" onclick='doPre()'/>
			&nbsp;&nbsp;
			<input type="button" class="btn btn-primary" value="下一条" onclick='doNext()'/>
		</div>
	</div>
</body>
</html>

