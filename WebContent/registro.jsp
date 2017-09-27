<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
   <head>
      <meta content="text/html; charset=ISO-8859-1" />
      <title>Registre-se</title>
   </head>
	<body>
		<jsp:include page="header.jsp"></jsp:include>
		<h3><i>Criar novo usuário</i></h3>
		<c:choose>
			<c:when test="${param.add==1}">
				<font color="red">
					Usuário adicionado com sucesso!
				</font>
				<br/><br/>
		    </c:when>
		    <c:otherwise>
		    	<c:choose>
		    		<c:when test="${param.add==0}">
						<font color="red">
							Esse login já existe!
						</font>
						<br/><br/>
		    		</c:when>
		    	</c:choose>
				<form method="post" action="/servlet-01/registro">
					Nome:<input name="nome" type="text" value="" required/><br/><br/>
					Endereço:<input name="endereco" type="text" value="" required/><br/><br/>
					Login:<input name="login" type="text" value="" required/><br/><br/>
					Senha:<input name="senha" type="password" value="" required/><br/><br/>
					<input type="submit" name="btn" value="Registrar"/><br/><br/>
				</form>
		    </c:otherwise>
		</c:choose>
   </body>
</html>
