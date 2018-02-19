package service;

import java.util.*;
import java.util.function.Consumer;

class Waiter {
    private int tableId;
    private final List<Order> orders = new ArrayList<Order>();
    private final HashMap<String, Integer> oneOrderForRegister = new HashMap<String, Integer>();

    void noteWhatCustomerSays(int tableId, String customerSay) {
        String[] extractedData = customerSay.split(": ");
        String customerName = extractedData[0];
        String customerOrder = extractedData[1];

        if (isSame(customerOrder)) {
            if (orders.isEmpty())
                //throw new IllegalSameOrder();
                System.out.println("same has no previous !!");
            customerOrder = getLastCustomerOrderContent();
        } else if (isOneOrderForNCustomer(customerOrder)) {
            String order = getOrderContentFrom(customerOrder);
            int nbrOfCustomers = getNumberOfExpectedCustomersFor(customerOrder);
            if (oneOrderForRegister.containsKey(order)) {
                nbrOfCustomers = oneOrderForRegister.get(order);
                --nbrOfCustomers;
            }
            oneOrderForRegister.put(order, nbrOfCustomers);
        }
        orders.add(new Order(tableId, customerName, customerOrder));

    }

    String createNewOrderFor(int tableId) {

         Set<Map.Entry<String, Integer>> entries = oneOrderForRegister.entrySet();

         for (Map.Entry<String, Integer> entry : entries) {
             if (entry.getValue() > 0)
                 System.out.println("MISSING " + entry.getValue() + " for " + entry.getKey());
         }

        Table table = new TableFactory().create(tableId);
        for (Order customerOrder : orders) {
            if (tableId == customerOrder.getTableId())
                table.addNewOrderContent(customerOrder.getContent());
        }
        return table.getOrdersContent();
    }

    private boolean isSame(String orderContent) {
        return orderContent.equals("Same");
    }

    private String getLastCustomerOrderContent() {
        int lastCustomerOrderIndex = orders.size() - 1;
        Order lastCustomerOrder = orders.get(lastCustomerOrderIndex);
        return lastCustomerOrder.getContent();
    }

    private boolean isOneOrderForNCustomer(String orderContent) {
        String[] orderContentDetails = orderContent.split(" ");
        return orderContentDetails.length == 3 && orderContentDetails[1].equals("for");
    }

    private int getNumberOfExpectedCustomersFor(String orderContent) {
        return Integer.valueOf(orderContent.split(" ")[2]);
    }

    private String getOrderContentFrom(String orderContent) {
        return orderContent.split(" ")[0];
    }


}
