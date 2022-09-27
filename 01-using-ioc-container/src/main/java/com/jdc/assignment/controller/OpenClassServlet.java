package com.jdc.assignment.controller;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jdc.assignment.domain.Course;
import com.jdc.assignment.domain.OpenClass;
import com.jdc.assignment.model.CourseModel;
import com.jdc.assignment.model.OpenClassModel;

@WebServlet(urlPatterns = {"/classes","/class-edit"})
public class OpenClassServlet extends AbstractBeanFactoryServlet{

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		var courseId=req.getParameter("courseId");
//		var classid=req.getParameter("classid");
		
		
		System.out.println("classid Id FROM OPEN CLASS"+courseId);
		
		//Find Courses
		var courseModel=getBean("courseModel", CourseModel.class);
		var course=courseModel.findById(Integer.parseInt(courseId));
		req.setAttribute("course", course);
		
		
		
		
		var page=switch(req.getServletPath()) {
		case "/classes" -> {
			var model=getBean("openClassModel", OpenClassModel.class);
			
			System.out.println(courseId+" is FROM open clas id");
			
			var classes=model.findByCourse(Integer.parseInt(courseId));	
			req.setAttribute("classes", classes);

			
			yield "classes";
		}
		default -> "class-edit";
		};
		
		
		getServletContext().getRequestDispatcher("/%s.jsp".formatted(page)).forward(req, resp);
	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		var classid=req.getParameter("courseId");
		var teacher=req.getParameter("teacher");
		var startdate=req.getParameter("start_date");
		
		System.out.println(teacher+"\t"+startdate+"\t"+classid+"\t Open CLASS SERVLET");
		
		var course=getBean("courseModel",CourseModel.class);
		var c=course.findById(Integer.parseInt(classid));
		
		OpenClass oc=new OpenClass();
		oc.setCourse(c);
		oc.setTeacher(teacher);
		oc.setStartDate(LocalDate.parse(startdate));
		
		getBean("openClassModel",OpenClassModel.class).create(oc);	
		
		//resp.sendRedirect("/classes");
		resp.sendRedirect("/classes?courseId="+classid);
	}
	
	

}
