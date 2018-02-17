package service;

public class Customer {
    private String name;

    Customer(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        return ((Customer) obj).name.equals(this.name);
    }

    @Override
    public String toString() {
        return name;
    }
}
