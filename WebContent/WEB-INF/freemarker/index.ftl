<#import '/spring.ftl' as spring />

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<@spring.url '/static/assets/bootstrap/css/bootstrap.min.css' />" type="text/css" />
	<!-- Plugins -->
	<link rel="stylesheet" href="<@spring.url '/static/assets/js/jquery/select2/select2.css' />" type="text/css" />
	<link rel="stylesheet" href="<@spring.url '/static/assets/js/jquery/select2/select2-bootstrap.css' />" type="text/css" />
	<link rel="stylesheet" href="<@spring.url '/static/assets/js/jquery/ui/css/custom-theme/jquery-ui-1.10.0.custom.css' />" type="text/css" />
	
	<link rel="stylesheet" href="<@spring.url '/static/assets/css/backend/template.css' />" type="text/css" />
	<link rel="stylesheet" href="<@spring.url '/static/assets/css/backend/style.css' />" type="text/css" />
	
	<script type="text/javascript" src="<@spring.url '/static/assets/js/jquery/jquery-1.9.0.min.js' />"></script>
	<script type="text/javascript" src="<@spring.url '/static/assets/bootstrap/js/bootstrap.min.js' />"></script>
	<script type="text/javascript" src="<@spring.url '/static/assets/js/jquery/ui/js/jquery-ui-1.9.2.custom.min.js' />"></script>
	<script type="text/javascript" src="<@spring.url '/static/assets/js/jquery/validation/jquery.validate.min.js' />"></script>
	<script type="text/javascript" src="<@spring.url '/static/assets/js/jquery/select2/select2.min.js' />"></script>
	
<link rel="stylesheet" type="text/css" href="<@spring.url '/static/assets/css/index/css' />" media="screen">
<link rel="stylesheet" type="text/css" href="<@spring.url '/static/assets/css/index/stylesheet.css' />" media="screen">
<link rel="stylesheet" type="text/css" href="<@spring.url '/static/assets/css/index/pygment_trac.css' />" media="screen">
<link rel="stylesheet" type="text/css" href="<@spring.url '/static/assets/css/index/print.css' />" media="print">
<title>海涛帮|最简单的海外代购系统</title>
</head>
<body>
<header>
	<div class="inner">
		<h1><a href="/htb2" style="color: #fff">海涛帮</a></h1>
		<h2>最简单的海外代购系统</h2>
		<a href="https://github.com/wangheng/htb" class="button"><small>view project on</small>GitHub</a>
	</div>
</header>
<div id="content-wrapper">
	<div class="inner clearfix">
		<section id="main-content">
			<h2>仓库列表</h2>
			<table class="table table-hover">
			 <tr class="success">
			    <td style="text-align: center; width: 30%">仓库名字</td>
			    <td style="text-align: center;">仓库地址</td>
			  </tr>
			<#list depots as depot>
				<tr class="info">
					<td style="text-align: center; width: 30%">${depot.name}</td>
					<td style="text-align: center;">${depot.location}
						<span class="label">主要接收类似${depot.description}运单</span>
					</td>
				</tr>
			</#list>
			</table>
			<h2>用户指南</h2>
				<p>做最专业的代购服务，为祖国人民买到更多好东西</p>
			
		</section>
		<aside id="sidebar">
			<a href="cs/members/new" class="button">用户注册</a>
			<a href="login" class="button">用户登录</a>
			<a href="about" class="button">关于海涛帮</a>
		</aside>
	</div>
</div>
<footer>
	<div class="inner">
		<h2>&copy;Nextplus Inc. Harris Wang</h2>
	</div>
</footer>
</body>
</html>