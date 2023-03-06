package fa.training.main;

import fa.training.entities.Customer;
import fa.training.entities.Order;
import fa.training.services.CustomerService;
import fa.training.utils.Constants;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Test {

    private static List<Customer> listNewCustomer;
    private static List<Customer> listCustomer;

    public static void main(String[] args) {
        String choice, status, phoneNumber;

        List<Order> ordersByCustomer;
        CustomerService customerService = new CustomerService();
        try (Scanner scanner = new Scanner(System.in)) {
            do {
                System.out.println("Choose function:");
                System.out.println("""
                        1. Add a new Customer
                        2. Show all Customer
                        3. Search Customer
                        4. Remove Customer
                        5. Exit""");
                choice = scanner.nextLine();
                choice = choice.trim();
                switch (choice) {
                    // Create new Customer
                    case Constants.INPUT -> {
                        if (listNewCustomer != null) {
                            listNewCustomer.clear();
                        }
                        listNewCustomer = customerService.createCustomer(scanner);
                        System.out.println("Input done!");

                        // Save to file
                        try {
                            if (listNewCustomer == null) {
                                throw new Exception();
                            }
                            status = customerService.save(listNewCustomer);
                            System.out.println("Save: " + status);
                        } catch (Exception e) {
                            System.out.println("Save Fail!");
                        }
                    }

                    //Display data
                    case Constants.SHOW -> {
                        if (listCustomer != null) {
                            listCustomer.clear();
                        }
                        try {
                            listCustomer = customerService.findAll();
                            if (listCustomer == null) {
                                throw new IOException();
                            }
                            customerService.display(listCustomer);
                        } catch (IOException e) {
                            System.out.println("No data to display");
                        }
                    }

                    //Search order by the customer
                    case Constants.SEARCH -> {
                        try {
                            System.out.print("Enter phone number: ");
                            phoneNumber = scanner.nextLine();
                            ordersByCustomer = customerService.search(phoneNumber);

                            if (ordersByCustomer == null) {
                                throw new IOException();
                            }
                            System.out.println("---List of Order---");
                            for (Order order : ordersByCustomer) {
                                System.out.printf(order.toString());
                            }
                        } catch (IOException e) {
                            System.out.print("No data to search");
                        }
                    }

                    //Remove a specific customer by phone number from the customer file
                    case Constants.REMOVE -> {
                        System.out.println("Enter a phone number to remove");
                        phoneNumber = scanner.nextLine();

                        try {
                            status = customerService.remove(phoneNumber);
                            System.out.println("Remove: " + status);
                        } catch (Exception e) {
                            System.out.println("Remove Fail!");
                        }
                    }
                    default -> choice = Constants.EXIT;
                }
            } while (!choice.equalsIgnoreCase(Constants.EXIT));
        }
    }
}
