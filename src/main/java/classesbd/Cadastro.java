package classesbd;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Cadastro {
	/*public static void main(){
		Cadastro cad=new Cadastro();
		Usuario u=new Usuario();
		Produto p=new Produto();
		Categoria c=new Categoria();
		Admin a=new Admin();
		a.setConta("admin");//login
		a.setSenha("admin");//senha
		a.setNome("Administrador");
		a.setEndereco("nenhum");
		u.setConta("conta");
		u.setSenha("senha");
		p.setDescricao("Celular");
		p.setPreco(500.0);
		c.setDescricao("Geral");
		Set<Produto> produtos = new HashSet<Produto>();
		produtos.add(p);
		c.setProdutos(produtos);
		cad.cadastraUsuario(u);
		cad.cadastraCategorias(c);
		cad.cadastraProdutos(p);
		cad.cadastraAdmin(a);
	}*/
	//pagina registro
	public boolean cadastraUsuario(Usuario u) {
		if(u!=null){
			UsuarioDAO userDAO = new UsuarioDAO();
			userDAO.salvar(u);
			return true;
		}else
			return false;
	}
	//somente Admin
	public boolean cadastraAdmin(Admin u) {
		if(u!=null){
			AdminDAO userDAO = new AdminDAO();
			userDAO.salvar(u);
			return true;
		}else
			return false;
	}
	//somente Admin
	public boolean cadastraProdutos(Produto p) {
		if(p!=null){
			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtoDAO.salvar(p);
			return true;
		}else
			return false;
	}
	//somente Admin
	public boolean cadastraCategorias(Categoria categoria) {
		if(categoria==null) return false;
		CategoriaDAO categoriaDAO = new CategoriaDAO();
		categoriaDAO.salvar(categoria);
		return true;
	}
	//somente Admin recebe uma lista de produtos e adiciona em categoria
	public boolean cadastraCategorias(Categoria categoria, List<Produto> listProduto) {
		if(categoria==null||listProduto==null) return false;
		CategoriaDAO categoriaDAO = new CategoriaDAO();
		Set<Produto> produtos = new HashSet<Produto>();
		for (int i = 0; i < listProduto.size(); i++) {
			produtos.add(listProduto.get(i));
		}
		categoria.setProdutos(produtos);
		categoriaDAO.salvar(categoria);
		return true;
	}
}

