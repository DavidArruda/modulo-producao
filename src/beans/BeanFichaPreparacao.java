/**
 * 
 */
package beans;

/**
 * @author david
 *
 */
public class BeanFichaPreparacao {

	private Long codFicha;
	private String fBase64;
	private String fContentType;
	private boolean atualizar = true;
	
	public boolean isAtualizar() {
		return atualizar;
	}
	
	public void setAtualizar(boolean atualizar) {
		this.atualizar = atualizar;
	}

	public Long getCodFicha() {
		return codFicha;
	}

	public void setCodFicha(Long codFicha) {
		this.codFicha = codFicha;
	}

	public String getfBase64() {
		return fBase64;
	}

	public void setfBase64(String fBase64) {
		this.fBase64 = fBase64;
	}

	public String getfContentType() {
		return fContentType;
	}

	public void setfContentType(String fContentType) {
		this.fContentType = fContentType;
	}

}
