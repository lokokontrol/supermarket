<#include "/header.ftl">
		
	<form role="form" name="order" action="/orders/order-new/add/" method="post">
        
        <#if errorIdBill??>
        <div class="form group has-error"> 
        <#else>
           <div class="form group">
        </#if>
        	<label for="idBill">Nº Bill</label>
        	<@spring.bind "orderDTO.idBill"/>
        	<input type="text" name="${spring.status.expression}" id="${spring.status.expression}" value="${spring.status.value?default('')}" class="form-control" id="idBill">
		    <span class="help-block"><@spring.showErrors "<br/>"/> </span>
		</div>
		 
		<!-- con las ?? significa que si errorCustomer es NULO , da FALSE -->
         <#if errorCustomer??>
         	<div class="form group has-error">
        <#else>
        	 <div class="form group">
        </#if>
        	 <label for="customer">Customer</label>
        	 <@spring.bind "orderDTO.customer"/>
       		 <input type="text" name="${spring.status.expression}" id="${spring.status.expression}" value="${spring.status.value?default('')}" class="form-control"id="customer">
       		 <span class="help-block"><@spring.showErrors "<br/>"/> </span>
        </div>
       
  
        <button class="btn btn-lg btn-success btn-block" type="submit">Create Order</button>
    </form>

<#include "/footer.ftl">