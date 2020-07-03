<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
    
<nav class="navbar navbar-expand-lg navbar-light bg-light row">
  	<a class="navbar-brand" href="/">Les amis de l'escalade</a>
  	<button class="navbar-toggler" type="button" data-toggle="collapse"  data-target="#menu">
  		<span class="navbar-toggler-icon"></span>
  	</button>
  	<div class="collapse navbar-collapse" id="menu">
	  	<sec:authorize access="!isAuthenticated()">
			<a class="nav-link" href="/public/listeSites">Liste des sites</a>	    
			<a class="nav-link" href="/inscription">S'inscrire</a>
	  	</sec:authorize>
	  	<sec:authorize access="isAuthenticated()">
		<div class="dropdown">
		    <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button"
		    aria-haspopup="true" aria-expanded="false">Sites</a>
		   	<div class="dropdown-menu">
		  			<a class="nav-link" href="/public/listeSites">Liste des sites</a>
		  			<a class="nav-link" href="/inscrit/listeSitesUtilisateur">Liste de vos sites</a>
		  			<a class="nav-link" href="/inscrit/publierSite">Publier un site</a>
		  		</div>
			</div>
			<div class="dropdown">
			    <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button"
			    aria-haspopup="true" aria-expanded="false">Topos</a>
			   	<div class="dropdown-menu">
			  			<a class="nav-link" href="/inscrit/listeTopos">Liste des Topos</a>
			  			<a class="nav-link" href="/inscrit/listeToposUtilisateur">Liste de vos Topos</a>
			  			<a class="nav-link" href="/inscrit/listeToposEmprunt">Liste des Topos empruntés</a>
			  		</div>
			</div>
			<sec:authorize access="hasAnyRole('ROLE_ADMINISTRATEUR')"> 
				<a class="nav-link" href="/admin/listeUtilisateurs">Utilisateurs</a>
			</sec:authorize>
			<a class="nav-link ml-auto" href="/logout">Se déconnecter</a>
		</sec:authorize>
	</div>
</nav>