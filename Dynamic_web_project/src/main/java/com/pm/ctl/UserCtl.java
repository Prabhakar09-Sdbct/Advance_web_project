package com.pm.ctl;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pm.bean.UserBean;
import com.pm.model.UserModal;

@WebServlet("/UserCtl")
public class UserCtl extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.sendRedirect("UserView.jsp");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		UserBean bean = new UserBean();
		
		bean.setFirstName(req.getParameter("firstName"));
		bean.setLastName(req.getParameter("lastName"));
		bean.setLoginId(req.getParameter("loginId"));
		bean.setPassword(req.getParameter("password"));
	    try {
			bean.setDob(sdf.parse(req.getParameter("dob")));
			
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		bean.setAddress(req.getParameter("address"));

		UserModal modal = new UserModal();
		try {
			modal.add(bean);
			req.setAttribute("msg", "User Added successfully..!!");
			RequestDispatcher rd = req.getRequestDispatcher("UserView.jsp");
			
			rd.forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		

	}

}
