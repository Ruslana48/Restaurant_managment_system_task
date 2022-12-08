package com.example.servlets;

import dao.ClientDAO;
import entity.Client;
import entity.Order;
import mysql.ReceiptDaoSql;
import util.Utils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.logging.LogManager;
import org.apache.log4j.Logger;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private static final Logger log = Logger.getLogger(LoginServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=UTF-8");
        HttpSession session = request.getSession();
        String clientEmail=request.getParameter("email");
        String clientPassword=request.getParameter("password");
        Client client=new Client(clientEmail, clientPassword);
        PrintWriter out=response.getWriter();
        ClientDAO cdao=new ClientDAO();
        try {
            Client clientLogin = cdao.login(client);
            if(clientLogin==null){
                request.setAttribute("err", "true");
                response.sendRedirect("wrongLogin.jsp");
            }else {
                request.getSession().setAttribute("client", clientLogin);
                Client cl = (Client) session.getAttribute("client");

                ReceiptDaoSql receiptDao = new ReceiptDaoSql();
                List<Order> orders = receiptDao.getAllClientOrder(cl.getId());
               session.setAttribute("clientOrdersList", orders);
                if (cl.getStatus().equals("MANAGER")) {
                    response.sendRedirect("adminPage.jsp");
                } else {
                    response.sendRedirect("clientPage.jsp");
                }
            }
        }catch (Exception e){
            log.error(Utils.getErrMessage(e));
            e.printStackTrace();
        }
    }
}
