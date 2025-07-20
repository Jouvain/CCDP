<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>    
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css">
<meta charset="UTF-8">
<title>CCDP : compte</title>
</head>
<body>
	<jsp:include page="header.jsp" />
	<main class="mainExemples">
		<h1> Votre compte personnel </h1>
		<div class="cardsGallery">
			<ul>
				<li>Nom : ${user.lastname }</li>
				<li>Prenom : ${user.firstname }</li>
				<li>Pseudo : ${user.username }</li>
			</ul>
			<a href="${pageContext.request.contextPath }/editCompte" ><button class="btn btn--small btn--farAway">Modifier vos informations</button></a>
			<a href="${pageContext.request.contextPath }/deleteUser?userId=${user.id}" ><button class="btn btn--small btn--farAway">Supprimer votre compte</button></a>	
		</div>

	</main>


</body>
</html>