
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <%-- JSP Standard Tag Library --%>
<!DOCTYPE html>
<html>
  
  <head>
  <link rel="stylesheet" href="css/simple.css">

  </head>
  
  <body>
    
    
    
      <h2>Bilutleie</h2>
      
      <fieldset>
      <form action="lokasjon" method="get">
    	<select id="lokasjon" name="lokasjon">
      <option value="">Velg lokasjon</option>
      <option value="Flesland">Flesland</option>
      <option value="Bergen">Bergen</option>
      <option value="Stavanger">Stavanger</option>
    </select>
    <p><input type="submit" value="Søk"/></p>
    </form>
    <br><br>
		
		
    
    <form action="login" method="get">
	
		<p><input type="submit" value="Logg inn"/></p>
		
	
	</form>
    </fieldset>
  </body>
</html>







