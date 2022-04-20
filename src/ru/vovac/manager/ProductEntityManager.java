package ru.vovac.manager;

import ru.vovac.App;
import ru.vovac.entity.ProductEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductEntityManager {

    public static void insert(ProductEntity entity) throws SQLException {
        try(Connection connection = App.getConnection()){

            String sql =
                "insert into " +
                "product(" +
                    "Title, " +
                    "ProductType, " +
                    "ArticleNumber, " +
                    "Description, " +
                    "Image, " +
                    "ProductionPersonCount, " +
                    "ProductionWorkshopNumber," +
                    " MinCostForAgent) " +
                "values(?,?,?,?,?,?,?,?)";

            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, entity.getTitle());
            statement.setString(2, entity.getProductType());
            statement.setString(3, entity.getArticleNumber());
            statement.setString(4, entity.getDescription());
            statement.setString(5, entity.getImagePath());
            statement.setInt(6, entity.getPersonCount());
            statement.setInt(7, entity.getWorkshopNumber());
            statement.setInt(8, entity.getMinCost());
            statement.executeUpdate();


            ResultSet resultSet = statement.getGeneratedKeys();
            while(resultSet.next()){
                entity.setID(resultSet.getInt(1));
            }
        }
    }

    public static void update(ProductEntity entity) throws SQLException {
        try(Connection connection = App.getConnection()){

            String sql =
                "update product set " +
                "Title=?, " +
                "ProductType=?, " +
                "ArticleNumber=?, " +
                "Description=?, " +
                "Image=?, " +
                "ProductionPersonCount=?, " +
                "ProductionWorkshopNumber=?, " +
                "MinCostForAgent=? " +
                "where ID = ?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, entity.getTitle());
            statement.setString(2, entity.getProductType());
            statement.setString(3, entity.getArticleNumber());
            statement.setString(4, entity.getDescription());
            statement.setString(5, entity.getImagePath());
            statement.setInt(6, entity.getPersonCount());
            statement.setInt(7, entity.getWorkshopNumber());
            statement.setInt(8, entity.getMinCost());
            statement.setInt(9, entity.getID());
            statement.executeUpdate();
        }
    }

    public static void delete(int id) throws SQLException {
        try(Connection connection = App.getConnection()){

            String sql = "delete from product where ID=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }

    public static List<ProductEntity> selectAll() throws SQLException {
        try(Connection connection = App.getConnection()){

            String sql = "select * from product";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            List<ProductEntity> entityList = new ArrayList<>();

            while(resultSet.next()){
                entityList.add(new ProductEntity(
                        resultSet.getInt("ID"),
                        resultSet.getString("Title"),
                        resultSet.getString("ProductType"),
                        resultSet.getString("ArticleNumber"),
                        resultSet.getString("Description"),
                        resultSet.getString("Image"),
                        resultSet.getInt("ProductionPersonCount"),
                        resultSet.getInt("ProductionWorkshopNumber"),
                        resultSet.getInt("MinCostForAgent")
                ));
            }

            return entityList;
        }
    }

    public static ProductEntity select(int id) throws SQLException {
        try (Connection connection = App.getConnection()) {

            String sql = "select * from product where ID=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return new ProductEntity(
                        resultSet.getInt("ID"),
                        resultSet.getString("Title"),
                        resultSet.getString("ProductType"),
                        resultSet.getString("ArticleNumber"),
                        resultSet.getString("Description"),
                        resultSet.getString("Image"),
                        resultSet.getInt("ProductionPersonCount"),
                        resultSet.getInt("ProductionWorkshopNumber"),
                        resultSet.getInt("MinCostForAgent")
                );
            }

            throw new SQLException("Такого ID нет в базе данных");
        }
    }
}
