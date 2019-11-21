/**
 * 
 */
package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

		String sql = "insert into ordem_servico(dateEmissao, dataEntrega, quantidade, status, produto) values(?, ?, ?, ?, ?)";

		try {
			
			PreparedStatement statement = connection.prepareStatement(sql);
	
			statement.setDate(1, new java.sql.Date (os.getDateEmissao().getTime()));
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

		String sql = "select * from produto";
		
		PreparedStatement statement = connection.prepareStatement(sql);

		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {
			BeanProduto produto = new BeanProduto();
			produto.setCodProduto(resultSet.getLong("codProduto"));
			produto.setPn(resultSet.getString("pn"));
			produto.setDescricao(resultSet.getString("descricao"));

			retorno.add(produto);
		}

		return retorno;

	}
	
	/*	
	public List<BeanUsuario> listar() throws Exception {
		List<BeanUsuario> lista = new ArrayList<BeanUsuario>();

		String sql = "select us.codUsuario, us.nome, us.login, us.senha, tp.descricao\n" + 
				"	from usuario us left outer join tipo_usuario tp on us.tipo_usuario = tp.codTipo;";

		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {

			BeanUsuario usuario = new BeanUsuario();
			usuario.setCodUsuario(resultSet.getLong("codUsuario"));
			usuario.setNome(resultSet.getString("nome"));
			usuario.setLogin(resultSet.getString("login"));
			usuario.setSenha(resultSet.getString("senha"));
			usuario.getTipo_usuario().setDescricao(resultSet.getString("descricao"));

			lista.add(usuario);

		}

		return lista;
	}

	public BeanUsuario consultar(String codUsuario) throws Exception { //consulta para atualização

		String sql = ("select * from usuario where codUsuario ='" + codUsuario + "'");

		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();

		if (resultSet.next()) {
			BeanUsuario usuario = new BeanUsuario();
			usuario.setCodUsuario(resultSet.getLong("codUsuario"));
			usuario.setNome(resultSet.getString("nome"));
			usuario.setLogin(resultSet.getString("login"));
			usuario.setSenha(resultSet.getString("senha"));
			usuario.getTipo_usuario().setCodTipo(resultSet.getLong("tipo_usuario"));

			return usuario;
		}
		return null;
	}

	public void atualizar(BeanUsuario usuario) {
		String sql = "update usuario set nome = ?, login = ?, senha = ?, tipo_usuario = ? where codUsuario = " + usuario.getCodUsuario();

		try {
			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setString(1, usuario.getNome());
			statement.setString(2, usuario.getLogin());
			statement.setString(3, usuario.getSenha());
			statement.setLong(4, usuario.getTipo_usuario().getCodTipo());

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

	public void delete(String codUsuario) {
		try {
			String sql = "delete from usuario where codUsuario = '" + codUsuario + "'";

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
	*/
}
