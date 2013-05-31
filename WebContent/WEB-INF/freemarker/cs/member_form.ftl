<#import '/spring.ftl' as spring />
<#import '*/template/backend.ftl' as backend />
<@backend.template>
<div class="path">
	<ul class="breadcrumb">
		<li><a href="#">HTB</a> <span class="divider">/</span></li>
		<#if memberForm.new>
			<li class="active">用户注册</li>
		<#else>
			<li class="active">修改密码</li>
		</#if>
		
	</ul>
</div>
<#if memberForm.new>
	<#assign url = '/cs/members' />
<#else>
	<#assign url = '/cs/members/${memberForm.id}' />
</#if>
<form  action="<@spring.url url />" method="post" class="form-horizontal">
	<div class="control-group">
		<@spring.bind 'memberForm.fullname' />
		<label class="control-label">姓名</label>
		<div class="controls">
			<input type="text" name="${spring.status.expression}"
					value="${spring.status.value!''}" class="text span3" placeholder="请输入你的姓名" />
			<#list spring.status.errorMessages as msg>
				<span class="text-error">${msg}</span>
			</#list>
		</div>
	</div>
	<div class="control-group">
		<@spring.bind 'memberForm.username' />
		<label class="control-label">用户名</label>
		<div class="controls">
			<input type="text" name="${spring.status.expression}"
					value="${spring.status.value!''}" class="text span3" placeholder="请输入你的用户名"/>
			<#list spring.status.errorMessages as msg>
				<span class="text-error">${msg}</span>
			</#list>
		</div>
	</div>
	<div class="control-group">
		<@spring.bind 'memberForm.password' />
		<label class="control-label">密码</label>
		<div class="controls">
			<input type="password" name="${spring.status.expression}"
					value="${spring.status.value!''}" class="text span3" placeholder="请输入你的密码"/>
			<#list spring.status.errorMessages as msg>
				<span class="text-error">${msg}</span>
			</#list>
		</div>
	</div>
	<div class="control-group">
		<@spring.bind 'memberForm.mail' />
		<label class="control-label">邮箱</label>
		<div class="controls">
			<input type="email" name="${spring.status.expression}"
					value="${spring.status.value!''}" class="text span3" />
			<#list spring.status.errorMessages as msg>
				<span class="text-error">${msg}</span>
			</#list>
		</div>
	</div>
	<div class="control-group">
		<@spring.bind 'memberForm.cellphone' />
		<label class="control-label">手机</label>
		<div class="controls">
			<input type="text" name="${spring.status.expression}"
					value="${spring.status.value!''}" class="text span3" />
			<#list spring.status.errorMessages as msg>
				<span class="text-error">${msg}</span>
			</#list>
		</div>
	</div>
	<div class="control-group">
		<div class="controls">
			<button type="submit" class="btn btn btn-primary">提交</button>
			<a class="btn btn btn-primary" href="<@spring.url '/cs/' />">登录</a>
		</div>
	</div>
</form>
</@backend.template>
