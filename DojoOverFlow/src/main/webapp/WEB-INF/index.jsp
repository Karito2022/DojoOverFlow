<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- c:out ; c:forEach etc. --> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Formato (fechas) --> 
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- para errores de renderizado en rutas PUT -->
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>New Product</title>
	<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
	<script src="/webjars/jquery/jquery.min.js"></script>
	<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		<div class="row mt-5">
			<div class="col-12">
				<h1>What's your question?</h1>
			</div>
		</div>
		<div class="row mt-3">
			<div class="col-6 p-5">
				<h2>New product</h2>
				<form:form action="/questions/save" method="post" class="mt-4">
					<c:if test="${error_vacios != null}">
						<div class="col-12">
							<p class="text-danger">${error_vacios}</p>
						</div>
					</c:if>
					<div class="mb-4 row">
						<div class="col-6">
							<h5>Question:</h5>
						</div>
						<div class="col-6">
							<textarea name="question" class="form-control"></textarea>
						</div>
					</div>
					<div class="mb-4 row">
						<p class="text-muted">Ingresar 3 tags separados por coma</p>
						<div class="col-6">
							<h5>Tags:</h5>
						</div>
						<div class="col-6">
							<input name="tags" class="form-control"/>
						</div>
						<c:if test="${error_cantidad_tag != null}">
							<div class="col-12">
								<p class="text-danger">${error_cantidad_tag}</p>
							</div>
						</c:if>
					</div>
					<div class="d-flex flex-row-reverse">
					  <input type="submit" value="Submit" class="btn btn-primary btn-lg"/>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</body>
</html>