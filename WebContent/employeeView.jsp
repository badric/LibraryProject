<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="java.util.List" %>
<%-- <% List bookList  = (List)session.getAttribute%>--> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Employee View</title>
<link type="text/css" rel="stylesheet" href="resources/css/Table.css" />
</head>


<body>

<div style="float: left;">
<%
String newBookPage = "newBookPage.jsp";
%>
<input type="button" value="New Book" onclick="JavaScript:window.location='<%= newBookPage %>'" style="margin: 45px; margin-bottom:0px; width: 750px;"/>

<table id="hor-minimalist" style="display: inlineBlock;">
	<thead>
		<tr>
			<th scope="col" id="title">Title</th>
			<th scope="col" id="autor">Autor</th>
			<th scope="col" id="year">Jahr</th>
			<th scope="col" id="ISBN">ISBN</th>
			<th scope="col" id="price">Preis</th>
			<th scope="col" id="age">Alter</th>
			<th scope="col" id="actions">Aktionen</th>
		</tr>
	</thead>
	
	<tbody>
		<c:forEach items="${books}" var="entry">
		<tr>
			<td> <c:out value="${entry.value.title}"/> </td>
			<td> <c:out value="${entry.value.autor}"/> </td>
			<td> <c:out value="${entry.value.year}"/> </td>
			<td> <c:out value="${entry.value.isbn}"/> </td>
			<td> <c:out value="${entry.value.price}"/> </td>
			<td> <c:out value="${entry.value.age}"/> </td>
			<td>
				<form action="employee" method="post" >
					<input type="hidden" name="bookID" id="bookID" value="${entry.key}" />
					<input type="submit" value="Delete Book" name="Submit" onclick="clickAction()"  />
					<input type="submit" value="Edit Book" name="Submit" onclick="clickAction()"  />
				</form>
			</td>
		</tr>
		</c:forEach>
	</tbody>
	
</table>
</div>

<div style="float: left;">
<%
String newUserPage = "newUserPage.jsp";
%>
<input type="button" value="New User" onclick="JavaScript:window.location='<%= newUserPage %>'" style="margin: 45px; margin-bottom:0px; width: 750px;" />

<table id="hor-minimalist" >
	<thead>
		<tr>
			<th scope="col" id="title">Name</th>
			<th scope="col" id="autor">Nachname</th>
			<th scope="col" id="year">Alter</th>
			<th scope="col" id="age">Actionen</th>
		</tr>
	</thead>
	
	<tbody>
		<c:forEach items="${users}" var="entry">
		<tr>
			<td> <c:out value="${entry.value.name}"/> </td>
			<td> <c:out value="${entry.value.surname}"/> </td>
			<td> <c:out value="${entry.value.age}"/> </td>
			<td>
				<form action="employee" method="post" >
					<input type="hidden" name="userID" id="userID" value="${entry.key}" />
					<input type="submit" value="Delete User" name="Submit" onclick="clickAction()"  />
					<input type="submit" value="Edit User" name="Submit" onclick="clickAction()"  />
				</form>
			</td>
		</tr>
		</c:forEach>
	</tbody>
</table>


</div>


<script src="http://code.jquery.com/jquery-3.3.1.js"
  integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60="
  crossorigin="anonymous" type="text/javascript"></script>
<script type="text/javascript" >
	function clickAction(){
		
// 		console.info("Fuehre die Clickaction aus!");
// 		var item = $(this).closest("tr");
// 		console.info(item);
// 		var hiddenValue = document.getElementById("selectedValue");
// 		hiddenValue = item;
// 		console.info("Fuehre die Clickaction aus!");
 		var form = $(this).closest("form");
 		form.submit();
	}
</script>

</body>
</html>