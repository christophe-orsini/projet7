<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<nav class="navbar navbar-expand-lg navbar-light bg-light row">
	<a class="navbar-brand" href="/">Bibliothèques de Bouquinville</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#menu">
		<span class="navbar-toggler-icon"></span>
	</button>
	<div class="collapse navbar-collapse" id="menu">
		<sec:authorize access="!isAuthenticated()">
			<a class="nav-link" href="/inscription">S'inscrire</a>
		</sec:authorize>
		<sec:authorize access="isAuthenticated()">
			<div class="dropdown">
				<a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#"
					role="button" aria-haspopup="true" aria-expanded="false">Ouvrages</a>
				<div class="dropdown-menu">
					<a class="nav-link" href="/abonne/listeOuvrages/1">Liste des ouvrages</a>
				</div>
			</div>
			<div class="dropdown">
				<a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#"
					role="button" aria-haspopup="true" aria-expanded="false">Prêts</a>
				<div class="dropdown-menu">
					<a class="nav-link" href="/abonne/listePrets">Vos prêts encours</a>
					<a class="nav-link" href="/abonne/prolongerPret">Prolongerun prêt</a>
				</div>
			</div>
			<sec:authorize access="hasAnyRole('ROLE_ADMINISTRATEUR')">
				<div class="dropdown">
					<a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#"
						role="button" aria-haspopup="true" aria-expanded="false">Administration</a>
					<div class="dropdown-menu">
						<a class="nav-link" href="/admin/creerPret">Créer un prêt</a>
						<a class="nav-link" href="/admin/retourPret">Retour d'un prêt</a>
						<a class="nav-link" href="/admin/crerOuvrage">Créer un ouvrage</a>					
						<a class="nav-link" href="/admin/listeThemes">Liste des thèmes</a>					
						<a class="nav-link" href="/admin/crerTheme">Créer un thème</a>					
					</div>
				</div>
			</sec:authorize>
			<a class="nav-link ml-auto" href="/logout">Se déconnecter</a>
		</sec:authorize>
	</div>
</nav>