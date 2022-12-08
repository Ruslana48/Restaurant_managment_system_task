<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang=${language}>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Registration</title>
    <link rel="stylesheet" href="css/signup-style.css">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link href="https://fonts.googleapis.com/css?family=Lato:300,400,700&display=swap" rel="stylesheet">

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

    <link rel="stylesheet" href="cssBootstrapSignUp/style.css">

</head>
<body>
<a href="localization?language=en_US">English</a>|
<a href="localization?language=ua_UA">Українська</a>
<%session.setAttribute("pageName","signUp.jsp");%>
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="i18n" var="lang"/>

<fmt:bundle basename="i18n">
    <fmt:message key="signUp.create.account" bundle="${lang}" var="createAnAccount"/>
    <fmt:message key="signUp.input.enterSurname" bundle="${lang}" var="enterSurname"/>
    <fmt:message key="signUp.input.enterName" bundle="${lang}" var="enterName"/>
    <fmt:message key="login.label.email" bundle="${lang}" var="email"/>
    <fmt:message key="signUp.input.phoneNumber" bundle="${lang}" var="phone"/>
    <fmt:message key="login.label.password" bundle="${lang}" var="password"/>
    <fmt:message key="login.signup.href" bundle="${lang}" var="signUp"/>
    <fmt:message key="signUp.chooseRole.client" bundle="${lang}" var="client"/>
    <fmt:message key="signUp.chooseRole.manager" bundle="${lang}" var="manager"/>
    <fmt:message key="login.please.enter.email" bundle="${lang}" var="EmailValidation"/>
    <fmt:message key="login.please.enter.password" bundle="${lang}" var="PasswordValidation"/>
    <fmt:message key="signUp.please.enter.surname" bundle="${lang}" var="surnameValidation"/>
    <fmt:message key="signUp.please.enter.name" bundle="${lang}" var="nameValidation"/>
    <fmt:message key="signUp.please.enter.phone" bundle="${lang}" var="phoneValidation"/>

    <fmt:message key="login.button.submit" bundle="${lang}" var="signIn"/>
    <fmt:message key="signUp.already.member" bundle="${lang}" var="member"/>
</fmt:bundle>
<section class="ftco-section">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-7 col-lg-6 col-xl-5">
                <div class="login-wrap p-4 p-md-5">
                    <div class="icon d-flex align-items-center justify-content-center">
                        <span class="fa fa-edit"></span>
                    </div>
                    <h3 class="text-center mb-4">${createAnAccount}</h3>
                    <form action="register" name ="registration" onsubmit="return validation()" class="signup-form needs-validation" method="post" novalidate>
                        <div class="form-group mb-3">
                            <input type="text" class="form-control" name="surname" placeholder="${enterSurname}" required>
                            <div class="invalid-feedback">
                                ${surnameValidation}
                            </div>
                        </div>
                        <div class="form-group mb-3">
                            <input type="text" class="form-control" name="name" placeholder="${enterName}" required>
                            <div class="invalid-feedback">
                                ${nameValidation}
                            </div>
                        </div>
                        <div class="form-group mb-3">
                            <input type="text" class="form-control" name="email" placeholder="${email}" required>
                            <div class="invalid-feedback">
                                ${EmailValidation}
                            </div>
                        </div>
                        <div class="form-group mb-3">
                            <input type="text" name="mobn" class="form-control" placeholder="${phone}"required>
                            <div class="invalid-feedback">
                                ${phoneValidation}
                            </div>
                        </div>
                        <div class="form-group mb-3">
                            <input type="password" name="password" class="form-control"placeholder="${password}"required>
                            <div class="invalid-feedback">
                                ${PasswordValidation}
                            </div>
                        </div>
                        <select name="yourrole">
                            <option value="CLIENT">${client}</option>
                            <option value="MANAGER">${manager}</option>
                        </select>
                        <div class="form-group">
                            <button type="submit" class="form-control btn btn-primary rounded submit px-3">${signUp}</button>
                        </div>
                    </form>
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
                            var surname =
                                document.forms.registration.surname.value
                            var name =
                                document.forms.registration.name.value;
                            var email =
                                document.forms.registration.email.value;
                            var phone =
                                document.forms.registration.mobn.value;
                            var password =
                                document.forms.registration.password.value;
                            var regEmail=/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/g;  //Javascript reGex for Email Validation.
                            var regPhone=/^\d{14}$/;                                        // Javascript reGex for Phone Number validation.
                            var regName = /\d+$/g;                                    // Javascript reGex for Name validation
                            var regSurname = /\d+$/g;


                            if (surname == "" || regSurname.test(surname)) {
                                window.alert("Please enter your surname properly.");
                                name.focus();
                                return false;

                            }
                            if(surname.length <3){
                                alert("Surname should be at least 3 character long");
                                surname.focus();
                                return false;

                            }
                            if (name == "" || regName.test(name)) {
                                window.alert("Please enter your name properly.");
                                name.focus();
                                return false;
                            }

                            if(name.length <3){
                                alert("Name should be at least 3 character long");
                                name.focus();
                                return false;

                            }
                            if (email == "" || !regEmail.test(email)) {
                                window.alert("Please enter a valid e-mail address.");
                                email.focus();
                                return false;
                            }

                            if (phone == "" || !regPhone.test(phone)) {
                                alert("Please enter valid phone number.");
                                phone.focus();
                                return false;
                            }

                            if (password == "") {
                                alert("Please enter your password");
                                password.focus();
                                return false;
                            }

                            if(password.length <6){
                                alert("Password should be atleast 6 character long");
                                password.focus();
                                return false;

                            }

                            return true;
                        }
                    </script>
                    <p>${member}! <a data-toggle="tab" href="login.jsp">${signIn}</a></p>
                </div>
            </div>
        </div>
    </div>
</section>
</body>
</html>
