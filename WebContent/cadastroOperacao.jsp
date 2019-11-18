<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>

<meta charset="ISO-8859-1">
<title>Processo</title>
<script src="resources/javascript/jquery.min.js" type="text/javascript"></script>
<link rel="stylesheet" href="resources/css/cadastro.css">

</head>

<body>
	<a href="acessoliberado.jsp"><img alt="Inicio" title="Início"
		src="resources/img/home.jpeg" height="48px" width="48px"></a>
	<a href="index.jsp"><img alt="Sair" title="Sair"
		src="resources/img/sair.png" height="48px" width="48px"></a>
	<center>
		<h1>Cadastro de Operações</h1>
		<h3 style="color: red;">${msg}</h3>
	</center>

	<form action="salvarOperacao" method="post" id="formUser"
		onsubmit="return validarCampos() ? true : false;">
		<ul class="form-style-1">
			<li>
				<table>
					<tr>
						<td>Código:</td>
						<td><input type="text" readonly="readonly" id="codOperacao"
							name="codOperacao" value="${operacao.codOperacao}"
							class="field-long"></td>
					</tr>
					<tr>
						<td>Nº OP:</td>
						<td><input type="text" id="nOperacao" name="nOperacao"
							placeholder="Informe o Nº da op" maxlength="4"
							value="${operacao.nOperacao}"></td>
					</tr>

					<tr>
						<td>Produto:</td>
						<td>
							<select id="produtos" name="produto">
								<c:forEach items="${produtos}" var="pr">
									<option value="${pr.codProduto}" id="${pr.codProduto}"
										<c:if test="${pr.codProduto == operacao.produto.codProduto}">
										<c:out value="selected=selected" />
										</c:if>>
									${pr.pn}
									</option>
								</c:forEach>
							
							</select>
						</td>
					</tr>

					<tr>
						<td></td>
						<td><input type="submit" value="Salvar" style="width: 95px;">
							<input type="submit" value="Cancelar" style="width: 95px;"
							onclick="document.getElementById('formUser').action ='salvarOperacao?acao=reset'"></td>
					</tr>
				</table>

			</li>
		</ul>
	</form>

	<div class="container">
		<table class="responsive-table">
			<caption>Lista de Produtos</caption>
			<tr>
				<th>Código</th>
				<th>Nº Operação</th>
				<th>Produto</th>
				<th>Ficha de Preparação</th>
				<th>Deletar</th>
				<th>Atualizar</th>
			</tr>

			<c:forEach items="${operacoes}" var="operacao">
				<tr>
					<td style="width: 150px"><c:out
							value="${operacao.codOperacao}">
						</c:out></td>

					<td style="width: 150px"><c:out value="${operacao.nOperacao}">
						</c:out></td>

					<td><c:out value="${operacao.produto}"></c:out></td>
					
					<td><a href="salvarFicha?acao=addFicha&operacao=${operacao.codOperacao}"><img
							alt="Telefone" title="Telefones" src="resources/img/phone.png"
							width="20px" height="20px"></a></td>

					<td><a
						href="salvarOperacao?acao=delete&operacao=${operacao.codOperacao}"
						onclick="return confirm('Confirmar a exclusão?');"><img
							src="resources/img/excluir.png" alt="excluir" title="Excluir"
							width="20px" height="20px"> </a></td>

					<td><a
						href="salvarOperacao?acao=editar&operacao=${operacao.codOperacao}"><img
							alt="Editar" title="Editar" src="resources/img/editar.png"
							width="20px" height="20px"></a></td>
				</tr>
			</c:forEach>
		</table>
	</div>

	<script type="text/javascript">
		function validarCampos() {

			if (document.getElementById("nOperacao").value == '') {
				alert('Informe o Nº da OP');
				return false;
			} else if (document.getElementById("Produto").value == '') {
				alert('Informe o pn do Produto');
				return false;
			}	
			return true;

		}
	</script>

</body>

</html>