<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang=${language}>
<head>
<link rel="stylesheet" href="css/signup-style.css">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link href="https://fonts.googleapis.com/css?family=Lato:300,400,700&display=swap" rel="stylesheet">

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

    <link rel="stylesheet" href="cssBootstrap/style.css">

    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;700&display=swap" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
    <title>Login</title>
    <style>
        .select{
            width: 200px;
        }
    </style>
</head>
<body>
<a href="localization?language=en_US">English</a>|
<a href="localization?language=ua_UA">Українська</a>
<%session.setAttribute("pageName","login.jsp");%>
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="i18n" var="lang"/>
<fmt:bundle basename="i18n">
    <fmt:message key="login.have.account" bundle="${lang}" var="haveAnAccount"/>
    <fmt:message key="login.label.email" bundle="${lang}" var="Email"/>
    <fmt:message key="login.please.enter.email" bundle="${lang}" var="EmailValidation"/>
    <fmt:message key="login.label.password" bundle="${lang}" var="Password"/>
    <fmt:message key="login.please.enter.password" bundle="${lang}" var="PasswordValidation"/>
    <fmt:message key="login.signup.href" bundle="${lang}" var="signUp"/>
    <fmt:message key="login.forgotPassword.href" bundle="${lang}" var="forgotPassword"/>
    <fmt:message key="login.button.submit" bundle="${lang}" var="submit"/>
</fmt:bundle>
<section class="ftco-section">
<div id='container'>
    <div class="row justify-content-center">
        <div class="col-md-6 col-lg-5">
            <div class="login-wrap p-4 p-md-5">
                <div class="icon d-flex align-items-center justify-content-center">
                    <span class="fa fa-user-o"></span>
                </div>
                <h3 class="text-center mb-4">${haveAnAccount} ?</h3>
                <form action="login" name="login" onsubmit="return validation()" class="login-form needs-validation" method="post" id="inputEmail" novalidate>
                    <div class="form-group">
                        <input type="text" class="form-control rounded-left" id = "email" name="email" placeholder=${Email} required>
                        <div class="invalid-feedback">
                            ${EmailValidation}
                        </div>
                    </div>
                    <div class="form-group d-flex">
                        <input type="password" class="form-control rounded-left" id="inputPassword" name="password" placeholder=${Password} required>
                        <div class="invalid-feedback">
                                ${PasswordValidation}
                                </div>
                                </div>
                    <div class="form-group d-md-flex">
                        <div class="w-50">
                            <label class="checkbox-wrap checkbox-primary">
                                <a href="signUp.jsp">${signUp} </a>
                            </label>
                        </div>
                    </div>
                    <div class="">
                        <a href="fogotPassword.jsp">${forgotPassword}?</a>
                    </div>

                    <div class="form-group">
                        <input type="submit" class="btn btn-primary rounded submit p-3 px-5" value="${submit}">
                    </div>
                </form>
                <!-- JavaScript for disabling form submissions if there are invalid fields -->
                <script>
                    // Self-executing function
                    (function() {
                        'use strict';
                        window.addEventListener('load', function() {
                            // Fetch all the forms we want to apply custom Bootstrap validation styles to
                            var forms = document.getElementsByClassName('needs-validation');

                            // Loop over them and prevent submission
                            var validation = Array.prototype.filter.call(forms, function(form) {
                                form.addEventListener('submit', function(event) {
                                    if (form.checkValidity() === false) {
                                        event.preventDefault();
                                        event.stopPropagation();
                                    }
                                    form.classList.add('was-validated');
                                }, false);
                            });
                        }, false);
                    })();
                </script>
                <script>
                    function validation() {
                        var email =
                            document.forms.login.email.value;
                        var password =
                            document.forms.login.password.value;
                        var regEmail=/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/g;  //Javascript reGex for Email Validation.

                        if (email == "" || !regEmail.test(email)) {
                            alert("Please enter a valid e-mail address.");
                            email.focus();
                            return false;
                        }

                        if (password == "") {
                            alert("Please enter your password");
                            password.focus();
                            return false;
                        }

                        if(password.length <4){
                            alert("Password should be at least 4 character long");
                            password.focus();
                            return false;
                        }
                        return true;
                    }
                </script>
            </div>
        </div>
    </div>
</div>
</div>
</section>
</body>
</html>

