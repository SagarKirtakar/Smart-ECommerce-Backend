package dao;

import exception.CustomerNotFoundException;
import model.Customer;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class CustomerDAO {

    //-------------INSERT CUSTOMER OPERATION----------------------------------

    public void addCustomer(Customer customer) {

        String query =
                "INSERT INTO customer(name, email, phone) VALUES(?,?,?)";

        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement ps = connection.prepareStatement(query)
        ) {

            ps.setString(1, customer.getName());
            ps.setString(2, customer.getEmail());
            ps.setString(3, customer.getPhone());

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Customer Registered Successfully!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //----------------------SELECT ALL CUSTOMER OPERATION-------------------

    public List<Customer> getAllCustomers() {

        List<Customer> customers = new ArrayList<>();

        String query = "SELECT * FROM customer";

        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement ps = connection.prepareStatement(query);
                ResultSet rs = ps.executeQuery()
        ) {

            while (rs.next()) {

                Customer customer = new Customer(
                        rs.getInt("customer_id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("phone")
                );

                customers.add(customer);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return customers;

    }

    //-----------GET CUSTOMER BY ID------------

    public Customer getCustomerById(int customerId)
            throws CustomerNotFoundException {

        String query =
                "SELECT * FROM customer WHERE customer_id=?";

        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement ps = connection.prepareStatement(query)
        ) {

            ps.setInt(1, customerId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                return new Customer(
                        rs.getInt("customer_id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("phone")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        throw new CustomerNotFoundException(
                "Customer with ID " + customerId + " not found.");

    }

//-----------UPDATE CUSTOMER---------------------

    public void updateCustomer(Customer customer) {

        String query = """
                UPDATE customer
                SET name=?, email=?, phone=?
                WHERE customer_id=?
                """;

        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement ps = connection.prepareStatement(query)
        ) {

            ps.setString(1, customer.getName());
            ps.setString(2, customer.getEmail());
            ps.setString(3, customer.getPhone());
            ps.setInt(4, customer.getCustomerId());

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Customer Updated Successfully!");
            } else {
                System.out.println("Customer Not Found!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

        //---------DELETE CUSTOMER BY ID--------------------
        public void deleteCustomer ( int customerId){

            String query = "DELETE FROM customer WHERE customer_id=?";

            try (
                    Connection connection = DBConnection.getConnection();
                    PreparedStatement ps = connection.prepareStatement(query)
            ) {

                ps.setInt(1, customerId);

                int rows = ps.executeUpdate();

                if (rows > 0) {
                    System.out.println("Customer Deleted Successfully!");
                } else {
                    System.out.println("Customer Not Found!");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

