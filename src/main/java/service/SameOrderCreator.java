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
        Order order = new NormalOrder(tableId, customerName, orderContent);
        order.type = "SameOrder";
        return order;
    }
}
