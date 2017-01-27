<#include "/header.ftl">
	<div class="row">
		<div class="table-responsive">
			<table class="table table-hover">
				<thead>
					<tr>
						<th>Nª Bill</th>
						<th>Customer</th>
					</tr>
				</thead>
				<tbody>
					<#list orders as order>
					<tr>
						<td><a href="/orders/order/${order.id}">${order.idBill}</a></td>
						<td><a href="/orders/order/${order.id}">${order.customer}</a></td>
					</tr>
					</#list>
				</tbody>
			</table>
		</div>
	</div>
	
	
<#include "/footer.ftl">
