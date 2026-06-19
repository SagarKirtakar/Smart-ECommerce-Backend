package dao;

import exception.ProductNotFoundException;
import model.Product;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    // ADD PRODUCT OPERATION

    public void addProduct(Product product) {

        String query =
                "INSERT INTO product(name,category,price,stock) VALUES(?,?,?,?)";

        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement ps = connection.prepareStatement(query)
        ) {

            ps.setString(1, product.getName());
            ps.setString(2, product.getCategory());
            ps.setDouble(3, product.getPrice());
            ps.setInt(4, product.getStock());

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Product Added Successfully!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // GET ALL PRODUCTS

    public List<Product> getAllProducts() {

        List<Product> products = new ArrayList<>();

        String query = "SELECT * FROM product";

        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement ps = connection.prepareStatement(query);
                ResultSet rs = ps.executeQuery()
        ) {

            while (rs.next()) {

                Product product = new Product(
                        rs.getString("name"),
                        rs.getInt("product_id"),
                        rs.getString("category"),
                        rs.getDouble("price"),
                        rs.getInt("stock")
                );

                products.add(product);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return products;
    }

    // GET PRODUCT BY ID

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

    // UPDATE PRODUCT

    public void updateProduct(Product product) {

        String query = """
            UPDATE product
            SET name=?, category=?, price=?, stock=?
            WHERE product_id=?
            """;

        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement ps = connection.prepareStatement(query)
        ) {

            ps.setString(1, product.getName());
            ps.setString(2, product.getCategory());
            ps.setDouble(3, product.getPrice());
            ps.setInt(4, product.getStock());
            ps.setInt(5, product.getProductId());

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Product Updated Successfully!");
            } else {
                System.out.println("Product Not Found!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // DELETE PRODUCT BY ID

    public void deleteProduct(int productId) {

        String query = "DELETE FROM product WHERE product_id=?";

        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement ps = connection.prepareStatement(query)
        ) {

            ps.setInt(1, productId);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Product Deleted Successfully!");
            } else {
                System.out.println("Product Not Found!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //-----------LOW STOCK ALERT----------
    public void getLowStockProducts() {

        String query = """
            SELECT *
            FROM product
            WHERE stock <= 5
            """;

        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement ps =
                        connection.prepareStatement(query)
        ) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                String name = rs.getString("name");
                int stock = rs.getInt("stock");

                System.out.println(
                        name + " - Stock = " + stock);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    //------COLLECTION FRAMEWORK + SORTING PRODUCTS-----------


}