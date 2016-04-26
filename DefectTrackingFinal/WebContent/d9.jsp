<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="java.util.ArrayList"%>
<%@page import="model.Defect"%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>imView</title>

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
window.open('d9.jsp');
}
else{
return;
}
}

</script>

</head>
<body>
<% ArrayList<Defect> dList=(ArrayList<Defect>)session.getAttribute("s"); %>
<form name="newOrderForm" action="DefectTrackingController" method="post">
<input type="hidden" name="action" value="imNewOrder">
<center>
<table border="2">
<tr>
<td>Order ID</td>
<td>Defect ID</td>
<td>Defect Product Code</td>
<td>ProductModel ID</td>
<td>Status</td>
<td>Place New Order</td>
<td>Allocate New Product</td>
</tr>
<%
for(int i=0;i<dList.size();i++)
{
%>
<tr>
<td><%=dList.get(i).getOrder_id() %></td>
<td><%=dList.get(i).getDefectID() %></td>
<td><%=dList.get(i).getDefectProCode() %></td>
<td><%=dList.get(i).getPm_id() %></td>
<td><%=dList.get(i).getDefectStatus() %></td>
<% 
if(dList.get(i).getDefectStatus().equalsIgnoreCase("Allocated"))
{
%>
<td></td>
<% 
}
else
{
%>
<td><input type="checkbox" name="im" value="<%=i %>"></td>
<td><input type="submit" value="Allocate" onClick="Validate()"></td>

<%
}
%>
</tr>
<%
}
%>
</center>
</table>
<br></br>
<center></center>

</form>
</body>
</html>