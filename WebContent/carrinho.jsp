<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
   <head>
      <meta content="text/html; charset=ISO-8859-1" />
      <title>Produtos no Carrinho</title>
   </head>
	<body>
		<jsp:include page="header.jsp"></jsp:include>
		<h3><i>Produtos no Carrinho</i></h3>
		<c:choose>
    	 	<c:when test="${requestScope.total==0.0}">
				<font color="red">
					O carrinho encontra-se vazio!
				</font>
				<br/><br/>			
		    </c:when>
		    <c:otherwise>
				<form method="post" action="/servlet-01/Carrinho">
					<c:forEach items="${requestScope.produtos}" var="produto" varStatus="loop">
						<a href="<c:url value='/ProdutoPage?cod=${produto.produto}'/>">${produto.descricao}</a>
						<fmt:formatNumber value="${produto.preco}" type="currency" currencySymbol="R$"/> x 1 = 
						<fmt:formatNumber value="${produto.preco}" type="currency" currencySymbol="R$"/>
						Quantidade:<input name="qtd${produto.produto}" type="number" value="1" />
						<br/><br/>
					</c:forEach>
					Total: <fmt:formatNumber value="${requestScope.total}" type="currency" currencySymbol="R$"/>
					<input type="submit" name="btnAtualiza" value="Atualizar">
					<input type="submit" name="btn" value="Finalizar Compra">
				</form>
		    </c:otherwise>
		</c:choose>
   </body>
</html>
