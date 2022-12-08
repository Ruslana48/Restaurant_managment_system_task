package mysql;

import dao.CategoryDAO;
import entity.Category;
import exceptions.DbExceptions;
import util.SqlUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategorySql implements CategoryDAO {

    public static Category setCategory(ResultSet rs)throws SQLException{
        return new Category.Builder().setId(rs.getLong("id"))
                .setName(rs.getString("name")).getCategory();
    }

    @Override
    public List<Category> getAllCategories() throws DbExceptions {
        List<Category>categoryList=new ArrayList<>();
        try(Connection con =ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = con.prepareStatement(SqlUtils.GET_ALL_CATEGORIES);){
            ResultSet rs=ps.executeQuery();
            while (rs.next()) {
                categoryList.add(setCategory(rs));
            }
            return categoryList;
        } catch (SQLException e) {
            throw new DbExceptions("Cannot getAllCategories", e);
        }
    }
}
