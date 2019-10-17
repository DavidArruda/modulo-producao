/**
 * 
 */
package beans;

/**
 * @author david
 *
 */
public class BeanOperacao {

	private Long codOperacao;
	private Integer nOperacao;
	private Long ficha_de_preparacao;
	private Long desenho;
	private Long produto;

	public Long getCodOperacao() {
		return codOperacao;
	}

	public void setCodOperacao(Long codOperacao) {
		this.codOperacao = codOperacao;
	}

	public Integer getnOperacao() {
		return nOperacao;
	}

	public void setnOperacao(Integer nOperacao) {
		this.nOperacao = nOperacao;
	}

	public Long getFicha_de_preparacao() {
		return ficha_de_preparacao;
	}

	public void setFicha_de_preparacao(Long ficha_de_preparacao) {
		this.ficha_de_preparacao = ficha_de_preparacao;
	}

	public Long getDesenho() {
		return desenho;
	}

	public void setDesenho(Long desenho) {
		this.desenho = desenho;
	}

	public Long getProduto() {
		return produto;
	}

	public void setProduto(Long produto) {
		this.produto = produto;
	}

}
