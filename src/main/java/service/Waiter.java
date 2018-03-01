package service;

import java.util.ArrayList;
import java.util.List;

class Waiter {

    private List<AbstractOrder> abstractOrders = new ArrayList<>();

    void noteWhatCustomerSays(int tableId, String customerSay) {
        OrderFactory orderFactory = new OrderFactory(abstractOrders);
        AbstractOrder customerAbstractOrder = orderFactory.create(tableId, customerSay);
        System.out.println("---->>> " + customerAbstractOrder.customerName + " - " + customerAbstractOrder.content);
        addOrderToOrdersList(tableId, customerAbstractOrder);
    }

    String createNewOrderFor(Table table) {
        StringBuilder builder = new StringBuilder();
        if (getNbrOfMissingOrders(table) > 0) {
            return "MISSING " + getNbrOfMissingOrders(table);
        }
        List<AbstractOrder> tableAbstractOrders = new ArrayList<>();
        tableAbstractOrders.addAll(getTableOrders(table.getId()));
        int index = 0;
        for (AbstractOrder abstractOrder : tableAbstractOrders) {
            if (abstractOrder instanceof OrderFor) {
                if (((OrderFor) abstractOrder).getNbrOfExpectedCustomers() != 0) {
                    return "MISSING " + ((OrderFor) abstractOrder).getNbrOfExpectedCustomers() + " for " + abstractOrder.content;
                }
            }
            System.out.println("table has = " + tableAbstractOrders.size() + " - (" + abstractOrder.customerName  + " | " + abstractOrder.content);
            builder.append(abstractOrder);
            if (index != tableAbstractOrders.size() - 1) {
                builder.append(", ");
            }
            index++;
        }
        return String.valueOf(builder);
    }

    private List<AbstractOrder> getTableOrders(int tableId) {
        List<AbstractOrder> tableAbstractOrders = new ArrayList<AbstractOrder>();
        for (AbstractOrder abstractOrder : abstractOrders)
            if (abstractOrder.tableId == tableId)
                tableAbstractOrders.add(abstractOrder);
        return tableAbstractOrders;
    }

    private void addOrderToOrdersList(int tableId, AbstractOrder customerAbstractOrder) {
        if(!isOrderExist(tableId, customerAbstractOrder)) {
            if (isThisCustomerHasOrderedBefore(tableId, customerAbstractOrder.customerName)) {
                replaceTheOldOrderByTheNewOne(tableId, customerAbstractOrder);
            } else {
                abstractOrders.add(customerAbstractOrder);
            }
            System.out.println(">>> " + customerAbstractOrder.customerName + " - " + customerAbstractOrder.content);
        }
    }

    private boolean isThisCustomerHasOrderedBefore(int tableId, final String customerName) {
        for (AbstractOrder abstractOrder : getTableOrders(tableId))
            if (abstractOrder.customerName.equals(customerName))
                return true;
        return false;
    }

    private void replaceTheOldOrderByTheNewOne(int tableId, AbstractOrder customerAbstractOrder) {
        AbstractOrder oldCustomerAbstractOrder = getCustomerOrder(tableId, customerAbstractOrder.customerName);
        int oldCustomerOrderIndex = abstractOrders.indexOf(oldCustomerAbstractOrder);
        abstractOrders.remove(oldCustomerAbstractOrder);
        if (customerAbstractOrder instanceof OrderFor) {
            if (((OrderFor) customerAbstractOrder).getNbrOfExpectedCustomers() > 0) {
                ((OrderFor) customerAbstractOrder).decrementNbrOfExpectedCustomers();
            }
        }
        System.out.println(oldCustomerOrderIndex + "+++ " + customerAbstractOrder.customerName + " * " + customerAbstractOrder.content);
        abstractOrders.add(oldCustomerOrderIndex, customerAbstractOrder);
    }

    private AbstractOrder getCustomerOrder(int tableId, String customerName) {
        for (AbstractOrder abstractOrder : getTableOrders(tableId)) {
            if (abstractOrder.customerName.equals(customerName)) {
                return abstractOrder;
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

    private boolean isOrderExist(int tableId, AbstractOrder customerAbstractOrder) {
        for (AbstractOrder abstractOrder : getTableOrders(tableId))
            if (abstractOrder.equals(customerAbstractOrder))
                return true;
        return false;
    }

}
