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
	private BeanProduto produto = new BeanProduto();

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

	public BeanProduto getProduto() {
		return produto;
	}

	public void setProduto(BeanProduto produto) {
		this.produto = produto;
	}


}
