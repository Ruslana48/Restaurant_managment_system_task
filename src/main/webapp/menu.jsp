<%@ page import="mysql.DishDaoSql" %>
<%@ page import="java.util.List" %>
<%@ page import="entity.Dish" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="entity.Client" %>
<%@ page import="dao.DAO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page errorPage="error.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta content="width=device-width, initial-scale=1.0" name="viewport">
<title>Menu</title>
<meta content="" name="description">
<meta content="" name="keywords">
    <style>
        .c_dishes2 {
            width: 1200px;
            position: center;
            margin: auto;
            display: flex;
            flex-wrap: wrap;
        }

        .dish_img2 {
            width: 350px;
            height: 300px;
            object-fit: cover;
            border-top-left-radius: var(--dish-container-corner-radius);
            border-top-right-radius: var(--dish-container-corner-radius);
        }
        .dish_container2 {
            border-radius: var(--dish-container-corner-radius);
            background-color: white;
            width: 350px;
            margin: 20px;
        }
        .between{
            height: 100px;
        }
        .form{
            margin-left: 200px;
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

    <link rel="stylesheet" href="css/allstyle.css">

<!-- Template Main CSS File -->
    <link href="css/mainCSS.css" rel="stylesheet">
<link href="css/menuStyle.css" rel="stylesheet">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
</head>

<body>
<%@include file="headerForClient.jspf" %>
<%List<Dish>dishes= (List<Dish>) request.getSession().getAttribute("allDishesForMenu");%>
<div class="between">

</div>
<form action="menu" class="form" method="get">
    <select name="filter">
        Show dishes:
        <option value="all">All</option>
        <option value="by-name">By Name</option>
        <option value="by-cost">By Cost</option>
        <option value="by-category">By Category</option>

    </select>
    <input type="submit" value="Show">

</form>
<main id="main">
    <div class="c_dishes2">
    <%
        String filterParam = request.getParameter("filter");
        String filterAttr = (String) session.getAttribute("filter");
        String filter = filterParam == null ? filterAttr == null ? "all" : filterAttr : filterParam;
        switch (filter){
            case "all":
                dishes= DAO.getDao().getDishDao().getAllDishes();
                break;
            case "by-name":
                System.out.println("case");
                dishes=DAO.getDao().getDishDao().getSortedDishesOnPage("by name");
                break;
            case "by-cost":
                dishes=DAO.getDao().getDishDao().getSortedDishesOnPage("by price");
                break;
            case "by-category":
                dishes=DAO.getDao().getDishDao().getSortedDishesOnPage("by category_id");
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + filter);
        }
        if (!dishes.isEmpty()){
            for (Dish dish:dishes){
    %>
    <div class="dish_container2">
        <img class="dish_img2" src="imagesForMenu/dish-<%=dish.getId()%>.jpg">
        <div class="details">
            <div class="details-sub">
                <h5><%=dish.getName()%></h5>
                <h5 class="price"><%=dish.getPrice()%>$</h5>
                <p class="weight"><%=dish.getWeight()%>g</p>
            </div>
            <p><%=dish.getDescription()%></p>
            <div class="mt-3 d-flex justify_content-between">
                <a href="cart?id=<%=dish.getId()%>" class="btn btn-dart">Add to Cart</a>
            </div>
        </div>
    </div>
    <% }
        }
    %>
    </div>
</main>
</body>
</html>
