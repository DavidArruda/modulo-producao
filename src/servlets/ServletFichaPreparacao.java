package servlets;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.tomcat.util.codec.binary.Base64;

import beans.BeanFichaPreparacao;
import daos.DaoFichaPreparecao;

@WebServlet("/salvarFicha")
public class ServletFichaPreparacao extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DaoFichaPreparecao daoFichaPreparacao = new DaoFichaPreparecao();

	public ServletFichaPreparacao() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			String acao = request.getParameter("acao");
			String fichaPreparacao = request.getParameter("fichaPreparacao");

			RequestDispatcher view = request.getRequestDispatcher("/cadastroFichaPreparacao.jsp");

			if (acao.equalsIgnoreCase("delete")) {
				daoFichaPreparacao.delete(fichaPreparacao);
				request.setAttribute("fichas", daoFichaPreparacao.listar());

			} else if (acao.equalsIgnoreCase("editar")) {
				BeanFichaPreparacao beanFicha = daoFichaPreparacao.consultar(fichaPreparacao);
				request.setAttribute("fichaPreparacao", beanFicha);

			} else if (acao.equalsIgnoreCase("listartodos")) {
				request.setAttribute("fichas", daoFichaPreparacao.listar());

			} else if (acao != null && acao.equalsIgnoreCase("download")) {

				BeanFichaPreparacao ficha = daoFichaPreparacao.consultar(fichaPreparacao);

				if (ficha != null) {

					String contentType = "";
					byte[] fileBytes = null;

					contentType = ficha.getfContentType();
					fileBytes = new Base64().decodeBase64(ficha.getfBase64()); // converte a base64 do pdf para byte[]

					response.setHeader("Content-Disposition",
							"attachment;filename=arquivo." + contentType.split("\\/")[1]);

					// Coloca os bytes em objeto de entrada para processar
					InputStream is = new ByteArrayInputStream(fileBytes);

					// resposta ao navegador
					int read = 0;
					byte[] bytes = new byte[1024];
					OutputStream os = response.getOutputStream();

					while ((read = is.read(bytes)) != -1) {
						os.write(bytes, 0, read);
					}

					os.flush();
					os.close();
				}
			}
			
			view.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String acao = request.getParameter("acao");

		if (acao != null && acao.equalsIgnoreCase("reset")) {

			try {
				RequestDispatcher view = request.getRequestDispatcher("/cadastroFichaPreparacao.jsp");
				request.setAttribute("fichas", daoFichaPreparacao.listar());
				view.forward(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {

			try {

				BeanFichaPreparacao ficha = new BeanFichaPreparacao();

				// Processa curriculo
				Part fichaPreparacao = request.getPart("fichaPreparacao");

				if (fichaPreparacao != null && fichaPreparacao.getInputStream().available() > 0) {
					String fBase64 = new Base64()
							.encodeBase64String(convertStreamParByte(fichaPreparacao.getInputStream()));

					ficha.setfBase64(fBase64);
					ficha.setfContentType(fichaPreparacao.getContentType());

				} else {
					ficha.setAtualizar(false);
				}

			} catch (Exception e) {

			}

		}
	}

	// Converte a entrada de fluxo de dados da imagem para byte[]
	private static byte[] convertStreamParByte(InputStream imagem) throws Exception {

		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		int reads = imagem.read();

		while (reads != -1) { // enquanto tiver dados em reads
			baos.write(reads);
			reads = imagem.read();
		}

		return baos.toByteArray();

	}

}
