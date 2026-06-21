package menu;

import dao.OrderDAO;

import java.util.Scanner;

public class OrderMenu {

    private OrderDAO orderDAO = new OrderDAO();
    private Scanner sc = new Scanner(System.in);

    public void showMenu() {

        while (true) {

            System.out.println("\n===== Order Module =====");

            System.out.println("1. Place Order");
            System.out.println("2. View Order History");
            System.out.println("3. Search Order");
            System.out.println("4. View Order Details");
            System.out.println("5. Back");

            System.out.print("Enter Choice : ");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    placeOrder();
                    break;

                case 2:
                    viewOrderHistory();
                    break;

                case 3:
                    searchOrder();
                    break;

                case 4:
                    viewOrderDetails();
                    break;

                case 5:
                    return;

                default:
                    System.out.println("Invalid Choice!");
            }
        }
    }

    public void placeOrder() {

        System.out.print("Enter Customer ID : ");
        int customerId = sc.nextInt();

        orderDAO.placeOrder(customerId);
    }

    public void viewOrderHistory() {

        System.out.print("Enter Customer ID : ");
        int customerId = sc.nextInt();

        orderDAO.getOrders(customerId);
    }

    public void searchOrder() {

        System.out.print("Enter Order ID : ");
        int orderId = sc.nextInt();

        orderDAO.searchOrder(orderId);
    }

    public void viewOrderDetails() {

        System.out.print("Enter Order ID : ");
        int orderId = sc.nextInt();

        orderDAO.getOrderItems(orderId);
    }


}