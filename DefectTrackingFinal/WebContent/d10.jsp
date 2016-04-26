<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>defectTrackingRetailer</title>
</head>
<body>
<form name="defectTrackingForm" action="DefectTrackingController" method="post">
<input type="hidden" name="action" value="defectTracking">
</input>
Enter Defect ID :<input type="text" name="defectId">
<input type="submit" value="Submit">
</form>
</body>
</html>