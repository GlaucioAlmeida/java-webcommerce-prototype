<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
   <head>
      <meta content="text/html; charset=ISO-8859-1" />
       <meta http-equiv="refresh" content="10;url=/servlet-01/admin/install">
      <title>Instalação</title>
   </head>
   <body>
    	<c:choose>
    	 	<c:when test="${param.i==1}">
				<font color="red">
					Banco de dados instalado com sucesso!<br>
					Usuario padrão: conta<br>
					Senha: senha<br>
					OBS: Altere sua senha!
				</font>
				<br/><br/>			
		    </c:when>
		    <c:otherwise>
				Banco de dados não foi instalado ou ja existe!<br>
		    </c:otherwise>
		</c:choose>
		Aguarde...    	
   </body>
</html>
