<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CCDP - Accueil</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css">
</head>

<body>
	<jsp:include page="header.jsp" />

	<main class="homeCenter">
		<c:choose>
			<c:when test="${logged}">
				<h1>Bienvenue, ${user.firstname}</h1>
			</c:when>
			<c:otherwise>
				<h1>Bienvenue de la part du Compagnon de Complétion du Dossier
					Professionnel !</h1>
				<img src="${pageContext.request.contextPath}/images/robot-face.svg"
					alt="Compagnon CCDP" class="homePortrait" />
				<div>
					<p>Avec CCDP :</p>
					<ul>
						<Li>Visualisez d'un coup d'oeil où en est votre dossier !</Li>
						<Li>Ne manquez aucune CP, et comptez vos combbos !</Li>
						<Li>Ajoutez, supprimez et affectez facilement vos exemples !</Li>
					</ul>
				</div>

				<a href="${pageContext.request.contextPath }/signup"><button class="btn">S'inscrire</button></a>
			</c:otherwise>
		</c:choose>

	</main>

</body>
</html>