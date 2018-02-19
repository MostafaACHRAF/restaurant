package service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class Table {
    private final int customersNumber;
    private final List<String> ordersContent = new ArrayList<String>();
    Table(int customersNumber) {
        this.customersNumber = customersNumber;
    }

//    private String displayOrdersDescription() {
//        StringBuilder ordersDescriptionDisplay = new StringBuilder();
//        int index = 0;
//        for (CustomerOrder customerOrder : customersOrders) {
//            ordersDescriptionDisplay.append(customerOrder);
//            appendComma(index, ordersDescriptionDisplay);
//            index++;
//        }
//        return String.valueOf(ordersDescriptionDisplay);
//    }

//    private void appendComma(int index, StringBuilder ordersDisplay) {
//        if (index != customersOrders.size() - 1)
//            ordersDisplay.append(", ");
//    }


    public int getCustomersNumber() {
        return customersNumber;
    }

    void addNewOrderContent(String orderContent) {
        ordersContent.add(orderContent);
    }

    String getOrdersContent() {
        StringBuilder builder = new StringBuilder();
        int index = 0;
        for (String content : ordersContent) {
            builder.append(content);
            if (index != ordersContent.size() - 1)
                builder.append(", ");
        }
        return String.valueOf(builder);
    }

//    boolean isCustomersOrdersEmpty() {
//        return customersOrders.isEmpty();
//    }
//
//    String getLastOrderDescription() {
//        //return customersOrders.get(customersOrders.size() - 1);
//        return "";
//    }
}
