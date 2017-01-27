<#import "/spring.ftl" as spring/>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<title>Supermarket</title>

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">

</head>
<body class="container">
	<div class="jumbotron text-center">
		<h3>SUPERMERCADO</h3>
	</div>
	<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="/">Kata SuperMarket</a>
    </div>
    <ul class="nav navbar-nav">
      <li class="active"><a href="/orders/order-new/add/">New Order</a></li>
      <li><a href="/orders/">Orders</a></li>
      <li><a href="/products/">Products</a></li> 
      <li>
      	<form role="form" class="form-inline" name="searchForm" action="/search/" method="post">
		     <div class="form-group">
		    	<label class="control-label" style="color:white" for="search">Search Product:</label>
      			<input type="text" placeholder="Macarrones" class="form-control"id="search" name="search">
      		 </div>
      		<button class="btn btn-success" type="submit">Search Product</button>
      	</form>
      </li>
    </ul>
  </div>
</nav>
	
	
