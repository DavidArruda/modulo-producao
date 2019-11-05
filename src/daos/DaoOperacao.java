/**
 * 
 */
package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.BeanOperacao;
import beans.BeanProduto;
import beans.BeanTipoUsuario;
import beans.BeanUsuario;
import connection.SingleConnection;

/**
 * @author david
 *
 */
public class DaoOperacao {
	
	Connection connection;
	
	public DaoOperacao() {
		connection = SingleConnection.getConnection();
	}
	
	
	public void salvar(BeanOperacao operacao) {

		String sql = "insert into operacao (nOperacao, produto) values(?, ?)";

		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, operacao.getnOperacao());
			statement.setLong(2, operacao.getProduto().getCodProduto());
			statement.execute();
			
			connection.commit();

		} catch (SQLException e) {
			e.printStackTrace();

			try {
				connection.rollback();

			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	public List<BeanProduto> listaProduto() throws Exception {
		List<BeanProduto> retorno = new ArrayList<BeanProduto>();

		String sql = "select * from produto";
		
		PreparedStatement statement = connection.prepareStatement(sql);

		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {
			BeanProduto produto = new BeanProduto();
			produto.setCodProduto(resultSet.getLong("codProduto"));
			produto.setPn(resultSet.getString("pn"));
			produto.setCliente(resultSet.getString("cliente"));
			produto.setDescricao(resultSet.getString("descricao"));

			retorno.add(produto);
		}

		return retorno;

	}
	
	
	public List<BeanOperacao> listar() throws Exception {
		List<BeanOperacao> lista = new ArrayList<BeanOperacao>();

		String sql = "select op.codOperacao, op.nOperacao, pr.pn"
				+ " from operacao op left outer join produto pr on op.produto = pr.codProduto;";

		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {

			BeanOperacao operacao = new BeanOperacao();
			operacao.setCodOperacao(resultSet.getLong("codOperacao"));
			operacao.setnOperacao(resultSet.getInt("nOperacao"));
			operacao.getProduto().setPn(resultSet.getString("pn"));

			lista.add(operacao);

		}

		return lista;
	}

	public BeanOperacao consultar(String codOperacao) throws Exception { //consulta para atualização

		String sql = ("select * from operacao where codOperacao ='" + codOperacao + "'");

		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();

		if (resultSet.next()) {
			BeanOperacao operacao = new BeanOperacao();
			operacao.setCodOperacao(resultSet.getLong("codOperacao"));
			operacao.setnOperacao(resultSet.getInt("nOperacao"));
			operacao.getProduto().setCodProduto(resultSet.getLong("produto"));

			return operacao;
		}
		return null;
	}

	public void atualizar(BeanOperacao operacao) {
		String sql = "update operacao set nOperacao = ?, produto = ? where codOperacao = " + operacao.getCodOperacao();

		try {
			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setInt(1, operacao.getnOperacao());
			statement.setLong(2, operacao.getProduto().getCodProduto());

			statement.executeUpdate();

			connection.commit();

		} catch (SQLException e) {

			e.printStackTrace();

			try {
				connection.rollback();

			} catch (SQLException e1) {

				e1.printStackTrace();
			}
		}

	}

	public void delete(String codOperacao) {
		try {
			String sql = "delete from operacao where codOperacao = '" + codOperacao + "'";

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.execute();
			connection.commit();

		} catch (SQLException e) {
			e.printStackTrace();

			try {
				connection.rollback();

			} catch (SQLException e1) {
				e1.printStackTrace();
			}

		}

	}
}
