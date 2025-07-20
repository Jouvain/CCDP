<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" >
<link href="https://fonts.googleapis.com/css2?family=Lemon&display=swap" rel="stylesheet">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" >
<link href="https://fonts.googleapis.com/css2?family=Averia+Gruesa+Libre&display=swap" rel="stylesheet">
<meta charset="UTF-8">
<title>CCDP : ajout d'une compétence</title>
</head>
<body>
	<h1 class="lemon-regular loneTitle">Liste des compétences intégrables :</h1>
	
	  
	<c:forEach var="cp" items="${competences }" >
		
		<form action="${pageContext.request.contextPath }/integrateCp" method="post" class="oneLinerForm">
			<input type="hidden" name="cpId" value="${cp.id}" />
			
			<input type="hidden" name="exempleId" value="${exemple.id}" />  
			<div>
				<label>${cp.title }</label>
				<button class="btn btn--small">Ajouter</button>
			</div>
		</form>
	</c:forEach>
	
</body>
</html>