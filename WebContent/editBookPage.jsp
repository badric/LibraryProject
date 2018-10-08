<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Buch Editieren</title>
<link type="text/css" rel="stylesheet" href="resources/css/Table.css" />
</head>
<body>
	<form action="employee" method="post" >
		<fieldset>
			<legend>Buch editieren:</legend>
			<div id="inputdiv" style="float: left;">
			Titel:</br>
			<input type="text" name="title" value="<c:out value="${book.title}"/>"></input></br>
			</div>
			<div id="inputdiv" style="float: left;">
			Autor:</br>
			<input type="text" name="autor" value="${book.autor}"></input></br>
			</div>
			<div id="inputdiv" style="float: left;">
			Erscheinungsjahr:</br>
			<input type="text" name="year" value="${book.year}"></input></br>
			</div>
			<div id="inputdiv" style="float: left;">
			ISBN:</br>
			<input type="text" name="isbn" value="${book.isbn}"></input></br>
			</div>
			<div id="inputdiv" style="float: left;">
			Preis:</br>
			<input type="text" name="price" value="${book.price}"></input></br>
			</div>
			<div id="inputdiv" style="float: left;">
			Zustand (alter):</br>
			<input type="text" name="age" value="${book.age}"></input></br>
			</div>
			<input type="hidden" name="bookID" id="bookID" value="${id}" />
			<input type="submit" value="Save Edited Book" name="Submit" style="margin-top:100px; float:right;"></input>
		</fieldset>
	</form>
</body>
</html>