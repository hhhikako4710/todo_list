<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <h2>ToDo一覧</h2>
        <ul>
            <c:forEach var="todo" items="${todos}">
                <li>
                    <a href="${pageContext.request.contextPath}/show?id=${todo.id}">
                        <c:out value="${todo.id}" />
                    </a>
                    ：<c:out value="${todo.content}" />
                </li>
            </c:forEach>
        </ul>

        <div id="pagination">
            （全 ${todos_count} 件）<br />
            <c:forEach var="i" begin="1" end="${((todos_count - 1) / 15) + 1}" step="1">
                <c:choose>
                    <c:when test="${i == page}">
                        <c:out value="${i}" />&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a href="${pageContext.request.contextPath}/index?page=${i}"><c:out value="${i}" /></a>&nbsp;
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>
        <p><a href="${pageContext.request.contextPath}/new">新規ToDoの投稿</a></p>

    </c:param>
</c:import>