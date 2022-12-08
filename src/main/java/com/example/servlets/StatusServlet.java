package com.example.servlets;

import dao.DAO;
import entity.Client;
import exceptions.DbExceptions;
import mysql.ReceiptDaoSql;
import org.apache.log4j.Logger;
import util.Utils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/status")
public class StatusServlet extends HttpServlet {

    private static final Logger log = Logger.getLogger(LoginServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        ReceiptDaoSql rds=new ReceiptDaoSql();
        int orderId= Integer.parseInt(request.getParameter("id"));
        int statusId= Integer.parseInt(request.getParameter("status_id"));
        Client client= (Client) request.getSession().getAttribute("client");
        int managerId=client.getId();
        try {
            DAO.getDao().getOrderDao().changeStatus(orderId, statusId, managerId);
        } catch (DbExceptions e) {
            log.error(Utils.getErrMessage(e));
            throw new RuntimeException(e);
        }
        response.sendRedirect("orderListForAdmin.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
