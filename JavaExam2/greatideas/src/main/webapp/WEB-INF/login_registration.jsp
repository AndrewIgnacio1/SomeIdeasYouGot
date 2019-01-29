<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Great Ideas | Login and Registration</title>
<link rel="stylesheet" type="text/css" media="screen" href="css/style.css" />
</head>

<body>

	<div class="header">
		<h1>Food Trucks</h1>
	</div>
	
	<div class="box_contain">
		<div class="box">
			
			<h2>Login + Registration</h2>
			
			
<!------------------------------------- Registration Form ------------------------------------->
			
			<div class="login_contain">
				<div class="login_box">
					<h3>Register</h3>
					
					<form:form action="/process_registration" method="post" modelAttribute="user">
				
				
						<form:errors class="errors" path="name"></form:errors>
						<form:input path="name" placeholder="name"></form:input>
						
						<form:errors class="errors" path="email"></form:errors>
						<form:input path="email" placeholder="email"></form:input>
						
						<form:errors class="errors" path="password"></form:errors>
						<form:input path="password" placeholder="password"></form:input>
						
						<form:errors class="errors" path="confirmation"></form:errors>
						<form:input path="confirmation" placeholder="confirm password"></form:input>
						
						<input class="submit" type="submit">

					</form:form>
				</div>
				
<!------------------------------------- Login Form ------------------------------------->		
				
				<div class="login_box">
					<h3>Login</h3>
					
					<form:form action="/process_login" method="post" modelAttribute="user">
				
						<form:errors class="errors" path="email"></form:errors>
						<form:input path="email" placeholder="email"></form:input>
						
						<form:errors class="errors" path="password"></form:errors>
						<form:input path="password" placeholder="password"></form:input>
						
						<input class="submit" type="submit">

					</form:form>
				</div>
			</div>
		</div>
	</div>

</body>
</html>