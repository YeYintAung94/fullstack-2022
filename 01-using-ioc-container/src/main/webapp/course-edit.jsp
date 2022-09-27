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
	
		<h1>USING IOC CONTAINER</h1>
		<h3>Add New Course</h3>
		
		
		<div class="row">
		
			<div class="d-flex justify-content-center">		
				<div class="col-6">
			
				<c:url var="save" value="/courses"></c:url>
				
				<form action="${save }" method="post">
				
				<div>
					<label class="form-label">Name</label>
					<input type="text" name="name" placeholder="Enter your name" required="required" class="form-control" />
				</div>
				
				<div>
					<label class="form-label">Duration</label>
					<input type="number" name="duration" placeholder="Enter your duration" required="required" class="form-control" />
				</div>
				
				<div>
					<label class="form-label">Fees</label>
					<input type="number" name="fees" placeholder="Enter your fees" required="required" class="form-control" />
				</div>
				
				<div class="mb-3">
					<label class="form-label">Description</label>
					<textarea cols="40" name="description" rows="4" class="form-control"></textarea>
				</div>
				
				<div class="d-flex justify-content-end">
					<input type="submit" value="Save Course" class="btn btn-primary"/>
				</div>			
				
				
				
				</form>
			</div>
			
			</div>	
		</div>
		
		
		
		<br>
		<div class="d-flex justify-content-end">
			<a href="/" class="btn btn-secondary rounded-0 d-flex justify-content-end">Back</a>
		</div>	
	</div>
	
	
	
</body>
</html>