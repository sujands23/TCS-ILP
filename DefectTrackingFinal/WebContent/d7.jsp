<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="java.util.ArrayList"%>
<%@page import="model.DefectTrackingDAO"%>
<%@page import="model.Defect"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<table border="2">
<%
ArrayList<Defect> defectList = (ArrayList<Defect>) session.getAttribute("defectDisplay");
 
%>
<tr>
<th>Defect Product Code</th>
<th>Replacement Product Code</th>
<th>Order ID</th>
<th>Defect ID</th>
<th>Productmodel ID</th>

<th>Status</th>
</tr>
<%for(int i=0;i<defectList.size();i++)
{
%>
<tr>
<td><%=defectList.get(i).getDefectProCode() %></td>
<td><%=defectList.get(i).getReplaceProCode() %></td>
<td><%=defectList.get(i).getOrder_id() %></td>
<td><%=defectList.get(i).getDefectID() %></td>
<td><%=defectList.get(i).getPm_id() %></td>

<td><%=defectList.get(i).getDefectStatus() %></td>
</tr>
<%
}
%>
</table>
</body>
</html>