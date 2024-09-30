package com.pm.ctl;

import java.io.IOException;
import java.text.SimpleDateFormat;

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

@WebServlet("/MarksheetCtl.do")
public class MarksheetCtl extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");

		MarksheetModal model = new MarksheetModal();

		if (id != null) {
			try {
				MarksheetBean bean = model.findByPk(Integer.parseInt(id));
				req.setAttribute("bean", bean);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		RequestDispatcher rd = req.getRequestDispatcher("MarksheetView.jsp");
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String op = req.getParameter("operation");

		MarksheetBean bean = new MarksheetBean();

		bean.setRollNo(Integer.parseInt(req.getParameter("rollNo")));
		bean.setName(req.getParameter("name"));
		bean.setPhysics(Integer.parseInt(req.getParameter("physics")));
		bean.setChemistry(Integer.parseInt(req.getParameter("chemistry")));
		bean.setMaths(Integer.parseInt(req.getParameter("maths")));

		MarksheetModal model = new MarksheetModal();
		if (op.equals("save")) {
			try {
				model.add(bean);
				req.setAttribute("msg", "Marksheet Added Successfully...!");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (op.equals("update")) {
			try {
				System.out.println("op"+op);
				bean.setId(Integer.parseInt(req.getParameter("id")));
				model.update(bean);
				bean = model.findByPk(bean.getId());
				req.setAttribute("bean", bean);
				req.setAttribute("msg", "Marksheet Updated Successfully...!");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		RequestDispatcher rd = req.getRequestDispatcher("MarksheetView.jsp");
		rd.forward(req, resp);

	}

}
