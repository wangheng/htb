<#import '/spring.ftl' as spring />
<#import '*/template/backend.ftl' as backend />

<#assign from = RequestParameters['from']!'' />
<#assign to = RequestParameters['to']!'' />
<#assign state = RequestParameters['state']!'' />
<#assign depotId = RequestParameters['depotId']!'' />

<@backend.template>
<div class="path">
	<ul class="breadcrumb">
		<li>
			<a href="<@spring.url '/cs/' />">HTB</a>
			<span class="divider">/</span>
		</li>
		<li class="active">运单管理</li>
	</ul>
</div>
<div class="filter">
	<form action="" method="get" class="form-inline">
		<label>运单状态：</label>
		<select name="state" class="span2">
			<option value="">不限</option>
			<#list states as _state>
				<option value="${_state}"
					<#if state?? && state == _state>
					selected="selected" </#if>>${_state.ex}</option>
			</#list>
		</select>
		<label>起始日期：</label>
		<input type="text" name="from" value="${from}" class="span2 datepicker" />
		<button type="submit" class="btn btn-primary">查询</button>
		<a href="<@spring.url '/cs/transactions' />" class="btn">清除</a>
<!-- 		<a href="<@spring.url '/backend/transactions?disp=xlsx' />" class="btn">导出数据</a> -->
		<br />
		<label>所属仓库：</label>
		<select name="depotId" class="span2">
			<option value="">不限</option>
			<#list depots as depot>
				<option value="${depot.id}"
					<#if depotId?? && depotId == depot.id?string>
					selected="selected" </#if>>${depot.name}</option>
			</#list>
		</select>
		<label>截止日期：</label>
		<input type="text" name="to" value="${to}" class="span2 datepicker" />
	</form>
</div>
<div>
	<a href="<@spring.url '/cs/transactions/new' />" class="btn btn btn-primary">添加新运单</a>
</div>
<table class="table table-bordered table-hover table-condensed">
	<caption>
		<span>列表</span>
		<span class="label">Total: ${paginator.total}</span>
	</caption>
	<thead>
		<tr>
			<th width="30">编号</th>
			<th width="240">名称</th>
			<th>目标地址</th>
			<th width="60">状态</th>
			<th width="120">创建时间</th>
			<th width="180">操作</th>
		</tr>
	</thead>
	<tbody>
	<#list transactions as transaction>
		<tr>
			<td>${transaction_index + 1}</td>
			<td>
				<a href="<@spring.url '/cs/transactions/${transaction.id}' />">${transaction.name}</a>
			</td>
			<td>
				<span class="label">${transaction.member.fullname}</span>
				${transaction.location}
			</td>
			<td>
				<span class="label">${transaction.state.ex}</span>
			</td>
			<td>
				<span class="muted">${transaction.createDate?string('yyyy-MM-dd HH:mm:ss')}</span>
			</td>
			<td>
				<#if transaction.updatable>
					| <a href="<@spring.url '/cs/transactions/${transaction.id}/edit' />">编辑</a>
				</#if>
				<#if transaction.deletable>
					<form action="<@spring.url '/cs/transactions/${transaction.id}' />" method="post" class="inline">
						<input type="hidden" name="_method" value="DELETE" />
						| <a class="reqbtn" href="#">删除</a>
					</form>
				</#if>
			</td>
		</tr>
	</#list>
	</tbody>
</table>
<div class="pagination pagination-centered">
	<ul>
		<li>
			<a href="?p=${paginator.previous}">上一页</a>
		</li>
		<#list 1..paginator.last as p>
			<li class="<#if p == paginator.current>active</#if>">
				<a href="<@spring.url '/cs/transactions?p=${p}' />">${p}</a>
			</li>
		</#list>
		<li>
			<a href="?p=${paginator.next}">下一页</a>
		</li>
	</ul>
</div>
</@backend.template>