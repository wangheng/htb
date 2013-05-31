<#import '/spring.ftl' as spring />
<#import '*/template/backend.ftl' as backend />
<@backend.template>
<div class="path">
	<ul class="breadcrumb">
		<li>
			<a href="#">HTB</a>
			<span class="divider">/</span>
		</li>
		<li class="active">
			<a href="<@spring.url '/cs/transactions' />">运单管理[${transaction.name}]</a>
		</li>
	</ul>
</div>
<div class="row-fluid">
	<div class="span12">
		<table class="table table-bordered table-hover table-condensed">
			<tbody>
				<tr>
					<th width="120">运单名称</th>
					<td>${transaction.name!'n/a'}</td>
				</tr>
				<tr>
					<th>运单描述</th>
					<td>${transaction.description!'n/a'}</td>
				</tr>
				<tr>
					<th>目标地址</th>
					<td>${transaction.location!'n/a'}</td>
				</tr>
				<tr>
					<th>存放仓库</th>
					<td>${(transaction.depot.name)!'n/a'}</td>
				</tr>
				<tr>
					<th>运单状态</th>
					<td>${transaction.state.ex}</td>
				</tr>
				<tr>
					<th>费用</th>
					<td>${transaction.expense!'n/a'}</td>
				</tr>
				<tr>
					<th>备注</th>
					<td>${transaction.cfmNotes!'n/a'}</td>
				</tr>
				
				<tr>
					<th>物流公司</th>
					<td>${(transaction.logisticsCorp.name)!'n/a'}</td>
				</tr>
				<tr>
					<th>运单号</th>
					<td>${transaction.logisticsNotes!'n/a'}</td>
				</tr>
				<tr>
					<th>创建时间</th>
					<td>${transaction.createDate?string('yyyy-MM-dd HH:mm:ss')}</td>
				</tr>
				<tr>
					<th>最后更新</th>
					<td>
						<#if transaction.updateDate??>
							${transaction.updateDate?string('yyyy-MM-dd HH:mm:ss')}
						</#if>
					</td>
				</tr>
			</tbody>
		</table>
		<div style="height: 50px;">
			<p class="btn">网上支付</p>
			<input class="btn" type="checkbox" /><img alt="" src="<@spring.url '/static/assets/img/alipay_logo.png' />">
		</div>
		<div>
			<a href="https://www.alipay.com/cooperate/gateway.do" class="btn btn-danger">立即支付</a>
		</div>
	</div>
</div>
</@backend.template>