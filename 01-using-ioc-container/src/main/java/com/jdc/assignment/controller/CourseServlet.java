package com.jdc.assignment.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.BeanFactory;

import com.jdc.assignment.domain.Course;
import com.jdc.assignment.listener.SpringContainerManager;
import com.jdc.assignment.model.CourseModel;


@WebServlet(urlPatterns = {"/","/course-edit","/courses"})
public class CourseServlet extends AbstractBeanFactoryServlet {
	private static final long serialVersionUID = 1L;
	
//	@Override
//	public void init() throws ServletException {
//		var message=getBean("message", String.class);
//		System.out.println(message);
//	}
	
	
	
//	@Override
//	public void init() throws ServletException {
//		/*
//		 * SpringContainerManager class ထဲမှာ Application context ကို ခေါ်သုံးထားတယ်။
//		 */
//		
//		var springContext=getServletContext().getAttribute(SpringContainerManager.SPRING_CONTEXT);
//		
//		if(null!=springContext && springContext instanceof BeanFactory factory) {
//			System.out.println(factory.getBean("message"));
//		}
//	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
//		System.out.println(req.getServletPath());
		
		var page=switch(req.getServletPath()) {
		case "/course-edit" -> "/course-edit.jsp";
		default -> {
			//Load Course and set result to request scope
			var model=getBean("courseModel", CourseModel.class);
			req.setAttribute("courses", model.getAll());
			
			yield "/index.jsp";
		}
		};
		
		getServletContext().getRequestDispatcher(page).forward(req, resp);
		
	}

	

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//get request parameters
		var name=req.getParameter("name");
		var duration=req.getParameter("duration");
		var fees=req.getParameter("fees");
		var description=req.getParameter("description");
		
		//create course object
		var course=new Course();
		course.setName(name);
		course.setDescription(description);
		course.setFees(Integer.parseInt(fees));
		course.setDuration(Integer.parseInt(duration));
		
		
		//save to db
		getBean("courseModel", CourseModel.class).save(course);
		
		//redirect to top page
		resp.sendRedirect("/");
	}

}
