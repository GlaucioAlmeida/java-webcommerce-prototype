package pagesAdmin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import classesbd.Admin;
import classesbd.AdminDAO;

@WebServlet(urlPatterns={"/admin/install"})
public class InstallDefaultAdmin extends HttpServlet{
	private static final long serialVersionUID = 7578450101152332331L;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException {
		AdminDAO admDAO=new AdminDAO();
		Admin a;
		a=admDAO.buscaAdmin("admin");
		if(a==null){
			a=new Admin();
			a.setConta("admin");//login
			a.setSenha("admin");//senha
			a.setNome("Administrador");
			a.setEndereco("nenhum");
			admDAO.salvar(a);
			res.sendRedirect(res.encodeRedirectURL("/servlet-01/admin/install/installed.jsp?i=1"));
		}else{
			res.sendRedirect(res.encodeRedirectURL("/servlet-01/admin/install/installed.jsp?i=0"));
		}
		
	}
}
