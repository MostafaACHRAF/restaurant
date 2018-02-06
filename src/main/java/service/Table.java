package service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Table {
    private final int placesCount;
    private List<CustomerOrder> customersOrders;

    Table(int placesCount) {
        this.placesCount = placesCount;
        this.customersOrders = new ArrayList<CustomerOrder>();
    }

    public String createNewOrder() {
        return displayOrdersDescription();
    }

    private String displayOrdersDescription() {
        StringBuilder ordersDescDisplay = new StringBuilder();
        int index = 0;
        for (String orderDesc: allOrdersDescription) {
            ordersDescDisplay.append(orderDesc);
            appendComma(index, ordersDescDisplay);
            index++;
        }
        return String.valueOf(ordersDescDisplay);
    }



    private void appendComma(int index, StringBuilder ordersDisplay) {
        if (index != allOrdersDescription.size() - 1)
            ordersDisplay.append(", ");
    }

    public void addNewCustomerOrder(String customerName, String customerOrder) {
        Order order = new Order(customerOrder);
        Customer customer = new Customer(customerName);
        customersOrders.add(new CustomerOrder(customer, order));
    }
}
