<#include "/header.ftl">
	<div class="row">
		<div class="table-responsive">
			<table class="table table-hover">
				<thead>
					<tr>
						<th>Product</th>
					</tr>
				</thead>
				<tbody>
					<#list products as product>
					<tr>
						<td><a href="/orders/order/${order.id}/addProduct/${product.id}">${product.name}</a></td>
					</tr>
					</#list>
				</tbody>
			</table>
		</div>
	</div>
	
	
<#include "/footer.ftl">
