<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/modules/cms/front/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>网上论坛</title>
	<meta name="decorator" content="cms_default_${site.theme}"/>
	<meta name="description" content="JeeSite ${site.description}" />
	<meta name="keywords" content="JeeSite ${site.keywords}" />
	<script src="${ctxStatic}/jquery-validation/1.11.1/jquery.validate.js" type="text/javascript"></script>
	<script src="${ctxStatic}/jquery-validation/1.11.1/jquery.validate.method.js" type="text/javascript"></script>
	<link href="${ctxStatic}/jquery-validation/1.11.1/jquery.validate.css" type="text/css" rel="stylesheet" />
	<script src="${ctxStatic}/layer-v3.1.1/layer/layer.js" type="text/javascript"></script>
	<link rel="stylesheet" href="${ctxStatic}/layer-v3.1.1/layer/theme/default/layer.css" />
	<script type="text/javascript" src="${ctxStatic}/dist/layui.js"></script>
	<link rel="stylesheet" href="${ctxStatic}/dist/css/layui.css" type="text/css"/>

	<script type="text/javascript">
			// $("#Bootstrap")..remove();
		$(document).ready(function() {
			$("#article_list").show();
			$("#article_content").hide();
			window.parent.document.getElementById("Bootstrap").remove();
			$("#main_nav li").each(function(){
				$(this).toggleClass("active", $(this).text().indexOf('网上论坛')>=0);
			});
		});
		function page(n,s){
			location="${ctx}/bbsArticle?pageNo="+n+"&pageSize="+s;
		}
		function sendArticle() {
			var fontUserId =  "${ccmFontUser.id}";
			console.log(fontUserId);
			if(fontUserId == null || fontUserId == ""|| fontUserId === undefined){
					layer.msg('登陆之后可以发帖！');
					return false;
			}
			$("#article_list").hide();
			$("#article_content").show();
		}
        var index=0;
		function sendArticleInfo(){
			var title = $(" #articleTitle ").val();
			//var contentText = $("#articleContent").val();

			var contentText = layedit.getContent(index)

			var fontUserName ='${ccmFontUser.name}';
			if(title == null || title == ""|| title === undefined){
				layer.msg('标题不可以为空！');
				return false;
			}
			if(contentText == null || contentText == ""|| contentText === undefined){
				layer.msg('内容不可以为空！');
				return false;
			}
			var url = '/arjccm/f/bbsArticle/sendArticle';
			$.post(url,{
				'title':title,
				'contentText':contentText,
				'fontUserName':fontUserName
			}, function(res){
				layer.msg('恭喜您发表帖子成功，请等待管理员审核！', {
					icon: 1,
					time: 3000 //2秒关闭（如果不配置，默认是3秒）
				}, function(){
					window.location.href="${ctx}/bbsArticle";
				});

			})
		}
		var layedit ;
        layui.use('layedit', function(){
            layedit = layui.layedit;
            index=layedit.build('articleContent',{
                tool: [
                    'strong' //加粗
                    ,'italic' //斜体
                    ,'underline' //下划线
                    ,'del' //删除线
                    ,'|' //分割线
                    ,'left' //左对齐
                    ,'center' //居中对齐
                    ,'right' //右对齐
                    ,'link' //超链接
                    ,'unlink' //清除链接
                    ,'face' //表情
                    // ,'image' //插入图片
                    ,'help' //帮助
                ]
            }); //建立编辑器
        });
	</script>
	<style type="text/css">
		.title{
			padding-left: 10px;
			background: #cccccc;
			height: 40px;
			margin-bottom: 2px;
			line-height: 40px;
		}
	</style>
</head>
<body>
	<div style="padding:0 0 20px;">
		<h4>
			<div style="margin-bottom: 10px;">
				<div>
					<span style=" padding-left: 10px; padding-right: 750px;"><a href="${ctx}/bbsArticle">网上论坛</a></span>
					<button onclick="sendArticle()" type="button" class="layui-btn layui-btn-normal">我要发贴</button>
				</div>

			</div>

		</h4>
		<div id="article_list" >
			<table id="contentTable" class="table table-striped table-bordered table-condensed">
				<thead>
				<tr>
					<th>主题</th>
					<th>作者</th>
					<th>查看/回复</th>
					<th>最后发表</th>
				</tr>
				</thead>
				<tbody>
				<c:forEach items="${page.list}" var="bbsArticle">
				<tr>

					<td> <a href="${ctx}/bbsArticle/form?id=${bbsArticle.id}">
							${bbsArticle.title}
					</td>
					<td>
							${bbsArticle.fontUserName}</br>
						<fmt:formatDate value="${bbsArticle.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</td>
					<td>
							${bbsArticle.viewNum}/${bbsArticle.commentNum}
					</td>
					<td>
							${bbsArticle.endCommentName}</br>
						<fmt:formatDate value="${bbsArticle.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</td>
					</c:forEach>
					<c:if test="${fn:length(page.list) eq 0}">
					<li>暂时还没有帖子！</li>
					</c:if>
				</tbody>
			</table>
			<div class="pagination">${page}</div>
		</div>

		<div id="article_content">
			<div class="layui-form-item">
				<label class="layui-form-label">标题：</label>
				<div >
					<input type="text" id="articleTitle" name="articleTitle" placeholder="请输入标题内容" autocomplete="off" class="input-xxlarge">
				</div>
			</div>

			<div class="layui-form-item layui-form-text">
				<label class="layui-form-label">内容：</label>
				<div class="layui-input-block"  lay-verify="required" >
					<textarea id="articleContent" name="articleContent" placeholder="请输入内容" class="layui-textarea"></textarea>
				</div>
			</div>
			<div class="layui-form-item" style="padding-left:60px;">
				<button onclick="sendArticleInfo()" type="button" class="btn btn-large btn-primary">发表</button>
			</div>
		</div>
	</div>

	
</body>
</html>