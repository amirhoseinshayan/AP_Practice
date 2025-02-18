package org.example;

import java.security.DigestInputStream;

public class DigitalWalletPayment extends Payment {
    private String email;
    private String walletName;

    public String getEmail(){
        return email;
    }
    public String getWalletName(){
        return walletName;
    }

    public DigitalWalletPayment(Account sourceAccount , Account destinitionAccount ,String walletName ,String email ,int amount  ){
        super.setAmount(amount);
        this.walletName = walletName;
        this.email =email;
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
            String result = "payment of amount " + amount + " processed via " + getWalletName() + " for " + getEmail();
            return result;
        }

    }


}
