<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CCDP : intégration d'exemple</title>
</head>
<body>
	<c:choose>
		<c:when test="${hasExemples }">
			<h1>Liste des exemples intégrables :</h1>
			<c:forEach var="ex" items="${user.exemples }">
				<c:if test="${ex.bloc == null || ex.bloc.id != bloc.id}">
					<p> ${ex.title }</p>
					<form action="${pageContext.request.contextPath }/integrateExemple" method="post" class="addForm">
						<input type="hidden" name="exempleId" value="${ex.id}" />
						<input type="hidden" name="blocId" value="${bloc.id}" />  
						<div>
							<label>${ex.title }</label>
							<button>Ajouter</button>
						</div>
					</form>
				</c:if>

			</c:forEach>
		</c:when>
		<c:otherwise>
			<h1> Aucun exemple intégrable...</h1>
			<p> Il vous faut d'abord créer <a href="${pageContext.request.contextPath}/exemples">vos exemples de pratique</a> ! </p>
		</c:otherwise>
	</c:choose>

</body>
</html>