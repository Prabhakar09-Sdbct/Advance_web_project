package com.pm.ctl;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pm.bean.UserBean;
import com.pm.model.UserModal;

@WebServlet("/LoginCtl")
public class LoginCtl extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.sendRedirect("LoginView.jsp");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy");
		
		UserBean bean = new UserBean();
		
		bean.setLoginId(req.getParameter("loginId"));
		bean.setPassword(req.getParameter("password"));

		UserModal modal = new UserModal();
		try {
			modal.add(bean);
			req.setAttribute("msg", "Login successfully..!!");
			RequestDispatcher rd = req.getRequestDispatcher("LoginView.jsp");
			
			rd.forward(req, resp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
