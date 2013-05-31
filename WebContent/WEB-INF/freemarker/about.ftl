<#import '/spring.ftl' as spring />

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
			<h2>关于海涛帮</h2>
				<h3>项目背景</h3>
					<p>目前，网购已经成为一个普通人生活中不可或缺的一部分。
					但是由于一些地域的限制，我们想买到国外的一些产品时，
					往往需要很费时费力的寻找帮我们代购的人。于是“海涛帮"代购系统就应运而生了，
					它解决了用户在想购买国外产品但是没有国外地址的尴尬局面--为用户提供一个虚拟
					的国外中转地址。</p>
					
				<h3>使用方法</h3>
					<p>以亚马逊为例，用户在国外购物网站买的东西无法直接运送到国内，该怎么办呢？
					他们只要在“海涛帮”注册一个地址，“海涛帮”则会提供一个国外的中转仓库地址，
					用户将在亚马逊的订单地址填写该中转仓库地址，并且填写自己想要寄往的国内地址，
					支付费用，用户就可以收到“海涛帮”海外代购系统中转过来的商品。</p>
			<#--  	
				<h3>使用技术</h3>
					<p>1)Spring MVC架构</p>
					<p>2)Hibernate ORM框架</p>
					<p>3)Boostrap开源CSS框架</p>
					<p>4)FreeMarker作为视图选择器进行页面展现</p>
					<p>5)HTML5进行部分页面展现</p>
				-->	
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