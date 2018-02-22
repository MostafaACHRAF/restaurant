package service;

import java.util.ArrayList;
import java.util.List;

class OrderForCreator extends OrderCreator {

    private int nbrOfExpectedCustomers;
    private final List<Order> allOrdersFor = new ArrayList<Order>();

    OrderForCreator(Order order, List<Order> allOrdersFor, int nbrOfExpectedCustomers) {
        super(order.tableId, order.customerName, order.content);
        this.allOrdersFor.addAll(allOrdersFor);
        this.nbrOfExpectedCustomers = nbrOfExpectedCustomers;
    }

    Order create() {
        OrderFor orderFor;
        if (isExist(orderContent)) {
            orderFor = (OrderFor) getOrderFor(orderContent);
            orderFor.decrementNbrOfExpectedCustomers();
        } else
            orderFor = new OrderFor(new NormalOrder(tableId, customerName, orderContent), nbrOfExpectedCustomers);
        return orderFor;
    }

    private boolean isExist(String orderContent) {
        return getOrderFor(orderContent) != null;
    }

    private Order getOrderFor(String orderContent) {
        Order orderFor = null;
        for (Order order : allOrdersFor)
            if (order.content.equals(orderContent)) {
                orderFor = order;
                break;
            }
        return orderFor;
    }
}
