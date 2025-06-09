<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <c:choose>
            <c:when test = "${todo != null}">
                <h2>id : ${todo.id} の詳細ページ</h2>
                <table>
                    <tbody>
                        <tr>
                            <th>やること</th>
                            <th><c:out value="${todo.content}"/></th>
                        </tr>
                        <tr>
                            <th>作成日時</th>
                            <th><fmt:formatDate value="${todo.created_at}" pattern="yyyy-MM-dd HH:mm:ss"/></th>
                        </tr>
                        <tr>
                            <th>完了フラグ</th>
                            <th>
                                <c:choose>
                                    <c:when test="${todo.done == false}">
                                        <span class="notdone">未完了</span>
                                    </c:when>
                                    <c:otherwise>
                                        完了
                                    </c:otherwise>
                                </c:choose>
                            </th>
                        </tr>
                    </tbody>
                </table>
                <p><a href="${pageContext.request.contextPath}/index">一覧に戻る</a></p>
                <p><a href="${pageContext.request.contextPath}/edit?id=${todo.id}">編集する</a></p>
            </c:when>
            <c:otherwise>
                <h2>お探しのデータは見つかりませんでした。</h2>
            </c:otherwise>
        </c:choose>
    </c:param>
</c:import>