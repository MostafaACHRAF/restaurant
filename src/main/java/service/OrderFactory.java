package service;

import AppConfiguration.WaiterConfig;

import java.util.ArrayList;
import java.util.List;

class OrderFactory {

    private String customerName;
    private String customerOrder;
    private List<AbstractOrder> orders = new ArrayList<>();

    OrderFactory(List<AbstractOrder> orders) {
        this.orders.addAll(orders);
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
        String[] extractedData = customerSays.split(WaiterConfig.CUSTOMER_ORDER_SEPARATOR);
        this.customerName = extractedData[WaiterConfig.CUSTOMER_NAME_INDEX];
        this.customerOrder = extractedData[WaiterConfig.ORDER_CONTENT_INDEX];
    }

    private boolean isOrderFor(String customerOrder) {
        String[] orderContentDetails = customerOrder.split(WaiterConfig.ORDER_FOR_SEPARATOR);
        return  hasLengthOfOrderFor(orderContentDetails) && hasKeyWordFOR(orderContentDetails);
    }

    private boolean hasLengthOfOrderFor(String[] orderContentDetails) {
        return orderContentDetails.length == WaiterConfig.ORDER_FOR_PARTS_COUNT;
    }

    private boolean hasKeyWordFOR(String[] orderContentDetails) {
        return orderContentDetails[WaiterConfig.ORDER_FOR_KEY_WORD_INDEX].equals(WaiterConfig.ORDER_FOR_KEY_WORD);
    }

    private List<OrderFor> getAllOrdersFor() {
        List<OrderFor> allOrdersFor = new ArrayList<>();
        for (AbstractOrder order : orders) {
            if (order instanceof OrderFor) {
                allOrdersFor.add((OrderFor) order);
            }
        }
        return allOrdersFor;
    }

    private int getNumberOfExpectedCustomersFor() {
        return Integer.valueOf(customerOrder.split(WaiterConfig.ORDER_FOR_SEPARATOR)[WaiterConfig.ORDER_FOR_EXPECTED_CUSTOMERS_INDEX]);
    }

    private boolean isSame(String customerOrder) {
        return customerOrder.equals(WaiterConfig.SAME_ORDER);
    }

    private String getLastCustomerOrderContent() {
       return orders.get(orders.size() - 1).content;
    }

}
