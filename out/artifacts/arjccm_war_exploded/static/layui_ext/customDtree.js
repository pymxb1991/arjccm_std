// dtree实际开发中将路径替换为自己项目中的路径
layui.extend({dtree: ctxStatic+'/layui_ext/dtree/dtree'}).use(['layer', 'dtree','jquery'], function() {
	var dtree = layui.dtree,
	layer = layui.layer,
	$ = layui.$;
    /*html 内容
    * <!--class="openTree_btn"必填  treeUrl(请求接口路径  必填)，allowClear(是否有清除按钮  默认为  false )，treeTitle(树形标题名称  默认为 ‘请选择’)，
	notAllowSelectParent(不能选择父节点  默认为false )，initLevel(展开层级1开始  默认为1)，rootParentId(设置跟节点ParentId 默认为0)-->
	
	<div class="input-append openTree_btn" treeUrl="dtreejson.json" allowClear="true"
		treeTitle="选择机构" notAllowSelectParent="true" initLevel="2"  rootParentId="0">
		<input id="officeId" name="office.id"  type="hidden" >
		<input  id="officeName" name="office.name" readonly="readonly" type="text" >
	</div>*/
	$(".openTree_btn ").each(function(){
		if($(this).html().trim() == ''){
			$(this).html('<input id="'+$(this).attr('id')+'Id" name="'+$(this).attr('name')+'" type="hidden"><input type="text" id="'+$(this).attr('id')+'Name" name="'+$(this).attr('labelName')+'" readonly="readonly" class="'+$(this).attr('cssClass')+'" style="'+$(this).attr('cssStyle')+'"><a href="javascript:" style="font-size:x-large;" class="btn" >&nbsp;<i class="icon-search"></i>&nbsp;</a>&nbsp;&nbsp;');
		}
	});
	$(".openTree_btn >input,.openTree_btn > a ").click(function(e) {
		var $thisclick = $(this).parent();
		layer.open({
			type: 1,
			//type:0 也行
			title: $thisclick.attr('treeTitle') ? $thisclick.attr('treeTitle') : "请选择",
			area: ["320px", "65%"],
			shade: 0.01,
			content: '<ul id="openTree1" class="dtree" data-id="' + ($thisclick.attr('rootParentId') ? $thisclick.attr('rootParentId') : 0) + '"></ul>',
			btn: $thisclick.attr('allowClear') == "true" ? ['确认', '关闭', '清除'] : ['确认', '关闭'],
			success: function(layero, index) {
				var DTree = dtree.render({
					 obj: $(layero).find("#openTree1"),    //如果直接用elem加载不出来，则可以使用这个方式加载jquery的DOM
					//elem: "#openTree1",
					url: ctx+$thisclick.attr('treeUrl'),
					method: "get",
					initLevel: $thisclick.attr('initLevel') ? $thisclick.attr('initLevel') : 1,
					menubar: true,
					/*checkbar: true,*/
					dataStyle: "layuiStyle", //使用layui风格的数据格式
					dataFormat: "list", //配置data的风格为list
					response: {
						message: "msg",
						statusCode: 0
					}, //修改response中返回数据的定义
				});
				// 点击节点名称获取所有选中节点值
				/*dtree.on("node('openTree1')", function(obj) {
					layer.msg(JSON.stringify(obj.param));
				});*/
				// 绑定节点的双击
				dtree.on("nodedblclick('openTree1')", function(obj) {
					var param = dtree.getNowParam("openTree1"); // 获取当前选中节点
					if (param.isLeaf||($thisclick.attr('notAllowSelectParent')?($thisclick.attr("notAllowSelectParent")=="false"):true)){
						$thisclick.children().eq(1).val(param.context);
						$thisclick.children().eq(0).val(param.nodeId)
						layer.close(index);
					} else{
						layer.msg('不能选择父节点', {icon: 5});
					}
				});
			},
			yes: function(index, layero) {
				var param = dtree.getNowParam("openTree1"); // 获取当前选中节点
				if(param.isLeaf || ($thisclick.attr('notAllowSelectParent') ? ($thisclick.attr("notAllowSelectParent") == "false") : true)) {
					$thisclick.children().eq(1).val(param.context);
					$thisclick.children().eq(0).val(param.nodeId)
					layer.close(index);
				} else {
					layer.msg('不能选择父节点', {
						icon: 5
					});
				}
			},
			btn2: function(index, layero) {
				layer.close(index);
			},
			btn3: function(index, layero) {
				$thisclick.children().eq(1).val("");
				$thisclick.children().eq(0).val("")
				layer.close(index);
			}
		});
		e.preventDefault();
	});
});