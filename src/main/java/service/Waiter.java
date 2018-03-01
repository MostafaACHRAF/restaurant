package service;

import java.util.ArrayList;
import java.util.List;

class Waiter {

    private List<Order> orders = new ArrayList<>();

    void noteWhatCustomerSays(int tableId, String customerSay) {
        OrderFactory orderFactory = new OrderFactory(orders);
        Order customerOrder = orderFactory.create(tableId, customerSay);
        System.out.println("---->>> " + customerOrder.customerName + " - " + customerOrder.content);
        addOrderToOrdersList(tableId, customerOrder);
    }

    String createNewOrderFor(Table table) {
        StringBuilder builder = new StringBuilder();
        if (getNbrOfMissingOrders(table) > 0) {
            return "MISSING " + getNbrOfMissingOrders(table);
        }
        List<Order> tableOrders = new ArrayList<>();
        tableOrders.addAll(getTableOrders(table.getId()));
        int index = 0;
        for (Order order : tableOrders) {
            if (order instanceof OrderFor) {
                if (((OrderFor) order).getNbrOfExpectedCustomers() != 0) {
                    return "MISSING " + ((OrderFor) order).getNbrOfExpectedCustomers() + " for " + order.content;
                }
            }
            System.out.println("table has = " + tableOrders.size() + " - (" + order.customerName  + " | " + order.content);
            builder.append(order);
            if (index != tableOrders.size() - 1) {
                builder.append(", ");
            }
            index++;
        }
        return String.valueOf(builder);
    }

    private List<Order> getTableOrders(int tableId) {
        List<Order> tableOrders = new ArrayList<Order>();
        for (Order order : orders)
            if (order.tableId == tableId)
                tableOrders.add(order);
        return tableOrders;
    }

    private void addOrderToOrdersList(int tableId, Order customerOrder) {
        if(!isOrderExist(tableId, customerOrder)) {
            if (isThisCustomerHasOrderedBefore(tableId, customerOrder.customerName)) {
                replaceTheOldOrderByTheNewOne(tableId, customerOrder);
            } else {
                orders.add(customerOrder);
            }
            System.out.println(">>> " + customerOrder.customerName + " - " + customerOrder.content);
        }
    }

    private boolean isThisCustomerHasOrderedBefore(int tableId, final String customerName) {
        for (Order order : getTableOrders(tableId))
            if (order.customerName.equals(customerName))
                return true;
        return false;
    }

    private void replaceTheOldOrderByTheNewOne(int tableId, Order customerOrder) {
        Order oldCustomerOrder = getCustomerOrder(tableId, customerOrder.customerName);
        int oldCustomerOrderIndex = orders.indexOf(oldCustomerOrder);
        orders.remove(oldCustomerOrder);
        if (customerOrder instanceof OrderFor) {
            if (((OrderFor) customerOrder).getNbrOfExpectedCustomers() > 0) {
                ((OrderFor) customerOrder).decrementNbrOfExpectedCustomers();
            }
        }
        System.out.println(oldCustomerOrderIndex + "+++ " + customerOrder.customerName + " * " + customerOrder.content);
        orders.add(oldCustomerOrderIndex, customerOrder);
    }

    private Order getCustomerOrder(int tableId, String customerName) {
        for (Order order : getTableOrders(tableId)) {
            if (order.customerName.equals(customerName)) {
                return order;
            }
        }
        return new EmptyOrder();
    }

    private int getNbrOfMissingOrders(Table table) {
        return table.getCustomersNumber() -  nbrOfOrdersIn(table.getId());
    }

    private int nbrOfOrdersIn(int tableId) {
        return getTableOrders(tableId).size();
    }

    private boolean isOrderExist(int tableId, Order customerOrder) {
        for (Order order : getTableOrders(tableId))
            if (order.equals(customerOrder))
                return true;
        return false;
    }

}
