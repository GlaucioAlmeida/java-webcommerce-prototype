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
		<li><a href="<%=request.getContextPath()%>/index">Home</a></li>
		<li><a href="<%=request.getContextPath()%>/login">Login</a></li>
		<li><a href="<%=request.getContextPath()%>/logout">Logout</a></li>
		<li><a href="<%=request.getContextPath()%>/registro">Registre-se</a></li>
		<li><a href="<%=request.getContextPath()%>/listapedidos">Meus Pedidos</a></li>
		<li><a href="<%=request.getContextPath()%>/carrinho">Carrinho</a></li>
	</ul>
</nav>