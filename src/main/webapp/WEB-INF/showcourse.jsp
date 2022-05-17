<%--
  Created by IntelliJ IDEA.
  User: plche
  Date: 15/03/22
  Time: 20:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Course Details</title>
        <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
        <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
    </head>
    <body>
        <%-- body --%>
        <%-- Courses Details Table --%>
        <div class="col-6 offset-3 d-flex justify-content-between">
            <span class="fs-1 fw-bolder align-self-center">Course Details</span>
            <div>
                <a href="/courses" class="btn btn-outline-primary mt-3">Back to Courses</a>
            </div>
        </div>
        <div class="container border col-6 mt-3">
            <div class="container col-12 bg-white p-3">
<%--                <h1><c:out value="${course.name}" /></h1>--%>
                <h1><c:out value="${rows.get(0)[1]}" /></h1>
                <table class="table p-2">
                    <tbody>
                        <tr>
                            <td>Instructor:</td>
                            <td><c:out value="${rows.get(0)[1]}" /></td>
                        </tr>
                        <tr>
                            <td>Sign Ups:</td>
                            <td><c:out value="${rows.size()}" /></td>
                        </tr>
                    </tbody>
                </table>
                <table class="table table-striped p-2">
                    <thead>
                        <tr>
                            <th scope="col">Name</th>
                            <th scope="col">Sign Up Date</th>
                            <th scope="col">Action</th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="row" items="${rows}">
                        <tr>
                            <td><c:out value="${row[2]}" /></td>
                            <td><fmt:formatDate pattern="MMM d, YYYY" value="${row[3]}" /></td>
                            <td>
                                <c:if test="${row[5] eq userLoggedIn.id}">
                                    <form action="/courses/${row[4]}/leave" method="post">
                                        <button class="btn btn-sm btn-outline-danger">Remove</button>
                                    </form>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <div class="btn-group" role="group">
                    <form action="/courses/${rows.get(0)[4]}/edit">
                        <button class="btn btn-primary">Edit</button>
                    </form>
                    <form action="/courses/${rows.get(0)[4]}/delete" method="post">
                        <input type="hidden" name="_method" value="delete">
                        <button type="submit" class="btn btn-danger">Delete</button>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
