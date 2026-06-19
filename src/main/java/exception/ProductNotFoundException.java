package exception;

import model.Product;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ProductNotFoundException extends Exception {

    public ProductNotFoundException(String message) {
        super(message);
    }

    public Product getProductById(int productId)
            throws ProductNotFoundException {

        String query =
                "SELECT * FROM product WHERE product_id=?";

        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement ps = connection.prepareStatement(query)
        ) {

            ps.setInt(1, productId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                return new Product(
                        rs.getString("name"),
                        rs.getInt("product_id"),
                        rs.getString("category"),
                        rs.getDouble("price"),
                        rs.getInt("stock")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        throw new ProductNotFoundException(
                "Product with ID " + productId + " not found.");
    }


}