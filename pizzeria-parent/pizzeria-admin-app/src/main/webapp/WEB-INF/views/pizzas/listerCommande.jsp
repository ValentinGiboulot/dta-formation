<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8" isELIgnored="false"%>
    
    <%@page import ="java.util.List" %>
    <%@page import="fr.pizzeria.model.Pizza" %>
    
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ici c'est le titre</title>

  <link rel='stylesheet' href=<c:url value='/css/bootstrap.min.css'></c:url>> 
  <script src="<c:url value='/js/jquery-1.12.4.min.js'></c:url>"></script>
  <script src="<c:url value='/js/bootstrap.min.js'></c:url>"></script>
  <script src="<c:url value='/js/script.js'></c:url>"></script>
</head>
<body>

<div class='container'>


<a href="<c:url value='/logout'/>" class="btn btn-danger">Se logouter</a>

<a href="<c:url value='/technique'/>" class="btn btn-info">Infos Techniques</a>



<table class='table table-striped'>

 <thead>
    <tr>
   	  <th>id</th>
      <th>numero commande</th>
      <th>data commande</th>
      <th>id livreur</th>
      <th>id client</th>
    </tr>
	</thead>
	<tbody>
	
<c:forEach var="comm" items="${lCommande}">

<tr>
<td>${comm.id}</td>
<td>${comm.numero_commande}</td>
<td>${comm.date_commande}</td>
<td>${comm.livreur_id}</td>
<td>${comm.client_id}</td>


<td><a href=<c:url value='/pizzas/edit?code=${comm.id}'></c:url>><button class='btn btn-warning'>Editer</button></a></td>
<td>	
<form action="<c:url value='/pizzas/list'/>" method="post">
	<input type='hidden' name='code' value='${comm.id}'>
	<input type='submit' class='btn btn-danger' value ='Supprimer'>
</form></td>

</c:forEach>
	
</tbody>
</table>

<a href="/pizzeria-admin-app/pizzas/new">
   <input type="button" value="CREER UNE NOUVELLE PIZZA !!!!!!"  class ='btn btn-success'/>
</a>	

</div>
	
	





</body>
</html>