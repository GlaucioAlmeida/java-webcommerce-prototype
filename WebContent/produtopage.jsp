<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
   <head>
      <meta content="text/html; charset=ISO-8859-1" />
      <title>Detalhes do produto</title>
   </head>
   <body>
   <jsp:include page="header.jsp"></jsp:include>
    	<c:choose>
    	 	<c:when test="${requestScope.add==1}">
				<font color="red">
					Produto adicionado ao carrinho com sucesso!
				</font>
				<br/><br/>			
		    </c:when>
		    <c:otherwise>

		    </c:otherwise>
		</c:choose>
		<h3><i>Detalhes do Produto:</i></h3>
    	<c:choose>
			<c:when test="${requestScope.produto.produto>0}">
				${requestScope.produto.descricao} (cod=${requestScope.produto.produto})
				<br>Preço: R$ ${requestScope.produto.preco}
				<form method="post">
					<input type="hidden" name="codProduto" value="${requestScope.produto.produto}">
					<input type="submit" value="Adicionar ao carrinho">
    			</form>			
		    </c:when>
		    <c:otherwise>
		    	Produto não encontrado!
		    </c:otherwise>
		</c:choose>
    	
   </body>
</html>
