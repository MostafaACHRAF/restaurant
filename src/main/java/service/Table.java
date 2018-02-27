package service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class Table {
    private int id;
    private final int customersNumber;

    Table(int id, int customersNumber) {
        this.id = id;
        this.customersNumber = customersNumber;
    }

    public int getId() {
        return id;
    }

    public int getCustomersNumber() {
        return customersNumber;
    }

}
