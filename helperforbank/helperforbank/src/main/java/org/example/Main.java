package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Account account1 = new Account("shayan" , 1000);
        Account account2 = new Account("shiri" , 2000);

        System.out.println(account1.logBalance());
        System.out.println(account2.logBalance());
        Payment[] peyments = new Payment[]{
                new CreditCardPayment(account1 , account2 , 500),
                new DigitalWalletPayment(account2 , account1 , "wallet1" , "email@email.com" , 300),
                new CashPayment(account1, account2, 900)
        };
        for (Payment item :peyments){
            System.out.println(item.ProcessPayment());
        }
        System.out.println(account1.logBalance());
        System.out.println(account2.logBalance());

    }

}