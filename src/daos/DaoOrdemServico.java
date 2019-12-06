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

import beans.BeanOrdemServico;
import beans.BeanProduto;
import connection.SingleConnection;

/**
 * @author david
 *
 */
public class DaoOrdemServico {

	Connection connection;

	public DaoOrdemServico() {
		connection = SingleConnection.getConnection();
	}

	public void salvar(BeanOrdemServico os) {

		String sql = "insert into ordem_servico (dateEmissao, dataEntrega, quantidade, status, produto) values(?, ?, ?, ?, ?)";

		try {

			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setDate(1, new java.sql.Date(os.getDateEmissao().getTime()));
			statement.setDate(2, new java.sql.Date(os.getDataEntrega().getTime()));
			statement.setInt(3, os.getQuantidade());
			statement.setString(4, os.getStatus());
			statement.setLong(5, os.getProduto().getCodProduto());

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

	public List<BeanProduto> listaProdutos() throws Exception {
		List<BeanProduto> retorno = new ArrayList<BeanProduto>();

		String sql = "select codProduto, pn from produto";

		PreparedStatement statement = connection.prepareStatement(sql);

		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {
			BeanProduto produto = new BeanProduto();
			produto.setCodProduto(resultSet.getLong("codProduto"));
			produto.setPn(resultSet.getString("pn"));

			retorno.add(produto);
		}

		return retorno;

	}

	public List<BeanOrdemServico> listar() throws Exception {
		List<BeanOrdemServico> lista = new ArrayList<BeanOrdemServico>();

		String sql = "select os.codOs, os.dateEmissao, os.dataEntrega, os.quantidade, os.status, pr.pn"
				+ " from ordem_servico os left outer join produto pr on os.produto = pr.codProduto;";

		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {

			BeanOrdemServico os = new BeanOrdemServico();
			os.setCodOs(resultSet.getLong("codOs"));
			os.setDateEmissao(resultSet.getDate("dateEmissao"));
			os.setDataEntrega(resultSet.getDate("dataEntrega"));
			os.setQuantidade(resultSet.getInt("quantidade"));
			os.setStatus(resultSet.getString("status"));
			os.getProduto().setPn(resultSet.getString("pn"));

			lista.add(os);

		}

		return lista;
	}

	public BeanOrdemServico consultar(String codOs) throws Exception { // consulta para atualização

		String sql = ("select * from ordem_servico where codOs ='" + codOs + "'");

		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();

		if (resultSet.next()) {

			BeanOrdemServico os = new BeanOrdemServico();
			os.setCodOs(resultSet.getLong("codOs"));
			os.setDateEmissao(resultSet.getDate("dateEmissao"));
			os.setDataEntrega(resultSet.getDate("dataEntrega"));
			os.setQuantidade(resultSet.getInt("quantidade"));
			os.setStatus(resultSet.getString("status"));
			os.getProduto().setCodProduto(resultSet.getLong("produto"));

			return os;
		}
		return null;
	}

	public void atualizar(BeanOrdemServico os) {
		String sql = "update ordem_servico set dateEmissao = ?, dataEntrega = ?, quantidade = ?, status = ?, produto = ? where codOs = "
				+ os.getCodOs();

		try {
			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setDate(1, new java.sql.Date(os.getDateEmissao().getTime()));
			statement.setDate(2, new java.sql.Date(os.getDateEmissao().getTime()));
			statement.setInt(3, os.getQuantidade());
			statement.setString(4, os.getStatus());
			statement.setLong(5, os.getProduto().getCodProduto());

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

	public void delete(String codOs) {
		try {
			String sql = "delete from ordem_servico where codUsuario = '" + codOs + "'";

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
