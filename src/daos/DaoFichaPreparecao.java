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

import beans.BeanFichaPreparacao;
import connection.SingleConnection;

/**
 * @author david
 *
 */
public class DaoFichaPreparecao {
	
	Connection connection;
	
	public DaoFichaPreparecao() {
		connection = SingleConnection.getConnection();
	}
	
	
	public void salvar(BeanFichaPreparacao fichaPreparacao) {
		
		String sql = "insert into ficha_de_preparacao (fBase64, fContentType) values(?, ?)";
		
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, fichaPreparacao.getfBase64());
			statement.setString(2, fichaPreparacao.getfContentType());
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
	
	public List<BeanFichaPreparacao> listar() throws Exception {
		List<BeanFichaPreparacao> lista = new ArrayList<BeanFichaPreparacao>();

		String sql = "select * from ficha_de_preparacao";

		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {

			BeanFichaPreparacao fichaPreparacao = new BeanFichaPreparacao();
			fichaPreparacao.setCodFicha(resultSet.getLong("codFicha"));
			fichaPreparacao.setfBase64(resultSet.getString("fBase64"));
			fichaPreparacao.setfContentType(resultSet.getString("fContentType"));

			lista.add(fichaPreparacao);

		}

		return lista;
	}

	public BeanFichaPreparacao consultar(String codFicha) throws Exception { //consulta para atualização

		String sql = ("select * from ficha_de_preparacao where codFicha ='" + codFicha + "'");

		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();

		if (resultSet.next()) {
			BeanFichaPreparacao fichaPreparacao = new BeanFichaPreparacao();
			fichaPreparacao.setCodFicha(resultSet.getLong("codProduto"));
			fichaPreparacao.setfBase64(resultSet.getString("fBase64"));
			fichaPreparacao.setfContentType(resultSet.getString("fContentType"));

			return fichaPreparacao;
		}
		return null;
	}

	public void atualizar(BeanFichaPreparacao fichaPreparacao) {
		String sql = "update ficha_de_preparacao set fBase64 = ?, fContentType = ? where codFicha = " + fichaPreparacao.getCodFicha();

		try {
			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setString(1, fichaPreparacao.getfBase64());
			statement.setString(2, fichaPreparacao.getfContentType());

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

	public void delete(String codFicha) {
		try {
			String sql = "delete from ficha_de_preparacao where codFicha = '" + codFicha + "'";

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
