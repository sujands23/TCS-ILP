<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="java.util.ArrayList"%>
<%@page import="model.pojo.ProductModel"%>
<%@page import="model.dao.ProductModelDAO"%>
<%@page import="model.pojo.RetailerProduct"%>
<html>
<head>
<meta name="keywords" content="" />
<meta name="description" content="" />
<meta http-equiv="content-type" content="text/html; charset=utf-8" />

<link href="style.css" rel="stylesheet" type="text/css" media="screen" />
<title>Telecom Store Inventory</title>
<script src="TSI.js"></script>

</head>
<body>
<div id="wrapper">
	<div id="header">
		<div id="logo">
			<h1><a href="#">Telecom Store Inventory  </a></h1>
			<p> Great Inventory store</p>
		</div>
	</div>
	<!-- end #header -->
	<div id="menu">
		<ul>
			<li><a href="home.jsp">Home</a></li>
			<li class="current_page_item"><a href="#">Product</a></li>
			<li><a href="stock.jsp">Stock</a></li>
			<li><a href="package.jsp">Packages</a></li>
			<li><a href="retailer.jsp">Retailer</a></li>
			<li><a href="logout.jsp">Logout</a></li>
			
		</ul>
	</div>
	
<div id="sidebar">
			<ul>
				<li><center>
					<div id="search" >
					<form method="get" action="#">
				<h3>Product Menu</h3>
					</form>
					</div>
					<div style="clear: both;">&nbsp;</div>
				</center></li>
				
			
			          <center><ul>
                  			   <li><a href="addProduct.jsp">Add Product</a></li>
                  		 	   <li><a href="deleteProduct.jsp">Delete Product</a></li>
                  			   <li><a href="updateProduct.jsp">Update Product</a></li>
                  			   <li><a href="viewProduct.jsp">View Product</a></li>
                  			   <li><a href="viewQuantity.jsp">View Quantity</a></li>
                   			   <li><a href="tagProduct.jsp">Tag Product</a></li>
                    	</ul></center>		
               			   
				</ul></div>        
</div>	<!-- end #menu -->
				
	<div id="page">
	<div id="page-bgtop">
	<div id="page-bgbtm">
		<div id="content">
			<div class="post">
				<div class="entry">
				<H3>DELETING PRODUCT</H3>
				
				
				
<form name="input" action="DeleteProductController" method="post"><BR><BR>
<table border="1" style="font-size:15px">
<tr>
<th><font color=black size=2>Retailer Id</font></th>
</tr>						
<%ArrayList<RetailerProduct> psList=(ArrayList<RetailerProduct>)request.getAttribute("temp");%>						
<%
	for(RetailerProduct rp:psList){
	%>
<input type="hidden" name=rid value="<%=rp.getProductmodelid() %>"></input>	
<tr>
<td><%=rp.getRetailerid() %></td>

</tr>
<% 
}
%>
</table>
<br>
<input type="submit" value="Delete" onclick="return confirm('Are you sure you want to delete?')"></input></a>
<!--				<tr><td><div align="right" ><input type="submit" value="Delete" onclick="validate_deleteProduct()">-->
            <input type="reset" value="Reset"></div></td></tr>


	</form>


				</div>
			</div>
		</div>	<!-- end #menu -->
				
				
</div>
	<!-- end #page -->
</div>
	<div id="footer">
		
</div>
	<!-- end #footer -->
</body>
</html>