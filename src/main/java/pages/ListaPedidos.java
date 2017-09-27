package pages;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import classesbd.Pedido;
import classesbd.PedidoDAO;
import classesbd.Produto;
import classesbd.ProdutoDAO;
import classesbd.Usuario;

@WebServlet(urlPatterns={"/listapedidos"})
public class ListaPedidos extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

	private static List<Pedido> lista;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException {
		if(!((String) req.getAttribute("logged")).equals("true")){
			res.sendRedirect(res.encodeRedirectURL("/servlet-01/login"));
		}else{
			HttpSession sessao = req.getSession();
			Usuario a=(Usuario)sessao.getAttribute("user");
			PedidoDAO pdao=new PedidoDAO();
			String status="";
			int id=0,op=4;
			if(req.getParameter("pedidoId")!=null)
				id=Integer.parseInt(req.getParameter("pedidoId"));
			if(req.getParameter("statusupdate")!=null)
				op=Integer.parseInt(req.getParameter("statusupdate"));
			switch(op){
				case 3:status="Finalizado";break;
				default:
			}
			if(req.getParameter("btnAtualiza")!=null && !status.isEmpty()){//atualizar status do pedido
				Pedido p=pdao.buscaPedido(id);
				if(p!=null){
					p.setStatus(status);
				}
				pdao.atualizar(p);
			}else if(req.getParameter("btn")!=null && status.equals("Finalizado")){//excluir pedido
				Pedido p=pdao.buscaPedido(id);
				if(p!=null){
					pdao.excluir(p);
				}
			}
			
			lista=pdao.listarPorStatusId(status,a.getUnicId());
			String[] nomeProd=new String[lista.size()];
			ProdutoDAO produtodao=new ProdutoDAO();
			for(int i=0;i<lista.size();i++){
				Pedido ped=lista.get(i);
				Produto p=produtodao.buscaProduto(ped.getCodProduto());
				nomeProd[i]=p.getDescricao();
			}
			req.setAttribute("pedidos", lista);
			req.setAttribute("nomeProd", nomeProd);
			RequestDispatcher rd = req.getRequestDispatcher("/listapedidos.jsp");
			rd.forward(req, res);
		}
	}
	public void doGet(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{
		if(!((String) req.getAttribute("logged")).equals("true")){
			res.sendRedirect(res.encodeRedirectURL("/servlet-01/login"));
		}else{
			HttpSession sessao = req.getSession();
			Usuario a=(Usuario)sessao.getAttribute("user");
			PedidoDAO pdao=new PedidoDAO();
			String[] nomeProd = null;
			String status="";
			int op=4;
			if(req.getParameter("op")!=null)
				op=Integer.parseInt(req.getParameter("op"));
			switch(op){
			case 0:status="Aguardando pagamento";break;
			case 1:status="Pago";break;
			case 2:status="Enviado";break;
			case 3:status="Finalizado";break;
				default:
			}
			lista=pdao.listarPorStatusId(status,a.getUnicId());
			nomeProd=new String[lista.size()];
			ProdutoDAO produtodao=new ProdutoDAO();
			for(int i=0;i<lista.size();i++){
				Pedido ped=lista.get(i);
				Produto p=produtodao.buscaProduto(ped.getCodProduto());
				nomeProd[i]=p.getDescricao();
			}
			req.setAttribute("pedidos", lista);
			req.setAttribute("nomeProd", nomeProd);
			RequestDispatcher rd = req.getRequestDispatcher("/listapedidos.jsp");
			rd.forward(req, res);
		}
	}
}
