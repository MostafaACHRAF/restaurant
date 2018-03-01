package service;

public abstract class AbstractOrder {
    int tableId;
    String customerName;
    String content;
    String type;

    AbstractOrder(int tableId, String customer, String content) {
        this.tableId = tableId;
        this.customerName = customer;
        this.content = content;
    }

    @Override
    public String toString() {
        return content;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof AbstractOrder) {
            if (((AbstractOrder) obj).tableId == this.tableId) {
                if (((AbstractOrder) obj).customerName.equals(this.customerName)) {
                    return ((AbstractOrder) obj).content.equals(content);
                }
            }
        }
        return false;
    }
}
