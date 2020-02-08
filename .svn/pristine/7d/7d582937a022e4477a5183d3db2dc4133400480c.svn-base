<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<script type="text/javascript">
	//详情弹框--刷新父页面
	function LayerDialog(src, title, height, width) {
		parent.layer.open({
			type : 2,
			title : title,
			area : [ height, width ],
			fixed : true, //固定
			maxmin : true,
			//btn: ['确定', '关闭'], //可以无限个按钮
			content : src,
			end : function() {
				location.reload();
			}
		});
	}
</script>

<div class="row">
	<c:forEach items="${page.list}" var="plmCar">
		<a
			onclick="LayerDialog('${ctx}/car/plmCarUse/carList?car.id=${plmCar.id}', '【${plmCar.vehicle}】领用记录', '1000px', '700px')"
			title="领用记录"> <c:if test="${plmCar.vehicleStatus=='01'}"
				var="condition">
				<div class="carOn">
			</c:if> <c:if test="${!condition}">
				<div class="carOff">
			</c:if> <shiro:hasPermission name="car:plmCarUse:edit">
				<div>${plmCar.vehicle}</div>
			</shiro:hasPermission>
</div>
</a>
</c:forEach>
</div>

</html>