<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
		<title>网格化平台-APP下载</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="assets/css/main.css" />
		<noscript><link rel="stylesheet" href="assets/css/noscript.css" /></noscript>
</head>
	<body class="is-preload">
			<header id="header">
				<div class="content">
					<h1><a href="#">网格化平台</a></h1>
					<ul class="actions">
						<li><a id="appUrl" href="" class="button primary icon fa-download">APP下载</a></li>
						<li><a href="#one" class="button icon fa-chevron-down scrolly">APP介绍</a></li>
					</ul>
				</div>
				<div class="image phone"><div class="inner"><img src="images/dg1.jpg" alt="" /></div></div>
			</header>
			<section id="one" class="wrapper style2 special">
				<header class="major">
					<h2>网格化平台介绍</h2>
				</header>
				<ul class="icons major">
					<li><span class="icon fa-camera-retro"><span class="label">Shoot</span></span></li>
					<li><span class="icon fa-refresh"><span class="label">Process</span></span></li>
					<li><span class="icon fa-cloud"><span class="label">Upload</span></span></li>
				</ul>
			</section>
			<section id="two" class="wrapper">
				<div class="inner alt">
					<section class="spotlight">
						<div class="image"><img src="images/dg.jpg" alt="" /></div>
						<div class="content">
							<h3>方便</h3>
							<p>节省了不同职职责的人员对警情的时时关注、快捷的办公</p>
						</div>
					</section>
					
					<section class="spotlight">
						<div class="image"><img src="images/dg.jpg" alt="" /></div>
						<div class="content">
							<h3>高效</h3>
							<p>自动化的警情同步，警情分流处警设置</p>
						</div>
					</section>
					
					<section class="spotlight">
						<div class="image"><img src="images/dg.jpg" alt="" /></div>
						<div class="content">
							<h3>实用</h3>
							<p>随着App功能的不断更新，接处警，实时警情，视频监控等，更多方面的应用到我们的办案当中</p>
						</div>
					</section>
					
				<section id="three" class="wrapper style2 special">
				<header class="major">
					<h2>网格化指挥调度App</h2>
				</header>
				<ul class="actions special">
				<li>期待您的下载</a></li>
   		
				</ul>
			</section>

			<footer id="footer">
				<ul class="icons">
					<li><a href="#" class="icon fa-facebook"><span class="label">Facebook</span></a></li>
					<li><a href="#" class="icon fa-twitter"><span class="label">Twitter</span></a></li>
					<li><a href="#" class="icon fa-instagram"><span class="label">Instagram</span></a></li>
				</ul>
				<p class="copyright">网格化指挥调度系统</a></p>
			</footer>
			
			<script src="assets/js/jquery.min.js"></script>
			<script src="assets/js/jquery.scrolly.min.js"></script>
			<script src="assets/js/browser.min.js"></script>
			<script src="assets/js/breakpoints.min.js"></script>
			<script src="assets/js/util.js"></script>
			<script src="assets/js/main.js"></script>
			<script type="text/javascript">
				$(document).ready(function() {
					var ctx = "/arjccm/app"
					$.get(ctx+'/getAppInfo',function(data){
						$("#appUrl").attr("href",data); 
					})
				});
		</script>   
	</body>
</html>