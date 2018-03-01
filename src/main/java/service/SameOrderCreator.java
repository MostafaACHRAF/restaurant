package service;

class SameOrderCreator extends AbstractOrderCreator {

    SameOrderCreator(AbstractOrder order) {
        super(order);
    }

    AbstractOrder create() {
        if (order.content == null) {
            logger.info("Error !!! No previous order.");
            return new EmptyOrder(order.tableId);
        }
        return new NormalOrder(order.tableId, order.customerName, order.content);
    }
}
