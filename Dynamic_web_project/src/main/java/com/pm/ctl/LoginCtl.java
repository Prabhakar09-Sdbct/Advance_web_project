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
import javax.servlet.http.HttpSession;

import com.pm.bean.UserBean;
import com.pm.model.UserModal;

@WebServlet("/LoginCtl")
public class LoginCtl extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String op = req.getParameter("operation");

		if (op != null && op.equals("logout")) {
			HttpSession session = req.getSession();
			session.invalidate();
		}
		resp.sendRedirect("LoginView.jsp");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		String loginId = req.getParameter("loginId");
		String password = req.getParameter("password");

		String op = req.getParameter("operation");

		if (op.equals("signIn")) {
			UserModal model = new UserModal();

			HttpSession session = req.getSession();

			try {
				UserBean bean = model.authenticate(loginId, password);
				if (bean != null) {
					session.setAttribute("user", bean);
					RequestDispatcher rd = req.getRequestDispatcher("Welcome.jsp");
					rd.forward(req, resp);
				} else {
					req.setAttribute("msg", "login id & password invalid");
					RequestDispatcher rd = req.getRequestDispatcher("LoginView.jsp");
					rd.forward(req, resp);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if(op.equals("signUp")) {
			resp.sendRedirect("UserRegistrationCtl");
		}

	}

}
