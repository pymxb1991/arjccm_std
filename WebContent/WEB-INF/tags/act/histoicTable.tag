<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ attribute name="procInsId" type="java.lang.String" required="true" description="流程实例ID"%>
<%@ attribute name="startAct" type="java.lang.String" required="false" description="开始活动节点名称"%>
<%@ attribute name="endAct" type="java.lang.String" required="false" description="结束活动节点名称"%>
<%@ attribute name="colspan" type="java.lang.String" required="true" description="表格意见部分跨列数"%>
<%@ attribute name="titleColspan" type="java.lang.String" required="false" description="表格标题部分跨列数"%>

	
	<tbody style="border-top: 1px solid black;" id="histoicFlowTable">
		
	</tbody>

<script type="text/javascript">
	$.get("${ctx}/act/task/histoicTable?procInsId=${procInsId}&startAct=${startAct}&endAct=${endAct}&colspan=${colspan}&titleColspan=${titleColspan}&t="+new Date().getTime(), function(data){
		$("#histoicFlowTable").html(data);
	});
</script>