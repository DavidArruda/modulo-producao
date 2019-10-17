<%@page import="beans.BeanUsuario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>

<meta charset="ISO-8859-1">
<title>Cadastro de Usuário</title>
<link rel="stylesheet" href="resources/css/cadastro.css">


<script src="https://code.jquery.com/jquery-3.4.1.min.js"
	integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
	crossorigin="anonymous"></script>

</head>

<body>
	<a href="acessoliberado.jsp"><img alt="Inicio" title="Início"
		src="resources/img/home.jpeg" height="48px" width="48px"></a>
	<a href="index.jsp"><img alt="Sair" title="Sair"
		src="resources/img/sair.png" height="48px" width="48px"></a>
	<center>
		<h1>Cadastro de usuário</h1>
		<h3 style="color: red;">${msg}</h3>
	</center>

	<form action="salvarUsuario" method="post" id="formUser"
		onsubmit="return validarCampos() ? true : false;"
		enctype="multipart/form-data">
		<ul class="form-style-1">
			<li>
				<table>
					<tr>
						<td>Código:</td>
						<td><input type="text" readonly="readonly" id="id" name="id"
							value="${user.id}" style="width: 193px;" class="field-long"></td>
					</tr>
					
					<tr>
						<td>Nome:</td>
						<td><input type="text" id="nome" name="nome" maxlength="50"
							placeholder="Informe o nome do usuário" value="${user.nome}"></td>
					</tr>
					
					<tr>
						<td>Login:</td>
						<td><input type="text" id="login" name="login" maxlength="10"
							placeholder="Informe um login" value="${user.login}"></td>
					</tr>

					<tr>
						<td>Senha:</td>
						<td><input type="password" id="senha" name="senha"
							maxlength="10" placeholder="Informe uma senha"
							value="${user.senha}"></td>
					</tr>

					<tr>
						<td>Cargo:</td>
						<td>
							<select id="tipo_usuario" name="tipo_usuario">
								<c:forEach items="${tipo_usuarios}" var="tipo">
									<option value="${tipo.codTipo}" id="${tipo.codTipo}"
										<c:if test="${tipo.codTipo == produto.tipo_usuario}">
										<c:out value="selected=selected" />
										</c:if>>
									${tipo.descricao}
									</option>
								</c:forEach>
							
							</select>
						</td>
					</tr>

					<tr>
						<td></td>
						<td><input type="submit" value="Salvar"> <input
							type="submit" value="Cancelar"
							onclick="document.getElementById('formUser').action ='salvarUsuario?acao=reset'"></td>
					</tr>
				</table>

			</li>
		</ul>
	</form>
	
	<form method="post" action="servletPesquisa" style="width: 90%">
	<ul class="form-style-1">
			<li>
				<table>
					<tr>
						<td>Descrição </td>
						<td><input type="text" id="descricaoConsulta" name="descricaoConsulta"> </td>
						<td><input type="submit" value="Pesquisar"> </td>
					</tr>

				</table> 
			</li> 
	</ul>
	</form>				


	<div class="container">
		<table class="responsive-table">
			<caption>Lista de Usuários</caption>
			<tr>
				<th>Id</th>
				<th>Nome</th>
				<th>login</th>
				<th>Senha</th>
				<th>Cargo</th>
				<th>Delete</th>
				<th>Editar</th>

			</tr>
			<c:forEach items="${usuarios}" var="user">
				<tr>
					<td style="width: 150px"><c:out value="${user.codUsuario}"></c:out></td>
					<td><c:out value="${user.nome}"></c:out></td>

					<td style="width: 150px"><c:out value="${user.codUsuario}"></c:out></td>
					<td><c:out value="${user.login}"></c:out></td>
					
					<td style="width: 150px"><c:out value="${user.codUsuario}"></c:out></td>
					<td><c:out value="${user.senha}"></c:out></td>
					
					<td style="width: 150px"><c:out value="${user.codUsuario}"></c:out></td>
					<td><c:out value="${user.tipo_usuario}"></c:out></td>

					<td><a href="salvarUsuario?acao=delete&user=${user.codUsuario}"
						onclick="return confirm('Confirmar a exclusão?');"><img
							src="resources/img/excluir.png" alt="excluir" title="Excluir"
							width="20px" height="20px"> </a></td>

					<td><a href="salvarUsuario?acao=editar&user=${user.codUsuario}"><img
							alt="Editar" title="Editar" src="resources/img/editar.png"
							width="20px" height="20px"></a></td>

				</tr>
			</c:forEach>
		</table>
	</div>

	<script type="text/javascript">
		function validarCampos() {
			
			if (document.getElementById("nome").value == '') {
				alert('Informe o nome');
				return false;
			} else if (document.getElementById("login").value == '') {
				alert('Informe o login');
				return false;
			} else if (document.getElementById("senha").value == '') {
				alert('Informe a senha');
				return false;
			} else if (document.getElementById("tipo_usuario").value == '') {
				alert('Informe o cargo');
				return false;
			}

			return true;
		}
	</script>

</body>

</html>