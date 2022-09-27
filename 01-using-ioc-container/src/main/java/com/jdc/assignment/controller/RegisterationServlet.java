package com.jdc.assignment.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jdc.assignment.domain.Registeration;
import com.jdc.assignment.model.CourseModel;
import com.jdc.assignment.model.OpenClassModel;
import com.jdc.assignment.model.RegisterationModel;

@WebServlet(urlPatterns = {"/registerations","/registeration-edit"})
public class RegisterationServlet extends AbstractBeanFactoryServlet{
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println(req.getServletPath()+req.getParameter("classid")+req.getParameter("courseId"));
		
		var classid=req.getParameter("classid");
		var courseId=req.getParameter("courseId");
		
		System.out.println("COURSE ID FROM REG SERVLET "+courseId);
		
		if(courseId!=null) {
		var courseModel=getBean("courseModel",CourseModel.class);
		var course=courseModel.findById(Integer.parseInt(courseId));
		req.setAttribute("course", course);
		}
//		var openclass=getBean("openClassModel", OpenClassModel.class);
//		var oc=openclass.findById(Integer.parseInt(classid));
//		
//		req.setAttribute("openclass", oc);
		
		var page=switch(req.getServletPath()) {
		case "/registerations" -> {
				var register=getBean("registerationModel",RegisterationModel.class);
				var reg=register.getAll(Integer.parseInt(classid));
				
				req.setAttribute("registerations", reg);
			
				yield "registerations";
			}
		default -> "registeration-edit";
		};
		
		getServletContext().getRequestDispatcher("/%s.jsp".formatted(page)).forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		var courseId=req.getParameter("courseId");
		var classid=req.getParameter("classid");
		var student=req.getParameter("student");
		var phone=req.getParameter("phone");
		var email=req.getParameter("email");
		
		System.out.println("FROM REG SERVLET => "+courseId+"\t"+classid);
		System.out.println("REGISTERATION SERVLET "+req.getServletPath());
		
		
		var ocmodel=getBean("openClassModel", OpenClassModel.class);
		var oc=ocmodel.findById(Integer.parseInt(classid));
		
		
		var register=new Registeration();
		register.setOpenClass(oc);
		register.setStudent(student);
		register.setPhone(phone);
		register.setEmail(email);
		
		getBean("registerationModel",RegisterationModel.class).register(register);
		

		resp.sendRedirect("/registerations?classid="+classid);
	}

}
