<#include "/header.ftl">
	<div class="row">
		<div class="table-responsive">
			<table class="table table-hover">
				<thead>
					<tr>
						<th>Nº Order</th>
						<th>Nº OrderLine</th>
						<th>Product</th>
						<th>Quantity</th>
					</tr>
				</thead>
				<tbody>
					<#list order.orderLines as orderLine>
					<tr>
						<td>${orderLine.order.id}</td>
						<td>${orderLine.numLineaPedido}</td>
						<td>${orderLine.product.name}</td>
						<td>${orderLine.quantity}</td>
					</tr>
					</#list>
				</tbody>
			</table>
		</div>
	</div>
	<div class="col-md-6">
		<a href="/orders/order/${order.id}/addProduct" class="btn btn-success col-md-12" role="button">Add Product</a>
	</div>
	<div class="col-md-6">
		<a href="/orders/order/${order.id}/tpv" class="btn btn-success col-md-12" role="button">Go Pay</a>
	</div>
	
	
<#include "/footer.ftl">