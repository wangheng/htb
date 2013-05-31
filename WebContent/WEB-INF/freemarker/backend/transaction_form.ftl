<#import '/spring.ftl' as spring />
<#import '*/template/backend.ftl' as backend />
<@backend.template>
<div class="path">
	<ul class="breadcrumb">
		<li><a href="#">HTB</a> <span class="divider">/</span></li>
		<li class="active"><a
			href="<@spring.url '/backend/transactions' />">运单管理</a> <span
			class="divider">/</span></li>
		<li class="active">${transactionForm.new?string('添加', '编辑')}</li>
	</ul>
</div>
<#if transactionForm.new>
	<#assign url = '/backend/transactions/' />
<#else>
	<#assign url = '/backend/transactions/${transactionForm.id}' />
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
		<@spring.bind 'transactionForm.depot.id' />
		<label class="control-label">存放仓库</label>
		<div class="controls">
			<select name="${spring.status.expression}" class="span3">
				<#list depots as depot>
					<option value="${depot.id}" 
						<#if spring.status.value?? && spring.status.value == depot.id>
						selected="selected"</#if>>${depot.name}</option>
				</#list>
			</select>
			<#list spring.status.errorMessages as msg>
				<span class="text-error">${msg}</span>
			</#list>
		</div>
	</div>
	<div class="control-group">
		<@spring.bind 'transactionForm.state' />
		<label class="control-label">运单状态</label>
		<div class="controls">
			<select name="${spring.status.expression}" class="span3">
				<#list states as state>
					<option value="${state}" 
						<#if spring.status.value?? && spring.status.value == state>
						selected="selected"</#if>>${state.ex}</option>
				</#list>
			</select>
			<#list spring.status.errorMessages as msg>
				<span class="text-error">${msg}</span>
			</#list>
		</div>
	</div>
	<div class="control-group">
		<@spring.bind 'transactionForm.expense' />
		<label class="control-label">费用</label>
		<div class="controls">
			<input type="text" name="${spring.status.expression}"
					value="${spring.status.value!''}" class="text span3" />
			<#list spring.status.errorMessages as msg>
				<span class="text-error">${msg}</span>
			</#list>
		</div>
	</div>
	<div class="control-group">
		<@spring.bind 'transactionForm.cfmNotes' />
		<label class="control-label">备注</label>
		<div class="controls">
			<input type="text" name="${spring.status.expression}"
					value="${spring.status.value!''}" class="text span3" />
			<#list spring.status.errorMessages as msg>
				<span class="text-error">${msg}</span>
			</#list>
		</div>
	</div>
	<div class="control-group">
		<@spring.bind 'transactionForm.logisticsCorp.id' />
		<label class="control-label">物流公司</label>
		<div class="controls">
			<select name="${spring.status.expression}" class="span3">
				<#list logisticsCorps as logisticsCorp>
					<option value="${logisticsCorp.id}" 
						<#if spring.status.value?? && spring.status.value == logisticsCorp.id>
						selected="selected"</#if>>${logisticsCorp.name}</option>
				</#list>
			</select>
			<#list spring.status.errorMessages as msg>
				<span class="text-error">${msg}</span>
			</#list>
		</div>
	</div>
	<div class="control-group">
		<@spring.bind 'transactionForm.logisticsNotes' />
		<label class="control-label">运单号</label>
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
			<a href="<@spring.url '/backend/transactions' />">返回</a>
		</div>
	</div>
</form>
</@backend.template>
