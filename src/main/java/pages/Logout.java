package pages;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import classesbd.Cookies;

@WebServlet(urlPatterns={"/Logout","/logout"})
public class Logout extends HttpServlet{	
	private static final long serialVersionUID = 1L;
		public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {		
		if(((String) req.getAttribute("logged")).equals("true")){
			HttpSession sessao = req.getSession();
			sessao.invalidate();			
			Cookies.removeCookie("carrinho", res);
		}
		res.sendRedirect(res.encodeRedirectURL("/servlet-01/Login.jsp"));
	}
}
