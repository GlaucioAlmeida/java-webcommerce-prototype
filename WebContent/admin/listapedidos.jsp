<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
   <head>
      <meta content="text/html; charset=ISO-8859-1" />
      <title>Listando Pedidos dos Usuários!</title>
      <script type="text/javascript">
      	function load(xxx){
      		window.location="/servlet-01/admin/listapedidos?op="+xxx;
      	}
      </script>
      <style>
      	table{border-collapse: collapse;width: 100%;}
      	th, td {padding: 8px;text-align: left;border-bottom: 1px solid #ddd;}
      	tr:hover{background-color:#f5f5f5}
      </style>
   </head>
	<body>
		<jsp:include page="header2.jsp"></jsp:include><br>
		Selecione o Status para listar:
		<input type="button" value="Aguardando Pagamento" onclick="javascript:load(0)"/>
		<input type="button" value="Pago" onclick="javascript:load(1)"/>
		<input type="button" value="Enviado" onclick="javascript:load(2)"/>
		<input type="button" value="Finalizado" onclick="javascript:load(3)"/>
		<h3><i>Pedidos</i></h3>
		<table style="width:80%">
  		<tr>
    		<th>Id</th>
    		<th>Cod. Pedido</th>
    		<th>Cod. Usuario</th> 
    		<th>Cod. Produto</th>
    		<th>Produto</th>
    		<th>Status</th>
    		<th>Quantidade</th>
    		<th>Preço</th>
    		<th>Subtotal</th>
    		<th> </th>
  		</tr>
  		<c:forEach items="${requestScope.pedidos}" var="pedido" varStatus="loop">
  			<tr><form action="/servlet-01/admin/listapedidos" method="post">
  				<input type="hidden" name="pedidoId" value="${pedido.id}" />
    			<td>${pedido.id}</td>
    			<td>${pedido.codPedido}</td>
    			<td>${pedido.codUsuario}</td> 
    			<td>${pedido.codProduto}</td>
    			<td>${requestScope.nomeProd[loop.index]}</td>
    			<td>${pedido.status}<br>Alterar: 
    				<select name="statusupdate">
  						<option value="0">Aguardando Pagamento</option>
  						<option value="1">Pago</option>
  						<option value="2">Enviado</option>
  						<option value="3">Finalizado</option>
  						<option value="4" selected="selected"></option>
					</select>
    			</td>
    			<td>${pedido.quantidade}</td>
    			<td>${pedido.preco}</td>
    			<td>${pedido.subtotal}</td>
    			<td><input type="submit" name="btnAtualiza" value="Atualizar">
					<input type="submit" name="btn" value="Excluir pedido"></td>
    			</form>
  			</tr>
		</c:forEach>
		</table>
   </body>
</html>
