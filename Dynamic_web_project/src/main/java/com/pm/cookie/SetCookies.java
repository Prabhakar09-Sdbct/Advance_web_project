package com.pm.cookie;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Set")
public class SetCookies extends HttpServlet {
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 String name = request.getParameter("name");
		 String value = request.getParameter("value");
		 

			System.out.println("name>>>>>"+name);
		
		 Cookie c = new Cookie(name, value);
		 
		 response.addCookie(c);
	}
}
