package service;

class OrderFor extends AbstractOrder {

    private int nbrOfExpectedCustomers;

    OrderFor(AbstractOrder abstractOrder, int nbrOfExpectedCustomers) {
        super(abstractOrder.tableId, abstractOrder.customerName, abstractOrder.content);
        this.nbrOfExpectedCustomers = nbrOfExpectedCustomers;
        logger.info(">>> Create new Order for [" + this.getClass() + "]");
    }

    int getNbrOfExpectedCustomers() {
        logger.info(">>> Get the number of expected customers in : " + this.getClass());
        return nbrOfExpectedCustomers;
    }

    void decrementNbrOfExpectedCustomers() {
        this.nbrOfExpectedCustomers -= 1;
        logger.info(">>> Decrement the number of expected customers for  : " + this.getClass() + " to : " + nbrOfExpectedCustomers);
    }
}
