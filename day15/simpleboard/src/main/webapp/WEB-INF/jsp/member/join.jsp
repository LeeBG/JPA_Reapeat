<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../layout/header.jsp"%>
<h1>join</h1>

<div>
    <form method="post">
        <p><input type="text" name="userId" placeholder="ID를 입력하세요" required></p>
        <p><input type="password" name="userPw" placeholder="Pw를 입력하세요" required></p>
        <p><input type="text" name="address" placeholder="주소를 입력하세요" required></p>
        <p><input type="number" name="age" placeholder="나이 입력하세요" required></p>
        <p><input type="text" name="phoneNum" placeholder="전화번호입력하세요" required></p>
        <p><input type="text" name="username" placeholder="이름을 입력하세요" required></p>
        <p><input type="email" name="email" placeholder="이메일 입력하세요" required></p>
        <p><input type="submit" value="회원가입"></p>
    </form>
</div>

</body>
</html>