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
	<title>Question ${ question.id }</title>
	<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
	<script src="/webjars/jquery/jquery.min.js"></script>
	<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		<div class="row mt-5">
			<div class="col-12">
				<h1>${ question.question }</h1>
			</div>
		</div>
		<div class="row mt-5">
			<h2>Tags:</h2>
			<div>
				<c:forEach var="tag" items="${question.tags}">
					<button class="btn btn-secondary m-1"><c:out value="${tag.subject}"/></button>
				</c:forEach>
			</div>
		</div>
		<div class="row mt-3">
			<div class="col-6">
				<table class="table table-striped mt-5">
				  <thead>
				    <tr>
				      <th scope="col">Anwers</th>
				    </tr>
				  </thead>
				  <tbody>
				  	<c:forEach var="answer" items="${question.answers}">
						<tr>
					      <td>${answer.answer}</td>
					    </tr>
					</c:forEach>	
				  </tbody>
				</table>
			</div>
			<div class="col-6">
				<h2>Add your answer:</h2>
				<form action="/questions/sendanswer" method="POST">
					<div class="form-group row mt-5">
					  <label for="textareaid" class="col-sm-3">Answer:</label>
					  <div class="col-sm-9">
					  	<textarea class="form-control" id="textareaid" name="answer" rows="5"></textarea>
					  	<input type="hidden" name="id" value="${question.id}"/>
					  </div>
					</div>
					<div class="d-flex flex-row-reverse">
					  <button type="submit" class="btn btn-primary mt-4">Answer it!</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>