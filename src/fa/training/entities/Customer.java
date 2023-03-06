package fa.training.entities;

import fa.training.utils.PhoneFormatException;
import fa.training.utils.Validator;

import java.io.Serial;
import java.io.Serializable;
import java.util.Set;

public class Customer implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String customerName;
    private String phoneNumber;
    private String address;
    private Set<Order> orders;

    public Customer() {
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) throws PhoneFormatException {
        if (Validator.isPhone(phoneNumber))
            this.phoneNumber = phoneNumber;
        else throw new PhoneFormatException("Phone number invalid!");
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerName='" + customerName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", orders=" + orders +
                '}';
    }
}
