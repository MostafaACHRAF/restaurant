package service;

public abstract class Order {
    int tableId;
    String customerName;
    String content;

    Order(int tableId, String customer, String content) {
        this.tableId = tableId;
        this.customerName = customer;
        this.content = content;
    }

    @Override
    public String toString() {
        return tableId + " - " + customerName + " - " + content;
    }

    public int getTableId() {
        return tableId;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getContent() {
        return content;
    }

    void setContent(String content) {
        this.content = content;
    }

}
