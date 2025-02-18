package org.example;

public class InsuredDrug extends Drug {

    public InsuredDrug(int price, String name, String type) {
        super(price, name, type);
    }

    /*public void InsuredDrug(int price, String name, String type) {
        super.setBasePrice(price);
        super.setName(name);
        super.setType(type);
    }*/

    @Override
    public double getBasePrice() {
        return (super.getBasePrice() * (7.0/10));
    }
}
