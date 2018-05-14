<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="ws" uri="wsTag" %>

<details itemscope itemtype="http://schema.org/ItemList">
    <summary itemtype="Text"><ws:entity-name type="method"/></summary>
    <ul>
        <c:forEach items="${requestScope.methods}" var="method">
            <li>
                <a itemprop="url" href="${pageContext.request.contextPath}/java_beans?command=GET_METHOD&id=${method.id}">${method.name}</a>
            </li>
        </c:forEach>
        <li style="list-style: none;"><a itemprop="url" href="${pageContext.request.contextPath}/java_beans?command=MODIFY&action=ADD_METHOD&typeOld=method">+ Добавить</a></li>
    </ul>
</details>
<details itemscope itemtype="http://schema.org/ItemList">
    <summary itemtype="Text"><ws:entity-name type="property"/></summary>
    <ul>
        <c:forEach items="${requestScope.property}" var="property">
            <li>
                <a itemprop="url" href="${pageContext.request.contextPath}/java_beans?command=GET_ENTITY&id=${property.id}&type=property">${property.name}</a>
            </li>
        </c:forEach>
        <li style="list-style: none;"><a itemprop="url" href="${pageContext.request.contextPath}/java_beans?command=MODIFY&action=ADD_ENTITY&typeOld=property">+ Добавить</a></li>
    </ul>
</details>
<details itemscope itemtype="http://schema.org/ItemList">
    <summary itemtype="Text"><ws:entity-name type="rule"/></summary>
    <ul>
        <c:forEach items="${requestScope.rule}" var="rule">
            <li>
                <a itemprop="url" href="${pageContext.request.contextPath}/java_beans?command=GET_ENTITY&id=${rule.id}&type=rule">${rule.name}</a>
            </li>
        </c:forEach>
        <li style="list-style: none;"><a itemprop="url" href="${pageContext.request.contextPath}/java_beans?command=MODIFY&action=ADD_ENTITY&typeOld=rule">+ Добавить</a></li>
    </ul>
</details>