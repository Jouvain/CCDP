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
				<h2>Votre dossier : ${dossier.title}</h2>
				<a href="${pageContext.request.contextPath }/deleteDossier?dossierId=${dossier.id}"><button>Effacer le DP</button></a>
				<a href="${pageContext.request.contextPath }/addBloc"><button>Ajouter un bloc</button></a>
				<c:forEach var="bloc" items="${dossier.blocs}">
					<h3>Bloc : ${bloc.title}</h3>
					<a href="${pageContext.request.contextPath }/deleteBloc?blocId=${bloc.id}"><button>Effacer le ${bloc.title} </button></a>
					<a href="${pageContext.request.contextPath }/addCp?blocId=${bloc.id}"><button>Ajouter une compétence</button></a>
					<ul>
						<c:forEach var="cp" items="${bloc.competences}">
							<li>${cp.title}</li>
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