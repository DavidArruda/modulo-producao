/**
 * 
 */
package beans;

import java.util.Date;

/**
 * @author david
 *
 */
public class BeanOrdemServico {

	private Long codOs;
	private Date dateEmissao;
	private Date dataEntrega;
	private Integer quantidade;
	private String status;
	private BeanProduto produto;

	public Long getCodOs() {
		return codOs;
	}

	public void setCodOs(Long codOs) {
		this.codOs = codOs;
	}

	public Date getDateEmissao() {
		return dateEmissao;
	}

	public void setDateEmissao(Date dateEmissao) {
		this.dateEmissao = dateEmissao;
	}

	public Date getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(Date dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BeanProduto getProduto() {
		return produto;
	}

	public void setProduto(BeanProduto produto) {
		this.produto = produto;
	}

}
