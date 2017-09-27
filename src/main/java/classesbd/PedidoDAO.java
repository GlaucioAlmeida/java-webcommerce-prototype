package classesbd;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

public class PedidoDAO {

	private Session			sessao;
	private Transaction	transacao;
	//somente user cria pedido
	public void salvar(Pedido pedido) {
		try {
			this.sessao = HibernateUtil.getSessionFactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			this.sessao.save(pedido);
			this.transacao.commit();
		} catch (HibernateException e) {
			System.out.println("Não foi possível inserir a categoria. Erro: " + e.getMessage());
		} finally {
			try {
				if (this.sessao.isOpen()) {
					this.sessao.close();
					HibernateUtil.getSessionFactory().close();
				}
			} catch (Throwable e) {
				System.out.println("Erro ao fechar operação de inserção. Mensagem: " + e.getMessage());
			}
		}
	}
	//somente o admin ou user para cancelar o pedido se o mesmo ainda não foi cocluido
	public void atualizar(Pedido pedido) {
		try {
			this.sessao = HibernateUtil.getSessionFactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			this.sessao.update(pedido);
			this.transacao.commit();
		} catch (HibernateException e) {
			System.out.println("Não foi possível alterar a categoria. Erro: " + e.getMessage());
		} finally {
			try {
				if (this.sessao.isOpen()) {
					this.sessao.close();
					HibernateUtil.getSessionFactory().close();
				}
			} catch (Throwable e) {
				System.out.println("Erro ao fechar operação de atualização. Mensagem: " + e.getMessage());
			}
		}
	}
	//somente admin e user somente se pedido tiver status concluido ou cancelado
	public void excluir(Pedido pedido) {
		try {
			this.sessao = HibernateUtil.getSessionFactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			this.sessao.delete(pedido);
			this.transacao.commit();
		} catch (HibernateException e) {
			System.out.println("Não foi possível excluir a categoria. Erro: " + e.getMessage());
		} finally {
			try {
				if (this.sessao.isOpen()) {
					this.sessao.close();
					HibernateUtil.getSessionFactory().close();
				}
			} catch (Throwable e) {
				System.out.println("Erro ao fechar operação de exclusão. Mensagem: " + e.getMessage());
			}
		}
	}
	
	public Pedido buscaPedido(Integer codigo) {
		Pedido pedido = null;

		try {
			this.sessao = HibernateUtil.getSessionFactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			Criteria filtro = this.sessao.createCriteria(Pedido.class);
			filtro.add(Restrictions.idEq(codigo));
			pedido = (Pedido) filtro.uniqueResult();
			this.transacao.commit();
		} catch (Throwable e) {
			if (this.transacao.isActive()) {
				this.transacao.rollback();
			}
		} finally {
			try {
				if (this.sessao.isOpen()) {
					this.sessao.close();
					HibernateUtil.getSessionFactory().close();
				}
			} catch (Throwable e) {
				System.out.println("Erro ao fechar operação de busca. Mensagem: " + e.getMessage());
			}
		}
		return pedido;
	}
	
	@SuppressWarnings("unchecked")
	public List<Pedido> listarPodUserId(int idUsuario) {
		List<Pedido> categorias = null;

		try {
			this.sessao = HibernateUtil.getSessionFactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			Criteria filtro = this.sessao.createCriteria(Pedido.class);
			filtro.add(Restrictions.eq("cod_usuario", idUsuario));
			categorias = filtro.list();
			this.transacao.commit();
		} catch (Throwable e) {
			if (this.transacao.isActive()) {
				this.transacao.rollback();
			}
		} finally {
			try {
				if (this.sessao.isOpen()) {
					this.sessao.close();
					HibernateUtil.getSessionFactory().close();
				}
			} catch (Throwable e) {
				System.out.println("Erro ao fechar operação de listagem. Mensagem: " + e.getMessage());
			}
		}
		return categorias;
	}
	@SuppressWarnings("unchecked")
	public List<Pedido> listarPorStatus(String status) {
		List<Pedido> categorias = null;

		try {
			this.sessao = HibernateUtil.getSessionFactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			Criteria filtro = this.sessao.createCriteria(Pedido.class);
			filtro.add(Restrictions.eq("status", status));
			categorias = filtro.list();
			this.transacao.commit();
		} catch (Throwable e) {
			if (this.transacao.isActive()) {
				this.transacao.rollback();
			}
		} finally {
			try {
				if (this.sessao.isOpen()) {
					this.sessao.close();
					HibernateUtil.getSessionFactory().close();
				}
			} catch (Throwable e) {
				System.out.println("Erro ao fechar operação de listagem. Mensagem: " + e.getMessage());
			}
		}
		return categorias;
	}
	@SuppressWarnings("unchecked")
	public List<Pedido> listarPorStatusId(String status, Integer idUsuario) {
		List<Pedido> categorias = null;

		try {
			this.sessao = HibernateUtil.getSessionFactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			Criteria filtro = this.sessao.createCriteria(Pedido.class);
			filtro.add(Restrictions.eq("status", status));
			filtro.add(Restrictions.eq("codUsuario", idUsuario));
			categorias = filtro.list();
			this.transacao.commit();
		} catch (Throwable e) {
			if (this.transacao.isActive()) {
				this.transacao.rollback();
			}
		} finally {
			try {
				if (this.sessao.isOpen()) {
					this.sessao.close();
					HibernateUtil.getSessionFactory().close();
				}
			} catch (Throwable e) {
				System.out.println("Erro ao fechar operação de listagem. Mensagem: " + e.getMessage());
			}
		}
		return categorias;
	}
}

