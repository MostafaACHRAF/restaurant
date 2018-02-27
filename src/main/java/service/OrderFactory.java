package service;

import java.util.ArrayList;
import java.util.List;

class OrderFactory {

    private String customerName;
    private String customerOrder;
    private List<Order> orders = new ArrayList<Order>();

    OrderFactory(List<Order> orders) {
        this.orders.addAll(orders);
    }

    Order create(int tableId, String customerSays) {
        extractDataFrom(customerSays);
        Order order = new NormalOrder(tableId, customerName, customerOrder);

        if (isOrderFor(customerOrder)) {
            OrderFor orderFor = new OrderForCreator(order, getAllOrdersFor(), getNumberOfExpectedCustomersFor()).create();
            System.out.println("--*--not exist before " + orderFor.getNbrOfExpectedCustomers());
            return orderFor;
        } else if (isSame(customerOrder)) {
            order.content = getLastCustomerOrderContent();
            return new SameOrderCreator(order).create();
        }
        return order;
    }

    private void extractDataFrom(String customerSays) {
        String[] extractedData = customerSays.split(": ");
        this.customerName = extractedData[0];
        this.customerOrder = extractedData[1];
    }

    private List<OrderFor> getAllOrdersFor() {
        List<OrderFor> allOrdersFor = new ArrayList<OrderFor>();
        for (Order order : orders)
            if (order instanceof OrderFor)
                allOrdersFor.add((OrderFor) order);
        return allOrdersFor;
    }

    private boolean isOrderFor(String customerOrder) {
        String[] orderContentDetails = customerOrder.split(" ");
        return  orderContentDetails.length == 3 && orderContentDetails[1].equals("for");
    }

    private String getLastCustomerOrderContent() {
       return orders.get(orders.size() - 1).content;
    }

    private int getNumberOfExpectedCustomersFor() {
        return Integer.valueOf(customerOrder.split(" ")[2]);
    }

    private boolean isSame(String customerOrder) {
        return customerOrder.equals("Same");
    }

}
