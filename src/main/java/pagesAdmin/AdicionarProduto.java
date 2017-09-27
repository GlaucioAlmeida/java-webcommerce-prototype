package pagesAdmin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import classesbd.Produto;
import classesbd.ProdutoDAO;

@WebServlet(urlPatterns={"/admin/novoproduto"})
public class AdicionarProduto extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException {
		if(!((String) req.getAttribute("adminlogged")).equals("true")){
			res.sendRedirect(res.encodeRedirectURL("/servlet-01/admin/indexAdmin.jsp"));
		}else{			
			if(!req.getParameter("descricao").isEmpty() && !req.getParameter("preco").isEmpty()){
				String descricao=req.getParameter("descricao");
				Double preco=Double.parseDouble(req.getParameter("preco"));
				ProdutoDAO bd=new ProdutoDAO();
				Produto a=bd.buscaProdutoPorDescricao(descricao);
				if(a==null){
					a=new Produto();
					a.setDescricao(descricao);
					a.setPreco(preco);
					bd.salvar(a);
					res.sendRedirect("/servlet-01/admin/novoproduto.jsp?add=1");//adicionou
				}else{
					res.sendRedirect("/servlet-01/admin/novoproduto.jsp?add=2");//ja existe
				}
			}else{
				res.sendRedirect("/servlet-01/admin/novoproduto.jsp?add=0");//dados nao recebidos
			}
		}
	}
	public void doGet(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{
		if(!((String) req.getAttribute("adminlogged")).equals("true")){
			res.sendRedirect(res.encodeRedirectURL("/servlet-01/admin/indexAdmin.jsp"));
		}else{
			RequestDispatcher rd = req.getRequestDispatcher("/admin/novoproduto.jsp");
			rd.forward(req, res);
		}
	}
}
