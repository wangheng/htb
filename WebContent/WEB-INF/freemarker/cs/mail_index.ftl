<#import '/spring.ftl' as spring />
<#import '*/template/backend.ftl' as backend />


<@backend.template>
<div class="path">
	<ul class="breadcrumb">
		<li>
			<a href="<@spring.url '/cs/' />">HTB</a>
			<span class="divider">/</span>
		</li>
		<li class="active">收件箱</li>
	</ul>
</div>

<table class="table table-bordered table-hover table-condensed">
	<caption>
		<span>列表</span>
		<span class="label">Total: ${paginator.total}</span>
	</caption>
	<thead>
		<tr>
			<th width="30">编号</th>
			<th width="240">收件人姓名</th>
			<th>消息内容</th>
			<th width="60">状态</th>
			<th width="120">发送时间</th>
			<th width="180">操作</th>
		</tr>
	</thead>
	<tbody>
	<#if !mails?? || mails?size <= 0>
		<p>还没有消息哦</p>
	<#else>
		<#list mails as mail>
			<tr>
				<td>${mail_index + 1}</td>
				<td>
					${mail.member.fullname}
				</td>
				<td>
					
					${mail.content}
				</td>
				<td>
					<span class="label"></span>
				</td>
				<td>
					<span class="muted">${mail.createDate?string('yyyy-MM-dd HH:mm:ss')}</span>
				</td>
				<td>
				
					<#if mail.deletable>
						<form action="<@spring.url '/cs/mails/${mail.id}' />" method="post" class="inline">
							<input type="hidden" name="_method" value="DELETE" />
							| <a class="reqbtn" href="#">删除</a>
						</form>
					</#if>
				</td>
			</tr>
		</#list>
	</#if>
	</tbody>
</table>
<div class="pagination pagination-centered">
	<ul>
		<li>
			<a href="?p=${paginator.previous}">上一页</a>
		</li>
		<#list 1..paginator.last as p>
			<li class="<#if p == paginator.current>active</#if>">
				<a href="<@spring.url '/cs/mails?p=${p}' />">${p}</a>
			</li>
		</#list>
		<li>
			<a href="?p=${paginator.next}">下一页</a>
		</li>
	</ul>
</div>
</@backend.template>