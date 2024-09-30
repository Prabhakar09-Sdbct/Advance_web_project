package com.pm.ctl;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pm.bean.MarksheetBean;
import com.pm.bean.UserBean;
import com.pm.model.MarksheetModal;
import com.pm.model.UserModal;

@WebServlet("/MarksheetListCtl.do")
public class MarksheetListCtl extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		MarksheetModal model = new MarksheetModal();

		MarksheetBean bean = null;
		int pageNo = 1;

		int pageSige = 5;

		try {
			List list = model.search(bean, pageNo, pageSige);
			req.setAttribute("list", list);
			req.setAttribute("pageNo", pageNo);
			RequestDispatcher rd = req.getRequestDispatcher("MarksheetListView.jsp");
			rd.forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		MarksheetModal model = new MarksheetModal();

		String op = req.getParameter("operation");

		MarksheetBean bean = null;

		int pageNo = 1;

		int pageSige = 5;

		if (op.equals("search")) {
			bean = new MarksheetBean();
			bean.setName(req.getParameter("name"));
		}

		if (op.equals("next")) {
			System.out.println("req.getParameter(\"pageNo\")" + req.getParameter("pageNo"));
			pageNo = Integer.parseInt(req.getParameter("pageNo"));
			pageNo++;
		}

		if (op.equals("previous")) {
			pageNo = Integer.parseInt(req.getParameter("pageNo"));
			pageNo--;
		}

		if (op.equals("add")) {
			resp.sendRedirect("MarksheetView.jsp");
		}

		if (op.equals("delete")) {
			String[] ids = req.getParameterValues("ids");

			for (String id : ids) {
				try {
					model.delete(Integer.parseInt(id));
					req.setAttribute("msg", "Data deleted successfully");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		try {
			List list = model.search(bean, pageNo, pageSige);

			req.setAttribute("list", list);
			req.setAttribute("pageNo", pageNo);
			RequestDispatcher rd = req.getRequestDispatcher("MarksheetListView.jsp");
			rd.forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
