<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Great Ideas | Dashboard</title>
<link rel="stylesheet" type="text/css" media="screen" href="css/style.css" />
</head>

<body>

	<div class="header">
		<h1>Tv Shows</h1>
		<h2>Welcome to your dashboard, <c:out value="${ name }"/></h2>
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
		
		<a href="/low_likes">Low Likes</a> | <a href="/high_likes">High Likes</a>
	
			<h2>Tv Shows</h2>
			<div class="table_contain">
				<table>
					<tr>
						<th>Idea</th>
						<th>Created By:</th>
						<th>Likes</th>
						<th>Action</th>
					</tr>
					
					<c:forEach items="${ ideas }" var="idea">
					
					<tr>
						<td><a href="/show_idea/${ idea.id }">${ idea.name }</a></td>
						<td><c:out value="${ idea.creator_name }"/></td>
						<td><c:out value="${ idea.users.size() }"/></td>
						<td>
							<c:if test="${ idea.creator_id == user.id }">
								<p>Your Idea!</p>
							</c:if>
							<c:if test="${ !idea.users.contains(user) && idea.creator_id != user.id  }">
								<p><a href="/like_idea/${ idea.id }/${ user.id }">Like</a></p>
							</c:if>
							<c:if test="${ idea.users.contains(user) && idea.creator_id != user.id }">
								<p><a href="/unlike_idea/${ idea.id }/${ user.id }">Unlike</a></p>
							</c:if>
						</td>
					</tr>
					
					</c:forEach>	
				</table>
			</div>
		</div>
	</div>

</body>
</html>