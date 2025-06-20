<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CCDP : création de CP</title>
</head>
<body>
	<h1>Création d'une compétence professionnelle :</h1>
	<form action="${pageContext.request.contextPath }/addCp" method="post" class="addForm">
		<div>
			<input type="hidden" name="blocId" value="${bloc.id}" />
			Intitulé : <input type="text" name="title" required>
		</div>
		<div>
			<button>Ajouter</button>
		</div>
	</form>
</body>
</html>