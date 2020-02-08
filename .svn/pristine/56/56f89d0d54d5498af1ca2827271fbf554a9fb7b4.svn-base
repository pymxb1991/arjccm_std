<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html >
<head>
<title>个人门户</title>
<meta name="decorator" content="default" />
<link rel="stylesheet" href="${ctxStatic}/common/home/css/home.css">
</head>
<body id="bodybody" >
<c:if var="e" test="${portletlist.size()>=1}">
<script type="text/javascript">
var bjckid="-1";//选中窗口id

</script>
<br>
<div id="bodydiv">
 <div id="portletcontainer jbox">
<c:forEach var="portlet" items="${portletlist}" > 

   <div   id="${portlet.id}" id="portletcolumn1"  class="portletcolumn"  
    style="height:${(fn:length(portlet.longItude)*48)+(fn:length(portlet.longItude)-1)}%;width:${fn:length(portlet.latItude)*94/portlet.type+(fn:length(portlet.latItude)-1)*1}%; top:${((fn:substring(portlet.longItude, 0, 1)-1)*49)+fn:substring(portlet.longItude, 0, 1)*0}%; left:${(fn:substring(portlet.latItude, 0, 1)-1)*(94/portlet.type+1)+1.433}%;z-index:${portlet.longItude*portlet.latItude};">
  <c:if test="${portlet.title!=null&&not empty  portlet.title}"> <div id="" class="portlettitle   "  > 
  <span id="" class="titlespan " ><i class="icon-caret-right tubiao"></i> ${portlet.title}</span> 
  <a id="" class="titlemore  " href="${ctx}${portlet.connect}"><c:if test="${portlet.connect!='null'&&not empty  portlet.connect}">更多<i class="icon-double-angle-right tubiao"></i></c:if></a> 
  </div> </c:if> 			  
	 <div id="portletcontent${portlet.id}" class="portletcontent" style="" >		 	 	
			<div>正在加载...</div>					 
  </div> 
  </div> 
  <script type="text/javascript">
      function exportExcel() {
          top.$.jBox.confirm("确认要导出数据吗？", "系统提示", function(v, h, f) {
              if (v == "ok") {
                  // 借用导出action
                  $("#searchForm").attr("action", "${ctx}/sys/ccmWorkReport/exportWorkReport");
                  $("#searchForm").submit();
                  // 还原查询action
                  $("#searchForm").attr("action", "${ctx}/sys/ccmWorkReport");
              }
          }, {
              buttonsFocus : 1
          });
          top.$('.jbox-body .jbox-icon').css('top', '55px');
      }
//加载窗口内容信息
  $(function(){	
	  var porwidth= $("#${portlet.id}").width();
	  var porheigh=($(window).height()-60)*0.48-10;

		$.ajax({
		  url:'${ctx}${portlet.content}',
		  type:'post',
		  data:{"height":porheigh ,"width":porwidth,"content":"${portlet.content}","divId":"portletcontent${portlet.id}"},
		  dataType:'html',
		  error: function(){ $("#portletcontent${portlet.id}").html("链接不正确")},
		  success:function(data){
		  $("#portletcontent${portlet.id}").html(data);
		  }
		  });	  
});   
  </script> 
  
  </c:forEach>
   </div> 
   </c:if>
 <c:if test="${!e}">当前没有数据  </c:if>
 </div>
  <script type="text/javascript">
 $(function(){	 	
	 $("#right", parent.document).css("padding","0px")
	 $("#bodydiv").height($(window).height());

   })
 </script>
</body>
</html>