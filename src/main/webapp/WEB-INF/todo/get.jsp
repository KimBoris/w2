<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@include file="../includes/header.jsp"%>

${todo}

<a href="/todo/edit?tno=${todo.tno}">Modify/Delete</a>

<%@include file="../includes/footer.jsp"%>