package service;

class OrderFor extends Order {

    private int nbrOfExpectedCustomers;

    OrderFor(Order order, int nbrOfExpectedCustomers) {
        super(order.tableId, order.customerName, order.content);
        this.nbrOfExpectedCustomers = nbrOfExpectedCustomers;
        this.type = "OrderFor";
    }

    int getNbrOfExpectedCustomers() {
        return nbrOfExpectedCustomers;
    }

    void decrementNbrOfExpectedCustomers() {
        System.out.println("one decrementation");
        this.nbrOfExpectedCustomers -= 1;
    }
}
