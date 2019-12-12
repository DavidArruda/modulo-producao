package servlets;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.BeanApontamento;
import daos.DaoApontamento;

/**
 * Servlet implementation class ServletApontamento
 */
@WebServlet("/ServletApontamento")
public class ServletApontamento extends HttpServlet {
	private static final long serialVersionUID = 1L;

	BeanApontamento apontamento = new BeanApontamento();
	DaoApontamento daoApontamento = new DaoApontamento();

	public ServletApontamento() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String acao = request.getParameter("acao");
		
		if(acao.equalsIgnoreCase("listar")) {
			
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String acao = request.getParameter("acao");

		if (acao != null && acao.equalsIgnoreCase("reset")) {

			try {
				RequestDispatcher view = request.getRequestDispatcher("/cadastroApontamento.jsp");
				// request.setAttribute("usuarios", daoUsuario.listar());
				// request.setAttribute("cargos", daoUsuario.listaTipoUsuario());
				view.forward(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {

			try {
				String codApontamento = request.getParameter("codApontamento");
				String inicio = request.getParameter("inicio");
				String termino = request.getParameter("termino");
				String usuario = request.getParameter("usuario");
				String operacao = request.getParameter("operacao");
				String os = request.getParameter("os");

				DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

				LocalDateTime inicioFormatado = LocalDateTime.parse(inicio, formatador);
				LocalDateTime terminoFormatado = LocalDateTime.parse(termino, formatador);

				apontamento.setCodApontamento((!codApontamento.isEmpty() ? Long.parseLong(codApontamento) : null));
				apontamento.setInicio(inicioFormatado);
				apontamento.setTermino(terminoFormatado);
				apontamento.getUsuario().setCodUsuario(Long.parseLong(usuario));
				apontamento.getOperacao().setCodOperacao(Long.parseLong(operacao));
				apontamento.getOrdem_servico().setCodOs(Long.parseLong(os));

				// VALIDA SE OS CAMPOS ESTÃO PREENCHIDOS
				if (inicio == null || inicio.isEmpty()) {
					request.setAttribute("msg", "Campo Inico está vazio");
					request.setAttribute("apontamento", apontamento);

				} else if (termino.isEmpty()) {
					request.setAttribute("msg", "Campo Termino está vazio");
					request.setAttribute("apontamento", apontamento);

				} else if (usuario.isEmpty()) {
					request.setAttribute("msg", "Campo Usuário está vazio");
					request.setAttribute("apontamento", apontamento);

				} else if (operacao.isEmpty()) {
					request.setAttribute("msg", "Campo Operação está vazio");
					request.setAttribute("apontamento", apontamento);

				} else if (os.isEmpty()) {
					request.setAttribute("msg", "Campo Os está vazio");
					request.setAttribute("apontamento", apontamento);

				} else if (codApontamento == null || codApontamento.isEmpty()) { // VERIFICA ID E SALVA PRODUTO
					daoApontamento.salvar(apontamento);

				} else { // ATUALIZA REGISTRO
					// daoUsuario.atualizar(usuario);
				}

				// REDIRECIONA A PÁGINA E LISTA OS PRODUTOS NOVAMENTE
				RequestDispatcher view = request.getRequestDispatcher("/cadastroApontamento.jsp");
				// request.setAttribute("apontamentos", daoUsuario.listar());
				// request.setAttribute("cargos", daoUsuario.listaTipoUsuario());
				view.forward(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

}
