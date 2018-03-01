package service;

abstract class AbstractOrderCreator {

    int tableId;
    String customerName;
    String orderContent;

    AbstractOrderCreator(int tableId, String customerName, String orderContent) {
        this.tableId = tableId;
        this.customerName = customerName;
        this.orderContent = orderContent;
    }

    abstract AbstractOrder create();

}
