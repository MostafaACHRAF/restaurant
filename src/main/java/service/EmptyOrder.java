package service;

import AppConfiguration.WaiterConfig;

class EmptyOrder extends AbstractOrder {
    EmptyOrder(int tableId) {
        super(tableId, WaiterConfig.emptyCustomerName, WaiterConfig.emptyOrderContent);
    }
}
