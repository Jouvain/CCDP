<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" >
<link href="https://fonts.googleapis.com/css2?family=Lemon&display=swap" rel="stylesheet">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" >
<link href="https://fonts.googleapis.com/css2?family=Averia+Gruesa+Libre&display=swap" rel="stylesheet">
<title>Authentification</title>
</head>
<body>
	<h1 class="lemon-regular loneTitle">Page d'authentification</h1>
	<form action="${pageContext.request.contextPath }/login" method="post" class="oneLinerForm">
		<div>
			Pseudo d'utilisateur : <input type="text" name="username">
		</div>
		<div>
			MDP : <input type="text" name="password">
		</div>
		<div>
			<button class="btn">Se connecter</button>
		</div>
	</form>
</body>
</html>