package fa.training.entities;

import fa.training.utils.InvalidNumberException;
import fa.training.utils.Validator;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

public class Order implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String orderNumber;
    private Date oderDate;

    public Order() {
    }

    public void setOrderNumber(String orderNumber) throws InvalidNumberException {
        if (Validator.isOrderNumber(orderNumber))
            this.orderNumber = orderNumber;
        else throw new InvalidNumberException("Id is invalid!");
    }

    public void setOrderDate(Date oderDate) {
        this.oderDate = oderDate;
    }

    @Override
    public String toString() {
        return "Order{" +
                "Order Number='" + orderNumber + '\'' +
                ", Oder Date=" + oderDate +
                '}';
    }
}
