<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="mysql.ReceiptDaoSql" %>
<%@ page import="java.util.List" %>
<%@ page import="entity.Order" %>
<%@ page import="entity.Dish" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    ReceiptDaoSql rd=new ReceiptDaoSql();
    List<Order>ordersL=rd.getAllOrders();
%>
<style>
    .table table-striped{
        width: 100%;
        max-width: 100%;
        margin-bottom: 1rem;
        background-color: transparent;
        margin-top: 50px;
    }

    .container my-3{
        margin-top: 150px;
    }
     .between{
         height: 100px;
     }
</style>
<html>
<head>
    <meta charset="utf-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">

    <title>Order list</title>
    <meta content="" name="description">
    <meta content="" name="keywords">

    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Open+Sans:ital,wght@0,300;0,400;0,500;0,600;0,700;1,300;1,400;1,600;1,700&family=Amatic+SC:ital,wght@0,300;0,400;0,500;0,600;0,700;1,300;1,400;1,500;1,600;1,700&family=Inter:ital,wght@0,300;0,400;0,500;0,600;0,700;1,300;1,400;1,500;1,600;1,700&display=swap" rel="stylesheet">

    <!-- Vendor CSS Files -->
    <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
    <link href="assets/vendor/aos/aos.css" rel="stylesheet">
    <link href="assets/vendor/glightbox/css/glightbox.min.css" rel="stylesheet">
    <link href="assets/vendor/swiper/swiper-bundle.min.css" rel="stylesheet">

    <!-- Template Main CSS File -->
    <link href="css/mainCSS.css" rel="stylesheet">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
</head>
<body>
<%@include file="headerForAdmin.jspf" %>
<div class="between"></div>
<div class="container my-3">
<table class="table table-striped">
    <thead>
    <tr>
        <th scope="col">id</th>
        <th scope="col">Client Id</th>
        <th scope="col">Manager Id</th>
        <th scope="col">Status</th>
        <th scope="col">Create Date</th>
        <th scope="col">Dishes</th>
        <th scope="col">Total</th>
    </tr>
    </thead>
    <tbody>

    <%
        if (ordersL!=null){
            for (Order o:ordersL) {%>
    <tr>
            <th><%=o.getId()%></th>
            <th><%=o.getClientId()%></th>
            <th><%=o.getManagerId()%></th>
            <th>
                        <%=o.getStatus()%>
                    <%
                    if(o.getStatus().getValue()!="Delivering"){
                        System.out.println(o.getStatus().getValue());
                        %>
                       <form method="get" action="status">
                            <input value="<%=o.getId()%>" name="id" style="display: none">
                            <input value="<%=o.getStatus().ordinal() + 2%>" name="status_id" style="display: none">
                        <input type="submit" value="Next status">
                    </form>
                <%
                }
                %>
            </th>
            <th><%=o.getCreateDate()%></th>
            <th>

                <%
                    ReceiptDaoSql rds=new ReceiptDaoSql();
                    List<Dish> allDishes=rds.getAllOrderDishes(o.getId());
                    System.out.println(allDishes);
                    for (Dish d:allDishes) {
                        %>
                        <p><%=d.getName()%>: <%=d.getPrice()%>$</p>
                   <%
                    }
                    %>
            </th>
            <th><%=o.getTotal()%>&#8372;</th>
        </tr>
            <%}
        }
            %>
    </tbody>
</table>
</div>
<nav aria-label="Page navigation example">
    <ul class="pagination justify-content-center">
        <li class="page-item ${page > 0 ? "" : "disabled"}">
            <a class="page-link" href="pagination?page=${page-1}"
               tabindex="-1">
                Previous</a>
        </li>
        <c:forEach var="num" begin="0" end="${4}">
            <li class="page-item ${page == num ? "active" : ""}">
                <a class="page-link" href="pagination?page=${num}">
                        ${num+1}</a></li>
        </c:forEach>
        <li class="page-item ${page < 4 ? "" : "disabled"}">
            <a class="page-link" href="pagination?page=${page+1}">
                Next
            </a>
        </li>
    </ul>
</nav>
</body>
</html>
