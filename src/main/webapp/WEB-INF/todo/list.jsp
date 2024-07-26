<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../includes/header.jsp" %>


<%--${nums}--%>

<%--${obj}--%>

<h1>Todo List Page</h1>

<h1>${pageInfo.start}</h1>
<h1>${pageInfo.end}</h1>
<h1>${pageInfo.prev}</h1>
<h1>${pageInfo.next}</h1>

<ul class="pagination">
    <c:if test="${pageInfo.prev}">
        <li class="page-item"><a class="page-link" href="/todo/list?page=${pageInfo.start -1}">Previous</a></li>
    </c:if>

    <c:forEach begin="${pageInfo.start}" end="${pageInfo.end}" var="num">
        <li class="page-item ${pageInfo.page == num ?'active':''}"><a class="page-link" href="/todo/list?page=${num}">${num}</a></li>
        <%--<c:out> 을 쓰는것이 안전한데 num 은 서버내부에서 만든 값이다 크로스 사이트 스크립팅이 가능한값이 아니다.
        크게 문제가 되지 않을 것 같다.--%>

    </c:forEach>

    <c:if test="${pageInfo.next}">
        <li class="page-item"><a class="page-link" href="/todo/list?page=${pageInfo.end +1}">Next</a></li>
    </c:if>
</ul>


<%--<c:out value="${tag}"/>

${tag}


<ul>
    <c:forEach items="${nums}" var="num">
        <li class="button ${num %2 == 0?'EVEN':'ODD'}">AAA ${nums}</li>
    </c:forEach>
</ul>--%>
<%--
<h2>${obj.subject1}</h2>
<h2>${obj.subject2}</h2>
<h2>${obj.subject3}</h2>
<h2>${obj.subject4}</h2>
<h2>${obj.subject5}</h2>
--%>

<%@include file="../includes/footer.jsp" %>

