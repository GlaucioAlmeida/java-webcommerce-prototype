<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
   <head>
      <meta content="text/html; charset=ISO-8859-1" />
      <title>Login Administrativo</title>
   </head>
   <body>
   <jsp:include page="header2.jsp"></jsp:include>
   
    	<b>Login Administrativo</b><br/><br/>    	
		<c:choose>
		    <c:when test="${param.erro==1}">
				<font color="red">
					Login Inválido!
				</font>
				<br/><br/>			
		    </c:when>
		    <c:when test="${param.erro==2}">
				<font color="red">
					Essa conta não existe!
				</font>
				<br/><br/>			
		    </c:when>
		    <c:otherwise>

		    </c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${param.logged==1}">
				Você já está Logado!!!<br>
		   		<a href='<c:url value="/admin/logout"/>'>Logout</a><br>
			</c:when>
		   	<c:otherwise>
		   		<form method="POST" action="<c:url value='/admin'/>">
    				Login: <input type='text' placeholder="digite seu nome... " name='conta' value=""/><br/>
    				Senha: <input type='password' name='senha' value=""/><br/>
    				<input type='submit' value="Enviar" /><br/>
    			</form>
    			<a href='<c:url value="/index"/>'>Inicio</a>
    			<a href='<c:url value="/registro"/>'>Registre-se</a><br/>
		    </c:otherwise>
    	</c:choose>   	
   </body>
</html>
