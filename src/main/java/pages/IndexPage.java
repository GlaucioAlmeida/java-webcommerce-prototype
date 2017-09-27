package pages;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import classesbd.Produto;
import classesbd.ProdutoDAO;

@WebServlet(urlPatterns={"/","/index","/Index"})
public class IndexPage extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

	private static List<Produto> lista;

	public void doGet(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{	
		ProdutoDAO pdao=new ProdutoDAO();
		lista=pdao.listar();
		req.setAttribute("produtos", lista);
		
		RequestDispatcher rd = req.getRequestDispatcher("/index.jsp");
		rd.forward(req, res);
	}
}

