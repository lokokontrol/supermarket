<#include "/header.ftl">
       
       <div class="col-md-6">
      	 <div class="form-group">
       		<label for = "name" > Product : </label>
        	<input type="text" disabled="true" class="form-control" value="${product.name}" id="name">
		 </div>
       	 <div class="form-group">
      		 <label for = "offer"> Offer : </label>
       		 <input type="text"  disabled="true" class="form-control" value="${product.offerName}" id="offer">
        </div>
        
          <div class="form-group">
      		 <label for = "offer"> Unit Price : </label>
       		 <input type="text"  disabled="true" class="form-control" value="${product.price.unitPrice.priceUnit} €" id="offer">
        </div>
        
        </div>
      
     


<#include "/footer.ftl">