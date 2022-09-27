<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
		<h3>Registeration for ${ course.name }</h3>
		
		<h3>REGISTERATION</h3>
		
		<div class="mb-2">
		
			<c:url var="addNew"  value="/registeration-edit">
				<c:param name="courseId" value="${ course.id }"></c:param>
				<c:param name="classid" value="${ param.classid }"></c:param>
			</c:url>
			<a href="${addNew}" class="btn btn-primary">Register Here</a>
		
		
		</div>
		
		<div class="row">
			
			<c:choose>
				<c:when test="${ empty registerations }">
					 <div class="bg-warning">
						There is no student.Please register here!
					</div>
				</c:when>	
				<c:otherwise>
					<table class="table table-striped">
						<thead>
							<tr>
								<th>Id</th>
								<th>Student</th>
								<th>Phone</th>
								<th>Email</th>
							</tr>
						</thead>
						<tbody>
							<c:set var="counter" value="0" />
							<c:forEach var="c" items="${registerations }">
								<tr>
									<td>${c.id}</td>
									<td>${c.student }</td>
									<td>${c.phone }</td>
									<td>${c.email }</td>
								</tr>
								<c:set var="counter" value="${counter+1}" /> 
							</c:forEach>
							
						</tbody>
						
						<tfoot>
							<tr>
								<td>Total Student : ${counter}</td>
							</tr>
						</tfoot>
						
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