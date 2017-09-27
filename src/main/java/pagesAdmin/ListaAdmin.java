package pagesAdmin;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import classesbd.Admin;
import classesbd.AdminDAO;

@WebServlet(urlPatterns={"/admin/listaadmin"})
public class ListaAdmin extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

	private static List<Admin> lista;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException {
		if(!((String) req.getAttribute("adminlogged")).equals("true")){
			res.sendRedirect(res.encodeRedirectURL("/servlet-01/admin/indexAdmin.jsp"));
		}else{
			HttpSession s=req.getSession();
			Admin a=(Admin)s.getAttribute("userAdm");
			AdminDAO pdao=new AdminDAO();
			int id=0;
			if(a==null)return;
			if(req.getParameter("id")!=null && Integer.parseInt(req.getParameter("id"))!=a.getUnicId()){
				id=Integer.parseInt(req.getParameter("id"));
				Admin p=pdao.buscaAdminPorId(id);
				if(p!=null){
					pdao.excluir(p);
				}
			}
			lista=pdao.listar();
			req.setAttribute("usuarios", lista);
			
			RequestDispatcher rd = req.getRequestDispatcher("/admin/listaadmin.jsp");
			rd.forward(req, res);
		}
	}
	public void doGet(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{
		if(!((String) req.getAttribute("adminlogged")).equals("true")){
			res.sendRedirect(res.encodeRedirectURL("/servlet-01/admin/indexAdmin.jsp"));
		}else{
			AdminDAO pdao=new AdminDAO();
			lista=pdao.listar();
			req.setAttribute("usuarios", lista);
		
			RequestDispatcher rd = req.getRequestDispatcher("/admin/listaadmin.jsp");
			rd.forward(req, res);
		}
	}
}
