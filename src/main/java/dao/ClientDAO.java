package dao;
import entity.Client;
import entity.Roles;
import exceptions.DbExceptions;
import mysql.ConnectionPool;
import util.SqlUtils;

import java.sql.*;

public class ClientDAO {
    public static Client setClient(ResultSet rs) throws SQLException {
        int k = 0;
        return new Client.Builder()
                .setId(rs.getInt(++k))
                .setSurname(rs.getString("surname"))
                .setName(rs.getString("name"))
                .setEmail(rs.getString("email"))
                .setPhone(rs.getString("phone"))
                .setPassword(rs.getString("password"))
                .setStatus(rs.getString("status")).getClient();
    }

    public static void loadDriver() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static String save(Client client) {
        String result=null;
        try(Connection con = ConnectionPool.getInstance().getConnection();){
            PreparedStatement pst = null;
            try {
                pst = con.prepareStatement(SqlUtils.SAVE);
                pst.setString(1, client.getSurname());
                pst.setString(2, client.getName());
                pst.setString(3, client.getEmail());
                pst.setString(4, client.getPhone());
                pst.setString(5, client.getPassword());
                String role=null;
                if(client.getStatus().equals(Roles.MANAGER.name())){
                    role=Roles.MANAGER.name();
                }
                else {
                    role=Roles.CLIENT.name();
                }
                pst.setString(6, role);
                pst.executeUpdate();
            } catch (SQLException e) {
                printSQLException(e);
            }

        } catch (SQLException ex) {
            printSQLException(ex);
        }return result;
    }

    public static Client login(Client client) throws DbExceptions{
        loadDriver();
        try(Connection con =ConnectionPool.getInstance().getConnection();){
            PreparedStatement ps=con.prepareStatement(SqlUtils.LOG_IN);
                ps.setString(1, client.getEmail());
                ps.setString(2,client.getPassword());
                try(ResultSet rs=ps.executeQuery()) {
                    if (!rs.next()){
                        return null;
                    }else {
                        return setClient(rs);
                    }
                }
        }catch (SQLException e){
            throw new DbExceptions("Cannot log in");
        }
    }

    private static void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
