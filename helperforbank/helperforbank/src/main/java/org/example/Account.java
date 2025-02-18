package org.example;

import java.util.ArrayList;
import java.util.List;

public class Account {
    private String name;
    private int inventory;
    List<Account> arrOfAccount = new ArrayList<>();


    public String getName() {
        return name;
    }
    public int getInventory(){
        return inventory;
    }
    public void setInventory(int newInventory){
        this.inventory = newInventory;
    }
    public Account(String name , int inventory){
        this.name = name;
        this.inventory = inventory;

    }
    public String logBalance() {
        String result = "Account: " + getName() + "," + " Balance: " + getInventory();
        return result;
    }

    public void withdrawalFromTheAccount(int reduce , String name){
        for (Account item : arrOfAccount){
            if(item.name.equals(name)){
                if (item.inventory >= reduce){
                    item.inventory -= reduce;
                }else {
                    System.out.println();
                }
            }
        }
    }

    public void DepositToTheAccount(int deposit ,String name){
        for (Account item : arrOfAccount) {
            if (item.name.equals(name)) {
                item.inventory += deposit;

            }
        }
    }

}
