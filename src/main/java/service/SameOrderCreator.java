package service;

class SameOrderCreator extends OrderCreator {

    SameOrderCreator(Order order) {
        super(order.tableId, order.customerName, order.content);
    }

    Order create() {
        if (orderContent == null) {
            System.out.println("Error no previous orders");
            orderContent = "null";
        }
        return new NormalOrder(tableId, customerName, orderContent);
    }
}
