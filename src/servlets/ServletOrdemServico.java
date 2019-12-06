package servlets;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.BeanOrdemServico;
import daos.DaoOrdemServico;

@WebServlet("/salvarOrdemServico")
public class ServletOrdemServico extends HttpServlet {
	private static final long serialVersionUID = 1L;

	DaoOrdemServico daoOrdemServico = new DaoOrdemServico();
	BeanOrdemServico beanOrdemServico = new BeanOrdemServico();

	public ServletOrdemServico() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			String acao = request.getParameter("acao") != null ? request.getParameter("acao") : "listarTodos";
			String os = request.getParameter("os");

			RequestDispatcher view = request.getRequestDispatcher("/cadastroOrdemServico.jsp");

			if (acao.equalsIgnoreCase("delete")) {
				daoOrdemServico.delete(os);
				request.setAttribute("ordens", daoOrdemServico.listar());

			} else if (acao.equalsIgnoreCase("editar")) {
				beanOrdemServico = daoOrdemServico.consultar(os);
				request.setAttribute("os", beanOrdemServico);

			} else if (acao.equalsIgnoreCase("listartodos")) {
				request.setAttribute("ordens", daoOrdemServico.listar());

			}

			request.setAttribute("produtos", daoOrdemServico.listaProdutos());
			view.forward(request, response); // Confirmação do redirecionamento

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String acao = request.getParameter("acao");

		if (acao != null && acao.equalsIgnoreCase("reset")) {

			try {
				RequestDispatcher view = request.getRequestDispatcher("/cadastroOrdemServico.jsp");
				request.setAttribute("ordens", daoOrdemServico.listar());
				request.setAttribute("produtos", daoOrdemServico.listaProdutos());
				view.forward(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {

			String codOs = request.getParameter("codOs");
			String dateEmissao = request.getParameter("dateEmissao");
			String dataEntrega = request.getParameter("dataEntrega");
			String quantidade = request.getParameter("quantidade");
			String status = request.getParameter("status");
			String produto = request.getParameter("produto");

			try {

				Date dFormatadaEmissao = new SimpleDateFormat("dd/MM/yyyy").parse(dateEmissao);
				Date dFormatadaEntrega = new SimpleDateFormat("dd/MM/yyyy").parse(dataEntrega);

				beanOrdemServico.setCodOs(!codOs.isEmpty() ? Long.parseLong(codOs) : null);
				beanOrdemServico.setDateEmissao(dFormatadaEmissao);
				beanOrdemServico.setDataEntrega(dFormatadaEntrega);
				beanOrdemServico.setQuantidade(Integer.parseInt(quantidade));
				beanOrdemServico.setStatus(status);
				beanOrdemServico.getProduto().setCodProduto(Long.parseLong(produto));

			} catch (Exception e) {
				e.printStackTrace();

			}

			try {
				// VALIDA SE OS CAMPOS ESTÃO PREENCHIDOS
				if (dateEmissao == null || dateEmissao.isEmpty()) {
					request.setAttribute("msg", "Campo Data Emissão está vazio");
					request.setAttribute("os", beanOrdemServico);

				} else if (dataEntrega.isEmpty()) {
					request.setAttribute("msg", "Campo Data Entrega está vazio");
					request.setAttribute("os", beanOrdemServico);

				} else if (quantidade.isEmpty()) {
					request.setAttribute("msg", "Campo Quantidade está vazio");
					request.setAttribute("os", beanOrdemServico);

				} else if (status.isEmpty()) {
					request.setAttribute("msg", "Campo Status está vazio");
					request.setAttribute("os", beanOrdemServico);

				} else if (codOs == null || codOs.isEmpty()) { // VERIFICA ID E SALVA PRODUTO
					daoOrdemServico.salvar(beanOrdemServico);

				} else { // ATUALIZA REGISTRO
					daoOrdemServico.atualizar(beanOrdemServico);
				}

				// REDIRECIONA A PÁGINA E LISTA OS PRODUTOS NOVAMENTE
				RequestDispatcher view = request.getRequestDispatcher("/cadastroOrdemServico.jsp");
				request.setAttribute("ordens", daoOrdemServico.listar());
				request.setAttribute("produtos", daoOrdemServico.listaProdutos());
				view.forward(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

}
