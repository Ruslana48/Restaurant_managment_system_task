import dao.DAO;
import entity.Client;
import entity.Dish;
import exceptions.DbExceptions;
import mysql.CartDaoSql;
import mysql.CategorySql;
import mysql.ConnectionPool;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import util.SqlUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class MySqlCartDaoTest {

    ResultSet resultSet = Mockito.mock(ResultSet.class);
    PreparedStatement preparedStatement = Mockito.mock(PreparedStatement.class);
    Connection connection = Mockito.mock(Connection.class);
    ConnectionPool connectionPool = Mockito.mock(ConnectionPool.class);

    @Test
    public void addDishToCart() throws SQLException {
        try {
            Mockito.when(preparedStatement.executeUpdate()).thenReturn(1);
            Mockito.when(connection.prepareStatement(SqlUtils.ADD_DISH_TO_CART)).thenReturn(preparedStatement);
            Mockito.when(connectionPool.getConnection()).thenReturn(connection);
            try (MockedStatic<ConnectionPool> cpMock = Mockito.mockStatic(ConnectionPool.class)) {
                cpMock.when(ConnectionPool::getInstance).thenReturn(connectionPool);
                DAO.getDao().getCartDao().addDishToCart(1, "1", 1);
            }
        } catch (DbExceptions e) {
            fail();
        }
    }

//    @Test
//    public void selectDishesByClient()throws SQLException{
//        Mockito.when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
//        Mockito.when(preparedStatement.executeQuery()).thenReturn(resultSet);
//        Mockito.when(connection.prepareStatement(SqlUtils.GET_DISHES_AND_THEIR_COUNT_FROM_CLIENT_CART)).thenReturn(preparedStatement);
//        Mockito.when(connection.prepareStatement(SqlUtils.GET_DISHES_BY_ORDER_INDEX)).thenReturn(preparedStatement);
//        Mockito.when(connectionPool.getConnection()).thenReturn(connection);
//        try (MockedStatic<ConnectionPool> cpMock = Mockito.mockStatic(ConnectionPool.class)) {
//            cpMock.when(ConnectionPool::getInstance).thenReturn(connectionPool);
//
//            DAO.getDao().getCartDao().selectDishesByClient(1);
//        }
//    }


    @Test
    public void checkWhetherDishExistInClientCart()throws SQLException, DbExceptions{
        Mockito.when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        Mockito.when(preparedStatement.executeQuery()).thenReturn(resultSet);
        Mockito.when(connection.prepareStatement(SqlUtils.SELECT_ALL_CLIENT_DISHES)).thenReturn(preparedStatement);
        Mockito.when(connectionPool.getConnection()).thenReturn(connection);
        try (MockedStatic<ConnectionPool> cpMock = Mockito.mockStatic(ConnectionPool.class)) {
            cpMock.when(ConnectionPool::getInstance).thenReturn(connectionPool);


            DAO.getDao().getCartDao().checkWhetherDishExistInClientCart("1",1);
        }
    }

    @Test
    public void getTotalCartPrice()throws SQLException, DbExceptions{
        Mockito.when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        Mockito.when(preparedStatement.executeQuery()).thenReturn(resultSet);
        Mockito.when(connection.prepareStatement(SqlUtils.SELECT_ALL_CLIENT_DISHES)).thenReturn(preparedStatement);
        Mockito.when(connectionPool.getConnection()).thenReturn(connection);

        try (MockedStatic<ConnectionPool> cpMock = Mockito.mockStatic(ConnectionPool.class)) {
            cpMock.when(ConnectionPool::getInstance).thenReturn(connectionPool);
            List<Dish>dishes=new ArrayList<>();
            dishes.add(0, MySqlDishDaoTest.getTestDishes(1).get(0));

            DAO.getDao().getCartDao().getTotalCartPrice(dishes,1);
        }
    }

//    @Test
//    public void dishesFromClientCart(){
//        try {
//            Mockito.when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
//            Mockito.when(preparedStatement.executeQuery()).thenReturn(resultSet);
//            Mockito.when(connection.prepareStatement(SqlUtils.SELECT_DISHES_FROM_CLIENT_CART)).thenReturn(preparedStatement);
//            Mockito.when(connection.prepareStatement(SqlUtils.GET_DISHES_BY_ORDER_INDEX)).thenReturn(preparedStatement);
//            Mockito.when(connectionPool.getConnection()).thenReturn(connection);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        try (MockedStatic<ConnectionPool> cpMock = Mockito.mockStatic(ConnectionPool.class)) {
//            cpMock.when(ConnectionPool::getInstance).thenReturn(connectionPool);
//
//            DAO.getDao().getCartDao().dishesFromClientCart(1);
//        } catch (DbExceptions e) {
//            throw new RuntimeException(e);
//        }
//    }

    @Test
    public void getDishCountInCart()throws SQLException, DbExceptions{
        Mockito.when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        Mockito.when(preparedStatement.executeQuery()).thenReturn(resultSet);
        Mockito.when(connection.prepareStatement(SqlUtils.GET_COUNT_DISH_IN_THE_CART)).thenReturn(preparedStatement);
        Mockito.when(connectionPool.getConnection()).thenReturn(connection);

        try (MockedStatic<ConnectionPool> cpMock = Mockito.mockStatic(ConnectionPool.class)) {
            cpMock.when(ConnectionPool::getInstance).thenReturn(connectionPool);

            DAO.getDao().getCartDao().getDishCountInCart(1,"1");
        }
    }

//    @Test
//    public void dishesFromClientCart()throws SQLException, DbExceptions{
//        try {
//            Mockito.when(preparedStatement.executeUpdate()).thenReturn(1);
//            Mockito.when(connection.prepareStatement(SqlUtils.SELECT_DISHES_FROM_CLIENT_CART)).thenReturn(preparedStatement);
//
//            Mockito.when(connectionPool.getConnection()).thenReturn(connection);
//            try (MockedStatic<ConnectionPool> cpMock = Mockito.mockStatic(ConnectionPool.class)) {
//                cpMock.when(ConnectionPool::getInstance).thenReturn(connectionPool);
//                DAO.getDao().getCartDao().dishesFromClientCart(1);
//            }
//        } catch (DbExceptions e) {
//
//        }
//    }

    @Test
    public void setTotalOrderPrice()throws SQLException,DbExceptions{
        Mockito.when(preparedStatement.executeUpdate()).thenReturn(1);
        Mockito.when(connection.prepareStatement(SqlUtils.UPDATE_TOTAL_PRICE_IN_RECEIPT)).thenReturn(preparedStatement);
        Mockito.when(connectionPool.getConnection()).thenReturn(connection);
        try (MockedStatic<ConnectionPool> cpMock = Mockito.mockStatic(ConnectionPool.class)) {
            cpMock.when(ConnectionPool::getInstance).thenReturn(connectionPool);
            DAO.getDao().getCartDao().setTotalOrderPrice(1, 1,1);
        }
    }

    @Test
    public void updateCount()throws SQLException, DbExceptions{
        Mockito.when(preparedStatement.executeUpdate()).thenReturn(1);
        Mockito.when(connection.prepareStatement(SqlUtils.UPDATE_COUNT_IN_THE_CART)).thenReturn(preparedStatement);
        Mockito.when(connectionPool.getConnection()).thenReturn(connection);
        try (MockedStatic<ConnectionPool> cpMock = Mockito.mockStatic(ConnectionPool.class)) {
            cpMock.when(ConnectionPool::getInstance).thenReturn(connectionPool);
            DAO.getDao().getCartDao().updateCount(1,1);
        }
    }

    @Test
    public void removeDishFromCart() throws SQLException, DbExceptions {
        Mockito.when(preparedStatement.executeUpdate()).thenReturn(1);
        Mockito.when(connection.prepareStatement(SqlUtils.REMOVE_DISH_FROM_CART)).thenReturn(preparedStatement);
        Mockito.when(connectionPool.getConnection()).thenReturn(connection);
        try (MockedStatic<ConnectionPool> cpMock = Mockito.mockStatic(ConnectionPool.class)) {
            cpMock.when(ConnectionPool::getInstance).thenReturn(connectionPool);
            DAO.getDao().getCartDao().removeDishFromCart(1, "1");
        }
    }

    @Test
    public void makeAnOrder() throws SQLException, DbExceptions {
        Mockito.when(resultSet.getLong(1)).thenReturn(1L);
        Mockito.when(resultSet.next()).thenReturn(true);
        Mockito.when(preparedStatement.getGeneratedKeys()).thenReturn(resultSet);
        Mockito.when(preparedStatement.executeUpdate()).thenReturn(1);
        Mockito.when(connection.prepareStatement(SqlUtils.REMOVE_DISH_FROM_CART)).thenReturn(preparedStatement);
        Mockito.when(connection.prepareStatement(SqlUtils.ADD_RECEIPT, Statement.RETURN_GENERATED_KEYS)).thenReturn(preparedStatement);
        Mockito.when(connection.prepareStatement(SqlUtils.ADD_RECEIPT_HAS_DISH)).thenReturn(preparedStatement);
        Mockito.when(connectionPool.getConnection()).thenReturn(connection);
        try (MockedStatic<ConnectionPool> cpMock = Mockito.mockStatic(ConnectionPool.class)) {
            cpMock.when(ConnectionPool::getInstance).thenReturn(connectionPool);
            List<Dish>dishes=new ArrayList<>();
            dishes.add(0, MySqlDishDaoTest.getTestDishes(1).get(0));
            DAO.getDao().getCartDao().makeAnOrder(1, dishes);
        }
    }



    @Test
        //if exception throws - test won't pass
    public void cleanCart() throws SQLException, DbExceptions {
        Mockito.when(preparedStatement.executeUpdate()).thenReturn(1);
        Mockito.when(connection.prepareStatement(SqlUtils.CLEAN_CART)).thenReturn(preparedStatement);
        Mockito.when(connectionPool.getConnection()).thenReturn(connection);
        try (MockedStatic<ConnectionPool> cpMock = Mockito.mockStatic(ConnectionPool.class)) {
            cpMock.when(ConnectionPool::getInstance).thenReturn(connectionPool);
            DAO.getDao().getCartDao().cleanCart(1);
        }
    }


}