<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CCDP : ajout d'une compétence</title>
</head>
<body>
	<h1>Liste des compétences intégrables :</h1>
	
	  
	<c:forEach var="cp" items="${competences }" >
		<p> ${cp.title }</p>
		<form action="${pageContext.request.contextPath }/integrateCp" method="post" class="addForm">
			<input type="hidden" name="cpId" value="${cp.id}" />
			
			<input type="hidden" name="exempleId" value="${exemple.id}" />  
			<div>
				<label>${cp.title }</label>
				<button>Ajouter</button>
			</div>
		</form>
	</c:forEach>
	
</body>
</html>