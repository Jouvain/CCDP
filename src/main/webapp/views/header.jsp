<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" >
	<link href="https://fonts.googleapis.com/css2?family=Lemon&display=swap" rel="stylesheet">
	<link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" >
	<link href="https://fonts.googleapis.com/css2?family=Averia+Gruesa+Libre&display=swap" rel="stylesheet">
</head>

<header>
	<p class="lemon-regular">CCDP</p>
	<nav>
		<ul>
			<c:choose>
				<c:when test="${not empty pageContext.request.userPrincipal}">
					<!-- Utilisateur connecté -->
					<li><a href="${pageContext.request.contextPath}/dossier">Dossier</a></li>
					<li><a href="${pageContext.request.contextPath}/exemples">Exemples</a></li>
					<li><a href="${pageContext.request.contextPath}/compte">Compte</a></li>
					<li><a href="${pageContext.request.contextPath}/logout"><button class="btn--light">Déconnexion</button></a></li>
				</c:when>
				<c:otherwise>
					<!-- Non connecté -->
					<li><a href="${pageContext.request.contextPath}/login"><button class="btn--light">Connexion</button></a></li>
				</c:otherwise>
			</c:choose>
		</ul>

	</nav>
</header>

