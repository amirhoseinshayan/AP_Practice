package org.example;

public class Payment {
    public int amount ;
    public Account sourceAccount;
    public Account destinitionAccount;


    public int getAmount(){
        return amount;
    }
    public Account getSourceAccount(){
        return sourceAccount;
    }
    public Account getDestinitionAccount (){
        return destinitionAccount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setDestinitionAccount(Account destinitionAccount) {
        this.destinitionAccount = destinitionAccount;
    }

    public void setSourceAccount(Account sourceAccount) {
        this.sourceAccount = sourceAccount;
    }

    public String  ProcessPayment(){
        return null;
    }
}
