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
		<h3>Classes for ${ course.name }</h3>
		
		<div>
			<c:url var="addNew"  value="/class-edit">
				<c:param name="courseId" value="${ course.id }"></c:param>
			</c:url>
			<a href="${addNew}" class="btn btn-primary">Add New Class</a>
		</div>
		
		<div class="row">
			
			<c:choose>
					<c:when test="${ empty classes }">
						<div class="alert alert-warning">
							There is no class for ${course.name }.<br>
							Please create a new class.
						</div>
					</c:when>
					
					<c:otherwise>
						<table class="table table-striped">
							<thead>
								<tr>
									<th>Id</th>
									<th>Course</th>
									<th>Teacher</th>
									<th>Start Date</th>
									<th>Fees</th>
									<th>Duration</th>
									<th>Description</th>
									<th>Action</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="c" items="${ classes }">
									<tr>
										<td>${c.id }</td>
										<td>${c.course.name }</td>
										<td>${c.teacher }</td>
										<td>${c.startDate }</td>
										<td>${c.course.fees}</td>
										<td>${c.course.duration } Months</td>
										<td>${c.course.description }</td>
										<td>
											
											<c:url var="registerations" value="/registerations">
												<c:param name="classid" value="${c.id}"></c:param>
												<c:param name="courseId" value="${ course.id }"></c:param>
											</c:url>
											<a href="${registerations }">Register</a>
											
										
										</td>
										
									</tr>
								</c:forEach>
							</tbody>
							
						</table>
					</c:otherwise>
				</c:choose>
			
			
			
		
		</div>
		
		<div class="d-flex justify-content-end">
			<a href="/" class="btn btn-secondary rounded-0 d-flex justify-content-end">Back</a>
		</div>	
	
	</div>
	
	
</body>
</html>