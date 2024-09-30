package com.pm.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.pm.bean.MarksheetBean;
import com.pm.bean.UserBean;
import com.pm.util.JDBCDataSourceRb;

public class MarksheetModal {

	public void add(MarksheetBean bean) throws Exception {
		
		MarksheetBean checkDuplicate =  findByRollNo(bean.getRollNo());
		
		if(checkDuplicate != null) {
			throw new RuntimeException("Roll no already exist !");
		}

		Class.forName("com.mysql.cj.jdbc.Driver");

		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");

		PreparedStatement pr = con.prepareStatement("insert into marksheet values (?, ?, ?, ?, ?, ?)");

		pr.setInt(1, bean.getId());
		pr.setInt(2, bean.getRollNo());
		pr.setString(3, bean.getName());
		pr.setInt(4, bean.getPhysics());
		pr.setInt(5, bean.getChemistry());
		pr.setInt(6, bean.getMaths());

		int i = pr.executeUpdate();

		System.out.println("data inserted = " + i);
	}

	public void update(MarksheetBean bean) throws Exception {

		Class.forName("com.mysql.cj.jdbc.Driver");

		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");

		PreparedStatement pr = con.prepareStatement(
				"update marksheet set roll_no = ?, name = ?, physics = ?, chemistry = ?, maths = ? where id = ?");

		pr.setInt(1, bean.getRollNo());
		pr.setString(2, bean.getName());
		pr.setInt(3, bean.getPhysics());
		pr.setInt(4, bean.getChemistry());
		pr.setInt(5, bean.getMaths());
		pr.setInt(6, bean.getId());

		int i = pr.executeUpdate();

		System.out.println("data updated = " + i);
	}

	public void delete(int id) throws Exception {

		Class.forName("com.mysql.cj.jdbc.Driver");

		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");

		PreparedStatement pr = con.prepareStatement("delete from marksheet where id = ?");

		pr.setInt(1, id);

		int i = pr.executeUpdate();

		System.out.println("data deleted = " + i);
	}

	public MarksheetBean findByPk(int id) throws Exception {

		Class.forName("com.mysql.cj.jdbc.Driver");

		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");

		PreparedStatement pr = con.prepareStatement("select * from marksheet where id = ?");

		pr.setInt(1, id);

		ResultSet rs = pr.executeQuery();

		MarksheetBean bean = null;

		while (rs.next()) {
			bean = new MarksheetBean();
			bean.setId(rs.getInt(1));
			bean.setRollNo(rs.getInt(2));
			bean.setName(rs.getString(3));
			bean.setPhysics(rs.getInt(4));
			bean.setChemistry(rs.getInt(5));
			bean.setMaths(rs.getInt(6));
			
			
		}
		return bean;
	}
	
	public List search(MarksheetBean bean, int pageNo, int pageSize) throws Exception {
		Connection con = JDBCDataSourceRb.getConnection();
		StringBuffer sql = new StringBuffer("select * from marksheet where 1=1");

		if (bean != null) {

			
		}
		if (bean != null) {
			if (bean.getRollNo() > 0) {
				sql.append(" and roll_no like '" + bean.getRollNo() + "%'");
			}

			if (bean.getName() != null && bean.getName().length() > 0) {
				sql.append(" and name like '" + bean.getName() + "%'");
			}
		}

		if (pageSize > 0) {
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" limit " + pageNo + "," + pageSize);
		}

		System.out.println("sql"+sql.toString());
		
		PreparedStatement psmt = con.prepareStatement(sql.toString());

		List list = new ArrayList();
		ResultSet rs = psmt.executeQuery();
		while (rs.next()) {
			bean = new MarksheetBean();
			bean.setId(rs.getInt(1));
			bean.setRollNo(rs.getInt(2));
			bean.setName(rs.getString(3));
			bean.setPhysics(rs.getInt(4));
			bean.setChemistry(rs.getInt(5));
			bean.setMaths(rs.getInt(6));
			list.add(bean);
		}

		return list;
	}
	
	public MarksheetBean findByRollNo(int id) throws Exception {

		Class.forName("com.mysql.cj.jdbc.Driver");

		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");

		PreparedStatement pr = con.prepareStatement("select * from marksheet where roll_no = ?");

		pr.setInt(1, id);

		ResultSet rs = pr.executeQuery();

		MarksheetBean bean = null;

		while (rs.next()) {
			bean = new MarksheetBean();
			bean.setId(rs.getInt(1));
			bean.setRollNo(rs.getInt(2));
			bean.setName(rs.getString(3));
			bean.setPhysics(rs.getInt(4));
			bean.setChemistry(rs.getInt(5));
			bean.setMaths(rs.getInt(6));
		}
		return bean;
	}

}
