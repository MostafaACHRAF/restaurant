package service;

import java.util.ArrayList;
import java.util.List;

class OrderForCreator extends AbstractOrderCreator {

    private static int nbrOfExpectedCustomers;
    private final List<OrderFor> allOrdersFor = new ArrayList<>();

    OrderForCreator(AbstractOrder order, List<OrderFor> allOrdersFor, int expectedCustomersCount) {
        super(order);
        this.allOrdersFor.addAll(allOrdersFor);
        nbrOfExpectedCustomers = expectedCustomersCount;
    }

    OrderFor create() {
        if (isExist(order.content)) {
            OrderFor previousOrderFor = getOrderFor(order.content);
            if (previousOrderFor != null) {
                //previousOrderFor.decrementNbrOfExpectedCustomers();
                //nbrOfExpectedCustomers = previousOrderFor.getNbrOfExpectedCustomers();
            }
        }
        return new OrderFor(new NormalOrder(order.tableId, order.customerName, order.content), nbrOfExpectedCustomers);
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
