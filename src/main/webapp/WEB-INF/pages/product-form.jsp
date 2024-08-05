
<%@include file="./header.jspf"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<div class="row">
<div class="col">

<sf:form modelAttribute="pr" action="save-product">
 <sf:hidden path="product_ID"/>
	<div class="row form-group m-2">
		<label class="col-md-4" for="product_name">Product Name</label>
		<div class="col-md-8">
			<sf:input path="product_name" cssClass="form-control" />
			<sf:errors path="product_name" cssClass="text-danger"/>
		</div>
	</div>
	
	<div class="row form-group m-2">
		<label class="col-md-4" for="product_price">Product Price</label>
		<div class="col-md-8">
			<sf:input path="product_price" cssClass="form-control" />
			<sf:errors path="product_price" cssClass="text-danger"/>
		</div>
	</div>
	
	<div class="row form-group m-2">
		<label class="col-md-4" for="product_desc">Product Description</label>
		<div class="col-md-8">
			<sf:input path="product_desc" cssClass="form-control" />
		    <sf:errors path="product_desc" cssClass="text-danger"/>
		</div>
	</div>
	
	<div class="row form-group m-2">
		<label class="col-md-4" for="quantity">Product Quantity</label>
		<div class="col-md-8">
			<sf:input path="quantity" cssClass="form-control" />
		    <sf:errors path="quantity" cssClass="text-danger"/>
		</div>
	</div>
	
	<div class="row form-group m-2">
		<label class="col-md-4" for="discontinued">Discontinue</label>
		<div class="col-md-8">
		  <label>
			<sf:radiobutton path="discontinued" value="1"/>
		    Yes
		  </label>  
		  <label>
			<sf:radiobutton path="discontinued" value="0"/>
		    No
		  </label>
		  <div>
		   <sf:errors path="discontinued" cssClass="text-danger"/>
		  </div>
		</div>
	</div>
	
	<div class="row form-group m-2">
		<label class="col-md-4"></label>
		<div class="col-md-8">
			<button class="btn btn-primary">Submit changes</button>
		</div>
	</div>

</sf:form>

</div>
<div class="col"></div>
</div>
<%@include file="./footer.jspf"%>