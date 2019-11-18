package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.BeanOperacao;
import daos.DaoOperacao;
import daos.DaoProduto;


@WebServlet("/salvarOperacao")
public class ServletOperacao extends HttpServlet {
	private static final long serialVersionUID = 1L;

	DaoOperacao daoOperacao = new DaoOperacao();
	DaoProduto daoProduto = new DaoProduto();

	public ServletOperacao() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			String acao = request.getParameter("acao") != null ? request.getParameter("acao") : "listarTodos";
			String operacao = request.getParameter("operacao");

			RequestDispatcher view = request.getRequestDispatcher("/cadastroOperacao.jsp");

			if (acao.equalsIgnoreCase("delete")) {
				daoOperacao.delete(operacao);
				request.setAttribute("operacoes", daoOperacao.listar());

			} else if (acao.equalsIgnoreCase("editar")) {
				BeanOperacao beanOperacao = daoOperacao.consultar(operacao);
				request.setAttribute("operacao", beanOperacao);

			} else if (acao.equalsIgnoreCase("listartodos")) {
				request.setAttribute("operacoes", daoOperacao.listar());
			}
			
			request.setAttribute("produtos", daoProduto.listar());
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
				RequestDispatcher view = request.getRequestDispatcher("/cadastroOperacao.jsp");request.setAttribute("cargos", daoProduto.listar());
				request.setAttribute("produtos", daoProduto.listar());
				request.setAttribute("operacoes", daoOperacao.listar());
				view.forward(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			
			try {
			
			String codOperacao = request.getParameter("codOperacao");
			String nOperacao = request.getParameter("nOperacao");
			String produto = request.getParameter("produto");

			BeanOperacao operacao = new BeanOperacao();
			operacao.setCodOperacao(!codOperacao.isEmpty() ? Long.parseLong(codOperacao) : null);
			operacao.setnOperacao(Integer.parseInt(nOperacao));
			operacao.getProduto().setCodProduto(Long.parseLong(produto));

			
				// VALIDA SE OS CAMPOS EST�O PREENCHIDOS
				if (nOperacao == null || nOperacao.isEmpty()) {
					request.setAttribute("msg", "Campo Nº está vazio");
					request.setAttribute("operacao", operacao);

				} else if (produto.isEmpty()) {
					request.setAttribute("msg", "Campo Produto está vazio");
					request.setAttribute("operacao", operacao);

				} else if (codOperacao == null || codOperacao.isEmpty()) { // VERIFICA ID E SALVA PRODUTO
					daoOperacao.salvar(operacao);

				} else { // ATUALIZA REGISTRO
					daoOperacao.atualizar(operacao);
				}

				// REDIRECIONA A P�GINA E LISTA OS PRODUTOS NOVAMENTE
				RequestDispatcher view = request.getRequestDispatcher("/cadastroOperacao.jsp");
				request.setAttribute("produtos", daoProduto.listar());
				request.setAttribute("operacoes", daoOperacao.listar());
				view.forward(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}
}
