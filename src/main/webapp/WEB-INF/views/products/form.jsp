<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Product Registration</title>
</head>
<body>
	<form method="post" action="/WebBookStore/products">
		<div>
			<label for="title">Title</label> <input type="text" name="title"
				id="title" />
		</div>
		<div>
			<label for="description">Description</label>
			<textarea rows="10" cols="20" name="description" id="description"></textarea>
		</div>
		<div>
			<label for="pages">Number of Pages</label> <input type="text"
				name="pages" id="pages" />
		</div>
		<div>
			<input type="submit" value="Save">
		</div>
	</form>
</body>
</html>