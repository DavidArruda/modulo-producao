<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>

<meta charset="ISO-8859-1">
<title>Cadastro Ficha de Preparação</title>
<script src="resources/javascript/jquery.min.js" type="text/javascript"></script>

<link rel="stylesheet" href="resources/css/cadastro.css">

</head>

<body>
	<a href="acessoliberado.jsp"><img alt="Inicio" title="Início"
		src="resources/img/home.jpeg" height="48px" width="48px"></a>
	<a href="index.jsp"><img alt="Sair" title="Sair"
		src="resources/img/sair.png" height="48px" width="48px"></a>
	<center>
		<h1>Cadastro de Ficha de Preparação</h1>
		<h3 style="color: red;">${msg}</h3>
	</center>

	<form action="salvarFicha" method="post" id="formUser"
		onsubmit="return validarCampos() ? true : false;"
		enctype="multipart/form-data">
		<ul class="form-style-1">
			<li>
				<table>
					<tr>
						<td>Código:</td>
						<td><input type="text" readonly="readonly" id="codFicha" name="codFicha"
							value="${fichaPreparacao.codFicha}" class="field-long"></td>
					</tr>

					<tr>		
						<td>Ficha de Preparação:</td>
						<td><input type="file" name="fichaPreparacao" value="fichaPreparacao"></td>
					</tr>
					
					<tr>
						<td></td>
						<td><input type="submit" value="Salvar" style="width: 95px;">
							<input type="submit" value="Cancelar" style="width: 95px;"
							onclick="document.getElementById('formUser').action ='salvarFicha?acao=reset'"></td>
					</tr>
				</table>

			</li>
		</ul>
	</form>

	<div class="container">
		<table class="responsive-table">
			<caption>Lista de Fichas de Preparação</caption>
			<tr>
				<th>Código</th>
				<th>Ficha</th>
				<th>Excluir</th>
				<th>Atualizar</th>
			</tr>
			
			<c:forEach items="${fichas}" var="fichaPreparacao">
				<tr>
					<td style="width: 150px"><c:out value="${fichaPreparacao.codFicha}">
						</c:out></td>
						
					<c:if test="${fichaPreparacao.fBase64.isEmpty() == false}">=		
					<td><a
							href="salvarFicha?acao=download&fichaPreparacao=${fichaPreparacao.codFicha}"><img
								alt="FichaPreparacao" src="resources/img/fotoPDF.png" width="32px"
								height="32px"></a></td>
					</c:if>

					<c:if test="${fichaPreparacao.fBase64 == null}">
						<td><img alt="FichaPreparacao" src="resources/img/pdfvazio.png"
							width="32px" height="32px"
							onclick="alert('Não possui curriculo')"></td>
					</c:if>
					
					<td><a
						href="salvarFicha?acao=delete&fichaPreparacao=${fichaPreparacao.codFicha}"
						onclick="return confirm('Confirmar a exclusão?');"><img
							src="resources/img/excluir.png" alt="excluir" title="Excluir"
							width="20px" height="20px"> </a></td>

					<td><a
						href="salvarFicha?acao=editar&fichaPreparacao=${fichaPreparacao.codFicha}"><img
							alt="Editar" title="Editar" src="resources/img/editar.png"
							width="20px" height="20px"></a></td>
					
				</tr>
			</c:forEach>
		</table>
	</div>

	<script type="text/javascript">
		function validarCampos() {

			if (document.getElementById("pn").value == '') {
				alert('Informe o PN');
				return false;
			} else if (document.getElementById("cliente").value == '') {
				alert('Informe o cliente');
				return false;
			} else if (document.getElementById("descricao").value == '') {
				alert('Informe a descrição');
				return false;
			}

			return true;

		}
	</script>

</body>

</html>