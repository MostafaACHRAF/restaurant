package service;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {

    private final List<Table> tables = new ArrayList<Table>();
    private final Waiter waiter = new Waiter();

    public int initTable(int nbrOfCustomers) {
        int id = tables.size();
        tables.add(new Table(id, nbrOfCustomers));
        return tables.size() - 1;
    }

    public void customerSays(int tableId, String customerSay) {
        waiter.noteWhatCustomerSays(tableId, customerSay);
    }

    private Table getTable(int tableId) {
        return tables.get(tableId);
    }

    public String createOrder(int tableId) {
        Table table = getTable(tableId);
        return waiter.createNewOrderFor(table);
    }

}
