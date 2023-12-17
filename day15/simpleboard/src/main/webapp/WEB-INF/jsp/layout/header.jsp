<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="cpath" value="${pageContext.request.contextPath}" />
<html>
<head>
    <title>${login}</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

<%--    <link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">--%>
<%--    <script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>--%>
</head>
<body>
<nav class="navbar navbar-expand-sm navbar-dark bg-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="${cpath}/">Board</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#mynavbar">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="mynavbar">
            <ul class="navbar-nav me-auto">
                <c:if test="${empty login}">
                    <li class="nav-item">
                        <a class="nav-link" href="${cpath}/member/login">Login</a>
                    </li>
                </c:if>
                <c:if test="${not empty login}">
                <li class="nav-item">
                    <a class="nav-link" href="${cpath}/member/logout">Logout</a>
                </li>
                </c:if>
                <c:if test="${empty login}">
                <li class="nav-item">
                    <a class="nav-link" href="${cpath}/member/join">Join</a>
                </li>
                </c:if>
                <li class="nav-item">
                    <a class="nav-link" href="${cpath}/board/list">List</a>
                </li>
                <c:if test="${not empty login}">
                <li class="nav-item">
                    <a class="nav-link" href="${cpath}/board/write">Write</a>
                </li>
                </c:if>
            </ul>
            <form class="d-flex">
                <input class="form-control me-2" type="text" placeholder="Search">
                <button class="btn btn-primary" type="button">Search</button>
            </form>
        </div>
    </div>
</nav>
