
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- JSP Standard Tag Library --%>
<!DOCTYPE html>
<html>

<head>
<link rel="stylesheet" href="css/simple.css">

</head>

<body>

	<p>
		Du er innlogget som
		<c:out value="${username}" />
		<br>
	<h2>Bilutleie</h2>

	<h1>
		Bilene ved
		<c:out value="${lokasjon}" />
		er:
	</h1>


	<c:if test="${lokasjon=='Flesland'}">
		<p>Volkswagen Golf</p>
		<p>Audi A4</p>
		<p>Tesla Model S</p>
	</c:if>


	<c:if test="${lokasjon=='Bergen'}">
		<p>Toyota Prius</p>
		<p>Volvo 240</p>
		<p>Tesla Model X</p>
	</c:if>




	<c:if test="${lokasjon=='Stavanger'}">
		<p>Lada</p>
		<p>Ford 150</p>
		<p>Subaru Forester</p>
	</c:if>



</body>
</html>