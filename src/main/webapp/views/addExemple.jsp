<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CCDP : création d'Exemple</title>
</head>
<body>
	<h1>Création du dossier professionnel :</h1>
	<form action="${pageContext.request.contextPath }/addExemple" method="post" class="addForm">
		<div>
			Intitulé : <input type="text" name="title" required>
		</div>
		<div>
			<button>Ajouter</button>
		</div>
	</form>
</body>
</html>