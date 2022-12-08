package com.example.servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/ValidateOtp")
public class ValidateOtpServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        int value=Integer.parseInt(request.getParameter("otp"));
        HttpSession session=request.getSession();
        int otp=(int)session.getAttribute("otp");



        RequestDispatcher dispatcher=null;


        if (value==otp)
        {

            request.setAttribute("email", request.getParameter("email"));
            request.setAttribute("status", "success");
            dispatcher=request.getRequestDispatcher("newPassword.jsp");
            dispatcher.forward(request, response);

        }
        else
        {
            request.setAttribute("message","wrong otp");

            dispatcher=request.getRequestDispatcher("EnterOtp.jsp");
            dispatcher.forward(request, response);

        }

    }

}
