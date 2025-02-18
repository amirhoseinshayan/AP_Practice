package org.example;

public class CashPayment extends Payment {
    public CashPayment(Account sourceAccount , Account destinitionAccount , int amount){
        super.setAmount(amount);
        super.setSourceAccount(sourceAccount);
        super.setDestinitionAccount(destinitionAccount);
    }
    public String ProcessPayment(){
        if (amount > sourceAccount.getInventory()){
            String result = "Insufficient funds in account: " + sourceAccount.getName();
            return result;
        }else{
            sourceAccount.setInventory((sourceAccount.getInventory() - amount));
            destinitionAccount.setInventory((destinitionAccount.getInventory() + amount));
            String result = "Cash payment of " + amount + " completed" ;
            return result;
        }

    }


}
