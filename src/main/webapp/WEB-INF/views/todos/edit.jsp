<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <c:choose>
            <c:when test = "${todo != null}">
                <h2>id : ${todo.id} の編集ページ</h2>
        
                <form method="POST" action="${pageContext.request.contextPath}/update">
                    <c:import url="_form.jsp" />
                    <%-- 完了or未完了チェック --%>
                    <label> <input type="checkbox" name="done"
                        <c:if test="${todo.done}">checked</c:if> /> 完了
                    </label>
                </form>
                
                <p><a href="${pageContext.request.contextPath}/index">一覧に戻る</a></p>
            </c:when>
            <c:otherwise>
                <h2>お探しのデータは見つかりませんでした。</h2>
            </c:otherwise>
        </c:choose>
    </c:param>
</c:import>