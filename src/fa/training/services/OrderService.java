package fa.training.services;

// imports

import fa.training.entities.Order;
import fa.training.utils.InvalidNumberException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class OrderService {

    /**
     * Create new Order
     *
     * @param scanner
     * @return
     */

    public Set<Order> createOrder(Scanner scanner, int maxSize) {
        String loop = "";
        String orderNumber;
        Date oderDate;
        Order order;
        boolean addStatus = false;

        Set<Order> orders = new HashSet<Order>();
        do {
            order = new Order();

            // Set student orderNumber

            do {
                System.out.println("Enter order number");
                orderNumber = scanner.nextLine();
                try {
                    order.setOrderNumber(orderNumber);
                } catch (InvalidNumberException e) {
                    continue;
                }
                break;
            } while (true);

            // Set oder date
            System.out.println("Enter oder date:");
            SimpleDateFormat sdf_ddMMyy = new SimpleDateFormat("dd-MM-yyyy");
            try {
                oderDate = sdf_ddMMyy.parse(scanner.nextLine());
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            order.setOrderDate(oderDate);

            // Add oder to the list
            addStatus = orders.add(order);
            if (!addStatus) {
                System.out.println("<<Student existed in Enroll!>>");
            }

            // Continue to input?
            if (orders.size() < maxSize) {
                System.out.println("Do you want continue to input student for this course (Y/N)?: ");
                loop = scanner.nextLine();
            } else {
                loop = "N";
            }
        } while (loop.charAt(0) == 'Y' || loop.charAt(0) == 'y');
        return orders;
    }
}
