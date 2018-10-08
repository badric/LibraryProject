<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="resources/css/style.css" rel="stylesheet" type="text/css" />
<title>Customer View</title>
<script type="text/javascript">
	function confirmReturn(form, id) {
		var conf = confirm("Do you want to return the book?");
		if (conf) {
			document.getElementById('id').value = id;
			console.log("Submitting: " + id);
			console.log("Button value: " + document.getElementById('rtn').value);
			document.getElementById('button1').value = document.getElementById('rtn').value;
			console.log("New hidden value: " + document.getElementById('button1').value);
			form.submit();
		}
	}
	
	function confirmAdd(form, id) {
		var conf = confirm("Do you want to borrow the book?");
		if (conf) {
			console.log("Button value: " + document.getElementById('brw').value);
			console.log("Borrowing: " + id);
			document.getElementById('addId').value = id;
			document.getElementById('button2').value = document.getElementById('brw').value;
			console.log("New hidden value: " + document.getElementById('button2').value);
			form.submit();
		}
	}
</script>
</head>
<body>
	<center>
		<h1>Welcome to the Customer Page!</h1>
		<div>
			<form method="post">
				<table>
					<caption>
						<h2>
							List of borrowed books for
							<%=session.getAttribute("fullname")%>
						</h2>
					</caption>
					<thead>
						<tr>
							<th>Title</th>
							<th>Author</th>
							<th>Year</th>
							<th>ISBN</th>
							<th>Price</th>
							<th>Age</th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="entry" items="${mapIds}">
							<tr>
								<td><c:out value="${entry.value.title}" /></td>
								<td><c:out value="${entry.value.autor}" /></td>
								<td><c:out value="${entry.value.year}" /></td>
								<td><c:out value="${entry.value.isbn}" /></td>
								<td><c:out value="${entry.value.price}" /></td>
								<td><c:out value="${entry.value.age}" /></td>
								<td><input type="button"
									onclick="confirmReturn(this.form, <c:out value="${entry.key}" />)"
									value="Return Book" id="rtn" /></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<input type="hidden" name="button" id="button1" /> <input
					type="hidden" name="id" id="id" value="" />

			</form>
		</div>
		<br />
		<div>
			<form method="post">
				<table>
					<caption>
						<h2>List of all available books</h2>
					</caption>
					<thead>
						<tr>
							<th>Title</th>
							<th>Author</th>
							<th>Year</th>
							<th>ISBN</th>
							<th>Price</th>
							<th>Age</th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="entry" items="${mapAvailable}">
							<tr>
								<td><c:out value="${entry.value.title}" /></td>
								<td><c:out value="${entry.value.autor}" /></td>
								<td><c:out value="${entry.value.year}" /></td>
								<td><c:out value="${entry.value.isbn}" /></td>
								<td><c:out value="${entry.value.price}" /></td>
								<td><c:out value="${entry.value.age}" /></td>
								<td><input type="button"
									onclick="confirmAdd(this.form, <c:out value="${entry.key}" />)"
									value="Borrow Book" id="brw" /></td>

							</tr>
						</c:forEach>
					</tbody>
				</table>
				<input type="hidden" name="button" id="button2" /> <input
					type="hidden" name="addId" id="addId" value="" />
			</form>

		</div>
	</center>
</body>
</html>