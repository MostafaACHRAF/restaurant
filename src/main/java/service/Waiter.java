package service;

import java.util.*;

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
                throw new IllegalSameOrder();
            customerOrder = getLastCustomerOrderContent();
            orders.add(new Order(tableId, customerName, customerOrder));
        } else if (isOneOrderForNCustomer(customerOrder)) {
            String order = getOrderContentFrom(customerOrder);
            int nbrOfCustomers = getNumberOfExpectedCustomersFor(customerOrder);
            oneOrderForRegister.put(order, nbrOfCustomers);

            if (!oneOrderForRegister.isEmpty()) {
                if (oneOrderForRegister.containsKey(customerOrder)) {
                    int nbrCustomers = oneOrderForRegister.get(customerOrder);
                    --nbrCustomers;
                    oneOrderForRegister.put(customerOrder, nbrCustomers);
                }
            }
        } else
            throw new RuntimeException("hmmmm I don't understand what you say !!");
        orders.add(new Order(tableId, customerName, customerOrder));
    }

    String createNewOrderFor(int tableId) {
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
