<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
   <head>
      <meta content="text/html; charset=ISO-8859-1" />
      <title>Adiicionar Produtos</title>
   </head>
	<body>
		<jsp:include page="header2.jsp"></jsp:include>
		<h3><i>Adicionar novo produto</i></h3>
		<c:choose>
			<c:when test="${param.add==1}">
				<font color="red">
					Produto adicionado com sucesso!
				</font>
				<br/><br/>
		    </c:when>
		    <c:otherwise>
		    	<c:choose>
		    		<c:when test="${param.add==0}">
						<font color="red">
							Dados não enviados!
						</font>
						<br/><br/>
		    		</c:when>
		    		<c:otherwise>
		    			<c:choose>
		    				<c:when test="${param.add==2}">
								<font color="red">
									O produto ja existe!
								</font>
								<br/><br/>
		    				</c:when>
		    			</c:choose>
		    		</c:otherwise>
		    	</c:choose>
				<form method="post" action="/servlet-01/admin/novoproduto">
					Nome:<input name="descricao" type="text" value="" required/><br/><br/>
					Preço:<input name="preco" type="text" value="" required/><br/><br/>
					<input type="submit" name="btn" value="Salvar"/><br/><br/>
				</form>
		    </c:otherwise>
		</c:choose>
   </body>
</html>
