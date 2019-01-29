<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Great Ideas | Update</title>

<style>
*{
	padding:0;
	margin:0;
	font-family: arial;
	font-weight: 100;
}

.header{
	width:100%;
	text-align: center;
	padding-top:20px;
	height:80px;
	border-bottom:4px solid #4190af;
	margin-bottom:40px;
	box-shadow: 0px 0px 40px #c7c7c7;
}

.header h2{
	font-size: 18px;
	padding: 10px 0px;
}

.center{
	margin: 20px;
	text-align: center;
}

.nav{
	display:flex;
	width:100%;
}

.nav a{
	border:2px solid #4CA170;
	padding:10px 10px 10px 10px;
	transition:0.3s;
	width:30%;
}

.reviewlist {
	margin-left: 500px;
	padding: 20px;
	overflow: auto;
	width: 70%;
	height: 350px;
}

h3{
	color: #4CA170;
}

a:link{
	text-decoration: none;
}

.nav a, .edit, .review{
	height:20px;
	border-radius:4px;
	text-align: center;
	color:#004080;
	margin:0px 20px;
}

.nav a:hover{
	background-color:#ededed;
}

.nav_contain{
	width:100%;
	display:flex;
}

.box_contain{
	margin-top: 40px;
	margin-bottom: 40px;
	display:flex;
	justify-content: center;
	
}

.dashboard{
	margin:auto;
	justify-content: center;
}

.box{
	width:80%;
	min-height: 500px;
	border-radius: 6px;
	box-shadow: 0px 0px 30px #c7c7c7;
}

.box h2{
	text-align: center;
	padding:20px 0px;
	border-bottom:2px solid #4190af;
}

table{
	width:85%;
	margin: 40px auto;
	border-collapse: collapse;
	border-radius:10px;
}

table a {
	color:#004080;
	text-decoration: none;
}

table a:hover{
	
}

table{
	padding:10px 10px;
	border:1px solid #ddd;
	border-collapse: collapse;
	text-align: left;
}

tr:nth-child(even) {
	background-color:#97b7fb;
}

.form_contain{
	text-align:center;
	width:50%;
	margin:
	50px auto;
}

label{
	font-size: 20px;
}
.form_contain input, textarea{
	width:100%;
	margin-top:10px;
	margin-bottom:20px;
	border-radius:3px;
	border:1px solid grey;
	font-size: 18px;
}

.form_contain input{
	text-align:center;
	height:40px;

}

.form_contain textarea {
	height:100px;
	text-align:center;
	padding:20px 2%;
	width:96%;
}

.submit{
	background-color:#7ab087;
	color:white;
	transition:.03s;
}

.submit:hover{
	background-color:#587f63;
	
}

.login_contain{
	margin-top:20px;
	display:flex;
}

.login_box{
	width:50%;
}

.login_box h3{
	padding-top:20px;
	text-align: center;
	font-size: 24px;
}

.login_box input{
	display:block;
	margin:auto;
	width:70%;
	height:35px;
	font-size: 16px;
	text-align: center;
	margin-bottom: 20px;
	border:1px solid grey;
	border-radius: 3px;

}

.login_box h3{
	margin-bottom: 20px;
}

.login_box .submit{
	background-color: green;
	border-style:none !important;
	transition:.08s;
}

.login_box .submit:hover{
	background-color: #5ec760;
}

.errors{
	margin:auto;
	text-align:center;
	display:block;
	width:66%;
	color:white;
	background-color: #aa0001;
	border-radius:4px;
	padding:2%;
	opacity:50%;
}

.remove{
	color:#c20001;
}

.t1, .t2{
	vertical-align:top;
	display:inline-block;
	padding:20px;
}

.t1{
	width:70%;
}
</style>

</head>

<body>

	<div class="header">
		<h1>Edit Idea</h1>
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
			<h1 class="center"><c:out value="${ idea.name }"/></h1>	
			<div class="form_contain">
				<form:form action="/update_idea/${ idea.id }" method="post" modelAttribute="idea">
					<form:label path="name">Content:</form:label>
					<form:errors path="name"></form:errors>
					<form:input path="name"/>
					</br>
					</br>
					
					<form:input path="creator_id" type="hidden" value="${user.id}"></form:input>
					<form:input path="creator_name" type="hidden" value="${user.name}"></form:input>
					
					<input class="submit" type="submit">
				</form:form>
				<a href="/delete/${ idea.id }">Delete</a>
			</div>		
		</div>
	</div>
	
</body>
</html>