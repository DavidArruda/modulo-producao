package daos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import beans.BeanApontamento;
import connection.SingleConnection;

public class DaoApontamento {

	private Connection connection;

	public DaoApontamento() {
		connection = SingleConnection.getConnection();
	}

	public void salvar(BeanApontamento apontamento) {

		String sql = "insert into apontamento (inicio, termino, usuario, operacao, ordem_servico)"
				+ " values (?, ?, ?, ?, ?)";

		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setDate(1, new Date(apontamento.getInicio().getHour()));
			statement.setDate(2, new Date(apontamento.getTermino().getHour()));
			statement.setLong(3, apontamento.getUsuario().getCodUsuario());
			statement.setLong(4, apontamento.getOperacao().getCodOperacao());
			statement.setLong(5, apontamento.getOrdem_servico().getCodOs());
			
			statement.execute();

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
	
	public List<BeanApontamento> listar() throws SQLException {
		
		String sql = "select * from apontamento";
		
		List<BeanApontamento> apontamentos = new ArrayList<BeanApontamento>();
		
		PreparedStatement statement = connection.prepareStatement(sql);
		
		ResultSet resultSet = statement.executeQuery();
		
		while(resultSet.next()) {
			
			BeanApontamento apontamento = new BeanApontamento();
			
			apontamento.setCodApontamento(resultSet.getLong("codApontamento"));
			apontamento.setInicio(formatarData(resultSet.getDate("inicio")));
			apontamento.setTermino(formatarData(resultSet.getDate("termino")));
			apontamento.getUsuario().setCodUsuario(resultSet.getLong("codUsuario"));
			apontamento.getOperacao().setCodOperacao(resultSet.getLong("operacao"));
			apontamento.getOrdem_servico().setCodOs(resultSet.getLong("os"));
		}
		
		return apontamentos;
	}
	
	public LocalDateTime formatarData(Date dataDoBanco) {

		LocalDateTime localDateTime = dataDoBanco.toInstant().atZone( ZoneId.systemDefault() ).toLocalDateTime();
		
		return localDateTime;
	}

}
