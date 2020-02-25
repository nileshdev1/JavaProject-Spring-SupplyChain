<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false" %>
     <%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
      
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h3>Welcome To UOM Edit Page</h3>
<form:form action="update" method="post" modelAttribute="uom">
<pre>
UOM ID:  <form:input path="uomId" readonly="true"/>

UOM Type: 
		<form:select path="uomType">
		<form:option value=" ">-SELECT-</form:option>
		<form:option value="Packing">PACKING</form:option>
		<form:option value="NoPacking">NO-PACKING</form:option>
		<form:option value="">-NA-</form:option>
</form:select>

UOM MODEL: <form:input path="uomModel" />

Description:  <form:textarea path="uomDesc" />

<input type="submit" value="update" />

</pre>

</form:form>
</body>
</html>