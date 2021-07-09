<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<h1>hello!</h1>

City List
<hr>

<table border="1">
    <tr>
        <th>Id</th>
        <th>name</th>
    </tr>

        <c:forEach var="city" items="${cityList}">
            <tr><th>${city.id}</th>
                <th>${city.name}</th></tr>

        </c:forEach>

</table>

</html>