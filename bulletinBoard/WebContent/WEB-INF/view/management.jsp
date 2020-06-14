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
	<title>投稿管理</title>
</head>

<body>
	<div class="right"><button onclick="location.href='http://localhost:8080/bulletinBoard/'">TOPへ戻る</button></div>
<%	ArrayList<PostBean> list = PostDao.selectAllPost();
	for(int i = list.size()-1 ; i >= 0 ; i--){
		PostBean post = list.get(i); %>
		<div class="post">
			<strong>
				<%=i + 1 %>）　投稿者：<%=post.getName() %>　　　投稿時間：<%=post.getPostTime() %>　　　編集時間：<%=post.getUpdateTime() == null ? "" : post.getUpdateTime() %>
			</strong>
			<span class="post-right">
				<button onclick="location.href='./editSelect?id=<%=post.getId() %>'">編集</button> 　
				<button onclick="location.href='./delete?id=<%=post.getId() %>'">削除</button>
			</span><br>
			<span class="content"><%=post.getContent() %></span>
		</div>
<%	}%>
</body>
</html>