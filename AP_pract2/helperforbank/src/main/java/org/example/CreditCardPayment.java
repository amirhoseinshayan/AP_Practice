package org.example;

public class CreditCardPayment extends Payment {
    public CreditCardPayment(Account sourceAccount , Account destinitionAccount , int amount ){
        super.setSourceAccount(sourceAccount);
        super.setDestinitionAccount(destinitionAccount);
        super.setAmount(amount);
    }


    public String ProcessPayment(){
        if (amount > sourceAccount.getInventory()){
            String result = "Insufficient funds in account: " + sourceAccount.getName();
            return result;
        }else{
            sourceAccount.setInventory((sourceAccount.getInventory() - amount));
            destinitionAccount.setInventory((destinitionAccount.getInventory() + amount));
            String result = "Credit card payment processed: " + amount ;
            return result;
        }
    }
}
