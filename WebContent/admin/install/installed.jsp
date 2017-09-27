<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
   <head>
      <meta content="text/html; charset=ISO-8859-1" />
      <title>Admin - Instalação</title>
   </head>
   <body>
    	<c:choose>
    	 	<c:when test="${param.i==1}">
				<font color="red">
					Administrador padrão instalado com sucesso!<br>
					Login: admin<br>
					Senha: admin<br>
					OBS: Altere sua senha!
				</font>
				<br/><br/>			
		    </c:when>
		    <c:otherwise>
				Administrador padrão não foi instalado ou ja existe!<br>
		    </c:otherwise>
		</c:choose>    	
   </body>
</html>
