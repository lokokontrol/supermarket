<#include "/header.ftl">
    <form name="detailsProductForm" class="form-signin" action="/orders/order/${order.id}/addProduct/${orderLine.product.id}" method="post">
       
       <div class="col-md-6">
       <div class="form-group">
       	<label for = "name" > Product : </label>
        <input type="text" disabled="true" class="form-control" value="${orderLine.product.name}" id="name">
		</div>
       <div class="form-group">
       <label for = "offer"> Offer : </label>
        <input type="text"  disabled="true" class="form-control" value="${orderLine.product.offerName}" id="offer">
        </div>
        
        <div class="form-group">
      		 <label for = "offer"> Unit Price : </label>
       		 <input type="text"  disabled="true" class="form-control" value="${orderLine.product.price.unitPrice.priceUnit} €" id="offer">
        </div>
        </div>
      
       
       <div class="col-md-6">
       <label for = "quantity"> Quantity : </label>
        <@spring.bind "orderLine.quantity"/>
        <input type="number" class="form-control" placeholder="0..10" name="quantity">
		</div>

        <button class="btn btn-lg btn-primary btn-block" type="submit">Add to Cart</button>
      </form>
<#include "/footer.ftl">