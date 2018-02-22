package service;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {

    private final List<Table> tables = new ArrayList<Table>();
    private final Waiter waiter = new Waiter();

    public int initTable(int i) {
        tables.add(new Table(i));
        return tables.size() - 1;
    }

    public void customerSays(int tableId, String customerSay) {
        waiter.noteWhatCustomerSays(tableId, customerSay);
    }

    public String createOrder(int tableId) {
        return waiter.createNewOrderFor(tableId);
    }

}
