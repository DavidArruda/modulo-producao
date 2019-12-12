<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>

<meta charset="ISO-8859-1">
<title>Cadastro de Ordem de Servi�o</title>

<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<link rel="stylesheet" href="resources/css/cadastro.css">

</head>

<body>
	<a href="acessoliberado.jsp"><img alt="Inicio" title="In�cio"
		src="resources/img/home.jpeg" height="48px" width="48px"></a>
	<a href="index.jsp"><img alt="Sair" title="Sair"
		src="resources/img/sair.png" height="48px" width="48px"></a>
	<center>
		<h1>Cadastro de Ordem de Servi�o</h1>
		<h3 style="color: red;">${msg}</h3>
	</center>

	<form action="salvarOrdemServico" method="post" id="formUser"
		onsubmit="return validarCampos() ? true : false;">

		<ul class="form-style-1">
			<li>
				<table>
					<tr>
						<td><label>C�digo:</label></td>
						<td><input type="text" readonly="readonly" id="codApontamento" name="codApontamenot"
							value="${ap.codApontamento}" class="field-long" style="width: 193px;"> </td>
					</tr>
		
					<tr>
						<td><label>Inicio:</label></td>
						<td><input id="inicio" name="inicio" value="${ap.inicio}" style="width: 189px;"></td>
					
					</tr>
					
					<tr>
						<td><label>Termino:</label></td>
						<td><input id="termino" name="termino" value="${ap.termino}" style="width: 189px;"></td>
					</tr>

					<tr>
						<td></td>
						<td><input type="submit" value="Salvar" style="width: 95px;">
							<input type="submit" value="Cancelar" style="width: 95px;"
							onclick="document.getElementById('formUser').action ='salvarOrdemServico?acao=reset'"></td>
					</tr>
				</table>

			</li>
		</ul>
	</form>

	<div class="container">
		<table class="responsive-table">
			<caption>Lista de Ordens de Servi�o</caption>
			<tr>
				<th>C�digo</th>
				<th>Data Emiss�o</th>
				<th>Data Entrega</th>
				<th>Quantidade</th>
				<th>Status</th>
				<th>Produto</th>
				<th>Deletar</th>
				<th>Atualizar</th>
				
			</tr>
			<c:forEach items="${ordens}" var="os">
				<tr>
					<td style="width: 150px"><c:out value="${os.codOs}">
						</c:out></td>

					<td style="width: 150px"><c:out value="${os.dateEmissao}">
						</c:out></td>
						
					<td style="width: 150px"><c:out value="${os.dataEntrega}">
						</c:out></td>
						
					<td style="width: 150px"><c:out value="${os.quantidade}">
						</c:out></td>
					
					<td style="width: 150px"><c:out value="${os.status}">
						</c:out></td>	
						
					<td style="width: 150px"><c:out value="${os.produto}">
						</c:out></td>			

					<td><a
						href="salvarOrdemServico?acao=delete&os=${os.codOs}"
						onclick="return confirm('Confirmar a exclus�o?');"><img
							src="resources/img/excluir.png" alt="excluir" title="Excluir"
							width="20px" height="20px"> </a></td>

					<td><a
						href="salvarOrdemServico?acao=editar&os=${os.codOs}"><img
							alt="Editar" title="Editar" src="resources/img/editar.png"
							width="20px" height="20px"></a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
	
	<script type="text/javascript">
		$(function(){
			$('#dateEmissao').datepicker({dateFormat: 'dd/mm/yy'});
		});
		
		$(function(){
			$('#dataEntrega').datepicker({dateFormat: 'dd/mm/yy'});
		});

	</script>

	<script type="text/javascript">
		function validarCampos() {

			if (document.getElementById("dateEmissao").value == '') {
				alert('Informe a Data de Emiss�o');
				return false;
			} else if (document.getElementById("dataEntrega").value == '') {
				alert('Informe a data de entrega');
				return false;
			} else if (document.getElementById("quantidade").value == '') {
				alert('Informe a quantidade');
				return false;
			} else if (document.getElementById("status").value == '') {
				alert('Informe o status');
				return false;
			}else if (document.getElementById("produto").value == '') {
				alert('Informe o produto');
				return false;
			}
			
			return true;

		}
	
	</script>

</body>

</html>