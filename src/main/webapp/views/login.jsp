<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<title>Authentification</title>
</head>
<body>
	<h1>Page d'authentification</h1>
	<form action="${pageContext.request.contextPath }/login" method="post">
		<div>
			Pseudo d'utilisateur : <input type="text" name="username">
		</div>
		<div>
			MDP : <input type="text" name="password">
		</div>
		<div>
			<button>Se connecter</button>
		</div>
	</form>
</body>
</html>