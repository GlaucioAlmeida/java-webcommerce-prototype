package pages;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import classesbd.Usuario;
import classesbd.UsuarioDAO;


@WebServlet(urlPatterns={"/registro","/Registro"})
public class Registro extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException{
		if(((String) req.getAttribute("logged")).equals("true")){
			res.sendRedirect(res.encodeRedirectURL("/servlet-01/Login.jsp?logged=1"));
		}else{
			String nome=req.getParameter("nome");
			String conta=req.getParameter("login");
			String endereco=req.getParameter("endereco");
			String senha=req.getParameter("senha");
			UsuarioDAO bd=new UsuarioDAO();
			Usuario a=bd.buscaUsuario(conta);
			if(a==null){
				a=new Usuario();
				a.setNome(nome);
				a.setEndereco(endereco);
				a.setConta(conta);
				a.setSenha(senha);
				bd.salvar(a);
				res.sendRedirect("/servlet-01/registro.jsp?add=1");
			}else{
				res.sendRedirect("/servlet-01/registro.jsp?add=0");
			}
		}
	}
	public void doGet(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{	
		if(((String) req.getAttribute("logged")).equals("true")){
			res.sendRedirect(res.encodeRedirectURL("/servlet-01/Login.jsp?logged=1"));
		}else{
			RequestDispatcher rd = req.getRequestDispatcher("/registro.jsp");
			rd.forward(req, res);
		}
	}
}


