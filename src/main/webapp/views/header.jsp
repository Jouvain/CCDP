<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

<header>
	<p>CCDP</p>
	<nav>
		<ul>
			<c:choose>
				<c:when test="${not empty pageContext.request.userPrincipal}">
					<!-- Utilisateur connecté -->
					<li><a href="${pageContext.request.contextPath}/dossier">Dossier</a></li>
					<li><a href="${pageContext.request.contextPath}/exemples">Exemples</a></li>
					<li><a href="${pageContext.request.contextPath}/compte">Compte</a></li>
					<li><a href="${pageContext.request.contextPath}/logout"><button>Déconnexion</button></a></li>
				</c:when>
				<c:otherwise>
					<!-- Non connecté -->
					<li><a href="${pageContext.request.contextPath}/login"><button>Connexion</button></a></li>
				</c:otherwise>
			</c:choose>
		</ul>

	</nav>
</header>

