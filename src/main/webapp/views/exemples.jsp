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
	<jsp:include page="header.jsp" />
	<h1>Bonjour ${user.firstname } !</h1>
	<c:choose>
		<c:when test="${hasExemple }">
			<p>La liste de vos exemples de pratique professionnelle :</p>
			<c:forEach var="ex" items="${user.exemples }">
				<h2>Intitulé : ${ex.title }</h2>
				<c:forEach var="cp" items="${ex.competences }" >
					<h3>CP : ${cp.title } </h3>
				</c:forEach>
				<a href="${pageContext.request.contextPath }/integrateCp?exempleId=${ex.id}" ><button>Ajouter une CP</button></a>
				<a href="${pageContext.request.contextPath }/deleteExemple?exempleId=${ex.id}" ><button>Effacer l'exemple ${ex.title }</button></a>
			</c:forEach>
			<a href="${pageContext.request.contextPath }/addExemple" ><button>Ajouter un exemple</button></a>
		</c:when>
		<c:otherwise>
			<p>Vous n'avez pas encore d'exemple !</p>
			<a href="${pageContext.request.contextPath }/addExemple" ><button>Ajouter un exemple</button></a>
		</c:otherwise>
	</c:choose>


</body>
</html>