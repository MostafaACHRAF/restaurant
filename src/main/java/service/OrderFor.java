package service;

class OrderFor extends Order {

    private int nbrOfExpectedCustomers;

    OrderFor(Order order, int nbrOfExpectedCustomers) {
        super(order.tableId, order.customerName, order.content);
        this.nbrOfExpectedCustomers = nbrOfExpectedCustomers;
    }

    void decrementNbrOfExpectedCustomers() {
        this.nbrOfExpectedCustomers -= 1;
    }
}
