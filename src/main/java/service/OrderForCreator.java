package service;

import java.util.ArrayList;
import java.util.List;

class OrderForCreator extends OrderCreator {

    private int nbrOfExpectedCustomers;
    private final List<OrderFor> allOrdersFor = new ArrayList<>();

    OrderForCreator(Order order, List<OrderFor> allOrdersFor, int nbrOfExpectedCustomers) {
        super(order.tableId, order.customerName, order.content);
        this.allOrdersFor.addAll(allOrdersFor);
        this.nbrOfExpectedCustomers = nbrOfExpectedCustomers;
    }

    OrderFor create() {
        OrderFor orderFor;
        if (isExist(orderContent)) {
            orderFor = getOrderFor(orderContent);
            orderFor.decrementNbrOfExpectedCustomers();
        } else {
            orderFor = new OrderFor(new NormalOrder(tableId, customerName, orderContent), nbrOfExpectedCustomers);
        }
        return orderFor;
    }

    private boolean isExist(String orderContent) {
        return getOrderFor(orderContent) != null;
    }

    private OrderFor getOrderFor(String orderContent) {
        for (OrderFor orderFor : allOrdersFor)
            if (orderFor.content.equals(orderContent)) {
                return orderFor;
            }
        return null;
    }
}
