<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
	
	<div class="container mt-4">
		<div class="row">
		<h1>Using IoC Container</h1>
		<h3>Register for ${course.name} ${course.id }</h3>
			
			<div class="d-flex justify-content-center">
				<div class="col-6">
				
					
					<c:url var="registerations" value="/registerations">
							<c:param name="courseid" value="${course.id}"></c:param>
							<c:param name="classid" value="${param.classid}"></c:param>
					</c:url>
				
					<form action="${registerations }" method="post">
					
						<div>
							<label for="student" class="form-label">Student Name</label>
							<input type="text" name="student" class="form-control"/>
						</div>
						
						<div>
							<label for="phone" class="form-label">Phone</label>
							<input type="phone" name="phone" class="form-control"/>
						</div>
						
						
						<div>
							<label for="email" class="form-label">Email</label>
							<input type="email" name="email" class="form-control"/>
						</div>
						
						<div class="d-flex justify-content-end mt-3">
							<input type="submit" class="btn btn-primary" value="Register" />
						</div>
					
					</form>
					
				</div>
			</div>
		
		</div>
		
	</div>

</body>
</html>