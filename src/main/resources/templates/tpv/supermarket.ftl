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
						<th>Price OrderLine </th>
					</tr>
				</thead>
				<tbody>
					<#list order.orderLines as orderLine>
					<tr>
						<td>${orderLine.order.id}</td>
						<td>${orderLine.numLineaPedido}</td>
						<td>${orderLine.product.name}</td>
						<td>${orderLine.quantity}</td>
						<td>${orderLine.priceOrderLine} €</td>
					</tr>
					</#list>
				</tbody>
			</table>
		</div>
	</div>






       	<label for = "price" > Total Price : </label>
        <input type="text" disabled="true" class="form-control" value="${totalPrice} €" id="price" readOnly = >




<#include "/footer.ftl">