package service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Restaurant {
    private List<Table> tables;

    public Restaurant() {
        tables = new ArrayList<Table>();
    }

    public int initTable(int i) {
        tables.add(new Table(i));
        return tables.size() - 1;
    }

    public void customerSays(int tableId, String s) {
        String[] customerSayData = s.split(": ");
        String customerOrder = customerSayData[0];
        String customerName = extractCustomerOrder(customerSayData[1]);
        tables.get(tableId).addNewCustomerOrder(customerName, customerOrder);
    }

    public String createOrder(int tableId) {
        return tables.get(tableId).createNewOrder();
    }

    private String extractCustomerOrder(String orderDescription) {
        if (isSameOrderAndNotTheFirst(orderDescription)) {
            orderDescription = getPreviousOrderDescription();
        }
        return orderDescription;
    }

    private boolean isSameOrderAndNotTheFirst(String orderDescription) {
        return isSameOrder(orderDescription) && !requestedOrders.isEmpty();
    }

    private boolean isSameOrder(String orderDescription) {
        return orderDescription.equals("Same");
    }

    private String getPreviousOrderDescription() {
        return (String) requestedOrders.values().toArray()[requestedOrders.size() - 1];
    }


}
