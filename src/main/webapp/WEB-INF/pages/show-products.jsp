<%@include file="./header.jspf"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <h2>${pageTitle}</h2>
<table class="table table-bordered  table-striped">
	<thead>
		<tr>
			<th>S.no</th>
			<th>Product Name</th>
			<th>Product Price</th>
			<th>Product Description</th>
			<th>Product Quantity</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${products}" var="p" varStatus="status">
			<tr>
				<td>${status.index+1}</td>
				<td>
				<a href="./product-details?id=${p.product_ID}" class="btn btn-link" style="text-decoration:none;">
				${p.product_name}</a>
				</td>
				<td>${p.product_price}</td>
				<td>${p.product_desc}</td>
				<td>${p.quantity}</td>
			</tr>

		</c:forEach>
	</tbody>

</table>

<%@include file="./footer.jspf"%>