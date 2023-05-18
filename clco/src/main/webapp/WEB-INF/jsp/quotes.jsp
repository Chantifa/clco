<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en" xmlns:c="">
<head>

    <link rel="stylesheet" type="text/css"
          href="webjars/bootstrap/3.3.7/css/bootstrap.min.css" />
    <c:url value="/css/main.css" var="jstlCss" />
    <link href="${jstlCss}" rel="stylesheet" />

</head>
<body>
<div class="container">
    <header>
        <h1>Clco Web Application</h1>
    </header>
    <div class="starter-template">
        <h1>Authors quote list</h1>
        <table
                class="table table-striped table-hover table-condensed table-bordered">
            <tr>
                <th>Author</th>
                <th>Quote</th>
            </tr>
            <c:forEach var="quote" items="${quotes}">
                <tr>
                    <td>${quote.author}</td>
                    <td>${quota.quote}</td>
                </tr>
            </c:forEach>
        </table>
    </div>

</div>

<script type="text/javascript"
        src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>

</html>