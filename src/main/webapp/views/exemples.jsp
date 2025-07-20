<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CCDP : Exemples</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
	<jsp:include page="header.jsp" />
	<main class="mainExemples">

		<h1>Bonjour ${user.firstname } !</h1>
		<div class="cardsGallery">
			<c:if test="${user.dossier == null }">
				<p>Vous n'avez pas encore de dossier, pensez à en faire un !</p>
			</c:if>
			<c:choose>
				<c:when test="${hasExemple }">
					<p>La liste de vos exemples de pratique professionnelle :</p>
					<c:forEach var="ex" items="${user.exemples }">
						<div class="exempleCard">
							<h2>Intitulé : ${ex.title }</h2>
							<c:forEach var="cp" items="${ex.competences }">
								<div class="cpInExemple">
								
								<form
									action="${pageContext.request.contextPath }/removeCpFromEx"
									method="post" class="addForm">
									<input type="hidden" name="cpId" value="${cp.id}" /> <input
										type="hidden" name="exempleId" value="${ex.id}" />
									<div>
										<label>${cp.title }</label>
										<button class="btn--light">Enlever</button>
									</div>
								</form>
								</div>

							</c:forEach>
							<a
								href="${pageContext.request.contextPath }/integrateCp?exempleId=${ex.id}"><button class="btn--light">Ajouter
									une CP</button></a> <a
								href="${pageContext.request.contextPath }/deleteExemple?exempleId=${ex.id}"><button class="btn--light">Effacer
									l'exemple ${ex.title }</button></a>
						</div>

					</c:forEach>
					<a href="${pageContext.request.contextPath }/addExemple"><button class="btn btn--farAway">Ajouter
							un exemple</button></a>
				</c:when>
				<c:otherwise>
					<p>Vous n'avez pas encore d'exemple !</p>
					<a href="${pageContext.request.contextPath }/addExemple"><button class="btn btn--farAway">Ajouter
							un exemple</button></a>
				</c:otherwise>
			</c:choose>
		</div>

	</main>



</body>
</html>