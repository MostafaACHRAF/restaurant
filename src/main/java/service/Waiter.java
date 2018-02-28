package service;

import java.util.ArrayList;
import java.util.List;

class Waiter {

    private List<Order> orders = new ArrayList<>();

    void noteWhatCustomerSays(int tableId, String customerSay) {
        OrderFactory orderFactory = new OrderFactory(orders);
        Order customerOrder = orderFactory.create(tableId, customerSay);
        addOrderToOrdersList(tableId, customerOrder);
    }

    private void addOrderToOrdersList(int tableId, Order customerOrder) {
        if(!isOrderExist(tableId, customerOrder)) {
            if (isThisCustomerHasOrderedBefore(tableId, customerOrder.customerName)) {
                replaceTheOldOrderByTheNewOne(tableId, customerOrder);
            } else {
                orders.add(customerOrder);
            }
        }
    }

    private boolean isOrderExist(int tableId, Order customerOrder) {
        for (Order order : getTableOrders(tableId))
            if (order.equals(customerOrder))
                return true;
        return false;
    }

    private List<Order> getTableOrders(int tableId) {
        List<Order> tableOrders = new ArrayList<>();
        for (Order order : orders)
            if (order.tableId == tableId)
                tableOrders.add(order);
        return tableOrders;
    }

    private boolean isThisCustomerHasOrderedBefore(int tableId, final String customerName) {
        for (Order order : getTableOrders(tableId))
            if (order.customerName.equals(customerName))
                return true;
        return false;
    }

    private void replaceTheOldOrderByTheNewOne(int tableId, Order newCustomerOrder) {
        Order oldCustomerOrder = getCustomerOrder(tableId, newCustomerOrder.customerName);
        orders.remove(oldCustomerOrder);
        if (newCustomerOrder instanceof OrderFor) {
            ((OrderFor) newCustomerOrder).decrementNbrOfExpectedCustomers();
        }
        orders.add(newCustomerOrder);
    }

    private Order getCustomerOrder(int tableId, String customerName) {
        for (Order order : getTableOrders(tableId)) {
            if (order.customerName.equals(customerName)) {
                return order;
            }
        }
        return new EmptyOrder();
    }

    String createNewOrderFor(Table table) {
        StringBuilder builder = new StringBuilder();
        if (getNbrOfMissingOrders(table) > 0)
            return "MISSING " + getNbrOfMissingOrders(table);
        List<Order> tableOrders = new ArrayList<>();
        tableOrders.addAll(getTableOrders(table.getId()));
        int index = 0;
        for (Order order : tableOrders) {
            if (order instanceof OrderFor)
                System.out.println("ok" + ((OrderFor) order).getNbrOfExpectedCustomers());
            if (order instanceof OrderFor)
                if (((OrderFor) order).getNbrOfExpectedCustomers() != 0)
                    return "MISSING " + ((OrderFor) order).getNbrOfExpectedCustomers() + " for " + order.content;
            builder.append(order);
            if (index != tableOrders.size() - 1)
                builder.append(", ");
            index++;
        }
        return String.valueOf(builder);
    }

    private int getNbrOfMissingOrders(Table table) {
        return table.getCustomersNumber() -  nbrOfOrdersIn(table.getId());
    }

    private int nbrOfOrdersIn(int tableId) {
        return getTableOrders(tableId).size();
    }

}
