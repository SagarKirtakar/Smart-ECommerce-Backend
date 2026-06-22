package dao;

import model.CartItem;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;

import model.ViewCartItem;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CartDAO {

    //--------INSERT CART OPERATION----------------------

    public void addToCart(CartItem cartItem) {

        String query =
                "INSERT INTO cart(customer_id, product_id, quantity) VALUES(?,?,?)";

        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement ps = connection.prepareStatement(query)
        ) {

            ps.setInt(1, cartItem.getCustomerId());
            ps.setInt(2, cartItem.getProductId());
            ps.setInt(3, cartItem.getQuantity());

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Product Added To Cart Successfully!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //------------VIEW CART OPERATION--------------------

    public List<ViewCartItem> viewCart(int customerId) {

        List<ViewCartItem> cartItems = new ArrayList<>();

        String query = """
            SELECT p.name,
                   p.price,
                   c.quantity
            FROM cart c
            JOIN product p
            ON c.product_id = p.product_id
            WHERE c.customer_id = ?
            """;

        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement ps = connection.prepareStatement(query)
        ) {

            ps.setInt(1, customerId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                ViewCartItem item = new ViewCartItem(
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getInt("quantity")
                );

                cartItems.add(item);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return cartItems;
    }

    //----------CALCULATE TOTAL AMOUNT-------------

    public double calculateTotal(int customerId) {

        double total = 0;

        String query = """
            SELECT p.price, c.quantity
            FROM cart c
            JOIN product p
            ON c.product_id = p.product_id
            WHERE c.customer_id = ?
            """;

        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement ps = connection.prepareStatement(query)
        ) {

            ps.setInt(1, customerId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");

                total += price * quantity;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return total;
    }

    //-----------REMOVE CART OPERATION-----------

    public void removeFromCart(int cartId) {

        String query = "DELETE FROM cart WHERE cart_id=?";

        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement ps = connection.prepareStatement(query)
        ) {

            ps.setInt(1, cartId);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Product Removed From Cart Successfully!");
            } else {
                System.out.println("Cart Item Not Found!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }




}