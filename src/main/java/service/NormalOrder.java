package service;

class NormalOrder extends Order {

    NormalOrder(){}

    NormalOrder(int tableId, String customer, String content) {
        super(tableId, customer, content);
        this.type = "NormalOrder";
    }
}
