/**
 * 
 */
package beans;

/**
 * @author david
 *
 */
public class BeanUsuario {

	private Long codUsuario;
	private String nome;
	private String login;
	private String senha;
	private Long tipo_usuario;

	public Long getCodUsuario() {
		return codUsuario;
	}

	public void setCodUsuario(Long codUsuario) {
		this.codUsuario = codUsuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Long getTipo_usuario() {
		return tipo_usuario;
	}

	public void setTipo_usuario(Long tipo_usuario) {
		this.tipo_usuario = tipo_usuario;
	}

}
