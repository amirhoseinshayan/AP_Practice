package org.example;

public abstract class Drug
{

    private double basePrice;
    private String name;
    private String type ;

    public Drug(int price, String name , String type ) {
        this.basePrice = price;
        this.name = name;
        this.type = type;
    }

    public double getBasePrice() {
            return basePrice;
    }

    public String getType(){
        return type;
    }

    public void setType(String type){
        this.type = type;
    }

    public void setBasePrice(int basePrice) {
        this.basePrice = basePrice;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

}

