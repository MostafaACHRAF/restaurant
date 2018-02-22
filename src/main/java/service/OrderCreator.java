package service;

abstract class OrderCreator {

    int tableId;
    String customerName;
    String orderContent;

    OrderCreator(int tableId, String customerName, String orderContent) {
        this.tableId = tableId;
        this.customerName = customerName;
        this.orderContent = orderContent;
    }

    abstract Order create();

}
