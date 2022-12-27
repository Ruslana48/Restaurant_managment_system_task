<%@ page import="entity.Client" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%Client client = (Client) session.getAttribute("client");%>
<head>
    <meta charset="utf-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">

    <title>Client</title>
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
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">;
    <title>Account</title>
    <style>
        .between{
            height: 100px;
        }
    </style>
</head>
<body>
<%@include file="headerForAdmin.jspf" %>
<div class="between"></div>
<main id="main">
    <section class="vh-100" style="background-color: #f4f5f7;">
        <div class="container py-5 h-100">
            <div class="row d-flex justify-content-center align-items-center h-100">
                <div class="col col-lg-6 mb-4 mb-lg-0">
                    <div class="card mb-3" style="border-radius: .5rem;">
                        <div class="row g-0">
                            <div class="col-md-4 gradient-custom text-center text-dark"
                                 style="border-top-left-radius: .5rem; border-bottom-left-radius: .5rem;">
                                <img src="avatar.jpg"
                                     alt="Avatar" class="img-fluid my-5" style="width: 80px;" />
                                <h5><%=client.getSurname()%> <%=client.getName()%></h5>
                                <p><%=client.getStatus()%></p>
                                <i class="far fa-edit mb-5"></i>
                            </div>
                            <div class="col-md-8">
                                <div class="card-body p-4">
                                    <h6>Information</h6>
                                    <hr class="mt-0 mb-4">
                                    <div class="row pt-1">
                                        <div class="col-6 mb-3">
                                            <h6>Email</h6>
                                            <p class="text-muted"><%=client.getEmail()%></p>
                                        </div>
                                        <div class="col-6 mb-3">
                                            <h6>Phone</h6>
                                            <p class="text-muted"><%=client.getPhone()%></p>
                                        </div>
                                        <div class="col-6 mb-3">
                                            <a href="newPassword.jsp">Change Password</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
</main>
</body>
</html>
