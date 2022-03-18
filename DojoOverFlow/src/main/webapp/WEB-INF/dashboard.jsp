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
	<title>Question Dashboard</title>
	<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
	<script src="/webjars/jquery/jquery.min.js"></script>
	<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		<div class="row mt-5">
			<div class="col-6">
				<h1>Question Dashboard</h1>
			</div>
			<div class="col-6">
				<a href="/questions/new" class="btn btn-primary">New Question</a>
			</div>
		</div>
		<div class="row mt-3">
			<div class="col-12 p-5">
				<table class="table table-striped">
				  <thead>
				    <tr>
				      <th scope="col">Question</th>
				      <th scope="col">Tags</th>
				    </tr>
				  </thead>
				  <tbody>
				  	<c:forEach var="question" items="${questions}">
				  		<tr>
					      <td>
					      	<a href="/questions/${question.id }">${question.question}</a>
					      </td>
					      <td>
						      <c:forEach var="tag" items="${question.tags}">
						      ${ tag.subject }
						      </c:forEach>
					      </td>
					    </tr>
				  	</c:forEach>
				  </tbody>
				</table>
			</div>
		</div>
	</div>
</body>
</html>