<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="bean.PostBean"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>投稿編集</title>
</head>

<body>
<%	PostBean post = (PostBean)session.getAttribute("edit_target"); %>
	<form action="<%=request.getContextPath() + "/editConfirm" %>">
		投稿者名：<br>
			<input class="text-input" type="text" name="name" value="<%=post.getName() %>" maxlength="30"><br>
		e-mail：<br>
			<input class="text-input" type="email" name="mail" value="<%=post.getMail() %>" maxlength="60"><br>
		内容：<br>
			<textarea name="content" maxlength="250"><%=post.getContent() %></textarea><br>
		<input id="button" type="submit" value="変更確定">
	</form>
</body>
</html>