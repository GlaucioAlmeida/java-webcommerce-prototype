package pages;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import classesbd.Cadastro;
import classesbd.Categoria;
import classesbd.Produto;
import classesbd.Usuario;
import classesbd.UsuarioDAO;

@WebServlet(urlPatterns={"/Install","/install"})
public class Install extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{	
		UsuarioDAO usuarioDAO=new UsuarioDAO();
		Usuario u=usuarioDAO.buscaUsuario("conta");
		if(u==null){
			u=new Usuario();
			Cadastro cad=new Cadastro();
			Produto p=new Produto();
			Categoria c=new Categoria();
			u.setConta("user1");
			u.setSenha("pass");
			p.setDescricao("Celular");
			p.setPreco(500.0);
			c.setDescricao("Geral");
			Set<Produto> produtos = new HashSet<Produto>();
			produtos.add(p);
			c.setProdutos(produtos);
			cad.cadastraUsuario(u);
			cad.cadastraCategorias(c);
			cad.cadastraProdutos(p);
			res.sendRedirect(res.encodeRedirectURL("/servlet-01/installed.jsp?i=1"));
		}else{
			res.sendRedirect(res.encodeRedirectURL("/servlet-01/installed.jsp?i=0"));
		}
		
		
		
	}
}

