<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Dojo Overflow</title>
</head>
<body>
	<h1>Questions Dashboard</h1>

	<table>
		<tr>
			<th>Question</th>
			<th>Tags</th>
		</tr>
		<c:forEach items="${records}" var="record">
		<tr>
			<td><a href="/questions/${record[2]}"><c:out value="${record[0]}"></c:out></a></td>
			<td><c:out value="${record[1]}"></c:out></td>
		</tr>
		</c:forEach>
	</table>

	<a href="/questions/new"><button>New Question</button></a>
</body>
</html>