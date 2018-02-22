package service;

import java.util.ArrayList;
import java.util.List;

class OrderFactory {

    private String customerName;
    private String customerOrder;
    private List<Order> orders;

    OrderFactory(List<Order> orders) {
        this.orders.addAll(orders);
    }

    Order create(int tableId, String customerSays) {
        String[] extractedData = customerSays.split(": ");
        customerName = extractedData[0];
        customerOrder = extractedData[1];
        Order order = new NormalOrder(tableId, customerName, customerOrder);

        if (isOrderFor(customerOrder)) {
            return new OrderForCreator(order, getAllOrdersFor(), getNumberOfExpectedCustomersFor()).create();
        } else if (isSame(customerOrder)) {
            order.setContent(getLastCustomerOrderContent());
            return new SameOrderCreator(order).create();
        }
        return order;
    }

    private List<Order> getAllOrdersFor() {
        List<Order> allOrdersFor = new ArrayList<Order>();
        for (Order order : orders)
            if (order instanceof OrderFor)
                allOrdersFor.add(order);
        return allOrdersFor;
    }

    private boolean isOrderFor(String customerOrder) {
        String[] orderContentDetails = customerOrder.split(" ");
        return orderContentDetails.length == 3 && orderContentDetails[1].equals("for");
    }

    private String getLastCustomerOrderContent() {
       return orders.get(orders.size() - 1).content;
    }

    private int getNumberOfExpectedCustomersFor() {
        return Integer.valueOf(customerOrder.split(" ")[2]);
    }

    private boolean isSame(String customerOrder) {
        return customerOrder.equals("Same");
    }

}
