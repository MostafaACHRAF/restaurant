package service;

public class CustomerOrder {
    private Customer customer;
    private Order order;

    public CustomerOrder(Customer customer, Order order) {
        this.customer =  customer;
        this.order = order;
    }
}
