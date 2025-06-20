<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CCDP : inscription</title>
</head>
<body>
	<h1>Inscription :</h1>
	<form action="${pageContext.request.contextPath }/signup" method="post" class="addForm">
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
			<button>Ajouter</button>
		</div>
	</form>
</body>
</html>