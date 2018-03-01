package service;

import java.util.ArrayList;
import java.util.List;

class OrderFactory {

    private String customerName;
    private String customerOrder;
    private List<AbstractOrder> abstractOrders = new ArrayList<>();

    OrderFactory(List<AbstractOrder> abstractOrders) {
        this.abstractOrders.addAll(abstractOrders);
    }

    AbstractOrder create(int tableId, String customerSays) {
        extractDataFrom(customerSays);
        AbstractOrder abstractOrder = new NormalOrder(tableId, customerName, customerOrder);

        if (isOrderFor(customerOrder)) {
            return new OrderForCreator(abstractOrder, getAllOrdersFor(), getNumberOfExpectedCustomersFor()).create();
        } else if (isSame(customerOrder)) {
            abstractOrder.content = getLastCustomerOrderContent();
            return new SameOrderCreator(abstractOrder).create();
        }
        return abstractOrder;
    }

    private void extractDataFrom(String customerSays) {
        String[] extractedData = customerSays.split(": ");
        this.customerName = extractedData[0];
        this.customerOrder = extractedData[1];
    }

    private List<OrderFor> getAllOrdersFor() {
        List<OrderFor> allOrdersFor = new ArrayList<>();
        for (AbstractOrder abstractOrder : abstractOrders)
            if (abstractOrder instanceof OrderFor)
                allOrdersFor.add((OrderFor) abstractOrder);
        return allOrdersFor;
    }

    private boolean isOrderFor(String customerOrder) {
        String[] orderContentDetails = customerOrder.split(" ");
        return  orderContentDetails.length == 3 && orderContentDetails[1].equals("for");
    }

    private String getLastCustomerOrderContent() {
       return abstractOrders.get(abstractOrders.size() - 1).content;
    }

    private int getNumberOfExpectedCustomersFor() {
        return Integer.valueOf(customerOrder.split(" ")[2]);
    }

    private boolean isSame(String customerOrder) {
        return customerOrder.equals("Same");
    }

}
