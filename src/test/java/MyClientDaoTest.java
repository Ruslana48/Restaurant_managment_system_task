import dao.ClientDAO;
import dao.DAO;
import entity.Client;
import entity.Dish;
import exceptions.DbExceptions;
import mysql.ConnectionPool;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import util.SqlUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.fail;

public class MyClientDaoTest {

    ResultSet resultSet = Mockito.mock(ResultSet.class);
    PreparedStatement preparedStatement = Mockito.mock(PreparedStatement.class);
    Connection connection = Mockito.mock(Connection.class);
    ConnectionPool connectionPool = Mockito.mock(ConnectionPool.class);

    @Test
    public void login() throws SQLException {
        try {
            Mockito.when(preparedStatement.executeUpdate()).thenReturn(1);
            Mockito.when(preparedStatement.executeQuery()).thenReturn(resultSet);
            Mockito.when(connection.prepareStatement(SqlUtils.LOG_IN)).thenReturn(preparedStatement);
            Mockito.when(connectionPool.getConnection()).thenReturn(connection);
            try (MockedStatic<ConnectionPool> cpMock = Mockito.mockStatic(ConnectionPool.class)) {
                cpMock.when(ConnectionPool::getInstance).thenReturn(connectionPool);
                Client client=ClientDAO.setClient(resultSet);
                ClientDAO.login(client);
            }
        } catch (DbExceptions e) {
            fail();
        }
    }

    @Test
    public void save() throws SQLException {
        Mockito.when(preparedStatement.executeUpdate()).thenReturn(1);
        Mockito.when(preparedStatement.executeQuery()).thenReturn(resultSet);
        Mockito.when(connection.prepareStatement(SqlUtils.SAVE)).thenReturn(preparedStatement);
        Mockito.when(connectionPool.getConnection()).thenReturn(connection);
        try (MockedStatic<ConnectionPool> cpMock = Mockito.mockStatic(ConnectionPool.class)) {
            cpMock.when(ConnectionPool::getInstance).thenReturn(connectionPool);
            Client client=ClientDAO.setClient(resultSet);
            client.setStatus("manager");
            ClientDAO.save(client);
        }
    }



}