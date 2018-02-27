package service;

public abstract class Order {
    int tableId;
    String customerName;
    String content;
    String type;

    Order(){}

    Order(int tableId, String customer, String content) {
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
        if (!(obj instanceof Order))
            return false;
        if (((Order) obj).tableId == this.tableId)
            if (((Order) obj).customerName.equals(this.customerName))
                if (((Order) obj).content.equals(content))
                    return true;
        return false;
    }
}
