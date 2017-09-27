<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
   <head>
      <meta content="text/html; charset=ISO-8859-1" />
      <title>Bem vindo e boas compras!</title>
   </head>
	<body>
		<jsp:include page="header.jsp"></jsp:include>
		<c:if test="${param.logged==1}">Login efetuado com sucesso!!!</c:if>
		<h3><i>Produtos</i></h3>
		<c:forEach items="${requestScope.produtos}" var="produto">
			<a href="<c:url value='/ProdutoPage?cod=${produto.produto}'/>">${produto.descricao}  >>>>  R$ ${produto.preco}</a>
			<form method="post" action="/servlet-01/ProdutoPage">
				<input name="codProduto" type="hidden" value="${produto.produto}">
				<input type="submit" value="Adicionar ao carrinho">
			</form>
			<br/><br/>
		</c:forEach>
   </body>
</html>
