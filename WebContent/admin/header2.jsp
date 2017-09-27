<style type="text/css">
#menu ul {
	padding:0px;
	margin:0px;
	background-color:#EDEDED;
	list-style:none;
}
#menu ul li { display: inline; }
#menu ul li a {
	padding: 2px 10px;
	display: inline-block;
 
	/* visual do link */
	background-color:#EDEDED;
	color: #333;
	text-decoration: none;
	border-bottom:3px solid #EDEDED;
}
#menu ul li a:hover {
	background-color:#D6D6D6;
	color: #6D6D6D;
	border-bottom:3px solid #EA0000;
}
</style>
<nav id="menu">
	<ul>
		<li><a href="<%=request.getContextPath()%>/admin/login">Login</a></li>
		<li><a href="<%=request.getContextPath()%>/admin/logout">Logout</a></li>
		<li><a href="<%=request.getContextPath()%>/admin/listapedidos">Listar Pedidos</a></li>
		<li><a href="<%=request.getContextPath()%>/admin/listaproduto">Listar Produtos</a></li>
		<li><a href="<%=request.getContextPath()%>/admin/listausuarios">Listar Usuários</a></li>
		<li><a href="<%=request.getContextPath()%>/admin/listaadmin">Listar Admins</a></li>
		<li><a href="<%=request.getContextPath()%>/admin/novoproduto">Adicionar produto</a></li>
		<li><a href="#">Adicionar categoria</a></li>
		<li><a href="<%=request.getContextPath()%>/admin/novoadmin">Registrar novo admin</a></li>
	</ul>
</nav>