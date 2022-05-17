<%--
  Created by IntelliJ IDEA.
  User: plche
  Date: 15/03/22
  Time: 01:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Edit Course</title>
        <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
        <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
    </head>
    <body>
        <div class="row justify-content-center mt-4">
            <div class="col-4">
                <div class="row">
                    <div class="col-8">
                        <h2 class="text-primary mb-3">Edit Course</h2>
                    </div>
                    <div class="col-4 align-self-center">
                        <form class="d-flex justify-content-end" action="/courses">
                            <button class="btn btn-sm btn-outline-warning">back to courses</button>
                        </form>
                    </div>
                </div>
                <form:form class="rounded border border-5 border-primary" action="/courses/${course.id}/save" method="post" modelAttribute="course">
                    <input type="hidden" name="_method" value="put">
                    <div class="row justify-content-center">
                        <div class="col-9">
                            <form:label for="name" path="name" class="form-label mt-3">Name:</form:label>
                            <form:errors path="name" cssClass="text-danger" />
                            <form:input type="text" class="form-control mb-3" id="name" path="name" aria-label="Course's name" />
                            <form:label for="instructor" path="instructor" class="form-label">Instructor:</form:label>
                            <form:errors path="instructor" cssClass="text-danger" />
                            <form:input type="text" class="form-control mb-3" id="instructor" path="instructor" aria-label="Course's instructor"/>
                            <form:label for="capacity" path="capacity" class="form-label">Capacity:</form:label>
                            <form:errors path="capacity" cssClass="text-danger" />
                            <div class="col-4">
                                <form:input type="number" class="form-control mb-3" id="capacity" path="capacity" aria-label="Project's capacity date" />
                            </div>
                            <div class="d-flex justify-content-end">
                                <button type="submit" class="btn btn-primary mb-3">Update</button>
                            </div>
                        </div>
                    </div>
                </form:form>
            </div>
        </div>
    </body>
</html>
