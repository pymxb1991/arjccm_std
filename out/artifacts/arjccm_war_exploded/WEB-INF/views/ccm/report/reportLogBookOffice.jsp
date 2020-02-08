<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>日常工作数据详情</title>
	<meta name="decorator" content="default"/>
	<style>
	.common-pading{
	  width:100%;
	  height:200px;
	  padding:5px;
	}
	.echarts{
	  width:100%;
	  height:100%;
	}
	
	
	</style>
	 <script>
	$(document).ready(function() {
		$("#btnSubmit").on("click" ,function(){
			$("#searchForm").submit();
		})
		$("#reportCount").val(${ccmWorkReportCountSource.reportCount}+".0")
		$("#eventIncidentCount").val(${ccmWorkReportCountSource.eventIncidentCount}+".0")
		$("#eventDealCount").val(${ccmWorkReportCountSource.eventDealCount}+".0")
		$("#eventDealScore").val(${ccmWorkReportCountSource.houseCount}+".0")
		$("#popUpdateCount").val(${ccmWorkReportCountSource.popUpdateCount}+".0")
		$("#popSpecialTailCount").val(${ccmWorkReportCountSource.popSpecialTailCount}+".0")
		$("#houseTailCount").val(${ccmWorkReportCountSource.houseTailCount}+".0")
		$("#orgTailCount").val(${ccmWorkReportCountSource.orgTailCount}+".0")
	});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/report/ccmReportOthers/logBookOffice?kpiSchemeId=${kpiScheme.id}&kpiId=${kpiSchemeKpi.id}">日常工作数据详情</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="ccmWorkReportCount" action="${ctx}/report/ccmReportOthers/logBookOffice" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input id="kpiSchemeId" name="kpiSchemeId" type="hidden" value="${kpiScheme.id}"/>
		<input id="kpiId" name="kpiId" type="hidden" value="${kpiSchemeKpi.id}"/>
		<ul class="ul-form">
			<li><label>时间段：</label> <input name="beginDate" type="text"
				readonly="readonly" maxlength="20" class="input-medium Wdate"
				value="<fmt:formatDate value="${ccmWorkReportCount.beginDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});" />
				- <input name="endDate" type="text" readonly="readonly"
				maxlength="20" class="input-medium Wdate"
				value="<fmt:formatDate value="${ccmWorkReportCount.endDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});" />
			</li>
			<li class="btns">
				<!-- <input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" /> -->
					<a href="javascript:;" id="btnSubmit" class="btn btn-primary">
                <i class="icon-search"></i> 查询 </a>
			</li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	     <div class="row-fluid">
		      <div class="span12" >
				<table id="contentTable" class="table table-striped table-bordered table-condensed">
					<thead>
						<tr>
							<th>姓名</th>
							<th>人员类型</th>
							<th>所属部门</th>
							<th>日报数</th>
							<th>上报案事件数</th>
							<th>事件处理数</th>
							<th>楼栋跟进次数</th>
							<th>人口更新数</th>
							<th>特殊人员跟进次数</th>
							<th>房屋跟进次数</th>
							<th>重点场所跟进次数</th>
							<th>分数(满分:<span style="color: red;" class="fullMarks">${kpiSchemeKpi.score}</span>分)</th>
						</tr>
						<tr>
							<th rowspan="2"><span style="color: red;" >本次计算数量标准(总分:${kpiSchemeKpi.score}分)</span></th>
							<th colspan="2"><span style="color: red;float: right;">单项处理上限：</span></th>
							<th><input id="" class="span10 ribao" type="text" value="0" /></th>
							<th><input id="" class="span10 anshijian" type="text" value="0" /></th>
							<th><input id="" class="span10 chuli" type="text" value="0" /></th>
							<th><input id="" class="span10 loudong" type="text" value="0" /></th>
							<th><input id="" class="span10 renkou" type="text" value="0" /></th>
							<th><input id="" class="span10 teshu" type="text" value="0" /></th>
							<th><input id="" class="span10 fangwu" type="text" value="0" /></th>
							<th><input id="" class="span10 zhongdian" type="text" value="0" /></th>
							<th rowspan="2">
								<input id="" class="input-mini" type="text" value="${kpiSchemeKpi.score}" name='sum-score'/><br>
								<input id="" class="btn btn-primary Calculation" type="button" value="计算" />
							</th>
						</tr>
						<tr>
							<th colspan="2"><span style="color: red;float: right;">单项最高分值：</span></th>
							<th><input id="reportCount" class="span10 ribao-score" type="text" value="" name="score" /></th>
							<th><input id="eventIncidentCount" class="span10 anshijian-score" type="text" value="" name="score"/></th>
							<th><input id="eventDealCount" class="span10 chuli-score" type="text" value="" name="score" /></th>
							<th><input id="eventDealScore" class="span10 loudong-score" type="text" value="" name="score"/></th>
							<th><input id="popUpdateCount" class="span10 renkou-score" type="text" value="" name="score"/></th>
							<th><input id="popSpecialTailCount" class="span10 teshu-score" type="text" value="" name="score"/></th>
							<th><input id="houseTailCount" class="span10 fangwu-score" type="text" value="" name="score" /></th>
							<th><input id="orgTailCount" class="span10 zhongdian-score" type="text" value="" name="score"/></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${logBookList}" var="ccmWorkReportCount">
							<tr>
								<td id="${ccmWorkReportCount.user.id}">${ccmWorkReportCount.user.name}</td>
								<td>${fns:getDictLabel(ccmWorkReportCount.user.userType, 'sys_user_type', '')}</td>
								<td>${ccmWorkReportCount.office.name}</td>
								<td><input class="ribao-td-${ccmWorkReportCount.user.id}" name="${ccmWorkReportCount.user.id}" type="hidden"/> <span class="count"  data-type="ribao-td"  data-id="${ccmWorkReportCount.user.id}">${ccmWorkReportCount.reportCount}</span></td>
								<td><input class="anshijian-td-${ccmWorkReportCount.user.id}"  name="${ccmWorkReportCount.user.id}" type="hidden"/><span class="count"  data-type="anshijian-td"  data-id="${ccmWorkReportCount.user.id}" >${ccmWorkReportCount.eventIncidentCount}</span></td>
