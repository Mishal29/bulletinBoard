<%@page import="servlet.NewPostServlet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList,
				 bean.PostBean,
				 dao.PostDao" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="./css/style.css">
	<title>掲示板</title>
</head>

<body>
<%	String posterName = request.getAttribute("poster_name") == null ? "" : (String)request.getAttribute("poster_name"); %>
<%	String posterMail = request.getAttribute("poster_mail") == null ? "" : (String)request.getAttribute("poster_mail"); %>
<%	boolean error = request.getAttribute("file_error") == null ? false : (boolean)request.getAttribute("file_error"); %>
	<div class="right"><button onclick="location.href='./management'">管理画面へ</button></div>
	<div id="form-center">
		<div id="form-left">
			<form action="<%=request.getContextPath() + "/newPost" %>" method="post" enctype="multipart/form-data">
				投稿者名：<br>
					<input id="name" class="text-input" type="text" name="name" value="<%=posterName %>" maxlength="30"><br>
				e-mail：<br>
					<input id="mail" class="text-input" type="email" name="mail" value="<%=posterMail %>" maxlength="60"><br>
				内容：<br>
					<textarea id="content" name="content" maxlength="250"></textarea><br>
				<input type="file" name="file"><br>
				<%if(error){ %>
					<span id="error">対象外のファイル形式です。</span>
				<%} %>
				<input id="button" type="submit" value="投稿">
			</form>
		</div>
	</div>
	<div id="line"></div>

<%	ArrayList<PostBean> list = PostDao.selectAllPost();
	for(int i = list.size()-1 ; i >= 0 ; i--){
		PostBean post = list.get(i); %>
		<div class="post">
			<strong>
				<%=i + 1 %>）　投稿者：<%=post.getName() %>　　　投稿時間：<%=post.getPostTime() %>　　　編集時間：<%=post.getUpdateTime() == null ? "" : post.getUpdateTime() %><br>
			</strong>
			<span class="content"><%=post.getContent() %></span><br>
			<span class="file">
				<%if(post.getFile() != null){
					if(NewPostServlet.imgExtensionSet.contains(post.getFile().substring(post.getFile().lastIndexOf(".")))){ %>
						<img src="<%=request.getContextPath() + "/upload/" + post.getFile() %>">
					<%}else if(NewPostServlet.docExtensionSet.contains(post.getFile().substring(post.getFile().lastIndexOf(".")))){ %>
						<a href="<%=request.getContextPath() + "/upload/" + post.getFile() %>"><%=post.getFile() %></a>
					<%}
				} %>
			</span>
		</div>
<%	}%>
</body>
</html>