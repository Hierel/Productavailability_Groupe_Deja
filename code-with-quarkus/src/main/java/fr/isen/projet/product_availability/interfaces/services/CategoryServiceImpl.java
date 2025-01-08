package fr.isen.projet.product_availability.interfaces.services;

import fr.isen.projet.product_availability.interfaces.models.Category;
import fr.isen.projet.product_availability.interfaces.services.CategoryService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryServiceImpl implements CategoryService {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/your_database";
    private static final String DB_USER = "your_user";
    private static final String DB_PASSWORD = "your_password";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    @Override
    public Category getOneCategory(final String id_category) {
        String query = "SELECT * FROM categories WHERE id_category = ? AND bDelete = false";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, id_category);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return mapResultSetToCategory(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Category> getAllCategory() {
        List<Category> categories = new ArrayList<>();
        String query = "SELECT * FROM categories WHERE bDelete = false";
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                categories.add(mapResultSetToCategory(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }

    @Override
    public String addCategory(final Category category) {
        String query = "INSERT INTO categories (id_category, name, description, date_created, bDelete) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, category.id_category);
            preparedStatement.setString(2, category.name);
            preparedStatement.setString(3, category.description);
            preparedStatement.setDate(4, new java.sql.Date(System.currentTimeMillis()));
            preparedStatement.setBoolean(5, category.bDelete);
            preparedStatement.executeUpdate();
            return category.id_category;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateCategory(final Category category) {
        String query = "UPDATE categories SET name = ?, description = ?, bDelete = ? WHERE id_category = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, category.name);
            preparedStatement.setString(2, category.description);
            preparedStatement.setBoolean(3, category.bDelete);
            preparedStatement.setString(4, category.id_category);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeCategory(final String id_category) {
        String query = "UPDATE categories SET bDelete = true WHERE id_category = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, id_category);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Category mapResultSetToCategory(ResultSet resultSet) throws SQLException {
        Category category = new Category();
        category.id_category = resultSet.getString("id_category");
        category.name = resultSet.getString("name");
        category.description = resultSet.getString("description");
        category.date_created = resultSet.getDate("date_created");
        category.bDelete = resultSet.getBoolean("bDelete");
        return category;
    }
}
