<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>User Registration</title>
<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f4f4f4;
	margin: 0;
	padding: 0;
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh;
}

.form-container {
	background-color: #fff;
	padding: 20px;
	border-radius: 8px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
	width: 400px;
}

.form-container h1 {
	text-align: center;
	margin-bottom: 20px;
}

.form-group {
	margin-bottom: 15px;
}

.form-group label {
	display: block;
	font-weight: bold;
	margin-bottom: 5px;
}

.form-group input[type="text"], .form-group input[type="date"],
	.form-group input[type="password"] {
	width: calc(100% - 22px);
	padding: 10px;
	border: 1px solid #ccc;
	border-radius: 4px;
	box-sizing: border-box;
}

.form-group input[type="submit"] {
	width: 100%;
	padding: 10px;
	border: none;
	border-radius: 4px;
	background-color: #28a745;
	color: white;
	font-size: 16px;
	cursor: pointer;
}

.form-group input[type="submit"]:hover {
	background-color: #218838;
}
</style>
</head>
<body>
	<div class="form-container">
		<h1>User Registration</h1>
		<form action="UserRegistrationCtl" method="post">
			<div class="form-group">
				<label for="firstName">First Name:</label> <input type="text"
					id="firstName" name="firstName">
			</div>
			<div class="form-group">
				<label for="lastName">Last Name:</label> <input type="text"
					id="lastName" name="lastName">
			</div>
			<div class="form-group">
				<label for="loginId">Login Id:</label> <input type="text"
					id="loginId" name="loginId">
			</div>
			<div class="form-group">
				<label for="password">Password:</label> <input type="password"
					id="password" name="password">
			</div>
			<div class="form-group">
				<label for="dob">DOB:</label> <input type="date" id="dob" name="dob">
			</div>
			<div class="form-group">
				<label for="address">Address:</label> <input type="text"
					id="address" name="address">
			</div>
			<div class="form-group">
				<input type="submit" value="Register">
			</div>
		</form>
	</div>
</body>
</html>
