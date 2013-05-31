<#import '/spring.ftl' as spring />
<#import '*/template/backend.ftl' as backend />
<@backend.template>
<div class="path">
	<ul class="breadcrumb">
		<li><a href="#">Creativoo</a> <span class="divider">/</span></li>
		<li class="active"><a
			href="<@spring.url '/backend/administrators' />">用户管理</a> <span
			class="divider">/</span></li>
		<li class="active">${administratorForm.new?string('添加', '编辑')}</li>
	</ul>
</div>
<#if administratorForm.new>
	<#assign url = '/backend/administrators/' />
<#else>
	<#assign url = '/backend/administrators/${administratorForm.id}' />
</#if>
<form  action="<@spring.url url />" method="post" class="form-horizontal">
	<div class="control-group">
		<@spring.bind 'administratorForm.fullname' />
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
		<@spring.bind 'administratorForm.username' />
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
		<@spring.bind 'administratorForm.password' />
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
		<div class="controls">
			<button type="submit" class="btn btn btn-primary">提交</button>
			<a href="<@spring.url '/backend/administrators' />">返回</a>
		</div>
	</div>
</form>
</@backend.template>
