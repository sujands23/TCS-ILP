<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="java.util.ArrayList"%>
<%@page import="model.ProductStock"%>
<html>
<head>

<script>
function Validate() {
var form = document.forms[0]
var counter=0;
for (i = 0; i < form.elements.length ; i++) {
if (form.elements[i].type == "checkbox"){
if(form.elements[i].checked == true){
counter++
}
}
}
if (counter == 0)
{
alert("You have not selected any fields");
window.open('d2.jsp');
}
else{
return;
}
}

</script>


<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Complaint</title>

</head>
<body>


<%
	ArrayList<ProductStock> psList = (ArrayList<ProductStock>) session.getAttribute("temp");
%>
<form name="myForm" action="DefectTrackingController" method="post">
<input type="hidden" name="action" value="ComplaintLodged">
<center>
<table border=1>
	<tr>
		<td>Order ID </td>
		<td>Product Code</td>
		<td>Product Model ID</td>
		<td>Product Model Name</td>
		<td>Select</td>
	</tr>
	<%
		for (int i = 0; i < psList.size(); i++) 
		{
	%>
	<tr>
		<td><%=psList.get(i).getOrderId()%></td>
		<td><%=psList.get(i).getProductCode()%></td>
		<td><%=psList.get(i).getProductModelId()%></td>
		<td><%=psList.get(i).getProductName()%></td>
		<td><input type="checkbox" name="product" value="<%=i%>"></td>
	</tr>
	<%
		}
	%>
</table>
<br></br>

<input type="submit" value="Report Defect" onClick="Validate()">
<input type="reset" value="Reset">
</center>
</form>

</body>
</html>
