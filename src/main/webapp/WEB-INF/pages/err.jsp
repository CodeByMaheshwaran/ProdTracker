
<%@page import="java.io.PrintWriter"%>
<%@include file="./header.jspf"%>
<h3 class="text-danger">OOPS! There was an error while processing your request</h3>
<p>Please retry after a while, and if the error persists, please write email to 
<a href="mailto:helpdesk@kmahesh.com" style="text-decoration:none;">helpdesk@kmahesh.com</a> </p>

<a class="btn-btn-link" style="text-decoration:none;" onclick="showErrorDetails()">Show Technical Details</a>
<pre style="visibility:hidden;" id="errorDetails">
<%
Exception ex = (Exception) request.getAttribute("ex");
ex.printStackTrace(new PrintWriter(out));
%>
</pre>
<%@include file="./footer.jspf"%>
<script>
function showErrorDetails(){
	document.getElementById("errorDetails").style.visibility="visible";
}
</script>