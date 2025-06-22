<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CCDP : Exemples</title>
</head>
<body>
	<h1>Bonjour ${user.firstname } !</h1>
	<p>La liste de vos exemples de pratique professionnelle :</p>
	<c:forEach var="ex" items="${user.exemples }">
		<h2>Intitulé : ${ex.title }</h2>
		<c:forEach var="cp" items="${ex.competences }" >
			<h3>CP : ${cp.title } </h3>
		</c:forEach>
		<a><button>Ajouter une CP</button></a>
	</c:forEach>

</body>
</html>