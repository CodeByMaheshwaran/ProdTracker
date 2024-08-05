package com.maheshtech.web.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.maheshtech.service.GreetingService;
@WebServlet("/greet")
public class GreetingController extends HttpServlet {

	private static final long serialVersionUID = 1L;
  
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//Responsibility of Servlet Controller
		// 1.Read the input from the request
		 String name=req.getParameter("name");
		
		// 2.Invoke the Model method to get a model data
		 GreetingService service=new GreetingService();
		 String msg=service.getMessage(name);
		 // 3.Store the model in a scope accessible to view
		 req.setAttribute("message", msg);
		// 4.forward the request to View
		 RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/pages/greet.jsp");
		 rd.forward(req, resp);
	}
	
}
