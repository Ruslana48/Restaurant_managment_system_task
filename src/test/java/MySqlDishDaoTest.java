import dao.DAO;
import dao.DishDao;
import entity.Dish;
import exceptions.DbExceptions;
import mysql.ConnectionPool;
import mysql.DishDaoSql;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import util.SqlUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;

@ExtendWith(MockitoExtension.class)
public class MySqlDishDaoTest {
    Connection connection = mock(Connection.class);
    ConnectionPool connectionPool = Mockito.mock(ConnectionPool.class);
    ResultSet resultSet = Mockito.mock(ResultSet.class);
    PreparedStatement preparedStatement = Mockito.mock(PreparedStatement.class);
    @Mock
    private DishDao dishDao;
    @InjectMocks
    private DishDaoSql dishDaoSql;

    @Before
    public void setup(){
        dishDaoSql=mock(DishDaoSql.class);
    }

    Dish dish;
    {
        try {
            dish = DAO.getDao().getDishDao().getDishById(1);
        } catch (DbExceptions e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void getAllDishes()throws SQLException, DbExceptions{
        List<Dish>allDishes=getTestDishes(1);
        Mockito.when(resultSet.getString("id")).thenReturn("1");
        Mockito.when(resultSet.getString("name")).thenReturn("DishName1");
        Mockito.when(resultSet.getLong("category_id")).thenReturn(1L);
        Mockito.when(resultSet.getLong("price")).thenReturn(1000L);
        Mockito.when(resultSet.getLong("weight")).thenReturn(100L);
        Mockito.when(resultSet.getString("description")).thenReturn("DishDescription1");

        Mockito.when(resultSet.next()).thenReturn(true).thenReturn(false);
        Mockito.when(preparedStatement.executeQuery()).thenReturn(resultSet);
        Mockito.when(connection.prepareStatement(SqlUtils.GET_ALL_DISHES)).thenReturn(preparedStatement);
        Mockito.when(connectionPool.getConnection()).thenReturn(connection);
        try (MockedStatic<ConnectionPool> cpMock = Mockito.mockStatic(ConnectionPool.class)) {
            cpMock.when(ConnectionPool::getInstance).thenReturn(connectionPool);
            List<Dish> dishes = DAO.getDao().getDishDao().getAllDishes();

            assertEquals(dishes.size(), 1);
            Utils.assertFullyEqualsDishes(dishes.get(0), allDishes.get(0));
        }
    }

//    @Test
//    public void getSortedDishesOnPage() throws SQLException, DbExceptions {
//        Dish actualDish = getTestDishes(1).get(0);
//        Utils.mockResultSetForDish(actualDish, resultSet);
//        mockOtherFields(SqlUtils.GET_SORTED_DISHES_FROM_CATEGORY + "sortingField LIMIT 0, 5");
//        try (MockedStatic<ConnectionPool> cpMock = Mockito.mockStatic(ConnectionPool.class)) {
//            cpMock.when(ConnectionPool::getInstance).thenReturn(connectionPool);
//            Utils.mockResultSetForDish(dish, resultSet);
//            mockOtherFields(SqlUtils.GET_SORTED_DISHES_FROM_CATEGORY+" by sortingField");
//
//            Dish dish2 = DAO.getDao().getDishDao()
//                    .getSortedDishesOnPage(" by sortingField").get(0);
//            Utils.assertFullyEqualsDishes(dish, dish);
//        }
//    }

    @Test
    public void getDishById() throws SQLException, DbExceptions {
        Dish actualDish = getTestDishes(1).get(0);
        Utils.mockResultSetForDish(actualDish, resultSet);
        mockOtherFields(SqlUtils.GET_DISH_BY_ID);
        try (MockedStatic<ConnectionPool> cpMock = Mockito.mockStatic(ConnectionPool.class)) {
            cpMock.when(ConnectionPool::getInstance).thenReturn(connectionPool);
            Dish dishTest=DAO.getDao().getDishDao().getDishById(1);
            assertNotNull(dishTest);
            //Utils.assertFullyEqualsDishes(dishTest, actualDish);
        }
    }

//    @Test
//    public void getSortedDishesFromCategoryOnPage() throws SQLException, DbExceptions {
//        Dish actualDish = getTestDishes(1).get(0);
//        Utils.mockResultSetForDish(actualDish, resultSet);
//        mockOtherFields(SqlUtils.GET_SORTED_DISHES_FROM_CATEGORY + "by sortingField");
//        try (MockedStatic<ConnectionPool> cpMock = Mockito.mockStatic(ConnectionPool.class)) {
//            cpMock.when(ConnectionPool::getInstance).thenReturn(connectionPool);
//            List<Dish> dishTest = DAO.getDao().getDishDao().getSortedDishesOnPage(" by sortingField");
//
//            Utils.assertFullyEqualsDishes(dishTest.get(0), actualDish);
//        }
//    }

//    //all sorting and paginating happens in sql queries, so there isn't need to test them
//    @Test
//    void getSortedDishesFromCategoryOnPage() throws SQLException, DbException {
//        Dish actualDish = getTestDishes(1).get(0);
//        Utils.mockResultSetForDish(actualDish, resultSet);
//        mockOtherFields(SqlUtils.GET_SORTED_DISHES_FROM_CATEGORY + "sortingField LIMIT 0, 5");
//        try (MockedStatic<ConnectionPool> cpMock = Mockito.mockStatic(ConnectionPool.class)) {
//            cpMock.when(ConnectionPool::getInstance).thenReturn(connectionPool);
//            Dish dish = DAO.getDao().getDishDao()
//                    .getSortedDishesFromCategoryOnPage(1, "sortingField", 5, 0).get(0);
//            Utils.assertFullyEqualsDishes(dish, actualDish);
//        }
//    }

//    @Test
//    void getSortedDishesOnPage() throws SQLException, DbException {
//        Dish actualDish = getTestDishes(1).get(0);
//        Utils.mockResultSetForDish(actualDish, resultSet);
//        mockOtherFields(SqlUtils.GET_SORTED_DISHES + "sortingField LIMIT 0, 5");
//        try (MockedStatic<ConnectionPool> cpMock = Mockito.mockStatic(ConnectionPool.class)) {
//            cpMock.when(ConnectionPool::getInstance).thenReturn(connectionPool);
//            Dish dish = DAO.getDao().getDishDao()
//                    .getSortedDishesOnPage("sortingField", 5, 0).get(0);
//            Utils.assertFullyEqualsDishes(dish, actualDish);
//        }
//    }
//
//    @Test
//    void getDishesNumber() throws SQLException, DbException {
//        int expected = 5;
//        Mockito.when(resultSet.getInt(1)).thenReturn(expected);
//        mockOtherFields(SqlUtils.GET_DISHES_COUNT);
//        try (MockedStatic<ConnectionPool> cpMock = Mockito.mockStatic(ConnectionPool.class)) {
//            cpMock.when(ConnectionPool::getInstance).thenReturn(connectionPool);
//            int actual = DAO.getDao().getDishDao().getDishesNumber();
//            assertEquals(expected, actual);
//        }
//    }
//
//    @Test
//    void getDishesNumberInCategory() throws SQLException, DbException {
//        int expected = 5;
//        Mockito.when(resultSet.getInt(1)).thenReturn(expected);
//        mockOtherFields(SqlUtils.GET_DISHES_COUNT_IN_CATEGORY);
//        try (MockedStatic<ConnectionPool> cpMock = Mockito.mockStatic(ConnectionPool.class)) {
//            cpMock.when(ConnectionPool::getInstance).thenReturn(connectionPool);
//            int actual = DAO.getDao().getDishDao().getDishesNumberInCategory(1);
//            assertEquals(expected, actual);
//        }
//    }

    private void mockOtherFields(String sqlQuery) throws SQLException {
        Mockito.when(resultSet.next()).thenReturn(true).thenReturn(false);
        Mockito.when(preparedStatement.executeQuery()).thenReturn(resultSet);
        Mockito.when(connection.prepareStatement(sqlQuery)).thenReturn(preparedStatement);
        Mockito.when(connectionPool.getConnection()).thenReturn(connection);
    }

    public static List<Dish> getTestDishes(long... ids) {
        List<Dish> dishes = new ArrayList<>();
        for (long id : ids) {
            dishes.add(new Dish.Builder()
                    .setId(String.valueOf(id))
                    .setName("DishName" + id)
                    .setCategoryId(1)
                    .setPrice(1000)
                    .setWeight(100)
                    .setDescription("DishDescription" + id)
                    .getDish());
        }
        return dishes;
    }

}