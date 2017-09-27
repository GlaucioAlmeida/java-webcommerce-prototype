package pagesAdmin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import classesbd.Admin;
import classesbd.AdminDAO;

@WebServlet(urlPatterns={"/admin/novoadmin"})
public class AdicionarAdmin extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException{
		if(!((String) req.getAttribute("adminlogged")).equals("true")){
			res.sendRedirect(res.encodeRedirectURL("/servlet-01/admin/indexAdmin.jsp"));
		}else{
			String nome=req.getParameter("nome");
			String conta=req.getParameter("login");
			String endereco=req.getParameter("endereco");
			String senha=req.getParameter("senha");
			AdminDAO bd=new AdminDAO();
			Admin a=bd.buscaAdmin(conta);
			if(a==null){
				a=new Admin();
				a.setNome(nome);
				a.setEndereco(endereco);
				a.setConta(conta);
				a.setSenha(senha);
				bd.salvar(a);
				res.sendRedirect("/servlet-01/admin/novoadmin.jsp?add=1");
			}else{
				res.sendRedirect("/servlet-01/admin/novoadmin.jsp?add=0");
			}
		}
	}
	public void doGet(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{	
		if(!((String) req.getAttribute("adminlogged")).equals("true")){
			res.sendRedirect(res.encodeRedirectURL("/servlet-01/admin/indexAdmin.jsp"));
		}else{
			RequestDispatcher rd = req.getRequestDispatcher("/admin/novoadmin.jsp");
			rd.forward(req, res);
		}
	}
}


