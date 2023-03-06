package fa.training.services;

import fa.training.entities.Customer;
import fa.training.entities.Order;
import fa.training.utils.Constants;
import fa.training.utils.PhoneFormatException;

import java.io.*;
import java.util.*;

public class CustomerService {

    /**
     * Input list of customer
     */
    public List<Customer> createCustomer(Scanner scanner) {
        String loop, name, phoneNumber, address;
        Customer customer;
        Set<Order> orders;
        List<Customer> customers = new ArrayList<>();
        OrderService orderService = new OrderService();

        do {
            customer = new Customer();

            //Set customer name
            System.out.println("Enter customer name:");
            name = scanner.nextLine();
            customer.setCustomerName(name);

            // Set customer phone
            do {
                System.out.println("Enter customer phone:");
                phoneNumber = scanner.nextLine();
                try {
                    customer.setPhoneNumber(phoneNumber);
                } catch (PhoneFormatException e) {
                    continue;
                }
                break;
            } while (true);

            //Set customer address
            System.out.println("Enter customer address:");
            address = scanner.nextLine();
            customer.setAddress(address);

            // Set order to the customer
            System.out.println("----Enter order in the customer----");
            orders = orderService.createOrder(scanner, Integer.parseInt(phoneNumber));
            customer.setOrders(orders);

            // Add customer to list
            customers.add(customer);

            // Do you want to continue?
            System.out.println("Do you want continue to input customer (Y/N)?: ");
            loop = scanner.nextLine();

        } while (loop.charAt(0) == 'Y' || loop.charAt(0) == 'y');
        return customers;
    }

    /**
     * Save list of courses to file
     */
    public String save(List<Customer> customer) throws Exception {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(Constants.CUSTOMER_FILE_PATH)); objectOutputStream) {
            objectOutputStream.writeObject(customer);
        } catch (IOException exception) {
            throw new Exception();
        }
        return Constants.SUCCESS;
    }

    /**
     * Get all the customer in file
     */
    @SuppressWarnings("unchecked")
    public List<Customer> findAll() throws IOException {
        ObjectInputStream objectInputStream = null;
        List<Customer> customers;
        try {
            objectInputStream = new ObjectInputStream(new FileInputStream(Constants.CUSTOMER_FILE_PATH));
            customers = (List<Customer>) objectInputStream.readObject();
        } catch (Exception exception) {
            throw new IOException();
        } finally {
            if (objectInputStream != null) {
                objectInputStream.close();
            }
        }
        return customers;
    }

    /**
     * Display data
     */
    public void display(List<Customer> customers) {

        System.out.println("---------------CUSTOMER LIST-------------------");

        for (Customer customer : customers) {
            System.out.println(customer.toString());
        }

    }

    /**
     * Search order by the customer
     */
    public List<Order> search(String phone) throws IOException {
        List<Customer> customers = findAll();
        List<Order> ordersByCustomer = new ArrayList<>();

        if (customers != null) {
            for (Customer customer : customers ) {
                for (Order orderCustomer : customer.getOrders()) {
                    if (phone.equalsIgnoreCase(customer.getPhoneNumber())) {
                        ordersByCustomer.add(orderCustomer);
                    }
                }
            }
        }
        return ordersByCustomer;
    }

    /**
     * Remove a specific customer by phone number from the customer file
     */
    public String remove(String phone) throws Exception {
        boolean removed = false;

        List<Customer> customers = findAll();
        if (customers == null) {
            throw new IOException();
        }
        Iterator<Customer> iterator = customers.iterator();
        while (iterator.hasNext()) {
            Customer customer = iterator.next();
            if (customer.getPhoneNumber().equalsIgnoreCase(phone)) {
                iterator.remove();
                removed = true;
                break;
            }
        }

        if (removed) {
            try {
                // update list
                save(customers);
            } catch (Exception exception) {
                throw new Exception();
            }

            return Constants.SUCCESS;
        }
        return Constants.FAIL;
    }
}
