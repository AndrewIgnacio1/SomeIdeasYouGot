<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>Great Ideas - Create new idea</title>
<link rel="stylesheet" type="text/css" media="screen" href="css/style.css" />
</head>

<body>
	<div class="header">
		<h1>Add a new show.</h1>
	</div>
	
	<div class="nav_contain">
		<div class="nav">
			<a href="/dashboard">Dashboard</a>
			<a href="/create_idea">Create an Idea</a>
			<a href="/logout">Logout</a>
		</div>
	</div>
	
	<div class="box_contain">
		<div class="box">
		
			<h2>New TV Show</h2>			
			<div class="form_contain">
				<form:form action="/process_new_idea" method="post" modelAttribute="idea">
					<c:if test="${ exists != null }">
							<p>${ exists }</p>
						</c:if>
						<c:if test="${ regError != null }">
							<p>${ regError }</p>
						</c:if>
					
					<form:label path="name">An Idea</form:label>
					<form:errors path="name"></form:errors>
					<form:input path="name"/>
					
					<form:input path="creator_id" type="hidden" value="${user.id}"></form:input>
					<form:input path="creator_name" type="hidden" value="${user.name}"></form:input>
					
					<a href="/dashboard">cancel</a> <input class="submit" type="submit">
				</form:form>
			</div>		
		</div>
	</div>
	
</body>
</html>