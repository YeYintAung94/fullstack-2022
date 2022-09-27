<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Using IOC container</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
	
	<div class="container mt-4">
	
		<h1>Using IoC Container</h1>
		<h3>Add New Class for ${course.name}</h3>
		
		<div class="row">
			
			<div class="d-flex justify-content-center">
				<div class="col-6">
					
					<c:url var="classes" value="/classes">
							<c:param name="courseId" value="${course.id}"></c:param>
					</c:url>
				
					<form action="${classes }" method="post">
									
							<div class="mt-2">
								<label class="form-label">Course Name</label>
								<input type="text" name="name" class="form-control" placeholder="Entre course name" value="${course.name }" readonly=true/>
								
							
							</div>
							
							<div class="mt-2">
								<label class="form-label">Teacher Name</label>
								<input type="text" name="teacher" class="form-control" placeholder="Entre teacher name"/>
							</div>
						
						
							<div class="mt-2">
								<label class="form-label">Start Date</label>
								<input type="date" name="start_date" class="form-control" placeholder="Entre duration"/>
							</div>
							
							<div class="d-flex justify-content-end mt-4">
								
								<input type="submit" value="Save Class" class="btn btn-primary"/>
							</div>			
							
					</form>
				</div>
			
			
			</div>
		
		</div>
	
	</div>
	
	
</body>
</html>