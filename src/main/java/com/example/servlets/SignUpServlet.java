package com.example.servlets;

import javax.servlet.http.*;
import javax.servlet.annotation.*;

import dao.ClientDAO;
import entity.Client;
import entity.Order;
import exceptions.DbExceptions;
import mysql.ReceiptDaoSql;
import org.apache.log4j.Logger;
import util.Utils;

import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


@WebServlet("/register")
public class SignUpServlet extends HttpServlet {

    private static final Logger log = Logger.getLogger(LoginServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out=response.getWriter();
        out.print("Working");

        String cSurname = request.getParameter("surname");
        String cName = request.getParameter("name");
        String cEmail = request.getParameter("email");
        String cNumber = request.getParameter("mobn");
        String cPassword = request.getParameter("password");
        String cRole = request.getParameter("yourrole");

        out.print(cSurname);
        out.print(cName);
        out.print(cEmail);
        out.print(cNumber);
        out.print(cRole);
        out.print(cPassword);
        Client client = new Client(cSurname, cName, cEmail, cNumber, cPassword, cRole);
        ClientDAO clientDAO = new ClientDAO();
        try {
            String result = clientDAO.save(client);
            response.getWriter().print(result);
        }catch (Exception e) {
            log.error(Utils.getErrMessage(e));
            e.printStackTrace();
        }
        request.getSession().setAttribute("client", client);
        ReceiptDaoSql receiptDao=new ReceiptDaoSql();
        try {
            List<Order> orders=receiptDao.getAllClientOrder(client.getId());
            request.getSession().setAttribute("clientOrdersList", orders);
        } catch (DbExceptions e) {
            log.error(Utils.getErrMessage(e));
        }

        if (client.getStatus().equals("MANAGER")) {
            response.sendRedirect("adminPage.jsp");
        }else {
            response.sendRedirect("clientPage.jsp");
        }
    }
}