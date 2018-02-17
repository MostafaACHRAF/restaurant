package service;

import java.util.*;

class ServiceMan {

    private final List<CustomerOrder> customersOrders = new ArrayList<CustomerOrder>();
    private final HashMap<String, Integer> oneOrderForRegister = new HashMap<String, Integer>();

    void noteWhatCustomerSays(int tableId, String customerOrderData) {
        String[] extractedData = customerOrderData.split(": ");
        String customerName = extractedData[0];
        String orderContent = extractedData[1];

        if (isSame(orderContent)) {
            if (customersOrders.isEmpty())
                throw new IllegalSameOrder();
            orderContent = getLastCustomerOrderContent();
            customersOrders.add(new CustomerOrder(tableId, customerName, orderContent));
        } else if (isOneOrderForNCustomer(orderContent)) {
            String order = getOrderContentFrom(orderContent);
            int nbrOfCustomers = getNumberOfExpectedCustomersFor(orderContent);
            oneOrderForRegister.put(order, nbrOfCustomers);

            if (!oneOrderForRegister.isEmpty()) {
                if (oneOrderForRegister.containsKey(orderContent)) {
                    int nbrCustomers = oneOrderForRegister.get(orderContent);
                    --nbrCustomers;
                    oneOrderForRegister.put(orderContent, nbrCustomers);
                }
            }
        } else
            throw new RuntimeException("hmmmm I don't understand what you say !!");
    }

    String createNewOrder(int tableId) {
        Table table = new TablesFactory().get(tableId);
        for (CustomerOrder customerOrder : customersOrders) {
            if (tableId == customerOrder.getTableId())
                table.addNewCustomerOrder(customerOrder);
        }
        return table.getOrder();
    }

    private boolean isSame(String orderContent) {
        return orderContent.equals("Same");
    }

    private String getLastCustomerOrderContent() {
        int lastCustomerOrderIndex = customersOrders.size() - 1;
        CustomerOrder lastCustomerOrder = customersOrders.get(lastCustomerOrderIndex);
        return lastCustomerOrder.getOrderContent();
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
