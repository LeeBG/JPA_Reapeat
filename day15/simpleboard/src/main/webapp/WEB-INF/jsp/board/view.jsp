<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../layout/header.jsp" %>
<c:if test="${empty login}">
    <c:redirect url="/board/list"/>
</c:if>
<div class="container">
    <div class="card mt-5">
        <div class="card-body">
            <h4 class="card-title">${dto.title}</h4>
            <p class="card-text">${dto.createDate}</p>
            <p class="card-text">${dto.member.username}</p>
            <textarea readonly rows="20" cols="130">${dto.content}</textarea>
        </div>
        <div>
            <c:if test="${login.id eq dto.member.id}">
                <a href="${cpath}/board/update/${dto.id}"><button class="btn btn-warning">수정하기</button></a>
                <a href="${cpath}/board/delete/${dto.id}"><button class="btn btn-danger">삭제하기</button></a>
            </c:if>
            <a href="${cpath}/board/list"><button class="btn btn-secondary">목록으로</button></a>
        </div>
    </div>

</div>

</body>
</html>