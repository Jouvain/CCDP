<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" >
<link href="https://fonts.googleapis.com/css2?family=Lemon&display=swap" rel="stylesheet">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" >
<link href="https://fonts.googleapis.com/css2?family=Averia+Gruesa+Libre&display=swap" rel="stylesheet">
<meta charset="UTF-8">
<title>CCDP : inscription</title>
</head>
<body>
	<h1 class="lemon-regular loneTitle">Inscription :</h1>
	<form action="${pageContext.request.contextPath }/signup" method="post" class="oneLinerForm">
		<div>
			Nom : <input type="text" name="lastname" required>
		</div>
		<div>
			Prénom : <input type="text" name="firstname" required>
		</div>
		<div>
			Pseudo : <input type="text" name="username" required>
		</div>
		<div>
			MDP : <input type="text" name="password" required>
		</div>
		<div>
			<button class="btn">Ajouter</button>
		</div>
	</form>
</body>
</html>