<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>New Question</title>
</head>
<body>
	<form action="/questions" method="post">
	<h3><label>Question</label></h3>
	<textarea name="question" rows="10" cols="50"></textarea>
	<h5><label>Tags</label></h5>
	<input type="text" name="tags">
	<input type="submit" value="Submit">
	</form>
	<br>
	<c:forEach items="${errors}" var="error">
	<h4><c:out value="${error}"></c:out></h4>
	</c:forEach>

</body>
</html>