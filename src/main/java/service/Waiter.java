package service;

import java.util.ArrayList;
import java.util.List;

class Waiter {

    private final List<Order> orders = new ArrayList<Order>();

    void noteWhatCustomerSays(int tableId, String customerSay) {
        OrderFactory orderFactory = new OrderFactory(orders);
        Order customerOrder = orderFactory.create(tableId, customerSay);
        addOrderToOrdersList(customerOrder);
    }

//    private boolean isSameOrder(Order customerOrder) {
//        return customerOrder instanceof SameOrder;
//    }
//
//    private boolean isOrderForAlreadyExist(Order customerOrder) {
//        return getAllOrdersFor().contains(customerOrder);
//    }
//
//    private Order getLastCustomerOrder() {
//        return orders.get(orders.size() - 1);
//    }

    private List<Order> getAllOrdersFor() {
        List<Order> allOrdersFor = new ArrayList<Order>();
        for (Order order : orders) {
            if (isOrderFor(order))
                allOrdersFor.add(order);
        }
        return allOrdersFor;
    }

    private boolean isOrderFor(Order customerOrder) {
        return customerOrder instanceof OrderFor;
    }

    String createNewOrderFor(int tableId) {
        StringBuilder builder = new StringBuilder();
        //List<Order> tableOrders = getTableOrders(tableId);
        int index = 0;
        for (Order order : orders) {
            builder.append(order);
            if (index != orders.size() - 1)
                builder.append(", ");
            index++;
        }
        return String.valueOf(builder);
    }

    private void addOrderToOrdersList(Order customerOrder) {
        if(!isOrderExist(customerOrder))
            orders.add(customerOrder);
    }

    private boolean isOrderExist(Order customerOrder) {
        for (Order order : orders)
            if (order.equals(customerOrder))
                return true;
        return false;
    }

//    private List<Order> getTableOrders(int tableId) {
//        List<Order> tableOrders = new ArrayList<Order>();
//        for (Order order : orders) {
//            if (order.getTableId() == tableId)
//                tableOrders.add(order);
//        }
//        return tableOrders;
//    }

}
