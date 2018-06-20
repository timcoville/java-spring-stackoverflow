<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>   
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>New Answer</title>
</head>
<body>
<h1><c:out value="${question.question }"></c:out></h1>
<div style="display:inline-block; padding: 5px; font-weight: bold;">Tags:</div>
<c:forEach items="${question.getTags() }" var="tag">
	<div style="display:inline-block; padding: 10px;">
		<c:out value="${tag.subject }"></c:out>
	</div>
</c:forEach>
<div>
	<div style="display:inline-block; vertical-align:top; width: 500px;">
		<h2>Answers:</h2>
		<c:forEach items="${answers }" var="item">
		<p><c:out value="${item.answer}"></c:out></p>
		</c:forEach>
	</div>
	<div style="display:inline-block; width: 400px;">
		<h3>Add Your Answer:</h3>
		
		<form action="/answers" method="post">
			<p><textarea name="answer" rows="10" cols="60"></textarea></p>
			<input type="hidden" name="questionID" value="${question.id }">
			<p><input type="submit" value="Answer it"></p>
		</form>

	</div>
</div>
</body>
</html>