package pages;

import java.io.IOException;
import java.util.ArrayList;

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
import classesbd.Pedido;
import classesbd.PedidoDAO;
import classesbd.Produto;
import classesbd.ProdutoDAO;
import classesbd.Usuario;

@WebServlet(urlPatterns={"/carrinho","/Carrinho"})
public class CarrinhoPage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static ArrayList<Produto> lista;
	public void doPost(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{
		if(!((String) req.getAttribute("logged")).equals("true")){
			res.sendRedirect(res.encodeRedirectURL("/servlet-01/Login"));
		}else{
			lista=new ArrayList<Produto>();
			ProdutoDAO pdao=new ProdutoDAO();
			Cookie umCookie = Cookies.getCookieByName("carrinho", req);
			HttpSession sessao = req.getSession();
			String aux="";
			if(req.getParameter("btnAtualiza") != null){
				//System.out.print("atualiza");
				if(sessao.getAttribute("aceitaCookie")=="false" || umCookie == null){
					aux=(String)sessao.getAttribute("carrinho");
				}else{
					aux=umCookie.getValue();
				}
			
				int[] carrinho=CarrinhoUtils.getFromCookieValue(aux);
				int[] qtd=new int[carrinho.length];
				Double total=0.0;
				for(int i=0;i<carrinho.length;i++){
					Produto p=pdao.buscaProduto(carrinho[i]);
					qtd[i]=Integer.parseInt(req.getParameter("qtd"+carrinho[i]));
					total+=(p.getPreco()*qtd[i]);
					lista.add(p);
				}
				req.setAttribute("produtos", lista.toArray());
				req.setAttribute("qtd", qtd);
				req.setAttribute("total", total);
				RequestDispatcher rd = req.getRequestDispatcher("/carrinho2.jsp");
				rd.forward(req, res);
				
			}else{
				//System.out.print("finaliza");
				Usuario u=(Usuario)sessao.getAttribute("user");
				PedidoDAO pedidoDAO= new PedidoDAO();
				if(sessao.getAttribute("aceitaCookie")=="false" || umCookie == null){
					aux=(String)sessao.getAttribute("carrinho");
				}else{
					aux=umCookie.getValue();
				}
			
				int[] carrinho=CarrinhoUtils.getFromCookieValue(aux);
				int[] qtd=new int[carrinho.length];
				Double total=0.0;
				for(int i=0;i<carrinho.length;i++){
					Produto p=pdao.buscaProduto(carrinho[i]);
					qtd[i]=Integer.parseInt(req.getParameter("qtd"+carrinho[i]));
					Pedido pedido = new Pedido();
					pedido.setCodPedido(carrinho.hashCode());//igual para todos os produtos inseridos no msm pedido
					pedido.setCodProduto(p.getProduto());//repetindo dados no mysql mas fiz correndo
					pedido.setCodUsuario(u.getUnicId());
					pedido.setStatus("Aguardando pagamento");
					pedido.setPreco(p.getPreco());
					pedido.setSubtotal(p.getPreco()*qtd[i]);
					pedido.setQuantidade(qtd[i]);
					pedidoDAO.salvar(pedido);
				}
				if(sessao.getAttribute("aceitaCookie")=="false" || umCookie == null){
					sessao.setAttribute("carrinho","");
				}else{
					umCookie.setValue("");
					res.addCookie(umCookie);
				}
				req.setAttribute("total", total);
				req.setAttribute("finalizado",1);
				RequestDispatcher rd = req.getRequestDispatcher("/carrinho2.jsp");
				rd.forward(req, res);
			}
		}
	}
	public void doGet(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{
		if(!((String) req.getAttribute("logged")).equals("true")){
			res.sendRedirect(res.encodeRedirectURL("/servlet-01/Login"));
		}else{
			lista=new ArrayList<Produto>();
			ProdutoDAO pdao=new ProdutoDAO();
			Cookie umCookie = Cookies.getCookieByName("carrinho", req);
			HttpSession sessao = req.getSession();
			String aux="";
			if(sessao.getAttribute("aceitaCookie")=="false" || umCookie == null){
				aux=(String)sessao.getAttribute("carrinho");
			}else{
				aux=umCookie.getValue();
			}
		
			int[] carrinho=CarrinhoUtils.getFromCookieValue(aux);
			Double total=0.0;
			if(carrinho!=null){
				for(int i=0;i<carrinho.length;i++){
					Produto p=pdao.buscaProduto(carrinho[i]);
					lista.add(p);
					total+=p.getPreco();
				}
				req.setAttribute("produtos", lista.toArray());
			}else{
				req.setAttribute("produtos", "");
			}
			req.setAttribute("total", total);
			RequestDispatcher rd = req.getRequestDispatcher("/carrinho.jsp");
			rd.forward(req, res);
		}
	}
}