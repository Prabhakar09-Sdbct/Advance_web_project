package com.pm.ctl;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pm.bean.UserBean;
import com.pm.model.UserModal;

@WebServlet("/UserListCtl")
public class UserListCtl extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UserModal model = new UserModal();

		UserBean bean = null;
		int pageNo = 1;

		int pageSige = 5;

		try {
			List list = model.search(bean, pageNo, pageSige);
			req.setAttribute("list", list);
			req.setAttribute("pageNo", pageNo);
			RequestDispatcher rd = req.getRequestDispatcher("UserListView.jsp");
			rd.forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		UserModal model = new UserModal();

		String op = req.getParameter("operation");

		UserBean bean = null;

		int pageNo = 1;

		int pageSige = 5;

		if (op.equals("search")) {
			bean = new UserBean();
			bean.setFirstName(req.getParameter("firstName"));
		}

		if (op.equals("next")) {
			System.out.println("req.getParameter(\"pageNo\")"+req.getParameter("pageNo"));
			pageNo = Integer.parseInt(req.getParameter("pageNo"));
			pageNo++;
		}

		if (op.equals("previous")) {
			pageNo = Integer.parseInt(req.getParameter("pageNo"));
			pageNo--;
		}

		try {
			List list = model.search(bean, pageNo, pageSige);

			req.setAttribute("list", list);
			req.setAttribute("pageNo", pageNo);
			RequestDispatcher rd = req.getRequestDispatcher("UserListView.jsp");
			rd.forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
