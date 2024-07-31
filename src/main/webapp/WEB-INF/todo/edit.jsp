<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@include file="../includes/header.jsp"%>

<h1>Edit Page</h1>

${todo}
<form action="/todo/edit" method="post">
    <div>
        <label>TNO</label>
        <input type="text" name="tno" value="${todo.tno}" readonly>
    </div>
    <div>
        <label>TITLE</label>
        <input type="text" name="title" value="${todo.title}">
    </div>
    <div>
        <label>WRITER</label>
        <input type="text" name="writer" value="${todo.writer}">
    </div>
    <div>
        <button>MODIFY</button>
    </div>
</form>

<%--폼태그 안에있는 버튼은 전송 전용이다.--%>
<form action="/todo/remove" method="post">
    <input type="hidden" name="tno"  value="${todo.tno}" >
    <button>REMOVE</button>
</form>

<%@include file="../includes/footer.jsp"%>
