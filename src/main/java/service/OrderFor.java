package service;

class OrderFor extends AbstractOrder {

    private int nbrOfExpectedCustomers;

    OrderFor(AbstractOrder abstractOrder, int nbrOfExpectedCustomers) {
        super(abstractOrder.tableId, abstractOrder.customerName, abstractOrder.content);
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
