package service;

class SameOrderCreator extends AbstractOrderCreator {

    SameOrderCreator(AbstractOrder abstractOrder) {
        super(abstractOrder.tableId, abstractOrder.customerName, abstractOrder.content);
    }

    AbstractOrder create() {
        if (orderContent == null) {
            System.out.println("Error no previous orders");
            orderContent = "null";
        }
        AbstractOrder abstractOrder = new NormalOrder(tableId, customerName, orderContent);
        abstractOrder.type = "SameOrder";
        return abstractOrder;
    }
}
