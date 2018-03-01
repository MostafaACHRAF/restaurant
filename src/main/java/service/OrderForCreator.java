package service;

import java.util.ArrayList;
import java.util.List;

class OrderForCreator extends AbstractOrderCreator {

    private int nbrOfExpectedCustomers;
    private final List<OrderFor> allOrdersFor = new ArrayList<>();

    OrderForCreator(AbstractOrder abstractOrder, List<OrderFor> allOrdersFor, int nbrOfExpectedCustomers) {
        super(abstractOrder.tableId, abstractOrder.customerName, abstractOrder.content);
        this.allOrdersFor.addAll(allOrdersFor);
        this.nbrOfExpectedCustomers = nbrOfExpectedCustomers;
    }

    OrderFor create() {
        if (isExist(orderContent)) {
            OrderFor previousOrderFor = getOrderFor(orderContent);
            if (previousOrderFor != null) {
                previousOrderFor.decrementNbrOfExpectedCustomers();
                this.nbrOfExpectedCustomers = previousOrderFor.getNbrOfExpectedCustomers();
            }
        }
        System.out.println("decrement one........ => " + customerName + " * " + nbrOfExpectedCustomers);
        return new OrderFor(new NormalOrder(tableId, customerName, orderContent), nbrOfExpectedCustomers);
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
