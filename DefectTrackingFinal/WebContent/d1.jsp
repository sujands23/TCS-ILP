<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>lodgeComplaint</title>
</head>
<body>
<form name="myForm" action="DefectTrackingController" method="post">
<input type="hidden" name="action" value="lodgeComplaint">
Enter Order ID : <input type="text" name="order_id">
<input type="submit" value="Submit">
</form>
</body>
</html>