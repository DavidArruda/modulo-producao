package beans;

import java.time.LocalDateTime;

public class BeanApontamento {

	private Long codApontamento;
	private LocalDateTime inicio;
	private LocalDateTime termino;
	private BeanUsuario usuario = new BeanUsuario();
	private BeanOperacao operacao = new BeanOperacao();
	private BeanOrdemServico ordem_servico = new BeanOrdemServico();

	public Long getCodApontamento() {
		return codApontamento;
	}

	public void setCodApontamento(Long codApontamento) {
		this.codApontamento = codApontamento;
	}

	public LocalDateTime getInicio() {
		return inicio;
	}

	public void setInicio(LocalDateTime inicio) {
		this.inicio = inicio;
	}

	public LocalDateTime getTermino() {
		return termino;
	}

	public void setTermino(LocalDateTime termino) {
		this.termino = termino;
	}

	public BeanUsuario getUsuario() {
		return usuario;
	}

	public void setUsuario(BeanUsuario usuario) {
		this.usuario = usuario;
	}

	public BeanOperacao getOperacao() {
		return operacao;
	}

	public void setOperacao(BeanOperacao operacao) {
		this.operacao = operacao;
	}

	public BeanOrdemServico getOrdem_servico() {
		return ordem_servico;
	}

	public void setOrdem_servico(BeanOrdemServico ordem_servico) {
		this.ordem_servico = ordem_servico;
	}
}
