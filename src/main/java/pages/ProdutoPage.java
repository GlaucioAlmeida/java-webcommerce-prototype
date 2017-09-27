package pages;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import classesbd.CarrinhoUtils;
import classesbd.Cookies;
import classesbd.Produto;
import classesbd.ProdutoDAO;

@WebServlet(urlPatterns={"/ProdutoPage","/produtopage"})
public class ProdutoPage extends HttpServlet{	
	private static final long serialVersionUID = 1L;
	public void doPost(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{		
		if(!((String) req.getAttribute("logged")).equals("true")){
			res.sendRedirect(res.encodeRedirectURL("/servlet-01/Login"));
		}else{
			Cookie umCookie = Cookies.getCookieByName("carrinho", req);
			String codProduto=req.getParameter("codProduto");
			HttpSession sessao = req.getSession();
			if(sessao.getAttribute("aceitaCookie")=="false"){
				String aux=CarrinhoUtils.add((String)sessao.getAttribute("carrinho"),codProduto);
				sessao.setAttribute("carrinho", aux);
			}else{
				if(umCookie == null){
					sessao.setAttribute("aceitaCookie", "false");
					sessao.setAttribute("carrinho", codProduto+",");
				}else{
					sessao.setAttribute("aceitaCookie", "true");
					String aux=CarrinhoUtils.add(umCookie.getValue(),codProduto);
					umCookie.setValue(aux);
					res.addCookie(umCookie);
				}
			}
			
			ProdutoDAO prod=new ProdutoDAO();
			Produto p=new Produto();
			try{
				p=prod.buscaProduto(Integer.parseInt(codProduto));
				req.setAttribute("produto", p);//retorna os detalhes do produto
				req.setAttribute("add", 1);
			}catch(Exception e){}
			
			RequestDispatcher rd = req.getRequestDispatcher("/produtopage.jsp");//envia para o jsp
			rd.forward(req, res);
		}
	}
	public void doGet(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{	
		int codProduto=0;
		ProdutoDAO prod=new ProdutoDAO();
		Produto p=new Produto();
		try{
			codProduto=Integer.parseInt(req.getParameter("cod"));
			p=prod.buscaProduto(codProduto);
			req.setAttribute("produto", p);//retorna os detalhes do produto
		}catch(Exception e){}
		
		RequestDispatcher rd = req.getRequestDispatcher("/produtopage.jsp");//envia para o jsp
		rd.forward(req, res);
	}
}
