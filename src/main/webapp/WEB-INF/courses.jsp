<%--
  Created by IntelliJ IDEA.
  User: plche
  Date: 15/03/22
  Time: 16:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Courses</title>
        <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
        <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
    </head>
    <body>
        <div class="row mt-2">
            <div class="col-6 offset-3">
                <%-- header --%>
                <div class="col d-flex justify-content-between">
                    <span class="fs-1 fw-bolder">Welcome, ${userLoggedIn.userName}!</span>
                    <%-- logout --%>
                    <div class="align-self-center">
                        <a href="/logout" class="btn btn-outline-danger">Logout</a>
                    </div>
                </div>
            </div>
        </div>
        <%-- body --%>
        <%-- All Courses Table --%>
        <div class="col-6 offset-3 d-flex justify-content-between">
            <span class="align-self-center">Courses</span>
        </div>
        <div class="container border col-6 mt-3">
            <div class="container col-12 bg-white p-3">
                <table class="table table-striped p-2">
                    <thead>
                        <tr>
                            <th scope="col">Course</th>
                            <th scope="col">Instructor</th>
                            <th scope="col">Signups</th>
                            <th scope="col">Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="course" items="${courses}">
                            <tr>
                                <td><a href="/courses/${course.id}"><c:out value="${course.name}" /></a></td>
                                <td><c:out value="${course.instructor}" /></td>
                                <td><c:out value="${course.users.size()}" />/<c:out value="${course.capacity}" /></td>
                                <td>
                                    <c:if test="${(not course.users.contains(userLoggedIn)) and (course.capacity ne course.users.size())}">
                                        <form action="/courses/${course.id}/add" method="post">
                                            <button type="submit" class="btn btn-sm btn-outline-info">Add</button>
                                        </form>
                                    </c:if>
                                    <c:if test="${course.users.contains(userLoggedIn)}">Already Added</c:if>
                                    <c:if test="${(course.capacity eq course.users.size()) and not course.users.contains(userLoggedIn)}">Full</c:if>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="offset-3 mt-3">
            <a href="/courses/new" class="btn btn-primary">Add a course</a>
        </div>
    </body>
</html>
