<%@ page import="entity.Client" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="mysql.DishDaoSql" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="entity.Dish" %>
<%@ page import="mysql.CartDaoSql" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    List<Dish>clientDishes=null;
    Client client= (Client) session.getAttribute("client");
    System.out.println(client);
    CartDaoSql cartDao=new CartDaoSql();
    clientDishes=cartDao.selectDishesByClient(client.getId());
%>
<html>
<%@include file="headerForClient.jspf" %>
<%@include file="headerForAdmin.jspf" %>
<head>
    <title>Cart</title>
        <meta charset="utf-8">
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <meta content="" name="description">
        <meta content="" name="keywords">
    <style>
        .example{
            margin-top: 120px;
        }
    </style>

    <link rel="stylesheet" href="css/cartCSS.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css" integrity="sha512-xh6O/CkQoPOWDdYTDqeRdPCVd1SpvCA9XXcUnZS2FmJNp1coAFzvtCN9BmamE+4aHK8yyUHUSCcJHgXloTyT2A==" crossorigin="anonymous" referrerpolicy="no-referrer" >


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
<!-- ======= Header ======= -->
<%@include file="headerForClient.jspf" %>

<div class="container my-3">
    <div class="example">
        <form method="post" action="checkout">
            <h3>Total Price: $<%=request.getSession().getAttribute("total")%></h3>
            <input type="submit" class="btn btn-info" value="Check Out">
        </form>
    </div>
    <table class="table table-striped">
        <thead>
        <tr>
        <th scope="col">Name</th>
        <th scope="col">Description</th>
        <th scope="col">Price</th>
        <th scope="col">Buy Now</th>
        <th scope="col">Cancel</th>
        </tr>
        </thead>
        <tbody>
        <%
            if (clientDishes!=null){
                for (Dish d:clientDishes){%>
        <tr>
            <td><%=d.getName()%></td>
            <td><%=d.getDescription()%></td>
            <td><%=d.getPrice()%>$</td>
            <td>
                <input type="hidden" name="id" value="<%=d.getId()%>" class="form_input">
                <form method="post" action="cart">
                    <div class="form_group d-flex justify-content-between">
                        <input type="text" name="quantity" class="quantity" min="1">
                    </div>
                    <input type="submit" class="btn btn-info" value="Save">
                </form>

            </td>
            <td><form class="form_line" method="post" action="remove">
                <a class="btn btn-sm btn-danger" value="<%=d.getId()%>" href="remove?id=<%=d.getId()%>">Remove</a></form>
            </td>
        </tr>
                <%}
            }%>

        </tbody>
    </table>

    <form action="order" method="get">
        <input type="submit" onclick="alert('Your order was sent to our manager. ' +
                 'Wait for it :)')" value="Make on order" data-target="#exampleModal">
    </form>
</div>
</body>
</html>
