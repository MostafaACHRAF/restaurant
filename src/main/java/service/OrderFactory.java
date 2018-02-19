package service;

import java.util.ArrayList;
import java.util.List;

public class OrderFactory {

    private final List<Order> orders = new ArrayList<Order>();

    public Order create(int tableId, String customerSays) {
        String[] extractedData = customerSays.split(": ");
        String customerName = extractedData[0];
        String customerOrder = extractedData[1];

        if (isOrderFor(customerOrder)) {
            return new OrderFor(tableId, customerName, getOrderContentFrom(customerOrder));
        }

    }

    private boolean isOrderFor(String customerOrder) {
        String[] orderContentDetails = customerOrder.split(" ");
        return orderContentDetails.length == 3 && orderContentDetails[1].equals("for");
    }

    private String getOrderContentFrom(String orderContent) {
        return orderContent.split(" ")[0];
    }
}
