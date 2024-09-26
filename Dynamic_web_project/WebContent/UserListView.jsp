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
	%>
	<%@ include file="Header.jsp"%>
	<form action="UserListCtl" method="post">
		<div align="center">
			<h1>User List</h1>
		</div>
		<table>
			<tr>
				<th>FirstName</th>
				<td><input type="text" name="firstName"> <input
					type="submit" name="operation" value="search"></td>
			</tr>
		</table>
		<br>
		<table border="1%" style="width: 100%">
			<tr>
				<th>Id</th>
				<th>First Name</th>
				<th>Last Name</th>
				<th>Login Id</th>
				<th>Password</th>
				<th>Dob</th>
				<th>Address</th>
			</tr>

			<%
			Iterator it = list.iterator();
			while (it.hasNext()) {
				UserBean bean = (UserBean) it.next();
			%>
			<tr>
				<td align="center"><%=bean.getId()%></td>
				<td align="center"><%=bean.getFirstName()%></td>
				<td align="center"><%=bean.getLastName()%></td>
				<td align="center"><%=bean.getLoginId()%></td>
				<td align="center"><%=bean.getPassword()%></td>
				<td align="center"><%=bean.getDob()%></td>
				<td align="center"><%=bean.getAddress()%></td>
			</tr>
			<%
			}
			%>
			<br>
			<table style="width: 100%">
				<tr>
					<td><input type="submit" name="operation" value="previous" <%=(pageNo == 1) ? "disabled" : ""%> /></td>
					<td style="text-align: right;"><input <%=(list.size() == 0) ? "disabled" : ""%> type="submit"
						name="operation" value="next"  /></td>
				</tr>
			</table>
			<input type="hidden" name="pageNo" value="<%=pageNo%>">
		</table>
	</form>
</body>
</html>