<#import '/spring.ftl' as spring />
<#import '*/template/backend.ftl' as backend />
<@backend.template>
<div class="path">
	<ul class="breadcrumb">
		<li>
			<a href="<@spring.url '/backend/' />">HTB</a>
			<span class="divider">/</span>
		</li>
		<li class="active">用户管理</li>
	</ul>
</div>
<table class="table table-bordered table-hover table-condensed">
	<caption>
		<span>用户列表</span>
		<span class="label">Total: ${paginator.total}</span>
	</caption>
	<thead>
		<tr>
			<th width="30">编号</th>
			<th width="240">名称</th>
			<th>用户名</th>
			<th width="120">创建时间</th>
			<th width="180">操作</th>
		</tr>
	</thead>
	<tbody>
	<#list members as member>
		<tr>
			<td>
				<span class="muted">${member_index + 1}</span>
			</td>
			<td>${member.username}</td>
			<td>${member.fullname}</td>
			<td>
				<span class="muted">${member.createDate?string('yyyy-MM-dd HH:mm:ss')}</span>
			</td>
			<td>
				<#if member.deletable>
					<form action="<@spring.url '/backend/members/${member.id}' />" method="post" class="inline">
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
				<a href="<@spring.url '/backend/members?p=${p}' />">${p}</a>
			</li>
		</#list>
		<li>
			<a href="?p=${paginator.next}">下一页</a>
		</li>
	</ul>
</div>
</@backend.template>