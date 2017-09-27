<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
   <head>
      <meta content="text/html; charset=ISO-8859-1" />
      <title>Listando Todos os Usuarios!</title>
      <style>
      	table{border-collapse: collapse;width: 100%;}
      	th, td {padding: 8px;text-align: left;border-bottom: 1px solid #ddd;}
      	tr:hover{background-color:#f5f5f5}
      </style>
   </head>
	<body>
		<jsp:include page="header2.jsp"></jsp:include><br>
		<h3><i>Listando Usuarios</i></h3>
		<table style="width:80%">
  		<tr>
    		<th>Id</th>
    		<th>Nome</th> 
    		<th>Endereço</th>
    		<th>Conta</th>
    		<th> </th>
  		</tr>
  		<c:forEach items="${requestScope.usuarios}" var="usuario">
  			<tr><form action="/servlet-01/admin/listausuarios" method="post">
  				<input type="hidden" name="id" value="${usuario.unicId}" />
    			<td>${usuario.unicId}</td>
    			<td>${usuario.nome}</td> 
    			<td>${usuario.endereco}</td>
    			<td>${usuario.conta}</td>
    			<td><input type="submit" name="btn" value="Excluir"></td>
    			</form>
  			</tr>
		</c:forEach>
		</table>
   </body>
</html>
