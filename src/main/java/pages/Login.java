package pages;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import classesbd.Cookies;
import classesbd.Usuario;
import classesbd.UsuarioDAO;

@WebServlet(urlPatterns={"/Login","/login"})
public class Login extends HttpServlet 
{	
	private static final long serialVersionUID = 1L;
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		if(((String) req.getAttribute("logged")).equals("true")){
			res.sendRedirect(res.encodeRedirectURL("/servlet-01/Login.jsp?logged=1"));
		}else{
			HttpSession sessao = req.getSession();
			sessao.setMaxInactiveInterval(12*60*60);//12 horas
			String conta=req.getParameter("conta");
			String senha=req.getParameter("senha");
		
			UsuarioDAO userDao=new UsuarioDAO();
			Usuario user = null;
			user=userDao.buscaUsuario(conta);
		
			if(user==null){
				sessao.setAttribute("userlogged", "false");
				res.sendRedirect(res.encodeRedirectURL("/servlet-01/Login.jsp?erro=2"));//usuario nao existe
			}else{
				if (conta.equals(user.getConta()) && senha.equals(user.getSenha())){
					String destino = (String) sessao.getAttribute("destino");
					sessao.setAttribute("userlogged", "true");
					sessao.setAttribute("user", user);
			
					if(!Cookies.setCookie("carrinho", "", res)){
						sessao.setAttribute("aceitaCookie", "false");
						sessao.setAttribute("carrinho", "");
					}
			
					if(destino==null){
						res.sendRedirect(res.encodeRedirectURL("/servlet-01/index?logged=1"));//confirma login
					}else{
						res.sendRedirect(res.encodeRedirectURL(destino));
					}
				}else{
					res.sendRedirect(res.encodeRedirectURL("/servlet-01/Login.jsp?erro=1"));//senha errada
				}
			}
		}
	}
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {		
		if(((String) req.getAttribute("logged")).equals("true")){
			res.sendRedirect(res.encodeRedirectURL("/servlet-01/Login.jsp?logged=1"));
		}else{
			res.sendRedirect(res.encodeRedirectURL("/servlet-01/Login.jsp"));
		}
	}
}
