package dao;

import model.Order;
import util.DBConnection;

import java.sql.*;

public class OrderDAO {

    public void placeOrder(int customerId) {

        Connection connection = null;

        try {

            connection = DBConnection.getConnection();

            // Start transaction
            connection.setAutoCommit(false);

            // Step 1: Calculate total amount
            double totalAmount = 0;

            String totalQuery = """
                SELECT p.price, c.quantity
                FROM cart c
                JOIN product p
                ON c.product_id = p.product_id
                WHERE c.customer_id = ?
                """;

            PreparedStatement totalPs =
                    connection.prepareStatement(totalQuery);

            totalPs.setInt(1, customerId);

            ResultSet rs = totalPs.executeQuery();

            while (rs.next()) {

                double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");

                totalAmount += price * quantity;
            }


            // Step 2: Insert into orders table
            String orderQuery =
                    "INSERT INTO orders(customer_id, total_amount) VALUES(?, ?)";

            PreparedStatement orderPs =
                    connection.prepareStatement(
                            orderQuery,
                            Statement.RETURN_GENERATED_KEYS);

            orderPs.setInt(1, customerId);
            orderPs.setDouble(2, totalAmount);

            orderPs.executeUpdate();


            // Step 3: Get generated order_id
            ResultSet generatedKeys = orderPs.getGeneratedKeys();

            int orderId = 0;

            if (generatedKeys.next()) {

                orderId = generatedKeys.getInt(1);
            }


            // Step 4: Get cart items
            String cartQuery = """
                SELECT c.product_id,
                       c.quantity,
                       p.price
                FROM cart c
                JOIN product p
                ON c.product_id = p.product_id
                WHERE c.customer_id = ?
                """;

            PreparedStatement cartPs =
                    connection.prepareStatement(cartQuery);

            cartPs.setInt(1, customerId);

            ResultSet cartRs = cartPs.executeQuery();


            // Step 5: Insert into order_items and update stock
            while (cartRs.next()) {

                int productId = cartRs.getInt("product_id");
                int quantity = cartRs.getInt("quantity");
                double price = cartRs.getDouble("price");


                // Insert into order_items
                String itemQuery = """
                    INSERT INTO order_items(
                            order_id,
                            product_id,
                            quantity,
                            price)
                    VALUES (?, ?, ?, ?)
                    """;

                PreparedStatement itemPs =
                        connection.prepareStatement(itemQuery);

                itemPs.setInt(1, orderId);
                itemPs.setInt(2, productId);
                itemPs.setInt(3, quantity);
                itemPs.setDouble(4, price);

                itemPs.executeUpdate();


                // Update product stock
                String stockQuery = """
                    UPDATE product
                    SET stock = stock - ?
                    WHERE product_id = ?
                    """;

                PreparedStatement stockPs =
                        connection.prepareStatement(stockQuery);

                stockPs.setInt(1, quantity);
                stockPs.setInt(2, productId);

                stockPs.executeUpdate();
            }


            // Step 6: Clear cart
            String clearCartQuery =
                    "DELETE FROM cart WHERE customer_id = ?";

            PreparedStatement clearPs =
                    connection.prepareStatement(clearCartQuery);

            clearPs.setInt(1, customerId);

            clearPs.executeUpdate();


            // Step 7: Commit transaction
            connection.commit();

            System.out.println("Order Placed Successfully!");

        } catch (Exception e) {

            try {

                if (connection != null) {

                    connection.rollback();
                }

            } catch (Exception ex) {

                ex.printStackTrace();
            }

            e.printStackTrace();

        } finally {

            try {

                if (connection != null) {

                    connection.setAutoCommit(true);
                    connection.close();
                }

            } catch (Exception e) {

                e.printStackTrace();
            }
        }
    }

    //--------VIEW ORDER DETAILS-------------
    public void getOrders(int customerId) {

        String query = """
            SELECT *
            FROM orders
            WHERE customer_id = ?
            """;

        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement ps =
                        connection.prepareStatement(query)
        ) {

            ps.setInt(1, customerId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                int orderId = rs.getInt("order_id");
                int customerId1 = rs.getInt("customer_id");
                Timestamp orderDate = rs.getTimestamp("order_date");
                double totalAmount = rs.getDouble("total_amount");

                Order order = new Order(
                        orderId,
                        customerId1,
                        orderDate,
                        totalAmount
                );

                System.out.println(order);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    //---------GET ORDER ITEMS DETAILS------------
    public void getOrderItems(int orderId) {

        String query = """
            SELECT p.name,
                   oi.price,
                   oi.quantity
            FROM order_items oi
            JOIN product p
            ON oi.product_id = p.product_id
            WHERE oi.order_id = ?
            """;

        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement ps =
                        connection.prepareStatement(query)
        ) {

            ps.setInt(1, orderId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                String productName = rs.getString("name");
                double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");

                System.out.println(
                        "Product = " + productName +
                                ", Price = " + price +
                                ", Quantity = " + quantity
                );
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    //-------SEARCH ORDER BY ID----------
    public void searchOrder(int orderId) {

        String query =
                "SELECT * FROM orders WHERE order_id=?";

        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement ps =
                        connection.prepareStatement(query)
        ) {

            ps.setInt(1, orderId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                Order order = new Order(
                        rs.getInt("order_id"),
                        rs.getInt("customer_id"),
                        rs.getTimestamp("order_date"),
                        rs.getDouble("total_amount")
                );

                System.out.println(order);

            } else {

                System.out.println("Order Not Found!");
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    //-------TOTAL SALES REPORT------------
    public void getTotalSales() {

        String query =
                "SELECT SUM(total_amount) AS total_sales FROM orders";

        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement ps =
                        connection.prepareStatement(query)
        ) {

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                double totalSales =
                        rs.getDouble("total_sales");

                System.out.println(
                        "Total Revenue = ₹" + totalSales);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }




}