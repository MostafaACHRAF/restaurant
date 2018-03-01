package service;

class NormalOrder extends AbstractOrder {

    NormalOrder(){}

    NormalOrder(int tableId, String customer, String content) {
        super(tableId, customer, content);
        this.type = "NormalOrder";
    }
}
