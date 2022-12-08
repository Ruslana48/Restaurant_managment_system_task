package mysql;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConnectionPool {
    private static ConnectionPool instance;
    String connectionUrl = "jdbc:mysql://localhost:3306/restaurant?serverTimezone=UTC";
    String user="root";
    String password="xhz2woVLxhz2woVL";
    private List<Connection> connectionPool;

    private static int INITIAL_POOL_SIZE = 10;

    private DataSource ds;

    private ConnectionPool() {
        try {
            createConnection(connectionUrl, user, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static Connection createConnection(
            String connectionUrl, String user, String password)
            throws SQLException {
        return DriverManager.getConnection(connectionUrl, user, password);
    }

    public static ConnectionPool getInstance() {
        if (instance == null) {
            instance = new ConnectionPool();
        }
        return instance;
    }

    /**
     * Get connection from pool
     * @return connection
     */
    public Connection getConnection() throws SQLException {
        return createConnection(connectionUrl, user, password);
    }
}
