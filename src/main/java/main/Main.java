package main;

import dao.CartDAO;
import dao.CustomerDAO;
import dao.OrderDAO;
import dao.ProductDAO;
import exception.CustomerNotFoundException;
import exception.ProductNotFoundException;
import model.CartItem;
import model.Customer;
import model.Product;
import model.ViewCartItem;
import util.DBConnection;

import java.sql.Connection;
import java.util.Comparator;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        try {
            Connection connection = DBConnection.getConnection();

            if (connection != null) {
//                System.out.println("Database Connected Successfully!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        //----------------------PRODUCT MODULE-----------------------------

        Product product1 =
                new Product("Headphones",
                        0,
                        "Electronics",
                        2500,
                        15);

        ProductDAO productDAO = new ProductDAO();

//        productDAO.addProduct(product1);

        List<Product> products = productDAO.getAllProducts();

        for (Product product : products) {
//            System.out.println(product);
        }

        // GET PRODUCT BY ID OPERATION

        try {

            Product product =
                    productDAO.getProductById(100);

//            System.out.println(product);

        } catch (ProductNotFoundException e) {

//            System.out.println(e.getMessage());

        }

        // CHECK UPDATE OPERATION

        Product updatedProduct =
                new Product(
                        "Mechanical Keyboard",
                        3,
                        "Accessories",
                        2500,
                        30
                );

//        productDAO.updateProduct(updatedProduct);

        // DELETE OPERATION BY ID

//        productDAO.deleteProduct(6);

        // --------------------------Customer Module-----------------------------

        // --------Insert operation--------

//        Customer customer = new Customer( "Sagar", "sagar@gmail.com", "9876543210" );
//        CustomerDAO customerDAO = new CustomerDAO();
//        customerDAO.addCustomer(customer);

        //---------SELECT ALL OPERATION------------

//        CustomerDAO customerDAO = new CustomerDAO();

//        List<Customer> customers = customerDAO.getAllCustomers();

//        for (Customer customer : customers) {
//            System.out.println(customer);
//        }

        //-------Get CUSTOMER BY ID----------

//        CustomerDAO customerDAO = new CustomerDAO();
//        try {
//            Customer customer = customerDAO.getCustomerById(100);
//            System.out.println(customer);
//        } catch (CustomerNotFoundException e) {
//            System.out.println(e.getMessage());
//        }

        //-------------UPDATE CUSTOMER BY ID--------------
//        Customer updatedCustomer = new Customer(
//                1,
//                "Sagar Kirtakar",
//                "sagar@gmail.com",
//                "9999999999"
//        );

//        customerDAO.updateCustomer(updatedCustomer);

        //---------DELETE CUSTOMER BY ID------------
        CustomerDAO customerDAO = new CustomerDAO();

//        customerDAO.deleteCustomer(1);

        //------------CART MODULE------------------

        CartDAO cartDAO = new CartDAO();

        CartItem cartItem = new CartItem(
                1,
                1,
                2
        );

//        cartDAO.addToCart(cartItem);

        //---------VIEW CART OPERATION

        List<ViewCartItem> items = cartDAO.viewCart(1);

//        for (ViewCartItem item : items) {
//            System.out.println(item);
//        }

        //---------CALCULATE TOTAL AMOUNT-------------

        double total = cartDAO.calculateTotal(1);

//        System.out.println("Total Amount = ₹" + total);

    //--------REMOVE CART OPERATION---------

//        cartDAO.removeFromCart(4);

        //-----------ORDER MODULE----------------

//        OrderDAO orderDAO = new OrderDAO();
//        orderDAO.placeOrder(1);

        //----------VIEW ORDER HISTORY----------
//        OrderDAO orderDAO = new OrderDAO();
//
//        orderDAO.getOrders(1);

        //--------GET ORDER DETAILS-------------
//        OrderDAO orderDAO = new OrderDAO();
//
//        orderDAO.getOrderItems(5);

        //--------SEARCH ODER BY ID---------
//        OrderDAO orderDAO = new OrderDAO();
//
//        orderDAO.searchOrder(5);

        //-------TOTAL SALES REPORT------------
//        OrderDAO orderDAO = new OrderDAO();
//
//        orderDAO.getTotalSales();

        //-------LOW STOCK ALERT----------
        ProductDAO productDAO2 = new ProductDAO();

//        productDAO2.getLowStockProducts();

    //--------SORT PRODUCTS BY USING COLLECTION FRAMEWORK-----------
        ProductDAO productDAO3 = new ProductDAO();

        List<Product> products2 = productDAO3.getAllProducts();

        products2.sort(
                Comparator.comparing(Product::getPrice)
        );

        for (Product product : products2) {

            System.out.println(product);
        }


    }
}