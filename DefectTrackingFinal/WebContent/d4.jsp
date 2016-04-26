<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="java.util.ArrayList"%>
<%@page import="model.ProductStock"%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>newOrder1</title>
</head>
<body>
Your complaint has been lodged successfully !
<%ArrayList<ProductStock> productList=(ArrayList<ProductStock>)session.getAttribute("temp"); %>
<%for(int i=0;i<productList.size();i++)
{
%>
<br></br>
The defected Product Code is	:
<%=productList.get(i).getProductCode() %>
<br></br>
The defect Id is	:
<%=productList.get(i).getDefectId() %>
<br></br>
<%
}
%>
</body>
</html>