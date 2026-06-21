package menu;

import dao.CustomerDAO;
import exception.CustomerNotFoundException;
import model.Customer;

import java.util.Scanner;

public class CustomerMenu {

    private CustomerDAO customerDAO = new CustomerDAO();
    private Scanner sc = new Scanner(System.in);

    public void showMenu() {

        while (true) {

            System.out.println("\n===== Customer Module =====");

            System.out.println("1. Register Customer");
            System.out.println("2. View Customers");
            System.out.println("3. Search Customer");
            System.out.println("4. Update Customer");
            System.out.println("5. Delete Customer");
            System.out.println("6. Back");

            System.out.print("Enter Choice : ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    addCustomer();
                    break;

                case 2:
                    viewCustomers();
                    break;

                case 3:
                    searchCustomer();
                    break;

                case 4:
                    updateCustomer();
                    break;

                case 5:
                    deleteCustomer();
                    break;

                case 6:
                    return;

                default:
                    System.out.println("Invalid Choice!");
            }
        }
    }

    public void addCustomer() {

        System.out.print("Enter Name : ");
        String name = sc.nextLine();

        System.out.print("Enter Email : ");
        String email = sc.nextLine();

        System.out.print("Enter Phone : ");
        String phone = sc.nextLine();

        Customer customer =
                new Customer(0, name, email, phone);

        customerDAO.addCustomer(customer);
    }

    public void viewCustomers() {

        for (Customer customer : customerDAO.getAllCustomers()) {

            System.out.println(customer);
        }
    }

    public void searchCustomer() {

        System.out.print("Enter Customer ID : ");

        int customerId = sc.nextInt();

        try {

            Customer customer =
                    customerDAO.getCustomerById(customerId);

            System.out.println(customer);

        } catch (CustomerNotFoundException e) {

            System.out.println(e.getMessage());
        }
    }

    public void updateCustomer() {

        System.out.print("Enter Customer ID : ");
        int customerId = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter New Name : ");
        String name = sc.nextLine();

        System.out.print("Enter New Email : ");
        String email = sc.nextLine();

        System.out.print("Enter New Phone : ");
        String phone = sc.nextLine();

        Customer customer =
                new Customer(customerId, name, email, phone);

        customerDAO.updateCustomer(customer);
    }

    public void deleteCustomer() {

        System.out.print("Enter Customer ID : ");

        int customerId = sc.nextInt();

        customerDAO.deleteCustomer(customerId);
    }



}