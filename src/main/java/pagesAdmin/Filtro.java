package pagesAdmin;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@WebFilter(urlPatterns = { "/admin/*" })
public class Filtro implements Filter{
	@SuppressWarnings("unused")
	private FilterConfig filterConfig = null;
	
	@Override
	public void init(FilterConfig filterConfig){	
		this.filterConfig = filterConfig;		
	}
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)throws IOException, ServletException{	
		HttpServletRequest request = (HttpServletRequest) req;
		//HttpServletResponse response = (HttpServletResponse) res;
		
		HttpSession sessao = request.getSession();
		
		String logged = (String) sessao.getAttribute("adminlogged");
		if (logged == "true"){
			request.setAttribute("adminlogged", "true");
			chain.doFilter(req, res);			
		}else{
			request.setAttribute("adminlogged", "false");
			chain.doFilter(req, res);
		}
	}
	@Override
	public void destroy(){
		
	}
}
