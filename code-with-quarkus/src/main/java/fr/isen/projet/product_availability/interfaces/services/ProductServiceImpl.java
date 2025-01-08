package fr.isen.projet.product_availability.interfaces.services;

import fr.isen.projet.product_availability.interfaces.models.Product;
import fr.isen.projet.product_availability.interfaces.services.ProductService;
import fr.isen.projet.product_availability.interfaces.models.enums.STATE_PRODUCT;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductServiceImpl implements ProductService {

    private final String jdbcUrl = "jdbc:mysql://localhost:3306/your_database_name";
    private final String jdbcUser = "your_username";
    private final String jdbcPassword = "your_password";

    @Override
    public Product getOneProduct(String id_product) {
        String query = "SELECT * FROM products WHERE id_product = ? AND bDelete = false";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, id_product);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return mapToProduct(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Product> getAllProduct() {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM products WHERE bDelete = false";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                products.add(mapToProduct(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public void updateProduct(Product product) {
        String query = "UPDATE products SET name = ?, unit_price = ?, status = ?, id_subcategory = ?, stock = ?, id_provider = ?, date_updated = NOW() WHERE id_product = ? AND bDelete = false";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, product.name);
            statement.setFloat(2, product.unit_price);
            statement.setString(3, product.status.name());
            statement.setString(4, product.id_subcategory);
            statement.setInt(5, product.stock);
            statement.setString(6, product.id_provider);
            statement.setString(7, product.id_product);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeProduct(String id_product) {
        String query = "UPDATE products SET bDelete = true WHERE id_product = ?";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, id_product);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String addProduct(Product product) {
        String query = "INSERT INTO products (id_product, name, unit_price, status, id_subcategory, stock, id_provider, date_added, date_created, bDelete) VALUES (?, ?, ?, ?, ?, ?, ?, NOW(), NOW(), false)";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, product.id_product);
            statement.setString(2, product.name);
            statement.setFloat(3, product.unit_price);
            statement.setString(4, product.status.name());
            statement.setString(5, product.id_subcategory);
            statement.setInt(6, product.stock);
            statement.setString(7, product.id_provider);

            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Product mapToProduct(ResultSet resultSet) throws SQLException {
        Product product = new Product();
        product.id_product = resultSet.getString("id_product");
        product.name = resultSet.getString("name");
        product.unit_price = resultSet.getFloat("unit_price");

        // Gestion correcte de l'énumération 'status'
        String statusString = resultSet.getString("status");
        if (statusString != null) {
            try {
                // Normalisation de la chaîne et utilisation de l'énumération pour l'attribut 'status'
                product.status = STATE_PRODUCT.valueOf(statusString.trim().toUpperCase().replace(" ", "_"));
            } catch (IllegalArgumentException e) {
                // Si la chaîne ne correspond à aucune valeur de l'énumération, assigner une valeur par défaut
                product.status = STATE_PRODUCT.Available; // Exemple de valeur par défaut
                System.err.println("Invalid status value: " + statusString);
            }
        }

        product.id_subcategory = resultSet.getString("id_subcategory");
        product.stock = resultSet.getInt("stock");
        product.id_provider = resultSet.getString("id_provider");
        product.date_added = resultSet.getTimestamp("date_added");
        product.date_created = resultSet.getTimestamp("date_created");
        product.date_updated = resultSet.getTimestamp("date_updated");
        product.bDelete = resultSet.getBoolean("bDelete");

        return product;
    }



}
