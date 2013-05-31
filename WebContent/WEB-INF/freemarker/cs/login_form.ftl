<#import '/spring.ftl' as spring />
<#import '*/template/backend.ftl' as backend />
<@backend.template>
<div class="path">
	<ul class="breadcrumb">
		<li><a href="#">HTB</a> <span class="divider">/</span></li>
		<li class="active">用户</li>
	</ul>
</div>
<form  action="<@spring.url '/j_spring_security_check' />" method="post" class="form-horizontal">
	<div class="control-group">
		<label class="control-label">用户名</label>
		<div class="controls">
			<input type="text" name="j_username" 
					class="text" />
		</div>
	</div>
	<div class="control-group">
		<label class="control-label">密码</label>
		<div class="controls">
			<input type="password" name="j_password" 
					class="text" />
		</div>
	</div>
	<div class="control-group">
		<div class="controls">
			<button type="submit" class="btn btn btn-primary">登录</button>
			<a  class="btn btn-primary" href="<@spring.url '/cs/members/new' />">注册</a>
		</div>
	</div>
</form>
</@backend.template>
