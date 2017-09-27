package pagesAdmin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import classesbd.Admin;
import classesbd.AdminDAO;

@WebServlet(urlPatterns={"/admin","/admin/","/admin/login","/admin/Login","/admin/index","/admin/indexAdmin"})
public class IndexAdmin extends HttpServlet {	
	private static final long serialVersionUID = 1L;
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		if(((String) req.getAttribute("adminlogged")).equals("true")){
			res.sendRedirect(res.encodeRedirectURL("/servlet-01/admin/indexAdmin.jsp?logged=1"));
		}else{
			HttpSession sessao = req.getSession();
			sessao.setMaxInactiveInterval(12*60*60);//12 horas
			String conta=req.getParameter("conta");
			String senha=req.getParameter("senha");
		
			AdminDAO userDao=new AdminDAO();
			Admin user = null;
			user=userDao.buscaAdmin(conta);
		
			if(user==null){
				sessao.setAttribute("adminlogged", "false");
				res.sendRedirect(res.encodeRedirectURL("/servlet-01/admin/indexAdmin.jsp?erro=2"));//usuario nao existe
			}else{
				if (conta.equals(user.getConta()) && senha.equals(user.getSenha())){
					String destino = (String) sessao.getAttribute("destino");
					sessao.setAttribute("adminlogged", "true");
					sessao.setAttribute("userAdm", user);
			
					if(destino==null){
						res.sendRedirect(res.encodeRedirectURL("/servlet-01/admin/indexAdmin"));//confirma login
					}else{
						res.sendRedirect(res.encodeRedirectURL(destino));
					}
				}else{
					res.sendRedirect(res.encodeRedirectURL("/servlet-01/admin/indexAdmin.jsp?erro=1"));//senha errada
				}
			}
		}
	}
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		if(((String) req.getAttribute("adminlogged")).equals("true")){
			res.sendRedirect(res.encodeRedirectURL("/servlet-01/admin/indexAdmin.jsp?logged=1"));
		}else{
			res.sendRedirect(res.encodeRedirectURL("/servlet-01/admin/indexAdmin.jsp"));
		}
	}
}