<%--								<td><input class="maodun-td-${ccmWorkReportCount.user.id}" name="${ccmWorkReportCount.user.id}"  type="hidden"/><span class="count"  data-type="maodun-td"  data-id="${ccmWorkReportCount.user.id}"> ${ccmWorkReportCount.eventAmbiCount}</span></td>--%>
<%--								<td><input class="qingqiu-td-${ccmWorkReportCount.user.id}"  name="${ccmWorkReportCount.user.id}" type="hidden"/><span class="count"   data-type="qingqiu-td" data-id="${ccmWorkReportCount.user.id}">${ccmWorkReportCount.eventRequestCount}</span></td>--%>
								<td><input class="chuli-td-${ccmWorkReportCount.user.id}"  name="${ccmWorkReportCount.user.id}" type="hidden"/><span  class="count" data-type="chuli-td"  data-id="${ccmWorkReportCount.user.id}">${ccmWorkReportCount.eventDealCount}</span></td>
								<td><input class="kaohe-td-${ccmWorkReportCount.user.id}" name="${ccmWorkReportCount.user.id}"  type="hidden"/><span class="count"  data-type="kaohe-td"  data-id="${ccmWorkReportCount.user.id}">${ccmWorkReportCount.houseCount}</span></td>
								<td><input class="renkou-td-${ccmWorkReportCount.user.id}" name="${ccmWorkReportCount.user.id}"  type="hidden"/><span  class="count" data-type="renkou-td" data-id="${ccmWorkReportCount.user.id}" >${ccmWorkReportCount.popUpdateCount}</span></td>
								<td><input class="teshu-td-${ccmWorkReportCount.user.id}"  name="${ccmWorkReportCount.user.id}" type="hidden"/>  <span class="count"   data-type="teshu-td"  data-id="${ccmWorkReportCount.user.id}">${ccmWorkReportCount.popSpecialTailCount}</span></td>
								<td><input class="fangwu-td-${ccmWorkReportCount.user.id}" name="${ccmWorkReportCount.user.id}"  type="hidden"/><span class="count"  data-type="fangwu-td"  data-id="${ccmWorkReportCount.user.id}">${ccmWorkReportCount.houseTailCount}</span></td>
								<td><input class="zhongdian-td-${ccmWorkReportCount.user.id}"  name="${ccmWorkReportCount.user.id}" type="hidden"/><span class="count"  data-type="zhongdian-td"  data-id="${ccmWorkReportCount.user.id}">${ccmWorkReportCount.orgTailCount}</span></td>
								<td><input id="${ccmWorkReportCount.user.id}" class="input-mini"  data-id="${kpiSchemeKpi.id}-${ccmWorkReportCount.user.id}" name="end-score" type="text" value="" /></td>
							</tr>
						</c:forEach>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="13"></th>
							<td><input id="" class="btn btn-primary" type="button" value="录入"  onclick="saveBtn()"/></th>
						</tr>
					</tfoot>
				</table>
			</div>
	    </div>
	<script type="text/javascript">
		$(document).ready(function() {
            var sumScore=0;
            $("input[name='score']").change(function(){
            	sumScore=0;
            	$("input[name='score']").each(function(){
            		$(this).val(parseFloat($(this).val()).toFixed(1))
            		sumScore+=Number($(this).val())
            	})
             $("input[name='sum-score']").val(parseFloat(sumScore).toFixed(1))
            })

            //计算
            $('.Calculation').click(function(){
				for (var i = 0; i < $('#contentTable').find('.span10').length; i++) {
					if(isInteger($('#contentTable').find('.span10')[i].value)){
						alert('请输入大于零数字！');
						return;
					}
				}

				function isInteger(obj) {
					reg = /^\d+(\.\d+)?$/;
					if (!reg.test(Number(obj))) {
						return true;
					} else {
						return false;
					}
				}


            	var sumScore=$("input[name='sum-score']").val()
            	var fullMarks=parseFloat($('.fullMarks').text()).toFixed(1)
            	if(sumScore!=fullMarks){
            		top.$.jBox.tip('满分为'+fullMarks+'分，当前所占分数总和应为'+sumScore+'分，不符合规范')
            	}else{
            		$('.count').each(function(){
	            		var dataType=$(this).attr("data-type");
	            		var type=dataType.split('-')[0];
	            		var dataId=	$(this).attr("data-id");
	            		var data1=Number($('.'+type).val());
	            		var data2=Number($('.'+type+'-score').val());
	            		var data3=Number($(this).text())	
	            		if(data1==0||data3==0||data3==""){
	            			$('.'+dataType+'-'+dataId+'').val(0)
	            		}else{
	            			if(data3>data1){
	            				$('.'+dataType+'-'+dataId+'').val(data2)
	            			}else{
	            				$('.'+dataType+'-'+dataId+'').val(parseFloat((data3/data1)*data2).toFixed(1))
	            			}

	            		}
            		})
            		var endScore=0;
            		$("input[name='end-score']").each(function(){
            			endScore=0;
            			var id=$(this).attr('id')
                        $("input[name='"+id+"']").each(function(){
                        	var value = $(this).val();
                        	if(value=="NaN"){
								value = 0;
							}
							endScore+=Number(value)
                        })
                		$(this).val(parseFloat(endScore).toFixed(1))
                	})
            		
            	}
            })
		});
		
		
		//录入
		function saveBtn(){
		 var frame = parent.document.getElementById("right").getElementsByTagName("iframe")[0].contentWindow;
			$("input[name='end-score']").each(function(){
          			var id=$(this).attr('data-id')
          			frame.$("#"+id).val($(this).val())
              })
			var index = parent.layer.getFrameIndex(window.name); 
			parent.layer.close(index);
		}
	</script>
</body>
</html>