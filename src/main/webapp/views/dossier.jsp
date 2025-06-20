<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CCDP : dossier</title>
</head>
<body>
	<jsp:include page="header.jsp" />
	<main>
		<h1>Bonjour, ${user.firstname} !</h1>
		<c:choose>
			<c:when test="${hasDossier }">
				<h2>Votre dossier : </h2>
			</c:when>
			<c:otherwise>
				<h2> Pas encore de dossier ?</h2>
			</c:otherwise>
		</c:choose>
	</main>
	
	

</body>
</html>