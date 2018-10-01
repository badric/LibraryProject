<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Neues Buch</title>
<link type="text/css" rel="stylesheet" href="resources/css/Table.css" />
</head>
<body>
	<form action="employee" method="post" >
		<fieldset>
			<legend>Neues Buch anlegen:</legend>
			<div id="inputdiv" style="float: left;">
			Titel:</br>
			<input type="text" name="title"></input></br>
			</div>
			<div id="inputdiv" style="float: left;">
			Autor:</br>
			<input type="text" name="autor"></input></br>
			</div>
			<div id="inputdiv" style="float: left;">
			Erscheinungsjahr:</br>
			<input type="text" name="year"></input></br>
			</div>
			<div id="inputdiv" style="float: left;">
			ISBN:</br>
			<input type="text" name="isbn"></input></br>
			</div>
			<div id="inputdiv" style="float: left;">
			Preis:</br>
			<input type="text" name="price"></input></br>
			</div>
			<div id="inputdiv" style="float: left;">
			Zustand (alter):</br>
			<input type="text" name="age"></input></br>
			</div>
			<input type="submit" value="Submit New Book" name="Submit" style="margin-top:100px;"></input>
		</fieldset>
	</form>
</body>
</html>