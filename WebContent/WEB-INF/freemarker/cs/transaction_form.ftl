<#import '/spring.ftl' as spring />
<#import '*/template/backend.ftl' as backend />
<@backend.template>
<div class="path">
	<ul class="breadcrumb">
		<li><a href="#">HTB</a> <span class="divider">/</span></li>
		<li class="active"><a
			href="<@spring.url '/cs/transactions' />">运单管理</a> <span
			class="divider">/</span></li>
		<li class="active">${transactionForm.new?string('添加', '编辑')}</li>
	</ul>
</div>
<#if transactionForm.new>
	<#assign url = '/cs/transactions/' />
<#else>
	<#assign url = '/cs/transactions/${transactionForm.id}' />
</#if>
<form  action="<@spring.url url />" method="post" class="form-horizontal" enctype="multipart/form-data">
	<div class="control-group">
		<@spring.bind 'transactionForm.name' />
		<label class="control-label">运单名称</label>
		<div class="controls">
			<input type="text" name="${spring.status.expression}"
					value="${spring.status.value!''}" class="text span3" />
			<#list spring.status.errorMessages as msg>
				<span class="text-error">${msg}</span>
			</#list>
		</div>
	</div>
	<div class="control-group">
		<@spring.bind 'transactionForm.description' />
		<label class="control-label">运单描述</label>
		<div class="controls">
			<input type="text" name="${spring.status.expression}"
					value="${spring.status.value!''}" class="text span3" />
			<#list spring.status.errorMessages as msg>
				<span class="text-error">${msg}</span>
			</#list>
		</div>
	</div>
	<div class="control-group">
		<@spring.bind 'transactionForm.location' />
		<label class="control-label">目标地址</label>
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
			<a href="<@spring.url '/cs/transactions' />">返回</a>
		</div>
	</div>
</form>
</@backend.template>
