<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Neuer Nutzer</title>
<link type="text/css" rel="stylesheet" href="resources/css/Table.css" />
</head>
<body>
	<form action="employee" method="post" >
		<fieldset>
			<legend>Alten Nutzer ändern:</legend>
			<div id="inputdiv" style="float: left;">
			Vorname:</br>
			<input type="text" name="name" value="${user.name}"></input></br>
			</div>
			<div id="inputdiv" style="float: left;">
			Nachname:</br>
			<input type="text" name="surname" value="${user.surname}"></input></br>
			</div>
			<div id="inputdiv" style="float: left;">
			Alter:</br>
			<input type="text" name="age" value="${user.age}"></input></br>
			</div>
			<input type="hidden" name="userID" id="userID" value="${id}" />
			<input type="submit" value="Save Edited User" name="Submit" style="margin-top:100px; float:right;"></input>
		</fieldset>
	</form>
</body>
</html>