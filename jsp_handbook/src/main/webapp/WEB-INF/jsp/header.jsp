<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="ws" uri="wsTag" %>
<ul id="navigation_bar">
    <c:choose>
        <c:when test="${not empty sessionScope.user}">
            <li><a href="${pageContext.request.contextPath}/java_beans?command=LOG_OUT">Выйти</a></li>
            <c:set var="firstName" value="${sessionScope.user.firstName}"/>
            <c:set var="lastName" value="${sessionScope.user.lastName}"/>
            <li><a style="width: auto;background-color: #3C3C3C;"><ws:full-name name="${firstName}" lastName="${lastName}"/></a></li>
        </c:when>
        <c:otherwise>
            <li>
                <a href="${pageContext.request.contextPath}/java_beans?command=VK_AUTH">
                    <img src="${pageContext.request.contextPath}/assets/images/vk.png" alt="vk" width="20" height="20">
                </a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/java_beans?command=GOOGLE_AUTH">
                    <img src="${pageContext.request.contextPath}/assets/images/google.png" alt="google" width="20" height="20">
                </a>
            </li>
        </c:otherwise>
    </c:choose>
    <li class="system-name"><a>Hostel System</a></li>
</ul>