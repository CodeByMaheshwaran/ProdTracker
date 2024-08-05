
<%@include file="./header.jspf"%>
<h2>Product Details for ${product.product_name}</h2>
<a href="./edit-product?id=${product.product_ID}" class="btn btn-primary">Edit</a>
<div class="row">
	<div class="col">
		<table class="table"> 
			<tbody>
				<tr>
					<td>Name</td>
					<td>${product.product_name}</td>
				</tr>
				<tr>
					<td>Price</td>
					<td>${product.product_price}</td>
				</tr>
				<tr>
					<td>Description></td>
					<td>${product.product_desc}</td>
				</tr>
				<tr>
					<td>Quantity></td>
					<td>${product.quantity}</td>
				</tr>
				<tr>
					<td>Discontinued ?</td>
					<td>${product.discontinued==1 ? "Yes" : "No" }</td>
				</tr>
			</tbody>

		</table>
	</div>
	<div class="col"></div>
</div>
<%@include file="./footer.jspf"%>