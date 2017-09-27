<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
   <head>
      <meta content="text/html; charset=ISO-8859-1" />
      <title>Criar novo Administrador</title>
   </head>
	<body>
		<jsp:include page="header2.jsp"></jsp:include>
		<h3><i>Criar novo Administrador</i></h3>
		<c:choose>
			<c:when test="${param.add==1}">
				<font color="red">
					Administrador adicionado com sucesso!
				</font>
				<br/><br/>
		    </c:when>
		    <c:otherwise>
		    	<c:choose>
		    		<c:when test="${param.add==0}">
						<font color="red">
							Esse Administrador já existe!
						</font>
						<br/><br/>
		    		</c:when>
		    	</c:choose>
				<form method="post" action="/servlet-01/admin/novoadmin">
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
