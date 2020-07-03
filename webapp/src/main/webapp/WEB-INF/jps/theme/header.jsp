<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
 <header class="row  bg-dark text-white">
 	<div class="col-12">
  		<h1>Les amis de l'escalade</h1>
		<h3 class="d-none d-md-block">Le site communautaire des grimpeurs</h3>
 	</div>
 	<div class="col-12">
  		<sec:authorize access="isAuthenticated()">
			Connecté en tant que <sec:authentication property="name"/>
		</sec:authorize>
		<sec:authorize access="!isAuthenticated()">
	  		<a class="navbar-brand" href="/login">S'identifier</a>
		</sec:authorize>
   	</div>
</header>