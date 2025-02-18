package org.example;

public class UninsurdDrug extends Drug{

    public UninsurdDrug(int price, String name, String type) {
        super(price, name, type);
    }

    @Override
    public double getBasePrice() {
        return super.getBasePrice();
    }
}
