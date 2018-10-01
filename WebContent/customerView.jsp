<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="style.css" rel="stylesheet" type="text/css" />
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Customer View</title>

<script type="text/javascript">
	function confirmReturn(form, id) {
		var conf = confirm("Do you want to return the book?");
		if (conf) {
			console.log("Submitting: " + id);
			document.getElementById('id').value = id;
			form.submit();
		} else {
			//Do nothing
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
							<%=session.getAttribute("userName")%>
						</h2>
					</caption>
					<thead>
						<tr>
							<th>Title</th>
							<th>Author</th>
							<th>Year</th>
							<th>ISBN</th>
							<th>Age</th>
							<th>Price</th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<!-- <c:forEach var="book" items="${listBook}">
							<tr>
								<td><c:out value="${book.title}" /></td>
								<td><c:out value="${book.autor}" /></td>
								<td><c:out value="${book.year}" /></td>
								<td><c:out value="${book.isbn}" /></td>
								<td><c:out value="${book.age}" /></td>
								<td><c:out value="${book.price}" /></td>
								<td><input type="button"
									onclick="confirmReturn(this.form, <c:out value="${mapIds[book]}" />)"
									value="Return Book" /></td>

							</tr>
						</c:forEach> -->
						<c:forEach var="entry" items="${mapIds}">
							<tr>
								<td><c:out value="${entry.key.title}" /></td>
								<td><c:out value="${entry.key.autor}" /></td>
								<td><c:out value="${entry.key.year}" /></td>
								<td><c:out value="${entry.key.isbn}" /></td>
								<td><c:out value="${entry.key.age}" /></td>
								<td><c:out value="${entry.key.price}" /></td>
								<td><input type="button"
									onclick="confirmReturn(this.form, <c:out value="${entry.value}" />)"
									value="Return Book" /></td>

							</tr>
						</c:forEach>
					</tbody>
				</table>
				<input type="hidden" name="id" id="id" value="" />
			</form>
		</div>

	</center>
</body>
</html>