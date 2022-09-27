package com.jdc.assignment.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.springframework.context.support.GenericXmlApplicationContext;

@WebListener
public class SpringContainerManager implements ServletContextListener{

	public static final String SPRING_CONTEXT="spring.context";
	
	private GenericXmlApplicationContext springContext;
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		//Start IoC Container
		System.out.println("Initialize IoC Container");
		
		//Add IoC Container to Application Scope
		//Servlet တိုင်းက Application Scope ထဲမှာ ရှိတဲ့ IoC container တွေကို ထုတ်ယူနိုင်အောင်လို့
		//application.xml ကနေ BeanConfig လုပ်
		//servletContext ထဲကို attribute သတ်မှတ်ပြီး ရလာတဲ့ bean ကို ထည့်
		//သူတို့ကို Servlet တွေကနေ ခေါ်သုံးနိုင်တယ်
		
		springContext=new GenericXmlApplicationContext("classpath:application.xml");
		sce.getServletContext().setAttribute(SPRING_CONTEXT, springContext);
		
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		//Close IoC Container
		System.out.println("Destroy IoC Container");
		
		if(null != springContext) {
			springContext.close();
		}
	}
}
