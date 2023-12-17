<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../layout/header.jsp" %>
<div class="container">
    <form method="post">
        <h2 class="mt-5">게시글 작성</h2>
        <div class="input-group mt-5">
            <p>
                <label>제목:</label>
            <input type="text" class="form-control w-100" name="title" required>
            </p>
            <p><label>내용:</label>
            <textarea id="summernote" name="content" rows="20" cols="150" class="form-control" required></textarea>
            </p>
            <input type="hidden" name="memberId" value="${login.id}">
        </div>
        <p>
            <button class="btn btn-success">글쓰기</button>
        </p>
    </form>
</div>
<script>
    $(document).ready(function () {
        $('#summernote').summernote();
    });
</script>

</body>
</html>