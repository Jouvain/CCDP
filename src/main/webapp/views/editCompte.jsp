<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CCDP : modification du compte</title>
</head>
<body>
	<h1>Modification de vos informations :</h1>
	<form action="${pageContext.request.contextPath }/editCompte?userId=${user.id}" method="post" class="addForm">
		<div>
			Nom : <input type="text" name="lastname" required>
		</div>
		<div>
			Prénom : <input type="text" name="firstname" required>
		</div>
		<div>
			MDP : <input type="text" name="password" required>
		</div>
		<div>
			<button>Éditer</button>
		</div>
	</form>
</body>
</html>