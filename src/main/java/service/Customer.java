package service;

import java.util.List;

public class Customer {
    private String name;
    private Order order;

    public Customer(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        return ((Customer) obj).name.equals(this.name);
    }
}
