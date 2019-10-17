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

import beans.BeanUsuario;
import connection.SingleConnection;

/**
 * @author deh0_
 *
 */
public class DaoUsuario {

	private Connection connection;

	public DaoUsuario() {
		connection = SingleConnection.getConnection();
	}

	public void salvar(BeanUsuario usuario) {

		String sql = "insert into usuario (nome, login, senha, tipo_usuario" + " values (?, ?, ?, ?)";

		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, usuario.getLogin());
			statement.setString(2, usuario.getSenha());
			statement.setString(3, usuario.getNome());
			statement.setLong(4, usuario.getTipo_usuario());
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

	public List<BeanUsuario> listar(String descricaoconsulta) throws SQLException {
		String sql = "select * from usuario where login <> 'admin' and nome like'%" + descricaoconsulta + "%'";
		return consultarUsuarios(sql);
	}

	public List<BeanUsuario> listar() throws Exception {

		String sql = "select * from usuario where login <> 'admin'";
		return consultarUsuarios(sql);

	}

	private List<BeanUsuario> consultarUsuarios(String sql) throws SQLException {

		List<BeanUsuario> lista = new ArrayList<BeanUsuario>();
		PreparedStatement select = connection.prepareStatement(sql);
		ResultSet resultSet = select.executeQuery();

		while (resultSet.next()) {
			BeanUsuario usuario = new BeanUsuario();
			usuario.setCodUsuario(resultSet.getLong("codUsuario"));
			usuario.setNome(resultSet.getString("nome"));
			usuario.setLogin(resultSet.getString("login"));
			usuario.setSenha(resultSet.getString("senha"));
			usuario.setTipo_usuario(resultSet.getLong("tipo_usuario"));

			lista.add(usuario);
		}
		return lista;

	}

	public void delete(String id) {

		try {

			String sql = "delete from usuario where codUsuario = '" + id + "' and login <> 'admin'";

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

	public BeanUsuario consultar(String codUsuario) throws Exception { // consulta para realizar update

		String sql = ("select * from usuario where codUsuario ='" + codUsuario + "' and login <> 'admin'");

		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();

		if (resultSet.next()) {
			BeanUsuario usuario = new BeanUsuario();

			usuario.setCodUsuario(resultSet.getLong("codUsuario"));
			usuario.setNome(resultSet.getString("nome"));
			usuario.setLogin(resultSet.getString("login"));
			usuario.setSenha(resultSet.getString("senha"));
			usuario.setTipo_usuario(resultSet.getLong("tipo_usuario"));

			return usuario;
		}
		return null;
	}

	public Boolean validarLogin(String login) throws Exception {

		String sql = ("select count(1) as qtd from usuario where login ='" + login + "'");

		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();

		if (resultSet.next()) {

			return resultSet.getInt("qtd") <= 0; // Return true
		}
		return false;
	}

	public Boolean validarLoginUpdate(String login, String codUsuario) throws Exception {

		String sql = ("select count(1) as qtd from usuario where login ='" + login + "' and codUsuario <> "
				+ codUsuario);

		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();

		if (resultSet.next()) {

			return resultSet.getInt("qtd") <= 0; // Return true
		}
		return false;
	}

	public void atualizar(BeanUsuario usuario) {

		String sql = "update usuario set nome = ?, login = ?, senha = ?, tipo_usuario = ?";

		try {
			PreparedStatement statement = connection.prepareStatement(sql.toString());
			statement.setString(1, usuario.getNome());
			statement.setString(2, usuario.getLogin());
			statement.setString(3, usuario.getSenha());
			statement.setLong(4, usuario.getTipo_usuario());

			statement.executeUpdate();

			connection.commit();

		} catch (Exception e) {
			e.printStackTrace();

			try {
				connection.rollback();

			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

}
