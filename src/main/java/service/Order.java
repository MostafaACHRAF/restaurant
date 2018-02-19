package service;

public abstract class Order {
    private int tableId;
    private String customer;
    private String content;

    Order(int tableId, String customer, String content) {
        this.tableId = tableId;
        this.customer = customer;
        this.content = content;
    }

    int getTableId() {
        return tableId;
    }

    String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return tableId + " - " + customer + " - " + content;
    }
}
