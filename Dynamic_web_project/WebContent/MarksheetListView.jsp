<%@page import="com.pm.bean.MarksheetBean"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
	List list = (List) request.getAttribute("list");
	int pageNo = (int) request.getAttribute("pageNo");
	int index = ((pageNo - 1) * 5) + 1;
	%>
	<%@ include file="Header.jsp"%>
	<form action="MarksheetListCtl.do" method="post">
		<div align="center">
			<%
				String msg = (String) request.getAttribute("msg");
			%>
			<h1>Marksheet List</h1>
			<%
				if (msg != null) {
			%>
			<h3>
				<font color="green"><%=msg%></font>
			</h3>
			<%
				}
			%>
		</div>
		<table>
			<tr>
				<th>Name</th>
				<td><input type="text" name="name"> <input
					type="submit" name="operation" value="search"></td>
			</tr>
		</table>
		<br>
		<table border="1%" style="width: 100%">
			<tr>
				<th>Select</th>
				<th>S. No.</th>
				<th>Roll No</th>
				<th>Name</th>
				<th>Physics</th>
				<th>Chemestry</th>
				<th>Maths</th>
				<th>edit</th>
			</tr>

			<%
			Iterator it = list.iterator();
			while (it.hasNext()) {
				MarksheetBean bean = (MarksheetBean) it.next();
			%>
			<tr>
				<td align="center"><input type="checkbox" name="ids"
					value="<%=bean.getId()%>"></td>
				<td align="center"><%=index++%></td>
				<td align="center"><%=bean.getRollNo()%></td>
				<td align="center"><%=bean.getName()%></td>
				<td align="center"><%=bean.getPhysics()%></td>
				<td align="center"><%=bean.getChemistry()%></td>
				<td align="center"><%=bean.getMaths()%></td>
				<td align="center"><a href="MarksheetCtl.do?id=<%=bean.getId()%>">edit</a></td>
			</tr>
			<%
			}
			%>
			<br>
			<table style="width: 100%">
				<tr>
					<td><input type="submit" name="operation" value="previous"
						<%=(pageNo == 1) ? "disabled" : ""%> /></td>
					<td style="width: 30%"><input type="submit" name="operation"
						value="add"></td>
					<td style="width: 25%"><input type="submit" name="operation"
						value="delete"></td>
					<td style="text-align: right;"><input
						<%=(list.size() == 0) ? "disabled" : ""%> type="submit"
						name="operation" value="next" /></td>
				</tr>
			</table>
			<input type="hidden" name="pageNo" value="<%=pageNo%>">
		</table>
	</form>
</body>
</html>