<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CCDP : dossier</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
	<jsp:include page="header.jsp" />
	<main class="mainExemples">
		<h1>Bonjour, ${user.firstname} !</h1>
		<c:choose>
			<c:when test="${hasDossier }">
				<h2>Votre dossier : ${dossier.title}</h2>
				<div class="dossierControls">
					<a href="${pageContext.request.contextPath }/deleteDossier?dossierId=${dossier.id}"><button class="btn">Effacer le DP</button></a>
					<a href="${pageContext.request.contextPath }/addBloc"><button class="btn">Ajouter un bloc</button></a>
				</div>
				<div class="cardsGallery">
					<c:forEach var="bloc" items="${dossier.blocs}">
						<div class="exempleCard">
							<h3>Bloc : ${bloc.title}</h3>
							<a href="${pageContext.request.contextPath }/deleteBloc?blocId=${bloc.id}"><button class="btn--light">Effacer le ${bloc.title} </button></a>
							<a href="${pageContext.request.contextPath }/addCp?blocId=${bloc.id}"><button class="btn--light">Ajouter une compétence</button></a>
							<c:if test="${fn:length(bloc.exemples) < 3}">
								<a href="${pageContext.request.contextPath }/integrateExemple?blocId=${bloc.id}"><button class="btn--light">Intégrer un exemple de pratique</button></a>
							</c:if>
							

							<div class="cpGallery">
							<c:forEach var="cp" items="${bloc.competences}" >
								<div class="cpInBloc">
									<div class="cpInBlocInfos">
										<p> CP : ${cp.title} </p>
										<div class="cpScoring">
										    <c:forEach var="i" begin="1" end="3">
										        <c:choose>
										            <c:when test="${i <= cpCountMap[cp.id]}">
										                <span class="dot dot--filled"></span>
										            </c:when>
										            <c:otherwise>
										                <span class="dot dot--empty"></span>
										            </c:otherwise>
										        </c:choose>
										    </c:forEach>
										</div>
									</div>
									<a href="${pageContext.request.contextPath }/deleteCp?cpId=${cp.id}&blocId=${bloc.id}" ><button class="btn--light btn--small">Effacer ${cp.title }</button></a>								
								</div>
							</c:forEach>
							</div>
							
							<div class="exemplesGallery">
							<c:forEach var="ex" items="${bloc.exemples }" >
								<div class="exempleInBloc">
									<p>${ex.title }</p>
									<a href="${pageContext.request.contextPath }/removeExFromBloc?exempleId=${ex.id}&blocId=${bloc.id}" ><button class="btn btn--small">Enlever ${ex.title }</button></a>								
								</div>
							</c:forEach>
							</div>
								
						</div>

					</c:forEach>				
				</div>
				

			</c:when>
			<c:otherwise>
				<h2>Pas encore de dossier ?</h2>
				<a href="${pageContext.request.contextPath }/addDossier"><button>Créer
						mon dossier</button></a>
			</c:otherwise>
		</c:choose>
	</main>



</body>
</html>