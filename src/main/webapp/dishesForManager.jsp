<%@ page import="entity.Dish" %>
<%@ page import="mysql.DishDaoSql" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%
        DishDaoSql dishDao=new DishDaoSql();
        List<Dish> dishes=dishDao.getAllDishes();
    %>
    <meta charset="utf-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">

    <title>All dishes</title>

    <meta content="" name="description">
    <meta content="" name="keywords">

    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Open+Sans:ital,wght@0,300;0,400;0,500;0,600;0,700;1,300;1,400;1,600;1,700&family=Amatic+SC:ital,wght@0,300;0,400;0,500;0,600;0,700;1,300;1,400;1,500;1,600;1,700&family=Inter:ital,wght@0,300;0,400;0,500;0,600;0,700;1,300;1,400;1,500;1,600;1,700&display=swap" rel="stylesheet">

    <!-- Vendor CSS Files -->
    <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="assets/vendor/aos/aos.css" rel="stylesheet">
    <link href="assets/vendor/glightbox/css/glightbox.min.css" rel="stylesheet">
    <link href="assets/vendor/swiper/swiper-bundle.min.css" rel="stylesheet">

    <!-- Template Main CSS File -->
    <link href="css/mainCSS.css" rel="stylesheet">
    <link rel="stylesheet" href="css/allstyle.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <style>
        .between{
            height: 100px;
        }
    </style>
</head>
<body>
<%@include file="headerForAdmin.jspf" %>
<div class="between"></div>

<div class="dc_container">
    <table class="table table-striped">
        <thead>
        <tr>
            <th scope="col">Image</th>
            <th scope="col">Name</th>
            <th scope="col">Description</th>
            <th scope="col">Weight</th>
            <th scope="col">Price</th>
        </tr>
        </thead>
        <tbody>
            <%
            if (!dishes.isEmpty()){
            for (Dish dish:dishes) {%>
            <tr>
            <td><img class="dc_img" src="imagesForMenu/dish-<%=dish.getId()%>.jpg"></td>
            <td><p><b><%=dish.getName()%></b></p></td>
            <td><p><%=dish.getDescription()%></p></td>
            <td><p class="weight"><%=dish.getWeight()%>g</p></td>
            <td><p class="price"><%=dish.getPrice()%>$</p></td>
            </tr>
             <%}
                }
                %>
        </tbody>
    </table>
</div>

</body>
</html>
