package dao;

import entity.Category;
import exceptions.DbExceptions;

import java.util.List;

public interface CategoryDAO {
    abstract List<Category> getAllCategories() throws DbExceptions;
}
