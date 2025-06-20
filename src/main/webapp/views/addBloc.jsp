<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CCDP : création de Bloc</title>
</head>
<body>
	<h1>Création d'un Bloc d'activité :</h1>
	<form action="${pageContext.request.contextPath }/addBloc" method="post" class="addForm">
		<div>
			Intitulé : <input type="text" name="title" required>
		</div>
		<div>
			<button>Ajouter</button>
		</div>
	</form>
</body>
</html>