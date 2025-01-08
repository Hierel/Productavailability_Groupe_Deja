package fr.isen.projet.product_availability.interfaces.services;

import fr.isen.projet.product_availability.interfaces.models.Subcategory;
import fr.isen.projet.product_availability.interfaces.services.SubCategoryService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SubCategoryServiceImpl implements SubCategoryService {

    // Configuration de la connexion à la base de données
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/votre_base_de_donnees";
    private static final String JDBC_USERNAME = "votre_utilisateur";
    private static final String JDBC_PASSWORD = "votre_mot_de_passe";

    // Méthode pour obtenir toutes les sous-catégories
    @Override
    public List<Subcategory> getAllSubcategory() {
        List<Subcategory> subcategories = new ArrayList<>();
        String query = "SELECT * FROM subcategory WHERE bDelete = false";

        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Subcategory subcategory = new Subcategory();
                subcategory.id_subcategory = rs.getString("id_subcategory");
                subcategory.name = rs.getString("name");
                subcategory.active = rs.getBoolean("active");
                subcategory.id_category = rs.getString("id_category");
                subcategory.date_created = rs.getDate("date_created");
                subcategory.bDelete = rs.getBoolean("bDelete");
                subcategories.add(subcategory);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subcategories;
    }

    // Méthode pour obtenir une sous-catégorie par son ID
    @Override
    public Subcategory getOneSubcategory(final String id_subcategory) {
        Subcategory subcategory = null;
        String query = "SELECT * FROM subcategory WHERE id_subcategory = ? AND bDelete = false";

        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, id_subcategory);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    subcategory = new Subcategory();
                    subcategory.id_subcategory = rs.getString("id_subcategory");
                    subcategory.name = rs.getString("name");
                    subcategory.active = rs.getBoolean("active");
                    subcategory.id_category = rs.getString("id_category");
                    subcategory.date_created = rs.getDate("date_created");
                    subcategory.bDelete = rs.getBoolean("bDelete");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subcategory;
    }

    // Méthode pour ajouter une sous-catégorie
    @Override
    public String addSubcategory(final Subcategory subcategory) {
        String query = "INSERT INTO subcategory (id_subcategory, name, active, id_category, date_created, bDelete) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, subcategory.id_subcategory);
            stmt.setString(2, subcategory.name);
            stmt.setBoolean(3, subcategory.active);
            stmt.setString(4, subcategory.id_category);
            stmt.setDate(5, new java.sql.Date(subcategory.date_created.getTime()));
            stmt.setBoolean(6, subcategory.bDelete);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                return subcategory.id_subcategory;  // Renvoie l'ID de la sous-catégorie ajoutée
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Méthode pour mettre à jour une sous-catégorie
    @Override
    public void updateSubcategory(final Subcategory subcategory) {
        String query = "UPDATE subcategory SET name = ?, active = ?, id_category = ?, date_created = ?, bDelete = ? WHERE id_subcategory = ?";

        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, subcategory.name);
            stmt.setBoolean(2, subcategory.active);
            stmt.setString(3, subcategory.id_category);
            stmt.setDate(4, new java.sql.Date(subcategory.date_created.getTime()));
            stmt.setBoolean(5, subcategory.bDelete);
            stmt.setString(6, subcategory.id_subcategory);

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Méthode pour supprimer une sous-catégorie (logiquement, on la marque comme supprimée)
    @Override
    public void removeSubcategory(final String id_subcategory) {
        String query = "UPDATE subcategory SET bDelete = true WHERE id_subcategory = ?";

        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, id_subcategory);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
