<#assign security=JspTaglibs["http://www.springframework.org/security/tags"] />
<@security.authentication property="principal" var="principal" />
<#import '/spring.ftl' as spring />

<#if !principal?? || principal == 'anonymousUser'>
	<#assign loggedIn = false />
<#else>
	<#assign loggedIn = true />
</#if>

<#macro template cssList = [], jsList = []>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">

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
	
	<title>HTB | Nextplus Inc.</title>
</head>
<body>
<div class="container-fluid">
	<div id="hd" class="clearfix">
		<div class="navbar navbar-inverse">
			<div class="navbar-inner">
				<a class="brand" href="/htb2">HTB</a>
				<ul class="nav">
					<#if !loggedIn>
						<li><a href="<@spring.url '/cs/members/new' />">用户注册</a></li>
						<li><a href="<@spring.url '/login' />">用户登陆</a></li>
					<#else>
						<li><a href="<@spring.url '/cs/transactions' />">我的运单</a></li>
						<li><a href="<@spring.url '/cs/mails' />">收件箱</a></li>
						<li><a href="<@spring.url '/cs/members/${principal.id}/edit' />">更改密码</a></li>
						<li><a href="<@spring.url '/logout' />">退出系统</a></li>
					</#if>
				</ul>
			</div>
		</div>
	</div>
	<div id="bd" class="clearfix">
		<#nested />
	</div>
	<div id="ft" class="clearfix pull-right muted">&copy; 2012 HTB | Nextplus Inc.</div>
</div>
<script type="text/javascript">
<!--

$(document).ready(function() {
// 	$.loading({onAjax: true, text: 'Loading...', delay: 0});
	
	$('form a.reqbtn').click(function(e) {
		e.preventDefault();
		if (confirm('确认执行此操作？')) {
			$(this).parents('form').submit();
		}
	});
	
	$('input.datepicker').datepicker({
		dateFormat: 'yy-mm-dd',
		changeYear: true,
		yearRange: "1949:+20"
	});
	
});

//-->
</script>
</body>
</html>
</#macro>