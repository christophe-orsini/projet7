<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<%@ include file="../theme/head.jsp" %>
<body class="container">
<%@ include file="../theme/header.jsp" %>
<%@ include file="../theme/menu.jsp" %>
	<section class="row justify-content-center">	
		<div class="col-12 border">
			<form:form action="/abonne/rechercheOuvrages" method="post" class="form-row form-inline"
				modelAttribute="ouvrageCherche">
				"anneeEdition": 0,
  "auteur": "string",
  "nbreExemplaire": 0,
  "theme": "string",
  "titre": "string"				
				<form:input path="titre" class="form-control form-control-sm col-12" placeholder="Titre" />						
				<form:input path="auteur" class="form-control form-control-sm col-12 col-md-4" placeholder="Auteur" />
				<form:input path="theme" class="form-control form-control-sm col-12 col-sm-6 col-md-4" 
					placeholder="Theme" />
				<form:input path="nbreExemplaire" class="form-control form-control-sm col-12 col-sm-6 col-md-4" 
					placeholder="Nbre exemplaires" />
				<input class="btn btn-primary btn-sm ml-auto" type="submit" name="submit" value="Rechercher" />																		
			</form:form>	
		</div>
		<div class="col-12">
			<p class="h3">Liste des ouvrages</p>
		</div>
		<div class="col-12 table-responsive">
			<table class="table table-sm table-striped table-bordered">
				<caption>Cliquer sur le titre pour voir les détails</caption>
				<thead>
					<tr>
						<th>Titre</th>
						<th>Publié en</th>
						<th>Auteur</th>
						<th>Thème</th>
						<th>Disponible</th>
					</tr>
				</thead>	
				<tbody>
					<c:forEach items="${ouvrages}" var="ouvrage" varStatus="status">
					<tr>
						<td><a href="/abonne/detailOuvrage/${ouvrage.id}">${ouvrage.titre}</a></td>
						<td><fmt:formatDate value="${ ouvrage.anneeEdition }" type="date" /></td>
						<td>${ouvrage.auteur}</td>
						<td>${ouvrage.theme.nom}</td>
						<td><c:if test="${ ouvrage.nbreExemplaires > 0 }">Oui</c:if></td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</section>
<%@ include file="../theme/footer.jsp" %>
</body>
</html>