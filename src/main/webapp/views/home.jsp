<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>CCDP - Accueil</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
	</head>
	
	<body>
		<jsp:include page="header.jsp" />
	
		<main>
			<h1>Bienvenue de la part du Compagnon de Complétion du Dossier Professionnel !</h1>
			<img src="${pageContext.request.contextPath}/images/robot-face.svg"  alt="Compagnon CCDP"/>
			<p>Avec CCDP :</p>
			<ul>
				<Li>Visualisez d'un coup d'oeil où en est votre dossier !</Li>
				<Li>Ne manquez aucune CP, et comptez vos combbos !</Li>
				<Li>Ajoutez, supprimez et affectez facilement vos exemples !</Li>				
			</ul>
			<a href="#"><button>S'inscrire</button></a>
		</main>
	
	</body>
</html>