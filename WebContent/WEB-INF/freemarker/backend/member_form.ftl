<#import '/spring.ftl' as spring />
<#import '*/template/backend.ftl' as backend />
<@backend.template>
<div class="path">
	<ul class="breadcrumb">
		<li><a href="#">HTB</a> <span class="divider">/</span></li>
		<li class="active"><a
			href="<@spring.url '/cs/members' />">用户管理</a> <span
			class="divider">/</span></li>
		<li class="active">${memberForm.new?string('添加', '编辑')}</li>
	</ul>
</div>
<#if memberForm.new>
	<#assign url = '/cs/members/' />
<#else>
	<#assign url = '/cs/members/${memberForm.id}' />
</#if>
<form  action="<@spring.url url />" method="post" class="form-horizontal">
	<div class="control-group">
		<@spring.bind 'memberForm.fullname' />
		<label class="control-label">姓名</label>
		<div class="controls">
			<input type="text" name="${spring.status.expression}"
					value="${spring.status.value!''}" class="text span3" />
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
					value="${spring.status.value!''}" class="text span3" />
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
					value="${spring.status.value!''}" class="text span3" />
			<#list spring.status.errorMessages as msg>
				<span class="text-error">${msg}</span>
			</#list>
		</div>
	</div>
	<div class="control-group">
		<@spring.bind 'memberForm.mail' />
		<label class="control-label">邮箱</label>
		<div class="controls">
			<input type="text" name="${spring.status.expression}"
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
			<a href="<@spring.url '/cs/members' />">返回</a>
		</div>
	</div>
</form>
</@backend.template>
