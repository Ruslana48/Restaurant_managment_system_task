import dao.DAO;
import entity.Dish;
import entity.Order;
import exceptions.DbExceptions;
import mysql.ConnectionPool;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import util.SqlUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MySqlReceiptDaoTest {

    ResultSet resultSet = Mockito.mock(ResultSet.class);
    PreparedStatement preparedStatement = Mockito.mock(PreparedStatement.class);
    Connection connection = Mockito.mock(Connection.class);
    ConnectionPool connectionPool = Mockito.mock(ConnectionPool.class);

    @Test
    public void getClientOrders() throws SQLException, DbExceptions {
        Mockito.when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        Mockito.when(preparedStatement.executeQuery()).thenReturn(resultSet);
        Mockito.when(connection.prepareStatement(SqlUtils.GET_ALL_CLIENT_ORDERS)).thenReturn(preparedStatement);
        Mockito.when(connection.prepareStatement(SqlUtils.GET_DISHES_BY_ORDER_INDEX)).thenReturn(preparedStatement);
        Mockito.when(connectionPool.getConnection()).thenReturn(connection);
        try (MockedStatic<ConnectionPool> cpMock = Mockito.mockStatic(ConnectionPool.class)) {
            cpMock.when(ConnectionPool::getInstance).thenReturn(connectionPool);

            DAO.getDao().getOrderDao().getAllClientOrder(1);
        }
    }

    @Test
    public void getAllOrderDishes()throws SQLException, DbExceptions{
        Mockito.when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        Mockito.when(preparedStatement.executeQuery()).thenReturn(resultSet);
        Mockito.when(connection.prepareStatement(SqlUtils.GET_ALL_DISHES_FROM_ORDER)).thenReturn(preparedStatement);
        Mockito.when(connection.prepareStatement(SqlUtils.GET_DISH_BY_ID)).thenReturn(preparedStatement);
        Mockito.when(connectionPool.getConnection()).thenReturn(connection);
        try (MockedStatic<ConnectionPool> cpMock = Mockito.mockStatic(ConnectionPool.class)) {
            cpMock.when(ConnectionPool::getInstance).thenReturn(connectionPool);

            DAO.getDao().getOrderDao().getAllOrderDishes(1);
        }
    }

    @Test
    public void getOrderNumber()throws SQLException, DbExceptions{
        int expected = 5;
        Mockito.when(resultSet.getInt(1)).thenReturn(expected);
        mockOtherFields(SqlUtils.GET_ORDER_COUNT);
        try (MockedStatic<ConnectionPool> cpMock = Mockito.mockStatic(ConnectionPool.class)) {
            cpMock.when(ConnectionPool::getInstance).thenReturn(connectionPool);
            int actual = DAO.getDao().getOrderDao().getOrderNumber();
            assertEquals(expected, actual);
        }
    }

    @Test
    public void getAllReceipts() throws SQLException, DbExceptions {
        Mockito.when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        Mockito.when(preparedStatement.executeQuery()).thenReturn(resultSet);
        Mockito.when(connection.prepareStatement(SqlUtils.GET_ALL_ORDERS)).thenReturn(preparedStatement);
        Mockito.when(connection.prepareStatement(SqlUtils.GET_DISHES_BY_ORDER_INDEX)).thenReturn(preparedStatement);
        Mockito.when(connectionPool.getConnection()).thenReturn(connection);
        try (MockedStatic<ConnectionPool> cpMock = Mockito.mockStatic(ConnectionPool.class)) {
            cpMock.when(ConnectionPool::getInstance).thenReturn(connectionPool);

            DAO.getDao().getOrderDao().getAllOrders();
        }
    }

    @Test
    public void getAllReceiptsAcceptedBy() throws SQLException, DbExceptions {
        Mockito.when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        Mockito.when(preparedStatement.executeQuery()).thenReturn(resultSet);
        Mockito.when(connection.prepareStatement(SqlUtils.GET_RECEIPTS_APPROVED_BY)).thenReturn(preparedStatement);
        Mockito.when(connection.prepareStatement(SqlUtils.GET_DISHES_BY_ORDER_INDEX)).thenReturn(preparedStatement);
        Mockito.when(connectionPool.getConnection()).thenReturn(connection);
        try (MockedStatic<ConnectionPool> cpMock = Mockito.mockStatic(ConnectionPool.class)) {
            cpMock.when(ConnectionPool::getInstance).thenReturn(connectionPool);

            List<Order> receiptList = DAO.getDao().getOrderDao().getAcceptedOrders(1);
            receiptList.forEach(System.out::println);
        }
    }

    @Test
    public void changeStatus() throws SQLException{
        Mockito.when(preparedStatement.executeUpdate()).thenReturn(1);
        Mockito.when(connection.prepareStatement(SqlUtils.CHANGE_STATUS)).thenReturn(preparedStatement);
        Mockito.when(connectionPool.getConnection()).thenReturn(connection);
        try (MockedStatic<ConnectionPool> cpMock = Mockito.mockStatic(ConnectionPool.class)) {
            cpMock.when(ConnectionPool::getInstance).thenReturn(connectionPool);
            DAO.getDao().getOrderDao().changeStatus(1, 1,1);
        } catch (DbExceptions e) {
            throw new RuntimeException(e);
        }
    }



    private void mockOtherFields(String sqlQuery) throws SQLException {
        Mockito.when(resultSet.next()).thenReturn(true).thenReturn(false);
        Mockito.when(preparedStatement.executeQuery()).thenReturn(resultSet);
        Mockito.when(connection.prepareStatement(sqlQuery)).thenReturn(preparedStatement);
        Mockito.when(connectionPool.getConnection()).thenReturn(connection);
    }

}