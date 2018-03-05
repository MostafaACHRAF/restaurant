package service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractOrder {
    int tableId;
    String customerName;
    String content;
    Logger logger = LoggerFactory.getLogger(AbstractOrder.class);

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
