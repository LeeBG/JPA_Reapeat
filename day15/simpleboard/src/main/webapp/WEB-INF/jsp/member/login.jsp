<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../layout/header.jsp"%>

<div class="container mt-5">
<h1>login</h1>

<form method="post">
    <p><input type="text" name="userId" placeholder="ID 입력" required></p>
    <p><input type="password" name="userPw" placeholder="PW 입력" required></p>
    <p><button class="btn btn-success">로그인</button></p>
</form>
</div>
</body>
</html>