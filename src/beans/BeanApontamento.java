package beans;

public class BeanApontamento {

	private Long codApontamento;
	private String inicio;
	private String termino;
	private Long usuario;
	private Long operacao;
	private Long ordem_servico;

	public Long getCodApontamento() {
		return codApontamento;
	}

	public void setCodApontamento(Long codApontamento) {
		this.codApontamento = codApontamento;
	}

	public String getInicio() {
		return inicio;
	}

	public void setInicio(String inicio) {
		this.inicio = inicio;
	}

	public String getTermino() {
		return termino;
	}

	public void setTermino(String termino) {
		this.termino = termino;
	}

	public Long getUsuario() {
		return usuario;
	}

	public void setUsuario(Long usuario) {
		this.usuario = usuario;
	}

	public Long getOperacao() {
		return operacao;
	}

	public void setOperacao(Long operacao) {
		this.operacao = operacao;
	}

	public Long getOrdem_servico() {
		return ordem_servico;
	}

	public void setOrdem_servico(Long ordem_servico) {
		this.ordem_servico = ordem_servico;
	}

}
