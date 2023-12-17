<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../layout/header.jsp" %>

<h1>list</h1>
<c:if test="${empty list}">잘 되나??</c:if>

<div class="container">
    <h2>게시글 목록</h2>
    <table class="table table-striped">
        <thead>
        <tr>
            <th>BoardId</th>
            <th>Title</th>
            <th>Writer</th>
            <th>상세보기</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${list}" var="dto">

                <tr>
                    <td>${dto.boardId}</td>
                    <td>${dto.title}</td>
                    <td>${dto.writer.username}</td>
                    <td><a href="${cpath}/board/view/${dto.boardId}"><button class="btn btn-primary">상세보기</button></a></td>
                </tr>

        </c:forEach>
        </tbody>
    </table>
</div>

</body>
</html>