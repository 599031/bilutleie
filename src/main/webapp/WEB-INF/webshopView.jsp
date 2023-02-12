
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <%-- JSP Standard Tag Library --%>

<!DOCTYPE html>
<html>
<head>
	<title>Webshop</title>
	<link rel="stylesheet" href="css/simple.css">
</head>

<body>
	<p>Du er innlogget som <c:out value="${username}"/><br>		<%-- "username" hentes ut fra session-objektet --%>
	Totalt i handlekurv: kr ${cart.total}</p>					<%-- "cart" hentes også ut fra session-objektet --%>
	
	<table><tr>													<%-- Setter opp tabell hvor <tr> tilsvarer en kolonne --%>
		<th>Vare</th>
		<th>Pris</th>
		<th>Antall</th>
		<th>Beløp</th></tr>
		<c:forEach var="item" items="${cart.items}"><tr>	<%-- Henter ut listen "items" fra "cart" vha. getItems()--%>
			<td>${item.name}</td>							<%-- For hvert "item", så henter vi egenskapen "name" --%>
			<td>${item.price}</td>							<%-- osv... --%>
			<td>${item.quantity}</td>
			<td>${item.price * item.quantity}</td></tr>
		</c:forEach>
	</table><br>
	
	<form action="webshop" method="post">
		<fieldset>
		<legend>Handle</legend>
		<input type="checkbox" name="vare" value="bukse" />Bukse<br/>		<%-- Merk! Det er "bukse" som evt. mottas i controller --%>
		<input type="checkbox" name="vare" value="genser" />Genser<br/>		<%-- Merk! Det er "genser" som evt. mottas i controller --%>
		<p><input type="submit" value="Legg i handlekurv" /></p>
		</fieldset>
	</form>
	
	<form action="logout" method="post">
		<fieldset>
		<p><input type="submit" value="Logg ut"/></p>
		</fieldset>
	</form>
</body>
</html>
