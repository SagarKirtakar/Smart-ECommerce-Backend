package dao;

import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AnalyticsDAO {

    //-----------GET TOTAL REVENUE----------------

    public void getTotalRevenue() {

        String query =
                "SELECT SUM(total_amount) AS total_revenue FROM orders";

        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement ps =
                        connection.prepareStatement(query)
        ) {

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                double revenue = rs.getDouble("total_revenue");

                if (revenue == 0) {
                    System.out.println("No revenue generated yet.");
                } else {
                    System.out.println("Total Revenue = ₹" + revenue);
                }
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public void getTotalOrders() {

        String query =
                "SELECT COUNT(*) AS total_orders FROM orders";

        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement ps =
                        connection.prepareStatement(query)
        ) {

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                int totalOrders = rs.getInt("total_orders");

                if (totalOrders == 0) {

                    System.out.println("No orders placed yet.");

                } else {

                    System.out.println(
                            "Total Orders = " + totalOrders);
                }
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

//-----------TOP SELLING PRODUCTS----------------

    public void getTopSellingProducts() {

        String query = """
            SELECT
                p.name,
                SUM(oi.quantity) AS total_sold
            FROM order_items oi
            JOIN product p
            ON oi.product_id = p.product_id
            GROUP BY p.product_id, p.name
            ORDER BY total_sold DESC
            """;

        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement ps =
                        connection.prepareStatement(query)
        ) {

            ResultSet rs = ps.executeQuery();

            boolean found = false;

            while (rs.next()) {

                found = true;

                String productName =
                        rs.getString("name");

                int totalSold =
                        rs.getInt("total_sold");

                System.out.println(
                        "Product = " + productName +
                                ", Total Sold = " + totalSold);
            }

            if (!found) {

                System.out.println(
                        "No products sold yet.");
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }






}
