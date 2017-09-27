<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
   <head>
      <meta content="text/html; charset=ISO-8859-1" />
      <title>Listando Todos os Produtos</title>
      <style>
      	table{border-collapse: collapse;width: 100%;}
      	th, td {padding: 8px;text-align: left;border-bottom: 1px solid #ddd;}
      	tr:hover{background-color:#f5f5f5}
      </style>
   </head>
	<body>
		<jsp:include page="header2.jsp"></jsp:include><br>
		<h3><i>Listando Produtos</i></h3>
		<table style="width:80%">
  		<tr>
    		<th>Id</th>
    		<th>Nome</th> 
    		<th>Preço</th>
    		<th> </th>
  		</tr>
  		<c:forEach items="${requestScope.produtos}" var="produto">
  			<tr><form action="/servlet-01/admin/listaproduto" method="post">
  				<input type="hidden" name="id" value="${produto.produto}" />
    			<td>${produto.produto}</td>
    			<td>${produto.descricao}</td> 
    			<td>${produto.preco}</td>
    			<td><input type="submit" name="btn" value="Excluir"></td>
    			</form>
  			</tr>
		</c:forEach>
		</table>
   </body>
</html>
