package org.example;

public class PrescriptionItem extends Drug{
    public PrescriptionItem(int price, String name, String type) {
        super(price, name, type);
    }

    @Override
    public double getBasePrice() {
        return super.getBasePrice();
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public String getType() {
        return super.getType();
    }

    @Override
    public void setBasePrice(int basePrice) {
        super.setBasePrice(basePrice);
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }

    @Override
    public void setType(String type) {
        super.setType(type);
    }
}
