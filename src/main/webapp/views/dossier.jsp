<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
				<h2>Votre dossier : ${dossier.title}</h2>
				<a href="${pageContext.request.contextPath }/deleteDossier?dossierId=${dossier.id}"><button>Effacer le DP</button></a>
				<a href="${pageContext.request.contextPath }/addBloc"><button>Ajouter un bloc</button></a>
				<c:forEach var="bloc" items="${dossier.blocs}">
					<h3>Bloc : ${bloc.title}</h3>
					<a href="${pageContext.request.contextPath }/deleteBloc?blocId=${bloc.id}"><button>Effacer le ${bloc.title} </button></a>
					<a href="${pageContext.request.contextPath }/addCp?blocId=${bloc.id}"><button>Ajouter une compétence</button></a>
					<c:if test="${fn:length(bloc.exemples) < 3}">
						<a href="${pageContext.request.contextPath }/integrateExemple?blocId=${bloc.id}"><button>Intégrer un exemple de pratique</button></a>
					</c:if>

					<ul>
						<c:forEach var="ex" items="${bloc.exemples }" >
							<li>${ex.title }</li>
							<a href="${pageContext.request.contextPath }/removeExFromBloc?exempleId=${ex.id}&blocId=${bloc.id}" ><button>Enlever ${ex.title }</button></a>
						</c:forEach>
					</ul>
					<ul>
						<c:forEach var="cp" items="${bloc.competences}" >
							<li>${cp.title}</li>
							<p>${cpCountMap[cp.id]}</p>
							<c:if test="${cpCountMap[cp.id] >= 1}">
							    <p>Cette compétence est validée !</p>
							</c:if>
							<a href="${pageContext.request.contextPath }/deleteCp?cpId=${cp.id}&blocId=${bloc.id}" ><button>Effacer ${cp.title }</button></a>
						</c:forEach>
					</ul>
				</c:forEach>
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