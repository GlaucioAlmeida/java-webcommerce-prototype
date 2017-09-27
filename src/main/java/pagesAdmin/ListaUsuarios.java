package pagesAdmin;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import classesbd.Usuario;
import classesbd.UsuarioDAO;

@WebServlet(urlPatterns={"/admin/listausuarios"})
public class ListaUsuarios extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

	private static List<Usuario> lista;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException {
		if(!((String) req.getAttribute("adminlogged")).equals("true")){
			res.sendRedirect(res.encodeRedirectURL("/servlet-01/admin/indexAdmin.jsp"));
		}else{
			UsuarioDAO pdao=new UsuarioDAO();
			int id=0;
			if(req.getParameter("id")!=null){
				id=Integer.parseInt(req.getParameter("id"));
				Usuario p=pdao.buscaUsuarioPorId(id);
				if(p!=null){
					pdao.excluir(p);
				}
			}
			lista=pdao.listar();
			req.setAttribute("usuarios", lista);
			
			RequestDispatcher rd = req.getRequestDispatcher("/admin/listausuarios.jsp");
			rd.forward(req, res);
		}
	}
	public void doGet(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{
		if(!((String) req.getAttribute("adminlogged")).equals("true")){
			res.sendRedirect(res.encodeRedirectURL("/servlet-01/admin/indexAdmin.jsp"));
		}else{
			UsuarioDAO pdao=new UsuarioDAO();
			lista=pdao.listar();
			req.setAttribute("usuarios", lista);
		
			RequestDispatcher rd = req.getRequestDispatcher("/admin/listausuarios.jsp");
			rd.forward(req, res);
		}
	}
}
